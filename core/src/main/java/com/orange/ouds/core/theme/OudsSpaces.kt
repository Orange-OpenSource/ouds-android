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
import com.orange.ouds.theme.tokens.OudsSpaceColumnGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceFixedKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceInsetKeyToken
import com.orange.ouds.theme.tokens.OudsSpacePaddingBlockKeyToken
import com.orange.ouds.theme.tokens.OudsSpacePaddingInlineKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceRowGapKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceScaledKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens

data class OudsSpaces(
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
    val paddingBlockShorter: Dp,
    val paddingBlockShort: Dp,
    val paddingBlockMedium: Dp,
    val paddingBlockTall: Dp,
    val paddingBlockTaller: Dp,
    val paddingBlockTallest: Dp,
    val paddingBlockWithIconNone: Dp,
    val paddingBlockWithIconShortest: Dp,
    val paddingBlockWithIconShorter: Dp,
    val paddingBlockWithIconShort: Dp,
    val paddingBlockWithIconMedium: Dp,
    val paddingBlockWithIconTall: Dp,
    val paddingBlockWithIconTaller: Dp,
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
    val columnGapWithIconShortest: Dp,
    val columnGapWithIconShorter: Dp,
    val columnGapWithIconShort: Dp,
    val columnGapWithIconMedium: Dp,
    val columnGapWithIconTall: Dp,
    val columnGapWithArrowNone: Dp,
    val columnGapWithArrowShortest: Dp,
    val columnGapWithArrowShorter: Dp,
    val columnGapWithArrowShort: Dp,
    val columnGapWithArrowMedium: Dp,
    val columnGapWithArrowTall: Dp,
    val rowGapNone: Dp,
    val rowGapShortest: Dp,
    val rowGapShorter: Dp,
    val rowGapShort: Dp,
    val rowGapMedium: Dp,
    val rowGapTall: Dp,
    val rowGapWithIconNone: Dp,
    val rowGapWithIconShortest: Dp,
    val rowGapWithIconShorter: Dp,
    val rowGapWithIconShort: Dp,
    val rowGapWithIconMedium: Dp,
    val rowGapWithIconTall: Dp,
)

