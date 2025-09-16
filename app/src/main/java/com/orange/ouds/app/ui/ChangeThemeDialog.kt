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

package com.orange.ouds.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.window.Dialog
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun ChangeThemeDialog(themeState: ThemeState, onThemeChange: (String) -> Unit, onDismissRequest: () -> Unit) {
    //TODO Use OudsDialog when available
    Dialog(onDismissRequest = onDismissRequest) {
        ChangeThemeDialogContent(
            themeState = themeState,
            onThemeChange = { themeName ->
                onThemeChange(themeName)
                onDismissRequest()
            }
        )
    }
}

@Composable
private fun ChangeThemeDialogContent(themeState: ThemeState, onThemeChange: (String) -> Unit) {
    DialogContent(
        title = stringResource(R.string.app_themeDialog_label),
        contentPadding = PaddingValues(bottom = OudsTheme.spaces.fixed.extraSmall)
    ) {
        Column(modifier = Modifier.selectableGroup()) {
            themeState.themes.forEach { theme ->
                OudsRadioButtonItem(
                    selected = theme.name == themeState.currentTheme.name,
                    label = theme.name,
                    onClick = { onThemeChange(theme.name) },
                    divider = false
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewChangeThemeDialogContent() = PreviewDialogContent {
    ChangeThemeDialogContent(
        themeState = rememberThemeState(),
        onThemeChange = {}
    )
}
