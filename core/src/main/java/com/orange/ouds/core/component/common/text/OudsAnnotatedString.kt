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
import androidx.compose.ui.text.LinkInteractionListener
import androidx.compose.ui.text.StringAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import com.orange.ouds.core.component.OudsLinkState
import com.orange.ouds.core.component.linkContentColor
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme

/**
 * Base class for annotated strings with rich text formatting support in OUDS components.
 *
 * This class wraps Compose's [AnnotatedString] to provide type-safe rich text formatting
 * with support for strong (bold/emphasized) text and clickable links. It serves as the
 * foundation for specialized annotated string types used throughout OUDS components.
 *
 * This class implements [CharSequence], making it compatible with standard string operations.
 */
open class OudsAnnotatedString<T> internal constructor(annotatedString: AnnotatedString) : CharSequence by annotatedString
        where T : OudsAnnotatedString<T> {

    internal companion object {

        const val StrongAnnotation = "OudsAnnotatedString.StrongAnnotation"
    }

    private val _annotatedString = annotatedString

    // The link colors depend on the current light / dark mode as well as the current OudsColorMode
    // Thus this function must be accessed at the call site of the Text composable it is displayed into
    // If it is accessed elsewhere in the hierarchy, the light / dark mode or the OudsColorMode might be different
    @Composable
    internal fun annotatedString(
        strongStyle: TextStyle = OudsTheme.typography.label.strong.medium,
        linkStyle: TextStyle = OudsTheme.typography.label.strong.medium
    ): AnnotatedString {
        val linkStyles = getTextLinkStyles(linkStyle)
        return _annotatedString.mapAnnotations { range ->
            when (val annotation = range.item) {
                is StringAnnotation if (annotation.value == StrongAnnotation) -> {
                    with(range) { AnnotatedString.Range(strongStyle.toSpanStyle(), start, end) }
                }
                is LinkAnnotation -> {
                    val linkAnnotation = when (annotation) {
                        is LinkAnnotation.Url -> annotation.copy(styles = linkStyles)
                        is LinkAnnotation.Clickable -> annotation.copy(styles = linkStyles)
                        else -> annotation
                    }
                    with(range) { AnnotatedString.Range(linkAnnotation, start, end) }
                }
                else -> range
            }
        }
    }

    /**
     * The plain text content without any formatting annotations.
     * 
     * @see AnnotatedString.text
     */
    val text: String = annotatedString.text

    /**
     * Concatenates this annotated string with another annotated string.
     *
     * @param other The annotated string to append.
     * @return A new annotated string containing both strings with their annotations preserved.
     * 
     * @see AnnotatedString.plus
     */
    operator fun plus(other: T): T {
        return createInstance(_annotatedString.plus(other._annotatedString))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OudsAnnotatedString<T>) return false
        if (other._annotatedString != _annotatedString) return false
        return true
    }

    override fun hashCode(): Int = _annotatedString.hashCode()

    override fun toString(): String = _annotatedString.toString()

    /**
     * Create upper case transformed [OudsAnnotatedString].
     *
     * The uppercase sometimes maps different number of characters. This function adjusts the text style
     * and paragraph style ranges to transformed offset.
     *
     * Note, if the style's offset is middle of the uppercase mapping context, this function won't
     * transform the character, e.g. style starts from between base alphabet character and accent
     * character.
     *
     * @param localeList A locale list used for upper case mapping. Only the first locale is effective.
     *   If empty locale list is passed, use the current locale instead.
     * @return An uppercase transformed string.
     */
    fun toUpperCase(localeList: LocaleList = LocaleList.current): T = createInstance(_annotatedString.toUpperCase(localeList))

    /**
     * Create lower case transformed [OudsAnnotatedString].
     *
     * The lowercase sometimes maps different number of characters. This function adjusts the text style
     * and paragraph style ranges to transformed offset.
     *
     * Note, if the style's offset is middle of the lowercase mapping context, this function won't
     * transform the character, e.g. style starts from between base alphabet character and accent
     * character.
     *
     * @param localeList A locale list used for lower case mapping. Only the first locale is effective.
     *   If empty locale list is passed, use the current locale instead.
     * @return A lowercase transformed string.
     */
    fun toLowerCase(localeList: LocaleList = LocaleList.current): T = createInstance(_annotatedString.toLowerCase(localeList))

    /**
     * Create capitalized [OudsAnnotatedString].
     *
     * The capitalization sometimes maps different number of characters. This function adjusts the text
     * style and paragraph style ranges to transformed offset.
     *
     * Note, if the style's offset is middle of the capitalization context, this function won't
     * transform the character, e.g. style starts from between base alphabet character and accent
     * character.
     *
     * @param localeList A locale list used for capitalize mapping. Only the first locale is effective.
     *   If empty locale list is passed, use the current locale instead. Note that, this locale is
     *   currently ignored since underlying Kotlin method is experimental.
     * @return A capitalized string.
     */
    fun capitalize(localeList: LocaleList = LocaleList.current): T = createInstance(_annotatedString.capitalize(localeList))

    /**
     * Create decapitalized [OudsAnnotatedString].
     *
     * The decapitalization sometimes maps different number of characters. This function adjusts the
     * text style and paragraph style ranges to transformed offset.
     *
     * Note, if the style's offset is middle of the decapitalization context, this function won't
     * transform the character, e.g. style starts from between base alphabet character and accent
     * character.
     *
     * @param localeList A locale list used for decapitalize mapping. Only the first locale is
     *   effective. If empty locale list is passed, use the current locale instead. Note that, this
     *   locale is currently ignored since underlying Kotlin method is experimental.
     * @return A decapitalized string.
     */
    fun decapitalize(localeList: LocaleList = LocaleList.current): T = createInstance(_annotatedString.decapitalize(localeList))

    private fun createInstance(annotatedString: AnnotatedString): T {
        @Suppress("UNCHECKED_CAST")
        return createOudsAnnotatedString(annotatedString, this::class.java as Class<T>)
    }

    @Composable
    private fun getTextLinkStyles(linkStyle: TextStyle): TextLinkStyles {
        val linkSpanStyle = linkStyle.toSpanStyle().copy(textDecoration = TextDecoration.Underline)
        return with(linkSpanStyle) {
            val monochrome = LocalColorMode.current?.monochrome == true
            val linkColor = linkContentColor(state = OudsLinkState.Enabled, monochrome = monochrome)
            val linkFocusedColor = linkContentColor(state = OudsLinkState.Focused, monochrome = monochrome)
            val linkHoveredColor = linkContentColor(state = OudsLinkState.Hovered, monochrome = monochrome)
            val linkPressedColor = linkContentColor(state = OudsLinkState.Pressed, monochrome = monochrome)
            TextLinkStyles(
                style = copy(color = linkColor),
                focusedStyle = copy(color = linkFocusedColor),
                hoveredStyle = copy(color = linkHoveredColor),
                pressedStyle = copy(color = linkPressedColor)
            )
        }
    }

    /**
     * Builder class for annotated string. Enables construction of an [OudsAnnotatedString] using methods
     * such as [append].
     * 
     * @param capacity Initial capacity for the internal char buffer.
     */
    open class Builder<T> internal constructor(capacity: Int, private val clazz: Class<T>) : Appendable where T : OudsAnnotatedString<T> {

        private val builder = AnnotatedString.Builder(capacity)

        /**
         * Appends the given [String] to this [Builder].
         *
         * @param text The text to append.
         * @return This [Builder] instance.
         */
        override fun append(char: Char): Builder<T> = apply { builder.append(char) }

        /**
         * Appends [text] to this [Builder] if non-null, and returns this [Builder].
         *
         * If [text] is an [OudsAnnotatedString], all spans and annotations will be copied over as well.
         * No other subtypes of [CharSequence] will be treated specially. For example, any
         * platform-specific types, such as `SpannedString` on Android, will only have their text
         * copied and any other information held in the sequence, such as Android `Span`s, will be
         * dropped.
         *
         * @param text The text to append.
         * @return This [Builder] instance.
         */
        override fun append(text: CharSequence?): Builder<T> = apply {
            when (text) {
                // The append method of AnnotatedString.Builder preserves annotations if the CharSequence is an AnnotatedString
                is OudsAnnotatedString<*> -> builder.append(text._annotatedString.filterSupportedAnnotations())
                is AnnotatedString -> builder.append(text.filterSupportedAnnotations())
                else -> builder.append(text)
            }
        }

        /**
         * Appends the range of [text] between [start] (inclusive) and [end] (exclusive) to this
         * [Builder] if non-null, and returns this [Builder].
         *
         * If [text] is an [OudsAnnotatedString], all spans and annotations from [text] between [start]
         * and [end] will be copied over as well. No other subtypes of [CharSequence] will be
         * treated specially. For example, any platform-specific types, such as `SpannedString` on
         * Android, will only have their text copied and any other information held in the sequence,
         * such as Android `Span`s, will be dropped.
         *
         * @param text The text to append.
         * @param start The index of the first character in [text] to copy over (inclusive).
         * @param end The index after the last character in [text] to copy over (exclusive).
         * @return This [Builder] instance.
         */
        override fun append(text: CharSequence?, start: Int, end: Int): Builder<T> = apply {
            when (text) {
                // The append method of AnnotatedString.Builder preserves annotations if the CharSequence is an AnnotatedString
                is OudsAnnotatedString<*> -> builder.append(text._annotatedString.filterSupportedAnnotations(), start, end)
                is AnnotatedString -> builder.append(text.filterSupportedAnnotations(), start, end)
                else -> builder.append(text, start, end)
            }
        }

        /**
         * Appends the given [String] to this [Builder].
         *
         * @param text The text to append.
         */
        fun append(text: String): Unit = builder.append(text)

        /**
         * Constructs an [OudsAnnotatedString] based on the configurations applied to the [Builder].
         * 
         * @return The constructed annotated string.
         */
        open fun toAnnotatedString(): T {
            return createOudsAnnotatedString(builder.toAnnotatedString(), clazz)
        }

        protected fun addStrongImpl(start: Int, end: Int) {
            builder.addStrongAnnotation(start, end)
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

        private fun AnnotatedString.Builder.addStrongAnnotation(start: Int, end: Int) = addStringAnnotation("", StrongAnnotation, start, end)

        private fun AnnotatedString.filterSupportedAnnotations(): AnnotatedString {
            val builder = AnnotatedString.Builder(text)

            if (this@Builder is LinkBuilder) {
                // Link annotations
                getLinkAnnotations(0, length).forEach { range ->
                    with(range) {
                        when (val linkAnnotation = item) {
                            is LinkAnnotation.Url -> builder.addLink(linkAnnotation, start, end)
                            is LinkAnnotation.Clickable -> builder.addLink(linkAnnotation, start, end)
                        }
                    }
                }
            }

            if (this@Builder is StrongBuilder) {
                // Text style annotations which font weight is greater than or equal to FontWeight.Bold are converted to strong annotations
                // whereas others are filtered out
                spanStyles.forEach { range ->
                    range.item.fontWeight?.let { fontWeight ->
                        if (fontWeight >= FontWeight.Bold) {
                            with(range) { builder.addStrongAnnotation(start, end) }
                        }
                    }
                }

                // Strong annotations
                getStringAnnotations(0, length).filter { it.item == StrongAnnotation }
                    .forEach { range ->
                        with(range) { builder.addStrongAnnotation(start, end) }
                    }
            }

            return builder.toAnnotatedString()
        }
    }

    /**
     * Base interface for all annotated string builders, providing stack management for annotations.
     */
    interface BaseBuilder {

        /**
         * Ends the style or annotation that was added via a push operation before.
         *
         * This is typically used after manually pushing annotations with [StrongBuilder.pushStrong] or [LinkBuilder.pushLink].
         * 
         * For most use cases, prefer using the [withStrong] or [withLink] DSL helpers which
         * automatically manage the annotation stack.
         */
        fun pop(): Unit

        /**
         * Ends the styles or annotation up to and `including` the [StrongBuilder.pushStrong] or [LinkBuilder.pushLink]
         * that returned the given index.
         * 
         * For most use cases, prefer using the [withStrong] or [withLink] DSL helpers which
         * automatically manage the annotation stack.
         * 
         * @param index The index returned by a previous push operation.
         */
        fun pop(index: Int)
    }

    /**
     * Interface for builders that support strong (bold/emphasized) text annotations.
     *
     * Implementations can add strong formatting either by specifying ranges with [addStrong],
     * or by using the push/pop pattern with [pushStrong] and [pop]. For most use cases,
     * prefer using the [withStrong] DSL helper.
     */
    interface StrongBuilder : BaseBuilder {

        /**
         * Adds strong (bold/emphasized) formatting to a specific range of text.
         *
         * @param start The inclusive starting offset of the range.
         * @param end The exclusive end offset of the range.
         */
        fun addStrong(start: Int, end: Int)

        /**
         * Applies strong (bold/emphasized) style to any appended text until a corresponding [pop] is called.
         *
         * For most use cases, prefer using the [withStrong] DSL helper which automatically manages the stack.
         * 
         * @return The index of the pushed annotation, to be used with [pop] when done.
         */
        fun pushStrong(): Int
    }

    /**
     * Interface for builders that support clickable link annotations.
     *
     * Implementations can add links either by specifying ranges with [addLink],
     * or by using the push/pop pattern with [pushLink] and [pop]. For most use cases,
     * prefer using the [withLink] DSL helper.
     */
    interface LinkBuilder : BaseBuilder {

        /**
         * Set an [OudsLinkAnnotation.Url] for the given range defined by [start] and [end].
         *
         * When clicking on the text in range, the corresponding URL from the [url] annotation will
         * be opened using [androidx.compose.ui.platform.UriHandler].
         *
         * URLs may be treated specially by screen readers, including being identified while reading
         * text with an audio icon or being summarized in a links menu.
         *
         * @param url An [OudsLinkAnnotation.Url] object that stores the URL being linked to.
         * @param start The inclusive starting offset of the range.
         * @param end The exclusive end offset of the range.
         */
        fun addLink(url: OudsLinkAnnotation.Url, start: Int, end: Int)

        /**
         * Set an [OudsLinkAnnotation.Clickable] for the given range defined by [start] and [end].
         *
         * When clicking on the text in range, a [LinkInteractionListener] will be triggered with
         * the [clickable] object.
         *
         * Clickable link may be treated specially by screen readers, including being identified
         * while reading text with an audio icon or being summarized in a links menu.
         *
         * @param clickable An [OudsLinkAnnotation.Clickable] object that stores the tag being linked to.
         * @param start The inclusive starting offset of the range.
         * @param end The exclusive end offset of the range.
         */
        fun addLink(clickable: OudsLinkAnnotation.Clickable, start: Int, end: Int)

        /**
         * Attach the given [OudsLinkAnnotation] to any appended text until a corresponding [pop] is
         * called.
         * 
         * For most use cases, prefer using the [withLink] DSL helper which automatically manages the stack.
         *
         * @param link An [OudsLinkAnnotation] object that stores the URL or clickable tag being linked to.
         * @return The index of the pushed annotation, to be used with [pop] when done.
         */
        fun pushLink(link: OudsLinkAnnotation): Int
    }
}

