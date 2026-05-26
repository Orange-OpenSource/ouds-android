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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsTextArea
import com.orange.ouds.core.component.OudsTextInputHelperLink
import com.orange.ouds.core.component.OudsTextInputLoader
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedHelperText
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsTextAreaStateBasedSample() {
    OudsTextArea(
        textFieldState = rememberTextFieldState("I would like to report an issue with my recent order. The product arrived damaged and I would appreciate a replacement or refund."),
        label = "Feedback",
        placeholder = "Share your thoughts or report an issue",
        loader = OudsTextInputLoader(null),
        helperText = "Please provide as much detail as possible (minimum 20 characters)",
        helperLink = OudsTextInputHelperLink(text = "Guidelines", onClick = { /* Open guidelines */ })
    )
}

@Composable
internal fun OudsTextAreaValueBasedSample() {
    var value by remember { mutableStateOf("I would like to report an issue with my recent order. The product arrived damaged and I would appreciate a replacement or refund.") }
    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Feedback",
        placeholder = "Share your thoughts or report an issue",
        loader = OudsTextInputLoader(null),
        helperText = "Please provide as much detail as possible (minimum 20 characters)",
        helperLink = OudsTextInputHelperLink(text = "Guidelines", onClick = { /* Open guidelines */ })
    )
}

@Composable
internal fun OudsTextAreaStateBasedErrorSample() {
    OudsTextArea(
        textFieldState = rememberTextFieldState(),
        label = "Comment",
        placeholder = "Add your comment here",
        outlined = true,
        error = OudsError(message = "Please enter at least 10 characters.")
    )
}

@Composable
internal fun OudsTextAreaValueBasedErrorSample() {
    var value by remember { mutableStateOf("") }
    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Comment",
        placeholder = "Add your comment here",
        outlined = true,
        error = OudsError(message = "Please enter at least 10 characters.")
    )
}

@Composable
internal fun OudsTextAreaStateBasedWithAnnotatedHelperTextSample() {
    val helperText = buildOudsAnnotatedHelperText {
        append("Description must be ")
        withStrong { append("at least 20 characters") }
        append(" long.")
    }

    OudsTextArea(
        textFieldState = rememberTextFieldState(),
        label = "Description",
        placeholder = "Enter description",
        helperText = helperText,
        outlined = true
    )
}

@Composable
internal fun OudsTextAreaStateBasedWithAnnotatedErrorMessageSample() {
    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("This field ")
            withStrong { append("cannot") }
            append(" be empty.")
        }
    )

    OudsTextArea(
        textFieldState = rememberTextFieldState(),
        label = "Description",
        placeholder = "Enter description",
        error = error,
        outlined = true
    )
}

@Composable
internal fun OudsTextAreaValueBasedWithAnnotatedHelperTextSample() {
    var value by remember { mutableStateOf("") }

    val helperText = buildOudsAnnotatedHelperText {
        append("Description must be ")
        withStrong { append("at least 20 characters") }
        append(" long.")
    }

    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Description",
        placeholder = "Enter description",
        helperText = helperText,
        outlined = true
    )
}

@Composable
internal fun OudsTextAreaValueBasedWithAnnotatedErrorMessageSample() {
    var value by remember { mutableStateOf("") }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("This field ")
            withStrong { append("cannot") }
            append(" be empty.")
        }
    )

    OudsTextArea(
        value = value,
        onValueChange = { value = it },
        label = "Description",
        placeholder = "Enter description",
        error = error,
        outlined = true
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaStateBasedSample() = OudsPreview {
    OudsTextAreaStateBasedSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaValueBasedSample() = OudsPreview {
    OudsTextAreaValueBasedSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaStateBasedErrorSample() = OudsPreview {
    OudsTextAreaStateBasedErrorSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaValueBasedErrorSample() = OudsPreview {
    OudsTextAreaValueBasedErrorSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaStateBasedWithAnnotatedHelperTextSample() = OudsPreview {
    OudsTextAreaStateBasedWithAnnotatedHelperTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaStateBasedWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsTextAreaStateBasedWithAnnotatedErrorMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaValueBasedWithAnnotatedHelperTextSample() = OudsPreview {
    OudsTextAreaValueBasedWithAnnotatedHelperTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTextAreaValueBasedWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsTextAreaValueBasedWithAnnotatedErrorMessageSample()
}