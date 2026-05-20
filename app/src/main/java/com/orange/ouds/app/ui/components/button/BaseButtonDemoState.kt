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

package com.orange.ouds.app.ui.components.button

import android.R.attr.label
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class BaseButtonDemoState(
    enabled: Boolean,
    onColoredBox: Boolean,
    hasLoader: Boolean
) {
    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        enabled,
                        onColoredBox,
                        hasLoader
                    )
                }
            },
            restore = { list: List<Any?> ->
                BaseButtonDemoState(
                    list[0] as Boolean,
                    list[1] as Boolean,
                    list[2] as Boolean
                )
            }
        )
    }

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    val enabledSwitchEnabled: Boolean
        get() = !hasLoader

    val loaderSwitchEnabled: Boolean
        get() = enabled
}