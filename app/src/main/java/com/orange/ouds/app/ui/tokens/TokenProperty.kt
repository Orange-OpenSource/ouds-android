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
import androidx.compose.runtime.Composable
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsElevationKeyToken
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.OudsOpacityKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

sealed class TokenProperty(
    @StringRes val nameRes: Int?,
    val tokens: @Composable () -> List<Token<Any>>
) {
    data object BorderRadius : TokenProperty(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = { OudsBorderKeyToken.Radius.entries.map { Token(it.name, it.value) } }
    )

    data object BorderStyle : TokenProperty(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = { OudsBorderKeyToken.Style.entries.map { Token(it.name, it.value) } }
    )

    data object BorderWidth : TokenProperty(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = { OudsBorderKeyToken.Width.entries.map { Token(it.name, it.value) } }
    )

    data object ColorAction : TokenProperty(
        nameRes = R.string.app_tokens_color_action_label,
        tokens = { OudsColorKeyToken.Action.entries.map { Token(it.name, it.value) } }
    )

    data object ColorAlways : TokenProperty(
        nameRes = R.string.app_tokens_color_always_label,
        tokens = { OudsColorKeyToken.Always.entries.map { Token(it.name, it.value) } }
    )

    data object ColorBackground : TokenProperty(
        nameRes = R.string.app_tokens_color_background_label,
        tokens = { OudsColorKeyToken.Background.entries.map { Token(it.name, it.value) } }
    )

    data object ColorBrand : TokenProperty(
        nameRes = R.string.app_tokens_color_brand_label,
        tokens = { OudsColorKeyToken.Brand.entries.map { Token(it.name, it.value) } }
    )

    data object ColorContent : TokenProperty(
        nameRes = R.string.app_tokens_color_content_label,
        tokens = { OudsColorKeyToken.Content.entries.map { Token(it.name, it.value) } }
    )

    data object ColorElevation : TokenProperty(
        nameRes = R.string.app_tokens_color_elevation_label,
        tokens = { OudsColorKeyToken.Elevation.entries.map { Token(it.name, it.value) } }
    )

    data object ColorGradient : TokenProperty(
        nameRes = R.string.app_tokens_color_gradient_label,
        tokens = { OudsColorKeyToken.Gradient.entries.map { Token(it.name, it.value) } }
    )

    data object ColorDecorative : TokenProperty(
        nameRes = R.string.app_tokens_color_decorative_label,
        tokens = { OudsColorKeyToken.Decorative.entries.map { Token(it.name, it.value) } }
    )

    data object Elevation : TokenProperty(
        nameRes = null,
        tokens = { OudsElevationKeyToken.entries.map { Token(it.name, it.value) } }
    )

    data object Grid : TokenProperty(
        nameRes = null,
        tokens = { OudsGridKeyToken.entries.map { Token(it.name, it.value) } }
    )

    data object Opacity : TokenProperty(
        nameRes = null,
        tokens = { OudsOpacityKeyToken.entries.map { Token(it.name, it.value) } }
    )

    data object SizeIconDecorative : TokenProperty(
        nameRes = R.string.app_tokens_dimension_size_iconDecorative_label,
        tokens = { OudsSizeKeyToken.IconDecorative.entries.map { Token(it.name, it.value) } }
    )

    data object SizeIconWithText : TokenProperty(
        nameRes = R.string.app_tokens_dimension_size_iconWithText_label,
        tokens = { OudsSizeKeyToken.IconWithText.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceColumnGap : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_columnGap_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceColumnGapWithIcon : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_columnGapWithIcon_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.WithIcon.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceColumnGapWithArrow : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_columnGapWithArrow_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.WithArrow.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceFixed : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_fixed_label,
        tokens = { OudsSpaceKeyToken.Fixed.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingInline : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInline_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingInlineWithIcon : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInlineWithIcon_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.WithIcon.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingInlineWithArrow : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInlineWithArrow_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.WithArrow.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingInset : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingInset_label,
        tokens = { OudsSpaceKeyToken.Inset.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingStack : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingStack_label,
        tokens = { OudsSpaceKeyToken.PaddingBlock.entries.map { Token(it.name, it.value) } }
    )

    data object SpacePaddingStackWithIcon : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_paddingStackWithIcon_label,
        tokens = { OudsSpaceKeyToken.PaddingBlock.WithIcon.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceRowGap : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_rowGap_label,
        tokens = { OudsSpaceKeyToken.RowGap.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceRowGapWithIcon : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_rowGapWithIcon_label,
        tokens = { OudsSpaceKeyToken.RowGap.WithIcon.entries.map { Token(it.name, it.value) } }
    )

    data object SpaceScaled : TokenProperty(
        nameRes = R.string.app_tokens_dimension_space_scaled_label,
        tokens = { OudsSpaceKeyToken.Scaled.entries.map { Token(it.name, it.value) } }
    )

    data object Typography : TokenProperty(nameRes = null, tokens = { OudsTypographyKeyToken.entries.map { Token(it.name, it.value) } })
}