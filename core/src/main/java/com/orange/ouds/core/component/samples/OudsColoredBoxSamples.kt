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

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.orange.ouds.core.component.coloredbox.OudsColoredBox

@Composable
internal fun OudsColoredBoxSample() {
    OudsColoredBox(color = OudsColoredBox.Color.StatusInfoEmphasized) {
        // From this point the theme is automatically adjusted to maximize the contrast with OudsColoredBox.Color.StatusInfoEmphasized
        Text("Text")
    }
}
