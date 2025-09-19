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
import com.orange.ouds.core.component.OudsBadgePreview
import com.orange.ouds.core.component.OudsBadgePreviewParameter
import com.orange.ouds.core.component.OudsBadgePreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsBadge
import com.orange.ouds.theme.OudsThemeContract
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
abstract class OudsBadgeTest(
    private val parameter: OudsBadgePreviewParameter,
    theme: OudsThemeContract
) : OudsSnapshotTest(theme, OudsBadgePreview.widthDp) {

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
