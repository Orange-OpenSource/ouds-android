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
import com.orange.ouds.core.component.OudsLabelTextDefaults
import com.orange.ouds.core.component.OudsLabelTextSize
import com.orange.ouds.core.component.OudsTextWeight

@Composable
fun rememberLabelTextDemoState(
    text: String = stringResource(id = R.string.app_components_typography_common_text_label),
    size: OudsLabelTextSize = OudsLabelTextDefaults.Size,
    weight: OudsTextWeight = OudsLabelTextDefaults.Weight,
    annotatedText: Boolean = false
) = rememberSaveable(text, size, weight, annotatedText, saver = LabelTextDemoState.Saver) { LabelTextDemoState(text, size, weight, annotatedText) }

class LabelTextDemoState(
    text: String,
    size: OudsLabelTextSize,
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
                    LabelTextDemoState(
                        text,
                        list[1] as OudsLabelTextSize,
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
