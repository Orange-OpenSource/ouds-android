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
import androidx.compose.ui.graphics.Color
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse

internal enum class OudsControlState {
    Enabled, Hovered, Pressed, Disabled, Focused;

    @Composable
    fun errorColor(): Color = with(OudsTheme.colorScheme.action.negative) {
        when (this@OudsControlState) {
            Enabled -> enabled
            Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
            Hovered -> hover
            Pressed -> pressed
            Focused -> focus
        }
    }
}

@Composable
internal fun getControlState(enabled: Boolean, interactionState: InteractionState): OudsControlState {
    return getPreviewEnumEntry<OudsControlState>().orElse {
        when {
            !enabled -> OudsControlState.Disabled
            interactionState == InteractionState.Hovered -> OudsControlState.Hovered
            interactionState == InteractionState.Pressed -> OudsControlState.Pressed
            interactionState == InteractionState.Focused -> OudsControlState.Focused
            else -> OudsControlState.Enabled
        }
    }
}

@Composable
internal fun controlErrorColor(state: OudsControlState): Color {
    return with(OudsTheme.colorScheme.action) {
        when (state) {
            OudsControlState.Enabled -> negative.enabled
            OudsControlState.Disabled -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
            OudsControlState.Hovered -> negative.hover
            OudsControlState.Pressed -> negative.pressed
            OudsControlState.Focused -> negative.focus
        }
    }
}
