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
}
