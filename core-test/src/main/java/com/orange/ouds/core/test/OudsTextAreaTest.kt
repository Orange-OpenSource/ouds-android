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
internal class OudsTextAreaTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TextArea.Default,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.TextArea.Default.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TextArea.Default.parameters
        }
    }

    class WithRoundedCorners : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TextArea.WithRoundedCorners,
        parameter = null,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.TextArea.WithRoundedCorners.PreviewHeightDp
    )

    @RunWith(Parameterized::class)
    class ConstrainedMaxWidth(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TextArea.ConstrainedMaxWidth,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.TextArea.ConstrainedMaxWidth.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TextArea.ConstrainedMaxWidth.parameters
        }
    }

    class MultiLineValue : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TextArea.MultiLineValue,
        parameter = null,
        OudsComponentTestSuite.theme
    )
}
