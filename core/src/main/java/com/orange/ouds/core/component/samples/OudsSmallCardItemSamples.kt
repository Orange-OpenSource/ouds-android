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
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.component.OudsListItemImageFormat
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsSmallCardItem
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.core.utilities.CheckerboardPainter
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsStaticSmallCardItemSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsSmallCardItem(
            label = "Notifications",
            description = "3 new alerts",
            leading = OudsSmallListItemLeading.Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications icon"
            ),
            decoration = OudsListItemDecoration.Outlined
        )
        OudsSmallCardItem(
            label = "Important",
            description = "Requires attention",
            leading = OudsSmallListItemLeading.Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Info icon"
            ),
            decoration = OudsListItemDecoration.Outlined
        )
    }
}

@Composable
internal fun OudsNavigationSmallCardItemSample() {
    val features = listOf("Deals", "New Arrivals", "Popular")
    var selectedFeature by rememberSaveable { mutableStateOf(features.first()) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        features.forEach { feature ->
            OudsSmallCardItem(
                label = feature,
                onClick = { selectedFeature = feature },
                indicator = OudsListItemIndicator.Next,
                decoration = OudsListItemDecoration.Background(divider = false)
            )
        }
    }
}

@Composable
internal fun OudsSmallCardItemWithIndicatorsSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsSmallCardItem(
            label = "Previous",
            onClick = { /* Navigate back */ },
            indicator = OudsListItemIndicator.Previous,
            decoration = OudsListItemDecoration.Outlined
        )
        OudsSmallCardItem(
            label = "Next",
            onClick = { /* Navigate forward */ },
            indicator = OudsListItemIndicator.Next,
            decoration = OudsListItemDecoration.Outlined
        )
        OudsSmallCardItem(
            label = "External",
            onClick = { /* Open browser */ },
            indicator = OudsListItemIndicator.External,
            decoration = OudsListItemDecoration.Outlined
        )
    }
}

@Composable
internal fun OudsSmallCardItemWithImageSample() {
    OudsSmallCardItem(
        label = "Special offer",
        description = "Limited time only",
        leading = OudsSmallListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Offer image",
            format = OudsListItemImageFormat.Square
        ),
        trailing = OudsSmallListItemTrailing.Text(label = "-50%", style = OudsListItemTextStyle.LabelStrong),
        decoration = OudsListItemDecoration.Outlined
    )
}

@Composable
internal fun OudsSmallCardItemWithUntintedIconSample() {
    OudsSmallCardItem(
        label = "Featured",
        description = "Exclusive content",
        leading = OudsSmallListItemLeading.Icon(
            painter = rememberRainbowHeartPainter(),
            contentDescription = "Featured icon",
            tinted = false
        ),
        decoration = OudsListItemDecoration.Outlined
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsStaticSmallCardItemSample() = OudsPreview {
    OudsStaticSmallCardItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationSmallCardItemSample() = OudsPreview {
    OudsNavigationSmallCardItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallCardItemWithIndicatorsSample() = OudsPreview {
    OudsSmallCardItemWithIndicatorsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallCardItemWithImageSample() = OudsPreview {
    OudsSmallCardItemWithImageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsSmallCardItemWithUntintedIconSample() = OudsPreview {
    OudsSmallCardItemWithUntintedIconSample()
}
