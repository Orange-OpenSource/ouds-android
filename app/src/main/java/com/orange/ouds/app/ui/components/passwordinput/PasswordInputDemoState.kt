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

package com.orange.ouds.app.ui.components.passwordinput

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsPasswordInputState

@Composable
fun rememberPasswordInputDemoState(
    passwordInputState: OudsPasswordInputState = OudsPasswordInputState(),
    label: String = stringResource(id = R.string.app_components_common_label_label),
    placeholder: String = "",
    outlined: Boolean = false,
    lockIcon: Boolean = false,
    hasLoader: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    errorMessage: String = stringResource(id = R.string.app_components_common_errorMessage_label),
    prefix: String = "",
    helperText: String = "",
    constrainedMaxWidth: Boolean = false
) = rememberSaveable(
    passwordInputState,
    label,
    placeholder,
    outlined,
    lockIcon,
    enabled,
    readOnly,
    hasLoader,
    error,
    errorMessage,
    prefix,
    helperText,
    constrainedMaxWidth,
    saver = PasswordInputDemoState.Saver
) {
    PasswordInputDemoState(
        passwordInputState,
        label,
        placeholder,
        outlined,
        lockIcon,
        hasLoader,
        enabled,
        readOnly,
        error,
        errorMessage,
        prefix,
        helperText,
        constrainedMaxWidth
    )
}

class PasswordInputDemoState(
    passwordInputState: OudsPasswordInputState,
    label: String,
    placeholder: String,
    outlined: Boolean,
    lockIcon: Boolean,
    hasLoader: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorMessage: String,
    prefix: String,
    helperText: String,
    constrainedMaxWidth: Boolean
) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(OudsPasswordInputState.Saver) { save(passwordInputState) },
                        label,
                        placeholder,
                        outlined,
                        lockIcon,
                        hasLoader,
                        enabled,
                        readOnly,
                        error,
                        errorMessage,
                        prefix,
                        helperText,
                        constrainedMaxWidth
                    )
                }
            },
            restore = { list: List<Any?> ->
                val passwordInputState = list[0]?.let { OudsPasswordInputState.Saver.restore(it) }
                PasswordInputDemoState(
                    passwordInputState as OudsPasswordInputState,
                    list[1] as String,
                    list[2] as String,
                    list[3] as Boolean,
                    list[4] as Boolean,
                    list[5] as Boolean,
                    list[6] as Boolean,
                    list[7] as Boolean,
                    list[8] as Boolean,
                    list[9] as String,
                    list[10] as String,
                    list[11] as String,
                    list[12] as Boolean
                )
            }
        )
    }

    var passwordInputState: OudsPasswordInputState by mutableStateOf(passwordInputState)

    var label: String by mutableStateOf(label)

    var placeholder: String by mutableStateOf(placeholder)

    var outlined: Boolean by mutableStateOf(outlined)

    var error: Boolean by mutableStateOf(error)

    var errorMessage: String by mutableStateOf(errorMessage)

    var lockIcon: Boolean by mutableStateOf(lockIcon)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var prefix: String by mutableStateOf(prefix)

    var helperText: String by mutableStateOf(helperText)

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
        get() = enabled && !readOnly && !error && passwordInputState.text.isNotEmpty()
}
