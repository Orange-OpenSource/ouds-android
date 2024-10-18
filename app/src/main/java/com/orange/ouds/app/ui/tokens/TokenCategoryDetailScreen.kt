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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.orange.ouds.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.theme.value
import com.orange.ouds.theme.OudsBorderStyle
import com.orange.ouds.theme.tokens.OudsBorderWidthKeyToken
import com.orange.ouds.theme.tokens.OudsSpacingFixedKeyToken
import com.orange.ouds.theme.tokens.OudsTypographyKeyToken

@Composable
fun TokenCategoryDetailScreen(tokenCategory: TokenCategory) {

    Screen {
        LazyColumn(contentPadding = PaddingValues(bottom = OudsSpacingFixedKeyToken.Medium.value)) {
            item {
                DetailScreenHeader(
                    descriptionRes = tokenCategory.descriptionRes,
                    imageRes = tokenCategory.imageRes
                )
            }

            items(tokenCategory.properties) { tokenProperty ->
                Spacer(modifier = Modifier.height(OudsSpacingFixedKeyToken.Medium.value))

                tokenProperty.nameRes?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(OudsSpacingFixedKeyToken.Medium.value),
                        text = stringResource(id = tokenProperty.nameRes),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = OudsTypographyKeyToken.HeadingMedium.value
                    )
                }

                tokenProperty.tokens().forEach { token ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = OudsSpacingFixedKeyToken.Medium.value, vertical = OudsSpacingFixedKeyToken.Shorter.value)
                    ) {
                        when (tokenProperty) {
                            is TokenProperty.BorderWidth -> {
                                val outside = token.name == OudsBorderWidthKeyToken.OutsideFocus.name
                                tokenProperty.Illustration(width = token.value as Dp, outside = outside)
                            }
                            is TokenProperty.BorderRadius -> tokenProperty.Illustration(radius = token.value as Dp)
                            is TokenProperty.BorderStyle -> tokenProperty.Illustration(style = token.value as OudsBorderStyle)
                            is TokenProperty.Opacity -> tokenProperty.Illustration(opacity = token.value as Float)
                            is TokenProperty.Elevation -> tokenProperty.Illustration(elevation = token.value as Dp)
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
                                style = OudsTypographyKeyToken.BodyDefaultMedium.value
                            )
                        }
                    }
                }
            }
        }
    }
}