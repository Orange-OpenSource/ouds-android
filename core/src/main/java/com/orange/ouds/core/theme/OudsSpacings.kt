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
import com.orange.ouds.theme.tokens.OudsSpacingColumnGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingPaddingBlockKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingPaddingInlineKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingPaddingInsetKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingRowGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingScaledKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSpacingSemanticTokens

data class OudsSpacings(
    val fixedNone: Dp,
    val fixedSmash: Dp,
    val fixedShortest: Dp,
    val fixedShorter: Dp,
    val fixedShort: Dp,
    val fixedMedium: Dp,
    val fixedTall: Dp,
    val fixedTaller: Dp,
    val fixedTallest: Dp,
    val fixedSpacious: Dp,
    val fixedHuge: Dp,
    val fixedJumbo: Dp,
    val scaledNone: OudsAdaptiveTokenValue<Dp>,
    val scaledSmash: OudsAdaptiveTokenValue<Dp>,
    val scaledShortest: OudsAdaptiveTokenValue<Dp>,
    val scaledShorter: OudsAdaptiveTokenValue<Dp>,
    val scaledShort: OudsAdaptiveTokenValue<Dp>,
    val scaledMedium: OudsAdaptiveTokenValue<Dp>,
    val scaledTall: OudsAdaptiveTokenValue<Dp>,
    val scaledTaller: OudsAdaptiveTokenValue<Dp>,
    val scaledTallest: OudsAdaptiveTokenValue<Dp>,
    val scaledSpacious: OudsAdaptiveTokenValue<Dp>,
    val paddingInlineNone: Dp,
    val paddingInlineShorter: Dp,
    val paddingInlineShort: Dp,
    val paddingInlineMedium: Dp,
    val paddingInlineTall: Dp,
    val paddingInlineTaller: Dp,
    val paddingInlineTallest: Dp,
    val paddingInlineIsIconNone: Dp,
    val paddingInlineIsIconShorter: Dp,
    val paddingInlineIsIconShort: Dp,
    val paddingInlineIsIconMedium: Dp,
    val paddingInlineIsIconTall: Dp,
    val paddingInlineIsIconTaller: Dp,
    val paddingInlineIsIconTallest: Dp,
    val paddingInlineIsArrowNone: Dp,
    val paddingInlineIsArrowShorter: Dp,
    val paddingInlineIsArrowShort: Dp,
    val paddingInlineIsArrowMedium: Dp,
    val paddingInlineIsArrowTall: Dp,
    val paddingInlineIsArrowTaller: Dp,
    val paddingInlineIsArrowTallest: Dp,
    val paddingBlockNone: Dp,
    val paddingBlockShortest: Dp,
    val paddingBlockShorter: Dp,
    val paddingBlockShort: Dp,
    val paddingBlockMedium: Dp,
    val paddingBlockTall: Dp,
    val paddingBlockTaller: Dp,
    val paddingBlockIsIconNone: Dp,
    val paddingBlockIsIconShorter: Dp,
    val paddingBlockIsIconShort: Dp,
    val paddingBlockIsIconMedium: Dp,
    val paddingBlockIsIconTall: Dp,
    val paddingBlockIsIconTaller: Dp,
    val paddingBlockIsIconTallest: Dp,
    val insetNone: Dp,
    val insetSmash: Dp,
    val insetShortest: Dp,
    val insetShorter: Dp,
    val insetShort: Dp,
    val insetMedium: Dp,
    val insetTall: Dp,
    val insetTaller: Dp,
    val insetTallest: Dp,
    val insetSpacious: Dp,
    val columnGapNone: Dp,
    val columnGapShorter: Dp,
    val columnGapShort: Dp,
    val columnGapMedium: Dp,
    val columnGapTall: Dp,
    val columnGapTaller: Dp,
    val columnGapIsIconNone: Dp,
    val columnGapIsIconShorter: Dp,
    val columnGapIsIconShort: Dp,
    val columnGapIsIconMedium: Dp,
    val columnGapIsIconTall: Dp,
    val columnGapIsIconTaller: Dp,
    val columnGapIsArrowNone: Dp,
    val columnGapIsArrowShorter: Dp,
    val columnGapIsArrowShort: Dp,
    val columnGapIsArrowMedium: Dp,
    val columnGapIsArrowTall: Dp,
    val columnGapIsArrowTaller: Dp,
    val rowGapNone: Dp,
    val rowGapShorter: Dp,
    val rowGapShort: Dp,
    val rowGapMedium: Dp,
    val rowGapTall: Dp,
    val rowGapTaller: Dp,
    val rowGapIsIconNone: Dp,
    val rowGapIsIconShorter: Dp,
    val rowGapIsIconShort: Dp,
    val rowGapIsIconMedium: Dp,
    val rowGapIsIconTall: Dp,
    val rowGapIsIconTaller: Dp,
)

