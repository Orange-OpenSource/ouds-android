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

package com.orange.ouds.app.ui.components.chip

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R

open class ChipDemoState(
    enabled: Boolean,
    layout: Layout,
    label: String
) {

    companion object {

        const val ChipCount = 2

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        enabled,
                        layout,
                        label
                    )
                }
            },
            restore = { list ->
                ChipDemoState(
                    list[0] as Boolean,
                    list[1] as Layout,
                    list[2] as String
                )
            }
        )
    }

    var enabled: Boolean by mutableStateOf(enabled)

    var layout: Layout by mutableStateOf(layout)

    var label: String by mutableStateOf(label)

    val labelTextFieldEnabled: Boolean
        get() = layout != Layout.IconOnly

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label),
        IconOnly(R.string.app_components_common_iconOnlyLayout_label)
    }
}
