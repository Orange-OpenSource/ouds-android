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

import com.orange.ouds.tokens.global.raw.BorderRawTokens

data class OudsBorderSemanticTokens(
    val widthNone: Float = BorderRawTokens.borderWidth0,
    val widthDefault: Float = BorderRawTokens.borderWidth25,
    val widthThin: Float = BorderRawTokens.borderWidth25,
    val widthMedium: Float = BorderRawTokens.borderWidth50,
    val widthThick: Float = BorderRawTokens.borderWidth75,
    val widthThicker: Float = BorderRawTokens.borderWidth100,
    val widthOutsideFocus: Float = BorderRawTokens.borderWidth50,
    val radiusNone: Float = BorderRawTokens.borderRadius0,
    val radiusDefault: Float = BorderRawTokens.borderRadius0,
    val radiusShort: Float = BorderRawTokens.borderRadius75,
    val radiusMedium: Float = BorderRawTokens.borderRadius150,
    val radiusTall: Float = BorderRawTokens.borderRadius300,
    val radiusPill: Float = BorderRawTokens.borderRadius9999,
    val styleDefault: String = BorderRawTokens.borderStyleSolid, //TODO Do we need to keep an enum here to avoid developers overriding this with unmanaged values?
    val styleDrag: String = BorderRawTokens.borderStyleDashed //TODO Do we need to keep an enum here to avoid developers overriding this with unmanaged values?
)