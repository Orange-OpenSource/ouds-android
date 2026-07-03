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
internal class OudsCardItemTest {

    @RunWith(Parameterized::class)
    class Static(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CardItem.Static,
        parameter,
        OudsComponentTestSuite.theme
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.CardItem.Static.parameters
        }
    }

    class StaticWithRoundedCorners : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CardItem.StaticWithRoundedCorners,
        parameter = null,
        OudsComponentTestSuite.theme
    )

    @RunWith(Parameterized::class)
    class Navigation(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CardItem.Navigation,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.CardItem.Navigation.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.CardItem.Navigation.parameters
        }
    }

    class NavigationWithRoundedCorners : OudsComponentSnapshotTest(
        OudsPreviewableComponent.CardItem.NavigationWithRoundedCorners,
        parameter = null,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.CardItem.Navigation.PreviewHeightDp
    )
}
