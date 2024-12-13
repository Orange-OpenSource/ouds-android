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

val OudsBorderKeyToken.Radius.order: Int
    get() = when (this) {
        OudsBorderKeyToken.Radius.None -> 0
        OudsBorderKeyToken.Radius.Default -> 1
        OudsBorderKeyToken.Radius.Short -> 2
        OudsBorderKeyToken.Radius.Medium -> 3
        OudsBorderKeyToken.Radius.Tall -> 4
        OudsBorderKeyToken.Radius.Pill -> 5
    }

val OudsBorderKeyToken.Width.order: Int
    get() = when (this) {
        OudsBorderKeyToken.Width.None -> 0
        OudsBorderKeyToken.Width.Default -> 2
        OudsBorderKeyToken.Width.Thin -> 3
        OudsBorderKeyToken.Width.Medium -> 4
        OudsBorderKeyToken.Width.Thick -> 5
        OudsBorderKeyToken.Width.Thicker -> 6
        OudsBorderKeyToken.Width.Focus -> 7
        OudsBorderKeyToken.Width.FocusInset -> 8
    }

val OudsElevationKeyToken.order: Int
    get() = when (this) {
        OudsElevationKeyToken.None -> 0
        OudsElevationKeyToken.Raised -> 1
        OudsElevationKeyToken.OverlayDefault -> 2
        OudsElevationKeyToken.AllSticky -> 3
        OudsElevationKeyToken.Drag -> 4
        OudsElevationKeyToken.OverlayEmphasized -> 5
    }

val OudsOpacityKeyToken.order: Int
    get() = when (this) {
        OudsOpacityKeyToken.Invisible -> 0
        OudsOpacityKeyToken.Weaker -> 1
        OudsOpacityKeyToken.Weak -> 2
        OudsOpacityKeyToken.Medium -> 3
        OudsOpacityKeyToken.Strong -> 5
        OudsOpacityKeyToken.Opaque -> 6
    }

val OudsSizeKeyToken.Icon.order: Int
    get() = when (this) {
        OudsSizeKeyToken.Icon.Decorative.ExtraExtraSmall -> 0
        OudsSizeKeyToken.Icon.Decorative.ExtraSmall -> 1
        OudsSizeKeyToken.Icon.Decorative.Small -> 2
        OudsSizeKeyToken.Icon.Decorative.Medium -> 3
        OudsSizeKeyToken.Icon.Decorative.Large -> 4
        OudsSizeKeyToken.Icon.Decorative.ExtraLarge -> 5
        OudsSizeKeyToken.Icon.Decorative.ExtraExtraLarge -> 6
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeSmall -> 10
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeMedium -> 11
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeLarge -> 12
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeSmall -> 20
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeMedium -> 21
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeLarge -> 22
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeSmall -> 30
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeMedium -> 31
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeLarge -> 32
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeSmall -> 40
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeMedium -> 41
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeLarge -> 42
        OudsSizeKeyToken.Icon.WithBody.Small.SizeSmall -> 50
        OudsSizeKeyToken.Icon.WithBody.Small.SizeMedium -> 51
        OudsSizeKeyToken.Icon.WithBody.Small.SizeLarge -> 52
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeSmall -> 60
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeMedium -> 61
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeLarge -> 62
        OudsSizeKeyToken.Icon.WithBody.Large.SizeSmall -> 70
        OudsSizeKeyToken.Icon.WithBody.Large.SizeMedium -> 71
        OudsSizeKeyToken.Icon.WithBody.Large.SizeLarge -> 72
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeSmall -> 80
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeMedium -> 81
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeLarge -> 82
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeSmall -> 90
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeMedium -> 91
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeLarge -> 92
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall -> 100
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall -> 101
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium -> 102
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeLarge -> 103
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraLarge -> 104
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeSmall -> 110
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeMedium -> 111
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeLarge -> 112
    }

val OudsSpaceKeyToken.ColumnGap.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.ColumnGap.None -> 0
        OudsSpaceKeyToken.ColumnGap.Smash -> 1
        OudsSpaceKeyToken.ColumnGap.Shortest -> 2
        OudsSpaceKeyToken.ColumnGap.Shorter -> 3
        OudsSpaceKeyToken.ColumnGap.Short -> 4
        OudsSpaceKeyToken.ColumnGap.Medium -> 5
        OudsSpaceKeyToken.ColumnGap.Tall -> 6
        OudsSpaceKeyToken.ColumnGap.Taller -> 7
        OudsSpaceKeyToken.ColumnGap.Tallest -> 8
    }

