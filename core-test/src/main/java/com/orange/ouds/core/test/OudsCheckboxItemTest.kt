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
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsCheckboxItem
import com.orange.ouds.core.component.PreviewOudsCheckboxItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithLongHelperText
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OudsCheckboxItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsCheckboxItemPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsCheckboxItemPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsCheckboxItem(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    @RunWith(org.junit.runners.Parameterized::class)
    class ParameterizedHighContrastMode(
        private val parameter: OudsCheckboxItemHighContrastModePreviewParameter
    ) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsCheckboxItemHighContrastModePreviewParameterProvider().values.toList()
        }

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !highContrastModeEnabled

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsCheckboxItemHighContrastModeEnabled(
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
            PreviewOudsCheckboxItemWithLongHelperText(theme)
        }
    }
}