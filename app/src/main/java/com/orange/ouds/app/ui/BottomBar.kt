/*
 * Software Name: Orange Unified Design System
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

@Composable
fun BottomBar(items: Array<BottomBarItem>, currentRoute: String, navigateToRoute: (String) -> Unit) {
    NavigationBar(
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
    Guidelines(R.string.app_bottomBar_guidelines_label, R.drawable.ic_guideline_dna, "main/guidelines"),
    Components(R.string.app_bottomBar_components_label, R.drawable.ic_component_atom, "main/components"),
    About(R.string.app_bottomBar_about_label, R.drawable.ic_info, "main/about");
}