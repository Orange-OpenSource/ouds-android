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
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.navigationbar.NavigationBarDemoState.Companion.MaxNavigationBarItemCount
import com.orange.ouds.app.ui.components.navigationbar.NavigationBarDemoState.Companion.MinNavigationBarItemCount
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.component.OudsNavigationBarItemIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun NavigationBarDemoScreen() {
    val state = rememberNavigationBarDemoState()
    val context = LocalContext.current
    DemoScreen(
        description = stringResource(id = Component.NavigationBar.descriptionRes),
        bottomSheetContent = { NavigationBarDemoBottomSheetContent(state = state) },
        codeSnippet = { navigationBarDemoCodeSnippet(state = state, context = context) },
        demoContent = { NavigationBarDemoContent(state = state) },
        demoContentPaddingValues = PaddingValues(horizontal = OudsTheme.spaces.fixed.none)
    )
}

@Composable
private fun NavigationBarDemoBottomSheetContent(state: NavigationBarDemoState) {
    with(state) {
        val itemCountOptions = remember { (MinNavigationBarItemCount..MaxNavigationBarItemCount).toList() }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_navigationBar_itemCount_label),
            chipLabels = itemCountOptions.map { it.toString() },
            selectedChipIndex = itemCountOptions.indexOf(itemCount),
            onSelectionChange = { id -> itemCount = itemCountOptions[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_navigationBar_alwaysShowLabel_label),
            checked = alwaysShowLabel,
            onCheckedChange = { alwaysShowLabel = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_navigationBar_lastItemEnabled_label),
            checked = lastItemEnabled,
            onCheckedChange = { checked ->
                // If the last item is selected when the last item is disabled, select the first item
                if (!checked && selectedItemId == itemCount - 1) {
                    selectedItemId = 0
                }
                lastItemEnabled = checked
            },
        )
    }
}

@Composable
private fun NavigationBarDemoContent(state: NavigationBarDemoState) {
    val navigationBarItems = NavigationBarItem.entries

    with(state) {
        OudsNavigationBar {
            navigationBarItems.take(itemCount).forEachIndexed { index, item ->
                val label = stringResource(id = item.labelRes)
                val isLastItem = index == itemCount - 1
                OudsNavigationBarItem(
                    modifier = Modifier.weight(1f),
                    selected = selectedItemId == index,
                    onClick = { selectedItemId = index },
                    label = label,
                    icon = OudsNavigationBarItemIcon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = label
                    ),
                    alwaysShowLabel = alwaysShowLabel,
                    enabled = !(isLastItem && !lastItemEnabled)
                )
            }
        }
    }
}

private fun Code.Builder.navigationBarDemoCodeSnippet(state: NavigationBarDemoState, context: Context) {
    functionCall("OudsNavigationBar") {
        functionCallArgument("items", "listOf") {
            NavigationBarItem.entries.take(state.itemCount).forEach { item ->
                val label = context.resources.getString(item.labelRes)
                functionCallArgument(null, "OudsNavigationBarItem") {
                    typedArgument("selected", item == NavigationBarItem.Home)
                    lambdaArgument("onClick", {})
                    labelArgument(label)
                    functionCallArgument(null, OudsNavigationBarItemIcon::class.simpleName.orEmpty()) {
                        painterArgument(id = item.iconRes)
                    }
                }
            }
        }
    }
}

enum class NavigationBarItem(@DrawableRes val iconRes: Int, @StringRes val labelRes: Int) {
    Home(R.drawable.ic_home, R.string.app_components_navigationBar_homeItem_label),
    Shop(R.drawable.ic_shop_store, R.string.app_components_navigationBar_shopItem_label),
    Notification(R.drawable.ic_notification_alert, R.string.app_components_navigationBar_notificationItem_label),
    Account(R.drawable.ic_avatar, R.string.app_components_navigationBar_accountItem_label),
    Settings(R.drawable.ic_settings, R.string.app_components_navigationBar_settingsItem_label),
}

@PreviewLightDark
@Composable
private fun PreviewNavigationBarDemoScreen() = OudsPreview {
    NavigationBarDemoScreen()
}