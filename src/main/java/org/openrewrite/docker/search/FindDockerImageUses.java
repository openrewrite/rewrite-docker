/*
 * Copyright 2024 the original author or authors.
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
package org.openrewrite.docker.search;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.jspecify.annotations.Nullable;
import org.openrewrite.*;
import org.openrewrite.docker.DockerImageVersion;
import org.openrewrite.docker.table.DockerBaseImages;
import org.openrewrite.docker.trait.ImageMatcher;
import org.openrewrite.marker.SearchResult;
import org.openrewrite.text.Find;
import org.openrewrite.text.PlainText;
import org.openrewrite.trait.Reference;

import java.nio.file.Path;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class FindDockerImageUses extends Recipe {
    transient DockerBaseImages dockerBaseImages = new DockerBaseImages(this);

    @Override
    public String getDisplayName() {
        return "Find uses of docker base images";
    }

    @Override
    public String getDescription() {
        return "Produce an impact analysis of base images used in Dockerfiles, .gitlab-ci files, Kubernetes Deployment file, etc.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new TreeVisitor<Tree, ExecutionContext>() {

            @Override
            public @Nullable Tree visit(@Nullable Tree tree, ExecutionContext ctx) {
                if (tree instanceof SourceFileWithReferences) {
                    SourceFileWithReferences sourceFile = (SourceFileWithReferences) tree;
                    Path sourcePath = sourceFile.getSourcePath();
                    Collection<Reference> references = sourceFile.getReferences().findMatches(new ImageMatcher());
                    Map<Tree, List<Reference>> matches = new HashMap<>();
                    for (Reference ref : references) {
                        DockerImageVersion from = DockerImageVersion.of(ref.getValue());
                        dockerBaseImages.insertRow(ctx,
                                new DockerBaseImages.Row(sourcePath.toString(), from.getImageName(), from.getVersion())
                        );
                        matches.computeIfAbsent(ref.getTree(), t -> new ArrayList<>()).add(ref);
                    }
                    return new ReferenceFindSearchResultVisitor(matches).visit(tree, ctx, getCursor());
                }
                return tree;
            }
        };
    }

    @EqualsAndHashCode(callSuper = false)
    @Value
    private static class ReferenceFindSearchResultVisitor extends TreeVisitor<Tree, ExecutionContext> {
        Map<Tree, List<Reference>> matches;

        @Override
        public @Nullable Tree postVisit(Tree tree, ExecutionContext ctx) {
            List<Reference> references = matches.get(tree);
            if (references != null) {
                if (tree instanceof PlainText) {
                    String find = references.stream().map(Reference::getValue).sorted().collect(joining("|"));
                    return new Find(find, true, null, null, null, null, true, null)
                            .getVisitor()
                            .visitNonNull(tree, ctx);
                }
                return SearchResult.found(tree, references.get(0).getValue());
            }
            return tree;
        }
    }
}
