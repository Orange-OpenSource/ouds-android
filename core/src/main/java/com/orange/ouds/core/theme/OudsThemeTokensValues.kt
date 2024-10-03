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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.fromToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsGridKeyToken
import com.orange.ouds.theme.tokens.semantic.fromToken

/**
 * Converts an OUDS color token to the local color value provided by the theme.
 */
val OudsColorKeyToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS grid token to the local grid value provided by the theme.
 * Note that grid token value returned varies depending on the window size.
 */
val OudsGridKeyToken.value: Dp
    @Composable
    get() = OudsTheme.gridTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))