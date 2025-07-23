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
import java.io.FileOutputStream
import java.io.PrintWriter
import kotlin.reflect.full.declaredMemberProperties

tasks.register<DefaultTask>("prepareDocumentation") {
    doLast {
        val mustacheFactory = DefaultMustacheFactory()

        // Retrieve OudsVersion.Component object and fill the table in core/Module.md with the component design versions
        val coreModuleDocumentationWriter = PrintWriter(FileOutputStream("core/Module.md"))
        // Build a map in order to loop through all component versions in core Module.mustache
        val componentVersions = Class.forName("com.orange.ouds.theme.OudsVersion${'$'}Component")
            .kotlin
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

        // Retrieve OudsVersion.Tokens object and fill the tokens versions in various Module.md files
        val moduleDocumentationDirectories = listOf(
            "global-raw-tokens",
            "theme-contract",
            "theme-orange",
            "theme-sosh"
        )
        // Use OudsVersion.Tokens directly in various Module.mustache files
        val tokensVersions = Class.forName("com.orange.ouds.theme.OudsVersion${'$'}Tokens").kotlin.objectInstance
        moduleDocumentationDirectories.forEach { moduleDocumentationDirectory ->
            val moduleDocumentationWriter = PrintWriter(FileOutputStream("$moduleDocumentationDirectory/Module.md"))
            mustacheFactory.compile("$moduleDocumentationDirectory/Module.mustache")
                .execute(moduleDocumentationWriter, tokensVersions)
                .flush()
        }
    }
}
