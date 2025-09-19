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

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonDefaults

@Composable
fun rememberButtonDemoState(
    label: String = stringResource(id = R.string.app_components_button_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    hasLoader: Boolean = false,
    appearance: OudsButton.Appearance = OudsButtonDefaults.Appearance,
    layout: ButtonDemoState.Layout = ButtonDemoState.Layout.entries.first()
) = rememberSaveable(label, enabled, onColoredBox, hasLoader, appearance, layout, saver = ButtonDemoState.Saver) {
    ButtonDemoState(label, enabled, onColoredBox, hasLoader, appearance, layout)
}

class ButtonDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    hasLoader: Boolean,
    appearance: OudsButton.Appearance,
    layout: Layout
) {

    companion object {
        private val ForbiddenAppearancesOnColoredBox = listOf(OudsButton.Appearance.Brand, OudsButton.Appearance.Negative)

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        enabled,
                        onColoredBox,
                        hasLoader,
                        appearance,
                        layout
                    )
                }
            },
            restore = { list: List<Any?> ->
                ButtonDemoState(
                    list[0] as String,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as OudsButton.Appearance,
                    list[5] as Layout,
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    private var _appearance: OudsButton.Appearance by mutableStateOf(appearance)
    var appearance: OudsButton.Appearance
        get() = _appearance
        set(value) {
            _appearance = value
            if (value in ForbiddenAppearancesOnColoredBox) {
                onColoredBox = false
            }
        }

    var layout: Layout by mutableStateOf(layout)

    val enabledSwitchEnabled: Boolean
        get() = !hasLoader

    val onColoredBoxSwitchEnabled: Boolean
        get() = appearance !in ForbiddenAppearancesOnColoredBox

    val loaderSwitchEnabled: Boolean
        get() = enabled

    val labelTextFieldEnabled: Boolean
        get() = layout != Layout.IconOnly

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label),
        IconOnly(R.string.app_components_common_iconOnlyLayout_label)
    }
}
