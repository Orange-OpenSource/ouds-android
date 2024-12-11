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

data class OudsColorSurfaceSemanticTokens(
    val surfaceBrandPrimaryLight: Color = OrangeBrandColorRawTokens.colorOrange500,
    val surfaceBrandPrimaryDark: Color = OrangeBrandColorRawTokens.colorOrange500,
    val surfaceStatusAccentEmphasizedLight: Color = ColorRawTokens.colorFunctionalSun500,
    val surfaceStatusAccentMutedLight: Color = ColorRawTokens.colorOpacitySun,
    val surfaceStatusAccentEmphasizedDark: Color = ColorRawTokens.colorFunctionalSun300,
    val surfaceStatusAccentMutedDark: Color = OrangeBrandColorRawTokens.colorWarmGray900,
    val surfaceStatusInfoEmphasizedLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    val surfaceStatusInfoMutedLight: Color = ColorRawTokens.colorOpacityDodgerBlue,
    val surfaceStatusInfoEmphasizedDark: Color = ColorRawTokens.colorFunctionalDodgerBlue300,
    val surfaceStatusInfoMutedDark: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    val surfaceStatusNegativeEmphasizedLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    val surfaceStatusNegativeMutedLight: Color = ColorRawTokens.colorOpacityScarlet,
    val surfaceStatusNegativeEmphasizedDark: Color = ColorRawTokens.colorFunctionalScarlet300,
    val surfaceStatusNegativeMutedDark: Color = ColorRawTokens.colorFunctionalScarlet900,
    val surfaceStatusNeutralEmphasizedLight: Color = ColorRawTokens.colorOpacityBlack840,
    val surfaceStatusNeutralMutedLight: Color = ColorRawTokens.colorOpacityBlack40,
    val surfaceStatusNeutralEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite800,
    val surfaceStatusNeutralMutedDark: Color = ColorRawTokens.colorOpacityWhite80,
    val surfaceStatusPositiveEmphasizedLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    val surfaceStatusPositiveMutedLight: Color = ColorRawTokens.colorOpacityMalachite,
    val surfaceStatusPositiveEmphasizedDark: Color = ColorRawTokens.colorFunctionalMalachite300,
    val surfaceStatusPositiveMutedDark: Color = ColorRawTokens.colorFunctionalMalachite900,
    val surfaceStatusWarningEmphasizedLight: Color = ColorRawTokens.colorFunctionalSun500,
    val surfaceStatusWarningMutedLight: Color = ColorRawTokens.colorOpacitySun,
    val surfaceStatusWarningEmphasizedDark: Color = ColorRawTokens.colorFunctionalSun300,
    val surfaceStatusWarningMutedDark: Color = ColorRawTokens.colorFunctionalSun900
)
