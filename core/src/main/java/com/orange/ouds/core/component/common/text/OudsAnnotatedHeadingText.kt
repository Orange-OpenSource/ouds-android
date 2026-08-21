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

class OudsAnnotatedHeadingText internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedHeadingText>(annotatedString) {

    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedHeadingText>(capacity, OudsAnnotatedHeadingText::class.java),
        ColorBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated heading text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedHeadingText) : this() {
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

        override fun addColor(color: Color, start: Int, end: Int) = addColorImpl(color, start, end)
        override fun pushColor(color: Color): Int = pushColorImpl(color)
    }
}

/**
 * Creates an [OudsAnnotatedHeadingText] using a builder DSL.
 *
 * Example:
 * ```
 * buildOudsAnnotatedHeadingText {
 *     append("This is ")
 *     withColor(OudsTheme.colorScheme.content.brandSecondary) {
 *         append("brand secondary text")
 *     }
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated heading text.
 */
fun buildOudsAnnotatedHeadingText(builder: (OudsAnnotatedHeadingText.Builder).() -> Unit): OudsAnnotatedHeadingText {
    return buildOudsAnnotatedString<OudsAnnotatedHeadingText, OudsAnnotatedHeadingText.Builder>(builder)
}