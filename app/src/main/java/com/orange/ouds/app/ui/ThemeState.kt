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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeDefaults
import com.orange.ouds.theme.OudsThemeContract

/**
 * Theme state source of truth.
 */
class ThemeState(
    val availableThemes: List<OudsThemeContract>,
    currentTheme: OudsThemeContract,
    settings: OudsTheme.Settings
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        availableThemes,
                        currentTheme,
                        settings
                    )
                }
            },
            restore = { list ->
                @Suppress("UNCHECKED_CAST")
                ThemeState(
                    list[0] as List<OudsThemeContract>,
                    list[1] as OudsThemeContract,
                    list[2] as OudsTheme.Settings
                )
            }
        )
    }

    var currentTheme by mutableStateOf(currentTheme)

    var settings by mutableStateOf(settings)
}

@Composable
fun rememberThemeState(
    availableThemes: List<OudsThemeContract>,
    currentTheme: OudsThemeContract,
    settings: OudsTheme.Settings = OudsThemeDefaults.Settings
) = rememberSaveable(availableThemes, currentTheme, settings, saver = ThemeState.Saver) {
    ThemeState(availableThemes, currentTheme, settings)
}
