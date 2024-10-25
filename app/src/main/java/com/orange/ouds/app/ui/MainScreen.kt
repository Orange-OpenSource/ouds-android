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
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orangecountry.OrangeCountryTheme
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.whitelabel.WhiteLabelTheme

@Composable
fun MainScreen(themes: List<OudsThemeContract>, mainViewModel: MainViewModel = viewModel()) {
    MainScreen(
        themes = themes,
        userThemeName = mainViewModel.getUserThemeName(),
        onThemeSelected = { themeName ->
            mainViewModel.storeUserThemeName(themeName)
        }
    )
}

@Composable
private fun MainScreen(themes: List<OudsThemeContract>, userThemeName: String?, onThemeSelected: (String) -> Unit) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val mainState = rememberMainState(
        themeState = rememberThemeState(
            currentTheme = rememberSaveable { mutableStateOf(getCurrentTheme(userThemeName, themes)) },
            darkModeEnabled = rememberSaveable { mutableStateOf(isSystemInDarkTheme) },
            availableThemes = themes
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
        OudsTheme(
            themeContract = mainState.themeState.currentTheme,
            darkThemeEnabled = configuration.isDarkModeEnabled
        ) {
            Scaffold(
                topBar = {
                    TopBar(
                        topBarState = mainState.topBarState,
                        upPress = mainState.navigationState::upPress,
                        onActionClick = { action ->
                            when (action) {
                                TopBarAction.ChangeTheme -> changeThemeDialogVisible = true
                                TopBarAction.ChangeMode -> mainState.themeState.darkModeEnabled = !configuration.isDarkModeEnabled
                            }
                        }
                    )
                },
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
                    startDestination = BottomBarItem.Tokens.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    appNavGraph(mainState.navigationState.navController)
                }

                if (changeThemeDialogVisible) {
                    ChangeThemeDialog(
                        themeManager = mainState.themeState,
                        dismissDialog = {
                            changeThemeDialogVisible = false
                        },
                        onThemeSelected = onThemeSelected
                    )
                }
            }
        }
    }
}

@Composable
private fun ChangeThemeDialog(themeManager: ThemeManager, dismissDialog: () -> Unit, onThemeSelected: (String) -> Unit) {
    var selectedThemeName by rememberSaveable { mutableStateOf(themeManager.currentTheme.name) }

    //TODO Use OudsDialog when available
    Dialog(onDismissRequest = dismissDialog) {
        Column(
            modifier = Modifier
                .background(OudsColorKeyToken.Background.Secondary.value)
                .selectableGroup()
        ) {
            Text(
                text = stringResource(R.string.app_themeDialog_label),
                modifier = Modifier
                    .padding(top = OudsSpaceKeyToken.Fixed.Medium.value, bottom = OudsSpaceKeyToken.Fixed.Short.value)
                    .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value),
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
                                    onThemeSelected(theme.name)
                                }
                                dismissDialog()
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme.name == selectedThemeName,
                        onClick = null
                    )
                    Text(
                        text = theme.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = OudsSpaceKeyToken.Fixed.Medium.value)
                    )
                }
            }
        }
    }
}

/**
 * Returns the stored user theme if it exists. Otherwise, returns the Orange theme or the first existing theme.
 */
private fun getCurrentTheme(
    storedUserThemeName: String?,
    themes: List<OudsThemeContract>
) = themes.firstOrNull { it.name == storedUserThemeName }
    .orElse { themes.firstOrNull { it.name == ORANGE_THEME_NAME } }
    .orElse { themes.first() }

@UiModePreviews.Default
@Composable
private fun PreviewMainScreen() = OudsPreview {
    // Tokens screen content is not displayed because the tokenCategories property uses sealedSubclasses which returns an empty list in Compose previews
    // Fixing this issue implies several modifications which would add unnecessary complexity to the code
    // See https://issuetracker.google.com/issues/240601093
    MainScreen(
        themes = listOf(OrangeTheme(), OrangeCountryTheme(), WhiteLabelTheme()),
        userThemeName = ORANGE_THEME_NAME,
        onThemeSelected = {}
    )
}
