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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsNavigationButtonAppearance
import com.orange.ouds.core.component.OudsNavigationButtonDefaults
import com.orange.ouds.core.component.OudsNavigationButtonLayout

@Composable
fun rememberNavigationButtonDemoState(
    label: String = stringResource(id = R.string.app_components_common_label_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    hasLoader: Boolean = false,
    appearance: OudsNavigationButtonAppearance = OudsNavigationButtonDefaults.Appearance,
    layout: OudsNavigationButtonLayout = OudsNavigationButtonLayout.Next,
    iconOnly: Boolean = false
) = rememberSaveable(label, enabled, onColoredBox, hasLoader, appearance, layout, iconOnly, saver = NavigationButtonDemoState.Saver) {
    NavigationButtonDemoState(label, enabled, onColoredBox, hasLoader, appearance, layout, iconOnly)
}

class NavigationButtonDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    hasLoader: Boolean,
    appearance: OudsNavigationButtonAppearance,
    layout: OudsNavigationButtonLayout,
    iconOnly: Boolean
) {

    companion object {
        private val ForbiddenAppearancesOnColoredBox = listOf(OudsNavigationButtonAppearance.Brand)

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        enabled,
                        onColoredBox,
                        hasLoader,
                        appearance,
                        layout,
                        this.iconOnly
                    )
                }
            },
            restore = { list: List<Any?> ->
                NavigationButtonDemoState(
                    list[0] as String,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as Boolean,
                    list[4] as OudsNavigationButtonAppearance,
                    list[5] as OudsNavigationButtonLayout,
                    list[6] as Boolean
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    private var _appearance: OudsNavigationButtonAppearance by mutableStateOf(appearance)
    var appearance: OudsNavigationButtonAppearance
        get() = _appearance
        set(value) {
            _appearance = value
            if (value in ForbiddenAppearancesOnColoredBox) {
                onColoredBox = false
            }
        }

    var layout: OudsNavigationButtonLayout by mutableStateOf(layout)

    var iconOnly: Boolean by mutableStateOf(iconOnly)

    val enabledSwitchEnabled: Boolean
        get() = !hasLoader

    val onColoredBoxSwitchEnabled: Boolean
        get() = appearance !in ForbiddenAppearancesOnColoredBox

    val loaderSwitchEnabled: Boolean
        get() = enabled

    val labelTextInputEnabled: Boolean
        get() = !iconOnly
}
