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
import com.orange.ouds.theme.OudsThemeContract

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> { error("CompositionLocal LocalThemeManager not present") }

interface ThemeManager {
    val availableThemes: List<OudsThemeContract>
    var currentTheme: OudsThemeContract
}

/**
 * Theme state source of truth.
 */
class ThemeState(
    override val availableThemes: List<OudsThemeContract>,
    currentTheme: MutableState<OudsThemeContract>,
) : ThemeManager {

    override var currentTheme by currentTheme
}

@Composable
fun rememberThemeState(
    availableThemes: List<OudsThemeContract>,
    currentTheme: MutableState<OudsThemeContract>
) = remember(availableThemes, currentTheme) {
    ThemeState(availableThemes, currentTheme)
}