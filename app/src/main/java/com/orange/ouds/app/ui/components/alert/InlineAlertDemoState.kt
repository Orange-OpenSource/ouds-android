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
import com.orange.ouds.core.component.OudsAlertIcon
import com.orange.ouds.core.component.OudsInlineAlertDefaults
import com.orange.ouds.core.component.OudsInlineAlertStatus

@Composable
fun rememberInlineAlertDemoState(
    label: String = stringResource(id = R.string.app_components_common_label_label),
    status: OudsInlineAlertStatus = OudsInlineAlertDefaults.Status,
) = rememberSaveable(
    label,
    status,
    saver = InlineAlertDemoState.Saver
) {
    InlineAlertDemoState(label, status)
}

class InlineAlertDemoState(
    label: String,
    status: OudsInlineAlertStatus
) {

    companion object {
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        label,
                        status::class.java.name
                    )
                }
            },
            restore = { list: List<Any?> ->
                val statusClassName = list[1] as String
                val status = when (val kClass = Class.forName(statusClassName).kotlin) {
                    OudsInlineAlertStatus.Neutral::class -> OudsInlineAlertStatus.Neutral(OudsAlertIcon.Default)
                    OudsInlineAlertStatus.Accent::class -> OudsInlineAlertStatus.Accent(OudsAlertIcon.Default)
                    else -> kClass.objectInstance as OudsInlineAlertStatus
                }

                InlineAlertDemoState(
                    list[0] as String,
                    status
                )
            }
        )
    }

    var status: OudsInlineAlertStatus by mutableStateOf(status)

    var label: String by mutableStateOf(label)
}