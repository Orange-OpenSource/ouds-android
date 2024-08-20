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

import com.orange.ouds.tokens.raw.Opacity

class OudsSemanticOpacity(
    var transparent: Float = Opacity.opacity0,
    var weaker: Float = Opacity.opacity100,
    var weak: Float = Opacity.opacity300,
    var medium: Float = Opacity.opacity500,
    var emphasis: Float = Opacity.opacity700,
    var opaque: Float = Opacity.opacity900
)