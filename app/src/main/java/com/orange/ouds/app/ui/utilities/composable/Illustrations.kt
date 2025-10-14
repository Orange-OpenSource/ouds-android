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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun ImageIllustration(@DrawableRes imageRes: Int) = Illustration {
    Image(
        painter = painterResource(id = imageRes),
        colorFilter = ColorFilter.tint(OudsTheme.colorScheme.content.default),
        contentDescription = null,
        contentScale = ContentScale.None
    )
}

@Composable
fun Illustration(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .clearAndSetSemantics {}
            .fillMaxWidth()
            .height(184.dp)
            .background(OudsTheme.colorScheme.surface.secondary),
        contentAlignment = Alignment.Center,
        content = { content() }
    )
}
