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

package com.orange.ouds.app.ui.components.alert

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsAlertMessageActionLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageDefaults
import com.orange.ouds.core.component.OudsAlertMessageStatus
import kotlin.reflect.full.createInstance

@Composable
fun rememberAlertMessageDemoState(
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    icon: AlertMessageDemoState.Icon = AlertMessageDemoState.Icon.Tinted,
    hasCloseButton: Boolean = false,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    description: String? = null,
    actionLink: String? = null,
    actionLinkPosition: OudsAlertMessageActionLinkPosition = OudsAlertMessageDefaults.ActionLinkPosition,
    bulletList: Map<Int, String>? = null,
) = rememberSaveable(
    status,
    icon,
    hasCloseButton,
    label,
    description,
    actionLink,
    actionLinkPosition,
    bulletList,
    saver = AlertMessageDemoState.Saver
) {
    AlertMessageDemoState(status, icon, hasCloseButton, label, description, actionLink, actionLinkPosition, bulletList)
}

class AlertMessageDemoState(
    status: OudsAlertMessageStatus,
    icon: Icon,
    hasCloseButton: Boolean,
    label: String,
    description: String?,
    actionLink: String?,
    actionLinkPosition: OudsAlertMessageActionLinkPosition,
    bulletList: Map<Int, String>?
) {

    @Suppress("UNCHECKED_CAST")
    companion object {
        const val MaxBulletCount = 3

        private val FunctionalStatuses = listOf(
            OudsAlertMessageStatus.Info,
            OudsAlertMessageStatus.Negative,
            OudsAlertMessageStatus.Positive,
            OudsAlertMessageStatus.Warning
        )

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        status::class.java.name,
                        icon,
                        hasCloseButton,
                        label,
                        description,
                        actionLink,
                        actionLinkPosition,
                        bulletList
                    )
                }
            },
            restore = { list: List<Any?> ->
                val statusClassName = list[0] as String
                val status = with(Class.forName(statusClassName).kotlin) { objectInstance ?: createInstance() } as OudsAlertMessageStatus

                AlertMessageDemoState(
                    status,
                    list[1] as Icon,
                    list[2] as Boolean,
                    list[3] as String,
                    list[4] as String?,
                    list[5] as String?,
                    list[6] as OudsAlertMessageActionLinkPosition,
                    list[7] as Map<Int, String>?
                )
            }
        )
    }

    private var _status: OudsAlertMessageStatus by mutableStateOf(status)
    var status: OudsAlertMessageStatus
        get() = _status
        set(value) {
            _status = value
            if (icon !in enabledIcons) {
                icon = Icon.Tinted
            }
        }

    var icon: Icon by mutableStateOf(icon)

    var hasCloseButton: Boolean by mutableStateOf(hasCloseButton)

    var label: String by mutableStateOf(label)

    var description: String? by mutableStateOf(description)

    var actionLink: String? by mutableStateOf(actionLink)

    var actionLinkPosition: OudsAlertMessageActionLinkPosition by mutableStateOf(actionLinkPosition)

    val actionLinkPositionChipsEnabled: Boolean
        get() = !actionLink.isNullOrEmpty()

    var bulletList: Map<Int, String>? by mutableStateOf(bulletList)

    val enabledIcons: List<Icon>
        get() = if (status !in FunctionalStatuses) Icon.entries else listOf(Icon.Tinted)

    enum class Icon(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_tech),
        Tinted(R.string.app_components_common_tintedIcon_tech),
        Untinted(R.string.app_components_common_untintedIcon_tech)
    }
}