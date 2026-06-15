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

package com.orange.ouds.core.component.samples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsControlItemIcon
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsSwitchItemSample() {
    var checked by remember { mutableStateOf(true) }

    OudsSwitchItem(
        checked = checked,
        label = "Notifications",
        description = "Display app notifications in the notification center",
        icon = OudsControlItemIcon(imageVector = Icons.Filled.FavoriteBorder),
        onCheckedChange = { value -> checked = value }
    )
}

@Composable
internal fun OudsSwitchItemWithAnnotatedErrorMessageSample() {
    var checked by remember { mutableStateOf(false) }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("You ")
            withStrong { append("must") }
            append(" enable notifications to continue")
        }
    )

    OudsSwitchItem(
        checked = checked,
        label = "Notifications",
        description = "Display app notifications in the notification center",
        onCheckedChange = { value -> checked = value },
        error = error
    )
}

@Composable
internal fun OudsSwitchItemWithUntintedIconSample() {
    var checked by remember { mutableStateOf(true) }

    OudsSwitchItem(
        checked = checked,
        label = "Notifications",
        description = "Display app notifications in the notification center",
        icon = OudsControlItemIcon(painter = rememberRainbowHeartPainter(), tinted = false),
        onCheckedChange = { value -> checked = value }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsSwitchItemSample() = OudsPreview {
    OudsSwitchItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSwitchItemWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsSwitchItemWithAnnotatedErrorMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSwitchItemWithUntintedIconSample() = OudsPreview {
    OudsSwitchItemWithUntintedIconSample()
}
