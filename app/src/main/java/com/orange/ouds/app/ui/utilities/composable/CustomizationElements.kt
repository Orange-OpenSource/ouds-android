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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ouds.core.component.OudsFilterChip
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
        divider = false,
        enabled = enabled
    )
}

@Composable
fun CustomizationFilterChips(
    label: String,
    chipLabels: List<String>,
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
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
        ) {
            chipLabels.forEachIndexed { id, label ->
                OudsFilterChip(
                    selected = selectedChipIndex == id,
                    onClick = { onSelectionChange(id) },
                    label = label
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
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = value)) }

    CustomizationTextField(
        label = label,
        value = textFieldValue,
        onValueChange = { newTextFieldValue ->
            textFieldValue = newTextFieldValue
            onValueChange(newTextFieldValue.text)
        },
        modifier = modifier,
        enabled = enabled,
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun CustomizationTextField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin), text = label, style = labelTextStyle)
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            textStyle = valueLabelTextStyle
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
        Text(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin), text = label, style = labelTextStyle)
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
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
