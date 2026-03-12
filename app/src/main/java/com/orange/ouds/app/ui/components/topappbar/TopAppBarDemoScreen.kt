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
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.colorArgument
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.components.topappbar.TopAppBarDemoState.Companion.ActionIconBadgeCount
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.core.component.OudsCenterAlignedTopAppBar
import com.orange.ouds.core.component.OudsLargeTopAppBar
import com.orange.ouds.core.component.OudsMediumTopAppBar
import com.orange.ouds.core.component.OudsTopAppBar
import com.orange.ouds.core.component.OudsTopAppBarAction
import com.orange.ouds.core.component.OudsTopAppBarActionBadge
import com.orange.ouds.core.component.OudsTopAppBarNavigationIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsVersion

@OptIn(ExperimentalMaterial3Api::class)
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
            label = stringResource(R.string.app_components_common_size_tech),
            chipLabels = TopAppBarDemoState.Size.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TopAppBarDemoState.Size.entries.indexOf(size),
            onSelectionChange = { index -> size = TopAppBarDemoState.Size.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_topAppBar_centerAligned_tech),
            checked = centerAligned,
            onCheckedChange = { centerAligned = it },
            enabled = centerAlignedSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_navigationIcon_tech),
            chipLabels = TopAppBarDemoState.NavigationIcon.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TopAppBarDemoState.NavigationIcon.entries.indexOf(navigationIcon),
            onSelectionChange = { index -> navigationIcon = TopAppBarDemoState.NavigationIcon.entries[index] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_title_tech),
            value = title,
            onValueChange = { value -> title = value }
        )
        val actionCountOptions = (TopAppBarDemoState.MinActionCount..TopAppBarDemoState.MaxActionCount).toList()
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_actionCount_tech),
            chipLabels = actionCountOptions.map { it.toString() },
            selectedChipIndex = actionCountOptions.indexOf(actionCount),
            onSelectionChange = { index -> actionCount = actionCountOptions[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_lastActionIconBadge_tech),
            chips = TopAppBarDemoState.ActionIconBadge.entries.map { CustomizationFilterChip(it.name, lastActionIconBadgeFilterChipsEnabled) },
            selectedChipIndex = TopAppBarDemoState.ActionIconBadge.entries.indexOf(lastActionIconBadge),
            onSelectionChange = { index -> lastActionIconBadge = TopAppBarDemoState.ActionIconBadge.entries[index] },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_actionAvatar_tech),
            chips = TopAppBarDemoState.ActionAvatar.entries.map { CustomizationFilterChip(stringResource(it.labelRes), actionAvatarFilterChipsEnabled) },
            selectedChipIndex = TopAppBarDemoState.ActionAvatar.entries.indexOf(actionAvatar),
            onSelectionChange = { index -> actionAvatar = TopAppBarDemoState.ActionAvatar.entries[index] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_topAppBar_actionAvatarMonogram_tech),
            value = actionAvatarMonogram.toString().trim(),
            onValueChange = { value -> actionAvatarMonogram = value.firstOrNull().orElse { ' ' } },
            enabled = actionAvatarMonogramTextInputEnabled
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarDemoContent(state: TopAppBarDemoState) {
    val themeDrawableResources = LocalThemeDrawableResources.current
    with(state) {
        val navigationIcon = when (navigationIcon) {
            TopAppBarDemoState.NavigationIcon.None -> null
            TopAppBarDemoState.NavigationIcon.Back -> OudsTopAppBarNavigationIcon.Back(onClick = {})
            TopAppBarDemoState.NavigationIcon.Close -> OudsTopAppBarNavigationIcon.Close(onClick = {})
            TopAppBarDemoState.NavigationIcon.Menu -> OudsTopAppBarNavigationIcon.Menu(onClick = {})
            TopAppBarDemoState.NavigationIcon.Custom -> OudsTopAppBarNavigationIcon(
                painter = painterResource(id = themeDrawableResources.tipsAndTricks),
                contentDescription = stringResource(R.string.app_components_common_icon_a11y),
                onClick = {}
            )
        }

        val lastActionIconIndex = actions.indexOfLast { it == TopAppBarDemoState.Action.Icon }
        val lastTopAppBarActionIconBadge = when (lastActionIconBadge) {
            TopAppBarDemoState.ActionIconBadge.None -> null
            TopAppBarDemoState.ActionIconBadge.Standard -> OudsTopAppBarActionBadge(contentDescription = stringResource(id = R.string.app_components_common_unreadNotificationsBadge_a11y))
            TopAppBarDemoState.ActionIconBadge.Count -> OudsTopAppBarActionBadge(
                contentDescription = pluralStringResource(
                    id = R.plurals.app_components_common_unreadMessageCountBadge_a11y,
                    count = ActionIconBadgeCount,
                    ActionIconBadgeCount
                ),
                count = ActionIconBadgeCount
            )
        }
        val topAppBarActions = actions.mapIndexed { index, action ->
            val contentDescription = getActionContentDescriptionResId(index)?.let { stringResource(it) }.orEmpty()
            when (action) {
                TopAppBarDemoState.Action.Icon -> {
                    OudsTopAppBarAction.Icon(
                        painter = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks),
                        contentDescription = contentDescription,
                        badge = if (index == lastActionIconIndex) lastTopAppBarActionIconBadge else null,
                        onClick = {}
                    )
                }
                TopAppBarDemoState.Action.Avatar -> {
                    when (actionAvatar) {
                        TopAppBarDemoState.ActionAvatar.Image -> OudsTopAppBarAction.Avatar(
                            painter = painterResource(id = R.drawable.il_top_app_bar_avatar),
                            contentDescription = contentDescription,
                            onClick = {}
                        )
                        TopAppBarDemoState.ActionAvatar.Monogram -> OudsTopAppBarAction.Avatar(
                            monogram = actionAvatarMonogram,
                            color = Color.White,
                            backgroundColor = Color(0xff138126),
                            contentDescription = contentDescription,
                            onClick = {}
                        )
                    }
                }
            }
        }

        when (size) {
            TopAppBarDemoState.Size.Small -> {
                if (centerAligned) {
                    OudsCenterAlignedTopAppBar(
                        title = title,
                        navigationIcon = navigationIcon,
                        actions = topAppBarActions
                    )
                } else {
                    OudsTopAppBar(
                        title = title,
                        navigationIcon = navigationIcon,
                        actions = topAppBarActions
                    )
                }
            }
            TopAppBarDemoState.Size.Medium -> {
                OudsMediumTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = topAppBarActions
                )
            }
            TopAppBarDemoState.Size.Large -> {
                OudsLargeTopAppBar(
                    title = title,
                    navigationIcon = navigationIcon,
                    actions = topAppBarActions
                )
            }
        }
    }
}

