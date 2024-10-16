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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.tokens.OudsBorderRadiusKeyToken
import com.orange.ouds.theme.tokens.OudsBorderStyleKeyToken
import com.orange.ouds.theme.tokens.OudsBorderWidthKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

val tokenCategories = TokenCategory::class.sealedSubclasses.mapNotNull { it.objectInstance }

private val defaultIllustrationSize = 64.dp

@Immutable
sealed class TokenCategory(
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val properties: List<TokenProperty>
) {

    companion object {
        fun fromId(tokenId: Long) = tokenCategories.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = TokenCategory::class.sealedSubclasses.indexOf(this::class).toLong()

    data object Border : TokenCategory(
        R.string.app_tokens_border_label,
        R.drawable.ic_border,
        R.string.app_tokens_border_description_text,
        listOf(TokenProperty.BorderWidth, TokenProperty.BorderRadius, TokenProperty.BorderStyle)
    )

    data object Opacity : TokenCategory(
        R.string.app_tokens_opacity_label,
        R.drawable.ic_filter_effects,
        R.string.app_tokens_opacity_description_text,
        listOf(TokenProperty.Opacity)
    )

    data object Elevation : TokenCategory(
        R.string.app_tokens_elevation_label,
        R.drawable.ic_layers,
        R.string.app_tokens_elevation_description_text,
        listOf(TokenProperty.Elevation)
    )

}

sealed class TokenProperty(
    @StringRes val nameRes: Int?,
    val tokens: @Composable () -> List<Token<Any>>
) {

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

    data object BorderStyle : TokenProperty(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = { OudsBorderStyleKeyToken.entries.map { Token(it.name, it.value) } }
    ) {
        @Composable
        fun Illustration(style: OudsBorderStyle) {
            val modifier = when (style) {
                OudsBorderStyle.None -> Modifier
                OudsBorderStyle.Solid -> Modifier.border(width = 1.dp, color = OudsColorKeyToken.OnSurface.value) //TODO use ContentDefault token when available
                OudsBorderStyle.Dashed -> Modifier.dashedBorder(color = OudsColorKeyToken.OnSurface.value, shape = RectangleShape)
            }
            Box(
                modifier = modifier
                    .size(defaultIllustrationSize)
                    .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
            )
        }
    }

}

data class Token<T>(val name: String, val value: T) {
    val literalValue: String
        @Composable
        get() = when (value) {
            is Float -> stringResource(id = R.string.app_tokens_floatFormat_label, value)
            is Dp -> stringResource(id = R.string.app_tokens_dpFormat_label, value.toString().replace(".0.dp", "").substringBeforeLast(".dp"))
            else -> value.toString()
        }
}