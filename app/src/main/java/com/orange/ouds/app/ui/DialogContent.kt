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

package com.orange.ouds.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.OudsThemeTweak
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun DialogContent(
    title: String,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(28.dp))
            .background(OudsTheme.colorScheme.background.primary)
    ) {
        Text(
            text = title,
            color = OudsTheme.colorScheme.content.default,
            modifier = Modifier.padding(all = 24.dp),
            style = OudsTheme.typography.heading.medium
        )
        Column(modifier = Modifier.padding(contentPadding)) {
            content()
        }
    }
}

@Composable
fun PreviewDialogContent(content: @Composable () -> Unit) = OudsPreview {
    OudsThemeTweak(tweak = OudsTheme.Tweak.Invert) {
        Box(
            modifier = Modifier
                .background(OudsTheme.colorScheme.background.primary)
                .padding(all = 10.dp)
        ) {
            OudsThemeTweak(
                tweak = OudsTheme.Tweak.Invert,
                content = content
            )
        }
    }
}
