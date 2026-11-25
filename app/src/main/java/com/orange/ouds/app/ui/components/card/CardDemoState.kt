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
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import com.orange.ouds.core.component.OudsCardDefaults
import com.orange.ouds.core.component.OudsCardOnBackgroundColor

@Composable
fun rememberCardDemoState(
    style: BaseCardDemoState.Style = BaseCardDemoState.Style.Filled,
    background: Boolean = OudsCardDefaults.Background,
    alternativeBackground: Boolean = OudsCardDefaults.AlternativeBackground,
    elevatedCardOnBackgroundColor: OudsCardOnBackgroundColor = OudsCardDefaults.OnBackgroundColor,
    divider: Boolean = false,
    contentExample: BaseCardDemoState.ContentExample = BaseCardDemoState.ContentExample.Empty,
    enabled: Boolean = true,
    skeleton: Boolean = false
) = rememberSaveable(style, background, divider, contentExample, enabled, skeleton, saver = CardDemoState.Saver) {
    CardDemoState(style, background, alternativeBackground, elevatedCardOnBackgroundColor, divider, contentExample, enabled, skeleton)
}

class CardDemoState(
    style: Style,
    background: Boolean,
    alternativeBackground: Boolean,
    elevatedCardOnBackgroundColor: OudsCardOnBackgroundColor,
    divider: Boolean,
    contentExample: ContentExample,
    enabled: Boolean,
    skeleton: Boolean
) : BaseCardDemoState(style, background, alternativeBackground, elevatedCardOnBackgroundColor, divider, contentExample, enabled, skeleton) {

    companion object {
        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                listOf(
                    with(BaseCardDemoState.Saver) { save(state) }
                )
            },
            restore = { list: List<Any?> ->
                val baseCardDemoState = list[0]?.let { BaseCardDemoState.Saver.restore(it) }
                baseCardDemoState?.run {
                    CardDemoState(
                        style,
                        background,
                        alternativeBackground,
                        elevatedCardOnBackgroundColor,
                        divider,
                        contentExample,
                        enabled,
                        skeleton
                    )
                }
            }
        )
    }

}
