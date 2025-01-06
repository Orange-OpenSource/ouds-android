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
import com.orange.ouds.theme.tokens.OudsColorKeyToken

@Composable
internal fun SimpleOudsColoredBox() {
    OudsColoredBox(color = OudsColorKeyToken.Surface.Status.Info.Emphasized) {
        // From this point LocalContentColor is automatically adjusted to maximize the contrast with OudsColorKeyToken.Surface.Status.Info.Emphasized
        Text("Text")
    }
}
