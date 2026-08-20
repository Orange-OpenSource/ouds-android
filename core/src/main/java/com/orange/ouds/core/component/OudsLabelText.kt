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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

/**
 * Label styles are intended for compact interface elements such as buttons, form fields, badges, and other small components.
 * Unlike other typography categories, they are not responsive and maintain a fixed size across all breakpoints. This ensures visual consistency and
 * predictable behavior within space-constrained UI elements. Labels should be preferred whenever content is displayed within small components.
 *
 * @param text Text to display.
 * @param modifier [Modifier] applied to the label text.
 * @param size Size of the label text.
 * @param weight Weight of the label text.
 *
 * @sample com.orange.ouds.core.component.samples.OudsLabelTextSample
 */
@Composable
fun OudsLabelText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsLabelTextSize = OudsLabelTextDefaults.Size,
    weight: OudsTextWeight = OudsLabelTextDefaults.Weight
) {
    Text(text = text, modifier = modifier, color = OudsTheme.colorScheme.content.default, style = textStyle(size = size, weight = weight))
}

/**
 * Default values for [OudsLabelText].
 */
object OudsLabelTextDefaults {

    /**
     * Default size of an [OudsLabelText].
     */
    val Size = OudsLabelTextSize.ExtraLarge

    /**
     * Default weight of an [OudsLabelText].
     */
    val Weight = OudsTextWeight.Default
}

/**
 * Represents the size of an [OudsLabelText].
 */
enum class OudsLabelTextSize {
    /**
     * The largest label size, intended for prominent UI controls and interface elements that require increased visibility and emphasis.
     */
    ExtraLarge,

    /**
     * A highly readable label size suitable for key interactive components and larger interface patterns.
     * It provides strong clarity while maintaining compactness.
     */
    Large,

    /**
     * The default label size, designed for most interface components and controls. It offers a balanced combination of readability and space efficiency.
     */
    Medium,

    /**
     * The most compact label size, optimized for dense interfaces and small UI components. Use it when space is limited while preserving legibility.
     */
    Small
}

@Composable
private fun textStyle(size: OudsLabelTextSize, weight: OudsTextWeight): TextStyle = with(OudsTheme.typography.label) {
    when (size) {
        OudsLabelTextSize.ExtraLarge -> when (weight) {
            OudsTextWeight.Default -> extraLarge.default
            OudsTextWeight.Moderate -> extraLarge.moderate
            OudsTextWeight.Strong -> extraLarge.strong
        }
        OudsLabelTextSize.Large -> when (weight) {
            OudsTextWeight.Default -> large.default
            OudsTextWeight.Moderate -> large.moderate
            OudsTextWeight.Strong -> large.strong
        }
        OudsLabelTextSize.Medium -> when (weight) {
            OudsTextWeight.Default -> medium.default
            OudsTextWeight.Moderate -> medium.moderate
            OudsTextWeight.Strong -> medium.strong
        }
        OudsLabelTextSize.Small -> when (weight) {
            OudsTextWeight.Default -> small.default
            OudsTextWeight.Moderate -> small.moderate
            OudsTextWeight.Strong -> small.strong
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLabelText() {
    PreviewOudsLabelText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsLabelText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsTextWeight, OudsLabelTextSize> { weight, size ->
        OudsLabelText("Label", size = size, weight = weight)
    }
}
