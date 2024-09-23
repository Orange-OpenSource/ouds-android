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

package com.orange.ouds.theme.tokens.semantic

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.tokens.global.raw.DimensionRawTokens

data class OudsSizeTokens(
    val iconDecorativeShortest: Dp = DimensionRawTokens.dimension200.dp,
    val iconDecorativeShorter: Dp = DimensionRawTokens.dimension300.dp,
    val iconDecorativeShort: Dp = DimensionRawTokens.dimension400.dp,
    val iconDecorativeMedium: Dp = DimensionRawTokens.dimension500.dp,
    val iconDecorativeTall: Dp = DimensionRawTokens.dimension600.dp,
    val iconDecorativeTaller: Dp = DimensionRawTokens.dimension700.dp,
    val iconDecorativeTallest: Dp = DimensionRawTokens.dimension900.dp,
    val iconWithTypeHeadingSmallShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp
    ),
    val iconWithTypeHeadingSmallMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val iconWithTypeHeadingSmallTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val iconWithTypeHeadingMediumShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val iconWithTypeHeadingMediumMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val iconWithTypeHeadingMediumTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val iconWithTypeHeadingLargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val iconWithTypeHeadingLargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val iconWithTypeHeadingLargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension650.dp
    ),
    val iconWithTypeHeadingXlargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension650.dp
    ),
    val iconWithTypeHeadingXlargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension700.dp
    ),
    val iconWithTypeHeadingXlargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension650.dp,
        DimensionRawTokens.dimension650.dp,
        DimensionRawTokens.dimension800.dp
    ),
    val iconWithTypeBodySmallShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp
    ),
    val iconWithTypeBodySmallMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val iconWithTypeBodySmallTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val iconWithTypeBodyMediumShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val iconWithTypeBodyMediumMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val iconWithTypeBodyMediumTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val iconWithTypeBodyLargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val iconWithTypeBodyLargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val iconWithTypeBodyLargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension350.dp,
        DimensionRawTokens.dimension350.dp,
        DimensionRawTokens.dimension350.dp
    ),
    val maxWidthTypeDisplaySmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeDisplayMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeDisplayLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeHeadingSmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val maxWidthTypeHeadingMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeHeadingLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeHeadingXlarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val maxWidthTypeBodySmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val maxWidthTypeBodyMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val maxWidthTypeBodyLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
)


enum class OudsSizeIconDecorativeKeyToken {
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
}

@Stable
fun OudsSizeTokens.fromToken(token: OudsSizeIconDecorativeKeyToken): Dp {
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

enum class OudsSizeIconWithTypeKeyToken {
    HeadingSmallShort,
    HeadingSmallMedium,
    HeadingSmallTall,
    HeadingMediumShort,
    HeadingMediumMedium,
    HeadingMediumTall,
    HeadingLargeShort,
    HeadingLargeMedium,
    HeadingLargeTall,
    HeadingXlargeShort,
    HeadingXlargeMedium,
    HeadingXlargeTall,
    BodySmallShort,
    BodySmallMedium,
    BodySmallTall,
    BodyMediumShort,
    BodyMediumMedium,
    BodyMediumTall,
    BodyLargeShort,
    BodyLargeMedium,
    BodyLargeTall,
}

@Stable
fun OudsSizeTokens.fromToken(token: OudsSizeIconWithTypeKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTypeToken = when (token) {
        OudsSizeIconWithTypeKeyToken.HeadingSmallShort -> iconWithTypeHeadingSmallShort
        OudsSizeIconWithTypeKeyToken.HeadingSmallMedium -> iconWithTypeHeadingSmallMedium
        OudsSizeIconWithTypeKeyToken.HeadingSmallTall -> iconWithTypeHeadingSmallTall
        OudsSizeIconWithTypeKeyToken.HeadingMediumShort -> iconWithTypeHeadingMediumShort
        OudsSizeIconWithTypeKeyToken.HeadingMediumMedium -> iconWithTypeHeadingMediumMedium
        OudsSizeIconWithTypeKeyToken.HeadingMediumTall -> iconWithTypeHeadingMediumTall
        OudsSizeIconWithTypeKeyToken.HeadingLargeShort -> iconWithTypeHeadingLargeShort
        OudsSizeIconWithTypeKeyToken.HeadingLargeMedium -> iconWithTypeBodyLargeMedium
        OudsSizeIconWithTypeKeyToken.HeadingLargeTall -> iconWithTypeHeadingLargeTall
        OudsSizeIconWithTypeKeyToken.HeadingXlargeShort -> iconWithTypeHeadingXlargeShort
        OudsSizeIconWithTypeKeyToken.HeadingXlargeMedium -> iconWithTypeHeadingXlargeMedium
        OudsSizeIconWithTypeKeyToken.HeadingXlargeTall -> iconWithTypeHeadingXlargeTall
        OudsSizeIconWithTypeKeyToken.BodySmallShort -> iconWithTypeBodySmallShort
        OudsSizeIconWithTypeKeyToken.BodySmallMedium -> iconWithTypeBodySmallMedium
        OudsSizeIconWithTypeKeyToken.BodySmallTall -> iconWithTypeBodySmallTall
        OudsSizeIconWithTypeKeyToken.BodyMediumShort -> iconWithTypeBodyMediumShort
        OudsSizeIconWithTypeKeyToken.BodyMediumMedium -> iconWithTypeBodyMediumMedium
        OudsSizeIconWithTypeKeyToken.BodyMediumTall -> iconWithTypeBodyMediumTall
        OudsSizeIconWithTypeKeyToken.BodyLargeShort -> iconWithTypeBodyLargeShort
        OudsSizeIconWithTypeKeyToken.BodyLargeMedium -> iconWithTypeBodyLargeMedium
        OudsSizeIconWithTypeKeyToken.BodyLargeTall -> iconWithTypeBodyLargeTall
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionSizeIconWithTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionSizeIconWithTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionSizeIconWithTypeToken.medium
    }
}

enum class OudsSizeMaxWidthTypeKeyToken {
    DisplaySmall,
    DisplayMedium,
    DisplayLarge,
    HeadingSmall,
    HeadingMedium,
    HeadingLarge,
    HeadingXlarge,
    BodySmall,
    BodyMedium,
    BodyLarge
}

@Stable
fun OudsSizeTokens.fromToken(token: OudsSizeMaxWidthTypeKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeMaxWidthTypeKeyToken.DisplaySmall -> maxWidthTypeDisplaySmall
        OudsSizeMaxWidthTypeKeyToken.DisplayMedium -> maxWidthTypeDisplayMedium
        OudsSizeMaxWidthTypeKeyToken.DisplayLarge -> maxWidthTypeDisplayLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingSmall -> maxWidthTypeHeadingSmall
        OudsSizeMaxWidthTypeKeyToken.HeadingMedium -> maxWidthTypeHeadingMedium
        OudsSizeMaxWidthTypeKeyToken.HeadingLarge -> maxWidthTypeHeadingLarge
        OudsSizeMaxWidthTypeKeyToken.HeadingXlarge -> maxWidthTypeHeadingXlarge
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