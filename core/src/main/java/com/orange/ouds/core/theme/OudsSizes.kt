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
import com.orange.ouds.theme.tokens.OudsSizeIconDecorativeKeyToken
import com.orange.ouds.theme.tokens.OudsSizeIconWithTextKeyToken
import com.orange.ouds.theme.tokens.OudsSizeMaxWidthTypeKeyToken
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
fun OudsSizes.fromToken(token: OudsSizeIconDecorativeKeyToken): Dp {
    return when (token) {
        OudsSizeIconDecorativeKeyToken.Shortest -> iconDecorativeShortest
        OudsSizeIconDecorativeKeyToken.Shorter -> iconDecorativeShorter
        OudsSizeIconDecorativeKeyToken.Short -> iconDecorativeShort
        OudsSizeIconDecorativeKeyToken.Medium -> iconDecorativeMedium
        OudsSizeIconDecorativeKeyToken.Tall -> iconDecorativeTall
        OudsSizeIconDecorativeKeyToken.Taller -> iconDecorativeTaller
        OudsSizeIconDecorativeKeyToken.Tallest -> iconDecorativeTallest
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeIconWithTextKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTextValue: Any = when (token) {
        OudsSizeIconWithTextKeyToken.HeadingSmallSizeShort -> iconWithHeadingSmallSizeShort
        OudsSizeIconWithTextKeyToken.HeadingSmallSizeMedium -> iconWithHeadingSmallSizeMedium
        OudsSizeIconWithTextKeyToken.HeadingSmallSizeTall -> iconWithHeadingSmallSizeTall
        OudsSizeIconWithTextKeyToken.HeadingMediumSizeShort -> iconWithHeadingMediumSizeShort
        OudsSizeIconWithTextKeyToken.HeadingMediumSizeMedium -> iconWithHeadingMediumSizeMedium
        OudsSizeIconWithTextKeyToken.HeadingMediumSizeTall -> iconWithHeadingMediumSizeTall
        OudsSizeIconWithTextKeyToken.HeadingLargeSizeShort -> iconWithHeadingLargeSizeShort
        OudsSizeIconWithTextKeyToken.HeadingLargeSizeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeIconWithTextKeyToken.HeadingLargeSizeTall -> iconWithHeadingLargeSizeTall
        OudsSizeIconWithTextKeyToken.HeadingExtraLargeSizeShort -> iconWithHeadingExtraLargeSizeShort
        OudsSizeIconWithTextKeyToken.HeadingExtraLargeSizeMedium -> iconWithHeadingExtraLargeSizeMedium
        OudsSizeIconWithTextKeyToken.HeadingExtraLargeSizeTall -> iconWithHeadingExtraLargeSizeTall
        OudsSizeIconWithTextKeyToken.LabelLargeSizeShorter -> iconWithLabelLargeSizeShorter
        OudsSizeIconWithTextKeyToken.LabelLargeSizeShort -> iconWithLabelLargeSizeShort
        OudsSizeIconWithTextKeyToken.LabelLargeSizeMedium -> iconWithLabelLargeSizeMedium
        OudsSizeIconWithTextKeyToken.LabelLargeSizeTall -> iconWithLabelLargeSizeTall
        OudsSizeIconWithTextKeyToken.LabelLargeSizeTaller -> iconWithLabelLargeSizeTaller
        OudsSizeIconWithTextKeyToken.LabelMediumSizeShort -> iconWithLabelMediumSizeShort
        OudsSizeIconWithTextKeyToken.LabelMediumSizeMedium -> iconWithLabelMediumSizeMedium
        OudsSizeIconWithTextKeyToken.LabelMediumSizeTall -> iconWithLabelMediumSizeTall
        OudsSizeIconWithTextKeyToken.LabelSmallSizeShort -> iconWithLabelSmallSizeShort
        OudsSizeIconWithTextKeyToken.LabelSmallSizeMedium -> iconWithLabelSmallSizeMedium
        OudsSizeIconWithTextKeyToken.LabelSmallSizeTall -> iconWithLabelSmallSizeTall
        OudsSizeIconWithTextKeyToken.LabelExtraLargeSizeShort -> iconWithLabelExtraLargeSizeShort
        OudsSizeIconWithTextKeyToken.LabelExtraLargeSizeMedium -> iconWithLabelExtraLargeSizeMedium
        OudsSizeIconWithTextKeyToken.LabelExtraLargeSizeTall -> iconWithLabelExtraLargeSizeTall
        OudsSizeIconWithTextKeyToken.BodySmallSizeShort -> iconWithBodySmallSizeShort
        OudsSizeIconWithTextKeyToken.BodySmallSizeMedium -> iconWithBodySmallSizeMedium
        OudsSizeIconWithTextKeyToken.BodySmallSizeTall -> iconWithBodySmallSizeTall
        OudsSizeIconWithTextKeyToken.BodyMediumSizeShort -> iconWithBodyMediumSizeShort
        OudsSizeIconWithTextKeyToken.BodyMediumSizeMedium -> iconWithBodyMediumSizeMedium
        OudsSizeIconWithTextKeyToken.BodyMediumSizeTall -> iconWithBodyMediumSizeTall
        OudsSizeIconWithTextKeyToken.BodyLargeSizeShort -> iconWithBodyLargeSizeShort
        OudsSizeIconWithTextKeyToken.BodyLargeSizeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeIconWithTextKeyToken.BodyLargeSizeTall -> iconWithBodyLargeSizeTall
    }

    return if (dimensionSizeIconWithTextValue is OudsAdaptiveTokenValue<*>) {
        dimensionSizeIconWithTextValue.getValue(adaptiveWindowType)
    } else {
        dimensionSizeIconWithTextValue
    } as Dp
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeMaxWidthTypeKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeMaxWidthTypeKeyToken.DisplaySmall -> maxWidthTypeDisplaySmall
        OudsSizeMaxWidthTypeKeyToken.DisplayMedium -> maxWidthTypeDisplayMedium
        OudsSizeMaxWidthTypeKeyToken.DisplayLarge -> maxWidthTypeDisplayLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingSmall -> maxWidthTypeHeadingSmall
        OudsSizeMaxWidthTypeKeyToken.HeadingMedium -> maxWidthTypeHeadingMedium
        OudsSizeMaxWidthTypeKeyToken.HeadingLarge -> maxWidthTypeHeadingLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingExtraLarge -> maxWidthTypeHeadingExtraLarge
        OudsSizeMaxWidthTypeKeyToken.BodySmall -> maxWidthTypeBodySmall
        OudsSizeMaxWidthTypeKeyToken.BodyMedium -> maxWidthTypeBodyMedium
        OudsSizeMaxWidthTypeKeyToken.BodyLarge -> maxWidthTypeBodyLarge
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
val OudsSizeIconDecorativeKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.sizes.fromToken(this)

/**
 * Converts an OUDS icon size with typography token to the local icon size with typography value provided by the theme depending on the window size.
 */
val OudsSizeIconWithTextKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS max width with typography token to the local max width with typography value provided by the theme depending on the window size.
 */
val OudsSizeMaxWidthTypeKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))