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
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headingExtraLarge: TextStyle,
    val headingLarge: TextStyle,
    val headingMedium: TextStyle,
    val headingSmall: TextStyle,
    val bodyDefaultLarge: TextStyle,
    val bodyDefaultMedium: TextStyle,
    val bodyDefaultSmall: TextStyle,
    val bodyStrongLarge: TextStyle,
    val bodyStrongMedium: TextStyle,
    val bodyStrongSmall: TextStyle,
    val labelDefaultExtraLarge: TextStyle,
    val labelDefaultLarge: TextStyle,
    val labelDefaultMedium: TextStyle,
    val labelDefaultSmall: TextStyle,
    val labelStrongExtraLarge: TextStyle,
    val labelStrongLarge: TextStyle,
    val labelStrongMedium: TextStyle,
    val labelStrongSmall: TextStyle,
)

internal fun OudsFontSemanticTokens.getTypography(fontFamily: FontFamily, windowWidthSizeClass: WindowWidthSizeClass) = with(windowWidthSizeClass) {
    OudsTypography(
        displayLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = getTokenValue(sizeDisplayLargeMobile, sizeDisplayLargeTablet).sp,
            lineHeight = getTokenValue(lineHeightDisplayLargeMobile, lineHeightDisplayLargeTablet).sp,
            letterSpacing = getTokenValue(letterSpacingDisplayLargeMobile, letterSpacingDisplayLargeTablet).sp
        ),
        displayMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = getTokenValue(sizeDisplayMediumMobile, sizeDisplayMediumTablet).sp,
            lineHeight = getTokenValue(lineHeightDisplayMediumMobile, lineHeightDisplayMediumTablet).sp,
            letterSpacing = getTokenValue(letterSpacingDisplayMediumMobile, letterSpacingDisplayMediumTablet).sp
        ),
        displaySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = getTokenValue(sizeDisplaySmallMobile, sizeDisplaySmallTablet).sp,
            lineHeight = getTokenValue(lineHeightDisplaySmallMobile, lineHeightDisplaySmallTablet).sp,
            letterSpacing = getTokenValue(letterSpacingDisplaySmallMobile, letterSpacingDisplaySmallTablet).sp
        ),
        headingExtraLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = getTokenValue(sizeHeadingXlargeMobile, sizeHeadingXlargeTablet).sp,
            lineHeight = getTokenValue(lineHeightHeadingXlargeMobile, lineHeightHeadingXlargeTablet).sp,
            letterSpacing = getTokenValue(letterSpacingHeadingXlargeMobile, letterSpacingHeadingXlargeTablet).sp
        ),
        headingLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = getTokenValue(sizeHeadingLargeMobile, sizeHeadingLargeTablet).sp,
            lineHeight = getTokenValue(lineHeightHeadingLargeMobile, lineHeightHeadingLargeTablet).sp,
            letterSpacing = getTokenValue(letterSpacingHeadingLargeMobile, letterSpacingHeadingLargeTablet).sp
        ),
        headingMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = getTokenValue(sizeHeadingMediumMobile, sizeHeadingMediumTablet).sp,
            lineHeight = getTokenValue(lineHeightHeadingMediumMobile, lineHeightHeadingMediumTablet).sp,
            letterSpacing = getTokenValue(letterSpacingHeadingMediumMobile, letterSpacingHeadingMediumTablet).sp
        ),
        headingSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = getTokenValue(sizeHeadingSmallMobile, sizeHeadingSmallTablet).sp,
            lineHeight = getTokenValue(lineHeightHeadingSmallMobile, lineHeightHeadingSmallTablet).sp,
            letterSpacing = getTokenValue(letterSpacingHeadingSmallMobile, letterSpacingHeadingSmallTablet).sp
        ),
        bodyDefaultLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = getTokenValue(sizeBodyLargeMobile, sizeBodyLargeTablet).sp,
            lineHeight = getTokenValue(lineHeightBodyLargeMobile, lineHeightBodyLargeTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodyLargeMobile, letterSpacingBodyLargeTablet).sp
        ),
        bodyDefaultMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = getTokenValue(sizeBodyMediumMobile, sizeBodyMediumTablet).sp,
            lineHeight = getTokenValue(lineHeightBodyMediumMobile, lineHeightBodyMediumTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodyMediumMobile, letterSpacingBodyMediumTablet).sp
        ),
        bodyDefaultSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = getTokenValue(sizeBodySmallMobile, sizeBodySmallTablet).sp,
            lineHeight = getTokenValue(lineHeightBodySmallMobile, lineHeightBodySmallTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodySmallMobile, letterSpacingBodySmallTablet).sp
        ),
        bodyStrongLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = getTokenValue(sizeBodyLargeMobile, sizeBodyLargeTablet).sp,
            lineHeight = getTokenValue(lineHeightBodyLargeMobile, lineHeightBodyLargeTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodyLargeMobile, letterSpacingBodyLargeTablet).sp
        ),
        bodyStrongMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = getTokenValue(sizeBodyMediumMobile, sizeBodyMediumTablet).sp,
            lineHeight = getTokenValue(lineHeightBodyMediumMobile, lineHeightBodyMediumTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodyMediumMobile, letterSpacingBodyMediumTablet).sp
        ),
        bodyStrongSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = getTokenValue(sizeBodySmallMobile, sizeBodySmallTablet).sp,
            lineHeight = getTokenValue(lineHeightBodySmallMobile, lineHeightBodySmallTablet).sp,
            letterSpacing = getTokenValue(letterSpacingBodySmallMobile, letterSpacingBodySmallTablet).sp
        ),
        labelDefaultExtraLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelDefault),
            fontSize = sizeLabelXlarge.sp,
            lineHeight = lineHeightLabelXlarge.sp,
            letterSpacing = letterSpacingLabelXlarge.sp
        ),
        labelDefaultLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelDefault),
            fontSize = sizeLabelLarge.sp,
            lineHeight = lineHeightLabelLarge.sp,
            letterSpacing = letterSpacingLabelLarge.sp
        ),
        labelDefaultMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelDefault),
            fontSize = sizeLabelMedium.sp,
            lineHeight = lineHeightLabelMedium.sp,
            letterSpacing = letterSpacingLabelMedium.sp
        ),
        labelDefaultSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelDefault),
            fontSize = sizeLabelSmall.sp,
            lineHeight = lineHeightLabelSmall.sp,
            letterSpacing = letterSpacingLabelSmall.sp
        ),
        labelStrongExtraLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelStrong),
            fontSize = sizeLabelXlarge.sp,
            lineHeight = lineHeightLabelXlarge.sp,
            letterSpacing = letterSpacingLabelXlarge.sp
        ),
        labelStrongLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelStrong),
            fontSize = sizeLabelLarge.sp,
            lineHeight = lineHeightLabelLarge.sp,
            letterSpacing = letterSpacingLabelLarge.sp
        ),
        labelStrongMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelStrong),
            fontSize = sizeLabelMedium.sp,
            lineHeight = lineHeightLabelMedium.sp,
            letterSpacing = letterSpacingLabelMedium.sp
        ),
        labelStrongSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightLabelStrong),
            fontSize = sizeLabelSmall.sp,
            lineHeight = lineHeightLabelSmall.sp,
            letterSpacing = letterSpacingLabelSmall.sp
        ),
    )
}

