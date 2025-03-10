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

import com.orange.ouds.gradle.MavenCentralPublishPluginExtension
import org.gradle.accessors.dm.LibrariesForLibs

private val libs = the<LibrariesForLibs>() // https://github.com/gradle/gradle/issues/15383

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-central-publish")
}

android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        val targetSdk = libs.versions.androidTargetSdk.get().toInt()
        testOptions.targetSdk = targetSdk
        lint.targetSdk = targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }

    kotlin {
        jvmToolchain(17)
        compilerOptions {
            allWarningsAsErrors = true
            // Suppresses an expected warning that triggers a build failure because allWarningsAsErrors is true
            // See https://youtrack.jetbrains.com/issue/KT-68400/K2-w-Kapt-currently-doesnt-support-language-version-2.0.-Falling-back-to-1.9.
            freeCompilerArgs.add("-Xsuppress-version-warnings")
        }
    }

    publishing {
        singleVariant(MavenCentralPublishPluginExtension.VARIANT) {
            withSourcesJar()
            withJavadocJar()
        }
    }

    sourceSets {
        named("main") {
            res.srcDirs("src/main/res", "src/main/res-public")
        }
    }
}
