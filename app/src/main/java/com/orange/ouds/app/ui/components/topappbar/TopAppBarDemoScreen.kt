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

package com.orange.ouds.app.ui.components.topappbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.colorArgument
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsCenterAlignedTopAppBar
import com.orange.ouds.core.component.OudsLargeTopAppBar
import com.orange.ouds.core.component.OudsMediumTopAppBar
import com.orange.ouds.core.component.OudsTopAppBar
import com.orange.ouds.core.component.OudsTopAppBarAction
import com.orange.ouds.core.component.OudsTopAppBarNavigationIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsVersion

@Composable
fun TopAppBarDemoScreen() {
    val state = rememberTopAppBarDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = Component.TopAppBar.descriptionRes),
        bottomSheetContent = { TopAppBarDemoBottomSheetContent(state = state) },
        codeSnippet = { topAppBarDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { TopAppBarDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.Bar
    )
}

@Composable
private fun TopAppBarDemoBottomSheetContent(state: TopAppBarDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = TopAppBarDemoState.Size.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TopAppBarDemoState.Size.entries.indexOf(size),
            onSelectionChange = { id -> size = TopAppBarDemoState.Size.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_topAppBar_centerAligned_label),
            checked = centerAligned,
            onCheckedChange = { centerAligned = it },
            enabled = centerAlignedSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_topAppBar_navigationIcon_label),
            checked = navigationIcon,
            onCheckedChange = { navigationIcon = it }
        )
        CustomizationTextField(
            label = stringResource(R.string.app_components_topAppBar_title_label),
            value = title,
            onValueChange = { value -> title = value }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_avatar_label),
            chipLabels = TopAppBarDemoState.Avatar.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TopAppBarDemoState.Avatar.entries.indexOf(avatar),
            onSelectionChange = { id -> avatar = TopAppBarDemoState.Avatar.entries[id] }
        )
        CustomizationTextField(
            label = stringResource(R.string.app_components_topAppBar_avatarMonogram_label),
            value = avatarMonogram.toString().trim(),
            onValueChange = { value -> avatarMonogram = value.firstOrNull().orElse { ' ' } },
            enabled = avatarMonogramTextFieldEnabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarDemoContent(state: TopAppBarDemoState) {
    with(state) {
        val navigationIcon = if (navigationIcon) OudsTopAppBarNavigationIcon.Back {} else null
        val avatarContentDescription = stringResource(R.string.app_components_topAppBar_secondAction_a11y)
        val avatarAction = when (avatar) {
            TopAppBarDemoState.Avatar.Image -> OudsTopAppBarAction.Avatar(
                painter = painterResource(id = R.drawable.il_top_app_bar_avatar),
                contentDescription = avatarContentDescription,
                onClick = {}
            )
            TopAppBarDemoState.Avatar.Monogram -> OudsTopAppBarAction.Avatar(
                monogram = avatarMonogram,
                color = Color.White,
                backgroundColor = Color(0xff138126),
                contentDescription = avatarContentDescription,
                onClick = {}
            )
        }
        val actions = listOf(
            OudsTopAppBarAction.Icon(
                painter = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                contentDescription = stringResource(R.string.app_components_topAppBar_firstAction_a11y),
                onClick = {}
            ),
            avatarAction
        )
        when (size) {
            TopAppBarDemoState.Size.Small -> {
                if (centerAligned) {
                    OudsCenterAlignedTopAppBar(
                        title = title,
                        navigationIcon = navigationIcon,
                        actions = actions
                    )
                } else {
                    OudsTopAppBar(
                        title = title,
                        navigationIcon = navigationIcon,
                        actions = actions
                    )
                }
            }
            TopAppBarDemoState.Size.Medium -> {
                OudsMediumTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions
                )
            }
            TopAppBarDemoState.Size.Large -> {
                OudsLargeTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = actions
                )
            }
        }
    }
}

private fun Code.Builder.topAppBarDemoCodeSnippet(state: TopAppBarDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        val functionName = when (size) {
            TopAppBarDemoState.Size.Small -> if (centerAligned) "OudsCenterAlignedTopAppBar" else "OudsTopAppBar"
            TopAppBarDemoState.Size.Medium -> "OudsMediumTopAppBar"
            TopAppBarDemoState.Size.Large -> "OudsLargeTopAppBar"
        }
        functionCall(functionName) {
            typedArgument("title", title)
            if (navigationIcon) {
                constructorCallArgument<OudsTopAppBarNavigationIcon.Back>("navigationIcon") {
                    trailingLambda = true
                    onClickArgument()
                }
            }
            functionCallArgument("actions", "listOf") {
                constructorCallArgument<OudsTopAppBarAction.Icon>(null) {
                    painterArgument(themeDrawableResources.tipsAndTricks)
                    contentDescriptionArgument(R.string.app_components_topAppBar_firstAction_a11y)
                    onClickArgument()
                }
                constructorCallArgument<OudsTopAppBarAction.Avatar>(null) {
                    when (avatar) {
                        TopAppBarDemoState.Avatar.Image -> {
                            painterArgument(R.drawable.il_top_app_bar_avatar)
                        }
                        TopAppBarDemoState.Avatar.Monogram -> {
                            typedArgument("monogram", avatarMonogram)
                            colorArgument("color", Color.White)
                            colorArgument("backgroundColor", Color(0xff138126))
                        }
                    }
                    contentDescriptionArgument(R.string.app_components_topAppBar_secondAction_a11y)
                    onClickArgument()
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTopAppBarDemoScreen() = AppPreview {
    TopAppBarDemoScreen()
}