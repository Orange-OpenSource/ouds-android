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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsPasswordInput
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedHelperText
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.component.rememberOudsPasswordInputState
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsPasswordInputSample() {
    OudsPasswordInput(
        state = rememberOudsPasswordInputState(),
        lockIcon = true,
        label = "Password",
        helperText = "Your password must be between 8 and 20 characters long.",
    )
}

@Composable
internal fun OudsPasswordInputErrorSample() {
    OudsPasswordInput(
        state = rememberOudsPasswordInputState(),
        label = "Password",
        error = OudsError("Password must be at least 8 characters.")
    )
}

@Composable
internal fun OudsPasswordInputWithAnnotatedHelperTextSample() {
    val helperText = buildOudsAnnotatedHelperText {
        append("Password must be ")
        withStrong { append("at least 8 characters") }
        append(" long")
    }

    OudsPasswordInput(
        state = rememberOudsPasswordInputState(),
        label = "Password",
        lockIcon = true,
        helperText = helperText
    )
}

@Composable
internal fun OudsPasswordInputWithAnnotatedErrorMessageSample() {
    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("This field ")
            withStrong { append("cannot") }
            append(" be empty")
        }
    )

    OudsPasswordInput(
        state = rememberOudsPasswordInputState(),
        label = "Password",
        lockIcon = true,
        error = error
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputSample() = OudsPreview {
    OudsPasswordInputSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputErrorSample() = OudsPreview {
    OudsPasswordInputErrorSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputWithAnnotatedHelperTextSample() = OudsPreview {
    OudsPasswordInputWithAnnotatedHelperTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsPasswordInputWithAnnotatedErrorMessageSample()
}
