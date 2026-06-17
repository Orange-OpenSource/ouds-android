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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsExtendedFloatingActionButton
import com.orange.ouds.core.component.OudsFloatingActionButton
import com.orange.ouds.core.component.OudsFloatingActionButtonAppearance
import com.orange.ouds.core.component.OudsFloatingActionButtonIcon
import com.orange.ouds.core.component.OudsLargeFloatingActionButton
import com.orange.ouds.core.component.OudsSmallFloatingActionButton
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsFloatingActionButtonSample() {
    OudsFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsSmallFloatingActionButtonSample() {
    OudsSmallFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsLargeFloatingActionButtonSample() {
    OudsLargeFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Content description"
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsExtendedFloatingActionButtonSampleWithLabelOnly() {
    OudsExtendedFloatingActionButton(
        label = "Label",
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsExtendedFloatingActionButtonSampleWithLabelAndIcon() {
    OudsExtendedFloatingActionButton(
        label = "Label",
        icon = OudsFloatingActionButtonIcon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = ""
        ),
        onClick = { /* Do something! */ }
    )
}

@Composable
internal fun OudsFloatingActionButtonWithUntintedIconSample() {
    OudsFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Content description",
            tinted = false
        ),
        onClick = { /* Do something! */ },
        appearance = OudsFloatingActionButtonAppearance.Strong
    )
}

@Composable
internal fun OudsSmallFloatingActionButtonWithUntintedIconSample() {
    OudsSmallFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Content description",
            tinted = false
        ),
        onClick = { /* Do something! */ },
        appearance = OudsFloatingActionButtonAppearance.Strong
    )
}

@Composable
internal fun OudsLargeFloatingActionButtonWithUntintedIconSample() {
    OudsLargeFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Content description",
            tinted = false
        ),
        onClick = { /* Do something! */ },
        appearance = OudsFloatingActionButtonAppearance.Strong
    )
}

@Composable
internal fun OudsExtendedFloatingActionButtonWithUntintedIconSample() {
    OudsExtendedFloatingActionButton(
        label = "Label",
        icon = OudsFloatingActionButtonIcon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "",
            tinted = false
        ),
        onClick = { /* Do something! */ },
        appearance = OudsFloatingActionButtonAppearance.Strong
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsFloatingActionButtonSample() = OudsPreview {
    OudsFloatingActionButtonSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallFloatingActionButtonSample() = OudsPreview {
    OudsSmallFloatingActionButtonSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLargeFloatingActionButtonSample() = OudsPreview {
    OudsLargeFloatingActionButtonSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsExtendedFloatingActionButtonSampleWithLabelOnly() = OudsPreview {
    OudsExtendedFloatingActionButtonSampleWithLabelOnly()
}

@PreviewLightDark
@Composable
private fun PreviewOudsExtendedFloatingActionButtonSampleWithLabelAndIcon() = OudsPreview {
    OudsExtendedFloatingActionButtonSampleWithLabelAndIcon()
}

@PreviewLightDark
@Composable
private fun PreviewOudsFloatingActionButtonWithUntintedIconSample() = OudsPreview {
    OudsFloatingActionButtonWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallFloatingActionButtonWithUntintedIconSample() = OudsPreview {
    OudsSmallFloatingActionButtonWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLargeFloatingActionButtonWithUntintedIconSample() = OudsPreview {
    OudsLargeFloatingActionButtonWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsExtendedFloatingActionButtonWithUntintedIconSample() = OudsPreview {
    OudsExtendedFloatingActionButtonWithUntintedIconSample()
}
