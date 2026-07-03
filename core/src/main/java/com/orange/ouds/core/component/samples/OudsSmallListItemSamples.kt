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

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsListItemImageFormat
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsSmallListItem
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsStaticSmallListItemSample() {
    Column {
        OudsSmallListItem(
            label = "Notifications",
            description = "Push notifications enabled",
            leading = OudsSmallListItemLeading.Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications icon"
            )
        )
        OudsSmallListItem(
            label = "Share",
            description = "Share app with friends",
            leading = OudsSmallListItemLeading.Icon(
                imageVector = Icons.Outlined.Share,
                contentDescription = "Share icon"
            )
        )
        OudsSmallListItem(
            label = "Settings",
            description = "App preferences",
            leading = OudsSmallListItemLeading.Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings icon"
            )
        )
    }
}

@Composable
internal fun OudsNavigationSmallListItemSample() {
    val options = listOf("General", "Privacy", "Notifications", "About")
    var selectedOption by rememberSaveable { mutableStateOf(options.first()) }

    Column {
        options.forEach { option ->
            OudsSmallListItem(
                label = option,
                onClick = { selectedOption = option },
                indicator = OudsListItemIndicator.Next,
                background = option == selectedOption
            )
        }
    }
}

@Composable
internal fun OudsSmallListItemWithIndicatorsSample() {
    Column {
        OudsSmallListItem(
            label = "Go back",
            onClick = { /* Navigate back */ },
            indicator = OudsListItemIndicator.Previous
        )
        OudsSmallListItem(
            label = "Continue",
            onClick = { /* Navigate forward */ },
            indicator = OudsListItemIndicator.Next
        )
        OudsSmallListItem(
            label = "View on web",
            onClick = { /* Open browser */ },
            indicator = OudsListItemIndicator.External
        )
    }
}

@Composable
internal fun OudsSmallListItemWithImageSample() {
    OudsSmallListItem(
        label = "Compact view",
        description = "Quick access to content",
        leading = OudsSmallListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Content image",
            format = OudsListItemImageFormat.Square
        ),
        trailing = OudsSmallListItemTrailing.Text(label = "New", style = OudsListItemTextStyle.LabelStrong)
    )
}

@Composable
internal fun OudsSmallListItemWithUntintedIconSample() {
    OudsSmallListItem(
        label = "Premium features",
        description = "Unlock exclusive content",
        leading = OudsSmallListItemLeading.Icon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Premium icon",
            tinted = false
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticSmallListItemSample() = OudsPreview {
    OudsStaticSmallListItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationSmallListItemSample() = OudsPreview {
    OudsNavigationSmallListItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithIndicatorsSample() = OudsPreview {
    OudsSmallListItemWithIndicatorsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithImageSample() = OudsPreview {
    OudsSmallListItemWithImageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithUntintedIconSample() = OudsPreview {
    OudsSmallListItemWithUntintedIconSample()
}
