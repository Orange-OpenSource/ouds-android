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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.dottedBorder
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

private val defaultIllustrationSize = 64.dp

@Composable
fun IllustrationBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = OudsColorKeyToken.Background.Emphasized.value,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit = { }
) {
    Box(
        modifier = modifier
            .size(defaultIllustrationSize)
            .background(color = backgroundColor),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Composable
fun BorderIllustrationBox(
    width: Dp = 1.dp,
    shape: Shape = RectangleShape,
    style: OudsBorderStyle = OudsBorderStyle.Solid
) {
    val borderColor = OudsColorKeyToken.Content.Default.value
    val modifier = when (style) {
        OudsBorderStyle.None -> Modifier
        OudsBorderStyle.Solid -> Modifier.border(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dashed -> Modifier.dashedBorder(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dotted -> Modifier.dottedBorder(width = width, color = borderColor, shape = shape)
    }
    IllustrationBox(modifier = modifier.clip(shape), backgroundColor = OudsColorKeyToken.Background.Secondary.value)
}

@Composable
fun ElevationIllustrationSurface(elevation: Dp) {
    Surface(shadowElevation = elevation) {
        IllustrationBox(backgroundColor = OudsColorKeyToken.Background.Secondary.value)
    }
}

@Composable
fun OpacityIllustrationBox(opacity: Float) {
    val squareColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Box {
        Image(painter = painterResource(id = R.drawable.il_opacity_union), contentDescription = null)
        Box(
            modifier = Modifier
                .padding(top = OudsSpaceKeyToken.Fixed.Medium.value, start = OudsSpaceKeyToken.Fixed.Medium.value)
                .size(48.dp)
                .background(color = squareColor.copy(alpha = opacity))
                .border(width = 1.dp, color = squareColor)
        )
    }
}

@Composable
fun SizeIconDecorativeIllustrationBox(size: Dp) {
    IllustrationBox(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(R.drawable.ic_design_token_figma),
            tint = Color(0xFF26B2FF), //TODO use AlwaysInfo token when available
            contentDescription = null
        )
    }
}

@Composable
fun SizeIconWithTextIllustrationRow(size: Dp, tokenName: String) {
    val label = tokenName.substringBefore("Size")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Shorter.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(R.drawable.ic_design_token_figma),
            tint = Color(0xFF26B2FF), //TODO use AlwaysInfo token when available
            contentDescription = null
        )
        Text(
            modifier = Modifier.weight(1f),
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = OudsTypographyKeyToken.entries.firstOrNull { it.name.replace("Strong", "") == label }?.value.orElse { LocalTextStyle.current }
        )
    }
}

@Composable
fun SpaceWithIconIllustrationColumn(size: Dp, verticalArrangement: Arrangement.Vertical = Arrangement.Top) {
    Column(
        modifier = Modifier
            .size(defaultIllustrationSize)
            .background(color = OudsColorKeyToken.Background.Emphasized.value),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = verticalArrangement
    ) {
        val image: @Composable () -> Unit = {
            Image(
                painter = painterResource(R.drawable.ic_design_token_figma_no_padding),
                contentDescription = null,
                contentScale = ContentScale.None
            )
        }

        val space: @Composable () -> Unit = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = size)
                    .background(OudsColorKeyToken.Always.Info.value)
            )
        }

        if (verticalArrangement == Arrangement.Bottom) {
            image()
            space()
        } else {
            space()
            image()
        }
    }
}

@Composable
fun SpaceWithIconIllustrationRow(size: Dp) {
    SpaceWithImageIllustrationRow(
        spaceSize = size,
        imagePainter = painterResource(R.drawable.ic_design_token_figma_no_padding),
        imageModifier = Modifier.padding(horizontal = 1.dp)
    )
}

@Composable
fun SpaceWithArrowIllustrationRow(size: Dp) {
    SpaceWithImageIllustrationRow(
        spaceSize = size,
        imagePainter = painterResource(R.drawable.ic_chevron_down)
    )
}

@Composable
private fun SpaceWithImageIllustrationRow(
    spaceSize: Dp,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .size(defaultIllustrationSize)
            .background(color = OudsColorKeyToken.Background.Emphasized.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = spaceSize)
                .background(OudsColorKeyToken.Always.Info.value)
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
fun SpacePaddingInsetIllustrationBox(size: Dp) {
    IllustrationBox {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = size)
                .background(OudsColorKeyToken.Always.Info.value)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = size)
                .background(OudsColorKeyToken.Always.Info.value)
        )
    }
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
            modifier = dimensionBoxModifier.background(color = OudsColorKeyToken.Always.Info.value)
        )
    }
}

enum class SpaceOrientation {
    Horizontal, Vertical
}

@Composable
fun GridIllustrations() {
    Column(
        modifier = Modifier.padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value),
        verticalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Medium.value)
    ) {
        val resourceIds = listOf(
            R.drawable.il_tokens_grid_column_margin,
            R.drawable.il_tokens_grid_min_width,
            R.drawable.il_tokens_grid_max_width
        )
        resourceIds.forEach {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OudsColorKeyToken.Background.Emphasized.value),
                painter = painterResource(id = it),
                contentDescription = null
            )
        }
    }
}
