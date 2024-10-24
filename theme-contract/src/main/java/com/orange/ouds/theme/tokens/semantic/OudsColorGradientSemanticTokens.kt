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

data class OudsColorGradientSemanticTokens(
    val gradientSkeletonMiddleLight: Color = ColorRawTokens.colorFunctionalLightGray160,
    val gradientSkeletonMiddleOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray640,
    val gradientSkeletonStartEndLight: Color = ColorRawTokens.colorFunctionalLightGray80,
    val gradientSkeletonStartEndOnBgEmphasizedLight: Color = ColorRawTokens.colorFunctionalDarkGray720,
    val gradientSkeletonMiddleDark: Color = ColorRawTokens.colorFunctionalDarkGray720,
    val gradientSkeletonMiddleOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray560,
    val gradientSkeletonStartEndDark: Color = ColorRawTokens.colorFunctionalDarkGray800,
    val gradientSkeletonStartEndOnBgEmphasizedDark: Color = ColorRawTokens.colorFunctionalDarkGray640,
)