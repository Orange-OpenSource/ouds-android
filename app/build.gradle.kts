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

import com.google.firebase.appdistribution.gradle.firebaseAppDistribution
import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.execute
import com.orange.ouds.gradle.findTypedProperty
import com.orange.ouds.gradle.gitHubApi
import com.orange.ouds.gradle.updateChangelog

plugins {
    id("firebase")
    id(libs.plugins.android.application.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
    alias(libs.plugins.compose.compiler)
    id(libs.plugins.firebase.appdistribution.get().pluginId)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.google.services)
    alias(libs.plugins.hilt)
}


android {
    namespace = "com.orange.ouds.app"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = project.findTypedProperty<String>("versionCode")?.toInt() ?: 3
        versionName = version.toString()
        versionNameSuffix = project.findTypedProperty<String>("versionNameSuffix")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
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

    val versionFlavorDimension = "version"
    flavorDimensions.add(versionFlavorDimension)
    productFlavors {
        create("alpha") {
            dimension = versionFlavorDimension
            applicationId = "com.orange.ouds.alpha.app"
        }
        create("beta") {
            dimension = versionFlavorDimension
            applicationId = "com.orange.ouds.beta.app"
        }
        create("prod") {
            dimension = versionFlavorDimension
            applicationId = "com.orange.ouds.app"
        }
    }

    kotlin {
        jvmToolchain(17)
        compilerOptions {
            allWarningsAsErrors = true
            // Suppresses an expected warning that triggers a build failure because allWarningsAsErrors is true
            // See https://youtrack.jetbrains.com/issue/KT-68400/K2-w-Kapt-currently-doesnt-support-language-version-2.0.-Falling-back-to-1.9.
            freeCompilerArgs.add("-Xsuppress-version-warnings")
            // From Kotlin 2.2, need to specify default rule for annotations
            // See http://youtrack.jetbrains.com/issue/KT-73255
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }

    androidResources {
        generateLocaleConfig = true
    }
}

firebaseAppDistribution {
    groups = project.findTypedProperty("appDistributionGroup")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":foundation"))
    implementation(project(":theme-orange"))
    implementation(project(":theme-sosh"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.kotlin.reflect)
}

tasks.register<DefaultTask>("updateAppChangelog") {
    doLast {
        updateChangelog(null)
        copy {
            from("../CHANGELOG.md").into("src/main/res/raw").rename { it.lowercase() }
        }
        execute("git", "checkout", "CHANGELOG.md")
    }
}

fun updateBuildConfig() {
    val gitHubWorkflow = Environment.getVariablesOrNull("GITHUB_WORKFLOW").first()
    val pullRequestNumber = if (gitHubWorkflow == "app-distribution-alpha") {
        gitHubApi {
            val pullRequests = getPullRequests()
            pullRequests.firstOrNull { it.branchName == Environment.branchName }?.number
        }
    } else {
        null
    }

    android.defaultConfig {
        buildConfigField("String", "PULL_REQUEST_NUMBER", if (pullRequestNumber != null) "\"$pullRequestNumber\"" else "null")
    }
}

gradle.projectsEvaluated {
    tasks["preBuild"].apply {
        dependsOn(":checkNotice")
        dependsOn(tasks["updateAppChangelog"])
    }
    updateBuildConfig()
}
