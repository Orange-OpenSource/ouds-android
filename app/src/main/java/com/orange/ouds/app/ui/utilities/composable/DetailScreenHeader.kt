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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.priorityClickable
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun DetailScreenHeader(
    description: String,
    illustration: (@Composable () -> Unit),
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            // Intercept all click events before they reach interactive components in illustration composable
            .priorityClickable(
                interactionSource = null,
                indication = null,
                onClick = {}
            )
    ) {
        Box(
            modifier = Modifier.height(186.dp),
            propagateMinConstraints = true
        ) {
            illustration()
        }
        DetailScreenDescription(
            modifier = Modifier
                .padding(horizontal = OudsTheme.grids.margin)
                .padding(top = OudsTheme.spaces.fixed.medium),
            description = description
        )
    }
}

@Composable
fun DetailScreenDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = description,
        color = OudsTheme.colorScheme.content.default,
        style = OudsTheme.typography.body.default.large
    )
}

@PreviewLightDark
@Composable
private fun PreviewDetailScreenHeader() = OudsPreview {
    DetailScreenHeader(
        description = stringResource(id = R.string.app_tokens_elevation_description_text),
        illustration = { ImageIllustration(imageRes = R.drawable.ic_layers) }
    )
}
