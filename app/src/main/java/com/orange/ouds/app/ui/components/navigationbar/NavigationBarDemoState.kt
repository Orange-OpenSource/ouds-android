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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberNavigationBarDemoState(
    itemCount: Int = NavigationBarDemoState.MinNavigationBarItemCount,
    selectedItemId: Int = 0,
    alwaysShowLabel: Boolean = true,
    lastItemEnabled: Boolean = true
) = rememberSaveable(itemCount, alwaysShowLabel, lastItemEnabled, saver = NavigationBarDemoState.Saver) {
    NavigationBarDemoState(itemCount, selectedItemId, alwaysShowLabel, lastItemEnabled)
}

class NavigationBarDemoState(
    itemCount: Int,
    selectedItemId: Int,
    alwaysShowLabel: Boolean,
    lastItemEnabled: Boolean
) {
    companion object {
        const val MinNavigationBarItemCount = 3
        const val MaxNavigationBarItemCount = 5

        val Saver = run {
            val itemCountKey = "itemCount"
            val selectedItemIdKey = "selectedItemId"
            val alwaysShowLabelKey = "alwaysShowLabel"
            val lastItemEnabledKey = "lastItemEnabled"
            mapSaver(
                save = { state ->
                    mapOf(
                        itemCountKey to state.itemCount,
                        selectedItemIdKey to state.selectedItemId,
                        alwaysShowLabelKey to state.alwaysShowLabel,
                        lastItemEnabledKey to state.lastItemEnabled
                    )
                },
                restore = { map ->
                    NavigationBarDemoState(
                        map[itemCountKey] as Int,
                        map[selectedItemIdKey] as Int,
                        map[alwaysShowLabelKey] as Boolean,
                        map[lastItemEnabledKey] as Boolean
                    )
                }
            )
        }
    }

    var itemCount: Int by mutableIntStateOf(itemCount)
    var selectedItemId: Int by mutableIntStateOf(selectedItemId)
    var alwaysShowLabel: Boolean by mutableStateOf(alwaysShowLabel)
    var lastItemEnabled: Boolean by mutableStateOf(lastItemEnabled)

}