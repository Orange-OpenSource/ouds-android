/*
 * Software Name: Orange Design System
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

val tokenCategories = TokenCategory::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class TokenCategory(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int
) {

    companion object {
        fun fromId(tokenId: Long?) = tokenCategories.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = TokenCategory::class.sealedSubclasses.indexOf(this::class).toLong()

    @Composable
    fun getTokens(): List<Token> {
        return when (this) {
            is Opacity -> OudsOpacityKeyToken.entries.map {
                Token(
                    it.name,
                    String.format(stringResource(id = R.string.app_common_floatFormat_label), it.value),
                    it.value
                )
            }

            is Elevation -> OudsElevationKeyToken.entries.map {
                Token(
                    it.name,
                    String.format(stringResource(id = R.string.app_common_dpFormat_label), it.value.toString().substringBeforeLast(".dp")),
                    it.value
                )
            }
        }
    }

    data object Opacity : TokenCategory(
        R.string.app_tokens_opacity_label,
        R.drawable.ic_filter_effects,
        R.string.app_token_opacity_description_text
    ) {
        @Composable
        fun Illustration(opacity: Float) {
            val squareColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            Box {
                Image(painter = painterResource(id = R.drawable.il_opacity_union), contentDescription = "")
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

    data object Elevation : TokenCategory(
        R.string.app_tokens_elevation_label,
        R.drawable.ic_layers,
        R.string.app_token_elevation_description_text
    ) {
        @Composable
        fun Illustration(elevation: Dp) {
            Surface(shadowElevation = elevation) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(color = OudsColorKeyToken.Surface.value), //TODO use BgDefaultSecondary token when available
                )
            }
        }
    }

}

data class Token(val name: String, val literalValue: String, val value: Any)
