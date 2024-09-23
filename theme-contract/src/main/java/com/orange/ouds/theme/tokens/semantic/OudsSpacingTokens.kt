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

data class OudsSpacingTokens(
    val fixedNone: Dp = DimensionRawTokens.dimension0.dp,
    val fixedSmash: Dp = DimensionRawTokens.dimension25.dp,
    val fixedShortest: Dp = DimensionRawTokens.dimension50.dp,
    val fixedShorter: Dp = DimensionRawTokens.dimension100.dp,
    val fixedShort: Dp = DimensionRawTokens.dimension150.dp,
    val fixedMedium: Dp = DimensionRawTokens.dimension200.dp,
    val fixedTall: Dp = DimensionRawTokens.dimension300.dp,
    val fixedTaller: Dp = DimensionRawTokens.dimension400.dp,
    val fixedTallest: Dp = DimensionRawTokens.dimension500.dp,
    val fixedSpacious: Dp = DimensionRawTokens.dimension600.dp,
    val fixedHuge: Dp = DimensionRawTokens.dimension700.dp,
    val fixedJumbo: Dp = DimensionRawTokens.dimension800.dp,
    val scaledNone: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension0.dp,
        DimensionRawTokens.dimension0.dp,
        DimensionRawTokens.dimension0.dp
    ),
    val scaledSmash: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension25.dp,
        DimensionRawTokens.dimension25.dp,
        DimensionRawTokens.dimension50.dp
    ),
    val scaledShortest: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension50.dp,
        DimensionRawTokens.dimension50.dp,
        DimensionRawTokens.dimension100.dp
    ),
    val scaledShorter: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension100.dp,
        DimensionRawTokens.dimension100.dp,
        DimensionRawTokens.dimension150.dp
    ),
    val scaledShort: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension150.dp,
        DimensionRawTokens.dimension200.dp
    ),
    val scaledMedium: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension200.dp,
        DimensionRawTokens.dimension300.dp
    ),
    val scaledTall: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension300.dp,
        DimensionRawTokens.dimension400.dp
    ),
    val scaledTaller: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension400.dp,
        DimensionRawTokens.dimension500.dp
    ),
    val scaledTallest: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension500.dp,
        DimensionRawTokens.dimension600.dp
    ),
    val scaledSpacious: OudsAdaptiveTokenValue = OudsAdaptiveTokenValue(
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension600.dp,
        DimensionRawTokens.dimension700.dp
    ),
    val paddingInlineNone: Dp = DimensionRawTokens.dimension0.dp,
    val paddingInlineShorter: Dp = DimensionRawTokens.dimension50.dp,
    val paddingInlineShort: Dp = DimensionRawTokens.dimension100.dp,
    val paddingInlineMedium: Dp = DimensionRawTokens.dimension200.dp,
    val paddingInlineTall: Dp = DimensionRawTokens.dimension300.dp,
    val paddingInlineTaller: Dp = DimensionRawTokens.dimension100.dp,
    val paddingInlineTallest: Dp = DimensionRawTokens.dimension150.dp,
    val paddingInlineIsIconNone: Dp = DimensionRawTokens.dimension0.dp,
    val paddingInlineIsIconShorter: Dp = DimensionRawTokens.dimension25.dp,
    val paddingInlineIsIconShort: Dp = DimensionRawTokens.dimension50.dp,
    val paddingInlineIsIconMedium: Dp = DimensionRawTokens.dimension75.dp,
    val paddingInlineIsIconTall: Dp = DimensionRawTokens.dimension100.dp,
    val paddingInlineIsIconTaller: Dp = DimensionRawTokens.dimension200.dp,
    val paddingInlineIsIconTallest: Dp = DimensionRawTokens.dimension300.dp,
    val paddingInlineIsArrowNone: Dp = DimensionRawTokens.dimension0.dp,
    val paddingInlineIsArrowShorter: Dp = DimensionRawTokens.dimension25.dp,
    val paddingInlineIsArrowShort: Dp = DimensionRawTokens.dimension50.dp,
    val paddingInlineIsArrowMedium: Dp = DimensionRawTokens.dimension75.dp,
    val paddingInlineIsArrowTall: Dp = DimensionRawTokens.dimension100.dp,
    val paddingInlineIsArrowTaller: Dp = DimensionRawTokens.dimension200.dp,
    val paddingInlineIsArrowTallest: Dp = DimensionRawTokens.dimension300.dp,
    val paddingBlockNone: Dp = DimensionRawTokens.dimension0.dp,
    val paddingBlockShortest: Dp = DimensionRawTokens.dimension50.dp,
    val paddingBlockShorter: Dp = DimensionRawTokens.dimension100.dp,
    val paddingBlockShort: Dp = DimensionRawTokens.dimension150.dp,
    val paddingBlockMedium: Dp = DimensionRawTokens.dimension200.dp,
    val paddingBlockTall: Dp = DimensionRawTokens.dimension300.dp,
    val paddingBlockTaller: Dp = DimensionRawTokens.dimension400.dp,
    val paddingBlockIsIconNone: Dp = DimensionRawTokens.dimension0.dp,
    val paddingBlockIsIconShorter: Dp = DimensionRawTokens.dimension25.dp,
    val paddingBlockIsIconShort: Dp = DimensionRawTokens.dimension50.dp,
    val paddingBlockIsIconMedium: Dp = DimensionRawTokens.dimension75.dp,
    val paddingBlockIsIconTall: Dp = DimensionRawTokens.dimension100.dp,
    val paddingBlockIsIconTaller: Dp = DimensionRawTokens.dimension150.dp,
    val paddingBlockIsIconTallest: Dp = DimensionRawTokens.dimension200.dp,
    val insetNone: Dp = DimensionRawTokens.dimension0.dp,
    val insetSmash: Dp = DimensionRawTokens.dimension25.dp,
    val insetShortest: Dp = DimensionRawTokens.dimension50.dp,
    val insetShorter: Dp = DimensionRawTokens.dimension75.dp,
    val insetShort: Dp = DimensionRawTokens.dimension100.dp,
    val insetMedium: Dp = DimensionRawTokens.dimension150.dp,
    val insetTall: Dp = DimensionRawTokens.dimension200.dp,
    val insetTaller: Dp = DimensionRawTokens.dimension300.dp,
    val insetTallest: Dp = DimensionRawTokens.dimension400.dp,
    val insetSpacious: Dp = DimensionRawTokens.dimension650.dp,
    val columnGapNone: Dp = DimensionRawTokens.dimension0.dp,
    val columnGapShorter: Dp = DimensionRawTokens.dimension50.dp,
    val columnGapShort: Dp = DimensionRawTokens.dimension100.dp,
    val columnGapMedium: Dp = DimensionRawTokens.dimension200.dp,
    val columnGapTall: Dp = DimensionRawTokens.dimension300.dp,
    val columnGapTaller: Dp = DimensionRawTokens.dimension400.dp,
    val columnGapIsIconNone: Dp = DimensionRawTokens.dimension0.dp,
    val columnGapIsIconShorter: Dp = DimensionRawTokens.dimension25.dp,
    val columnGapIsIconShort: Dp = DimensionRawTokens.dimension50.dp,
    val columnGapIsIconMedium: Dp = DimensionRawTokens.dimension75.dp,
    val columnGapIsIconTall: Dp = DimensionRawTokens.dimension100.dp,
    val columnGapIsIconTaller: Dp = DimensionRawTokens.dimension200.dp,
    val columnGapIsArrowNone: Dp = DimensionRawTokens.dimension0.dp,
    val columnGapIsArrowShorter: Dp = DimensionRawTokens.dimension25.dp,
    val columnGapIsArrowShort: Dp = DimensionRawTokens.dimension50.dp,
    val columnGapIsArrowMedium: Dp = DimensionRawTokens.dimension75.dp,
    val columnGapIsArrowTall: Dp = DimensionRawTokens.dimension100.dp,
    val columnGapIsArrowTaller: Dp = DimensionRawTokens.dimension200.dp,
    val rowGapNone: Dp = DimensionRawTokens.dimension0.dp,
    val rowGapShorter: Dp = DimensionRawTokens.dimension25.dp,
    val rowGapShort: Dp = DimensionRawTokens.dimension50.dp,
    val rowGapMedium: Dp = DimensionRawTokens.dimension75.dp,
    val rowGapTall: Dp = DimensionRawTokens.dimension100.dp,
    val rowGapTaller: Dp = DimensionRawTokens.dimension200.dp,
    val rowGapIsIconNone: Dp = DimensionRawTokens.dimension0.dp,
    val rowGapIsIconShorter: Dp = DimensionRawTokens.dimension25.dp,
    val rowGapIsIconShort: Dp = DimensionRawTokens.dimension50.dp,
    val rowGapIsIconMedium: Dp = DimensionRawTokens.dimension75.dp,
    val rowGapIsIconTall: Dp = DimensionRawTokens.dimension100.dp,
    val rowGapIsIconTaller: Dp = DimensionRawTokens.dimension200.dp,
)

enum class OudsSpacingFixedKeyToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingFixedKeyToken): Dp {
    return when (token) {
        OudsSpacingFixedKeyToken.None -> fixedNone
        OudsSpacingFixedKeyToken.Smash -> fixedSmash
        OudsSpacingFixedKeyToken.Shortest -> fixedShortest
        OudsSpacingFixedKeyToken.Shorter -> fixedShorter
        OudsSpacingFixedKeyToken.Short -> fixedShort
        OudsSpacingFixedKeyToken.Medium -> fixedMedium
        OudsSpacingFixedKeyToken.Tall -> fixedTall
        OudsSpacingFixedKeyToken.Taller -> fixedTaller
        OudsSpacingFixedKeyToken.Tallest -> fixedTallest
        OudsSpacingFixedKeyToken.Spacious -> fixedSpacious
        OudsSpacingFixedKeyToken.Huge -> fixedHuge
        OudsSpacingFixedKeyToken.Jumbo -> fixedJumbo
    }
}

enum class OudsSpacingScaledKeyToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingScaledKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
        OudsSpacingScaledKeyToken.None -> scaledNone
        OudsSpacingScaledKeyToken.Smash -> scaledSmash
        OudsSpacingScaledKeyToken.Shortest -> scaledShortest
        OudsSpacingScaledKeyToken.Shorter -> scaledShorter
        OudsSpacingScaledKeyToken.Short -> scaledShort
        OudsSpacingScaledKeyToken.Medium -> scaledMedium
        OudsSpacingScaledKeyToken.Tall -> scaledTall
        OudsSpacingScaledKeyToken.Taller -> scaledTaller
        OudsSpacingScaledKeyToken.Tallest -> scaledTallest
        OudsSpacingScaledKeyToken.Spacious -> scaledSpacious
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionAdaptableSpaceToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionAdaptableSpaceToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionAdaptableSpaceToken.medium
    }
}

