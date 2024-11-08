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

import androidx.compose.ui.graphics.Color
import com.orange.ouds.tokens.global.raw.ColorRawTokens

data class OudsColorTransparentSemanticTokens(
    val transparentDefaultLight: Color = ColorRawTokens.colorTransparentBlack0,
    val transparentDefaultDark: Color = ColorRawTokens.colorTransparentBlack0,
)