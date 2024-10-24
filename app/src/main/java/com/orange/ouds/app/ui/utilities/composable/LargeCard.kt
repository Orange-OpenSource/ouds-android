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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorBackgroundKeyToken
import com.orange.ouds.theme.tokens.OudsColorContentKeyToken
import com.orange.ouds.theme.tokens.OudsColorElevationKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

/**
 * Temporary large card used by the demo app
 */
@Composable
fun LargeCard(
    title: String,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier, shape = RectangleShape, elevation = cardElevation(defaultElevation = OudsElevationKeyToken.Raised.value), onClick = onClick) {
        Column(modifier = Modifier.background(OudsColorElevationKeyToken.Raised.value)) {
            Image(
                painter = painterResource(imageRes),
                colorFilter = ColorFilter.tint(OudsColorContentKeyToken.DefaultOnBgEmphasized.value),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(184.dp)
                    .background(OudsColorBackgroundKeyToken.Emphasized.value),
                contentScale = ContentScale.None
            )
            Column(
                modifier = Modifier.padding(OudsSpaceKeyToken.Fixed.Medium.value)
            ) {
                Text(
                    text = title,
                    color = OudsColorContentKeyToken.Default.value,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = OudsTypographyKeyToken.HeadingMedium.value
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
        imageRes = R.drawable.ic_filter_effects,
        onClick = {}
    )
}
