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
import com.orange.ouds.core.component.OudsDivider

@Composable
fun rememberDividerDemoState(
    color: OudsDivider.Color = OudsDivider.Color.Default
) = rememberSaveable(color, saver = DividerDemoState.Saver) {
    DividerDemoState(color)
}

class DividerDemoState(
    color: OudsDivider.Color
) {
    companion object {
        val Saver = Saver<DividerDemoState, OudsDivider.Color>(
            save = { it.color },
            restore = { DividerDemoState(it) }
        )
    }

    var color: OudsDivider.Color by mutableStateOf(color)
}