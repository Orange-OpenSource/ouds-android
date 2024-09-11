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

package com.orange.ouds.core.component.button

import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OudsButtonTest(private val parameter: OudsButtonPreviewParameter) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsButtonPreviewParameterProvider().values.toList()
    }

    @get:Rule
    val paparazzi = Paparazzi(renderingMode = SessionParams.RenderingMode.SHRINK, maxPercentDifference = 0.0)

    @Test
    fun takeOudsButtonLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsButton(
                darkThemeEnabled = false,
                parameter = parameter
            )
        }
    }

    @Test
    fun takeOudsButtonDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsButton(
                darkThemeEnabled = true,
                parameter = parameter
            )
        }
    }
}