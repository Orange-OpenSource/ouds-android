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
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameter
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsSuggestionChip
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OudsSuggestionChipTest(private val parameter: OudsSuggestionChipPreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsSuggestionChipPreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsSuggestionChip(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}