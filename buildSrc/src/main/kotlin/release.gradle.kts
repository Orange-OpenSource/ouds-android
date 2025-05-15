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

import com.orange.ouds.gradle.artifactId
import com.orange.ouds.gradle.createGitChangelogApi
import com.orange.ouds.gradle.execute
import com.orange.ouds.gradle.isPublished
import com.orange.ouds.gradle.requireTypedProperty
import com.orange.ouds.gradle.updateChangelog

plugins {
    id("se.bjurr.gitchangelog.git-changelog-gradle-plugin")
}

tasks.register<DefaultTask>("prepareRelease") {
    doLast {
        val version = project.gradle.startParameter.projectProperties["version"] ?: run { createGitChangelogApi().nextSemanticVersion.toString() }
        updateVersion(version)
        updateDependencies(version)
        updateVersionCode()
        updateChangelog(version)
    }
}

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}

fun updateVersion(version: String) {
    File("gradle.properties").replace("(version=).*".toRegex()) { matchResult ->
        "${matchResult.groupValues[1]}$version"
    }
}

fun updateDependencies(version: String) {
    val regex = "(com\\.orange\\.ouds\\.android:ouds-[^:]*:)\\d+\\.\\d+\\.\\d+".toRegex()
    val transform: (MatchResult) -> CharSequence = { matchResult ->
        "${matchResult.groupValues[1]}$version"
    }
    File("docs/index.md").replace(regex, transform)
}

fun updateVersionCode() {
    val versionCodeRegex = "(versionCode = project.findTypedProperty<String>\\(\"versionCode\"\\)\\?\\.toInt\\(\\) \\?: )(\\d+)".toRegex()
    File("app/build.gradle.kts").replace(versionCodeRegex) { matchResult ->
        val versionCode = matchResult.groupValues[2].toInt() + 1
        "${matchResult.groupValues[1]}$versionCode"
    }
}

tasks.register<DefaultTask>("testSonatypeRepository") {
    doLast {
        val sonatypeRepositoryId = project.requireTypedProperty<String>("sonatypeRepositoryId")

        // Add Sonatype Maven repository in root build.gradle.kts file
        File("settings.gradle.kts").replace("(\\s*)mavenCentral\\(\\)".toRegex()) { matchResult ->
            val indent = matchResult.groupValues[1]
            "${matchResult.value}${indent}maven(url = \"https://oss.sonatype.org/content/repositories/comorange-$sonatypeRepositoryId\")"
        }

        val publishedSubprojects = rootProject.subprojects.filter { it.isPublished }
        val nonPublishedSubprojects = rootProject.subprojects.filter { !it.isPublished }
        publishedSubprojects.forEach { publishedSubproject ->
            // Remove published Android Studio modules from settings.gradle.kts
            File("settings.gradle.kts").replace("include\\(\":${publishedSubproject.name}\"\\)(\\n)?".toRegex(), "")

            // Replace project dependencies used for dokka with artifact dependencies in build.gradle.kts
            File("build.gradle.kts").replace(
                "dokka\\(project\\(\":${publishedSubproject.name}\"\\)\\)".toRegex(),
                "dokka(\"com.orange.ouds.android:${publishedSubproject.artifactId}:$version\")"
            )

            // Replace project dependencies with artifact dependencies in build.gradle.kts files of non published modules
            nonPublishedSubprojects.forEach { nonPublishedSubproject ->
                File("${nonPublishedSubproject.name}/build.gradle.kts").replace(
                    "implementation\\(project\\(\":${publishedSubproject.name}\"\\)\\)".toRegex(),
                    "implementation(\"com.orange.ouds.android:${publishedSubproject.artifactId}:$version\")"
                )
            }
        }
    }
}
