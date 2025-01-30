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
import com.orange.ouds.theme.tokens.semantic.OudsColorSurfaceSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorSurfaceSemanticTokens(
    override val surfaceBrandPrimaryLight: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val surfaceBrandPrimaryDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val surfaceStatusAccentEmphasizedLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val surfaceStatusAccentMutedLight: Color = ColorRawTokens.colorOpacitySun,
    override val surfaceStatusAccentEmphasizedDark: Color = ColorRawTokens.colorFunctionalSun300,
    override val surfaceStatusAccentMutedDark: Color = WhiteLabelColorRawTokens.colorWarmGray900,
    override val surfaceStatusInfoEmphasizedLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val surfaceStatusInfoMutedLight: Color = ColorRawTokens.colorOpacityDodgerBlue,
    override val surfaceStatusInfoEmphasizedDark: Color = ColorRawTokens.colorFunctionalDodgerBlue300,
    override val surfaceStatusInfoMutedDark: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    override val surfaceStatusNegativeEmphasizedLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val surfaceStatusNegativeMutedLight: Color = ColorRawTokens.colorOpacityScarlet,
    override val surfaceStatusNegativeEmphasizedDark: Color = ColorRawTokens.colorFunctionalScarlet300,
    override val surfaceStatusNegativeMutedDark: Color = ColorRawTokens.colorFunctionalScarlet900,
    override val surfaceStatusNeutralEmphasizedLight: Color = ColorRawTokens.colorOpacityBlack840,
    override val surfaceStatusNeutralMutedLight: Color = ColorRawTokens.colorOpacityBlack40,
    override val surfaceStatusNeutralEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite800,
    override val surfaceStatusNeutralMutedDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val surfaceStatusPositiveEmphasizedLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val surfaceStatusPositiveMutedLight: Color = ColorRawTokens.colorOpacityMalachite,
    override val surfaceStatusPositiveEmphasizedDark: Color = ColorRawTokens.colorFunctionalMalachite300,
    override val surfaceStatusPositiveMutedDark: Color = ColorRawTokens.colorFunctionalMalachite900,
    override val surfaceStatusWarningEmphasizedLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val surfaceStatusWarningMutedLight: Color = ColorRawTokens.colorOpacitySun,
    override val surfaceStatusWarningEmphasizedDark: Color = ColorRawTokens.colorFunctionalSun300,
    override val surfaceStatusWarningMutedDark: Color = ColorRawTokens.colorFunctionalSun900
) : OudsColorSurfaceSemanticTokens
