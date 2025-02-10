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
import com.orange.ouds.core.component.OudsCheckbox
import com.orange.ouds.core.component.OudsTriStateCheckbox

@Composable
internal fun OudsCheckboxSample() {
    OudsCheckbox(
        checked = false,
        onCheckedChange = { _ -> /* Do something! */ }
    )
}

@Composable
internal fun OudsTriStateCheckboxSample() {
    var toggleableState by remember { mutableStateOf(ToggleableState.Off) }

    OudsTriStateCheckbox(
        state = toggleableState,
        onClick = {
            toggleableState = when (toggleableState) {
                ToggleableState.On -> ToggleableState.Off
                ToggleableState.Off -> ToggleableState.Indeterminate
                ToggleableState.Indeterminate -> ToggleableState.On
            }
        }
    )
}
