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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.theme.OudsThemeContract

/**
 * Theme state source of truth.
 */
class ThemeState(
    val availableThemes: List<OudsThemeContract>,
    currentTheme: OudsThemeContract
) {

    companion object {

        val Saver = run {
            val availableThemesKey = "availableThemes"
            val currentThemeKey = "currentTheme"
            mapSaver(
                save = { state ->
                    mapOf(
                        availableThemesKey to state.availableThemes,
                        currentThemeKey to state.currentTheme,
                    )
                },
                restore = { map ->
                    @Suppress("UNCHECKED_CAST")
                    ThemeState(
                        map[availableThemesKey] as List<OudsThemeContract>,
                        map[currentThemeKey] as OudsThemeContract,
                    )
                }
            )
        }
    }

    var currentTheme by mutableStateOf(currentTheme)
}

@Composable
fun rememberThemeState(
    availableThemes: List<OudsThemeContract>,
    currentTheme: OudsThemeContract
) = rememberSaveable(availableThemes, currentTheme, saver = ThemeState.Saver) {
    ThemeState(availableThemes, currentTheme)
}