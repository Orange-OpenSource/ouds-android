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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
}