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

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.priorityClickable
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

/**
 * Temporary large card used by the demo app
 */
@Composable
fun LargeCard(
    title: String,
    illustration: (@Composable () -> Unit),
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        colors = CardDefaults.cardColors(containerColor = OudsTheme.colorScheme.background.primary),
        modifier = Modifier
            // Intercept all click events before they reach interactive components in illustration composable
            .priorityClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        interactionSource = interactionSource,
        shape = RectangleShape,
        elevation = cardElevation(defaultElevation = OudsTheme.elevations.raised),
        onClick = onClick // Card onClick is needed for keyboard navigation
    ) {
        Column(modifier = Modifier.background(OudsTheme.colorScheme.overlay.dropdown)) {
            illustration()
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(OudsTheme.spaces.fixed.medium),
                text = title,
                color = OudsTheme.colorScheme.content.default,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = OudsTheme.typography.heading.medium
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewLargeCard() = OudsPreview {
    LargeCard(
        title = "Title",
        illustration = { ImageIllustration(imageRes = R.drawable.ic_filter_effects) },
        onClick = {}
    )
}
