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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsVerticalDivider
import com.orange.ouds.core.theme.OudsTheme

@Composable
internal fun OudsHorizontalDividerSample() {
    Column(modifier = Modifier.padding(OudsTheme.spaces.fixed.short)) {
        Text(text = "Up")
        OudsHorizontalDivider(modifier = Modifier.fillMaxWidth())
        Text(text = "Down")
    }
}

@Composable
internal fun OudsVerticalDividerSample() {
    Row(modifier = Modifier.padding(OudsTheme.spaces.fixed.short)) {
        Text(text = "Before")
        OudsVerticalDivider(modifier = Modifier.fillMaxWidth())
        Text(text = "After")
    }
}