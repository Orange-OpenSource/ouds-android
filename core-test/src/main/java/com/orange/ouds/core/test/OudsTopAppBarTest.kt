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
class OudsTopAppBarTest() {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TopAppBar.Default,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TopAppBar.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class CenterAligned(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TopAppBar.CenterAligned,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TopAppBar.CenterAligned.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Medium(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TopAppBar.Medium,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TopAppBar.Medium.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Large(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.TopAppBar.Large,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.TopAppBar.Large.parameters
        }
    }
}
