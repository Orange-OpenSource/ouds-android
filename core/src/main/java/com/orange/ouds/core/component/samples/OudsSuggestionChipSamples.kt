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
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ouds.core.component.OudsChip
import com.orange.ouds.core.component.OudsSuggestionChip
import com.orange.ouds.core.utilities.OudsPreview

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
        icon = OudsChip.Icon(
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
        icon = OudsChip.Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        )
    )
}

@Preview
@Composable
private fun PreviewOudsSuggestionChipTextOnlySample() = OudsPreview {
    OudsSuggestionChipTextOnlySample()
}

@Preview
@Composable
private fun PreviewOudsSuggestionChipIconOnlySample() = OudsPreview {
    OudsSuggestionChipIconOnlySample()
}

@Preview
@Composable
private fun PreviewOudsSuggestionChipTextAndIconSample() = OudsPreview {
    OudsSuggestionChipTextAndIconSample()
}
