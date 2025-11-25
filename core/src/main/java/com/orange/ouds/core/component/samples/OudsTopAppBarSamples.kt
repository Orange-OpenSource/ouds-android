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

package com.orange.ouds.core.component.samples

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsCenterAlignedTopAppBar
import com.orange.ouds.core.component.OudsLargeTopAppBar
import com.orange.ouds.core.component.OudsMediumTopAppBar
import com.orange.ouds.core.component.OudsTopAppBar
import com.orange.ouds.core.component.OudsTopAppBarAction
import com.orange.ouds.core.component.OudsTopAppBarNavigationIcon
import com.orange.ouds.core.utilities.OudsPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsTopAppBarSample() {
    OudsTopAppBar(
        title = "Title",
        navigationIcon = OudsTopAppBarNavigationIcon.Back {},
        actions = listOf(
            OudsTopAppBarAction.Icon(Icons.Outlined.FavoriteBorder, "") {},
            OudsTopAppBarAction.Avatar(monogram = "A", color = Color.White, backgroundColor = Color(0xffd5204e), "") {}
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsCenterAlignedTopAppBarSample() {
    OudsCenterAlignedTopAppBar(
        title = "Title",
        navigationIcon = OudsTopAppBarNavigationIcon.Back {},
        actions = listOf(
            OudsTopAppBarAction.Icon(Icons.Outlined.FavoriteBorder, "") {},
            OudsTopAppBarAction.Avatar(monogram = "A", color = Color.White, backgroundColor = Color(0xff247a85), "") {}
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsMediumTopAppBarSample() {
    OudsMediumTopAppBar(
        title = "Title",
        navigationIcon = OudsTopAppBarNavigationIcon.Back {},
        actions = listOf(
            OudsTopAppBarAction.Icon(Icons.Outlined.FavoriteBorder, "") {},
            OudsTopAppBarAction.Avatar(monogram = "A", color = Color.Black, backgroundColor = Color(0xfffbcd00), "") {}
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OudsLargeTopAppBarSample() {
    OudsLargeTopAppBar(
        title = "Title",
        navigationIcon = OudsTopAppBarNavigationIcon.Back {},
        actions = listOf(
            OudsTopAppBarAction.Icon(Icons.Outlined.FavoriteBorder, "") {},
            OudsTopAppBarAction.Avatar(monogram = "A", color = Color.White, backgroundColor = Color(0xff0073b2), "") {}
        )
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsTopAppBarSample() = OudsPreview {
    OudsTopAppBarSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsCenterAlignedTopAppBarSample() = OudsPreview {
    OudsCenterAlignedTopAppBarSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsMediumTopAppBarSample() = OudsPreview {
    OudsMediumTopAppBarSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsLargeTopAppBarSample() = OudsPreview {
    OudsLargeTopAppBarSample()
}
