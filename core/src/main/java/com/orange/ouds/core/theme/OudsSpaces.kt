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
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val withIcon: WithIcon,
        val withArrow: WithArrow
    ) {
        data class WithIcon(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
            val taller: Dp,
            val tallest: Dp,
        )

        data class WithArrow(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
            val taller: Dp,
            val tallest: Dp,
        )
    }

    data class PaddingBlock(
        val none: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val tallest: Dp,
        val withIcon: WithIcon
    ) {
        data class WithIcon(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
            val taller: Dp,
        )
    }

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
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val taller: Dp,
        val withIcon: WithIcon,
        val withArrow: WithArrow
    ) {
        data class WithIcon(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
        )

        data class WithArrow(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
        )
    }

    data class RowGap(
        val none: Dp,
        val shortest: Dp,
        val shorter: Dp,
        val short: Dp,
        val medium: Dp,
        val tall: Dp,
        val withIcon: WithIcon
    ) {
        data class WithIcon(
            val none: Dp,
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
        )
    }
}

fun OudsSpaceSemanticTokens.getSpaces() = OudsSpaces(
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
        shorter = paddingInlineShorter.dp,
        short = paddingInlineShort.dp,
        medium = paddingInlineMedium.dp,
        tall = paddingInlineTall.dp,
        taller = paddingInlineTaller.dp,
        tallest = paddingInlineTallest.dp,
        withIcon = OudsSpaces.PaddingInline.WithIcon(
            none = paddingInlineWithIconNone.dp,
            shortest = paddingInlineWithIconShortest.dp,
            shorter = paddingInlineWithIconShorter.dp,
            short = paddingInlineWithIconShort.dp,
            medium = paddingInlineWithIconMedium.dp,
            tall = paddingInlineWithIconTall.dp,
            taller = paddingInlineWithIconTaller.dp,
            tallest = paddingInlineWithIconTallest.dp,
        ),
        withArrow = OudsSpaces.PaddingInline.WithArrow(
            none = paddingInlineWithArrowNone.dp,
            shortest = paddingInlineWithArrowShortest.dp,
            shorter = paddingInlineWithArrowShorter.dp,
            short = paddingInlineWithArrowShort.dp,
            medium = paddingInlineWithArrowMedium.dp,
            tall = paddingInlineWithArrowTall.dp,
            taller = paddingInlineWithArrowTaller.dp,
            tallest = paddingInlineWithArrowTallest.dp,
        ),
    ),
    paddingBlock = OudsSpaces.PaddingBlock(
        none = paddingBlockNone.dp,
        shorter = paddingBlockShorter.dp,
        short = paddingBlockShort.dp,
        medium = paddingBlockMedium.dp,
        tall = paddingBlockTall.dp,
        taller = paddingBlockTaller.dp,
        tallest = paddingBlockTallest.dp,
        withIcon = OudsSpaces.PaddingBlock.WithIcon(
            none = paddingBlockWithIconNone.dp,
            shortest = paddingBlockWithIconShortest.dp,
            shorter = paddingBlockWithIconShorter.dp,
            short = paddingBlockWithIconShort.dp,
            medium = paddingBlockWithIconMedium.dp,
            tall = paddingBlockWithIconTall.dp,
            taller = paddingBlockWithIconTaller.dp,
        ),
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
        shorter = columnGapShorter.dp,
        short = columnGapShort.dp,
        medium = columnGapMedium.dp,
        tall = columnGapTall.dp,
        taller = columnGapTaller.dp,
        withIcon = OudsSpaces.ColumnGap.WithIcon(
            none = columnGapWithIconNone.dp,
            shortest = columnGapWithIconShortest.dp,
            shorter = columnGapWithIconShorter.dp,
            short = columnGapWithIconShort.dp,
            medium = columnGapWithIconMedium.dp,
            tall = columnGapWithIconTall.dp,
        ),
        withArrow = OudsSpaces.ColumnGap.WithArrow(
            none = columnGapWithArrowNone.dp,
            shortest = columnGapWithArrowShortest.dp,
            shorter = columnGapWithArrowShorter.dp,
            short = columnGapWithArrowShort.dp,
            medium = columnGapWithArrowMedium.dp,
            tall = columnGapWithArrowTall.dp,
        ),
    ),
    rowGap = OudsSpaces.RowGap(
        none = rowGapNone.dp,
        shortest = rowGapShortest.dp,
        shorter = rowGapShorter.dp,
        short = rowGapShort.dp,
        medium = rowGapMedium.dp,
        tall = rowGapTall.dp,
        withIcon = OudsSpaces.RowGap.WithIcon(
            none = rowGapWithIconNone.dp,
            shortest = rowGapWithIconShortest.dp,
            shorter = rowGapWithIconShorter.dp,
            short = rowGapWithIconShort.dp,
            medium = rowGapWithIconMedium.dp,
            tall = rowGapWithIconTall.dp,
        )
    )
)

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Fixed): Dp {
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
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Scaled, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionAdaptableSpaceToken = when (token) {
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
        OudsSpaceKeyToken.PaddingInline.None -> paddingInline.none
        OudsSpaceKeyToken.PaddingInline.Shorter -> paddingInline.shorter
        OudsSpaceKeyToken.PaddingInline.Short -> paddingInline.short
        OudsSpaceKeyToken.PaddingInline.Medium -> paddingInline.medium
        OudsSpaceKeyToken.PaddingInline.Tall -> paddingInline.tall
        OudsSpaceKeyToken.PaddingInline.Taller -> paddingInline.taller
        OudsSpaceKeyToken.PaddingInline.Tallest -> paddingInline.tallest
        OudsSpaceKeyToken.PaddingInline.WithIconNone -> paddingInline.withIcon.none
        OudsSpaceKeyToken.PaddingInline.WithIconShortest -> paddingInline.withIcon.shortest
        OudsSpaceKeyToken.PaddingInline.WithIconShorter -> paddingInline.withIcon.shorter
        OudsSpaceKeyToken.PaddingInline.WithIconShort -> paddingInline.withIcon.short
        OudsSpaceKeyToken.PaddingInline.WithIconMedium -> paddingInline.withIcon.medium
        OudsSpaceKeyToken.PaddingInline.WithIconTall -> paddingInline.withIcon.tall
        OudsSpaceKeyToken.PaddingInline.WithIconTaller -> paddingInline.withIcon.taller
        OudsSpaceKeyToken.PaddingInline.WithIconTallest -> paddingInline.withIcon.tallest
        OudsSpaceKeyToken.PaddingInline.WithArrowNone -> paddingInline.withArrow.none
        OudsSpaceKeyToken.PaddingInline.WithArrowShortest -> paddingInline.withArrow.shortest
        OudsSpaceKeyToken.PaddingInline.WithArrowShorter -> paddingInline.withArrow.shorter
        OudsSpaceKeyToken.PaddingInline.WithArrowShort -> paddingInline.withArrow.short
        OudsSpaceKeyToken.PaddingInline.WithArrowMedium -> paddingInline.withArrow.medium
        OudsSpaceKeyToken.PaddingInline.WithArrowTall -> paddingInline.withArrow.tall
        OudsSpaceKeyToken.PaddingInline.WithArrowTaller -> paddingInline.withArrow.taller
        OudsSpaceKeyToken.PaddingInline.WithArrowTallest -> paddingInline.withArrow.tallest
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingBlock.None -> paddingBlock.none
        OudsSpaceKeyToken.PaddingBlock.Shorter -> paddingBlock.shorter
        OudsSpaceKeyToken.PaddingBlock.Short -> paddingBlock.short
        OudsSpaceKeyToken.PaddingBlock.Medium -> paddingBlock.medium
        OudsSpaceKeyToken.PaddingBlock.Tall -> paddingBlock.tall
        OudsSpaceKeyToken.PaddingBlock.Taller -> paddingBlock.taller
        OudsSpaceKeyToken.PaddingBlock.Tallest -> paddingBlock.tallest
        OudsSpaceKeyToken.PaddingBlock.WithIconNone -> paddingBlock.withIcon.none
        OudsSpaceKeyToken.PaddingBlock.WithIconShortest -> paddingBlock.withIcon.shortest
        OudsSpaceKeyToken.PaddingBlock.WithIconShorter -> paddingBlock.withIcon.shorter
        OudsSpaceKeyToken.PaddingBlock.WithIconShort -> paddingBlock.withIcon.short
        OudsSpaceKeyToken.PaddingBlock.WithIconMedium -> paddingBlock.withIcon.medium
        OudsSpaceKeyToken.PaddingBlock.WithIconTall -> paddingBlock.withIcon.tall
        OudsSpaceKeyToken.PaddingBlock.WithIconTaller -> paddingBlock.withIcon.taller
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Inset): Dp {
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
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.None -> columnGap.none
        OudsSpaceKeyToken.ColumnGap.Shorter -> columnGap.shorter
        OudsSpaceKeyToken.ColumnGap.Short -> columnGap.short
        OudsSpaceKeyToken.ColumnGap.Medium -> columnGap.medium
        OudsSpaceKeyToken.ColumnGap.Tall -> columnGap.tall
        OudsSpaceKeyToken.ColumnGap.Taller -> columnGap.taller
        OudsSpaceKeyToken.ColumnGap.WithIconNone -> columnGap.withIcon.none
        OudsSpaceKeyToken.ColumnGap.WithIconShortest -> columnGap.withIcon.shortest
        OudsSpaceKeyToken.ColumnGap.WithIconShorter -> columnGap.withIcon.shorter
        OudsSpaceKeyToken.ColumnGap.WithIconShort -> columnGap.withIcon.short
        OudsSpaceKeyToken.ColumnGap.WithIconMedium -> columnGap.withIcon.medium
        OudsSpaceKeyToken.ColumnGap.WithIconTall -> columnGap.withIcon.tall
        OudsSpaceKeyToken.ColumnGap.WithArrowNone -> columnGap.withArrow.none
        OudsSpaceKeyToken.ColumnGap.WithArrowShortest -> columnGap.withArrow.shortest
        OudsSpaceKeyToken.ColumnGap.WithArrowShorter -> columnGap.withArrow.shorter
        OudsSpaceKeyToken.ColumnGap.WithArrowShort -> columnGap.withArrow.short
        OudsSpaceKeyToken.ColumnGap.WithArrowMedium -> columnGap.withArrow.medium
        OudsSpaceKeyToken.ColumnGap.WithArrowTall -> columnGap.withArrow.tall
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.RowGap.None -> rowGap.none
        OudsSpaceKeyToken.RowGap.Shortest -> rowGap.shortest
        OudsSpaceKeyToken.RowGap.Shorter -> rowGap.shorter
        OudsSpaceKeyToken.RowGap.Short -> rowGap.short
        OudsSpaceKeyToken.RowGap.Medium -> rowGap.medium
        OudsSpaceKeyToken.RowGap.Tall -> rowGap.tall
        OudsSpaceKeyToken.RowGap.WithIconNone -> rowGap.withIcon.none
        OudsSpaceKeyToken.RowGap.WithIconShortest -> rowGap.withIcon.shortest
        OudsSpaceKeyToken.RowGap.WithIconShorter -> rowGap.withIcon.shorter
        OudsSpaceKeyToken.RowGap.WithIconShort -> rowGap.withIcon.short
        OudsSpaceKeyToken.RowGap.WithIconMedium -> rowGap.withIcon.medium
        OudsSpaceKeyToken.RowGap.WithIconTall -> rowGap.withIcon.tall
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
