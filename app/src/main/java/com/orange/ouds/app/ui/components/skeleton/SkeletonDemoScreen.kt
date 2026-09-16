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

package com.orange.ouds.app.ui.components.skeleton

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.core.component.OudsSkeleton
import com.orange.ouds.theme.OudsVersion

@Composable
fun SkeletonDemoScreen() {
    val state = rememberSkeletonDemoState()
    DemoScreen(
        description = stringResource(id = Component.Skeleton.descriptionRes),
        bottomSheetContent = { SkeletonDemoBottomSheetContent(state = state) },
        codeSnippet = { skeletonDemoCodeSnippet(state = state) },
        demoContent = { SkeletonDemoContent(state = state) },
        version = OudsVersion.Component.Skeleton
    )
}

@Composable
private fun SkeletonDemoBottomSheetContent(state: SkeletonDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_skeleton_securityMargin_tech),
            checked = securityMargin,
            onCheckedChange = { securityMargin = it },
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_animated_tech),
            checked = state.skeletonState.isAnimationRunning,
            onCheckedChange = { with(state.skeletonState) { if (it) startAnimation() else stopAnimation() } },
        )
    }
}

@Composable
private fun SkeletonDemoContent(state: SkeletonDemoState) {
    with(state) {
        OudsSkeleton(
            modifier = Modifier.size(200.dp, 62.dp),
            state = state.skeletonState,
            securityMargin = securityMargin
        )
    }
}

private fun Code.Builder.skeletonDemoCodeSnippet(state: SkeletonDemoState) {
    with(state) {
        functionCall("OudsSkeleton") {
            rawArgument("modifier", "Modifier.size(200.dp, 62.dp)")
            functionCallArgument("state", "rememberOudsSkeletonState")
            typedArgument("securityMargin", securityMargin)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSkeletonDemoScreen() = AppPreview {
    SkeletonDemoScreen()
}
