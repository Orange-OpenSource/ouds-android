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
import com.orange.ouds.theme.AdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveDisplayType
import com.orange.ouds.tokens.global.raw.GridRawTokens

class OudsGridTokens(
    var minWidth: AdaptiveTokenValue = AdaptiveTokenValue(GridRawTokens.gridMinWidthExtraCompact.dp, GridRawTokens.gridMinWidthCompact.dp, GridRawTokens.gridMinWidthMedium.dp),
    var maxWidth: AdaptiveTokenValue = AdaptiveTokenValue(GridRawTokens.gridMaxWidthExtraCompact.dp, GridRawTokens.gridMaxWidthCompact.dp, GridRawTokens.gridMaxWidthMedium.dp),
    var margin: AdaptiveTokenValue = AdaptiveTokenValue(GridRawTokens.gridMargin100.dp, GridRawTokens.gridMargin300.dp, GridRawTokens.gridMargin500.dp),
    var columnGap: AdaptiveTokenValue = AdaptiveTokenValue(GridRawTokens.gridColumnGap100.dp, GridRawTokens.gridColumnGap200.dp, GridRawTokens.gridColumnGap400.dp),
)

enum class OudsGridToken {
    MinWidth,
    MaxWidth,
    ColumnGap,
    Margin,
}

@Stable
fun OudsGridTokens.fromToken(token: OudsGridToken, windowSizeClass: OudsAdaptiveDisplayType): Dp {
    return when (token) {
        OudsGridToken.MinWidth -> when (windowSizeClass) {
            OudsAdaptiveDisplayType.EXTRA_COMPACT -> minWidth.extraCompact
            OudsAdaptiveDisplayType.COMPACT -> minWidth.compact
            OudsAdaptiveDisplayType.MEDIUM -> minWidth.medium
        }

        OudsGridToken.MaxWidth -> when (windowSizeClass) {
            OudsAdaptiveDisplayType.EXTRA_COMPACT -> maxWidth.extraCompact
            OudsAdaptiveDisplayType.COMPACT -> maxWidth.compact
            OudsAdaptiveDisplayType.MEDIUM -> maxWidth.medium
        }

        OudsGridToken.ColumnGap -> when (windowSizeClass) {
            OudsAdaptiveDisplayType.EXTRA_COMPACT -> columnGap.extraCompact
            OudsAdaptiveDisplayType.COMPACT -> columnGap.compact
            OudsAdaptiveDisplayType.MEDIUM -> columnGap.medium
        }

        OudsGridToken.Margin -> when (windowSizeClass) {
            OudsAdaptiveDisplayType.EXTRA_COMPACT -> margin.extraCompact
            OudsAdaptiveDisplayType.COMPACT -> margin.compact
            OudsAdaptiveDisplayType.MEDIUM -> margin.medium
        }
    }
}