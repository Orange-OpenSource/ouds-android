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

import com.orange.ouds.gradle.Environment
import com.orange.ouds.gradle.MavenCentralPublishPluginExtension
import com.orange.ouds.gradle.artifactId
import com.orange.ouds.gradle.isSnapshot
import org.gradle.api.publish.maven.MavenPublication

plugins {
    `maven-publish`
    signing
}

val pluginExtension: MavenCentralPublishPluginExtension?
    get() = extensions.findByName(MavenCentralPublishPluginExtension.NAME) as? MavenCentralPublishPluginExtension?

apply {
    if (pluginExtension == null) {
        extensions.create<MavenCentralPublishPluginExtension>(MavenCentralPublishPluginExtension.NAME)
    }
}

afterEvaluate {
    // Register dokkaJar task if dokkaGenerate exists
    if (tasks.findByName("dokkaGenerate") != null) {
        tasks.register<Jar>("dokkaJar") {
            dependsOn(tasks["dokkaGenerate"])
            from(layout.buildDirectory.dir("dokka"))
            destinationDirectory = layout.buildDirectory.dir("outputs")
            archiveClassifier.set("javadoc")
        }
    }
    
    if (pluginExtension?.enabled == true) {
        publishing {
            val groupId = "com.orange.ouds.kmp"
            val baseArtifactId = pluginExtension?.artifactId ?: project.artifactId
            val version = project.version.toString()

            // Configure all KMP auto-generated publications
            publications.withType<MavenPublication>().configureEach {
                // Override the groupId for all publications
                this.groupId = groupId
                
                // Add "ouds-" prefix to all artifact IDs
                // kotlinMultiplatform publication gets the base name (e.g., "ouds-foundation")
                // Platform-specific publications get suffixes (e.g., "ouds-foundation-android", "ouds-foundation-iosarm64")
                val prefixedArtifactId = if (this.artifactId.startsWith("ouds-")) {
                    this.artifactId
                } else {
                    "ouds-${this.artifactId}"
                }
                this.artifactId = prefixedArtifactId

                // Attach Dokka javadoc jar only to the main kotlinMultiplatform publication
                // Platform-specific publications (Android, iOS) don't need separate javadoc
                // since they share the same documentation with the main publication
                if (this.name == "kotlinMultiplatform" && tasks.findByName("dokkaGenerate") != null) {
                    val dokkaJar = layout.buildDirectory.file("outputs/${project.name}-${project.version}-javadoc.jar")
                    val dokkaArtifact = this@afterEvaluate.artifacts.add("default", dokkaJar) {
                        type = "jar"
                        builtBy(tasks["dokkaJar"])
                    }
                    artifact(dokkaArtifact)
                }

                // Configure POM metadata for all publications
                pom {
                    configurePom(this@configureEach.artifactId)
                }
            }

            repositories {
                maven {
                    val releasesRepositoryUrl = "https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/"
                    val snapshotsRepositoryUrl = "https://central.sonatype.com/repository/maven-snapshots"
                    url = uri(if (isSnapshot) snapshotsRepositoryUrl else releasesRepositoryUrl)
                    credentials {
                        val (username, password) = Environment.getVariablesOrNull("CENTRAL_PUBLISHER_PORTAL_USERNAME", "CENTRAL_PUBLISHER_PORTAL_PASSWORD")
                        this.username = username
                        this.password = password
                    }
                }
            }
        }

        signing {
            val (signingKeyId, signingSecretKey, signingPassword) = Environment.getVariablesOrNull(
                "GNUPG_SIGNING_KEY_ID",
                "GNUPG_SIGNING_SECRET_KEY",
                "GNUPG_SIGNING_PASSWORD"
            )
            
            // Only configure signing if credentials are available
            if (signingKeyId != null && signingSecretKey != null && signingPassword != null) {
                useInMemoryPgpKeys(signingKeyId, signingSecretKey, signingPassword)
                // Sign all publications
                sign(publishing.publications)
            }
        }
    }
}

private fun MavenPom.configurePom(artifactId: String) {
    val description = "Orange Unified Design System for Android"
    val gitHubUrl = "https://github.com/Orange-OpenSource/ouds-android"

    name.set(artifactId)
    this.description.set(description)
    url.set(gitHubUrl)
    licenses {
        license {
            name.set("MIT License")
            url.set("https://github.com/Orange-OpenSource/ouds-android/blob/main/LICENSE")
        }
    }
    scm {
        url.set(gitHubUrl)
        connection.set("scm:git:git://github.com/Orange-OpenSource/ouds-android.git")
        developerConnection.set("scm:git:ssh://git@github.com/Orange-OpenSource/ouds-android.git")
    }
    developers {
        developer {
            name.set("Pauline Auvray")
            email.set("pauline.auvray@orange.com")
        }
        developer {
            name.set("Florent Maitre")
            email.set("florent.maitre@orange.com")
        }
    }
}
