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

package com.orange.ouds.core.component

import com.orange.ouds.core.test.OudsComponentSnapshotTest
import com.orange.ouds.core.test.OudsComponentTestSuite
import com.orange.ouds.core.utilities.OudsPreviewableComponent
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith


@RunWith(Enclosed::class)
class OudsNavigationBarTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBar.Parameterized,
        parameter,
        OudsComponentTestSuite.theme
    ) {
        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBar.Parameterized.parameters
        }
    }
}

@RunWith(Enclosed::class)
class OudsNavigationBarItemTest {

    @RunWith(org.junit.runners.Parameterized::class)
    class Parameterized(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBarItem.Parameterized,
        parameter,
        OudsComponentTestSuite.theme
    ) {
        companion object {
            @JvmStatic
            @org.junit.runners.Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBarItem.Parameterized.parameters
        }
    }
}