/**
 * DSL helper for applying strong (bold/emphasized) formatting to a block of text.
 *
 * This is the recommended way to add strong formatting as it automatically manages
 * the annotation stack.
 *
 * Example:
 * ```
 * buildOudsAnnotatedHelperText {
 *     append("Password must be ")
 *     withStrong {
 *         append("at least 8 characters")
 *     }
 *     append(" long")
 * }
 * ```
 *
 * @param block The lambda that appends the text to be formatted as strong.
 * @return The result of the block.
 */
inline fun <R : Any> OudsAnnotatedString.StrongBuilder.withStrong(block: OudsAnnotatedString.StrongBuilder.() -> R): R {
    val index = pushStrong()
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

/**
 * DSL helper for applying link formatting to a block of text.
 *
 * This is the recommended way to add links as it automatically manages
 * the annotation stack.
 *
 * Example:
 * ```
 * buildOudsAnnotatedAlertMessageDescription {
 *     append("Please read the ")
 *     withLink(OudsLinkAnnotation.Url("https://example.com/terms")) {
 *         append("terms and conditions")
 *     }
 *     append(" carefully")
 * }
 * ```
 *
 * @param link The link annotation (URL or clickable).
 * @param block The lambda that appends the text to be formatted as a link.
 * @return The result of the block.
 */
inline fun <R : Any> OudsAnnotatedString.LinkBuilder.withLink(link: OudsLinkAnnotation, block: OudsAnnotatedString.LinkBuilder.() -> R): R {
    val index = pushLink(link)
    return try {
        block(this)
    } finally {
        pop(index)
    }
}

internal inline fun <T, reified U> buildOudsAnnotatedString(noinline builder: (U).() -> Unit): T where U : OudsAnnotatedString.Builder<T>, T : OudsAnnotatedString<T> {
    return buildOudsAnnotatedString(U::class.java, builder)
}

@PublishedApi
internal fun <T, U> buildOudsAnnotatedString(
    builderClass: Class<T>,
    builder: (T).() -> Unit
): U where T : OudsAnnotatedString.Builder<U>, U : OudsAnnotatedString<U> {
    return builderClass.getConstructor()
        .newInstance()
        .apply(builder)
        .toAnnotatedString()
}

private fun <T> createOudsAnnotatedString(annotatedString: AnnotatedString, clazz: Class<T>): T where T : OudsAnnotatedString<T> {
    return clazz.getConstructor(AnnotatedString::class.java).newInstance(annotatedString)
}
