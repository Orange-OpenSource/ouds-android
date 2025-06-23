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
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsLinkSample() {
    OudsLink(
        label = "Link",
        icon = OudsLink.Icon(imageVector = Icons.Filled.FavoriteBorder),
        onClick = { /* Do something! */ },
    )
}

@Composable
internal fun OudsLinkWithArrowSample() {
    OudsLink(
        label = "Link",
        arrow = OudsLink.Arrow.Next,
        onClick = { /* Do something! */ },
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinkSample() = OudsPreview {
    OudsLinkSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinkWithArrowSample() = OudsPreview {
    OudsLinkWithArrowSample()
}