enum class OudsSpacingPaddingInlineKeyToken {
    None,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    Tallest,
    IsIconNone,
    IsIconShorter,
    IsIconShort,
    IsIconMedium,
    IsIconTall,
    IsIconTaller,
    IsIconTallest,
    IsArrowNone,
    IsArrowShorter,
    IsArrowShort,
    IsArrowMedium,
    IsArrowTall,
    IsArrowTaller,
    IsArrowTallest,
}

@Stable
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingInlineKeyToken): Dp {
    return when (token) {
        OudsSpacingPaddingInlineKeyToken.None -> paddingInlineNone
        OudsSpacingPaddingInlineKeyToken.Shorter -> paddingInlineShorter
        OudsSpacingPaddingInlineKeyToken.Short -> paddingInlineShort
        OudsSpacingPaddingInlineKeyToken.Medium -> paddingInlineMedium
        OudsSpacingPaddingInlineKeyToken.Tall -> paddingInlineTall
        OudsSpacingPaddingInlineKeyToken.Taller -> paddingInlineTaller
        OudsSpacingPaddingInlineKeyToken.Tallest -> paddingInlineTallest
        OudsSpacingPaddingInlineKeyToken.IsIconNone -> paddingInlineIsIconNone
        OudsSpacingPaddingInlineKeyToken.IsIconShorter -> paddingInlineIsIconShorter
        OudsSpacingPaddingInlineKeyToken.IsIconShort -> paddingInlineIsIconShort
        OudsSpacingPaddingInlineKeyToken.IsIconMedium -> paddingInlineIsIconMedium
        OudsSpacingPaddingInlineKeyToken.IsIconTall -> paddingInlineIsIconTall
        OudsSpacingPaddingInlineKeyToken.IsIconTaller -> paddingInlineIsIconTaller
        OudsSpacingPaddingInlineKeyToken.IsIconTallest -> paddingInlineIsIconTallest
        OudsSpacingPaddingInlineKeyToken.IsArrowNone -> paddingInlineIsArrowNone
        OudsSpacingPaddingInlineKeyToken.IsArrowShorter -> paddingInlineIsArrowShorter
        OudsSpacingPaddingInlineKeyToken.IsArrowShort -> paddingInlineIsArrowShort
        OudsSpacingPaddingInlineKeyToken.IsArrowMedium -> paddingInlineIsArrowMedium
        OudsSpacingPaddingInlineKeyToken.IsArrowTall -> paddingInlineIsArrowTall
        OudsSpacingPaddingInlineKeyToken.IsArrowTaller -> paddingInlineIsArrowTaller
        OudsSpacingPaddingInlineKeyToken.IsArrowTallest -> paddingInlineIsArrowTallest
    }
}

