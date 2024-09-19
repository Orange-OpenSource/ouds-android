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
import com.orange.ouds.theme.tokens.semantic.OudsDimensionAdaptableSpaceToken
import com.orange.ouds.theme.tokens.semantic.OudsDimensionMaxWidthTypeToken
import com.orange.ouds.theme.tokens.semantic.OudsDimensionSizeIconDecorativeToken
import com.orange.ouds.theme.tokens.semantic.OudsDimensionSizeIconWithTypeToken
import com.orange.ouds.theme.tokens.semantic.OudsDimensionSpaceToken
import com.orange.ouds.theme.tokens.semantic.fromToken

/**
 * Converts an OUDS dimension adaptable space token to the local dimension space value provided by the theme depending on the window size.
 */
val OudsDimensionAdaptableSpaceToken.value: Dp
    @Composable
    get() = OudsTheme.dimensionTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS dimension space token to the local dimension space value provided by the theme.
 */
val OudsDimensionSpaceToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.dimensionTokens.fromToken(this)

/**
 * Converts an OUDS dimension decorative icon size token to the local dimension decorative icon size value provided by the theme.
 */
val OudsDimensionSizeIconDecorativeToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.dimensionTokens.fromToken(this)

/**
 * Converts an OUDS dimension icon size with typography token to the local dimension icon size with typography value provided by the theme depending on the window size.
 */
val OudsDimensionSizeIconWithTypeToken.value: Dp
    @Composable
    get() = OudsTheme.dimensionTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS dimension max width with typography token to the local dimension max width with typography value provided by the theme depending on the window size.
 */
val OudsDimensionMaxWidthTypeToken.value: Dp
    @Composable
    get() = OudsTheme.dimensionTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
