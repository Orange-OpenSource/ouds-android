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
 * An annotated string for [com.orange.ouds.core.component.OudsDisplayText] text in OUDS components.
 *
 * This class supports colored text formatting to highlight one or more important words in a display text.
 *
 * Use [buildOudsAnnotatedDisplayText] to create instances:
 * ```
 * OudsDisplayText(
 *     text = buildOudsAnnotatedDisplayText {
 *         append("This is ")
 *         withColor(OudsTheme.colorScheme.content.brandSecondary) {
 *         append("brand secondary text")
 *     }
 * )
 * ```
 */
class OudsAnnotatedDisplayText internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedDisplayText>(annotatedString) {

    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedDisplayText>(capacity, OudsAnnotatedDisplayText::class.java),
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
         * Creates a builder initialized with an existing annotated display text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedDisplayText) : this() {
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
 * Creates an [OudsAnnotatedDisplayText] using a builder DSL.
 *
 * Example:
 * ```
 * buildOudsAnnotatedDisplayText {
 *     append("This is ")
 *     withColor(OudsTheme.colorScheme.content.brandSecondary) {
 *         append("brand secondary text")
 *     }
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated display text.
 */
inline fun buildOudsAnnotatedDisplayText(builder: (OudsAnnotatedDisplayText.Builder).() -> Unit): OudsAnnotatedDisplayText {
    return buildOudsAnnotatedString<OudsAnnotatedDisplayText, OudsAnnotatedDisplayText.Builder>(builder)
}