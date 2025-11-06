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
    val maxWidth: MaxWidth,
    val minInteractiveArea: Dp
) {
    data class Icon(
        val decorative: Decorative,
        val withHeading: WithHeading,
        val withLabel: WithLabel,
        val withBody: WithBody
    ) {
        data class Decorative(
            val fourExtraSmall: Dp,
            val threeExtraSmall: Dp,
            val twoExtraSmall: Dp,
            val extraSmall: Dp,
            val small: Dp,
            val medium: Dp,
            val large: Dp,
            val extraLarge: Dp,
            val twoExtraLarge: Dp,
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

    data class MaxWidth(
        val type: Type
    ) {
        data class Type(
            val display: Display,
            val heading: Heading,
            val body: Body
        ) {
            data class Display(
                val small: Dp,
                val medium: Dp,
                val large: Dp
            )

            data class Heading(
                val small: Dp,
                val medium: Dp,
                val large: Dp,
                val extraLarge: Dp
            )

            data class Body(
                val small: Dp,
                val medium: Dp,
                val large: Dp
            )
        }
    }
}

internal fun OudsSizeSemanticTokens.getSizes(windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsSizes(
        icon = OudsSizes.Icon(
            decorative = OudsSizes.Icon.Decorative(
                fourExtraSmall = iconDecorative4xsmall.dp,
                threeExtraSmall = iconDecorative3xsmall.dp,
                twoExtraSmall = iconDecorative2xsmall.dp,
                extraSmall = iconDecorativeXsmall.dp,
                small = iconDecorativeSmall.dp,
                medium = iconDecorativeMedium.dp,
                large = iconDecorativeLarge.dp,
                extraLarge = iconDecorativeXlarge.dp,
                twoExtraLarge = iconDecorative2xlarge.dp,
            ),
            withHeading = OudsSizes.Icon.WithHeading(
                small = OudsSizes.Icon.WithHeading.Small(
                    sizeSmall = getTokenValue(iconWithHeadingSmallSizeSmallMobile, iconWithHeadingSmallSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingSmallSizeMediumMobile, iconWithHeadingSmallSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingSmallSizeLargeMobile, iconWithHeadingSmallSizeLargeTablet).dp,
                ),
                medium = OudsSizes.Icon.WithHeading.Medium(
                    sizeSmall = getTokenValue(iconWithHeadingMediumSizeSmallMobile, iconWithHeadingMediumSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingMediumSizeMediumMobile, iconWithHeadingMediumSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingMediumSizeLargeMobile, iconWithHeadingMediumSizeLargeTablet).dp,
                ),
                large = OudsSizes.Icon.WithHeading.Large(
                    sizeSmall = getTokenValue(iconWithHeadingLargeSizeSmallMobile, iconWithHeadingLargeSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingLargeSizeMediumMobile, iconWithHeadingLargeSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingLargeSizeLargeMobile, iconWithHeadingLargeSizeLargeTablet).dp,
                ),
                extraLarge = OudsSizes.Icon.WithHeading.ExtraLarge(
                    sizeSmall = getTokenValue(iconWithHeadingXlargeSizeSmallMobile, iconWithHeadingXlargeSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithHeadingXlargeSizeMediumMobile, iconWithHeadingXlargeSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithHeadingXlargeSizeLargeMobile, iconWithHeadingXlargeSizeLargeTablet).dp,
                )
            ),
            withLabel = OudsSizes.Icon.WithLabel(
                small = OudsSizes.Icon.WithLabel.Small(
                    sizeExtraSmall = iconWithLabelSmallSizeXsmall.dp,
                    sizeSmall = iconWithLabelSmallSizeSmall.dp,
                    sizeMedium = iconWithLabelSmallSizeMedium.dp,
                    sizeLarge = iconWithLabelSmallSizeLarge.dp,
                ),
                medium = OudsSizes.Icon.WithLabel.Medium(
                    sizeExtraSmall = iconWithLabelMediumSizeXsmall.dp,
                    sizeSmall = iconWithLabelMediumSizeSmall.dp,
                    sizeMedium = iconWithLabelMediumSizeMedium.dp,
                    sizeLarge = iconWithLabelMediumSizeLarge.dp,
                ),
                large = OudsSizes.Icon.WithLabel.Large(
                    sizeExtraSmall = iconWithLabelLargeSizeXsmall.dp,
                    sizeSmall = iconWithLabelLargeSizeSmall.dp,
                    sizeMedium = iconWithLabelLargeSizeMedium.dp,
                    sizeLarge = iconWithLabelLargeSizeLarge.dp,
                    sizeExtraLarge = iconWithLabelLargeSizeXlarge.dp,
                ),
                extraLarge = OudsSizes.Icon.WithLabel.ExtraLarge(
                    sizeSmall = iconWithLabelXlargeSizeSmall.dp,
                    sizeMedium = iconWithLabelXlargeSizeMedium.dp,
                    sizeLarge = iconWithLabelXlargeSizeLarge.dp,
                )
            ),
            withBody = OudsSizes.Icon.WithBody(
                small = OudsSizes.Icon.WithBody.Small(
                    sizeSmall = getTokenValue(iconWithBodySmallSizeSmallMobile, iconWithBodySmallSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodySmallSizeMediumMobile, iconWithBodySmallSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodySmallSizeLargeMobile, iconWithBodySmallSizeLargeTablet).dp,
                ),
                medium = OudsSizes.Icon.WithBody.Medium(
                    sizeSmall = getTokenValue(iconWithBodyMediumSizeSmallMobile, iconWithBodyMediumSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodyMediumSizeMediumMobile, iconWithBodyMediumSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodyMediumSizeLargeMobile, iconWithBodyMediumSizeLargeTablet).dp,
                ),
                large = OudsSizes.Icon.WithBody.Large(
                    sizeSmall = getTokenValue(iconWithBodyLargeSizeSmallMobile, iconWithBodyLargeSizeSmallTablet).dp,
                    sizeMedium = getTokenValue(iconWithBodyLargeSizeMediumMobile, iconWithBodyLargeSizeMediumTablet).dp,
                    sizeLarge = getTokenValue(iconWithBodyLargeSizeLargeMobile, iconWithBodyLargeSizeLargeTablet).dp,
                ),
            ),
        ),
        maxWidth = OudsSizes.MaxWidth(
            type = OudsSizes.MaxWidth.Type(
                display = OudsSizes.MaxWidth.Type.Display(
                    small = getTokenValue(maxWidthTypeDisplaySmallMobile, maxWidthTypeDisplaySmallTablet).dp,
                    medium = getTokenValue(maxWidthTypeDisplayMediumMobile, maxWidthTypeDisplayMediumTablet).dp,
                    large = getTokenValue(maxWidthTypeDisplayLargeMobile, maxWidthTypeDisplayLargeTablet).dp,
                ),
                heading = OudsSizes.MaxWidth.Type.Heading(
                    small = getTokenValue(maxWidthTypeHeadingSmallMobile, maxWidthTypeHeadingSmallTablet).dp,
                    medium = getTokenValue(maxWidthTypeHeadingMediumMobile, maxWidthTypeHeadingMediumTablet).dp,
                    large = getTokenValue(maxWidthTypeHeadingLargeMobile, maxWidthTypeHeadingLargeTablet).dp,
                    extraLarge = getTokenValue(maxWidthTypeHeadingXlargeMobile, maxWidthTypeHeadingXlargeTablet).dp,
                ),
                body = OudsSizes.MaxWidth.Type.Body(
                    small = getTokenValue(maxWidthTypeBodySmallMobile, maxWidthTypeBodySmallTablet).dp,
                    medium = getTokenValue(maxWidthTypeBodyMediumMobile, maxWidthTypeBodyMediumTablet).dp,
                    large = getTokenValue(maxWidthTypeBodyLargeMobile, maxWidthTypeBodyLargeTablet).dp,
                )
            )
        ),
        minInteractiveArea = minInteractiveArea.dp
    )
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.Decorative): Dp {
    return with(icon.decorative) {
        when (token) {
            OudsSizeKeyToken.Icon.Decorative.FourExtraSmall -> fourExtraSmall
            OudsSizeKeyToken.Icon.Decorative.ThreeExtraSmall -> threeExtraSmall
            OudsSizeKeyToken.Icon.Decorative.TwoExtraSmall -> twoExtraSmall
            OudsSizeKeyToken.Icon.Decorative.ExtraSmall -> extraSmall
            OudsSizeKeyToken.Icon.Decorative.Small -> small
            OudsSizeKeyToken.Icon.Decorative.Medium -> medium
            OudsSizeKeyToken.Icon.Decorative.Large -> large
            OudsSizeKeyToken.Icon.Decorative.ExtraLarge -> extraLarge
            OudsSizeKeyToken.Icon.Decorative.TwoExtraLarge -> twoExtraLarge
        }
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading): Dp {
    return with(icon.withHeading) {
        when (token) {
            OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeSmall -> extraLarge.sizeSmall
            OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeMedium -> extraLarge.sizeMedium
            OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeLarge -> extraLarge.sizeLarge
            OudsSizeKeyToken.Icon.WithHeading.Large.SizeSmall -> large.sizeSmall
            OudsSizeKeyToken.Icon.WithHeading.Large.SizeMedium -> large.sizeMedium
            OudsSizeKeyToken.Icon.WithHeading.Large.SizeLarge -> large.sizeLarge
            OudsSizeKeyToken.Icon.WithHeading.Medium.SizeSmall -> medium.sizeSmall
            OudsSizeKeyToken.Icon.WithHeading.Medium.SizeMedium -> medium.sizeMedium
            OudsSizeKeyToken.Icon.WithHeading.Medium.SizeLarge -> medium.sizeLarge
            OudsSizeKeyToken.Icon.WithHeading.Small.SizeSmall -> small.sizeSmall
            OudsSizeKeyToken.Icon.WithHeading.Small.SizeMedium -> small.sizeMedium
            OudsSizeKeyToken.Icon.WithHeading.Small.SizeLarge -> small.sizeLarge
        }
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBody): Dp {
    return with(icon.withBody) {
        when (token) {
            OudsSizeKeyToken.Icon.WithBody.Large.SizeSmall -> large.sizeSmall
            OudsSizeKeyToken.Icon.WithBody.Large.SizeMedium -> large.sizeMedium
            OudsSizeKeyToken.Icon.WithBody.Large.SizeLarge -> large.sizeLarge
            OudsSizeKeyToken.Icon.WithBody.Medium.SizeSmall -> medium.sizeSmall
            OudsSizeKeyToken.Icon.WithBody.Medium.SizeMedium -> medium.sizeMedium
            OudsSizeKeyToken.Icon.WithBody.Medium.SizeLarge -> medium.sizeLarge
            OudsSizeKeyToken.Icon.WithBody.Small.SizeSmall -> small.sizeSmall
            OudsSizeKeyToken.Icon.WithBody.Small.SizeMedium -> small.sizeMedium
            OudsSizeKeyToken.Icon.WithBody.Small.SizeLarge -> small.sizeLarge
        }
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel): Dp {
    return with(icon.withLabel) {
        when (token) {
            OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeSmall -> extraLarge.sizeSmall
            OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeMedium -> extraLarge.sizeMedium
            OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeLarge -> extraLarge.sizeLarge
            OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall -> large.sizeExtraSmall
            OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall -> large.sizeSmall
            OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium -> large.sizeMedium
            OudsSizeKeyToken.Icon.WithLabel.Large.SizeLarge -> large.sizeLarge
            OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraLarge -> large.sizeExtraLarge
            OudsSizeKeyToken.Icon.WithLabel.Medium.SizeExtraSmall -> medium.sizeExtraSmall
            OudsSizeKeyToken.Icon.WithLabel.Medium.SizeSmall -> medium.sizeSmall
            OudsSizeKeyToken.Icon.WithLabel.Medium.SizeMedium -> medium.sizeMedium
            OudsSizeKeyToken.Icon.WithLabel.Medium.SizeLarge -> medium.sizeLarge
            OudsSizeKeyToken.Icon.WithLabel.Small.SizeExtraSmall -> small.sizeExtraSmall
            OudsSizeKeyToken.Icon.WithLabel.Small.SizeSmall -> small.sizeSmall
            OudsSizeKeyToken.Icon.WithLabel.Small.SizeMedium -> small.sizeMedium
            OudsSizeKeyToken.Icon.WithLabel.Small.SizeLarge -> small.sizeLarge
        }
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidth): Dp {
    return with(maxWidth.type) {
        when (token) {
            OudsSizeKeyToken.MaxWidth.Type.Display.Small -> display.small
            OudsSizeKeyToken.MaxWidth.Type.Display.Medium -> display.medium
            OudsSizeKeyToken.MaxWidth.Type.Display.Large -> display.large
            OudsSizeKeyToken.MaxWidth.Type.Heading.Small -> heading.small
            OudsSizeKeyToken.MaxWidth.Type.Heading.Medium -> heading.medium
            OudsSizeKeyToken.MaxWidth.Type.Heading.Large -> heading.large
            OudsSizeKeyToken.MaxWidth.Type.Heading.ExtraLarge -> heading.extraLarge
            OudsSizeKeyToken.MaxWidth.Type.Body.Small -> body.small
            OudsSizeKeyToken.MaxWidth.Type.Body.Medium -> body.medium
            OudsSizeKeyToken.MaxWidth.Type.Body.Large -> body.large
        }
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
        is OudsSizeKeyToken.MinInteractiveArea -> OudsTheme.sizes.minInteractiveArea
    }
