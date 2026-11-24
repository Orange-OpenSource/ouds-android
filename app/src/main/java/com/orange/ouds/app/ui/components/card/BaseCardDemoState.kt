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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.OudsElevatedCardOnBackgroundColor

open class BaseCardDemoState(
    style: Style,
    background: Boolean,
    alternativeBackground: Boolean,
    elevatedCardOnBackgroundColor: OudsElevatedCardOnBackgroundColor,
    divider: Boolean,
    contentExample: ContentExample,
    enabled: Boolean,
    skeleton: Boolean
) {

    companion object {

        val DemoCardWidth = 300.dp

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
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
            },
            restore = { list: List<Any> ->
                BaseCardDemoState(
                    list[0] as Style,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as OudsElevatedCardOnBackgroundColor,
                    list[4] as Boolean,
                    list[5] as ContentExample,
                    list[6] as Boolean,
                    list[7] as Boolean
                )
            }
        )
    }

    var style: Style by mutableStateOf(style)

    var background: Boolean by mutableStateOf(background)

    var alternativeBackground: Boolean by mutableStateOf(alternativeBackground)

    var elevatedCardOnBackgroundColor: OudsElevatedCardOnBackgroundColor by mutableStateOf(elevatedCardOnBackgroundColor)

    var divider: Boolean by mutableStateOf(divider)

    var contentExample: ContentExample by mutableStateOf(contentExample)

    var enabled: Boolean by mutableStateOf(enabled)

    var skeleton: Boolean by mutableStateOf(skeleton)

    val demoContentOnColor: OudsColoredBoxColor?
        get() = if (style == Style.Elevated) elevatedCardOnBackgroundColor.toOudsColoredBoxColor() else null

    val elevatedCardSettingsEnabled: Boolean
        get() = style == Style.Elevated

    val filledCardSettingsEnabled: Boolean
        get() = style == Style.Filled

    enum class Style {
        Filled, Outlined, Elevated
    }

    enum class ContentExample {
        Empty, UseCase
    }
}

fun OudsElevatedCardOnBackgroundColor.toOudsColoredBoxColor(): OudsColoredBoxColor? = when (this) {
    OudsElevatedCardOnBackgroundColor.Default -> null
    OudsElevatedCardOnBackgroundColor.BackgroundPrimary -> OudsColoredBoxColor.BackgroundPrimary
    OudsElevatedCardOnBackgroundColor.BackgroundSecondary -> OudsColoredBoxColor.BackgroundSecondary
    OudsElevatedCardOnBackgroundColor.BackgroundTertiary -> OudsColoredBoxColor.BackgroundTertiary
    OudsElevatedCardOnBackgroundColor.BackgroundInverseLow -> OudsColoredBoxColor.BackgroundInverseLow
    OudsElevatedCardOnBackgroundColor.BackgroundInverseHigh -> OudsColoredBoxColor.BackgroundInverseHigh
    OudsElevatedCardOnBackgroundColor.OverlayModalSheet -> OudsColoredBoxColor.OverlayModal
}