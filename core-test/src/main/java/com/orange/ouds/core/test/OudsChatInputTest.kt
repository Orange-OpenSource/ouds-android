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
internal class OudsChatInputTest {

    @RunWith(Parameterized::class)
    class Default(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.ChatInput.Default,
        parameter,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.ChatInput.Default.PreviewHeightDp
    ) {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.ChatInput.Default.parameters
        }
    }

    class WithoutRoundedCorners : OudsComponentSnapshotTest(
        OudsPreviewableComponent.ChatInput.WithoutRoundedCorners,
        parameter = null,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.ChatInput.WithoutRoundedCorners.PreviewHeightDp
    )

    @RunWith(Parameterized::class)
    class ConstrainedMaxWidth(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.ChatInput.ConstrainedMaxWidth,
        parameter,
        OudsComponentTestSuite.theme,
        OudsPreviewableComponent.ChatInput.ConstrainedMaxWidth.PreviewWidthDp
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.ChatInput.ConstrainedMaxWidth.parameters
        }
    }

    @RunWith(Parameterized::class)
    class AutoResize(parameter: Any) : OudsComponentSnapshotTest(
        OudsPreviewableComponent.ChatInput.AutoResize,
        parameter,
        OudsComponentTestSuite.theme
    ) {
        companion object {
            @JvmStatic
            @Parameterized.Parameters
            internal fun data() = OudsPreviewableComponent.ChatInput.AutoResize.parameters
        }
    }

//    @RunWith(Parameterized::class)
//    class WithRichText(parameter: Any) : OudsComponentSnapshotTest(
//        OudsPreviewableComponent.TextInput.WithRichText,
//        parameter,
//        OudsComponentTestSuite.theme
//    ) {
//
//        companion object {
//            @JvmStatic
//            @Parameterized.Parameters
//            internal fun data() = OudsPreviewableComponent.TextInput.WithRichText.parameters
//        }
//    }

    class WithUntintedBottomIconButton : OudsComponentSnapshotTest(
        OudsPreviewableComponent.ChatInput.WithUntintedBottomIconButton,
        parameter = null,
        OudsComponentTestSuite.theme,
        heightDp = OudsPreviewableComponent.ChatInput.WithUntintedBottomIconButton.PreviewHeightDp
    )
}
