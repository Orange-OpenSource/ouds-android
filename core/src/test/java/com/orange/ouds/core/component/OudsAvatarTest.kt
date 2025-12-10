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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OudsAvatarTest(val isMonogram: Boolean) : OudsSnapshotTest(theme = OrangeTheme()) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsAvatarPreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsAvatar(
            theme = theme,
            darkThemeEnabled = darkThemeEnabled,
            isMonogram = isMonogram
        )
    }
}
