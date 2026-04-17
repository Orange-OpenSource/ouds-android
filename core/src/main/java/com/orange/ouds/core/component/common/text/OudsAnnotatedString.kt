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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.StringAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import com.orange.ouds.core.component.OudsLinkState
import com.orange.ouds.core.component.linkContentColor
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme

open class OudsAnnotatedString<T> internal constructor(annotatedString: AnnotatedString) : CharSequence by annotatedString
        where T : OudsAnnotatedString<T> {

    internal companion object {

        const val StrongAnnotation = "OudsAnnotatedString.StrongAnnotation"
    }

    private val _annotatedString = annotatedString

    // The link colors depend on the current light / dark mode as well as the current OudsColorMode
    // Thus this property must be accessed at the call site of the Text composable it is displayed into
    // If it is accessed elsewhere in the hierarchy, the light / dark mode or the OudsColorMode might be different
    @Composable
    internal fun annotatedString(
        strongStyle: TextStyle = OudsTheme.typography.label.strong.medium,
        linkStyle: TextStyle = OudsTheme.typography.label.strong.medium
    ): AnnotatedString {
        val monochrome = LocalColorMode.current?.monochrome == true
        val linkColor = linkContentColor(state = OudsLinkState.Enabled, monochrome = monochrome)
        val linkFocusedColor = linkContentColor(state = OudsLinkState.Focused, monochrome = monochrome)
        val linkHoveredColor = linkContentColor(state = OudsLinkState.Hovered, monochrome = monochrome)
        val linkPressedColor = linkContentColor(state = OudsLinkState.Pressed, monochrome = monochrome)

        return _annotatedString.mapAnnotations { annotation ->
            when (val item = annotation.item) {
                is LinkAnnotation -> {
                    val spanStyle = linkStyle.toSpanStyle().copy(textDecoration = TextDecoration.Underline)
                    val styles = with(spanStyle) {
                        TextLinkStyles(
                            style = copy(color = linkColor),
                            focusedStyle = copy(color = linkFocusedColor),
                            hoveredStyle = copy(color = linkHoveredColor),
                            pressedStyle = copy(color = linkPressedColor)
                        )
                    }
                    val linkAnnotation = when (item) {
                        is LinkAnnotation.Url -> item.copy(styles = styles)
                        is LinkAnnotation.Clickable -> item.copy(styles = styles)
                        else -> item
                    }
                    with(annotation) { AnnotatedString.Range(linkAnnotation, start, end, tag) }
                }
                is StringAnnotation if item.value == StrongAnnotation -> {
                    with(annotation) { AnnotatedString.Range(strongStyle.toSpanStyle(), start, end, tag) }
                }
                else -> annotation
            }
        }
    }

    val text: String = annotatedString.text

    operator fun plus(other: OudsAnnotatedString<T>): OudsAnnotatedString<T> {
        return OudsAnnotatedString(_annotatedString.plus(other._annotatedString))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OudsAnnotatedString<T>) return false
        if (other._annotatedString != _annotatedString) return false
        return true
    }

    override fun hashCode(): Int = _annotatedString.hashCode()

    override fun toString(): String = _annotatedString.toString()

    fun toUpperCase(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> = OudsAnnotatedString(_annotatedString.toUpperCase(localeList))

    fun toLowerCase(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> = OudsAnnotatedString(_annotatedString.toLowerCase(localeList))

    fun capitalize(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> = OudsAnnotatedString(_annotatedString.capitalize(localeList))

    fun decapitalize(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> = OudsAnnotatedString(_annotatedString.decapitalize(localeList))

    open class Builder<T> internal constructor(capacity: Int, private val clazz: Class<T>) : Appendable where T : OudsAnnotatedString<T> {

        private val builder = AnnotatedString.Builder(capacity)

        override fun append(char: Char): Builder<T> = apply { builder.append(char) }

        override fun append(text: CharSequence?): Builder<T> = apply { builder.append(text) }

        override fun append(text: CharSequence?, start: Int, end: Int): Builder<T> = apply { builder.append(text, start, end) }

        fun append(text: String): Unit = builder.append(text)

        fun append(text: T): Unit = builder.append(text._annotatedString)

        fun append(text: T, start: Int, end: Int): Unit = builder.append(text._annotatedString, start, end)

        open fun toAnnotatedString(): T {
            val constructor: (AnnotatedString) -> T = { clazz.getConstructor(AnnotatedString::class.java).newInstance(it) }
            return constructor(builder.toAnnotatedString())
        }

        protected fun addStrongImpl(start: Int, end: Int) {
            builder.addStringAnnotation("", StrongAnnotation, start, end)
        }

        protected fun addLinkImpl(url: OudsLinkAnnotation.Url, start: Int, end: Int) {
            builder.addLink(url.linkAnnotation, start, end)
        }

        protected fun addLinkImpl(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) {
            builder.addLink(clickable.linkAnnotation, start, end)
        }

        protected fun pushStrongImpl(): Int = builder.pushStringAnnotation("", StrongAnnotation)

        protected fun pushLinkImpl(link: OudsLinkAnnotation): Int = builder.pushLink(link.linkAnnotation)

        fun pop(): Unit = builder.pop()

        fun pop(index: Int): Unit = builder.pop(index)
    }

    interface BaseBuilder {

        fun pop(): Unit

        fun pop(index: Int)
    }

    interface StrongBuilder : BaseBuilder {

        fun addStrong(start: Int, end: Int)

        fun pushStrong(): Int
    }

    interface LinkBuilder : BaseBuilder {

        fun addLink(url: OudsLinkAnnotation.Url, start: Int, end: Int)

        fun addLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int)

        fun pushLink(link: OudsLinkAnnotation): Int
    }
}

inline fun <R : Any> OudsAnnotatedString.StrongBuilder.withStrong(block: OudsAnnotatedString.StrongBuilder.() -> R): R {
    val index = pushStrong()
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

inline fun <R : Any> OudsAnnotatedString.LinkBuilder.withLink(link: OudsLinkAnnotation, block: OudsAnnotatedString.LinkBuilder.() -> R): R {
    val index = pushLink(link)
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

inline fun <T, reified U> buildOudsAnnotatedString(noinline builder: (U).() -> Unit): T where U : OudsAnnotatedString.Builder<T>, T : OudsAnnotatedString<T> {
    return buildOudsAnnotatedString(U::class.java, builder)
}

@PublishedApi
internal fun <T, U> buildOudsAnnotatedString(
    builderClass: Class<T>,
    builder: (T).() -> Unit
): U where T : OudsAnnotatedString.Builder<U>, U : OudsAnnotatedString<U> {
    val constructor: () -> T = { builderClass.getConstructor().newInstance() }
    return constructor().apply { builder() }.toAnnotatedString()
}
