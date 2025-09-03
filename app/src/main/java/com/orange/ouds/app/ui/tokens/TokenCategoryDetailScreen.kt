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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.CodeSnippet
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.ImageIllustration
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.OudsBorderStyle
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsTypography
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TokenCategoryDetailScreen(tokenCategory: TokenCategory<*>, onSubcategoryClick: (Long) -> Unit) {

    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsTheme.spaces.fixed.medium)) {
            item {
                DetailScreenHeader(
                    description = stringResource(id = tokenCategory.descriptionRes),
                    illustration = { ImageIllustration(imageRes = tokenCategory.imageRes) }
                )
                tokenCategory.valueCodeExample?.let { codeExample ->
                    CodeColumn(modifier = Modifier.padding(top = OudsTheme.spaces.fixed.twoExtraSmall), codeExample = codeExample)
                }
            }

            if (tokenCategory.subcategories.isNotEmpty()) {
                items(tokenCategory.subcategories) { subcategory ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = OudsTheme.spaces.fixed.medium)
                            .clickable { onSubcategoryClick(subcategory.id) }
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin),
                            text = stringResource(id = subcategory.nameRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTheme.typography.heading.medium
                        )
                    }
                }
            } else {
                tokenCategory.properties.forEach { tokenProperty ->
                    item {
                        Spacer(modifier = Modifier.height(OudsTheme.spaces.fixed.medium))
                    }
                    stickyHeader {
                        tokenProperty.nameRes?.let {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = OudsTheme.colorScheme.background.primary)
                                    .padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin)
                                    .semantics {
                                        heading()
                                    },
                                text = stringResource(id = tokenProperty.nameRes),
                                color = OudsTheme.colorScheme.content.default,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = OudsTheme.typography.heading.medium
                            )
                        }
                    }
                    item {
                        TokenPropertyHeader(
                            modifier = Modifier
                                .padding(horizontal = OudsTheme.spaces.fixed.medium)
                                .padding(bottom = OudsTheme.spaces.fixed.medium),
                            tokenProperty = tokenProperty
                        )
                    }

                    if (tokenProperty == TokenProperty.SizeIconWithText) {
                        tokenProperty.tokens.groupBy { it.name.substringBeforeLast('.') }.forEach { entry ->
                            val typographyTokenIdentifier = entry.key.removePrefix("sizes.icon.with").replaceFirstChar { it.lowercaseChar() }
                            val typographyToken = getTokens<OudsTypography>()
                                .asOrNull<List<Token<TextStyle>>>()
                                ?.firstOrNull { typographyToken ->
                                    // For instance if entry key is sizes.icon.withLabel.large,
                                    // typography token identifier will be label.large which will match typography token named typography.label.strong.large
                                    typographyToken.name.removePrefix("typography.").replace("strong.", "") == typographyTokenIdentifier
                                }
                            if (typographyToken != null) {
                                item {
                                    SizeIconWithTextHeader(
                                        modifier = Modifier.padding(
                                            horizontal = OudsTheme.spaces.fixed.medium,
                                            vertical = OudsTheme.spaces.fixed.extraSmall
                                        ),
                                        typographyToken = typographyToken,
                                        size = entry.value.last().value() as Dp
                                    )
                                }
                            }
                            items(entry.value) { token ->
                                TokenRow(tokenProperty = tokenProperty, token = token)
                            }
                        }
                    } else {
                        items(tokenProperty.tokens) { token ->
                            TokenRow(tokenProperty = tokenProperty, token = token)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TokenRow(tokenProperty: TokenProperty<out TokenCategory<*>>, token: Token<*>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.extraSmall),
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
    ) {
        TokenIllustration(tokenProperty = tokenProperty, token = token)

        Column(
            modifier = Modifier
                .weight(1f)
                .semantics(mergeDescendants = true) {}
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (tokenProperty == TokenProperty.SizeIconWithText) {
                    token.relativeName.substringAfterLast('.')
                } else {
                    token.relativeName
                },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = if (tokenProperty == TokenProperty.Typography) {
                    token.value() as TextStyle
                } else {
                    OudsTheme.typography.body.strong.large
                }
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = token.literalValue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = OudsTheme.typography.body.default.medium.copy(color = OudsTheme.colorScheme.content.muted)
            )
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
    is TokenProperty.SizeIconDecorative -> SizeIconIllustrationBox(size = token.value() as Dp)
    is TokenProperty.SizeIconWithText -> SizeIconIllustrationBox(size = token.value() as Dp)
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
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    val linkStateDescription = stringResource(if (isExpanded) R.string.app_common_expanded_a11y else R.string.app_common_collapsed_a11y)
    val linkContentDescription = stringResource(R.string.app_tokens_common_viewCodeExample_label)
    val transition = updateTransition(targetState = isExpanded, label = "LinkArrowTransition")
    val linkArrowRotation by transition.animateFloat(
        label = "LinkArrowRotation",
        transitionSpec = {
            tween(durationMillis = 300, easing = FastOutSlowInEasing)
        }
    ) { expanded ->
        if (expanded) 180f else 0f
    }

    Column(modifier = modifier.padding(vertical = OudsTheme.spaces.fixed.medium, horizontal = OudsTheme.grids.margin)) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = OudsTheme.spaces.fixed.small)
                    .clickable {
                        isExpanded = !isExpanded
                    }
                    .clearAndSetSemantics {
                        contentDescription = linkContentDescription
                        stateDescription = linkStateDescription
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.paddingInline.small)
            ) {
                Text(
                    text = stringResource(R.string.app_tokens_common_viewCodeExample_label),
                    style = OudsTheme.typography.label.strong.large,
                    color = OudsTheme.colorScheme.content.default
                )
                Icon(
                    modifier = Modifier.rotate(linkArrowRotation),
                    painter = painterResource(R.drawable.ic_chevron_down),
                    tint = OudsTheme.colorScheme.content.brandPrimary,
                    contentDescription = null
                )
            }
        }
        AnimatedVisibility(visible = isExpanded, enter = fadeIn(tween(delayMillis = 150)) + expandVertically()) {
            CodeSnippet(codeExample)
        }
    }
}

@PreviewLightDark
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