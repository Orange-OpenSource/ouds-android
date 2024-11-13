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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.dashedBorder
import com.orange.ouds.theme.tokens.OudsColorKeyToken
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
                    .background(OudsColorKeyToken.Background.Emphasized.value),
                painter = painterResource(id = resourceId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SpaceHeader(
    spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = OudsColorKeyToken.Background.Emphasized.value)
            .padding(all = OudsSpaceKeyToken.Fixed.Medium.value)
    ) {
        SpaceHeaderContent(spaceTokenProperty = spaceTokenProperty)
    }
}

@Composable
private fun SpaceHeaderContent(spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>) {
    val dashedBorderWidth = 2.dp

    val externalSpaceValues = when (spaceTokenProperty) {
        TokenProperty.SpaceScaled -> PaddingValues(start = OudsSpaceKeyToken.Fixed.Shorter.value, top = OudsSpaceKeyToken.Fixed.Shortest.value)
        TokenProperty.SpaceFixed,
        TokenProperty.SpacePaddingInlineWithArrow,
        TokenProperty.SpacePaddingInlineWithIcon -> PaddingValues(start = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingInline -> PaddingValues(horizontal = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingStack,
        TokenProperty.SpacePaddingStackWithIcon -> PaddingValues(vertical = OudsSpaceKeyToken.Fixed.Shorter.value)
        TokenProperty.SpacePaddingInset -> PaddingValues(all = OudsSpaceKeyToken.Fixed.Shorter.value)
        else -> PaddingValues(all = 0.dp)
    }

    val internalSpaceColor = when (spaceTokenProperty) {
        TokenProperty.SpaceColumnGap,
        TokenProperty.SpaceColumnGapWithIcon,
        TokenProperty.SpaceColumnGapWithArrow,
        TokenProperty.SpaceRowGap,
        TokenProperty.SpaceRowGapWithIcon -> OudsColorKeyToken.Always.Info.value
        else -> OudsColorKeyToken.Background.Emphasized.value
    }

    val modifier = Modifier
        .dashedBorder(width = dashedBorderWidth, color = OudsColorKeyToken.Content.DefaultOnBgEmphasized.value)
        .padding(all = dashedBorderWidth)
        .background(color = OudsColorKeyToken.Always.Info.value)
        .padding(externalSpaceValues)
        .background(internalSpaceColor)

    val column = remember {
        spaceTokenProperty in listOf(
            TokenProperty.SpaceRowGap,
            TokenProperty.SpaceRowGapWithIcon,
            TokenProperty.SpacePaddingStack,
            TokenProperty.SpacePaddingStackWithIcon
        )
    }

    val arrangement = Arrangement.spacedBy(OudsSpaceKeyToken.Fixed.Shorter.value)

    if (column) {
        Column(
            modifier = modifier,
            verticalArrangement = arrangement
        ) {
            if (spaceTokenProperty == TokenProperty.SpaceRowGap) {
                SpaceHeaderText()
            } else {
                SpaceHeaderImage(
                    modifier = Modifier.fillMaxWidth(),
                    spaceTokenProperty = spaceTokenProperty,
                    alignment = Alignment.CenterStart
                )
            }
            SpaceHeaderText()
        }
    } else {
        Row(
            modifier = modifier.height(IntrinsicSize.Min),
            horizontalArrangement = arrangement
        ) {
            if (spaceTokenProperty == TokenProperty.SpaceColumnGap) {
                SpaceHeaderText(modifier = Modifier.weight(0.5f))
                SpaceHeaderText(modifier = Modifier.weight(0.5f))
            } else {
                SpaceHeaderImage(
                    modifier = Modifier.fillMaxHeight(),
                    spaceTokenProperty = spaceTokenProperty
                )
                SpaceHeaderText()
            }
        }
    }
}

@Composable
private fun SpaceHeaderText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.background(color = OudsColorKeyToken.Background.Emphasized.value),
        text = stringResource(id = R.string.app_tokens_header_text),
        color = OudsColorKeyToken.Content.DefaultOnBgEmphasized.value,
        style = OudsTypographyKeyToken.BodyDefaultMedium.value
    )
}

@Composable
private fun SpaceHeaderImage(
    spaceTokenProperty: TokenProperty<TokenCategory.Dimension.Space>,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center
) {
    val imageResId = when (spaceTokenProperty) {
        TokenProperty.SpacePaddingInlineWithIcon,
        TokenProperty.SpacePaddingStackWithIcon,
        TokenProperty.SpaceColumnGapWithIcon,
        TokenProperty.SpaceRowGapWithIcon -> R.drawable.ic_design_token_figma_no_padding
        TokenProperty.SpacePaddingInlineWithArrow,
        TokenProperty.SpaceColumnGapWithArrow -> R.drawable.ic_chevron_down
        else -> null
    }

    if (imageResId != null) {
        val paddingValue = 1.dp
        val paddingValues = when (spaceTokenProperty) {
            TokenProperty.SpacePaddingInlineWithIcon -> PaddingValues(start = paddingValue)
            TokenProperty.SpacePaddingStackWithIcon -> PaddingValues(top = paddingValue)
            TokenProperty.SpaceColumnGapWithIcon -> PaddingValues(end = paddingValue)
            TokenProperty.SpaceRowGapWithIcon -> PaddingValues(bottom = paddingValue)
            else -> PaddingValues(all = 0.dp)
        }

        Image(
            modifier = modifier
                .background(color = OudsColorKeyToken.Background.Emphasized.value)
                .padding(paddingValues = paddingValues),
            painter = painterResource(id = imageResId),
            contentDescription = null,
            alignment = alignment
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
//        TokenProperty.SpacePaddingInlineWithIcon,
//        TokenProperty.SpacePaddingInlineWithArrow,
//        TokenProperty.SpacePaddingStack,
//        TokenProperty.SpacePaddingStackWithIcon,
//        TokenProperty.SpacePaddingInset,
//        TokenProperty.SpaceColumnGap,
//        TokenProperty.SpaceColumnGapWithIcon,
//        TokenProperty.SpaceColumnGapWithArrow,
//        TokenProperty.SpaceRowGap,
//        TokenProperty.SpaceRowGapWithIcon
    )
