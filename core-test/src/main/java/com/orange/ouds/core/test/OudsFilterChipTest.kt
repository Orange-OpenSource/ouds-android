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
import com.orange.ouds.core.component.OudsFilterChipPreviewParameter
import com.orange.ouds.core.component.OudsFilterChipPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsFilterChip
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OudsFilterChipTest(private val parameter: OudsFilterChipPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsFilterChipPreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsFilterChip(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}
