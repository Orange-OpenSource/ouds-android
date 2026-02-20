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
import androidx.compose.runtime.Immutable
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.ThemeDrawableResourceProvider
import com.orange.ouds.app.ui.utilities.previewCompatibleClass
import com.orange.ouds.core.theme.OudsTheme

val tokenCategories = TokenCategory::class.previewCompatibleClass.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class TokenCategory<T>(
    @StringRes val nameRes: Int,
    val imageResourceProvider: ThemeDrawableResourceProvider,
    @StringRes val descriptionRes: Int,
    val properties: List<TokenProperty<T>> = emptyList(),
    val subcategories: List<TokenCategory<*>> = emptyList(),
) where T : TokenCategory<T> {

    companion object {
        fun fromId(tokenId: Long) = tokenCategories.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = TokenCategory::class.previewCompatibleClass.sealedSubclasses.indexOf(this::class).toLong()
    val isSubcategory: Boolean
        get() = tokenCategories.any { it.subcategories.contains(this) }

    val valueCodeExample = properties.firstOrNull()
        ?.tokens
        ?.firstOrNull()
        ?.name
        ?.let { "${OudsTheme::class.simpleName}.$it" }

    data object Border : TokenCategory<Border>(
        R.string.app_tokens_border_tech,
        { R.drawable.ic_border },
        R.string.app_tokens_border_description_text,
        listOf(TokenProperty.BorderWidth, TokenProperty.BorderRadius, TokenProperty.BorderStyle),
    )

    data object Color : TokenCategory<Color>(
        R.string.app_tokens_color_tech,
        { it.palette },
        R.string.app_tokens_color_description_text,
        listOf(
            TokenProperty.ColorAction,
            TokenProperty.ColorAlways,
            TokenProperty.ColorBackground,
            TokenProperty.ColorBorder,
            TokenProperty.ColorContent,
            TokenProperty.ColorOpacity,
            TokenProperty.ColorOverlay,
            TokenProperty.ColorSurface,
        )
    )

    data object Dimension : TokenCategory<Dimension>(
        R.string.app_tokens_dimension_tech,
        { R.drawable.ic_dimension },
        R.string.app_tokens_dimension_description_text,
        subcategories = listOf(Space, Size)
    ) {
        data object Space : TokenCategory<Space>(
            R.string.app_tokens_dimension_space_tech,
            { R.drawable.ic_dimension },
            R.string.app_tokens_dimension_space_description_text,
            listOf(
                TokenProperty.SpaceScaled,
                TokenProperty.SpaceFixed,
                TokenProperty.SpacePaddingInline,
                TokenProperty.SpacePaddingBlock,
                TokenProperty.SpaceInset,
                TokenProperty.SpaceColumnGap,
                TokenProperty.SpaceRowGap,
            ),
        )

        data object Size : TokenCategory<Size>(
            R.string.app_tokens_dimension_size_tech,
            { R.drawable.ic_dimension },
            R.string.app_tokens_dimension_size_description_text,
            listOf(TokenProperty.SizeMinInteractiveArea, TokenProperty.SizeIconDecorative, TokenProperty.SizeIconWithText, TokenProperty.SizeMaxWidth),
        )
    }

    data object Elevation : TokenCategory<Elevation>(
        R.string.app_tokens_elevation_tech,
        { R.drawable.ic_layers },
        R.string.app_tokens_elevation_description_text,
        listOf(TokenProperty.Elevation)
    )

    data object Grid : TokenCategory<Grid>(
        R.string.app_tokens_grid_tech,
        { it.menuGrid },
        R.string.app_tokens_grid_description_text,
        listOf(TokenProperty.Grid)
    )

    data object Opacity : TokenCategory<Opacity>(
        R.string.app_tokens_opacity_tech,
        { R.drawable.ic_filter_effects },
        R.string.app_tokens_opacity_description_text,
        listOf(TokenProperty.Opacity)
    )

    data object Typography : TokenCategory<Typography>(
        R.string.app_tokens_typography_tech,
        { R.drawable.ic_typography },
        R.string.app_tokens_typography_description_text,
        listOf(TokenProperty.Typography)
    )
}
