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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ouds.app.R
import com.orange.ouds.core.theme.value
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews
import com.orange.ouds.theme.tokens.OudsColorKeyToken

@Composable
fun BottomBar(currentRoute: String, navigateToRoute: (String) -> Unit) {
    val items = BottomBarItem.entries.toTypedArray()
    NavigationBar(
        containerColor = OudsColorKeyToken.Background.Secondary.value, //TODO Temporary color. Waiting for Material colors from Maxime.
        content = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(painterResource(item.iconRes), null)
                    },
                    label = {
                        Text(
                            text = stringResource(item.titleRes),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    onClick = { navigateToRoute(item.route) }
                )
            }

        }
    )
}

enum class BottomBarItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: String
) {
    Tokens(R.string.app_bottomBar_tokens_label, R.drawable.ic_design_token_figma, "main/tokens"),
    Components(R.string.app_bottomBar_components_label, R.drawable.ic_component_atom, "main/components"),
    About(R.string.app_bottomBar_about_label, R.drawable.ic_info, "main/about");
}

@UiModePreviews.Default
@Composable
private fun PreviewBottomBar() = OudsPreview {
    BottomBar(currentRoute = "") {}
}
