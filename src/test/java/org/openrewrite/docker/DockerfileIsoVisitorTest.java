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
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ExpectedToFail;
import org.openrewrite.DocumentExample;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.docker.tree.Dockerfile;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static java.util.Collections.singletonList;
import static org.openrewrite.Tree.randomId;
import static org.openrewrite.docker.Assertions.dockerfile;

class DockerfileIsoVisitorTest implements RewriteTest {

	@DocumentExample @Test void replaceFromImage() {
        rewriteRun(
            spec -> spec.recipe(new ReplaceFromImage("ubuntu:20.04", "ubuntu:22.04")),
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN apt-get update
                """,
                """
                FROM ubuntu:22.04
                RUN apt-get update
                """
            )
        );
    }

    @ExpectedToFail("Not yet implemented")
    @Test
    void replaceFromImageWithFlags() {
        rewriteRun(
            spec -> spec.recipe(new ReplaceFromImage("ubuntu:20.04", "ubuntu:22.04")),
            dockerfile(
                """
                FROM --platform=linux/amd64 ubuntu:20.04
                RUN apt-get update
                """,
                """
                FROM --platform=linux/amd64 ubuntu:22.04
                RUN apt-get update
                """
            )
        );
    }

    @Test
    void replaceFromImageWithAs() {
        rewriteRun(
            spec -> spec.recipe(new ReplaceFromImage("golang:1.20", "golang:1.21")),
            dockerfile(
                """
                FROM golang:1.20 AS builder
                RUN go build -o app .

                FROM alpine:latest
                COPY --from=builder /app /app
                """,
                """
                FROM golang:1.21 AS builder
                RUN go build -o app .

                FROM alpine:latest
                COPY --from=builder /app /app
                """
            )
        );
    }

    @Test
    void replaceFromImageMultipleStages() {
        rewriteRun(
            spec -> spec.recipe(new ReplaceFromImage("ubuntu:20.04", "ubuntu:22.04")),
            dockerfile(
                """
                FROM ubuntu:20.04 AS base
                RUN apt-get update

                FROM ubuntu:20.04 AS builder
                RUN apt-get install -y build-essential

                FROM alpine:latest
                COPY --from=builder /app /app
                """,
                """
                FROM ubuntu:22.04 AS base
                RUN apt-get update

                FROM ubuntu:22.04 AS builder
                RUN apt-get install -y build-essential

                FROM alpine:latest
                COPY --from=builder /app /app
                """
            )
        );
    }

    @Test
    void dontReplaceNonMatchingFromImage() {
        rewriteRun(
            spec -> spec.recipe(new ReplaceFromImage("ubuntu:20.04", "ubuntu:22.04")),
            dockerfile(
                """
                FROM alpine:latest
                RUN apk add --no-cache ca-certificates
                """
            )
        );
    }

    /**
     * Recipe that replaces a specific FROM image with another.
     */
    static class ReplaceFromImage extends Recipe {
        private final String oldImage;
        private final String newImage;

        @JsonCreator
        ReplaceFromImage(@JsonProperty("oldImage") String oldImage, @JsonProperty("newImage") String newImage) {
            this.oldImage = oldImage;
            this.newImage = newImage;
        }

        @Override
        public String getDisplayName() {
            return "Replace FROM image";
        }

        @Override
        public String getDescription() {
            return "Replaces a specific base image in FROM instructions.";
        }

        @Override
        public DockerfileIsoVisitor<ExecutionContext> getVisitor() {
            return new DockerfileIsoVisitor<>() {
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

                    if (originalPlainText != null && originalPlainText.getText().equals(oldImage)) {
                        // Replace the image argument, preserving the original prefix
                        Dockerfile.Argument newImageArg = f.getImage().withContents(
                            singletonList(
                                new Dockerfile.PlainText(
                                    randomId(),
                                    originalPlainText.getPrefix(),
                                    originalPlainText.getMarkers(),
                                    newImage
                                )
                            )
                        );

                        return f.withImage(newImageArg);
                    }

                    return f;
                }
            };
        }
    }
}
