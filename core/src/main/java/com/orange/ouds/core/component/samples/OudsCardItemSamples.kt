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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsCardItem
import com.orange.ouds.core.component.OudsInlineAlert
import com.orange.ouds.core.component.OudsInlineAlertStatus
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.theme.OudsTheme
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

@Composable
internal fun OudsStaticCardItemWithCustomContentsSample() {
    val contentColor = OudsTheme.colorScheme.content.default
    OudsCardItem(
        label = "Hotel Paradise",
        description = "Luxury beachfront resort",
        decoration = OudsListItemDecoration.Background(divider = true),
        leading = OudsListItemLeading.Content {
            Column(
                modifier = Modifier
                    .size(56.dp)
                    .background(OudsTheme.colorScheme.surface.status.info.muted, RoundedCornerShape(8.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Rating",
                    tint = OudsTheme.colorScheme.content.onStatus.warning.muted
                )
                Text(text = "4.8", style = OudsTheme.typography.label.medium.strong, color = contentColor)
            }
        },
        trailing = OudsListItemTrailing.Content {
            Text(text = "€299", style = OudsTheme.typography.label.large.strong, color = contentColor)
        },
        belowTextContent = {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                OudsTag(label = "Pool", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
                OudsTag(label = "WiFi", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
        },
        bottomContent = {
            Text(
                text = "Only 2 rooms available",
                style = OudsTheme.typography.label.small.strong,
                color = OudsTheme.colorScheme.content.status.warning
            )
        }
    )
}

@Composable
internal fun OudsNavigationCardItemWithCustomContentsSample() {
    val contentColor = OudsTheme.colorScheme.content.default
    OudsCardItem(
        label = "City Tour Package",
        description = "Guided tour with transport",
        onClick = { /* Book tour */ },
        indicator = OudsListItemIndicator.Next,
        decoration = OudsListItemDecoration.Outlined,
        leading = OudsListItemLeading.Content {
            Column(
                modifier = Modifier
                    .size(56.dp)
                    .background(color = OudsTheme.colorScheme.surface.status.info.muted, shape = RoundedCornerShape(8.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Duration",
                    tint = OudsTheme.colorScheme.content.onStatus.info.muted
                )
                Text(text = "3h", style = OudsTheme.typography.label.small.strong, color = contentColor)
            }
        },
        trailing = OudsListItemTrailing.Content {
            Column(horizontalAlignment = Alignment.End) {
                Icon(imageVector = Icons.Outlined.Person, contentDescription = "People", modifier = Modifier.size(20.dp), tint = contentColor)
                Text(text = "8/15", style = OudsTheme.typography.label.small.strong, color = contentColor)
            }
        },
        belowTextContent = {
            Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.twoExtraSmall)) {
                OudsTag(label = "EN", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
                OudsTag(label = "FR", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
        },
        bottomContent = {
            OudsInlineAlert(
                label = "Next departure in 30 minutes",
                status = OudsInlineAlertStatus.Info
            )
        }
    )
}

@Composable
internal fun OudsStaticCardItemWithLabelContentSample() {
    val contentColor = OudsTheme.colorScheme.content.default
    OudsCardItem(
        labelContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
            ) {
                Text(text = "Grand Hotel", style = OudsTheme.typography.label.large.strong, color = contentColor)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.threeExtraSmall)
                ) {
                    Icon(imageVector = Icons.Outlined.Star, contentDescription = null, modifier = Modifier.size(14.dp), tint = contentColor)
                    Text(text = "4.9", style = OudsTheme.typography.label.small.strong, color = contentColor)
                }
            }
        },
        description = "Historic 5-star hotel in city center",
        decoration = OudsListItemDecoration.Background(divider = true),
        leading = OudsListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Hotel image",
            size = OudsListItemImageSize.Large,
            ratio = OudsListItemImageRatio.Square
        )
    )
}

@Composable
internal fun OudsNavigationCardItemWithLabelContentSample() {
    val contentColor = OudsTheme.colorScheme.content.default
    OudsCardItem(
        labelContent = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Amsterdam", style = OudsTheme.typography.label.large.strong, color = contentColor)
                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "from", style = OudsTheme.typography.label.small.default, color = contentColor)
                    Text(text = "€89", style = OudsTheme.typography.label.large.strong, color = contentColor)
                }
            }
        },
        onClick = { /* View flights */ },
        indicator = OudsListItemIndicator.Next,
        decoration = OudsListItemDecoration.Outlined,
        description = "Direct flight • 1h 20min"
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

@PreviewLightDark
@Composable
private fun PreviewOudsStaticCardItemWithCustomContentsSample() = OudsPreview {
    OudsStaticCardItemWithCustomContentsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationCardItemWithCustomContentsSample() = OudsPreview {
    OudsNavigationCardItemWithCustomContentsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticCardItemWithLabelContentSample() = OudsPreview {
    OudsStaticCardItemWithLabelContentSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationCardItemWithLabelContentSample() = OudsPreview {
    OudsNavigationCardItemWithLabelContentSample()
}
