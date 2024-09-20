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

data class OudsDimensionTokens(
    val spaceNone: Dp = DimensionRawTokens.dimension0.dp,
    val spaceSmash: Dp = DimensionRawTokens.dimension25.dp,
    val spaceShortest: Dp = DimensionRawTokens.dimension50.dp,
    val spaceShorter: Dp = DimensionRawTokens.dimension100.dp,
    val spaceShort: Dp = DimensionRawTokens.dimension150.dp,
    val spaceMedium: Dp = DimensionRawTokens.dimension200.dp,
    val spaceTall: Dp = DimensionRawTokens.dimension300.dp,
    val spaceTaller: Dp = DimensionRawTokens.dimension400.dp,
    val spaceTallest: Dp = DimensionRawTokens.dimension500.dp,
    val spaceSpacious: Dp = DimensionRawTokens.dimension600.dp,
    val spaceHuge: Dp = DimensionRawTokens.dimension700.dp,
    val spaceJumbo: Dp = DimensionRawTokens.dimension800.dp,
    val spaceAdaptableNone: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension0.dp,
        DimensionRawTokens.dimension0.dp,
        DimensionRawTokens.dimension0.dp
    ),
    val spaceAdaptableSmash: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension25.dp,
        DimensionRawTokens.dimension25.dp,
        DimensionRawTokens.dimension50.dp
    ),
    val spaceAdaptableShortest: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension50.dp,
        DimensionRawTokens.dimension50.dp,
        DimensionRawTokens.dimension100.dp
    ),
    val spaceAdaptableShorter: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension100.dp,
        DimensionRawTokens.dimension100.dp,
        DimensionRawTokens.dimension150.dp
    ),
    val spaceAdaptableShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val spaceAdaptableMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val spaceAdaptableTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension400.dp
    ),
    val spaceAdaptableTaller: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val spaceAdaptableTallest: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val spaceAdaptableSpacious: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension700.dp
    ),
    val sizeIconDecorativeShortest: Dp = DimensionRawTokens.dimension200.dp,
    val sizeIconDecorativeShorter: Dp = DimensionRawTokens.dimension300.dp,
    val sizeIconDecorativeShort: Dp = DimensionRawTokens.dimension400.dp,
    val sizeIconDecorativeMedium: Dp = DimensionRawTokens.dimension500.dp,
    val sizeIconDecorativeTall: Dp = DimensionRawTokens.dimension600.dp,
    val sizeIconDecorativeTaller: Dp = DimensionRawTokens.dimension700.dp,
    val sizeIconDecorativeTallest: Dp = DimensionRawTokens.dimension900.dp,
    val sizeIconWithTypeHeadingSmallShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp
    ),
    val sizeIconWithTypeHeadingSmallMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val sizeIconWithTypeHeadingSmallTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val sizeIconWithTypeHeadingMediumShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val sizeIconWithTypeHeadingMediumMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val sizeIconWithTypeHeadingMediumTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val sizeIconWithTypeHeadingLargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension550.dp
    ),
    val sizeIconWithTypeHeadingLargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val sizeIconWithTypeHeadingLargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension650.dp
    ),
    val sizeIconWithTypeHeadingXlargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension550.dp,
        DimensionRawTokens.dimension650.dp
    ),
    val sizeIconWithTypeHeadingXlargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension700.dp
    ),
    val sizeIconWithTypeHeadingXlargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension650.dp,
        DimensionRawTokens.dimension650.dp,
        DimensionRawTokens.dimension800.dp
    ),
    val sizeIconWithTypeBodySmallShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp
    ),
    val sizeIconWithTypeBodySmallMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val sizeIconWithTypeBodySmallTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val sizeIconWithTypeBodyMediumShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val sizeIconWithTypeBodyMediumMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val sizeIconWithTypeBodyMediumTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val sizeIconWithTypeBodyLargeShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp,
        DimensionRawTokens.dimension250.dp
    ),
    val sizeIconWithTypeBodyLargeMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val sizeIconWithTypeBodyLargeTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension350.dp,
        DimensionRawTokens.dimension350.dp,
        DimensionRawTokens.dimension350.dp
    ),
    val sizeMaxWidthTypeDisplaySmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeDisplayMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeDisplayLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeHeadingSmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val sizeMaxWidthTypeHeadingMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeHeadingLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeHeadingXlarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp,
        DimensionRawTokens.dimension9000.dp
    ),
    val sizeMaxWidthTypeBodySmall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val sizeMaxWidthTypeBodyMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
    val sizeMaxWidthTypeBodyLarge: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp,
        DimensionRawTokens.dimension6000.dp
    ),
)