@Stable
private fun OudsTypography.fromToken(token: OudsTypographyKeyToken): TextStyle {
    return when (token) {
        OudsTypographyKeyToken.Display.Large -> displayLarge
        OudsTypographyKeyToken.Display.Medium -> displayMedium
        OudsTypographyKeyToken.Display.Small -> displaySmall
        OudsTypographyKeyToken.Heading.ExtraLarge -> headingExtraLarge
        OudsTypographyKeyToken.Heading.Large -> headingLarge
        OudsTypographyKeyToken.Heading.Medium -> headingMedium
        OudsTypographyKeyToken.Heading.Small -> headingSmall
        OudsTypographyKeyToken.Body.Default.Large -> bodyDefaultLarge
        OudsTypographyKeyToken.Body.Default.Medium -> bodyDefaultMedium
        OudsTypographyKeyToken.Body.Default.Small -> bodyDefaultSmall
        OudsTypographyKeyToken.Body.Strong.Large -> bodyStrongLarge
        OudsTypographyKeyToken.Body.Strong.Medium -> bodyStrongMedium
        OudsTypographyKeyToken.Body.Strong.Small -> bodyStrongSmall
        OudsTypographyKeyToken.Label.Default.ExtraLarge -> labelDefaultExtraLarge
        OudsTypographyKeyToken.Label.Default.Large -> labelDefaultLarge
        OudsTypographyKeyToken.Label.Default.Medium -> labelDefaultMedium
        OudsTypographyKeyToken.Label.Default.Small -> labelDefaultSmall
        OudsTypographyKeyToken.Label.Strong.ExtraLarge -> labelStrongExtraLarge
        OudsTypographyKeyToken.Label.Strong.Large -> labelStrongLarge
        OudsTypographyKeyToken.Label.Strong.Medium -> labelStrongMedium
        OudsTypographyKeyToken.Label.Strong.Small -> labelStrongSmall
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
