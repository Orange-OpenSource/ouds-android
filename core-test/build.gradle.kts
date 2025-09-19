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

android {
    namespace = "com.orange.ouds.core.test"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":foundation"))
    api(project(":core"))

    implementation(libs.paparazzi)
    api(libs.androidx.compose.ui.test.junit4)
    api(libs.mockito.android)
    api(libs.mockito.kotlin)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    api(libs.androidx.compose.ui.test.manifest)
}
