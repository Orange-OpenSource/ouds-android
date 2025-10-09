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
class OudsSwitchItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SwitchItem.Parameterized,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.SwitchItem.Parameterized.parameters
        }
    }

    class NonParameterized : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SwitchItem.NonParameterized,
        parameter = null,
        OudsComponentTestSuite.theme
    )
}
