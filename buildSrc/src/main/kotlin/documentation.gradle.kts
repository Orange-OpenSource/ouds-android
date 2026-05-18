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

private val moduleDocumentationDirectories = listOf(
    "global-raw-tokens",
    "theme-contract",
    "theme-orange",
    "theme-orange-compact",
    "theme-sosh",
    "theme-wireframe"
)

private val draftVersion = "Draft"

project.extra["moduleDocumentationDirectories"] = moduleDocumentationDirectories

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
        val componentsByFile = mutableMapOf<String, MutableList<Component>>()
        Component.entries.forEach { component ->
            component.getSourceFilePaths(project).forEach { filePath ->
                componentsByFile.getOrPut(filePath) { mutableListOf() }.add(component)
            }
        }

        componentsByFile.forEach { (filePath, components) ->
            val content = File(filePath).readText()
            val componentByDesignName = components.associateBy { it.designName }

            val pattern = "> Design name: (.+?)\\s*\\n \\*\\n \\* > Design version: ([^\n]+)".toRegex()
            val matches = pattern.findAll(content).toList()
            if (matches.isEmpty()) {
                throw GradleException("Could not find design name and/or design version in $filePath.")
            }
            matches.forEach { match ->
                val designName = match.groupValues[1]
                val version = match.groupValues[2]
                val component = componentByDesignName[designName]
                    ?: throw GradleException("Unknown design name '$designName' in $filePath. Expected one of: ${components.joinToString { it.designName }}.")

                val componentVersion = if (component.version == "0.0.0") draftVersion else component.version
                if (version != componentVersion) {
                    throw GradleException("Design version for '$designName' in $filePath is '$version' but expected '${componentVersion}'. Please launch updateDocumentation Gradle task.")
                }
            }
        }
    }
}

tasks.register<DefaultTask>("updateDocumentation") {
    doLast {
        val filePaths = Component.entries.flatMap { it.getSourceFilePaths(project) }
        val pattern = "(> Design name: (.+?)\\s*\\n \\*\\n \\* > Design version: )[^\n]+".toRegex()
        filePaths.forEach { filePath ->
            File(filePath).replace(pattern) { matchResult ->
                val designName = matchResult.groupValues[2]
                val component = Component.entries.find { it.designName == designName }
                if (component == null) {
                    throw GradleException("Could not find component with design name $designName.")
                } else {
                    val componentVersion = if (component.version == "0.0.0") draftVersion else component.version
                    "${matchResult.groupValues[1]}$componentVersion"
                }
            }
        }
    }
}