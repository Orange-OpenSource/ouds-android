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
import com.orange.ouds.core.component.OudsCheckboxControlItem
import com.orange.ouds.core.component.OudsTriStateCheckboxControlItem

@Composable
internal fun OudsCheckboxControlItemSample() {
    var checked by remember { mutableStateOf(false) }

    OudsCheckboxControlItem(
        checked = checked,
        text = "Terms of use",
        helperText = "By checking this box, I acknowledge having read the conditions of use.",
        onClick = { value -> checked = value },
        divider = false
    )
}

@Composable
internal fun OudsTriStateCheckboxControlItemSample() {
    var toggleableState by remember { mutableStateOf(ToggleableState.Off) }

    OudsTriStateCheckboxControlItem(
        state = toggleableState,
        text = "My hobbies",
        helperText = "Select the hobbies you practice regularly.",
        onClick = {
            toggleableState = when (toggleableState) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        }
    )
}
