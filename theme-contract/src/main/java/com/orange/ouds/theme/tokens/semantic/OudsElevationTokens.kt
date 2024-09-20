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
import androidx.compose.ui.unit.Dp
import com.orange.ouds.tokens.global.raw.ElevationRawTokens

data class OudsElevationTokens(
    val none: Dp = ElevationRawTokens.elevation0,
    val raised: Dp = ElevationRawTokens.elevation1,
    val overlayDefault: Dp = ElevationRawTokens.elevation2,
    val allSticky: Dp = ElevationRawTokens.elevation3,
    val drag: Dp = ElevationRawTokens.elevation4,
    val overlayEmphasized: Dp = ElevationRawTokens.elevation5,
)

enum class OudsElevationKeyToken {
    None,
    Raised,
    OverlayDefault,
    AllSticky,
    Drag,
    OverlayEmphasized
}

@Stable
fun OudsElevationTokens.fromToken(token: OudsElevationKeyToken): Dp {
    return when (token) {
        OudsElevationKeyToken.None -> none
        OudsElevationKeyToken.Raised -> raised
        OudsElevationKeyToken.OverlayDefault -> overlayDefault
        OudsElevationKeyToken.AllSticky -> allSticky
        OudsElevationKeyToken.Drag -> drag
        OudsElevationKeyToken.OverlayEmphasized -> overlayEmphasized
    }
}