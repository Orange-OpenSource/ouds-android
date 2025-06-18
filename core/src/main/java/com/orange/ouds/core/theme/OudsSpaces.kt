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

    data class Scaled(
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

    data class PaddingInline(
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

    data class PaddingBlock(
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
    )

    data class Inset(
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

    data class ColumnGap(
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

    data class RowGap(
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
            threeExtraSmall = fixed3xs.dp,
            twoExtraSmall = fixed2xs.dp,
            extraSmall = fixedXs.dp,
            small = fixedSm.dp,
            medium = fixedMd.dp,
            large = fixedLg.dp,
            extraLarge = fixedXl.dp,
            twoExtraLarge = fixed2xl.dp,
            threeExtraLarge = fixed3xl.dp,
            fourExtraLarge = fixed4xl.dp,
            fiveExtraLarge = fixed5xl.dp,
        ),
        scaled = OudsSpaces.Scaled(
            none = getTokenValue(scaledNoneMobile, scaledNoneTablet).dp,
            threeExtraSmall = getTokenValue(scaled3xsMobile, scaled3xsTablet).dp,
            twoExtraSmall = getTokenValue(scaled2xsMobile, scaled2xsTablet).dp,
            extraSmall = getTokenValue(scaledXsMobile, scaledXsTablet).dp,
            small = getTokenValue(scaledSmMobile, scaledSmTablet).dp,
            medium = getTokenValue(scaledMdMobile, scaledMdTablet).dp,
            large = getTokenValue(scaledLgMobile, scaledLgTablet).dp,
            extraLarge = getTokenValue(scaledXlMobile, scaledXlTablet).dp,
            twoExtraLarge = getTokenValue(scaled2xlMobile, scaled2xlTablet).dp,
            threeExtraLarge = getTokenValue(scaled3xlMobile, scaled3xlTablet).dp,
        ),
        paddingInline = OudsSpaces.PaddingInline(
            none = paddingInlineNone.dp,
            fourExtraSmall = paddingInline4xs.dp,
            threeExtraSmall = paddingInline3xs.dp,
            twoExtraSmall = paddingInline2xs.dp,
            extraSmall = paddingInlineXs.dp,
            small = paddingInlineSm.dp,
            medium = paddingInlineMd.dp,
            large = paddingInlineLg.dp,
            extraLarge = paddingInlineXl.dp,
            twoExtraLarge = paddingInline2xl.dp,
            threeExtraLarge = paddingInline3xl.dp,
            fourExtraLarge = paddingInline4xl.dp,
        ),
        paddingBlock = OudsSpaces.PaddingBlock(
            none = paddingBlockNone.dp,
            threeExtraSmall = paddingBlock3xs.dp,
            twoExtraSmall = paddingBlock2xs.dp,
            extraSmall = paddingBlockXs.dp,
            small = paddingBlockSm.dp,
            medium = paddingBlockMd.dp,
            large = paddingBlockLg.dp,
            extraLarge = paddingBlockXl.dp,
            twoExtraLarge = paddingBlock2xl.dp,
            threeExtraLarge = paddingBlock3xl.dp,
            fourExtraLarge = paddingBlock4xl.dp,
        ),
        inset = OudsSpaces.Inset(
            none = insetNone.dp,
            fourExtraSmall = inset4xs.dp,
            threeExtraSmall = inset3xs.dp,
            twoExtraSmall = inset2xs.dp,
            extraSmall = insetXs.dp,
            small = insetSm.dp,
            medium = insetMd.dp,
            large = insetLg.dp,
            extraLarge = insetXl.dp,
            twoExtraLarge = inset2xl.dp,
            threeExtraLarge = inset3xl.dp,
        ),
        columnGap = OudsSpaces.ColumnGap(
            none = columnGapNone.dp,
            threeExtraSmall = columnGap3xs.dp,
            twoExtraSmall = columnGap2xs.dp,
            extraSmall = columnGapXs.dp,
            small = columnGapSm.dp,
            medium = columnGapMd.dp,
            large = columnGapLg.dp,
            extraLarge = columnGapXl.dp,
            twoExtraLarge = columnGap2xl.dp,
        ),
        rowGap = OudsSpaces.RowGap(
            none = rowGapNone.dp,
            threeExtraSmall = rowGap3xs.dp,
            twoExtraSmall = rowGap2xs.dp,
            extraSmall = rowGapXs.dp,
            small = rowGapSm.dp,
            medium = rowGapMd.dp,
            large = rowGapLg.dp,
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
