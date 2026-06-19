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

import com.android.ide.common.vectordrawable.Svg2Vector
import com.orange.ouds.gradle.requireTypedProperty
import java.io.ByteArrayOutputStream
import java.nio.file.Files.createTempDirectory
import java.util.zip.ZipFile

/**
 * Plugin that provides the importIcons task for importing OUDS icons from a zip file.
 */

/**
 * Represents an icon to be imported.
 */
data class Icon(
    val svgPath: String,                // Relative path from theme directory (e.g., "component/alert/important-fill.svg")
    val resourceName: String,           // Android resource name (e.g., "ic_orange_component_alert_important_fill")
    val autoMirrored: Boolean = false   // Indicates if the icon should be auto-mirrored for RTL support
)

abstract class ImportIconsTask : DefaultTask() {

    companion object {

        private val SupportedThemes = listOf("orange", "sosh", "wireframe")

        /**
         * List of icon paths to import.
         * Paths are relative to the theme directory (e.g., "component/alert/important-fill.svg")
         * Add or remove icon paths here to update which icons are imported.
         */
        private val IconPaths = setOf(
            // Communication
            "communication/accessibility/accessibility-vision.svg",
            "communication/security-and-safety/lock-closed.svg",

            // Component - Alert
            "component/alert/important-fill.svg",
            "component/alert/info-fill.svg",
            "component/alert/tick-confirmation-fill.svg",
            "component/alert/warning-external-shape.svg",
            "component/alert/warning-internal-shape.svg",

            // Component - BadgeIcon
            "component/badge-icon/error-fill.svg",
            "component/badge-icon/info-fill.svg",
            "component/badge-icon/tick-confirmation-fill.svg",
            "component/badge-icon/warning-external-shape.svg",
            "component/badge-icon/warning-internal-shape.svg",

            // Component - BulletList
            "component/bullet-list/bullet-level-0.svg",
            "component/bullet-list/bullet-level-1.svg",
            "component/bullet-list/bullet-level-2.svg",
            "component/bullet-list/bullet-tick.svg",

            // Component - Other
            "component/button/expurge.svg",
            "component/checkbox/checkbox-selected.svg",
            "component/checkbox/checkbox-undetermined.svg",
            "component/chip/tick.svg",
            "component/link/next.svg",
            "component/link/previous.svg",
            "component/radio-button/radio-button-selected.svg",
            "component/switch/selected-switch.svg",
            "component/tag/close.svg",

            // Functional
            "functional/actions/delete-cross-round.svg",
            "functional/navigation/form-chevron-left.svg",
            "functional/navigation/menu-grid-ui-round.svg",
            "functional/settings-and-tools/accessibility-hide.svg",
            "functional/social-and-engagement/heart-recommend.svg"
        )

        /**
         * Set of icon paths that should have android:autoMirrored="true" for RTL support.
         */
        private val AutoMirroredIcons = setOf(
            "component/link/next.svg",
            "component/link/previous.svg",
            "functional/navigation/form-chevron-left.svg"
        )

        /**
         * Transform function to customize the drawable resource name.
         * The default behavior converts the SVG path to snake_case.
         * Use this to remove redundant prefixes or rename specific icons.
         */
        private fun transformResourceBaseName(resourceBaseName: String): String = when (resourceBaseName) {
            "communication_accessibility_accessibility_vision" -> "communication_accessibility_vision"
            "component_bullet_list_bullet_level_0" -> "component_bullet_list_level0"
            "component_bullet_list_bullet_level_1" -> "component_bullet_list_level1"
            "component_bullet_list_bullet_level_2" -> "component_bullet_list_level2"
            "component_bullet_list_bullet_tick" -> "component_bullet_list_tick"
            "component_checkbox_checkbox_selected" -> "component_checkbox_selected"
            "component_checkbox_checkbox_undetermined" -> "component_checkbox_undetermined"
            "component_radio_button_radio_button_selected" -> "component_radio_button_selected"
            else -> resourceBaseName
        }
    }

    private val failures = mutableListOf<String>()
    private var successCount = 0

    init {
        // Always run this task, don't cache it
        outputs.upToDateWhen { false }
    }

