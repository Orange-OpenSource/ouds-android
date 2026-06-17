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
import com.orange.ouds.core.component.OudsChipIcon
import com.orange.ouds.core.component.OudsSuggestionChip
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsSuggestionChipTextOnlySample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label"
    )
}

@Composable
internal fun OudsSuggestionChipIconOnlySample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        icon = OudsChipIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        )
    )
}

@Composable
internal fun OudsSuggestionChipTextAndIconSample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label",
        icon = OudsChipIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        )
    )
}

@Composable
internal fun OudsSuggestionChipIconOnlyWithUntintedIconSample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        icon = OudsChipIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Content description",
            tinted = false
        )
    )
}

@Composable
internal fun OudsSuggestionChipTextAndIconWithUntintedIconSample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label",
        icon = OudsChipIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "",
            tinted = false
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsSuggestionChipTextOnlySample() = OudsPreview {
    OudsSuggestionChipTextOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSuggestionChipIconOnlySample() = OudsPreview {
    OudsSuggestionChipIconOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSuggestionChipTextAndIconSample() = OudsPreview {
    OudsSuggestionChipTextAndIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSuggestionChipIconOnlyWithUntintedIconSample() = OudsPreview {
    OudsSuggestionChipIconOnlyWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSuggestionChipTextAndIconWithUntintedIconSample() = OudsPreview {
    OudsSuggestionChipTextAndIconWithUntintedIconSample()
}
