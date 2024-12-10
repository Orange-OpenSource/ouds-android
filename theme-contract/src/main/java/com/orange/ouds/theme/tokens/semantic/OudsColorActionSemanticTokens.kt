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

data class OudsColorActionSemanticTokens(
  val actionDisabledLight: Color = ColorRawTokens.colorOpacityBlack200,
  val actionEnabledLight: Color = ColorRawTokens.colorFunctionalBlack,
  val actionFocusLight: Color = ColorRawTokens.colorOpacityBlack680,
  val actionHighlightedLight: Color = ColorRawTokens.colorFunctionalBlack,
  val actionHoverLight: Color = ColorRawTokens.colorOpacityBlack680,
  val actionLoadingLight: Color = OrangeBrandColorRawTokens.colorOrange550,
  val actionNegativeEnabledLight: Color = ColorRawTokens.colorFunctionalScarlet600,
  val actionNegativeFocusLight: Color = ColorRawTokens.colorFunctionalScarlet700,
  val actionNegativeHoverLight: Color = ColorRawTokens.colorFunctionalScarlet700,
  val actionNegativeLoadingLight: Color = ColorRawTokens.colorFunctionalScarlet800,
  val actionNegativePressedLight: Color = ColorRawTokens.colorFunctionalScarlet800,
  val actionNegativeEnabledDark: Color = ColorRawTokens.colorFunctionalScarlet300,
  val actionNegativeFocusDark: Color = ColorRawTokens.colorFunctionalScarlet200,
  val actionNegativeHoverDark: Color = ColorRawTokens.colorFunctionalScarlet200,
  val actionNegativeLoadingDark: Color = ColorRawTokens.colorFunctionalScarlet100,
  val actionNegativePressedDark: Color = ColorRawTokens.colorFunctionalScarlet100,
  val actionPressedLight: Color = OrangeBrandColorRawTokens.colorOrange550,
  val actionSelectedLight: Color = OrangeBrandColorRawTokens.colorOrange550,
  val actionSupportEnabledLight: Color = ColorRawTokens.colorOpacityBlack40,
  val actionSupportFocusLight: Color = ColorRawTokens.colorOpacityBlack80,
  val actionSupportHoverLight: Color = ColorRawTokens.colorOpacityBlack80,
  val actionSupportLoadingLight: Color = ColorRawTokens.colorOpacityBlack80,
  val actionSupportPressedLight: Color = ColorRawTokens.colorOpacityBlack80,
  val actionSupportEnabledDark: Color = ColorRawTokens.colorOpacityWhite40,
  val actionSupportFocusDark: Color = ColorRawTokens.colorOpacityWhite80,
  val actionSupportHoverDark: Color = ColorRawTokens.colorOpacityWhite80,
  val actionSupportLoadingDark: Color = ColorRawTokens.colorOpacityWhite80,
  val actionSupportPressedDark: Color = ColorRawTokens.colorOpacityWhite80,
  val actionVisitedLight: Color = ColorRawTokens.colorDecorativeAmethyst600,
  val actionDisabledDark: Color = ColorRawTokens.colorOpacityWhite200,
  val actionEnabledDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val actionFocusDark: Color = ColorRawTokens.colorOpacityWhite640,
  val actionHighlightedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val actionHoverDark: Color = ColorRawTokens.colorOpacityWhite640,
  val actionLoadingDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val actionPressedDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val actionSelectedDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val actionVisitedDark: Color = ColorRawTokens.colorDecorativeAmethyst400
)
