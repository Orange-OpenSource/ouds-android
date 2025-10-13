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
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsRadioButtonItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsRadioButtonItem_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsRadioButtonItem"
            val selected = false
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsRadioButtonItem(
                    selected = selected,
                    label = "Label",
                    onClick = onClick,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsNotSelected()
            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }

    @Test
    fun oudsRadioButtonItem_withHelperText_helperTextDisplayed() {
        with(composeTestRule) {
            val helperText = "Helper text"

            setOudsContent {
                OudsRadioButtonItem(
                    selected = false,
                    label = "Label",
                    helperText = helperText,
                    onClick = {}
                )
            }

            onNodeWithText(helperText).assertIsDisplayed()
        }
    }

    @Test
    fun oudsRadioButtonItem_withBlankHelperText_helperTextNotDisplayed() {
        with(composeTestRule) {
            val helperText = "   "

            setOudsContent {
                OudsRadioButtonItem(
                    selected = false,
                    label = "Label",
                    helperText = helperText,
                    onClick = {}
                )
            }

            onNodeWithText(helperText).assertIsNotDisplayed()
        }
    }

    @Test
    fun oudsRadioButtonItem_withAdditionalLabel_additionalLabelDisplayed() {
        with(composeTestRule) {
            val additionalLabel = "Additional label"

            setOudsContent {
                OudsRadioButtonItem(
                    selected = false,
                    label = "Label",
                    additionalLabel = additionalLabel,
                    onClick = {}
                )
            }

            onNodeWithText(additionalLabel).assertIsDisplayed()
        }
    }

    @Test
    fun oudsRadioButtonItem_withBlankAdditionalLabel_additionalLabelNotDisplayed() {
        with(composeTestRule) {
            val additionalLabel = "   "

            setOudsContent {
                OudsRadioButtonItem(
                    selected = false,
                    label = "Label",
                    additionalLabel = additionalLabel,
                    onClick = {}
                )
            }

            onNodeWithText(additionalLabel).assertIsNotDisplayed()
        }
    }
}
