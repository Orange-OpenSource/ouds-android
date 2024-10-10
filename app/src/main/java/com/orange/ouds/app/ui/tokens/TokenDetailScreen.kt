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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken
import com.orange.ouds.theme.tokens.semantic.OudsColorKeyToken

@Composable
fun TokenDetailScreen(tokenType: TokenType) {
    val tokens = tokenType.getTokens()

    Screen {
        LazyColumn(
            modifier = Modifier
                .background(OudsColorKeyToken.Background.value)
                .padding(bottom = OudsSpacingFixedKeyToken.Medium.value)
        ) {
            item {
                DetailScreenHeader(
                    descriptionRes = tokenType.descriptionRes,
                    imageRes = tokenType.imageRes
                )
                Spacer(modifier = Modifier.padding(top = OudsSpacingFixedKeyToken.Medium.value))
            }

            items(tokens) { token ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = OudsSpacingFixedKeyToken.Medium.value, vertical = OudsSpacingFixedKeyToken.Shorter.value),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (tokenType) {
                        is TokenType.Opacity -> tokenType.Illustration(token.value as Float)
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = OudsSpacingFixedKeyToken.Medium.value)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = token.name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.BodyStrongLarge.value
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = token.literalValue,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OudsTypographyKeyToken.BodyDefaultLarge.value
                        )
                    }
                }
            }
        }
    }
}