    @TaskAction
    fun importIcons() {
        val zipPath = project.requireTypedProperty<String>("zipPath")

        // Validate zip file exists
        val zipFile = File(zipPath.replace("~", System.getProperty("user.home")))
        if (!zipFile.exists()) {
            throw GradleException("Zip file not found at path ${zipFile.absolutePath}.")
        }
        logger.lifecycle("✓ Found zip file ${zipFile.name}")

        // Extract and process icons
        ZipFile(zipFile).use { zip ->
            val tempDir = createTempDirectory("ouds-icons-import").toFile()
            try {
                // Extract Zip to temp directory
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
                logger.lifecycle("✓ Unzipped file successfully")

                // Find the root directory (should be "OUDS Icons V*.*")
                // Skip __MACOSX and other system directories
                val rootDir = tempDir.listFiles()
                    ?.filter { it.isDirectory && !it.name.startsWith("__") && !it.name.startsWith(".") }
                    ?.firstOrNull { it.name.matches(Regex("OUDS Icons V\\d+\\.\\d+.*")) }
                    ?: throw GradleException("Invalid zip structure: no 'OUDS Icons V*.*' directory found.")

                logger.lifecycle("✓ Found icons directory ${rootDir.name}")

                // Validate themes exist
                SupportedThemes.forEach { theme ->
                    val themeDir = File(rootDir, theme)
                    if (!themeDir.exists()) {
                        throw GradleException("Theme directory ${theme} not found.")
                    }
                }
                logger.lifecycle("✓ All theme directories found (${SupportedThemes.joinToString(", ")})")

                // Validate that all files listed in IconPaths exist in the zip for each theme
                SupportedThemes.forEach { theme ->
                    val themeDir = File(rootDir, theme)
                    IconPaths.forEach { iconPath ->
                        val iconFile = File(themeDir, iconPath)
                        if (!iconFile.exists()) {
                            failures.add("File ${iconFile.toRelativeString(rootDir)} not found in zip")
                        }
                    }
                }

                // Process all icons
                val icons = SupportedThemes.flatMap { theme ->
                    val themeDir = File(rootDir, theme)
                    getIcons(themeDir, themeDir)
                }

                logger.lifecycle("\nProcessing ${icons.size} icons...")
                logger.lifecycle("─".repeat(70))

                icons.forEachIndexed { index, icon ->
                    processIcon(icon, index, icons.size, rootDir)
                }
            } finally {
                tempDir.deleteRecursively()
            }
        }

        // Print summary
        printSummary()
    }

    /**
     * Recursively retrieves SVG files in a theme directory.
     */
    private fun getIcons(currentDir: File, themeDir: File): List<Icon> {
        val icons = mutableListOf<Icon>()
        currentDir.listFiles()?.forEach { file ->
            if (file.isDirectory) {
                icons.addAll(getIcons(file, themeDir))
            } else if (file.extension.equals("svg", ignoreCase = true)) {
                val svgPath = file.toRelativeString(themeDir)
                val theme = themeDir.toPath().last().toString()
                if (IconPaths.contains(svgPath.lowercase())) {
                    // Resource base name is the SVG path converted to snake case
                    // Renaming (if any) is done explicitly in the transformResourceBaseName method
                    val resourceBaseName = svgPath
                        .removeSuffix(".svg")
                        .lowercase()
                        .replace("/", "_")
                        .replace("-", "_")
                    val resourceName = "ic_${theme}_${transformResourceBaseName(resourceBaseName)}"
                    val autoMirrored = AutoMirroredIcons.contains(svgPath.lowercase())
                    icons.add(Icon(svgPath, resourceName, autoMirrored))
                }
            }
        }

        return icons.toList()
    }

    private fun processIcon(icon: Icon, iconIndex: Int, iconCount: Int, rootDir: File) {
        val theme = icon.resourceName.removePrefix("ic_").substringBefore("_")
        val svgFile = File(rootDir, "$theme/${icon.svgPath}")
        val outputFile = File(
            project.rootDir,
            "theme-${theme}/src/main/res/drawable/${icon.resourceName}.xml"
        )

        val progress = "[${iconIndex + 1}/$iconCount]"
        val svgPath = "$theme/${icon.svgPath}"

        try {
            val vectorDrawable = convertSvgToVectorDrawable(svgFile, icon.autoMirrored)
            outputFile.parentFile.mkdirs()
            outputFile.writeText(vectorDrawable)
            successCount++
            logger.lifecycle("$progress ✓ $svgPath")
        } catch (e: Exception) {
            failures.add("Conversion failed for ${svgFile.toRelativeString(rootDir)}: ${e.message}")
            logger.lifecycle("$progress ✗ $svgPath (${e.message})")
        }
    }

    private fun convertSvgToVectorDrawable(svgFile: File, autoMirrored: Boolean): String {
        val outputStream = ByteArrayOutputStream()

        // Use Android's Svg2Vector tool to convert SVG to Vector Drawable
        svgFile.inputStream().use { inputStream ->
            val errorMessage = Svg2Vector.parseSvgToXml(svgFile.toPath(), outputStream)
            if (errorMessage.isNotEmpty()) {
                throw GradleException("Could not parse SVG: $errorMessage")
            }
        }

        var vectorDrawable = outputStream.toString("UTF-8")

        // If autoMirrored is true, add the android:autoMirrored="true" attribute
        if (autoMirrored) {
            vectorDrawable = vectorDrawable.replace(
                Regex("""(<vector[^>]*android:viewportHeight="[^"]*")(\s*>)"""),
                "$1\n    android:autoMirrored=\"true\"$2"
            )
        }

        return vectorDrawable.replace("android:fillColor=\"currentColor\"", "android:fillColor=\"#000000\"")
    }

    private fun printSummary() {
        logger.lifecycle("─".repeat(70))
        logger.lifecycle("\n✓ Successfully imported: $successCount icons")
        logger.lifecycle("✗ Failures: ${failures.size}")

        if (failures.isNotEmpty()) {
            logger.lifecycle("\nFailed imports:")
            failures.forEach { failure ->
                logger.lifecycle("  • $failure")
            }
            val failurePlural = if (failures.count() > 1) "s" else ""
            throw GradleException("Icon import completed with ${failures.size} failure$failurePlural.")
        }

        logger.lifecycle("\n✅ All icons imported successfully!")
    }
}

tasks.register<ImportIconsTask>("importIcons")
