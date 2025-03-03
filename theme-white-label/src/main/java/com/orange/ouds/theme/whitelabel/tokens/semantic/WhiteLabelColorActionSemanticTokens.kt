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
import com.orange.ouds.theme.tokens.semantic.OudsColorActionSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorActionSemanticTokens(
    override val actionDisabledLight: Color = ColorRawTokens.colorOpacityBlack200,
    override val actionEnabledLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val actionFocusLight: Color = ColorRawTokens.colorOpacityBlack680,
    override val actionHighlightedLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val actionHoverLight: Color = ColorRawTokens.colorOpacityBlack680,
    override val actionLoadingLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val actionNegativeEnabledLight: Color = ColorRawTokens.colorFunctionalScarlet600,
    override val actionNegativeFocusLight: Color = ColorRawTokens.colorFunctionalScarlet700,
    override val actionNegativeHoverLight: Color = ColorRawTokens.colorFunctionalScarlet700,
    override val actionNegativeLoadingLight: Color = ColorRawTokens.colorFunctionalScarlet800,
    override val actionNegativePressedLight: Color = ColorRawTokens.colorFunctionalScarlet800,
    override val actionNegativeEnabledDark: Color = ColorRawTokens.colorFunctionalScarlet300,
    override val actionNegativeFocusDark: Color = ColorRawTokens.colorFunctionalScarlet200,
    override val actionNegativeHoverDark: Color = ColorRawTokens.colorFunctionalScarlet200,
    override val actionNegativeLoadingDark: Color = ColorRawTokens.colorFunctionalScarlet100,
    override val actionNegativePressedDark: Color = ColorRawTokens.colorFunctionalScarlet100,
    override val actionPressedLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val actionSelectedLight: Color = WhiteLabelColorRawTokens.colorBlue600,
    override val actionSupportEnabledLight: Color = ColorRawTokens.colorOpacityBlack40,
    override val actionSupportFocusLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val actionSupportHoverLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val actionSupportLoadingLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val actionSupportPressedLight: Color = ColorRawTokens.colorOpacityBlack80,
    override val actionSupportEnabledDark: Color = ColorRawTokens.colorOpacityWhite40,
    override val actionSupportFocusDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val actionSupportHoverDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val actionSupportLoadingDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val actionSupportPressedDark: Color = ColorRawTokens.colorOpacityWhite80,
    override val actionVisitedLight: Color = ColorRawTokens.colorDecorativeAmethyst600,
    override val actionDisabledDark: Color = ColorRawTokens.colorOpacityWhite200,
    override val actionEnabledDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val actionFocusDark: Color = ColorRawTokens.colorOpacityWhite640,
    override val actionHighlightedDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val actionHoverDark: Color = ColorRawTokens.colorOpacityWhite640,
    override val actionLoadingDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val actionPressedDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val actionSelectedDark: Color = WhiteLabelColorRawTokens.colorBlue500,
    override val actionVisitedDark: Color = ColorRawTokens.colorDecorativeAmethyst400
) : OudsColorActionSemanticTokens
