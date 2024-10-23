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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.tokens.OudsBorderRadiusKeyToken
import com.orange.ouds.theme.tokens.OudsBorderStyleKeyToken
import com.orange.ouds.theme.tokens.OudsBorderWidthKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeIconDecorativeKeyToken
import com.orange.ouds.theme.tokens.OudsSizeIconWithTextKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceColumnGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceFixedKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceInsetKeyToken
import com.orange.ouds.theme.tokens.OudsSpacePaddingBlockKeyToken
import com.orange.ouds.theme.tokens.OudsSpacePaddingInlineKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceRowGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceScaledKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

sealed class TokenProperty(
    @StringRes val nameRes: Int?,
    val tokens: @Composable () -> List<Token<Any>>
) {
    data object BorderRadius : TokenProperty(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = { OudsBorderRadiusKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(radius: Dp) = BorderIllustrationBox(shape = RoundedCornerShape(radius))
    }

    data object BorderStyle : TokenProperty(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = { OudsBorderStyleKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(style: OudsBorderStyle) = BorderIllustrationBox(style = style)
    }

    data object BorderWidth : TokenProperty(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = { OudsBorderWidthKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(width: Dp) = BorderIllustrationBox(width = width)
    }

    data object Elevation : TokenProperty(
        nameRes = null,
        tokens = { OudsElevationKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(elevation: Dp) {
            Surface(shadowElevation = elevation) {
                IllustrationBox(backgroundColor = OudsColorKeyToken.Surface.value) //TODO use BgDefaultSecondary token when available
            }
        }
    }

    data object Opacity : TokenProperty(
        nameRes = null,
        tokens = { OudsOpacityKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(opacity: Float) {
            val squareColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            Box {
                Image(painter = painterResource(id = R.drawable.il_opacity_union), contentDescription = null)
                Box(
                    modifier = Modifier
                        .padding(top = OudsSpaceFixedKeyToken.Medium.value, start = OudsSpaceFixedKeyToken.Medium.value)
                        .size(48.dp)
                        .background(color = squareColor.copy(alpha = opacity))
                        .border(width = 1.dp, color = squareColor)
                )
            }
        }
    }

    data object SizeIconDecorative : TokenProperty(
        nameRes = R.string.app_tokens_dimension_size_iconDecorative_label,
        tokens = { OudsSizeIconDecorativeKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) {
            IllustrationBox(modifier = Modifier.size(80.dp), contentAlignment = Alignment.Center) {
                Icon(
                    modifier = Modifier.size(size),
                    painter = painterResource(R.drawable.ic_design_token_figma),
                    tint = Color(0xFF26B2FF), //TODO use AlwaysInfo token when available
                    contentDescription = null
                )
            }
        }
    }

    data object SizeIconWithLabel : TokenProperty(
        nameRes = R.string.app_tokens_dimension_size_iconWithLabel_label,
        tokens = { OudsSizeIconWithTextKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp, tokenName: String) {
            val label = tokenName.substringBefore("Size")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(OudsSpaceFixedKeyToken.Shorter.value),
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
                    style = OudsTypographyKeyToken.entries.firstOrNull { it.name == label }?.value.orElse { OudsTypographyKeyToken.BodyStrongLarge.value }
                )
            }
        }
    }

    data object SpaceColumnGap : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_columnGap_label,
        tokens = { OudsSpaceColumnGapKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpaceIllustrationBox(size = size, contentAlignment = Alignment.Center)
    }

    data object SpaceFixed : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_fixed_label,
        tokens = { OudsSpaceFixedKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpaceIllustrationBox(size, contentAlignment = Alignment.Center)
    }

    data object SpacePaddingInline : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInline_label,
        tokens = { OudsSpacePaddingInlineKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp, tokenName: String) = when {
            tokenName.contains("WithIcon") -> SpacePaddingInlineWithImageIllustrationRow(
                spaceSize = size,
                imagePainter = painterResource(R.drawable.ic_design_token_figma_no_padding),
                imageModifier = Modifier.padding(horizontal = 1.dp)
            )
            tokenName.contains("WithArrow") -> SpacePaddingInlineWithImageIllustrationRow(
                spaceSize = size,
                imagePainter = painterResource(R.drawable.ic_chevron_down)
            )
            else -> SpaceIllustrationBox(size = size)
        }
    }

    data object SpacePaddingInset : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInset_label,
        tokens = { OudsSpaceInsetKeyToken.entries.map { Token(it.name, it.value) } }
    ) {

        @Composable
        fun Illustration(size: Dp) {
            IllustrationBox {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height = size)
                        .background(Color(0xFF26B2FF)) //TODO use AlwaysInfo token when available
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(width = size)
                        .background(Color(0xFF26B2FF)) //TODO use AlwaysInfo token when available
                )
            }
        }
    }

    data object SpacePaddingStack : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingStack_label,
        tokens = { OudsSpacePaddingBlockKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpaceIllustrationBox(size = size, orientation = SpaceOrientation.Vertical)
    }

    data object SpaceRowGap : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_rowGap_label,
        tokens = { OudsSpaceRowGapKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpaceIllustrationBox(
            size = size,
            orientation = SpaceOrientation.Vertical,
            contentAlignment = Alignment.Center
        )
    }

    data object SpaceScaled : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_scaled_label,
        tokens = { OudsSpaceScaledKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpaceIllustrationBox(size = size, contentAlignment = Alignment.Center)
    }

    data object Typography : TokenProperty(nameRes = null, tokens = { OudsTypographyKeyToken.entries.map { Token(it.name, it.value) } })

}