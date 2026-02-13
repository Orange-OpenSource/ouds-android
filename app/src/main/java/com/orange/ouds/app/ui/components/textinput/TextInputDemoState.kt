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

package com.orange.ouds.app.ui.components.textinput

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberTextInputDemoState(
    textFieldState: TextFieldState = TextFieldState(),
    label: String = stringResource(id = R.string.app_components_common_label_tech),
    placeholder: String = "",
    outlined: Boolean = false,
    leadingIcon: Boolean = false,
    trailingIcon: Boolean = false,
    hasLoader: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    prefix: String = "",
    suffix: String = "",
    helperText: String = "",
    helperLink: String = "",
    constrainedMaxWidth: Boolean = false
) = rememberSaveable(
    textFieldState,
    label,
    placeholder,
    outlined,
    leadingIcon,
    trailingIcon,
    enabled,
    readOnly,
    hasLoader,
    error,
    errorMessage,
    prefix,
    suffix,
    helperText,
    helperLink,
    constrainedMaxWidth,
    saver = TextInputDemoState.Saver
) {
    TextInputDemoState(
        textFieldState,
        label,
        placeholder,
        outlined,
        leadingIcon,
        trailingIcon,
        hasLoader,
        enabled,
        readOnly,
        error,
        errorMessage,
        prefix,
        suffix,
        helperText,
        helperLink,
        constrainedMaxWidth
    )
}

class TextInputDemoState(
    textFieldState: TextFieldState,
    label: String,
    placeholder: String,
    outlined: Boolean,
    leadingIcon: Boolean,
    trailingIcon: Boolean,
    hasLoader: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorMessage: String,
    prefix: String,
    suffix: String,
    helperText: String,
    helperLink: String,
    constrainedMaxWidth: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TextFieldState.Saver) { save(textFieldState) },
                        label,
                        placeholder,
                        outlined,
                        leadingIcon,
                        trailingIcon,
                        hasLoader,
                        enabled,
                        readOnly,
                        error,
                        this.errorMessage,
                        prefix,
                        suffix,
                        helperText,
                        helperLink,
                        constrainedMaxWidth
                    )
                }
            },
            restore = { list: List<Any?> ->
                val textFieldState = list[0]?.let { TextFieldState.Saver.restore(it) }
                TextInputDemoState(
                    textFieldState as TextFieldState,
                    list[1] as String,
                    list[2] as String,
                    list[3] as Boolean,
                    list[4] as Boolean,
                    list[5] as Boolean,
                    list[6] as Boolean,
                    list[7] as Boolean,
                    list[8] as Boolean,
                    list[9] as Boolean,
                    list[10] as String,
                    list[11] as String,
                    list[12] as String,
                    list[13] as String,
                    list[14] as String,
                    list[15] as Boolean
                )
            }
        )
    }

    var textFieldState: TextFieldState by mutableStateOf(textFieldState)

    var label: String by mutableStateOf(label)

    var placeholder: String by mutableStateOf(placeholder)

    var outlined: Boolean by mutableStateOf(outlined)

    var error: Boolean by mutableStateOf(error)

    var errorMessage: String by mutableStateOf(errorMessage)

    var leadingIcon: Boolean by mutableStateOf(leadingIcon)

    var trailingIcon: Boolean by mutableStateOf(trailingIcon)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var prefix: String by mutableStateOf(prefix)

    var suffix: String by mutableStateOf(suffix)

    var helperText: String by mutableStateOf(helperText)

    var helperLink: String by mutableStateOf(helperLink)

    var constrainedMaxWidth: Boolean by mutableStateOf(constrainedMaxWidth)

    val enabledSwitchEnabled: Boolean
        get() = !error && !hasLoader

    val errorSwitchEnabled: Boolean
        get() = !readOnly && !hasLoader && enabled

    val errorMessageTextInputEnabled: Boolean
        get() = error

    val readOnlySwitchEnabled: Boolean
        get() = !error && !hasLoader

    val loaderSwitchEnabled: Boolean
        get() = enabled && !readOnly && !error && textFieldState.text.isNotEmpty()
}
