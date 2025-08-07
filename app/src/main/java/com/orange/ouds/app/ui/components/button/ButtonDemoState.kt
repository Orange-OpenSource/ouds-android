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
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.tag.TagDemoState
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonDefaults
import com.orange.ouds.core.component.OudsTag

@Composable
fun rememberButtonDemoState(
    label: String = stringResource(id = R.string.app_components_button_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    loading: Boolean = false,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.Hierarchy,
    layout: ButtonDemoState.Layout = ButtonDemoState.Layout.entries.first()
) = rememberSaveable(label, enabled, onColoredBox, loading, hierarchy, layout, saver = ButtonDemoState.Saver) {
    ButtonDemoState(label, enabled, onColoredBox, loading, hierarchy, layout)
}

class ButtonDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    loading: Boolean,
    hierarchy: OudsButton.Hierarchy,
    layout: Layout
) {

    companion object {
        private val ForbiddenHierarchiesOnColoredBox = listOf(OudsButton.Hierarchy.Brand, OudsButton.Hierarchy.Negative)

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        enabled,
                        onColoredBox,
                        loading,
                        hierarchy,
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
                    list[4] as OudsButton.Hierarchy,
                    list[5] as Layout,
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var loading: Boolean by mutableStateOf(loading)

    private var _hierarchy: OudsButton.Hierarchy by mutableStateOf(hierarchy)
    var hierarchy: OudsButton.Hierarchy
        get() = _hierarchy
        set(value) {
            _hierarchy = value
            if (value in ForbiddenHierarchiesOnColoredBox) {
                onColoredBox = false
            }
        }

    var layout: Layout by mutableStateOf(layout)

    val enabledSwitchEnabled: Boolean
        get() = !loading

    val onColoredBoxSwitchEnabled: Boolean
        get() = hierarchy !in ForbiddenHierarchiesOnColoredBox

    val loadingSwitchEnabled: Boolean
        get() = enabled

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label),
        IconOnly(R.string.app_components_common_iconOnlyLayout_label)
    }
}
