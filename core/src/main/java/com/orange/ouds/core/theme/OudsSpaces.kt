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
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens

/**
 * @suppress
 */
data class OudsSpaces(
    val fixed: Fixed,
    val scaled: Scaled,
    val paddingInline: PaddingInline,
    val paddingBlock: PaddingBlock,
    val inset: Inset,
    val columnGap: ColumnGap,
    val rowGap: RowGap
) {

    data class Fixed(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val spacious: Dp,
        val huge: Dp,
        val jumbo: Dp
    )

    data class Scaled(
        val none: OudsAdaptiveTokenValue<Dp>,
        val smash: OudsAdaptiveTokenValue<Dp>,
        val shortest: OudsAdaptiveTokenValue<Dp>,
        val shorter: OudsAdaptiveTokenValue<Dp>,
        val short: OudsAdaptiveTokenValue<Dp>,
        val medium: OudsAdaptiveTokenValue<Dp>,
        val tall: OudsAdaptiveTokenValue<Dp>,
        val taller: OudsAdaptiveTokenValue<Dp>,
        val tallest: OudsAdaptiveTokenValue<Dp>,
        val spacious: OudsAdaptiveTokenValue<Dp>
    )

    data class PaddingInline(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val huge: Dp,
        val spacious: Dp,
    )

    data class PaddingBlock(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val spacious: Dp,
        val huge: Dp,
    )

    data class Inset(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val spacious: Dp,
    )

    data class ColumnGap(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
    )

    data class RowGap(
        val none: Dp,
        val smash: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
    )
}

