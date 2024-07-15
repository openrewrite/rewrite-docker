/*
 * Copyright 2024 the original author or authors.
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
package org.openrewrite.docker.search;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.docker.DockerImageVersion;
import org.openrewrite.docker.table.DockerBaseImages;
import org.openrewrite.marker.SearchResult;
import org.openrewrite.maven.table.DependenciesInUse;

import java.util.List;
import java.util.stream.Collectors;

import static org.openrewrite.docker.trait.Traits.dockerfile;

public class FindDockerImageUses extends Recipe {
    transient DockerBaseImages dockerBaseImages = new DockerBaseImages(this);

    /**
     * This is a bit of a stretch, but helps us to use pre-existing visualizations for
     * categorizing dependency use.
     */
    transient DependenciesInUse dependenciesInUse = new DependenciesInUse(this);

    @Override
    public String getDisplayName() {
        return "Find uses of docker base images";
    }

    @Override
    public String getDescription() {
        return "Produce an impact analysis of base images used in Dockerfiles.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return dockerfile().asVisitor((docker, ctx) -> {
            List<DockerImageVersion> froms = docker.getFroms();
            if (!froms.isEmpty()) {
                for (DockerImageVersion from : froms) {
                    String version = from.getVersion() == null ? "" : from.getVersion();
                    dockerBaseImages.insertRow(ctx, new DockerBaseImages.Row(
                            from.getImageName(),
                            version
                    ));
                    dependenciesInUse.insertRow(ctx, new DependenciesInUse.Row(
                            "", "docker", "", from.getImageName(),
                            version.isEmpty() ? "unversioned" : version, null, "", 0
                    ));
                }
                return SearchResult.found(docker.getTree(),
                        froms.stream().map(DockerImageVersion::toString)
                                .collect(Collectors.joining(", ")));
            }
            return docker.getTree();
        });
    }
}
