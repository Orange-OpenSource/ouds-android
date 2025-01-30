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
import com.orange.ouds.core.component.OudsLink

@Composable
internal fun OudsLinkSample() {
    OudsLink(
        text = "Link",
        icon = OudsLink.Icon(painterResource(id = R.drawable.star_on)),
        onClick = { /* Do something! */ },
    )
}

@Composable
internal fun OudsLinkWithArrowSample() {
    OudsLink(
        text = "Link",
        arrow = OudsLink.Arrow.Next,
        onClick = { /* Do something! */ },
    )
}
