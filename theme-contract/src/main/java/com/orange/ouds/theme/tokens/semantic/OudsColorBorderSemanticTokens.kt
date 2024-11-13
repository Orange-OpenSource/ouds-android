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
  val borderBrandPrimaryOnBgEmphasizedLight: Color = OrangeBrandColorRawTokens.colorOrange500,
  val borderDefaultLight: Color = ColorRawTokens.colorFunctionalLightGray400,
  val borderDefaultOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray400,
  val borderEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
  val borderEmphasizedOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderFocusLight: Color = ColorRawTokens.colorFunctionalBlack,
  val borderFocusInsetLight: Color = ColorRawTokens.colorFunctionalWhite,
  val borderFocusInsetOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray880,
  val borderFocusOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalWhite,
  val borderOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
  val borderBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val borderBrandPrimaryOnBgEmphasizedDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val borderDefaultDark: Color = ColorRawTokens.colorFunctionalDarkGray400,
  val borderDefaultOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray400,
  val borderEmphasizedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderEmphasizedOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderFocusDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderFocusInsetDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
  val borderFocusInsetOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val borderFocusOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray880
)
