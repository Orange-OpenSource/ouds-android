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

package com.orange.ouds.theme.sosh

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.sosh.tokens.components.SoshComponentsTokens
import com.orange.ouds.theme.sosh.tokens.material.SoshMaterialColorTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshBorderSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshColorSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshElevationSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshFontSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshGridSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshOpacitySemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshSizeSemanticTokens
import com.orange.ouds.theme.sosh.tokens.semantic.SoshSpaceSemanticTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens
import kotlinx.parcelize.Parcelize

const val SOSH_THEME_NAME = "Sosh"

@Parcelize
class SoshTheme : OudsThemeContract {

    override val name: String
        get() = SOSH_THEME_NAME

    override val fontFamily: FontFamily
        get() = FontFamily(
            Font(R.font.sosh_black, weight = FontWeight.Black),
            Font(R.font.sosh_medium, weight = FontWeight.Medium),
            Font(R.font.sosh_bold, weight = FontWeight.Bold),
            Font(R.font.sosh_regular, weight = FontWeight.Normal),
            Font(R.font.sosh_thin, weight = FontWeight.Thin)
        )

    override val materialColorTokens: OudsMaterialColorTokens
        get() = SoshMaterialColorTokens()

    override val colorTokens: OudsColorSemanticTokens
        get() = SoshColorSemanticTokens()

    override val borderTokens: OudsBorderSemanticTokens
        get() = SoshBorderSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = SoshElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = SoshFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = SoshGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = SoshOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = SoshSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = SoshSpaceSemanticTokens()

    override val componentsTokens: OudsComponentsTokens
        get() = SoshComponentsTokens()

    override val drawableResources: OudsDrawableResources
        get() = SoshDrawableResources()
}
