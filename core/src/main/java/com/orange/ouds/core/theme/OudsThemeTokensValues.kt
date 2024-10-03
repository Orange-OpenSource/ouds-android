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
import androidx.compose.ui.unit.Dp
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.tokens.semantic.OudsSizeIconDecorativeKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSizeIconWithTypeKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSizeMaxWidthTypeKeyToken
import com.orange.ouds.theme.tokens.semantic.fromToken

/**
 * Converts an OUDS decorative icon size token to the local decorative icon size value provided by the theme.
 */
val OudsSizeIconDecorativeKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.sizeTokens.fromToken(this)

/**
 * Converts an OUDS icon size with typography token to the local icon size with typography value provided by the theme depending on the window size.
 */
val OudsSizeIconWithTypeKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizeTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS max width with typography token to the local max width with typography value provided by the theme depending on the window size.
 */
val OudsSizeMaxWidthTypeKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizeTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))