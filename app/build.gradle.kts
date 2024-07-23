import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.findTypedProperty

/*
 * Software Name: Orange Unified Design System
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
    id(libs.plugins.android.application.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    alias(libs.plugins.compose.compiler)
    id(libs.plugins.kotlin.android.get().pluginId)
}


android {
    namespace = "com.orange.ouds.app"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.orange.ouds.app"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = project.findTypedProperty<String>("versionCode")?.toInt() ?: 1
        versionName = "1.0"
        versionNameSuffix = project.findTypedProperty<String>("versionNameSuffix")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    val (storeFilePath, storePassword, keyAlias, keyPassword) = Environment.getVariablesOrNull(
        "SIGNING_STORE_FILE_PATH",
        "SIGNING_STORE_PASSWORD",
        "SIGNING_KEY_ALIAS",
        "SIGNING_KEY_PASSWORD"
    )

    val storeFile = if (storeFilePath != null) file(storeFilePath).takeIf { it.exists() } else null
    val signingConfigName = "signingConfig"

    if (storeFile != null && storePassword != null && keyAlias != null && keyPassword != null) {
        signingConfigs {
            create(signingConfigName) {
                this.storeFile = storeFile
                this.storePassword = storePassword
                this.keyAlias = keyAlias
                this.keyPassword = keyPassword
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            if (storeFile != null) {
                signingConfig = this@android.signingConfigs.getByName(signingConfigName)
            }
        }
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":foundation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.reflect)
}