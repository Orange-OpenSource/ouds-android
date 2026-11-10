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
import com.orange.ouds.foundation.RestrictedOudsApi
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
class OudsSpaces internal constructor(
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
    class Fixed internal constructor(
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
    class Scaled internal constructor(
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
     * @property fiveExtraLarge 5xl padding.
     * @property sixExtraLarge 6xl padding.
     */
    class PaddingInline internal constructor(
        @RestrictedOudsApi val none: Dp,
        @RestrictedOudsApi val fourExtraSmall: Dp,
        @RestrictedOudsApi val threeExtraSmall: Dp,
        @RestrictedOudsApi val twoExtraSmall: Dp,
        @RestrictedOudsApi val extraSmall: Dp,
        @RestrictedOudsApi val small: Dp,
        @RestrictedOudsApi val medium: Dp,
        @RestrictedOudsApi val large: Dp,
        @RestrictedOudsApi val extraLarge: Dp,
        @RestrictedOudsApi val twoExtraLarge: Dp,
        @RestrictedOudsApi val threeExtraLarge: Dp,
        @RestrictedOudsApi val fourExtraLarge: Dp,
        @RestrictedOudsApi val fiveExtraLarge: Dp,
        @RestrictedOudsApi val sixExtraLarge: Dp
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
     * @property fiveExtraLarge 5xl padding.
     */
    class PaddingBlock internal constructor(
        @RestrictedOudsApi val none: Dp,
        @RestrictedOudsApi val fourExtraSmall: Dp,
        @RestrictedOudsApi val threeExtraSmall: Dp,
        @RestrictedOudsApi val twoExtraSmall: Dp,
        @RestrictedOudsApi val extraSmall: Dp,
        @RestrictedOudsApi val small: Dp,
        @RestrictedOudsApi val medium: Dp,
        @RestrictedOudsApi val large: Dp,
        @RestrictedOudsApi val extraLarge: Dp,
        @RestrictedOudsApi val twoExtraLarge: Dp,
        @RestrictedOudsApi val threeExtraLarge: Dp,
        @RestrictedOudsApi val fourExtraLarge: Dp,
        @RestrictedOudsApi val fiveExtraLarge: Dp,
    )

    /**
     * Spacing values used for inset positioning.
     *
     * @property none 0dp inset.
     * @property fiveExtraSmall 5xs inset.
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
    class Inset internal constructor(
        @RestrictedOudsApi val none: Dp,
        @RestrictedOudsApi val fiveExtraSmall: Dp,
        @RestrictedOudsApi val fourExtraSmall: Dp,
        @RestrictedOudsApi val threeExtraSmall: Dp,
        @RestrictedOudsApi val twoExtraSmall: Dp,
        @RestrictedOudsApi val extraSmall: Dp,
        @RestrictedOudsApi val small: Dp,
        @RestrictedOudsApi val medium: Dp,
        @RestrictedOudsApi val large: Dp,
        @RestrictedOudsApi val extraLarge: Dp,
        @RestrictedOudsApi val twoExtraLarge: Dp,
        @RestrictedOudsApi val threeExtraLarge: Dp,
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
     * @property threeExtraLarge 3xl gap.
     * @property fourExtraLarge 4xl gap.
     */
    class ColumnGap internal constructor(
        @RestrictedOudsApi val none: Dp,
        @RestrictedOudsApi val threeExtraSmall: Dp,
        @RestrictedOudsApi val twoExtraSmall: Dp,
        @RestrictedOudsApi val extraSmall: Dp,
        @RestrictedOudsApi val small: Dp,
        @RestrictedOudsApi val medium: Dp,
        @RestrictedOudsApi val large: Dp,
        @RestrictedOudsApi val extraLarge: Dp,
        @RestrictedOudsApi val twoExtraLarge: Dp,
        @RestrictedOudsApi val threeExtraLarge: Dp,
        @RestrictedOudsApi val fourExtraLarge: Dp
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
    class RowGap internal constructor(
        @RestrictedOudsApi val none: Dp,
        @RestrictedOudsApi val threeExtraSmall: Dp,
        @RestrictedOudsApi val twoExtraSmall: Dp,
        @RestrictedOudsApi val extraSmall: Dp,
        @RestrictedOudsApi val small: Dp,
        @RestrictedOudsApi val medium: Dp,
        @RestrictedOudsApi val large: Dp,
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
            none = @OptIn(RestrictedOudsApi::class) paddingInlineNone.dp,
            fourExtraSmall = @OptIn(RestrictedOudsApi::class) paddingInline4xsmall.dp,
            threeExtraSmall = @OptIn(RestrictedOudsApi::class) paddingInline3xsmall.dp,
            twoExtraSmall = @OptIn(RestrictedOudsApi::class) paddingInline2xsmall.dp,
            extraSmall = @OptIn(RestrictedOudsApi::class) paddingInlineXsmall.dp,
            small = @OptIn(RestrictedOudsApi::class) paddingInlineSmall.dp,
            medium = @OptIn(RestrictedOudsApi::class) paddingInlineMedium.dp,
            large = @OptIn(RestrictedOudsApi::class) paddingInlineLarge.dp,
            extraLarge = @OptIn(RestrictedOudsApi::class) paddingInlineXlarge.dp,
            twoExtraLarge = @OptIn(RestrictedOudsApi::class) paddingInline2xlarge.dp,
            threeExtraLarge = @OptIn(RestrictedOudsApi::class) paddingInline3xlarge.dp,
            fourExtraLarge = @OptIn(RestrictedOudsApi::class) paddingInline4xlarge.dp,
            fiveExtraLarge = @OptIn(RestrictedOudsApi::class) paddingInline5xlarge.dp,
            sixExtraLarge = @OptIn(RestrictedOudsApi::class) paddingInline6xlarge.dp,
        ),
        paddingBlock = OudsSpaces.PaddingBlock(
            none = @OptIn(RestrictedOudsApi::class) paddingBlockNone.dp,
            fourExtraSmall = @OptIn(RestrictedOudsApi::class) paddingBlock4xsmall.dp,
            threeExtraSmall = @OptIn(RestrictedOudsApi::class) paddingBlock3xsmall.dp,
            twoExtraSmall = @OptIn(RestrictedOudsApi::class) paddingBlock2xsmall.dp,
            extraSmall = @OptIn(RestrictedOudsApi::class) paddingBlockXsmall.dp,
            small = @OptIn(RestrictedOudsApi::class) paddingBlockSmall.dp,
            medium = @OptIn(RestrictedOudsApi::class) paddingBlockMedium.dp,
            large = @OptIn(RestrictedOudsApi::class) paddingBlockLarge.dp,
            extraLarge = @OptIn(RestrictedOudsApi::class) paddingBlockXlarge.dp,
            twoExtraLarge = @OptIn(RestrictedOudsApi::class) paddingBlock2xlarge.dp,
            threeExtraLarge = @OptIn(RestrictedOudsApi::class) paddingBlock3xlarge.dp,
            fourExtraLarge = @OptIn(RestrictedOudsApi::class) paddingBlock4xlarge.dp,
            fiveExtraLarge = @OptIn(RestrictedOudsApi::class) paddingBlock5xlarge.dp,
        ),
        inset = OudsSpaces.Inset(
            none = @OptIn(RestrictedOudsApi::class) insetNone.dp,
            fiveExtraSmall = @OptIn(RestrictedOudsApi::class) inset5xsmall.dp,
            fourExtraSmall = @OptIn(RestrictedOudsApi::class) inset4xsmall.dp,
            threeExtraSmall = @OptIn(RestrictedOudsApi::class) inset3xsmall.dp,
            twoExtraSmall = @OptIn(RestrictedOudsApi::class) inset2xsmall.dp,
            extraSmall = @OptIn(RestrictedOudsApi::class) insetXsmall.dp,
            small = @OptIn(RestrictedOudsApi::class) insetSmall.dp,
            medium = @OptIn(RestrictedOudsApi::class) insetMedium.dp,
            large = @OptIn(RestrictedOudsApi::class) insetLarge.dp,
            extraLarge = @OptIn(RestrictedOudsApi::class) insetXlarge.dp,
            twoExtraLarge = @OptIn(RestrictedOudsApi::class) inset2xlarge.dp,
            threeExtraLarge = @OptIn(RestrictedOudsApi::class) inset3xlarge.dp,
        ),
        columnGap = OudsSpaces.ColumnGap(
            none = @OptIn(RestrictedOudsApi::class) columnGapNone.dp,
            threeExtraSmall = @OptIn(RestrictedOudsApi::class) columnGap3xsmall.dp,
            twoExtraSmall = @OptIn(RestrictedOudsApi::class) columnGap2xsmall.dp,
            extraSmall = @OptIn(RestrictedOudsApi::class) columnGapXsmall.dp,
            small = @OptIn(RestrictedOudsApi::class) columnGapSmall.dp,
            medium = @OptIn(RestrictedOudsApi::class) columnGapMedium.dp,
            large = @OptIn(RestrictedOudsApi::class) columnGapLarge.dp,
            extraLarge = @OptIn(RestrictedOudsApi::class) columnGapXlarge.dp,
            twoExtraLarge = @OptIn(RestrictedOudsApi::class) columnGap2xlarge.dp,
            threeExtraLarge = @OptIn(RestrictedOudsApi::class) columnGap3xlarge.dp,
            fourExtraLarge = @OptIn(RestrictedOudsApi::class) columnGap4xlarge.dp
        ),
        rowGap = OudsSpaces.RowGap(
            none = @OptIn(RestrictedOudsApi::class) rowGapNone.dp,
            threeExtraSmall = @OptIn(RestrictedOudsApi::class) rowGap3xsmall.dp,
            twoExtraSmall = @OptIn(RestrictedOudsApi::class) rowGap2xsmall.dp,
            extraSmall = @OptIn(RestrictedOudsApi::class) rowGapXsmall.dp,
            small = @OptIn(RestrictedOudsApi::class) rowGapSmall.dp,
            medium = @OptIn(RestrictedOudsApi::class) rowGapMedium.dp,
            large = @OptIn(RestrictedOudsApi::class) rowGapLarge.dp,
        )
    )
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Fixed): Dp {
    return with(fixed) {
        when (token) {
            OudsSpaceKeyToken.Fixed.None -> none
            OudsSpaceKeyToken.Fixed.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.Fixed.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.Fixed.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.Fixed.Small -> small
            OudsSpaceKeyToken.Fixed.Medium -> medium
            OudsSpaceKeyToken.Fixed.Large -> large
            OudsSpaceKeyToken.Fixed.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.Fixed.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.Fixed.ThreeExtraLarge -> threeExtraLarge
            OudsSpaceKeyToken.Fixed.FourExtraLarge -> fourExtraLarge
            OudsSpaceKeyToken.Fixed.FiveExtraLarge -> fiveExtraLarge
        }
    }
}

@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Scaled): Dp {
    return with(scaled) {
        when (token) {
            OudsSpaceKeyToken.Scaled.None -> none
            OudsSpaceKeyToken.Scaled.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.Scaled.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.Scaled.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.Scaled.Small -> small
            OudsSpaceKeyToken.Scaled.Medium -> medium
            OudsSpaceKeyToken.Scaled.Large -> large
            OudsSpaceKeyToken.Scaled.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.Scaled.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.Scaled.ThreeExtraLarge -> threeExtraLarge
        }
    }
}

@OptIn(RestrictedOudsApi::class)
@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingInline): Dp {
    return with(paddingInline) {
        when (token) {
            OudsSpaceKeyToken.PaddingInline.None -> none
            OudsSpaceKeyToken.PaddingInline.FourExtraSmall -> fourExtraSmall
            OudsSpaceKeyToken.PaddingInline.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.PaddingInline.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.PaddingInline.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.PaddingInline.Small -> small
            OudsSpaceKeyToken.PaddingInline.Medium -> medium
            OudsSpaceKeyToken.PaddingInline.Large -> large
            OudsSpaceKeyToken.PaddingInline.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.PaddingInline.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.PaddingInline.ThreeExtraLarge -> threeExtraLarge
            OudsSpaceKeyToken.PaddingInline.FourExtraLarge -> fourExtraLarge
            OudsSpaceKeyToken.PaddingInline.FiveExtraLarge -> fiveExtraLarge
            OudsSpaceKeyToken.PaddingInline.SixExtraLarge -> sixExtraLarge
        }
    }
}

@OptIn(RestrictedOudsApi::class)
@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.PaddingBlock): Dp {
    return with(paddingBlock) {
        when (token) {
            OudsSpaceKeyToken.PaddingBlock.None -> none
            OudsSpaceKeyToken.PaddingBlock.FourExtraSmall -> fourExtraSmall
            OudsSpaceKeyToken.PaddingBlock.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.PaddingBlock.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.PaddingBlock.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.PaddingBlock.Small -> small
            OudsSpaceKeyToken.PaddingBlock.Medium -> medium
            OudsSpaceKeyToken.PaddingBlock.Large -> large
            OudsSpaceKeyToken.PaddingBlock.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.PaddingBlock.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.PaddingBlock.ThreeExtraLarge -> threeExtraLarge
            OudsSpaceKeyToken.PaddingBlock.FourExtraLarge -> fourExtraLarge
            OudsSpaceKeyToken.PaddingBlock.FiveExtraLarge -> fiveExtraLarge
        }
    }
}

