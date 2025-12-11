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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens

/**
 * Holds the typography styles defined in the OUDS theme.
 *
 * Typography is managed with a defined set of text styles. It enhances communication and reinforce the attribution to the brand.
 *
 * > Design guidelines: [Typography tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-typography)
 *
 * @property display Styles for large, prominent text.
 * @property heading Styles for headings and titles.
 * @property body Styles for body text (paragraphs, descriptions).
 * @property label Styles for smaller utility text (e.g., captions, labels).
 */
data class OudsTypography(
    val fontFamily: FontFamily,
    val display: Display,
    val heading: Heading,
    val body: Body,
    val label: Label
) {
    /**
     * Styles for large, prominent text, typically used for major application titles.
     *
     * @property large Large display text style.
     * @property medium Medium display text style.
     * @property small Small display text style.
     */
    data class Display(
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    /**
     * Styles for headings and titles, used to structure content hierarchy.
     *
     * @property extraLarge Extra large heading style.
     * @property large Large heading style.
     * @property medium Medium heading style.
     * @property small Small heading style.
     */
    data class Heading(
        val extraLarge: TextStyle,
        val large: TextStyle,
        val medium: TextStyle,
        val small: TextStyle
    )

    /**
     * Styles for body text, used for the main content, paragraphs, and descriptions.
     *
     * @property default Default font weight styles (Regular).
     * @property moderate Moderate font weight styles (Medium).
     * @property strong Strong font weight styles (Bold).
     */
    data class Body(
        val default: Default,
        val moderate: Moderate,
        val strong: Strong
    ) {
        /**
         * Body styles with Default font weight (Regular).
         *
         * @property large Large body text.
         * @property medium Medium body text.
         * @property small Small body text.
         */
        data class Default(
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        /**
         * Body styles with Moderate font weight (Medium).
         *
         * @property large Large moderate body text.
         * @property medium Medium moderate body text.
         * @property small Small moderate body text.
         */
        data class Moderate(
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        /**
         * Body styles with Strong font weight (Bold).
         *
         * @property large Large strong body text.
         * @property medium Medium strong body text.
         * @property small Small strong body text.
         */
        data class Strong(
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )
    }


    /**
     * Styles for utility text such as captions, input labels, and metadata.
     *
     * @property default Default font weight styles (Regular).
     * @property moderate Moderate font weight styles (Medium).
     * @property strong Strong font weight styles (Bold).
     */
    data class Label(
        val default: Default,
        val moderate: Moderate,
        val strong: Strong
    ) {
        /**
         * Label styles with Default font weight (Regular).
         *
         * @property extraLarge Extra large label text.
         * @property large Large label text.
         * @property medium Medium label text.
         * @property small Small label text.
         */
        data class Default(
            val extraLarge: TextStyle,
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        /**
         * Label styles with Moderate font weight (Medium).
         *
         * @property extraLarge Extra large moderate label text.
         * @property large Large moderate label text.
         * @property medium Medium moderate label text.
         * @property small Small moderate label text.
         */
        data class Moderate(
            val extraLarge: TextStyle,
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )

        /**
         * Label styles with Strong font weight (Bold).
         *
         * @property extraLarge Extra large strong label text.
         * @property large Large strong label text.
         * @property medium Medium strong label text.
         * @property small Small strong label text.
         */
        data class Strong(
            val extraLarge: TextStyle,
            val large: TextStyle,
            val medium: TextStyle,
            val small: TextStyle
        )
    }
}

internal fun OudsFontSemanticTokens.getTypography(fontFamily: FontFamily, windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    val lineHeightStyle = LineHeightStyle(alignment = LineHeightStyle.Alignment.Proportional, trim = LineHeightStyle.Trim.None)
    OudsTypography(
        fontFamily = fontFamily,
        display = OudsTypography.Display(
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplayLargeMobile, sizeDisplayLargeTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplayLargeMobile, lineHeightDisplayLargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplayLargeMobile, letterSpacingDisplayLargeTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplayMediumMobile, sizeDisplayMediumTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplayMediumMobile, lineHeightDisplayMediumTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplayMediumMobile, letterSpacingDisplayMediumTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightDisplay),
                fontSize = getTokenValue(sizeDisplaySmallMobile, sizeDisplaySmallTablet).sp,
                lineHeight = getTokenValue(lineHeightDisplaySmallMobile, lineHeightDisplaySmallTablet).sp,
                letterSpacing = getTokenValue(letterSpacingDisplaySmallMobile, letterSpacingDisplaySmallTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
        ),
        heading = OudsTypography.Heading(
            extraLarge = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingXlargeMobile, sizeHeadingXlargeTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingXlargeMobile, lineHeightHeadingXlargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingXlargeMobile, letterSpacingHeadingXlargeTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
            large = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingLargeMobile, sizeHeadingLargeTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingLargeMobile, lineHeightHeadingLargeTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingLargeMobile, letterSpacingHeadingLargeTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
            medium = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingMediumMobile, sizeHeadingMediumTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingMediumMobile, lineHeightHeadingMediumTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingMediumMobile, letterSpacingHeadingMediumTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
            small = TextStyle(
                fontFamily = fontFamily,
                fontWeight = FontWeight(weightHeading),
                fontSize = getTokenValue(sizeHeadingSmallMobile, sizeHeadingSmallTablet).sp,
                lineHeight = getTokenValue(lineHeightHeadingSmallMobile, lineHeightHeadingSmallTablet).sp,
                letterSpacing = getTokenValue(letterSpacingHeadingSmallMobile, letterSpacingHeadingSmallTablet).sp,
                lineHeightStyle = lineHeightStyle
            ),
        ),
        body = OudsTypography.Body(
            default = OudsTypography.Body.Default(
                large = bodyLargeTextStyle(fontFamily, FontWeight(weightBodyDefault), lineHeightStyle, windowWidthSizeClass),
                medium = bodyMediumTextStyle(fontFamily, FontWeight(weightBodyDefault), lineHeightStyle, windowWidthSizeClass),
                small = bodySmallTextStyle(fontFamily, FontWeight(weightBodyDefault), lineHeightStyle, windowWidthSizeClass)
            ),
            moderate = OudsTypography.Body.Moderate(
                large = bodyLargeTextStyle(fontFamily, FontWeight(weightBodyModerate), lineHeightStyle, windowWidthSizeClass),
                medium = bodyMediumTextStyle(fontFamily, FontWeight(weightBodyModerate), lineHeightStyle, windowWidthSizeClass),
                small = bodySmallTextStyle(fontFamily, FontWeight(weightBodyModerate), lineHeightStyle, windowWidthSizeClass),
            ),
            strong = OudsTypography.Body.Strong(
                large = bodyLargeTextStyle(fontFamily, FontWeight(weightBodyStrong), lineHeightStyle, windowWidthSizeClass),
                medium = bodyMediumTextStyle(fontFamily, FontWeight(weightBodyStrong), lineHeightStyle, windowWidthSizeClass),
                small = bodySmallTextStyle(fontFamily, FontWeight(weightBodyStrong), lineHeightStyle, windowWidthSizeClass)
            )
        ),
        label = OudsTypography.Label(
            default = OudsTypography.Label.Default(
                extraLarge = labelExtraLargeTextStyle(fontFamily, FontWeight(weightLabelDefault), lineHeightStyle, windowWidthSizeClass),
                large = labelLargeTextStyle(fontFamily, FontWeight(weightLabelDefault), lineHeightStyle, windowWidthSizeClass),
                medium = labelMediumTextStyle(fontFamily, FontWeight(weightLabelDefault), lineHeightStyle, windowWidthSizeClass),
                small = labelSmallTextStyle(fontFamily, FontWeight(weightLabelDefault), lineHeightStyle, windowWidthSizeClass),
            ),
            moderate = OudsTypography.Label.Moderate(
                extraLarge = labelExtraLargeTextStyle(fontFamily, FontWeight(weightLabelModerate), lineHeightStyle, windowWidthSizeClass),
                large = labelLargeTextStyle(fontFamily, FontWeight(weightLabelModerate), lineHeightStyle, windowWidthSizeClass),
                medium = labelMediumTextStyle(fontFamily, FontWeight(weightLabelModerate), lineHeightStyle, windowWidthSizeClass),
                small = labelSmallTextStyle(fontFamily, FontWeight(weightLabelModerate), lineHeightStyle, windowWidthSizeClass),
            ),
            strong = OudsTypography.Label.Strong(
                extraLarge = labelExtraLargeTextStyle(fontFamily, FontWeight(weightLabelStrong), lineHeightStyle, windowWidthSizeClass),
                large = labelLargeTextStyle(fontFamily, FontWeight(weightLabelStrong), lineHeightStyle, windowWidthSizeClass),
                medium = labelMediumTextStyle(fontFamily, FontWeight(weightLabelStrong), lineHeightStyle, windowWidthSizeClass),
                small = labelSmallTextStyle(fontFamily, FontWeight(weightLabelStrong), lineHeightStyle, windowWidthSizeClass),
            )
        ),
    )
}

private fun OudsFontSemanticTokens.bodyLargeTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = getTokenValue(sizeBodyLargeMobile, sizeBodyLargeTablet).sp,
        lineHeight = getTokenValue(lineHeightBodyLargeMobile, lineHeightBodyLargeTablet).sp,
        letterSpacing = getTokenValue(letterSpacingBodyLargeMobile, letterSpacingBodyLargeTablet).sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.bodyMediumTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = getTokenValue(sizeBodyMediumMobile, sizeBodyMediumTablet).sp,
        lineHeight = getTokenValue(lineHeightBodyMediumMobile, lineHeightBodyMediumTablet).sp,
        letterSpacing = getTokenValue(letterSpacingBodyMediumMobile, letterSpacingBodyMediumTablet).sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.bodySmallTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = getTokenValue(sizeBodySmallMobile, sizeBodySmallTablet).sp,
        lineHeight = getTokenValue(lineHeightBodySmallMobile, lineHeightBodySmallTablet).sp,
        letterSpacing = getTokenValue(letterSpacingBodySmallMobile, letterSpacingBodySmallTablet).sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.labelExtraLargeTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = sizeLabelXlarge.sp,
        lineHeight = lineHeightLabelXlarge.sp,
        letterSpacing = letterSpacingLabelXlarge.sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.labelLargeTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = sizeLabelLarge.sp,
        lineHeight = lineHeightLabelLarge.sp,
        letterSpacing = letterSpacingLabelLarge.sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.labelMediumTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = sizeLabelMedium.sp,
        lineHeight = lineHeightLabelMedium.sp,
        letterSpacing = letterSpacingLabelMedium.sp,
        lineHeightStyle = lineHeightStyle
    )
}

private fun OudsFontSemanticTokens.labelSmallTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    lineHeightStyle: LineHeightStyle,
    windowWidthSizeClass: WindowWidthSizeClass
) = with(windowWidthSizeClass) {
    TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = sizeLabelSmall.sp,
        lineHeight = lineHeightLabelSmall.sp,
        letterSpacing = letterSpacingLabelSmall.sp,
        lineHeightStyle = lineHeightStyle
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
