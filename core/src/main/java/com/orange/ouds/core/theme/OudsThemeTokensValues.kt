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

import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.toSize
import com.orange.ouds.theme.OudsAdaptiveDisplayType
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.fromToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderRadiusToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderStyleToken
import com.orange.ouds.theme.tokens.semantic.OudsBorderWidthToken
import com.orange.ouds.theme.tokens.semantic.OudsColorToken
import com.orange.ouds.theme.tokens.semantic.OudsElevationToken
import com.orange.ouds.theme.tokens.semantic.OudsGridToken
import com.orange.ouds.theme.tokens.semantic.fromToken

/**
 * Converts an OUDS border radius token to the local border radius value provided by the theme.
 */
val OudsBorderRadiusToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borderTokens.fromToken(this)

/**
 * Converts an OUDS border style token to the local [OudsBorderStyle] value provided by the theme.
 */
val OudsBorderStyleToken.value: OudsBorderStyle
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borderTokens.fromToken(this)

/**
 * Converts an OUDS border width token to the local border width value provided by the theme.
 */
val OudsBorderWidthToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.borderTokens.fromToken(this)

/**
 * Converts an OUDS color token to the local color value provided by the theme.
 */
val OudsColorToken.value: Color
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.colorScheme.fromToken(this)

/**
 * Converts an OUDS elevation token to the local elevation value provided by the theme.
 */
val OudsElevationToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.elevationTokens.fromToken(this)

/**
 * Converts an OUDS grid token to the local grid value provided by the theme.
 * Note that grid token value returned varies depending on the screen size.
 */
val OudsGridToken.value: Dp
    @Composable
    get() {
        val windowWidth = with(LocalDensity.current) { currentWindowSize().toSize().toDpSize().width }
        return OudsTheme.gridTokens.fromToken(this, OudsAdaptiveDisplayType.fromWindowWidth(windowWidth))
    }