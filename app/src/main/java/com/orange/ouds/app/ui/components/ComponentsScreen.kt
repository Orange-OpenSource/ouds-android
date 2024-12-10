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

package com.orange.ouds.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.component.button.OudsButton
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken
import com.orange.ouds.theme.tokens.OudsGridKeyToken
import com.orange.ouds.theme.tokens.OudsSpaceKeyToken

@Composable
fun ComponentsScreen() {
    Screen {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier.padding(bottom = 8.dp), text = "Components screen")

            OudsButton(text = "OUDS button", onClick = { })

            Box(
                modifier = Modifier
                    .padding(top = OudsSpaceKeyToken.Fixed.Medium.value)
                    .width(OudsGridKeyToken.Margin.value)
                    .height(OudsGridKeyToken.ColumnGap.value)
                    .background(OudsColorKeyToken.Background.Primary.value)
            )
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewComponentsScreen() = OudsPreview {
    ComponentsScreen()
}
