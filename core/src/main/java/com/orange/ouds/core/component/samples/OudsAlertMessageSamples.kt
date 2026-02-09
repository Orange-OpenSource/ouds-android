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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsAlertMessageIcon
import com.orange.ouds.core.component.OudsAlertMessageLink
import com.orange.ouds.core.component.OudsAlertMessageLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageStatus
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsAlertMessageSample() {
    OudsAlertMessage(
        label = "Label",
        description = "Description of the alert message.",
        status = OudsAlertMessageStatus.Accent(OudsAlertMessageIcon(imageVector = Icons.Filled.FavoriteBorder)),
        onClose = { /* Close the alert message */ },
        link = OudsAlertMessageLink(label = "Action", onClick = { /* Do something */ }),
        bulletList = listOf(
            "Bullet 1",
            "Bullet 2",
            "Bullet 3"
        )
    )
}

@Composable
internal fun OudsAlertMessageFunctionalWithTopEndLinkSample() {
    OudsAlertMessage(
        label = "Label",
        description = "Description of the alert message.",
        status = OudsAlertMessageStatus.Positive(),
        onClose = { /* Close the alert message */ },
        link = OudsAlertMessageLink(label = "Action", onClick = { /* Do something */ }, position = OudsAlertMessageLinkPosition.TopEnd),
        bulletList = listOf(
            "Bullet 1",
            "Bullet 2",
            "Bullet 3"
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageSample() = OudsPreview {
    OudsAlertMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageFunctionalWithTopEndLinkSample() = OudsPreview {
    OudsAlertMessageFunctionalWithTopEndLinkSample()
}