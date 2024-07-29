plugins {
    id("com.orange.ouds.gradle.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.ouds.theme.orange"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":theme-contract"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
}