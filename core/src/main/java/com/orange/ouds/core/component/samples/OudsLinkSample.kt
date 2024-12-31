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
import com.orange.ouds.core.component.link.OudsLink

@Composable
fun OudsLinkSample() {
    OudsLink(
        text = "Link",
        icon = OudsLink.Icon(painterResource(id = android.R.drawable.star_on)),
        onClick = { /* Do something! */ },
    )
}

@Composable
fun OudsLinkWithArrowSample() {
    OudsLink(
        text = "Link",
        arrow = OudsLink.Arrow.Next,
        onClick = { /* Do something! */ },
    )
}
