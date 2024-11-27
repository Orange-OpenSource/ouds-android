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

package com.orange.ouds.core.component.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ouds.core.extensions.enabled

@Composable
internal fun OudsIcon(
    graphicsObject: Any,
    contentDescription: String,
    modifier: Modifier = Modifier,
    tint: Color = OudsIconDefaults.tint,
    enabled: Boolean = true,
) {
    val iconTint = tint.enabled(enabled = enabled)
    when (graphicsObject) {
        is Painter -> Icon(painter = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        is ImageVector -> Icon(imageVector = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        is ImageBitmap -> Icon(bitmap = graphicsObject, contentDescription = contentDescription, modifier = modifier, tint = iconTint)
        else -> {}
    }
}
