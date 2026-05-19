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

package com.orange.ouds.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.w3c.dom.Element
import java.io.File
import java.util.zip.ZipFile
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Gradle task to import OUDS icons from a zip file into all theme modules.
 * 
 * This task automates the process of:
 * 1. Extracting SVG files from the OUDS icons zip
 * 2. Converting SVGs to Android Vector Drawable XML format
 * 3. Importing icons into Orange, Sosh, and Wireframe theme modules
 * 
 * Usage:
 * ```
 * ./gradlew importIcons --zip-path="~/Downloads/OUDS Icons V1.7.zip"
 * ./gradlew importIcons --zip-path="/path/to/OUDS Icons V1.7.zip" --dry-run
 * ```
 */
abstract class ImportIconsTask : DefaultTask() {

    @get:Input
    @set:Option(option = "zip-path", description = "Path to the OUDS icons zip file (required)")
    var zipPath: String = ""

    @get:Input
    @set:Option(option = "dry-run", description = "Simulate the import without writing files")
    var dryRun: Boolean = false

    private val failures = mutableListOf<String>()
    private var successCount = 0

    @TaskAction
    fun importIcons() {
        logger.lifecycle("╔════════════════════════════════════════════════════════════╗")
        logger.lifecycle("║          OUDS Icons Import Task                            ║")
        logger.lifecycle("╚════════════════════════════════════════════════════════════╝")
        logger.lifecycle("")

        if (dryRun) {
            logger.lifecycle("🔍 DRY RUN MODE: No files will be written")
            logger.lifecycle("")
        }

        // Validate zip-path parameter is provided
        if (zipPath.isEmpty()) {
            throw GradleException(
                """
                ❌ Missing required parameter: --zip-path
                
                Usage:
                  ./gradlew importIcons --zip-path="/path/to/OUDS Icons V1.7.zip"
                
                Example:
                  ./gradlew importIcons --zip-path="~/Downloads/OUDS Icons V1.7.zip"
            """.trimIndent()
            )
        }

        // Validate zip file exists
        val zipFile = File(zipPath.replace("~", System.getProperty("user.home")))
        if (!zipFile.exists()) {
            throw GradleException("❌ Zip file not found: ${zipFile.absolutePath}")
        }
        logger.lifecycle("✓ Found zip file: ${zipFile.name}")

        // Validate theme-orange-compact doesn't have drawable directory
        validateOrangeCompact()

        // Extract and process icons
        ZipFile(zipFile).use { zip ->
            val tempDir = createTempDir("ouds-icons-import")
            try {
                // Extract zip
                logger.lifecycle("✓ Extracting zip file...")
                zip.entries().asSequence().forEach { entry ->
                    if (!entry.isDirectory) {
                        val file = File(tempDir, entry.name)
                        file.parentFile.mkdirs()
                        zip.getInputStream(entry).use { input ->
                            file.outputStream().use { output ->
                                input.copyTo(output)
                            }
                        }
                    }
                }

                // Find the root directory (should be "OUDS Icons Vx.y")
                val rootDir = tempDir.listFiles()?.firstOrNull { it.isDirectory }
                    ?: throw GradleException("❌ Invalid zip structure: no root directory found")

                logger.lifecycle("✓ Found icons directory: ${rootDir.name}")
                logger.lifecycle("")

                // Validate themes exist
                validateThemes(rootDir)

                // Process all icons
                processAllIcons(rootDir)

            } finally {
                tempDir.deleteRecursively()
            }
        }

        // Step 5: Print summary
        printSummary()
    }

    private fun validateOrangeCompact() {
        val orangeCompactDrawableDir = File(project.rootDir, "theme-orange-compact/src/main/res/drawable")
        if (orangeCompactDrawableDir.exists() && orangeCompactDrawableDir.listFiles()?.isNotEmpty() == true) {
            logger.warn("⚠️  WARNING: theme-orange-compact has drawable resources!")
            logger.warn("   This theme should reuse theme-orange drawables.")
            logger.warn("   Directory: ${orangeCompactDrawableDir.absolutePath}")
            logger.lifecycle("")
        }
    }

