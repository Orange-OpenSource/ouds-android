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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens

/**
 * Holds the spacing values defined in the OUDS theme.
 *
 * Spacing tokens are used to define margins, paddings, and gaps between elements,
 * ensuring a consistent rhythm and layout structure across the application.
 *
 * > Design guidelines: [Space tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-space)
 *
 * @property fixed Fixed spacing values that do not change based on screen size.
 * @property scaled Spacing values that scale adaptively based on the screen size (e.g., Mobile vs Tablet).
 * @property paddingInline Spacing used for horizontal padding (start/end).
 * @property paddingBlock Spacing used for vertical padding (top/bottom).
 * @property inset Spacing used for inset distances (e.g., distance from the edge of a container).
 * @property columnGap Spacing used for gaps between columns in a grid or layout.
 * @property rowGap Spacing used for gaps between rows in a grid or layout.
 */
@ConsistentCopyVisibility
data class OudsSpaces internal constructor(
    val fixed: Fixed,
    val scaled: Scaled,
    val paddingInline: PaddingInline,
    val paddingBlock: PaddingBlock,
    val inset: Inset,
    val columnGap: ColumnGap,
    val rowGap: RowGap
) {

    /**
     * Fixed spacing values that remain constant regardless of the screen size context.
     *
     * @property none 0dp space.
     * @property threeExtraSmall 3xs space.
     * @property twoExtraSmall 2xs space.
     * @property extraSmall Extra small space.
     * @property small Small space.
     * @property medium Medium space.
     * @property large Large space.
     * @property extraLarge Extra large space.
     * @property twoExtraLarge 2xl space.
     * @property threeExtraLarge 3xl space.
     * @property fourExtraLarge 4xl space.
     * @property fiveExtraLarge 5xl space.
     */
    @ConsistentCopyVisibility
    data class Fixed internal constructor(
        val none: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
        val threeExtraLarge: Dp,
        val fourExtraLarge: Dp,
        val fiveExtraLarge: Dp
    )


    /**
     * Scaled spacing values that adapt based on the window size class (e.g. larger on tablets).
     *
     * @property none 0dp space.
     * @property threeExtraSmall 3xs scaled space.
     * @property twoExtraSmall 2xs scaled space.
     * @property extraSmall Extra small scaled space.
     * @property small Small scaled space.
     * @property medium Medium scaled space.
     * @property large Large scaled space.
     * @property extraLarge Extra large scaled space.
     * @property twoExtraLarge 2xl scaled space.
     * @property threeExtraLarge 3xl scaled space.
     */
    @ConsistentCopyVisibility
    data class Scaled internal constructor(
        val none: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
        val threeExtraLarge: Dp
    )

    /**
     * Spacing values specifically designed for inline padding (Start/End).
     *
     * @property none 0dp padding.
     * @property fourExtraSmall 4xs padding.
     * @property threeExtraSmall 3xs padding.
     * @property twoExtraSmall 2xs padding.
     * @property extraSmall Extra small padding.
     * @property small Small padding.
     * @property medium Medium padding.
     * @property large Large padding.
     * @property extraLarge Extra large padding.
     * @property twoExtraLarge 2xl padding.
     * @property threeExtraLarge 3xl padding.
     * @property fourExtraLarge 4xl padding.
     */
    @ConsistentCopyVisibility
    data class PaddingInline internal constructor(
        val none: Dp,
        val fourExtraSmall: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
        val threeExtraLarge: Dp,
        val fourExtraLarge: Dp,
    )


    /**
     * Spacing values specifically designed for block padding (Top/Bottom).
     *
     * @property none 0dp padding.
     * @property fourExtraSmall 4xs padding.
     * @property threeExtraSmall 3xs padding.
     * @property twoExtraSmall 2xs padding.
     * @property extraSmall Extra small padding.
     * @property small Small padding.
     * @property medium Medium padding.
     * @property large Large padding.
     * @property extraLarge Extra large padding.
     * @property twoExtraLarge 2xl padding.
     * @property threeExtraLarge 3xl padding.
     * @property fourExtraLarge 4xl padding.
     */
    @ConsistentCopyVisibility
    data class PaddingBlock internal constructor(
        val none: Dp,
        val fourExtraSmall: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
        val threeExtraLarge: Dp,
        val fourExtraLarge: Dp,
    )

    /**
     * Spacing values used for inset positioning.
     *
     * @property none 0dp inset.
     * @property fourExtraSmall 4xs inset.
     * @property threeExtraSmall 3xs inset.
     * @property twoExtraSmall 2xs inset.
     * @property extraSmall Extra small inset.
     * @property small Small inset.
     * @property medium Medium inset.
     * @property large Large inset.
     * @property extraLarge Extra large inset.
     * @property twoExtraLarge 2xl inset.
     * @property threeExtraLarge 3xl inset.
     */
    @ConsistentCopyVisibility
    data class Inset internal constructor(
        val none: Dp,
        val fourExtraSmall: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
        val threeExtraLarge: Dp,
    )

    /**
     * Spacing values used for gaps between columns.
     *
     * @property none 0dp gap.
     * @property threeExtraSmall 3xs gap.
     * @property twoExtraSmall 2xs gap.
     * @property extraSmall Extra small gap.
     * @property small Small gap.
     * @property medium Medium gap.
     * @property large Large gap.
     * @property extraLarge Extra large gap.
     * @property twoExtraLarge 2xl gap.
     */
    @ConsistentCopyVisibility
    data class ColumnGap internal constructor(
        val none: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
        val extraLarge: Dp,
        val twoExtraLarge: Dp,
    )

    /**
     * Spacing values used for gaps between rows.
     *
     * @property none 0dp gap.
     * @property threeExtraSmall 3xs gap.
     * @property twoExtraSmall 2xs gap.
     * @property extraSmall Extra small gap.
     * @property small Small gap.
     * @property medium Medium gap.
     * @property large Large gap.
     */
    @ConsistentCopyVisibility
    data class RowGap internal constructor(
        val none: Dp,
        val threeExtraSmall: Dp,
        val twoExtraSmall: Dp,
        val extraSmall: Dp,
        val small: Dp,
        val medium: Dp,
        val large: Dp,
    )
}

