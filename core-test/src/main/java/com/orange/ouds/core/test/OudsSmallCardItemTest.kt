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
internal class OudsSmallCardItemTest {

    @RunWith(Parameterized::class)
    class Static(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SmallCardItem.Static,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.SmallCardItem.Static.parameters
        }
    }

    @RunWith(Parameterized::class)
    class StaticWithRoundedCorners(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SmallCardItem.StaticWithRoundedCorners,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.SmallCardItem.StaticWithRoundedCorners.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Navigation(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SmallCardItem.Navigation,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.SmallCardItem.Navigation.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.SmallCardItem.Navigation.parameters
        }
    }

    @RunWith(Parameterized::class)
    class NavigationWithRoundedCorners(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.SmallCardItem.NavigationWithRoundedCorners,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.SmallCardItem.Navigation.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.SmallCardItem.NavigationWithRoundedCorners.parameters
        }
    }
}
