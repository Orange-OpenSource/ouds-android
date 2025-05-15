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

package com.orange.ouds

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.orange.ouds.core.utilities.LocalSnapshotTest
import org.junit.Assume
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

internal abstract class OudsSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        renderingMode = SessionParams.RenderingMode.SHRINK,
        // Double the screen height and set useDeviceResolution to true in order to avoid truncated snapshots for vertical content
        deviceConfig = with(DeviceConfig.NEXUS_5) { copy(screenHeight = screenHeight * 2) },
        useDeviceResolution = true,
        maxPercentDifference = 0.01
    )

    @get:Rule
    var name = TestName()

    /**
     * Allows to ignore the execution of specified snapshots tests.
     * By default, high contrast mode snapshots are ignored.
     */
    open fun ignoreSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = highContrastModeEnabled

    @Composable
    abstract fun Snapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean)

    @Before
    fun setUp() {
        val isLightThemeSnapshotTest = name.methodName.startsWith(::takeLightThemeSnapshot.name)
        val isDarkThemeSnapshotTest = name.methodName.startsWith(::takeDarkThemeSnapshot.name)
        val isLightThemeHighContrastModeSnapshotTest = name.methodName.startsWith(::takeLightThemeHighContrastSnapshot.name)
        val isDarkThemeHighContrastModeSnapshotTest = name.methodName.startsWith(::takeDarkThemeHighContrastSnapshot.name)
        val ignoreTest = (ignoreSnapshot(darkThemeEnabled = false, highContrastModeEnabled = false) && isLightThemeSnapshotTest)
                || (ignoreSnapshot(darkThemeEnabled = true, highContrastModeEnabled = false) && isDarkThemeSnapshotTest)
                || (ignoreSnapshot(darkThemeEnabled = false, highContrastModeEnabled = true) && isLightThemeHighContrastModeSnapshotTest)
                || (ignoreSnapshot(darkThemeEnabled = true, highContrastModeEnabled = true) && isDarkThemeHighContrastModeSnapshotTest)
        Assume.assumeTrue(!ignoreTest)
    }

    @Test
    fun takeLightThemeSnapshot() = takeSnapshot(darkThemeEnabled = false)

    @Test
    fun takeLightThemeHighContrastSnapshot() = takeSnapshot(darkThemeEnabled = false, highContrastModeEnabled = true)

    @Test
    fun takeDarkThemeSnapshot() = takeSnapshot(darkThemeEnabled = true)

    @Test
    fun takeDarkThemeHighContrastSnapshot() = takeSnapshot(darkThemeEnabled = true, highContrastModeEnabled = true)

    private fun takeSnapshot(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean = false) {
        paparazzi.snapshot {
            CompositionLocalProvider(value = LocalSnapshotTest provides true) {
                Snapshot(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled)
            }
        }
    }
}
