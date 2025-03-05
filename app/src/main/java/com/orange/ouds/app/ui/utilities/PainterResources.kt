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

package com.orange.ouds.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

data class DrawableResourceId(@DrawableRes val light: Int, @DrawableRes val dark: Int) {

    constructor(@DrawableRes id: Int) : this(id, id)
}

@Composable
fun painterResource(id: DrawableResourceId): Painter {
    return if (isSystemInDarkTheme()) painterResource(id = id.dark) else painterResource(id = id.light)
}