enum class OudsSpacingPaddingBlockKeyToken {
    None,
    Shortest,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    IsIconNone,
    IsIconShorter,
    IsIconShort,
    IsIconMedium,
    IsIconTall,
    IsIconTaller,
    IsIconTallest,
}

@Stable
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingBlockKeyToken): Dp {
    return when (token) {
        OudsSpacingPaddingBlockKeyToken.None -> paddingBlockNone
        OudsSpacingPaddingBlockKeyToken.Shortest -> paddingBlockShortest
        OudsSpacingPaddingBlockKeyToken.Shorter -> paddingBlockShorter
        OudsSpacingPaddingBlockKeyToken.Short -> paddingBlockShort
        OudsSpacingPaddingBlockKeyToken.Medium -> paddingBlockMedium
        OudsSpacingPaddingBlockKeyToken.Tall -> paddingBlockTall
        OudsSpacingPaddingBlockKeyToken.Taller -> paddingBlockTaller
        OudsSpacingPaddingBlockKeyToken.IsIconNone -> paddingBlockIsIconNone
        OudsSpacingPaddingBlockKeyToken.IsIconShorter -> paddingBlockIsIconShorter
        OudsSpacingPaddingBlockKeyToken.IsIconShort -> paddingBlockIsIconShort
        OudsSpacingPaddingBlockKeyToken.IsIconMedium -> paddingBlockIsIconMedium
        OudsSpacingPaddingBlockKeyToken.IsIconTall -> paddingBlockIsIconTall
        OudsSpacingPaddingBlockKeyToken.IsIconTaller -> paddingBlockIsIconTaller
        OudsSpacingPaddingBlockKeyToken.IsIconTallest -> paddingBlockIsIconTallest
    }
}

enum class OudsSpacingPaddingInsetKeyToken {
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
}

@Stable
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingInsetKeyToken): Dp {
    return when (token) {
        OudsSpacingPaddingInsetKeyToken.None -> insetNone
        OudsSpacingPaddingInsetKeyToken.Smash -> insetSmash
        OudsSpacingPaddingInsetKeyToken.Shortest -> insetShortest
        OudsSpacingPaddingInsetKeyToken.Shorter -> insetShorter
        OudsSpacingPaddingInsetKeyToken.Short -> insetShort
        OudsSpacingPaddingInsetKeyToken.Medium -> insetMedium
        OudsSpacingPaddingInsetKeyToken.Tall -> insetTall
        OudsSpacingPaddingInsetKeyToken.Taller -> insetTaller
        OudsSpacingPaddingInsetKeyToken.Tallest -> insetTallest
        OudsSpacingPaddingInsetKeyToken.Spacious -> insetSpacious
    }
}