enum class OudsDimensionSpaceToken {
    None,
    Smash,
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
    Spacious,
    Huge,
    Jumbo
}

@Stable
fun OudsDimensionTokens.fromToken(token: OudsDimensionSpaceToken): Dp {
    return when (token) {
        OudsDimensionSpaceToken.None -> spaceNone
        OudsDimensionSpaceToken.Smash -> spaceSmash
        OudsDimensionSpaceToken.Shortest -> spaceShortest
        OudsDimensionSpaceToken.Shorter -> spaceShorter
        OudsDimensionSpaceToken.Short -> spaceShort
        OudsDimensionSpaceToken.Medium -> spaceMedium
        OudsDimensionSpaceToken.Tall -> spaceTall
        OudsDimensionSpaceToken.Taller -> spaceTaller
        OudsDimensionSpaceToken.Tallest -> spaceTallest
        OudsDimensionSpaceToken.Spacious -> spaceSpacious
        OudsDimensionSpaceToken.Huge -> spaceHuge
        OudsDimensionSpaceToken.Jumbo -> spaceJumbo
    }
}

enum class OudsDimensionAdaptableSpaceToken {
    None,
    Smash,
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
    Spacious
}

@Stable
fun OudsDimensionTokens.fromToken(token: OudsDimensionAdaptableSpaceToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
        OudsDimensionAdaptableSpaceToken.None -> spaceAdaptableNone
        OudsDimensionAdaptableSpaceToken.Smash -> spaceAdaptableSmash
        OudsDimensionAdaptableSpaceToken.Shortest -> spaceAdaptableShortest
        OudsDimensionAdaptableSpaceToken.Shorter -> spaceAdaptableShorter
        OudsDimensionAdaptableSpaceToken.Short -> spaceAdaptableShort
        OudsDimensionAdaptableSpaceToken.Medium -> spaceAdaptableMedium
        OudsDimensionAdaptableSpaceToken.Tall -> spaceAdaptableTall
        OudsDimensionAdaptableSpaceToken.Taller -> spaceAdaptableTaller
        OudsDimensionAdaptableSpaceToken.Tallest -> spaceAdaptableTallest
        OudsDimensionAdaptableSpaceToken.Spacious -> spaceAdaptableSpacious
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionAdaptableSpaceToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionAdaptableSpaceToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionAdaptableSpaceToken.medium
    }
}

enum class OudsDimensionSizeIconDecorativeToken {
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
}

@Stable
fun OudsDimensionTokens.fromToken(token: OudsDimensionSizeIconDecorativeToken): Dp {
    return when (token) {
        OudsDimensionSizeIconDecorativeToken.Shortest -> sizeIconDecorativeShortest
        OudsDimensionSizeIconDecorativeToken.Shorter -> sizeIconDecorativeShorter
        OudsDimensionSizeIconDecorativeToken.Short -> sizeIconDecorativeShort
        OudsDimensionSizeIconDecorativeToken.Medium -> sizeIconDecorativeMedium
        OudsDimensionSizeIconDecorativeToken.Tall -> sizeIconDecorativeTall
        OudsDimensionSizeIconDecorativeToken.Taller -> sizeIconDecorativeTaller
        OudsDimensionSizeIconDecorativeToken.Tallest -> sizeIconDecorativeTallest
    }
}

