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

package com.orange.ouds.app.ui.components.tag

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.skeletonArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsInputTag
import com.orange.ouds.core.component.OudsSkeleton
import com.orange.ouds.core.component.rememberOudsSkeletonState
import com.orange.ouds.theme.OudsVersion

@Composable
fun InputTagDemoScreen() {
    val state = rememberInputTagDemoState()
    DemoScreen(
        bottomSheetContent = { InputTagDemoBottomSheetContent(state = state) },
        codeSnippet = { inputTagDemoCodeSnippet(state = state) },
        demoContent = { InputTagDemoContent(state = state) },
        version = OudsVersion.Component.Tag
    )
}

@Composable
private fun InputTagDemoBottomSheetContent(state: InputTagDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_skeleton_tech),
            checked = skeleton,
            onCheckedChange = { skeleton = it },
        )
    }
}

@Composable
private fun InputTagDemoContent(state: InputTagDemoState) {
    with(state) {
        OudsInputTag(
            label = label,
            onClick = {},
            enabled = enabled,
            skeleton = if (skeleton) OudsSkeleton(rememberOudsSkeletonState()) else null
        )
    }
}

private fun Code.Builder.inputTagDemoCodeSnippet(state: InputTagDemoState) {
    with(state) {
        functionCall("OudsInputTag") {
            labelArgument(label)
            onClickArgument()
            typedArgument("enabled", enabled)
            if (skeleton) skeletonArgument()
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTagDemoScreen() = AppPreview {
    TagDemoScreen()
}
