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

import android.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsColoredBox

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
            painterResource(id = R.drawable.star_on),
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
                painterResource(id = R.drawable.star_on),
                "Content description"
            ),
            onClick = { /* Do something! */ }
        )
    }
}

@Composable
internal fun OudsButtonIconAndTextSample() {
    OudsButton(
        icon = OudsButton.Icon(
            painterResource(id = R.drawable.star_on),
            "Content description"
        ),
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsButtonIconAndTextOnColoredBackgroundSample() {
    OudsColoredBox(color = OudsColoredBox.Color.StatusInfoEmphasized) {
        // The colors of this button are automatically adjusted to maximize the contrast with the colored background.
        OudsButton(
            icon = OudsButton.Icon(
                painterResource(id = R.drawable.star_on),
                "Content description"
            ),
            label = "Label",
            onClick = { /* Do something! */ }
        )
    }
}
