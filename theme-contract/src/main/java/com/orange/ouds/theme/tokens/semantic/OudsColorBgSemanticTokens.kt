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

data class OudsColorBgSemanticTokens(
    val bgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray880,
    val bgPrimaryLight: Color = ColorRawTokens.colorFunctionalWhite,
    val bgSecondaryLight: Color = ColorRawTokens.colorFunctionalLightGray80,
    val bgTertiaryLight: Color = OrangeBrandColorRawTokens.colorWarmGray100,
    val bgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
    val bgPrimaryDark: Color = ColorRawTokens.colorFunctionalDarkGray880,
    val bgSecondaryDark: Color = ColorRawTokens.colorFunctionalDarkGray800,
    val bgTertiaryDark: Color = OrangeBrandColorRawTokens.colorWarmGray900
)
