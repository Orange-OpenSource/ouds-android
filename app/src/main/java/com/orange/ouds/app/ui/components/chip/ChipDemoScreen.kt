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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsChipIcon
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun ChipDemoBottomSheetContent(state: ChipDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_tech),
            chipLabels = ChipDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ChipDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { index -> layout = ChipDemoState.Layout.entries[index] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelTextInputEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_tintedIcon_tech),
            checked = tintedIcon,
            onCheckedChange = { tintedIcon = it },
            enabled = tintedIconSwitchEnabled
        )
    }
}

@Composable
fun ChipDemoContent(state: ChipDemoState, content: @Composable (index: Int, icon: OudsChipIcon) -> Unit) {
    with(state) {
        val themeDrawableResources = LocalThemeDrawableResources.current
        val icons = listOf(
            themeDrawableResources.call,
            themeDrawableResources.smsMessage
        )
        FlowRow(
            modifier = Modifier.selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
        ) {
            repeat(ChipDemoState.ChipCount) { index ->
                val painter = if (tintedIcon) {
                    painterResource(id = icons[index % icons.count()])
                } else {
                    rememberUntintedIconPainter()
                }
                val icon = OudsChipIcon(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.app_components_common_icon_a11y),
                    tinted = tintedIcon
                )
                content(index, icon)
            }
        }
    }
}

fun FunctionCall.Builder.chipArguments(state: ChipDemoState, themeDrawableResources: ThemeDrawableResources) = with(state) {
    onClickArgument()
    if (layout in listOf(ChipDemoState.Layout.IconOnly, ChipDemoState.Layout.TextAndIcon)) {
        iconArgument<OudsChipIcon>("icon", themeDrawableResources.call, R.string.app_components_common_icon_a11y, tintedIcon)
    }
    if (layout in listOf(ChipDemoState.Layout.TextOnly, ChipDemoState.Layout.TextAndIcon)) {
        val separator = if (label.isBlank()) "" else " "
        labelArgument("$label${separator}1")
    }
    enabledArgument(enabled)
}
