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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsBasicSuggestionChip
import com.orange.ouds.core.component.OudsChipIcon
import com.orange.ouds.core.component.OudsSuggestionChip
import com.orange.ouds.core.theme.OudsTheme
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

@Composable
internal fun OudsBasicSuggestionChipTextOnlySample() {
    OudsBasicSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label"
    ) {
        Column {
            Label()
            Text(
                text = "Extra label",
                color = contentColor,
                style = OudsTheme.typography.label.small.moderate
            )
        }
    }
}

@Composable
internal fun OudsBasicSuggestionChipIconOnlySample() {
    OudsBasicSuggestionChip(
        onClick = { /* Do something! */ },
        icon = OudsChipIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        )
    ) {
        Icon(modifier = Modifier.size(OudsTheme.sizes.icon.withLabel.medium.sizeMedium))
    }
}

@Composable
internal fun OudsBasicSuggestionChipTextAndIconSample() {
    OudsBasicSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label",
        icon = OudsChipIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.components.chip.space.columnGap.icon, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon()
            Column {
                Label()
                Text(
                    text = "Extra label",
                    color = contentColor,
                    style = OudsTheme.typography.label.small.moderate
                )
            }
        }
    }
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

@Preview
@Composable
private fun PreviewOudsBasicSuggestionChipTextOnlySample() = OudsPreview {
    OudsBasicSuggestionChipTextOnlySample()
}

@Preview
@Composable
private fun PreviewOudsBasicSuggestionChipIconOnlySample() = OudsPreview {
    OudsBasicSuggestionChipIconOnlySample()
}

@Preview
@Composable
private fun PreviewOudsBasicSuggestionChipTextAndIconSample() = OudsPreview {
    OudsBasicSuggestionChipTextAndIconSample()
}
