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
import com.orange.ouds.core.component.OudsHeadingText
import com.orange.ouds.core.component.OudsHeadingTextSize
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsHeadingTextSample() {
    OudsHeadingText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = "Heading",
        size = OudsHeadingTextSize.Medium
    )
}

@Composable
internal fun OudsHeadingTextLargeWithMarkerSample() {
    OudsHeadingText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = "Heading",
        size = OudsHeadingTextSize.Large()
    )
}

@Composable
internal fun OudsHeadingTextLargeWithoutMarkerSample() {
    OudsHeadingText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = "Heading",
        size = OudsHeadingTextSize.Large(marker = false)
    )
}


@PreviewLightDark
@Composable
private fun PreviewOudsHeadingTextSample() = OudsPreview {
    OudsHeadingTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsHeadingTextLargeWithMarkerSample() = OudsPreview {
    OudsHeadingTextLargeWithMarkerSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsHeadingTextLargeWithoutMarkerSample() = OudsPreview {
    OudsHeadingTextLargeWithoutMarkerSample()
}
