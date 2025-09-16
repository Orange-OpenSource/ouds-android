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
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.ui.navigation.AppNavigationState

class TopBarState(
    private val navigationState: AppNavigationState,
    private val themeState: ThemeState
) {

    val showNavigationIcon: Boolean
        @Composable get() = navigationState.currentScreen?.isHome() == false

    val title: String
        @Composable get() = navigationState.currentScreen?.title?.let { stringResource(it) }.orEmpty()

    val actions: List<TopBarAction>
        get() = TopBarAction.entries.filter { action ->
            action != TopBarAction.ChangeThemeSettings || ChangeThemeSettingsDialog.isAvailable(themeState.currentTheme)
        }
}

@Composable
fun rememberTopBarState(navigationState: AppNavigationState, themeState: ThemeState) = remember(navigationState) {
    TopBarState(navigationState, themeState)
}