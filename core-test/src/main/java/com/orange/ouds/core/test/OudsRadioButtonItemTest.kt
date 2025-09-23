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
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsRadioButtonItem
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithLongHelperText
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OudsRadioButtonItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsRadioButtonItemPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsRadioButtonItemPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsRadioButtonItem(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    @RunWith(org.junit.runners.Parameterized::class)
    class ParameterizedHighContrastMode(
        private val parameter: OudsRadioButtonItemHighContrastModePreviewParameter
    ) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsRadioButtonItemHighContrastModePreviewParameterProvider().values.toList()
        }

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !highContrastModeEnabled

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsRadioButtonItemHighContrastModeEnabled(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    class NonParameterized : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = darkThemeEnabled || highContrastModeEnabled

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsRadioButtonItemWithLongHelperText(theme = theme)
        }
    }
}