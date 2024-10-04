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
import com.orange.ouds.theme.tokens.OudsSizeIconWithTypeKeyToken
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
    val iconWithHeadingXlargeSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingXlargeSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithHeadingXlargeSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodySmallSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodySmallSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodySmallSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyMediumSizeTall: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeShort: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeMedium: OudsAdaptiveTokenValue<Dp>,
    val iconWithBodyLargeSizeTall: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthDisplaySmall: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthDisplayMedium: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthDisplayLarge: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthHeadingSmall: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthHeadingMedium: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthHeadingLarge: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthHeadingXlarge: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthBodySmall: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthBodyMedium: OudsAdaptiveTokenValue<Dp>,
    val typeMaxWidthBodyLarge: OudsAdaptiveTokenValue<Dp>,
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
    iconWithHeadingXlargeSizeShort = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeShortMobile.dp,
        iconWithHeadingXlargeSizeShortMobile.dp,
        iconWithHeadingXlargeSizeShortTablet.dp
    ),
    iconWithHeadingXlargeSizeMedium = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeMediumMobile.dp,
        iconWithHeadingXlargeSizeMediumMobile.dp,
        iconWithHeadingXlargeSizeMediumTablet.dp
    ),
    iconWithHeadingXlargeSizeTall = OudsAdaptiveTokenValue(
        iconWithHeadingXlargeSizeTallMobile.dp,
        iconWithHeadingXlargeSizeTallMobile.dp,
        iconWithHeadingXlargeSizeTallTablet.dp
    ),
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
    typeMaxWidthDisplaySmall = OudsAdaptiveTokenValue(
        typeMaxWidthDisplaySmallMobile.dp,
        typeMaxWidthDisplaySmallMobile.dp,
        typeMaxWidthDisplaySmallTablet.dp
    ),
    typeMaxWidthDisplayMedium = OudsAdaptiveTokenValue(
        typeMaxWidthDisplayMediumMobile.dp,
        typeMaxWidthDisplayMediumMobile.dp,
        typeMaxWidthDisplayMediumTablet.dp
    ),
    typeMaxWidthDisplayLarge = OudsAdaptiveTokenValue(
        typeMaxWidthDisplayLargeMobile.dp,
        typeMaxWidthDisplayLargeMobile.dp,
        typeMaxWidthDisplayLargeTablet.dp
    ),
    typeMaxWidthHeadingSmall = OudsAdaptiveTokenValue(
        typeMaxWidthHeadingSmallMobile.dp,
        typeMaxWidthHeadingSmallMobile.dp,
        typeMaxWidthHeadingSmallTablet.dp
    ),
    typeMaxWidthHeadingMedium = OudsAdaptiveTokenValue(
        typeMaxWidthHeadingMediumMobile.dp,
        typeMaxWidthHeadingMediumMobile.dp,
        typeMaxWidthHeadingMediumTablet.dp
    ),
    typeMaxWidthHeadingLarge = OudsAdaptiveTokenValue(
        typeMaxWidthHeadingLargeMobile.dp,
        typeMaxWidthHeadingLargeMobile.dp,
        typeMaxWidthHeadingLargeTablet.dp
    ),
    typeMaxWidthHeadingXlarge = OudsAdaptiveTokenValue(
        typeMaxWidthHeadingXlargeMobile.dp,
        typeMaxWidthHeadingXlargeMobile.dp,
        typeMaxWidthHeadingXlargeTablet.dp
    ),
    typeMaxWidthBodySmall = OudsAdaptiveTokenValue(
        typeMaxWidthBodySmallMobile.dp,
        typeMaxWidthBodySmallMobile.dp,
        typeMaxWidthBodySmallTablet.dp
    ),
    typeMaxWidthBodyMedium = OudsAdaptiveTokenValue(
        typeMaxWidthBodyMediumMobile.dp,
        typeMaxWidthBodyMediumMobile.dp,
        typeMaxWidthBodyMediumTablet.dp
    ),
    typeMaxWidthBodyLarge = OudsAdaptiveTokenValue(
        typeMaxWidthBodyLargeMobile.dp,
        typeMaxWidthBodyLargeMobile.dp,
        typeMaxWidthBodyLargeTablet.dp
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
fun OudsSizes.fromToken(token: OudsSizeIconWithTypeKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTypeToken = when (token) {
        OudsSizeIconWithTypeKeyToken.HeadingSmallShort -> iconWithHeadingSmallSizeShort
        OudsSizeIconWithTypeKeyToken.HeadingSmallMedium -> iconWithHeadingSmallSizeMedium
        OudsSizeIconWithTypeKeyToken.HeadingSmallTall -> iconWithHeadingSmallSizeTall
        OudsSizeIconWithTypeKeyToken.HeadingMediumShort -> iconWithHeadingMediumSizeShort
        OudsSizeIconWithTypeKeyToken.HeadingMediumMedium -> iconWithHeadingMediumSizeMedium
        OudsSizeIconWithTypeKeyToken.HeadingMediumTall -> iconWithHeadingMediumSizeTall
        OudsSizeIconWithTypeKeyToken.HeadingLargeShort -> iconWithHeadingLargeSizeShort
        OudsSizeIconWithTypeKeyToken.HeadingLargeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeIconWithTypeKeyToken.HeadingLargeTall -> iconWithHeadingLargeSizeTall
        OudsSizeIconWithTypeKeyToken.HeadingXlargeShort -> iconWithHeadingXlargeSizeShort
        OudsSizeIconWithTypeKeyToken.HeadingXlargeMedium -> iconWithHeadingXlargeSizeMedium
        OudsSizeIconWithTypeKeyToken.HeadingXlargeTall -> iconWithHeadingXlargeSizeTall
        OudsSizeIconWithTypeKeyToken.BodySmallShort -> iconWithBodySmallSizeShort
        OudsSizeIconWithTypeKeyToken.BodySmallMedium -> iconWithBodySmallSizeMedium
        OudsSizeIconWithTypeKeyToken.BodySmallTall -> iconWithBodySmallSizeTall
        OudsSizeIconWithTypeKeyToken.BodyMediumShort -> iconWithBodyMediumSizeShort
        OudsSizeIconWithTypeKeyToken.BodyMediumMedium -> iconWithBodyMediumSizeMedium
        OudsSizeIconWithTypeKeyToken.BodyMediumTall -> iconWithBodyMediumSizeTall
        OudsSizeIconWithTypeKeyToken.BodyLargeShort -> iconWithBodyLargeSizeShort
        OudsSizeIconWithTypeKeyToken.BodyLargeMedium -> iconWithBodyLargeSizeMedium
        OudsSizeIconWithTypeKeyToken.BodyLargeTall -> iconWithBodyLargeSizeTall
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionSizeIconWithTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionSizeIconWithTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionSizeIconWithTypeToken.medium
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeMaxWidthTypeKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeMaxWidthTypeKeyToken.DisplaySmall -> typeMaxWidthDisplaySmall
        OudsSizeMaxWidthTypeKeyToken.DisplayMedium -> typeMaxWidthDisplayMedium
        OudsSizeMaxWidthTypeKeyToken.DisplayLarge -> typeMaxWidthDisplayLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingSmall -> typeMaxWidthHeadingSmall
        OudsSizeMaxWidthTypeKeyToken.HeadingMedium -> typeMaxWidthHeadingMedium
        OudsSizeMaxWidthTypeKeyToken.HeadingLarge -> typeMaxWidthHeadingLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingXlarge -> typeMaxWidthHeadingXlarge
        OudsSizeMaxWidthTypeKeyToken.BodySmall -> typeMaxWidthBodySmall
        OudsSizeMaxWidthTypeKeyToken.BodyMedium -> typeMaxWidthBodyMedium
        OudsSizeMaxWidthTypeKeyToken.BodyLarge -> typeMaxWidthBodyLarge
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
val OudsSizeIconWithTypeKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS max width with typography token to the local max width with typography value provided by the theme depending on the window size.
 */
val OudsSizeMaxWidthTypeKeyToken.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))