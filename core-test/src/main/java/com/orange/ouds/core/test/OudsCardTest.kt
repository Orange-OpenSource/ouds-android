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
internal class OudsCardTest {

    @RunWith(Parameterized::class)
    class ActionFilled(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Card.ActionFilled,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.Card.ActionFilled.PreviewHeightDp,
        widthDp = OudsPreviewableComponent.Card.ActionFilled.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Card.ActionFilled.parameters
        }
    }

    @RunWith(Parameterized::class)
    class ActionOutlined(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Card.ActionOutlined,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.Card.ActionOutlined.PreviewHeightDp,
        widthDp = OudsPreviewableComponent.Card.ActionOutlined.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Card.ActionOutlined.parameters
        }
    }

    @RunWith(Parameterized::class)
    class ActionElevated(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Card.ActionElevated,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.Card.ActionElevated.PreviewHeightDp,
        widthDp = OudsPreviewableComponent.Card.ActionElevated.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Card.ActionElevated.parameters
        }
    }

    @RunWith(Parameterized::class)
    class Content(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Card.Content,
        parameter,
        OudsComponentTestSuite.theme,
        widthDp = OudsPreviewableComponent.Card.Content.PreviewWidthDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.Card.Content.parameters
        }
    }

    class DisabledWithComponents : OudsComponentSnapshotTest(
        OudsPreviewableComponent.Card.DisabledWithComponents,
        parameter = null,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.Card.DisabledWithComponents.PreviewHeightDp
    )
}
