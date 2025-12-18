/* * Software Name: OUDS Android
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class OudsNavigationBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private data class TestNavigationBarItem(
        val label: String,
        val icon: ImageVector,
        val badge: OudsNavigationBarItemBadge? = null
    )

    companion object {
        private const val Home = "Home"
        private const val Profile = "Profile"
        private const val Info = "Info"
        private const val InfoBadgeCount = 27
    }

    private val navigationBarItems = listOf(
        TestNavigationBarItem(label = Home, icon = Icons.Filled.Home),
        TestNavigationBarItem(label = Profile, icon = Icons.Filled.Person),
        TestNavigationBarItem(
            label = Info,
            icon = Icons.Filled.Info,
            badge = OudsNavigationBarItemBadge(contentDescription = InfoBadgeCount.toString(), InfoBadgeCount)
        )
    )

    @Test
    fun oudsNavigationBar_displaysAllItems() {
        with(composeTestRule) {
            setOudsContent {
                OudsNavigationBar(
                    items = navigationBarItems.mapIndexed { index, item ->
                        OudsNavigationBarItem(
                            selected = index == 0,
                            onClick = { },
                            icon = OudsNavigationBarItemIcon(imageVector = item.icon),
                            label = item.label,
                            badge = item.badge
                        )
                    }
                )
            }

            onNodeWithText(Home).assertIsDisplayed()
            onNodeWithText(Home).assertIsSelected()
            onNodeWithText(Profile).assertIsDisplayed()
            onNodeWithText(Info).assertIsDisplayed()
        }
    }

    @Test
    fun oudsNavigationBar_itemClick_succeeds() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()
            setOudsContent {
                OudsNavigationBar(
                    items = navigationBarItems.mapIndexed { index, item ->
                        OudsNavigationBarItem(
                            selected = false,
                            onClick = if (index == 0) {
                                onClick
                            } else {
                                {}
                            },
                            icon = OudsNavigationBarItemIcon(imageVector = item.icon),
                            label = item.label
                        )
                    }
                )
            }

            onNodeWithText(Home).assertIsNotSelected()
            onNodeWithText(Home).performClick()
            verify(onClick).invoke()
        }
    }
}
