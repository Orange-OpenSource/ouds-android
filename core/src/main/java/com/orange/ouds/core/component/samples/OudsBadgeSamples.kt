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
import com.orange.ouds.core.component.OudsBadgeIcon
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.component.OudsIconBadgeStatus
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsBadgeSample() {
    OudsBadge(
        modifier = Modifier.semantics {
            contentDescription = "Information"
        },
        status = OudsBadgeStatus.Info,
        size = OudsBadgeSize.Small
    )
}

@Composable
internal fun OudsBadgeWithCountSample() {
    val count = 10
    OudsBadge(
        modifier = Modifier.semantics {
            contentDescription = "$count unread emails"
        },
        status = OudsBadgeStatus.Info,
        count = count
    )
}

@Composable
internal fun OudsBadgeWithDefaultIconSample() {
    OudsBadge(
        modifier = Modifier.semantics {
            contentDescription = "Information"
        },
        status = OudsIconBadgeStatus.Info,
        size = OudsBadgeSize.Large
    )
}

@Composable
internal fun OudsBadgeWithCustomIconSample() {
    OudsBadge(
        modifier = Modifier.semantics {
            contentDescription = "Favorite"
        },
        status = OudsIconBadgeStatus.Accent(
            OudsBadgeIcon(imageVector = Icons.Filled.FavoriteBorder)
        ),
        size = OudsBadgeSize.Large
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
                            status = OudsBadgeStatus.Accent
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
private fun PreviewOudsBadgeWithDefaultIconSample() = OudsPreview {
    OudsBadgeWithDefaultIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeWithCustomIconSample() = OudsPreview {
    OudsBadgeWithCustomIconSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsBadgeWithCountInNavigationBarItemSample() = OudsPreview {
    OudsBadgeWithCountInNavigationBarItemSample()
}
