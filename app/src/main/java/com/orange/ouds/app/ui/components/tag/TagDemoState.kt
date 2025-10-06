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

package com.orange.ouds.app.ui.components.tag

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsTagDefaults
import com.orange.ouds.core.component.OudsTagHierarchy
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus


@Composable
fun rememberTagDemoState(
    label: String = stringResource(id = R.string.app_components_tag_label),
    hierarchy: OudsTagHierarchy = OudsTagDefaults.Hierarchy,
    layout: TagDemoState.Layout = TagDemoState.Layout.TextOnly,
    roundedCorners: Boolean = true,
    size: OudsTagSize = OudsTagDefaults.Size,
    status: OudsTagStatus = OudsTagDefaults.Status,
    hasLoader: Boolean = false
) = rememberSaveable(label, hierarchy, layout, roundedCorners, size, status, hasLoader, saver = TagDemoState.Saver) {
    TagDemoState(label, hierarchy, layout, roundedCorners, size, status, hasLoader)
}

class TagDemoState(
    label: String,
    hierarchy: OudsTagHierarchy,
    layout: Layout,
    roundedCorners: Boolean,
    size: OudsTagSize,
    status: OudsTagStatus,
    hasLoader: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        hierarchy,
                        layout,
                        roundedCorners,
                        size,
                        status,
                        hasLoader
                    )
                }
            },
            restore = { list: List<Any?> ->
                TagDemoState(
                    list[0] as String,
                    list[1] as OudsTagHierarchy,
                    list[2] as Layout,
                    list[3] as Boolean,
                    list[4] as OudsTagSize,
                    list[5] as OudsTagStatus,
                    list[6] as Boolean
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var hierarchy: OudsTagHierarchy by mutableStateOf(hierarchy)

    var layout: Layout by mutableStateOf(layout)

    var roundedCorners: Boolean by mutableStateOf(roundedCorners)

    var size: OudsTagSize by mutableStateOf(size)

    var status: OudsTagStatus by mutableStateOf(status)

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    val loaderSwitchEnabled: Boolean
        get() = status != OudsTagStatus.Disabled

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndBullet(R.string.app_components_tag_textAndBulletLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label)
    }
}
