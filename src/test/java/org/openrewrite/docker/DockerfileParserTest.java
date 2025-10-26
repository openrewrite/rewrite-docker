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

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.docker.Assertions.dockerfile;

class DockerfileParserTest implements RewriteTest {

    @Test
    void simpleFrom() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                """
            )
        );
    }

    @Test
    void fromWithPlatform() {
        rewriteRun(
            dockerfile(
                """
                FROM --platform=linux/amd64 ubuntu:20.04
                """
            )
        );
    }

    @Test
    void fromWithAs() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04 AS base
                """
            )
        );
    }

    @Test
    void simpleRun() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN apt-get update
                """
            )
        );
    }

    @Test
    void runExecForm() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN ["apt-get", "update"]
                """
            )
        );
    }

    @Test
    void multipleInstructions() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN apt-get update
                RUN apt-get install -y curl
                """
            )
        );
    }

    @Test
    void lowercaseInstructions() {
        rewriteRun(
            dockerfile(
                """
                from ubuntu:20.04
                run apt-get update
                """
            )
        );
    }

    @Test
    void mixedCaseInstructions() {
        rewriteRun(
            dockerfile(
                """
                From ubuntu:20.04 as builder
                Run apt-get update
                """
            )
        );
    }

    @Test
    void commentsAtTop() {
        rewriteRun(
            dockerfile(
                """
                # This is a comment
                # Another comment line
                FROM ubuntu:20.04
                """
            )
        );
    }

    @Test
    void commentsInline() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04  # Base image
                RUN apt-get update  # Update packages
                """
            )
        );
    }

    @Test
    void commentsBetweenInstructions() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                # Update and install dependencies
                RUN apt-get update
                # Install curl
                RUN apt-get install -y curl
                """
            )
        );
    }

    @Test
    void emptyLinesAndComments() {
        rewriteRun(
            dockerfile(
                """
                # Base image
                FROM ubuntu:20.04

                # System updates
                RUN apt-get update

                # Install packages
                RUN apt-get install -y curl wget
                """
            )
        );
    }

    @Test
    void multiStageFrom() {
        rewriteRun(
            dockerfile(
                """
                FROM golang:1.20 AS builder
                RUN go build -o app .

                FROM alpine:latest
                RUN apk add --no-cache ca-certificates
                """
            )
        );
    }

    @Test
    void runWithLineContinuation() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN apt-get update && \\
                    apt-get install -y curl && \\
                    rm -rf /var/lib/apt/lists/*
                """
            )
        );
    }

    @Test
    void runWithMultipleFlags() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                RUN --network=none --mount=type=cache,target=/cache apt-get update
                """
            )
        );
    }

    @Disabled("CMD instruction not yet implemented")
    @Test
    void cmdShellForm() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                CMD nginx -g daemon off;
                """
            )
        );
    }

    @Disabled("CMD instruction not yet implemented")
    @Test
    void cmdExecForm() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                CMD ["nginx", "-g", "daemon off;"]
                """
            )
        );
    }

    @Disabled("ENV instruction not yet implemented")
    @Test
    void envSingleLine() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                ENV NODE_VERSION=18.0.0
                ENV PATH=/usr/local/bin:$PATH
                """
            )
        );
    }

    @Disabled("ARG instruction not yet implemented")
    @Test
    void argInstructions() {
        rewriteRun(
            dockerfile(
                """
                ARG BASE_IMAGE=ubuntu:20.04
                FROM ${BASE_IMAGE}
                ARG VERSION
                """
            )
        );
    }

    @Disabled("COPY instruction not yet implemented")
    @Test
    void copyInstructions() {
        rewriteRun(
            dockerfile(
                """
                FROM ubuntu:20.04
                COPY --chown=app:app app.jar /app/
                COPY --from=builder /build/output /app/
                """
            )
        );
    }

    @Disabled("Multiple instructions not yet implemented")
    @Test
    void comprehensiveDockerfile() {
        rewriteRun(
            dockerfile(
                """
                # syntax=docker/dockerfile:1

                # Build stage
                FROM golang:1.20 AS builder
                WORKDIR /build
                COPY go.mod go.sum ./
                RUN go mod download
                COPY . .
                RUN CGO_ENABLED=0 go build -o app

                # Runtime stage
                FROM alpine:latest
                RUN apk add --no-cache ca-certificates
                WORKDIR /app
                COPY --from=builder /build/app .
                EXPOSE 8080
                USER nobody
                ENTRYPOINT ["./app"]
                """
            )
        );
    }
}
