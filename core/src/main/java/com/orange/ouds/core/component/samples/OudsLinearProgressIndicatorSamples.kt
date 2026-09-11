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
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsDeterminateLinearProgressIndicatorHelperText
import com.orange.ouds.core.component.OudsIndeterminateLinearProgressIndicatorHelperText
import com.orange.ouds.core.component.OudsLinearProgressIndicator
import com.orange.ouds.core.component.OudsProgressIndicatorStatus
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsLinearProgressIndicatorDeterminateSample() {
    OudsLinearProgressIndicator(
        progress = { 0.75f },
        status = OudsProgressIndicatorStatus.Accent
    )
}

@Composable
internal fun OudsLinearProgressIndicatorDeterminateWithHelperTextStringSample() {
    OudsLinearProgressIndicator(
        progress = { 0.75f },
        helperText = "Uploading file..."
    )
}

@Composable
internal fun OudsLinearProgressIndicatorDeterminateWithHelperTextSample() {
    OudsLinearProgressIndicator(
        progress = { 0.75f },
        helperText = OudsDeterminateLinearProgressIndicatorHelperText(
            progress = true,
            label = "Uploading file...",
            progressAlignment = Alignment.Start,
            labelAlignment = Alignment.End
        )
    )
}

@Composable
internal fun OudsLinearProgressIndicatorIndeterminateSample() {
    OudsLinearProgressIndicator()
}

@Composable
internal fun OudsLinearProgressIndicatorIndeterminateWithHelperTextSample() {
    OudsLinearProgressIndicator(
        helperText = OudsIndeterminateLinearProgressIndicatorHelperText(
            label = "Processing...",
            alignment = Alignment.Start
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinearProgressIndicatorDeterminateSample() = OudsPreview {
    OudsLinearProgressIndicatorDeterminateSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinearProgressIndicatorDeterminateWithHelperTextStringSample() = OudsPreview {
    OudsLinearProgressIndicatorDeterminateWithHelperTextStringSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinearProgressIndicatorDeterminateWithHelperTextSample() = OudsPreview {
    OudsLinearProgressIndicatorDeterminateWithHelperTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinearProgressIndicatorIndeterminateSample() = OudsPreview {
    OudsLinearProgressIndicatorIndeterminateSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLinearProgressIndicatorIndeterminateWithHelperTextSample() = OudsPreview {
    OudsLinearProgressIndicatorIndeterminateWithHelperTextSample()
}
