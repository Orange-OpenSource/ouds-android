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

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TokenCategoryDetailScreen(tokenCategory: TokenCategory<*>, onSubcategoryClick: (Long) -> Unit) {

    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsSpaceKeyToken.Fixed.Medium.value)) {
            item {
                DetailScreenHeader(
                    descriptionRes = tokenCategory.descriptionRes,
                    imageRes = tokenCategory.imageRes
                )
                tokenCategory.valueCodeExample?.let { codeExample ->
                    CodeColumn(modifier = Modifier.padding(top = OudsSpaceKeyToken.Fixed.Shortest.value), codeExample = codeExample)
                }
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
                tokenCategory.properties.forEach { tokenProperty ->
                    item {
                        Spacer(modifier = Modifier.height(OudsSpaceKeyToken.Fixed.Medium.value))
                    }
                    stickyHeader {
                        tokenProperty.nameRes?.let {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = OudsColorKeyToken.Background.Primary.value)
                                    .padding(all = OudsSpaceKeyToken.Fixed.Medium.value)
                                    .semantics {
                                        heading()
                                    },
                                text = stringResource(id = tokenProperty.nameRes),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = OudsTypographyKeyToken.Heading.Medium.value
                            )
                        }
                    }
                    item {
                        TokenPropertyHeader(
                            modifier = Modifier
                                .padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value)
                                .padding(bottom = OudsSpaceKeyToken.Fixed.Medium.value),
                            tokenProperty = tokenProperty
                        )
                    }

                    items(tokenProperty.tokens) { token ->
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

                                Column(modifier = Modifier
                                    .weight(1f)
                                    .semantics(mergeDescendants = true) {}
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = token.name,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        style = if (tokenProperty == TokenProperty.Typography) {
                                            token.value() as TextStyle
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
    is TokenProperty.BorderWidth -> BorderIllustrationBox(width = token.value() as Dp)
    is TokenProperty.BorderRadius -> BorderIllustrationBox(shape = RoundedCornerShape(token.value() as Dp))
    is TokenProperty.BorderStyle -> BorderIllustrationBox(style = token.value() as OudsBorderStyle)
    is TokenProperty.ColorAction, TokenProperty.ColorAlways, TokenProperty.ColorBackground, TokenProperty.ColorBorder, TokenProperty.ColorContent,
    TokenProperty.ColorDecorative, TokenProperty.ColorOverlay, TokenProperty.ColorSurface -> BorderIllustrationBox(backgroundColor = token.value() as Color)
    is TokenProperty.Opacity -> OpacityIllustrationBox(opacity = token.value() as Float)
    is TokenProperty.Elevation -> ElevationIllustrationSurface(elevation = token.value() as Dp)
    is TokenProperty.SizeIconDecorative -> SizeIconDecorativeIllustrationBox(size = token.value() as Dp)
    is TokenProperty.SizeIconWithText -> SizeIconWithTextIllustrationRow(size = token.value() as Dp, token.name)
    is TokenProperty.SpaceColumnGap, TokenProperty.SpaceFixed, TokenProperty.SpaceScaled -> SpaceIllustrationBox(
        size = token.value() as Dp,
        contentAlignment = Alignment.Center
    )
    TokenProperty.SpacePaddingInline -> SpaceIllustrationBox(size = token.value() as Dp)
    TokenProperty.SpacePaddingInset -> SpacePaddingInsetIllustrationBox(size = token.value() as Dp)
    TokenProperty.SpacePaddingStack -> SpaceIllustrationBox(size = token.value() as Dp, orientation = SpaceOrientation.Vertical)
    TokenProperty.SpaceRowGap -> SpaceIllustrationBox(size = token.value() as Dp, orientation = SpaceOrientation.Vertical, contentAlignment = Alignment.Center)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CodeColumn(codeExample: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val linkStateDescription = stringResource(if (isExpanded) R.string.app_common_expanded_a11y else R.string.app_common_collapsed_a11y)
    val linkContentDescription = stringResource(R.string.app_tokens_viewCodeExample_label)
    val transition = updateTransition(targetState = isExpanded, label = "LinkArrowTransition")
    val linkArrowRotation by transition.animateFloat(
        label = "LinkArrowRotation",
        transitionSpec = {
            tween(durationMillis = 300, easing = FastOutSlowInEasing)
        }
    ) { expanded ->
        if (expanded) 180f else 0f
    }

    Column(modifier = modifier.padding(horizontal = OudsSpaceKeyToken.Fixed.Medium.value)) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = OudsSpaceKeyToken.Fixed.Short.value)
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .clearAndSetSemantics {
                        contentDescription = linkContentDescription
                        stateDescription = linkStateDescription
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.PaddingInline.Short.value)
            ) {
                Text(
                    text = stringResource(R.string.app_tokens_viewCodeExample_label),
                    style = OudsTypographyKeyToken.Label.Strong.Large.value
                )
                Icon(
                    modifier = Modifier.rotate(linkArrowRotation),
                    painter = painterResource(R.drawable.ic_chevron_down),
                    tint = OudsColorKeyToken.Content.Brand.Primary.value,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(visible = isExpanded, enter = fadeIn(tween(delayMillis = 150)) + expandVertically()) {
            Box(
                modifier = Modifier
                    .background(color = OudsColorKeyToken.Background.Secondary.value)
                    .border(width = 1.dp, color = OudsColorKeyToken.Border.Default.value, shape = RectangleShape)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Short.value),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = OudsSpaceKeyToken.Fixed.Medium.value)
                            .padding(start = OudsSpaceKeyToken.Fixed.Medium.value), text = codeExample, style = TextStyle(fontFamily = FontFamily.Monospace)
                    )
                    IconButton(onClick = { copyCodeToClipboard(context, codeExample, clipboardManager) }) {
                        Icon(painter = painterResource(R.drawable.ic_copy), contentDescription = stringResource(R.string.app_common_copyCode_a11y))
                    }
                }
            }
        }
    }
}

private fun copyCodeToClipboard(context: Context, code: String, clipboardManager: ClipboardManager) {
    clipboardManager.setText(AnnotatedString(code))
    Toast.makeText(context, context.getString(R.string.app_common_codeCopied_text), Toast.LENGTH_SHORT).show()
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