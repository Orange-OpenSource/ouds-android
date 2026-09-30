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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsCardItem
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsStaticCardItemSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsCardItem(
            label = "Hotel Paradise",
            description = "Luxury hotel in the city center",
            leading = OudsListItemLeading.Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "Location icon"
            ),
            trailing = OudsListItemTrailing.Text(label = "4.5★", style = OudsListItemTextStyle.LabelStrong)
        )
        OudsCardItem(
            label = "Beach Resort",
            description = "Relaxing resort by the sea",
            leading = OudsListItemLeading.Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Star icon"
            ),
            trailing = OudsListItemTrailing.Text(label = "4.8★", style = OudsListItemTextStyle.LabelStrong)
        )
    }
}

@Composable
internal fun OudsNavigationCardItemSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsCardItem(
            label = "Return to search",
            onClick = { /* Navigate back */ },
            indicator = OudsListItemIndicator.Previous,
            decoration = OudsListItemDecoration.Outlined
        )
        OudsCardItem(
            label = "View details",
            onClick = { /* Navigate forward */ },
            indicator = OudsListItemIndicator.Next,
            decoration = OudsListItemDecoration.Outlined
        )
        OudsCardItem(
            label = "Book on partner website",
            onClick = { /* Open browser */ },
            indicator = OudsListItemIndicator.External,
            decoration = OudsListItemDecoration.Outlined
        )
    }
}

@Composable
internal fun OudsCardItemWithAllElementsSample() {
    OudsCardItem(
        overline = "Featured destination",
        label = "Paris, France",
        extraLabel = "Special offer",
        description = "Discover the city of lights with exclusive deals.",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Favorite icon"
        ),
        trailing = OudsListItemTrailing.Text(label = "From €299", style = OudsListItemTextStyle.LabelStrong),
        helperText = "Limited time offer - Book now!",
        boldLabel = true,
        decoration = OudsListItemDecoration.Background(divider = true)
    )
}

@Composable
internal fun OudsCardItemWithLeadingImageAndTrailingTagSample() {
    OudsCardItem(
        label = "Premium Suite",
        description = "Spacious room with panoramic view",
        leading = OudsListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Suite image",
            size = OudsListItemImageSize.Large,
            ratio = OudsListItemImageRatio.Square
        ),
        trailing = OudsListItemTrailing.Tag(status = OudsTagStatus.Positive(), label = "Available", size = OudsTagSize.Small),
        decoration = OudsListItemDecoration.Outlined
    )
}

@Composable
internal fun OudsCardItemWithLeadingIconAndTrailingBadgeSample() {
    OudsCardItem(
        label = "Messages",
        description = "Unread notifications",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "Location icon"
        ),
        trailing = OudsListItemTrailing.Badge(count = 5),
        decoration = OudsListItemDecoration.Outlined
    )
}

@Composable
internal fun OudsCardItemWithUntintedIconSample() {
    OudsCardItem(
        label = "Wishlist",
        description = "Your favorite destinations",
        leading = OudsListItemLeading.Icon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Wishlist icon",
            tinted = false
        ),
        decoration = OudsListItemDecoration.Outlined
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticCardItemSample() = OudsPreview {
    OudsStaticCardItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationCardItemSample() = OudsPreview {
    OudsNavigationCardItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCardItemWithAllElementsSample() = OudsPreview {
    OudsCardItemWithAllElementsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCardItemWithLeadingImageAndTrailingTagSample() = OudsPreview {
    OudsCardItemWithLeadingImageAndTrailingTagSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCardItemWithLeadingIconAndTrailingBadgeSample() = OudsPreview {
    OudsCardItemWithLeadingIconAndTrailingBadgeSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCardItemWithUntintedIconSample() = OudsPreview {
    OudsCardItemWithUntintedIconSample()
}
