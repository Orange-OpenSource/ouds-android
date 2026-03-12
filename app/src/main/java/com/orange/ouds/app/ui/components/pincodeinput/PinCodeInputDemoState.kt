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

package com.orange.ouds.app.ui.components.pincodeinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberPinCodeInputDemoState(
    code: String = "",
    length: Int = 6,
    outlined: Boolean = false,
    obscureText: Boolean = true,
    enabled: Boolean = true,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    helperText: String = ""
) = rememberSaveable(
    code,
    length,
    outlined,
    obscureText,
    enabled,
    error,
    errorMessage,
    helperText,
    saver = PinCodeInputDemoState.Saver
) {
    PinCodeInputDemoState(
        code,
        length,
        outlined,
        obscureText,
        enabled,
        error,
        errorMessage,
        helperText
    )
}

class PinCodeInputDemoState(
    code: String,
    length: Int,
    outlined: Boolean,
    obscureText: Boolean,
    enabled: Boolean,
    error: Boolean,
    errorMessage: String,
    helperText: String
) {
    var code by mutableStateOf(code)
    var length by mutableIntStateOf(length)
    var outlined by mutableStateOf(outlined)
    var obscureText by mutableStateOf(obscureText)
    var enabled by mutableStateOf(enabled)
    var error by mutableStateOf(error)
    var errorMessage by mutableStateOf(errorMessage)
    var helperText by mutableStateOf(helperText)

    companion object {
        val Saver = listSaver<PinCodeInputDemoState, Any>(
            save = {
                listOf(
                    it.code,
                    it.length,
                    it.outlined,
                    it.obscureText,
                    it.enabled,
                    it.error,
                    it.errorMessage,
                    it.helperText
                )
            },
            restore = {
                PinCodeInputDemoState(
                    code = it[0] as String,
                    length = it[1] as Int,
                    outlined = it[2] as Boolean,
                    obscureText = it[3] as Boolean,
                    enabled = it[4] as Boolean,
                    error = it[5] as Boolean,
                    errorMessage = it[6] as String,
                    helperText = it[7] as String
                )
            }
        )
    }
}
