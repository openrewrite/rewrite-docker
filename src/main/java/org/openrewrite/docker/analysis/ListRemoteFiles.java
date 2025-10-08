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
package org.openrewrite.docker.analysis;

import lombok.EqualsAndHashCode;
import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.SourceFile;
import org.openrewrite.TreeVisitor;
import org.openrewrite.docker.DockerIsoVisitor;
import org.openrewrite.docker.table.RemoteFileReport;
import org.openrewrite.docker.tree.Docker;
import org.openrewrite.marker.SearchResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListRemoteFiles extends ScanningRecipe<List<RemoteFileReport.Row>> {
    @EqualsAndHashCode.Exclude
    transient RemoteFileReport report = new RemoteFileReport(this);

    @Override
    public String getDisplayName() {
        return "List remote files";
    }

    @Override
    public String getDescription() {
        return "Outputs a data table describing the remote files referenced in the Dockerfile.";
    }

    @Override
    public List<RemoteFileReport.Row> getInitialValue(ExecutionContext ctx) {
        return new ArrayList<>();
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getScanner(List<RemoteFileReport.Row> acc) {
        return new DockerIsoVisitor<ExecutionContext>() {
            @Override
            public Docker.Document visitDocument(Docker.Document dockerfile, ExecutionContext ctx) {
                if (dockerfile.getStages() != null) {
                    for (Docker.Stage stage : dockerfile.getStages()) {
                        if (stage != null) {
                            for (Docker child : stage.getChildren()) {
                                if (child instanceof Docker.Add) {
                                    Docker.Add instruction = (Docker.Add) child;
                                    List<Docker.Literal> urls = instruction.getSources().stream()
                                            .filter(s -> s.getText().startsWith("http"))
                                            .collect(Collectors.toList());

                                    if (!urls.isEmpty()) {
                                        urls.forEach(url -> {
                                            acc.add(new RemoteFileReport.Row(
                                                    dockerfile.getSourcePath().toString(),
                                                    url.getText()
                                            ));
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
                if (!acc.isEmpty()) {
                    return SearchResult.found(dockerfile);
                }
                return dockerfile;
            }
        };
    }

    @Override
    public Collection<? extends SourceFile> generate(List<RemoteFileReport.Row> acc, ExecutionContext ctx) {
        if (acc.isEmpty()) {
            return Collections.emptyList();
        }

        for (RemoteFileReport.Row row : acc) {
            report.insertRow(ctx, row);
        }

        acc.clear();

        return Collections.emptyList();
    }
}
