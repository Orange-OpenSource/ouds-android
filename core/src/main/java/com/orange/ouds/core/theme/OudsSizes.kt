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
                sizeSmall = iconWithLabelSmallSizeSm.dp,
                sizeMedium = iconWithLabelSmallSizeMd.dp,
                sizeLarge = iconWithLabelSmallSizeLg.dp,
            ),
            medium = OudsSizes.Icon.WithLabel.Medium(
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
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.Decorative): Dp {
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
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingExtraLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeSmall -> icon.withHeading.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeMedium -> icon.withHeading.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeLarge -> icon.withHeading.extraLarge.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeSmall -> icon.withHeading.large.sizeSmall
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeMedium -> icon.withHeading.large.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeLarge -> icon.withHeading.large.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingMedium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeSmall -> icon.withHeading.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeMedium -> icon.withHeading.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeLarge -> icon.withHeading.medium.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingSmall, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeSmall -> icon.withHeading.small.sizeSmall
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeMedium -> icon.withHeading.small.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeLarge -> icon.withHeading.small.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodyLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeSmall -> icon.withBody.large.sizeSmall
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeMedium -> icon.withBody.large.sizeMedium
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeLarge -> icon.withBody.large.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodyMedium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeSmall -> icon.withBody.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeMedium -> icon.withBody.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeLarge -> icon.withBody.medium.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodySmall, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodySmall.SizeSmall -> icon.withBody.small.sizeSmall
        OudsSizeKeyToken.Icon.WithBodySmall.SizeMedium -> icon.withBody.small.sizeMedium
        OudsSizeKeyToken.Icon.WithBodySmall.SizeLarge -> icon.withBody.small.sizeLarge
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelExtraLarge): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeSmall -> icon.withLabel.extraLarge.sizeSmall
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeMedium -> icon.withLabel.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeLarge -> icon.withLabel.extraLarge.sizeLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelLarge): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeExtraSmall -> icon.withLabel.large.sizeExtraSmall
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeSmall -> icon.withLabel.large.sizeSmall
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeMedium -> icon.withLabel.large.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeLarge -> icon.withLabel.large.sizeLarge
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeExtraLarge -> icon.withLabel.large.sizeExtraLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelMedium): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeSmall -> icon.withLabel.medium.sizeSmall
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeMedium -> icon.withLabel.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeLarge -> icon.withLabel.medium.sizeLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelSmall): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeSmall -> icon.withLabel.small.sizeSmall
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeMedium -> icon.withLabel.small.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeLarge -> icon.withLabel.small.sizeLarge
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidth, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.MaxWidth.Type.DisplaySmall -> maxWidthType.display.small
        OudsSizeKeyToken.MaxWidth.Type.DisplayMedium -> maxWidthType.display.medium
        OudsSizeKeyToken.MaxWidth.Type.DisplayLarge -> maxWidthType.display.large
        OudsSizeKeyToken.MaxWidth.Type.HeadingSmall -> maxWidthType.heading.small
        OudsSizeKeyToken.MaxWidth.Type.HeadingMedium -> maxWidthType.heading.medium
        OudsSizeKeyToken.MaxWidth.Type.HeadingLarge -> maxWidthType.heading.large
        OudsSizeKeyToken.MaxWidth.Type.HeadingExtraLarge -> maxWidthType.heading.extraLarge
        OudsSizeKeyToken.MaxWidth.Type.BodySmall -> maxWidthType.body.small
        OudsSizeKeyToken.MaxWidth.Type.BodyMedium -> maxWidthType.body.medium
        OudsSizeKeyToken.MaxWidth.Type.BodyLarge -> maxWidthType.body.large
    }.getValue(adaptiveWindowType)
}

val OudsSizeKeyToken.value: Dp
    @Composable
    get() = when (this) {
        is OudsSizeKeyToken.Icon.Decorative -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithHeadingExtraLarge -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeadingLarge -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeadingMedium -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithHeadingSmall -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBodyLarge -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBodyMedium -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithBodySmall -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
        is OudsSizeKeyToken.Icon.WithLabelExtraLarge -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabelLarge -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabelMedium -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.Icon.WithLabelSmall -> OudsTheme.sizes.fromToken(this)
        is OudsSizeKeyToken.MaxWidth -> OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
    }
