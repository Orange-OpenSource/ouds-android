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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens

/**
 * @suppress
 */
data class OudsBorders(
    val width: Width,
    val radius: Radius,
    val style: Style
) {
    data class Width(
        val none: Dp,
        val default: Dp,
        val thin: Dp,
        val medium: Dp,
        val thick: Dp,
        val thicker: Dp,
        val focus: Dp,
        val focusInset: Dp
    )

    data class Radius(
        val none: Dp,
        val default: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val pill: Dp
    )

    data class Style(
        val default: OudsBorderStyle,
        val drag: OudsBorderStyle
    )
}

internal fun OudsBorderSemanticTokens.getBorders() = OudsBorders(
    width = OudsBorders.Width(
        none = widthNone.dp,
        default = widthDefault.dp,
        thin = widthThin.dp,
        medium = widthMedium.dp,
        thick = widthThick.dp,
        thicker = widthThicker.dp,
        focus = widthFocus.dp,
        focusInset = widthFocusInset.dp
    ),
    radius = OudsBorders.Radius(
        none = radiusNone.dp,
        default = radiusDefault.dp,
        short = radiusShort.dp,
        medium = radiusMedium.dp,
        tall = radiusTall.dp,
        pill = radiusPill.dp
    ),
    style = OudsBorders.Style(
        default = OudsBorderStyle.fromString(styleDefault),
        drag = OudsBorderStyle.fromString(styleDrag)
    )
)

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Width): Dp {
    return when (token) {
        OudsBorderKeyToken.Width.None -> width.none
        OudsBorderKeyToken.Width.Default -> width.default
        OudsBorderKeyToken.Width.Thin -> width.thin
        OudsBorderKeyToken.Width.Medium -> width.medium
        OudsBorderKeyToken.Width.Thick -> width.thick
        OudsBorderKeyToken.Width.Thicker -> width.thicker
        OudsBorderKeyToken.Width.Focus -> width.focus
        OudsBorderKeyToken.Width.FocusInset -> width.focusInset
    }
}

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Radius): Dp {
    return when (token) {
        OudsBorderKeyToken.Radius.None -> radius.none
        OudsBorderKeyToken.Radius.Default -> radius.default
        OudsBorderKeyToken.Radius.Short -> radius.short
        OudsBorderKeyToken.Radius.Medium -> radius.medium
        OudsBorderKeyToken.Radius.Tall -> radius.tall
        OudsBorderKeyToken.Radius.Pill -> radius.pill
    }
}

@Stable
private fun OudsBorders.fromToken(token: OudsBorderKeyToken.Style): OudsBorderStyle {
    return when (token) {
        OudsBorderKeyToken.Style.Default -> style.default
        OudsBorderKeyToken.Style.Drag -> style.drag
    }
}

/**
 * Converts an OUDS border radius token to the local border radius value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Radius.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border style token to the local [OudsBorderStyle] value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Style.value: OudsBorderStyle
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)

/**
 * Converts an OUDS border width token to the local border width value provided by the theme.
 */
@InternalOudsApi
val OudsBorderKeyToken.Width.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borders.fromToken(this)