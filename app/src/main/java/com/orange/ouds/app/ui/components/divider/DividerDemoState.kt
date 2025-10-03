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

package com.orange.ouds.app.ui.components.divider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.core.component.OudsDividerColor
import com.orange.ouds.core.component.OudsDividerDefaults

@Composable
fun rememberDividerDemoState(
    color: OudsDividerColor = OudsDividerDefaults.Color
) = rememberSaveable(color, saver = DividerDemoState.Saver) {
    DividerDemoState(color)
}

class DividerDemoState(
    color: OudsDividerColor
) {
    companion object {
        val Saver = Saver<DividerDemoState, OudsDividerColor>(
            save = { it.color },
            restore = { DividerDemoState(it) }
        )
    }

    var color: OudsDividerColor by mutableStateOf(color)
}