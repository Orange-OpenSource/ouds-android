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
import androidx.compose.ui.test.onNodeWithContentDescription
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

internal class OudsTextAreaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsTextArea_stringValueChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsTextArea"
            val onValueChange = mock<(String) -> Unit>()

            setOudsContent {
                OudsTextArea(
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
    fun oudsTextArea_textFieldStateChange_succeeds() {
        with(composeTestRule) {
            var textFieldState: TextFieldState? = null
            val testTag = "OudsTextArea"

            setOudsContent {
                textFieldState = rememberTextFieldState(initialText = "Text")
                OudsTextArea(
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
    fun oudsTextArea_textFieldValueChange_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsTextArea"
            val onValueChange = mock<(TextFieldValue) -> Unit>()

            setOudsContent {
                val text = "Text"
                OudsTextArea(
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
    fun oudsTextArea_withLabel_labelDisplayed() {
        with(composeTestRule) {
            val label = "Label"

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    label = label
                )
            }

            onNodeWithText(label).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withLabel_labelNotDisplayed() {
        with(composeTestRule) {
            val label = "   "

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    label = label
                )
            }

            onNodeWithText(label).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withLabelAndPlaceholder_labelAndPlaceholderDisplayed() {
        with(composeTestRule) {
            val label = "Label"
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextArea(
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
    fun oudsTextArea_withPlaceholder_placeholderDisplayed() {
        with(composeTestRule) {
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withPlaceholder_placeholderNotDisplayed() {
        with(composeTestRule) {
            val placeholder = "   "

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withPlaceholderAndText_placeholderNotDisplayed() {
        with(composeTestRule) {
            val placeholder = "Placeholder"

            setOudsContent {
                OudsTextArea(
                    value = "Text",
                    onValueChange = {},
                    placeholder = placeholder
                )
            }

            onNodeWithText(placeholder).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithContentDescription(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    helperText = helperText
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withHelperLink_helperLinkDisplayed() {
        with(composeTestRule) {
            val helperLink = "Helper link"

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    helperLink = OudsTextInputHelperLink(helperLink, {})
                )
            }

            onNodeWithText(helperLink).assertIsDisplayed()
        }
    }

    @Test
    fun oudsTextArea_withHelperLink_helperLinkNotDisplayed() {
        with(composeTestRule) {
            val helperLink = "   "

            setOudsContent {
                OudsTextArea(
                    value = "",
                    onValueChange = {},
                    helperLink = OudsTextInputHelperLink(helperLink, {})
                )
            }

            onNodeWithText(helperLink).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsTextArea_helperLinkClick_succeeds() {
        with(composeTestRule) {
            val helperLink = "Helper link"
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsTextArea(
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