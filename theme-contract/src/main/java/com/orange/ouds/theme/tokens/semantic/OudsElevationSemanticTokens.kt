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

import com.orange.ouds.tokens.global.raw.ElevationRawTokens

data class OudsElevationSemanticTokens(
    val none: Float = ElevationRawTokens.elevation0,
    val raised: Float = ElevationRawTokens.elevation1,
    val overlayDefault: Float = ElevationRawTokens.elevation2,
    val allSticky: Float = ElevationRawTokens.elevation3,
    val drag: Float = ElevationRawTokens.elevation4,
    val overlayEmphasized: Float = ElevationRawTokens.elevation5,
)