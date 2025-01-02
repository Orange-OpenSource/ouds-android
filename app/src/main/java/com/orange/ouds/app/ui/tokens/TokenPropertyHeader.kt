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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.getTokens
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

@Composable
fun GridHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Medium.value)
    ) {
        val resourceIds = listOf(
            R.drawable.il_tokens_grid_column_margin,
            R.drawable.il_tokens_grid_min_width,
            R.drawable.il_tokens_grid_max_width
        )
        resourceIds.forEach { resourceId ->
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(OudsColorKeyToken.Surface.Status.Neutral.Muted.value),
                painter = painterResource(id = resourceId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SizeIconWithTextHeader(
    size: Dp,
    tokenName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = OudsColorKeyToken.Surface.Status.Neutral.Muted.value)
            .padding(all = OudsSpaceKeyToken.Fixed.Medium.value),
        horizontalArrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Shorter.value),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(R.drawable.ic_design_token_figma),
            tint = OudsColorKeyToken.Content.Status.Info.value,
            contentDescription = null
        )

        val style = if (LocalInspectionMode.current) {
            OudsTypographyKeyToken.Heading.Small.value
        } else {
            OudsTypographyKeyToken::class.getTokens()
                .asOrNull<List<Token<TextStyle>>>()
                ?.firstOrNull { typographyToken ->
                    typographyToken.name.replace(".Strong", "") == tokenName
                }
                ?.value?.invoke()
                .orElse { LocalTextStyle.current }
        }
        Text(
            modifier = Modifier.weight(1f),
            text = tokenName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = style,
            color = OudsColorKeyToken.Content.Default.value
        )
    }
}

@Composable
fun SpaceHeader(
    spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = OudsColorKeyToken.Surface.Status.Neutral.Muted.value)
            .padding(all = OudsSpaceKeyToken.Fixed.Medium.value)
    ) {
        SpaceHeaderContent(spaceTokenProperty = spaceTokenProperty)
    }
}

@Composable
private fun SpaceHeaderContent(spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>) {
    val dashedBorderWidth = 1.dp

    val externalSpaceValues = when (spaceTokenProperty) {
        TokenProperty.SpaceScaled -> PaddingValues(start = OudsSpaceKeyToken.Fixed.Shorter.value, top = OudsSpaceKeyToken.Fixed.Shortest.value)
        TokenProperty.SpaceFixed -> PaddingValues(start = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingInline -> PaddingValues(horizontal = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingStack -> PaddingValues(vertical = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingInset -> PaddingValues(all = OudsSpaceKeyToken.Fixed.Shorter.value)
        else -> PaddingValues(all = 0.dp)
    }

    val modifier = Modifier
        .dashedBorder(width = dashedBorderWidth, color = OudsColorKeyToken.Content.Default.value)
        .padding(all = dashedBorderWidth)
        .background(color = OudsColorKeyToken.Content.Status.Info.value)
        .padding(externalSpaceValues)

    val column = remember {
        spaceTokenProperty in listOf(
            TokenProperty.SpaceRowGap,
            TokenProperty.SpacePaddingStack,
        )
    }

    val arrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Shorter.value)

    if (column) {
        Column(
            modifier = modifier,
            verticalArrangement = arrangement
        ) {
            if (spaceTokenProperty == TokenProperty.SpaceRowGap) {
                SpaceHeaderText(spaceTokenProperty = spaceTokenProperty)
            }
            SpaceHeaderText(spaceTokenProperty = spaceTokenProperty)
        }
    } else {
        Row(
            modifier = modifier.height(IntrinsicSize.Min),
            horizontalArrangement = arrangement
        ) {
            if (spaceTokenProperty == TokenProperty.SpaceColumnGap) {
                SpaceHeaderText(modifier = Modifier.weight(0.5f), spaceTokenProperty = spaceTokenProperty)
                SpaceHeaderText(modifier = Modifier.weight(0.5f), spaceTokenProperty = spaceTokenProperty)
            } else {
                SpaceHeaderText(spaceTokenProperty = spaceTokenProperty)
            }
        }
    }
}

@Composable
private fun SpaceHeaderText(spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>, modifier: Modifier = Modifier) {
    val textResId = when (spaceTokenProperty) {
        TokenProperty.SpaceColumnGap -> R.string.app_tokens_dimension_space_columnGapHeader_text
        TokenProperty.SpaceFixed -> R.string.app_tokens_dimension_space_fixedHeader_text
        TokenProperty.SpacePaddingInline -> R.string.app_tokens_dimension_space_paddingInlineHeader_text
        TokenProperty.SpacePaddingInset -> R.string.app_tokens_dimension_space_paddingInsetHeader_text
        TokenProperty.SpacePaddingStack -> R.string.app_tokens_dimension_space_paddingStackHeader_text
        TokenProperty.SpaceRowGap -> R.string.app_tokens_dimension_space_rowGapHeader_text
        TokenProperty.SpaceScaled -> R.string.app_tokens_dimension_space_scaledHeader_text
        else -> null
    }
    if (textResId != null) {
        Text(
            modifier = modifier
                .background(color = OudsColorKeyToken.Background.Primary.value)
                .background(color = OudsColorKeyToken.Surface.Status.Neutral.Muted.value),
            text = stringResource(id = textResId),
            color = OudsColorKeyToken.Content.Default.value,
            style = OudsTypographyKeyToken.Body.Default.Medium.value
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewGridHeader() = OudsPreview {
    GridHeader()
}

@UiModePreviews.Default
@Composable
private fun PreviewSizeIconWithTextHeader() = OudsPreview {
    SizeIconWithTextHeader(size = OudsSizeKeyToken.Icon.WithHeading.Small.SizeLarge.value, tokenName = "Heading.Small")
}

@UiModePreviews.Default
@Composable
private fun PreviewSpaceHeader(@PreviewParameter(SpaceHeaderPreviewParameterProvider::class) parameter: TokenProperty<TokenCategory.Dimension.Space>) =
    OudsPreview {
        SpaceHeader(parameter)
    }

private class SpaceHeaderPreviewParameterProvider :
    BasicPreviewParameterProvider<TokenProperty<TokenCategory.Dimension.Space>>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<TokenProperty<TokenCategory.Dimension.Space>>
    get() = listOf(
        TokenProperty.SpaceScaled,
//        TokenProperty.SpaceFixed,
//        TokenProperty.SpacePaddingInline,
//        TokenProperty.SpacePaddingStack,
//        TokenProperty.SpacePaddingInset,
//        TokenProperty.SpaceColumnGap,
//        TokenProperty.SpaceRowGap,
    )
