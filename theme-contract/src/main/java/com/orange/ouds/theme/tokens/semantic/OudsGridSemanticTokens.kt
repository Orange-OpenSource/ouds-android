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

import com.orange.ouds.tokens.global.raw.GridRawTokens

data class OudsGridSemanticTokens(
    val minWidthExtraCompact: Int = GridRawTokens.gridMinWidthExtraCompact,
    val minWidthCompact: Int = GridRawTokens.gridMinWidthCompact,
    val minWidthMedium: Int = GridRawTokens.gridMinWidthMedium,
    val maxWidthExtraCompact: Int = GridRawTokens.gridMaxWidthExtraCompact,
    val maxWidthCompact: Int = GridRawTokens.gridMaxWidthCompact,
    val maxWidthMedium: Int = GridRawTokens.gridMaxWidthMedium,
    val marginExtraCompact: Int = GridRawTokens.gridMargin100,
    val marginCompact: Int = GridRawTokens.gridMargin300,
    val marginMedium: Int = GridRawTokens.gridMargin500,
    val columnGapExtraCompact: Float = GridRawTokens.gridColumnGap100,
    val columnGapCompact: Float = GridRawTokens.gridColumnGap100,
    val columnGapMedium: Float = GridRawTokens.gridColumnGap400,
)