fun OudsSpacingSemanticTokens.getSpacings() = OudsSpacings(
    fixedNone = fixedNone.dp,
    fixedSmash = fixedSmash.dp,
    fixedShortest = fixedShortest.dp,
    fixedShorter = fixedShorter.dp,
    fixedShort = fixedShort.dp,
    fixedMedium = fixedMedium.dp,
    fixedTall = fixedTall.dp,
    fixedTaller = fixedTaller.dp,
    fixedTallest = fixedTallest.dp,
    fixedSpacious = fixedSpacious.dp,
    fixedHuge = fixedHuge.dp,
    fixedJumbo = fixedJumbo.dp,
    scaledNone = OudsAdaptiveTokenValue(
        scaledMobileNone.dp,
        scaledTabletNone.dp,
        scaledDesktopNone.dp
    ),
    scaledSmash = OudsAdaptiveTokenValue(
        scaledMobileSmash.dp,
        scaledTabletSmash.dp,
        scaledDesktopSmash.dp
    ),
    scaledShortest = OudsAdaptiveTokenValue(
        scaledMobileShortest.dp,
        scaledTabletShortest.dp,
        scaledDesktopShortest.dp
    ),
    scaledShorter = OudsAdaptiveTokenValue(
        scaledMobileShorter.dp,
        scaledTabletShorter.dp,
        scaledDesktopShorter.dp
    ),
    scaledShort = OudsAdaptiveTokenValue(
        scaledMobileShort.dp,
        scaledTabletShort.dp,
        scaledDesktopShort.dp
    ),
    scaledMedium = OudsAdaptiveTokenValue(
        scaledMobileMedium.dp,
        scaledTabletMedium.dp,
        scaledDesktopMedium.dp
    ),
    scaledTall = OudsAdaptiveTokenValue(
        scaledMobileTall.dp,
        scaledTabletTall.dp,
        scaledDesktopTall.dp
    ),
    scaledTaller = OudsAdaptiveTokenValue(
        scaledMobileTaller.dp,
        scaledTabletTaller.dp,
        scaledDesktopTaller.dp
    ),
    scaledTallest = OudsAdaptiveTokenValue(
        scaledMobileTallest.dp,
        scaledTabletTallest.dp,
        scaledDesktopTallest.dp
    ),
    scaledSpacious = OudsAdaptiveTokenValue(
        scaledMobileSpacious.dp,
        scaledTabletSpacious.dp,
        scaledDesktopSpacious.dp
    ),
    paddingInlineNone = paddingInlineNone.dp,
    paddingInlineShorter = paddingInlineShorter.dp,
    paddingInlineShort = paddingInlineShort.dp,
    paddingInlineMedium = paddingInlineMedium.dp,
    paddingInlineTall = paddingInlineTall.dp,
    paddingInlineTaller = paddingInlineTaller.dp,
    paddingInlineTallest = paddingInlineTallest.dp,
    paddingInlineIsIconNone = paddingInlineIsIconNone.dp,
    paddingInlineIsIconShorter = paddingInlineIsIconShorter.dp,
    paddingInlineIsIconShort = paddingInlineIsIconShort.dp,
    paddingInlineIsIconMedium = paddingInlineIsIconMedium.dp,
    paddingInlineIsIconTall = paddingInlineIsIconTall.dp,
    paddingInlineIsIconTaller = paddingInlineIsIconTaller.dp,
    paddingInlineIsIconTallest = paddingInlineIsIconTallest.dp,
    paddingInlineIsArrowNone = paddingInlineIsArrowNone.dp,
    paddingInlineIsArrowShorter = paddingInlineIsArrowShorter.dp,
    paddingInlineIsArrowShort = paddingInlineIsArrowShort.dp,
    paddingInlineIsArrowMedium = paddingInlineIsArrowMedium.dp,
    paddingInlineIsArrowTall = paddingInlineIsArrowTall.dp,
    paddingInlineIsArrowTaller = paddingInlineIsArrowTaller.dp,
    paddingInlineIsArrowTallest = paddingInlineIsArrowTallest.dp,
    paddingBlockNone = paddingBlockNone.dp,
    paddingBlockShortest = paddingBlockShortest.dp,
    paddingBlockShorter = paddingBlockShorter.dp,
    paddingBlockShort = paddingBlockShort.dp,
    paddingBlockMedium = paddingBlockMedium.dp,
    paddingBlockTall = paddingBlockTall.dp,
    paddingBlockTaller = paddingBlockTaller.dp,
    paddingBlockIsIconNone = paddingBlockIsIconNone.dp,
    paddingBlockIsIconShorter = paddingBlockIsIconShorter.dp,
    paddingBlockIsIconShort = paddingBlockIsIconShort.dp,
    paddingBlockIsIconMedium = paddingBlockIsIconMedium.dp,
    paddingBlockIsIconTall = paddingBlockIsIconTall.dp,
    paddingBlockIsIconTaller = paddingBlockIsIconTaller.dp,
    paddingBlockIsIconTallest = paddingBlockIsIconTallest.dp,
    insetNone = insetNone.dp,
    insetSmash = insetSmash.dp,
    insetShortest = insetShortest.dp,
    insetShorter = insetShorter.dp,
    insetShort = insetShort.dp,
    insetMedium = insetMedium.dp,
    insetTall = insetTall.dp,
    insetTaller = insetTaller.dp,
    insetTallest = insetTallest.dp,
    insetSpacious = insetSpacious.dp,
    columnGapNone = columnGapNone.dp,
    columnGapShorter = columnGapShorter.dp,
    columnGapShort = columnGapShort.dp,
    columnGapMedium = columnGapMedium.dp,
    columnGapTall = columnGapTall.dp,
    columnGapTaller = columnGapTaller.dp,
    columnGapIsIconNone = columnGapIsIconNone.dp,
    columnGapIsIconShorter = columnGapIsIconShorter.dp,
    columnGapIsIconShort = columnGapIsIconShort.dp,
    columnGapIsIconMedium = columnGapIsIconMedium.dp,
    columnGapIsIconTall = columnGapIsIconTall.dp,
    columnGapIsIconTaller = columnGapIsIconTaller.dp,
    columnGapIsArrowNone = columnGapIsArrowNone.dp,
    columnGapIsArrowShorter = columnGapIsArrowShorter.dp,
    columnGapIsArrowShort = columnGapIsArrowShort.dp,
    columnGapIsArrowMedium = columnGapIsArrowMedium.dp,
    columnGapIsArrowTall = columnGapIsArrowTall.dp,
    columnGapIsArrowTaller = columnGapIsArrowTaller.dp,
    rowGapNone = rowGapNone.dp,
    rowGapShorter = rowGapShorter.dp,
    rowGapShort = rowGapShort.dp,
    rowGapMedium = rowGapMedium.dp,
    rowGapTall = rowGapTall.dp,
    rowGapTaller = rowGapTaller.dp,
    rowGapIsIconNone = rowGapIsIconNone.dp,
    rowGapIsIconShorter = rowGapIsIconShorter.dp,
    rowGapIsIconShort = rowGapIsIconShort.dp,
    rowGapIsIconMedium = rowGapIsIconMedium.dp,
    rowGapIsIconTall = rowGapIsIconTall.dp,
    rowGapIsIconTaller = rowGapIsIconTaller.dp,
)

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingFixedKeyToken): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingScaledKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingPaddingInlineKeyToken): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingPaddingBlockKeyToken): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingPaddingInsetKeyToken): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingColumnGapKeyToken): Dp {
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

@Stable
fun OudsSpacings.fromToken(token: OudsSpacingRowGapKeyToken): Dp {
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

/**
 * Converts an OUDS column gap space token to the local column gap space value provided by the theme.
 */
val OudsSpacingColumnGapKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS fixed space token to the local space value provided by the theme.
 */
val OudsSpacingFixedKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS block padding token to the local block padding value provided by the theme.
 */
val OudsSpacingPaddingBlockKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS inline padding token to the local inline padding value provided by the theme.
 */
val OudsSpacingPaddingInlineKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS inset padding token to the local inset padding value provided by the theme.
 */
val OudsSpacingPaddingInsetKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS row gap space token to the local row gap space value provided by the theme.
 */
val OudsSpacingRowGapKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this)

/**
 * Converts an OUDS scaled space token to the local space value provided by the theme depending on the window size.
 */
val OudsSpacingScaledKeyToken.value: Dp
    @Composable
    get() = OudsTheme.spacingTokens.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
