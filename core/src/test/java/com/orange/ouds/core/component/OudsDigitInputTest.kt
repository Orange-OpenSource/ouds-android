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
import com.orange.ouds.core.test.OudsSnapshotTest
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.getPreviewOrangeFontFamily
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
internal class OudsDigitInputTest {

    @RunWith(Parameterized::class)
    class Default(val parameter: OudsDigitInputPreviewParameter) : OudsSnapshotTest(OrangeTheme(getPreviewOrangeFontFamily())) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsDigitInputPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsDigitInput(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter
            )
        }
    }

    @RunWith(Parameterized::class)
    class WithRoundedCorners(val parameter: Boolean) : OudsSnapshotTest(OrangeTheme(getPreviewOrangeFontFamily())) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsDigitInputWithRoundedCornersPreviewParameterProvider().values.toList()
        }

        @Composable
        override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
            PreviewOudsDigitInputWithRoundedCorners(
                theme = theme,
                outlined = parameter
            )
        }

        override fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = darkThemeEnabled || highContrastModeEnabled
    }
}
