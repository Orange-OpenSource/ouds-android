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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsChip
import com.orange.ouds.core.component.OudsFilterChip
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsFilterChipTextOnlySample() {
    var selected by remember { mutableStateOf(false) }
    OudsFilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = "Label"
    )
}

@Composable
internal fun OudsFilterChipIconOnlySample() {
    var selected by remember { mutableStateOf(false) }
    OudsFilterChip(
        selected = selected,
        onClick = { selected = !selected },
        icon = OudsChip.Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        )
    )
}

@Composable
internal fun OudsFilterChipTextAndIconSample() {
    var selected by remember { mutableStateOf(false) }
    OudsFilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = "Label",
        icon = OudsChip.Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsFilterChipTextOnlySample() = OudsPreview {
    OudsFilterChipTextOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsFilterChipIconOnlySample() = OudsPreview {
    OudsFilterChipIconOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsFilterChipTextAndIconSample() = OudsPreview {
    OudsFilterChipTextAndIconSample()
}
