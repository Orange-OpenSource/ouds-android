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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import com.orange.ouds.core.component.OudsLinkState
import com.orange.ouds.core.component.linkContentColor
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.foundation.extensions.orElse

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

internal fun <T> T.withStyle(style: TextStyle): T where T : LinkAnnotation {
    val spanStyle = style.toSpanStyle().copy(textDecoration = TextDecoration.Underline)
    val styles = TextLinkStyles(style = spanStyle)
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is LinkAnnotation.Url -> copy(styles = styles)
        is LinkAnnotation.Clickable -> copy(styles = styles)
        else -> this
    } as T
}

internal fun TextLinkStyles.updateColors(colors: TextLinkColors): TextLinkStyles {
    return TextLinkStyles(
        style = colors.color?.let { style?.copy(color = it) },
        focusedStyle = colors.focusedColor?.let { focusedStyle.orElse { style }?.copy(color = it) },
        hoveredStyle = colors.hoveredColor?.let { hoveredStyle.orElse { style }?.copy(color = it) },
        pressedStyle = colors.pressedColor?.let { pressedStyle.orElse { style }?.copy(color = it) }
    )
}

@Composable
internal fun getTextLinkColors(): TextLinkColors {
    val monochrome = LocalColorMode.current?.monochrome == true
    return TextLinkColors(
        color = linkContentColor(state = OudsLinkState.Enabled, monochrome = monochrome),
        focusedColor = linkContentColor(state = OudsLinkState.Focused, monochrome = monochrome),
        hoveredColor = linkContentColor(state = OudsLinkState.Hovered, monochrome = monochrome),
        pressedColor = linkContentColor(state = OudsLinkState.Pressed, monochrome = monochrome),
    )
}

internal class TextLinkColors(
    val color: Color? = null,
    val focusedColor: Color? = null,
    val hoveredColor: Color? = null,
    val pressedColor: Color? = null,
)