@OptIn(RestrictedOudsApi::class)
@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.Inset): Dp {
    return with(inset) {
        when (token) {
            OudsSpaceKeyToken.Inset.None -> none
            OudsSpaceKeyToken.Inset.FiveExtraSmall -> fiveExtraSmall
            OudsSpaceKeyToken.Inset.FourExtraSmall -> fourExtraSmall
            OudsSpaceKeyToken.Inset.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.Inset.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.Inset.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.Inset.Small -> small
            OudsSpaceKeyToken.Inset.Medium -> medium
            OudsSpaceKeyToken.Inset.Large -> large
            OudsSpaceKeyToken.Inset.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.Inset.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.Inset.ThreeExtraLarge -> threeExtraLarge
        }
    }
}

@OptIn(RestrictedOudsApi::class)
@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.ColumnGap): Dp {
    return with(columnGap) {
        when (token) {
            OudsSpaceKeyToken.ColumnGap.None -> none
            OudsSpaceKeyToken.ColumnGap.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.ColumnGap.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.ColumnGap.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.ColumnGap.Small -> small
            OudsSpaceKeyToken.ColumnGap.Medium -> medium
            OudsSpaceKeyToken.ColumnGap.Large -> large
            OudsSpaceKeyToken.ColumnGap.ExtraLarge -> extraLarge
            OudsSpaceKeyToken.ColumnGap.TwoExtraLarge -> twoExtraLarge
            OudsSpaceKeyToken.ColumnGap.ThreeExtraLarge -> threeExtraLarge
            OudsSpaceKeyToken.ColumnGap.FourExtraLarge -> fourExtraLarge
        }
    }
}

@OptIn(RestrictedOudsApi::class)
@Stable
private fun OudsSpaces.fromToken(token: OudsSpaceKeyToken.RowGap): Dp {
    return with(rowGap) {
        when (token) {
            OudsSpaceKeyToken.RowGap.None -> none
            OudsSpaceKeyToken.RowGap.ThreeExtraSmall -> threeExtraSmall
            OudsSpaceKeyToken.RowGap.TwoExtraSmall -> twoExtraSmall
            OudsSpaceKeyToken.RowGap.ExtraSmall -> extraSmall
            OudsSpaceKeyToken.RowGap.Small -> small
            OudsSpaceKeyToken.RowGap.Medium -> medium
            OudsSpaceKeyToken.RowGap.Large -> large
        }
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
