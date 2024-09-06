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

package com.orange.ouds.core.component.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.orange.OrangeTheme

internal fun ComposeContentTestRule.setOudsContent(composable: @Composable () -> Unit) {
    setContent {
        OudsTheme(themeContract = OrangeTheme(), content = composable)
    }
}
