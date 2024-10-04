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
import com.orange.ouds.theme.OudsAdaptiveTokenValue
import com.orange.ouds.theme.OudsAdaptiveWindowType
import com.orange.ouds.theme.currentWindowWidth
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens


data class OudsTypography(
    val displayLarge: OudsAdaptiveTokenValue<TextStyle>,
    val displayMedium: OudsAdaptiveTokenValue<TextStyle>,
    val displaySmall: OudsAdaptiveTokenValue<TextStyle>,
    val headingExtraLarge: OudsAdaptiveTokenValue<TextStyle>,
    val headingLarge: OudsAdaptiveTokenValue<TextStyle>,
    val headingMedium: OudsAdaptiveTokenValue<TextStyle>,
    val headingSmall: OudsAdaptiveTokenValue<TextStyle>,
    val bodyDefaultLarge: OudsAdaptiveTokenValue<TextStyle>,
    val bodyDefaultMedium: OudsAdaptiveTokenValue<TextStyle>,
    val bodyDefaultSmall: OudsAdaptiveTokenValue<TextStyle>,
    val bodyStrongLarge: OudsAdaptiveTokenValue<TextStyle>,
    val bodyStrongMedium: OudsAdaptiveTokenValue<TextStyle>,
    val bodyStrongSmall: OudsAdaptiveTokenValue<TextStyle>,
    val labelDefaultExtraLarge: TextStyle,
    val labelDefaultLarge: TextStyle,
    val labelDefaultMedium: TextStyle,
    val labelDefaultSmall: TextStyle,
    val labelStrongExtraLarge: TextStyle,
    val labelStrongLarge: TextStyle,
    val labelStrongMedium: TextStyle,
    val labelStrongSmall: TextStyle,
)


fun OudsFontSemanticTokens.getTypography(fontFamily: FontFamily) = OudsTypography(
    displayLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayLargeMobile.sp,
            lineHeight = lineHeightDisplayLargeMobile.sp,
            letterSpacing = letterSpacingDisplayLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayLargeMobile.sp,
            lineHeight = lineHeightDisplayLargeMobile.sp,
            letterSpacing = letterSpacingDisplayLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayLargeTablet.sp,
            lineHeight = lineHeightDisplayLargeTablet.sp,
            letterSpacing = letterSpacingDisplayLargeTablet.sp
        ),
    ),
    displayMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayMediumMobile.sp,
            lineHeight = lineHeightDisplayMediumMobile.sp,
            letterSpacing = letterSpacingDisplayMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayMediumMobile.sp,
            lineHeight = lineHeightDisplayMediumMobile.sp,
            letterSpacing = letterSpacingDisplayMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplayMediumTablet.sp,
            lineHeight = lineHeightDisplayMediumTablet.sp,
            letterSpacing = letterSpacingDisplayMediumTablet.sp
        ),
    ),
    displaySmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplaySmallMobile.sp,
            lineHeight = lineHeightDisplaySmallMobile.sp,
            letterSpacing = letterSpacingDisplaySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplaySmallMobile.sp,
            lineHeight = lineHeightDisplaySmallMobile.sp,
            letterSpacing = letterSpacingDisplaySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeDisplaySmallTablet.sp,
            lineHeight = lineHeightDisplaySmallTablet.sp,
            letterSpacing = letterSpacingDisplaySmallTablet.sp
        ),
    ),
    headingExtraLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingXlargeMobile.sp,
            lineHeight = lineHeightHeadingXlargeMobile.sp,
            letterSpacing = letterSpacingHeadingXlargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingXlargeMobile.sp,
            lineHeight = lineHeightHeadingXlargeMobile.sp,
            letterSpacing = letterSpacingHeadingXlargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingXlargeTablet.sp,
            lineHeight = lineHeightHeadingXlargeTablet.sp,
            letterSpacing = letterSpacingHeadingXlargeTablet.sp
        ),
    ),
    headingLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingLargeMobile.sp,
            lineHeight = lineHeightHeadingLargeMobile.sp,
            letterSpacing = letterSpacingHeadingLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingLargeMobile.sp,
            lineHeight = lineHeightHeadingLargeMobile.sp,
            letterSpacing = letterSpacingHeadingLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingLargeTablet.sp,
            lineHeight = lineHeightHeadingLargeTablet.sp,
            letterSpacing = letterSpacingHeadingLargeTablet.sp
        ),
    ),
    headingMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingMediumMobile.sp,
            lineHeight = lineHeightHeadingMediumMobile.sp,
            letterSpacing = letterSpacingHeadingMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingMediumMobile.sp,
            lineHeight = lineHeightHeadingMediumMobile.sp,
            letterSpacing = letterSpacingHeadingMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingMediumTablet.sp,
            lineHeight = lineHeightHeadingMediumTablet.sp,
            letterSpacing = letterSpacingHeadingMediumTablet.sp
        ),
    ),
    headingSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingSmallMobile.sp,
            lineHeight = lineHeightHeadingSmallMobile.sp,
            letterSpacing = letterSpacingHeadingSmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingSmallMobile.sp,
            lineHeight = lineHeightHeadingSmallMobile.sp,
            letterSpacing = letterSpacingHeadingSmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeHeadingSmallTablet.sp,
            lineHeight = lineHeightHeadingSmallTablet.sp,
            letterSpacing = letterSpacingHeadingSmallTablet.sp
        ),
    ),
    bodyDefaultLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyLargeMobile.sp,
            lineHeight = lineHeightBodyLargeMobile.sp,
            letterSpacing = letterSpacingBodyLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyLargeMobile.sp,
            lineHeight = lineHeightBodyLargeMobile.sp,
            letterSpacing = letterSpacingBodyLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyLargeTablet.sp,
            lineHeight = lineHeightBodyLargeTablet.sp,
            letterSpacing = letterSpacingBodyLargeTablet.sp
        ),
    ),
    bodyDefaultMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyMediumMobile.sp,
            lineHeight = lineHeightBodyMediumMobile.sp,
            letterSpacing = letterSpacingBodyMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyMediumMobile.sp,
            lineHeight = lineHeightBodyMediumMobile.sp,
            letterSpacing = letterSpacingBodyMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodyMediumTablet.sp,
            lineHeight = lineHeightBodyMediumTablet.sp,
            letterSpacing = letterSpacingBodyMediumTablet.sp
        ),
    ),
    bodyDefaultSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodySmallMobile.sp,
            lineHeight = lineHeightBodySmallMobile.sp,
            letterSpacing = letterSpacingBodySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodySmallMobile.sp,
            lineHeight = lineHeightBodySmallMobile.sp,
            letterSpacing = letterSpacingBodySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeBodySmallTablet.sp,
            lineHeight = lineHeightBodySmallTablet.sp,
            letterSpacing = letterSpacingBodySmallTablet.sp
        ),
    ),
    bodyStrongLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyLargeMobile.sp,
            lineHeight = lineHeightBodyLargeMobile.sp,
            letterSpacing = letterSpacingBodyLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyLargeMobile.sp,
            lineHeight = lineHeightBodyLargeMobile.sp,
            letterSpacing = letterSpacingBodyLargeMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyLargeTablet.sp,
            lineHeight = lineHeightBodyLargeTablet.sp,
            letterSpacing = letterSpacingBodyLargeTablet.sp
        ),
    ),
    bodyStrongMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyMediumMobile.sp,
            lineHeight = lineHeightBodyMediumMobile.sp,
            letterSpacing = letterSpacingBodyMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyMediumMobile.sp,
            lineHeight = lineHeightBodyMediumMobile.sp,
            letterSpacing = letterSpacingBodyMediumMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodyMediumTablet.sp,
            lineHeight = lineHeightBodyMediumTablet.sp,
            letterSpacing = letterSpacingBodyMediumTablet.sp
        ),
    ),
    bodyStrongSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodySmallMobile.sp,
            lineHeight = lineHeightBodySmallMobile.sp,
            letterSpacing = letterSpacingBodySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodySmallMobile.sp,
            lineHeight = lineHeightBodySmallMobile.sp,
            letterSpacing = letterSpacingBodySmallMobile.sp
        ),
        TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeBodySmallTablet.sp,
            lineHeight = lineHeightBodySmallTablet.sp,
            letterSpacing = letterSpacingBodySmallTablet.sp
        ),
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

