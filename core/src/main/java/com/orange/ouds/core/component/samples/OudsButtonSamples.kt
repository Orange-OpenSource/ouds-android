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
import androidx.compose.ui.res.painterResource
import com.orange.ouds.core.component.button.OudsButton

@Composable
fun OudsButtonWithTextSample() {
    OudsButton(
        text = "Text",
        onClick = { /* Do something! */ }
    )
}

@Composable
fun OudsButtonWithIconSample() {
    OudsButton(
        icon = OudsButton.Icon(
            painterResource(id = android.R.drawable.star_on),
            "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
fun OudsButtonWithIconAndTextSample() {
    OudsButton(
        icon = OudsButton.Icon(
            painterResource(id = android.R.drawable.star_on),
            "Content description"
        ),
        text = "Text",
        onClick = { /* Do something! */ }
    )
}
