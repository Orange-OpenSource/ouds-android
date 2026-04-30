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

import androidx.compose.ui.text.AnnotatedString

/**
 * An annotated string for helper text in text input components.
 *
 * This class supports strong (bold/emphasized) text formatting to highlight important
 * information in helper text. It is used by components like [com.orange.ouds.core.component.OudsTextInput], [com.orange.ouds.core.component.OudsTextArea],
 * [com.orange.ouds.core.component.OudsPasswordInput], and [com.orange.ouds.core.component.OudsPinCodeInput].
 *
 * Use [buildOudsAnnotatedHelperText] to create instances:
 * ```
 * val helperText = buildOudsAnnotatedHelperText {
 *     append("Password must be ")
 *     withStrong { append("at least 8 characters") }
 *     append(" long")
 * }
 *
 * OudsTextInput(
 *     textFieldState = rememberTextFieldState(),
 *     label = "Password",
 *     helperText = helperText
 * )
 * ```
 */
class OudsAnnotatedHelperText internal constructor(annotatedString: AnnotatedString) : OudsAnnotatedString<OudsAnnotatedHelperText>(annotatedString) {

    /**
     * Builder for creating [OudsAnnotatedHelperText] with strong text formatting.
     *
     * Supports strong text annotation through [addStrong] and [pushStrong] methods,
     * or more conveniently through the [withStrong] DSL helper.
     *
     * @param capacity Initial capacity for the underlying string builder. Defaults to 16.
     */
    class Builder(capacity: Int = 16) : OudsAnnotatedString.Builder<OudsAnnotatedHelperText>(capacity, OudsAnnotatedHelperText::class.java), StrongBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated helper text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedHelperText) : this() {
            append(text)
        }

        override fun addStrong(start: Int, end: Int) = addStrongImpl(start, end)

        override fun pushStrong(): Int = pushStrongImpl()
    }
}

/**
 * Creates an [OudsAnnotatedHelperText] using a builder DSL.
 *
 * Example:
 * ```
 * val helperText = buildOudsAnnotatedHelperText {
 *     append("Description must be ")
 *     withStrong { append("at least 20 characters") }
 *     append(" long")
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated helper text.
 */
fun buildOudsAnnotatedHelperText(builder: (OudsAnnotatedHelperText.Builder).() -> Unit): OudsAnnotatedHelperText {
    return buildOudsAnnotatedString<OudsAnnotatedHelperText, OudsAnnotatedHelperText.Builder>(builder)
}
