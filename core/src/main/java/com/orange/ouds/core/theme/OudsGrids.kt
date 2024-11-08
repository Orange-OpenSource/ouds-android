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
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens

data class OudsGrids(
    val minWidth: OudsAdaptiveTokenValue<Dp>,
    val maxWidth: OudsAdaptiveTokenValue<Dp>,
    val margin: OudsAdaptiveTokenValue<Dp>,
    val columnGap: OudsAdaptiveTokenValue<Dp>
)


fun OudsGridSemanticTokens.getGrids() = OudsGrids(
    minWidth = OudsAdaptiveTokenValue(
        extraCompactMinWidth.dp,
        compactMinWidth.dp,
        mediumMinWidth.dp
    ),
    maxWidth = OudsAdaptiveTokenValue(
        extraCompactMaxWidth.dp,
        compactMaxWidth.dp,
        mediumMaxWidth.dp,
    ),
    margin = OudsAdaptiveTokenValue(
        extraCompactMargin.dp,
        compactMargin.dp,
        mediumMargin.dp,
    ),
    columnGap = OudsAdaptiveTokenValue(
        extraCompactColumnGap.dp,
        compactColumnGap.dp,
        mediumColumnGap.dp,
    )
)

@Stable
fun OudsGrids.fromToken(token: OudsGridKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val gridToken = when (token) {
        OudsGridKeyToken.MinWidth -> minWidth
        OudsGridKeyToken.MaxWidth -> maxWidth
        OudsGridKeyToken.ColumnGap -> columnGap
        OudsGridKeyToken.Margin -> margin
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> gridToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> gridToken.compact
        OudsAdaptiveWindowType.MEDIUM -> gridToken.medium
    }
}

/**
 * Converts an OUDS grid token to the local grid value provided by the theme.
 * Note that grid token value returned varies depending on the window size.
 */
val OudsGridKeyToken.value: Dp
    @Composable
    get() = OudsTheme.grids.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))