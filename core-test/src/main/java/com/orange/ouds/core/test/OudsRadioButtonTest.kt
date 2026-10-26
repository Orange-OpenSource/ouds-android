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
internal class OudsRadioButtonTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.RadioButton.Default,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.RadioButton.Default.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.RadioButton.Default.parameters
        }
    }

    class HighContrastModeEnabled : OudsComponentSnapshotTest(
        OudsPreviewableComponent.RadioButton.HighContrastModeEnabled,
        parameter = null,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.RadioButton.HighContrastModeEnabled.PreviewWidthDp
    )
}