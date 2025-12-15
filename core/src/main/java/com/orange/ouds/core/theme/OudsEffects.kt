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
import com.orange.ouds.theme.tokens.OudsEffectKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsEffectSemanticTokens

/**
 * Holds the visual effects defined in the OUDS theme.
 *
 * Visual effects, such as blurs and shadows, add depth and realism to the UI,
 * helping to define hierarchy and interaction states.
 *
 * @property blurDrag The radius of the blur effect applied to an element when it is being dragged.
 */
@ConsistentCopyVisibility
data class OudsEffects internal constructor(
    val blurDrag: Int
)

internal fun OudsEffectSemanticTokens.getEffects() = OudsEffects(
    blurDrag = blurDrag,
)

@Stable
private fun OudsEffects.fromToken(token: OudsEffectKeyToken): Int {
    return when (token) {
        OudsEffectKeyToken.Blur.Drag -> blurDrag
    }
}


/**
 * Converts an OUDS effect token to the local effect value provided by the theme.
 *
 * @suppress
 */
@InternalOudsApi
val OudsEffectKeyToken.value: Int
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.effects.fromToken(this)