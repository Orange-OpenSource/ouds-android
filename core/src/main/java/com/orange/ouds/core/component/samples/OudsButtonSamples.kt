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
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsButtonTextOnlySample() {
    OudsButton(
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsButtonTextOnlyOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBox.Color.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsButton(
            label = "Label",
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsButtonIconOnlySample() {
    OudsButton(
        icon = OudsButton.Icon(
            Icons.Filled.FavoriteBorder,
            "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsButtonIconOnlyOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBox.Color.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsButton(
            icon = OudsButton.Icon(
                Icons.Filled.FavoriteBorder,
                "Content description"
            ),
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsButtonTextAndIconSample() {
    OudsButton(
        icon = OudsButton.Icon(
            Icons.Filled.FavoriteBorder,
            "Content description"
        ),
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsButtonTextAndIconOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBox.Color.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsButton(
            icon = OudsButton.Icon(
                Icons.Filled.FavoriteBorder,
                "Content description"
            ),
            label = "Label",
            onClick = { /* Do something! */ }
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonTextOnlySample() = OudsPreview {
    OudsButtonTextOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonTextOnlyOnColoredBackgroundSample() = OudsPreview {
    OudsButtonTextOnlyOnColoredBackgroundSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonIconOnlySample() = OudsPreview {
    OudsButtonIconOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonIconOnlyOnColoredBackgroundSample() = OudsPreview {
    OudsButtonIconOnlyOnColoredBackgroundSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonTextAndIconSample() = OudsPreview {
    OudsButtonTextAndIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsButtonTextAndIconOnColoredBackgroundSample() = OudsPreview {
    OudsButtonTextAndIconOnColoredBackgroundSample()
}
