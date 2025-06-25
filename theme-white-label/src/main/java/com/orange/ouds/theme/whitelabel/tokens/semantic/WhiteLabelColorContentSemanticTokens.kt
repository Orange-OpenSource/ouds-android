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

package com.orange.ouds.theme.whitelabel.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.semantic.OudsColorContentSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.raw.ColorRawTokens

data class WhiteLabelColorContentSemanticTokens(
    override val contentDefaultLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentDefaultDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentMutedLight: Color = ColorRawTokens.colorOpacityBlack680,
    override val contentMutedDark: Color = ColorRawTokens.colorOpacityWhite640,
    override val contentDisabledLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val contentDisabledDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val contentBrandPrimaryLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val contentBrandPrimaryDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val contentBrandSecondaryLight: Color = Color(0x00ff0000),
    override val contentBrandSecondaryDark: Color = Color(0x00ff0000),
    override val contentBrandTertiaryLight: Color = Color(0x00ff0000),
    override val contentBrandTertiaryDark: Color = Color(0x00ff0000),
    override val contentStatusPositiveLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val contentStatusPositiveDark: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val contentStatusInfoLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val contentStatusInfoDark: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val contentStatusWarningLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentStatusWarningDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentStatusNegativeLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val contentStatusNegativeDark: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val contentStatusAccentLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentStatusAccentDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val contentOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnBrandSecondaryLight: Color = Color(0x00ff0000),
    override val contentOnBrandSecondaryDark: Color = Color(0x00ff0000),
    override val contentOnBrandTertiaryLight: Color = Color(0x00ff0000),
    override val contentOnBrandTertiaryDark: Color = Color(0x00ff0000),
    override val contentOnStatusNeutralMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusNeutralMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusNeutralEmphasizedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnStatusNeutralEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusPositiveMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusPositiveMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusPositiveEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusPositiveEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusInfoMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusInfoMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusInfoEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusInfoEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusWarningMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusWarningMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusWarningEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusWarningEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusNegativeMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusNegativeMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusNegativeEmphasizedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnStatusNegativeEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusAccentMutedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusAccentMutedDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val contentOnStatusAccentEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnStatusAccentEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionEnabledLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionEnabledDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionHoverLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionHoverDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionPressedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionPressedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionLoadingLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionLoadingDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionDisabledLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionDisabledDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionFocusLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionFocusDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnActionSelectedLight: Color = Color(0x00ff0000),
    override val contentOnActionSelectedDark: Color = Color(0x00ff0000),
    override val contentOnActionHighlightedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnActionHighlightedDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val contentOnOverlayEmphasizedLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val contentOnOverlayEmphasizedDark: Color = ColorRawTokens.colorFunctionalBlack
) : OudsColorContentSemanticTokens
