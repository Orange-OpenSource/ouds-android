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

package com.orange.ouds.core.test

import androidx.compose.runtime.Composable
import com.orange.ouds.core.utilities.PreviewableComponent
import com.orange.ouds.theme.OudsThemeContract

abstract class OudsComponentSnapshotTest(
    val previewableComponent: PreviewableComponent,
    val parameter: Any?,
    theme: OudsThemeContract,
    widthDp: Int = -1
) : OudsSnapshotTest(theme, widthDp) {

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        previewableComponent.Preview(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            highContrastModeEnabled = highContrastModeEnabled,
            parameter = parameter
        )
    }

    override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean): Boolean {
        return !previewableComponent.isPreviewAvailable(darkThemeEnabled, highContrastModeEnabled)
    }
}
