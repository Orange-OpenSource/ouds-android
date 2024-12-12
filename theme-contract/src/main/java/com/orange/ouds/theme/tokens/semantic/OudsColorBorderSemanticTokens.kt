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

package com.orange.ouds.theme.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.tokens.global.raw.ColorRawTokens
import com.orange.ouds.tokens.global.raw.OrangeBrandColorRawTokens

data class OudsColorBorderSemanticTokens(
    val borderBrandPrimaryLight: Color = OrangeBrandColorRawTokens.colorOrange550,
    val borderDefaultLight: Color = ColorRawTokens.colorOpacityBlack200,
    val borderEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    val borderFocusLight: Color = ColorRawTokens.colorFunctionalBlack,
    val borderFocusInsetLight: Color = ColorRawTokens.colorFunctionalWhite,
    val borderOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    val borderOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
    val borderBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
    val borderDefaultDark: Color = ColorRawTokens.colorOpacityWhite200,
    val borderEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite920,
    val borderFocusDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    val borderFocusInsetDark: Color = ColorRawTokens.colorFunctionalDarkGray880
)
