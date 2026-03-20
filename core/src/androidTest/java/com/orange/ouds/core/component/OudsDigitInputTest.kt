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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsDigitInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsDigitInput_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsDigitInput"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsDigitInput_digit_displayed() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = '7',
                    onClick = {}
                )
            }

            onNodeWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertIsDisplayed()
            onNodeWithText("-").assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_emptyDigit_showsPlaceholder() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onClick = {})
            }

            onNodeWithText("-").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_onlyDigits_displayed() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = 'a',
                    onClick = {})
            }

            onNodeWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertIsNotDisplayed()
            onNodeWithText("-").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_focused_hidesPlaceholder() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onClick = {},
                    state = OudsDigitInputState.Focused
                )
            }

            onNodeWithText("-").assertDoesNotExist()
        }
    }

    @Test
    fun oudsDigitInput_focused_showsCursor() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = '5',
                    onClick = {},
                    state = OudsDigitInputState.Focused
                )
            }

            onNodeWithText("|").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_notFocused_hidesCursor() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = '5',
                    onClick = {},
                    state = OudsDigitInputState.Enabled
                )
            }

            onNodeWithText("|").assertDoesNotExist()
        }
    }

    @Test
    fun oudsDigitInput_placeholderDisabled_noPlaceholderWhenEmpty() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(
                    digit = null,
                    onClick = {},
                    placeholder = false
                )
            }

            onNodeWithText("-").assertDoesNotExist()
        }
    }
}
