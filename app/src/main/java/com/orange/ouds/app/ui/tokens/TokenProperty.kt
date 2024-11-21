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
import kotlin.reflect.KClass

sealed class TokenProperty<T>(
    @StringRes val nameRes: Int?,
    val tokens: @Composable () -> List<Token<*>>,
    val categoryClass: KClass<T>
) where T : TokenCategory<T> {

    data object BorderRadius : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_radius_label,
        tokens = { OudsBorderKeyToken.Radius.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Border::class
    )

    data object BorderStyle : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_style_label,
        tokens = { OudsBorderKeyToken.Style.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Border::class
    )

    data object BorderWidth : TokenProperty<TokenCategory.Border>(
        nameRes = R.string.app_tokens_border_width_label,
        tokens = { OudsBorderKeyToken.Width.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Border::class
    )

    data object ColorAction : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_action_label,
        tokens = { OudsColorKeyToken.Action::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorAlways : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_always_label,
        tokens = { OudsColorKeyToken.Always::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBackground : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_background_label,
        tokens = { OudsColorKeyToken.Background::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBorder : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_border_label,
        tokens = { OudsColorKeyToken.Border::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorBrand : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_brand_label,
        tokens = { OudsColorKeyToken.Brand::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorContent : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_content_label,
        tokens = { OudsColorKeyToken.Content::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorDecorative : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_decorative_label,
        tokens = { OudsColorKeyToken.Decorative::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorElevation : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_elevation_label,
        tokens = { OudsColorKeyToken.Elevation::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object ColorGradient : TokenProperty<TokenCategory.Color>(
        nameRes = R.string.app_tokens_color_gradient_label,
        tokens = { OudsColorKeyToken.Gradient::class.getTokens<OudsColorKeyToken>() },
        categoryClass = TokenCategory.Color::class
    )

    data object Elevation : TokenProperty<TokenCategory.Elevation>(
        nameRes = null,
        tokens = { OudsElevationKeyToken.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Elevation::class
    )

    data object Grid : TokenProperty<TokenCategory.Grid>(
        nameRes = null,
        tokens = { OudsGridKeyToken.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Grid::class
    )

    data object Opacity : TokenProperty<TokenCategory.Opacity>(
        nameRes = null,
        tokens = { OudsOpacityKeyToken.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Opacity::class
    )

    data object SizeIconDecorative : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconDecorative_label,
        tokens = { OudsSizeKeyToken.IconDecorative.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SizeIconWithText : TokenProperty<TokenCategory.Dimension.Size>(
        nameRes = R.string.app_tokens_dimension_size_iconWithText_label,
        tokens = { OudsSizeKeyToken.IconWithText.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Size::class
    )

    data object SpaceColumnGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_columnGap_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceColumnGapWithIcon : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_columnGapWithIcon_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.WithIcon.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceColumnGapWithArrow : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_columnGapWithArrow_label,
        tokens = { OudsSpaceKeyToken.ColumnGap.WithArrow.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceFixed : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_fixed_label,
        tokens = { OudsSpaceKeyToken.Fixed.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInline : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInline_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInlineWithIcon : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInlineWithIcon_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.WithIcon.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInlineWithArrow : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInlineWithArrow_label,
        tokens = { OudsSpaceKeyToken.PaddingInline.WithArrow.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingInset : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingInset_label,
        tokens = { OudsSpaceKeyToken.Inset.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingStack : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingStack_label,
        tokens = { OudsSpaceKeyToken.PaddingBlock.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpacePaddingStackWithIcon : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_paddingStackWithIcon_label,
        tokens = { OudsSpaceKeyToken.PaddingBlock.WithIcon.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceRowGap : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_rowGap_label,
        tokens = { OudsSpaceKeyToken.RowGap.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceRowGapWithIcon : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_rowGapWithIcon_label,
        tokens = { OudsSpaceKeyToken.RowGap.WithIcon.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object SpaceScaled : TokenProperty<TokenCategory.Dimension.Space>(
        nameRes = R.string.app_tokens_dimension_space_scaled_label,
        tokens = { OudsSpaceKeyToken.Scaled.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Dimension.Space::class
    )

    data object Typography : TokenProperty<TokenCategory.Typography>(
        nameRes = null,
        tokens = { OudsTypographyKeyToken.entries.map { Token(it.name, it.value) } },
        categoryClass = TokenCategory.Typography::class
    )
}

@PublishedApi
internal fun KClass<*>.getRelativeName(parent: KClass<*>): String {
    return qualifiedName.orEmpty().removePrefix("${parent.qualifiedName.orEmpty()}.")
}

@PublishedApi
internal inline fun <reified T> KClass<*>.getNestedObjects(): List<T> {
    return getNestedClassesRecursive().mapNotNull { it.objectInstance }.filterIsInstance<T>()
}

@PublishedApi
internal fun KClass<*>.getNestedClassesRecursive(): List<KClass<*>> {
    return nestedClasses + nestedClasses.flatMap { it.getNestedClassesRecursive() }
}

@Composable
inline fun <reified T : Any> KClass<*>.getTokens(): List<Token<*>> {
    return getNestedObjects<T>().mapNotNull { keyToken ->
        val value = when (keyToken) {
            is OudsColorKeyToken -> keyToken.value
            else -> null
        }
        value?.let { Token(keyToken::class.getRelativeName(this).removeSuffix(".Companion"), it) }
    }.sortedBy { it.name }
}
