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
internal class OudsCheckboxItemTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CheckboxItem.Default,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.CheckboxItem.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class HighContrastModeEnabled(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CheckboxItem.HighContrastModeEnabled,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.CheckboxItem.HighContrastModeEnabled.parameters
        }
    }

    class WithLongDescription : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CheckboxItem.WithLongDescription,
        parameter = null,
        OudsComponentTestSuite.theme
    )

    class WithEdgeToEdgeDisabled : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CheckboxItem.WithEdgeToEdgeDisabled,
        parameter = null,
        OudsComponentTestSuite.theme
    )

    @RunWith(Parameterized::class)
    class ConstrainedMaxWidth(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CheckboxItem.ConstrainedMaxWidth,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.CheckboxItem.ConstrainedMaxWidth.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.CheckboxItem.ConstrainedMaxWidth.parameters
        }
    }
}