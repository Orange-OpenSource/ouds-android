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

data class OudsColorContentSemanticTokens(
  val contentBrandPrimaryLight: Color = OrangeBrandColorRawTokens.colorOrange550,
  val contentDefaultLight: Color = ColorRawTokens.colorFunctionalBlack,
  val contentDisabledLight: Color = ColorRawTokens.colorOpacityBlack200,
  val contentMutedLight: Color = ColorRawTokens.colorOpacityBlack680,
  val contentOnActionDisabledLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionEnabledLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionFocusLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionHighlightedLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionHoverLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionLoadingLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionNegativeLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionPressedLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnActionDisabledDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionEnabledDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionFocusDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionHighlightedDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionHoverDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionLoadingDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionNegativeDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnActionPressedDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnOverlayEmphasizedLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnOverlayEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnStatusEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnStatusEmphasizedNeutralLight: Color = ColorRawTokens.colorFunctionalWhite,
  val contentOnStatusMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnStatusEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnStatusEmphasizedNeutralDark: Color = ColorRawTokens.colorFunctionalBlack,
  val contentOnStatusMutedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val contentStatusInfoLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
  val contentStatusNegativeLight: Color = ColorRawTokens.colorFunctionalScarlet600,
  val contentStatusPositiveLight: Color = ColorRawTokens.colorFunctionalMalachite500,
  val contentStatusWarningLight: Color = ColorRawTokens.colorFunctionalSun500,
  val contentStatusInfoDark: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
  val contentStatusNegativeDark: Color = ColorRawTokens.colorFunctionalScarlet600,
  val contentStatusPositiveDark: Color = ColorRawTokens.colorFunctionalMalachite500,
  val contentStatusWarningDark: Color = ColorRawTokens.colorFunctionalSun500,
  val contentBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
  val contentDefaultDark: Color = ColorRawTokens.colorFunctionalLightGray160,
  val contentDisabledDark: Color = ColorRawTokens.colorOpacityWhite200,
  val contentMutedDark: Color = ColorRawTokens.colorOpacityWhite640
)
