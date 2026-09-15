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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsDisplayTextDefaults
import com.orange.ouds.core.component.OudsDisplayTextSize

@Composable
fun rememberDisplayTextDemoState(
    text: String = stringResource(id = R.string.app_components_typography_common_text_label),
    size: OudsDisplayTextSize = OudsDisplayTextDefaults.Size,
    annotatedText: Boolean = false
) = rememberSaveable(text, size, annotatedText, saver = DisplayTextDemoState.Saver) { DisplayTextDemoState(text, size, annotatedText) }

class DisplayTextDemoState(
    text: String,
    size: OudsDisplayTextSize,
    annotatedText: Boolean
) : TypographyDemoState(text, annotatedText) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TypographyDemoState.Saver) { save(state) },
                        size
                    )
                }
            },
            restore = { list: List<Any?> ->
                val typographyDemoState = list[0]?.let { TypographyDemoState.Saver.restore(it) }
                typographyDemoState?.run {
                    DisplayTextDemoState(
                        text,
                        list[1] as OudsDisplayTextSize,
                        annotatedText
                    )
                }
            }
        )
    }

    var size by mutableStateOf(size)

}
