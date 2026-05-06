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

package com.orange.ouds.core.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme

/**
 * Returns a high contrasted color for better contrast when high contrast mode is enabled.
 *
 * When high contrast mode is disabled, the original color is returned unchanged.
 *
 * @param background The background color on which this color will be displayed.
 *                   If transparent, the theme's primary background color is used as reference.
 * @return `OudsTheme.colorScheme.always.black` or `OudsTheme.colorScheme.always.white` color when high contrast mode is enabled, or the original color otherwise.
 */
@Composable
fun Color.highContrasted(background: Color = Color.Transparent): Color {
    if (!LocalHighContrastModeEnabled.current) return this

    // Composite the background over primary background color to handle transparency
    val effectiveBackground = background.compositeOver(OudsTheme.colorScheme.background.primary)

    // Composite the current color over background to handle transparency
    val effectiveColor = compositeOver(effectiveBackground)

    // Return the best contrast color for the current color
    return if (effectiveColor.luminance() < 0.3f) {
        OudsTheme.colorScheme.always.black
    } else {
        OudsTheme.colorScheme.always.white
    }
}