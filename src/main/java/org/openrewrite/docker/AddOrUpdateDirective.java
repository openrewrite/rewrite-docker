/*
 * Copyright 2022 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.docker;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.openrewrite.*;
import org.openrewrite.docker.tree.Docker;
import org.openrewrite.internal.ListUtils;
import org.openrewrite.marker.SearchResult;

@Value
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class AddOrUpdateDirective extends ScanningRecipe<AddOrUpdateDirective.Scanned> {
    @Option(displayName = "The full directive",
            description = "The full directive without leading comment. One of: syntax, escape, check.",
            example = "check=skip=JSONArgsRecommended;error=true")
    String directive;

    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "Add or update directive";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "Add a directive or update an existing directive.";
    }

    @Override
    public Scanned getInitialValue(ExecutionContext ctx) {
        Scanned scanned = new Scanned();
        scanned.hasDirective = false;
        scanned.requiresUpdate = true;
        return scanned;
    }

    @Override
    public Validated<Object> validate(ExecutionContext ctx) {
        Validated<Object> validated = super.validate();

        if (!directive.matches("^(?i)(syntax|escape|check)\\s*=.+$")) {
            return validated
                    .and(Validated.invalid("directive", directive,
                            "Directive key must be one of: syntax, escape, check."));
        }

        return validated;
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getScanner(Scanned acc) {
        String[] parts = directive.split("=", 2);

        String key = parts[0];
        String value = parts[1];

        acc.targetKey = key;
        acc.targetValue = value;

        return new DockerIsoVisitor<ExecutionContext>() {
            @Override
            public Docker.Document visitDocument(Docker.Document dockerfile, ExecutionContext ctx) {
                dockerfile = super.visitDocument(dockerfile, ctx);
                Docker.Document d = dockerfile;
                if (acc.requiresUpdate) {
                    return SearchResult.found(d);
                }
                return d;
            }

            @Override
            public Docker.Directive visitDirective(Docker.Directive directive, ExecutionContext ctx) {
                directive = super.visitDirective(directive, ctx);
                Docker.Directive d = directive;
                if (d.getKey().equalsIgnoreCase(key)) {
                    acc.hasDirective = true;
                    if (d.getValue().trim().equals(value.trim())) {
                        acc.requiresUpdate = false;
                    } else {
                        return SearchResult.found(d);
                    }
                }

                return d;
            }
        };
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor(Scanned acc) {
        return Preconditions.check(acc.requiresUpdate, new DockerIsoVisitor<ExecutionContext>() {
            @Override
            public Docker.Document visitDocument(Docker.Document dockerfile, ExecutionContext ctx) {
                dockerfile = super.visitDocument(dockerfile, ctx); // visit to hit the directive case

                if (!acc.hasDirective) {
                    dockerfile = dockerfile.withStages(
                            ListUtils.mapFirst(dockerfile.getStages(), stage ->
                            {
                                if (stage == null) {
                                    return null;
                                }

                                return stage.withChildren(ListUtils.insert(stage.getChildren(), Docker.Directive.build(acc.targetKey, acc.targetValue), 0));
                            }));
                }
                return dockerfile;
            }

            @Override
            public Docker.Directive visitDirective(Docker.Directive directive, ExecutionContext ctx) {
                if (acc.requiresUpdate && directive.getKey().equalsIgnoreCase(acc.targetKey)) {
                    acc.requiresUpdate = false;
                    return directive.withKey(acc.targetKey).withValue(acc.targetValue).withMarkers(directive.getMarkers());
                }
                return directive;
            }
        });
    }

    public static class Scanned {
        boolean requiresUpdate;
        boolean hasDirective;
        String targetKey;
        String targetValue;
    }
}
