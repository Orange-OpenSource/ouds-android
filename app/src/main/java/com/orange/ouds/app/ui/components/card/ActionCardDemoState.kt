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

package com.orange.ouds.app.ui.components.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberActionCardDemoState(
    style: BaseCardDemoState.Style = BaseCardDemoState.Style.Filled,
    background: Boolean = true,
    divider: Boolean = false,
    contentExample: BaseCardDemoState.ContentExample = BaseCardDemoState.ContentExample.SimpleContent,
    enabled: Boolean = true,
    hasLoader: Boolean = false,
    skeleton: Boolean = false
) = rememberSaveable(style, background, divider, contentExample, enabled, hasLoader, skeleton, saver = ActionCardDemoState.Saver) {
    ActionCardDemoState(style, background, divider, contentExample, enabled, hasLoader, skeleton)
}

class ActionCardDemoState(
    style: Style,
    background: Boolean,
    divider: Boolean,
    contentExample: ContentExample,
    enabled: Boolean,
    hasLoader: Boolean,
    skeleton: Boolean
) : BaseCardDemoState(style, background, divider, contentExample, enabled, skeleton) {

    companion object {
        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        hasLoader,
                        with(BaseCardDemoState.Saver) { save(state) }
                    )
                }
            },
            restore = { list: List<Any?> ->
                val baseCardDemoState = list[1]?.let { BaseCardDemoState.Saver.restore(it) }
                baseCardDemoState?.run {
                    ActionCardDemoState(
                        style,
                        background,
                        divider,
                        contentExample,
                        enabled,
                        list[0] as Boolean,
                        skeleton
                    )
                }
            }
        )
    }

    var hasLoader: Boolean by mutableStateOf(hasLoader)

    val loaderSwitchEnabled: Boolean
        get() = enabled
}
