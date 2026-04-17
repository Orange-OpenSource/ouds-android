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
internal class OudsAlertMessageTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.AlertMessage.Default,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.AlertMessage.Default.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.AlertMessage.Default.parameters
        }
    }

    class WithRichText : OudsComponentSnapshotTest(
        OudsPreviewableComponent.AlertMessage.WithRichText,
        null,
        OudsComponentTestSuite.theme
    )
}
