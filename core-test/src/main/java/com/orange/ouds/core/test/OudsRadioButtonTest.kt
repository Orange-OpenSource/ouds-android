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
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsRadioButton
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OudsRadioButtonTest(private val parameter: OudsRadioButtonPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsRadioButtonPreviewParameterProvider().values.toList()
    }

    override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = false

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsRadioButton(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            highContrastModeEnabled = highContrastModeEnabled,
            parameter = parameter
        )
    }
}