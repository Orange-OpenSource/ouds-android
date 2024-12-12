//
// Software Name: OUDS Android
// SPDX-FileCopyrightText: Copyright (c) Orange SA
// SPDX-License-Identifier: MIT
//
// This software is distributed under the MIT license,
// the text of which is available at https://opensource.org/license/MIT/
// or see the "LICENSE" file for more details.
//
// Software description: Android library of reusable graphical components
//

package com.orange.ouds.theme.tokens.components

import androidx.compose.ui.graphics.Color
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.tokens.global.raw.ColorRawTokens

open class OudsSkeletonTokens(
    val colorBg: Color = ColorRawTokens.colorOpacityBlack40,
    val colorGradientMiddle: Color = ColorRawTokens.colorOpacityBlack80,
    val colorGradientStartEnd: OudsColorKeyToken = OudsColorKeyToken.Opacity.Invisible.Black
)
