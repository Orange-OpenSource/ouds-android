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

plugins {
    id("com.android.library")
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

if (tasks.findByName("dokkaGenerate") != null) {
    tasks.register<Jar>("dokkaJar") {
        dependsOn(tasks["dokkaGenerate"])
        from(layout.buildDirectory.dir("dokka"))
        destinationDirectory = layout.buildDirectory.dir("outputs")
        archiveClassifier.set("javadoc")
    }
}

afterEvaluate {
    if (pluginExtension?.enabled == true) {
        publishing {
            publications {
                create<MavenPublication>(MavenCentralPublishPluginExtension.VARIANT) {
                    from(components["release"])
                    groupId = "com.orange.ouds.android"
                    artifactId = pluginExtension?.artifactId ?: project.artifactId
                    this.version = version
                    
                    if (tasks.findByName("dokkaGenerate") != null) {
                        val dokkaJar = layout.buildDirectory.file("outputs/${project.name}-${project.version}-javadoc.jar")
                        val dokkaArtifact = this@afterEvaluate.artifacts.add("default", dokkaJar) {
                            type = "jar"
                            builtBy(tasks["dokkaJar"])
                        }
                        artifact(dokkaArtifact)
                    }

                    pom {
                        name.set(artifactId)
                        description.set("Orange Unified Design System for Android")
                        val gitHubUrl = "https://github.com/Orange-OpenSource/ouds-android"
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
            useInMemoryPgpKeys(signingKeyId, signingSecretKey, signingPassword)
            sign(publishing.publications[MavenCentralPublishPluginExtension.VARIANT])
        }
    }
}
