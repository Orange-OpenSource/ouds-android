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

package com.orange.ouds.core.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewPaddingDefault
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

/**
 * Heading styles are used to structure content and define the hierarchy of information within an interface.
 * Available in multiple sizes, they help users quickly understand the organization of a page or section.
 * Their size automatically adjusts across breakpoints to ensure optimal readability on all devices.
 * Headings serve as the primary entry point for visual navigation.
 *
 * @param text Text to display.
 * @param modifier [Modifier] applied to the heading text.
 * @param size Size of the heading text.
 */
@Composable
fun OudsHeadingText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsHeadingTextSize = OudsHeadingTextDefaults.Size
) {
    Column(modifier = modifier) {
        Text(text = text, color = OudsTheme.colorScheme.content.default, style = size.textStyle)
        with(OudsTheme.components.typography) {
            // Marker is only displayed if the theme allows marker for heading large texts AND if the marker parameter for the component is set to `true`.
            if (size is OudsHeadingTextSize.Large && headingLargeMarker && size.marker) {
                OudsTheme.drawableResources.other.headingTextMarker?.let { resId ->
                    Icon(
                        modifier = Modifier.padding(top = space.paddingBlock.topHeadingLargeMarker, bottom = space.paddingBlock.bottomHeadingLargeMarker),
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        tint = OudsTheme.colorScheme.content.brandPrimary
                    )
                }
            }
        }
    }
}

/**
 * Default values for [OudsHeadingText].
 */
object OudsHeadingTextDefaults {

    /**
     * Default size of an [OudsHeadingText].
     */
    val Size = OudsHeadingTextSize.Large()
}

/**
 * Represents the size of an [OudsHeadingText].
 */
sealed interface OudsHeadingTextSize {

    /**
     * The highest level of heading hierarchy, used to introduce major sections and establish clear content structure.
     * It serves as the primary navigational anchor within a page or experience.
     */
    object ExtraLarge : OudsHeadingTextSize

    /**
     * A high-emphasis heading used for important sections and content groupings.
     * It helps organize information while maintaining strong visual hierarchy.
     *
     * @param marker Controls the brand-colored marker display below the heading large text.
     * It enhances its visual emphasis and reinforce
     *   information hierarchy. This optional decorative element helps highlight important sections and improve content scanability.
     *   Use it selectively to maintain its impact and avoid visual clutter.
     * Note: If the current theme doesn't allow marker for heading large texts, this parameter is ignored.
     */
    class Large(val marker: Boolean = true) : OudsHeadingTextSize

    /**
     * A versatile heading size suitable for standard section titles and subsections.
     * It provides a clear hierarchy while preserving content balance.
     */
    object Medium : OudsHeadingTextSize

    /**
     * The most compact heading size, designed for minor sections and localized content organization.
     * Use it where a heading is required within limited space.
     */
    object Small : OudsHeadingTextSize

    val textStyle: TextStyle
        @Composable
        get() = with(OudsTheme.typography.heading) {
            when (this@OudsHeadingTextSize) {
                is ExtraLarge -> extraLarge
                is Large -> large
                is Medium -> medium
                is Small -> small
            }
        }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsHeadingText() {
    PreviewOudsHeadingText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsHeadingText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Column(verticalArrangement = Arrangement.spacedBy(PreviewPaddingDefault)) {
        listOf(
            OudsHeadingTextSize.ExtraLarge,
            OudsHeadingTextSize.Large(),
            OudsHeadingTextSize.Medium,
            OudsHeadingTextSize.Small
        ).forEach { size ->
            OudsHeadingText("Heading", size = size)
        }
    }
}
