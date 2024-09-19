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

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.tokens.global.raw.BorderRawTokens

data class OudsBorderTokens(
    val widthNone: Dp = BorderRawTokens.borderWidth0.dp,
    val widthDefault: Dp = BorderRawTokens.borderWidth25.dp,
    val widthThin: Dp = BorderRawTokens.borderWidth25.dp,
    val widthThick: Dp = BorderRawTokens.borderWidth50.dp,
    val widthThicker: Dp = BorderRawTokens.borderWidth75.dp,
    val widthThickest: Dp = BorderRawTokens.borderWidth100.dp,
    val widthInteractivePrimaryFocus: Dp = BorderRawTokens.borderWidth100.dp,
    val radiusNone: Dp = BorderRawTokens.borderRadius0.dp,
    val radiusDefault: Dp = BorderRawTokens.borderRadius0.dp,
    val radiusShort: Dp = BorderRawTokens.borderRadius75.dp,
    val radiusMedium: Dp = BorderRawTokens.borderRadius150.dp,
    val radiusTall: Dp = BorderRawTokens.borderRadius300.dp,
    val radiusPill: Dp = BorderRawTokens.borderRadius9999.dp,
    val styleDefault: OudsBorderStyle = OudsBorderStyle.fromString(BorderRawTokens.borderStyleSolid),
    val styleDrag: OudsBorderStyle = OudsBorderStyle.fromString(BorderRawTokens.borderStyleDashed)
)

enum class OudsBorderWidthToken {
    None,
    Default,
    Thin,
    Thick,
    Thicker,
    Thickest,
    InteractivePrimaryFocus
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderWidthToken): Dp {
    return when (token) {
        OudsBorderWidthToken.None -> widthNone
        OudsBorderWidthToken.Default -> widthDefault
        OudsBorderWidthToken.Thin -> widthThin
        OudsBorderWidthToken.Thick -> widthThick
        OudsBorderWidthToken.Thicker -> widthThicker
        OudsBorderWidthToken.Thickest -> widthThickest
        OudsBorderWidthToken.InteractivePrimaryFocus -> widthInteractivePrimaryFocus
    }
}

enum class OudsBorderRadiusToken {
    None,
    Default,
    Short,
    Medium,
    Tall,
    Pill
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderRadiusToken): Dp {
    return when (token) {
        OudsBorderRadiusToken.None -> radiusNone
        OudsBorderRadiusToken.Default -> radiusDefault
        OudsBorderRadiusToken.Short -> radiusShort
        OudsBorderRadiusToken.Medium -> radiusMedium
        OudsBorderRadiusToken.Tall -> radiusTall
        OudsBorderRadiusToken.Pill -> radiusPill
    }
}

enum class OudsBorderStyleToken {
    Default,
    Drag
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderStyleToken): OudsBorderStyle {
    return when (token) {
        OudsBorderStyleToken.Default -> styleDefault
        OudsBorderStyleToken.Drag -> styleDrag
    }
}
