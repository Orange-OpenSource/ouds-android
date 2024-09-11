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

package com.orange.ouds.core.component.button

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test

internal class OudsButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsButtonRendersAsExpected() {
        with(composeTestRule) {
            val text = "Click"
            val onClick = {}

            setOudsContent {
                OudsButton(
                    text = text,
                    onClick = onClick,
                )
            }

            onNodeWithText(text).assertIsDisplayed()
        }
    }
}