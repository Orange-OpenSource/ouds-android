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
import com.orange.ouds.core.component.PreviewOudsInputTag

class OudsInputTagTest() : OudsSnapshotTest(OudsComponentTestSuite.theme) {

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsInputTag(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled
        )
    }
}