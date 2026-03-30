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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Devices
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.orange.ouds.core.utilities.OudsPreviewDevice
import com.orange.ouds.theme.OudsThemeContract
import org.junit.Assume
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import kotlin.math.max

abstract class OudsSnapshotTest(val theme: OudsThemeContract, widthDp: Int = -1, heightDp: Int = -1) {

    @Suppress("KotlinConstantConditions")
    private val deviceConfig = when (OudsPreviewDevice) {
        Devices.NEXUS_5 -> DeviceConfig.NEXUS_5
        Devices.NEXUS_7 -> DeviceConfig.NEXUS_7
        Devices.NEXUS_10 -> DeviceConfig.NEXUS_10
        Devices.PIXEL_C -> DeviceConfig.PIXEL_C
        Devices.PIXEL -> DeviceConfig.PIXEL
        Devices.PIXEL_XL -> DeviceConfig.PIXEL_XL
        Devices.PIXEL_2 -> DeviceConfig.PIXEL_2
        Devices.PIXEL_2_XL -> DeviceConfig.PIXEL_2_XL
        Devices.PIXEL_3 -> DeviceConfig.PIXEL_3
        Devices.PIXEL_3_XL -> DeviceConfig.PIXEL_3_XL
        Devices.PIXEL_3A -> DeviceConfig.PIXEL_3A
        Devices.PIXEL_3A_XL -> DeviceConfig.PIXEL_3A_XL
        Devices.PIXEL_4 -> DeviceConfig.PIXEL_4
        Devices.PIXEL_4_XL -> DeviceConfig.PIXEL_4_XL
        Devices.PIXEL_4A -> DeviceConfig.PIXEL_4A
        Devices.PIXEL_5 -> DeviceConfig.PIXEL_5
        Devices.PIXEL_6 -> DeviceConfig.PIXEL_6
        Devices.PIXEL_6_PRO -> DeviceConfig.PIXEL_6_PRO
        Devices.PIXEL_6A -> DeviceConfig.PIXEL_6A
        Devices.PIXEL_7 -> DeviceConfig.PIXEL_7
        Devices.PIXEL_7_PRO -> DeviceConfig.PIXEL_7_PRO
        Devices.PIXEL_7A -> DeviceConfig.PIXEL_7A
        Devices.PIXEL_8 -> DeviceConfig.PIXEL_8
        Devices.PIXEL_8_PRO -> DeviceConfig.PIXEL_8_PRO
        Devices.PIXEL_8A -> DeviceConfig.PIXEL_8A
        Devices.PIXEL_9 -> DeviceConfig.PIXEL_9
        Devices.PIXEL_9_PRO -> DeviceConfig.PIXEL_9_PRO
        Devices.PIXEL_9_PRO_FOLD -> DeviceConfig.PIXEL_9_PRO_FOLD
        Devices.PIXEL_9_PRO_XL -> DeviceConfig.PIXEL_9_PRO_XL
        Devices.PIXEL_FOLD -> DeviceConfig.PIXEL_FOLD
        Devices.PIXEL_TABLET -> DeviceConfig.PIXEL_TABLET
        else -> error("Preview device is not supported for snapshot generation.")
    }

    @get:Rule
    val paparazzi = Paparazzi(
        renderingMode = SessionParams.RenderingMode.SHRINK,
        deviceConfig = with(deviceConfig) {
            val width = if (widthDp > 0) (widthDp * density.dpiValue / 160f).toInt() else screenWidth
            val height = if (heightDp > 0) (heightDp * density.dpiValue / 160f).toInt() else screenHeight
            copy(
                screenWidth = width,
                // Fixes a bug where the width is not taken into account by Paparazzi when it is bigger than the height
                // There is no issue setting a bigger height since the final rendering is shrunk to the content size 
                screenHeight = max(width, height)
            )
        },
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
        val isDarkThemeSnapshotTest = name.methodName.contains("DarkTheme")
        val isHighContrastModeSnapshotTest = name.methodName.contains("HighContrast")
        val ignoreTest = ignoreSnapshot(isDarkThemeSnapshotTest, isHighContrastModeSnapshotTest)
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
            CompositionLocalProvider(value = LocalInspectionMode provides true) {
                Snapshot(darkThemeEnabled = darkThemeEnabled, highContrastModeEnabled = highContrastModeEnabled)
            }
        }
    }
}
