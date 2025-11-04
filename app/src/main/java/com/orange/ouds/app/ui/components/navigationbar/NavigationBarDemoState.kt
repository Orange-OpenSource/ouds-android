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
    lastItemEnabled: Boolean = true,
    secondItemBadge: NavigationBarDemoState.ItemBadge = NavigationBarDemoState.ItemBadge.None
) = rememberSaveable(itemCount, alwaysShowLabel, lastItemEnabled, secondItemBadge, saver = NavigationBarDemoState.Saver) {
    NavigationBarDemoState(itemCount, selectedItemId, alwaysShowLabel, lastItemEnabled, secondItemBadge)
}

class NavigationBarDemoState(
    itemCount: Int,
    selectedItemId: Int,
    alwaysShowLabel: Boolean,
    lastItemEnabled: Boolean,
    secondItemBadge: ItemBadge
) {
    companion object {
        const val MinNavigationBarItemCount = 3
        const val MaxNavigationBarItemCount = 5
        const val ItemBadgeCount = 9

        val Saver = run {
            val itemCountKey = "itemCount"
            val selectedItemIdKey = "selectedItemId"
            val alwaysShowLabelKey = "alwaysShowLabel"
            val lastItemEnabledKey = "lastItemEnabled"
            val secondItemBadgeKey = "secondItemBadge"
            mapSaver(
                save = { state ->
                    mapOf(
                        itemCountKey to state.itemCount,
                        selectedItemIdKey to state.selectedItemId,
                        alwaysShowLabelKey to state.alwaysShowLabel,
                        lastItemEnabledKey to state.lastItemEnabled,
                        secondItemBadgeKey to state.secondItemBadge
                    )
                },
                restore = { map ->
                    NavigationBarDemoState(
                        map[itemCountKey] as Int,
                        map[selectedItemIdKey] as Int,
                        map[alwaysShowLabelKey] as Boolean,
                        map[lastItemEnabledKey] as Boolean,
                        map[secondItemBadgeKey] as ItemBadge
                    )
                }
            )
        }
    }

    var itemCount: Int by mutableIntStateOf(itemCount)
    var selectedItemId: Int by mutableIntStateOf(selectedItemId)
    var alwaysShowLabel: Boolean by mutableStateOf(alwaysShowLabel)
    var lastItemEnabled: Boolean by mutableStateOf(lastItemEnabled)
    var secondItemBadge: ItemBadge by mutableStateOf(secondItemBadge)

    enum class ItemBadge(@StringRes val labelRes: Int) {
        None(R.string.app_components_navigationBar_secondItemBadgeNone_label),
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_countType_label)
    }
}