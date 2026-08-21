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
import com.orange.ouds.core.component.OudsHeadingTextSize

@Composable
fun rememberHeadingTextDemoState(
    text: String = stringResource(id = R.string.app_components_typography_common_text_label),
    size: HeadingTextDemoState.Size = HeadingTextDemoState.Size.Large,
    headingLargeMarker: Boolean = true,
    annotatedText: Boolean = false
) = rememberSaveable(text, size, headingLargeMarker, annotatedText, saver = HeadingTextDemoState.Saver) {
    HeadingTextDemoState(
        text,
        size,
        headingLargeMarker,
        annotatedText
    )
}

class HeadingTextDemoState(
    text: String,
    size: Size,
    headingLargeMarker: Boolean,
    annotatedText: Boolean
) : TypographyDemoState(text) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        with(TypographyDemoState.Saver) { save(state) },
                        size,
                        headingLargeMarker,
                        annotatedText
                    )
                }
            },
            restore = { list: List<Any?> ->
                val typographyDemoState = list[0]?.let { TypographyDemoState.Saver.restore(it) }
                typographyDemoState?.run {
                    HeadingTextDemoState(
                        text,
                        list[1] as Size,
                        list[2] as Boolean,
                        list[3] as Boolean
                    )
                }
            }
        )
    }

    var size by mutableStateOf(size)

    var headingLargeMarker by mutableStateOf(headingLargeMarker)
    val headingLargeMarkerSwitchEnabled
        get() = size == Size.Large

    var annotatedText: Boolean by mutableStateOf(annotatedText)

    enum class Size {
        ExtraLarge, Large, Medium, Small;

        fun toOudsHeadingTextSize(marker: Boolean) = when (this) {
            ExtraLarge -> OudsHeadingTextSize.ExtraLarge
            Large -> OudsHeadingTextSize.Large(marker = marker)
            Medium -> OudsHeadingTextSize.Medium
            Small -> OudsHeadingTextSize.Small
        }
    }
}