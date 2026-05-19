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

import org.w3c.dom.Element
import java.io.File
import java.util.zip.ZipFile
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Plugin that provides the importIcons task for importing OUDS Icons from a zip file.
 */

/**
 * Represents an icon to be imported.
 */
data class IconInfo(
    val svgPath: String,           // Relative path from theme directory (e.g., "Component/alert/important-fill.svg")
    val resourceName: String,      // Android resource name (e.g., "ic_orange_component_alert_important_fill")
    val autoMirrored: Boolean = false
)

abstract class ImportIconsTask : DefaultTask() {

    @get:Internal
    @set:org.gradle.api.tasks.options.Option(option = "zip-path", description = "Path to the OUDS Icons zip file (required)")
    var zipPath: String = ""

    private val failures = mutableListOf<String>()
    private var successCount = 0
    
    private val supportedThemes = listOf("orange", "sosh", "wireframe")
    
    /**
     * Filter to determine which SVG files should be imported.
     * The path is relative to the theme directory (e.g., "Component/alert/important-fill.svg")
     * Update this lambda to add or remove icons from the import.
     */
    private val filter: (String) -> Boolean = { svgPath ->
        val path = svgPath.lowercase()
        
        // Include specific icon paths - use exact matches to avoid partial matches
        path == "communication/accessibility/accessibility-vision.svg" ||
        path == "communication/security-and-safety/lock.svg" ||
        path == "component/alert/important-fill.svg" ||
        path == "component/alert/info-fill.svg" ||
        path == "component/alert/tick-confirmation-fill.svg" ||
        path == "component/alert/warning-external-shape.svg" ||
        path == "component/alert/warning-internal-shape.svg" ||
        path == "component/badge-icon/error-fill.svg" ||
        path == "component/badge-icon/info-fill.svg" ||
        path == "component/badge-icon/tick-confirmation-fill.svg" ||
        path == "component/badge-icon/warning-external-shape.svg" ||
        path == "component/badge-icon/warning-internal-shape.svg" ||
        path == "component/bullet-list/bullet-level-0.svg" ||
        path == "component/bullet-list/bullet-level-1.svg" ||
        path == "component/bullet-list/bullet-level-2.svg" ||
        path == "component/bullet-list/bullet-tick.svg" ||
        path == "component/button/expurge.svg" ||
        path == "component/checkbox/checkbox-selected.svg" ||
        path == "component/checkbox/checkbox-undetermined.svg" ||
        path == "component/chip/tick.svg" ||
        path == "component/link/next.svg" ||
        path == "component/link/previous.svg" ||
        path == "component/radio-button/radio-button-selected.svg" ||
        path == "component/switch/selected-switch.svg" ||
        path == "component/tag/close.svg" ||
        path == "functional/actions/delete.svg" ||
        path == "functional/navigation/form-chevron-left.svg" ||
        path == "functional/navigation/menu.svg" ||
        path == "functional/settings-and-tools/hide.svg" ||
        path == "functional/social-and-engagement/heart-empty.svg"
    }
    
    /**
     * Transform function to customize the drawable resource name.
     * The default behavior converts the path to snake_case and removes redundant prefixes.
     * Update this lambda to customize specific icon names.
     */
    private val transform: (String) -> String = { resourceBaseName ->
        when (resourceBaseName) {
            // Custom mappings for specific icons
            "component_switch_selected" -> "component_switch_selected_switch"
            "component_bullet_list_level_0" -> "component_bullet_list_level0"
            "component_bullet_list_level_1" -> "component_bullet_list_level1"
            "component_bullet_list_level_2" -> "component_bullet_list_level2"
            "component_bullet_list_tick" -> "component_bullet_list_tick"
            "component_checkbox_selected" -> "component_checkbox_selected"
            "component_checkbox_undetermined" -> "component_checkbox_undetermined"
            "component_radio_button_selected" -> "component_radio_button_selected"
            "communication_accessibility_vision" -> "communication_accessibility_vision"
            else -> resourceBaseName
        }
    }
    
    /**
     * Set of icon paths that should have android:autoMirrored="true" for RTL support.
     */
    private val autoMirroredIcons = setOf(
        "component/link/next.svg",
        "component/link/previous.svg",
        "functional/navigation/form-chevron-left.svg"
    )

