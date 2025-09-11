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
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.sosh.SOSH_THEME_NAME
import com.orange.ouds.theme.sosh.SoshTheme
import com.orange.ouds.theme.wireframe.WIREFRAME_THEME_NAME
import com.orange.ouds.theme.wireframe.WireframeTheme

@Composable
fun rememberThemeState(
    settings: OudsThemeSettings = OudsThemeSettings(),
    themeNames: List<String> = listOf(ORANGE_THEME_NAME, SOSH_THEME_NAME, WIREFRAME_THEME_NAME),
    currentThemeName: String = ORANGE_THEME_NAME
) = rememberSaveable(settings, themeNames, currentThemeName, saver = ThemeState.Saver) {
    ThemeState(settings, themeNames, currentThemeName)
}

/**
 * Theme state source of truth.
 */
class ThemeState(
    settings: OudsThemeSettings,
    private val themeNames: List<String>,
    private var currentThemeName: String
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        settings,
                        themeNames,
                        currentThemeName
                    )
                }
            },
            restore = { list ->
                @Suppress("UNCHECKED_CAST")
                ThemeState(
                    list[0] as OudsThemeSettings,
                    list[1] as List<String>,
                    list[2] as String
                )
            }
        )
    }

    private var _settings by mutableStateOf(settings)
    var settings: OudsThemeSettings
        get() = _settings
        set(value) {
            _settings = value
            themes = getThemes(value, themeNames)
            setCurrentTheme(currentThemeName)
        }

    var themes by mutableStateOf(getThemes(settings, themeNames))
        private set

    var currentTheme by mutableStateOf(getCurrentTheme(currentThemeName))
        private set

    fun setCurrentTheme(name: String) {
        currentThemeName = name
        currentTheme = getCurrentTheme(name)
    }

    private fun getCurrentTheme(name: String): OudsThemeContract {
        return themes.firstOrNull { it.name == name }
            .orElse { themes.firstOrNull { it.name == ORANGE_THEME_NAME } }
            .orElse { themes.first() }
    }

    private fun getThemes(settings: OudsThemeSettings, names: List<String>): List<OudsThemeContract> {
        return with(settings) {
            names.mapNotNull { name ->
                when (name) {
                    ORANGE_THEME_NAME -> OrangeTheme(
                        roundedCornerButtons = roundedCornerButtons.orElse { false },
                        roundedCornerTextInputs = roundedCornerTextInputs.orElse { false }
                    )
                    SOSH_THEME_NAME -> SoshTheme()
                    WIREFRAME_THEME_NAME -> WireframeTheme()
                    else -> null
                }
            }
        }
    }
}
