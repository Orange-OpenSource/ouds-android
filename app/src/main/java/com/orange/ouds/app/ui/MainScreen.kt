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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.navigation.appNavGraph
import com.orange.ouds.app.ui.utilities.isDarkModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsCustomTheme
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(customThemes: List<OudsCustomTheme>, mainViewModel: MainViewModel = viewModel()) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val mainState = rememberMainState(
        themeState = rememberThemeState(
            currentTheme = rememberSaveable {
                mutableStateOf(
                    getCurrentCustomTheme(
                        mainViewModel.getUserThemeName(),
                        customThemes
                    )
                )
            },
            darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) },
            customThemes = customThemes
        )
    )

    // Change isSystemInDarkTheme() value to make switching theme working with custom color
    val configuration = LocalConfiguration.current.apply {
        isDarkModeEnabled = mainState.themeState.darkModeEnabled
    }

    var changeThemeDialogVisible by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalThemeManager provides mainState.themeState,
    ) {
        TopBarActionsHandler(onChangeThemeActionClick = { changeThemeDialogVisible = true })
        OudsTheme(
            customTheme = mainState.themeState.currentTheme,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            Scaffold(
                topBar = { TopBar(mainState.topBarState) },
                bottomBar = {
                    AnimatedVisibility(
                        visible = mainState.showBottomBar,
                        enter = fadeIn(tween(100)),
                        exit = fadeOut(tween(100))
                    ) {
                        BottomBar(
                            currentRoute = mainState.navigationState.currentRoute.orEmpty(),
                            navigateToRoute = { route ->
                                mainState.navigationState.navigateToBottomBarRoute(route)
                            }
                        )
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = mainState.navigationState.navController,
                    startDestination = BottomBarItem.Guidelines.route,
                    modifier = Modifier.padding(innerPadding),
                    exitTransition = { ExitTransition.None },
                ) {
                    appNavGraph(mainState.navigationState.navController)
                }

                if (changeThemeDialogVisible) {
                    ChangeThemeDialog(
                        themeManager = mainState.themeState,
                        dismissDialog = {
                            changeThemeDialogVisible = false
                        },
                        onThemeSelected = {
                            mainViewModel.storeUserThemeName(mainState.themeState.currentTheme.name)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBarActionsHandler(onChangeThemeActionClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val themeManager = LocalThemeManager.current

    // Manage top bar actions clicked
    LaunchedEffect(key1 = Unit) {
        Screen.topBarActionClicked.onEach { action ->
            when (action) {
                TopBarAction.ChangeTheme -> onChangeThemeActionClick()
                TopBarAction.ChangeMode -> themeManager.darkModeEnabled = !configuration.isDarkModeEnabled
            }
        }.launchIn(this)
    }
}

@Composable
private fun ChangeThemeDialog(themeManager: ThemeManager, dismissDialog: () -> Unit, onThemeSelected: () -> Unit) {
    var selectedThemeName by rememberSaveable { mutableStateOf(themeManager.currentTheme.name) }

    //TODO Use OudsDialog when available
    Dialog(onDismissRequest = dismissDialog) {
        Column(
            modifier = Modifier
                .background(OudsTheme.colors.surfaceVariant)
                .selectableGroup()
        ) {
            //TODO Replace hard coded values by tokens when available and use OUDS typography
            Text(
                text = stringResource(R.string.app_changeThemeDialog_label),
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            themeManager.availableThemes.forEach { theme ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = theme.name == selectedThemeName,
                            onClick = {
                                selectedThemeName = theme.name
                                if (theme != themeManager.currentTheme) {
                                    themeManager.currentTheme = theme
                                    onThemeSelected()
                                }
                                dismissDialog()
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme.name == selectedThemeName,
                        onClick = null
                    )
                    Text(
                        text = theme.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

/**
 * Returns the stored user theme if it exists. Otherwise, returns the Orange theme or the first existing theme.
 */
private fun getCurrentCustomTheme(
    storedUserThemeName: String?,
    customThemes: List<OudsCustomTheme>
) = customThemes.firstOrNull { it.name == storedUserThemeName }
    .orElse { customThemes.firstOrNull { it.name == ORANGE_THEME_NAME } }
    .orElse { customThemes.first() }
