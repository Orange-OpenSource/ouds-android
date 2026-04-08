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

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalCursorBlinkEnabled
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsPinCodeInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsPinCodeInput_valueChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            val onValueChange = mock<(String) -> Unit>()

            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = onValueChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("123456")
            waitForIdle()
            verify(onValueChange).invoke("123456")
        }
    }

    @Test
    fun oudsPinCodeInput_lengthFour_displaysCorrectNumberOfDigits() {
        with(composeTestRule) {
            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = {},
                    length = OudsPinCodeInputLength.Four
                )
            }

            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_lengthSix_displaysCorrectNumberOfDigits() {
        with(composeTestRule) {
            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = {},
                    length = OudsPinCodeInputLength.Six
                )
            }

            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(6)
        }
    }

    @Test
    fun oudsPinCodeInput_lengthEight_displaysCorrectNumberOfDigits() {
        with(composeTestRule) {
            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = {},
                    length = OudsPinCodeInputLength.Eight
                )
            }

            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(8)
        }
    }

    @Test
    fun oudsPinCodeInput_sequentialInput_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            var pinCode = ""

            setOudsContent {
                var value by remember { mutableStateOf(pinCode) }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = {
                        pinCode = it
                        value = it
                    },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("1")
            waitForIdle()
            Assert.assertEquals("1---", pinCode)

            onNodeWithTag(testTag).performTextInput("2")
            waitForIdle()
            Assert.assertEquals("12--", pinCode)

            onNodeWithTag(testTag).performTextInput("3")
            waitForIdle()
            Assert.assertEquals("123-", pinCode)

            onNodeWithTag(testTag).performTextInput("4")
            waitForIdle()
            Assert.assertEquals("1234", pinCode)
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_maxLengthReached_replacesLastDigit() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            var pinCode = "1234"

            setOudsContent {
                var value by remember { mutableStateOf(pinCode) }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = {
                        value = it
                        pinCode = it
                    },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("5")
            waitForIdle()
            Assert.assertEquals("1235", pinCode)
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_nonNumericInput_filtered() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            val onValueChange = mock<(String) -> Unit>()
            var focusManager: FocusManager? = null

            setOudsContent {
                focusManager = LocalFocusManager.current
                OudsPinCodeInput(
                    value = "",
                    onValueChange = onValueChange,
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("abc")
            runOnIdle {
                focusManager?.clearFocus()
            }
            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_partialInput_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            var pinCode = ""
            var focusManager: FocusManager? = null

            setOudsContent {
                focusManager = LocalFocusManager.current
                var value by remember { mutableStateOf(pinCode) }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = {
                        value = it
                        pinCode = it
                    },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput("12")
            runOnIdle {
                focusManager?.clearFocus()
            }
            Assert.assertEquals("12--", pinCode)
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(2)
            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(2)
        }
    }

    @Test
    fun oudsPinCodeInput_replaceValue_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            var pinCode = "1234"

            setOudsContent {
                var value by remember { mutableStateOf(pinCode) }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = {
                        value = it
                        pinCode = it
                    },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextReplacement("567890")
            waitForIdle()
            Assert.assertEquals("5678", pinCode)
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_clearValue_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"
            var pinCode = "1234"
            var focusManager: FocusManager? = null

            setOudsContent {
                focusManager = LocalFocusManager.current
                var value by remember { mutableStateOf(pinCode) }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = {
                        value = it
                        pinCode = it
                    },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextReplacement("")
            runOnIdle {
                focusManager?.clearFocus()
            }
            Assert.assertEquals("----", pinCode)
            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(4)
        }
    }

    @Test
    fun oudsPinCodeInput_emptyDigitClick_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"

            setOudsContent {
                var value by remember { mutableStateOf("12") }
                OudsPinCodeInput(
                    value = value,
                    onValueChange = { value = it },
                    length = OudsPinCodeInputLength.Four,
                    modifier = Modifier.testTag(testTag)
                )
            }

            val placeholderNodes = onAllNodesWithText(OudsDigitInputPlaceholder.toString())
            placeholderNodes[0].performClick()
            waitForIdle()
            onNodeWithTag(testTag).assertIsFocused()
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(2)
            onNodeWithText(OudsDigitInputCursor.toString()).assertIsDisplayed()
            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(1)
        }
    }

    @Test
    fun oudsPinCodeInput_obfuscatedDigitClick_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsPinCodeInput"

            setOudsContent {
                CompositionLocalProvider(LocalCursorBlinkEnabled provides false) {
                    var value by remember { mutableStateOf("12") }
                    OudsPinCodeInput(
                        value = value,
                        onValueChange = { value = it },
                        length = OudsPinCodeInputLength.Four,
                        modifier = Modifier.testTag(testTag)
                    )
                }
            }

            val obfuscationCharacterNodes = onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString())
            obfuscationCharacterNodes[0].performClick()
            waitForIdle()
            onNodeWithTag(testTag).assertIsFocused()
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(2)
            onNode(hasTextExactly(OudsPasswordInputTextObfuscationCharacter.toString(), OudsDigitInputCursor.toString())).assertIsDisplayed()
            onAllNodesWithText(OudsDigitInputPlaceholder.toString()).assertCountEquals(2)
        }
    }

    @Test
    fun oudsPinCodeInput_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Enter the 4-digit code sent to your phone."

            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithContentDescription(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsPinCodeInput_withBlankHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "    "

            setOudsContent {
                OudsPinCodeInput(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithContentDescription(helperText).assertIsNotDisplayed()
        }
    }

    // Error message display can't be tested because there is no matcher for the error semantics
    //@Test
    //fun oudsPinCodeInput_withError_errorMessageDisplayed() {}

    @Test
    fun oudsPinCodeInput_withError_hidesPlaceholders() {
        with(composeTestRule) {
            setOudsContent {
                OudsPinCodeInput(
                    value = "12",
                    onValueChange = {},
                    length = OudsPinCodeInputLength.Four,
                    error = OudsError("Invalid code")
                )
            }

            // Even with error, digits should be obfuscated
            onAllNodesWithText(OudsPasswordInputTextObfuscationCharacter.toString()).assertCountEquals(2)
            onNodeWithText(OudsDigitInputPlaceholder.toString()).assertIsNotDisplayed()
        }
    }
}
