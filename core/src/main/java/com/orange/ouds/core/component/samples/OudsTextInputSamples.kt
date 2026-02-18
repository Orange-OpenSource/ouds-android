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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLeadingIcon
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.OudsTextInputTrailingIconButton
import com.orange.ouds.core.component.common.OudsError

@Composable
internal fun OudsTextInputStateBasedSample() {
    OudsTextInput(
        textFieldState = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
        leadingIcon = OudsTextInputLeadingIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        ),
        prefix = "Prefix",
        suffix = "Suffix",
        loader = OudsTextInputLoader(null),
        helperText = "Helper text",
        helperLink = OudsTextInputHelperLink(text = "Helper link", onClick = { })
    )
}

@Composable
internal fun OudsTextInputValueBasedSample() {
    var value by remember { mutableStateOf("Input") }
    OudsTextInput(
        value = value,
        onValueChange = { value = it },
        label = "Label",
        placeholder = "Placeholder",
        leadingIcon = OudsTextInputLeadingIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        ),
        prefix = "Prefix",
        suffix = "Suffix",
        loader = OudsTextInputLoader(null),
        helperText = "Helper text",
        helperLink = OudsTextInputHelperLink(text = "Helper link", onClick = { })
    )
}

@Composable
internal fun OudsTextInputStateBasedErrorSample() {
    OudsTextInput(
        textFieldState = rememberTextFieldState(),
        label = "Label",
        placeholder = "Placeholder",
        trailingIconButton = OudsTextInputTrailingIconButton(
            imageVector = Icons.Filled.DateRange,
            contentDescription = "Display calendar",
            onClick = { }),
        outlined = true,
        error = OudsError(message = "This field can't be empty.")
    )
}

@Composable
internal fun OudsTextInputValueBasedErrorSample() {
    var value by remember { mutableStateOf("") }
    OudsTextInput(
        value = value,
        onValueChange = { value = it },
        label = "Label",
        placeholder = "Placeholder",
        trailingIconButton = OudsTextInputTrailingIconButton(
            imageVector = Icons.Filled.DateRange,
            contentDescription = "Display calendar",
            onClick = { }),
        outlined = true,
        error = OudsError(message = "This field can't be empty.")
    )
}