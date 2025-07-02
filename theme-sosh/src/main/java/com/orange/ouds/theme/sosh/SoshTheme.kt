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

import com.orange.ouds.theme.OudsThemeContract
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
open class SoshTheme : OudsThemeContract {

    override val name: String
        get() = SOSH_THEME_NAME

    override val materialColorTokens: OudsMaterialColorTokens
        get() = TODO("Not yet implemented")

    override val colorTokens: OudsColorSemanticTokens
        get() = TODO("Not yet implemented")

    override val borderTokens: OudsBorderSemanticTokens
        get() = TODO("Not yet implemented")

    override val elevationTokens: OudsElevationSemanticTokens
        get() = TODO("Not yet implemented")

    override val fontTokens: OudsFontSemanticTokens
        get() = TODO("Not yet implemented")

    override val gridTokens: OudsGridSemanticTokens
        get() = TODO("Not yet implemented")

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = TODO("Not yet implemented")

    override val sizeTokens: OudsSizeSemanticTokens
        get() = TODO("Not yet implemented")

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = TODO("Not yet implemented")
}
