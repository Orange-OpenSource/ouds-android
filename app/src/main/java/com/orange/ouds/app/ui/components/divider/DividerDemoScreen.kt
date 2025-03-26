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

package com.orange.ouds.app.ui.components.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.CustomizationBottomSheetScaffold
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.composable.LightDarkDemo
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsDivider
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsVerticalDivider
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DividerDemoScreen(vertical: Boolean = false) = DemoScreen(rememberDividerDemoState()) {
    CustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            val colors = OudsDivider.Color.entries
            CustomizationDropdownMenu(
                label = stringResource(id = R.string.app_components_common_color_label),
                itemLabels = colors.map { it.formattedName },
                selectedItemIndex = colors.indexOf(color),
                onSelectionChange = { color = colors[it] },
                itemLeadingIcons = colors.map { color ->
                    {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color.value)
                        )
                    }
                }

            )
        }
    ) {
        LightDarkDemo {
            if (vertical) {
                OudsVerticalDivider(
                    modifier = Modifier.height(50.dp),
                    color = color
                )
            } else {
                OudsHorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = OudsTheme.spaces.fixed.medium),
                    color = color
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewHorizontalDividerDemoScreen() = OudsPreview {
    DividerDemoScreen()
}

@PreviewLightDark
@Composable
private fun PreviewVerticalDividerDemoScreen() = OudsPreview {
    DividerDemoScreen(vertical = true)
}