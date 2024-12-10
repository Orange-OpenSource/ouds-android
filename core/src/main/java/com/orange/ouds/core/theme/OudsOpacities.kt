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

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens

data class OudsOpacities(
    val invisible: Float,
    val weaker: Float,
    val weak: Float,
    val medium: Float,
    val strong: Float,
    val opaque: Float,
)

fun OudsOpacitySemanticTokens.getOpacity() = OudsOpacities(
    invisible = invisible,
    weaker = weaker,
    weak = weak,
    medium = medium,
    strong = strong,
    opaque = opaque,
)

@Stable
fun OudsOpacities.fromToken(token: OudsOpacityKeyToken): Float {
    return when (token) {
        OudsOpacityKeyToken.Invisible -> invisible
        OudsOpacityKeyToken.Weaker -> weaker
        OudsOpacityKeyToken.Weak -> weak
        OudsOpacityKeyToken.Medium -> medium
        OudsOpacityKeyToken.Strong -> strong
        OudsOpacityKeyToken.Opaque -> opaque
    }
}


/**
 * Converts an OUDS opacity token to the local opacity value provided by the theme.
 */
val OudsOpacityKeyToken.value: Float
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.opacities.fromToken(this)