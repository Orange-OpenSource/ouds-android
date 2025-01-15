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
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens

/**
 * @suppress
 */
data class OudsGrids(
    val minWidth: Dp,
    val maxWidth: Dp,
    val margin: Dp,
    val columnGap: Dp
)

internal fun OudsGridSemanticTokens.getGrids(windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsGrids(
        minWidth = getTokenValue(extraCompactMinWidth, compactMinWidth, mediumMinWidth).dp,
        maxWidth = getTokenValue(extraCompactMaxWidth, compactMaxWidth, mediumMaxWidth).dp,
        margin = getTokenValue(extraCompactMargin, compactMargin, mediumMargin).dp,
        columnGap = getTokenValue(extraCompactColumnGap, compactColumnGap, mediumColumnGap).dp
    )
}

@Stable
private fun OudsGrids.fromToken(token: OudsGridKeyToken): Dp {
    return when (token) {
        OudsGridKeyToken.MinWidth -> minWidth
        OudsGridKeyToken.MaxWidth -> maxWidth
        OudsGridKeyToken.ColumnGap -> columnGap
        OudsGridKeyToken.Margin -> margin
    }
}

/**
 * Converts an OUDS grid token to the local grid value provided by the theme.
 * Note that grid token value returned varies depending on the window size.
 */
@InternalOudsApi
val OudsGridKeyToken.value: Dp
    @Composable
    get() = OudsTheme.grids.fromToken(this)