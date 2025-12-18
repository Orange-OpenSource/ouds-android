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

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.LightDarkResourceId
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.painterResource
import com.orange.ouds.core.component.OudsTopAppBar
import com.orange.ouds.core.component.OudsTopAppBarAction
import com.orange.ouds.core.component.OudsTopAppBarNavigationIcon


@Composable
fun TopBar(
    topBarState: TopBarState,
    upPress: () -> Unit,
    onActionClick: (TopBarAction) -> Unit,
    modifier: Modifier = Modifier
) {
    TopBar(
        modifier = modifier,
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
    onActionClick: (TopBarAction) -> Unit,
    modifier: Modifier = Modifier
) {
    OudsTopAppBar(
        modifier = modifier.windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal)),
        translucent = Build.VERSION.SDK_INT > Build.VERSION_CODES.S_V2,
        title = title,
        navigationIcon = if (showNavigationIcon) OudsTopAppBarNavigationIcon.Back(onClick = upPress) else null,
        actions = actions.map { action ->
            OudsTopAppBarAction.Icon(
                painter = action.painter,
                contentDescription = action.contentDescription,
                onClick = { onActionClick(action) }
            )
        }
    )
}

enum class TopBarAction {
    ChangeThemeSettings, ChangeTheme, ChangeMode;

    val painter: Painter
        @Composable
        get() = when (this) {
            ChangeThemeSettings -> painterResource(id = LocalThemeDrawableResources.current.filters)
            ChangeTheme -> painterResource(id = R.drawable.ic_solar_palette)
            ChangeMode -> painterResource(LightDarkResourceId(R.drawable.ic_ui_light_mode, R.drawable.ic_ui_dark_mode))
        }

    val contentDescription: String
        @Composable
        get() {
            val id = when (this) {
                ChangeThemeSettings -> R.string.app_topBar_themeSettings_button_a11y
                ChangeTheme -> R.string.app_topBar_theme_button_a11y
                ChangeMode -> if (isSystemInDarkTheme()) R.string.app_topBar_lightMode_button_a11y else R.string.app_topBar_darkMode_button_a11y
            }

            return stringResource(id = id)
        }
}

@PreviewLightDark
@Composable
private fun PreviewTopBar() = AppPreview {
    TopBar(
        showNavigationIcon = true,
        title = "Title",
        actions = listOf(TopBarAction.ChangeThemeSettings, TopBarAction.ChangeTheme, TopBarAction.ChangeMode),
        upPress = {},
        onActionClick = {}
    )
}
