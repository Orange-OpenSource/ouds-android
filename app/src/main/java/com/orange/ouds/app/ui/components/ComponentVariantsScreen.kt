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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.BasicPreviewParameterProvider

@Composable
fun ComponentVariantsScreen(component: Component, onVariantClick: (id: Long) -> Unit) {
    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsTheme.spaces.fixed.medium)) {
            item {
                DetailScreenHeader(
                    modifier = Modifier.padding(bottom = OudsTheme.spaces.fixed.medium),
                    description = stringResource(id = component.descriptionRes),
                    illustration = component.illustration
                )
            }

            items(component.variants) { variant ->
                Text(
                    modifier = Modifier
                        .clickable { onVariantClick(variant.id) }
                        .fillMaxWidth()
                        .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
                    text = stringResource(id = variant.nameRes),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = OudsTheme.colorScheme.content.default,
                    style = OudsTheme.typography.heading.medium
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewComponentVariantsScreen(
    @PreviewParameter(ComponentVariantsScreenPreviewParameterProvider::class) parameter: Component
) = OudsPreview {
    ComponentVariantsScreen(
        component = parameter,
        onVariantClick = {}
    )
}

private class ComponentVariantsScreenPreviewParameterProvider : BasicPreviewParameterProvider<Component>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<Component>
    get() = listOf(Component.Checkbox)