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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsCodeText
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsCodeTextSample() {
    OudsCodeText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = "Code",
    )
}

@Composable
internal fun OudsCodeWithAnnotatedTextSample() {
    OudsCodeText(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.small),
        text = buildAnnotatedString {
            append("Code with ")
            withStyle(SpanStyle(color = OudsTheme.colorScheme.content.brandPrimary)) { append("colored text") }
            append(" and ")
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("bold text") }
        }
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsCodeTextSample() = OudsPreview {
    OudsCodeTextSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCodeWithAnnotatedTextSample() = OudsPreview {
    OudsCodeWithAnnotatedTextSample()
}
