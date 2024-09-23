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

enum class OudsSpacingFixedToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingFixedToken): Dp {
    return when (token) {
        OudsSpacingFixedToken.None -> fixedNone
        OudsSpacingFixedToken.Smash -> fixedSmash
        OudsSpacingFixedToken.Shortest -> fixedShortest
        OudsSpacingFixedToken.Shorter -> fixedShorter
        OudsSpacingFixedToken.Short -> fixedShort
        OudsSpacingFixedToken.Medium -> fixedMedium
        OudsSpacingFixedToken.Tall -> fixedTall
        OudsSpacingFixedToken.Taller -> fixedTaller
        OudsSpacingFixedToken.Tallest -> fixedTallest
        OudsSpacingFixedToken.Spacious -> fixedSpacious
        OudsSpacingFixedToken.Huge -> fixedHuge
        OudsSpacingFixedToken.Jumbo -> fixedJumbo
    }
}

enum class OudsSpacingScaledToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingScaledToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
        OudsSpacingScaledToken.None -> scaledNone
        OudsSpacingScaledToken.Smash -> scaledSmash
        OudsSpacingScaledToken.Shortest -> scaledShortest
        OudsSpacingScaledToken.Shorter -> scaledShorter
        OudsSpacingScaledToken.Short -> scaledShort
        OudsSpacingScaledToken.Medium -> scaledMedium
        OudsSpacingScaledToken.Tall -> scaledTall
        OudsSpacingScaledToken.Taller -> scaledTaller
        OudsSpacingScaledToken.Tallest -> scaledTallest
        OudsSpacingScaledToken.Spacious -> scaledSpacious
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionAdaptableSpaceToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionAdaptableSpaceToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionAdaptableSpaceToken.medium
    }
}

enum class OudsSpacingPaddingInlineToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingInlineToken): Dp {
    return when (token) {
        OudsSpacingPaddingInlineToken.None -> paddingInlineNone
        OudsSpacingPaddingInlineToken.Shorter -> paddingInlineShorter
        OudsSpacingPaddingInlineToken.Short -> paddingInlineShort
        OudsSpacingPaddingInlineToken.Medium -> paddingInlineMedium
        OudsSpacingPaddingInlineToken.Tall -> paddingInlineTall
        OudsSpacingPaddingInlineToken.Taller -> paddingInlineTaller
        OudsSpacingPaddingInlineToken.Tallest -> paddingInlineTallest
        OudsSpacingPaddingInlineToken.IsIconNone -> paddingInlineIsIconNone
        OudsSpacingPaddingInlineToken.IsIconShorter -> paddingInlineIsIconShorter
        OudsSpacingPaddingInlineToken.IsIconShort -> paddingInlineIsIconShort
        OudsSpacingPaddingInlineToken.IsIconMedium -> paddingInlineIsIconMedium
        OudsSpacingPaddingInlineToken.IsIconTall -> paddingInlineIsIconTall
        OudsSpacingPaddingInlineToken.IsIconTaller -> paddingInlineIsIconTaller
        OudsSpacingPaddingInlineToken.IsIconTallest -> paddingInlineIsIconTallest
        OudsSpacingPaddingInlineToken.IsArrowNone -> paddingInlineIsArrowNone
        OudsSpacingPaddingInlineToken.IsArrowShorter -> paddingInlineIsArrowShorter
        OudsSpacingPaddingInlineToken.IsArrowShort -> paddingInlineIsArrowShort
        OudsSpacingPaddingInlineToken.IsArrowMedium -> paddingInlineIsArrowMedium
        OudsSpacingPaddingInlineToken.IsArrowTall -> paddingInlineIsArrowTall
        OudsSpacingPaddingInlineToken.IsArrowTaller -> paddingInlineIsArrowTaller
        OudsSpacingPaddingInlineToken.IsArrowTallest -> paddingInlineIsArrowTallest
    }
}

enum class OudsSpacingPaddingBlockToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingBlockToken): Dp {
    return when (token) {
        OudsSpacingPaddingBlockToken.None -> paddingBlockNone
        OudsSpacingPaddingBlockToken.Shortest -> paddingBlockShortest
        OudsSpacingPaddingBlockToken.Shorter -> paddingBlockShorter
        OudsSpacingPaddingBlockToken.Short -> paddingBlockShort
        OudsSpacingPaddingBlockToken.Medium -> paddingBlockMedium
        OudsSpacingPaddingBlockToken.Tall -> paddingBlockTall
        OudsSpacingPaddingBlockToken.Taller -> paddingBlockTaller
        OudsSpacingPaddingBlockToken.IsIconNone -> paddingBlockIsIconNone
        OudsSpacingPaddingBlockToken.IsIconShorter -> paddingBlockIsIconShorter
        OudsSpacingPaddingBlockToken.IsIconShort -> paddingBlockIsIconShort
        OudsSpacingPaddingBlockToken.IsIconMedium -> paddingBlockIsIconMedium
        OudsSpacingPaddingBlockToken.IsIconTall -> paddingBlockIsIconTall
        OudsSpacingPaddingBlockToken.IsIconTaller -> paddingBlockIsIconTaller
        OudsSpacingPaddingBlockToken.IsIconTallest -> paddingBlockIsIconTallest
    }
}

enum class OudsSpacingPaddingInsetToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingPaddingInsetToken): Dp {
    return when (token) {
        OudsSpacingPaddingInsetToken.None -> insetNone
        OudsSpacingPaddingInsetToken.Smash -> insetSmash
        OudsSpacingPaddingInsetToken.Shortest -> insetShortest
        OudsSpacingPaddingInsetToken.Shorter -> insetShorter
        OudsSpacingPaddingInsetToken.Short -> insetShort
        OudsSpacingPaddingInsetToken.Medium -> insetMedium
        OudsSpacingPaddingInsetToken.Tall -> insetTall
        OudsSpacingPaddingInsetToken.Taller -> insetTaller
        OudsSpacingPaddingInsetToken.Tallest -> insetTallest
        OudsSpacingPaddingInsetToken.Spacious -> insetSpacious
    }
}

enum class OudsSpacingColumnGapToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingColumnGapToken): Dp {
    return when (token) {
        OudsSpacingColumnGapToken.None -> columnGapNone
        OudsSpacingColumnGapToken.Shorter -> columnGapShorter
        OudsSpacingColumnGapToken.Short -> columnGapShort
        OudsSpacingColumnGapToken.Medium -> columnGapMedium
        OudsSpacingColumnGapToken.Tall -> columnGapTall
        OudsSpacingColumnGapToken.Taller -> columnGapTaller
        OudsSpacingColumnGapToken.IsIconNone -> columnGapIsIconNone
        OudsSpacingColumnGapToken.IsIconShorter -> columnGapIsIconShorter
        OudsSpacingColumnGapToken.IsIconShort -> columnGapIsIconShort
        OudsSpacingColumnGapToken.IsIconMedium -> columnGapIsIconMedium
        OudsSpacingColumnGapToken.IsIconTall -> columnGapIsIconTall
        OudsSpacingColumnGapToken.IsIconTaller -> columnGapIsIconTaller
        OudsSpacingColumnGapToken.IsArrowNone -> columnGapIsArrowNone
        OudsSpacingColumnGapToken.IsArrowShorter -> columnGapIsArrowShorter
        OudsSpacingColumnGapToken.IsArrowShort -> columnGapIsArrowShort
        OudsSpacingColumnGapToken.IsArrowMedium -> columnGapIsArrowMedium
        OudsSpacingColumnGapToken.IsArrowTall -> columnGapIsArrowTall
        OudsSpacingColumnGapToken.IsArrowTaller -> columnGapIsArrowTaller
    }
}

enum class OudsSpacingRowGapToken {
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
fun OudsSpacingTokens.fromToken(token: OudsSpacingRowGapToken): Dp {
    return when (token) {
        OudsSpacingRowGapToken.None -> rowGapNone
        OudsSpacingRowGapToken.Shorter -> rowGapShorter
        OudsSpacingRowGapToken.Short -> rowGapShort
        OudsSpacingRowGapToken.Medium -> rowGapMedium
        OudsSpacingRowGapToken.Tall -> rowGapTall
        OudsSpacingRowGapToken.Taller -> rowGapTaller
        OudsSpacingRowGapToken.IsIconNone -> rowGapIsIconNone
        OudsSpacingRowGapToken.IsIconShorter -> rowGapIsIconShorter
        OudsSpacingRowGapToken.IsIconShort -> rowGapIsIconShort
        OudsSpacingRowGapToken.IsIconMedium -> rowGapIsIconMedium
        OudsSpacingRowGapToken.IsIconTall -> rowGapIsIconTall
        OudsSpacingRowGapToken.IsIconTaller -> rowGapIsIconTaller
    }
}
