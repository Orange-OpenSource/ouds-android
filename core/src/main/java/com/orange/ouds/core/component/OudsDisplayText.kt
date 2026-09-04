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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.orange.ouds.core.utilities.PreviewPaddingDefault
import com.orange.ouds.core.utilities.getPreviewTheme
import com.orange.ouds.theme.OudsThemeContract

// TODO Add design guideline link when available
/**
 * Display styles are intended for high-impact content such as landing pages, marketing campaigns, and key messages. Their large type sizes help capture
 * attention and establish strong visual emphasis.
 * Variants automatically adapt across breakpoints to maintain a consistent visual hierarchy on every screen size.
 * Use them sparingly to preserve their impact and effectiveness.
 *
 * An overload accepting annotated string is available for rich text formatting.
 *
 * > Design name: Display
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed.
 * @param modifier [Modifier] applied to the display text.
 * @param size Size of the display text.
 * @param color Color of the display text.
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
 * @sample com.orange.ouds.core.component.samples.OudsDisplayTextSample
 */
@Composable
fun OudsDisplayText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsDisplayTextSize = OudsDisplayTextDefaults.Size,
    color: Color = OudsDisplayTextDefaults.Color,
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
        style = size.textStyle,
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
 * Display styles are intended for high-impact content such as landing pages, marketing campaigns, and key messages. Their large type sizes help capture
 * attention and establish strong visual emphasis.
 * Variants automatically adapt across breakpoints to maintain a consistent visual hierarchy on every screen size.
 * Use them sparingly to preserve their impact and effectiveness.
 *
 * An overload accepting annotated string is available for rich text formatting.
 *
 * > Design name: Display
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed. Note: Use rich text in compliance with guidelines and accessibility criteria.
 * @param modifier [Modifier] applied to the display text.
 * @param size Size of the display text.
 * @param color Color of the display text.
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
 * @sample com.orange.ouds.core.component.samples.OudsDisplayWithAnnotatedTextSample
 */
@Composable
fun OudsDisplayText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    size: OudsDisplayTextSize = OudsDisplayTextDefaults.Size,
    color: Color = OudsDisplayTextDefaults.Color,
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
        style = size.textStyle,
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
 * Default values for [OudsDisplayText].
 */
object OudsDisplayTextDefaults {

    /**
     * Default size of an [OudsDisplayText].
     */
    val Size = OudsDisplayTextSize.Large

    /**
     * Default color of an [OudsDisplayText].
     */
    val Color
        @Composable
        get() = OudsTheme.colorScheme.content.default
}

/**
 * Represents the size of an [OudsDisplayText].
 */
enum class OudsDisplayTextSize {
    /**
     * The largest Display size, intended for maximum visual impact. Use it for hero sections,
     * key messages, and high-priority content that requires immediate attention.
     */
    Large,

    /**
     * A prominent Display size that provides strong emphasis while maintaining a more balanced visual presence.
     * Suitable for major content areas and featured messages.
     */
    Medium,

    /**
     * The most compact Display size, offering visual impact in space-constrained layouts.
     * Use it when a display style is needed without overwhelming surrounding content.
     */
    Small;

    val textStyle: TextStyle
        @Composable
        get() = with(OudsTheme.typography.display) {
            when (this@OudsDisplayTextSize) {
                Large -> large
                Medium -> medium
                Small -> small
            }
        }

    val maxWidth: Dp
        @Composable
        get() = with(OudsTheme.sizes.maxWidth.display) {
            when (this@OudsDisplayTextSize) {
                Large -> large
                Medium -> medium
                Small -> small
            }
        }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsDisplayText() {
    PreviewOudsDisplayText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsDisplayText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Column(verticalArrangement = Arrangement.spacedBy(PreviewPaddingDefault)) {
        OudsDisplayTextSize.entries.forEach { size ->
            OudsDisplayText("Display", size = size)
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsDisplayTextWithAnnotatedText() {
    PreviewOudsDisplayTextWithAnnotatedText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsDisplayTextWithAnnotatedText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsDisplayText(
        text = buildAnnotatedString {
            append("Display with ")
            withStyle(SpanStyle(color = OudsTheme.colorScheme.content.brandPrimary)) { append("colored text") }
            append(" and ")
            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append("normal text") }
        },
        size = OudsDisplayTextSize.Small
    )
}