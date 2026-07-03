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
import com.orange.ouds.core.component.OudsButtonIcon
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.OudsSmallButton
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsSmallButtonTextOnlySample() {
    OudsSmallButton(
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsSmallButtonTextOnlyOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsSmallButton(
            label = "Label",
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsSmallButtonIconOnlySample() {
    OudsSmallButton(
        icon = OudsButtonIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsSmallButtonIconOnlyOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsSmallButton(
            icon = OudsButtonIcon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "Content description"
            ),
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsSmallButtonTextAndIconSample() {
    OudsSmallButton(
        icon = OudsButtonIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        ),
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsSmallButtonTextAndIconOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsSmallButton(
            icon = OudsButtonIcon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = ""
            ),
            label = "Label",
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsSmallButtonIconOnlyWithUntintedIconSample() {
    OudsSmallButton(
        icon = OudsButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Content description",
            tinted = false
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsSmallButtonTextAndIconWithUntintedIconSample() {
    OudsSmallButton(
        icon = OudsButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "",
            tinted = false
        ),
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonTextOnlySample() = OudsPreview {
    OudsSmallButtonTextOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonTextOnlyOnColoredBackgroundSample() = OudsPreview {
    OudsSmallButtonTextOnlyOnColoredBackgroundSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonIconOnlySample() = OudsPreview {
    OudsSmallButtonIconOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonIconOnlyOnColoredBackgroundSample() = OudsPreview {
    OudsSmallButtonIconOnlyOnColoredBackgroundSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonTextAndIconSample() = OudsPreview {
    OudsSmallButtonTextAndIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonTextAndIconOnColoredBackgroundSample() = OudsPreview {
    OudsSmallButtonTextAndIconOnColoredBackgroundSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonIconOnlyWithUntintedIconSample() = OudsPreview {
    OudsSmallButtonIconOnlyWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallButtonTextAndIconWithUntintedIconSample() = OudsPreview {
    OudsSmallButtonTextAndIconWithUntintedIconSample()
}
