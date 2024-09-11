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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.BasicPreviewParameterProvider
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.UiModePreviews
import com.orange.ouds.theme.fromToken
import com.orange.ouds.theme.tokens.semantic.fromToken

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
        shape = RoundedCornerShape(OudsTheme.borderTokens.fromToken(OudsTheme.componentsTokens.button.cornerRadius)),
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() },
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = OudsTheme.elevationTokens.fromToken(OudsTheme.componentsTokens.button.defaultElevation),
            pressedElevation = OudsTheme.elevationTokens.fromToken(OudsTheme.componentsTokens.button.pressedElevation),
            focusedElevation = OudsTheme.elevationTokens.fromToken(OudsTheme.componentsTokens.button.focusedElevation),
            hoveredElevation = OudsTheme.elevationTokens.fromToken(OudsTheme.componentsTokens.button.hoveredElevation),
            disabledElevation = OudsTheme.elevationTokens.fromToken(OudsTheme.componentsTokens.button.disabledElevation),
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.colorScheme.fromToken(OudsTheme.componentsTokens.button.containerColor),
            contentColor = OudsTheme.colorScheme.fromToken(OudsTheme.componentsTokens.button.contentColor),
            disabledContainerColor = OudsTheme.colorScheme.fromToken(OudsTheme.componentsTokens.button.disabledContainerColor),
            disabledContentColor = OudsTheme.colorScheme.fromToken(OudsTheme.componentsTokens.button.disabledContentColor),
        )
    ) {
        Text(
            text = text,
            modifier = modifier,
        )
    }
}

@Suppress("PreviewShouldNotBeCalledRecursively")
@UiModePreviews.Button
@Composable
private fun PreviewOudsButton(@PreviewParameter(OudsButtonPreviewParameterProvider::class) parameter: OudsButtonPreviewParameter) = OudsPreview {
    PreviewOudsButton(darkThemeEnabled = isSystemInDarkTheme(), parameter = parameter)
}

@Composable
internal fun PreviewOudsButton(darkThemeEnabled: Boolean, parameter: OudsButtonPreviewParameter) = OudsPreview(darkThemeEnabled) {
    with(parameter) {
        OudsButton(text = "Text", onClick = {}, enabled = enabled)
    }
}

internal data class OudsButtonPreviewParameter(
    val enabled: Boolean = true
)

internal class OudsButtonPreviewParameterProvider :
    BasicPreviewParameterProvider<OudsButtonPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OudsButtonPreviewParameter>
    get() = mutableListOf<OudsButtonPreviewParameter>().apply {
        add(OudsButtonPreviewParameter())
        add(OudsButtonPreviewParameter(enabled = false))
    }

