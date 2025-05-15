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

package com.orange.ouds.core.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.theme.LocalHighContrastModeEnabled
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.outerBorder

@Composable
internal fun Modifier.outerBorder(state: OudsControl.State, handleHighContrastMode: Boolean = false) =
    // To be able to distinguish the enabled and the hover states when high contrast mode is activated,
    // the hover state must display the focus border in this case
    if (state == OudsControl.State.Focused || (state == OudsControl.State.Hovered && handleHighContrastMode && LocalHighContrastModeEnabled.current)) {
         outerBorder(
             width = OudsTheme.borders.width.focus,
             color = OudsTheme.colorScheme.border.focus,
             insetWidth = OudsTheme.borders.width.focusInset,
             insetColor = OudsTheme.colorScheme.border.focusInset
         )
     } else {
         this
     }


internal object OudsControl {

    enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@Composable
internal fun rememberOudsControlState(
    enabled: Boolean,
    interactionState: InteractionState
): OudsControl.State = remember(enabled, interactionState) {
    when {
        !enabled -> OudsControl.State.Disabled
        interactionState == InteractionState.Hovered -> OudsControl.State.Hovered
        interactionState == InteractionState.Pressed -> OudsControl.State.Pressed
        interactionState == InteractionState.Focused -> OudsControl.State.Focused
        else -> OudsControl.State.Enabled
    }
}