fun OudsSpaceSemanticTokens.getSpaces() = OudsSpaces(
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
    paddingBlockShorter = paddingBlockShorter.dp,
    paddingBlockShort = paddingBlockShort.dp,
    paddingBlockMedium = paddingBlockMedium.dp,
    paddingBlockTall = paddingBlockTall.dp,
    paddingBlockTaller = paddingBlockTaller.dp,
    paddingBlockTallest = paddingBlockTallest.dp,
    paddingBlockWithIconNone = paddingBlockWithIconNone.dp,
    paddingBlockWithIconShortest = paddingBlockWithIconShortest.dp,
    paddingBlockWithIconShorter = paddingBlockWithIconShorter.dp,
    paddingBlockWithIconShort = paddingBlockWithIconShort.dp,
    paddingBlockWithIconMedium = paddingBlockWithIconMedium.dp,
    paddingBlockWithIconTall = paddingBlockWithIconTall.dp,
    paddingBlockWithIconTaller = paddingBlockWithIconTaller.dp,
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
    columnGapWithIconShortest = columnGapWithIconShortest.dp,
    columnGapWithIconShorter = columnGapWithIconShorter.dp,
    columnGapWithIconShort = columnGapWithIconShort.dp,
    columnGapWithIconMedium = columnGapWithIconMedium.dp,
    columnGapWithIconTall = columnGapWithIconTall.dp,
    columnGapWithArrowNone = columnGapWithArrowNone.dp,
    columnGapWithArrowShortest = columnGapWithArrowShortest.dp,
    columnGapWithArrowShorter = columnGapWithArrowShorter.dp,
    columnGapWithArrowShort = columnGapWithArrowShort.dp,
    columnGapWithArrowMedium = columnGapWithArrowMedium.dp,
    columnGapWithArrowTall = columnGapWithArrowTall.dp,
    rowGapNone = rowGapNone.dp,
    rowGapShortest = rowGapShortest.dp,
    rowGapShorter = rowGapShorter.dp,
    rowGapShort = rowGapShort.dp,
    rowGapMedium = rowGapMedium.dp,
    rowGapTall = rowGapTall.dp,
    rowGapWithIconNone = rowGapWithIconNone.dp,
    rowGapWithIconShortest = rowGapWithIconShortest.dp,
    rowGapWithIconShorter = rowGapWithIconShorter.dp,
    rowGapWithIconShort = rowGapWithIconShort.dp,
    rowGapWithIconMedium = rowGapWithIconMedium.dp,
    rowGapWithIconTall = rowGapWithIconTall.dp,
)

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceFixedKeyToken): Dp {
    return when (token) {
        OudsSpaceFixedKeyToken.None -> fixedNone
        OudsSpaceFixedKeyToken.Smash -> fixedSmash
        OudsSpaceFixedKeyToken.Shortest -> fixedShortest
        OudsSpaceFixedKeyToken.Shorter -> fixedShorter
        OudsSpaceFixedKeyToken.Short -> fixedShort
        OudsSpaceFixedKeyToken.Medium -> fixedMedium
        OudsSpaceFixedKeyToken.Tall -> fixedTall
        OudsSpaceFixedKeyToken.Taller -> fixedTaller
        OudsSpaceFixedKeyToken.Tallest -> fixedTallest
        OudsSpaceFixedKeyToken.Spacious -> fixedSpacious
        OudsSpaceFixedKeyToken.Huge -> fixedHuge
        OudsSpaceFixedKeyToken.Jumbo -> fixedJumbo
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceScaledKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
        OudsSpaceScaledKeyToken.None -> scaledNone
        OudsSpaceScaledKeyToken.Smash -> scaledSmash
        OudsSpaceScaledKeyToken.Shortest -> scaledShortest
        OudsSpaceScaledKeyToken.Shorter -> scaledShorter
        OudsSpaceScaledKeyToken.Short -> scaledShort
        OudsSpaceScaledKeyToken.Medium -> scaledMedium
        OudsSpaceScaledKeyToken.Tall -> scaledTall
        OudsSpaceScaledKeyToken.Taller -> scaledTaller
        OudsSpaceScaledKeyToken.Tallest -> scaledTallest
        OudsSpaceScaledKeyToken.Spacious -> scaledSpacious
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionAdaptableSpaceToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionAdaptableSpaceToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionAdaptableSpaceToken.medium
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpacePaddingInlineKeyToken): Dp {
    return when (token) {
        OudsSpacePaddingInlineKeyToken.None -> paddingInlineNone
        OudsSpacePaddingInlineKeyToken.Shorter -> paddingInlineShorter
        OudsSpacePaddingInlineKeyToken.Short -> paddingInlineShort
        OudsSpacePaddingInlineKeyToken.Medium -> paddingInlineMedium
        OudsSpacePaddingInlineKeyToken.Tall -> paddingInlineTall
        OudsSpacePaddingInlineKeyToken.Taller -> paddingInlineTaller
        OudsSpacePaddingInlineKeyToken.Tallest -> paddingInlineTallest
        OudsSpacePaddingInlineKeyToken.WithIconNone -> paddingInlineWithIconNone
        OudsSpacePaddingInlineKeyToken.WithIconShortest -> paddingInlineWithIconShortest
        OudsSpacePaddingInlineKeyToken.WithIconShorter -> paddingInlineWithIconShorter
        OudsSpacePaddingInlineKeyToken.WithIconShort -> paddingInlineWithIconShort
        OudsSpacePaddingInlineKeyToken.WithIconMedium -> paddingInlineWithIconMedium
        OudsSpacePaddingInlineKeyToken.WithIconTall -> paddingInlineWithIconTall
        OudsSpacePaddingInlineKeyToken.WithIconTaller -> paddingInlineWithIconTaller
        OudsSpacePaddingInlineKeyToken.WithIconTallest -> paddingInlineWithIconTallest
        OudsSpacePaddingInlineKeyToken.WithArrowNone -> paddingInlineWithArrowNone
        OudsSpacePaddingInlineKeyToken.WithArrowShortest -> paddingInlineWithArrowShortest
        OudsSpacePaddingInlineKeyToken.WithArrowShorter -> paddingInlineWithArrowShorter
        OudsSpacePaddingInlineKeyToken.WithArrowShort -> paddingInlineWithArrowShort
        OudsSpacePaddingInlineKeyToken.WithArrowMedium -> paddingInlineWithArrowMedium
        OudsSpacePaddingInlineKeyToken.WithArrowTall -> paddingInlineWithArrowTall
        OudsSpacePaddingInlineKeyToken.WithArrowTaller -> paddingInlineWithArrowTaller
        OudsSpacePaddingInlineKeyToken.WithArrowTallest -> paddingInlineWithArrowTallest
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpacePaddingBlockKeyToken): Dp {
    return when (token) {
        OudsSpacePaddingBlockKeyToken.None -> paddingBlockNone
        OudsSpacePaddingBlockKeyToken.Shorter -> paddingBlockShorter
        OudsSpacePaddingBlockKeyToken.Short -> paddingBlockShort
        OudsSpacePaddingBlockKeyToken.Medium -> paddingBlockMedium
        OudsSpacePaddingBlockKeyToken.Tall -> paddingBlockTall
        OudsSpacePaddingBlockKeyToken.Taller -> paddingBlockTaller
        OudsSpacePaddingBlockKeyToken.Tallest -> paddingBlockTallest
        OudsSpacePaddingBlockKeyToken.WithIconNone -> paddingBlockWithIconNone
        OudsSpacePaddingBlockKeyToken.WithIconShortest -> paddingBlockWithIconShortest
        OudsSpacePaddingBlockKeyToken.WithIconShorter -> paddingBlockWithIconShorter
        OudsSpacePaddingBlockKeyToken.WithIconShort -> paddingBlockWithIconShort
        OudsSpacePaddingBlockKeyToken.WithIconMedium -> paddingBlockWithIconMedium
        OudsSpacePaddingBlockKeyToken.WithIconTall -> paddingBlockWithIconTall
        OudsSpacePaddingBlockKeyToken.WithIconTaller -> paddingBlockWithIconTaller
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceInsetKeyToken): Dp {
    return when (token) {
        OudsSpaceInsetKeyToken.None -> insetNone
        OudsSpaceInsetKeyToken.Smash -> insetSmash
        OudsSpaceInsetKeyToken.Shortest -> insetShortest
        OudsSpaceInsetKeyToken.Shorter -> insetShorter
        OudsSpaceInsetKeyToken.Short -> insetShort
        OudsSpaceInsetKeyToken.Medium -> insetMedium
        OudsSpaceInsetKeyToken.Tall -> insetTall
        OudsSpaceInsetKeyToken.Taller -> insetTaller
        OudsSpaceInsetKeyToken.Tallest -> insetTallest
        OudsSpaceInsetKeyToken.Spacious -> insetSpacious
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceColumnGapKeyToken): Dp {
    return when (token) {
        OudsSpaceColumnGapKeyToken.None -> columnGapNone
        OudsSpaceColumnGapKeyToken.Shorter -> columnGapShorter
        OudsSpaceColumnGapKeyToken.Short -> columnGapShort
        OudsSpaceColumnGapKeyToken.Medium -> columnGapMedium
        OudsSpaceColumnGapKeyToken.Tall -> columnGapTall
        OudsSpaceColumnGapKeyToken.Taller -> columnGapTaller
        OudsSpaceColumnGapKeyToken.WithIconNone -> columnGapWithIconNone
        OudsSpaceColumnGapKeyToken.WithIconShortest -> columnGapWithIconShortest
        OudsSpaceColumnGapKeyToken.WithIconShorter -> columnGapWithIconShorter
        OudsSpaceColumnGapKeyToken.WithIconShort -> columnGapWithIconShort
        OudsSpaceColumnGapKeyToken.WithIconMedium -> columnGapWithIconMedium
        OudsSpaceColumnGapKeyToken.WithIconTall -> columnGapWithIconTall
        OudsSpaceColumnGapKeyToken.WithArrowNone -> columnGapWithArrowNone
        OudsSpaceColumnGapKeyToken.WithArrowShortest -> columnGapWithArrowShortest
        OudsSpaceColumnGapKeyToken.WithArrowShorter -> columnGapWithArrowShorter
        OudsSpaceColumnGapKeyToken.WithArrowShort -> columnGapWithArrowShort
        OudsSpaceColumnGapKeyToken.WithArrowMedium -> columnGapWithArrowMedium
        OudsSpaceColumnGapKeyToken.WithArrowTall -> columnGapWithArrowTall
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceRowGapKeyToken): Dp {
    return when (token) {
        OudsSpaceRowGapKeyToken.None -> rowGapNone
        OudsSpaceRowGapKeyToken.Shortest -> rowGapShortest
        OudsSpaceRowGapKeyToken.Shorter -> rowGapShorter
        OudsSpaceRowGapKeyToken.Short -> rowGapShort
        OudsSpaceRowGapKeyToken.Medium -> rowGapMedium
        OudsSpaceRowGapKeyToken.Tall -> rowGapTall
        OudsSpaceRowGapKeyToken.WithIconNone -> rowGapWithIconNone
        OudsSpaceRowGapKeyToken.WithIconShortest -> rowGapWithIconShortest
        OudsSpaceRowGapKeyToken.WithIconShorter -> rowGapWithIconShorter
        OudsSpaceRowGapKeyToken.WithIconShort -> rowGapWithIconShort
        OudsSpaceRowGapKeyToken.WithIconMedium -> rowGapWithIconMedium
        OudsSpaceRowGapKeyToken.WithIconTall -> rowGapWithIconTall
    }
}

/**
 * Converts an OUDS column gap space token to the local column gap space value provided by the theme.
 */
val OudsSpaceColumnGapKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS fixed space token to the local space value provided by the theme.
 */
val OudsSpaceFixedKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS block padding token to the local block padding value provided by the theme.
 */
val OudsSpacePaddingBlockKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS inline padding token to the local inline padding value provided by the theme.
 */
val OudsSpacePaddingInlineKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS inset token to the local inset value provided by the theme.
 */
val OudsSpaceInsetKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS row gap space token to the local row gap space value provided by the theme.
 */
val OudsSpaceRowGapKeyToken.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS scaled space token to the local space value provided by the theme depending on the window size.
 */
val OudsSpaceScaledKeyToken.value: Dp
    @Composable
    get() = OudsTheme.spaces.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
