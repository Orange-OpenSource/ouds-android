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
import com.orange.ouds.core.component.OudsTagIcon
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsTagSample() {
    OudsTag(
        label = "Tag",
        status = OudsTagStatus.Positive,
        size = OudsTagSize.Small
    )
}

@Composable
internal fun OudsTagWithBulletSample() {
    OudsTag(
        label = "Tag",
        icon = OudsTagIcon.Bullet,
        status = OudsTagStatus.Positive
    )
}

@Composable
internal fun OudsTagWithIconSample() {
    OudsTag(
        label = "Tag",
        icon = OudsTagIcon(imageVector = Icons.Filled.FavoriteBorder),
        status = OudsTagStatus.Positive
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
