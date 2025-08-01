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

// OUDS Core tokens version 1.2.0
//
// Android Core tokens version 1.0.0
// Android System tokens version 1.0.0
//
// Orange Core tokens version 1.1.0
// Orange Brand tokens version 1.2.0
//
// Generated by Tokenator

package com.orange.ouds.theme.sosh.tokens.semantic

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.sosh.tokens.raw.SoshColorRawTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.tokens.raw.ColorRawTokens

data class SoshColorBorderSemanticTokens(
    override val borderBrandPrimaryDark: Color = SoshColorRawTokens.colorMagenta300,
    override val borderBrandPrimaryLight: Color = SoshColorRawTokens.colorMagenta500,
    override val borderBrandSecondaryDark: Color = SoshColorRawTokens.colorBlueDuckLight800,
    override val borderBrandSecondaryLight: Color = SoshColorRawTokens.colorBlueDuckDark400,
    override val borderBrandTertiaryDark: Color = SoshColorRawTokens.colorCitrine300,
    override val borderBrandTertiaryLight: Color = SoshColorRawTokens.colorCitrine500,
    override val borderDefaultDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val borderDefaultLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val borderEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite920,
    override val borderEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderFocusDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val borderFocusInsetDark: Color = ColorRawTokens.colorFunctionalGrayDark880,
    override val borderFocusInsetLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val borderFocusLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderMutedDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val borderMutedLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val borderOnBrandPrimaryDark: Color = SoshColorRawTokens.colorBlueDuckDark960,
    override val borderOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val borderOnBrandSecondaryDark: Color = SoshColorRawTokens.colorBlueDuckDark960,
    override val borderOnBrandSecondaryLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val borderOnBrandTertiaryDark: Color = SoshColorRawTokens.colorBlueDuckDark960,
    override val borderOnBrandTertiaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderStatusAccentDark: Color = Color(0x00ff0000),
    override val borderStatusAccentLight: Color = Color(0x00ff0000),
    override val borderStatusInfoDark: Color = Color(0x00ff0000),
    override val borderStatusInfoLight: Color = Color(0x00ff0000),
    override val borderStatusNegativeDark: Color = Color(0x00ff0000),
    override val borderStatusNegativeLight: Color = Color(0x00ff0000),
    override val borderStatusPositiveDark: Color = Color(0x00ff0000),
    override val borderStatusPositiveLight: Color = Color(0x00ff0000),
    override val borderStatusWarningDark: Color = Color(0x00ff0000),
    override val borderStatusWarningLight: Color = Color(0x00ff0000)
) : OudsColorBorderSemanticTokens
