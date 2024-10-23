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

package com.orange.ouds.app.ui.tokens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.dottedBorder
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceFixedKeyToken

private val defaultIllustrationSize = 64.dp

@Composable
fun IllustrationBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = OudsColorKeyToken.BgEmphasized.value,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = { }
) {
    Box(
        modifier = modifier
            .size(defaultIllustrationSize)
            .background(color = backgroundColor),
        contentAlignment = contentAlignment,
    ) {
        content()
    }
}

@Composable
fun BorderIllustrationBox(
    width: Dp = 1.dp,
    shape: Shape = RectangleShape,
    style: OudsBorderStyle = OudsBorderStyle.Solid
) {
    val borderColor = OudsColorKeyToken.ContentDefault.value
    val modifier = when (style) {
        OudsBorderStyle.None -> Modifier
        OudsBorderStyle.Solid -> Modifier.border(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dashed -> Modifier.dashedBorder(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dotted -> Modifier.dottedBorder(width = width, color = borderColor, shape = shape)
    }
    IllustrationBox(modifier = modifier, backgroundColor = Color.Transparent)
}

@Composable
fun SpaceIllustrationBox(
    size: Dp,
    orientation: SpaceOrientation = SpaceOrientation.Horizontal,
    contentAlignment: Alignment = Alignment.TopStart
) {
    val dimensionBoxModifier = when (orientation) {
        SpaceOrientation.Horizontal -> Modifier
            .fillMaxHeight()
            .width(width = size)

        SpaceOrientation.Vertical -> Modifier
            .fillMaxWidth()
            .height(height = size)
    }
    IllustrationBox(contentAlignment = contentAlignment) {
        Box(
            modifier = dimensionBoxModifier.background(color = OudsColorKeyToken.AlwaysInfo.value)
        )
    }
}

enum class SpaceOrientation {
    Horizontal, Vertical
}

@Composable
fun SpacePaddingInlineWithImageIllustrationRow(
    spaceSize: Dp,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .size(defaultIllustrationSize)
            .background(color = OudsColorKeyToken.BgEmphasized.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = spaceSize)
                .background(color = OudsColorKeyToken.AlwaysInfo.value)
        )
        Image(
            modifier = imageModifier,
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.None
        )
    }
}

@Composable
fun GridIllustrations() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OudsSpaceFixedKeyToken.Medium.value)
            .background(OudsColorKeyToken.BgEmphasized.value),
        painter = painterResource(id = R.drawable.il_tokens_grid_column_margin),
        contentDescription = null
    )
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OudsSpaceFixedKeyToken.Medium.value)
            .padding(top = OudsSpaceFixedKeyToken.Medium.value)
            .background(OudsColorKeyToken.BgEmphasized.value),
        painter = painterResource(id = R.drawable.il_tokens_grid_min_width),
        contentDescription = null
    )
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OudsSpaceFixedKeyToken.Medium.value)
            .padding(top = OudsSpaceFixedKeyToken.Medium.value)
            .background(OudsColorKeyToken.BgEmphasized.value),
        painter = painterResource(id = R.drawable.il_tokens_grid_max_width),
        contentDescription = null
    )
}