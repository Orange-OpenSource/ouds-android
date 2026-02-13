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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsFloatingActionButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsFloatingActionButton_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsFloatingActionButton"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsFloatingActionButton(
                    icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsSmallFloatingActionButton_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsSmallFloatingActionButton"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsSmallFloatingActionButton(
                    icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsLargeFloatingActionButton_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsLargeFloatingActionButton"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsLargeFloatingActionButton(
                    icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsExtendedFloatingActionButtonWithLabelOnly_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsExtendedFloatingActionButton"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsExtendedFloatingActionButton(
                    label = "Label",
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsExtendedFloatingActionButtonWithLabelAndIcon_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsExtendedFloatingActionButton"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsExtendedFloatingActionButton(
                    label = "Label",
                    icon = OudsFloatingActionButtonIcon(Icons.Filled.FavoriteBorder, ""),
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }
}
