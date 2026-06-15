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
import com.orange.ouds.core.component.OudsTagAppearance
import com.orange.ouds.core.component.OudsTagDefaults
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import kotlin.reflect.full.createInstance

@Composable
fun rememberTagDemoState(
    label: String = stringResource(id = R.string.app_components_common_label_label),
    appearance: OudsTagAppearance = OudsTagDefaults.Appearance,
    layout: TagDemoState.Layout = TagDemoState.Layout.TextOnly,
    roundedCorners: Boolean = true,
    size: OudsTagSize = OudsTagDefaults.Size,
    status: OudsTagStatus = OudsTagDefaults.Status,
    hasLoader: Boolean = false,
    enabled: Boolean = true,
    icon: TagDemoState.Icon = TagDemoState.Icon.Tinted
) = rememberSaveable(label, appearance, layout, roundedCorners, size, status, hasLoader, enabled, icon, saver = TagDemoState.Saver) {
    TagDemoState(label, appearance, layout, roundedCorners, size, status, hasLoader, enabled, icon)
}

class TagDemoState(
    label: String,
    appearance: OudsTagAppearance,
    layout: Layout,
    roundedCorners: Boolean,
    size: OudsTagSize,
    status: OudsTagStatus,
    hasLoader: Boolean,
    enabled: Boolean,
    icon: Icon
) {

    companion object {

        private val FunctionalStatusClasses = listOf(
            OudsTagStatus.Info::class,
            OudsTagStatus.Negative::class,
            OudsTagStatus.Positive::class,
            OudsTagStatus.Warning::class
        )

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        appearance,
                        layout,
                        roundedCorners,
                        size,
                        status::class.java.name,
                        hasLoader,
                        enabled,
                        icon
                    )
                }
            },
            restore = { list: List<Any?> ->
                val statusName = list[5] as String
                val status = Class.forName(statusName).kotlin.createInstance() as OudsTagStatus

                TagDemoState(
                    list[0] as String,
                    list[1] as OudsTagAppearance,
                    list[2] as Layout,
                    list[3] as Boolean,
                    list[4] as OudsTagSize,
                    status,
                    list[6] as Boolean,
                    list[7] as Boolean,
                    list[8] as Icon
                )
            }
        )
    }

    var label: String by mutableStateOf(label)

    var appearance: OudsTagAppearance by mutableStateOf(appearance)

    var layout: Layout by mutableStateOf(layout)

    var roundedCorners: Boolean by mutableStateOf(roundedCorners)

    var size: OudsTagSize by mutableStateOf(size)

    private var _status: OudsTagStatus by mutableStateOf(status)
    var status: OudsTagStatus
        get() = _status
        set(value) {
            _status = value
            if (layout == Layout.TextAndIcon && icon !in enabledIcons) {
                icon = enabledIcons.first()
            }
        }

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    var enabled: Boolean by mutableStateOf(enabled)

    var icon: Icon by mutableStateOf(icon)

    val enabledSwitchEnabled: Boolean
        get() = !hasLoader

    val loaderSwitchEnabled: Boolean
        get() = enabled

    val enabledIcons: List<Icon>
        get() = when (layout) {
            Layout.TextAndIcon if status::class !in FunctionalStatusClasses -> Icon.entries
            Layout.TextAndIcon -> listOf(Icon.Tinted)
            Layout.TextOnly,
            Layout.TextAndBullet -> emptyList()
        }

    enum class Layout(@StringRes val labelRes: Int) {
        TextOnly(R.string.app_components_common_textOnlyLayout_tech),
        TextAndBullet(R.string.app_components_tag_textAndBulletLayout_tech),
        TextAndIcon(R.string.app_components_common_textAndIconLayout_tech)
    }

    enum class Icon(@StringRes val labelRes: Int) {
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}