enum class OudsDimensionSizeIconWithTypeToken {
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
fun OudsDimensionTokens.fromToken(token: OudsDimensionSizeIconWithTypeToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTypeToken = when (token) {
        OudsDimensionSizeIconWithTypeToken.HeadingSmallShort -> sizeIconWithTypeHeadingSmallShort
        OudsDimensionSizeIconWithTypeToken.HeadingSmallMedium -> sizeIconWithTypeHeadingSmallMedium
        OudsDimensionSizeIconWithTypeToken.HeadingSmallTall -> sizeIconWithTypeHeadingSmallTall
        OudsDimensionSizeIconWithTypeToken.HeadingMediumShort -> sizeIconWithTypeHeadingMediumShort
        OudsDimensionSizeIconWithTypeToken.HeadingMediumMedium -> sizeIconWithTypeHeadingMediumMedium
        OudsDimensionSizeIconWithTypeToken.HeadingMediumTall -> sizeIconWithTypeHeadingMediumTall
        OudsDimensionSizeIconWithTypeToken.HeadingLargeShort -> sizeIconWithTypeHeadingLargeShort
        OudsDimensionSizeIconWithTypeToken.HeadingLargeMedium -> sizeIconWithTypeBodyLargeMedium
        OudsDimensionSizeIconWithTypeToken.HeadingLargeTall -> sizeIconWithTypeHeadingLargeTall
        OudsDimensionSizeIconWithTypeToken.HeadingXlargeShort -> sizeIconWithTypeHeadingXlargeShort
        OudsDimensionSizeIconWithTypeToken.HeadingXlargeMedium -> sizeIconWithTypeHeadingXlargeMedium
        OudsDimensionSizeIconWithTypeToken.HeadingXlargeTall -> sizeIconWithTypeHeadingXlargeTall
        OudsDimensionSizeIconWithTypeToken.BodySmallShort -> sizeIconWithTypeBodySmallShort
        OudsDimensionSizeIconWithTypeToken.BodySmallMedium -> sizeIconWithTypeBodySmallMedium
        OudsDimensionSizeIconWithTypeToken.BodySmallTall -> sizeIconWithTypeBodySmallTall
        OudsDimensionSizeIconWithTypeToken.BodyMediumShort -> sizeIconWithTypeBodyMediumShort
        OudsDimensionSizeIconWithTypeToken.BodyMediumMedium -> sizeIconWithTypeBodyMediumMedium
        OudsDimensionSizeIconWithTypeToken.BodyMediumTall -> sizeIconWithTypeBodyMediumTall
        OudsDimensionSizeIconWithTypeToken.BodyLargeShort -> sizeIconWithTypeBodyLargeShort
        OudsDimensionSizeIconWithTypeToken.BodyLargeMedium -> sizeIconWithTypeBodyLargeMedium
        OudsDimensionSizeIconWithTypeToken.BodyLargeTall -> sizeIconWithTypeBodyLargeTall
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionSizeIconWithTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionSizeIconWithTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionSizeIconWithTypeToken.medium
    }
}

enum class OudsDimensionMaxWidthTypeToken {
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
fun OudsDimensionTokens.fromToken(token: OudsDimensionMaxWidthTypeToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsDimensionMaxWidthTypeToken.DisplaySmall -> sizeMaxWidthTypeDisplaySmall
        OudsDimensionMaxWidthTypeToken.DisplayMedium -> sizeMaxWidthTypeDisplayMedium
        OudsDimensionMaxWidthTypeToken.DisplayLarge -> sizeMaxWidthTypeDisplayLarge
        OudsDimensionMaxWidthTypeToken.HeadingSmall -> sizeMaxWidthTypeHeadingSmall
        OudsDimensionMaxWidthTypeToken.HeadingMedium -> sizeMaxWidthTypeHeadingMedium
        OudsDimensionMaxWidthTypeToken.HeadingLarge -> sizeMaxWidthTypeHeadingLarge
        OudsDimensionMaxWidthTypeToken.HeadingXlarge -> sizeMaxWidthTypeHeadingXlarge
        OudsDimensionMaxWidthTypeToken.BodySmall -> sizeMaxWidthTypeBodySmall
        OudsDimensionMaxWidthTypeToken.BodyMedium -> sizeMaxWidthTypeBodyMedium
        OudsDimensionMaxWidthTypeToken.BodyLarge -> sizeMaxWidthTypeBodyLarge
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionMaxWidthTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionMaxWidthTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionMaxWidthTypeToken.medium
    }
}