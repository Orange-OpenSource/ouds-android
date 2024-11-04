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
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens

data class OudsBorders(
    val widthNone: Dp,
    val widthDefault: Dp,
    val widthThin: Dp,
    val widthMedium: Dp,
    val widthThick: Dp,
    val widthThicker: Dp,
    val widthFocus: Dp,
    val widthFocusInset: Dp,
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
    widthFocus = widthFocus.dp,
    widthFocusInset = widthFocusInset.dp,
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
fun OudsBorders.fromToken(token: OudsBorderKeyToken.Width): Dp {
    return when (token) {
        OudsBorderKeyToken.Width.None -> widthNone
        OudsBorderKeyToken.Width.Default -> widthDefault
        OudsBorderKeyToken.Width.Thin -> widthThin
        OudsBorderKeyToken.Width.Medium -> widthMedium
        OudsBorderKeyToken.Width.Thick -> widthThick
        OudsBorderKeyToken.Width.Thicker -> widthThicker
        OudsBorderKeyToken.Width.Focus -> widthFocus
        OudsBorderKeyToken.Width.FocusInset -> widthFocusInset
    }
}

@Stable
fun OudsBorders.fromToken(token: OudsBorderKeyToken.Radius): Dp {
    return when (token) {
        OudsBorderKeyToken.Radius.None -> radiusNone
        OudsBorderKeyToken.Radius.Default -> radiusDefault
        OudsBorderKeyToken.Radius.Short -> radiusShort
        OudsBorderKeyToken.Radius.Medium -> radiusMedium
        OudsBorderKeyToken.Radius.Tall -> radiusTall
        OudsBorderKeyToken.Radius.Pill -> radiusPill
    }
}

@Stable
fun OudsBorders.fromToken(token: OudsBorderKeyToken.Style): OudsBorderStyle {
    return when (token) {
        OudsBorderKeyToken.Style.Default -> styleDefault
        OudsBorderKeyToken.Style.Drag -> styleDrag
    }
}

/**
 * Converts an OUDS border radius token to the local border radius value provided by the theme.
 */
val OudsBorderKeyToken.Radius.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border style token to the local [OudsBorderStyle] value provided by the theme.
 */
val OudsBorderKeyToken.Style.value: OudsBorderStyle
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border width token to the local border width value provided by the theme.
 */
val OudsBorderKeyToken.Width.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)