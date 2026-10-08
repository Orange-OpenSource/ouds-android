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
import org.junit.runners.Parameterized

@RunWith(Enclosed::class)
internal class OudsCheckboxTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Checkbox.Default,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.Checkbox.Default.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Checkbox.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class HighContrastModeEnabled(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Checkbox.HighContrastModeEnabled,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.Checkbox.HighContrastModeEnabled.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Checkbox.HighContrastModeEnabled.parameters
        }
    }
}
