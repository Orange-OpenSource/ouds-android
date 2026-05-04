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
 * An annotated string for error messages in OUDS components.
 *
 * This class supports strong (bold/emphasized) text formatting to highlight critical
 * information in error messages. It is used by components like [com.orange.ouds.core.component.OudsTextInput],
 * [com.orange.ouds.core.component.OudsTextArea], [com.orange.ouds.core.component.OudsPasswordInput], [com.orange.ouds.core.component.OudsPinCodeInput], [com.orange.ouds.core.component.OudsCheckboxItem],
 * [com.orange.ouds.core.component.OudsRadioButtonItem], and [com.orange.ouds.core.component.OudsSwitchItem] through the [com.orange.ouds.core.component.common.OudsError] class.
 *
 * Use [buildOudsAnnotatedErrorMessage] to create instances:
 * ```
 * val errorMessage = buildOudsAnnotatedErrorMessage {
 *     append("This field ")
 *     withStrong { append("cannot") }
 *     append(" be empty")
 * }
 *
 * OudsTextInput(
 *     textFieldState = rememberTextFieldState(),
 *     label = "Email",
 *     error = OudsError(annotatedMessage = errorMessage)
 * )
 * ```
 */
class OudsAnnotatedErrorMessage internal constructor(annotatedString: AnnotatedString) : OudsAnnotatedString<OudsAnnotatedErrorMessage>(annotatedString) {

    /**
     * Builder for creating [OudsAnnotatedErrorMessage] with strong text formatting.
     *
     * Supports strong text annotation through [addStrong] and [pushStrong] methods,
     * or more conveniently through the [withStrong] DSL helper.
     *
     * @param capacity Initial capacity for the underlying string builder. Defaults to 16.
     */
    class Builder(capacity: Int = 16) : OudsAnnotatedString.Builder<OudsAnnotatedErrorMessage>(capacity, OudsAnnotatedErrorMessage::class.java),
        StrongBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated error message.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedErrorMessage) : this() {
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
    }
}

/**
 * Creates an [OudsAnnotatedErrorMessage] using a builder DSL.
 *
 * Example:
 * ```
 * val errorMessage = buildOudsAnnotatedErrorMessage {
 *     append("The code you entered is ")
 *     withStrong { append("incorrect") }
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated error message.
 */
fun buildOudsAnnotatedErrorMessage(builder: (OudsAnnotatedErrorMessage.Builder).() -> Unit): OudsAnnotatedErrorMessage {
    return buildOudsAnnotatedString<OudsAnnotatedErrorMessage, OudsAnnotatedErrorMessage.Builder>(builder)
}
