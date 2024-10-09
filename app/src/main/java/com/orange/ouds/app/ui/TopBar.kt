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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.TopBarAction.ChangeMode
import com.orange.ouds.app.ui.TopBarAction.ChangeTheme
import com.orange.ouds.app.ui.utilities.isDarkModeEnabled
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

val TopBarDefaultActions = listOf(ChangeTheme, ChangeMode)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    topBarState: TopBarState,
    upPress: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.semantics { isTraversalGroup = true },
        navigationIcon = topBarState.getNavigationIcon(upPress = upPress),
        title = {
            Text(
                text = topBarState.title,
                color = OudsColorKeyToken.OnSurface.value, //TODO use ContentDefault token when available
                modifier = Modifier.semantics { traversalIndex = -1f },
                style = OudsTypographyKeyToken.HeadingMedium.value
            )
        },
        actions = {
            topBarState.actions.forEach { topBarAction ->
                topBarAction()
            }
        }
    )
}

enum class TopBarAction {
    ChangeTheme, ChangeMode;

    @Composable
    fun TopBarIconButton(onActionClick: (TopBarAction) -> Unit) = when (this) {
        ChangeTheme -> ChangeThemeAction(onActionClick)
        ChangeMode -> ChangeModeAction(onActionClick)
    }
}

@Composable
fun getDefaultActions(onActionClick: (TopBarAction) -> Unit): List<@Composable () -> Unit> =
    TopBarDefaultActions.map { { it.TopBarIconButton(onActionClick = onActionClick) } }

@Composable
private fun ChangeThemeAction(onClick: (TopBarAction) -> Unit) {
    IconButton(onClick = { onClick(ChangeTheme) }) {
        Icon(painter = painterResource(id = R.drawable.ic_solar_palette), contentDescription = stringResource(id = R.string.app_topBar_theme_button_a11y))
    }
}

@Composable
private fun ChangeModeAction(onClick: (TopBarAction) -> Unit) {
    val configuration = LocalConfiguration.current

    val painterRes = if (configuration.isDarkModeEnabled) R.drawable.ic_ui_light_mode else R.drawable.ic_ui_dark_mode
    val iconDesc = if (configuration.isDarkModeEnabled) R.string.app_topBar_lightMode_button_a11y else R.string.app_topBar_darkMode_button_a11y

    IconButton(onClick = { onClick(ChangeMode) }) {
        Icon(painter = painterResource(id = painterRes), contentDescription = stringResource(id = iconDesc))
    }
}