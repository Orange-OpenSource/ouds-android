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
    val widthMedium: Dp = BorderRawTokens.borderWidth50.dp,
    val widthThick: Dp = BorderRawTokens.borderWidth75.dp,
    val widthThicker: Dp = BorderRawTokens.borderWidth100.dp,
    val widthOutsideFocus: Dp = BorderRawTokens.borderWidth50.dp,
    val radiusNone: Dp = BorderRawTokens.borderRadius0.dp,
    val radiusDefault: Dp = BorderRawTokens.borderRadius0.dp,
    val radiusShort: Dp = BorderRawTokens.borderRadius75.dp,
    val radiusMedium: Dp = BorderRawTokens.borderRadius150.dp,
    val radiusTall: Dp = BorderRawTokens.borderRadius300.dp,
    val radiusPill: Dp = BorderRawTokens.borderRadius9999.dp,
    val styleDefault: OudsBorderStyle = OudsBorderStyle.fromString(BorderRawTokens.borderStyleSolid),
    val styleDrag: OudsBorderStyle = OudsBorderStyle.fromString(BorderRawTokens.borderStyleDashed)
)

enum class OudsBorderWidthKeyToken {
    None,
    Default,
    Thin,
    Medium,
    Thick,
    Thicker,
    OutsideFocus
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderWidthKeyToken): Dp {
    return when (token) {
        OudsBorderWidthKeyToken.None -> widthNone
        OudsBorderWidthKeyToken.Default -> widthDefault
        OudsBorderWidthKeyToken.Thin -> widthThin
        OudsBorderWidthKeyToken.Medium -> widthMedium
        OudsBorderWidthKeyToken.Thick -> widthThick
        OudsBorderWidthKeyToken.Thicker -> widthThicker
        OudsBorderWidthKeyToken.OutsideFocus -> widthOutsideFocus
    }
}

enum class OudsBorderRadiusKeyToken {
    None,
    Default,
    Short,
    Medium,
    Tall,
    Pill
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderRadiusKeyToken): Dp {
    return when (token) {
        OudsBorderRadiusKeyToken.None -> radiusNone
        OudsBorderRadiusKeyToken.Default -> radiusDefault
        OudsBorderRadiusKeyToken.Short -> radiusShort
        OudsBorderRadiusKeyToken.Medium -> radiusMedium
        OudsBorderRadiusKeyToken.Tall -> radiusTall
        OudsBorderRadiusKeyToken.Pill -> radiusPill
    }
}

enum class OudsBorderStyleKeyToken {
    Default,
    Drag
}

@Stable
fun OudsBorderTokens.fromToken(token: OudsBorderStyleKeyToken): OudsBorderStyle {
    return when (token) {
        OudsBorderStyleKeyToken.Default -> styleDefault
        OudsBorderStyleKeyToken.Drag -> styleDrag
    }
}
