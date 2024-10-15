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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

@Composable
fun DetailScreenHeader(
    @StringRes descriptionRes: Int,
    @DrawableRes imageRes: Int,
) {
    Image(
        painter = painterResource(imageRes),
        colorFilter = ColorFilter.tint(OudsColorKeyToken.InverseOnSurface.value), //TODO use OnBgEmphasized token when available
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(186.dp)
            .background(OudsColorKeyToken.OnSurface.value), //TODO use BgEmphasizedPrimary token when available
        alignment = Alignment.Center,
        contentScale = ContentScale.None
    )

    DetailScreenDescription(
        modifier = Modifier.padding(horizontal = OudsSpacingFixedKeyToken.Medium.value),
        descriptionRes = descriptionRes
    )
}

@Composable
private fun DetailScreenDescription(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int
) {
    Text(
        text = stringResource(descriptionRes),
        modifier = modifier.padding(top = OudsSpacingFixedKeyToken.Medium.value),
        color = OudsColorKeyToken.OnSurface.value, //TODO use ContentDefault token when available
        style = OudsTypographyKeyToken.BodyDefaultLarge.value
    )
}