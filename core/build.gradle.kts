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
    id("com.orange.ouds.gradle.library")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
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

// TODO Remove when https://github.com/google/guava/issues/6567 is fixed.
// See also: https://github.com/google/guava/issues/6801.
dependencies.constraints {
    testImplementation("com.google.guava:guava") {
        attributes {
            attribute(
                TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM)
            )
        }
        because(
            "Paparazzi's layoutlib and sdk-common depend on Guava's -jre published variant." +
                "See https://github.com/cashapp/paparazzi/issues/906."
        )
    }
}