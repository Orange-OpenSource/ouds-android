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
    val paddingInlineWithIconNone: Dp,
    val paddingInlineWithIconShortest: Dp,
    val paddingInlineWithIconShorter: Dp,
    val paddingInlineWithIconShort: Dp,
    val paddingInlineWithIconMedium: Dp,
    val paddingInlineWithIconTall: Dp,
    val paddingInlineWithIconTaller: Dp,
    val paddingInlineWithIconTallest: Dp,
    val paddingInlineWithArrowNone: Dp,
    val paddingInlineWithArrowShortest: Dp,
    val paddingInlineWithArrowShorter: Dp,
    val paddingInlineWithArrowShort: Dp,
    val paddingInlineWithArrowMedium: Dp,
    val paddingInlineWithArrowTall: Dp,
    val paddingInlineWithArrowTaller: Dp,
    val paddingInlineWithArrowTallest: Dp,
    val paddingBlockNone: Dp,
    val paddingBlockShortest: Dp,
    val paddingBlockShorter: Dp,
    val paddingBlockShort: Dp,
    val paddingBlockMedium: Dp,
    val paddingBlockTall: Dp,
    val paddingBlockTaller: Dp,
    val paddingBlockWithIconNone: Dp,
    val paddingBlockWithIconShorter: Dp,
    val paddingBlockWithIconShort: Dp,
    val paddingBlockWithIconMedium: Dp,
    val paddingBlockWithIconTall: Dp,
    val paddingBlockWithIconTaller: Dp,
    val paddingBlockWithIconTallest: Dp,
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
    val columnGapWithIconNone: Dp,
    val columnGapWithIconShorter: Dp,
    val columnGapWithIconShort: Dp,
    val columnGapWithIconMedium: Dp,
    val columnGapWithIconTall: Dp,
    val columnGapWithIconTaller: Dp,
    val columnGapWithArrowNone: Dp,
    val columnGapWithArrowShorter: Dp,
    val columnGapWithArrowShort: Dp,
    val columnGapWithArrowMedium: Dp,
    val columnGapWithArrowTall: Dp,
    val columnGapWithArrowTaller: Dp,
    val rowGapNone: Dp,
    val rowGapShorter: Dp,
    val rowGapShort: Dp,
    val rowGapMedium: Dp,
    val rowGapTall: Dp,
    val rowGapTaller: Dp,
    val rowGapWithIconNone: Dp,
    val rowGapWithIconShorter: Dp,
    val rowGapWithIconShort: Dp,
    val rowGapWithIconMedium: Dp,
    val rowGapWithIconTall: Dp,
    val rowGapWithIconTaller: Dp,
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
        scaledNoneMobile.dp,
        scaledNoneMobile.dp,
        scaledNoneTablet.dp
    ),
    scaledSmash = OudsAdaptiveTokenValue(
        scaledSmashMobile.dp,
        scaledSmashMobile.dp,
        scaledSmashTablet.dp
    ),
    scaledShortest = OudsAdaptiveTokenValue(
        scaledShortestMobile.dp,
        scaledShortestMobile.dp,
        scaledShortestTablet.dp
    ),
    scaledShorter = OudsAdaptiveTokenValue(
        scaledShorterMobile.dp,
        scaledShorterMobile.dp,
        scaledShorterTablet.dp
    ),
    scaledShort = OudsAdaptiveTokenValue(
        scaledShortMobile.dp,
        scaledShortMobile.dp,
        scaledShortTablet.dp
    ),
    scaledMedium = OudsAdaptiveTokenValue(
        scaledMediumMobile.dp,
        scaledMediumMobile.dp,
        scaledMediumTablet.dp
    ),
    scaledTall = OudsAdaptiveTokenValue(
        scaledTallMobile.dp,
        scaledTallMobile.dp,
        scaledTallTablet.dp
    ),
    scaledTaller = OudsAdaptiveTokenValue(
        scaledTallerMobile.dp,
        scaledTallerMobile.dp,
        scaledTallerTablet.dp
    ),
    scaledTallest = OudsAdaptiveTokenValue(
        scaledTallestMobile.dp,
        scaledTallestMobile.dp,
        scaledTallestTablet.dp
    ),
    scaledSpacious = OudsAdaptiveTokenValue(
        scaledSpaciousMobile.dp,
        scaledSpaciousMobile.dp,
        scaledSpaciousTablet.dp
    ),
    paddingInlineNone = paddingInlineNone.dp,
    paddingInlineShorter = paddingInlineShorter.dp,
    paddingInlineShort = paddingInlineShort.dp,
    paddingInlineMedium = paddingInlineMedium.dp,
    paddingInlineTall = paddingInlineTall.dp,
    paddingInlineTaller = paddingInlineTaller.dp,
    paddingInlineTallest = paddingInlineTallest.dp,
    paddingInlineWithIconNone = paddingInlineWithIconNone.dp,
    paddingInlineWithIconShortest = paddingInlineWithIconShortest.dp,
    paddingInlineWithIconShorter = paddingInlineWithIconShorter.dp,
    paddingInlineWithIconShort = paddingInlineWithIconShort.dp,
    paddingInlineWithIconMedium = paddingInlineWithIconMedium.dp,
    paddingInlineWithIconTall = paddingInlineWithIconTall.dp,
    paddingInlineWithIconTaller = paddingInlineWithIconTaller.dp,
    paddingInlineWithIconTallest = paddingInlineWithIconTallest.dp,
    paddingInlineWithArrowNone = paddingInlineWithArrowNone.dp,
    paddingInlineWithArrowShortest = paddingInlineWithArrowShortest.dp,
    paddingInlineWithArrowShorter = paddingInlineWithArrowShorter.dp,
    paddingInlineWithArrowShort = paddingInlineWithArrowShort.dp,
    paddingInlineWithArrowMedium = paddingInlineWithArrowMedium.dp,
    paddingInlineWithArrowTall = paddingInlineWithArrowTall.dp,
    paddingInlineWithArrowTaller = paddingInlineWithArrowTaller.dp,
    paddingInlineWithArrowTallest = paddingInlineWithArrowTallest.dp,
    paddingBlockNone = paddingBlockNone.dp,
    paddingBlockShortest = paddingBlockShortest.dp,
    paddingBlockShorter = paddingBlockShorter.dp,
    paddingBlockShort = paddingBlockShort.dp,
    paddingBlockMedium = paddingBlockMedium.dp,
    paddingBlockTall = paddingBlockTall.dp,
    paddingBlockTaller = paddingBlockTaller.dp,
    paddingBlockWithIconNone = paddingBlockWithIconNone.dp,
    paddingBlockWithIconShorter = paddingBlockWithIconShorter.dp,
    paddingBlockWithIconShort = paddingBlockWithIconShort.dp,
    paddingBlockWithIconMedium = paddingBlockWithIconMedium.dp,
    paddingBlockWithIconTall = paddingBlockWithIconTall.dp,
    paddingBlockWithIconTaller = paddingBlockWithIconTaller.dp,
    paddingBlockWithIconTallest = paddingBlockWithIconTallest.dp,
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
    columnGapWithIconNone = columnGapWithIconNone.dp,
    columnGapWithIconShorter = columnGapWithIconShorter.dp,
    columnGapWithIconShort = columnGapWithIconShort.dp,
    columnGapWithIconMedium = columnGapWithIconMedium.dp,
    columnGapWithIconTall = columnGapWithIconTall.dp,
    columnGapWithIconTaller = columnGapWithIconTaller.dp,
    columnGapWithArrowNone = columnGapWithArrowNone.dp,
    columnGapWithArrowShorter = columnGapWithArrowShorter.dp,
    columnGapWithArrowShort = columnGapWithArrowShort.dp,
    columnGapWithArrowMedium = columnGapWithArrowMedium.dp,
    columnGapWithArrowTall = columnGapWithArrowTall.dp,
    columnGapWithArrowTaller = columnGapWithArrowTaller.dp,
    rowGapNone = rowGapNone.dp,
    rowGapShorter = rowGapShorter.dp,
    rowGapShort = rowGapShort.dp,
    rowGapMedium = rowGapMedium.dp,
    rowGapTall = rowGapTall.dp,
    rowGapTaller = rowGapTaller.dp,
    rowGapWithIconNone = rowGapWithIconNone.dp,
    rowGapWithIconShorter = rowGapWithIconShorter.dp,
    rowGapWithIconShort = rowGapWithIconShort.dp,
    rowGapWithIconMedium = rowGapWithIconMedium.dp,
    rowGapWithIconTall = rowGapWithIconTall.dp,
    rowGapWithIconTaller = rowGapWithIconTaller.dp,
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
        OudsSpacingPaddingInlineKeyToken.WithIconNone -> paddingInlineWithIconNone
        OudsSpacingPaddingInlineKeyToken.WithIconShortest -> paddingInlineWithIconShortest
        OudsSpacingPaddingInlineKeyToken.WithIconShorter -> paddingInlineWithIconShorter
        OudsSpacingPaddingInlineKeyToken.WithIconShort -> paddingInlineWithIconShort
        OudsSpacingPaddingInlineKeyToken.WithIconMedium -> paddingInlineWithIconMedium
        OudsSpacingPaddingInlineKeyToken.WithIconTall -> paddingInlineWithIconTall
        OudsSpacingPaddingInlineKeyToken.WithIconTaller -> paddingInlineWithIconTaller
        OudsSpacingPaddingInlineKeyToken.WithIconTallest -> paddingInlineWithIconTallest
        OudsSpacingPaddingInlineKeyToken.WithArrowNone -> paddingInlineWithArrowNone
        OudsSpacingPaddingInlineKeyToken.WithArrowShortest -> paddingInlineWithArrowShortest
        OudsSpacingPaddingInlineKeyToken.WithArrowShorter -> paddingInlineWithArrowShorter
        OudsSpacingPaddingInlineKeyToken.WithArrowShort -> paddingInlineWithArrowShort
        OudsSpacingPaddingInlineKeyToken.WithArrowMedium -> paddingInlineWithArrowMedium
        OudsSpacingPaddingInlineKeyToken.WithArrowTall -> paddingInlineWithArrowTall
        OudsSpacingPaddingInlineKeyToken.WithArrowTaller -> paddingInlineWithArrowTaller
        OudsSpacingPaddingInlineKeyToken.WithArrowTallest -> paddingInlineWithArrowTallest
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
        OudsSpacingPaddingBlockKeyToken.WithIconNone -> paddingBlockWithIconNone
        OudsSpacingPaddingBlockKeyToken.WithIconShorter -> paddingBlockWithIconShorter
        OudsSpacingPaddingBlockKeyToken.WithIconShort -> paddingBlockWithIconShort
        OudsSpacingPaddingBlockKeyToken.WithIconMedium -> paddingBlockWithIconMedium
        OudsSpacingPaddingBlockKeyToken.WithIconTall -> paddingBlockWithIconTall
        OudsSpacingPaddingBlockKeyToken.WithIconTaller -> paddingBlockWithIconTaller
        OudsSpacingPaddingBlockKeyToken.WithIconTallest -> paddingBlockWithIconTallest
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
        OudsSpacingColumnGapKeyToken.WithIconNone -> columnGapWithIconNone
        OudsSpacingColumnGapKeyToken.WithIconShorter -> columnGapWithIconShorter
        OudsSpacingColumnGapKeyToken.WithIconShort -> columnGapWithIconShort
        OudsSpacingColumnGapKeyToken.WithIconMedium -> columnGapWithIconMedium
        OudsSpacingColumnGapKeyToken.WithIconTall -> columnGapWithIconTall
        OudsSpacingColumnGapKeyToken.WithIconTaller -> columnGapWithIconTaller
        OudsSpacingColumnGapKeyToken.WithArrowNone -> columnGapWithArrowNone
        OudsSpacingColumnGapKeyToken.WithArrowShorter -> columnGapWithArrowShorter
        OudsSpacingColumnGapKeyToken.WithArrowShort -> columnGapWithArrowShort
        OudsSpacingColumnGapKeyToken.WithArrowMedium -> columnGapWithArrowMedium
        OudsSpacingColumnGapKeyToken.WithArrowTall -> columnGapWithArrowTall
        OudsSpacingColumnGapKeyToken.WithArrowTaller -> columnGapWithArrowTaller
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
        OudsSpacingRowGapKeyToken.WithIconNone -> rowGapWithIconNone
        OudsSpacingRowGapKeyToken.WithIconShorter -> rowGapWithIconShorter
        OudsSpacingRowGapKeyToken.WithIconShort -> rowGapWithIconShort
        OudsSpacingRowGapKeyToken.WithIconMedium -> rowGapWithIconMedium
        OudsSpacingRowGapKeyToken.WithIconTall -> rowGapWithIconTall
        OudsSpacingRowGapKeyToken.WithIconTaller -> rowGapWithIconTaller
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
