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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken

@Composable
fun OudsButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    with(OudsTheme.componentsTokens.button) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = RoundedCornerShape(cornerRadius.value),
            modifier = modifier,
            contentPadding = PaddingValues(
                vertical = verticalContentPadding.value,
                horizontal = horizontalContentPadding.value
            ),
            interactionSource = remember { MutableInteractionSource() },
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = defaultElevation.value,
                pressedElevation = pressedElevation.value,
                focusedElevation = focusedElevation.value,
                hoveredElevation = hoveredElevation.value,
                disabledElevation = disabledElevation.value,
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor.value,
                contentColor = contentColor.value,
                disabledContainerColor = disabledContainerColor.value,
                disabledContentColor = disabledContentColor.value,
            )
        ) {
            Text(
                modifier = modifier,
                text = text,
                style = labelStyle.value,
                color = OudsColorKeyToken.ContentOnActionPrimaryEnabled.value
            )
        }
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

