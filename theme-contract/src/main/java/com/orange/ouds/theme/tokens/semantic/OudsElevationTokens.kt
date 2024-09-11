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

class OudsElevationTokens(
    var none: Dp = ElevationRawTokens.elevation0,
    var raised: Dp = ElevationRawTokens.elevation1,
    var overlayDefault: Dp = ElevationRawTokens.elevation2,
    var allSticky: Dp = ElevationRawTokens.elevation3,
    var drag: Dp = ElevationRawTokens.elevation4,
    var overlayEmphased: Dp = ElevationRawTokens.elevation5,
)

enum class OudsElevationToken {
    None,
    Raised,
    OverlayDefault,
    AllSticky,
    Drag,
    OverlayEmphased
}

@Stable
fun OudsElevationTokens.fromToken(token: OudsElevationToken): Dp {
    return when (token) {
        OudsElevationToken.None -> none
        OudsElevationToken.Raised -> raised
        OudsElevationToken.OverlayDefault -> overlayDefault
        OudsElevationToken.AllSticky -> allSticky
        OudsElevationToken.Drag -> drag
        OudsElevationToken.OverlayEmphased -> overlayEmphased
    }
}