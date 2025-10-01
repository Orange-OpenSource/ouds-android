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
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameter
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsSwitchItem
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithLongHelperText
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OudsSwitchItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsSwitchItemPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsSwitchItemPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsSwitchItem(
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
            PreviewOudsSwitchItemWithLongHelperText(theme = theme)
        }
    }
}
