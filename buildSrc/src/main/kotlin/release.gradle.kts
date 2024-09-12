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
import com.orange.ouds.gradle.execute
import com.orange.ouds.gradle.findTypedProperty
import com.orange.ouds.gradle.isPublished

tasks.register<DefaultTask>("prepareRelease") {
    doLast {
        val newVersion = project.gradle.startParameter.projectProperties["version"]
        if (newVersion == null) {
            throw GradleException("Please set the \"version\" project property.")
        }

        updateVersion(newVersion)
        updateVersionCode()
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

fun updateVersionCode() {
    val versionCodeRegex = "(versionCode = project.findTypedProperty<String>\\(\"versionCode\"\\)\\?\\.toInt\\(\\) \\?: )(\\d+)".toRegex()
    File("app/build.gradle.kts").replace(versionCodeRegex) { matchResult ->
        val versionCode = matchResult.groupValues[2].toInt() + 1
        "${matchResult.groupValues[1]}$versionCode"
    }
}

tasks.register<DefaultTask>("testSonatypeRepository") {
    doLast {
        val sonatypeRepositoryId = project.findTypedProperty<String>("sonatypeRepositoryId")
        if (sonatypeRepositoryId == null) {
            throw GradleException("Please set the \"sonatypeRepositoryId\" project property.")
        }

        // Add Sonatype Maven repository in root build.gradle.kts file
        File("build.gradle.kts").replace("(\\s*)mavenCentral\\(\\)".toRegex()) { matchResult ->
            val indent = matchResult.groupValues[1]
            "${matchResult.value}${indent}maven(url = \"https://oss.sonatype.org/content/repositories/comorange-$sonatypeRepositoryId\")"
        }

        val publishedSubprojects = rootProject.subprojects.filter { it.isPublished }
        val nonPublishedSubprojects = rootProject.subprojects.filter { !it.isPublished }
        publishedSubprojects.forEach { publishedSubproject ->
            // Remove published Android Studio modules from settings.gradle.kts
            File("settings.gradle.kts").replace("include\\(\":${publishedSubproject.name}\"\\)(\\n)?".toRegex(), "")

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