    init {
        // Always run this task, don't cache it
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun importIcons() {
        logger.lifecycle("╔════════════════════════════════════════════════════════════╗")
        logger.lifecycle("║          OUDS Icons Import Task                            ║")
        logger.lifecycle("╚════════════════════════════════════════════════════════════╝")
        logger.lifecycle("")

        // Step 1: Validate zip-path parameter is provided
        if (zipPath.isEmpty()) {
            throw GradleException("""
                ❌ Missing required parameter: --zip-path
                
                Usage:
                  ./gradlew importIcons --zip-path="/path/to/OUDS Icons V1.7.zip"
                
                Example:
                  ./gradlew importIcons --zip-path="~/Downloads/OUDS Icons V1.7.zip"
            """.trimIndent())
        }

        // Step 2: Validate zip file exists
        val zipFile = File(zipPath.replace("~", System.getProperty("user.home")))
        if (!zipFile.exists()) {
            throw GradleException("❌ Zip file not found: ${zipFile.absolutePath}")
        }
        logger.lifecycle("✓ Found zip file: ${zipFile.name}")

        // Step 3: Validate theme-orange-compact doesn't have drawable directory
        validateOrangeCompact()

        // Step 4: Extract and process icons
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

                // Find the root directory (should be "OUDS Icons V*.*")
                // Skip __MACOSX and other system directories
                val rootDir = tempDir.listFiles()
                    ?.filter { it.isDirectory && !it.name.startsWith("__") && !it.name.startsWith(".") }
                    ?.firstOrNull { it.name.matches(Regex("OUDS Icons V\\d+\\.\\d+.*")) }
                    ?: throw GradleException("❌ Invalid zip structure: no 'OUDS Icons V*.*' directory found")

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
        supportedThemes.forEach { theme ->
            val themeDir = File(rootDir, theme)
            if (!themeDir.exists()) {
                throw GradleException("❌ Theme directory not found: ${theme}")
            }
        }
        logger.lifecycle("✓ All theme directories found (${supportedThemes.joinToString(", ")})")
        logger.lifecycle("")
    }

    private fun processAllIcons(rootDir: File) {
        // Discover all SVG files from the zip
        val allIcons = mutableListOf<IconInfo>()
        
        supportedThemes.forEach { theme ->
            val themeDir = File(rootDir, theme)
            discoverSvgFiles(themeDir, theme, "", allIcons)
        }
        
        val totalIcons = allIcons.size
        var currentIcon = 0

        logger.lifecycle("Processing $totalIcons icons...")
        logger.lifecycle("─".repeat(60))

        allIcons.forEach { iconInfo ->
            currentIcon++
            processIcon(rootDir, iconInfo, currentIcon, totalIcons)
        }
    }
    
    /**
     * Recursively discover SVG files in a theme directory and filter them.
     */
    private fun discoverSvgFiles(dir: File, theme: String, relativePath: String, icons: MutableList<IconInfo>) {
        dir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                val newRelativePath = if (relativePath.isEmpty()) file.name else "$relativePath/${file.name}"
                discoverSvgFiles(file, theme, newRelativePath, icons)
            } else if (file.extension.equals("svg", ignoreCase = true)) {
                val svgPath = if (relativePath.isEmpty()) file.name else "$relativePath/${file.name}"
                
                // Apply filter
                if (filter(svgPath)) {
                    // Convert path to resource name
                    val resourceBaseName = svgPathToResourceName(svgPath)
                    val transformedName = transform(resourceBaseName)
                    val resourceName = "ic_${theme}_${transformedName}"
                    val autoMirrored = autoMirroredIcons.contains(svgPath.lowercase())
                    
                    icons.add(IconInfo(svgPath, resourceName, autoMirrored))
                }
            }
        }
    }
    
    /**
     * Convert SVG path to Android resource base name.
     * Example: "Component/alert/important-fill.svg" -> "component_alert_important_fill"
     * 
     * Special rule: If the file name starts with the last directory name (or any word from it), remove that prefix.
     * Examples:
     * - "communication/accessibility/accessibility-vision.svg" -> "communication_accessibility_vision"
     * - "Component/bullet-list/bullet-level-0.svg" -> "component_bullet_list_level_0" 
     * - "Component/checkbox/checkbox-selected.svg" -> "component_checkbox_selected"
     * - "Component/radio-button/radio-button-selected.svg" -> "component_radio_button_selected"
     */
    private fun svgPathToResourceName(svgPath: String): String {
        val pathWithoutExtension = svgPath.removeSuffix(".svg")
        val parts = pathWithoutExtension.split("/")
        
        if (parts.size >= 2) {
            val directory = parts[parts.size - 2].lowercase().replace("-", "_")
            val fileName = parts.last().lowercase().replace("-", "_")
            
            var fileNameWithoutPrefix = fileName
            
            // First, try to remove the full directory name as a prefix
            if (fileName.startsWith("${directory}_")) {
                fileNameWithoutPrefix = fileName.removePrefix("${directory}_")
            } else {
                // If that doesn't work, try each word in the directory name
                val directoryWords = directory.split("_")
                for (word in directoryWords) {
                    if (fileName.startsWith("${word}_")) {
                        fileNameWithoutPrefix = fileName.removePrefix("${word}_")
                        break
                    }
                }
            }
            
            // Build the full resource name
            val allParts = parts.dropLast(1).map { it.lowercase().replace("-", "_") } + fileNameWithoutPrefix
            return allParts.joinToString("_")
        }
        
        return pathWithoutExtension.lowercase().replace("/", "_").replace("-", "_")
    }

    private fun processIcon(
        rootDir: File,
        iconInfo: IconInfo,
        currentIcon: Int,
        totalIcons: Int
    ) {
        val theme = iconInfo.resourceName.removePrefix("ic_").substringBefore("_")
        val svgFile = File(rootDir, "$theme/${iconInfo.svgPath}")
        val outputFile = File(
            project.rootDir,
            "theme-${theme}/src/main/res/drawable/${iconInfo.resourceName}.xml"
        )

        val progress = "[$currentIcon/$totalIcons]"
        val displayPath = iconInfo.svgPath.padEnd(60)

        try {
            // Convert SVG to Vector Drawable
            val vectorXml = convertSvgToVectorDrawable(svgFile, iconInfo.autoMirrored)

            // Write output file
            outputFile.parentFile.mkdirs()
            outputFile.writeText(vectorXml)

            successCount++
            logger.lifecycle("$progress ✓ $displayPath")

        } catch (e: Exception) {
            failures.add("Conversion failed for ${iconInfo.svgPath}: ${e.message}")
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
        logger.lifecycle("✅ All icons imported successfully!")
        logger.lifecycle("")
        logger.lifecycle("Next steps:")
        logger.lifecycle("  1. Review the changes with: git status")
        logger.lifecycle("  2. Build the project: ./gradlew build")
        logger.lifecycle("  3. Run snapshot tests: ./gradlew verifyPaparazziDebug")
        logger.lifecycle("")
    }
}

// Register the importIcons task
tasks.register<ImportIconsTask>("importIcons") {
    group = "ouds"
    description = "Import OUDS Icons from a zip file into all theme modules"
}
