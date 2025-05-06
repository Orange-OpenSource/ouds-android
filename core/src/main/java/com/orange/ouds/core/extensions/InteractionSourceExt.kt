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

package com.orange.ouds.core.extensions

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

internal enum class InteractionState {
    None, Focused, Hovered, Pressed
}

@Composable
internal fun InteractionSource.collectInteractionStateAsState(): State<InteractionState> {
    val isFocused by collectIsFocusedAsState()
    val isHovered by collectIsHoveredAsState()
    val isPressed by collectIsPressedAsState()

    return remember {
        derivedStateOf {
            when {
                isPressed -> InteractionState.Pressed
                isHovered -> InteractionState.Hovered
                isFocused -> InteractionState.Focused
                else -> InteractionState.None
            }
        }
    }
}
