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

import com.orange.ouds.core.utilities.PreviewableComponent
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
class OudsButtonTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(parameter: Any) : OudsComponentSnapshotTest(
        PreviewableComponent.OudsButton.Parameterized,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = PreviewableComponent.OudsButton.Parameterized.parameters
        }
    }

    class NonParameterized : OudsComponentSnapshotTest(
        PreviewableComponent.OudsButton.NonParameterized,
        parameter = null,
        OudsComponentTestSuite.theme
    )
}
