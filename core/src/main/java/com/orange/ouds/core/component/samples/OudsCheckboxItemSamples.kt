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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsCheckboxItem
import com.orange.ouds.core.component.OudsTriStateCheckboxItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsCheckboxItemSample() {
    var checked by remember { mutableStateOf(false) }

    OudsCheckboxItem(
        checked = checked,
        label = "Terms of use",
        description = "By checking this box, I acknowledge having read the conditions of use.",
        onCheckedChange = { value -> checked = value }
    )
}

@Composable
internal fun OudsTriStateCheckboxItemSample() {
    var toggleableState by remember { mutableStateOf(ToggleableState.Off) }

    OudsTriStateCheckboxItem(
        state = toggleableState,
        label = "My hobbies",
        description = "Select the hobbies you practice regularly.",
        onClick = {
            toggleableState = when (toggleableState) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        }
    )
}

@Composable
internal fun OudsCheckboxItemWithAnnotatedErrorMessageSample() {
    var checked by remember { mutableStateOf(false) }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("You ")
            withStrong { append("must") }
            append(" accept the terms to continue")
        }
    )

    OudsCheckboxItem(
        checked = checked,
        label = "Terms of use",
        description = "By checking this box, I acknowledge having read the conditions of use.",
        onCheckedChange = { value -> checked = value },
        error = error
    )
}

@Composable
internal fun OudsTriStateCheckboxItemWithAnnotatedErrorMessageSample() {
    var toggleableState by remember { mutableStateOf(ToggleableState.Off) }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("You ")
            withStrong { append("must") }
            append(" select at least one option")
        }
    )

    OudsTriStateCheckboxItem(
        state = toggleableState,
        label = "My hobbies",
        description = "Select the hobbies you practice regularly.",
        onClick = {
            toggleableState = when (toggleableState) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        },
        error = error
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsCheckboxItemSample() = OudsPreview {
    OudsCheckboxItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTriStateCheckboxItemSample() = OudsPreview {
    OudsTriStateCheckboxItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCheckboxItemWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsCheckboxItemWithAnnotatedErrorMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTriStateCheckboxItemWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsTriStateCheckboxItemWithAnnotatedErrorMessageSample()
}
