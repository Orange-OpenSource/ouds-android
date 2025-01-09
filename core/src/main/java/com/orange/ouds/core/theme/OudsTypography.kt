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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens

/**
 * @suppress
 */
data class OudsTypography(
    val display: Display,
    val heading: Heading,
    val body: Body,
    val label: Label
) {
    data class Display(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    data class Heading(
        val extraLarge: TextStyle,
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    data class Body(
        val default: Default,
        val strong: Strong
    ) {
        data class Default(
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        data class Strong(
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )
    }

    data class Label(
        val default: Default,
        val strong: Strong
    ) {
        data class Default(
            val extraLarge: TextStyle,
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        data class Strong(
            val extraLarge: TextStyle,
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )
    }
}

internal fun OudsFontSemanticTokens.getTypography(fontFamily: FontFamily, windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsTypography(
        display = OudsTypography.Display(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplayLargeMobile, sizeDisplayLargeTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplayLargeMobile, lineHeightDisplayLargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplayLargeMobile, letterSpacingDisplayLargeTablet).sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplayMediumMobile, sizeDisplayMediumTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplayMediumMobile, lineHeightDisplayMediumTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplayMediumMobile, letterSpacingDisplayMediumTablet).sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplaySmallMobile, sizeDisplaySmallTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplaySmallMobile, lineHeightDisplaySmallTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplaySmallMobile, letterSpacingDisplaySmallTablet).sp
            ),
        ),
        heading = OudsTypography.Heading(
            extraLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingXlargeMobile, sizeHeadingXlargeTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingXlargeMobile, lineHeightHeadingXlargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingXlargeMobile, letterSpacingHeadingXlargeTablet).sp
            ),
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingLargeMobile, sizeHeadingLargeTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingLargeMobile, lineHeightHeadingLargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingLargeMobile, letterSpacingHeadingLargeTablet).sp
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingMediumMobile, sizeHeadingMediumTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingMediumMobile, lineHeightHeadingMediumTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingMediumMobile, letterSpacingHeadingMediumTablet).sp
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingSmallMobile, sizeHeadingSmallTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingSmallMobile, lineHeightHeadingSmallTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingSmallMobile, letterSpacingHeadingSmallTablet).sp
            ),
        ),
        body = OudsTypography.Body(
            default = OudsTypography.Body.Default(
                large = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyDefault),
                    fontSize = getTokenValue(sizeBodyLargeMobile, sizeBodyLargeTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodyLargeMobile, lineHeightBodyLargeTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodyLargeMobile, letterSpacingBodyLargeTablet).sp
                ),
                medium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyDefault),
                    fontSize = getTokenValue(sizeBodyMediumMobile, sizeBodyMediumTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodyMediumMobile, lineHeightBodyMediumTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodyMediumMobile, letterSpacingBodyMediumTablet).sp
                ),
                small = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyDefault),
                    fontSize = getTokenValue(sizeBodySmallMobile, sizeBodySmallTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodySmallMobile, lineHeightBodySmallTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodySmallMobile, letterSpacingBodySmallTablet).sp
                ),
            ),
            strong = OudsTypography.Body.Strong(
                large = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyStrong),
                    fontSize = getTokenValue(sizeBodyLargeMobile, sizeBodyLargeTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodyLargeMobile, lineHeightBodyLargeTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodyLargeMobile, letterSpacingBodyLargeTablet).sp
                ),
                medium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyStrong),
                    fontSize = getTokenValue(sizeBodyMediumMobile, sizeBodyMediumTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodyMediumMobile, lineHeightBodyMediumTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodyMediumMobile, letterSpacingBodyMediumTablet).sp
                ),
                small = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightBodyStrong),
                    fontSize = getTokenValue(sizeBodySmallMobile, sizeBodySmallTablet).sp,
                    lineHeight = getTokenValue(lineHeightBodySmallMobile, lineHeightBodySmallTablet).sp,
                    letterSpacing = getTokenValue(letterSpacingBodySmallMobile, letterSpacingBodySmallTablet).sp
                ),
            )
        ),
        label = OudsTypography.Label(
            default = OudsTypography.Label.Default(
                extraLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelDefault),
                    fontSize = sizeLabelXlarge.sp,
                    lineHeight = lineHeightLabelXlarge.sp,
                    letterSpacing = letterSpacingLabelXlarge.sp
                ),
                large = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelDefault),
                    fontSize = sizeLabelLarge.sp,
                    lineHeight = lineHeightLabelLarge.sp,
                    letterSpacing = letterSpacingLabelLarge.sp
                ),
                medium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelDefault),
                    fontSize = sizeLabelMedium.sp,
                    lineHeight = lineHeightLabelMedium.sp,
                    letterSpacing = letterSpacingLabelMedium.sp
                ),
                small = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelDefault),
                    fontSize = sizeLabelSmall.sp,
                    lineHeight = lineHeightLabelSmall.sp,
                    letterSpacing = letterSpacingLabelSmall.sp
                ),
            ),
            strong = OudsTypography.Label.Strong(
                extraLarge = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelStrong),
                    fontSize = sizeLabelXlarge.sp,
                    lineHeight = lineHeightLabelXlarge.sp,
                    letterSpacing = letterSpacingLabelXlarge.sp
                ),
                large = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelStrong),
                    fontSize = sizeLabelLarge.sp,
                    lineHeight = lineHeightLabelLarge.sp,
                    letterSpacing = letterSpacingLabelLarge.sp
                ),
                medium = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelStrong),
                    fontSize = sizeLabelMedium.sp,
                    lineHeight = lineHeightLabelMedium.sp,
                    letterSpacing = letterSpacingLabelMedium.sp
                ),
                small = TextStyle(
                    fontFamily = fontFamily,
                    fontWeight = FontWeight(weightLabelStrong),
                    fontSize = sizeLabelSmall.sp,
                    lineHeight = lineHeightLabelSmall.sp,
                    letterSpacing = letterSpacingLabelSmall.sp
                ),
            )
        ),
    )
}

