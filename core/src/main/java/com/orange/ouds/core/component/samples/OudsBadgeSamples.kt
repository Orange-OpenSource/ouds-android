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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsBadgeSample() {
    OudsBadge(
        status = OudsBadge.Status.Info,
        size = OudsBadge.Size.Small
    )
}

@Composable
internal fun OudsBadgeWithCountSample() {
    OudsBadge(
        status = OudsBadge.Status.Info,
        count = 10
    )
}

@Composable
internal fun OudsBadgeWithIconSample() {
    OudsBadge(
        icon = OudsBadge.Icon(
            imageVector = Icons.Filled.FavoriteBorder,
            contentDescription = "Content description"
        ),
        status = OudsBadge.Status.Info,
        size = OudsBadge.Size.Large
    )
}

@Composable
internal fun OudsBadgeWithCountInNavigationBarItemSample() {
    NavigationBar {
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        val count = 8
                        OudsBadge(
                            modifier = Modifier.semantics {
                                contentDescription = "$count new notifications"
                            },
                            count = count,
                            status = OudsBadge.Status.Accent
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Favorite"
                    )
                }
            },
            selected = false,
            onClick = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeSample() = OudsPreview {
    OudsBadgeSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeWithCountSample() = OudsPreview {
    OudsBadgeWithCountSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeWithIconSample() = OudsPreview {
    OudsBadgeWithIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeWithCountInNavigationBarItemSample() = OudsPreview {
    OudsBadgeWithCountInNavigationBarItemSample()
}
