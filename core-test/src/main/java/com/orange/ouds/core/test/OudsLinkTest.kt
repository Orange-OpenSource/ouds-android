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
import com.orange.ouds.core.component.OudsLinkPreviewParameter
import com.orange.ouds.core.component.OudsLinkPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsLink
import com.orange.ouds.core.component.PreviewOudsLinkOnTwoLines
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OudsLinkTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsLinkPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {
        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsLinkPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsLink(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    class NonParameterized() : OudsSnapshotTest(OudsComponentTestSuite.theme) {

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = darkThemeEnabled || highContrastModeEnabled

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsLinkOnTwoLines(theme = theme)
        }
    }
}