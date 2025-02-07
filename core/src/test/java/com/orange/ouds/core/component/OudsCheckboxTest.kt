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

import OudsCheckboxPreviewParameter
import OudsCheckboxPreviewParameterProvider
import PreviewOudsCheckbox
import com.orange.ouds.OudsPaparazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OudsCheckboxTest(private val parameter: OudsCheckboxPreviewParameter) : OudsPaparazziTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsCheckboxPreviewParameterProvider().values.toList()
    }

    @Test
    fun takeOudsCheckboxLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckbox(
                darkThemeEnabled = false,
                parameter = parameter
            )
        }
    }

    @Test
    fun takeOudsCheckboxDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckbox(
                darkThemeEnabled = true,
                parameter = parameter
            )
        }
    }
}