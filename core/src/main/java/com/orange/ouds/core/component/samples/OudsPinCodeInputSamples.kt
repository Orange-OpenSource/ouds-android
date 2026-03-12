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
import com.orange.ouds.core.component.OudsPinCodeInput
import com.orange.ouds.core.component.common.OudsError

@Composable
internal fun OudsPinCodeInputSample() {
    var code by remember { mutableStateOf("") }
    OudsPinCodeInput(
        value = code,
        onValueChange = { code = it },
        length = 6,
        helperText = "Enter the 6-digit code sent to your phone.",
        onComplete = {
            // Handle PIN code completion
        }
    )
}

@Composable
internal fun OudsPinCodeInputErrorSample() {
    var code by remember { mutableStateOf("1234") }
    OudsPinCodeInput(
        value = code,
        onValueChange = { code = it },
        length = 4,
        error = OudsError("Verification failed. Check and enter the correct code.")
    )
}

@Composable
internal fun OudsPinCodeInputOutlinedSample() {
    var code by remember { mutableStateOf("") }
    OudsPinCodeInput(
        value = code,
        onValueChange = { code = it },
        length = 4,
        outlined = true,
        helperText = "Enter the 4-digit code."
    )
}
