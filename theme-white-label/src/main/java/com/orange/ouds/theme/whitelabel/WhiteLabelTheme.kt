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

package com.orange.ouds.theme.whitelabel

import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.material.WhiteLabelMaterialColorTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSemanticTokens
import com.orange.ouds.theme.wireframe.WireframeTheme
import java.util.Locale

const val WHITE_LABEL_THEME_NAME = "White Label"

/**
 * The White Label theme.
 *
 * This theme extends [WireframeTheme] to provide a customizable foundation for white-labeling applications.
 * Unlike the Wireframe theme which uses the Shantell Sans font, the White Label theme uses the default font family,
 * making it ideal for applications that need a neutral starting point with custom branding.
 *
 * The White Label theme can be customized in several ways:
 * - **Font family**: Provide a custom [WhiteLabelFontFamily] implementation.
 * - **Color semantic tokens**: Pass custom [WhiteLabelColorSemanticTokens] to override semantic color values.
 * - **Border semantic tokens**: Pass custom [WhiteLabelBorderSemanticTokens] to override border properties like radius.
 * - **Material color tokens**: Pass custom [WhiteLabelMaterialColorTokens] to override Material color mappings.
 *
 * ## Default Usage
 *
 * By default, the White Label theme uses WireframeTheme appearance with the default font family:
 *
 * ```
 * OudsTheme(theme = WhiteLabelTheme()) {
 *     // Your composables here
 * }
 * ```
 *
 * ## Custom Font Family
 *
 * Override the font family to use your brand's typography:
 *
 * ```
 * val customFontFamily = WhiteLabelFontFamily { locale ->
 *     FontFamily(
 *         Font(R.font.my_brand_regular, FontWeight.Normal),
 *         Font(R.font.my_brand_bold, FontWeight.Bold)
 *     )
 * }
 *
 * OudsTheme(theme = WhiteLabelTheme(whiteLabelFontFamily = customFontFamily)) {
 *     // Your composables here
 * }
 * ```
 *
 * ## Custom Colors
 *
 * Override specific color tokens to match your brand:
 *
 * ```
 * val customColorTokens = WhiteLabelColorSemanticTokens(
 *     actionColorTokens = WhiteLabelColorActionSemanticTokens(
 *         actionEnabledLight = Color(0xFF007AFF),
 *         actionEnabledDark = Color(0xFF0A84FF)
 *     )
 * )
 *
 * OudsTheme(theme = WhiteLabelTheme(colorTokens = customColorTokens)) {
 *     // Your composables here
 * }
 * ```
 *
 * ## Custom Border Radius
 *
 * Override the default border radius:
 *
 * ```
 * val customBorderTokens = WhiteLabelBorderSemanticTokens(
 *     radiusDefault = 8.dp.value // Custom default border radius
 * )
 *
 * OudsTheme(theme = WhiteLabelTheme(borderTokens = customBorderTokens)) {
 *     // Your composables here
 * }
 * ```
 *
 * @param whiteLabelFontFamily The font family to use. Defaults to [WhiteLabelFontFamily.Default] which uses the default font family.
 * @param colorTokens The semantic color tokens. Defaults to [WhiteLabelColorSemanticTokens] which inherits from Wireframe colors.
 * @param materialColorTokens The Material color tokens. Defaults to [WhiteLabelMaterialColorTokens] which inherits from Wireframe Material colors.
 * @param borderTokens The border semantic tokens. Defaults to [WhiteLabelBorderSemanticTokens] which inherits from Wireframe border properties.
 */
class WhiteLabelTheme(
    private val whiteLabelFontFamily: WhiteLabelFontFamily = WhiteLabelFontFamily.Default,
    colorTokens: WhiteLabelColorSemanticTokens = WhiteLabelColorSemanticTokens(),
    materialColorTokens: WhiteLabelMaterialColorTokens = WhiteLabelMaterialColorTokens(),
    borderTokens: WhiteLabelBorderSemanticTokens = WhiteLabelBorderSemanticTokens()
) : WireframeTheme() {

    override val name: String
        get() = WHITE_LABEL_THEME_NAME

    override fun getFontFamily(locale: Locale): FontFamily {
        return whiteLabelFontFamily.getFontFamily(locale)
    }

    override val colorTokens: OudsColorSemanticTokens = colorTokens

    override val materialColorTokens: OudsMaterialColorTokens = materialColorTokens

    override val borderTokens: OudsBorderSemanticTokens = borderTokens
}
