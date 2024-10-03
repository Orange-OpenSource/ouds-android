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

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.tokens.OudsBorderRadiusKeyToken
import com.orange.ouds.theme.tokens.OudsBorderStyleKeyToken
import com.orange.ouds.theme.tokens.OudsBorderWidthKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens

data class OudsBorders(
    val widthNone: Dp,
    val widthDefault: Dp,
    val widthThin: Dp,
    val widthMedium: Dp,
    val widthThick: Dp,
    val widthThicker: Dp,
    val widthOutsideFocus: Dp,
    val radiusNone: Dp,
    val radiusDefault: Dp,
    val radiusShort: Dp,
    val radiusMedium: Dp,
    val radiusTall: Dp,
    val radiusPill: Dp,
    val styleDefault: OudsBorderStyle,
    val styleDrag: OudsBorderStyle,
)

fun OudsBorderSemanticTokens.getBorders() = OudsBorders(
    widthNone = widthNone.dp,
    widthDefault = widthDefault.dp,
    widthThin = widthThin.dp,
    widthMedium = widthMedium.dp,
    widthThick = widthThick.dp,
    widthThicker = widthThicker.dp,
    widthOutsideFocus = widthOutsideFocus.dp,
    radiusNone = radiusNone.dp,
    radiusDefault = radiusDefault.dp,
    radiusShort = radiusShort.dp,
    radiusMedium = radiusMedium.dp,
    radiusTall = radiusTall.dp,
    radiusPill = radiusPill.dp,
    styleDefault = OudsBorderStyle.fromString(styleDefault),
    styleDrag = OudsBorderStyle.fromString(styleDrag)
)

@Stable
fun OudsBorders.fromToken(token: OudsBorderWidthKeyToken): Dp {
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

@Stable
fun OudsBorders.fromToken(token: OudsBorderRadiusKeyToken): Dp {
    return when (token) {
        OudsBorderRadiusKeyToken.None -> radiusNone
        OudsBorderRadiusKeyToken.Default -> radiusDefault
        OudsBorderRadiusKeyToken.Short -> radiusShort
        OudsBorderRadiusKeyToken.Medium -> radiusMedium
        OudsBorderRadiusKeyToken.Tall -> radiusTall
        OudsBorderRadiusKeyToken.Pill -> radiusPill
    }
}

@Stable
fun OudsBorders.fromToken(token: OudsBorderStyleKeyToken): OudsBorderStyle {
    return when (token) {
        OudsBorderStyleKeyToken.Default -> styleDefault
        OudsBorderStyleKeyToken.Drag -> styleDrag
    }
}

/**
 * Converts an OUDS border radius token to the local border radius value provided by the theme.
 */
val OudsBorderRadiusKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border style token to the local [OudsBorderStyle] value provided by the theme.
 */
val OudsBorderStyleKeyToken.value: OudsBorderStyle
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border width token to the local border width value provided by the theme.
 */
val OudsBorderWidthKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)