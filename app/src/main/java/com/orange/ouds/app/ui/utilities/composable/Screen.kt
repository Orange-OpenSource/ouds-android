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

package com.orange.ouds.app.ui.utilities.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.listItemHorizontalPadding
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun Screen(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OudsTheme.colorScheme.background.primary)
    ) {
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoScreen(
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
    codeSnippet: Code.Builder.() -> Unit,
    demoContent: @Composable () -> Unit,
    demoContentOnColoredBox: Boolean = false,
    demoContentPaddingValues: PaddingValues = PaddingValues(horizontal = OudsTheme.grids.margin),
    description: String? = null,
    version: String? = null
) {
    Screen {
        CustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = bottomSheetContent
        ) {
            if (description != null) {
                DetailScreenDescription(
                    modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium),
                    description = description
                )
            }
            val demoContentModifier = Modifier.padding(paddingValues = demoContentPaddingValues)
            if (!demoContentOnColoredBox) {
                LightDarkDemo(modifier = demoContentModifier, content = demoContent)
            } else {
                OnColoredBoxDemo(modifier = demoContentModifier, content = demoContent)
            }
            CodeSnippet(
                modifier = Modifier
                    .padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.medium)
                    .padding(top = OudsTheme.spaces.fixed.medium),
                init = codeSnippet
            )
            if (version != null) {
                ListItem(
                    modifier = Modifier.listItemHorizontalPadding(),
                    headlineContent = {
                        Text(
                            text = stringResource(R.string.app_components_common_version_label),
                            style = OudsTheme.typography.label.strong.large,
                            color = OudsTheme.colorScheme.content.default
                        )
                    },
                    trailingContent = {
                        Text(
                            text = version,
                            style = OudsTheme.typography.label.default.large,
                            color = OudsTheme.colorScheme.content.default
                        )
                    }
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewScreen() = OudsPreview {
    Screen {}
}
