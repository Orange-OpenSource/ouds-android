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

package com.orange.ouds.core.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.OudsPreviewLightDark
import com.orange.ouds.core.utilities.PreviewEnumEntries
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

// TODO Add design guideline link when available
/**
 * Body styles are designed for everyday text content such as paragraphs, descriptions, and informational messages. They prioritize readability and provide
 * a comfortable reading experience across all screen sizes. Multiple size options allow content importance to be expressed while maintaining consistency.
 * Their typography automatically scales across breakpoints to support responsive layouts.
 *
 * An overload accepting annotated string is available for rich text formatting.
 *
 * > Design name: Body
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed.
 * @param modifier [Modifier] applied to the body text.
 * @param size Size of the body text.
 * @param weight Weight of the body text.
 * @param color Color of the body text.
 * @param textAlign Alignment of the text within the lines of the paragraph. See
 *   [TextStyle.textAlign].
 * @param lineHeight Line height for the [Paragraph] in [TextUnit] unit, e.g. SP or EM. See
 *   [TextStyle.lineHeight].
 * @param overflow How visual overflow should be handled.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 *   text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 *   [overflow] and TextAlign may have unexpected effects.
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if necessary.
 *   If the text exceeds the given number of lines, it will be truncated according to [overflow] and
 *   [softWrap]. It is required that 1 <= [minLines] <= [maxLines].
 * @param minLines The minimum height in terms of minimum number of visible lines. It is required
 *   that 1 <= [minLines] <= [maxLines].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 *   [TextLayoutResult] object that callback provides contains paragraph information, size of the
 *   text, baselines and other details. The callback can be used to add additional decoration or
 *   functionality to the text. For example, to draw selection around the text.
 *
 * @sample com.orange.ouds.core.component.samples.OudsBodyTextSample
 */
