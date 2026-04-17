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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import com.orange.ouds.core.theme.OudsTheme

open class OudsAnnotatedString<T> internal constructor(annotatedString: AnnotatedString) : CharSequence by annotatedString
        where T : OudsAnnotatedString<T> {

    private val _annotatedString = annotatedString

    // The link colors depend on the current light / dark mode as well as the current OudsColorMode
    // Thus this property must be accessed at the call site of the Text composable it is displayed into
    // If it is accessed elsewhere in the hierarchy, the light / dark mode or the OudsColorMode might be different
    internal val annotatedString: AnnotatedString
        @Composable
        get() {
            val textLinkColors = getTextLinkColors()
            return _annotatedString.mapAnnotations { annotation ->
                val item = annotation.item
                if (item is LinkAnnotation) {
                    val styles = item.styles?.updateColors(textLinkColors)
                    val linkAnnotation = when (item) {
                        is LinkAnnotation.Url -> item.copy(styles = styles)
                        is LinkAnnotation.Clickable -> item.copy(styles = styles)
                        else -> item
                    }
                    @Suppress("UNCHECKED_CAST")
                    (annotation as AnnotatedString.Range<LinkAnnotation>).copy(item = linkAnnotation)
                } else {
                    annotation
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

        protected open val strongStyle: TextStyle
            @Composable
            get() = OudsTheme.typography.label.strong.medium

        protected open val linkStyle: TextStyle
            @Composable
            get() = OudsTheme.typography.label.strong.medium

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

        @Composable
        protected fun AddStrongImpl(start: Int, end: Int) {
            builder.addStyle(strongStyle.toSpanStyle(), start, end)
        }

        @Composable
        protected fun AddLinkImpl(url: OudsLinkAnnotation.Url, start: Int, end: Int) {
            builder.addLink(url.linkAnnotation.withStyle(linkStyle), start, end)
        }

        @Composable
        protected fun AddLinkImpl(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) {
            builder.addLink(clickable.linkAnnotation.withStyle(linkStyle), start, end)
        }

        @Composable
        protected fun pushStrongImpl(): Int = builder.pushStyle(strongStyle.toSpanStyle())

        @Composable
        protected fun pushLinkImpl(link: OudsLinkAnnotation): Int = builder.pushLink(link.linkAnnotation.withStyle(linkStyle))

        fun pop(): Unit = builder.pop()

        fun pop(index: Int): Unit = builder.pop(index)
    }

    interface BaseBuilder {

        fun pop(): Unit

        fun pop(index: Int)
    }

    interface StrongBuilder : BaseBuilder {

        @Composable
        fun AddStrong(start: Int, end: Int)

        @Composable
        fun pushStrong(): Int
    }

    interface LinkBuilder : BaseBuilder {

        @Composable
        fun AddLink(url: OudsLinkAnnotation.Url, start: Int, end: Int)

        @Composable
        fun AddLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int)

        @Composable
        fun pushLink(link: OudsLinkAnnotation): Int
    }
}

@Composable
inline fun <R : Any> OudsAnnotatedString.StrongBuilder.withStrong(block: OudsAnnotatedString.StrongBuilder.() -> R): R {
    val index = pushStrong()
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

@Composable
inline fun <R : Any> OudsAnnotatedString.LinkBuilder.withLink(link: OudsLinkAnnotation, block: OudsAnnotatedString.LinkBuilder.() -> R): R {
    val index = pushLink(link)
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

@Composable
inline fun <T, reified U> buildOudsAnnotatedString(noinline builder: @Composable ((U).() -> Unit)): T where U : OudsAnnotatedString.Builder<T>, T : OudsAnnotatedString<T> {
    return buildOudsAnnotatedString(U::class.java, builder)
}

@PublishedApi
@Composable
internal fun <T, U> buildOudsAnnotatedString(
    builderClass: Class<T>,
    builder: @Composable ((T).() -> Unit)
): U where T : OudsAnnotatedString.Builder<U>, U : OudsAnnotatedString<U> {
    val constructor: () -> T = { builderClass.getConstructor().newInstance() }
    return constructor().apply { builder() }.toAnnotatedString()
}
