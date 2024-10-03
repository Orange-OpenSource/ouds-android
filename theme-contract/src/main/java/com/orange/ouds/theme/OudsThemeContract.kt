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

package com.orange.ouds.theme

import android.os.Parcelable
import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpacingSemanticTokens

interface OudsThemeContract : Parcelable {

    /**
     * Theme display name
     */
    val name: String

    /**
     * Color semantic tokens values used in the theme
     */
    val colorTokens: OudsColorTokens

    /**
     * Border semantic tokens values used in the theme
     */
    val borderTokens: OudsBorderSemanticTokens
        get() = OudsBorderSemanticTokens()

    /**
     * Elevation semantic tokens values used in the theme
     */
    val elevationTokens: OudsElevationSemanticTokens
        get() = OudsElevationSemanticTokens()

    /**
     * Font family used in the theme
     * You can provide your own theme font family `FontFamily(Font(R.font.my_theme_font))`
     */
    val fontFamily: FontFamily
        get() = FontFamily.Default

    /**
     * Font semantic tokens values used in the theme
     */
    val fontTokens: OudsFontSemanticTokens
        get() = OudsFontSemanticTokens()

    /**
     * Grid semantic tokens values used in the theme
     */
    val gridTokens: OudsGridSemanticTokens
        get() = OudsGridSemanticTokens()

    /**
     * Opacity semantic tokens values used in the theme
     */
    val opacityTokens: OudsOpacitySemanticTokens
        get() = OudsOpacitySemanticTokens()

    /**
     * Size semantic tokens values used in the theme
     */
    val sizeTokens: OudsSizeTokens
        get() = OudsSizeTokens()

    /**
     * Spacing semantic tokens values used in the theme
     */
    val spacingTokens: OudsSpacingSemanticTokens
        get() = OudsSpacingSemanticTokens()

    /**
     * Customization of the OUDS components if needed
     */
    val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens()
}