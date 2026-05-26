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
 * An annotated string for bullet list item labels.
 *
 * This class supports both strong (bold/emphasized) text and clickable links to create
 * rich, interactive bullet list items. It is used by [com.orange.ouds.core.component.OudsBulletList] component.
 *
 * Use [buildOudsAnnotatedBulletListLabel] to create instances:
 * ```
 * OudsBulletList {
 *     item(label = buildOudsAnnotatedBulletListLabel {
 *         withStrong { append("Important:") }
 *         append(" Read this carefully")
 *     })
 *     item(label = buildOudsAnnotatedBulletListLabel {
 *         append("For more information, visit our ")
 *         withLink(OudsLinkAnnotation.Url("https://help.example.com")) {
 *             append("help center")
 *         }
 *     })
 * }
 * ```
 */
class OudsAnnotatedBulletListLabel internal constructor(annotatedString: AnnotatedString) :
    OudsAnnotatedString<OudsAnnotatedBulletListLabel>(annotatedString) {

    /**
     * Builder for creating [OudsAnnotatedBulletListLabel] with strong text and link formatting.
     *
     * Supports both strong text and link annotations through [addStrong], [pushStrong],
     * [addLink], and [pushLink] methods, or more conveniently through the [withStrong]
     * and [withLink] DSL helpers.
     *
     * @param capacity Initial capacity for the underlying string builder. Defaults to 16.
     */
    class Builder(capacity: Int = 16) :
        OudsAnnotatedString.Builder<OudsAnnotatedBulletListLabel>(capacity, OudsAnnotatedBulletListLabel::class.java),
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
         * Creates a builder initialized with an existing annotated helper text.
         *
         * @param text The initial annotated text to copy.
         */
        constructor(text: OudsAnnotatedHelperText) : this() {
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
 * Creates an [OudsAnnotatedBulletListLabel] using a builder DSL.
 *
 * Example:
 * ```
 * val label = buildOudsAnnotatedBulletListLabel {
 *     append("Product details available on our ")
 *     withLink(OudsLinkAnnotation.Url("https://example.com/products")) {
 *         append("website")
 *     }
 * }
 * ```
 *
 * @param builder Lambda with receiver for building the annotated string.
 * @return The constructed annotated bullet list label.
 */
fun buildOudsAnnotatedBulletListLabel(builder: (OudsAnnotatedBulletListLabel.Builder).() -> Unit): OudsAnnotatedBulletListLabel {
    return buildOudsAnnotatedString<OudsAnnotatedBulletListLabel, OudsAnnotatedBulletListLabel.Builder>(builder)
}
