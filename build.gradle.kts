plugins {
    id("org.openrewrite.build.recipe-library") version "latest.release"
    id("org.openrewrite.build.moderne-source-available-license") version "latest.release"
}

group = "org.openrewrite.recipe"
description = "Rewrite Docker recipes."

// Configuration for ANTLR generation
val antlrGeneration by configurations.creating {
    extendsFrom(configurations.implementation.get())
}

// Run manually with `./gradlew generateAntlrSources` when you need to regenerate
tasks.register<JavaExec>("generateAntlrSources") {
    mainClass.set("org.antlr.v4.Tool")

    args = listOf(
        "-o", "src/main/java/org/openrewrite/docker/internal/grammar",
        "-package", "org.openrewrite.docker.internal.grammar",
        "-visitor"
    ) + fileTree("src/main/antlr").matching { include("**/*.g4") }.map { it.path }

    classpath = antlrGeneration
}

val rewriteVersion = rewriteRecipe.rewriteVersion.get()
dependencies {
    implementation(platform("org.openrewrite:rewrite-bom:$rewriteVersion"))
    implementation("org.openrewrite:rewrite-core")
    implementation("org.openrewrite:rewrite-yaml")
    implementation("org.antlr:antlr4-runtime:4.13.2")
    implementation("io.micrometer:micrometer-core:1.9.+")

    antlrGeneration("org.antlr:antlr4:4.13.2") {
        exclude(group = "com.ibm.icu", module = "icu4j")
    }

    compileOnly("org.openrewrite:rewrite-test")

    testImplementation("org.openrewrite:rewrite-test")
}
