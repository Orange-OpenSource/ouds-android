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
    value: String = "",
    label: String = stringResource(id = R.string.app_components_common_label_label),
    placeholder: String = "",
    outlined: Boolean = false,
    leadingIcon: Boolean = false,
    trailingIcon: Boolean = false,
    hasLoader: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    error: Boolean = false,
    prefix: String = "",
    suffix: String = "",
    helperText: String = "",
    helperLink: String = "",
) = rememberSaveable(
    value,
    label,
    placeholder,
    outlined,
    leadingIcon,
    trailingIcon,
    enabled,
    readOnly,
    hasLoader,
    error,
    prefix,
    suffix,
    helperText,
    helperLink,
    saver = TextInputDemoState.Saver
) {
    TextInputDemoState(
        value,
        label,
        placeholder,
        outlined,
        leadingIcon,
        trailingIcon,
        hasLoader,
        enabled,
        readOnly,
        error,
        prefix,
        suffix,
        helperText,
        helperLink
    )
}

class TextInputDemoState(
    value: String,
    label: String,
    placeholder: String,
    outlined: Boolean,
    leadingIcon: Boolean,
    trailingIcon: Boolean,
    hasLoader: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    prefix: String,
    suffix: String,
    helperText: String,
    helperLink: String,
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        value,
                        label,
                        placeholder,
                        outlined,
                        leadingIcon,
                        trailingIcon,
                        hasLoader,
                        enabled,
                        readOnly,
                        error,
                        prefix,
                        suffix,
                        helperText,
                        helperLink
                    )
                }
            },
            restore = { list: List<Any?> ->
                TextInputDemoState(
                    list[0] as String,
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
                    list[13] as String
                )
            }
        )
    }

    var value: String by mutableStateOf(value)

    var label: String by mutableStateOf(label)

    var placeholder: String by mutableStateOf(placeholder)

    var outlined: Boolean by mutableStateOf(outlined)

    var error: Boolean by mutableStateOf(error)

    var leadingIcon: Boolean by mutableStateOf(leadingIcon)

    var trailingIcon: Boolean by mutableStateOf(trailingIcon)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    var enabled: Boolean by mutableStateOf(enabled)

    var readOnly: Boolean by mutableStateOf(readOnly)

    var prefix: String by mutableStateOf(prefix)

    var suffix: String by mutableStateOf(suffix)

    var helperText: String by mutableStateOf(helperText)

    var helperLink: String by mutableStateOf(helperLink)

    val enabledSwitchEnabled: Boolean
        get() = !error && !hasLoader

    val errorSwitchEnabled: Boolean
        get() = !readOnly && !hasLoader && enabled

    val readOnlySwitchEnabled: Boolean
        get() = !error && !hasLoader

    val loaderSwitchEnabled: Boolean
        get() = enabled && !readOnly && !error && value.isNotEmpty()
}
