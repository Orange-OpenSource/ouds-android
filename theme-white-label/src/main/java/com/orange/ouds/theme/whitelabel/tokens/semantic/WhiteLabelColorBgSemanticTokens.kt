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
import com.orange.ouds.theme.tokens.semantic.OudsColorBgSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.raw.WhiteLabelColorRawTokens
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class WhiteLabelColorBgSemanticTokens(
    override val bgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray880,
    override val bgPrimaryLight: Color = ColorRawTokens.colorFunctionalWhite,
    override val bgSecondaryLight: Color = ColorRawTokens.colorFunctionalLightGray80,
    override val bgTertiaryLight: Color = WhiteLabelColorRawTokens.colorWarmGray100,
    override val bgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
    override val bgPrimaryDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
    override val bgSecondaryDark: Color = ColorRawTokens.colorFunctionalDarkGray800,
    override val bgTertiaryDark: Color = WhiteLabelColorRawTokens.colorWarmGray900
) : OudsColorBgSemanticTokens
