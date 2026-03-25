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

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.orange.ouds.core.component.OudsTextArea
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.common.OudsError

@Composable
internal fun OudsTextAreaStateBasedSample() {
    OudsTextArea(
        textFieldState = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
        loader = OudsTextInputLoader(null),
        helperText = "Helper text",
        helperLink = OudsTextInputHelperLink(text = "Helper link", onClick = { })
    )
}

@Composable
internal fun OudsTextAreaValueBasedSample() {
    var value by remember { mutableStateOf("Text content") }
    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Label",
        placeholder = "Placeholder",
        loader = OudsTextInputLoader(null),
        helperText = "Helper text",
        helperLink = OudsTextInputHelperLink(text = "Helper link", onClick = { })
    )
}

@Composable
internal fun OudsTextAreaStateBasedErrorSample() {
    OudsTextArea(
        textFieldState = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
        outlined = true,
        error = OudsError(message = "This field can't be empty.")
    )
}

@Composable
internal fun OudsTextAreaValueBasedErrorSample() {
    var value by remember { mutableStateOf("") }
    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Label",
        placeholder = "Placeholder",
        outlined = true,
        error = OudsError(message = "This field can't be empty.")
    )
}
