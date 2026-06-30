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
import com.orange.ouds.core.component.OudsCircularProgressIndicator
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsCircularProgressIndicatorDeterminateSample() {
    OudsCircularProgressIndicator(progress = { 0.75f })
}

@Composable
internal fun OudsCircularProgressIndicatorIndeterminateSample() {
    OudsCircularProgressIndicator()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCircularProgressIndicatorDeterminateSample() = OudsPreview {
    OudsCircularProgressIndicatorDeterminateSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCircularProgressIndicatorIndeterminateSample() = OudsPreview {
    OudsCircularProgressIndicatorIndeterminateSample()
}