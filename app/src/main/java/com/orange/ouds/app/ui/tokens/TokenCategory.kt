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
import androidx.compose.runtime.Immutable
import com.orange.ouds.app.R

val tokenCategories = TokenCategory::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class TokenCategory(
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val properties: List<TokenProperty> = emptyList(),
    val subcategories: List<TokenCategory> = emptyList(),
) {

    companion object {
        fun fromId(tokenId: Long) = tokenCategories.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = TokenCategory::class.sealedSubclasses.indexOf(this::class).toLong()
    val isSubcategory: Boolean
        get() = tokenCategories.any { it.subcategories.contains(this) }

    data object Border : TokenCategory(
        R.string.app_tokens_border_label,
        R.drawable.ic_border,
        R.string.app_tokens_border_description_text,
        listOf(TokenProperty.BorderWidth, TokenProperty.BorderRadius, TokenProperty.BorderStyle)
    )

    data object Dimension : TokenCategory(
        R.string.app_tokens_dimension_label,
        R.drawable.ic_dimension,
        R.string.app_tokens_dimension_description_text,
        subcategories = listOf(Space, Size)
    ) {
        data object Space : TokenCategory(
            R.string.app_tokens_dimension_space_label,
            R.drawable.ic_dimension,
            R.string.app_tokens_dimension_space_description_text,
            listOf(
                TokenProperty.SpaceScaled, TokenProperty.SpaceFixed, TokenProperty.SpacePaddingInline, TokenProperty.SpacePaddingStack,
                TokenProperty.SpacePaddingInset, TokenProperty.SpaceColumnGap, TokenProperty.SpaceRowGap
            ),
        )

        data object Size : TokenCategory(
            R.string.app_tokens_dimension_size_label,
            R.drawable.ic_dimension,
            R.string.app_tokens_dimension_size_description_text,
            listOf(TokenProperty.SizeIconDecorative, TokenProperty.SizeIconWithText),
        )
    }

    data object Elevation : TokenCategory(
        R.string.app_tokens_elevation_label,
        R.drawable.ic_layers,
        R.string.app_tokens_elevation_description_text,
        listOf(TokenProperty.Elevation)
    )

    data object Opacity : TokenCategory(
        R.string.app_tokens_opacity_label,
        R.drawable.ic_filter_effects,
        R.string.app_tokens_opacity_description_text,
        listOf(TokenProperty.Opacity)
    )

    data object Typography : TokenCategory(
        R.string.app_tokens_typography_label,
        R.drawable.ic_typography,
        R.string.app_tokens_typography_description_text,
        listOf(TokenProperty.Typography)
    )
}
