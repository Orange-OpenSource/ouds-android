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

data class OudsColorOpacitySemanticTokens(
    val opacityInvisibleBlackLight: Color = ColorRawTokens.colorOpacityBlack0,
    val opacityInvisibleWhiteLight: Color = ColorRawTokens.colorOpacityWhite0,
    val opacityInvisibleBlackDark: Color = ColorRawTokens.colorOpacityWhite0,
    val opacityInvisibleWhiteDark: Color = ColorRawTokens.colorOpacityWhite0
)
