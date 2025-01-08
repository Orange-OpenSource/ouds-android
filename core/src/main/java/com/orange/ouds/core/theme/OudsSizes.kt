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
import com.orange.ouds.theme.tokens.OudsSizeKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens

/**
 * @suppress
 */
data class OudsSizes(
    val icon: Icon,
    val maxWidthType: MaxWidthType
) {
    data class Icon(
        val decorative: Decorative,
        val withHeading: WithHeading,
        val withLabel: WithLabel,
        val withBody: WithBody
    ) {
        data class Decorative(
            val extraExtraSmall: Dp,
            val extraSmall: Dp,
            val small: Dp,
            val medium: Dp,
            val large: Dp,
            val extraLarge: Dp,
            val extraExtraLarge: Dp,
        )

        data class WithHeading(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            data class Small(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Medium(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Large(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class ExtraLarge(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }

        data class WithLabel(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            data class Small(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Medium(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Large(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
                val sizeExtraLarge: Dp,
            )

            data class ExtraLarge(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }

        data class WithBody(
            val small: Small,
            val medium: Medium,
            val large: Large,
        ) {
            data class Small(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Medium(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            data class Large(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }
    }

    data class MaxWidthType(
        val display: Display,
        val heading: Heading,
        val body: Body
    ) {
        data class Display(
            val small: Dp,
            val medium: Dp,
            val large: Dp,
        )

        data class Heading(
            val small: Dp,
            val medium: Dp,
            val large: Dp,
            val extraLarge: Dp,
        )

        data class Body(
            val small: Dp,
            val medium: Dp,
            val large: Dp,
        )
    }
}

internal fun OudsSizeSemanticTokens.getSizes(windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsSizes(
        icon = OudsSizes.Icon(
            decorative = OudsSizes.Icon.Decorative(
                extraExtraSmall = iconDecorative2xs.dp,
                extraSmall = iconDecorativeXs.dp,
                small = iconDecorativeSm.dp,
                medium = iconDecorativeMd.dp,
                large = iconDecorativeLg.dp,
                extraLarge = iconDecorativeXl.dp,
                extraExtraLarge = iconDecorative2xl.dp,
            ),
            withHeading = OudsSizes.Icon.WithHeading(
                small = OudsSizes.Icon.WithHeading.Small(
                    sizeSmall = getTokenValue(iconWithHeadingSmallSizeSmMobile, iconWithHeadingSmallSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingSmallSizeMdMobile, iconWithHeadingSmallSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingSmallSizeLgMobile, iconWithHeadingSmallSizeLgTablet).dp,
                ),
                medium = OudsSizes.Icon.WithHeading.Medium(
                    sizeSmall = getTokenValue(iconWithHeadingMediumSizeSmMobile, iconWithHeadingMediumSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingMediumSizeMdMobile, iconWithHeadingMediumSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingMediumSizeLgMobile, iconWithHeadingMediumSizeLgTablet).dp,
                ),
                large = OudsSizes.Icon.WithHeading.Large(
                    sizeSmall = getTokenValue(iconWithHeadingLargeSizeSmMobile, iconWithHeadingLargeSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingLargeSizeMdMobile, iconWithHeadingLargeSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingLargeSizeLgMobile, iconWithHeadingLargeSizeLgTablet).dp,
                ),
                extraLarge = OudsSizes.Icon.WithHeading.ExtraLarge(
                    sizeSmall = getTokenValue(iconWithHeadingXlargeSizeSmMobile, iconWithHeadingXlargeSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingXlargeSizeMdMobile, iconWithHeadingXlargeSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingXlargeSizeLgMobile, iconWithHeadingXlargeSizeLgTablet).dp,
                )
            ),
            withLabel = OudsSizes.Icon.WithLabel(
                small = OudsSizes.Icon.WithLabel.Small(
                    sizeExtraSmall = iconWithLabelSmallSizeXs.dp,
                    sizeSmall = iconWithLabelSmallSizeSm.dp,
                    sizeMedium = iconWithLabelSmallSizeMd.dp,
                    sizeLarge = iconWithLabelSmallSizeLg.dp,
                ),
                medium = OudsSizes.Icon.WithLabel.Medium(
                    sizeExtraSmall = iconWithLabelMediumSizeXs.dp,
                    sizeSmall = iconWithLabelMediumSizeSm.dp,
                    sizeMedium = iconWithLabelMediumSizeMd.dp,
                    sizeLarge = iconWithLabelMediumSizeLg.dp,
                ),
                large = OudsSizes.Icon.WithLabel.Large(
                    sizeExtraSmall = iconWithLabelLargeSizeXs.dp,
                    sizeSmall = iconWithLabelLargeSizeSm.dp,
                    sizeMedium = iconWithLabelLargeSizeMd.dp,
                    sizeLarge = iconWithLabelLargeSizeLg.dp,
                    sizeExtraLarge = iconWithLabelLargeSizeXl.dp,
                ),
                extraLarge = OudsSizes.Icon.WithLabel.ExtraLarge(
                    sizeSmall = iconWithLabelXlargeSizeSm.dp,
                    sizeMedium = iconWithLabelXlargeSizeMd.dp,
                    sizeLarge = iconWithLabelXlargeSizeLg.dp,
                )
            ),
            withBody = OudsSizes.Icon.WithBody(
                small = OudsSizes.Icon.WithBody.Small(
                    sizeSmall = getTokenValue(iconWithBodySmallSizeSmMobile, iconWithBodySmallSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodySmallSizeMdMobile, iconWithBodySmallSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodySmallSizeLgMobile, iconWithBodySmallSizeLgTablet).dp,
                ),
                medium = OudsSizes.Icon.WithBody.Medium(
                    sizeSmall = getTokenValue(iconWithBodyMediumSizeSmMobile, iconWithBodyMediumSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodyMediumSizeMdMobile, iconWithBodyMediumSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodyMediumSizeLgMobile, iconWithBodyMediumSizeLgTablet).dp,
                ),
                large = OudsSizes.Icon.WithBody.Large(
                    sizeSmall = getTokenValue(iconWithBodyLargeSizeSmMobile, iconWithBodyLargeSizeSmTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodyLargeSizeMdMobile, iconWithBodyLargeSizeMdTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodyLargeSizeLgMobile, iconWithBodyLargeSizeLgTablet).dp,
                ),
            ),
        ),
        maxWidthType = OudsSizes.MaxWidthType(
            display = OudsSizes.MaxWidthType.Display(
                small = getTokenValue(maxWidthTypeDisplaySmallMobile, maxWidthTypeDisplaySmallTablet).dp,
                medium = getTokenValue(maxWidthTypeDisplayMediumMobile, maxWidthTypeDisplayMediumTablet).dp,
                large = getTokenValue(maxWidthTypeDisplayLargeMobile, maxWidthTypeDisplayLargeTablet).dp,
            ),
            heading = OudsSizes.MaxWidthType.Heading(
                small = getTokenValue(maxWidthTypeHeadingSmallMobile, maxWidthTypeHeadingSmallTablet).dp,
                medium = getTokenValue(maxWidthTypeHeadingMediumMobile, maxWidthTypeHeadingMediumTablet).dp,
                large = getTokenValue(maxWidthTypeHeadingLargeMobile, maxWidthTypeHeadingLargeTablet).dp,
                extraLarge = getTokenValue(maxWidthTypeHeadingXlargeMobile, maxWidthTypeHeadingXlargeTablet).dp,
            ),
            body = OudsSizes.MaxWidthType.Body(
                small = getTokenValue(maxWidthTypeBodySmallMobile, maxWidthTypeBodySmallTablet).dp,
                medium = getTokenValue(maxWidthTypeBodyMediumMobile, maxWidthTypeBodyMediumTablet).dp,
                large = getTokenValue(maxWidthTypeBodyLargeMobile, maxWidthTypeBodyLargeTablet).dp,
            )
        )
    )
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.Decorative): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.Decorative.ExtraExtraSmall -> icon.decorative.extraExtraSmall
        OudsSizeKeyToken.Icon.Decorative.ExtraSmall -> icon.decorative.extraSmall
        OudsSizeKeyToken.Icon.Decorative.Small -> icon.decorative.small
        OudsSizeKeyToken.Icon.Decorative.Medium -> icon.decorative.medium
        OudsSizeKeyToken.Icon.Decorative.Large -> icon.decorative.large
        OudsSizeKeyToken.Icon.Decorative.ExtraLarge -> icon.decorative.extraLarge
        OudsSizeKeyToken.Icon.Decorative.ExtraExtraLarge -> icon.decorative.extraExtraLarge
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeSmall -> icon.withHeading.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeMedium -> icon.withHeading.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeLarge -> icon.withHeading.extraLarge.sizeLarge
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeSmall -> icon.withHeading.large.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeMedium -> icon.withHeading.large.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeLarge -> icon.withHeading.large.sizeLarge
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeSmall -> icon.withHeading.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeMedium -> icon.withHeading.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeLarge -> icon.withHeading.medium.sizeLarge
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeSmall -> icon.withHeading.small.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeMedium -> icon.withHeading.small.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeLarge -> icon.withHeading.small.sizeLarge
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBody): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBody.Large.SizeSmall -> icon.withBody.large.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Large.SizeMedium -> icon.withBody.large.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Large.SizeLarge -> icon.withBody.large.sizeLarge
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeSmall -> icon.withBody.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeMedium -> icon.withBody.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeLarge -> icon.withBody.medium.sizeLarge
        OudsSizeKeyToken.Icon.WithBody.Small.SizeSmall -> icon.withBody.small.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Small.SizeMedium -> icon.withBody.small.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Small.SizeLarge -> icon.withBody.small.sizeLarge
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeSmall -> icon.withLabel.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeMedium -> icon.withLabel.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeLarge -> icon.withLabel.extraLarge.sizeLarge
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall -> icon.withLabel.large.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall -> icon.withLabel.large.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium -> icon.withLabel.large.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeLarge -> icon.withLabel.large.sizeLarge
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraLarge -> icon.withLabel.large.sizeExtraLarge
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeExtraSmall -> icon.withLabel.medium.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeSmall -> icon.withLabel.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeMedium -> icon.withLabel.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeLarge -> icon.withLabel.medium.sizeLarge
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeExtraSmall -> icon.withLabel.small.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeSmall -> icon.withLabel.small.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeMedium -> icon.withLabel.small.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeLarge -> icon.withLabel.small.sizeLarge
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidth): Dp {
    return when (token) {
        OudsSizeKeyToken.MaxWidth.Type.Display.Small -> maxWidthType.display.small
        OudsSizeKeyToken.MaxWidth.Type.Display.Medium -> maxWidthType.display.medium
        OudsSizeKeyToken.MaxWidth.Type.Display.Large -> maxWidthType.display.large
        OudsSizeKeyToken.MaxWidth.Type.Heading.Small -> maxWidthType.heading.small
        OudsSizeKeyToken.MaxWidth.Type.Heading.Medium -> maxWidthType.heading.medium
        OudsSizeKeyToken.MaxWidth.Type.Heading.Large -> maxWidthType.heading.large
        OudsSizeKeyToken.MaxWidth.Type.Heading.ExtraLarge -> maxWidthType.heading.extraLarge
        OudsSizeKeyToken.MaxWidth.Type.Body.Small -> maxWidthType.body.small
        OudsSizeKeyToken.MaxWidth.Type.Body.Medium -> maxWidthType.body.medium
        OudsSizeKeyToken.MaxWidth.Type.Body.Large -> maxWidthType.body.large
    }
}

@InternalOudsApi
val OudsSizeKeyToken.value: Dp
    @Composable
    get() = when (this) {
        is OudsSizeKeyToken.Icon.Decorative -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithHeading -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithBody -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabel -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.MaxWidth -> OudsTheme.sizes.fromToken(this)
    }
