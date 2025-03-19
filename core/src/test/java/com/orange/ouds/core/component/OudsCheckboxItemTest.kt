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

import com.orange.ouds.OudsPaparazziTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class OudsCheckboxItemTest(private val parameter: OudsCheckboxItemPreviewParameter) : OudsPaparazziTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsCheckboxItemPreviewParameterProvider().values.toList()
    }

    @Test
    fun takeOudsCheckboxItemLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckboxItem(
                darkThemeEnabled = false,
                parameter = parameter
            )
        }
    }

    @Test
    fun takeOudsCheckboxItemDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckboxItem(
                darkThemeEnabled = true,
                parameter = parameter
            )
        }
    }
}