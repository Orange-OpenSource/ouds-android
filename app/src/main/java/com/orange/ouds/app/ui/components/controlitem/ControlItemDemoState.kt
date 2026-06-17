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

package com.orange.ouds.app.ui.components.controlitem

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R

open class ControlItemDemoState(
    icon: Icon,
    edgeToEdge: Boolean,
    divider: Boolean,
    reversed: Boolean,
    enabled: Boolean,
    readOnly: Boolean,
    error: Boolean,
    errorMessage: String,
    label: String,
    description: String?,
    constrainedMaxWidth: Boolean,
    annotatedText: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        icon,
                        edgeToEdge,
                        divider,
                        reversed,
                        enabled,
                        readOnly,
                        error,
                        errorMessage,
                        label,
                        description,
                        constrainedMaxWidth,
                        annotatedText
                    )
                }
            },
            restore = { list ->
                ControlItemDemoState(
                    list[0] as Icon,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as Boolean,
                    list[5] as Boolean,
                    list[6] as Boolean,
                    list[7] as String,
                    list[8] as String,
                    list[9] as String?,
                    list[10] as Boolean,
                    list[11] as Boolean
                )
            }
        )
    }

    var icon: Icon by mutableStateOf(icon)
    var constrainedMaxWidth: Boolean by mutableStateOf(constrainedMaxWidth)
    var edgeToEdge: Boolean by mutableStateOf(edgeToEdge)
    var divider: Boolean by mutableStateOf(divider)
    var reversed: Boolean by mutableStateOf(reversed)
    var enabled: Boolean by mutableStateOf(enabled)
    var readOnly: Boolean by mutableStateOf(readOnly)
    var error: Boolean by mutableStateOf(error)
    var errorMessage: String by mutableStateOf(errorMessage)
    var label: String by mutableStateOf(label)
    var description: String? by mutableStateOf(description)

    private var _annotatedText: Boolean by mutableStateOf(annotatedText)
    var annotatedText: Boolean
        get() = _annotatedText
        set(value) {
            _annotatedText = value
            if (value) {
                error = true
            }
        }

    val enabledSwitchEnabled: Boolean
        get() = !error

    val readOnlySwitchEnabled: Boolean
        get() = !error

    val errorSwitchEnabled: Boolean
        get() = enabled && !readOnly

    val errorMessageTextInputEnabled: Boolean
        get() = error && !annotatedText

    enum class Icon(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_tech),
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}
