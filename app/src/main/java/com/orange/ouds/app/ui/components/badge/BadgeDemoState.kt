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
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsBadgeDefaults

@Composable
fun rememberBadgeDemoState(
    type: BadgeDemoState.Type = BadgeDemoState.Type.Count,
    size: OudsBadge.Size = OudsBadgeDefaults.Size,
    status: OudsBadge.Status = OudsBadgeDefaults.Status,
    count: Int = 1
) = rememberSaveable(type, size, status, count, saver = BadgeDemoState.Saver) {
    BadgeDemoState(type, size, status, count)
}

class BadgeDemoState(
    type: Type,
    size: OudsBadge.Size,
    status: OudsBadge.Status,
    count: Int
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        type,
                        size,
                        status,
                        count
                    )
                }
            },
            restore = { list: List<Any?> ->
                BadgeDemoState(
                    list[0] as Type,
                    list[1] as OudsBadge.Size,
                    list[2] as OudsBadge.Status,
                    list[3] as Int
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

    var size: OudsBadge.Size by mutableStateOf(size)

    var enabledSizes: List<OudsBadge.Size> = getEnabledSizes(type)
        private set(value) {
            field = value
            if (size !in enabledSizes) {
                size = enabledSizes.first()
            }
        }

    var status: OudsBadge.Status by mutableStateOf(status)

    var count: Int by mutableIntStateOf(count)

    val countTextFieldEnabled: Boolean
        get() = type == Type.Count

    private fun getEnabledSizes(type: Type): List<OudsBadge.Size> {
        return when (type) {
            Type.Standard -> OudsBadge.Size.entries
            Type.Count,
            Type.Icon -> listOf(OudsBadge.Size.Medium, OudsBadge.Size.Large)
        }
    }

    enum class Type(@StringRes val labelRes: Int) {
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_count_label),
        Icon(R.string.app_components_badge_iconType_label)
    }
}
