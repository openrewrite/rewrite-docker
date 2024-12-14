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
package org.openrewrite.docker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openrewrite.DocumentExample;
import org.openrewrite.docker.search.FindDockerImageUses;
import org.openrewrite.docker.table.DockerBaseImages;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openrewrite.test.SourceSpecs.text;

class FindDockerImagesUsedTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new FindDockerImageUses());
    }

    @DocumentExample
    @ParameterizedTest
    @ValueSource(strings = {"Dockerfile", "Containerfile"})
    void dockerfile(String path) {
        rewriteRun(
          text(
            //language=Dockerfile
            """
              FROM nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04
              LABEL maintainer="Hugging Face"
              ARG DEBIAN_FRONTEND=noninteractive
              SHELL ["sh", "-lc"]
              """,
            """
              ~~(nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04)~~>FROM nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04
              LABEL maintainer="Hugging Face"
              ARG DEBIAN_FRONTEND=noninteractive
              SHELL ["sh", "-lc"]
              """,
            spec -> spec.path(path)
          )
        );
    }

    @Test
    void multistageDockerfile() {
        rewriteRun(
          spec -> spec.dataTable(DockerBaseImages.Row.class, rows -> assertThat(rows)
            .containsOnly(new DockerBaseImages.Row("nvidia/cuda", "11.8.0-cudnn8-devel-ubuntu20.04"))),
          text(
            //language=Dockerfile
            """
              FROM nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04 AS base
              LABEL maintainer="Hugging Face"
              ARG DEBIAN_FRONTEND=noninteractive
              SHELL ["sh", "-lc"]
              """,
            """
              ~~(nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04)~~>FROM nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04 AS base
              LABEL maintainer="Hugging Face"
              ARG DEBIAN_FRONTEND=noninteractive
              SHELL ["sh", "-lc"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }
}
