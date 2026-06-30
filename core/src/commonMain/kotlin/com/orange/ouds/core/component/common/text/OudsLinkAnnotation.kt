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

import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener

/**
 * An annotation that represents a clickable part of the text displayed by an [OudsAnnotatedString].
 */
abstract class OudsLinkAnnotation {

    internal abstract val linkAnnotation: LinkAnnotation

    /**
     * An annotation that contains a [url] string. When clicking on the text to which this
     * annotation is attached, the app will try to open the url using
     * [androidx.compose.ui.platform.UriHandler]. However, if [linkInteractionListener] is provided,
     * its [LinkInteractionListener.onClick] method will be called instead and so you need to then
     * handle opening url manually (for example by calling
     * [androidx.compose.ui.platform.UriHandler]).
     */
    class Url private constructor(override val linkAnnotation: LinkAnnotation.Url) : OudsLinkAnnotation() {

        /**
         * The URL to navigate to when the link is clicked.
         */
        val url = linkAnnotation.url

        /**
         * Interaction listener triggered when user interacts with this link.
         */
        val linkInteractionListener = linkAnnotation.linkInteractionListener

        /**
         * Creates a URL-based link annotation.
         *
         * @param url The URL to navigate to when the link is clicked.
         * @param linkInteractionListener Interaction listener triggered when user interacts with this link.
         */
        constructor(
            url: String,
            linkInteractionListener: LinkInteractionListener? = null
        ) : this(LinkAnnotation.Url(url, linkInteractionListener = linkInteractionListener))

        /**
         * Creates a copy of this URL link with optionally modified properties.
         *
         * @param url The URL for the new link. Defaults to the current URL.
         * @param linkInteractionListener The interaction listener for the new link. Defaults to the current listener.
         * @return A new [Url] instance with the specified properties.
         */
        fun copy(
            url: String = this.url,
            linkInteractionListener: LinkInteractionListener? = this.linkInteractionListener,
        ): Url = Url(linkAnnotation.copy(url, linkInteractionListener = linkInteractionListener))

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Url) return false
            if (other.linkAnnotation != linkAnnotation) return false
            return true
        }

        override fun hashCode(): Int {
            return linkAnnotation.hashCode()
        }

        override fun toString(): String {
            return "OudsLinkAnnotation.Url(url=$url)"
        }
    }

    /**
     * An annotation that contains a clickable marked with [tag]. When clicking on the text to which
     * this annotation is attached, the app will trigger a [linkInteractionListener] listener.
     */
    class Clickable private constructor(override val linkAnnotation: LinkAnnotation.Clickable) : OudsLinkAnnotation() {

        /**
         * A string identifier for this clickable link.
         */
        val tag = linkAnnotation.tag

        /**
         * Interaction listener triggered when user interacts with this link.
         */
        val linkInteractionListener = linkAnnotation.linkInteractionListener

        /**
         * Creates a tag-based clickable link annotation.
         *
         * @param tag A string identifier for this clickable link.
         * @param linkInteractionListener Interaction listener triggered when user interacts with this link.
         */
        constructor(
            tag: String,
            linkInteractionListener: LinkInteractionListener? = null
        ) : this(LinkAnnotation.Clickable(tag, linkInteractionListener = linkInteractionListener))

        /**
         * Creates a copy of this clickable link with optionally modified properties.
         *
         * @param tag The tag for the new link. Defaults to the current tag.
         * @param linkInteractionListener The interaction listener for the new link. Defaults to the current listener.
         * @return A new [Clickable] instance with the specified properties.
         */
        fun copy(
            tag: String = this.tag,
            linkInteractionListener: LinkInteractionListener? = this.linkInteractionListener,
        ): Clickable = Clickable(linkAnnotation.copy(tag, linkInteractionListener = linkInteractionListener))

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Clickable) return false
            if (other.linkAnnotation != linkAnnotation) return false
            return true
        }

        override fun hashCode(): Int {
            return linkAnnotation.hashCode()
        }

        override fun toString(): String {
            return "OudsLinkAnnotation.Clickable(tag=$tag)"
        }
    }
}
