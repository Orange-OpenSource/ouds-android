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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OudsBadgeTest(private val parameter: OudsBadgePreviewParameter) : OudsSnapshotTest(OudsBadgePreview.widthDp) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsBadgePreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsBadge(
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}
