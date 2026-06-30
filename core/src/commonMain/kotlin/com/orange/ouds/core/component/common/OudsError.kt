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

package com.orange.ouds.core.component.common

import com.orange.ouds.core.component.common.text.OudsAnnotatedErrorMessage
import com.orange.ouds.foundation.extensions.orElse

/**
 * Represents an error for OUDS components.
 * 
 * This class supports both plain and annotated error messages.
 */
class OudsError private constructor(message: String?, annotatedMessage: OudsAnnotatedErrorMessage?) {

    /**
     * The plain text representation of the error message.
     *
     * If the error was created with an [OudsAnnotatedErrorMessage], this property contains
     * the text without formatting. If created with a plain string, this property contains
     * that string. Returns an empty string if no message was provided.
     */
    val message = message.orElse { annotatedMessage?.text }.orEmpty()

    /**
     * The annotated error message, or `null` if the error was created with plain text.
     *
     * When non-null, this contains the formatted error message with styling information such as
     * strong (bold/emphasized) text.
     */
    val annotatedMessage = annotatedMessage

    /**
     * Creates an error with a plain text message.
     *
     * ```
     * val error = OudsError("This field is required.")
     * ```
     *
     * @param message The plain text error message to display.
     */
    constructor(message: String) : this(message, null)

    /**
     * Creates an error with an annotated message.
     *
     * ```
     * val error = OudsError(
     *     annotatedMessage = buildOudsAnnotatedErrorMessage {
     *         append("Password must be ")
     *         withStrong { append("at least 8 characters") }
     *     }
     * )
     * ```
     *
     * @param annotatedMessage The annotated error message with rich text formatting.
     */
    constructor(annotatedMessage: OudsAnnotatedErrorMessage) : this(null, annotatedMessage)
}
