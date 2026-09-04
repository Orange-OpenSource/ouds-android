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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract

// TODO Add design guideline link when available
/**
 * Heading styles are used to structure content and define the hierarchy of information within an interface.
 * Available in multiple sizes, they help users quickly understand the organization of a page or section.
 * Their size automatically adjusts across breakpoints to ensure optimal readability on all devices.
 * Headings serve as the primary entry point for visual navigation.
 *
 * An overload accepting annotated string is available for rich text formatting.
 *
 * > Design name: Heading
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed.
 * @param modifier [Modifier] applied to the heading text.
 * @param size Size of the heading text.
 * @param color Color of the heading text.
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
 * @sample com.orange.ouds.core.component.samples.OudsHeadingTextSample
 * @sample com.orange.ouds.core.component.samples.OudsHeadingTextLargeWithMarkerSample
 * @sample com.orange.ouds.core.component.samples.OudsHeadingTextLargeWithoutMarkerSample
 */
@Composable
fun OudsHeadingText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsHeadingTextSize = OudsHeadingTextDefaults.Size,
    color: Color = OudsHeadingTextDefaults.Color,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    OudsHeadingText(
        text = text,
        annotatedText = null,
        modifier = modifier,
        size = size,
        color = color,
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
 * Heading styles are used to structure content and define the hierarchy of information within an interface.
 * Available in multiple sizes, they help users quickly understand the organization of a page or section.
 * Their size automatically adjusts across breakpoints to ensure optimal readability on all devices.
 * Headings serve as the primary entry point for visual navigation.
 *
 * An overload accepting plain text is available for simple text without formatting.
 *
 * > Design name: Heading
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed. Note: Use rich text in compliance with guidelines and accessibility criteria.
 * @param modifier [Modifier] applied to the heading text.
 * @param size Size of the heading text.
 * @param color Color of the heading text.
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
 * @sample com.orange.ouds.core.component.samples.OudsHeadingTextWithAnnotatedTextSample
 */
@Composable
fun OudsHeadingText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    size: OudsHeadingTextSize = OudsHeadingTextDefaults.Size,
    color: Color = OudsHeadingTextDefaults.Color,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    OudsHeadingText(
        text = null,
        annotatedText = text,
        modifier = modifier,
        size = size,
        color = color,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

@Composable
private fun OudsHeadingText(
    text: String?,
    annotatedText: AnnotatedString?,
    modifier: Modifier = Modifier,
    size: OudsHeadingTextSize = OudsHeadingTextDefaults.Size,
    color: Color = OudsHeadingTextDefaults.Color,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    Column(modifier = modifier.widthIn(max = size.maxWidth)) {
        if (!annotatedText.isNullOrBlank()) {
            Text(
                text = annotatedText,
                color = color,
                style = size.textStyle,
                textAlign = textAlign,
                lineHeight = lineHeight,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
                onTextLayout = onTextLayout.orElse { {} }
            )
        } else {
            Text(
                text = text.orEmpty(),
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

        with(OudsTheme.components.typography) {
            // Marker is only displayed if the theme allows marker for heading large texts AND if the marker parameter for the component is set to `true`.
            if (size is OudsHeadingTextSize.Large && headingLargeMarker && size.marker) {
                OudsTheme.drawableResources.other.headingTextMarker?.let { resId ->
                    Icon(
                        modifier = Modifier.padding(top = space.paddingBlock.topHeadingLargeMarker, bottom = space.paddingBlock.bottomHeadingLargeMarker),
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        tint = OudsTheme.colorScheme.content.brandPrimary
                    )
                }
            }
        }
    }
}

/**
 * Default values for [OudsHeadingText].
 */
object OudsHeadingTextDefaults {

    /**
     * Default size of an [OudsHeadingText].
     */
    val Size = OudsHeadingTextSize.Large()

    /**
     * Default color of an [OudsHeadingText].
     */
    val Color
        @Composable
        get() = OudsTheme.colorScheme.content.default
}

/**
 * Represents the size of an [OudsHeadingText].
 */
sealed interface OudsHeadingTextSize {

    /**
     * The highest level of heading hierarchy, used to introduce major sections and establish clear content structure.
     * It serves as the primary navigational anchor within a page or experience.
     */
    object ExtraLarge : OudsHeadingTextSize

    /**
     * A high-emphasis heading used for important sections and content groupings.
     * It helps organize information while maintaining strong visual hierarchy.
     *
     * @param marker Controls the brand-colored marker display below the heading large text.
     * It enhances its visual emphasis and reinforce
     *   information hierarchy. This optional decorative element helps highlight important sections and improve content scanability.
     *   Use it selectively to maintain its impact and avoid visual clutter.
     * Note: If the current theme doesn't allow marker for heading large texts, this parameter is ignored.
     */
    class Large(val marker: Boolean = true) : OudsHeadingTextSize

    /**
     * A versatile heading size suitable for standard section titles and subsections.
     * It provides a clear hierarchy while preserving content balance.
     */
    object Medium : OudsHeadingTextSize

    /**
     * The most compact heading size, designed for minor sections and localized content organization.
     * Use it where a heading is required within limited space.
     */
    object Small : OudsHeadingTextSize

    val textStyle: TextStyle
        @Composable
        get() = with(OudsTheme.typography.heading) {
            when (this@OudsHeadingTextSize) {
                is ExtraLarge -> extraLarge
                is Large -> large
                is Medium -> medium
                is Small -> small
            }
        }

    val maxWidth: Dp
        @Composable
        get() = with(OudsTheme.sizes.maxWidth.heading) {
            when (this@OudsHeadingTextSize) {
                is ExtraLarge -> extraLarge
                is Large -> large
                is Medium -> medium
                is Small -> small
            }
        }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsHeadingText() {
    PreviewOudsHeadingText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsHeadingText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    Column(verticalArrangement = Arrangement.spacedBy(PreviewPaddingDefault)) {
        listOf(
            OudsHeadingTextSize.ExtraLarge,
            OudsHeadingTextSize.Large(),
            OudsHeadingTextSize.Medium,
            OudsHeadingTextSize.Small
        ).forEach { size ->
            OudsHeadingText("Heading", size = size)
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsHeadingTextWithAnnotatedText() {
    PreviewOudsHeadingTextWithAnnotatedText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsHeadingTextWithAnnotatedText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsHeadingText(
        text = buildAnnotatedString {
            append("Heading with ")
            withStyle(SpanStyle(color = OudsTheme.colorScheme.content.brandPrimary)) { append("colored text") }
            append(" and ")
            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append("normal text") }
        },
        size = OudsHeadingTextSize.Large(marker = false)
    )
}