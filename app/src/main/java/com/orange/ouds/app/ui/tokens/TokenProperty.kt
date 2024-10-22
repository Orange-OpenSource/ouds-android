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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.dottedBorder
import com.orange.ouds.theme.tokens.OudsBorderRadiusKeyToken
import com.orange.ouds.theme.tokens.OudsBorderStyleKeyToken
import com.orange.ouds.theme.tokens.OudsBorderWidthKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeIconDecorativeKeyToken
import com.orange.ouds.theme.tokens.OudsSizeIconWithTextKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingColumnGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingInsetKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingPaddingBlockKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingPaddingInlineKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingRowGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingScaledKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

sealed class TokenProperty(
    @StringRes val nameRes: Int?,
    val tokens: @Composable () -> List<Token<Any>>
) {
    protected companion object {
        protected val defaultIllustrationSize = 64.dp
    }

    data object BorderRadius : TokenProperty(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = { OudsBorderRadiusKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(radius: Dp) {
            Box(
                modifier = Modifier
                    .size(defaultIllustrationSize)
                    .border(
                        width = 1.dp,
                        color = OudsColorKeyToken.OnSurface.value,
                        shape = RoundedCornerShape(radius)
                    ) //TODO use ContentDefault token when available
                    .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
            )
        }
    }

    data object BorderStyle : TokenProperty(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = { OudsBorderStyleKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(style: OudsBorderStyle) {
            val borderColor = OudsColorKeyToken.OnSurface.value //TODO use ContentDefault token when available
            val borderWidth = 1.dp
            val modifier = when (style) {
                OudsBorderStyle.None -> Modifier
                OudsBorderStyle.Solid -> Modifier.border(width = borderWidth, color = borderColor)
                OudsBorderStyle.Dashed -> Modifier.dashedBorder(width = borderWidth, color = borderColor)
                OudsBorderStyle.Dotted -> Modifier.dottedBorder(width = borderWidth, color = borderColor)
            }
            Box(
                modifier = modifier
                    .size(defaultIllustrationSize)
                    .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
            )
        }
    }

    data object BorderWidth : TokenProperty(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = { OudsBorderWidthKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(width: Dp) {
            Box(
                modifier = Modifier
                    .size(defaultIllustrationSize)
                    .border(width = width, color = OudsColorKeyToken.OnSurface.value) //TODO use ContentDefault token when available
                    .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
            )
        }
    }

    data object Elevation : TokenProperty(
        nameRes = null,
        tokens = { OudsElevationKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(elevation: Dp) {
            Surface(shadowElevation = elevation) {
                Box(
                    modifier = Modifier
                        .size(defaultIllustrationSize)
                        .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
                )
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
                        .padding(top = OudsSpacingFixedKeyToken.Medium.value, start = OudsSpacingFixedKeyToken.Medium.value)
                        .size(48.dp)
                        .background(color = squareColor.copy(alpha = opacity))
                        .run {
                            if (opacity <= 0f) {
                                border(width = 1.dp, color = squareColor)
                            } else this
                        }
                )
            }
        }
    }

    data object SizeIconDecorative : TokenProperty(
        nameRes = R.string.app_tokens_size_iconDecorative_label,
        tokens = { OudsSizeIconDecorativeKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = OudsColorKeyToken.OnSurface.value), //TODO use BgEmphasizedPrimary token when available
                contentAlignment = Alignment.Center,
            ) {
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
        nameRes = R.string.app_tokens_size_iconWithLabel_label,
        tokens = { OudsSizeIconWithTextKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp, tokenName: String) {
            val label = tokenName.substringBefore("Size")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(OudsSpacingFixedKeyToken.Shorter.value),
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

    data object SpacingColumnGap : TokenProperty(
        nameRes = R.string.app_tokens_spacing_columnGap_label,
        tokens = { OudsSpacingColumnGapKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpacingIllustration(size = size, contentAlignment = Alignment.Center)
    }

    data object SpacingFixed : TokenProperty(
        nameRes = R.string.app_tokens_spacing_fixed_label,
        tokens = { OudsSpacingFixedKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpacingIllustration(size, contentAlignment = Alignment.Center)
    }

    data object SpacingPaddingInline : TokenProperty(
        nameRes = R.string.app_tokens_spacing_paddingInline_label,
        tokens = { OudsSpacingPaddingInlineKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp, tokenName: String) = when {
            tokenName.contains("WithIcon") -> {
                Row(
                    modifier = Modifier
                        .size(defaultIllustrationSize)
                        .background(color = OudsColorKeyToken.OnSurface.value), //TODO use BgEmphasizedPrimary token when available
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(width = size)
                            .background(Color(0xFF26B2FF)) //TODO use AlwaysInfo token when available
                    )
                    Image(
                        modifier = Modifier.padding(horizontal = 1.dp),
                        painter = painterResource(R.drawable.ic_design_token_figma_no_padding),
                        contentDescription = null,
                        contentScale = ContentScale.None
                    )
                }
            }
            else -> SpacingIllustration(size = size)
        }
    }

    data object SpacingPaddingInset : TokenProperty(
        nameRes = R.string.app_tokens_spacing_paddingInset_label,
        tokens = { OudsSpacingInsetKeyToken.entries.map { Token(it.name, it.value) } }
    ) {

        @Composable
        fun Illustration(size: Dp) {
            Box(
                modifier = Modifier
                    .size(defaultIllustrationSize)
                    .background(color = OudsColorKeyToken.OnSurface.value), //TODO use BgEmphasizedPrimary token when available
            ) {
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

    data object SpacingPaddingStack : TokenProperty(
        nameRes = R.string.app_tokens_spacing_paddingStack_label,
        tokens = { OudsSpacingPaddingBlockKeyToken.entries.map { Token(it.name, it.value) } }
    ) {

        @Composable
        fun Illustration(size: Dp) = SpacingIllustration(
            size = size,
            orientation = DimensionOrientation.Vertical
        )
    }

    data object SpacingRowGap : TokenProperty(
        nameRes = R.string.app_tokens_spacing_rowGap_label,
        tokens = { OudsSpacingRowGapKeyToken.entries.map { Token(it.name, it.value) } }
    ) {

        @Composable
        fun Illustration(size: Dp) = SpacingIllustration(
            size = size,
            orientation = DimensionOrientation.Vertical,
            contentAlignment = Alignment.Center
        )
    }

    data object SpacingScaled : TokenProperty(
        nameRes = R.string.app_tokens_spacing_scaled_label,
        tokens = { OudsSpacingScaledKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(size: Dp) = SpacingIllustration(size = size, contentAlignment = Alignment.Center)
    }

    data object Typography : TokenProperty(nameRes = null, tokens = { OudsTypographyKeyToken.entries.map { Token(it.name, it.value) } })

    @Composable
    protected fun SpacingIllustration(
        size: Dp,
        orientation: DimensionOrientation = DimensionOrientation.Horizontal,
        contentAlignment: Alignment = Alignment.TopStart
    ) {
        val dimensionBoxModifier = when (orientation) {
            DimensionOrientation.Horizontal -> Modifier
                .fillMaxHeight()
                .width(width = size)

            DimensionOrientation.Vertical -> Modifier
                .fillMaxWidth()
                .height(height = size)
        }
        Box(
            modifier = Modifier
                .size(defaultIllustrationSize)
                .background(color = OudsColorKeyToken.OnSurface.value), //TODO use BgEmphasizedPrimary token when available
            contentAlignment = contentAlignment,
        ) {
            Box(
                modifier = dimensionBoxModifier.background(Color(0xFF26B2FF)) //TODO use AlwaysInfo token when available
            )
        }
    }

    protected enum class DimensionOrientation {
        Horizontal, Vertical
    }
}

data class Token<T>(val name: String, val value: T) {
    val literalValue: String
        @Composable
        get() = when (value) {
            is Float -> stringResource(id = R.string.app_tokens_floatFormat_label, value)
            is Dp -> stringResource(id = R.string.app_tokens_dpFormat_label, value.toString().replace(".0.dp", "").substringBeforeLast(".dp"))
            is TextStyle -> stringResource(id = R.string.app_tokens_spFormat_label, value.fontSize.toString().replace(".0.sp", "").substringBeforeLast(".sp"))
            else -> value.toString()
        }
}
