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

package com.orange.ouds.core.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.colors.fromToken

@Composable
fun OudsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.colors.fromToken(OudsTheme.componentsTokens.button.containerColor),
            contentColor = OudsTheme.colors.fromToken(OudsTheme.componentsTokens.button.contentColor),
            disabledContainerColor = OudsTheme.colors.fromToken(OudsTheme.componentsTokens.button.disabledContainerColor),
            disabledContentColor = OudsTheme.colors.fromToken(OudsTheme.componentsTokens.button.disabledContentColor),
        )
    ) {
        Text(
            text = text,
            modifier = modifier,
        )
    }
}