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

package com.orange.ouds.theme.wireframe

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.components.WireframeComponentsTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeBorderSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeColorSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeElevationSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeFontSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeGridSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeOpacitySemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeSizeSemanticTokens
import com.orange.ouds.theme.wireframe.tokens.semantic.WireframeSpaceSemanticTokens
import kotlinx.parcelize.Parcelize

const val WIREFRAME_THEME_NAME = "Wireframe"

@Parcelize
class WireframeTheme : OudsThemeContract {

    override val name: String
        get() = WIREFRAME_THEME_NAME

    override val settings: OudsThemeSettings
        get() = OudsThemeSettings(roundedCornerButtons = null)

    override val fontFamily: FontFamily
        get() = FontFamily(
            Font(R.font.shantellsans_bold, weight = FontWeight.Bold),
            Font(R.font.shantellsans_regular, weight = FontWeight.Normal),
            Font(R.font.shantellsans_light, weight = FontWeight.Light)
        )

    override val colorTokens: OudsColorSemanticTokens
        get() = WireframeColorSemanticTokens()

    override val borderTokens: OudsBorderSemanticTokens
        get() = WireframeBorderSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = WireframeElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = WireframeFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = WireframeGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = WireframeOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = WireframeSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = WireframeSpaceSemanticTokens()

    override val componentsTokens: OudsComponentsTokens
        get() = WireframeComponentsTokens()

    override val drawableResources: OudsDrawableResources
        get() = WireframeDrawableResources()
}
