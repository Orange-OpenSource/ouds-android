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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsVerticalDivider
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsHorizontalDividerSample() {
    Column(modifier = Modifier.padding(OudsTheme.spaces.fixed.short)) {
        Text(
            text = "Up",
            color = OudsTheme.colorScheme.content.default
        )
        OudsHorizontalDivider(modifier = Modifier.fillMaxWidth())
        Text(
            text = "Down",
            color = OudsTheme.colorScheme.content.default
        )
    }
}

@Composable
internal fun OudsVerticalDividerSample() {
    Row(modifier = Modifier.padding(OudsTheme.spaces.fixed.short)) {
        Text(
            modifier = Modifier.weight(1f),
            text = "Start",
            color = OudsTheme.colorScheme.content.default
        )
        OudsVerticalDivider(modifier = Modifier.height(50.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "End",
            color = OudsTheme.colorScheme.content.default
        )
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsHorizontalDividerSample() = OudsPreview {
    OudsHorizontalDividerSample()
}


@PreviewLightDark
@Composable
private fun PreviewOudsVerticalDividerSample() = OudsPreview {
    OudsVerticalDividerSample()
}
