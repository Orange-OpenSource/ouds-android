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
import com.orange.ouds.core.component.OudsChip
import com.orange.ouds.core.component.OudsSuggestionChip

@Composable
internal fun OudsSuggestionChipTextOnlySample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Suggestion chip"
    )
}

@Composable
internal fun OudsSuggestionChipIconOnlySample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        icon = OudsChip.Icon(
            Icons.Filled.FavoriteBorder,
            "Content description"
        )
    )
}

@Composable
internal fun OudsSuggestionTextAndIconSample() {
    OudsSuggestionChip(
        onClick = { /* Do something! */ },
        label = "Label",
        icon = OudsChip.Icon(
            Icons.Filled.FavoriteBorder,
            "Content description"
        )
    )
}
