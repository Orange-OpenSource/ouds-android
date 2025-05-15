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

package com.orange.ouds.core.component

import androidx.compose.runtime.Composable
import com.orange.ouds.OudsSnapshotTest
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
internal class OudsRadioButtonItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsRadioButtonItemPreviewParameter) : OudsSnapshotTest() {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsRadioButtonItemPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsRadioButtonItem(
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    class NonParameterized : OudsSnapshotTest() {

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = darkThemeEnabled || highContrastModeEnabled

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsRadioButtonItemWithLongHelperText()
        }
    }
}