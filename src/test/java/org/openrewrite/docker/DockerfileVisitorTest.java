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

import org.junit.jupiter.api.Test;
import org.openrewrite.DocumentExample;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.docker.Assertions.dockerfile;

class DockerfileVisitorTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new ReplaceAddWithCopy());
    }

	@DocumentExample @Test void replaceAddWithCopy() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ADD app.jar /app/
                RUN echo "test"
                """,
                """
                FROM ubuntu:20.04
                COPY app.jar /app/
                RUN echo "test"
                """
            )
        );
    }

    @Test
    void replaceAddWithCopyMultipleSources() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ADD file1.txt file2.txt /app/
                """,
                """
                FROM ubuntu:20.04
                COPY file1.txt file2.txt /app/
                """
            )
        );
    }

    @Test
    void replaceAddWithCopyWithFlags() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ADD --chown=app:app app.jar /app/
                """,
                """
                FROM ubuntu:20.04
                COPY --chown=app:app app.jar /app/
                """
            )
        );
    }

    @Test
    void replaceAddWithHeredoc() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ADD <<EOF /app/config.txt
                # Configuration file
                setting1=value1
                setting2=value2
                EOF
                """,
                """
                FROM ubuntu:20.04
                COPY <<EOF /app/config.txt
                # Configuration file
                setting1=value1
                setting2=value2
                EOF
                """
            )
        );
    }

    @Test
    void replaceMultipleAdd() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ADD app.jar /app/
                ADD config.xml /config/
                RUN echo "test"
                ADD final.txt /final/
                """,
                """
                FROM ubuntu:20.04
                COPY app.jar /app/
                COPY config.xml /config/
                RUN echo "test"
                COPY final.txt /final/
                """
            )
        );
    }

    @Test
    void dontChangeCopyInstructions() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                COPY app.jar /app/
                RUN echo "test"
                """
            )
        );
    }

    /**
     * Recipe that replaces ADD instructions with COPY instructions.
     */
    static class ReplaceAddWithCopy extends Recipe {

        @Override
        public String getDisplayName() {
            return "Replace ADD with COPY";
        }

        @Override
        public String getDescription() {
            return "Replaces all ADD instructions with COPY instructions in Dockerfiles.";
        }

        @Override
        public DockerfileVisitor<ExecutionContext> getVisitor() {
            return new DockerfileVisitor<>() {
                @Override
                public Dockerfile visitAdd(Dockerfile.Add add, ExecutionContext ctx) {
                    // Visit children first
                    Dockerfile.Add a = (Dockerfile.Add) super.visitAdd(add, ctx);

                    // Transform ADD to COPY
                    return new Dockerfile.Copy(
                      a.getId(),
                      a.getPrefix(),
                      a.getMarkers(),
                      "COPY",  // Replace keyword
                      a.getFlags(),
                      a.getHeredoc(),
                      a.getSources(),
                      a.getDestination()
                    );
                }
            };
        }
    }
}
