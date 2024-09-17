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
import com.orange.ouds.tokens.global.raw.OpacityRawTokens

data class OudsOpacityTokens(
    val transparent: Float = OpacityRawTokens.opacity0,
    val weaker: Float = OpacityRawTokens.opacity100,
    val weak: Float = OpacityRawTokens.opacity300,
    val medium: Float = OpacityRawTokens.opacity500,
    val strong: Float = OpacityRawTokens.opacity700,
    val opaque: Float = OpacityRawTokens.opacity900
)

enum class OudsOpacityToken {
    Transparent,
    Weaker,
    Weak,
    Medium,
    Strong,
    Opaque
}

@Stable
fun OudsOpacityTokens.fromToken(token: OudsOpacityToken): Float {
    return when (token) {
        OudsOpacityToken.Transparent -> transparent
        OudsOpacityToken.Weaker -> weaker
        OudsOpacityToken.Weak -> weak
        OudsOpacityToken.Medium -> medium
        OudsOpacityToken.Strong -> strong
        OudsOpacityToken.Opaque -> opaque
    }
}