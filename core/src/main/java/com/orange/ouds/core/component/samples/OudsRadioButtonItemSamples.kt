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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsRadioButtonItemSample() {
    val genders = listOf("Female", "Male", "Other")
    var selectedGender by rememberSaveable { mutableStateOf(genders.first()) }

    Column(modifier = Modifier.selectableGroup()) {
        genders.forEach { gender ->
            OudsRadioButtonItem(
                selected = gender == selectedGender,
                label = gender,
                onClick = { selectedGender = gender },
                divider = true
            )
        }
    }
}

@Composable
internal fun OudsRadioButtonItemWithAnnotatedErrorMessageSample() {
    val genders = listOf("Female", "Male", "Other")
    var selectedGender by rememberSaveable { mutableStateOf("") }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("You ")
            withStrong { append("must") }
            append(" select an option")
        }
    )

    Column(modifier = Modifier.selectableGroup()) {
        genders.forEachIndexed { index, gender ->
            OudsRadioButtonItem(
                selected = gender == selectedGender,
                label = gender,
                onClick = { selectedGender = gender },
                error = if (index == genders.lastIndex) error else OudsError(""),
                divider = true
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonItemSample() = OudsPreview {
    OudsRadioButtonItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonItemWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsRadioButtonItemWithAnnotatedErrorMessageSample()
}
