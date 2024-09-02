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
import com.orange.ouds.theme.tokens.type.BorderStyle
import com.orange.ouds.tokens.raw.BorderRawTokens

class OudsBorderTokens(
    var widthNone: Dp = BorderRawTokens.borderWidth0.dp,
    var widthDefault: Dp = BorderRawTokens.borderWidth25.dp,
    var widthThin: Dp = BorderRawTokens.borderWidth25.dp,
    var widthThick: Dp = BorderRawTokens.borderWidth50.dp,
    var widthThicker: Dp = BorderRawTokens.borderWidth75.dp,
    var widthThickest: Dp = BorderRawTokens.borderWidth100.dp,
    var widthInteractivePrimaryFocus: Dp = BorderRawTokens.borderWidth100.dp,
    var radiusNone: Dp = BorderRawTokens.borderRadius0.dp,
    var radiusDefault: Dp = BorderRawTokens.borderRadius0.dp,
    var radiusShort: Dp = BorderRawTokens.borderRadius75.dp,
    var radiusMedium: Dp = BorderRawTokens.borderRadius150.dp,
    var radiusTall: Dp = BorderRawTokens.borderRadius300.dp,
    var radiusPill: Dp = BorderRawTokens.borderRadius9999.dp,
    var styleDefault: BorderStyle = BorderStyle.fromString(BorderRawTokens.borderStyleSolid),
    var styleDrag: BorderStyle = BorderStyle.fromString(BorderRawTokens.borderStyleDashed)
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
fun OudsBorderTokens.fromToken(token: OudsBorderStyleToken): BorderStyle {
    return when (token) {
        OudsBorderStyleToken.Default -> styleDefault
        OudsBorderStyleToken.Drag -> styleDrag
    }
}
