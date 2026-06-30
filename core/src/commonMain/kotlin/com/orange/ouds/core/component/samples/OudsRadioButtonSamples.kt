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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsRadioButtonSample() {
    val identifiers = listOf(0, 1)
    var selectedId by rememberSaveable { mutableIntStateOf(identifiers.first()) }

    Column(modifier = Modifier.selectableGroup()) {
        identifiers.forEach { id ->
            OudsRadioButton(
                selected = id == selectedId,
                onClick = { selectedId = id }
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonSample() = OudsPreview {
    OudsRadioButtonSample()
}
