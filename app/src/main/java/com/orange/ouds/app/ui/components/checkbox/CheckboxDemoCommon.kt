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

package com.orange.ouds.app.ui.components.checkbox

import androidx.compose.ui.state.ToggleableState

/**
 * Represents the different identifiers that can be used for a Checkbox in demo screens.
 */
enum class CheckboxIdentifier {
    First, Second
}

/**
 * Returns the next [ToggleableState] in the sequence:
 * [ToggleableState.On] -> [ToggleableState.Off] -> [ToggleableState.Indeterminate] -> [ToggleableState.On].
 *
 * @param toggleableState The current [ToggleableState].
 * @return The next [ToggleableState] in the sequence.
 */
fun getNewToggleableState(toggleableState: ToggleableState) = when (toggleableState) {
    ToggleableState.On -> ToggleableState.Off
    ToggleableState.Off -> ToggleableState.Indeterminate
    ToggleableState.Indeterminate -> ToggleableState.On
}