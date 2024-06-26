plugins {
    id("com.orange.uds.gradle.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.uds.core"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
}