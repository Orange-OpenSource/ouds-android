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

package com.orange.ouds.app.ui.components.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun rememberButtonDemoState(
    enabled: MutableState<Boolean> = rememberSaveable { mutableStateOf(true) },
    loading: MutableState<Boolean> = rememberSaveable { mutableStateOf(false) },
    hierarchy: MutableState<TempButtonHierarchy> = rememberSaveable { mutableStateOf(TempButtonHierarchy.Default) },
    layout: MutableState<ButtonLayout> = rememberSaveable { mutableStateOf(ButtonLayout.TextOnly) }
) = remember(enabled, loading, hierarchy, layout) {
    ButtonDemoState(enabled, loading, hierarchy, layout)
}

class ButtonDemoState(
    val enabled: MutableState<Boolean>,
    val loading: MutableState<Boolean>,
    val hierarchy: MutableState<TempButtonHierarchy>,
    val layout: MutableState<ButtonLayout>
) {
    val isEnabled
        get() = enabled.value

    val isLoading
        get() = loading.value
}