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

data class OudsGridTokens(
    val minWidth: OudsAdaptiveTokenValue<Dp> = OudsAdaptiveTokenValue(
        GridRawTokens.gridMinWidthExtraCompact.dp,
        GridRawTokens.gridMinWidthCompact.dp,
        GridRawTokens.gridMinWidthMedium.dp
    ),
    val maxWidth: OudsAdaptiveTokenValue<Dp> = OudsAdaptiveTokenValue(
        GridRawTokens.gridMaxWidthExtraCompact.dp,
        GridRawTokens.gridMaxWidthCompact.dp,
        GridRawTokens.gridMaxWidthMedium.dp
    ),
    val margin: OudsAdaptiveTokenValue<Dp> = OudsAdaptiveTokenValue(
        GridRawTokens.gridMargin100.dp,
        GridRawTokens.gridMargin300.dp,
        GridRawTokens.gridMargin500.dp
    ),
    val columnGap: OudsAdaptiveTokenValue<Dp> = OudsAdaptiveTokenValue(
        GridRawTokens.gridColumnGap100.dp,
        GridRawTokens.gridColumnGap200.dp,
        GridRawTokens.gridColumnGap400.dp
    ),
)

enum class OudsGridKeyToken {
    MinWidth,
    MaxWidth,
    ColumnGap,
    Margin,
}

@Stable
fun OudsGridTokens.fromToken(token: OudsGridKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
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