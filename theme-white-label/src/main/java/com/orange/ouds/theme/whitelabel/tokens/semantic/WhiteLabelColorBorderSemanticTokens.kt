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
import com.orange.ouds.theme.tokens.semantic.OudsColorBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorBorderSemanticTokens(
    override val borderDefaultLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val borderDefaultDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val borderMutedLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val borderMutedDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val borderEmphasizedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderEmphasizedDark: Color = ColorRawTokens.colorOpacityWhite920,
    override val borderFocusLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderFocusDark: Color = ColorRawTokens.colorFunctionalGrayLight160,
    override val borderFocusInsetLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val borderFocusInsetDark: Color = ColorRawTokens.colorFunctionalGrayDark880,
    override val borderBrandPrimaryLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val borderBrandPrimaryDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val borderBrandSecondaryLight: Color = Color(0x00ff0000),
    override val borderBrandSecondaryDark: Color = Color(0x00ff0000),
    override val borderBrandTertiaryLight: Color = Color(0x00ff0000),
    override val borderBrandTertiaryDark: Color = Color(0x00ff0000),
    override val borderStatusPositiveLight: Color = Color(0x00ff0000),
    override val borderStatusPositiveDark: Color = Color(0x00ff0000),
    override val borderStatusInfoLight: Color = Color(0x00ff0000),
    override val borderStatusInfoDark: Color = Color(0x00ff0000),
    override val borderStatusWarningLight: Color = Color(0x00ff0000),
    override val borderStatusWarningDark: Color = Color(0x00ff0000),
    override val borderStatusNegativeLight: Color = Color(0x00ff0000),
    override val borderStatusNegativeDark: Color = Color(0x00ff0000),
    override val borderStatusAccentLight: Color = Color(0x00ff0000),
    override val borderStatusAccentDark: Color = Color(0x00ff0000),
    override val borderOnBrandPrimaryLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderOnBrandPrimaryDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val borderOnBrandSecondaryLight: Color = Color(0x00ff0000),
    override val borderOnBrandSecondaryDark: Color = Color(0x00ff0000),
    override val borderOnBrandTertiaryLight: Color = Color(0x00ff0000),
    override val borderOnBrandTertiaryDark: Color = Color(0x00ff0000)
) : OudsColorBorderSemanticTokens
