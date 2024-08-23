/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

plugins {
    id("library")
    alias(libs.plugins.compose.compiler)
}

/**
 * The OudsCustomTheme implementation used by Android Studio previews for the OUDS library.
 * Please change this value if you want to have a custom theme preview for the OUDS library and
 * don't forget to add a dependency to your custom theme in this case.
 */
val previewCustomThemeClass = "com.orange.ouds.theme.orange.OrangeTheme"

android {
    namespace = "com.orange.ouds.core"

    defaultConfig {
        buildConfigField("com.orange.ouds.theme.OudsCustomTheme", "PREVIEW_CUSTOM_THEME", "new $previewCustomThemeClass()")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    api(project(":theme-contract"))
    api(project(":theme-orange"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.material)
}