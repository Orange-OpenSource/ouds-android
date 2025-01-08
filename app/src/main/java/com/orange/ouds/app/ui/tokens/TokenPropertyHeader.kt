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
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

@Composable
fun GridHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
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
                    .background(OudsTheme.colorScheme.surfaceColors.statusNeutralMuted),
                painter = painterResource(id = resourceId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SizeIconWithTextHeader(
    size: Dp,
    typographyTokenName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = OudsTheme.colorScheme.surfaceColors.statusNeutralMuted)
            .padding(all = OudsTheme.spaces.fixed.medium),
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shorter),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(R.drawable.ic_design_token_figma),
            tint = OudsTheme.colorScheme.contentColors.statusInfo,
            contentDescription = null
        )

        val style = if (LocalInspectionMode.current) {
            OudsTheme.typography.headingSmall
        } else {
            getTokens<OudsTypographyKeyToken>()
                .asOrNull<List<Token<TextStyle>>>()
                ?.firstOrNull { typographyToken ->
                    typographyToken.name.replace(".Strong", "") == typographyTokenName
                }
                ?.value?.invoke()
                .orElse { LocalTextStyle.current }
        }
        Text(
            modifier = Modifier.weight(1f),
            text = typographyTokenName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = style,
            color = OudsTheme.colorScheme.contentColors.default
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
            .background(color = OudsTheme.colorScheme.surfaceColors.statusNeutralMuted)
            .padding(all = OudsTheme.spaces.fixed.medium)
    ) {
        SpaceHeaderContent(spaceTokenProperty = spaceTokenProperty)
    }
}

@Composable
private fun SpaceHeaderContent(spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>) {
    val dashedBorderWidth = 1.dp

    val externalSpaceValues = when (spaceTokenProperty) {
        TokenProperty.SpaceScaled -> PaddingValues(start = OudsTheme.spaces.fixed.shorter, top = OudsTheme.spaces.fixed.shortest)
        TokenProperty.SpaceFixed -> PaddingValues(start = OudsTheme.spaces.fixed.shorter)
        TokenProperty.SpacePaddingInline -> PaddingValues(horizontal = OudsTheme.spaces.fixed.shorter)
        TokenProperty.SpacePaddingStack -> PaddingValues(vertical = OudsTheme.spaces.fixed.shorter)
        TokenProperty.SpacePaddingInset -> PaddingValues(all = OudsTheme.spaces.fixed.shorter)
        else -> PaddingValues(all = 0.dp)
    }

    val modifier = Modifier
        .dashedBorder(width = dashedBorderWidth, color = OudsTheme.colorScheme.contentColors.default)
        .padding(all = dashedBorderWidth)
        .background(color = OudsTheme.colorScheme.contentColors.statusInfo)
        .padding(externalSpaceValues)

    val column = remember {
        spaceTokenProperty in listOf(
            TokenProperty.SpaceRowGap,
            TokenProperty.SpacePaddingStack,
        )
    }

    val arrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shorter)

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
                .background(color = OudsTheme.colorScheme.backgroundColors.primary)
                .background(color = OudsTheme.colorScheme.surfaceColors.statusNeutralMuted),
            text = stringResource(id = textResId),
            color = OudsTheme.colorScheme.contentColors.default,
            style = OudsTheme.typography.bodyDefaultMedium
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
    SizeIconWithTextHeader(size = OudsTheme.sizes.icon.withHeading.small.sizeLarge, typographyTokenName = "Heading.Small")
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
