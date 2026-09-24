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
    Enabled, Hovered, Focused, Pressed, ReadOnly, Disabled, Skeleton
}

@Composable
internal fun getControlState(enabled: Boolean, readOnly: Boolean, skeleton: OudsSkeleton?, interactionState: InteractionState): OudsControlState {
    return getPreviewEnumEntry<OudsControlState>().orElse {
        when {
            skeleton != null -> OudsControlState.Skeleton
            !enabled -> OudsControlState.Disabled
            readOnly -> OudsControlState.ReadOnly
            interactionState == InteractionState.Hovered -> OudsControlState.Hovered
            interactionState == InteractionState.Pressed -> OudsControlState.Pressed
            interactionState == InteractionState.Focused -> OudsControlState.Focused
            else -> OudsControlState.Enabled
        }
    }
}

@Composable
internal fun errorColor(state: OudsControlState): Color = with(OudsTheme.colorScheme.action.negative) {
    when (state) {
        OudsControlState.Enabled -> enabled
        OudsControlState.Disabled, OudsControlState.ReadOnly -> Color.Unspecified // Not allowed, exception thrown at the beginning of OudsCheckbox
        OudsControlState.Hovered -> hover
        OudsControlState.Pressed -> pressed
        OudsControlState.Focused -> focus
        OudsControlState.Skeleton -> Color.Transparent
    }
}