@Stable
private fun OudsTypography.fromToken(token: OudsTypographyKeyToken): TextStyle {
    return when (token) {
        OudsTypographyKeyToken.Display.Large -> display.large
        OudsTypographyKeyToken.Display.Medium -> display.medium
        OudsTypographyKeyToken.Display.Small -> display.small
        OudsTypographyKeyToken.Heading.ExtraLarge -> heading.extraLarge
        OudsTypographyKeyToken.Heading.Large -> heading.large
        OudsTypographyKeyToken.Heading.Medium -> heading.medium
        OudsTypographyKeyToken.Heading.Small -> heading.small
        OudsTypographyKeyToken.Body.Default.Large -> body.default.large
        OudsTypographyKeyToken.Body.Default.Medium -> body.default.medium
        OudsTypographyKeyToken.Body.Default.Small -> body.default.small
        OudsTypographyKeyToken.Body.Strong.Large -> body.strong.large
        OudsTypographyKeyToken.Body.Strong.Medium -> body.strong.medium
        OudsTypographyKeyToken.Body.Strong.Small -> body.strong.small
        OudsTypographyKeyToken.Label.Default.ExtraLarge -> label.default.extraLarge
        OudsTypographyKeyToken.Label.Default.Large -> label.default.large
        OudsTypographyKeyToken.Label.Default.Medium -> label.default.medium
        OudsTypographyKeyToken.Label.Default.Small -> label.default.small
        OudsTypographyKeyToken.Label.Strong.ExtraLarge -> label.strong.extraLarge
        OudsTypographyKeyToken.Label.Strong.Large -> label.strong.large
        OudsTypographyKeyToken.Label.Strong.Medium -> label.strong.medium
        OudsTypographyKeyToken.Label.Strong.Small -> label.strong.small
    }
}

/**
 * Converts an OUDS typography token to the local typography value provided by the theme.
 * Note that the typography value returned varies depending on the window size.
 */
@InternalOudsApi
val OudsTypographyKeyToken.value: TextStyle
    @Composable
    get() = OudsTheme.typography.fromToken(this)