val OudsSpaceKeyToken.Fixed.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.Fixed.None -> 0
        OudsSpaceKeyToken.Fixed.Smash -> 1
        OudsSpaceKeyToken.Fixed.Shortest -> 2
        OudsSpaceKeyToken.Fixed.Shorter -> 3
        OudsSpaceKeyToken.Fixed.Short -> 4
        OudsSpaceKeyToken.Fixed.Medium -> 5
        OudsSpaceKeyToken.Fixed.Tall -> 6
        OudsSpaceKeyToken.Fixed.Taller -> 7
        OudsSpaceKeyToken.Fixed.Tallest -> 8
        OudsSpaceKeyToken.Fixed.Spacious -> 9
        OudsSpaceKeyToken.Fixed.Huge -> 10
        OudsSpaceKeyToken.Fixed.Jumbo -> 11
    }

val OudsSpaceKeyToken.Inset.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.Inset.None -> 0
        OudsSpaceKeyToken.Inset.Smash -> 1
        OudsSpaceKeyToken.Inset.Shortest -> 2
        OudsSpaceKeyToken.Inset.Shorter -> 3
        OudsSpaceKeyToken.Inset.Short -> 4
        OudsSpaceKeyToken.Inset.Medium -> 5
        OudsSpaceKeyToken.Inset.Tall -> 6
        OudsSpaceKeyToken.Inset.Taller -> 7
        OudsSpaceKeyToken.Inset.Tallest -> 8
        OudsSpaceKeyToken.Inset.Spacious -> 9
    }

val OudsSpaceKeyToken.PaddingBlock.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.PaddingBlock.None -> 0
        OudsSpaceKeyToken.PaddingBlock.Smash -> 1
        OudsSpaceKeyToken.PaddingBlock.Shortest -> 2
        OudsSpaceKeyToken.PaddingBlock.Shorter -> 3
        OudsSpaceKeyToken.PaddingBlock.Short -> 4
        OudsSpaceKeyToken.PaddingBlock.Medium -> 5
        OudsSpaceKeyToken.PaddingBlock.Tall -> 6
        OudsSpaceKeyToken.PaddingBlock.Taller -> 7
        OudsSpaceKeyToken.PaddingBlock.Tallest -> 8
        OudsSpaceKeyToken.PaddingBlock.Spacious -> 9
        OudsSpaceKeyToken.PaddingBlock.Huge -> 10
    }

val OudsSpaceKeyToken.PaddingInline.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.PaddingInline.None -> 0
        OudsSpaceKeyToken.PaddingInline.Smash -> 1
        OudsSpaceKeyToken.PaddingInline.Shortest -> 2
        OudsSpaceKeyToken.PaddingInline.Shorter -> 3
        OudsSpaceKeyToken.PaddingInline.Short -> 4
        OudsSpaceKeyToken.PaddingInline.Medium -> 5
        OudsSpaceKeyToken.PaddingInline.Tall -> 6
        OudsSpaceKeyToken.PaddingInline.Taller -> 7
        OudsSpaceKeyToken.PaddingInline.Tallest -> 8
        OudsSpaceKeyToken.PaddingInline.Spacious -> 9
        OudsSpaceKeyToken.PaddingInline.Huge -> 10
    }

val OudsSpaceKeyToken.RowGap.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.RowGap.None -> 0
        OudsSpaceKeyToken.RowGap.Smash -> 1
        OudsSpaceKeyToken.RowGap.Shortest -> 2
        OudsSpaceKeyToken.RowGap.Shorter -> 3
        OudsSpaceKeyToken.RowGap.Short -> 4
        OudsSpaceKeyToken.RowGap.Medium -> 5
        OudsSpaceKeyToken.RowGap.Tall -> 6
    }

val OudsSpaceKeyToken.Scaled.order: Int
    get() = when (this) {
        OudsSpaceKeyToken.Scaled.None -> 0
        OudsSpaceKeyToken.Scaled.Smash -> 1
        OudsSpaceKeyToken.Scaled.Shortest -> 2
        OudsSpaceKeyToken.Scaled.Shorter -> 3
        OudsSpaceKeyToken.Scaled.Short -> 4
        OudsSpaceKeyToken.Scaled.Medium -> 5
        OudsSpaceKeyToken.Scaled.Tall -> 6
        OudsSpaceKeyToken.Scaled.Taller -> 7
        OudsSpaceKeyToken.Scaled.Tallest -> 8
        OudsSpaceKeyToken.Scaled.Spacious -> 9
    }