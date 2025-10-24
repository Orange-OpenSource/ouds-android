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
    id("dokka")
    id("library")
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.orange.ouds.core"

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-opt-in=com.orange.ouds.foundation.InternalOudsApi")
        }
    }
}

dependencies {
    dokkaPlugin(project(":dokka-plugin"))
    implementation(project(":foundation"))
    api(project(":theme-contract"))
    // compileOnly dependencies on themes are needed for previews
    compileOnly(project(":theme-orange"))
    compileOnly(project(":theme-sosh"))
    compileOnly(project(":theme-wireframe"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.material)

    testImplementation(project(":theme-orange"))
    androidTestImplementation(project(":theme-orange"))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.kotlin.reflect)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.kotlin)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.androidx.compose.ui.test.manifest)
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