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
import com.orange.ouds.theme.tokens.semantic.OudsColorAlwaysSemanticTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorAlwaysSemanticTokens(
    override val alwaysBlackLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysOnBlackLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val alwaysOnWhiteLight: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysWhiteLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val alwaysBlackDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysOnBlackDark: Color = ColorRawTokens.colorFunctionalLightGray160,
    override val alwaysOnWhiteDark: Color = ColorRawTokens.colorFunctionalBlack,
    override val alwaysWhiteDark: Color = ColorRawTokens.colorFunctionalWhite
) : OudsColorAlwaysSemanticTokens
