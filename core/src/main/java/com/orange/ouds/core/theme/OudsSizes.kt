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
 * Holds the size values defined in the OUDS theme.
 *
 * These values ensure consistent sizing for icons, touch targets, and layout constraints
 * across different screen sizes and contexts.
 *
 * > Design guidelines: [Size tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-size)
 *
 * @property icon Sizes related to icons.
 * @property maxWidth Max width constraints for layouts.
 * @property minInteractiveArea The minimum size for an interactive touch target.
 */
@ConsistentCopyVisibility
data class OudsSizes internal constructor(
    val icon: Icon,
    val maxWidth: MaxWidth,
    val minInteractiveArea: Dp
) {
    /**
     * Sizes for icons depending on their usage context.
     *
     * @property decorative Sizes for standalone decorative icons.
     * @property withHeading Sizes for icons paired with heading typography.
     * @property withLabel Sizes for icons paired with label typography.
     * @property withBody Sizes for icons paired with body typography.
     */
    @ConsistentCopyVisibility
    data class Icon internal constructor(
        val decorative: Decorative,
        val withHeading: WithHeading,
        val withLabel: WithLabel,
        val withBody: WithBody
    ) {
        /**
         * Sizes for standalone decorative icons.
         *
         * @property fourExtraSmall 4xs size.
         * @property threeExtraSmall 3xs size.
         * @property twoExtraSmall 2xs size.
         * @property extraSmall Extra small size.
         * @property small Small size.
         * @property medium Medium size.
         * @property large Large size.
         * @property extraLarge Extra large size.
         * @property twoExtraLarge 2xl size.
         */
        @ConsistentCopyVisibility
        data class Decorative internal constructor(
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

        /**
         * Sizes for icons paired with heading typography.
         *
         * The properties correspond to the size of the associated heading text.
         *
         * @property small Icons associated with small heading.
         * @property medium Icons associated with medium heading.
         * @property large Icons associated with large heading.
         * @property extraLarge Icons associated with extra large heading.
         */
        @ConsistentCopyVisibility
        data class WithHeading internal constructor(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            /**
             * Icon sizes for small heading.
             *
             * @property sizeSmall Small icon size for use with a small heading.
             * @property sizeMedium Medium icon size for use with a small heading.
             * @property sizeLarge Large icon size for use with a small heading.
             */
            @ConsistentCopyVisibility
            data class Small internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for medium heading.
             *
             * @property sizeSmall Small icon size for use with a medium heading.
             * @property sizeMedium Medium icon size for use with a medium heading.
             * @property sizeLarge Large icon size for use with a medium heading.
             */
            @ConsistentCopyVisibility
            data class Medium internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for large heading.
             *
             * @property sizeSmall Small icon size for use with a large heading.
             * @property sizeMedium Medium icon size for use with a large heading.
             * @property sizeLarge Large icon size for use with a large heading.
             */
            @ConsistentCopyVisibility
            data class Large internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for extra large heading.
             *
             * @property sizeSmall Small icon size for use with an extra large heading.
             * @property sizeMedium Medium icon size for use with an extra large heading.
             * @property sizeLarge Large icon size for use with an extra large heading.
             */
            @ConsistentCopyVisibility
            data class ExtraLarge internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }

        /**
         * Sizes for icons paired with label typography.
         *
         * The properties correspond to the size of the associated label text.
         *
         * @property small Icons associated with small labels.
         * @property medium Icons associated with medium labels.
         * @property large Icons associated with large labels.
         * @property extraLarge Icons associated with extra large labels.
         */
        @ConsistentCopyVisibility
        data class WithLabel internal constructor(
            val small: Small,
            val medium: Medium,
            val large: Large,
            val extraLarge: ExtraLarge
        ) {
            /**
             * Icon sizes for small label.
             *
             * @property sizeExtraSmall Extra small icon size for use with a small label.
             * @property sizeSmall Small icon size for use with a small label.
             * @property sizeMedium Medium icon size for use with a small label.
             * @property sizeLarge Large icon size for use with a small label.
             */
            @ConsistentCopyVisibility
            data class Small internal constructor(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for medium label.
             *
             * @property sizeExtraSmall Extra small icon size for use with a medium label.
             * @property sizeSmall Small icon size for use with a medium label.
             * @property sizeMedium Medium icon size for use with a medium label.
             * @property sizeLarge Large icon size for use with a medium label.
             */
            @ConsistentCopyVisibility
            data class Medium internal constructor(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for large label.
             *
             * @property sizeExtraSmall Extra small icon size for use with a large label.
             * @property sizeSmall Small icon size for use with a large label.
             * @property sizeMedium Medium icon size for use with a large label.
             * @property sizeLarge Large icon size for use with a large label.
             * @property sizeExtraLarge Extra large icon size for use with a large label.
             */
            @ConsistentCopyVisibility
            data class Large internal constructor(
                val sizeExtraSmall: Dp,
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
                val sizeExtraLarge: Dp,
            )

            /**
             * Icon sizes for extra large label.
             *
             * @property sizeSmall Small icon size for use with an extra large label.
             * @property sizeMedium Medium icon size for use with an extra large label.
             * @property sizeLarge Large icon size for use with an extra large label.
             */
            @ConsistentCopyVisibility
            data class ExtraLarge internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }

        /**
         * Sizes for icons paired with body typography.
         *
         * The properties correspond to the size of the associated body text.
         *
         * @property small Icons associated with small body.
         * @property medium Icons associated with medium body.
         * @property large Icons associated with large body.
         */
        @ConsistentCopyVisibility
        data class WithBody internal constructor(
            val small: Small,
            val medium: Medium,
            val large: Large,
        ) {
            /**
             * Icon sizes for small body.
             *
             * @property sizeSmall Small icon size for use with small body text.
             * @property sizeMedium Medium icon size for use with small body text.
             * @property sizeLarge Large icon size for use with small body text.
             */
            @ConsistentCopyVisibility
            data class Small internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for medium body.
             *
             * @property sizeSmall Small icon size for use with medium body text.
             * @property sizeMedium Medium icon size for use with medium body text.
             * @property sizeLarge Large icon size for use with medium body text.
             */
            @ConsistentCopyVisibility
            data class Medium internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )

            /**
             * Icon sizes for large body.
             *
             * @property sizeSmall Small icon size for use with large body text.
             * @property sizeMedium Medium icon size for use with large body text.
             * @property sizeLarge Large icon size for use with large body text.
             */
            @ConsistentCopyVisibility
            data class Large internal constructor(
                val sizeSmall: Dp,
                val sizeMedium: Dp,
                val sizeLarge: Dp,
            )
        }
    }

    /**
     * Maximum width constraints used to control line length and layout density.
     *
     * @property type Contains max width values grouped by typography type.
     */
    @ConsistentCopyVisibility
    data class MaxWidth internal constructor(
        val type: Type
    ) {
        /**
         * Max width values grouped by typography type.
         *
         * @property body Max widths for body text contexts.
         * @property display Max widths for display text contexts.
         * @property heading Max widths for heading text contexts.
         * @property label Max widths for label text contexts.
         */
        @ConsistentCopyVisibility
        data class Type internal constructor(
            val body: Body,
            val display: Display,
            val heading: Heading,
            val label: Label
        ) {
            /**
             * Max widths for body text contexts.
             *
             * @property small Max width constraint for small body text.
             * @property medium Max width constraint for medium body text.
             * @property large Max width constraint for large body text.
             */
            @ConsistentCopyVisibility
            data class Body internal constructor(
                val small: Dp,
                val medium: Dp,
                val large: Dp
            )

            /**
             * Max widths for display text contexts.
             *
             * @property small Max width constraint for small display text.
             * @property medium Max width constraint for medium display text.
             * @property large Max width constraint for large display text.
             */
            @ConsistentCopyVisibility
            data class Display internal constructor(
                val small: Dp,
                val medium: Dp,
                val large: Dp
            )

            /**
             * Max widths for heading text contexts.
             *
             * @property small Max width constraint for small heading text.
             * @property medium Max width constraint for medium heading text.
             * @property large Max width constraint for large heading text.
             * @property extraLarge Max width constraint for extra large heading text.
             */
            @ConsistentCopyVisibility
            data class Heading internal constructor(
                val small: Dp,
                val medium: Dp,
                val large: Dp,
                val extraLarge: Dp
            )

            /**
             * Max widths for label text contexts.
             *
             * @property small Max width constraint for small label text.
             * @property medium Max width constraint for medium label text.
             * @property large Max width constraint for large label text.
             * @property extraLarge Max width constraint for extra large label text.
             */
            @ConsistentCopyVisibility
            data class Label internal constructor(
                val small: Dp,
                val medium: Dp,
                val large: Dp,
                val extraLarge: Dp
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
                body = OudsSizes.MaxWidth.Type.Body(
                    small = getTokenValue(maxWidthTypeBodySmallMobile, maxWidthTypeBodySmallTablet).dp,
                    medium = getTokenValue(maxWidthTypeBodyMediumMobile, maxWidthTypeBodyMediumTablet).dp,
                    large = getTokenValue(maxWidthTypeBodyLargeMobile, maxWidthTypeBodyLargeTablet).dp,
                ),
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
                label = OudsSizes.MaxWidth.Type.Label(
                    small = getTokenValue(maxWidthTypeLabelSmallMobile, maxWidthTypeLabelSmallTablet).dp,
                    medium = getTokenValue(maxWidthTypeLabelMediumMobile, maxWidthTypeLabelMediumTablet).dp,
                    large = getTokenValue(maxWidthTypeLabelLargeMobile, maxWidthTypeLabelLargeTablet).dp,
                    extraLarge = getTokenValue(maxWidthTypeLabelXlargeMobile, maxWidthTypeLabelXlargeTablet).dp,
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
            OudsSizeKeyToken.MaxWidth.Type.Body.Small -> body.small
            OudsSizeKeyToken.MaxWidth.Type.Body.Medium -> body.medium
            OudsSizeKeyToken.MaxWidth.Type.Body.Large -> body.large
            OudsSizeKeyToken.MaxWidth.Type.Display.Small -> display.small
            OudsSizeKeyToken.MaxWidth.Type.Display.Medium -> display.medium
            OudsSizeKeyToken.MaxWidth.Type.Display.Large -> display.large
            OudsSizeKeyToken.MaxWidth.Type.Heading.Small -> heading.small
            OudsSizeKeyToken.MaxWidth.Type.Heading.Medium -> heading.medium
            OudsSizeKeyToken.MaxWidth.Type.Heading.Large -> heading.large
            OudsSizeKeyToken.MaxWidth.Type.Heading.ExtraLarge -> heading.extraLarge
            OudsSizeKeyToken.MaxWidth.Type.Label.ExtraLarge -> label.extraLarge
            OudsSizeKeyToken.MaxWidth.Type.Label.Large -> label.large
            OudsSizeKeyToken.MaxWidth.Type.Label.Medium -> label.medium
            OudsSizeKeyToken.MaxWidth.Type.Label.Small -> label.small
        }
    }
}

/**
 * Converts an OUDS size token to the local size value provided by the theme.
 *
 * @suppress
 */
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
