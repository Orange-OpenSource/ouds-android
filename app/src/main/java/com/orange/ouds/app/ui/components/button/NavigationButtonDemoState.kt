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
import com.orange.ouds.core.component.OudsNavigationButtonAppearance
import com.orange.ouds.core.component.OudsNavigationButtonChevron
import com.orange.ouds.core.component.OudsNavigationButtonDefaults

@Composable
fun rememberNavigationButtonDemoState(
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    hasLoader: Boolean = false,
    appearance: OudsNavigationButtonAppearance = OudsNavigationButtonDefaults.Appearance,
    chevron: OudsNavigationButtonChevron = OudsNavigationButtonDefaults.Chevron,
    label: String? = null
) = rememberSaveable(label, enabled, onColoredBox, hasLoader, appearance, chevron, saver = NavigationButtonDemoState.Saver) {
    NavigationButtonDemoState(enabled, onColoredBox, hasLoader, appearance, chevron, label)
}

class NavigationButtonDemoState(
    enabled: Boolean,
    onColoredBox: Boolean,
    hasLoader: Boolean,
    appearance: OudsNavigationButtonAppearance,
    chevron: OudsNavigationButtonChevron,
    label: String?
) : BaseButtonDemoState(enabled, onColoredBox, hasLoader) {

    companion object {

        private val ForbiddenAppearancesOnColoredBox = listOf(OudsNavigationButtonAppearance.Brand)

        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        appearance,
                        chevron,
                        label,
                        with(BaseButtonDemoState.Saver) { save(state) }
                    )
                }
            },
            restore = { list: List<Any?> ->
                val baseButtonDemoState = list[3]?.let { BaseButtonDemoState.Saver.restore(it) }
                baseButtonDemoState?.run {
                    NavigationButtonDemoState(
                        enabled,
                        onColoredBox,
                        hasLoader,
                        list[0] as OudsNavigationButtonAppearance,
                        list[1] as OudsNavigationButtonChevron,
                        list[2] as String?
                    )
                }
            }
        )
    }

    private var _appearance: OudsNavigationButtonAppearance by mutableStateOf(appearance)
    var appearance: OudsNavigationButtonAppearance
        get() = _appearance
        set(value) {
            _appearance = value
            if (value in ForbiddenAppearancesOnColoredBox) {
                onColoredBox = false
            }
        }

    var chevron: OudsNavigationButtonChevron by mutableStateOf(chevron)

    var label: String? by mutableStateOf(label)

    val onColoredBoxSwitchEnabled: Boolean
        get() = appearance !in ForbiddenAppearancesOnColoredBox
}
