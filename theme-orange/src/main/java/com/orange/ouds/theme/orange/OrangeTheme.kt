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

package com.orange.ouds.theme.orange

import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.orange.tokens.semantic.OrangeBorderSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorRepositorySemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeElevationSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeFontSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeGridSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeOpacitySemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeSizeSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeSpaceSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens
import kotlinx.parcelize.Parcelize

const val ORANGE_THEME_NAME = "Orange"

@Parcelize
open class OrangeTheme : OudsThemeContract {

    override val name: String
        get() = ORANGE_THEME_NAME

    override val colorTokens: OudsColorSemanticTokens
        get() = OrangeColorSemanticTokens(repositoryColorTokens = OrangeColorRepositorySemanticTokens())

    override val borderTokens: OudsBorderSemanticTokens
        get() = OrangeBorderSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = OrangeElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = OrangeFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = OrangeGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = OrangeOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = OrangeSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = OrangeSpaceSemanticTokens()
}
