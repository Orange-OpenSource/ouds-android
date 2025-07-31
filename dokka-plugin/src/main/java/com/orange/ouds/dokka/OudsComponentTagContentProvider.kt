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

package com.orange.ouds.dokka

import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.base.transformers.pages.tags.CustomTagContentProvider
import org.jetbrains.dokka.base.translators.documentables.PageContentBuilder
import org.jetbrains.dokka.model.doc.CustomTagWrapper

object OudsComponentTagContentProvider : CustomTagContentProvider {

    const val COMPONENT_DESIGN_VERSION_TAG_NAME = "ComponentDesignVersion"

    const val COMPONENT_DESIGN_GUIDELINES_TAG_NAME = "ComponentDesignGuidelines"

    override fun isApplicable(customTag: CustomTagWrapper): Boolean =
        customTag.name in listOf(COMPONENT_DESIGN_VERSION_TAG_NAME, COMPONENT_DESIGN_GUIDELINES_TAG_NAME)

    override fun PageContentBuilder.DocumentableContentBuilder.contentForDescription(
        sourceSet: DokkaConfiguration.DokkaSourceSet,
        customTag: CustomTagWrapper
    ) {
        val headerText = when (customTag.name) {
            COMPONENT_DESIGN_VERSION_TAG_NAME -> "Design version"
            COMPONENT_DESIGN_GUIDELINES_TAG_NAME -> "Design guidelines"
            else -> ""
        }
        group(sourceSets = setOf(sourceSet)) {
            header(4, headerText)
            comment(customTag.root)
        }
    }
}