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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.navigation.appNavGraph
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orangecountry.OrangeCountryTheme
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
    val mainState = rememberMainState(
        themeState = rememberThemeState(
            currentTheme = getCurrentTheme(userThemeName, themes),
            availableThemes = themes
        )
    )

    val isSystemInDarkTheme = isSystemInDarkTheme()
    val activity = LocalActivity.current as? ComponentActivity
    activity?.enableEdgeToEdge(SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isSystemInDarkTheme })

    var changeThemeDialogVisible by remember { mutableStateOf(false) }

    OudsTheme(
        themeContract = mainState.themeState.currentTheme,
        darkThemeEnabled = isSystemInDarkTheme
    ) {
        Scaffold(
            topBar = {
                val context = LocalContext.current
                TopBar(
                    topBarState = mainState.topBarState,
                    upPress = mainState.navigationState::upPress,
                    onActionClick = { action ->
                        when (action) {
                            TopBarAction.ChangeTheme -> changeThemeDialogVisible = true
                            TopBarAction.ChangeMode -> setApplicationNightModeEnabled(!isSystemInDarkTheme, context)
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
                modifier = Modifier
                    .consumeWindowInsets(innerPadding)
                    .padding(innerPadding)
            ) {
                appNavGraph(mainState.navigationState.navController)
            }

            if (changeThemeDialogVisible) {
                ChangeThemeDialog(
                    themeState = mainState.themeState,
                    dismissDialog = {
                        changeThemeDialogVisible = false
                    },
                    onThemeSelected = onThemeSelected
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

@Composable
private fun ChangeThemeDialog(themeState: ThemeState, dismissDialog: () -> Unit, onThemeSelected: (String) -> Unit) {
    var selectedThemeName by rememberSaveable { mutableStateOf(themeState.currentTheme.name) }

    //TODO Use OudsDialog when available
    Dialog(onDismissRequest = dismissDialog) {
        Column(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.secondary)
                .selectableGroup()
        ) {
            Text(
                text = stringResource(R.string.app_themeDialog_label),
                modifier = Modifier
                    .padding(top = OudsTheme.spaces.fixed.medium, bottom = OudsTheme.spaces.fixed.short)
                    .padding(horizontal = OudsTheme.spaces.fixed.medium),
                style = MaterialTheme.typography.titleLarge
            )
            themeState.availableThemes.forEach { theme ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = theme.name == selectedThemeName,
                            onClick = {
                                selectedThemeName = theme.name
                                if (theme != themeState.currentTheme) {
                                    themeState.currentTheme = theme
                                    onThemeSelected(theme.name)
                                }
                                dismissDialog()
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = OudsTheme.spaces.fixed.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = theme.name == selectedThemeName,
                        onClick = null
                    )
                    Text(
                        text = theme.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = OudsTheme.spaces.fixed.medium)
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
