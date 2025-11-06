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
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsSwitchTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsSwitch_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsSwitch"
            val checked = false
            val onCheckedChange = mock<(Boolean) -> Unit>()

            setOudsContent {
                OudsSwitch(
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsOff()
            onNodeWithTag(testTag).performClick()
            verify(onCheckedChange).invoke(!checked)
        }
    }
}
