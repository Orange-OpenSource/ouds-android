/*
 * Software Name: OUDS Android
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ouds.theme.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.tokens.global.raw.ColorRawTokens
import com.orange.ouds.tokens.global.raw.OrangeBrandColorRawTokens

data class OudsColorBrandSemanticTokens(
    val brandAccentDefaultLight: Color = ColorRawTokens.colorFunctionalSun500,
    val brandAccentHighLight: Color = ColorRawTokens.colorFunctionalSun600,
    val brandAccentHighestLight: Color = OrangeBrandColorRawTokens.colorWarmGray900,
    val brandAccentLowestLight: Color = OrangeBrandColorRawTokens.colorWarmGray100,
    val brandInfoDefaultLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    val brandInfoHighestLight: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    val brandInfoLowestLight: Color = ColorRawTokens.colorFunctionalDodgerBlue100,
    val brandNegativeDefaultLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    val brandNegativeHighLight: Color = ColorRawTokens.colorFunctionalScarlet700,
    val brandNegativeHigherLight: Color = ColorRawTokens.colorFunctionalScarlet800,
    val brandNegativeHighestLight: Color = ColorRawTokens.colorFunctionalScarlet900,
    val brandNegativeLowestLight: Color = ColorRawTokens.colorFunctionalScarlet100,
    val brandNeutralEmphasizedBlackLight: Color = ColorRawTokens.colorFunctionalBlack,
    val brandNeutralEmphasizedHighLight: Color = ColorRawTokens.colorFunctionalDarkGray720,
    val brandNeutralEmphasizedHigherLight: Color = ColorRawTokens.colorFunctionalDarkGray800,
    val brandNeutralEmphasizedHighestLight: Color = ColorRawTokens.colorFunctionalDarkGray880,
    val brandNeutralEmphasizedLowLight: Color = ColorRawTokens.colorFunctionalDarkGray560,
    val brandNeutralEmphasizedLowerLight: Color = ColorRawTokens.colorFunctionalDarkGray480,
    val brandNeutralEmphasizedLowestLight: Color = ColorRawTokens.colorFunctionalDarkGray400,
    val brandNeutralEmphasizedMediumLight: Color = ColorRawTokens.colorFunctionalDarkGray640,
    val brandNeutralMutedHighestLight: Color = ColorRawTokens.colorFunctionalDarkGray160,
    val brandNeutralMutedLowLight: Color = ColorRawTokens.colorFunctionalLightGray400,
    val brandNeutralMutedLowerLight: Color = ColorRawTokens.colorFunctionalLightGray160,
    val brandNeutralMutedLowestLight: Color = ColorRawTokens.colorFunctionalLightGray80,
    val brandNeutralMutedMediumLight: Color = ColorRawTokens.colorFunctionalLightGray560,
    val brandNeutralMutedWhiteLight: Color = ColorRawTokens.colorFunctionalWhite,
    val brandPositiveDefaultLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    val brandPositiveHighestLight: Color = ColorRawTokens.colorFunctionalMalachite900,
    val brandPositiveLowestLight: Color = ColorRawTokens.colorFunctionalMalachite100,
    val brandPrimaryDefaultLight: Color = OrangeBrandColorRawTokens.colorOrange550,
    val brandPrimaryLowLight: Color = OrangeBrandColorRawTokens.colorOrange500,
    val brandWarningDefaultLight: Color = ColorRawTokens.colorFunctionalSun500,
    val brandWarningHighLight: Color = ColorRawTokens.colorFunctionalSun600,
    val brandWarningHighestLight: Color = ColorRawTokens.colorFunctionalSun900,
    val brandWarningLowestLight: Color = ColorRawTokens.colorFunctionalSun100,
    val brandAccentDefaultDark: Color = ColorRawTokens.colorFunctionalSun500,
    val brandAccentHighDark: Color = ColorRawTokens.colorFunctionalSun600,
    val brandAccentHighestDark: Color = OrangeBrandColorRawTokens.colorWarmGray900,
    val brandAccentLowestDark: Color = OrangeBrandColorRawTokens.colorWarmGray100,
    val brandInfoDefaultDark: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    val brandInfoHighestDark: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    val brandInfoLowestDark: Color = ColorRawTokens.colorFunctionalDodgerBlue100,
    val brandNegativeDefaultDark: Color = ColorRawTokens.colorFunctionalScarlet600,
    val brandNegativeHighDark: Color = ColorRawTokens.colorFunctionalScarlet700,
    val brandNegativeHigherDark: Color = ColorRawTokens.colorFunctionalScarlet800,
    val brandNegativeHighestDark: Color = ColorRawTokens.colorFunctionalScarlet900,
    val brandNegativeLowestDark: Color = ColorRawTokens.colorFunctionalScarlet100,
    val brandNeutralEmphasizedBlackDark: Color = ColorRawTokens.colorFunctionalBlack,
    val brandNeutralEmphasizedHighDark: Color = ColorRawTokens.colorFunctionalDarkGray720,
    val brandNeutralEmphasizedHigherDark: Color = ColorRawTokens.colorFunctionalDarkGray800,
    val brandNeutralEmphasizedHighestDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
    val brandNeutralEmphasizedLowDark: Color = ColorRawTokens.colorFunctionalDarkGray560,
    val brandNeutralEmphasizedLowerDark: Color = ColorRawTokens.colorFunctionalDarkGray480,
    val brandNeutralEmphasizedLowestDark: Color = ColorRawTokens.colorFunctionalDarkGray400,
    val brandNeutralEmphasizedMediumDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
    val brandNeutralMutedHighestDark: Color = ColorRawTokens.colorFunctionalDarkGray160,
    val brandNeutralMutedLowDark: Color = ColorRawTokens.colorFunctionalLightGray400,
    val brandNeutralMutedLowerDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    val brandNeutralMutedLowestDark: Color = ColorRawTokens.colorFunctionalLightGray80,
    val brandNeutralMutedMediumDark: Color = ColorRawTokens.colorFunctionalLightGray560,
    val brandNeutralMutedWhiteDark: Color = ColorRawTokens.colorFunctionalWhite,
    val brandPositiveDefaultDark: Color = ColorRawTokens.colorFunctionalMalachite500,
    val brandPositiveHighestDark: Color = ColorRawTokens.colorFunctionalMalachite900,
    val brandPositiveLowestDark: Color = ColorRawTokens.colorFunctionalMalachite100,
    val brandPrimaryDefaultDark: Color = OrangeBrandColorRawTokens.colorOrange550,
    val brandPrimaryLowDark: Color = OrangeBrandColorRawTokens.colorOrange500,
    val brandWarningDefaultDark: Color = ColorRawTokens.colorFunctionalSun500,
    val brandWarningHighDark: Color = ColorRawTokens.colorFunctionalSun600,
    val brandWarningHighestDark: Color = ColorRawTokens.colorFunctionalSun900,
    val brandWarningLowestDark: Color = ColorRawTokens.colorFunctionalSun100,
)