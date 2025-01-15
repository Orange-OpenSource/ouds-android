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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@Composable
fun DetailScreenHeader(
    @StringRes descriptionRes: Int,
    @DrawableRes imageRes: Int,
) {
    Column {
        Image(
            painter = painterResource(imageRes),
            colorFilter = ColorFilter.tint(OudsTheme.colorScheme.content.default),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(186.dp)
                .background(OudsTheme.colorScheme.surface.status.neutral.muted),
            contentScale = ContentScale.None
        )

        DetailScreenDescription(
            modifier = Modifier
                .padding(horizontal = OudsTheme.spaces.fixed.medium)
                .padding(top = OudsTheme.spaces.fixed.medium),
            descriptionRes = descriptionRes
        )
    }
}

@Composable
fun DetailScreenDescription(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int
) {
    Text(
        modifier = modifier,
        text = stringResource(descriptionRes),
        color = OudsTheme.colorScheme.content.default,
        style = OudsTheme.typography.body.default.large
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewDetailScreenHeader() = OudsPreview {
    DetailScreenHeader(
        descriptionRes = R.string.app_tokens_elevation_description_text,
        imageRes = R.drawable.ic_layers
    )
}
