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
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagDefaults


@Composable
fun rememberTagDemoState(
    label: String = stringResource(id = R.string.app_components_tag_label),
    hierarchy: OudsTag.Hierarchy = OudsTagDefaults.Hierarchy,
    layout: TagDemoState.Layout = TagDemoState.Layout.TextOnly,
    shape: OudsTag.Shape = OudsTagDefaults.Shape,
    size: OudsTag.Size = OudsTagDefaults.Size,
    status: OudsTag.Status = OudsTagDefaults.Status,
    loading: Boolean = false
) = rememberSaveable(label, hierarchy, layout, shape, size, status, loading, saver = TagDemoState.Saver) {
    TagDemoState(label, hierarchy, layout, shape, size, status, loading)
}

class TagDemoState(
    label: String,
    hierarchy: OudsTag.Hierarchy,
    layout: Layout,
    shape: OudsTag.Shape,
    size: OudsTag.Size,
    status: OudsTag.Status,
    loading: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        hierarchy,
                        layout,
                        shape,
                        size,
                        status,
                        loading
                    )
                }
            },
            restore = { list: List<Any?> ->
                TagDemoState(
                    list[0] as String,
                    list[1] as OudsTag.Hierarchy,
                    list[2] as Layout,
                    list[3] as OudsTag.Shape,
                    list[4] as OudsTag.Size,
                    list[5] as OudsTag.Status,
                    list[6] as Boolean
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var hierarchy: OudsTag.Hierarchy by mutableStateOf(hierarchy)

    var layout: Layout by mutableStateOf(layout)

    var shape: OudsTag.Shape by mutableStateOf(shape)

    var size: OudsTag.Size by mutableStateOf(size)

    var status: OudsTag.Status by mutableStateOf(status)

    val loading: Boolean by mutableStateOf(loading)

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_label),
        TextAndBullet(R.string.app_components_tag_textAndBulletLayout_label),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_label)
    }
}
