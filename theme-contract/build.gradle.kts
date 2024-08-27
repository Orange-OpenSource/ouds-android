plugins {
    id("library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.ouds.theme"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":global-raw-tokens"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
}