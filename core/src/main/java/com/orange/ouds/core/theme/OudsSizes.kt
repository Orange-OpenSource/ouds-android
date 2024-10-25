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

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens

data class OudsSizes(
    val iconDecorativeShortest: Dp,
    val iconDecorativeShorter: Dp,
    val iconDecorativeShort: Dp,
    val iconDecorativeMedium: Dp,
    val iconDecorativeTall: Dp,
    val iconDecorativeTaller: Dp,
    val iconDecorativeTallest: Dp,
    val iconWithHeadingSmallSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingSmallSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingSmallSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingMediumSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingMediumSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingMediumSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingLargeSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingLargeSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingLargeSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingExtraLargeSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingExtraLargeSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingExtraLargeSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithLabelSmallSizeShort: Dp,
    val iconWithLabelSmallSizeMedium: Dp,
    val iconWithLabelSmallSizeTall: Dp,
    val iconWithLabelMediumSizeShort: Dp,
    val iconWithLabelMediumSizeMedium: Dp,
    val iconWithLabelMediumSizeTall: Dp,
    val iconWithLabelLargeSizeShorter: Dp,
    val iconWithLabelLargeSizeShort: Dp,
    val iconWithLabelLargeSizeMedium: Dp,
    val iconWithLabelLargeSizeTall: Dp,
    val iconWithLabelLargeSizeTaller: Dp,
    val iconWithLabelExtraLargeSizeShort: Dp,
    val iconWithLabelExtraLargeSizeMedium: Dp,
    val iconWithLabelExtraLargeSizeTall: Dp,
    val iconWithBodySmallSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodySmallSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodySmallSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeTall: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeDisplaySmall: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeDisplayMedium: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeDisplayLarge: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeHeadingSmall: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeHeadingMedium: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeHeadingLarge: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeHeadingExtraLarge: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeBodySmall: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeBodyMedium: OudsAdaptiveTokenValue<Dp>,
    val maxWidthTypeBodyLarge: OudsAdaptiveTokenValue<Dp>,
)

