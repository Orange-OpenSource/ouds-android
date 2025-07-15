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

package com.orange.ouds.app.ui.utilities

import androidx.compose.foundation.Indication
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse

// TODO Remove when OudsListItem is available
@Composable
fun Modifier.listItemHorizontalPadding() = padding(horizontal = max(OudsTheme.grids.margin - 16.dp, 0.dp))

/**
 * This modifier is equivalent to clickable except it consumes pointer change events during the initial pass instead of the main one,
 * meaning that a parent will consume events before its children.
 */
@Composable
fun Modifier.priorityClickable(
    interactionSource: MutableInteractionSource?,
    indication: Indication?,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier {
    return semantics {
        if (!enabled) {
            disabled()
        }
        role?.let { this.role = it }
        onClick(label = onClickLabel) {
            onClick()
            true
        }
    }
        .run {
            if (enabled) {
                @Suppress("NAME_SHADOWING")
                val interactionSource = interactionSource.orElse { remember { MutableInteractionSource() } }
                var press by remember { mutableStateOf(PressInteraction.Press(Offset.Zero)) }

                indication(interactionSource, indication)
                    .pointerInput(interactionSource, onClick) {
                        awaitEachGesture {
                            val down = awaitFirstDown(pass = PointerEventPass.Initial, requireUnconsumed = true)
                            down.consume()
                            press = PressInteraction.Press(down.position)
                            interactionSource.tryEmit(press)

                            val up = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                            if (up != null) {
                                up.consume()
                                onClick()
                                interactionSource.tryEmit(PressInteraction.Release(press))
                            } else {
                                interactionSource.tryEmit(PressInteraction.Cancel(press))
                            }
                        }
                    }
            } else {
                this
            }
        }
}
