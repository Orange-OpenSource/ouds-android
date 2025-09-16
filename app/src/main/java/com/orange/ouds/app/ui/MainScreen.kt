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

import android.app.UiModeManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.content.getSystemService
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.orange.ouds.app.ui.navigation.appNavGraph
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    MainScreen(
        themeSettings = mainViewModel.getUserThemeSettings().orElse { OudsThemeSettings() },
        currentThemeName = mainViewModel.getUserThemeName().orElse { ORANGE_THEME_NAME },
        onThemeChange = { themeName ->
            mainViewModel.storeUserThemeName(themeName)
        },
        onThemeSettingsChange = { themeSettings ->
            mainViewModel.storeUserThemeSettings(themeSettings)
        }
    )
}

@Composable
fun MainScreen(
    themeSettings: OudsThemeSettings,
    currentThemeName: String,
    onThemeChange: (String) -> Unit,
    onThemeSettingsChange: (OudsThemeSettings) -> Unit
) {
    val mainState = rememberMainState(
        themeState = rememberThemeState(
            settings = themeSettings,
            currentThemeName = currentThemeName
        )
    )

    val isSystemInDarkTheme = isSystemInDarkTheme()
    val activity = LocalActivity.current as? ComponentActivity
    activity?.enableEdgeToEdge(
        SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isSystemInDarkTheme },
        SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isSystemInDarkTheme }
    )

    var changeThemeDialogVisible by rememberSaveable { mutableStateOf(false) }
    var changeThemeSettingsDialogVisible by rememberSaveable { mutableStateOf(false) }

    OudsTheme(
        theme = mainState.themeState.currentTheme,
        darkThemeEnabled = isSystemInDarkTheme,
    ) {
        Scaffold(
            contentWindowInsets = ScaffoldDefaults.contentWindowInsets.union(WindowInsets.displayCutout),
            topBar = {
                val context = LocalContext.current
                TopBar(
                    topBarState = mainState.topBarState,
                    upPress = mainState.navigationState::upPress,
                    onActionClick = { action ->
                        when (action) {
                            TopBarAction.ChangeThemeSettings -> changeThemeSettingsDialogVisible = true
                            TopBarAction.ChangeTheme -> changeThemeDialogVisible = true
                            TopBarAction.ChangeMode -> setApplicationNightModeEnabled(!isSystemInDarkTheme, context)
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    currentRoute = mainState.navigationState.currentRoute.orEmpty(),
                    navigateToRoute = { route ->
                        mainState.navigationState.navigateToBottomBarRoute(route)
                    },
                    visible = mainState.showBottomBar
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = mainState.navigationState.navController,
                startDestination = BottomBarItem.Tokens.route,
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding)
            ) {
                appNavGraph(mainState)
            }

            if (changeThemeDialogVisible) {
                ChangeThemeDialog(
                    themeState = mainState.themeState,
                    onThemeChange = { themeName ->
                        mainState.themeState.setCurrentTheme(themeName)
                        onThemeChange(themeName)
                    },
                    onDismissRequest = {
                        changeThemeDialogVisible = false
                    }
                )
            }

            if (changeThemeSettingsDialogVisible) {
                ChangeThemeSettingsDialog(
                    themeState = mainState.themeState,
                    onThemeSettingsChange = { themeSettings ->
                        mainState.themeState.settings = themeSettings
                        onThemeSettingsChange(themeSettings)
                    },
                    onDismissRequest = {
                        changeThemeSettingsDialogVisible = false
                    }
                )
            }
        }
    }
}

private fun setApplicationNightModeEnabled(night: Boolean, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val uiModeManager = context.getSystemService<UiModeManager>()
        val mode = if (night) UiModeManager.MODE_NIGHT_YES else UiModeManager.MODE_NIGHT_NO
        uiModeManager?.setApplicationNightMode(mode)
    } else {
        val mode = if (night) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}

@PreviewLightDark
@Composable
private fun PreviewMainScreen() = OudsPreview {
    // Tokens screen content is not displayed because the tokenCategories property uses sealedSubclasses which does not work in Compose previews
    // See https://issuetracker.google.com/issues/240601093
    MainScreen(
        themeSettings = OudsThemeSettings(),
        currentThemeName = ORANGE_THEME_NAME,
        onThemeChange = {},
        onThemeSettingsChange = {}
    )
}
