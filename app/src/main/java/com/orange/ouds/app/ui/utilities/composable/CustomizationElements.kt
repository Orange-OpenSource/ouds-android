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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

@Composable
fun CustomizationSwitchListItem(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit, enabled: Boolean = true) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { onCheckedChange(!checked) },
        headlineContent = { Text(text = label, style = OudsTypographyKeyToken.Heading.Medium.value) },
        trailingContent = { Switch(checked = checked, onCheckedChange = null, enabled = enabled) }
    )
}

@Composable
fun CustomizationChoiceChipsColumn(
    label: String,
    chipsLabels: List<String>,
    selectedChipIndex: Int,
    onSelectionChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(modifier = Modifier.padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value), text = label, style = OudsTypographyKeyToken.Heading.Medium.value)
        Row(
            Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
                .selectableGroup()
                .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value, vertical = OudsSpaceKeyToken.Fixed.Shorter.value),
            horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Shorter.value)
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