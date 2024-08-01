plugins {
    id("com.orange.ouds.gradle.library")
    alias(libs.plugins.compose.compiler)
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

android {
    namespace = "com.orange.ouds.theme.whitelabel"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":theme-contract"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
}