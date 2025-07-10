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
internal class OudsNavigationBarTest(private val parameter: OudsNavigationBarPreviewParameter) : OudsSnapshotTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsNavigationBarPreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsNavigationBar(
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}

@RunWith(Parameterized::class)
internal class OudsNavigationBarItemTest(private val parameter: OudsNavigationBarItemPreviewParameter) : OudsSnapshotTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsNavigationBarItemPreviewParameterProvider().values.toList()
    }

    @Composable
    override fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) {
        PreviewOudsNavigationBarItem(
            darkThemeEnabled = darkThemeEnabled,
            parameter = parameter
        )
    }
}