private fun getActionContentDescriptionResId(index: Int): Int? {
    return when (index) {
        0 -> R.string.app_components_topAppBar_firstAction_a11y
        1 -> R.string.app_components_topAppBar_secondAction_a11y
        2 -> R.string.app_components_topAppBar_thirdAction_a11y
        else -> null
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
            val navigationIconClass = when (navigationIcon) {
                TopAppBarDemoState.NavigationIcon.None -> null
                TopAppBarDemoState.NavigationIcon.Back -> OudsTopAppBarNavigationIcon.Back::class.java
                TopAppBarDemoState.NavigationIcon.Close -> OudsTopAppBarNavigationIcon.Close::class.java
                TopAppBarDemoState.NavigationIcon.Menu -> OudsTopAppBarNavigationIcon.Menu::class.java
                TopAppBarDemoState.NavigationIcon.Custom -> OudsTopAppBarNavigationIcon::class.java
            }
            if (navigationIconClass != null) {
                functionCallArgument("navigationIcon", navigationIconClass.nestedName) {
                    trailingLambda = navigationIcon != TopAppBarDemoState.NavigationIcon.Custom
                    if (navigationIcon == TopAppBarDemoState.NavigationIcon.Custom) {
                        painterArgument(themeDrawableResources.tipsAndTricks)
                        contentDescriptionArgument(R.string.app_components_common_icon_a11y)
                    }
                    onClickArgument()
                }
            }
            if (actions.isNotEmpty()) {
                functionCallArgument("actions", "listOf") {
                    actions.forEachIndexed { index, action ->
                        val contentDescriptionResId = getActionContentDescriptionResId(index)
                        when (action) {
                            TopAppBarDemoState.Action.Icon -> {
                                constructorCallArgument<OudsTopAppBarAction.Icon>(null) {
                                    painterArgument(themeDrawableResources.tipsAndTricks)
                                    if (contentDescriptionResId != null) {
                                        contentDescriptionArgument(contentDescriptionResId)
                                    }
                                    val lastActionIconIndex = actions.indexOfLast { it == TopAppBarDemoState.Action.Icon }
                                    if (lastActionIconIndex == index && lastActionIconBadge != TopAppBarDemoState.ActionIconBadge.None) {
                                        functionCallArgument("badge", OudsTopAppBarActionBadge::class.simpleName.orEmpty()) {
                                            when (lastActionIconBadge) {
                                                TopAppBarDemoState.ActionIconBadge.None -> {}
                                                TopAppBarDemoState.ActionIconBadge.Standard -> contentDescriptionArgument(id = R.string.app_components_common_unreadNotificationsBadge_a11y)
                                                TopAppBarDemoState.ActionIconBadge.Count -> contentDescriptionArgument(
                                                    id = R.plurals.app_components_common_unreadMessageCountBadge_a11y,
                                                    count = ActionIconBadgeCount,
                                                    ActionIconBadgeCount
                                                )
                                            }
                                            if (lastActionIconBadge == TopAppBarDemoState.ActionIconBadge.Count) {
                                                typedArgument("count", ActionIconBadgeCount)
                                            }
                                        }
                                    }
                                    onClickArgument()
                                }
                            }
                            TopAppBarDemoState.Action.Avatar -> {
                                constructorCallArgument<OudsTopAppBarAction.Avatar>(null) {
                                    when (actionAvatar) {
                                        TopAppBarDemoState.ActionAvatar.Image -> {
                                            painterArgument(R.drawable.il_top_app_bar_avatar)
                                        }
                                        TopAppBarDemoState.ActionAvatar.Monogram -> {
                                            typedArgument("monogram", actionAvatarMonogram)
                                            colorArgument("color", Color.White)
                                            colorArgument("backgroundColor", Color(0xff138126))
                                        }
                                    }
                                    if (contentDescriptionResId != null) {
                                        contentDescriptionArgument(contentDescriptionResId)
                                    }
                                    onClickArgument()
                                }
                            }
                        }
                    }
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