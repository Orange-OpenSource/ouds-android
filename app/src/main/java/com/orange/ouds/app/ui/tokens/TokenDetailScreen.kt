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

package com.orange.ouds.app.ui.tokens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

@Composable
fun TokenDetailScreen(token: Token) {
    Screen {
        Column(
            modifier = Modifier
                .background(OudsColorKeyToken.Background.value)
                .verticalScroll(rememberScrollState())
                .padding(bottom = OudsSpacingFixedKeyToken.Medium.value)
        ) {
            DetailScreenHeader(
                descriptionRes = token.descriptionRes,
                imageRes = token.imageRes
            )
            Column(
                modifier = Modifier.padding(top = OudsSpacingFixedKeyToken.Medium.value)
            ) {


            }
        }
    }
}
