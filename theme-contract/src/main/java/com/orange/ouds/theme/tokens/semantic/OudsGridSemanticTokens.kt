//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

package com.orange.ouds.theme.tokens.semantic

import com.orange.ouds.tokens.global.raw.GridRawTokens

data class OudsGridSemanticTokens(
  val compactColumnGap: Float = GridRawTokens.gridColumnGap100,
  val compactMargin: Int = GridRawTokens.gridMargin300,
  val compactMaxWidth: Int = GridRawTokens.gridMaxWidthCompact,
  val compactMinWidth: Int = GridRawTokens.gridMinWidthCompact,
  val extraCompactColumnGap: Float = GridRawTokens.gridColumnGap100,
  val extraCompactMargin: Int = GridRawTokens.gridMargin100,
  val extraCompactMaxWidth: Int = GridRawTokens.gridMaxWidthExtraCompact,
  val extraCompactMinWidth: Int = GridRawTokens.gridMinWidthExtraCompact,
  val mediumColumnGap: Float = GridRawTokens.gridColumnGap400,
  val mediumMargin: Int = GridRawTokens.gridMargin500,
  val mediumMaxWidth: Int = GridRawTokens.gridMaxWidthMedium,
  val mediumMinWidth: Int = GridRawTokens.gridMinWidthMedium
)
