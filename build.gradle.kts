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
