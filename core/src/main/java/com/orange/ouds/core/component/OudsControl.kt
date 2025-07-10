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

internal object OudsControl {

    enum class State {
        Enabled, Hovered, Pressed, Disabled, Focused
    }
}

@Composable
internal fun getControlState(enabled: Boolean, interactionState: InteractionState): OudsControl.State {
    return getPreviewEnumEntry<OudsControl.State>().orElse {
        when {
            !enabled -> OudsControl.State.Disabled
            interactionState == InteractionState.Hovered -> OudsControl.State.Hovered
            interactionState == InteractionState.Pressed -> OudsControl.State.Pressed
            interactionState == InteractionState.Focused -> OudsControl.State.Focused
            else -> OudsControl.State.Enabled
        }
    }
}
