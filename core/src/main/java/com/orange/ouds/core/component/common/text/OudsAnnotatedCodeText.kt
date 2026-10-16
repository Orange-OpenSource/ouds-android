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
 * An annotated string for [com.orange.ouds.core.component.OudsCodeText] text in OUDS components.
 *
 * This class supports colored text for syntax highlighting in code texts.
 *
 * Use [buildOudsAnnotatedCodeText] to create instances:
 * ```
 * val highlightColor = OudsTheme.colorScheme.content.brandPrimary
 * OudsCodeText(
 *     text = buildOudsAnnotatedCodeText {
 *         withColor(color = info) { append("val") }
 *         append(" state = ")
 *         withColor(color = positive) { append("rememberState") }
 *         append("()")
 *     }
 * )
 * ```
 */
class OudsAnnotatedCodeText internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedCodeText>(annotatedString) {

    class Builder(capacity: Int = 16) : OudsAnnotatedString.Builder<OudsAnnotatedCodeText>(capacity, OudsAnnotatedCodeText::class.java), ColorBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedCodeText) : this() {
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
 * Creates an [OudsAnnotatedCodeText] using a builder DSL.
 *
 * Example:
 * ```
 * buildOudsAnnotatedCodeText {
 *      withColor(color = info) { append("val") }
 *      append(" state = ")
 *      withColor(color = positive) { append("rememberState") }
 *      append("()")
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated code text.
 */
inline fun buildOudsAnnotatedCodeText(builder: (OudsAnnotatedCodeText.Builder).() -> Unit): OudsAnnotatedCodeText {
    return buildOudsAnnotatedString<OudsAnnotatedCodeText, OudsAnnotatedCodeText.Builder>(builder)
}