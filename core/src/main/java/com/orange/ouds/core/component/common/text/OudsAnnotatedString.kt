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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase

open class OudsAnnotatedString<T> internal constructor(internal val annotatedString: AnnotatedString) : CharSequence where T : OudsAnnotatedString<T> {

    val text: String = annotatedString.text

    override val length: Int = annotatedString.length

    override operator fun get(index: Int): Char = annotatedString[index]

    override fun subSequence(startIndex: Int, endIndex: Int): OudsAnnotatedString<T> {
        return OudsAnnotatedString(annotatedString.subSequence(startIndex, endIndex))
    }

    operator fun plus(other: OudsAnnotatedString<T>): OudsAnnotatedString<T> {
        return OudsAnnotatedString(annotatedString.plus(other.annotatedString))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OudsAnnotatedString<T>) return false
        if (other.annotatedString != annotatedString) return false
        return true
    }

    override fun hashCode(): Int = annotatedString.hashCode()

    override fun toString(): String = annotatedString.toString()

    open class Builder<T> internal constructor(capacity: Int, private val clazz: Class<T>) : Appendable where T : OudsAnnotatedString<T> {

        internal companion object {

            val strongStyle = SpanStyle(fontWeight = FontWeight.Bold)
        }

        private val builder = AnnotatedString.Builder(capacity)

        override fun append(char: Char): Builder<T> = apply { builder.append(char) }

        override fun append(text: CharSequence?): Builder<T> = apply { builder.append(text) }

        override fun append(text: CharSequence?, start: Int, end: Int): Builder<T> = apply { builder.append(text, start, end) }

        fun append(text: String): Unit = builder.append(text)

        fun append(text: T): Unit = builder.append(text.annotatedString)

        fun append(text: T, start: Int, end: Int): Unit = builder.append(text.annotatedString, start, end)

        open fun toAnnotatedString(): T {
            val constructor: (AnnotatedString) -> T = { clazz.getConstructor(AnnotatedString::class.java).newInstance(it) }
            return constructor(builder.toAnnotatedString())
        }

        internal fun addStrongInternal(start: Int, end: Int) {
            builder.addStyle(strongStyle, start, end)
        }

        internal fun addLinkInternal(url: OudsLinkAnnotation.Url, start: Int, end: Int) {
            builder.addLink(url.linkAnnotation, start, end)
        }

        internal fun addLinkInternal(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int) {
            builder.addLink(clickable.linkAnnotation, start, end)
        }

        internal fun pushStrongInternal(): Int = builder.pushStyle(strongStyle)

        internal fun pushLinkInternal(link: OudsLinkAnnotation): Int = builder.pushLink(link.linkAnnotation)

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

fun <T> OudsAnnotatedString<T>.toUpperCase(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> where T : OudsAnnotatedString<T> {
    return OudsAnnotatedString(annotatedString.toUpperCase(localeList))
}

fun <T> OudsAnnotatedString<T>.toLowerCase(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> where T : OudsAnnotatedString<T> {
    return OudsAnnotatedString(annotatedString.toLowerCase(localeList))
}

fun <T> OudsAnnotatedString<T>.capitalize(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> where T : OudsAnnotatedString<T> {
    return OudsAnnotatedString(annotatedString.capitalize(localeList))
}

fun <T> OudsAnnotatedString<T>.decapitalize(localeList: LocaleList = LocaleList.current): OudsAnnotatedString<T> where T : OudsAnnotatedString<T> {
    return OudsAnnotatedString(annotatedString.decapitalize(localeList))
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

inline fun <reified T, U> buildOudsAnnotatedString(noinline builder: (T).() -> Unit): U where T : OudsAnnotatedString.Builder<U>, U : OudsAnnotatedString<U> {
    return buildOudsAnnotatedString(T::class.java, builder)
}

@PublishedApi
internal fun <T, U> buildOudsAnnotatedString(
    builderClass: Class<T>,
    builder: (T).() -> Unit
): U where T : OudsAnnotatedString.Builder<U>, U : OudsAnnotatedString<U> {
    val constructor: () -> T = { builderClass.getConstructor().newInstance() }
    return constructor().apply(builder).toAnnotatedString()
}
