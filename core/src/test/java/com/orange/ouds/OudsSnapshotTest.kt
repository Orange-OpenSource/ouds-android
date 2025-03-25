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
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.orange.ouds.foundation.LocalSnapshotTest
import org.junit.Assume
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

internal abstract class OudsSnapshotTest {

    @get:Rule
    val paparazzi = Paparazzi(renderingMode = SessionParams.RenderingMode.SHRINK, maxPercentDifference = 0.01)

    @get:Rule
    var name = TestName()

    open fun ignoreSnapshot(darkThemeEnabled: Boolean) = false

    @Composable
    abstract fun Snapshot(darkThemeEnabled: Boolean)

    @Before
    fun setUp() {
        val isLightThemeSnapshotTest = name.methodName.startsWith(::takeLightThemeSnapshot.name)
        val isDarkThemeSnapshotTest = name.methodName.startsWith(::takeDarkThemeSnapshot.name)
        val ignoreTest = (ignoreSnapshot(false) && isLightThemeSnapshotTest) || (ignoreSnapshot(true) && isDarkThemeSnapshotTest)
        Assume.assumeTrue(!ignoreTest)
    }

    @Test
    fun takeLightThemeSnapshot() = takeSnapshot(darkThemeEnabled = false)

    @Test
    fun takeDarkThemeSnapshot() = takeSnapshot(darkThemeEnabled = true)

    private fun takeSnapshot(darkThemeEnabled: Boolean) {
        paparazzi.snapshot {
            CompositionLocalProvider(value = LocalSnapshotTest provides true) {
                Snapshot(darkThemeEnabled = darkThemeEnabled)
            }
        }
    }
}
