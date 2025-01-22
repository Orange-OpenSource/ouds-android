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

package com.orange.ouds.app.ui.about

internal object Markdown {

    private const val SPECIAL_CHARACTERS = "\\`*_{}[]()#+-.!"

    fun toHtml(markdown: String): String {
        return markdown.replaceEscapedCharacters()
            .replaceLinks()
            .replaceCode()
            .replaceHeadings()
            .replaceNewlines()
    }

    private fun CharSequence.replaceEscapedCharacters(): String {
        val escapedCharacters = SPECIAL_CHARACTERS.map { "\\$it" }.joinToString("")
        return replace("\\\\([$escapedCharacters])".toRegex(), "$1")
    }

    private fun CharSequence.replaceLinks(): String {
        return replace("<([^>]*)>".toRegex(), "<a href=\"$1\">$1</a>")
            .replace("\\[(.[^]]*)]\\(([^)]*)\\)".toRegex(), "<a href=\"$2\">$1</a>")
    }

    private fun CharSequence.replaceCode() = replace("`([^`]*)`".toRegex(), "<code>$1</code>")

    private fun CharSequence.replaceHeadings(): String {
        var result = toString()
        for (level in 1..6) {
            val markdownHeading = "#".repeat(level)
            result = result.replace("(?m)^$markdownHeading (.*)$".toRegex(), "<h$level>$1</h$level>")
        }

        return result
    }

    // This method must be called after replaceHeadings
    private fun CharSequence.replaceNewlines(): String {
        var result = toString().replace("\n", "<br>")
        for (level in 1..6) {
            // Remove useless newlines before and after headings
            result = result.replace("(<br>)*<h$level>".toRegex(), "<h$level>")
                .replace("</h$level>(<br>)*".toRegex(), "</h$level>")
        }

        return result
    }
}
