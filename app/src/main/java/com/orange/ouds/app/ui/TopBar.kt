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

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview


@Composable
fun TopBar(
    topBarState: TopBarState,
    upPress: () -> Unit,
    onActionClick: (TopBarAction) -> Unit
) {
    TopBar(
        title = topBarState.title,
        showNavigationIcon = topBarState.showNavigationIcon,
        actions = topBarState.actions,
        upPress = upPress,
        onActionClick = onActionClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    showNavigationIcon: Boolean,
    title: String,
    actions: List<TopBarAction>,
    upPress: () -> Unit,
    onActionClick: (TopBarAction) -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .semantics { isTraversalGroup = true }
            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)),
        navigationIcon = {
            if (showNavigationIcon) {
                IconButton(onClick = upPress) {
                    Image(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.app_common_back_a11y),
                        colorFilter = ColorFilter.tint(OudsTheme.colorScheme.content.default)
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
                color = OudsTheme.colorScheme.content.default,
                modifier = Modifier.semantics { traversalIndex = -1f },
                style = OudsTheme.typography.heading.medium
            )
        },
        actions = {
            actions.map { it.TopBarIconButton(onActionClick = onActionClick) }
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
private fun ChangeThemeAction(onClick: (TopBarAction) -> Unit) {
    IconButton(onClick = { onClick(TopBarAction.ChangeTheme) }) {
        Icon(painter = painterResource(id = R.drawable.ic_solar_palette), contentDescription = stringResource(id = R.string.app_topBar_theme_button_a11y))
    }
}

@Composable
private fun ChangeModeAction(onClick: (TopBarAction) -> Unit) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val painterRes = if (isSystemInDarkTheme) R.drawable.ic_ui_light_mode else R.drawable.ic_ui_dark_mode
    val iconDesc = if (isSystemInDarkTheme) R.string.app_topBar_lightMode_button_a11y else R.string.app_topBar_darkMode_button_a11y

    IconButton(onClick = { onClick(TopBarAction.ChangeMode) }) {
        Icon(painter = painterResource(id = painterRes), contentDescription = stringResource(id = iconDesc))
    }
}

@PreviewLightDark
@Composable
private fun PreviewTopBar() = OudsPreview {
    TopBar(
        showNavigationIcon = true,
        title = "Title",
        actions = listOf(TopBarAction.ChangeTheme, TopBarAction.ChangeMode),
        upPress = {},
        onActionClick = {}
    )
}
