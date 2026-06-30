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
//    id("library")
    id("paparazzi")

    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id(libs.plugins.android.kotlin.multiplatform.library.get().pluginId)
    alias(libs.plugins.paparazzi)
}

kotlin {
    jvmToolchain(17)

    compilerOptions {
        freeCompilerArgs.addAll(
            "-opt-in=com.orange.ouds.foundation.RestrictedOudsApi",
            "-opt-in=com.orange.ouds.foundation.ExperimentalOudsApi",
            "-opt-in=com.orange.ouds.foundation.InternalOudsApi"
        )
    }

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    android {
        namespace = "com.orange.ouds.core"
        compileSdk = libs.versions.androidCompileSdk.get().toInt()
        minSdk = libs.versions.androidMinSdk.get().toInt()

        androidResources.enable = true
        
        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    // A step-by-step guide on how to include this library in an XCode
    // project can be found here:
    // https://developer.android.com/kotlin/multiplatform/migrate
    val xcfName = "oudsCore"

//    iosX64 {
//        binaries.framework {
//            baseName = xcfName
//        }
//    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(libs.compose.ui)
                api(libs.compose.ui.tooling.preview)


                //dokkaPlugin(project(":dokka-plugin"))
                implementation(project(":foundation"))
                api(project(":theme-contract"))
                // compileOnly dependencies on themes are needed for previews
                //    compileOnly(project(":theme-orange"))
                //    compileOnly(project(":theme-orange-compact"))
                //    compileOnly(project(":theme-sosh"))
                compileOnly(project(":theme-wireframe"))

                //implementation(platform(libs.androidx.compose.bom))
                //implementation(libs.androidx.compose.material.icons.core)
                implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")
                implementation(libs.compose.material3)
                implementation(libs.compose.ui.tooling.preview)
                //implementation(libs.androidx.constraintlayout.compose)
                implementation("org.jetbrains.compose.foundation:foundation-layout")
                implementation(libs.material)

                implementation(libs.compose.components.resources)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
                implementation(libs.androidx.core)
            }
        }

        androidUnitTest {
            dependencies {
                implementation(project(":core-test"))
                //testImplementation(project(":theme-orange"))
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
//                implementation(libs.androidx.junit)
//                implementation(libs.androidx.runner)
//                implementation(libs.core)

                //androidTestImplementation(project(":theme-orange"))
                implementation(libs.androidx.compose.ui.test.junit4)
                implementation(libs.kotlin.reflect)
                implementation(libs.mockito.android)
                implementation(libs.mockito.kotlin)
                // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
                //debugImplementation(libs.androidx.compose.ui.test.manifest)
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }
}

// TODO Remove when https://github.com/google/guava/issues/6567 is fixed.
// See also: https://github.com/google/guava/issues/6801.
//dependencies.constraints {
//    testImplementation("com.google.guava:guava") {
//        attributes {
//            attribute(
//                TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
//                objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM)
//            )
//        }
//        because(
//            "Paparazzi's layoutlib and sdk-common depend on Guava's -jre published variant." +
//                    "See https://github.com/cashapp/paparazzi/issues/906."
//        )
//    }
//}
