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
import com.orange.ouds.core.component.OudsBadgePreviewParameter
import com.orange.ouds.core.component.OudsBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsBadgePreviewWidthDp
import com.orange.ouds.core.component.PreviewOudsBadge
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OudsBadgeTest(private val parameter: OudsBadgePreviewParameter) : OudsSnapshotTest(OudsComponentTestSuite.theme, OudsBadgePreviewWidthDp) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsBadgePreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsBadge(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}
