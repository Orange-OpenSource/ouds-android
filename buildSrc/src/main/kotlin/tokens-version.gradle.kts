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

import com.orange.ouds.gradle.findTypedProperty

private val moduleDocumentationFilePaths = listOf(
    "global-raw-tokens",
    "theme-contract",
    "theme-orange"
).map { "${rootProject.projectDir}/$it/Module.md" }

private val tokensVersion: String
    get() = getTokensVersion(
        "${rootProject.projectDir}/global-raw-tokens/src/main/java/com/orange/ouds/tokens/raw/ColorRawTokens.kt",
        "^// Tokens version (.*)"
    )

private fun getTokensVersion(filePath: String, pattern: String): String {
    val tokensVersionRegex = pattern.toRegex()
    return File(filePath).readLines()
        .firstNotNullOfOrNull { line ->
            tokensVersionRegex.find(line)
                ?.groupValues
                ?.get(1)
        } ?: throw GradleException("Could not find tokens version in $filePath.")
}

tasks.register<DefaultTask>("checkTokensVersion") {
    doLast {
        val gradlePropertiesTokensVersion = findTypedProperty<String>("tokensVersion")
        if (gradlePropertiesTokensVersion != tokensVersion) {
            throw GradleException("Tokens version in gradle.properties is not up to date. Please launch updateTokensVersion Gradle task.")
        }
        moduleDocumentationFilePaths.forEach { moduleDocumentationFilePath ->
            if (getTokensVersion(moduleDocumentationFilePath, "^Tokens version (.*)") != tokensVersion) {
                throw GradleException("Tokens version in $moduleDocumentationFilePath is not up to date. Please launch updateTokensVersion Gradle task.")
            }
        }
    }
}

tasks.register<DefaultTask>("updateTokensVersion") {
    doLast {
        File("gradle.properties").replace("(tokensVersion=).*".toRegex()) { matchResult ->
            "${matchResult.groupValues[1]}$tokensVersion"
        }
        moduleDocumentationFilePaths.forEach { moduleDocumentationFilePath ->
            File(moduleDocumentationFilePath).replace("(Tokens version ).*".toRegex()) { matchResult ->
                "${matchResult.groupValues[1]}$tokensVersion"
            }
        }
    }
}
