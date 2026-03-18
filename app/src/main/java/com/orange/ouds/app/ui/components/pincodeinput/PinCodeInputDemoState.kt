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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsPinCodeInputDefaults
import com.orange.ouds.core.component.OudsPinCodeInputLength

@Composable
fun rememberPinCodeInputDemoState(
    value: String = "",
    length: OudsPinCodeInputLength = OudsPinCodeInputDefaults.Length,
    outlined: Boolean = false,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    helperText: String = ""
) = rememberSaveable(value, length, outlined, error, errorMessage, helperText, saver = PinCodeInputDemoState.Saver) {
    PinCodeInputDemoState(value, length, outlined, error, errorMessage, helperText)
}

class PinCodeInputDemoState(
    value: String,
    length: OudsPinCodeInputLength,
    outlined: Boolean,
    error: Boolean,
    errorMessage: String,
    helperText: String
) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        value,
                        length,
                        outlined,
                        error,
                        errorMessage,
                        helperText
                    )
                }
            },
            restore = { list: List<Any?> ->
                PinCodeInputDemoState(
                    list[0] as String,
                    list[1] as OudsPinCodeInputLength,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as String,
                    list[5] as String
                )
            }
        )
    }

    var value by mutableStateOf(value)

    var length: OudsPinCodeInputLength by mutableStateOf(length)

    var outlined: Boolean by mutableStateOf(outlined)

    var error: Boolean by mutableStateOf(error)

    var errorMessage: String by mutableStateOf(errorMessage)

    var helperText: String by mutableStateOf(helperText)

    val errorMessageTextInputEnabled: Boolean
        get() = error
}
