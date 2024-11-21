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
            val shortest: Dp,
            val shorter: Dp,
            val short: Dp,
            val medium: Dp,
            val tall: Dp,
            val taller: Dp,
            val tallest: Dp,
        )

        data class WithHeading(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            data class Small(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )

            data class Medium(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )

            data class Large(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )

            data class ExtraLarge(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )
        }

        data class WithLabel(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            data class Small(
                val sizeShort: Dp,
                val sizeMedium: Dp,
                val sizeTall: Dp,
            )

            data class Medium(
                val sizeShort: Dp,
                val sizeMedium: Dp,
                val sizeTall: Dp,
            )

            data class Large(
                val sizeShorter: Dp,
                val sizeShort: Dp,
                val sizeMedium: Dp,
                val sizeTall: Dp,
                val sizeTaller: Dp,
            )

            data class ExtraLarge(
                val sizeShort: Dp,
                val sizeMedium: Dp,
                val sizeTall: Dp,
            )
        }

        data class WithBody(
            val small: Small,
            val medium: Medium,
            val large: Large,
        ) {
            data class Small(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )

            data class Medium(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
            )

            data class Large(
                val sizeShort: OudsAdaptiveTokenValue<Dp>,
                val sizeMedium: OudsAdaptiveTokenValue<Dp>,
                val sizeTall: OudsAdaptiveTokenValue<Dp>,
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
            shortest = iconDecorativeShortest.dp,
            shorter = iconDecorativeShorter.dp,
            short = iconDecorativeShort.dp,
            medium = iconDecorativeMedium.dp,
            tall = iconDecorativeTall.dp,
            taller = iconDecorativeTaller.dp,
            tallest = iconDecorativeTallest.dp,
        ),
        withHeading = OudsSizes.Icon.WithHeading(
            small = OudsSizes.Icon.WithHeading.Small(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeShortMobile.dp,
                    iconWithHeadingSmallSizeShortMobile.dp,
                    iconWithHeadingSmallSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeMediumMobile.dp,
                    iconWithHeadingSmallSizeMediumMobile.dp,
                    iconWithHeadingSmallSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithHeadingSmallSizeTallMobile.dp,
                    iconWithHeadingSmallSizeTallMobile.dp,
                    iconWithHeadingSmallSizeTallTablet.dp
                ),
            ),
            medium = OudsSizes.Icon.WithHeading.Medium(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeShortMobile.dp,
                    iconWithHeadingMediumSizeShortMobile.dp,
                    iconWithHeadingMediumSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeMediumMobile.dp,
                    iconWithHeadingMediumSizeMediumMobile.dp,
                    iconWithHeadingMediumSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithHeadingMediumSizeTallMobile.dp,
                    iconWithHeadingMediumSizeTallMobile.dp,
                    iconWithHeadingMediumSizeTallTablet.dp
                ),
            ),
            large = OudsSizes.Icon.WithHeading.Large(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeShortMobile.dp,
                    iconWithHeadingLargeSizeShortMobile.dp,
                    iconWithHeadingLargeSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeMediumMobile.dp,
                    iconWithHeadingLargeSizeMediumMobile.dp,
                    iconWithHeadingLargeSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithHeadingLargeSizeTallMobile.dp,
                    iconWithHeadingLargeSizeTallMobile.dp,
                    iconWithHeadingLargeSizeTallTablet.dp
                ),
            ),
            extraLarge = OudsSizes.Icon.WithHeading.ExtraLarge(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeShortMobile.dp,
                    iconWithHeadingXlargeSizeShortMobile.dp,
                    iconWithHeadingXlargeSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeMediumMobile.dp,
                    iconWithHeadingXlargeSizeMediumMobile.dp,
                    iconWithHeadingXlargeSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithHeadingXlargeSizeTallMobile.dp,
                    iconWithHeadingXlargeSizeTallMobile.dp,
                    iconWithHeadingXlargeSizeTallTablet.dp
                ),
            )
        ),
        withLabel = OudsSizes.Icon.WithLabel(
            small = OudsSizes.Icon.WithLabel.Small(
                sizeShort = iconWithLabelSmallSizeShort.dp,
                sizeMedium = iconWithLabelSmallSizeMedium.dp,
                sizeTall = iconWithLabelSmallSizeTall.dp,
            ),
            medium = OudsSizes.Icon.WithLabel.Medium(
                sizeShort = iconWithLabelMediumSizeShort.dp,
                sizeMedium = iconWithLabelMediumSizeMedium.dp,
                sizeTall = iconWithLabelMediumSizeTall.dp,
            ),
            large = OudsSizes.Icon.WithLabel.Large(
                sizeShorter = iconWithLabelLargeSizeShorter.dp,
                sizeShort = iconWithLabelLargeSizeShort.dp,
                sizeMedium = iconWithLabelLargeSizeMedium.dp,
                sizeTall = iconWithLabelLargeSizeTall.dp,
                sizeTaller = iconWithLabelLargeSizeTaller.dp,
            ),
            extraLarge = OudsSizes.Icon.WithLabel.ExtraLarge(
                sizeShort = iconWithLabelXlargeSizeShort.dp,
                sizeMedium = iconWithLabelXlargeSizeMedium.dp,
                sizeTall = iconWithLabelXlargeSizeTall.dp,
            )
        ),
        withBody = OudsSizes.Icon.WithBody(
            small = OudsSizes.Icon.WithBody.Small(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeShortMobile.dp,
                    iconWithBodySmallSizeShortMobile.dp,
                    iconWithBodySmallSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeMediumMobile.dp,
                    iconWithBodySmallSizeMediumMobile.dp,
                    iconWithBodySmallSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithBodySmallSizeTallMobile.dp,
                    iconWithBodySmallSizeTallMobile.dp,
                    iconWithBodySmallSizeTallTablet.dp
                ),
            ),
            medium = OudsSizes.Icon.WithBody.Medium(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeShortMobile.dp,
                    iconWithBodyMediumSizeShortMobile.dp,
                    iconWithBodyMediumSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeMediumMobile.dp,
                    iconWithBodyMediumSizeMediumMobile.dp,
                    iconWithBodyMediumSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithBodyMediumSizeTallMobile.dp,
                    iconWithBodyMediumSizeTallMobile.dp,
                    iconWithBodyMediumSizeTallTablet.dp
                ),
            ),
            large = OudsSizes.Icon.WithBody.Large(
                sizeShort = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeShortMobile.dp,
                    iconWithBodyLargeSizeShortMobile.dp,
                    iconWithBodyLargeSizeShortTablet.dp
                ),
                sizeMedium = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeMediumMobile.dp,
                    iconWithBodyLargeSizeMediumMobile.dp,
                    iconWithBodyLargeSizeMediumTablet.dp
                ),
                sizeTall = OudsAdaptiveTokenValue(
                    iconWithBodyLargeSizeTallMobile.dp,
                    iconWithBodyLargeSizeTallMobile.dp,
                    iconWithBodyLargeSizeTallTablet.dp
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
        OudsSizeKeyToken.Icon.Decorative.Shortest -> icon.decorative.shortest
        OudsSizeKeyToken.Icon.Decorative.Shorter -> icon.decorative.shorter
        OudsSizeKeyToken.Icon.Decorative.Short -> icon.decorative.short
        OudsSizeKeyToken.Icon.Decorative.Medium -> icon.decorative.medium
        OudsSizeKeyToken.Icon.Decorative.Tall -> icon.decorative.tall
        OudsSizeKeyToken.Icon.Decorative.Taller -> icon.decorative.taller
        OudsSizeKeyToken.Icon.Decorative.Tallest -> icon.decorative.tallest
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingExtraLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeShort -> icon.withHeading.extraLarge.sizeShort
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeMedium -> icon.withHeading.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingExtraLarge.SizeTall -> icon.withHeading.extraLarge.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeShort -> icon.withHeading.large.sizeShort
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeMedium -> icon.withHeading.large.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingLarge.SizeTall -> icon.withHeading.large.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingMedium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeShort -> icon.withHeading.medium.sizeShort
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeMedium -> icon.withHeading.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingMedium.SizeTall -> icon.withHeading.medium.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithHeadingSmall, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeShort -> icon.withHeading.small.sizeShort
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeMedium -> icon.withHeading.small.sizeMedium
        OudsSizeKeyToken.Icon.WithHeadingSmall.SizeTall -> icon.withHeading.small.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodyLarge, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeShort -> icon.withBody.large.sizeShort
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeMedium -> icon.withBody.large.sizeMedium
        OudsSizeKeyToken.Icon.WithBodyLarge.SizeTall -> icon.withBody.large.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodyMedium, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeShort -> icon.withBody.medium.sizeShort
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeMedium -> icon.withBody.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithBodyMedium.SizeTall -> icon.withBody.medium.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithBodySmall, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithBodySmall.SizeShort -> icon.withBody.small.sizeShort
        OudsSizeKeyToken.Icon.WithBodySmall.SizeMedium -> icon.withBody.small.sizeMedium
        OudsSizeKeyToken.Icon.WithBodySmall.SizeTall -> icon.withBody.small.sizeTall
    }.getValue(adaptiveWindowType)
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelExtraLarge): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeShort -> icon.withLabel.extraLarge.sizeShort
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeMedium -> icon.withLabel.extraLarge.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelExtraLarge.SizeTall -> icon.withLabel.extraLarge.sizeTall
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelLarge): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeShorter -> icon.withLabel.large.sizeShorter
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeShort -> icon.withLabel.large.sizeShort
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeMedium -> icon.withLabel.large.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeTall -> icon.withLabel.large.sizeTall
        OudsSizeKeyToken.Icon.WithLabelLarge.SizeTaller -> icon.withLabel.large.sizeTaller
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelMedium): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeShort -> icon.withLabel.medium.sizeShort
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeMedium -> icon.withLabel.medium.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelMedium.SizeTall -> icon.withLabel.medium.sizeTall
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.Icon.WithLabelSmall): Dp {
    return when (token) {
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeShort -> icon.withLabel.small.sizeShort
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeMedium -> icon.withLabel.small.sizeMedium
        OudsSizeKeyToken.Icon.WithLabelSmall.SizeTall -> icon.withLabel.small.sizeTall
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
