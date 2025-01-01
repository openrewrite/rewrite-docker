![Logo](https://github.com/openrewrite/rewrite/raw/main/doc/logo-oss.png)
# Rewrite Docker

Docker recipes for studying and transforming Docker usage.

[![ci](https://github.com/openrewrite/rewrite-docker/actions/workflows/ci.yml/badge.svg)](https://github.com/openrewrite/rewrite-docker/actions/workflows/ci.yml)
[![Maven Central](https://img.shields.io/maven-central/v/org.openrewrite.recipe/rewrite-docker.svg)](https://mvnrepository.com/artifact/org.openrewrite.recipe/rewrite-docker)
[![Revved up by Develocity](https://img.shields.io/badge/Revved%20up%20by-Develocity-06A0CE?logo=Gradle&labelColor=02303A)](https://ge.openrewrite.org/scans)

### What is this?

This project is an OpenRewrite module providing recipes for analyzing and modifying Docker configurations.  It allows developers to automate tasks related to Docker image management and improve consistency across projects.


### Capabilities

This module offers recipes to perform several key functions:

* **Docker Image Usage Analysis:** Identify all locations where specific Docker images are used across your project's codebase. This includes Dockerfiles, Kubernetes manifests, and other configuration files. This capability is crucial for understanding the impact of changes to base images or identifying potential vulnerabilities.

* **Automated Docker Image Updates:** Streamline the process of updating Docker images to newer versions, ensuring your applications utilize the latest security patches and features.

* **Improved Consistency and Maintainability:** This module helps enforce consistent Docker image usage across your projects, reducing the likelihood of discrepancies and promoting maintainability.

Browse [a selection of recipes available through this module in the recipe catalog](https://docs.openrewrite.org/recipes/docker).

## Contributing

We appreciate all types of contributions. See the [contributing guide](https://github.com/openrewrite/.github/blob/main/CONTRIBUTING.md) for detailed instructions on how to get started.