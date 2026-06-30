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
 * An annotated string for alert message descriptions.
 *
 * This class supports both strong (bold/emphasized) text and clickable links to create
 * rich, informative alert descriptions. It is used by [com.orange.ouds.core.component.OudsAlertMessage] component.
 *
 * Use [buildOudsAnnotatedAlertMessageDescription] to create instances:
 * ```
 * val description = buildOudsAnnotatedAlertMessageDescription {
 *     withStrong { append("Important:") }
 *     append(" Please read the ")
 *     withLink(OudsLinkAnnotation.Url("https://example.com/terms")) {
 *         append("terms and conditions")
 *     }
 *     append(" carefully before proceeding")
 * }
 *
 * OudsAlertMessage(
 *     label = "Before you continue",
 *     description = description,
 *     status = OudsAlertMessageStatus.Warning
 * )
 * ```
 */
class OudsAnnotatedAlertMessageDescription internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedAlertMessageDescription>(annotatedString) {

    /**
     * Builder for creating [OudsAnnotatedAlertMessageDescription] with strong text and link formatting.
     *
     * Supports both strong text and link annotations through [addStrong], [pushStrong],
     * [addLink], and [pushLink] methods, or more conveniently through the [withStrong]
     * and [withLink] DSL helpers.
     *
     * @param capacity Initial capacity for the underlying string builder. Defaults to 16.
     */
    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedAlertMessageDescription>(capacity, OudsAnnotatedAlertMessageDescription::class.java),
        StrongBuilder, LinkBuilder {

        /**
         * Creates a builder initialized with plain text.
         *
         * @param text The initial text content.
         */
        constructor(text: String) : this() {
            append(text)
        }

        /**
         * Creates a builder initialized with an existing annotated alert message description.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedAlertMessageDescription) : this() {
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
    }
}

/**
 * Creates an [OudsAnnotatedAlertMessageDescription] using a builder DSL.
 *
 * Example:
 * ```
 * val description = buildOudsAnnotatedAlertMessageDescription {
 *     withStrong { append("Note:") }
 *     append(" Your data will be encrypted and stored securely. ")
 *     append("For more details, see our ")
 *     withLink(OudsLinkAnnotation.Url("https://example.com/privacy")) {
 *         append("privacy policy")
 *     }
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated alert message description.
 */
fun buildOudsAnnotatedAlertMessageDescription(builder: (OudsAnnotatedAlertMessageDescription.Builder).() -> Unit): OudsAnnotatedAlertMessageDescription {
    return buildOudsAnnotatedString<OudsAnnotatedAlertMessageDescription, OudsAnnotatedAlertMessageDescription.Builder>(builder)
}
