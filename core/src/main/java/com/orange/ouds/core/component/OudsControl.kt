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
import com.orange.ouds.core.component.OudsControlState.Disabled
import com.orange.ouds.core.component.OudsControlState.Enabled
import com.orange.ouds.core.component.OudsControlState.Focused
import com.orange.ouds.core.component.OudsControlState.Hovered
import com.orange.ouds.core.component.OudsControlState.Pressed
import com.orange.ouds.core.component.OudsControlState.ReadOnly
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse

internal enum class OudsControlState {
    Enabled, Hovered, Focused, Pressed, ReadOnly, Disabled
}

@Composable
internal fun getControlState(enabled: Boolean, readOnly: Boolean, interactionState: InteractionState): OudsControlState {
    return getPreviewEnumEntry<OudsControlState>().orElse {
        when {
            !enabled -> Disabled
            readOnly -> ReadOnly
            interactionState == InteractionState.Hovered -> Hovered
            interactionState == InteractionState.Pressed -> Pressed
            interactionState == InteractionState.Focused -> Focused
            else -> Enabled
        }
    }
}

@Composable
internal fun errorColor(state: OudsControlState): Color = with(OudsTheme.colorScheme.action.negative) {
    when (state) {
        Enabled -> enabled
        Disabled, ReadOnly -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
        Hovered -> hover
        Pressed -> pressed
        Focused -> focus
    }
}