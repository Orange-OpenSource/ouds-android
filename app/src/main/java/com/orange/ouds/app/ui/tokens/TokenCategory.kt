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
import com.orange.ouds.app.ui.utilities.getTokenName
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

val tokenCategories = TokenCategory::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class TokenCategory<T>(
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val descriptionRes: Int,
    val valueCodeExample: String? = null,
    val properties: List<TokenProperty<T>> = emptyList(),
    val subcategories: List<TokenCategory<*>> = emptyList(),

    ) where T : TokenCategory<T> {

    companion object {
        fun fromId(tokenId: Long) = tokenCategories.firstOrNull { token -> token.id == tokenId }
    }

    val id: Long = TokenCategory::class.sealedSubclasses.indexOf(this::class).toLong()
    val isSubcategory: Boolean
        get() = tokenCategories.any { it.subcategories.contains(this) }

    data object Border : TokenCategory<Border>(
        R.string.app_tokens_border_label,
        R.drawable.ic_border,
        R.string.app_tokens_border_description_text,
        getTokenValueCode<OudsBorderKeyToken.Width.None>(),
        listOf(TokenProperty.BorderWidth, TokenProperty.BorderRadius, TokenProperty.BorderStyle),
    )

    data object Color : TokenCategory<Color>(
        R.string.app_tokens_color_label,
        R.drawable.ic_palette,
        R.string.app_tokens_color_description_text,
        getTokenValueCode<OudsColorKeyToken.Action.Disabled>(),
        listOf(
            TokenProperty.ColorAction,
            TokenProperty.ColorAlways,
            TokenProperty.ColorBackground,
            TokenProperty.ColorBorder,
            TokenProperty.ColorBrand,
            TokenProperty.ColorContent,
            TokenProperty.ColorDecorative,
            TokenProperty.ColorElevation,
            TokenProperty.ColorGradient,
        )
    )

    data object Dimension : TokenCategory<Dimension>(
        R.string.app_tokens_dimension_label,
        R.drawable.ic_dimension,
        R.string.app_tokens_dimension_description_text,
        subcategories = listOf(Space, Size)
    ) {
        data object Space : TokenCategory<Space>(
            R.string.app_tokens_dimension_space_label,
            R.drawable.ic_dimension,
            R.string.app_tokens_dimension_space_description_text,
            getTokenValueCode<OudsSpaceKeyToken.Scaled.None>(),
            listOf(
                TokenProperty.SpaceScaled,
                TokenProperty.SpaceFixed,
                TokenProperty.SpacePaddingInline,
                TokenProperty.SpacePaddingInlineWithIcon,
                TokenProperty.SpacePaddingInlineWithArrow,
                TokenProperty.SpacePaddingStack,
                TokenProperty.SpacePaddingStackWithIcon,
                TokenProperty.SpacePaddingInset,
                TokenProperty.SpaceColumnGap,
                TokenProperty.SpaceColumnGapWithIcon,
                TokenProperty.SpaceColumnGapWithArrow,
                TokenProperty.SpaceRowGap,
                TokenProperty.SpaceRowGapWithIcon
            ),
        )

        data object Size : TokenCategory<Size>(
            R.string.app_tokens_dimension_size_label,
            R.drawable.ic_dimension,
            R.string.app_tokens_dimension_size_description_text,
            getTokenValueCode<OudsSizeKeyToken.Icon.Decorative.Shortest>(),
            listOf(TokenProperty.SizeIconDecorative, TokenProperty.SizeIconWithText),
        )
    }

    data object Elevation : TokenCategory<Elevation>(
        R.string.app_tokens_elevation_label,
        R.drawable.ic_layers,
        R.string.app_tokens_elevation_description_text,
        getTokenValueCode<OudsElevationKeyToken.None>(),
        listOf(TokenProperty.Elevation)
    )

    data object Grid : TokenCategory<Grid>(
        R.string.app_tokens_grid_label,
        R.drawable.ic_menu_grid,
        R.string.app_tokens_grid_description_text,
        getTokenValueCode<OudsGridKeyToken.MinWidth>(),
        listOf(TokenProperty.Grid)
    )

    data object Opacity : TokenCategory<Opacity>(
        R.string.app_tokens_opacity_label,
        R.drawable.ic_filter_effects,
        R.string.app_tokens_opacity_description_text,
        getTokenValueCode<OudsOpacityKeyToken.Transparent>(),
        listOf(TokenProperty.Opacity)
    )

    data object Typography : TokenCategory<Typography>(
        R.string.app_tokens_typography_label,
        R.drawable.ic_typography,
        R.string.app_tokens_typography_description_text,
        getTokenValueCode<OudsTypographyKeyToken.Display.Large>(),
        listOf(TokenProperty.Typography)
    )
}

private inline fun <reified T> getTokenValueCode() = "${T::class.getTokenName()}.value"