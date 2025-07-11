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

package com.orange.ouds.app.ui.utilities

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.BottomBarItem
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.asOrNull
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.tryOrNull
import kotlin.properties.Delegates

@DslMarker
annotation class CodeDslMarker

private const val CODE_INDENT = "  "

fun interface Formattable {

    fun format(context: Context): String
}

fun code(init: Code.Builder.() -> Unit) = Code.Builder().apply(init).build()

data class Code(val elements: List<Formattable>) : Formattable {

    override fun format(context: Context): String {
        return elements.joinToString("\n") { it.format(context) }
    }

    @CodeDslMarker
    class Builder {

        private var elements: MutableList<Formattable> = mutableListOf()

        fun functionCall(name: String, init: FunctionCall.Builder.() -> Unit = {}) {
            val functionCall = FunctionCall.Builder()
                .apply {
                    this.name = name
                    init()
                }
                .build()
            elements.add(functionCall)
        }

        fun comment(text: String, init: Comment.Builder.() -> Unit = {}) {
            val comment = Comment.Builder()
                .apply {
                    this.text = text
                    init()
                }
                .build()
            elements.add(comment)
        }

        fun newline() {
            elements.add(Newline())
        }

        fun build() = Code(elements)
    }
}

data class Comment(val text: String, val isMultiline: Boolean) : Formattable {

    override fun format(context: Context): String = if (isMultiline) "/* ${text.replace("\n", "\n   ")} */" else "// $text"

    @CodeDslMarker
    class Builder {

        var text: String by Delegates.notNull()

        var isMultiline: Boolean = false

        fun build() = Comment(text, isMultiline)
    }
}

data class FunctionCall(val name: String, val elements: List<Formattable>, val isMultiline: Boolean, val trailingLambda: Boolean) : Formattable {

    override fun format(context: Context): String {
        val isMultiline = isMultiline && elements.isNotEmpty()
        val elementSeparator = if (isMultiline) "\n" else " "
        val lambda = elements.lastOrNull()?.asOrNull<Argument<*>>()?.value?.asOrNull<Lambda>().takeIf { trailingLambda }
        val elements = if (lambda != null) elements.dropLast(1) else elements
        val formattedElements = elements.joinToString(elementSeparator) { codeFormattable ->
            val elementSuffix = if (codeFormattable is Argument<*>) "," else ""
            "${codeFormattable.format(context)}$elementSuffix"
        }
            .removeSuffix(",")
            .run { if (isMultiline) prependIndent(CODE_INDENT) else this }
        val parenthesisSeparator = if (isMultiline) "\n" else ""

        val formattedLambda = if (lambda != null) " ${lambda.format(context)}" else ""

        return "$name($parenthesisSeparator$formattedElements$parenthesisSeparator)$formattedLambda"
    }

    @CodeDslMarker
    class Builder {

        var name: String by Delegates.notNull()

        @PublishedApi
        internal var elements: MutableList<Formattable> = mutableListOf()

        var isMultiline = true

        var trailingLambda = false

        inline fun <reified T> typedArgument(name: String?, value: T) {
            elements.add(Argument(name, value, T::class.java))
        }
        
        fun formattableArgument(name: String?, format: (Context) -> String) = typedArgument(name, Formattable(format))

        fun rawArgument(name: String?, value: String) = formattableArgument(name) { value }

        fun stringResourceArgument(name: String?, @StringRes id: Int, vararg formatArgs: Any) {
            formattableArgument(name) { "\"${it.getString(id, formatArgs)}\"" }
        }

        fun lambdaArgument(name: String?, init: Code.Builder.() -> Unit = {}) {
            val code = Code.Builder().apply(init).build()
            typedArgument(name, Lambda(code))
        }

        fun functionCallArgument(name: String?, functionName: String, init: Builder.() -> Unit = {}) {
            val builder = Builder().apply {
                this.name = functionName
                init()
            }
            val functionCall = builder.build()
            typedArgument(name, functionCall)
        }

        inline fun <reified T> constructorCallArgument(name: String?, noinline init: Builder.() -> Unit = {}) {
            functionCallArgument(name, T::class.java.nestedName, init)
        }

        fun comment(text: String, init: Comment.Builder.() -> Unit = {}) {
            val comment = Comment.Builder()
                .apply {
                    this.text = text
                    init()
                }
                .build()
            elements.add(comment)
        }

        fun build() = FunctionCall(name, elements, isMultiline, trailingLambda)
    }
}

data class Argument<T>(val name: String?, val value: T, val clazz: Class<T>) : Formattable {

    override fun format(context: Context): String {
        val valueString = when (value) {
            is Float -> "${value}F"
            is String -> "\"$value\""
            is Int -> {
                val resourceName = tryOrNull { context.resources.getResourceName(value) }?.substringAfter("/")
                val resourceTypeName = tryOrNull { context.resources.getResourceTypeName(value) }
                if (resourceName != null && resourceTypeName != null) "R.$resourceTypeName.$resourceName" else value.toString()
            }
            is Enum<*> -> "${clazz.nestedName}.${value.name}" // Displays OudsButton.Hierarchy.Strong instead of Strong
            is Formattable -> value.format(context)
            else -> {
                val valueClass = value?.let { it::class }.orElse { null }
                if (valueClass?.previewCompatibleClass?.isData == true) {
                    // Displays OudsButton.Style.Loading(progress = null) instead of Loading(progress=null)
                    "${valueClass.java.nestedName.substringBeforeLast(".")}.${value.toString().replace("=", " = ")}"
                } else {
                    value.toString()
                }
            }
        }

        return if (name?.isNotBlank() == true) "$name = $valueString" else valueString
    }
}

data class Lambda(val code: Code) : Formattable {

    override fun format(context: Context): String {
        val formattedCode = code.format(context)
        return if (formattedCode.isNotBlank()) "{\n${formattedCode.prependIndent(CODE_INDENT)}\n}" else "{}"
    }
}

class Newline : Formattable {

    override fun format(context: Context): String = "" // There is no need to return "\n" because code elements are already joined using "\n"
}

@Preview
@Composable
internal fun PreviewCode() = OudsPreview {
    val code = code {
        comment("Multiline\ncomment") { isMultiline = true }
        newline()
        functionCall("OudsComponent") {
            trailingLambda = true
            constructorCallArgument<OudsButton.Icon>("icon") {
                functionCallArgument("painter", "painterResource") {
                    typedArgument("id", R.drawable.ic_heart)
                }
                lambdaArgument("onClick") {
                    comment("click") { isMultiline = true }
                }
            }
            functionCallArgument("list", "listOf") {
                isMultiline = false
                rawArgument(null, "OudsTheme.shapes") // Raw
                stringResourceArgument(null, R.string.app_name)
                typedArgument(null, "Text") // String
                typedArgument(null, 1.234) // Double
                comment("Comment") { isMultiline = true }
                typedArgument(null, 1.234f) // Float
                typedArgument(null, 1234) // Int
                typedArgument(null, true) // Boolean
                typedArgument(null, BottomBarItem.Tokens) // Enum
                typedArgument<String?>(null, null) // null
            }
            lambdaArgument("content") {
                comment("Single line comment")
                functionCall("content")
            }
        }
    }
    val context = LocalContext.current
    Text(
        text = code.format(context),
        style = TextStyle(fontFamily = FontFamily.Monospace),
        color = OudsTheme.colorScheme.content.default
    )
}
