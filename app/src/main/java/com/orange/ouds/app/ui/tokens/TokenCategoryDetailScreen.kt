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
import com.orange.ouds.theme.tokens.OudsSpaceFixedKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

@Composable
fun TokenCategoryDetailScreen(tokenCategory: TokenCategory, onSubcategoryClick: (Long) -> Unit) {

    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsSpaceFixedKeyToken.Medium.value)) {
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
                            .padding(top = OudsSpaceFixedKeyToken.Medium.value)
                            .clickable { onSubcategoryClick(subcategory.id) }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(OudsSpaceFixedKeyToken.Medium.value),
                            text = stringResource(id = subcategory.nameRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.HeadingMedium.value
                        )
                    }
                }
            } else {
                items(tokenCategory.properties) { tokenProperty ->
                    Spacer(modifier = Modifier.height(OudsSpaceFixedKeyToken.Medium.value))

                    tokenProperty.nameRes?.let {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(OudsSpaceFixedKeyToken.Medium.value),
                            text = stringResource(id = tokenProperty.nameRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.HeadingMedium.value
                        )
                    }

                    tokenProperty.tokens().forEach { token ->
                        if (tokenProperty == TokenProperty.SizeIconWithLabel) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = OudsSpaceFixedKeyToken.Medium.value, vertical = OudsSpaceFixedKeyToken.Shorter.value)
                            ) {
                                TokenIllustration(tokenProperty = tokenProperty, token = token)
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = stringResource(id = R.string.app_tokens_dimension_size_iconWithLabelTokenName_label, token.name, token.literalValue),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style = OudsTypographyKeyToken.BodyDefaultMedium.value,
                                    color = OudsColorKeyToken.OnSurfaceVariant.value //TODO use ContentMuted token when available
                                )
                            }
                        } else {
                            val isTypographyProperty = tokenProperty is TokenProperty.Typography

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = OudsSpaceFixedKeyToken.Medium.value, vertical = OudsSpaceFixedKeyToken.Shorter.value)
                            ) {
                                TokenIllustration(tokenProperty = tokenProperty, token = token)

                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = if (isTypographyProperty) OudsSpaceFixedKeyToken.None.value else OudsSpaceFixedKeyToken.Medium.value)
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = token.name,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = if (isTypographyProperty) {
                                            token.value as TextStyle
                                        } else {
                                            OudsTypographyKeyToken.BodyStrongLarge.value
                                        }
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = token.literalValue,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = OudsTypographyKeyToken.BodyDefaultMedium.value.copy(color = OudsColorKeyToken.Tertiary.value), //TODO use ContentMuted token when available
                                        color = OudsColorKeyToken.OnSurfaceVariant.value //TODO use ContentMuted token when available
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
private fun TokenIllustration(tokenProperty: TokenProperty, token: Token<Any>) = when (tokenProperty) {
    is TokenProperty.BorderWidth -> BorderIllustrationBox(width = token.value as Dp)
    is TokenProperty.BorderRadius -> BorderIllustrationBox(shape = RoundedCornerShape(token.value as Dp))
    is TokenProperty.BorderStyle -> BorderIllustrationBox(style = token.value as OudsBorderStyle)
    is TokenProperty.Opacity -> tokenProperty.Illustration(opacity = token.value as Float)
    is TokenProperty.Elevation -> tokenProperty.Illustration(elevation = token.value as Dp)
    is TokenProperty.SizeIconDecorative -> tokenProperty.Illustration(size = token.value as Dp)
    is TokenProperty.SizeIconWithLabel -> tokenProperty.Illustration(size = token.value as Dp, token.name)
    is TokenProperty.SpaceColumnGap, TokenProperty.SpaceFixed, TokenProperty.SpaceScaled -> SpaceIllustrationBox(
        size = token.value as Dp,
        contentAlignment = Alignment.Center
    )
    is TokenProperty.SpacePaddingInline -> tokenProperty.Illustration(size = token.value as Dp, token.name)
    is TokenProperty.SpacePaddingInset -> tokenProperty.Illustration(size = token.value as Dp)
    is TokenProperty.SpacePaddingStack -> SpaceIllustrationBox(size = token.value as Dp, orientation = SpaceOrientation.Vertical)
    is TokenProperty.SpaceRowGap -> SpaceIllustrationBox(size = token.value as Dp, orientation = SpaceOrientation.Vertical, contentAlignment = Alignment.Center)
    is TokenProperty.Typography -> Unit
}

@UiModePreviews.Default
@Composable
private fun PreviewTokenCategoryDetailScreen(
    @PreviewParameter(TokenCategoryDetailScreenPreviewParameterProvider::class) parameter: TokenCategory
) = OudsPreview {
    TokenCategoryDetailScreen(parameter) {}
}

private class TokenCategoryDetailScreenPreviewParameterProvider : BasicPreviewParameterProvider<TokenCategory>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<TokenCategory>
    get() = listOf(
        TokenCategory.Opacity,
        TokenCategory.Elevation
    )