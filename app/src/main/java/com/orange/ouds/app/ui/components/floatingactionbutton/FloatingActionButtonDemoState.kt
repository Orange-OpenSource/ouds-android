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

package com.orange.ouds.app.ui.components.floatingactionbutton

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsFloatingActionButtonAppearance
import com.orange.ouds.core.component.OudsFloatingActionButtonDefaults

@Composable
fun rememberFloatingActionButtonDemoState(
    size: FloatingActionButtonDemoState.Size = FloatingActionButtonDemoState.Size.Medium,
    appearance: OudsFloatingActionButtonAppearance = OudsFloatingActionButtonDefaults.Appearance,
    layout: FloatingActionButtonDemoState.Layout = FloatingActionButtonDemoState.Layout.entries.first(),
    label: String = stringResource(id = R.string.app_components_floatingActionButton_label),
    expanded: Boolean = true
) = rememberSaveable(size, appearance, layout, label, expanded, saver = FloatingActionButtonDemoState.Saver) {
    FloatingActionButtonDemoState(size, appearance, layout, label, expanded)
}

class FloatingActionButtonDemoState(
    size: Size,
    appearance: OudsFloatingActionButtonAppearance,
    layout: Layout,
    label: String,
    expanded: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        size,
                        appearance,
                        layout,
                        label,
                        expanded
                    )
                }
            },
            restore = { list: List<Any?> ->
                FloatingActionButtonDemoState(
                    list[0] as Size,
                    list[1] as OudsFloatingActionButtonAppearance,
                    list[2] as Layout,
                    list[3] as String,
                    list[4] as Boolean
                )
            }
        )
    }

    private var _size: Size by mutableStateOf(size)
    var size: Size
        get() = _size
        set(value) {
            _size = value
            if (layout !in enabledLayouts) {
                layout = enabledLayouts.first()
            }
        }

    var appearance: OudsFloatingActionButtonAppearance by mutableStateOf(appearance)

    private var _layout: Layout by mutableStateOf(layout)
    var layout: Layout
        get() = _layout
        set(value) {
            if (_layout != value) {
                _layout = value
                if (value == Layout.TextAndIcon) {
                    expanded = true
                }
            }
        }

    val enabledLayouts: List<Layout>
        get() = when (size) {
            Size.Small,
            Size.Large -> listOf(Layout.IconOnly)
            Size.Medium -> Layout.entries
        }

    var label: String by mutableStateOf(label)

    var expanded: Boolean by mutableStateOf(expanded)

    val labelTextInputEnabled: Boolean
        get() = layout != Layout.IconOnly

    val expandedSwitchEnabled: Boolean
        get() = layout == Layout.TextAndIcon

    enum class Size(@StringRes val labelRes: Int) {
        Small(R.string.app_components_common_smallSize_label),
        Medium(R.string.app_components_common_mediumSize_label),
        Large(R.string.app_components_common_largeSize_label)
    }

    enum class Layout(@StringRes val labelRes: Int) {
        IconOnly(R.string.app_components_common_iconOnlyLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label),
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
    }
}
