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
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens

data class OudsElevations(
    val none: Dp,
    val raised: Dp,
    val overlayDefault: Dp,
    val allSticky: Dp,
    val drag: Dp,
    val overlayEmphasized: Dp,
)

fun OudsElevationSemanticTokens.getElevation() = OudsElevations(
    none = none.dp,
    raised = raised.dp,
    overlayDefault = overlayDefault.dp,
    allSticky = allSticky.dp,
    drag = drag.dp,
    overlayEmphasized = overlayEmphasized.dp
)

@Stable
fun OudsElevations.fromToken(token: OudsElevationKeyToken): Dp {
    return when (token) {
        OudsElevationKeyToken.None -> none
        OudsElevationKeyToken.Raised -> raised
        OudsElevationKeyToken.OverlayDefault -> overlayDefault
        OudsElevationKeyToken.AllSticky -> allSticky
        OudsElevationKeyToken.Drag -> drag
        OudsElevationKeyToken.OverlayEmphasized -> overlayEmphasized
    }
}

/**
 * Converts an OUDS elevation token to the local elevation value provided by the theme.
 */
val OudsElevationKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.elevations.fromToken(this)
