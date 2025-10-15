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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.OudsBorderStyle
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.dashedBorder
import com.orange.ouds.core.theme.dottedBorder

private val defaultIllustrationSize = 64.dp

@Composable
fun IllustrationBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = OudsTheme.colorScheme.surface.secondary,
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
    style: OudsBorderStyle = OudsBorderStyle.Solid,
    backgroundColor: Color = OudsTheme.colorScheme.background.secondary
) {
    val borderColor = OudsTheme.colorScheme.border.emphasized
    val modifier = when (style) {
        OudsBorderStyle.None -> Modifier
        OudsBorderStyle.Solid -> Modifier.border(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dashed -> Modifier.dashedBorder(width = width, color = borderColor, shape = shape)
        OudsBorderStyle.Dotted -> Modifier.dottedBorder(width = width, color = borderColor, shape = shape)
    }
    IllustrationBox(modifier = modifier.clip(shape), backgroundColor = backgroundColor)
}

@Composable
fun ElevationIllustrationSurface(elevation: Dp) {
    Surface(shadowElevation = elevation) {
        IllustrationBox(backgroundColor = OudsTheme.colorScheme.background.secondary)
    }
}

@Composable
fun OpacityIllustrationBox(opacity: Float) {
    val squareColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Box {
        Image(painter = painterResource(id = R.drawable.il_opacity_union), contentDescription = null)
        Box(
            modifier = Modifier
                .padding(top = OudsTheme.spaces.fixed.medium, start = OudsTheme.spaces.fixed.medium)
                .size(48.dp)
                .background(color = squareColor.copy(alpha = opacity))
                .border(width = 1.dp, color = squareColor)
        )
    }
}

@Composable
fun SizeIconIllustrationBox(size: Dp) {
    IllustrationBox(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(R.drawable.ic_design_token_figma),
            tint = OudsTheme.colorScheme.content.status.info,
            contentDescription = null
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
                .background(OudsTheme.colorScheme.content.status.info)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(width = size)
                .background(OudsTheme.colorScheme.content.status.info)
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
            modifier = dimensionBoxModifier.background(color = OudsTheme.colorScheme.content.status.info)
        )
    }
}

enum class SpaceOrientation {
    Horizontal, Vertical
}
