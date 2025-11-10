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
import androidx.compose.ui.res.painterResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.core.component.OudsBadgeDefaults
import com.orange.ouds.core.component.OudsBadgeIcon
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.component.OudsIconBadgeStatus

@Composable
fun rememberBadgeDemoState(
    type: BadgeDemoState.Type = BadgeDemoState.Type.Count,
    size: OudsBadgeSize = OudsBadgeDefaults.Size,
    status: OudsBadgeStatus = OudsBadgeDefaults.Status,
    count: Int = 1,
    enabled: Boolean = true
) = rememberSaveable(type, size, status, count, enabled, saver = BadgeDemoState.Saver) {
    BadgeDemoState(type, size, status, count, enabled)
}

class BadgeDemoState(
    type: Type,
    size: OudsBadgeSize,
    status: OudsBadgeStatus,
    count: Int,
    enabled: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        type,
                        size,
                        status,
                        count,
                        enabled
                    )
                }
            },
            restore = { list: List<Any?> ->
                BadgeDemoState(
                    list[0] as Type,
                    list[1] as OudsBadgeSize,
                    list[2] as OudsBadgeStatus,
                    list[3] as Int,
                    list[4] as Boolean
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
                enabledSizes = getEnabledSizes(value)
            }
        }

    var size: OudsBadgeSize by mutableStateOf(size)

    var enabledSizes: List<OudsBadgeSize> = getEnabledSizes(type)
        private set(value) {
            field = value
            if (size !in enabledSizes) {
                size = enabledSizes.first()
            }
        }

    var status: OudsBadgeStatus by mutableStateOf(status)

    val badgeWithIconStatus: OudsIconBadgeStatus
        @Composable
        get() = when (status) {
            OudsBadgeStatus.Neutral -> OudsIconBadgeStatus.Neutral(OudsBadgeIcon(painterResource(LocalThemeDrawableResources.current.heartEmpty)))
            OudsBadgeStatus.Accent -> OudsIconBadgeStatus.Accent(OudsBadgeIcon(painterResource(LocalThemeDrawableResources.current.heartEmpty)))
            OudsBadgeStatus.Positive -> OudsIconBadgeStatus.Positive
            OudsBadgeStatus.Info -> OudsIconBadgeStatus.Info
            OudsBadgeStatus.Warning -> OudsIconBadgeStatus.Warning
            OudsBadgeStatus.Negative -> OudsIconBadgeStatus.Negative
        }

    var count: Int by mutableIntStateOf(count)

    var enabled: Boolean by mutableStateOf(enabled)

    val countTextFieldEnabled: Boolean
        get() = type == Type.Count

    private fun getEnabledSizes(type: Type): List<OudsBadgeSize> {
        return when (type) {
            Type.Standard -> OudsBadgeSize.entries
            Type.Count,
            Type.Icon -> listOf(OudsBadgeSize.Medium, OudsBadgeSize.Large)
        }
    }

    enum class Type(@StringRes val labelRes: Int) {
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_count_label),
        Icon(R.string.app_components_badge_iconType_label)
    }
}
