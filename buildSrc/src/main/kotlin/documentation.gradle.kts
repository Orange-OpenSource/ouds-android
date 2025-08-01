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

import com.github.mustachejava.DefaultMustacheFactory
import com.orange.ouds.gradle.Component
import com.orange.ouds.theme.OudsVersion
import java.io.FileOutputStream
import java.io.PrintWriter
import kotlin.reflect.full.declaredMemberProperties

tasks.register<DefaultTask>("prepareDocumentation") {
    dependsOn(tasks["checkDocumentation"])

    doLast {
        val mustacheFactory = DefaultMustacheFactory()

        // Fill the component versions table in core/Module.md with values from OudsVersion.Component
        val coreModuleDocumentationWriter = PrintWriter(FileOutputStream("core/Module.md"))
        // Build a map in order to loop through all component versions in core Module.mustache
        val componentVersions = OudsVersion.Component::class
            .declaredMemberProperties
            .map { property ->
                val name = property.name
                    .split("(?=\\p{Upper})".toRegex())
                    .filter { it.isNotEmpty() }
                    .mapIndexed { index, word -> if (index == 0) word else word.lowercase() }
                    .joinToString(" ")
                val value = property.getter.call() as String
                mapOf("name" to name, "value" to value)
            }
        mustacheFactory.compile("core/Module.mustache")
            .execute(coreModuleDocumentationWriter, mapOf("versions" to componentVersions))
            .flush()

        // Fill the tokens versions in various Module.md files with the values from OudsVersion.Tokens
        val moduleDocumentationDirectories = listOf(
            "global-raw-tokens",
            "theme-contract",
            "theme-orange",
            "theme-sosh"
        )
        moduleDocumentationDirectories.forEach { moduleDocumentationDirectory ->
            val moduleDocumentationWriter = PrintWriter(FileOutputStream("$moduleDocumentationDirectory/Module.md"))
            mustacheFactory.compile("$moduleDocumentationDirectory/Module.mustache")
                .execute(moduleDocumentationWriter, OudsVersion.Tokens)
                .flush()
        }
    }
}

tasks.register<DefaultTask>("checkDocumentation") {
    doLast {
        val componentVersionRegex = "Design version: (.*)$".toRegex()
        Component.values().forEach { component ->
            component.getSourceFilePaths(project).forEach { sourceFilePath ->
                val versionByLineIndex = File(sourceFilePath).readLines()
                    .mapIndexedNotNull { index, line ->
                        componentVersionRegex.find(line)
                            ?.groupValues
                            ?.getOrNull(1)
                            ?.let { version ->
                                index to version
                            }
                    }
                versionByLineIndex.forEach { (lineIndex, version) ->
                    if (version != component.version) {
                        throw GradleException("Component version at line ${lineIndex + 1} in $sourceFilePath is not up to date. Please launch updateDocumentation Gradle task.")
                    }
                }
            }
        }
    }
}

tasks.register<DefaultTask>("updateDocumentation") {
    doLast {
        val componentVersionRegex = "(Design version: ).*".toRegex()
        Component.values().forEach { component ->
            component.getSourceFilePaths(project).forEach { sourceFilePath ->
                File(sourceFilePath).replace(componentVersionRegex) { matchResult ->
                    "${matchResult.groupValues[1]}${component.version}"
                }
            }
        }
    }
}
