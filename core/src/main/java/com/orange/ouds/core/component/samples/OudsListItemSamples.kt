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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsListItem
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
internal fun OudsStaticListItemSample() {
    Column {
        OudsListItem(
            label = "Name",
            description = "John Doe",
            leading = OudsListItemLeading.Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = "Person icon"
            )
        )
        OudsListItem(
            label = "Email",
            description = "john.doe@example.com",
            leading = OudsListItemLeading.Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email icon"
            )
        )
        OudsListItem(
            label = "Phone",
            description = "+33 6 12 34 56 78",
            leading = OudsListItemLeading.Icon(
                imageVector = Icons.Outlined.Phone,
                contentDescription = "Phone icon"
            )
        )
    }
}

@Composable
internal fun OudsNavigationListItemSample() {
    Column {
        OudsListItem(
            label = "Back to previous screen",
            onClick = { /* Navigate back */ },
            indicator = OudsListItemIndicator.Previous
        )
        OudsListItem(
            label = "Go to next screen",
            onClick = { /* Navigate forward */ },
            indicator = OudsListItemIndicator.Next
        )
        OudsListItem(
            label = "Open external link",
            onClick = { /* Open browser */ },
            indicator = OudsListItemIndicator.External
        )
    }
}

@Composable
internal fun OudsListItemWithAllElementsSample() {
    OudsListItem(
        overline = "Overline text",
        label = "Main label",
        extraLabel = "Extra label",
        description = "This is a description that provides additional context.",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Favorite icon"
        ),
        trailing = OudsListItemTrailing.Text(label = "99+", style = OudsListItemTextStyle.Label),
        helperText = "Helper text appears below the item",
        boldLabel = true,
        background = true,
        divider = true
    )
}

@Composable
internal fun OudsListItemWithLeadingImageAndTrailingTagSample() {
    OudsListItem(
        label = "Product name",
        description = "Product description with details",
        leading = OudsListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Product image",
            size = OudsListItemImageSize.Large,
            ratio = OudsListItemImageRatio.Square
        ),
        trailing = OudsListItemTrailing.Tag(status = OudsTagStatus.Positive(asset = null), label = "Available", size = OudsTagSize.Small)
    )
}

@Composable
internal fun OudsListItemWithLeadingIconAndTrailingBadgeSample() {
    OudsListItem(
        label = "Messages",
        description = "Unread notifications",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Email,
            contentDescription = "Messages icon"
        ),
        trailing = OudsListItemTrailing.Badge(count = 5)
    )
}

@Composable
internal fun OudsListItemWithUntintedIconSample() {
    OudsListItem(
        label = "Favorites",
        description = "View your favorite items",
        leading = OudsListItemLeading.Icon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Favorites icon",
            tinted = false
        )
    )
}

@Composable
internal fun OudsStaticListItemWithCustomContentsSample() {
    OudsListItem(
        label = "John Doe",
        description = "john.doe@example.com",
        leading = OudsListItemLeading.Content {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(OudsTheme.colorScheme.surface.status.positive.muted, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "User",
                    tint = OudsTheme.colorScheme.content.onStatus.positive.muted
                )
            }
        },
        trailing = OudsListItemTrailing.Content {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Verified",
                tint = OudsTheme.colorScheme.content.status.positive
            )
        },
        belowTextContent = {
            Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.twoExtraSmall)) {
                OudsTag(label = "Admin", status = OudsTagStatus.Positive(), size = OudsTagSize.Small)
                OudsTag(label = "Active", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
        },
        bottomContent = {
            Text(
                text = "Last login: 2 hours ago",
                style = OudsTheme.typography.label.small.default,
                color = OudsTheme.colorScheme.content.muted
            )
        }
    )
}

@Composable
internal fun OudsNavigationListItemWithCustomContentsSample() {
    OudsListItem(
        label = "Paris Office",
        description = "123 Champs-Élysées",
        onClick = { /* Navigate */ },
        indicator = OudsListItemIndicator.Next,
        leading = OudsListItemLeading.Content {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(OudsTheme.colorScheme.surface.status.info.muted, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Location",
                    tint = OudsTheme.colorScheme.content.onStatus.info.muted
                )
            }
        },
        trailing = OudsListItemTrailing.Content {
            Text(text = "2.5 km", style = OudsTheme.typography.label.medium.strong)
        },
        belowTextContent = {
            Row(horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.twoExtraSmall)) {
                OudsTag(label = "WiFi", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
                OudsTag(label = "Parking", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
        },
        bottomContent = {
            Text(
                text = "Open Mon-Fri: 9:00 - 18:00",
                style = OudsTheme.typography.label.small.default,
                color = OudsTheme.colorScheme.content.muted
            )
        }
    )
}

@Composable
internal fun OudsStaticListItemWithLabelContentSample() {
    OudsListItem(
        labelContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
            ) {
                Text(text = "Premium Account", style = OudsTheme.typography.label.large.strong, color = OudsTheme.colorScheme.content.default)
                OudsTag(label = "VIP", status = OudsTagStatus.Warning(), size = OudsTagSize.Small)
            }
        },
        description = "Full access to all features",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Star,
            contentDescription = "Premium icon"
        )
    )
}

@Composable
internal fun OudsNavigationListItemWithLabelContentSample() {
    OudsListItem(
        labelContent = {
            Column(verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.threeExtraSmall)) {
                Text(text = "Special Offer", style = OudsTheme.typography.label.large.strong, color = OudsTheme.colorScheme.content.default)
                Text(
                    text = "Save 30% this month",
                    style = OudsTheme.typography.label.medium.strong,
                    color = OudsTheme.colorScheme.content.status.positive
                )
            }
        },
        onClick = { /* View offer */ },
        indicator = OudsListItemIndicator.Next,
        description = "Limited time promotion",
        leading = OudsListItemLeading.Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "Offer icon"
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticListItemSample() = OudsPreview {
    OudsStaticListItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationListItemSample() = OudsPreview {
    OudsNavigationListItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithAllElementsSample() = OudsPreview {
    OudsListItemWithAllElementsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithLeadingImageAndTrailingTagSample() = OudsPreview {
    OudsListItemWithLeadingImageAndTrailingTagSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithLeadingIconAndTrailingBadgeSample() = OudsPreview {
    OudsListItemWithLeadingIconAndTrailingBadgeSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithUntintedIconSample() = OudsPreview {
    OudsListItemWithUntintedIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticListItemWithCustomContentsSample() = OudsPreview {
    OudsStaticListItemWithCustomContentsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationListItemWithCustomContentsSample() = OudsPreview {
    OudsNavigationListItemWithCustomContentsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticListItemWithLabelContentSample() = OudsPreview {
    OudsStaticListItemWithLabelContentSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationListItemWithLabelContentSample() = OudsPreview {
    OudsNavigationListItemWithLabelContentSample()
}