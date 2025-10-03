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
import com.orange.ouds.core.extensions.InteractionState
import com.orange.ouds.core.utilities.getPreviewEnumEntry
import com.orange.ouds.foundation.extensions.orElse

internal enum class OudsControlState {
    Enabled, Hovered, Pressed, Disabled, Focused
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
