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
    id("org.jetbrains.dokka")
}

dokka {
    dokkaSourceSets {
        configureEach {
            includes.from("Module.md")
            pluginsConfiguration.html {
                customAssets.from("${rootProject.projectDir}/docs/assets/logo-icon.svg", "${rootProject.projectDir}/docs/assets/banner.png")
                customStyleSheets.from("${rootProject.projectDir}/docs/assets/orange-style.css")
                footerMessage.set("Copyright © Orange 2024")
            }
        }
    }

    dokkaPublications.html {
        failOnWarning.set(true)
    }
}

gradle.projectsEvaluated {
    tasks["dokkaGenerate"].dependsOn(rootProject.tasks["prepareDocumentation"]) // Case where dokkaGenerate is called from the subproject
    tasks["dokkaGenerateModuleHtml"].dependsOn(rootProject.tasks["prepareDocumentation"]) // Case where dokkaGenerate is called from the root project
}
