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

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.insert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalClipboard
import com.orange.ouds.core.theme.OudsTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import ouds_android.core.generated.resources.Res
import ouds_android.core.generated.resources.core_pinCodeInput_paste_label

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal actual fun OudsPinCodeInputTooltipBox(
    textFieldState: TextFieldState,
    length: OudsPinCodeInputLength,
    content: @Composable () -> Unit
) {
    val tooltipState = rememberTooltipState(isPersistent = true)
    // TODO: Replace with OUDS tooltip when available 
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
            positioning = TooltipAnchorPosition.Above,
            spacingBetweenTooltipAndAnchor = OudsTheme.spaces.fixed.extraSmall
        ),
        tooltip = {
            val clipboard = LocalClipboard.current
            val scope = rememberCoroutineScope()
            RichTooltip {
                CompositionLocalProvider(LocalRippleConfiguration provides null) {
                    TextButton(
                        onClick = {
                            scope.launch {
                                clipboard.getClipEntry()?.clipData?.let { clipData ->
                                    if (clipData.itemCount > 0) {
                                        val text = clipData.getItemAt(0).text.toString()
                                        textFieldState.edit {
                                            insert(selection.min, text)
                                            with(inputTransformation(length)) {
                                                transformInput()
                                            }
                                        }
                                    }
                                }
                                tooltipState.dismiss()
                            }
                        }
                    ) {
                        Text(text = stringResource(Res.string.core_pinCodeInput_paste_label))
                    }
                }
            }
        },
        state = tooltipState,
        content = content
    )
}
