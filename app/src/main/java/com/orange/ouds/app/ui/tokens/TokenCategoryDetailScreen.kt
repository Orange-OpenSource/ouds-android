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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

@Composable
fun TokenCategoryDetailScreen(tokenCategory: TokenCategory<*>, onSubcategoryClick: (Long) -> Unit) {

    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsSpaceKeyToken.Fixed.Medium.value)) {
            item {
                DetailScreenHeader(
                    descriptionRes = tokenCategory.descriptionRes,
                    imageRes = tokenCategory.imageRes
                )
            }

            if (tokenCategory.subcategories.isNotEmpty()) {
                items(tokenCategory.subcategories) { subcategory ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = OudsSpaceKeyToken.Fixed.Medium.value)
                            .clickable { onSubcategoryClick(subcategory.id) }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(OudsSpaceKeyToken.Fixed.Medium.value),
                            text = stringResource(id = subcategory.nameRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.Heading.Medium.value
                        )
                    }
                }
            } else {
                items(tokenCategory.properties) { tokenProperty ->
                    Spacer(modifier = Modifier.height(OudsSpaceKeyToken.Fixed.Medium.value))

                    tokenProperty.nameRes?.let {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
                            text = stringResource(id = tokenProperty.nameRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.Heading.Medium.value
                        )
                    }

                    TokenPropertyHeader(
                        modifier = Modifier
                            .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value)
                            .padding(bottom = OudsSpaceKeyToken.Fixed.Medium.value),
                        tokenProperty = tokenProperty
                    )

                    tokenProperty.tokens().forEach { token ->
                        if (tokenProperty == TokenProperty.SizeIconWithText) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value, vertical = OudsSpaceKeyToken.Fixed.Shorter.value)
                            ) {
                                TokenIllustration(tokenProperty = tokenProperty, token = token)
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(id = R.string.app_tokens_dimension_size_iconWithTextTokenName_label, token.name, token.literalValue),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = OudsTypographyKeyToken.Body.Default.Medium.value,
                                    color = OudsColorKeyToken.Content.Muted.value
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value, vertical = OudsSpaceKeyToken.Fixed.Shorter.value),
                                horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Medium.value)
                            ) {
                                TokenIllustration(tokenProperty = tokenProperty, token = token)

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = token.name,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = if (tokenProperty == TokenProperty.Typography) {
                                            token.value as TextStyle
                                        } else {
                                            OudsTypographyKeyToken.Body.Strong.Large.value
                                        }
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = token.literalValue,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = OudsTypographyKeyToken.Body.Default.Medium.value.copy(color = OudsColorKeyToken.Content.Muted.value)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TokenIllustration(tokenProperty: TokenProperty<*>, token: Token<*>) = when (tokenProperty) {
    is TokenProperty.BorderWidth -> BorderIllustrationBox(width = token.value as Dp)
    is TokenProperty.BorderRadius -> BorderIllustrationBox(shape = RoundedCornerShape(token.value as Dp))
    is TokenProperty.BorderStyle -> BorderIllustrationBox(style = token.value as OudsBorderStyle)
    is TokenProperty.ColorAction, TokenProperty.ColorAlways, TokenProperty.ColorBackground, TokenProperty.ColorBorder, TokenProperty.ColorBrand, TokenProperty.ColorContent,
    TokenProperty.ColorElevation, TokenProperty.ColorGradient, TokenProperty.ColorDecorative -> BorderIllustrationBox(backgroundColor = token.value as Color)
    is TokenProperty.Opacity -> OpacityIllustrationBox(opacity = token.value as Float)
    is TokenProperty.Elevation -> ElevationIllustrationSurface(elevation = token.value as Dp)
    is TokenProperty.SizeIconDecorative -> SizeIconDecorativeIllustrationBox(size = token.value as Dp)
    is TokenProperty.SizeIconWithText -> SizeIconWithTextIllustrationRow(size = token.value as Dp, token.name)
    is TokenProperty.SpaceColumnGap, TokenProperty.SpaceFixed, TokenProperty.SpaceScaled -> SpaceIllustrationBox(
        size = token.value as Dp,
        contentAlignment = Alignment.Center
    )
    TokenProperty.SpacePaddingInline -> SpaceIllustrationBox(size = token.value as Dp)
    TokenProperty.SpacePaddingInlineWithIcon, TokenProperty.SpaceColumnGapWithIcon -> SpaceWithIconIllustrationRow(size = token.value as Dp)
    TokenProperty.SpacePaddingInlineWithArrow, TokenProperty.SpaceColumnGapWithArrow -> SpaceWithArrowIllustrationRow(size = token.value as Dp)
    TokenProperty.SpacePaddingInset -> SpacePaddingInsetIllustrationBox(size = token.value as Dp)
    TokenProperty.SpacePaddingStack -> SpaceIllustrationBox(size = token.value as Dp, orientation = SpaceOrientation.Vertical)
    TokenProperty.SpaceRowGap -> SpaceIllustrationBox(size = token.value as Dp, orientation = SpaceOrientation.Vertical, contentAlignment = Alignment.Center)
    TokenProperty.SpaceRowGapWithIcon -> SpaceWithIconIllustrationColumn(size = token.value as Dp, verticalArrangement = Arrangement.Bottom)
    TokenProperty.SpacePaddingStackWithIcon -> SpaceWithIconIllustrationColumn(size = token.value as Dp)
    TokenProperty.Typography, TokenProperty.Grid -> Unit
}

@Composable
private fun TokenPropertyHeader(tokenProperty: TokenProperty<*>, modifier: Modifier = Modifier) {
    @Suppress("UNCHECKED_CAST")
    when (tokenProperty.categoryClass) {
        TokenCategory.Grid::class -> GridHeader(modifier = modifier)
        TokenCategory.Dimension.Space::class -> SpaceHeader(
            modifier = modifier,
            spaceTokenProperty = tokenProperty as TokenProperty<TokenCategory.Dimension.Space>
        )
        else -> {}
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewTokenCategoryDetailScreen(
    @PreviewParameter(TokenCategoryDetailScreenPreviewParameterProvider::class) parameter: TokenCategory<*>
) = OudsPreview {
    TokenCategoryDetailScreen(parameter) {}
}

private class TokenCategoryDetailScreenPreviewParameterProvider : BasicPreviewParameterProvider<TokenCategory<*>>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<TokenCategory<*>>
    get() = listOf(
        TokenCategory.Border,
//        TokenCategory.Color,
//        TokenCategory.Dimension,
//        TokenCategory.Dimension.Size,
//        TokenCategory.Dimension.Space,
//        TokenCategory.Elevation,
//        TokenCategory.Grid,
//        TokenCategory.Opacity,
//        TokenCategory.Typography,
    )