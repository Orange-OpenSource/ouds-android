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
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.tokens.global.raw.GridRawTokens

class OudsGridTokens(
    var minWidth: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        GridRawTokens.gridMinWidthExtraCompact.dp,
        GridRawTokens.gridMinWidthCompact.dp,
        GridRawTokens.gridMinWidthMedium.dp
    ),
    var maxWidth: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        GridRawTokens.gridMaxWidthExtraCompact.dp,
        GridRawTokens.gridMaxWidthCompact.dp,
        GridRawTokens.gridMaxWidthMedium.dp
    ),
    var margin: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        GridRawTokens.gridMargin100.dp,
        GridRawTokens.gridMargin300.dp,
        GridRawTokens.gridMargin500.dp
    ),
    var columnGap: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        GridRawTokens.gridColumnGap100.dp,
        GridRawTokens.gridColumnGap200.dp,
        GridRawTokens.gridColumnGap400.dp
    ),
)

enum class OudsGridToken {
    MinWidth,
    MaxWidth,
    ColumnGap,
    Margin,
}

@Stable
fun OudsGridTokens.fromToken(token: OudsGridToken, windowSizeClass: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsGridToken.MinWidth -> when (windowSizeClass) {
            OudsAdaptiveWindowType.EXTRA_COMPACT -> minWidth.extraCompact
            OudsAdaptiveWindowType.COMPACT -> minWidth.compact
            OudsAdaptiveWindowType.MEDIUM -> minWidth.medium
        }

        OudsGridToken.MaxWidth -> when (windowSizeClass) {
            OudsAdaptiveWindowType.EXTRA_COMPACT -> maxWidth.extraCompact
            OudsAdaptiveWindowType.COMPACT -> maxWidth.compact
            OudsAdaptiveWindowType.MEDIUM -> maxWidth.medium
        }

        OudsGridToken.ColumnGap -> when (windowSizeClass) {
            OudsAdaptiveWindowType.EXTRA_COMPACT -> columnGap.extraCompact
            OudsAdaptiveWindowType.COMPACT -> columnGap.compact
            OudsAdaptiveWindowType.MEDIUM -> columnGap.medium
        }

        OudsGridToken.Margin -> when (windowSizeClass) {
            OudsAdaptiveWindowType.EXTRA_COMPACT -> margin.extraCompact
            OudsAdaptiveWindowType.COMPACT -> margin.compact
            OudsAdaptiveWindowType.MEDIUM -> margin.medium
        }
    }
}