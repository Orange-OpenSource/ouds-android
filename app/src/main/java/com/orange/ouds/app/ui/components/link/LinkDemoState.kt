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
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.component.OudsLinkDefaults

@Composable
fun rememberLinkDemoState(
    label: String = stringResource(R.string.app_components_link_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    size: OudsLink.Size = OudsLinkDefaults.Size,
    layout: LinkDemoState.Layout = LinkDemoState.Layout.entries.first()
) = rememberSaveable(label, enabled, onColoredBox, size, layout, saver = LinkDemoState.Saver) {
    LinkDemoState(label, enabled, onColoredBox, size, layout)
}

class LinkDemoState(
    label: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    size: OudsLink.Size,
    layout: Layout
) {

    companion object {
        val Saver = run {
            val labelKey = "label"
            val enabledKey = "enabled"
            val onColoredBoxKey = "onColoredBox"
            val sizeKey = "size"
            val layoutKey = "layout"
            mapSaver(
                save = { state ->
                    mapOf(
                        labelKey to state.label,
                        enabledKey to state.enabled,
                        onColoredBoxKey to state.onColoredBox,
                        sizeKey to state.size,
                        layoutKey to state.layout
                    )
                },
                restore = { map ->
                    LinkDemoState(
                        map[labelKey] as String,
                        map[enabledKey] as Boolean,
                        map[onColoredBoxKey] as Boolean,
                        map[sizeKey] as OudsLink.Size,
                        map[layoutKey] as Layout
                    )
                }
            )
        }
    }

    var label: String by mutableStateOf(label)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var size: OudsLink.Size by mutableStateOf(size)

    var layout: Layout by mutableStateOf(layout)

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label),
        ArrowBack(R.string.app_components_link_backLayout_label),
        ArrowNext(R.string.app_components_link_nextLayout_label)
    }
}