@Stable
fun OudsTypography.fromToken(token: OudsTypographyKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): TextStyle {
    val typography: Any = when (token) {
        OudsTypographyKeyToken.DisplayLarge -> displayLarge
        OudsTypographyKeyToken.DisplayMedium -> displayMedium
        OudsTypographyKeyToken.DisplaySmall -> displaySmall
        OudsTypographyKeyToken.HeadingExtraLarge -> headingExtraLarge
        OudsTypographyKeyToken.HeadingLarge -> headingLarge
        OudsTypographyKeyToken.HeadingMedium -> headingMedium
        OudsTypographyKeyToken.HeadingSmall -> headingSmall
        OudsTypographyKeyToken.BodyDefaultLarge -> bodyDefaultLarge
        OudsTypographyKeyToken.BodyDefaultMedium -> bodyDefaultMedium
        OudsTypographyKeyToken.BodyDefaultSmall -> bodyDefaultSmall
        OudsTypographyKeyToken.BodyStrongLarge -> bodyStrongLarge
        OudsTypographyKeyToken.BodyStrongMedium -> bodyStrongMedium
        OudsTypographyKeyToken.BodyStrongSmall -> bodyStrongSmall
        OudsTypographyKeyToken.LabelDefaultExtraLarge -> labelDefaultExtraLarge
        OudsTypographyKeyToken.LabelDefaultLarge -> labelDefaultLarge
        OudsTypographyKeyToken.LabelDefaultMedium -> labelDefaultMedium
        OudsTypographyKeyToken.LabelDefaultSmall -> labelDefaultSmall
        OudsTypographyKeyToken.LabelStrongExtraLarge -> labelStrongExtraLarge
        OudsTypographyKeyToken.LabelStrongLarge -> labelStrongLarge
        OudsTypographyKeyToken.LabelStrongMedium -> labelStrongMedium
        OudsTypographyKeyToken.LabelStrongSmall -> labelStrongSmall
    }

    return if (typography is OudsAdaptiveTokenValue<*>) {
        typography.getValue(adaptiveWindowType)
    } else {
        typography
    } as TextStyle
}

/**
 * Converts an OUDS typography token to the local typography value provided by the theme.
 * Note that the typography value returned varies depending on the window size.
 */
val OudsTypographyKeyToken.value: TextStyle
    @Composable
    get() = OudsTheme.typography.fromToken(this, OudsAdaptiveWindowType.fromWindowWidth(currentWindowWidth()))
