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

package com.orange.ouds.app.ui.utilities.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

/**
 * Temporary large card used by the demo app
 */
@Composable
fun LargeCard(
    title: String,
    painter: Painter,
    onClick: () -> Unit,
    imageTint: Color? = null
) {
    Card(shape = RectangleShape, elevation = cardElevation(defaultElevation = OudsTheme.elevations.raised), onClick = onClick) {
        Column(modifier = Modifier.background(OudsTheme.colorScheme.background.primary)) {
            Image(
                painter = painter,
                colorFilter = imageTint?.let { ColorFilter.tint(imageTint) },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp)
                    .background(OudsTheme.colorScheme.overlay.default)
                    .background(OudsTheme.colorScheme.surface.status.neutral.muted),
                contentScale = ContentScale.None
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OudsTheme.colorScheme.overlay.default)
                    .padding(OudsTheme.spaces.fixed.medium)
            ) {
                Text(
                    text = title,
                    color = OudsTheme.colorScheme.content.default,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = OudsTheme.typography.heading.medium
                )
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewLargeCard() = OudsPreview {
    LargeCard(
        title = "Title",
        painter = painterResource(R.drawable.ic_filter_effects),
        onClick = {},
        imageTint = OudsTheme.colorScheme.content.default
    )
}
