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

package com.orange.ouds.app.ui.components.navigationbar

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.ShortNavigationBarArrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.navigationbar.NavigationBarDemoState.Companion.ItemBadgeCount
import com.orange.ouds.app.ui.components.navigationbar.NavigationBarDemoState.Companion.MaxItemCount
import com.orange.ouds.app.ui.components.navigationbar.NavigationBarDemoState.Companion.MinItemCount
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.component.OudsNavigationBarItemBadge
import com.orange.ouds.core.component.OudsNavigationBarItemIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.OudsVersion

@Composable
fun NavigationBarDemoScreen() {
    val state = rememberNavigationBarDemoState()
    val context = LocalContext.current
    val themeDrawableResources = LocalThemeDrawableResources.current
    val compactWindowSize = LocalWindowInfo.current.containerDpSize.width < 600.dp
    DemoScreen(
        description = stringResource(id = Component.NavigationBar.descriptionRes),
        bottomSheetContent = { NavigationBarDemoBottomSheetContent(state = state) },
        codeSnippet = {
            navigationBarDemoCodeSnippet(
                state = state,
                context = context,
                themeDrawableResources = themeDrawableResources,
                compactWindowSize = compactWindowSize
            )
        },
        demoContent = { NavigationBarDemoContent(state = state, compactWindowSize = compactWindowSize) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none),
        version = OudsVersion.Component.Bar
    )
}

@Composable
private fun NavigationBarDemoBottomSheetContent(state: NavigationBarDemoState) {
    with(state) {
        val itemCountOptions = remember { (MinItemCount..MaxItemCount).toList() }
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_navigationBar_itemCount_label),
            chipLabels = itemCountOptions.map { it.toString() },
            selectedChipIndex = itemCountOptions.indexOf(itemCount),
            onSelectionChange = { index -> itemCount = itemCountOptions[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_navigationBar_lastItemBadge_label),
            chipLabels = NavigationBarDemoState.ItemBadge.entries.map { it.name },
            selectedChipIndex = NavigationBarDemoState.ItemBadge.entries.indexOf(lastItemBadge),
            onSelectionChange = { index -> lastItemBadge = NavigationBarDemoState.ItemBadge.entries[index] }
        )
    }
}

@Composable
private fun NavigationBarDemoContent(state: NavigationBarDemoState, compactWindowSize: Boolean) {
    with(state) {
        OudsNavigationBar(
            items = items.mapIndexed { index, item ->
                val label = stringResource(id = item.labelRes)
                val isLastItem = index == itemCount - 1
                OudsNavigationBarItem(
                    selected = selectedItemId == index,
                    onClick = { selectedItemId = index },
                    label = label,
                    icon = OudsNavigationBarItemIcon(painter = painterResource(id = item.iconResourceProvider.getResource(LocalThemeDrawableResources.current))),
                    iconPosition = getNavigationItemIconPosition(compactWindowSize),
                    badge = if (isLastItem) {
                        when (lastItemBadge) {
                            NavigationBarDemoState.ItemBadge.None -> null
                            NavigationBarDemoState.ItemBadge.Standard -> OudsNavigationBarItemBadge(stringResource(id = R.string.app_components_common_unreadNotificationsBadge_a11y))
                            NavigationBarDemoState.ItemBadge.Count -> OudsNavigationBarItemBadge(
                                contentDescription = pluralStringResource(
                                    id = R.plurals.app_components_common_unreadMessageCountBadge_a11y,
                                    count = ItemBadgeCount,
                                    ItemBadgeCount
                                ),
                                count = ItemBadgeCount
                            )
                        }
                    } else {
                        null
                    }
                )
            },
            arrangement = getArrangement(compactWindowSize)
        )
    }
}

private fun Code.Builder.navigationBarDemoCodeSnippet(
    state: NavigationBarDemoState,
    context: Context,
    themeDrawableResources: ThemeDrawableResources,
    compactWindowSize: Boolean
) {
    with(state) {
        functionCall("OudsNavigationBar") {
            functionCallArgument("items", "listOf") {
                items.forEachIndexed { index, item ->
                    val isLastItem = index == itemCount - 1
                    val label = context.resources.getString(item.labelRes)
                    functionCallArgument(null, "OudsNavigationBarItem") {
                        typedArgument("selected", index == state.selectedItemId)
                        lambdaArgument("onClick", {})
                        labelArgument(label)
                        functionCallArgument("icon", OudsNavigationBarItemIcon::class.simpleName.orEmpty()) {
                            painterArgument(id = item.iconResourceProvider.getResource(themeDrawableResources))
                        }
                        if (!compactWindowSize) {
                            typedArgument("iconPosition", NavigationItemIconPosition.Start)
                        }
                        if (isLastItem && lastItemBadge != NavigationBarDemoState.ItemBadge.None) {
                            functionCallArgument("badge", OudsNavigationBarItemBadge::class.simpleName.orEmpty()) {
                                when (lastItemBadge) {
                                    NavigationBarDemoState.ItemBadge.None -> {}
                                    NavigationBarDemoState.ItemBadge.Standard -> contentDescriptionArgument(id = R.string.app_components_common_unreadNotificationsBadge_a11y)
                                    NavigationBarDemoState.ItemBadge.Count -> contentDescriptionArgument(
                                        id = R.plurals.app_components_common_unreadMessageCountBadge_a11y,
                                        count = ItemBadgeCount,
                                        ItemBadgeCount
                                    )
                                }
                                if (lastItemBadge == NavigationBarDemoState.ItemBadge.Count) {
                                    typedArgument("count", ItemBadgeCount)
                                }
                            }
                        }
                    }
                }
            }
            if (!compactWindowSize) {
                typedArgument("arrangement", ShortNavigationBarArrangement.Centered)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewNavigationBarDemoScreen() = AppPreview {
    NavigationBarDemoScreen()
}