    private fun validateThemes(rootDir: File) {
        IconMappings.supportedThemes.forEach { theme ->
            val themeDir = File(rootDir, theme)
            if (!themeDir.exists()) {
                throw GradleException("❌ Theme directory not found: ${theme}")
            }
        }
        logger.lifecycle("✓ All theme directories found (orange, sosh, wireframe)")
        logger.lifecycle("")
    }

    private fun processAllIcons(rootDir: File) {
        val totalIcons = IconMappings.allIcons.size * IconMappings.supportedThemes.size
        var currentIcon = 0

        logger.lifecycle("Processing ${IconMappings.allIcons.size} icons across ${IconMappings.supportedThemes.size} themes (${totalIcons} total)...")
        logger.lifecycle("─".repeat(60))

        IconMappings.supportedThemes.forEach { theme ->
            IconMappings.allIcons.forEach { iconDef ->
                currentIcon++
                processIcon(rootDir, theme, iconDef, currentIcon, totalIcons)
            }
        }
    }

    private fun processIcon(
        rootDir: File,
        theme: String,
        iconDef: IconDefinition,
        currentIcon: Int,
        totalIcons: Int
    ) {
        val svgFile = File(rootDir, "$theme/${iconDef.svgPath}")
        val resourceName = "ic_${theme}_${iconDef.resourceBaseName}"
        val outputFile = File(
            project.rootDir,
            "theme-${theme}/src/main/res/drawable/${resourceName}.xml"
        )

        val progress = "[$currentIcon/$totalIcons]"
        val displayPath = iconDef.interfacePath.padEnd(50)

        try {
            // Check if SVG exists
            if (!svgFile.exists()) {
                failures.add("Missing SVG: $theme/${iconDef.svgPath}")
                logger.lifecycle("$progress ✗ $displayPath (SVG not found)")
                return
            }

            // Convert SVG to Vector Drawable
            val vectorXml = convertSvgToVectorDrawable(svgFile, iconDef.autoMirrored)

            // Write output file
            if (!dryRun) {
                outputFile.parentFile.mkdirs()
                outputFile.writeText(vectorXml)
            }

            successCount++
            val status = if (dryRun) "[DRY RUN]" else "✓"
            logger.lifecycle("$progress $status $displayPath")
        } catch (e: Exception) {
            failures.add("Conversion failed for $theme/${iconDef.svgPath}: ${e.message}")
            logger.lifecycle("$progress ✗ $displayPath (${e.message})")
        }
    }

    private fun convertSvgToVectorDrawable(svgFile: File, autoMirrored: Boolean): String {
        val docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val doc = docBuilder.parse(svgFile)
        val svgElement = doc.documentElement

        // Extract SVG attributes
        val width = svgElement.getAttribute("width").takeIf { it.isNotEmpty() } ?: "24"
        val height = svgElement.getAttribute("height").takeIf { it.isNotEmpty() } ?: "24"
        val viewBox = svgElement.getAttribute("viewBox")

        val (viewportWidth, viewportHeight) = if (viewBox.isNotEmpty()) {
            val parts = viewBox.trim().split("\\s+".toRegex())
            if (parts.size == 4) {
                parts[2] to parts[3]
            } else {
                width.removeSuffix("px").removeSuffix("dp") to height.removeSuffix("px").removeSuffix("dp")
            }
        } else {
            width.removeSuffix("px").removeSuffix("dp") to height.removeSuffix("px").removeSuffix("dp")
        }

        // Extract paths from SVG
        val paths = mutableListOf<Element>()
        extractPaths(svgElement, paths)

        // Build Vector Drawable XML with Android Studio formatting
        // Format: 4-space indentation for vector attributes, 2-space for path, 6-space for path attributes
        val lines = mutableListOf<String>()

        // Vector opening tag
        lines.add("<vector xmlns:android=\"http://schemas.android.com/apk/res/android\"")
        lines.add("    android:width=\"${width.removeSuffix("px")}dp\"")
        lines.add("    android:height=\"${height.removeSuffix("px")}dp\"")
        lines.add("    android:viewportWidth=\"$viewportWidth\"")

        // ViewportHeight - last attribute before closing or autoMirrored
        if (autoMirrored) {
            lines.add("    android:viewportHeight=\"$viewportHeight\"")
            lines.add("    android:autoMirrored=\"true\">")
        } else {
            lines.add("    android:viewportHeight=\"$viewportHeight\">")
        }

        // Add paths
        paths.forEach { path ->
            val pathData = path.getAttribute("d")
            val fill = path.getAttribute("fill").takeIf { it.isNotEmpty() } ?: "#000000"
            val fillRule = path.getAttribute("fill-rule").takeIf { it.isNotEmpty() }

            if (pathData.isNotEmpty()) {
                lines.add("  <path")

                // Format pathData to match Android Studio's Vector Asset import format
                val formattedPathData = formatPathDataLikeAndroidStudio(pathData)
                lines.add("      android:pathData=\"$formattedPathData\"")

                // Fill color - check if it's last attribute
                if (fillRule == "evenodd") {
                    lines.add("      android:fillColor=\"$fill\"")
                    lines.add("      android:fillType=\"evenOdd\" />")
                } else {
                    lines.add("      android:fillColor=\"$fill\" />")
                }
            }
        }

        // Closing tag
        lines.add("</vector>")

        // Add trailing newline to match Android Studio format
        return lines.joinToString("\n") + "\n"
    }

