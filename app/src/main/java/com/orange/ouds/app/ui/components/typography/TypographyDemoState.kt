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

package com.orange.ouds.app.ui.components.typography

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

open class TypographyDemoState(
    text: String,
    annotatedText: Boolean = false
) {
    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        text,
                        annotatedText
                    )
                }
            },
            restore = { list: List<Any?> ->
                TypographyDemoState(
                    list[0] as String,
                    list[1] as Boolean
                )
            }
        )
    }

    var text by mutableStateOf(text)

    var annotatedText: Boolean by mutableStateOf(annotatedText)
}