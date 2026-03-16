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

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

internal class OudsDigitInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsDigitInput_digitChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"
            val onDigitChange = mock<(Char?) -> Unit>()

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = onDigitChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("5")
            // Wait for LaunchedEffect and snapshotFlow to complete
            waitForIdle()
            verify(onDigitChange).invoke('5')
        }
    }

    @Test
    fun oudsDigitInput_initialDigit_displayed() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"

            setOudsContent {
                OudsDigitInput(
                    digit = '7',
                    onDigitChange = {},
                    modifier = Modifier.testTag(testTag)
                )
            }

            // The semantic text contains the actual digit (for accessibility)
            // even though it's visually obfuscated as '•'
            onNodeWithTag(testTag).assertTextEquals("7")
            onNodeWithText("-").assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_emptyDigit_showsPlaceholder() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = {}
                )
            }

            onNodeWithText("-").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_focused_hidesPlaceholder() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"
            var focusManager: FocusManager? = null

            setOudsContent {
                focusManager = LocalFocusManager.current
                OudsDigitInput(
                    digit = null,
                    onDigitChange = {},
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithText("-").assertIsDisplayed()

            onNodeWithTag(testTag).performClick()
            waitForIdle()
            onNodeWithText("-").assertDoesNotExist()

            runOnIdle {
                focusManager?.clearFocus()
            }
            waitForIdle()
            onNodeWithText("-").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_onlyDigits_accepted() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"
            val onDigitChange = mock<(Char?) -> Unit>()

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = onDigitChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("a")
            // Wait for LaunchedEffect and snapshotFlow to complete
            waitForIdle()
            verify(onDigitChange, never()).invoke(any())
            onNodeWithText("-").assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_singleDigit_accepted() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"
            val onDigitChange = mock<(Char?) -> Unit>()

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = onDigitChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("123")
            // Wait for LaunchedEffect and snapshotFlow to complete
            waitForIdle()
            onNodeWithTag(testTag).assertTextEquals("3")
            verify(onDigitChange).invoke('3')
        }
    }

    @Test
    fun oudsDigitInput_disabled_notEnabled() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = {},
                    enabled = false,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsNotEnabled()
        }
    }

    @Test
    fun oudsDigitInput_readOnly_notEnabled() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onDigitChange = {},
                    readOnly = true,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsNotEnabled()
        }
    }
}
