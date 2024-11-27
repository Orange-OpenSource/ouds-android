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

package com.orange.ouds.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.app.ui.utilities.composable.DetailScreenDescription
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

@Composable
fun ComponentDetailScreen(component: Component) {
    Screen {
        Column {
            DetailScreenDescription(modifier = Modifier.padding(all = OudsSpaceKeyToken.Fixed.Medium.value), descriptionRes = component.descriptionRes)
            component.demoScreen()
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewComponentDetailScreen(
    @PreviewParameter(ComponentDetailScreenPreviewParameterProvider::class) parameter: Component
) = OudsPreview {
    ComponentDetailScreen(parameter)
}

private class ComponentDetailScreenPreviewParameterProvider : BasicPreviewParameterProvider<Component>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<Component>
    get() = listOf(Component.Button)