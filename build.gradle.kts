plugins {
    id("org.openrewrite.build.recipe-library") version "latest.release"
}

group = "org.openrewrite.recipe"
description = "Rewrite Docker recipes."

val rewriteVersion = rewriteRecipe.rewriteVersion.get()
dependencies {
    implementation(platform("org.openrewrite.recipe:rewrite-recipe-bom:$rewriteVersion"))
    implementation("org.openrewrite:rewrite-core:$rewriteVersion")
}

signing {
    isRequired = false
}

configure<nebula.plugin.release.git.base.ReleasePluginExtension> {
    defaultVersionStrategy = nebula.plugin.release.NetflixOssStrategies.SNAPSHOT(project)
}

configure<PublishingExtension> {
    publications {
        named("nebula", MavenPublication::class.java) {
            suppressPomMetadataWarningsFor("runtimeElements")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "moderne"
            url = uri("https://us-west1-maven.pkg.dev/moderne-dev/moderne-recipe")
        }
    }
}
