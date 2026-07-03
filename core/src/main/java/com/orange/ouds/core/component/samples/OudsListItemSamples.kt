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
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsListItem
import com.orange.ouds.core.component.OudsListItemImageFormat
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
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
    val menuItems = listOf("Profile", "Settings", "Privacy")
    var selectedItem by rememberSaveable { mutableStateOf(menuItems.first()) }

    Column {
        menuItems.forEach { item ->
            OudsListItem(
                label = item,
                onClick = { selectedItem = item },
                indicator = OudsListItemIndicator.Next,
                background = item == selectedItem
            )
        }
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
internal fun OudsListItemWithIndicatorsSample() {
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
internal fun OudsListItemWithImageSample() {
    OudsListItem(
        label = "Product name",
        description = "Product description with details",
        leading = OudsListItemLeading.Image(
            painter = CheckerboardPainter,
            contentDescription = "Product image",
            size = OudsListItemImageSize.Large,
            format = OudsListItemImageFormat.Square
        ),
        trailing = OudsListItemTrailing.Text(label = "€29.99", style = OudsListItemTextStyle.Label)
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
private fun PreviewOudsListItemWithIndicatorsSample() = OudsPreview {
    OudsListItemWithIndicatorsSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithImageSample() = OudsPreview {
    OudsListItemWithImageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsListItemWithUntintedIconSample() = OudsPreview {
    OudsListItemWithUntintedIconSample()
}
