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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsAlertMessageDefaults
import com.orange.ouds.core.component.OudsAlertMessageLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageStatus
import kotlin.reflect.full.createInstance

@Composable
fun rememberAlertMessageDemoState(
    status: OudsAlertMessageStatus = OudsAlertMessageDefaults.Status,
    hasStatusIcon: Boolean = true,
    hasCloseButton: Boolean = false,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    description: String? = null,
    actionLink: String? = null,
    actionLinkPosition: OudsAlertMessageLinkPosition = OudsAlertMessageDefaults.LinkPosition,
    bulletList: Map<Int, String>? = null
) = rememberSaveable(
    status,
    hasStatusIcon,
    hasCloseButton,
    label,
    description,
    actionLink,
    actionLinkPosition,
    bulletList,
    saver = AlertMessageDemoState.Saver
) {
    AlertMessageDemoState(status, hasStatusIcon, hasCloseButton, label, description, actionLink, actionLinkPosition, bulletList)
}

class AlertMessageDemoState(
    status: OudsAlertMessageStatus,
    hasStatusIcon: Boolean,
    hasCloseButton: Boolean,
    label: String,
    description: String?,
    actionLink: String?,
    actionLinkPosition: OudsAlertMessageLinkPosition,
    bulletList: Map<Int, String>?
) {

    @Suppress("UNCHECKED_CAST")
    companion object {
        const val MaxBulletCount = 3

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        status::class.java.name,
                        hasStatusIcon,
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
                val statusName = list[0] as String
                val status = Class.forName(statusName).kotlin.createInstance() as OudsAlertMessageStatus

                AlertMessageDemoState(
                    status,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as String,
                    list[4] as String?,
                    list[5] as String?,
                    list[6] as OudsAlertMessageLinkPosition,
                    list[7] as Map<Int, String>?
                )
            }
        )
    }

    var status: OudsAlertMessageStatus by mutableStateOf(status)

    var hasStatusIcon: Boolean by mutableStateOf(hasStatusIcon)

    var hasCloseButton: Boolean by mutableStateOf(hasCloseButton)

    var label: String by mutableStateOf(label)

    var description: String? by mutableStateOf(description)

    var actionLink: String? by mutableStateOf(actionLink)

    var actionLinkPosition: OudsAlertMessageLinkPosition by mutableStateOf(actionLinkPosition)

    val actionLinkPositionChipsEnabled: Boolean
        get() = !actionLink.isNullOrEmpty()

    var bulletList: Map<Int, String>? by mutableStateOf(bulletList)
}