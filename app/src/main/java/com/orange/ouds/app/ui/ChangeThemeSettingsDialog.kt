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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.window.Dialog
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsSwitchItem
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings

@Composable
fun ChangeThemeSettingsDialog(themeState: ThemeState, onThemeSettingsChange: (OudsThemeSettings) -> Unit, onDismissRequest: () -> Unit) {
    //TODO Use OudsDialog when available
    Dialog(onDismissRequest = onDismissRequest) {
        ChangeThemeSettingsDialogContent(
            themeState = themeState,
            onThemeSettingsChange = { themeSettings ->
                onThemeSettingsChange(themeSettings)
                onDismissRequest()
            }
        )
    }
}

@Composable
private fun ChangeThemeSettingsDialogContent(themeState: ThemeState, onThemeSettingsChange: (OudsThemeSettings) -> Unit) {
    var themeSettings by rememberSaveable { mutableStateOf(themeState.settings) }
    DialogContent(
        title = stringResource(R.string.app_themeSettingsDialog_label)
    ) {
        getSupportedThemeSettings(themeState.currentTheme).forEach { themeSetting ->
            OudsSwitchItem(
                checked = when (themeSetting) {
                    ThemeSetting.RoundedCornerButtons -> themeSettings.roundedCornerButtons.orElse { false }
                },
                label = stringResource(themeSetting.titleResId),
                onCheckedChange = { checked ->
                    themeSettings = when (themeSetting) {
                        ThemeSetting.RoundedCornerButtons -> themeSettings.copy(roundedCornerButtons = checked)
                    }
                },
                divider = false
            )
        }

        Row(
            modifier = Modifier
                .padding(all = OudsTheme.spaces.fixed.large)
                .align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
        ) {
            OudsButton(
                label = stringResource(R.string.app_themeSettingsDialog_cancel_label),
                appearance = OudsButton.Appearance.Minimal,
                onClick = { onThemeSettingsChange(themeState.currentTheme.settings) }
            )
            OudsButton(
                label = stringResource(R.string.app_themeSettingsDialog_apply_label),
                onClick = { onThemeSettingsChange(themeSettings) }
            )
        }
    }
}

private fun getSupportedThemeSettings(theme: OudsThemeContract): List<ThemeSetting> {
    return ThemeSetting.entries.filter { themeSetting ->
        with(theme.settings) {
            when (themeSetting) {
                ThemeSetting.RoundedCornerButtons -> roundedCornerButtons != null
            }
        }
    }
}

object ChangeThemeSettingsDialog {

    fun isAvailable(theme: OudsThemeContract): Boolean {
        return getSupportedThemeSettings(theme).isNotEmpty()
    }
}

private enum class ThemeSetting {

    RoundedCornerButtons;

    val titleResId: Int
        @StringRes
        get() = when (this) {
            RoundedCornerButtons -> R.string.app_themeSettingsDialog_roundedCornerButtons_label
        }
}

@PreviewLightDark
@Composable
private fun PreviewChangeThemeSettingsDialogContent() = PreviewDialogContent {
    ChangeThemeSettingsDialogContent(
        themeState = rememberThemeState(),
        onThemeSettingsChange = {}
    )
}
