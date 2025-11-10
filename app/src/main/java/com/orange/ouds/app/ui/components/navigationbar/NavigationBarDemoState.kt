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

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R

@Composable
fun rememberNavigationBarDemoState(
    itemCount: Int = NavigationBarDemoState.MinNavigationBarItemCount,
    selectedItemId: Int = 0,
    alwaysShowLabel: Boolean = true,
    lastItemBadge: NavigationBarDemoState.ItemBadge = NavigationBarDemoState.ItemBadge.None
) = rememberSaveable(itemCount, alwaysShowLabel, lastItemBadge, saver = NavigationBarDemoState.Saver) {
    NavigationBarDemoState(itemCount, selectedItemId, alwaysShowLabel, lastItemBadge)
}

class NavigationBarDemoState(
    itemCount: Int,
    selectedItemId: Int,
    alwaysShowLabel: Boolean,
    lastItemBadge: ItemBadge
) {
    companion object {
        const val MinNavigationBarItemCount = 3
        const val MaxNavigationBarItemCount = 5
        const val ItemBadgeCount = 9

        val Saver = run {
            val itemCountKey = "itemCount"
            val selectedItemIdKey = "selectedItemId"
            val alwaysShowLabelKey = "alwaysShowLabel"
            val lastItemBadgeKey = "lastItemBadge"
            mapSaver(
                save = { state ->
                    mapOf(
                        itemCountKey to state.itemCount,
                        selectedItemIdKey to state.selectedItemId,
                        alwaysShowLabelKey to state.alwaysShowLabel,
                        lastItemBadgeKey to state.lastItemBadge
                    )
                },
                restore = { map ->
                    NavigationBarDemoState(
                        map[itemCountKey] as Int,
                        map[selectedItemIdKey] as Int,
                        map[alwaysShowLabelKey] as Boolean,
                        map[lastItemBadgeKey] as ItemBadge
                    )
                }
            )
        }
    }

    var itemCount: Int by mutableIntStateOf(itemCount)
    var selectedItemId: Int by mutableIntStateOf(selectedItemId)
    var alwaysShowLabel: Boolean by mutableStateOf(alwaysShowLabel)
    var lastItemBadge: ItemBadge by mutableStateOf(lastItemBadge)

    enum class ItemBadge(@StringRes val labelRes: Int) {
        None(R.string.app_components_navigationBar_lastItemBadgeNone_label),
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_countType_label)
    }
}