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

import org.openrewrite.docker.tree.Docker;
import org.junit.jupiter.api.Test;
import org.openrewrite.RecipeRun;
import org.openrewrite.test.RewriteTest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openrewrite.test.SourceSpecs.text;

class AsDockerfileTest implements RewriteTest {
    /**
     * Assert that the recipe run results in a single Dockerfile with a single FROM statement:
     * FROM alpine:latest
     *
     * @param after The recipe run status
     */
    private static void assertSimpleFrom(RecipeRun after) {
        assertThat(after.getChangeset().getAllResults()).hasSize(1);
        assertThat(Objects.requireNonNull(after.getChangeset().getAllResults().get(0).getAfter()))
          .isInstanceOf(Docker.Document.class);

        Docker.Document doc = (Docker.Document) after.getChangeset().getAllResults().get(0).getAfter();
        assertThat(doc).isNotNull();
        assertThat(doc.getStages()).hasSize(1);
        assertThat(doc.getStages().get(0)).isNotNull();

        Docker.From from = (Docker.From) doc.getStages().get(0).getChildren().get(0);
        assertThat(from.getImage().getText()).isEqualTo("alpine");
        assertThat(from.getVersion().getText()).isEqualTo(":latest");
    }

    @Test
    void loadPlainTextAsDockerDocument() {
        rewriteRun(
          spec -> spec.recipe(new AsDockerfile("**/*.build"))
            .afterRecipe(AsDockerfileTest::assertSimpleFrom),
          text(
            """
            FROM alpine:latest
            """,
            """
            FROM alpine:latest
            """,
            spec -> spec.path("build/Dockerfile.build")
          )
        );
    }

    @Test
    void loadPlainTextAllowsMultiplePatterns() {
        rewriteRun(
          spec -> spec.recipe(new AsDockerfile("**/*.build;**/*.docker"))
            .afterRecipe(AsDockerfileTest::assertSimpleFrom),
          text(
            """
            FROM alpine:latest
            """,
            """
            FROM alpine:latest
            """,
            spec -> spec.path("build/build.docker")
          )
        );
    }

    @Test
    void loadPlainTextWithinDeclarativeRecipe() {
        rewriteRun(
          spec -> spec.recipeFromYaml(
            //language=yaml
            """
            type: specs.openrewrite.org/v1beta/recipe
            name: org.openrewrite.docker.AsDockerfileTest
            displayName: AsDockerfileTest
            description: Load plain text as Docker document.
            recipeList:
              - org.openrewrite.docker.AsDockerfile:
                  pattern: "**/*.build"
              - org.openrewrite.docker.ModifyLiteral:
                  matchText: "alpine"
                  replacementText: "myimage"
            """,
            "org.openrewrite.docker.AsDockerfileTest"),
          text(
            """
            FROM alpine:latest
            """,
            """
            FROM myimage:latest
            """,
            spec -> spec.path("build/build.docker")
          )
        );
    }
}
