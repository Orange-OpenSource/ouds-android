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
    text: String = stringResource(R.string.app_components_link_label),
    enabled: Boolean = true,
    onColoredBox: Boolean = false,
    size: OudsLink.Size = OudsLinkDefaults.Size,
    layout: LinkDemoState.Layout = LinkDemoState.Layout.TextOnly
) = rememberSaveable(text, enabled, onColoredBox, size, layout, saver = LinkDemoState.Saver) {
    LinkDemoState(text, enabled, onColoredBox, size, layout)
}

class LinkDemoState(
    text: String,
    enabled: Boolean,
    onColoredBox: Boolean,
    size: OudsLink.Size,
    layout: Layout
) {

    companion object {
        val Saver = run {
            val textKey = "text"
            val enabledKey = "enabled"
            val onColoredBoxKey = "onColoredBox"
            val sizeKey = "size"
            val layoutKey = "layout"
            mapSaver(
                save = { state ->
                    mapOf(
                        textKey to state.text,
                        enabledKey to state.enabled,
                        onColoredBoxKey to state.onColoredBox,
                        sizeKey to state.size,
                        layoutKey to state.layout
                    )
                },
                restore = { map ->
                    LinkDemoState(
                        map[textKey] as String,
                        map[enabledKey] as Boolean,
                        map[onColoredBoxKey] as Boolean,
                        map[sizeKey] as OudsLink.Size,
                        map[layoutKey] as Layout
                    )
                }
            )
        }
    }

    var text: String by mutableStateOf(text)

    var enabled: Boolean by mutableStateOf(enabled)

    var onColoredBox: Boolean by mutableStateOf(onColoredBox)

    var size: OudsLink.Size by mutableStateOf(size)

    var layout: Layout by mutableStateOf(layout)

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        IconAndText(R.string.app_components_common_iconAndTextLayout_label),
        ArrowBack(R.string.app_components_link_back_label),
        ArrowNext(R.string.app_components_link_next_label)
    }
}
