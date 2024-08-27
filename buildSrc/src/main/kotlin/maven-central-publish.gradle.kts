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

afterEvaluate {
    if (pluginExtension?.enabled == true) {
        publishing {
            publications {
                create<MavenPublication>(MavenCentralPublishPluginExtension.VARIANT) {
                    from(components["release"])
                    groupId = "com.orange.ouds.android"
                    artifactId = pluginExtension?.artifactId ?: project.artifactId
                    this.version = version

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
                    val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                    credentials {
                        val (username, password) = Environment.getVariablesOrNull("SONATYPE_USERNAME", "SONATYPE_PASSWORD")
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
