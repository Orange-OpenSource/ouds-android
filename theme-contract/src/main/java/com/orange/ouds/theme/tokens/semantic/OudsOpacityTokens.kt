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

import com.orange.ouds.tokens.OpacityRawTokens

class OudsOpacityTokens(
    var transparent: Float = OpacityRawTokens.opacity0,
    var weaker: Float = OpacityRawTokens.opacity100,
    var weak: Float = OpacityRawTokens.opacity300,
    var medium: Float = OpacityRawTokens.opacity500,
    var emphasis: Float = OpacityRawTokens.opacity700,
    var opaque: Float = OpacityRawTokens.opacity900
)