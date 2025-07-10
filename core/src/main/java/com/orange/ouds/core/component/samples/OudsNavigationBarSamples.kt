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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsNavigationBarSample() {
    data class Menu(val label: String, val imageVector: ImageVector, val enabled: Boolean = true)

    val items = listOf(
        Menu("Call", Icons.Default.Call),
        Menu("Email", Icons.Default.Email),
        Menu("Agenda", Icons.Default.DateRange, enabled = false),
        Menu("Settings", Icons.Default.Settings)
    )
    var selectedItemIndex: Int by rememberSaveable { mutableIntStateOf(0) }

    OudsNavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = index == selectedItemIndex
            OudsNavigationBarItem(
                modifier = Modifier.weight(1f),
                selected = isSelected,
                onClick = {
                    selectedItemIndex = index
                    // Do something else here
                },
                icon = OudsNavigationBarItem.Icon(
                    imageVector = item.imageVector,
                    contentDescription = item.label
                ),
                label = item.label,
                enabled = item.enabled
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationBarSample() = OudsPreview {
    OudsNavigationBarSample()
}