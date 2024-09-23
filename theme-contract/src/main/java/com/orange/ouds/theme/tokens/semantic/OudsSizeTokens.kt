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


enum class OudsSizeIconDecorativeToken {
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
}

@Stable
fun OudsSizeTokens.fromToken(token: OudsSizeIconDecorativeToken): Dp {
    return when (token) {
        OudsSizeIconDecorativeToken.Shortest -> iconDecorativeShortest
        OudsSizeIconDecorativeToken.Shorter -> iconDecorativeShorter
        OudsSizeIconDecorativeToken.Short -> iconDecorativeShort
        OudsSizeIconDecorativeToken.Medium -> iconDecorativeMedium
        OudsSizeIconDecorativeToken.Tall -> iconDecorativeTall
        OudsSizeIconDecorativeToken.Taller -> iconDecorativeTaller
        OudsSizeIconDecorativeToken.Tallest -> iconDecorativeTallest
    }
}

enum class OudsSizeIconWithTypeToken {
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
fun OudsSizeTokens.fromToken(token: OudsSizeIconWithTypeToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTypeToken = when (token) {
        OudsSizeIconWithTypeToken.HeadingSmallShort -> iconWithTypeHeadingSmallShort
        OudsSizeIconWithTypeToken.HeadingSmallMedium -> iconWithTypeHeadingSmallMedium
        OudsSizeIconWithTypeToken.HeadingSmallTall -> iconWithTypeHeadingSmallTall
        OudsSizeIconWithTypeToken.HeadingMediumShort -> iconWithTypeHeadingMediumShort
        OudsSizeIconWithTypeToken.HeadingMediumMedium -> iconWithTypeHeadingMediumMedium
        OudsSizeIconWithTypeToken.HeadingMediumTall -> iconWithTypeHeadingMediumTall
        OudsSizeIconWithTypeToken.HeadingLargeShort -> iconWithTypeHeadingLargeShort
        OudsSizeIconWithTypeToken.HeadingLargeMedium -> iconWithTypeBodyLargeMedium
        OudsSizeIconWithTypeToken.HeadingLargeTall -> iconWithTypeHeadingLargeTall
        OudsSizeIconWithTypeToken.HeadingXlargeShort -> iconWithTypeHeadingXlargeShort
        OudsSizeIconWithTypeToken.HeadingXlargeMedium -> iconWithTypeHeadingXlargeMedium
        OudsSizeIconWithTypeToken.HeadingXlargeTall -> iconWithTypeHeadingXlargeTall
        OudsSizeIconWithTypeToken.BodySmallShort -> iconWithTypeBodySmallShort
        OudsSizeIconWithTypeToken.BodySmallMedium -> iconWithTypeBodySmallMedium
        OudsSizeIconWithTypeToken.BodySmallTall -> iconWithTypeBodySmallTall
        OudsSizeIconWithTypeToken.BodyMediumShort -> iconWithTypeBodyMediumShort
        OudsSizeIconWithTypeToken.BodyMediumMedium -> iconWithTypeBodyMediumMedium
        OudsSizeIconWithTypeToken.BodyMediumTall -> iconWithTypeBodyMediumTall
        OudsSizeIconWithTypeToken.BodyLargeShort -> iconWithTypeBodyLargeShort
        OudsSizeIconWithTypeToken.BodyLargeMedium -> iconWithTypeBodyLargeMedium
        OudsSizeIconWithTypeToken.BodyLargeTall -> iconWithTypeBodyLargeTall
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionSizeIconWithTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionSizeIconWithTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionSizeIconWithTypeToken.medium
    }
}

enum class OudsSizeMaxWidthTypeToken {
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
fun OudsSizeTokens.fromToken(token: OudsSizeMaxWidthTypeToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeMaxWidthTypeToken.DisplaySmall -> maxWidthTypeDisplaySmall
        OudsSizeMaxWidthTypeToken.DisplayMedium -> maxWidthTypeDisplayMedium
        OudsSizeMaxWidthTypeToken.DisplayLarge -> maxWidthTypeDisplayLarge
        OudsSizeMaxWidthTypeToken.HeadingSmall -> maxWidthTypeHeadingSmall
        OudsSizeMaxWidthTypeToken.HeadingMedium -> maxWidthTypeHeadingMedium
        OudsSizeMaxWidthTypeToken.HeadingLarge -> maxWidthTypeHeadingLarge
        OudsSizeMaxWidthTypeToken.HeadingXlarge -> maxWidthTypeHeadingXlarge
        OudsSizeMaxWidthTypeToken.BodySmall -> maxWidthTypeBodySmall
        OudsSizeMaxWidthTypeToken.BodyMedium -> maxWidthTypeBodyMedium
        OudsSizeMaxWidthTypeToken.BodyLarge -> maxWidthTypeBodyLarge
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionMaxWidthTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionMaxWidthTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionMaxWidthTypeToken.medium
    }
}