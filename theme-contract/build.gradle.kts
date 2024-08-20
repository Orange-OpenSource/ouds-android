plugins {
    id("com.orange.ouds.gradle.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.ouds.theme"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":tokens-global-raw"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
}