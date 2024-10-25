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
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
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
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Fixed): Dp {
    return when (token) {
        OudsSpaceKeyToken.Fixed.None -> fixedNone
        OudsSpaceKeyToken.Fixed.Smash -> fixedSmash
        OudsSpaceKeyToken.Fixed.Shortest -> fixedShortest
        OudsSpaceKeyToken.Fixed.Shorter -> fixedShorter
        OudsSpaceKeyToken.Fixed.Short -> fixedShort
        OudsSpaceKeyToken.Fixed.Medium -> fixedMedium
        OudsSpaceKeyToken.Fixed.Tall -> fixedTall
        OudsSpaceKeyToken.Fixed.Taller -> fixedTaller
        OudsSpaceKeyToken.Fixed.Tallest -> fixedTallest
        OudsSpaceKeyToken.Fixed.Spacious -> fixedSpacious
        OudsSpaceKeyToken.Fixed.Huge -> fixedHuge
        OudsSpaceKeyToken.Fixed.Jumbo -> fixedJumbo
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Scaled, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
        OudsSpaceKeyToken.Scaled.None -> scaledNone
        OudsSpaceKeyToken.Scaled.Smash -> scaledSmash
        OudsSpaceKeyToken.Scaled.Shortest -> scaledShortest
        OudsSpaceKeyToken.Scaled.Shorter -> scaledShorter
        OudsSpaceKeyToken.Scaled.Short -> scaledShort
        OudsSpaceKeyToken.Scaled.Medium -> scaledMedium
        OudsSpaceKeyToken.Scaled.Tall -> scaledTall
        OudsSpaceKeyToken.Scaled.Taller -> scaledTaller
        OudsSpaceKeyToken.Scaled.Tallest -> scaledTallest
        OudsSpaceKeyToken.Scaled.Spacious -> scaledSpacious
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionAdaptableSpaceToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionAdaptableSpaceToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionAdaptableSpaceToken.medium
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingInline.None -> paddingInlineNone
        OudsSpaceKeyToken.PaddingInline.Shorter -> paddingInlineShorter
        OudsSpaceKeyToken.PaddingInline.Short -> paddingInlineShort
        OudsSpaceKeyToken.PaddingInline.Medium -> paddingInlineMedium
        OudsSpaceKeyToken.PaddingInline.Tall -> paddingInlineTall
        OudsSpaceKeyToken.PaddingInline.Taller -> paddingInlineTaller
        OudsSpaceKeyToken.PaddingInline.Tallest -> paddingInlineTallest
        OudsSpaceKeyToken.PaddingInline.WithIconNone -> paddingInlineWithIconNone
        OudsSpaceKeyToken.PaddingInline.WithIconShortest -> paddingInlineWithIconShortest
        OudsSpaceKeyToken.PaddingInline.WithIconShorter -> paddingInlineWithIconShorter
        OudsSpaceKeyToken.PaddingInline.WithIconShort -> paddingInlineWithIconShort
        OudsSpaceKeyToken.PaddingInline.WithIconMedium -> paddingInlineWithIconMedium
        OudsSpaceKeyToken.PaddingInline.WithIconTall -> paddingInlineWithIconTall
        OudsSpaceKeyToken.PaddingInline.WithIconTaller -> paddingInlineWithIconTaller
        OudsSpaceKeyToken.PaddingInline.WithIconTallest -> paddingInlineWithIconTallest
        OudsSpaceKeyToken.PaddingInline.WithArrowNone -> paddingInlineWithArrowNone
        OudsSpaceKeyToken.PaddingInline.WithArrowShortest -> paddingInlineWithArrowShortest
        OudsSpaceKeyToken.PaddingInline.WithArrowShorter -> paddingInlineWithArrowShorter
        OudsSpaceKeyToken.PaddingInline.WithArrowShort -> paddingInlineWithArrowShort
        OudsSpaceKeyToken.PaddingInline.WithArrowMedium -> paddingInlineWithArrowMedium
        OudsSpaceKeyToken.PaddingInline.WithArrowTall -> paddingInlineWithArrowTall
        OudsSpaceKeyToken.PaddingInline.WithArrowTaller -> paddingInlineWithArrowTaller
        OudsSpaceKeyToken.PaddingInline.WithArrowTallest -> paddingInlineWithArrowTallest
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingBlock.None -> paddingBlockNone
        OudsSpaceKeyToken.PaddingBlock.Shorter -> paddingBlockShorter
        OudsSpaceKeyToken.PaddingBlock.Short -> paddingBlockShort
        OudsSpaceKeyToken.PaddingBlock.Medium -> paddingBlockMedium
        OudsSpaceKeyToken.PaddingBlock.Tall -> paddingBlockTall
        OudsSpaceKeyToken.PaddingBlock.Taller -> paddingBlockTaller
        OudsSpaceKeyToken.PaddingBlock.Tallest -> paddingBlockTallest
        OudsSpaceKeyToken.PaddingBlock.WithIconNone -> paddingBlockWithIconNone
        OudsSpaceKeyToken.PaddingBlock.WithIconShortest -> paddingBlockWithIconShortest
        OudsSpaceKeyToken.PaddingBlock.WithIconShorter -> paddingBlockWithIconShorter
        OudsSpaceKeyToken.PaddingBlock.WithIconShort -> paddingBlockWithIconShort
        OudsSpaceKeyToken.PaddingBlock.WithIconMedium -> paddingBlockWithIconMedium
        OudsSpaceKeyToken.PaddingBlock.WithIconTall -> paddingBlockWithIconTall
        OudsSpaceKeyToken.PaddingBlock.WithIconTaller -> paddingBlockWithIconTaller
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Inset): Dp {
    return when (token) {
        OudsSpaceKeyToken.Inset.None -> insetNone
        OudsSpaceKeyToken.Inset.Smash -> insetSmash
        OudsSpaceKeyToken.Inset.Shortest -> insetShortest
        OudsSpaceKeyToken.Inset.Shorter -> insetShorter
        OudsSpaceKeyToken.Inset.Short -> insetShort
        OudsSpaceKeyToken.Inset.Medium -> insetMedium
        OudsSpaceKeyToken.Inset.Tall -> insetTall
        OudsSpaceKeyToken.Inset.Taller -> insetTaller
        OudsSpaceKeyToken.Inset.Tallest -> insetTallest
        OudsSpaceKeyToken.Inset.Spacious -> insetSpacious
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.None -> columnGapNone
        OudsSpaceKeyToken.ColumnGap.Shorter -> columnGapShorter
        OudsSpaceKeyToken.ColumnGap.Short -> columnGapShort
        OudsSpaceKeyToken.ColumnGap.Medium -> columnGapMedium
        OudsSpaceKeyToken.ColumnGap.Tall -> columnGapTall
        OudsSpaceKeyToken.ColumnGap.Taller -> columnGapTaller
        OudsSpaceKeyToken.ColumnGap.WithIconNone -> columnGapWithIconNone
        OudsSpaceKeyToken.ColumnGap.WithIconShortest -> columnGapWithIconShortest
        OudsSpaceKeyToken.ColumnGap.WithIconShorter -> columnGapWithIconShorter
        OudsSpaceKeyToken.ColumnGap.WithIconShort -> columnGapWithIconShort
        OudsSpaceKeyToken.ColumnGap.WithIconMedium -> columnGapWithIconMedium
        OudsSpaceKeyToken.ColumnGap.WithIconTall -> columnGapWithIconTall
        OudsSpaceKeyToken.ColumnGap.WithArrowNone -> columnGapWithArrowNone
        OudsSpaceKeyToken.ColumnGap.WithArrowShortest -> columnGapWithArrowShortest
        OudsSpaceKeyToken.ColumnGap.WithArrowShorter -> columnGapWithArrowShorter
        OudsSpaceKeyToken.ColumnGap.WithArrowShort -> columnGapWithArrowShort
        OudsSpaceKeyToken.ColumnGap.WithArrowMedium -> columnGapWithArrowMedium
        OudsSpaceKeyToken.ColumnGap.WithArrowTall -> columnGapWithArrowTall
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.RowGap.None -> rowGapNone
        OudsSpaceKeyToken.RowGap.Shortest -> rowGapShortest
        OudsSpaceKeyToken.RowGap.Shorter -> rowGapShorter
        OudsSpaceKeyToken.RowGap.Short -> rowGapShort
        OudsSpaceKeyToken.RowGap.Medium -> rowGapMedium
        OudsSpaceKeyToken.RowGap.Tall -> rowGapTall
        OudsSpaceKeyToken.RowGap.WithIconNone -> rowGapWithIconNone
        OudsSpaceKeyToken.RowGap.WithIconShortest -> rowGapWithIconShortest
        OudsSpaceKeyToken.RowGap.WithIconShorter -> rowGapWithIconShorter
        OudsSpaceKeyToken.RowGap.WithIconShort -> rowGapWithIconShort
        OudsSpaceKeyToken.RowGap.WithIconMedium -> rowGapWithIconMedium
        OudsSpaceKeyToken.RowGap.WithIconTall -> rowGapWithIconTall
    }
}

/**
 * Converts an OUDS column gap space token to the local column gap space value provided by the theme.
 */
val OudsSpaceKeyToken.ColumnGap.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS fixed space token to the local space value provided by the theme.
 */
val OudsSpaceKeyToken.Fixed.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS block padding token to the local block padding value provided by the theme.
 */
val OudsSpaceKeyToken.PaddingBlock.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS inline padding token to the local inline padding value provided by the theme.
 */
val OudsSpaceKeyToken.PaddingInline.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS inset token to the local inset value provided by the theme.
 */
val OudsSpaceKeyToken.Inset.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS row gap space token to the local row gap space value provided by the theme.
 */
val OudsSpaceKeyToken.RowGap.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS scaled space token to the local space value provided by the theme depending on the window size.
 */
val OudsSpaceKeyToken.Scaled.value: Dp
    @Composable
    get() = OudsTheme.spaces.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
