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
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline.WithIcon): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingInline.WithIcon.None -> paddingInline.withIcon.none
        OudsSpaceKeyToken.PaddingInline.WithIcon.Shortest -> paddingInline.withIcon.shortest
        OudsSpaceKeyToken.PaddingInline.WithIcon.Shorter -> paddingInline.withIcon.shorter
        OudsSpaceKeyToken.PaddingInline.WithIcon.Short -> paddingInline.withIcon.short
        OudsSpaceKeyToken.PaddingInline.WithIcon.Medium -> paddingInline.withIcon.medium
        OudsSpaceKeyToken.PaddingInline.WithIcon.Tall -> paddingInline.withIcon.tall
        OudsSpaceKeyToken.PaddingInline.WithIcon.Taller -> paddingInline.withIcon.taller
        OudsSpaceKeyToken.PaddingInline.WithIcon.Tallest -> paddingInline.withIcon.tallest
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline.WithArrow): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingInline.WithArrow.None -> paddingInline.withArrow.none
        OudsSpaceKeyToken.PaddingInline.WithArrow.Shortest -> paddingInline.withArrow.shortest
        OudsSpaceKeyToken.PaddingInline.WithArrow.Shorter -> paddingInline.withArrow.shorter
        OudsSpaceKeyToken.PaddingInline.WithArrow.Short -> paddingInline.withArrow.short
        OudsSpaceKeyToken.PaddingInline.WithArrow.Medium -> paddingInline.withArrow.medium
        OudsSpaceKeyToken.PaddingInline.WithArrow.Tall -> paddingInline.withArrow.tall
        OudsSpaceKeyToken.PaddingInline.WithArrow.Taller -> paddingInline.withArrow.taller
        OudsSpaceKeyToken.PaddingInline.WithArrow.Tallest -> paddingInline.withArrow.tallest
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
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock.WithIcon): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingBlock.WithIcon.None -> paddingBlock.withIcon.none
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Shortest -> paddingBlock.withIcon.shortest
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Shorter -> paddingBlock.withIcon.shorter
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Short -> paddingBlock.withIcon.short
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Medium -> paddingBlock.withIcon.medium
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Tall -> paddingBlock.withIcon.tall
        OudsSpaceKeyToken.PaddingBlock.WithIcon.Taller -> paddingBlock.withIcon.taller
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
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap.WithIcon): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.WithIcon.None -> columnGap.withIcon.none
        OudsSpaceKeyToken.ColumnGap.WithIcon.Shortest -> columnGap.withIcon.shortest
        OudsSpaceKeyToken.ColumnGap.WithIcon.Shorter -> columnGap.withIcon.shorter
        OudsSpaceKeyToken.ColumnGap.WithIcon.Short -> columnGap.withIcon.short
        OudsSpaceKeyToken.ColumnGap.WithIcon.Medium -> columnGap.withIcon.medium
        OudsSpaceKeyToken.ColumnGap.WithIcon.Tall -> columnGap.withIcon.tall
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap.WithArrow): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.WithArrow.None -> columnGap.withArrow.none
        OudsSpaceKeyToken.ColumnGap.WithArrow.Shortest -> columnGap.withArrow.shortest
        OudsSpaceKeyToken.ColumnGap.WithArrow.Shorter -> columnGap.withArrow.shorter
        OudsSpaceKeyToken.ColumnGap.WithArrow.Short -> columnGap.withArrow.short
        OudsSpaceKeyToken.ColumnGap.WithArrow.Medium -> columnGap.withArrow.medium
        OudsSpaceKeyToken.ColumnGap.WithArrow.Tall -> columnGap.withArrow.tall
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
    }
}

@Stable
fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap.WithIcon): Dp {
    return when (token) {
        OudsSpaceKeyToken.RowGap.WithIcon.None -> rowGap.withIcon.none
        OudsSpaceKeyToken.RowGap.WithIcon.Shortest -> rowGap.withIcon.shortest
        OudsSpaceKeyToken.RowGap.WithIcon.Shorter -> rowGap.withIcon.shorter
        OudsSpaceKeyToken.RowGap.WithIcon.Short -> rowGap.withIcon.short
        OudsSpaceKeyToken.RowGap.WithIcon.Medium -> rowGap.withIcon.medium
        OudsSpaceKeyToken.RowGap.WithIcon.Tall -> rowGap.withIcon.tall
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
 * Converts an OUDS column gap with icon space token to the local column gap with icon space value provided by the theme.
 */
val OudsSpaceKeyToken.ColumnGap.WithIcon.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS column gap with arrow space token to the local column gap with arrow space value provided by the theme.
 */
val OudsSpaceKeyToken.ColumnGap.WithArrow.value: Dp
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
 * Converts an OUDS block with icon padding token to the local block with icon padding value provided by the theme.
 */
val OudsSpaceKeyToken.PaddingBlock.WithIcon.value: Dp
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
 * Converts an OUDS inline padding with icon token to the local inline padding with icon value provided by the theme.
 */
val OudsSpaceKeyToken.PaddingInline.WithIcon.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS inline padding with arrow token to the local inline padding with arrow value provided by the theme.
 */
val OudsSpaceKeyToken.PaddingInline.WithArrow.value: Dp
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
 * Converts an OUDS row gap with icon space token to the local row gap with icon space value provided by the theme.
 */
val OudsSpaceKeyToken.RowGap.WithIcon.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.spaces.fromToken(this)

/**
 * Converts an OUDS scaled space token to the local space value provided by the theme depending on the window size.
 */
val OudsSpaceKeyToken.Scaled.value: Dp
    @Composable
    get() = OudsTheme.spaces.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
