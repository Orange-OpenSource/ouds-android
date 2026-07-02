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
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsButtonDefaults

@Composable
fun rememberButtonDemoState(
    label: String = stringResource(id = R.string.app_components_common_label_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    hasLoader: Boolean = false,
    appearance: OudsButtonAppearance = OudsButtonDefaults.Appearance,
    size: ButtonDemoState.Size = ButtonDemoState.Size.entries.first(),
    layout: ButtonDemoState.Layout = ButtonDemoState.Layout.entries.first(),
    icon: ButtonDemoState.Icon = ButtonDemoState.Icon.Tinted
) = rememberSaveable(label, enabled, onColoredBox, hasLoader, appearance, size, layout, icon, saver = ButtonDemoState.Saver) {
    ButtonDemoState(label, enabled, onColoredBox, hasLoader, appearance, size, layout, icon)
}

class ButtonDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    hasLoader: Boolean,
    appearance: OudsButtonAppearance,
    size: Size,
    layout: Layout,
    icon: Icon
) : BaseButtonDemoState(enabled, onColoredBox, hasLoader) {

    companion object {

        private val ForbiddenAppearancesOnColoredBox = listOf(OudsButtonAppearance.Brand, OudsButtonAppearance.Negative)

        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(BaseButtonDemoState.Saver) { save(state) },
                        label,
                        appearance,
                        size,
                        layout,
                        icon
                    )
                }
            },
            restore = { list: List<Any?> ->
                val baseButtonDemoState = list[0]?.let { BaseButtonDemoState.Saver.restore(it) }
                baseButtonDemoState?.run {
                    ButtonDemoState(
                        list[1] as String,
                        enabled,
                        onColoredBox,
                        hasLoader,
                        list[2] as OudsButtonAppearance,
                        list[3] as Size,
                        list[4] as Layout,
                        list[5] as Icon
                    )
                }
            }
        )
    }

    var label: String by mutableStateOf(label)

    var size: Size by mutableStateOf(size)

    var layout: Layout by mutableStateOf(layout)

    private var _appearance: OudsButtonAppearance by mutableStateOf(appearance)
    var appearance: OudsButtonAppearance
        get() = _appearance
        set(value) {
            _appearance = value
            if (value in ForbiddenAppearancesOnColoredBox) {
                onColoredBox = false
            }
        }

    val onColoredBoxSwitchEnabled: Boolean
        get() = appearance !in ForbiddenAppearancesOnColoredBox

    val labelTextInputEnabled: Boolean
        get() = layout != Layout.IconOnly

    var icon: Icon by mutableStateOf(icon)

    val enabledIcons: List<Icon>
        get() = if (layout != Layout.TextOnly) Icon.entries else emptyList()

    enum class Size(@StringRes val labelRes: Int) {
        Default(R.string.app_components_button_button_defaultSize_tech),
        Small(R.string.app_components_common_smallSize_tech)
    }

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_tech),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_tech),
        IconOnly(R.string.app_components_common_iconOnlyLayout_tech)
    }

    enum class Icon(@StringRes val labelRes: Int) {
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}
