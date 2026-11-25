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

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

internal class OudsCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsCard_click_succeeds() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsCard(onClick = onClick) {
                    Text("Content")
                }
            }

            onNode(hasClickAction()).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsElevatedCard_click_succeeds() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsElevatedCard(onClick = onClick) {
                    Text("Content")
                }
            }

            onNode(hasClickAction()).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsOutlinedCard_click_succeeds() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsOutlinedCard(onClick = onClick) {
                    Text("Content")
                }
            }

            onNode(hasClickAction()).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsCard_disabledClick_fails() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsCard(
                    onClick = onClick,
                    enabled = false
                ) {
                    Text("Content")
                }
            }

            onNode(hasClickAction()).performClick()
            verify(onClick, never()).invoke()
        }
    }

    @Test
    fun oudsCard_disabledWithButton_buttonDisabled() {
        with(composeTestRule) {
            val buttonTag = "button"

            setOudsContent {
                OudsCard(enabled = false) {
                    OudsButton(
                        label = "Test",
                        onClick = {},
                        modifier = Modifier.testTag(buttonTag)
                    )
                }
            }

            onNodeWithTag(buttonTag).assertIsNotEnabled()
        }
    }

    @Test
    fun oudsCard_withLoaderClick_fails() {
        with(composeTestRule) {
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsCard(
                    onClick = onClick,
                    loader = OudsCardLoader(progress = null)
                ) {
                    Text("Content")
                }
            }

            onNode(hasClickAction()).performClick()
            verify(onClick, never()).invoke()
        }
    }
}
