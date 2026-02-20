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
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.UndoState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.text.input.setTextAndSelectAll
import androidx.compose.foundation.text.input.toTextFieldBuffer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextRange
import com.orange.ouds.foundation.extensions.orElse

/**
 * Create and remember an [OudsPasswordInputState]. The state is remembered using [rememberSaveable] and so
 * will be saved and restored with the composition.
 *
 * If you need to store an [OudsPasswordInputState] in another object, use the [OudsPasswordInputState.Saver] object
 * to manually save and restore the state.
 *
 * @param initialText The initial text state. If a different value is passed in a subsequent
 *   recomposition, the value of the state will _not_ be updated. To update the state after it's
 *   initialized, call methods on [OudsPasswordInputState].
 * @param initialSelection The initial selection state. If a different value is passed in a
 *   subsequent recomposition, the value of the state will _not_ be updated. To update the state
 *   after it's initialized, call methods on [OudsPasswordInputState].
 * @param initialTextObfuscationMode The initial method used to obscure the input text.
 *   If a different value is passed in a subsequent recomposition, the value of the state will _not_
 *   be updated. To update the state after it's initialized, call [OudsPasswordInputState.textObfuscationMode].
 */
@Composable
fun rememberOudsPasswordInputState(
    initialText: String = "",
    initialSelection: TextRange = TextRange(initialText.length),
    initialTextObfuscationMode: TextObfuscationMode = OudsPasswordInputDefaults.TextObfuscationMode
) = rememberSaveable(saver = OudsPasswordInputState.Saver) {
    OudsPasswordInputState(initialText, initialSelection, initialTextObfuscationMode)
}

/**
 * The editable text state of a password input, including the [text] itself, the position of the
 * cursor or selection, and the password visibility.
 *
 * To change the password input contents programmatically, call [edit], [setTextAndSelectAll],
 * [setTextAndPlaceCursorAtEnd], or [clearText]. Individual parts of the state like [text],
 * [selection], or [composition] can be read from any snapshot restart scope like Composable
 * functions. To observe these members from outside a restart scope, use `snapshotFlow {
 * passwordInputState.text }` or `snapshotFlow { passwordInputState.selection }`.
 *
 * When instantiating this class from a composable, use [rememberOudsPasswordInputState] to automatically
 * save and restore the password input state. For more advanced use cases, pass [OudsPasswordInputState.Saver] to
 * [rememberSaveable].
 */
