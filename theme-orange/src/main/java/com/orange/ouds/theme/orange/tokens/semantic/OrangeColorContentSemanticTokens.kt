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
import com.orange.ouds.theme.tokens.semantic.OudsColorContentSemanticTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens
import com.orange.ouds.tokens.global.raw.OrangeBrandColorRawTokens

data class OrangeColorContentSemanticTokens(
    override val contentBrandPrimaryLight: Color = OrangeBrandColorRawTokens.colorOrange550,
    override val contentDefaultLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentDisabledLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val contentMutedLight: Color = ColorRawTokens.colorOpacityBlack680,
    override val contentOnActionDisabledLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionEnabledLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionFocusLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionHighlightedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionHoverLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionLoadingLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionNegativeLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionPressedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionDisabledDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionEnabledDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionFocusDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionHighlightedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionHoverDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionLoadingDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionNegativeDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionPressedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnOverlayEmphasizedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnOverlayEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusEmphasizedNeutralLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnStatusMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusEmphasizedNeutralDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusMutedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val contentStatusInfoLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val contentStatusNegativeLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val contentStatusPositiveLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val contentStatusWarningLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentStatusInfoDark: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val contentStatusNegativeDark: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val contentStatusPositiveDark: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val contentStatusWarningDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
    override val contentDefaultDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val contentDisabledDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val contentMutedDark: Color = ColorRawTokens.colorOpacityWhite640
) : OudsColorContentSemanticTokens
