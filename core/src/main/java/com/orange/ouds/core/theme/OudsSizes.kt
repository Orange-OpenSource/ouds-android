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
fun OudsSizes.fromToken(token: OudsSizeKeyToken.IconDecorative): Dp {
    return when (token) {
        OudsSizeKeyToken.IconDecorative.Shortest -> icon.decorative.shortest
        OudsSizeKeyToken.IconDecorative.Shorter -> icon.decorative.shorter
        OudsSizeKeyToken.IconDecorative.Short -> icon.decorative.short
        OudsSizeKeyToken.IconDecorative.Medium -> icon.decorative.medium
        OudsSizeKeyToken.IconDecorative.Tall -> icon.decorative.tall
        OudsSizeKeyToken.IconDecorative.Taller -> icon.decorative.taller
        OudsSizeKeyToken.IconDecorative.Tallest -> icon.decorative.tallest
    }
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.IconWithText, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionSizeIconWithTextValue: Any = when (token) {
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeShort -> icon.withHeading.small.sizeShort
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeMedium -> icon.withHeading.small.sizeMedium
        OudsSizeKeyToken.IconWithText.HeadingSmallSizeTall -> icon.withHeading.small.sizeTall
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeShort -> icon.withHeading.medium.sizeShort
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeMedium -> icon.withHeading.medium.sizeMedium
        OudsSizeKeyToken.IconWithText.HeadingMediumSizeTall -> icon.withHeading.medium.sizeTall
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeShort -> icon.withHeading.large.sizeShort
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeMedium -> icon.withHeading.large.sizeMedium
        OudsSizeKeyToken.IconWithText.HeadingLargeSizeTall -> icon.withHeading.large.sizeTall
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeShort -> icon.withHeading.extraLarge.sizeShort
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeMedium -> icon.withHeading.extraLarge.sizeMedium
        OudsSizeKeyToken.IconWithText.HeadingExtraLargeSizeTall -> icon.withHeading.extraLarge.sizeTall
        OudsSizeKeyToken.IconWithText.LabelLargeSizeShorter -> icon.withLabel.large.sizeShorter
        OudsSizeKeyToken.IconWithText.LabelLargeSizeShort -> icon.withLabel.large.sizeShort
        OudsSizeKeyToken.IconWithText.LabelLargeSizeMedium -> icon.withLabel.large.sizeMedium
        OudsSizeKeyToken.IconWithText.LabelLargeSizeTall -> icon.withLabel.large.sizeTall
        OudsSizeKeyToken.IconWithText.LabelLargeSizeTaller -> icon.withLabel.large.sizeTaller
        OudsSizeKeyToken.IconWithText.LabelMediumSizeShort -> icon.withLabel.medium.sizeShort
        OudsSizeKeyToken.IconWithText.LabelMediumSizeMedium -> icon.withLabel.medium.sizeMedium
        OudsSizeKeyToken.IconWithText.LabelMediumSizeTall -> icon.withLabel.medium.sizeTall
        OudsSizeKeyToken.IconWithText.LabelSmallSizeShort -> icon.withLabel.small.sizeShort
        OudsSizeKeyToken.IconWithText.LabelSmallSizeMedium -> icon.withLabel.small.sizeMedium
        OudsSizeKeyToken.IconWithText.LabelSmallSizeTall -> icon.withLabel.small.sizeTall
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeShort -> icon.withLabel.extraLarge.sizeShort
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeMedium -> icon.withLabel.extraLarge.sizeMedium
        OudsSizeKeyToken.IconWithText.LabelExtraLargeSizeTall -> icon.withLabel.extraLarge.sizeTall
        OudsSizeKeyToken.IconWithText.BodySmallSizeShort -> icon.withBody.small.sizeShort
        OudsSizeKeyToken.IconWithText.BodySmallSizeMedium -> icon.withBody.small.sizeMedium
        OudsSizeKeyToken.IconWithText.BodySmallSizeTall -> icon.withBody.small.sizeTall
        OudsSizeKeyToken.IconWithText.BodyMediumSizeShort -> icon.withBody.medium.sizeShort
        OudsSizeKeyToken.IconWithText.BodyMediumSizeMedium -> icon.withBody.medium.sizeMedium
        OudsSizeKeyToken.IconWithText.BodyMediumSizeTall -> icon.withBody.medium.sizeTall
        OudsSizeKeyToken.IconWithText.BodyLargeSizeShort -> icon.withBody.large.sizeShort
        OudsSizeKeyToken.IconWithText.BodyLargeSizeMedium -> icon.withBody.large.sizeMedium
        OudsSizeKeyToken.IconWithText.BodyLargeSizeTall -> icon.withBody.large.sizeTall
    }

    return if (dimensionSizeIconWithTextValue is OudsAdaptiveTokenValue<*>) {
        dimensionSizeIconWithTextValue.getValue(adaptiveWindowType)
    } else {
        dimensionSizeIconWithTextValue
    } as Dp
}

@Stable
fun OudsSizes.fromToken(token: OudsSizeKeyToken.MaxWidthType, adaptiveWindowType: OudsAdaptiveWindowType): Dp {
    val dimensionMaxWidthTypeToken = when (token) {
        OudsSizeKeyToken.MaxWidthType.DisplaySmall -> maxWidthType.display.small
        OudsSizeKeyToken.MaxWidthType.DisplayMedium -> maxWidthType.display.medium
        OudsSizeKeyToken.MaxWidthType.DisplayLarge -> maxWidthType.display.large
        OudsSizeKeyToken.MaxWidthType.HeadingSmall -> maxWidthType.heading.small
        OudsSizeKeyToken.MaxWidthType.HeadingMedium -> maxWidthType.heading.medium
        OudsSizeKeyToken.MaxWidthType.HeadingLarge -> maxWidthType.heading.large
        OudsSizeKeyToken.MaxWidthType.HeadingExtraLarge -> maxWidthType.heading.extraLarge
        OudsSizeKeyToken.MaxWidthType.BodySmall -> maxWidthType.body.small
        OudsSizeKeyToken.MaxWidthType.BodyMedium -> maxWidthType.body.medium
        OudsSizeKeyToken.MaxWidthType.BodyLarge -> maxWidthType.body.large
    }

    return when (adaptiveWindowType) {
        OudsAdaptiveWindowType.EXTRA_COMPACT -> dimensionMaxWidthTypeToken.extraCompact
        OudsAdaptiveWindowType.COMPACT -> dimensionMaxWidthTypeToken.compact
        OudsAdaptiveWindowType.MEDIUM -> dimensionMaxWidthTypeToken.medium
    }
}

/**
 * Converts an OUDS decorative icon size token to the local decorative icon size value provided by the theme.
 */
val OudsSizeKeyToken.IconDecorative.value: Dp
    @ReadOnlyComposable
    @Composable
    get() = OudsTheme.sizes.fromToken(this)

/**
 * Converts an OUDS icon size with typography token to the local icon size with typography value provided by the theme depending on the window size.
 */
val OudsSizeKeyToken.IconWithText.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))

/**
 * Converts an OUDS max width with typography token to the local max width with typography value provided by the theme depending on the window size.
 */
val OudsSizeKeyToken.MaxWidthType.value: Dp
    @Composable
    get() = OudsTheme.sizes.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))