enum class OudsSpacingColumnGapKeyToken {
    None,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    IsIconNone,
    IsIconShorter,
    IsIconShort,
    IsIconMedium,
    IsIconTall,
    IsIconTaller,
    IsArrowNone,
    IsArrowShorter,
    IsArrowShort,
    IsArrowMedium,
    IsArrowTall,
    IsArrowTaller,
}

@Stable
fun OudsSpacingTokens.fromToken(token: OudsSpacingColumnGapKeyToken): Dp {
    return when (token) {
        OudsSpacingColumnGapKeyToken.None -> columnGapNone
        OudsSpacingColumnGapKeyToken.Shorter -> columnGapShorter
        OudsSpacingColumnGapKeyToken.Short -> columnGapShort
        OudsSpacingColumnGapKeyToken.Medium -> columnGapMedium
        OudsSpacingColumnGapKeyToken.Tall -> columnGapTall
        OudsSpacingColumnGapKeyToken.Taller -> columnGapTaller
        OudsSpacingColumnGapKeyToken.IsIconNone -> columnGapIsIconNone
        OudsSpacingColumnGapKeyToken.IsIconShorter -> columnGapIsIconShorter
        OudsSpacingColumnGapKeyToken.IsIconShort -> columnGapIsIconShort
        OudsSpacingColumnGapKeyToken.IsIconMedium -> columnGapIsIconMedium
        OudsSpacingColumnGapKeyToken.IsIconTall -> columnGapIsIconTall
        OudsSpacingColumnGapKeyToken.IsIconTaller -> columnGapIsIconTaller
        OudsSpacingColumnGapKeyToken.IsArrowNone -> columnGapIsArrowNone
        OudsSpacingColumnGapKeyToken.IsArrowShorter -> columnGapIsArrowShorter
        OudsSpacingColumnGapKeyToken.IsArrowShort -> columnGapIsArrowShort
        OudsSpacingColumnGapKeyToken.IsArrowMedium -> columnGapIsArrowMedium
        OudsSpacingColumnGapKeyToken.IsArrowTall -> columnGapIsArrowTall
        OudsSpacingColumnGapKeyToken.IsArrowTaller -> columnGapIsArrowTaller
    }
}

enum class OudsSpacingRowGapKeyToken {
    None,
    Shorter,
    Short,
    Medium,
    Tall,
    Taller,
    IsIconNone,
    IsIconShorter,
    IsIconShort,
    IsIconMedium,
    IsIconTall,
    IsIconTaller,
}


@Stable
fun OudsSpacingTokens.fromToken(token: OudsSpacingRowGapKeyToken): Dp {
    return when (token) {
        OudsSpacingRowGapKeyToken.None -> rowGapNone
        OudsSpacingRowGapKeyToken.Shorter -> rowGapShorter
        OudsSpacingRowGapKeyToken.Short -> rowGapShort
        OudsSpacingRowGapKeyToken.Medium -> rowGapMedium
        OudsSpacingRowGapKeyToken.Tall -> rowGapTall
        OudsSpacingRowGapKeyToken.Taller -> rowGapTaller
        OudsSpacingRowGapKeyToken.IsIconNone -> rowGapIsIconNone
        OudsSpacingRowGapKeyToken.IsIconShorter -> rowGapIsIconShorter
        OudsSpacingRowGapKeyToken.IsIconShort -> rowGapIsIconShort
        OudsSpacingRowGapKeyToken.IsIconMedium -> rowGapIsIconMedium
        OudsSpacingRowGapKeyToken.IsIconTall -> rowGapIsIconTall
        OudsSpacingRowGapKeyToken.IsIconTaller -> rowGapIsIconTaller
    }
}
