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
 * An annotated string for bullet list items within alert messages.
 *
 * This class supports both strong (bold/emphasized) text and clickable links to create
 * rich, interactive bullet points in alert messages. It is used by [com.orange.ouds.core.component.OudsAlertMessage] component.
 *
 * Use [buildOudsAnnotatedAlertMessageBulletListLabel] to create instances:
 * ```
 * val bulletList = listOf(
 *     buildOudsAnnotatedAlertMessageBulletListLabel {
 *         withStrong { append("Data security:") }
 *         append(" Your information will be encrypted")
 *     },
 *     buildOudsAnnotatedAlertMessageBulletListLabel {
 *         append("For more information, visit our ")
 *         withLink(OudsLinkAnnotation.Url("https://help.example.com")) {
 *             append("help center")
 *         }
 *     }
 * )
 *
 * OudsAlertMessage(
 *     label = "Important information",
 *     bulletList = bulletList
 * )
 * ```
 */
class OudsAnnotatedAlertMessageBulletListLabel internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedAlertMessageBulletListLabel>(annotatedString) {

    /**
     * Builder for creating [OudsAnnotatedAlertMessageBulletListLabel] with strong text and link formatting.
     *
     * Supports both strong text and link annotations through [addStrong], [pushStrong],
     * [addLink], and [pushLink] methods, or more conveniently through the [withStrong]
     * and [withLink] DSL helpers.
     *
     * @param capacity Initial capacity for the underlying string builder. Defaults to 16.
     */
    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedAlertMessageBulletListLabel>(capacity, OudsAnnotatedAlertMessageBulletListLabel::class.java),
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
         * Creates a builder initialized with an existing annotated alert message bullet list.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedAlertMessageBulletListLabel) : this() {
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
 * Creates an [OudsAnnotatedAlertMessageBulletListLabel] using a builder DSL.
 *
 * Example:
 * ```
 * val bullet = buildOudsAnnotatedAlertMessageBulletListLabel {
 *     withStrong { append("Privacy policy:") }
 *     append(" We respect your privacy and will not share your data")
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated alert message bullet list label.
 */
fun buildOudsAnnotatedAlertMessageBulletListLabel(builder: (OudsAnnotatedAlertMessageBulletListLabel.Builder).() -> Unit): OudsAnnotatedAlertMessageBulletListLabel {
    return buildOudsAnnotatedString<OudsAnnotatedAlertMessageBulletListLabel, OudsAnnotatedAlertMessageBulletListLabel.Builder>(builder)
}
