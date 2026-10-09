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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsSmallListItem
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
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
internal fun OudsSmallListItemWithAllElementsSample() {
    OudsSmallListItem(
        label = "Main label",
        description = "This is a description that provides additional context.",
        leading = OudsSmallListItemLeading.Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Favorite icon"
        ),
        trailing = OudsSmallListItemTrailing.Text(label = "99+", style = OudsListItemTextStyle.Label),
        helperText = "Helper text appears below the item",
        boldLabel = true,
        background = true,
        divider = true
    )
}

@Composable
internal fun OudsSmallListItemWithLeadingImageAndTrailingTagSample() {
    OudsSmallListItem(
        label = "Product name",
        description = "Product description",
        leading = OudsSmallListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Product image",
            ratio = OudsListItemImageRatio.Square
        ),
        trailing = OudsSmallListItemTrailing.Tag(status = OudsTagStatus.Info(asset = null), label = "New", size = OudsTagSize.Small)
    )
}

@Composable
internal fun OudsSmallListItemWithLeadingIconAndTrailingBadgeSample() {
    OudsSmallListItem(
        label = "Messages",
        description = "Unread notifications",
        leading = OudsSmallListItemLeading.Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "Notifications icon"
        ),
        trailing = OudsSmallListItemTrailing.Badge(count = 5)
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
private fun PreviewOudsSmallListItemWithAllElementsSample() = OudsPreview {
    OudsSmallListItemWithAllElementsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithLeadingImageAndTrailingTagSample() = OudsPreview {
    OudsSmallListItemWithLeadingImageAndTrailingTagSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithLeadingIconAndTrailingBadgeSample() = OudsPreview {
    OudsSmallListItemWithLeadingIconAndTrailingBadgeSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallListItemWithUntintedIconSample() = OudsPreview {
    OudsSmallListItemWithUntintedIconSample()
}
