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

package com.orange.ouds.app.ui.utilities.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagAppearance
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun ComponentVersion(version: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = OudsTheme.grids.margin)
            .padding(bottom = OudsTheme.spaces.fixed.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.app_components_common_version_label),
            style = with(OudsTheme.typography.label.strong.large) { copy(lineHeightStyle = lineHeightStyle?.copy(alignment = LineHeightStyle.Alignment.Center)) },
            color = OudsTheme.colorScheme.content.default
        )
        OudsTag(modifier = Modifier.padding(start = 10.dp), label = version, appearance = OudsTagAppearance.Muted, status = OudsTagStatus.Info())
    }
}