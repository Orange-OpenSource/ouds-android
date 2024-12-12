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

import com.orange.ouds.tokens.global.raw.BorderRawTokens

data class OudsBorderSemanticTokens(
    val radiusDefault: Float = BorderRawTokens.borderRadius0,
    val radiusMedium: Float = BorderRawTokens.borderRadius150,
    val radiusNone: Float = BorderRawTokens.borderRadius0,
    val radiusPill: Float = BorderRawTokens.borderRadius9999,
    val radiusShort: Float = BorderRawTokens.borderRadius75,
    val radiusTall: Float = BorderRawTokens.borderRadius300,
    val styleDefault: String = BorderRawTokens.borderStyleSolid,
    val styleDrag: String = BorderRawTokens.borderStyleDashed,
    val widthDefault: Float = BorderRawTokens.borderWidth25,
    val widthFocus: Float = BorderRawTokens.borderWidth125,
    val widthFocusInset: Float = BorderRawTokens.borderWidth50,
    val widthMedium: Float = BorderRawTokens.borderWidth50,
    val widthNone: Float = BorderRawTokens.borderWidth0,
    val widthThick: Float = BorderRawTokens.borderWidth75,
    val widthThicker: Float = BorderRawTokens.borderWidth100,
    val widthThin: Float = BorderRawTokens.borderWidth25
)
