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

package com.orange.ouds.core.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ouds.core.theme.LocalColorMode
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.theme.dashedBorder

@Composable
internal fun CheckedContent(
    expression: Boolean,
    exceptionMessage: () -> String,
    previewMessage: () -> String = { "⛔" },
    previewMessagePaddingValues: PaddingValues = PaddingValues(all = OudsTheme.spaces.fixed.small),
    previewDashedBorderShape: Shape = RectangleShape,
    previewDashedBorderPhase: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    // Throw an exception at runtime if expression is false
    if (!LocalInspectionMode.current) {
        check(expression, exceptionMessage)
    }

    if (expression) {
        content()
    } else {
        // Display a text in the preview if expression is false
        val color = with(OudsTheme.colorScheme) { if (LocalColorMode.current != null) always.white else action.negative.enabled }
        val backgroundColor = if (LocalColorMode.current != null) Color.Black.copy(alpha = 0.68f) else Color.Transparent
        Box(
            modifier = Modifier
                .dashedBorder(width = 1.dp, color = color, shape = previewDashedBorderShape, intervals = listOf(10.dp, 5.dp), phase = previewDashedBorderPhase)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.alpha(0f)) { content() } // Add content but hide it in order to make room for the text
            Text(
                modifier = Modifier.padding(previewMessagePaddingValues),
                text = previewMessage(),
                color = color,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                style = TextStyle(fontFamily = FontFamily.Monospace)
            )
        }
    }
}
