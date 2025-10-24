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
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.component.OudsNavigationBarItemIcon
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
fun BottomBar(currentRoute: String, navigateToRoute: (String) -> Unit, visible: Boolean = true) {
    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))) {
        val systemNavigationBackgroundColor = OudsTheme.colorScheme.background.secondary //TODO Temporary color. Waiting for Material colors from Maxime.
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(100)),
            exit = fadeOut(tween(100))
        ) {
            val items = BottomBarItem.entries.toTypedArray()
            OudsNavigationBar(
                modifier = Modifier.consumeWindowInsets(WindowInsets.navigationBars)
            ) {
                items.forEach { item ->
                    OudsNavigationBarItem(
                        modifier = Modifier.weight(1f),
                        selected = currentRoute == item.route,
                        icon = OudsNavigationBarItemIcon(painter = painterResource(item.iconRes), ""),
                        label = stringResource(item.titleRes),
                        onClick = { navigateToRoute(item.route) }
                    )
                }
            }
        }
        val systemNavigationBarBackgroundColor by animateColorAsState(if (visible) systemNavigationBackgroundColor else MaterialTheme.colorScheme.surface)
        Spacer(
            modifier = Modifier
                .background(systemNavigationBarBackgroundColor)
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
                .fillMaxWidth()
        )
    }
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

@PreviewLightDark
@Composable
private fun PreviewBottomBar() = OudsPreview {
    BottomBar(currentRoute = "", navigateToRoute = {})
}
