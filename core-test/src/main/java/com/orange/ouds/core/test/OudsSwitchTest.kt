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
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class OudsSwitchTest(parameter: Any) : OudsComponentSnapshotTest(
    OudsPreviewableComponent.Switch,
    parameter,
    OudsComponentTestSuite.theme
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        internal fun data() = OudsPreviewableComponent.Switch.parameters
    }
}