@Stable
class OudsPasswordInputState internal constructor(
    @PublishedApi internal val textFieldState: TextFieldState,
    initialTextObfuscationMode: TextObfuscationMode,
    initialLastNonVisibleTextObfuscationMode: TextObfuscationMode
) {

    companion object {

        private val DefaultLastNonVisibleTextObfuscationMode = OudsPasswordInputDefaults.TextObfuscationMode
            .takeIf { it != TextObfuscationMode.Visible }
            .orElse { TextObfuscationMode.RevealLastTyped }

        /**
         * Saves and restores an [OudsPasswordInputState] for [rememberSaveable].
         */
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TextFieldState.Saver) { save(textFieldState) },
                        textObfuscationMode.value,
                        lastNonVisibleTextObfuscationMode.value
                    )
                }
            },
            restore = { list: List<Any?> ->
                val textFieldState = list[0]?.let { TextFieldState.Saver.restore(it) }
                val textObfuscationModes = with(TextObfuscationMode.Companion) { listOf(Visible, RevealLastTyped, Hidden) }
                val textObfuscationMode = textObfuscationModes.firstOrNull { it.value == list[1] }.orElse { OudsPasswordInputDefaults.TextObfuscationMode }
                val lastNonVisibleTextObfuscationMode =
                    textObfuscationModes.firstOrNull { it.value == list[2] }.orElse { DefaultLastNonVisibleTextObfuscationMode }
                OudsPasswordInputState(
                    textFieldState as TextFieldState,
                    textObfuscationMode,
                    lastNonVisibleTextObfuscationMode
                )
            }
        )
    }

    /**
     * Creates an instance of [OudsPasswordInputState].
     *
     * @param initialText The initial text state.
     * @param initialSelection The initial selection state.
     * @param initialTextObfuscationMode The initial method used to obscure the input text.
     */
    constructor(
        initialText: String = "",
        initialSelection: TextRange = TextRange(initialText.length),
        initialTextObfuscationMode: TextObfuscationMode = OudsPasswordInputDefaults.TextObfuscationMode
    ) : this(
        TextFieldState(initialText, initialSelection),
        initialTextObfuscationMode,
        initialTextObfuscationMode.takeIf { it != TextObfuscationMode.Visible }.orElse { DefaultLastNonVisibleTextObfuscationMode }
    )

    /**
     * The current text content. This value will automatically update when the user enters text or
     * otherwise changes the password input contents. To change it programmatically, call [edit].
     *
     * To observe changes to this property outside a restartable function, use `snapshotFlow { text
     * }`.
     *
     * @see TextFieldState.text
     */
    val text: CharSequence
        get() = textFieldState.text

    /**
     * The current selection range. If the selection is collapsed, it represents cursor location.
     * This value will automatically update when the user enters text or otherwise changes the password
     * input selection range. To change it programmatically, call [edit].
     *
     * To observe changes to this property outside a restartable function, use `snapshotFlow {
     * selection }`.
     *
     * @see TextFieldState.selection
     */
    val selection: TextRange
        get() = textFieldState.selection

    /**
     * The current composing range dictated by the IME. If null, there is no composing region.
     *
     * To observe changes to this property outside a restartable function, use `snapshotFlow {
     * composition }`.
     *
     * @see TextFieldState.composition
     */
    val composition: TextRange?
        get() = textFieldState.composition

    /**
     * Runs [block] with a mutable version of the current state. The block can make changes to the
     * text and cursor/selection. See the documentation on [TextFieldBuffer] for a more detailed
     * description of the available operations.
     *
     * Make sure that you do not make concurrent calls to this function or call it again inside
     * [block]'s scope. Doing either of these actions will result in triggering an
     * [IllegalStateException].
     *
     * @see TextFieldState.edit
     */
    inline fun edit(block: TextFieldBuffer.() -> Unit): Unit = textFieldState.edit(block)

    override fun toString(): String = textFieldState.toString()

    @ExperimentalFoundationApi
    val undoState: UndoState = textFieldState.undoState

    private var _textObfuscationMode: TextObfuscationMode by mutableStateOf(initialTextObfuscationMode)

    /**
     * The method used to obscure the input text.
     */
    var textObfuscationMode: TextObfuscationMode
        get() = _textObfuscationMode
        set(value) {
            if (value != TextObfuscationMode.Visible) {
                lastNonVisibleTextObfuscationMode = value
            }
            _textObfuscationMode = value
        }

    internal var lastNonVisibleTextObfuscationMode: TextObfuscationMode by mutableStateOf(initialLastNonVisibleTextObfuscationMode)
        private set
}

/**
 * Sets the text in this [OudsPasswordInputState] to [text], replacing any text that was previously there,
 * and places the cursor at the end of the new text.
 *
 * To perform more complicated edits on the text, call [OudsPasswordInputState.edit]. This function is
 * equivalent to calling:
 * ```
 * edit {
 *   replace(0, length, text)
 *   placeCursorAtEnd()
 * }
 * ```
 *
 * @see TextFieldState.setTextAndPlaceCursorAtEnd
 */
fun OudsPasswordInputState.setTextAndPlaceCursorAtEnd(text: String): Unit = textFieldState.setTextAndPlaceCursorAtEnd(text)

/**
 * Sets the text in this [OudsPasswordInputState] to [text], replacing any text that was previously there,
 * and selects all the text.
 *
 * To perform more complicated edits on the text, call [OudsPasswordInputState.edit]. This function is
 * equivalent to calling:
 * ```
 * edit {
 *   replace(0, length, text)
 *   selectAll()
 * }
 * ```
 *
 * @see TextFieldState.setTextAndSelectAll
 */
fun OudsPasswordInputState.setTextAndSelectAll(text: String): Unit = textFieldState.setTextAndSelectAll(text)

/**
 * Deletes all the text in the state.
 *
 * To perform more complicated edits on the text, call [OudsPasswordInputState.edit]. This function is
 * equivalent to calling:
 * ```
 * edit {
 *   delete(0, length)
 *   placeCursorAtEnd()
 * }
 * ```
 *
 * @see TextFieldState.clearText
 */
fun OudsPasswordInputState.clearText(): Unit = textFieldState.clearText()

/**
 * Creates a temporary, mutable [TextFieldBuffer] representing the current state of this
 * [OudsPasswordInputState].
 *
 * Use a [TextFieldBuffer] to apply transformations for testing purposes.
 *
 * This is similar to calling [OudsPasswordInputState.edit], but without committing the changes back to the
 * [OudsPasswordInputState].
 *
 * **Important:** A [TextFieldBuffer] is intended for short-term use. Let the garbage collector
 * dispose of it when you're finished to avoid unnecessary memory usage.
 *
 * @see TextFieldState.toTextFieldBuffer
 */
fun OudsPasswordInputState.toTextFieldBuffer(): TextFieldBuffer = textFieldState.toTextFieldBuffer()
