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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens

/**
 * @suppress
 */
data class OudsOpacities(
    val disabled: Float,
    val invisible: Float,
    val weakest: Float,
    val weaker: Float,
    val weak: Float,
    val medium: Float,
    val strong: Float,
    val opaque: Float,
)

internal fun OudsOpacitySemanticTokens.getOpacity() = OudsOpacities(
    disabled = disabled,
    invisible = invisible,
    weakest = weakest,
    weaker = weaker,
    weak = weak,
    medium = medium,
    strong = strong,
    opaque = opaque,
)

@Stable
private fun OudsOpacities.fromToken(token: OudsOpacityKeyToken): Float {
    return when (token) {
        OudsOpacityKeyToken.Disabled -> disabled
        OudsOpacityKeyToken.Invisible -> invisible
        OudsOpacityKeyToken.Medium -> medium
        OudsOpacityKeyToken.Opaque -> opaque
        OudsOpacityKeyToken.Strong -> strong
        OudsOpacityKeyToken.Weak -> weak
        OudsOpacityKeyToken.Weaker -> weaker
        OudsOpacityKeyToken.Weakest -> weakest
    }
}


/**
 * Converts an OUDS opacity token to the local opacity value provided by the theme.
 */
@InternalOudsApi
val OudsOpacityKeyToken.value: Float
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.opacities.fromToken(this)