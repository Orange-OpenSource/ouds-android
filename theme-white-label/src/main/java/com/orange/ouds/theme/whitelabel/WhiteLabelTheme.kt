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

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorRepositorySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelElevationSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelFontSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelGridSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelOpacitySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelSizeSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelSpaceSemanticTokens
import kotlinx.parcelize.Parcelize

@Parcelize
open class WhiteLabelTheme : OudsThemeContract {

    override val name: String
        get() = "White label"

    override val colorTokens: OudsColorSemanticTokens
        get() = WhiteLabelColorSemanticTokens(repositoryColorTokens = WhiteLabelColorRepositorySemanticTokens())

    override val borderTokens: OudsBorderSemanticTokens
        get() = WhiteLabelBorderSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = WhiteLabelElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = WhiteLabelFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = WhiteLabelGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = WhiteLabelOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = WhiteLabelSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = WhiteLabelSpaceSemanticTokens()

    override val fontFamily: FontFamily
        get() = FontFamily(Font(R.font.oswald))

    override val componentsTokens: OudsComponentsTokens
        get() = OudsComponentsTokens(
            button = OudsButtonTokens(
                borderRadius = OudsBorderKeyToken.Radius.Pill,
                spacePaddingBlock = OudsSpaceKeyToken.PaddingBlock.Shortest
            )
        )
}
