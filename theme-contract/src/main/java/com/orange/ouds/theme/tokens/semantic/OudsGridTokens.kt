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
import com.orange.ouds.tokens.global.raw.GridRawTokens

class OudsGridTokens(
    var extraCompactMinWidth: Dp = GridRawTokens.gridMinWidthExtraCompact.dp,
    var extraCompactMaxWidth: Dp = GridRawTokens.gridMaxWidthExtraCompact.dp,
    var extraCompactMargin: Dp = GridRawTokens.gridMargin100.dp,
    var extraCompactColumnGap: Dp = GridRawTokens.gridColumnGap100.dp,
    var compactMinWidth: Dp = GridRawTokens.gridMinWidthCompact.dp,
    var compactMaxWidth: Dp = GridRawTokens.gridMaxWidthCompact.dp,
    var compactMargin: Dp = GridRawTokens.gridMargin300.dp,
    var compactColumnGap: Dp = GridRawTokens.gridColumnGap200.dp,
    var mediumMinWidth: Dp = GridRawTokens.gridMinWidthMedium.dp,
    var mediumMaxWidth: Dp = GridRawTokens.gridMaxWidthMedium.dp,
    var mediumMargin: Dp = GridRawTokens.gridMargin500.dp,
    var mediumColumnGap: Dp = GridRawTokens.gridColumnGap400.dp,
)

enum class OudsGridToken {
    ExtraCompactMinWidth,
    ExtraCompactMaxWidth,
    ExtraCompactColumnGap,
    ExtraCompactMargin,
    CompactMinWidth,
    CompactMaxWidth,
    CompactColumnGap,
    CompactMargin,
    MediumMinWidth,
    MediumMaxWidth,
    MediumColumnGap,
    MediumMargin,
}

@Stable
fun OudsGridTokens.fromToken(token: OudsGridToken): Dp {
    return when (token) {
        OudsGridToken.ExtraCompactMinWidth -> extraCompactMinWidth
        OudsGridToken.ExtraCompactMaxWidth -> extraCompactMaxWidth
        OudsGridToken.ExtraCompactColumnGap -> extraCompactColumnGap
        OudsGridToken.ExtraCompactMargin -> extraCompactMargin
        OudsGridToken.CompactMinWidth -> compactMinWidth
        OudsGridToken.CompactMaxWidth -> compactMaxWidth
        OudsGridToken.CompactColumnGap -> compactColumnGap
        OudsGridToken.CompactMargin -> compactMargin
        OudsGridToken.MediumMinWidth -> mediumMinWidth
        OudsGridToken.MediumMaxWidth -> mediumMaxWidth
        OudsGridToken.MediumColumnGap -> mediumColumnGap
        OudsGridToken.MediumMargin -> mediumMargin
    }
}