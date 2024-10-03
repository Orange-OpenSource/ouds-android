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


fun OudsFontSemanticTokens.getTypography() = OudsTypography(
    displayLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplayLarge.sp,
            lineHeight = lineHeightMobileDisplayLarge.sp,
            letterSpacing = letterSpacingMobileDisplayLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplayLarge.sp,
            lineHeight = lineHeightMobileDisplayLarge.sp,
            letterSpacing = letterSpacingMobileDisplayLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeTabletDisplayLarge.sp,
            lineHeight = lineHeightTabletDisplayLarge.sp,
            letterSpacing = letterSpacingTabletDisplayLarge.sp
        ),
    ),
    displayMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplayMedium.sp,
            lineHeight = lineHeightMobileDisplayMedium.sp,
            letterSpacing = letterSpacingMobileDisplayMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplayMedium.sp,
            lineHeight = lineHeightMobileDisplayMedium.sp,
            letterSpacing = letterSpacingMobileDisplayMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeTabletDisplayMedium.sp,
            lineHeight = lineHeightTabletDisplayMedium.sp,
            letterSpacing = letterSpacingTabletDisplayMedium.sp
        ),
    ),
    displaySmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplaySmall.sp,
            lineHeight = lineHeightMobileDisplaySmall.sp,
            letterSpacing = letterSpacingMobileDisplaySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeMobileDisplaySmall.sp,
            lineHeight = lineHeightMobileDisplaySmall.sp,
            letterSpacing = letterSpacingMobileDisplaySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightDisplay),
            fontSize = sizeTabletDisplaySmall.sp,
            lineHeight = lineHeightTabletDisplaySmall.sp,
            letterSpacing = letterSpacingTabletDisplaySmall.sp
        ),
    ),
    headingExtraLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingXLarge.sp,
            lineHeight = lineHeightMobileHeadingXLarge.sp,
            letterSpacing = letterSpacingMobileHeadingXLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingXLarge.sp,
            lineHeight = lineHeightMobileHeadingXLarge.sp,
            letterSpacing = letterSpacingMobileHeadingXLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeTabletHeadingXLarge.sp,
            lineHeight = lineHeightTabletHeadingXLarge.sp,
            letterSpacing = letterSpacingTabletHeadingXLarge.sp
        ),
    ),
    headingLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingLarge.sp,
            lineHeight = lineHeightMobileHeadingLarge.sp,
            letterSpacing = letterSpacingMobileHeadingLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingLarge.sp,
            lineHeight = lineHeightMobileHeadingLarge.sp,
            letterSpacing = letterSpacingMobileHeadingLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeTabletHeadingLarge.sp,
            lineHeight = lineHeightTabletHeadingLarge.sp,
            letterSpacing = letterSpacingTabletHeadingLarge.sp
        ),
    ),
    headingMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingMedium.sp,
            lineHeight = lineHeightMobileHeadingMedium.sp,
            letterSpacing = letterSpacingMobileHeadingMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingMedium.sp,
            lineHeight = lineHeightMobileHeadingMedium.sp,
            letterSpacing = letterSpacingMobileHeadingMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeTabletHeadingMedium.sp,
            lineHeight = lineHeightTabletHeadingMedium.sp,
            letterSpacing = letterSpacingTabletHeadingMedium.sp
        ),
    ),
    headingSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingSmall.sp,
            lineHeight = lineHeightMobileHeadingSmall.sp,
            letterSpacing = letterSpacingMobileHeadingSmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeMobileHeadingSmall.sp,
            lineHeight = lineHeightMobileHeadingSmall.sp,
            letterSpacing = letterSpacingMobileHeadingSmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightHeading),
            fontSize = sizeTabletHeadingSmall.sp,
            lineHeight = lineHeightTabletHeadingSmall.sp,
            letterSpacing = letterSpacingTabletHeadingSmall.sp
        ),
    ),
    bodyDefaultLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodyLarge.sp,
            lineHeight = lineHeightMobileBodyLarge.sp,
            letterSpacing = letterSpacingMobileBodyLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodyLarge.sp,
            lineHeight = lineHeightMobileBodyLarge.sp,
            letterSpacing = letterSpacingMobileBodyLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeTabletBodyLarge.sp,
            lineHeight = lineHeightTabletBodyLarge.sp,
            letterSpacing = letterSpacingTabletBodyLarge.sp
        ),
    ),
    bodyDefaultMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodyMedium.sp,
            lineHeight = lineHeightMobileBodyMedium.sp,
            letterSpacing = letterSpacingMobileBodyMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodyMedium.sp,
            lineHeight = lineHeightMobileBodyMedium.sp,
            letterSpacing = letterSpacingMobileBodyMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeTabletBodyMedium.sp,
            lineHeight = lineHeightTabletBodyMedium.sp,
            letterSpacing = letterSpacingTabletBodyMedium.sp
        ),
    ),
    bodyDefaultSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodySmall.sp,
            lineHeight = lineHeightMobileBodySmall.sp,
            letterSpacing = letterSpacingMobileBodySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeMobileBodySmall.sp,
            lineHeight = lineHeightMobileBodySmall.sp,
            letterSpacing = letterSpacingMobileBodySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyDefault),
            fontSize = sizeTabletBodySmall.sp,
            lineHeight = lineHeightTabletBodySmall.sp,
            letterSpacing = letterSpacingTabletBodySmall.sp
        ),
    ),
    bodyStrongLarge = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodyLarge.sp,
            lineHeight = lineHeightMobileBodyLarge.sp,
            letterSpacing = letterSpacingMobileBodyLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodyLarge.sp,
            lineHeight = lineHeightMobileBodyLarge.sp,
            letterSpacing = letterSpacingMobileBodyLarge.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeTabletBodyLarge.sp,
            lineHeight = lineHeightTabletBodyLarge.sp,
            letterSpacing = letterSpacingTabletBodyLarge.sp
        ),
    ),
    bodyStrongMedium = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodyMedium.sp,
            lineHeight = lineHeightMobileBodyMedium.sp,
            letterSpacing = letterSpacingMobileBodyMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodyMedium.sp,
            lineHeight = lineHeightMobileBodyMedium.sp,
            letterSpacing = letterSpacingMobileBodyMedium.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeTabletBodyMedium.sp,
            lineHeight = lineHeightTabletBodyMedium.sp,
            letterSpacing = letterSpacingTabletBodyMedium.sp
        ),
    ),
    bodyStrongSmall = OudsAdaptiveTokenValue(
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodySmall.sp,
            lineHeight = lineHeightMobileBodySmall.sp,
            letterSpacing = letterSpacingMobileBodySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeMobileBodySmall.sp,
            lineHeight = lineHeightMobileBodySmall.sp,
            letterSpacing = letterSpacingMobileBodySmall.sp
        ),
        TextStyle(
            fontWeight = FontWeight(weightBodyStrong),
            fontSize = sizeTabletBodySmall.sp,
            lineHeight = lineHeightTabletBodySmall.sp,
            letterSpacing = letterSpacingTabletBodySmall.sp
        ),
    ),
    labelDefaultExtraLarge = TextStyle(
        fontWeight = FontWeight(weightLabelDefault),
        fontSize = sizeLabelXLarge.sp,
        lineHeight = lineHeightLabelXLarge.sp,
        letterSpacing = letterSpacingLabelXLarge.sp
    ),
    labelDefaultLarge = TextStyle(
        fontWeight = FontWeight(weightLabelDefault),
        fontSize = sizeLabelLarge.sp,
        lineHeight = lineHeightLabelLarge.sp,
        letterSpacing = letterSpacingLabelLarge.sp
    ),
    labelDefaultMedium = TextStyle(
        fontWeight = FontWeight(weightLabelDefault),
        fontSize = sizeLabelMedium.sp,
        lineHeight = lineHeightLabelMedium.sp,
        letterSpacing = letterSpacingLabelMedium.sp
    ),
    labelDefaultSmall = TextStyle(
        fontWeight = FontWeight(weightLabelDefault),
        fontSize = sizeLabelSmall.sp,
        lineHeight = lineHeightLabelSmall.sp,
        letterSpacing = letterSpacingLabelSmall.sp
    ),
    labelStrongExtraLarge = TextStyle(
        fontWeight = FontWeight(weightLabelStrong),
        fontSize = sizeLabelXLarge.sp,
        lineHeight = lineHeightLabelXLarge.sp,
        letterSpacing = letterSpacingLabelXLarge.sp
    ),
    labelStrongLarge = TextStyle(
        fontWeight = FontWeight(weightLabelStrong),
        fontSize = sizeLabelLarge.sp,
        lineHeight = lineHeightLabelLarge.sp,
        letterSpacing = letterSpacingLabelLarge.sp
    ),
    labelStrongMedium = TextStyle(
        fontWeight = FontWeight(weightLabelStrong),
        fontSize = sizeLabelMedium.sp,
        lineHeight = lineHeightLabelMedium.sp,
        letterSpacing = letterSpacingLabelMedium.sp
    ),
    labelStrongSmall = TextStyle(
        fontWeight = FontWeight(weightLabelStrong),
        fontSize = sizeLabelSmall.sp,
        lineHeight = lineHeightLabelSmall.sp,
        letterSpacing = letterSpacingLabelSmall.sp
    ),
)

@Stable
fun OudsTypography.fromToken(token: OudsTypographyKeyToken, adaptiveWindowType: OudsAdaptiveWindowType): TextStyle {
    val typography = when (token) {
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
