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

package com.orange.ouds.core.component.samples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.component.OudsNavigationBarItemBadge
import com.orange.ouds.core.component.OudsNavigationBarItemIcon
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsNavigationBarSample() {
    data class Item(val label: String, val imageVector: ImageVector, val count: Int? = null)

    val items = listOf(
        Item("Call", Icons.Default.Call),
        Item("Email", Icons.Default.Email, count = 27),
        Item("Agenda", Icons.Default.DateRange),
        Item("Settings", Icons.Default.Settings)
    )
    var selectedItemIndex: Int by rememberSaveable { mutableIntStateOf(0) }

    OudsNavigationBar(
        items = items.mapIndexed { index, item ->
            val isSelected = index == selectedItemIndex
            OudsNavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectedItemIndex = index
                    // Do something else here
                },
                icon = OudsNavigationBarItemIcon(
                    imageVector = item.imageVector,
                    contentDescription = item.label
                ),
                label = item.label,
                badge = item.count?.let { count ->
                    OudsNavigationBarItemBadge(contentDescription = "$count unread emails", count = count)
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationBarSample() = OudsPreview {
    OudsNavigationBarSample()
}