internal fun OudsSpaceSemanticTokens.getSpaces() = OudsSpaces(
    fixed = OudsSpaces.Fixed(
        none = fixedNone.dp,
        smash = fixedSmash.dp,
        shortest = fixedShortest.dp,
        shorter = fixedShorter.dp,
        short = fixedShort.dp,
        medium = fixedMedium.dp,
        tall = fixedTall.dp,
        taller = fixedTaller.dp,
        tallest = fixedTallest.dp,
        spacious = fixedSpacious.dp,
        huge = fixedHuge.dp,
        jumbo = fixedJumbo.dp,
    ),
    scaled = OudsSpaces.Scaled(
        none = OudsAdaptiveTokenValue(
            scaledNoneMobile.dp,
            scaledNoneMobile.dp,
            scaledNoneTablet.dp
        ),
        smash = OudsAdaptiveTokenValue(
            scaledSmashMobile.dp,
            scaledSmashMobile.dp,
            scaledSmashTablet.dp
        ),
        shortest = OudsAdaptiveTokenValue(
            scaledShortestMobile.dp,
            scaledShortestMobile.dp,
            scaledShortestTablet.dp
        ),
        shorter = OudsAdaptiveTokenValue(
            scaledShorterMobile.dp,
            scaledShorterMobile.dp,
            scaledShorterTablet.dp
        ),
        short = OudsAdaptiveTokenValue(
            scaledShortMobile.dp,
            scaledShortMobile.dp,
            scaledShortTablet.dp
        ),
        medium = OudsAdaptiveTokenValue(
            scaledMediumMobile.dp,
            scaledMediumMobile.dp,
            scaledMediumTablet.dp
        ),
        tall = OudsAdaptiveTokenValue(
            scaledTallMobile.dp,
            scaledTallMobile.dp,
            scaledTallTablet.dp
        ),
        taller = OudsAdaptiveTokenValue(
            scaledTallerMobile.dp,
            scaledTallerMobile.dp,
            scaledTallerTablet.dp
        ),
        tallest = OudsAdaptiveTokenValue(
            scaledTallestMobile.dp,
            scaledTallestMobile.dp,
            scaledTallestTablet.dp
        ),
        spacious = OudsAdaptiveTokenValue(
            scaledSpaciousMobile.dp,
            scaledSpaciousMobile.dp,
            scaledSpaciousTablet.dp
        ),
    ),
    paddingInline = OudsSpaces.PaddingInline(
        none = paddingInlineNone.dp,
        smash = paddingInlineSmash.dp,
        shortest = paddingInlineShortest.dp,
        shorter = paddingInlineShorter.dp,
        short = paddingInlineShort.dp,
        medium = paddingInlineMedium.dp,
        tall = paddingInlineTall.dp,
        taller = paddingInlineTaller.dp,
        tallest = paddingInlineTallest.dp,
        huge = paddingInlineHuge.dp,
        spacious = paddingInlineSpacious.dp,
    ),
    paddingBlock = OudsSpaces.PaddingBlock(
        none = paddingBlockNone.dp,
        smash = paddingBlockSmash.dp,
        shortest = paddingBlockShortest.dp,
        shorter = paddingBlockShorter.dp,
        short = paddingBlockShort.dp,
        medium = paddingBlockMedium.dp,
        tall = paddingBlockTall.dp,
        taller = paddingBlockTaller.dp,
        tallest = paddingBlockTallest.dp,
        spacious = paddingBlockSpacious.dp,
        huge = paddingBlockHuge.dp,
    ),
    inset = OudsSpaces.Inset(
        none = insetNone.dp,
        smash = insetSmash.dp,
        shortest = insetShortest.dp,
        shorter = insetShorter.dp,
        short = insetShort.dp,
        medium = insetMedium.dp,
        tall = insetTall.dp,
        taller = insetTaller.dp,
        tallest = insetTallest.dp,
        spacious = insetSpacious.dp,
    ),
    columnGap = OudsSpaces.ColumnGap(
        none = columnGapNone.dp,
        smash = columnGapSmash.dp,
        shortest = columnGapShortest.dp,
        shorter = columnGapShorter.dp,
        short = columnGapShort.dp,
        medium = columnGapMedium.dp,
        tall = columnGapTall.dp,
        taller = columnGapTaller.dp,
        tallest = columnGapTallest.dp,
    ),
    rowGap = OudsSpaces.RowGap(
        none = rowGapNone.dp,
        smash = rowGapSmash.dp,
        shortest = rowGapShortest.dp,
        shorter = rowGapShorter.dp,
        short = rowGapShort.dp,
        medium = rowGapMedium.dp,
        tall = rowGapTall.dp,
    )
)

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Fixed): Dp {
    return when (token) {
        OudsSpaceKeyToken.Fixed.None -> fixed.none
        OudsSpaceKeyToken.Fixed.Smash -> fixed.smash
        OudsSpaceKeyToken.Fixed.Shortest -> fixed.shortest
        OudsSpaceKeyToken.Fixed.Shorter -> fixed.shorter
        OudsSpaceKeyToken.Fixed.Short -> fixed.short
        OudsSpaceKeyToken.Fixed.Medium -> fixed.medium
        OudsSpaceKeyToken.Fixed.Tall -> fixed.tall
        OudsSpaceKeyToken.Fixed.Taller -> fixed.taller
        OudsSpaceKeyToken.Fixed.Tallest -> fixed.tallest
        OudsSpaceKeyToken.Fixed.Spacious -> fixed.spacious
        OudsSpaceKeyToken.Fixed.Huge -> fixed.huge
        OudsSpaceKeyToken.Fixed.Jumbo -> fixed.jumbo
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Scaled, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSpaceKeyToken.Scaled.None -> scaled.none
        OudsSpaceKeyToken.Scaled.Smash -> scaled.smash
        OudsSpaceKeyToken.Scaled.Shortest -> scaled.shortest
        OudsSpaceKeyToken.Scaled.Shorter -> scaled.shorter
        OudsSpaceKeyToken.Scaled.Short -> scaled.short
        OudsSpaceKeyToken.Scaled.Medium -> scaled.medium
        OudsSpaceKeyToken.Scaled.Tall -> scaled.tall
        OudsSpaceKeyToken.Scaled.Taller -> scaled.taller
        OudsSpaceKeyToken.Scaled.Tallest -> scaled.tallest
        OudsSpaceKeyToken.Scaled.Spacious -> scaled.spacious
    }.getValue(adaptiveWindowType)
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingInline.None -> paddingInline.none
        OudsSpaceKeyToken.PaddingInline.Smash -> paddingInline.smash
        OudsSpaceKeyToken.PaddingInline.Shortest -> paddingInline.shortest
        OudsSpaceKeyToken.PaddingInline.Shorter -> paddingInline.shorter
        OudsSpaceKeyToken.PaddingInline.Short -> paddingInline.short
        OudsSpaceKeyToken.PaddingInline.Medium -> paddingInline.medium
        OudsSpaceKeyToken.PaddingInline.Tall -> paddingInline.tall
        OudsSpaceKeyToken.PaddingInline.Taller -> paddingInline.taller
        OudsSpaceKeyToken.PaddingInline.Tallest -> paddingInline.tallest
        OudsSpaceKeyToken.PaddingInline.Huge -> paddingInline.huge
        OudsSpaceKeyToken.PaddingInline.Spacious -> paddingInline.spacious
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingBlock.None -> paddingBlock.none
        OudsSpaceKeyToken.PaddingBlock.Smash -> paddingBlock.smash
        OudsSpaceKeyToken.PaddingBlock.Shortest -> paddingBlock.shortest
        OudsSpaceKeyToken.PaddingBlock.Shorter -> paddingBlock.shorter
        OudsSpaceKeyToken.PaddingBlock.Short -> paddingBlock.short
        OudsSpaceKeyToken.PaddingBlock.Medium -> paddingBlock.medium
        OudsSpaceKeyToken.PaddingBlock.Tall -> paddingBlock.tall
        OudsSpaceKeyToken.PaddingBlock.Taller -> paddingBlock.taller
        OudsSpaceKeyToken.PaddingBlock.Tallest -> paddingBlock.tallest
        OudsSpaceKeyToken.PaddingBlock.Spacious -> paddingBlock.spacious
        OudsSpaceKeyToken.PaddingBlock.Huge -> paddingBlock.huge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Inset): Dp {
    return when (token) {
        OudsSpaceKeyToken.Inset.None -> inset.none
        OudsSpaceKeyToken.Inset.Smash -> inset.smash
        OudsSpaceKeyToken.Inset.Shortest -> inset.shortest
        OudsSpaceKeyToken.Inset.Shorter -> inset.shorter
        OudsSpaceKeyToken.Inset.Short -> inset.short
        OudsSpaceKeyToken.Inset.Medium -> inset.medium
        OudsSpaceKeyToken.Inset.Tall -> inset.tall
        OudsSpaceKeyToken.Inset.Taller -> inset.taller
        OudsSpaceKeyToken.Inset.Tallest -> inset.tallest
        OudsSpaceKeyToken.Inset.Spacious -> inset.spacious
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.None -> columnGap.none
        OudsSpaceKeyToken.ColumnGap.Smash -> columnGap.smash
        OudsSpaceKeyToken.ColumnGap.Shortest -> columnGap.shortest
        OudsSpaceKeyToken.ColumnGap.Shorter -> columnGap.shorter
        OudsSpaceKeyToken.ColumnGap.Short -> columnGap.short
        OudsSpaceKeyToken.ColumnGap.Medium -> columnGap.medium
        OudsSpaceKeyToken.ColumnGap.Tall -> columnGap.tall
        OudsSpaceKeyToken.ColumnGap.Taller -> columnGap.taller
        OudsSpaceKeyToken.ColumnGap.Tallest -> columnGap.tallest
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.RowGap.None -> rowGap.none
        OudsSpaceKeyToken.RowGap.Smash -> rowGap.smash
        OudsSpaceKeyToken.RowGap.Shortest -> rowGap.shortest
        OudsSpaceKeyToken.RowGap.Shorter -> rowGap.shorter
        OudsSpaceKeyToken.RowGap.Short -> rowGap.short
        OudsSpaceKeyToken.RowGap.Medium -> rowGap.medium
        OudsSpaceKeyToken.RowGap.Tall -> rowGap.tall
    }
}

/**
 * Converts an OUDS space token to the local space value provided by the theme.
 */
val OudsSpaceKeyToken.value: Dp
    @Composable
    get() = when (this) {
        is OudsSpaceKeyToken.ColumnGap -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Fixed -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Inset -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.PaddingBlock -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.PaddingInline -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.RowGap -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Scaled -> OudsTheme.spaces.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
    }
