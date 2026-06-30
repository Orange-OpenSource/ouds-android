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
import com.orange.ouds.core.component.OudsAlertIcon
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsAlertMessageActionLink
import com.orange.ouds.core.component.OudsAlertMessageActionLinkPosition
import com.orange.ouds.core.component.OudsAlertMessageStatus
import com.orange.ouds.core.component.common.text.OudsLinkAnnotation
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageBulletListLabel
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedAlertMessageDescription
import com.orange.ouds.core.component.common.text.withLink
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsAlertMessageSample() {
    OudsAlertMessage(
        label = "New features available",
        description = "We've added exciting new features to improve your experience.",
        status = OudsAlertMessageStatus.Accent(OudsAlertIcon(imageVector = Icons.Filled.FavoriteBorder)),
        onClose = { /* Close the alert message */ },
        actionLink = OudsAlertMessageActionLink(label = "Learn more", onClick = { /* Navigate to features page */ }),
        bulletList = listOf(
            "Enhanced security with biometric authentication",
            "Improved performance and faster load times",
            "New customization options for your dashboard"
        )
    )
}

@Composable
internal fun OudsAlertMessageFunctionalWithTopEndActionLinkSample() {
    OudsAlertMessage(
        label = "Account successfully verified",
        description = "Your account has been verified and is now fully activated.",
        status = OudsAlertMessageStatus.Positive,
        onClose = { /* Close the alert message */ },
        actionLink = OudsAlertMessageActionLink(label = "Details", onClick = { /* Navigate to account details */ }, position = OudsAlertMessageActionLinkPosition.TopEnd),
        bulletList = listOf(
            "All features are now unlocked",
            "You can start using premium services",
            "Your data is fully synchronized"
        )
    )
}

@Composable
internal fun OudsAlertMessageWithAnnotatedTextSample() {
    val description = buildOudsAnnotatedAlertMessageDescription {
        withStrong { append("Important:") }
        append(" Please read the ")
        withLink(OudsLinkAnnotation.Url("https://example.com")) {
            append("terms and conditions")
        }
        append(" carefully before proceeding.")
    }

    val bulletList = listOf(
        buildOudsAnnotatedAlertMessageBulletListLabel {
            withStrong { append("Data security:") }
            append(" Your information will be encrypted and stored securely")
        },
        buildOudsAnnotatedAlertMessageBulletListLabel {
            withStrong { append("Privacy policy:") }
            append(" We respect your privacy and will not share your data")
        },
        buildOudsAnnotatedAlertMessageBulletListLabel {
            append("For more information, visit our ")
            withLink(OudsLinkAnnotation.Url("https://help.example.com")) {
                append("help center")
            }
        }
    )

    OudsAlertMessage(
        label = "Before you continue",
        description = description,
        status = OudsAlertMessageStatus.Accent(OudsAlertIcon(imageVector = Icons.Filled.FavoriteBorder)),
        onClose = { /* Close the alert message */ },
        bulletList = bulletList
    )
}

@Composable
internal fun OudsAlertMessageNonFunctionalWithUntintedIconSample() {
    OudsAlertMessage(
        label = "New features available",
        description = "We've added exciting new features to improve your experience.",
        status = OudsAlertMessageStatus.Accent(OudsAlertIcon(painter = rememberRainbowHeartPainter(), tinted = false)),
        onClose = { /* Close the alert message */ }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageSample() = OudsPreview {
    OudsAlertMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageFunctionalWithTopEndActionLinkSample() = OudsPreview {
    OudsAlertMessageFunctionalWithTopEndActionLinkSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageWithAnnotatedTextSample() = OudsPreview {
    OudsAlertMessageWithAnnotatedTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsAlertMessageNonFunctionalWithUntintedIconSample() = OudsPreview {
    OudsAlertMessageNonFunctionalWithUntintedIconSample()
}