    private fun extractPaths(element: Element, paths: MutableList<Element>) {
        val children = element.childNodes
        for (i in 0 until children.length) {
            val child = children.item(i)
            if (child is Element) {
                when (child.nodeName) {
                    "path" -> paths.add(child)
                    "g", "svg" -> extractPaths(child, paths)
                }
            }
        }
    }

    /**
     * Formats SVG pathData to match Android Studio's Vector Asset import format.
     * Android Studio adds commas in specific places when converting SVG to Vector Drawable.
     */
    private fun formatPathDataLikeAndroidStudio(pathData: String): String {
        // Clean up whitespace first
        var formatted = pathData.trim().replace(Regex("""\s+"""), " ")

        // Android Studio adds commas after path commands before first coordinate
        // Pattern: "M12 2.4" -> "M12,2.4"
        formatted = formatted.replace(Regex("""([MmLlHhVvCcSsQqTtAa])(\s*)(-?[\d.]+)\s+(-?[\d.]+)""")) { match ->
            "${match.groupValues[1]}${match.groupValues[3]},${match.groupValues[4]}"
        }

        // For Arc (A/a) commands, also add commas after the radii pair
        // Pattern: "A9.6 9.6 0 0 0 2.4" -> "A9.6,9.6 0,0 0,2.4"
        formatted = formatted.replace(Regex("""([Aa][\d.,]+)\s+(-?[\d.]+)\s+(-?[\d.]+)\s+(-?[\d.]+)\s+(-?[\d.]+)""")) { match ->
            "${match.groupValues[1]} ${match.groupValues[2]},${match.groupValues[3]} ${match.groupValues[4]},${match.groupValues[5]}"
        }

        return formatted
    }

    private fun printSummary() {
        logger.lifecycle("─".repeat(60))
        logger.lifecycle("")
        logger.lifecycle("╔════════════════════════════════════════════════════════════╗")
        logger.lifecycle("║                    Import Summary                          ║")
        logger.lifecycle("╚════════════════════════════════════════════════════════════╝")
        logger.lifecycle("")
        logger.lifecycle("  ✓ Successfully imported: $successCount icons")
        logger.lifecycle("  ✗ Failures: ${failures.size}")

        if (failures.isNotEmpty()) {
            logger.lifecycle("")
            logger.lifecycle("Failed imports:")
            failures.forEach { failure ->
                logger.lifecycle("  • $failure")
            }
            logger.lifecycle("")
            throw GradleException("❌ Icon import completed with ${failures.size} failure(s)")
        }

        logger.lifecycle("")
        if (dryRun) {
            logger.lifecycle("🔍 DRY RUN completed successfully. No files were modified.")
        } else {
            logger.lifecycle("✅ All icons imported successfully!")
            logger.lifecycle("")
            logger.lifecycle("Next steps:")
            logger.lifecycle("  1. Review the changes with: git status")
            logger.lifecycle("  2. Build the project: ./gradlew build")
            logger.lifecycle("  3. Run snapshot tests: ./gradlew verifyPaparazziDebug")
        }
        logger.lifecycle("")
    }
}
