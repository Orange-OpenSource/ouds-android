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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewPaddingDefault
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

/**
 * Display styles are intended for high-impact content such as landing pages, marketing campaigns, and key messages. Their large type sizes help capture
 * attention and establish strong visual emphasis.
 * Variants automatically adapt across breakpoints to maintain a consistent visual hierarchy on every screen size.
 * Use them sparingly to preserve their impact and effectiveness.
 *
 * @param text Text to display.
 * @param modifier [Modifier] applied to the display text.
 * @param size Size of the display text.
 */
@Composable
fun OudsDisplayText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsDisplayTextSize = OudsDisplayTextDefaults.Size
) {
    Text(text = text, modifier = modifier, color = OudsTheme.colorScheme.content.default, style = size.textStyle)
}

/**
 * Default values for [OudsDisplayText].
 */
object OudsDisplayTextDefaults {

    /**
     * Default size of an [OudsDisplayText].
     */
    val Size = OudsDisplayTextSize.Large
}

/**
 * Represents the size of an [OudsDisplayText].
 */
enum class OudsDisplayTextSize {
    /**
     * The largest Display size, intended for maximum visual impact. Use it for hero sections,
     * key messages, and high-priority content that requires immediate attention.
     */
    Large,

    /**
     * A prominent Display size that provides strong emphasis while maintaining a more balanced visual presence.
     * Suitable for major content areas and featured messages.
     */
    Medium,

    /**
     * The most compact Display size, offering visual impact in space-constrained layouts.
     * Use it when a display style is needed without overwhelming surrounding content.
     */
    Small;

    val textStyle: TextStyle
        @Composable
        get() = with(OudsTheme.typography.display) {
            when (this@OudsDisplayTextSize) {
                Large -> large
                Medium -> medium
                Small -> small
            }
        }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsDisplayText() {
    PreviewOudsDisplayText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsDisplayText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Column(verticalArrangement = Arrangement.spacedBy(PreviewPaddingDefault)) {
        OudsDisplayTextSize.entries.forEach { size ->
            OudsDisplayText("Display", size = size)
        }
    }
}
