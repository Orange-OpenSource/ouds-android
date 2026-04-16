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
import com.orange.ouds.core.component.OudsPinCodeInput
import com.orange.ouds.core.component.OudsPinCodeInputLength
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsPinCodeInputSample() {
    var value by remember { mutableStateOf("") }
    OudsPinCodeInput(
        value = value,
        onValueChange = { value = it },
        length = OudsPinCodeInputLength.Four,
        helperText = "Enter the 4-digit code sent to your phone."
    )
}

@Composable
internal fun OudsPinCodeInputErrorSample() {
    var value by remember { mutableStateOf("1234") }
    OudsPinCodeInput(
        value = value,
        onValueChange = { value = it },
        length = OudsPinCodeInputLength.Four,
        error = OudsError("Verification failed. Check and enter the correct code."),
        helperText = "Enter the 4-digit code sent to your phone."
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsPinCodeInputSample() = OudsPreview {
    OudsPinCodeInputSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsPinCodeInputErrorSample() = OudsPreview {
    OudsPinCodeInputErrorSample()
}
