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

import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsEffectSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens

/**
 * An interface to create an OUDS supported theme.
 *
 * Implementations of this interface define the look and feel of the application by providing specific token values
 * (colors, typography, spacing, etc.). Any values not explicitly set will generally rely on abstract definitions,
 * but typical implementations should provide full sets of tokens.
 */
interface OudsThemeContract {

    /**
     * The display name of the theme (e.g., "Orange", "Sosh").
     */
    val name: String

    /**
     * The general settings for the theme configuration.
     */
    val settings: OudsThemeSettings

    /**
     * The collection of semantic color tokens used in the theme.
     */
    val colorTokens: OudsColorSemanticTokens

    /**
     * The mapping of OUDS tokens to standard Material 3 color roles.
     * This ensures compatibility with standard Material components.
     */
    val materialColorTokens: OudsMaterialColorTokens

    /**
     * The collection of border-related tokens (width, radius, style) used in the theme.
     */
    val borderTokens: OudsBorderSemanticTokens

    /**
     * The collection of visual effect tokens (e.g., blurs) used in the theme.
     */
    val effectTokens: OudsEffectSemanticTokens

    /**
     * The collection of elevation tokens (z-index, shadows) used in the theme.
     */
    val elevationTokens: OudsElevationSemanticTokens

    /**
     * The font family used in the theme.
     *
     * Defaults to [FontFamily.Default] (system font).
     * You can provide a custom font family, for example: `FontFamily(Font(R.font.my_custom_font))`.
     */
    val fontFamily: FontFamily
        get() = FontFamily.Default

    /**
     * The collection of typography semantic tokens (font sizes, weights, line heights) used in the theme.
     */
    val fontTokens: OudsFontSemanticTokens

    /**
     * The collection of grid layout tokens used in the theme.
     */
    val gridTokens: OudsGridSemanticTokens

    /**
     * The collection of opacity tokens used in the theme.
     */
    val opacityTokens: OudsOpacitySemanticTokens

    /**
     * The collection of size tokens (icons, constraints) used in the theme.
     */
    val sizeTokens: OudsSizeSemanticTokens

    /**
     * The collection of spacing tokens (padding, margins, gaps) used in the theme.
     */
    val spaceTokens: OudsSpaceSemanticTokens

    /**
     * Specific tokens for customizing the internal behavior or style of OUDS components.
     */
    val componentsTokens: OudsComponentsTokens

    /**
     * Allows customization of drawable resources used explicitly by OUDS components.
     *
     * **Caution:**
     * To avoid resource conflicts, Android recommends using a prefix or other consistent naming scheme that is unique to the module (or is unique across all project modules).
     * We strongly recommend that you prefix your resources with the theme name.
     * Example: If your theme is "LoremIpsum", name resources like `ic_lorem_ipsum_checkbox_selected`, `ic_lorem_ipsum_chip_tick`, etc.
     */
    val drawableResources: OudsDrawableResources
}