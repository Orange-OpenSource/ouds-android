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

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsAlertIcon
import com.orange.ouds.core.component.OudsInlineAlert
import com.orange.ouds.core.component.OudsInlineAlertStatus
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsInlineAlertNonFunctionalStatusSample() {
    OudsInlineAlert(
        label = "Label",
        status = OudsInlineAlertStatus.Accent(OudsAlertIcon.Default)
    )
}

@Composable
internal fun OudsInlineAlertFunctionalStatusSample() {
    OudsInlineAlert(
        label = "Label",
        status = OudsInlineAlertStatus.Positive
    )
}

@Composable
internal fun OudsInlineAlertNonFunctionalWithUntintedIconSample() {
    OudsInlineAlert(
        label = "Label",
        status = OudsInlineAlertStatus.Accent(OudsAlertIcon(painter = rememberRainbowHeartPainter(), tinted = false))
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsInlineAlertNonFunctionalStatusSample() = OudsPreview {
    OudsInlineAlertNonFunctionalStatusSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsInlineAlertFunctionalStatusSample() = OudsPreview {
    OudsInlineAlertFunctionalStatusSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsInlineAlertNonFunctionalWithUntintedIconSample() = OudsPreview {
    OudsInlineAlertNonFunctionalWithUntintedIconSample()
}