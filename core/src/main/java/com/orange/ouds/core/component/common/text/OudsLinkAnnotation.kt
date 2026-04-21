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

abstract class OudsLinkAnnotation {

    internal abstract val linkAnnotation: LinkAnnotation

    class Url private constructor(override val linkAnnotation: LinkAnnotation.Url) : OudsLinkAnnotation() {

        val url = linkAnnotation.url

        val linkInteractionListener = linkAnnotation.linkInteractionListener

        constructor(
            url: String,
            linkInteractionListener: LinkInteractionListener? = null
        ) : this(LinkAnnotation.Url(url, linkInteractionListener = linkInteractionListener))

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

    class Clickable private constructor(override val linkAnnotation: LinkAnnotation.Clickable) : OudsLinkAnnotation() {

        val tag = linkAnnotation.tag

        val linkInteractionListener = linkAnnotation.linkInteractionListener

        constructor(
            tag: String,
            linkInteractionListener: LinkInteractionListener? = null
        ) : this(LinkAnnotation.Clickable(tag, linkInteractionListener = linkInteractionListener))

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
