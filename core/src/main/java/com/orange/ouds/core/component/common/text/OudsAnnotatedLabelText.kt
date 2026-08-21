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

package com.orange.ouds.core.component.common.text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString

/**
 * An annotated string for [com.orange.ouds.core.component.OudsLabelText] text in OUDS components.
 *
 * This class supports both colored text, strong (bold/emphasized) text and clickable links to create rich, interactive label texts.
 *
 * Use [buildOudsAnnotatedLabelText] to create instances:
 * ```
 * val highlightColor = OudsTheme.colorScheme.content.brandPrimary
 * OudsLabelText(
 *     text = buildOudsAnnotatedLabelText {
 *         withStrong {
 *             withColor(color = highlightColor) { append("Important update") }
 *         }
 *         append(", see details ")
 *         withLink(link = OudsLinkAnnotation.Url("https://example.com/details")) { append("here") }
 *         append(".")
 *     }
 * )
 * ```
 */
class OudsAnnotatedLabelText internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedLabelText>(annotatedString) {

    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedLabelText>(capacity, OudsAnnotatedLabelText::class.java),
        StrongBuilder, LinkBuilder, ColorBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated display text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedLabelText) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: AnnotatedString) : this() {
            append(text)
        }

        override fun addStrong(start: Int, end: Int) = addStrongImpl(start, end)
        override fun pushStrong(): Int = pushStrongImpl()

        override fun addLink(url: OudsLinkAnnotation.Url, start: Int, end: Int) = addLinkImpl(url, start, end)
        override fun addLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) = addLinkImpl(clickable, start, end)
        override fun pushLink(link: OudsLinkAnnotation): Int = pushLinkImpl(link)

        override fun addColor(color: Color, start: Int, end: Int) = addColorImpl(color, start, end)
        override fun pushColor(color: Color): Int = pushColorImpl(color)
    }
}

/**
 * Creates an [OudsAnnotatedLabelText] using a builder DSL.
 *
 * Example:
 * ```
 * buildOudsAnnotatedLabelText {
 *     withStrong {
 *         withColor(color = highlightColor) { append("Important update") }
 *     }
 *     append(", see details ")
 *     withLink(link = OudsLinkAnnotation.Url("https://example.com/details")) { append("here") }
 *     append(".")
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated display text.
 */
fun buildOudsAnnotatedLabelText(builder: (OudsAnnotatedLabelText.Builder).() -> Unit): OudsAnnotatedLabelText {
    return buildOudsAnnotatedString<OudsAnnotatedLabelText, OudsAnnotatedLabelText.Builder>(builder)
}