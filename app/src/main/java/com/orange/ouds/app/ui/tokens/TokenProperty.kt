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
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.getTokenName
import com.orange.ouds.app.ui.utilities.getTokens
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import kotlin.reflect.KClass

sealed class TokenProperty<T>(
    @StringRes val nameRes: Int?,
    val tokens: List<Token<*>>,
    val categoryClass: KClass<T>
) where T : TokenCategory<T> {

    data object BorderRadius : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = OudsBorderKeyToken.Radius::class.getTokens(),
        categoryClass = TokenCategory.Border::class
    )

    data object BorderStyle : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = OudsBorderKeyToken.Style::class.getTokens(),
        categoryClass = TokenCategory.Border::class
    )

    data object BorderWidth : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = OudsBorderKeyToken.Width::class.getTokens(),
        categoryClass = TokenCategory.Border::class
    )

    data object ColorAction : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_action_label,
        tokens = OudsColorKeyToken.Action::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorAlways : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_always_label,
        tokens = OudsColorKeyToken.Always::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBackground : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_background_label,
        tokens = OudsColorKeyToken.Background::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBorder : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_border_label,
        tokens = OudsColorKeyToken.Border::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorContent : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_content_label,
        tokens = OudsColorKeyToken.Content::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorDecorative : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_decorative_label,
        tokens = OudsColorKeyToken.Decorative::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorOverlay : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_overlay_label,
        tokens = OudsColorKeyToken.Overlay::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object ColorSurface : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_surface_label,
        tokens = OudsColorKeyToken.Surface::class.getTokens(),
        categoryClass = TokenCategory.Color::class
    )

    data object Elevation : TokenProperty<TokenCategory.Elevation>(
        nameRes = null,
        tokens = OudsElevationKeyToken::class.getTokens(),
        categoryClass = TokenCategory.Elevation::class
    )

    data object Grid : TokenProperty<TokenCategory.Grid>(
        nameRes = null,
        tokens = OudsGridKeyToken::class.getTokens(),
        categoryClass = TokenCategory.Grid::class
    )

    data object Opacity : TokenProperty<TokenCategory.Opacity>(
        nameRes = null,
        tokens = OudsOpacityKeyToken::class.getTokens(),
        categoryClass = TokenCategory.Opacity::class
    )

    data object SizeIconDecorative : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconDecorative_label,
        tokens = OudsSizeKeyToken.Icon.Decorative::class.getTokens(),
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SizeIconWithText : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconWithText_label,
        tokens = listOf(
            OudsSizeKeyToken.Icon.WithHeading.ExtraLarge::class,
            OudsSizeKeyToken.Icon.WithHeading.Large::class,
            OudsSizeKeyToken.Icon.WithHeading.Medium::class,
            OudsSizeKeyToken.Icon.WithHeading.Small::class,
            OudsSizeKeyToken.Icon.WithBody.Large::class,
            OudsSizeKeyToken.Icon.WithBody.Medium::class,
            OudsSizeKeyToken.Icon.WithBody.Small::class,
            OudsSizeKeyToken.Icon.WithLabel.ExtraLarge::class,
            OudsSizeKeyToken.Icon.WithLabel.Large::class,
            OudsSizeKeyToken.Icon.WithLabel.Medium::class,
            OudsSizeKeyToken.Icon.WithLabel.Small::class,
        ).flatMap { keyTokenClass ->
            keyTokenClass.getTokens(tokenName = { it.getTokenName(OudsSizeKeyToken.Icon::class).removePrefix("With") })
        },
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SpaceColumnGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_columnGap_label,
        tokens = OudsSpaceKeyToken.ColumnGap::class.getTokens(recursive = false),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceFixed : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_fixed_label,
        tokens = OudsSpaceKeyToken.Fixed::class.getTokens(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInline : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInline_label,
        tokens = OudsSpaceKeyToken.PaddingInline::class.getTokens(recursive = false),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInset : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInset_label,
        tokens = OudsSpaceKeyToken.Inset::class.getTokens(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingStack : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingStack_label,
        tokens = OudsSpaceKeyToken.PaddingBlock::class.getTokens(recursive = false),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceRowGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_rowGap_label,
        tokens = OudsSpaceKeyToken.RowGap::class.getTokens(recursive = false),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceScaled : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_scaled_label,
        tokens = OudsSpaceKeyToken.Scaled::class.getTokens(),
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object Typography : TokenProperty<TokenCategory.Typography>(
        nameRes = null,
        tokens = OudsTypographyKeyToken::class.getTokens(),
        categoryClass = TokenCategory.Typography::class
    )
}
