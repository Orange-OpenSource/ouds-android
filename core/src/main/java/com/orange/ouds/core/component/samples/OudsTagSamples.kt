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
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsTagSample() {
    OudsTag(
        label = "Tag",
        status = OudsTag.Status.Positive,
        size = OudsTag.Size.Small
    )
}

@Composable
internal fun OudsTagWithBulletSample() {
    OudsTag(
        label = "Tag",
        icon = OudsTag.Icon.Bullet,
        status = OudsTag.Status.Positive
    )
}

@Composable
internal fun OudsTagWithIconSample() {
    OudsTag(
        label = "Tag",
        icon = OudsTag.Icon(imageVector = Icons.Filled.FavoriteBorder),
        status = OudsTag.Status.Positive
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsTagSample() = OudsPreview {
    OudsTagSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTagWithBulletSample() = OudsPreview {
    OudsTagWithBulletSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsTagWithIconSample() = OudsPreview {
    OudsTagWithIconSample()
}
