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
 * Label styles are intended for compact interface elements such as buttons, form fields, badges, and other small components.
 * Unlike other typography categories, they are not responsive and maintain a fixed size across all breakpoints. This ensures visual consistency and
 * predictable behavior within space-constrained UI elements. Labels should be preferred whenever content is displayed within small components.
 *
 * An overload accepting annotated string is available for rich text formatting.
 *
 * > Design name: Label
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed.
 * @param modifier [Modifier] applied to the label text.
 * @param size Size of the label text.
 * @param weight Weight of the label text.
 * @param color Color of the label text.
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
 * @sample com.orange.ouds.core.component.samples.OudsLabelTextSample
 */
@Composable
fun OudsLabelText(
    text: String,
    modifier: Modifier = Modifier,
    size: OudsLabelTextSize = OudsLabelTextDefaults.Size,
    weight: OudsTextWeight = OudsLabelTextDefaults.Weight,
    color: Color = OudsLabelTextDefaults.Color,
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
 * Label styles are intended for compact interface elements such as buttons, form fields, badges, and other small components.
 * Unlike other typography categories, they are not responsive and maintain a fixed size across all breakpoints. This ensures visual consistency and
 * predictable behavior within space-constrained UI elements. Labels should be preferred whenever content is displayed within small components.
 *
 * An overload accepting plain text is available for simple text without formatting.
 *
 * > Design name: Label
 *
 * > Design version: 1.0.0
 *
 * @param text Text to be displayed. Note: Use rich text in compliance with guidelines and accessibility criteria.
 * @param modifier [Modifier] applied to the label text.
 * @param size Size of the label text.
 * @param weight Weight of the label text.
 * @param color Color of the label text.
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
 * @sample com.orange.ouds.core.component.samples.OudsLabelWithAnnotatedTextSample
 */
@Composable
fun OudsLabelText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    size: OudsLabelTextSize = OudsLabelTextDefaults.Size,
    weight: OudsTextWeight = OudsLabelTextDefaults.Weight,
    color: Color = OudsLabelTextDefaults.Color,
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
 * Default values for [OudsLabelText].
 */
object OudsLabelTextDefaults {

    /**
     * Default size of an [OudsLabelText].
     */
    val Size = OudsLabelTextSize.ExtraLarge

    /**
     * Default weight of an [OudsLabelText].
     */
    val Weight = OudsTextWeight.Default

    /**
     * Default color of an [OudsLabelText].
     */
    val Color
        @Composable
        get() = OudsTheme.colorScheme.content.default
}

/**
 * Represents the size of an [OudsLabelText].
 */
enum class OudsLabelTextSize {
    /**
     * The largest label size, intended for prominent UI controls and interface elements that require increased visibility and emphasis.
     */
    ExtraLarge,

    /**
     * A highly readable label size suitable for key interactive components and larger interface patterns.
     * It provides strong clarity while maintaining compactness.
     */
    Large,

    /**
     * The default label size, designed for most interface components and controls. It offers a balanced combination of readability and space efficiency.
     */
    Medium,

    /**
     * The most compact label size, optimized for dense interfaces and small UI components. Use it when space is limited while preserving legibility.
     */
    Small;

    val maxWidth: Dp
        @Composable
        get() = with(OudsTheme.sizes.maxWidth.label) {
            when (this@OudsLabelTextSize) {
                ExtraLarge -> extraLarge
                Large -> large
                Medium -> medium
                Small -> small
            }
        }
}

@Composable
private fun textStyle(size: OudsLabelTextSize, weight: OudsTextWeight): TextStyle = with(OudsTheme.typography.label) {
    when (size) {
        OudsLabelTextSize.ExtraLarge -> when (weight) {
            OudsTextWeight.Default -> extraLarge.default
            OudsTextWeight.Moderate -> extraLarge.moderate
            OudsTextWeight.Strong -> extraLarge.strong
        }
        OudsLabelTextSize.Large -> when (weight) {
            OudsTextWeight.Default -> large.default
            OudsTextWeight.Moderate -> large.moderate
            OudsTextWeight.Strong -> large.strong
        }
        OudsLabelTextSize.Medium -> when (weight) {
            OudsTextWeight.Default -> medium.default
            OudsTextWeight.Moderate -> medium.moderate
            OudsTextWeight.Strong -> medium.strong
        }
        OudsLabelTextSize.Small -> when (weight) {
            OudsTextWeight.Default -> small.default
            OudsTextWeight.Moderate -> small.moderate
            OudsTextWeight.Strong -> small.strong
        }
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLabelText() {
    PreviewOudsLabelText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsLabelText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    PreviewEnumEntries<OudsTextWeight, OudsLabelTextSize> { weight, size ->
        OudsLabelText("Label", size = size, weight = weight)
    }
}

@OudsPreviewLightDark
@Composable
@Suppress("PreviewShouldNotBeCalledRecursively")
private fun PreviewOudsLabelTextWithAnnotatedText() {
    PreviewOudsLabelTextWithAnnotatedText(theme = getPreviewTheme(), darkThemeEnabled = isSystemInDarkTheme())
}

@Composable
internal fun PreviewOudsLabelTextWithAnnotatedText(
    theme: OudsThemeContract,
    darkThemeEnabled: Boolean,
) = OudsPreview(theme = theme, darkThemeEnabled = darkThemeEnabled) {
    OudsLabelText(
        text = buildAnnotatedString {
            append("Label with ")
            withStyle(SpanStyle(color = OudsTheme.colorScheme.content.brandPrimary)) { append("colored text") }
            append(" and ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold text") }
        },
        size = OudsLabelTextSize.Large
    )
}