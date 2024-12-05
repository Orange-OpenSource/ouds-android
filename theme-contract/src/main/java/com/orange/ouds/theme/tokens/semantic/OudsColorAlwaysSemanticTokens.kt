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

data class OudsColorAlwaysSemanticTokens(
  val alwaysBlackLight: Color = ColorRawTokens.colorFunctionalBlack,
  val alwaysOnBlackLight: Color = ColorRawTokens.colorFunctionalWhite,
  val alwaysOnWhiteLight: Color = ColorRawTokens.colorFunctionalBlack,
  val alwaysWhiteLight: Color = ColorRawTokens.colorFunctionalWhite,
  val alwaysBlackDark: Color = ColorRawTokens.colorFunctionalBlack,
  val alwaysOnBlackDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val alwaysOnWhiteDark: Color = ColorRawTokens.colorFunctionalBlack,
  val alwaysWhiteDark: Color = ColorRawTokens.colorFunctionalWhite
)