fun OudsSizeSemanticTokens.getSizes() = OudsSizes(
    iconDecorativeShortest = iconDecorativeShortest.dp,
    iconDecorativeShorter = iconDecorativeShorter.dp,
    iconDecorativeShort = iconDecorativeShort.dp,
    iconDecorativeMedium = iconDecorativeMedium.dp,
    iconDecorativeTall = iconDecorativeTall.dp,
    iconDecorativeTaller = iconDecorativeTaller.dp,
    iconDecorativeTallest = iconDecorativeTallest.dp,
    iconWithHeadingSmallSizeShort = OudsAdaptiveTokenValue(
        iconWithHeadingSmallSizeShortMobile.dp,
        iconWithHeadingSmallSizeShortMobile.dp,
        iconWithHeadingSmallSizeShortTablet.dp
    ),
    iconWithHeadingSmallSizeMedium = OudsAdaptiveTokenValue(
        iconWithHeadingSmallSizeMediumMobile.dp,
        iconWithHeadingSmallSizeMediumMobile.dp,
        iconWithHeadingSmallSizeMediumTablet.dp
    ),
    iconWithHeadingSmallSizeTall = OudsAdaptiveTokenValue(
        iconWithHeadingSmallSizeTallMobile.dp,
        iconWithHeadingSmallSizeTallMobile.dp,
        iconWithHeadingSmallSizeTallTablet.dp
    ),
    iconWithHeadingMediumSizeShort = OudsAdaptiveTokenValue(
        iconWithHeadingMediumSizeShortMobile.dp,
        iconWithHeadingMediumSizeShortMobile.dp,
        iconWithHeadingMediumSizeShortTablet.dp
    ),
    iconWithHeadingMediumSizeMedium = OudsAdaptiveTokenValue(
        iconWithHeadingMediumSizeMediumMobile.dp,
        iconWithHeadingMediumSizeMediumMobile.dp,
        iconWithHeadingMediumSizeMediumTablet.dp
    ),
    iconWithHeadingMediumSizeTall = OudsAdaptiveTokenValue(
        iconWithHeadingMediumSizeTallMobile.dp,
        iconWithHeadingMediumSizeTallMobile.dp,
        iconWithHeadingMediumSizeTallTablet.dp
    ),
    iconWithHeadingLargeSizeShort = OudsAdaptiveTokenValue(
        iconWithHeadingLargeSizeShortMobile.dp,
        iconWithHeadingLargeSizeShortMobile.dp,
        iconWithHeadingLargeSizeShortTablet.dp
    ),
    iconWithHeadingLargeSizeMedium = OudsAdaptiveTokenValue(
        iconWithHeadingLargeSizeMediumMobile.dp,
        iconWithHeadingLargeSizeMediumMobile.dp,
        iconWithHeadingLargeSizeMediumTablet.dp
    ),
    iconWithHeadingLargeSizeTall = OudsAdaptiveTokenValue(
        iconWithHeadingLargeSizeTallMobile.dp,
        iconWithHeadingLargeSizeTallMobile.dp,
        iconWithHeadingLargeSizeTallTablet.dp
    ),
    iconWithHeadingExtraLargeSizeShort = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeShortMobile.dp,
        iconWithHeadingXlargeSizeShortMobile.dp,
        iconWithHeadingXlargeSizeShortTablet.dp
    ),
    iconWithHeadingExtraLargeSizeMedium = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeMediumMobile.dp,
        iconWithHeadingXlargeSizeMediumMobile.dp,
        iconWithHeadingXlargeSizeMediumTablet.dp
    ),
    iconWithHeadingExtraLargeSizeTall = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeTallMobile.dp,
        iconWithHeadingXlargeSizeTallMobile.dp,
        iconWithHeadingXlargeSizeTallTablet.dp
    ),
    iconWithLabelSmallSizeShort = iconWithLabelSmallSizeShort.dp,
    iconWithLabelSmallSizeMedium = iconWithLabelSmallSizeMedium.dp,
    iconWithLabelSmallSizeTall = iconWithLabelSmallSizeTall.dp,
    iconWithLabelMediumSizeShort = iconWithLabelMediumSizeShort.dp,
    iconWithLabelMediumSizeMedium = iconWithLabelMediumSizeMedium.dp,
    iconWithLabelMediumSizeTall = iconWithLabelMediumSizeTall.dp,
    iconWithLabelLargeSizeShorter = iconWithLabelLargeSizeShorter.dp,
    iconWithLabelLargeSizeShort = iconWithLabelLargeSizeShort.dp,
    iconWithLabelLargeSizeMedium = iconWithLabelLargeSizeMedium.dp,
    iconWithLabelLargeSizeTall = iconWithLabelLargeSizeTall.dp,
    iconWithLabelLargeSizeTaller = iconWithLabelLargeSizeTaller.dp,
    iconWithLabelExtraLargeSizeShort = iconWithLabelXlargeSizeShort.dp,
    iconWithLabelExtraLargeSizeMedium = iconWithLabelXlargeSizeMedium.dp,
    iconWithLabelExtraLargeSizeTall = iconWithLabelXlargeSizeTall.dp,
    iconWithBodySmallSizeShort = OudsAdaptiveTokenValue(
        iconWithBodySmallSizeShortMobile.dp,
        iconWithBodySmallSizeShortMobile.dp,
        iconWithBodySmallSizeShortTablet.dp
    ),
    iconWithBodySmallSizeMedium = OudsAdaptiveTokenValue(
        iconWithBodySmallSizeMediumMobile.dp,
        iconWithBodySmallSizeMediumMobile.dp,
        iconWithBodySmallSizeMediumTablet.dp
    ),
    iconWithBodySmallSizeTall = OudsAdaptiveTokenValue(
        iconWithBodySmallSizeTallMobile.dp,
        iconWithBodySmallSizeTallMobile.dp,
        iconWithBodySmallSizeTallTablet.dp
    ),
    iconWithBodyMediumSizeShort = OudsAdaptiveTokenValue(
        iconWithBodyMediumSizeShortMobile.dp,
        iconWithBodyMediumSizeShortMobile.dp,
        iconWithBodyMediumSizeShortTablet.dp
    ),
    iconWithBodyMediumSizeMedium = OudsAdaptiveTokenValue(
        iconWithBodyMediumSizeMediumMobile.dp,
        iconWithBodyMediumSizeMediumMobile.dp,
        iconWithBodyMediumSizeMediumTablet.dp
    ),
    iconWithBodyMediumSizeTall = OudsAdaptiveTokenValue(
        iconWithBodyMediumSizeTallMobile.dp,
        iconWithBodyMediumSizeTallMobile.dp,
        iconWithBodyMediumSizeTallTablet.dp
    ),
    iconWithBodyLargeSizeShort = OudsAdaptiveTokenValue(
        iconWithBodyLargeSizeShortMobile.dp,
        iconWithBodyLargeSizeShortMobile.dp,
        iconWithBodyLargeSizeShortTablet.dp
    ),
    iconWithBodyLargeSizeMedium = OudsAdaptiveTokenValue(
        iconWithBodyLargeSizeMediumMobile.dp,
        iconWithBodyLargeSizeMediumMobile.dp,
        iconWithBodyLargeSizeMediumTablet.dp
    ),
    iconWithBodyLargeSizeTall = OudsAdaptiveTokenValue(
        iconWithBodyLargeSizeTallMobile.dp,
        iconWithBodyLargeSizeTallMobile.dp,
        iconWithBodyLargeSizeTallTablet.dp
    ),
    maxWidthTypeDisplaySmall = OudsAdaptiveTokenValue(
        maxWidthTypeDisplaySmallMobile.dp,
        maxWidthTypeDisplaySmallMobile.dp,
        maxWidthTypeDisplaySmallTablet.dp
    ),
    maxWidthTypeDisplayMedium = OudsAdaptiveTokenValue(
        maxWidthTypeDisplayMediumMobile.dp,
        maxWidthTypeDisplayMediumMobile.dp,
        maxWidthTypeDisplayMediumTablet.dp
    ),
    maxWidthTypeDisplayLarge = OudsAdaptiveTokenValue(
        maxWidthTypeDisplayLargeMobile.dp,
        maxWidthTypeDisplayLargeMobile.dp,
        maxWidthTypeDisplayLargeTablet.dp
    ),
    maxWidthTypeHeadingSmall = OudsAdaptiveTokenValue(
        maxWidthTypeHeadingSmallMobile.dp,
        maxWidthTypeHeadingSmallMobile.dp,
        maxWidthTypeHeadingSmallTablet.dp
    ),
    maxWidthTypeHeadingMedium = OudsAdaptiveTokenValue(
        maxWidthTypeHeadingMediumMobile.dp,
        maxWidthTypeHeadingMediumMobile.dp,
        maxWidthTypeHeadingMediumTablet.dp
    ),
    maxWidthTypeHeadingLarge = OudsAdaptiveTokenValue(
        maxWidthTypeHeadingLargeMobile.dp,
        maxWidthTypeHeadingLargeMobile.dp,
        maxWidthTypeHeadingLargeTablet.dp
    ),
    maxWidthTypeHeadingExtraLarge = OudsAdaptiveTokenValue(
        maxWidthTypeHeadingXlargeMobile.dp,
        maxWidthTypeHeadingXlargeMobile.dp,
        maxWidthTypeHeadingXlargeTablet.dp
    ),
    maxWidthTypeBodySmall = OudsAdaptiveTokenValue(
        maxWidthTypeBodySmallMobile.dp,
        maxWidthTypeBodySmallMobile.dp,
        maxWidthTypeBodySmallTablet.dp
    ),
    maxWidthTypeBodyMedium = OudsAdaptiveTokenValue(
        maxWidthTypeBodyMediumMobile.dp,
        maxWidthTypeBodyMediumMobile.dp,
        maxWidthTypeBodyMediumTablet.dp
    ),
    maxWidthTypeBodyLarge = OudsAdaptiveTokenValue(
        maxWidthTypeBodyLargeMobile.dp,
        maxWidthTypeBodyLargeMobile.dp,
        maxWidthTypeBodyLargeTablet.dp
    ),
)

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.IconDecorative): Dp {
    return when (token) {
        OudsSizeKeyToken.IconDecorative.Shortest -> iconDecorativeShortest
        OudsSizeKeyToken.IconDecorative.Shorter -> iconDecorativeShorter
        OudsSizeKeyToken.IconDecorative.Short -> iconDecorativeShort
        OudsSizeKeyToken.IconDecorative.Medium -> iconDecorativeMedium
        OudsSizeKeyToken.IconDecorative.Tall -> iconDecorativeTall
        OudsSizeKeyToken.IconDecorative.Taller -> iconDecorativeTaller
        OudsSizeKeyToken.IconDecorative.Tallest -> iconDecorativeTallest
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.IconWithText, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTextValue: Any = when (token) {
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeShort -> iconWithHeadingSmallSizeShort
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeMedium -> iconWithHeadingSmallSizeMedium
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeTall -> iconWithHeadingSmallSizeTall
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeShort -> iconWithHeadingMediumSizeShort
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeMedium -> iconWithHeadingMediumSizeMedium
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeTall -> iconWithHeadingMediumSizeTall
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeShort -> iconWithHeadingLargeSizeShort
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeTall -> iconWithHeadingLargeSizeTall
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeShort -> iconWithHeadingExtraLargeSizeShort
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeMedium -> iconWithHeadingExtraLargeSizeMedium
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeTall -> iconWithHeadingExtraLargeSizeTall
        OudsSizeKeyToken.IconWithText.LabelLargeSizeShorter -> iconWithLabelLargeSizeShorter
        OudsSizeKeyToken.IconWithText.LabelLargeSizeShort -> iconWithLabelLargeSizeShort
        OudsSizeKeyToken.IconWithText.LabelLargeSizeMedium -> iconWithLabelLargeSizeMedium
        OudsSizeKeyToken.IconWithText.LabelLargeSizeTall -> iconWithLabelLargeSizeTall
        OudsSizeKeyToken.IconWithText.LabelLargeSizeTaller -> iconWithLabelLargeSizeTaller
        OudsSizeKeyToken.IconWithText.LabelMediumSizeShort -> iconWithLabelMediumSizeShort
        OudsSizeKeyToken.IconWithText.LabelMediumSizeMedium -> iconWithLabelMediumSizeMedium
        OudsSizeKeyToken.IconWithText.LabelMediumSizeTall -> iconWithLabelMediumSizeTall
        OudsSizeKeyToken.IconWithText.LabelSmallSizeShort -> iconWithLabelSmallSizeShort
        OudsSizeKeyToken.IconWithText.LabelSmallSizeMedium -> iconWithLabelSmallSizeMedium
        OudsSizeKeyToken.IconWithText.LabelSmallSizeTall -> iconWithLabelSmallSizeTall
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeShort -> iconWithLabelExtraLargeSizeShort
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeMedium -> iconWithLabelExtraLargeSizeMedium
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeTall -> iconWithLabelExtraLargeSizeTall
        OudsSizeKeyToken.IconWithText.BodySmallSizeShort -> iconWithBodySmallSizeShort
        OudsSizeKeyToken.IconWithText.BodySmallSizeMedium -> iconWithBodySmallSizeMedium
        OudsSizeKeyToken.IconWithText.BodySmallSizeTall -> iconWithBodySmallSizeTall
        OudsSizeKeyToken.IconWithText.BodyMediumSizeShort -> iconWithBodyMediumSizeShort
        OudsSizeKeyToken.IconWithText.BodyMediumSizeMedium -> iconWithBodyMediumSizeMedium
        OudsSizeKeyToken.IconWithText.BodyMediumSizeTall -> iconWithBodyMediumSizeTall
        OudsSizeKeyToken.IconWithText.BodyLargeSizeShort -> iconWithBodyLargeSizeShort
        OudsSizeKeyToken.IconWithText.BodyLargeSizeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeKeyToken.IconWithText.BodyLargeSizeTall -> iconWithBodyLargeSizeTall
    }

    return if (dimensionSizeIconWithTextValue is OudsAdaptiveTokenValue<*>) {
        dimensionSizeIconWithTextValue.getValue(adaptiveWindowType)
    } else {
        dimensionSizeIconWithTextValue
    } as Dp
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidthType, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeKeyToken.MaxWidthType.DisplaySmall -> maxWidthTypeDisplaySmall
        OudsSizeKeyToken.MaxWidthType.DisplayMedium -> maxWidthTypeDisplayMedium
        OudsSizeKeyToken.MaxWidthType.DisplayLarge -> maxWidthTypeDisplayLarge
        OudsSizeKeyToken.MaxWidthType.HeadingSmall -> maxWidthTypeHeadingSmall
        OudsSizeKeyToken.MaxWidthType.HeadingMedium -> maxWidthTypeHeadingMedium
        OudsSizeKeyToken.MaxWidthType.HeadingLarge -> maxWidthTypeHeadingLarge
        OudsSizeKeyToken.MaxWidthType.HeadingExtraLarge -> maxWidthTypeHeadingExtraLarge
        OudsSizeKeyToken.MaxWidthType.BodySmall -> maxWidthTypeBodySmall
        OudsSizeKeyToken.MaxWidthType.BodyMedium -> maxWidthTypeBodyMedium
        OudsSizeKeyToken.MaxWidthType.BodyLarge -> maxWidthTypeBodyLarge
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionMaxWidthTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionMaxWidthTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionMaxWidthTypeToken.medium
    }
}

/**
 * Converts an OUDS decorative icon size token to the local decorative icon size value provided by the theme.
 */
val OudsSizeKeyToken.IconDecorative.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.sizes.fromToken(this)

/**
 * Converts an OUDS icon size with typography token to the local icon size with typography value provided by the theme depending on the window size.
 */
val OudsSizeKeyToken.IconWithText.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS max width with typography token to the local max width with typography value provided by the theme depending on the window size.
 */
val OudsSizeKeyToken.MaxWidthType.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))