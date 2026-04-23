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

package com.orange.ouds.core.component.samples

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsNavigationButton
import com.orange.ouds.core.component.OudsNavigationButtonLayout
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsNavigationButtonIconOnlySample() {
    OudsNavigationButton(
        layout = OudsNavigationButtonLayout.Next,
        onClick = { /* Navigate to next screen */ }
    )
}

@Composable
internal fun OudsNavigationButtonTextAndIconSample() {
    OudsNavigationButton(
        label = "Next",
        layout = OudsNavigationButtonLayout.Next,
        onClick = { /* Navigate to next screen */ }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationButtonIconOnlySample() = OudsPreview {
    OudsNavigationButtonIconOnlySample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsNavigationButtonTextAndIconSample() = OudsPreview {
    OudsNavigationButtonTextAndIconSample()
}
