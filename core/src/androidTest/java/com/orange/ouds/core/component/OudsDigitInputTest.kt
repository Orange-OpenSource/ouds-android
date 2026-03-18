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

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test

internal class OudsDigitInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsDigitInput_digit_displayed() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(digit = '7')
            }

            onNodeWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertIsDisplayed()
            onNodeWithText("-").assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_emptyDigit_showsPlaceholder() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(digit = null)
            }

            onNodeWithText("-").assertIsDisplayed()
        }
    }

    @Test
    fun oudsDigitInput_onlyDigits_displayed() {
        with(composeTestRule) {
            setOudsContent {
                OudsDigitInput(digit = 'a')
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
                    placeholder = false
                )
            }

            onNodeWithText("-").assertDoesNotExist()
        }
    }
}
