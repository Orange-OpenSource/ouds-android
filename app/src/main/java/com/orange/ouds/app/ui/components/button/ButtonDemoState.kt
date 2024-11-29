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
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R
import com.orange.ouds.core.component.button.OudsButton
import com.orange.ouds.core.component.button.OudsButtonDefaults

@Composable
fun rememberButtonDemoState(
    enabled: Boolean = true,
    style: OudsButton.Style = OudsButtonDefaults.style,
    hierarchy: OudsButton.Hierarchy = OudsButtonDefaults.hierarchy,
    layout: ButtonDemoState.Layout = ButtonDemoState.Layout.TextOnly
) = rememberSaveable(enabled, style, hierarchy, layout, saver = ButtonDemoState.Saver) {
    ButtonDemoState(enabled, style, hierarchy, layout)
}

class ButtonDemoState(
    enabled: Boolean,
    style: OudsButton.Style,
    hierarchy: OudsButton.Hierarchy,
    layout: Layout
) {

    companion object {

        val Saver = run {
            val enabledKey = "enabled"
            val styleKey = "style"
            val hierarchyKey = "hierarchy"
            val layoutKey = "layout"
            mapSaver(
                save = { state ->
                    mapOf(
                        enabledKey to state.enabled,
                        styleKey to state.style,
                        hierarchyKey to state.hierarchy,
                        layoutKey to state.layout
                    )
                },
                restore = { map ->
                    ButtonDemoState(
                        map[enabledKey] as Boolean,
                        map[styleKey] as OudsButton.Style,
                        map[hierarchyKey] as OudsButton.Hierarchy,
                        map[layoutKey] as Layout
                    )
                }
            )
        }
    }

    var enabled: Boolean by mutableStateOf(enabled)

    var style: OudsButton.Style by mutableStateOf(style)

    var hierarchy: OudsButton.Hierarchy by mutableStateOf(hierarchy)

    var layout: Layout by mutableStateOf(layout)

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_button_textOnlyLayout_label),
        IconAndText(R.string.app_components_button_iconAndTextLayout_label),
        IconOnly(R.string.app_components_button_iconOnlyLayout_label)
    }
}
