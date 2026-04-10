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

import org.gradle.api.DefaultTask

private val failuresDir = project.layout.buildDirectory.dir("paparazzi/failures").get().asFile

tasks.register<DefaultTask>("updatePaparazziFailures") {
    group = "verification"
    // Clean failures directory first to avoid copying old failures
    dependsOn(tasks["cleanPaparazziFailures"])
    // Using finalizedBy in cunjunction with mustRunAfter allows to run verifyPaparazzi and copyPaparazziFailures
    // in that order even if verifyPaparazzi fails
    tasks["cleanPaparazziFailures"].finalizedBy("verifyPaparazzi", "copyPaparazziFailures")
    tasks["copyPaparazziFailures"].mustRunAfter(tasks["verifyPaparazzi"])
}

tasks.register<DefaultTask>("cleanPaparazziFailures") {
    doLast {
        if (failuresDir.exists()) {
            failuresDir.deleteRecursively()
            logger.lifecycle("Cleaned failures in ${project.name}.")
        }
    }
}

tasks.register<DefaultTask>("copyPaparazziFailures") {
    doLast {
        val snapshotsDir = File("${project.projectDir}/src/test/snapshots/images")

        // Get all PNG files except delta comparison images
        val failureFiles = failuresDir.walkTopDown()
            .filter { it.isFile && it.extension == "png" && !it.name.startsWith("delta-") }
            .toList()

        // Ensure snapshots directory exists
        snapshotsDir.mkdirs()

        // Copy failure files to snapshots directory
        failureFiles.forEach { failureFile ->
            val snapshotFile = snapshotsDir.resolve(failureFile.name)
            failureFile.copyTo(snapshotFile, overwrite = true)
        }

        if (failureFiles.isEmpty()) {
            logger.lifecycle("No snapshots updated in ${project.name}.")
        } else {
            val snapshotPlural = if (failureFiles.count() > 1) "s" else ""
            logger.lifecycle("Updated ${failureFiles.count()} snapshot$snapshotPlural in ${project.name}.")
        }
    }
}
