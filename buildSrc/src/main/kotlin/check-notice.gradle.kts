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

import com.android.build.api.dsl.CommonExtension
import com.orange.ouds.gradle.CheckNoticePluginExtension

val pluginExtension: CheckNoticePluginExtension?
    get() = extensions.findByName(CheckNoticePluginExtension.NAME) as? CheckNoticePluginExtension?

apply {
    if (pluginExtension == null) {
        extensions.create<CheckNoticePluginExtension>(CheckNoticePluginExtension.NAME)
    }
}

tasks.register<DefaultTask>("checkNotice") {
    group = "verification"

    doLast {
        // Get resources listed in NOTICE.txt
        val resourceExtensions = listOf("xml", "png", "svg", "ttf")
        val noticeResources = File("${rootDir.path}/NOTICE.txt").readLines()
            .mapNotNull { line ->
                File("${rootDir.path}/$line").takeIf { it.extension in resourceExtensions }
            }
            .sortedBy { it.path }

        if (noticeResources.isNotEmpty()) {
            logger.info("Detected resources in NOTICE.txt:\n${noticeResources.joinToString("\n") { "  ${it.path}" }}")
        } else {
            logger.info("No resource detected in NOTICE.txt.")
        }

        // Get resources in project
        val subprojectsResourcePaths = subprojects.flatMap { subproject ->
            val androidExtension = subproject.extensions.findByName("android") as? CommonExtension<*, *, *, *, *, *>
            val flavorNames = androidExtension?.productFlavors.orEmpty().map { it.name }
            val sourceSetNames = listOf("main", *flavorNames.toTypedArray())
            sourceSetNames.mapNotNull { androidExtension?.sourceSets?.get(it) }.flatMap { sourceSet ->
                sourceSet.res.directories.flatMap { directory ->
                    File("${subproject.projectDir}/$directory").walk().mapNotNull { file ->
                        if (file.isDirectory && (file.name.startsWith("drawable") || file.name == "font")) file.path else null
                    }
                }
            }
        }
        val resourcePaths = subprojectsResourcePaths + pluginExtension?.additionalResourcePaths.orEmpty()
        val excludedResourcesPaths = pluginExtension?.excludedResourcePaths.orEmpty()
        val resources = resourcePaths.toSet()
            .flatMap { path ->
                File(path).walk().filter { file ->
                    file.extension in resourceExtensions && !excludedResourcesPaths.any { file.path.startsWith(it) }
                }
            }
            .sortedBy { it.path }

        if (resources.isNotEmpty()) {
            logger.info("Detected resources in project:\n${resources.joinToString("\n") { "  ${it.path}" }}")
        } else {
            logger.info("No resource detected in project.")
        }

        // Check if resources listed in NOTICE.txt exist
        val surplusResources = noticeResources.filter { !it.exists() }

        // Check if resources are missing in NOTICE.txt
        val missingResources = resources.filter { !noticeResources.contains(it) }

        // Check if resources listed in NOTICE.txt are duplicated
        val duplicatedResources = noticeResources.groupBy { it.path }.mapNotNull { (_, resources) ->
            resources.takeIf { it.count() > 1 }?.first()
        }

        if (surplusResources.isNotEmpty() || missingResources.isNotEmpty() || duplicatedResources.isNotEmpty()) {
            val message = buildString {
                val appendErrorMessage: (String, List<File>) -> Unit = { errorMessage, resources ->
                    if (resources.isNotEmpty()) {
                        appendLine(
                            """
                        |${errorMessage}:
                        |${resources.joinToString("\n") { "  ${it.path.removePrefix("${rootDir.path}/")}" }}
                        """.trimMargin()
                        )
                    }
                }
                appendErrorMessage("One or more files are listed in NOTICE.txt but do not exist", surplusResources)
                appendErrorMessage("One or more files are not listed in NOTICE.txt", missingResources)
                appendErrorMessage("One or more files are duplicated in NOTICE.txt", duplicatedResources)
            }

            throw GradleException(message)
        }

        logger.lifecycle("NOTICE.txt is up to date.")
    }
}
