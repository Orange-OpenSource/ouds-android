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

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsBodyText
import com.orange.ouds.core.component.OudsBodyTextSize
import com.orange.ouds.core.component.OudsTextWeight
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsBodyTextSample() {
    OudsBodyText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = "Body",
        size = OudsBodyTextSize.Medium,
        weight = OudsTextWeight.Moderate
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsBodyTextSample() = OudsPreview {
    OudsBodyTextSample()
}
