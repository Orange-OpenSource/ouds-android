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

package com.orange.ouds.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ouds.theme.OudsCustomTheme

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> { error("CompositionLocal LocalThemeManager not present") }

interface ThemeManager {
    val availableThemes: List<OudsCustomTheme>
    var currentTheme: OudsCustomTheme
    var darkModeEnabled: Boolean
}

/**
 * Theme state source of truth.
 */
class ThemeState(
    override val availableThemes: List<OudsCustomTheme>,
    currentTheme: MutableState<OudsCustomTheme>,
    darkModeEnabled: MutableState<Boolean>
) : ThemeManager {

    override var currentTheme by currentTheme

    override var darkModeEnabled by darkModeEnabled
}

@Composable
fun rememberThemeState(
    availableThemes: List<OudsCustomTheme>,
    currentTheme: MutableState<OudsCustomTheme>,
    darkModeEnabled: MutableState<Boolean>,
) = remember(availableThemes, currentTheme, darkModeEnabled) {
    ThemeState(availableThemes, currentTheme, darkModeEnabled)
}