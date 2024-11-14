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
        val resourceExtensions = listOf("xml", "png", "svg")
        val noticeResources = File("${rootDir.path}/NOTICE.txt").readLines()
            .mapNotNull { line ->
                File("${rootDir.path}/$line").takeIf { it.extension in resourceExtensions }
            }
            .sortedBy { it.path }

        if (noticeResources.isNotEmpty()) {
            logger.lifecycle("Detected resources in NOTICE.txt:\n${noticeResources.joinToString("\n") { "  ${it.path}" }}")
        } else {
            logger.lifecycle("No resource detected in NOTICE.txt.")
        }

        // Get resources in project
        val subprojectsResourcePaths = subprojects.flatMap { subproject ->
            val androidExtension = subproject.extensions.getByName("android") as? CommonExtension<*, *, *, *, *, *>
            val flavorNames = androidExtension?.productFlavors.orEmpty().map { it.name }
            val sourceSetNames = listOf("main", *flavorNames.toTypedArray())
            sourceSetNames.mapNotNull { androidExtension?.sourceSets?.get(it) }.flatMap { sourceSet ->
                sourceSet.res.directories.flatMap { directory ->
                    File("${subproject.projectDir}/$directory").walk().mapNotNull { file ->
                        if (file.isDirectory && file.name.startsWith("drawable")) file.path else null
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
            logger.lifecycle("Detected resources in project:\n${resources.joinToString("\n") { "  ${it.path}" }}")
        } else {
            logger.lifecycle("No resource detected in project.")
        }

        // Check if resources listed in NOTICE.txt exist
        noticeResources.forEach { noticeResource ->
            if (!noticeResource.exists()) {
                throw GradleException("File ${noticeResource.path.removePrefix("${rootDir.path}/")} is listed in NOTICE.txt but does not exist.")
            }
        }

        // Check if resources are missing in NOTICE.txt
        resources.forEach { resource ->
            if (!noticeResources.contains(resource)) {
                throw GradleException("File ${resource.path.removePrefix("${rootDir.path}/")} is not listed in NOTICE.txt.")
            }
        }

        logger.lifecycle("NOTICE.txt is up to date.")
    }
}
