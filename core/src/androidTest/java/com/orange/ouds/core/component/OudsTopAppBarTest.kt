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

package com.orange.ouds.core.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extension.setOudsContent
import com.orange.ouds.core.utilities.PreviewCheckerboardPainter
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class OudsTopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun oudsTopAppBar_navigationIconClick_succeeds() {
        with(composeTestRule) {
            val navigationIconContentDescription = "Navigation icon content description"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsTopAppBar(
                    title = "Title",
                    navigationIcon = OudsTopAppBarNavigationIcon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = navigationIconContentDescription,
                        onClick = onClick
                    )
                )
            }

            onNodeWithContentDescription(navigationIconContentDescription).performClick()
            verify(onClick).invoke()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun oudsTopAppBar_actionClick_succeeds() {
        with(composeTestRule) {
            val onIconClick = mock<() -> Unit>()
            val iconContentDescription = "Icon content description"
            val onImageAvatarClick = mock<() -> Unit>()
            val imageAvatarContentDescription = "Image avatar content description"
            val onMonogramAvatarClick = mock<() -> Unit>()
            val monogramAvatarContentDescription = "Monogram avatar content description"

            setOudsContent {
                OudsTopAppBar(
                    title = "Title",
                    actions = listOf(
                        OudsTopAppBarAction.Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = iconContentDescription,
                            onClick = onIconClick
                        ),
                        OudsTopAppBarAction.Avatar(
                            painter = PreviewCheckerboardPainter(4.dp, Color.White, Color.Black),
                            contentDescription = imageAvatarContentDescription,
                            onClick = onImageAvatarClick
                        ),
                        OudsTopAppBarAction.Avatar(
                            monogram = "A",
                            color = Color.White,
                            backgroundColor = Color.Black,
                            contentDescription = monogramAvatarContentDescription,
                            onClick = onMonogramAvatarClick
                        )
                    )
                )
            }

            onNodeWithContentDescription(iconContentDescription).performClick()
            verify(onIconClick).invoke()
            onNodeWithContentDescription(imageAvatarContentDescription).performClick()
            verify(onImageAvatarClick).invoke()
            onNodeWithContentDescription(monogramAvatarContentDescription).performClick()
            verify(onMonogramAvatarClick).invoke()
        }
    }
}
