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

package com.orange.ouds.app.ui.components.chip

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.core.component.OudsChip
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun ChipDemoBottomSheetContent(state: ChipDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_label),
            checked = enabled,
            onCheckedChange = { enabled = it }
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_common_layout_label),
            chipsLabels = ChipDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ChipDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = ChipDemoState.Layout.entries[id] }
        )
    }
}

@Composable
fun ChipDemoContent(content: @Composable (index: Int, icon: OudsChip.Icon) -> Unit) {
    val icons = listOf(
        Icons.Filled.Person,
        Icons.Filled.Phone
    )
    FlowRow(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)) {
        repeat(ChipDemoState.ChipCount) { index ->
            val icon = OudsChip.Icon(
                imageVector = icons[index % icons.count()],
                contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
            )
            content(index, icon)
        }
    }
}

fun FunctionCall.Builder.chipArguments(state: ChipDemoState, label: String) = with(state) {
    onClickArgument()
    if (layout in listOf(ChipDemoState.Layout.IconOnly, ChipDemoState.Layout.TextAndIcon)) {
        constructorCallArgument<OudsChip.Icon>("icon") {
            rawArgument("imageVector", "Icons.Filled.Person")
            contentDescriptionArgument(R.string.app_components_common_icon_a11y)
        }
    }
    if (layout in listOf(ChipDemoState.Layout.TextOnly, ChipDemoState.Layout.TextAndIcon)) {
        labelArgument(label)
    }
    enabledArgument(enabled)
}
