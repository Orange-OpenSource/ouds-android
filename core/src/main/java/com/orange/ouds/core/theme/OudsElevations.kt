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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens

/**
 * @suppress
 */
data class OudsElevations(
    val none: Dp,
    val default: Dp,
    val drag: Dp,
    val emphasized: Dp,
    val raised: Dp,
    val sticky: Dp,
)

internal fun OudsElevationSemanticTokens.getElevation() = OudsElevations(
    none = none.dp,
    raised = raised.dp,
    default = default.dp,
    sticky = sticky.dp,
    drag = drag.dp,
    emphasized = emphasized.dp
)

@Stable
private fun OudsElevations.fromToken(token: OudsElevationKeyToken): Dp {
    return when (token) {
        OudsElevationKeyToken.None -> none
        OudsElevationKeyToken.Default -> default
        OudsElevationKeyToken.Drag -> drag
        OudsElevationKeyToken.Emphasized -> emphasized
        OudsElevationKeyToken.Sticky -> sticky
        OudsElevationKeyToken.Raised -> raised
    }
}

/**
 * Converts an OUDS elevation token to the local elevation value provided by the theme.
 */
@InternalOudsApi
val OudsElevationKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.elevations.fromToken(this)
