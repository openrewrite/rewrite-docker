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

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openrewrite.test.SourceSpecs.text;
import static org.openrewrite.yaml.Assertions.yaml;

class FindDockerImagesUsedTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new FindDockerImageUses());
    }

    @DocumentExample
    @Test
    void yamlFileWithMultipleImages() {
        rewriteRun(
          assertImages("golang:1.7.0", "golang:1.7.0", "golang:1.7.3"),
          yaml(
            """
              test:
                image: golang:1.7.3

              accp:
                image: golang:1.7.0

              prod:
                image: golang:1.7.0
              """,
            """
              test:
                image: ~~(golang:1.7.3)~~>golang:1.7.3

              accp:
                image: ~~(golang:1.7.0)~~>golang:1.7.0

              prod:
                image: ~~(golang:1.7.0)~~>golang:1.7.0
              """
          )
        );
    }

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
              FROM ~~(nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04)~~>nvidia/cuda:11.8.0-cudnn8-devel-ubuntu20.04
              LABEL maintainer="Hugging Face"
              ARG DEBIAN_FRONTEND=noninteractive
              SHELL ["sh", "-lc"]
              """,
            spec -> spec.path(path)
          )
        );
    }

    @Test
    void dockerFile() {
        rewriteRun(
          assertImages("golang:1.7.3"),
          text(
            //language=Dockerfile
            """
              FROM golang:1.7.3 AS builder
              # another FROM to prove aliases can be used
              FROM builder AS stage1
              WORKDIR /go/src/github.com/alexellis/href-counter/
              RUN go get -d -v golang.org/x/net/html
              COPY app.go .
              RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o app .
              """,
            """
              FROM ~~(golang:1.7.3)~~>golang:1.7.3 AS builder
              # another FROM to prove aliases can be used
              FROM builder AS stage1
              WORKDIR /go/src/github.com/alexellis/href-counter/
              RUN go get -d -v golang.org/x/net/html
              COPY app.go .
              RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o app .
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    @Test
    void dockerMultipleStageFileWithLowerCaseText() {
        rewriteRun(
          assertImages("alpine:latest", "golang:1.7.3"),
          text(
            //language=Dockerfile
            """
              FROM golang:1.7.3 as builder
              WORKDIR /go/src/github.com/alexellis/href-counter/
              RUN go get -d -v golang.org/x/net/html
              COPY app.go .
              RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o app .

              from alpine:latest
              run apk --no-cache add ca-certificates
              workdir /root/
              copy --from=builder /go/src/github.com/alexellis/href-counter/app .
              cmd ["./app"]
              """,
            """
              FROM ~~(golang:1.7.3)~~>golang:1.7.3 as builder
              WORKDIR /go/src/github.com/alexellis/href-counter/
              RUN go get -d -v golang.org/x/net/html
              COPY app.go .
              RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o app .

              from ~~(alpine:latest)~~>alpine:latest
              run apk --no-cache add ca-certificates
              workdir /root/
              copy --from=builder /go/src/github.com/alexellis/href-counter/app .
              cmd ["./app"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    @Test
    void dockerMultipleStageFileWithImageInFromOption() {
        rewriteRun(
          assertImages("alpine:latest", "nginx:latest"),
          text(
            //language=Dockerfile
            """
              FROM alpine:latest
              COPY --from=nginx:latest /etc/nginx/nginx.conf /etc/nginx/
              COPY --from=nginx:latest /usr/share/nginx/html /usr/share/nginx/html
              RUN apk add --no-cache bash
              WORKDIR /usr/share/nginx/html
              CMD ["ls", "-la", "/usr/share/nginx/html"]
              """,
            """
              FROM ~~(alpine:latest)~~>alpine:latest
              COPY --from=~~(nginx:latest)~~>nginx:latest /etc/nginx/nginx.conf /etc/nginx/
              COPY --from=~~(nginx:latest)~~>nginx:latest /usr/share/nginx/html /usr/share/nginx/html
              RUN apk add --no-cache bash
              WORKDIR /usr/share/nginx/html
              CMD ["ls", "-la", "/usr/share/nginx/html"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    @Test
    void dockerMultipleStageFileWithFromOptionAsStageNumber() {
        rewriteRun(
          assertImages("golang:1.23", "scratch"),
          text(
            //language=Dockerfile
            """
              # syntax=docker/dockerfile:1
              FROM golang:1.23
              WORKDIR /src
              COPY <<EOF ./main.go
              package main

              import "fmt"

              func main() {
                fmt.Println("hello, world")
              }
              EOF

              RUN go build -o /bin/hello ./main.go

              FROM scratch
              COPY --from=0 /bin/hello /bin/hello
              CMD ["/bin/hello"]
              """,
            """
              # syntax=docker/dockerfile:1
              FROM ~~(golang:1.23)~~>golang:1.23
              WORKDIR /src
              COPY <<EOF ./main.go
              package main

              import "fmt"

              func main() {
                fmt.Println("hello, world")
              }
              EOF

              RUN go build -o /bin/hello ./main.go

              FROM ~~(scratch)~~>scratch
              COPY --from=0 /bin/hello /bin/hello
              CMD ["/bin/hello"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }


    @Test
    void platformDockerfile() {
        rewriteRun(
          assertImages("alpine:latest"),
          text(
            //language=Dockerfile
            """
              FROM --platform=linux/arm64 alpine:latest
              RUN echo "Hello from ARM64!" > /message.txt
              CMD ["cat", "/message.txt"]
              """,
            """
              FROM --platform=linux/arm64 ~~(alpine:latest)~~>alpine:latest
              RUN echo "Hello from ARM64!" > /message.txt
              CMD ["cat", "/message.txt"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    @Test
    void dockerFileIgnoreComment() {
        rewriteRun(
          assertImages("alpine:latest", "eclipse-temurin:17-jammy"),
          text(
            //language=Dockerfile
            """
              # FROM alpine
              FROM alpine:latest
              # more comments
              FROM eclipse-temurin:17-jammy
              """,
            """
              # FROM alpine
              FROM ~~(alpine:latest)~~>alpine:latest
              # more comments
              FROM ~~(eclipse-temurin:17-jammy)~~>eclipse-temurin:17-jammy
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"aarch64-dockerfile", "Dockerfile.aarch64"})
    void dockerFileWithSuffixOrPrefix(String fileName) {
        rewriteRun(
          assertImages("ghcr.io/cross-rs/aarch64-unknown-linux-gnu:main"),
          text(
            //language=Dockerfile
            """
              FROM ghcr.io/cross-rs/aarch64-unknown-linux-gnu:main

              RUN apt-get update && apt-get install --assume-yes clang zlib1g-dev libelf-dev
              RUN dpkg --add-architecture arm64
              RUN apt-get update && apt-get install --assume-yes zlib1g-dev:arm64 libelf-dev:arm64
              """,
            """
              FROM ~~(ghcr.io/cross-rs/aarch64-unknown-linux-gnu:main)~~>ghcr.io/cross-rs/aarch64-unknown-linux-gnu:main

              RUN apt-get update && apt-get install --assume-yes clang zlib1g-dev libelf-dev
              RUN dpkg --add-architecture arm64
              RUN apt-get update && apt-get install --assume-yes zlib1g-dev:arm64 libelf-dev:arm64
              """,
            spec -> spec.path(fileName)
          )
        );
    }

    @Test
    void gitlabCIFile() {
        rewriteRun(
          assertImages("maven:latest"),
          //language=yaml
          yaml(
            """
              image: maven:latest

              variables:
                MAVEN_CLI_OPTS: "-s .m2/settings.xml --batch-mode"
                MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

              cache:
                paths:
                  - .m2/repository/
                  - target/

              build:
                stage: build
                script:
                  - mvn $MAVEN_CLI_OPTS compile

              test:
                stage: test
                script:
                  - mvn $MAVEN_CLI_OPTS test

              deploy:
                stage: deploy
                script:
                  - mvn $MAVEN_CLI_OPTS deploy
                only:
                  - master
              """,
            """
              image: ~~(maven:latest)~~>maven:latest

              variables:
                MAVEN_CLI_OPTS: "-s .m2/settings.xml --batch-mode"
                MAVEN_OPTS: "-Dmaven.repo.local=.m2/repository"

              cache:
                paths:
                  - .m2/repository/
                  - target/

              build:
                stage: build
                script:
                  - mvn $MAVEN_CLI_OPTS compile

              test:
                stage: test
                script:
                  - mvn $MAVEN_CLI_OPTS test

              deploy:
                stage: deploy
                script:
                  - mvn $MAVEN_CLI_OPTS deploy
                only:
                  - master
              """,
            spec -> spec.path(".gitlab-ci")
          )
        );
    }

    @Test
    void githubActions() {
        rewriteRun(
          assertImages("ghcr.io/owner/image"),
          //language=yaml
          yaml(
            """
              container:
                image: ghcr.io/owner/image
              """,
            """
              container:
                image: ~~(ghcr.io/owner/image)~~>ghcr.io/owner/image
              """,
            spec -> spec.path(".github/workflows/ci.yml")
          )
        );
    }

    @Test
    void kubernetesFile() {
        rewriteRun(
          assertImages("image", "app:v1.2.3", "account/image:latest", "repo.id/account/bucket/image:v1.2.3@digest"),
          //language=yaml
          yaml(
            """
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - image: image
                    name: image-container
              ---
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - name: my-container
                    image: app:v1.2.3
                initContainers:
                  - image: account/image:latest
                    name: my-init-container
              ---
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - image: repo.id/account/bucket/image:v1.2.3@digest
                    name: my-container
              """,
            """
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - image: ~~(image)~~>image
                    name: image-container
              ---
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - name: my-container
                    image: ~~(app:v1.2.3)~~>app:v1.2.3
                initContainers:
                  - image: ~~(account/image:latest)~~>account/image:latest
                    name: my-init-container
              ---
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - image: ~~(repo.id/account/bucket/image:v1.2.3@digest)~~>repo.id/account/bucket/image:v1.2.3@digest
                    name: my-container
              """,
            spec -> spec.path(".gitlab-ci")
          )
        );
    }

    @Test
    void dockerFileIgnoreDescription() {
        rewriteRun(
          assertImages("docker.io/python:3.13-bookworm"),
          text(
            //language=Dockerfile
            """
              FROM docker.io/python:3.13-bookworm
              LABEL authors='Richard Hendricks [rich@piedpiper.io]'
              LABEL description='Parse and process data from UAT access logs.'

              WORKDIR /app
              COPY requirements.txt
              RUN pip install -r requirements.txt
              COPY . .
              CMD ["python", "app.py"]
              """,
            """
              FROM ~~(docker.io/python:3.13-bookworm)~~>docker.io/python:3.13-bookworm
              LABEL authors='Richard Hendricks [rich@piedpiper.io]'
              LABEL description='Parse and process data from UAT access logs.'

              WORKDIR /app
              COPY requirements.txt
              RUN pip install -r requirements.txt
              COPY . .
              CMD ["python", "app.py"]
              """,
            spec -> spec.path("Dockerfile")
          )
        );
    }

    private static Consumer<RecipeSpec> assertImages(String... expected) {
        return spec -> spec.recipe(new FindDockerImageUses())
          .dataTable(DockerBaseImages.Row.class, rows ->
            assertThat(rows)
              .hasSameSizeAs(expected)
              .extracting(it -> it.getImageName() + (it.getTag() == null ? "" : ":" + it.getTag()))
              .containsExactlyInAnyOrder(expected)
          );
    }
}
