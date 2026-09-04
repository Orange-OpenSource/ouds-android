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
import com.orange.ouds.core.component.OudsBodyTextDefaults
import com.orange.ouds.core.component.OudsBodyTextSize
import com.orange.ouds.core.component.OudsTextWeight

@Composable
fun rememberBodyTextDemoState(
    text: String = stringResource(id = R.string.app_components_typography_common_text_label),
    size: OudsBodyTextSize = OudsBodyTextDefaults.Size,
    weight: OudsTextWeight = OudsBodyTextDefaults.Weight,
    annotatedText: Boolean = false
) = rememberSaveable(text, size, weight, annotatedText, saver = BodyTextDemoState.Saver) { BodyTextDemoState(text, size, weight, annotatedText) }

class BodyTextDemoState(
    text: String,
    size: OudsBodyTextSize,
    weight: OudsTextWeight,
    annotatedText: Boolean
) : TypographyDemoState(text, annotatedText) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TypographyDemoState.Saver) { save(state) },
                        size,
                        weight
                    )
                }
            },
            restore = { list: List<Any?> ->
                val typographyDemoState = list[0]?.let { TypographyDemoState.Saver.restore(it) }
                typographyDemoState?.run {
                    BodyTextDemoState(
                        text,
                        list[1] as OudsBodyTextSize,
                        list[2] as OudsTextWeight,
                        annotatedText
                    )
                }
            }
        )
    }

    var size by mutableStateOf(size)

    var weight by mutableStateOf(weight)
}
