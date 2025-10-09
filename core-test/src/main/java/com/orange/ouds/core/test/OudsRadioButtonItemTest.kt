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

import com.orange.ouds.core.utilities.OudsPreviewableComponent
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
class OudsRadioButtonItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.RadioButtonItem.Parameterized,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.RadioButtonItem.Parameterized.parameters
        }
    }

    @RunWith(org.junit.runners.Parameterized::class)
    class ParameterizedHighContrastMode(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.RadioButtonItem.ParameterizedHighContrastMode,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.RadioButtonItem.ParameterizedHighContrastMode.parameters
        }
    }

    class NonParameterized : OudsComponentSnapshotTest(
        OudsPreviewableComponent.RadioButtonItem.NonParameterized,
        parameter = null,
        OudsComponentTestSuite.theme
    )
}