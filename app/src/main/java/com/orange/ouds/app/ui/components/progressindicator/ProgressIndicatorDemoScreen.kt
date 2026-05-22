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

package com.orange.ouds.app.ui.components.progressindicator

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput

@Composable
fun ProgressIndicatorDemoBottomSheetContent(state: ProgressIndicatorDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_type_tech),
            chipLabels = ProgressIndicatorDemoState.Type.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = ProgressIndicatorDemoState.Type.entries.indexOf(type),
            onSelectionChange = { index: Int -> type = ProgressIndicatorDemoState.Type.entries[index] }
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_progressIndicator_progress_tech),
                value = TextFieldValue(
                    text = progressText,
                    selection = TextRange(progressText.length)
                ),
                onValueChange = { textFieldValue ->
                    progressText = textFieldValue.text.replace(',', '.').filter { it.isDigit() || it == '.' }
                },
                enabled = progressTextInputEnabled,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
                resetValue = ProgressIndicatorDemoState.InitialProgressValue.toString()
            )
        }
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_brandColor_tech),
            checked = brandColor,
            onCheckedChange = { brandColor = it }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_progressIndicator_track_tech),
            checked = track,
            onCheckedChange = { track = it }
        )
    }
}
