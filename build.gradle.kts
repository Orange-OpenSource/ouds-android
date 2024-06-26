// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.compose.compiler) apply false
}

tasks.register<Delete>("clean") {
    group = "cleanup"
    delete(rootProject.layout.buildDirectory)
}