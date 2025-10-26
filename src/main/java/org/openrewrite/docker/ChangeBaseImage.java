/*
 * Copyright 2025 the original author or authors.
 * <p>
 * Licensed under the Moderne Source Available License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://docs.moderne.io/licensing/moderne-source-available-license
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openrewrite.docker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Option;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.docker.tree.Dockerfile;

import static java.util.Collections.singletonList;
import static org.openrewrite.Tree.randomId;

@Value
@EqualsAndHashCode(callSuper = false)
public class ChangeBaseImage extends Recipe {

    @Option(displayName = "Old image name",
            description = "The old image name to replace. Supports glob patterns.",
            example = "ubuntu:20.04")
    String oldImageName;

    @Option(displayName = "New image name",
            description = "The new image name to use.",
            example = "ubuntu:22.04")
    String newImageName;

    @JsonCreator
    public ChangeBaseImage(@JsonProperty("oldImageName") String oldImageName,
                           @JsonProperty("newImageName") String newImageName) {
        this.oldImageName = oldImageName;
        this.newImageName = newImageName;
    }

    @Override
    public String getDisplayName() {
        return "Change Docker base image";
    }

    @Override
    public String getDescription() {
        return "Change the base image in a Dockerfile FROM instruction.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new DockerfileIsoVisitor<ExecutionContext>() {
            @Override
            public Dockerfile.From visitFrom(Dockerfile.From from, ExecutionContext ctx) {
                // Visit children first
                Dockerfile.From f = super.visitFrom(from, ctx);

                // Find the PlainText content containing the image name
                Dockerfile.PlainText originalPlainText = f.getImage().getContents().stream()
                    .filter(content -> content instanceof Dockerfile.PlainText)
                    .map(content -> (Dockerfile.PlainText) content)
                    .findFirst()
                    .orElse(null);

                if (originalPlainText != null &&
                    matchesGlob(originalPlainText.getText(), oldImageName) &&
                    !originalPlainText.getText().equals(newImageName)) {
                    // Replace the image argument, preserving the original prefix
                    Dockerfile.Argument newImageArg = f.getImage().withContents(
                        singletonList(
                            new Dockerfile.PlainText(
                                randomId(),
                                originalPlainText.getPrefix(),
                                originalPlainText.getMarkers(),
                                newImageName
                            )
                        )
                    );

                    return f.withImage(newImageArg);
                }

                return f;
            }
        };
    }

    private boolean matchesGlob(String value, @Nullable String glob) {
        if (glob == null || value == null) {
            return false;
        }

        // If no glob patterns, do exact match
        if (!glob.contains("*") && !glob.contains("?")) {
            return value.equals(glob);
        }

        // Convert glob to regex by iterating through characters
        StringBuilder regex = new StringBuilder();
        for (int i = 0; i < glob.length(); i++) {
            char c = glob.charAt(i);
            switch (c) {
                case '*':
                    regex.append(".*");
                    break;
                case '?':
                    regex.append(".");
                    break;
                // Escape regex special characters
                case '.':
                case '+':
                case '(':
                case ')':
                case '[':
                case ']':
                case '{':
                case '}':
                case '^':
                case '$':
                case '|':
                case '\\':
                    regex.append("\\").append(c);
                    break;
                default:
                    regex.append(c);
            }
        }

        return value.matches(regex.toString());
    }
}
