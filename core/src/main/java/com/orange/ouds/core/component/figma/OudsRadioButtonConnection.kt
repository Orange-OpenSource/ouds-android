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

package com.orange.ouds.core.component.figma

import androidx.compose.runtime.Composable
import com.figma.code.connect.Figma
import com.figma.code.connect.FigmaConnect
import com.figma.code.connect.FigmaProperty
import com.figma.code.connect.FigmaType
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.component.common.OudsError

/**
 * @suppress
 */
@FigmaConnect(url = "<FIGMA_COMPONENTS_RADIO_BUTTON>")
class OudsRadioButtonConnection {

    @FigmaProperty(FigmaType.Boolean, "Selected")
    val selected = false

    @FigmaProperty(FigmaType.Boolean, "Error")
    val error: OudsError? = Figma.mapping(
        false to null,
        true to OudsError("Error message read by screen readers")
    )

    @FigmaProperty(FigmaType.Enum, "State")
    val enabled: Boolean = Figma.mapping(
        "Enabled" to true,
        "Hover" to true,
        "Focus" to true,
        "Pressed" to true,
        "Read only" to true,
        "Disabled" to false,
        "Skeleton" to true
    )

    @FigmaProperty(FigmaType.Enum, "State")
    val readOnly: Boolean = Figma.mapping(
        "Enabled" to false,
        "Hover" to false,
        "Focus" to false,
        "Pressed" to false,
        "Read only" to true,
        "Disabled" to false,
        "Skeleton" to false
    )

    @Composable
    fun OudsRadioButtonExample() {
        OudsRadioButton(selected = selected, onClick = { }, enabled = enabled, readOnly = readOnly, error = error)
    }
}