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
internal class OudsFloatingActionButtonTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.FloatingActionButton.Default,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.FloatingActionButton.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Small(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.FloatingActionButton.Small,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.FloatingActionButton.Small.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Large(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.FloatingActionButton.Large,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.FloatingActionButton.Large.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.FloatingActionButton.Large.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Extended(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.FloatingActionButton.Extended,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.FloatingActionButton.Extended.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.FloatingActionButton.Extended.parameters
        }
    }
}
