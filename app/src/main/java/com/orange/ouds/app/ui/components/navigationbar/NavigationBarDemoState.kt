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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R

@Composable
fun rememberNavigationBarDemoState(
    itemCount: Int = NavigationBarDemoState.MinNavigationBarItemCount,
    selectedItemId: Int = 0,
    lastItemBadge: NavigationBarDemoState.ItemBadge = NavigationBarDemoState.ItemBadge.None
) = rememberSaveable(itemCount, lastItemBadge, saver = NavigationBarDemoState.Saver) {
    NavigationBarDemoState(itemCount, selectedItemId, lastItemBadge)
}

class NavigationBarDemoState(
    itemCount: Int,
    selectedItemId: Int,
    lastItemBadge: ItemBadge
) {
    companion object {
        const val MinNavigationBarItemCount = 3
        const val MaxNavigationBarItemCount = 5
        const val ItemBadgeCount = 9

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        itemCount,
                        selectedItemId,
                        lastItemBadge
                    )
                }
            },
            restore = { list ->
                NavigationBarDemoState(
                    list[0] as Int,
                    list[1] as Int,
                    list[2] as ItemBadge
                )
            }
        )
    }

    var itemCount: Int by mutableIntStateOf(itemCount)
    var selectedItemId: Int by mutableIntStateOf(selectedItemId)
    var lastItemBadge: ItemBadge by mutableStateOf(lastItemBadge)

    enum class ItemBadge(@StringRes val labelRes: Int) {
        None(R.string.app_components_navigationBar_itemBadgeNone_label),
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_countType_label)
    }
}