internal fun OudsSpaceSemanticTokens.getSpaces(windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsSpaces(
        fixed = OudsSpaces.Fixed(
            none = fixedNone.dp,
            threeExtraSmall = fixed3xsmall.dp,
            twoExtraSmall = fixed2xsmall.dp,
            extraSmall = fixedXsmall.dp,
            small = fixedSmall.dp,
            medium = fixedMedium.dp,
            large = fixedLarge.dp,
            extraLarge = fixedXlarge.dp,
            twoExtraLarge = fixed2xlarge.dp,
            threeExtraLarge = fixed3xlarge.dp,
            fourExtraLarge = fixed4xlarge.dp,
            fiveExtraLarge = fixed5xlarge.dp,
        ),
        scaled = OudsSpaces.Scaled(
            none = getTokenValue(scaledNoneMobile, scaledNoneTablet).dp,
            threeExtraSmall = getTokenValue(scaled3xsmallMobile, scaled3xsmallTablet).dp,
            twoExtraSmall = getTokenValue(scaled2xsmallMobile, scaled2xsmallTablet).dp,
            extraSmall = getTokenValue(scaledXsmallMobile, scaledXsmallTablet).dp,
            small = getTokenValue(scaledSmallMobile, scaledSmallTablet).dp,
            medium = getTokenValue(scaledMediumMobile, scaledMediumTablet).dp,
            large = getTokenValue(scaledLargeMobile, scaledLargeTablet).dp,
            extraLarge = getTokenValue(scaledXlargeMobile, scaledXlargeTablet).dp,
            twoExtraLarge = getTokenValue(scaled2xlargeMobile, scaled2xlargeTablet).dp,
            threeExtraLarge = getTokenValue(scaled3xlargeMobile, scaled3xlargeTablet).dp,
        ),
        paddingInline = OudsSpaces.PaddingInline(
            none = paddingInlineNone.dp,
            fourExtraSmall = paddingInline4xsmall.dp,
            threeExtraSmall = paddingInline3xsmall.dp,
            twoExtraSmall = paddingInline2xsmall.dp,
            extraSmall = paddingInlineXsmall.dp,
            small = paddingInlineSmall.dp,
            medium = paddingInlineMedium.dp,
            large = paddingInlineLarge.dp,
            extraLarge = paddingInlineXlarge.dp,
            twoExtraLarge = paddingInline2xlarge.dp,
            threeExtraLarge = paddingInline3xlarge.dp,
            fourExtraLarge = paddingInline4xlarge.dp,
        ),
        paddingBlock = OudsSpaces.PaddingBlock(
            none = paddingBlockNone.dp,
            fourExtraSmall = paddingBlock4xsmall.dp,
            threeExtraSmall = paddingBlock3xsmall.dp,
            twoExtraSmall = paddingBlock2xsmall.dp,
            extraSmall = paddingBlockXsmall.dp,
            small = paddingBlockSmall.dp,
            medium = paddingBlockMedium.dp,
            large = paddingBlockLarge.dp,
            extraLarge = paddingBlockXlarge.dp,
            twoExtraLarge = paddingBlock2xlarge.dp,
            threeExtraLarge = paddingBlock3xlarge.dp,
            fourExtraLarge = paddingBlock4xlarge.dp,
        ),
        inset = OudsSpaces.Inset(
            none = insetNone.dp,
            fourExtraSmall = inset4xsmall.dp,
            threeExtraSmall = inset3xsmall.dp,
            twoExtraSmall = inset2xsmall.dp,
            extraSmall = insetXsmall.dp,
            small = insetSmall.dp,
            medium = insetMedium.dp,
            large = insetLarge.dp,
            extraLarge = insetXlarge.dp,
            twoExtraLarge = inset2xlarge.dp,
            threeExtraLarge = inset3xlarge.dp,
        ),
        columnGap = OudsSpaces.ColumnGap(
            none = columnGapNone.dp,
            threeExtraSmall = columnGap3xsmall.dp,
            twoExtraSmall = columnGap2xsmall.dp,
            extraSmall = columnGapXsmall.dp,
            small = columnGapSmall.dp,
            medium = columnGapMedium.dp,
            large = columnGapLarge.dp,
            extraLarge = columnGapXlarge.dp,
            twoExtraLarge = columnGap2xlarge.dp,
        ),
        rowGap = OudsSpaces.RowGap(
            none = rowGapNone.dp,
            threeExtraSmall = rowGap3xsmall.dp,
            twoExtraSmall = rowGap2xsmall.dp,
            extraSmall = rowGapXsmall.dp,
            small = rowGapSmall.dp,
            medium = rowGapMedium.dp,
            large = rowGapLarge.dp,
        )
    )
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Fixed): Dp {
    return when (token) {
        OudsSpaceKeyToken.Fixed.None -> fixed.none
        OudsSpaceKeyToken.Fixed.ThreeExtraSmall -> fixed.threeExtraSmall
        OudsSpaceKeyToken.Fixed.TwoExtraSmall -> fixed.twoExtraSmall
        OudsSpaceKeyToken.Fixed.ExtraSmall -> fixed.extraSmall
        OudsSpaceKeyToken.Fixed.Small -> fixed.small
        OudsSpaceKeyToken.Fixed.Medium -> fixed.medium
        OudsSpaceKeyToken.Fixed.Large -> fixed.large
        OudsSpaceKeyToken.Fixed.ExtraLarge -> fixed.extraLarge
        OudsSpaceKeyToken.Fixed.TwoExtraLarge -> fixed.twoExtraLarge
        OudsSpaceKeyToken.Fixed.ThreeExtraLarge -> fixed.threeExtraLarge
        OudsSpaceKeyToken.Fixed.FourExtraLarge -> fixed.fourExtraLarge
        OudsSpaceKeyToken.Fixed.FiveExtraLarge -> fixed.fiveExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Scaled): Dp {
    return when (token) {
        OudsSpaceKeyToken.Scaled.None -> scaled.none
        OudsSpaceKeyToken.Scaled.ThreeExtraSmall -> scaled.threeExtraSmall
        OudsSpaceKeyToken.Scaled.TwoExtraSmall -> scaled.twoExtraSmall
        OudsSpaceKeyToken.Scaled.ExtraSmall -> scaled.extraSmall
        OudsSpaceKeyToken.Scaled.Small -> scaled.small
        OudsSpaceKeyToken.Scaled.Medium -> scaled.medium
        OudsSpaceKeyToken.Scaled.Large -> scaled.large
        OudsSpaceKeyToken.Scaled.ExtraLarge -> scaled.extraLarge
        OudsSpaceKeyToken.Scaled.TwoExtraLarge -> scaled.twoExtraLarge
        OudsSpaceKeyToken.Scaled.ThreeExtraLarge -> scaled.threeExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingInline.None -> paddingInline.none
        OudsSpaceKeyToken.PaddingInline.FourExtraSmall -> paddingInline.fourExtraSmall
        OudsSpaceKeyToken.PaddingInline.ThreeExtraSmall -> paddingInline.threeExtraSmall
        OudsSpaceKeyToken.PaddingInline.TwoExtraSmall -> paddingInline.twoExtraSmall
        OudsSpaceKeyToken.PaddingInline.ExtraSmall -> paddingInline.extraSmall
        OudsSpaceKeyToken.PaddingInline.Small -> paddingInline.small
        OudsSpaceKeyToken.PaddingInline.Medium -> paddingInline.medium
        OudsSpaceKeyToken.PaddingInline.Large -> paddingInline.large
        OudsSpaceKeyToken.PaddingInline.ExtraLarge -> paddingInline.extraLarge
        OudsSpaceKeyToken.PaddingInline.TwoExtraLarge -> paddingInline.twoExtraLarge
        OudsSpaceKeyToken.PaddingInline.ThreeExtraLarge -> paddingInline.threeExtraLarge
        OudsSpaceKeyToken.PaddingInline.FourExtraLarge -> paddingInline.fourExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock): Dp {
    return when (token) {
        OudsSpaceKeyToken.PaddingBlock.None -> paddingBlock.none
        OudsSpaceKeyToken.PaddingBlock.FourExtraSmall -> paddingBlock.fourExtraSmall
        OudsSpaceKeyToken.PaddingBlock.ThreeExtraSmall -> paddingBlock.threeExtraSmall
        OudsSpaceKeyToken.PaddingBlock.TwoExtraSmall -> paddingBlock.twoExtraSmall
        OudsSpaceKeyToken.PaddingBlock.ExtraSmall -> paddingBlock.extraSmall
        OudsSpaceKeyToken.PaddingBlock.Small -> paddingBlock.small
        OudsSpaceKeyToken.PaddingBlock.Medium -> paddingBlock.medium
        OudsSpaceKeyToken.PaddingBlock.Large -> paddingBlock.large
        OudsSpaceKeyToken.PaddingBlock.ExtraLarge -> paddingBlock.extraLarge
        OudsSpaceKeyToken.PaddingBlock.TwoExtraLarge -> paddingBlock.twoExtraLarge
        OudsSpaceKeyToken.PaddingBlock.ThreeExtraLarge -> paddingBlock.threeExtraLarge
        OudsSpaceKeyToken.PaddingBlock.FourExtraLarge -> paddingBlock.fourExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Inset): Dp {
    return when (token) {
        OudsSpaceKeyToken.Inset.None -> inset.none
        OudsSpaceKeyToken.Inset.FourExtraSmall -> inset.fourExtraSmall
        OudsSpaceKeyToken.Inset.ThreeExtraSmall -> inset.threeExtraSmall
        OudsSpaceKeyToken.Inset.TwoExtraSmall -> inset.twoExtraSmall
        OudsSpaceKeyToken.Inset.ExtraSmall -> inset.extraSmall
        OudsSpaceKeyToken.Inset.Small -> inset.small
        OudsSpaceKeyToken.Inset.Medium -> inset.medium
        OudsSpaceKeyToken.Inset.Large -> inset.large
        OudsSpaceKeyToken.Inset.ExtraLarge -> inset.extraLarge
        OudsSpaceKeyToken.Inset.TwoExtraLarge -> inset.twoExtraLarge
        OudsSpaceKeyToken.Inset.ThreeExtraLarge -> inset.threeExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.ColumnGap.None -> columnGap.none
        OudsSpaceKeyToken.ColumnGap.ThreeExtraSmall -> columnGap.threeExtraSmall
        OudsSpaceKeyToken.ColumnGap.TwoExtraSmall -> columnGap.twoExtraSmall
        OudsSpaceKeyToken.ColumnGap.ExtraSmall -> columnGap.extraSmall
        OudsSpaceKeyToken.ColumnGap.Small -> columnGap.small
        OudsSpaceKeyToken.ColumnGap.Medium -> columnGap.medium
        OudsSpaceKeyToken.ColumnGap.Large -> columnGap.large
        OudsSpaceKeyToken.ColumnGap.ExtraLarge -> columnGap.extraLarge
        OudsSpaceKeyToken.ColumnGap.TwoExtraLarge -> columnGap.twoExtraLarge
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap): Dp {
    return when (token) {
        OudsSpaceKeyToken.RowGap.None -> rowGap.none
        OudsSpaceKeyToken.RowGap.ThreeExtraSmall -> rowGap.threeExtraSmall
        OudsSpaceKeyToken.RowGap.TwoExtraSmall -> rowGap.twoExtraSmall
        OudsSpaceKeyToken.RowGap.ExtraSmall -> rowGap.extraSmall
        OudsSpaceKeyToken.RowGap.Small -> rowGap.small
        OudsSpaceKeyToken.RowGap.Medium -> rowGap.medium
        OudsSpaceKeyToken.RowGap.Large -> rowGap.large
    }
}

/**
 * Converts an OUDS space token to the local space value provided by the theme.
 *
 * @suppress
 */
@InternalOudsApi
val OudsSpaceKeyToken.value: Dp
    @Composable
    get() = when (this) {
        is OudsSpaceKeyToken.ColumnGap -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Fixed -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Inset -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.PaddingBlock -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.PaddingInline -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.RowGap -> OudsTheme.spaces.fromToken(this)
        is OudsSpaceKeyToken.Scaled -> OudsTheme.spaces.fromToken(this)
    }
