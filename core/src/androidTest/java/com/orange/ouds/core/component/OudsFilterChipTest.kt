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
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.orange.ouds.core.extension.setOudsContent
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

internal class OudsFilterChipTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun oudsFilterChip_click_succeeds() {
        with(composeTestRule) {
            val testTag = "OudsFilterChip"
            val selected = false
            val onClick = mock<() -> Unit>()

            setOudsContent {
                OudsFilterChip(
                    selected = selected,
                    onClick = onClick,
                    label = "Label",
                    modifier = Modifier.testTag(testTag)
                )
            }

            onNodeWithTag(testTag).assertIsNotSelected()
            onNodeWithTag(testTag).performClick()
            verify(onClick).invoke()
        }
    }
}
