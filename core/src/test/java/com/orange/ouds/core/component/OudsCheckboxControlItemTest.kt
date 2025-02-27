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
internal class OudsCheckboxControlItemTest(private val parameter: OudsCheckboxControlItemPreviewParameter) : OudsPaparazziTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsCheckboxControlItemPreviewParameterProvider().values.toList()
    }

    @Test
    fun takeOudsCheckboxControlItemLightThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckboxControlItem(
                darkThemeEnabled = false,
                parameter = parameter
            )
        }
    }

    @Test
    fun takeOudsCheckboxControlItemDarkThemeSnapshot() {
        paparazzi.snapshot {
            PreviewOudsCheckboxControlItem(
                darkThemeEnabled = true,
                parameter = parameter
            )
        }
    }
}