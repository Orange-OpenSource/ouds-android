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

package com.orange.ouds.app.ui.utilities.composable

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.theme.OudsTheme

private val labelTextStyle: TextStyle
    @Composable
    get() = OudsTheme.typography.body.strong.large

private val valueLabelTextStyle: TextStyle
    @Composable
    get() = OudsTheme.typography.label.strong.large

@Composable
fun CustomizationSwitchItem(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, enabled: Boolean = true) {
    OudsSwitchItem(
        checked = checked,
        label = label,
        onCheckedChange = onCheckedChange,
        reversed = true,
        enabled = enabled
    )
}

@Composable
fun CustomizationChoiceChips(
    label: String,
    chipsLabels: List<String>,
    selectedChipIndex: Int,
    onSelectionChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin), text = label, style = labelTextStyle)
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
                .selectableGroup()
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.shorter),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shorter)
        ) {
            chipsLabels.forEachIndexed { id, label ->
                val isSelected = selectedChipIndex == id
                FilterChip(
                    selected = isSelected,
                    leadingIcon = if (isSelected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                    onClick = { onSelectionChange(id) },
                    label = { Text(text = label) }
                )
            }
        }
    }
}

@Composable
fun CustomizationTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin), text = label, style = labelTextStyle)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.shorter),
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationDropdownMenu(
    label: String,
    itemLabels: List<String>,
    selectedItemIndex: Int,
    onSelectionChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    itemLeadingIcons: List<@Composable () -> Unit>? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(modifier = Modifier.padding(horizontal = OudsTheme.spaces.fixed.medium), text = label, style = labelTextStyle)
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(horizontal = OudsTheme.spaces.fixed.medium, vertical = OudsTheme.spaces.fixed.shorter),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            val leadingIconBoxModifier = Modifier.size(OudsTheme.sizes.icon.withLabel.medium.sizeMedium)
            TextField(
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                value = itemLabels[selectedItemIndex],
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = valueLabelTextStyle,
                leadingIcon = itemLeadingIcons?.get(selectedItemIndex)?.let { { Box(modifier = leadingIconBoxModifier) { it() } } },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                itemLabels.forEachIndexed { index, itemLabel ->
                    DropdownMenuItem(
                        text = { Text(text = itemLabel, style = valueLabelTextStyle) },
                        onClick = {
                            onSelectionChange(index)
                            expanded = false
                        },
                        leadingIcon = itemLeadingIcons?.get(index)?.let { { Box(modifier = leadingIconBoxModifier) { it() } } }
                    )
                }
            }
        }
    }
}
