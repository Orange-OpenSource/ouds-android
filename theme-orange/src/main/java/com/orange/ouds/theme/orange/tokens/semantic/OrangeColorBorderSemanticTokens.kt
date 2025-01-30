//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

package com.orange.ouds.theme.orange.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens
import com.orange.ouds.theme.orange.tokens.raw.OrangeBrandColorRawTokens

data class OrangeColorBorderSemanticTokens(
    override val borderBrandPrimaryLight: Color = OrangeBrandColorRawTokens.colorOrange550,
    override val borderDefaultLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val borderEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderFocusLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderFocusInsetLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val borderOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
    override val borderDefaultDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val borderEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite920,
    override val borderFocusDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val borderFocusInsetDark: Color = ColorRawTokens.colorFunctionalDarkGray880
) : OudsColorBorderSemanticTokens
