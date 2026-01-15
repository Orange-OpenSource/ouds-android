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
internal class OudsNavigationBarTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBar.Default,
        parameter,
        OudsComponentTestSuite.theme
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBar.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class MediumWindowSize(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBar.MediumWindowSize,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.NavigationBar.MediumWindowSize.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBar.MediumWindowSize.parameters
        }
    }
}

@RunWith(Enclosed::class)
internal class OudsNavigationBarItemTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBarItem.Default,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBarItem.Default.parameters
        }
    }

    @RunWith(Parameterized::class)
    class MediumWindowSize(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.NavigationBarItem.MediumWindowSize,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.NavigationBarItem.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.NavigationBarItem.MediumWindowSize.parameters
        }
    }
}