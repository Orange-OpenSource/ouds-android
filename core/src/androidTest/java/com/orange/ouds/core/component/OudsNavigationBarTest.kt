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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
        val enabled: Boolean = true,
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
        TestNavigationBarItem(label = Profile, icon = Icons.Filled.Person, enabled = false),
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
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    OudsNavigationBar {
                        navigationBarItems.forEachIndexed { index, item ->
                            OudsNavigationBarItem(
                                selected = index == 0,
                                onClick = { },
                                icon = OudsNavigationBarItemIcon(imageVector = item.icon, contentDescription = ""),
                                label = item.label,
                                enabled = item.enabled,
                                badge = item.badge,
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag(item.label)
                            )
                        }
                    }
                }
            }

            onNodeWithTag(Home).assertIsDisplayed()
            onNodeWithTag(Home).assertIsSelected()
            onNodeWithTag(Profile).assertIsDisplayed()
            onNodeWithTag(Profile).assertIsNotEnabled()
            onNodeWithTag(Info).assertIsDisplayed()
        }
    }

    @Test
    fun oudsNavigationBar_itemClick_succeeds() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()
            setOudsContent {
                OudsNavigationBar {
                    navigationBarItems.forEachIndexed { index, item ->
                        OudsNavigationBarItem(
                            selected = false,
                            onClick = if (index == 0) {
                                onClick
                            } else {
                                {}
                            },
                            icon = OudsNavigationBarItemIcon(imageVector = item.icon, contentDescription = ""),
                            label = item.label,
                            enabled = item.enabled,
                            modifier = Modifier
                                .weight(1f)
                                .testTag(item.label)
                        )
                    }
                }
            }

            onNodeWithTag(Home).assertIsNotSelected()
            onNodeWithTag(Home).performClick()
            verify(onClick).invoke()
        }
    }
}