@Composable
fun OudsBodyText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsBodyTextSize = OudsBodyTextDefaults.Size,
    weight: OudsTextWeight = OudsBodyTextDefaults.Weight,
    color: Color = OudsBodyTextDefaults.Color,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    Text(
        modifier = modifier.widthIn(max = size.maxWidth),
        text = text,
        color = color,
        style = textStyle(size = size, weight = weight),
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

// TODO Add design guideline link when available
/**
 * Body styles are designed for everyday text content such as paragraphs, descriptions, and informational messages. They prioritize readability and provide
 * a comfortable reading experience across all screen sizes. Multiple size options allow content importance to be expressed while maintaining consistency.
 * Their typography automatically scales across breakpoints to support responsive layouts.
 *
 * An overload accepting plain text is available for simple text without formatting.
 *
 * > Design name: Body
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed. Note: Use rich text in compliance with guidelines and accessibility criteria.
 * @param modifier [Modifier] applied to the body text.
 * @param size Size of the body text.
 * @param weight Weight of the body text.
 * @param color Color of the body text.
 * @param textAlign Alignment of the text within the lines of the paragraph. See
 *   [TextStyle.textAlign].
 * @param lineHeight Line height for the [Paragraph] in [TextUnit] unit, e.g. SP or EM. See
 *   [TextStyle.lineHeight].
 * @param overflow How visual overflow should be handled.
 * @param softWrap Whether the text should break at soft line breaks. If false, the glyphs in the
 *   text will be positioned as if there was unlimited horizontal space. If [softWrap] is false,
 *   [overflow] and TextAlign may have unexpected effects.
 * @param maxLines An optional maximum number of lines for the text to span, wrapping if necessary.
 *   If the text exceeds the given number of lines, it will be truncated according to [overflow] and
 *   [softWrap]. It is required that 1 <= [minLines] <= [maxLines].
 * @param minLines The minimum height in terms of minimum number of visible lines. It is required
 *   that 1 <= [minLines] <= [maxLines].
 * @param onTextLayout Callback that is executed when a new text layout is calculated. A
 *   [TextLayoutResult] object that callback provides contains paragraph information, size of the
 *   text, baselines and other details. The callback can be used to add additional decoration or
 *   functionality to the text. For example, to draw selection around the text.
 *
 * @sample com.orange.ouds.core.component.samples.OudsBodyWithAnnotatedTextSample
 */
@Composable
fun OudsBodyText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    size: OudsBodyTextSize = OudsBodyTextDefaults.Size,
    weight: OudsTextWeight = OudsBodyTextDefaults.Weight,
    color: Color = OudsBodyTextDefaults.Color,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        modifier = modifier.widthIn(max = size.maxWidth),
        text = text,
        color = color,
        style = textStyle(size = size, weight = weight),
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

/**
 * Default values for [OudsBodyText].
 */
object OudsBodyTextDefaults {

    /**
     * Default size of an [OudsBodyText].
     */
    val Size = OudsBodyTextSize.Large

    /**
     * Default weight of an [OudsBodyText].
     */
    val Weight = OudsTextWeight.Default

    /**
     * Default color of an [OudsBodyText].
     */
    val Color
        @Composable
        get() = OudsTheme.colorScheme.content.default
}

/**
 * Represents the size of an [OudsBodyText].
 */
enum class OudsBodyTextSize {
    /**
     * The most prominent body text size, ideal for introductory paragraphs, highlighted content, or situations where enhanced readability is desired.
     */
    Large,

    /**
     * The default body text size, providing an optimal balance between readability, information density, and visual hierarchy across most use cases.
     */
    Medium,

    /**
     * A compact body text size intended for supporting information, secondary content, and dense layouts where space efficiency is important.
     */
    Small;

    val maxWidth: Dp
        @Composable
        get() = with(OudsTheme.sizes.maxWidth.body) {
            when (this@OudsBodyTextSize) {
                Large -> large
                Medium -> medium
                Small -> small
            }
        }
}

/**
 * Represents the weight of an [OudsBodyText] or an [OudsLabelText].
 */
enum class OudsTextWeight {
    /**
     * The default weight provides the standard level of typographic emphasis for Body and Label styles. It is designed for optimal readability
     * and neutral hierarchy, making it the baseline for most interface content. Use it whenever no additional emphasis is required.
     */
    Default,

    /**
     * Moderate weight increases visual presence while remaining balanced and readable. It is used to reinforce hierarchy within Body
     * and Label typography without introducing strong emphasis. Suitable for highlighting important information or improving scanability.
     */
    Moderate,

    /**
     * Strong weight provides the highest level of emphasis for Body and Label styles. It is intended for critical information, key actions,
     * or elements that must stand out within dense content. Use it sparingly to preserve its impact and avoid visual overload.
     */
    Strong
}

@Composable
private fun textStyle(size: OudsBodyTextSize, weight: OudsTextWeight): TextStyle = with(OudsTheme.typography.body) {
    when (size) {
        OudsBodyTextSize.Large -> when (weight) {
            OudsTextWeight.Default -> large.default
            OudsTextWeight.Moderate -> large.moderate
            OudsTextWeight.Strong -> large.strong
        }
        OudsBodyTextSize.Medium -> when (weight) {
            OudsTextWeight.Default -> medium.default
            OudsTextWeight.Moderate -> medium.moderate
            OudsTextWeight.Strong -> medium.strong
        }
        OudsBodyTextSize.Small -> when (weight) {
            OudsTextWeight.Default -> small.default
            OudsTextWeight.Moderate -> small.moderate
            OudsTextWeight.Strong -> small.strong
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBodyText() {
    PreviewOudsBodyText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsBodyText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsTextWeight, OudsBodyTextSize> { weight, size ->
        OudsBodyText("Body", size = size, weight = weight)
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsBodyTextWithAnnotatedText() {
    PreviewOudsBodyTextWithAnnotatedText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsBodyTextWithAnnotatedText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsBodyText(
        text = buildAnnotatedString {
            append("Body with ")
            withStyle(SpanStyle(color = OudsTheme.colorScheme.content.brandPrimary)) { append("colored text") }
            append(" and ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold text") }
        },
        size = OudsBodyTextSize.Large
    )
}