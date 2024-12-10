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

import com.orange.ouds.tokens.global.raw.OpacityRawTokens

data class OudsOpacitySemanticTokens(
  val invisible: Float = OpacityRawTokens.opacity0,
  val medium: Float = OpacityRawTokens.opacity320,
  val opaque: Float = OpacityRawTokens.opacity1000,
  val strong: Float = OpacityRawTokens.opacity640,
  val weak: Float = OpacityRawTokens.opacity160,
  val weaker: Float = OpacityRawTokens.opacity40
)
