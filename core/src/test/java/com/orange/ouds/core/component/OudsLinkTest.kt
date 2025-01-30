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
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
internal class OudsLinkTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(private val parameter: OudsLinkPreviewParameter) : OudsPaparazziTest() {
        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsLinkPreviewParameterProvider().values.toList()
        }

        @Test
        fun takeOudsLinkLightThemeSnapshot() {
            paparazzi.snapshot {
                PreviewOudsLink(
                    darkThemeEnabled = false,
                    parameter = parameter
                )
            }
        }

        @Test
        fun takeOudsLinkDarkThemeSnapshot() {
            paparazzi.snapshot {
                PreviewOudsLink(
                    darkThemeEnabled = true,
                    parameter = parameter
                )
            }
        }
    }

    class NonParameterized: OudsPaparazziTest() {
        @Test
        fun takeOudsLinkOnTwoLinesSnapshot() {
            paparazzi.snapshot {
                PreviewOudsLinkOnTwoLines()
            }
        }
    }
}