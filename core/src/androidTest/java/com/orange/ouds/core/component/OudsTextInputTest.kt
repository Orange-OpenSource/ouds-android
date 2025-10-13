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

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsTextInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsTextInput_stringValueChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsTextInput"
            val onValueChange = mock<(String) -> Unit>()

            setOudsContent {
                OudsTextInput(
                    value = "Text",
                    onValueChange = onValueChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput(" changed")
            verify(onValueChange).invoke("Text changed")
        }
    }

    @Test
    fun oudsTextInput_textFieldStateChange_succeeds() {
        with(composeTestRule) {
            var textFieldState: TextFieldState? = null
            val testTag = "OudsTextInput"

            setOudsContent {
                textFieldState = rememberTextFieldState(initialText = "Text")
                OudsTextInput(
                    textFieldState = textFieldState,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput(" changed")
            Assert.assertEquals("Text changed", textFieldState?.text.toString())
        }
    }

    @Test
    fun oudsTextInput_textFieldValueChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsTextInput"
            val onValueChange = mock<(TextFieldValue) -> Unit>()

            setOudsContent {
                val text = "Text"
                OudsTextInput(
                    value = TextFieldValue(text, TextRange(text.length)),
                    onValueChange = onValueChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).performClick()
            onNodeWithTag(testTag).performTextInput(" changed")
            val expectedText = "Text changed"
            verify(onValueChange).invoke(TextFieldValue(expectedText, TextRange(expectedText.length)))
        }
    }

    @Test
    fun oudsTextInput_withLabel_labelDisplayed() {
        with(composeTestRule) {
            val label = "Label"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    label = label
                )
            }

            onNodeWithText(label).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withLabel_labelNotDisplayed() {
        with(composeTestRule) {
            val label = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    label = label
                )
            }

            onNodeWithText(label).assertIsNotDisplayed()
        }
    }
    
    @Test
    fun oudsTextInput_withLabelAndPlaceholder_labelAndPlaceholderDisplayed() {
        with(composeTestRule) {
            val label = "Label"
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    label = label,
                    placeholder = placeholder
                )
            }

            onNodeWithText(label).assertIsDisplayed()
            onNodeWithText(placeholder).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withPlaceholder_placeholderDisplayed() {
        with(composeTestRule) {
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withPlaceholder_placeholderNotDisplayed() {
        with(composeTestRule) {
            val placeholder = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withPlaceholderAndText_placeholderNotDisplayed() {
        with(composeTestRule) {
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextInput(
                    value = "Text",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withPrefix_prefixDisplayed() {
        with(composeTestRule) {
            val prefix = "Prefix"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    prefix = prefix
                )
            }

            onNodeWithText(prefix).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withPrefix_prefixNotDisplayed() {
        with(composeTestRule) {
            val prefix = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    prefix = prefix
                )
            }

            onNodeWithText(prefix).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withSuffix_suffixDisplayed() {
        with(composeTestRule) {
            val suffix = "Suffix"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    suffix = suffix
                )
            }

            onNodeWithText(suffix).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withSuffix_suffixNotDisplayed() {
        with(composeTestRule) {
            val suffix = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    suffix = suffix
                )
            }

            onNodeWithText(suffix).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withHelperLink_helperLinkDisplayed() {
        with(composeTestRule) {
            val helperLink = "Helper link"

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    helperLink = OudsTextInputHelperLink(helperLink, {})
                )
            }

            onNodeWithText(helperLink).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextInput_withHelperLink_helperLinkNotDisplayed() {
        with(composeTestRule) {
            val helperLink = "   "

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    helperLink = OudsTextInputHelperLink(helperLink, {})
                )
            }

            onNodeWithText(helperLink).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextInput_helperLinkClick_succeeds() {
        with(composeTestRule) {
            val helperLink = "Helper link"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsTextInput(
                    value = "",
                    onValueChange = {},
                    helperLink = OudsTextInputHelperLink(helperLink, onClick)
                )
            }

            onNodeWithText(helperLink).performClick()
            verify(onClick).invoke()
        }
    }
}
