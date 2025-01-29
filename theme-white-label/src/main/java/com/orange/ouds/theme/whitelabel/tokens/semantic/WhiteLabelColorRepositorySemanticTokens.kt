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
import com.orange.ouds.theme.tokens.semantic.OudsColorRepositorySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorRepositorySemanticTokens(
    override val repositoryAccentDefaultLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val repositoryAccentHighestLight: Color = WhiteLabelColorRawTokens.colorWarmGray900,
    override val repositoryAccentLowLight: Color = ColorRawTokens.colorFunctionalSun300,
    override val repositoryAccentLowestLight: Color = WhiteLabelColorRawTokens.colorWarmGray100,
    override val repositoryAccentDefaultDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val repositoryAccentHighestDark: Color = WhiteLabelColorRawTokens.colorWarmGray900,
    override val repositoryAccentLowDark: Color = ColorRawTokens.colorFunctionalSun300,
    override val repositoryAccentLowestDark: Color = WhiteLabelColorRawTokens.colorWarmGray100,
    override val repositoryInfoDefaultLight: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val repositoryInfoHighestLight: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    override val repositoryInfoLowLight: Color = ColorRawTokens.colorFunctionalDodgerBlue300,
    override val repositoryInfoLowestLight: Color = ColorRawTokens.colorFunctionalDodgerBlue100,
    override val repositoryInfoDefaultDark: Color = ColorRawTokens.colorFunctionalDodgerBlue500,
    override val repositoryInfoHighestDark: Color = ColorRawTokens.colorFunctionalDodgerBlue900,
    override val repositoryInfoLowDark: Color = ColorRawTokens.colorFunctionalDodgerBlue300,
    override val repositoryInfoLowestDark: Color = ColorRawTokens.colorFunctionalDodgerBlue100,
    override val repositoryNegativeDefaultLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val repositoryNegativeHighLight: Color = ColorRawTokens.colorFunctionalScarlet700,
    override val repositoryNegativeHigherLight: Color = ColorRawTokens.colorFunctionalScarlet800,
    override val repositoryNegativeHighestLight: Color = ColorRawTokens.colorFunctionalScarlet900,
    override val repositoryNegativeLowLight: Color = ColorRawTokens.colorFunctionalScarlet300,
    override val repositoryNegativeLowerLight: Color = ColorRawTokens.colorFunctionalScarlet200,
    override val repositoryNegativeLowestLight: Color = ColorRawTokens.colorFunctionalScarlet100,
    override val repositoryNegativeDefaultDark: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val repositoryNegativeHighDark: Color = ColorRawTokens.colorFunctionalScarlet700,
    override val repositoryNegativeHigherDark: Color = ColorRawTokens.colorFunctionalScarlet800,
    override val repositoryNegativeHighestDark: Color = ColorRawTokens.colorFunctionalScarlet900,
    override val repositoryNegativeLowDark: Color = ColorRawTokens.colorFunctionalScarlet300,
    override val repositoryNegativeLowerDark: Color = ColorRawTokens.colorFunctionalScarlet200,
    override val repositoryNegativeLowestDark: Color = ColorRawTokens.colorFunctionalScarlet100,
    override val repositoryNeutralEmphasizedBlackLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val repositoryNeutralEmphasizedHighLight: Color = ColorRawTokens.colorFunctionalDarkGray720,
    override val repositoryNeutralEmphasizedHigherLight: Color = ColorRawTokens.colorFunctionalDarkGray800,
    override val repositoryNeutralEmphasizedHighestLight: Color = ColorRawTokens.colorFunctionalDarkGray880,
    override val repositoryNeutralEmphasizedMediumLight: Color = ColorRawTokens.colorFunctionalDarkGray640,
    override val repositoryNeutralEmphasizedBlackDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val repositoryNeutralEmphasizedHighDark: Color = ColorRawTokens.colorFunctionalDarkGray720,
    override val repositoryNeutralEmphasizedHigherDark: Color = ColorRawTokens.colorFunctionalDarkGray800,
    override val repositoryNeutralEmphasizedHighestDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
    override val repositoryNeutralEmphasizedMediumDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
    override val repositoryNeutralMutedLowerLight: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val repositoryNeutralMutedLowestLight: Color = ColorRawTokens.colorFunctionalLightGray80,
    override val repositoryNeutralMutedWhiteLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val repositoryNeutralMutedLowerDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val repositoryNeutralMutedLowestDark: Color = ColorRawTokens.colorFunctionalLightGray80,
    override val repositoryNeutralMutedWhiteDark: Color = ColorRawTokens.colorFunctionalWhite,
    override val repositoryOpacityBlackHigherLight: Color = ColorRawTokens.colorOpacityBlack680,
    override val repositoryOpacityBlackHighestLight: Color = ColorRawTokens.colorOpacityBlack840,
    override val repositoryOpacityBlackLowLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val repositoryOpacityBlackLowerLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val repositoryOpacityBlackLowestLight: Color = ColorRawTokens.colorOpacityBlack40,
    override val repositoryOpacityBlackMediumLight: Color = ColorRawTokens.colorOpacityBlack280,
    override val repositoryOpacityBlackTransparentLight: Color = ColorRawTokens.colorOpacityBlack0,
    override val repositoryOpacityBlackHigherDark: Color = ColorRawTokens.colorOpacityBlack680,
    override val repositoryOpacityBlackHighestDark: Color = ColorRawTokens.colorOpacityBlack840,
    override val repositoryOpacityBlackLowDark: Color = ColorRawTokens.colorOpacityBlack200,
    override val repositoryOpacityBlackLowerDark: Color = ColorRawTokens.colorOpacityBlack80,
    override val repositoryOpacityBlackLowestDark: Color = ColorRawTokens.colorOpacityBlack40,
    override val repositoryOpacityBlackMediumDark: Color = ColorRawTokens.colorOpacityBlack280,
    override val repositoryOpacityBlackTransparentDark: Color = ColorRawTokens.colorOpacityBlack0,
    override val repositoryOpacityInfoLight: Color = ColorRawTokens.colorOpacityDodgerBlue,
    override val repositoryOpacityNegativeLight: Color = ColorRawTokens.colorOpacityScarlet,
    override val repositoryOpacityPositiveLight: Color = ColorRawTokens.colorOpacityMalachite,
    override val repositoryOpacityWarningLight: Color = ColorRawTokens.colorOpacitySun,
    override val repositoryOpacityWhiteHighLight: Color = ColorRawTokens.colorOpacityWhite640,
    override val repositoryOpacityWhiteHigherLight: Color = ColorRawTokens.colorOpacityWhite800,
    override val repositoryOpacityWhiteHighestLight: Color = ColorRawTokens.colorOpacityWhite920,
    override val repositoryOpacityWhiteLowLight: Color = ColorRawTokens.colorOpacityWhite200,
    override val repositoryOpacityWhiteLowerLight: Color = ColorRawTokens.colorOpacityWhite80,
    override val repositoryOpacityWhiteLowestLight: Color = ColorRawTokens.colorOpacityWhite40,
    override val repositoryOpacityWhiteTransparentLight: Color = ColorRawTokens.colorOpacityWhite0,
    override val repositoryOpacityWhiteHighDark: Color = ColorRawTokens.colorOpacityWhite640,
    override val repositoryOpacityWhiteHigherDark: Color = ColorRawTokens.colorOpacityWhite800,
    override val repositoryOpacityWhiteHighestDark: Color = ColorRawTokens.colorOpacityWhite920,
    override val repositoryOpacityWhiteLowDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val repositoryOpacityWhiteLowerDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val repositoryOpacityWhiteLowestDark: Color = ColorRawTokens.colorOpacityWhite40,
    override val repositoryOpacityWhiteTransparentDark: Color = ColorRawTokens.colorOpacityWhite0,
    override val repositoryOpacityInfoDark: Color = ColorRawTokens.colorOpacityDodgerBlue,
    override val repositoryOpacityNegativeDark: Color = ColorRawTokens.colorOpacityScarlet,
    override val repositoryOpacityPositiveDark: Color = ColorRawTokens.colorOpacityMalachite,
    override val repositoryOpacityWarningDark: Color = ColorRawTokens.colorOpacitySun,
    override val repositoryPositiveDefaultLight: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val repositoryPositiveHighestLight: Color = ColorRawTokens.colorFunctionalMalachite900,
    override val repositoryPositiveLowLight: Color = ColorRawTokens.colorFunctionalMalachite300,
    override val repositoryPositiveLowestLight: Color = ColorRawTokens.colorFunctionalMalachite100,
    override val repositoryPositiveDefaultDark: Color = ColorRawTokens.colorFunctionalMalachite500,
    override val repositoryPositiveHighestDark: Color = ColorRawTokens.colorFunctionalMalachite900,
    override val repositoryPositiveLowDark: Color = ColorRawTokens.colorFunctionalMalachite300,
    override val repositoryPositiveLowestDark: Color = ColorRawTokens.colorFunctionalMalachite100,
    override val repositoryPrimaryDefaultLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val repositoryPrimaryLowLight: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val repositoryPrimaryDefaultDark: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val repositoryPrimaryLowDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val repositoryWarningDefaultLight: Color = ColorRawTokens.colorFunctionalSun500,
    override val repositoryWarningHighestLight: Color = ColorRawTokens.colorFunctionalSun900,
    override val repositoryWarningLowLight: Color = ColorRawTokens.colorFunctionalSun300,
    override val repositoryWarningLowestLight: Color = ColorRawTokens.colorFunctionalSun100,
    override val repositoryWarningDefaultDark: Color = ColorRawTokens.colorFunctionalSun500,
    override val repositoryWarningHighestDark: Color = ColorRawTokens.colorFunctionalSun900,
    override val repositoryWarningLowDark: Color = ColorRawTokens.colorFunctionalSun300,
    override val repositoryWarningLowestDark: Color = ColorRawTokens.colorFunctionalSun100
) : OudsColorRepositorySemanticTokens
