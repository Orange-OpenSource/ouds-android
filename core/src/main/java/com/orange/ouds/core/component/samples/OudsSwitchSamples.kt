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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsSwitch
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsSwitchSample() {
    var checked by remember { mutableStateOf(true) }
    OudsSwitch(
        checked = checked,
        onCheckedChange = { value -> checked = value }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsSwitchSample() = OudsPreview {
    OudsSwitchSample()
}
