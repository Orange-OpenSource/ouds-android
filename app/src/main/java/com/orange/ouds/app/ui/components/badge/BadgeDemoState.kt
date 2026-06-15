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

package com.orange.ouds.app.ui.components.badge

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsBadgeDefaults
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus

@Composable
fun rememberBadgeDemoState(
    type: BadgeDemoState.Type = BadgeDemoState.Type.Count,
    size: OudsBadgeSize = OudsBadgeDefaults.Size,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    count: Int = 1,
    enabled: Boolean = true,
    icon: BadgeDemoState.Icon = BadgeDemoState.Icon.Tinted
) = rememberSaveable(type, size, status, count, enabled, icon, saver = BadgeDemoState.Saver) {
    BadgeDemoState(type, size, status, count, enabled, icon)
}

class BadgeDemoState(
    type: Type,
    size: OudsBadgeSize,
    status: OudsBadgeStatus,
    count: Int,
    enabled: Boolean,
    icon: Icon
) {

    companion object {

        private val FunctionalStatuses = listOf(
            OudsBadgeStatus.Info,
            OudsBadgeStatus.Negative,
            OudsBadgeStatus.Positive,
            OudsBadgeStatus.Warning
        )

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        type,
                        size,
                        status,
                        count,
                        enabled,
                        icon
                    )
                }
            },
            restore = { list: List<Any?> ->
                BadgeDemoState(
                    list[0] as Type,
                    list[1] as OudsBadgeSize,
                    list[2] as OudsBadgeStatus,
                    list[3] as Int,
                    list[4] as Boolean,
                    list[5] as Icon
                )
            }
        )
    }

    private var _type: Type by mutableStateOf(type)

    var type: Type
        get() = _type
        set(value) {
            if (_type != value) {
                _type = value
                if (size !in enabledSizes) {
                    size = enabledSizes.first()
                }
            }
        }

    var size: OudsBadgeSize by mutableStateOf(size)

    val enabledSizes: List<OudsBadgeSize>
        get() = when (type) {
            Type.Standard,
            Type.Icon -> OudsBadgeSize.entries
            Type.Count -> listOf(OudsBadgeSize.Medium, OudsBadgeSize.Large)
        }

    private var _status: OudsBadgeStatus by mutableStateOf(status)
    var status: OudsBadgeStatus
        get() = _status
        set(value) {
            _status = value
            if (type == Type.Icon && icon !in enabledIcons) {
                icon = enabledIcons.first()
            }
        }

    var count: Int by mutableIntStateOf(count)

    var enabled: Boolean by mutableStateOf(enabled)

    var icon: Icon by mutableStateOf(icon)

    val countTextInputEnabled: Boolean
        get() = type == Type.Count

    val enabledIcons: List<Icon>
        get() = when (type) {
            Type.Icon if status !in FunctionalStatuses -> Icon.entries
            Type.Icon -> listOf(Icon.Tinted)
            Type.Standard,
            Type.Count -> emptyList()
        }

    enum class Type(@StringRes val labelRes: Int) {
        Standard(R.string.app_components_badge_standardType_tech),
        Count(R.string.app_components_badge_count_tech),
        Icon(R.string.app_components_badge_iconType_tech)
    }

    enum class Icon(@StringRes val labelRes: Int) {
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}
