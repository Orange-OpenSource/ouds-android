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
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import com.orange.ouds.core.component.OudsFilterChip
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.component.OudsTextInputTrailingIconButton
import com.orange.ouds.core.theme.OudsTheme

private val labelTextStyle: TextStyle
    @Composable
    get() = OudsTheme.typography.label.default.large

private val valueLabelTextStyle: TextStyle
    @Composable
    get() = OudsTheme.typography.label.strong.large

private val elementTopPadding: Dp
    @Composable
    get() = OudsTheme.spaces.fixed.medium

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

@OptIn(ExperimentalMaterial3Api::class)
@JvmName("CustomizationFilterChipsLabels")
@Composable
fun CustomizationFilterChips(
    label: String,
    chipLabels: List<String>,
    selectedChipIndex: Int,
    onSelectionChange: (Int) -> Unit,
    applyTopPadding: Boolean,
    modifier: Modifier = Modifier
) {
    CustomizationFilterChips(
        label = label,
        chips = chipLabels.map { CustomizationFilterChip(label = it) },
        selectedChipIndex = selectedChipIndex,
        onSelectionChange = onSelectionChange,
        applyTopPadding = applyTopPadding,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationFilterChips(
    label: String,
    chips: List<CustomizationFilterChip>,
    selectedChipIndex: Int,
    onSelectionChange: (Int) -> Unit,
    applyTopPadding: Boolean,
    modifier: Modifier = Modifier
) {
    val modifier = if (applyTopPadding) modifier.padding(top = elementTopPadding) else modifier
    Column(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
    ) {
        Text(modifier = Modifier.padding(horizontal = OudsTheme.grids.margin), text = label, style = labelTextStyle)
        // Setting an horizontalScroll in the Row breaks the canFocus parameter of the focusProperties Modifier
        // in the parent Column of CustomizationBottomSheetScaffold
        // That is why we set canFocus here again
        val sheetValue = LocalCustomizationBottomSheetValue.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
                .selectableGroup()
                .focusProperties { canFocus = sheetValue == SheetValue.Expanded }
                .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
        ) {
            chips.forEachIndexed { id, chip ->
                OudsFilterChip(
                    selected = selectedChipIndex == id,
                    onClick = { onSelectionChange(id) },
                    label = chip.label,
                    enabled = chip.enabled
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
        keyboardOptions = keyboardOptions,
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
    OudsTextInput(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
        value = value,
        onValueChange = onValueChange,
        label = label,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        trailingIconButton = if (value.text.isNotEmpty()) {
            OudsTextInputTrailingIconButton(
                painter = painterResource(com.orange.ouds.theme.orange.R.drawable.ic_orange_tag_close),
                contentDescription = "",
                onClick = {
                    onValueChange(value.copy(text = ""))
                })
        } else {
            null
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizationDropdownMenu(
    label: String,
    items: List<CustomizationDropdownMenuItem>,
    selectedItemIndex: Int,
    onSelectionChange: (Int) -> Unit,
    applyTopPadding: Boolean,
    modifier: Modifier = Modifier,
) {
    val modifier = if (applyTopPadding) modifier.padding(top = elementTopPadding) else modifier

    Column(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
    ) {
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
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                value = items[selectedItemIndex].label,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                textStyle = valueLabelTextStyle,
                leadingIcon = items[selectedItemIndex].leadingIcon?.let { { Box(modifier = leadingIconBoxModifier) { it() } } },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = item.label, style = valueLabelTextStyle) },
                        onClick = {
                            onSelectionChange(index)
                            expanded = false
                        },
                        leadingIcon = item.leadingIcon?.let { { Box(modifier = leadingIconBoxModifier) { it() } } },
                        enabled = item.enabled
                    )
                }
            }
        }
    }
}

data class CustomizationDropdownMenuItem(val label: String, val leadingIcon: (@Composable () -> Unit)? = null, val enabled: Boolean = true)

data class CustomizationFilterChip(val label: String, val enabled: Boolean = true)
