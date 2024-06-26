plugins {
    id("com.orange.uds.gradle.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.orange.uds.foundation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
}