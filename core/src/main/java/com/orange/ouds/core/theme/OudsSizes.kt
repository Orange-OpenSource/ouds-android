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
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )

            data class Medium(
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )

            data class Large(
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )

            data class ExtraLarge(
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
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
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )

            data class Medium(
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )

            data class Large(
                val sizeSmall: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeLarge: OudsAdaptiveTokenValue<Dp>,
            )
        }
    }

    data class MaxWidthType(
        val display: Display,
        val heading: Heading,
        val body: Body
    ) {
        data class Display(
            val small: OudsAdaptiveTokenValue<Dp>,
            val medium: OudsAdaptiveTokenValue<Dp>,
            val large: OudsAdaptiveTokenValue<Dp>,
        )

        data class Heading(
            val small: OudsAdaptiveTokenValue<Dp>,
            val medium: OudsAdaptiveTokenValue<Dp>,
            val large: OudsAdaptiveTokenValue<Dp>,
            val extraLarge: OudsAdaptiveTokenValue<Dp>,
        )

        data class Body(
            val small: OudsAdaptiveTokenValue<Dp>,
            val medium: OudsAdaptiveTokenValue<Dp>,
            val large: OudsAdaptiveTokenValue<Dp>,
        )
    }
}

/**
 * @suppress
 */
fun OudsSizeSemanticTokens.getSizes() = OudsSizes(
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
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeSmMobile.dp,
                    iconWithHeadingSmallSizeSmMobile.dp,
                    iconWithHeadingSmallSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeMdMobile.dp,
                    iconWithHeadingSmallSizeMdMobile.dp,
                    iconWithHeadingSmallSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeLgMobile.dp,
                    iconWithHeadingSmallSizeLgMobile.dp,
                    iconWithHeadingSmallSizeLgTablet.dp
                ),
            ),
            medium = OudsSizes.Icon.WithHeading.Medium(
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeSmMobile.dp,
                    iconWithHeadingMediumSizeSmMobile.dp,
                    iconWithHeadingMediumSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeMdMobile.dp,
                    iconWithHeadingMediumSizeMdMobile.dp,
                    iconWithHeadingMediumSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeLgMobile.dp,
                    iconWithHeadingMediumSizeLgMobile.dp,
                    iconWithHeadingMediumSizeLgTablet.dp
                ),
            ),
            large = OudsSizes.Icon.WithHeading.Large(
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeSmMobile.dp,
                    iconWithHeadingLargeSizeSmMobile.dp,
                    iconWithHeadingLargeSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeMdMobile.dp,
                    iconWithHeadingLargeSizeMdMobile.dp,
                    iconWithHeadingLargeSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeLgMobile.dp,
                    iconWithHeadingLargeSizeLgMobile.dp,
                    iconWithHeadingLargeSizeLgTablet.dp
                ),
            ),
            extraLarge = OudsSizes.Icon.WithHeading.ExtraLarge(
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeSmMobile.dp,
                    iconWithHeadingXlargeSizeSmMobile.dp,
                    iconWithHeadingXlargeSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeMdMobile.dp,
                    iconWithHeadingXlargeSizeMdMobile.dp,
                    iconWithHeadingXlargeSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeLgMobile.dp,
                    iconWithHeadingXlargeSizeLgMobile.dp,
                    iconWithHeadingXlargeSizeLgTablet.dp
                ),
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
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeSmMobile.dp,
                    iconWithBodySmallSizeSmMobile.dp,
                    iconWithBodySmallSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeMdMobile.dp,
                    iconWithBodySmallSizeMdMobile.dp,
                    iconWithBodySmallSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeLgMobile.dp,
                    iconWithBodySmallSizeLgMobile.dp,
                    iconWithBodySmallSizeLgTablet.dp
                ),
            ),
            medium = OudsSizes.Icon.WithBody.Medium(
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeSmMobile.dp,
                    iconWithBodyMediumSizeSmMobile.dp,
                    iconWithBodyMediumSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeMdMobile.dp,
                    iconWithBodyMediumSizeMdMobile.dp,
                    iconWithBodyMediumSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeLgMobile.dp,
                    iconWithBodyMediumSizeLgMobile.dp,
                    iconWithBodyMediumSizeLgTablet.dp
                ),
            ),
            large = OudsSizes.Icon.WithBody.Large(
                sizeSmall = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeSmMobile.dp,
                    iconWithBodyLargeSizeSmMobile.dp,
                    iconWithBodyLargeSizeSmTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeMdMobile.dp,
                    iconWithBodyLargeSizeMdMobile.dp,
                    iconWithBodyLargeSizeMdTablet.dp
                ),
                sizeLarge = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeLgMobile.dp,
                    iconWithBodyLargeSizeLgMobile.dp,
                    iconWithBodyLargeSizeLgTablet.dp
                ),
            ),
        ),
    ),
    maxWidthType = OudsSizes.MaxWidthType(
        display = OudsSizes.MaxWidthType.Display(
            small = OudsAdaptiveTokenValue(
                maxWidthTypeDisplaySmallMobile.dp,
                maxWidthTypeDisplaySmallMobile.dp,
                maxWidthTypeDisplaySmallTablet.dp
            ),
            medium = OudsAdaptiveTokenValue(
                maxWidthTypeDisplayMediumMobile.dp,
                maxWidthTypeDisplayMediumMobile.dp,
                maxWidthTypeDisplayMediumTablet.dp
            ),
            large = OudsAdaptiveTokenValue(
                maxWidthTypeDisplayLargeMobile.dp,
                maxWidthTypeDisplayLargeMobile.dp,
                maxWidthTypeDisplayLargeTablet.dp
            ),
        ),
        heading = OudsSizes.MaxWidthType.Heading(
            small = OudsAdaptiveTokenValue(
                maxWidthTypeHeadingSmallMobile.dp,
                maxWidthTypeHeadingSmallMobile.dp,
                maxWidthTypeHeadingSmallTablet.dp
            ),
            medium = OudsAdaptiveTokenValue(
                maxWidthTypeHeadingMediumMobile.dp,
                maxWidthTypeHeadingMediumMobile.dp,
                maxWidthTypeHeadingMediumTablet.dp
            ),
            large = OudsAdaptiveTokenValue(
                maxWidthTypeHeadingLargeMobile.dp,
                maxWidthTypeHeadingLargeMobile.dp,
                maxWidthTypeHeadingLargeTablet.dp
            ),
            extraLarge = OudsAdaptiveTokenValue(
                maxWidthTypeHeadingXlargeMobile.dp,
                maxWidthTypeHeadingXlargeMobile.dp,
                maxWidthTypeHeadingXlargeTablet.dp
            ),
        ),
        body = OudsSizes.MaxWidthType.Body(
            small = OudsAdaptiveTokenValue(
                maxWidthTypeBodySmallMobile.dp,
                maxWidthTypeBodySmallMobile.dp,
                maxWidthTypeBodySmallTablet.dp
            ),
            medium = OudsAdaptiveTokenValue(
                maxWidthTypeBodyMediumMobile.dp,
                maxWidthTypeBodyMediumMobile.dp,
                maxWidthTypeBodyMediumTablet.dp
            ),
            large = OudsAdaptiveTokenValue(
                maxWidthTypeBodyLargeMobile.dp,
                maxWidthTypeBodyLargeMobile.dp,
                maxWidthTypeBodyLargeTablet.dp
            ),
        )
    )
)

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
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading.ExtraLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeSmall -> icon.withHeading.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeMedium -> icon.withHeading.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.ExtraLarge.SizeLarge -> icon.withHeading.extraLarge.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading.Large, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeSmall -> icon.withHeading.large.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeMedium -> icon.withHeading.large.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Large.SizeLarge -> icon.withHeading.large.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading.Medium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeSmall -> icon.withHeading.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeMedium -> icon.withHeading.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Medium.SizeLarge -> icon.withHeading.medium.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeading.Small, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeSmall -> icon.withHeading.small.sizeSmall
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeMedium -> icon.withHeading.small.sizeMedium
        OudsSizeKeyToken.Icon.WithHeading.Small.SizeLarge -> icon.withHeading.small.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBody.Large, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBody.Large.SizeSmall -> icon.withBody.large.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Large.SizeMedium -> icon.withBody.large.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Large.SizeLarge -> icon.withBody.large.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBody.Medium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeSmall -> icon.withBody.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeMedium -> icon.withBody.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Medium.SizeLarge -> icon.withBody.medium.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBody.Small, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBody.Small.SizeSmall -> icon.withBody.small.sizeSmall
        OudsSizeKeyToken.Icon.WithBody.Small.SizeMedium -> icon.withBody.small.sizeMedium
        OudsSizeKeyToken.Icon.WithBody.Small.SizeLarge -> icon.withBody.small.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel.ExtraLarge): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeSmall -> icon.withLabel.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeMedium -> icon.withLabel.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.ExtraLarge.SizeLarge -> icon.withLabel.extraLarge.sizeLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel.Large): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraSmall -> icon.withLabel.large.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeSmall -> icon.withLabel.large.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeMedium -> icon.withLabel.large.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeLarge -> icon.withLabel.large.sizeLarge
        OudsSizeKeyToken.Icon.WithLabel.Large.SizeExtraLarge -> icon.withLabel.large.sizeExtraLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel.Medium): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeExtraSmall -> icon.withLabel.medium.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeSmall -> icon.withLabel.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeMedium -> icon.withLabel.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Medium.SizeLarge -> icon.withLabel.medium.sizeLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabel.Small): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeExtraSmall -> icon.withLabel.small.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeSmall -> icon.withLabel.small.sizeSmall
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeMedium -> icon.withLabel.small.sizeMedium
        OudsSizeKeyToken.Icon.WithLabel.Small.SizeLarge -> icon.withLabel.small.sizeLarge
    }
}

@Stable
private fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidth, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
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
    }.getValue(adaptiveWindowType)
}

val OudsSizeKeyToken.value: Dp
    @Composable
    get() = when (this) {
        is OudsSizeKeyToken.Icon.Decorative -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithHeading.ExtraLarge -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeading.Large -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeading.Medium -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeading.Small -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBody.Large -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBody.Medium -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBody.Small -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithLabel.ExtraLarge -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabel.Large -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabel.Medium -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabel.Small -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.MaxWidth -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
    }
