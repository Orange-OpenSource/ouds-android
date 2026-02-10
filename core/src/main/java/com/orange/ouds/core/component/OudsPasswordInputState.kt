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

package com.orange.ouds.core.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.UndoState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.text.input.setTextAndSelectAll
import androidx.compose.foundation.text.input.toTextFieldBuffer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange

@Composable
fun rememberOudsPasswordInputState(
    initialText: String = "",
    initialSelection: TextRange = TextRange(initialText.length),
    initialIsPasswordHidden: Boolean = true
) = rememberSaveable(saver = OudsPasswordInputState.Saver) {
    val textFieldState = TextFieldState(initialText, initialSelection)
    OudsPasswordInputState(textFieldState, initialIsPasswordHidden)
}

class OudsPasswordInputState internal constructor(
    @PublishedApi internal val textFieldState: TextFieldState,
    initialIsPasswordHidden: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TextFieldState.Saver) { save(textFieldState) },
                        isPasswordHidden
                    )
                }
            },
            restore = { list: List<Any?> ->
                val textFieldState = list[0]?.let { TextFieldState.Saver.restore(it) }
                OudsPasswordInputState(
                    textFieldState as TextFieldState,
                    list[1] as Boolean
                )
            }
        )
    }

    constructor(
        initialText: String = "",
        initialSelection: TextRange = TextRange(initialText.length),
        initialIsPasswordHidden: Boolean = true
    ) : this(TextFieldState(initialText, initialSelection), initialIsPasswordHidden)

    val text: CharSequence
        get() = textFieldState.text

    val selection: TextRange
        get() = textFieldState.selection

    val composition: TextRange?
        get() = textFieldState.composition

    inline fun edit(block: TextFieldBuffer.() -> Unit): Unit = textFieldState.edit(block)

    override fun toString(): String = textFieldState.toString()

    @ExperimentalFoundationApi
    val undoState: UndoState = textFieldState.undoState

    var isPasswordHidden: Boolean by mutableStateOf(initialIsPasswordHidden)
}

fun OudsPasswordInputState.setTextAndPlaceCursorAtEnd(text: String): Unit = textFieldState.setTextAndPlaceCursorAtEnd(text)

fun OudsPasswordInputState.setTextAndSelectAll(text: String): Unit = textFieldState.setTextAndSelectAll(text)

fun OudsPasswordInputState.clearText(): Unit = textFieldState.clearText()

fun OudsPasswordInputState.toTextFieldBuffer(): TextFieldBuffer = textFieldState.toTextFieldBuffer()
