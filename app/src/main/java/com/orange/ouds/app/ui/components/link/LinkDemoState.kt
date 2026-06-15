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

package com.orange.ouds.app.ui.components.link

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsLinkDefaults
import com.orange.ouds.core.component.OudsLinkSize

@Composable
fun rememberLinkDemoState(
    label: String = stringResource(R.string.app_components_common_label_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    size: OudsLinkSize = OudsLinkDefaults.Size,
    layout: LinkDemoState.Layout = LinkDemoState.Layout.entries.first(),
    icon: LinkDemoState.Icon = LinkDemoState.Icon.Tinted
) = rememberSaveable(label, enabled, onColoredBox, size, layout, icon, saver = LinkDemoState.Saver) {
    LinkDemoState(label, enabled, onColoredBox, size, layout, icon)
}

class LinkDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    size: OudsLinkSize,
    layout: Layout,
    icon: Icon
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        enabled,
                        onColoredBox,
                        size,
                        layout,
                        icon
                    )
                }
            },
            restore = { list: List<Any?> ->
                LinkDemoState(
                    list[0] as String,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as OudsLinkSize,
                    list[4] as Layout,
                    list[5] as Icon
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var size: OudsLinkSize by mutableStateOf(size)

    var layout: Layout by mutableStateOf(layout)

    var icon: Icon by mutableStateOf(icon)

    val enabledIcons: List<Icon>
        get() = if (layout == Layout.TextAndIcon) Icon.entries else emptyList()

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_tech),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_tech),
        ChevronBack(R.string.app_components_link_backLayout_tech),
        ChevronNext(R.string.app_components_link_nextLayout_tech)
    }

    enum class Icon(@StringRes val labelRes: Int) {
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}
