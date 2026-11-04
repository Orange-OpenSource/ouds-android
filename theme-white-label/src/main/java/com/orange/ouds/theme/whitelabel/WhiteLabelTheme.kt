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
 * - **Identifier**: Provide a unique identifier to distinguish between multiple White Label theme instances.
 * - **Font family**: Provide a custom [WhiteLabelFontFamily] implementation.
 * - **Color scheme**: Pass a custom [WhiteLabelColorScheme] to override semantic color values.
 * - **Material color scheme**: Pass a custom [WhiteLabelMaterialColorScheme] to override Material 3 color mappings.
 * - **Borders**: Pass a custom [WhiteLabelBorders] to override border properties like radius.
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
 * ## Customization Example
 *
 * ```
 * val customColorScheme = WhiteLabelColorScheme(
 *     action = WhiteLabelColorScheme.Action(
 *         enabled = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784))
 *     ),
 *     content = WhiteLabelColorScheme.Content(
 *         brandPrimary = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784))
 *     )
 * )
 *
 * val customMaterialColors = WhiteLabelMaterialColorScheme(
 *     primary = OudsLightDarkColor(Color(0xFF1B5E20), Color(0xFF81C784)),
 *     onPrimary = OudsLightDarkColor(Color.White, Color.Black)
 * )
 *
 * val customBorders = WhiteLabelBorders(
 *     radius = WhiteLabelBorders.Radius(default = 12.0.dp)
 * )
 *
 * val customFont = WhiteLabelFontFamily { locale ->
 *     FontFamily(
 *         Font(R.font.brand_regular, FontWeight.Normal),
 *         Font(R.font.brand_bold, FontWeight.Bold)
 *     )
 * }
 *
 * OudsTheme(
 *     theme = WhiteLabelTheme(
 *         identifier = "MyBrand",
 *         colorScheme = customColorScheme,
 *         materialColorScheme = customMaterialColors,
 *         borders = customBorders,
 *         whiteLabelFontFamily = customFont
 *     )
 * ) {
 *     // Your fully branded app
 * }
 * ```
 *
 * @param identifier An optional identifier to distinguish between multiple White Label theme instances. Useful for logging, analytics, and conditional logic. Defaults to null.
 * @param whiteLabelFontFamily The font family to use. Defaults to [WhiteLabelFontFamily.Default] which uses the default font family.
 * @param colorScheme The color scheme to use. Defaults to [WhiteLabelColorScheme] which inherits from Wireframe colors.
 * @param materialColorScheme The Material 3 color scheme. Defaults to [WhiteLabelMaterialColorScheme] which inherits from Wireframe Material colors.
 * @param borders The border properties. Defaults to [WhiteLabelBorders] which inherits from Wireframe border properties.
 */
class WhiteLabelTheme(
    val identifier: String? = null,
    private val whiteLabelFontFamily: WhiteLabelFontFamily = WhiteLabelFontFamily.Default,
    colorScheme: WhiteLabelColorScheme = WhiteLabelColorScheme(),
    materialColorScheme: WhiteLabelMaterialColorScheme = WhiteLabelMaterialColorScheme(),
    borders: WhiteLabelBorders = WhiteLabelBorders()
) : WireframeTheme() {

    override val name: String
        get() = WHITE_LABEL_THEME_NAME

    override fun getFontFamily(locale: Locale): FontFamily {
        return whiteLabelFontFamily.getFontFamily(locale)
    }

    override val colorTokens: OudsColorSemanticTokens = colorScheme.colorSemanticTokens

    override val materialColorTokens: OudsMaterialColorTokens = materialColorScheme.materialColorTokens

    override val borderTokens: OudsBorderSemanticTokens = borders.borderSemanticTokens
}
