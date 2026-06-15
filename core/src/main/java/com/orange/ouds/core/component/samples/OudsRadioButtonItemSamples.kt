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

package com.orange.ouds.core.component.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsControlItemIcon
import com.orange.ouds.core.component.OudsRadioButtonItem
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedErrorMessage
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.core.utilities.rememberRainbowHeartPainter

@Composable
internal fun OudsRadioButtonItemSample() {
    val shippingMethods = listOf("Standard delivery", "Express delivery", "Pick up in store")
    var selectedMethod by rememberSaveable { mutableStateOf(shippingMethods.first()) }

    Column(modifier = Modifier.selectableGroup()) {
        shippingMethods.forEach { method ->
            OudsRadioButtonItem(
                selected = method == selectedMethod,
                label = method,
                icon = OudsControlItemIcon(imageVector = Icons.Filled.FavoriteBorder),
                onClick = { selectedMethod = method },
                divider = true
            )
        }
    }
}

@Composable
internal fun OudsRadioButtonItemWithAnnotatedErrorMessageSample() {
    val shippingMethods = listOf("Standard delivery", "Express delivery", "Pick up in store")
    var selectedMethod by rememberSaveable { mutableStateOf("") }

    val error = OudsError(
        annotatedMessage = buildOudsAnnotatedErrorMessage {
            append("You ")
            withStrong { append("must") }
            append(" select an option")
        }
    )

    Column(modifier = Modifier.selectableGroup()) {
        shippingMethods.forEachIndexed { index, method ->
            OudsRadioButtonItem(
                selected = method == selectedMethod,
                label = method,
                onClick = { selectedMethod = method },
                error = if (index == shippingMethods.lastIndex) error else OudsError(""),
                divider = true
            )
        }
    }
}

@Composable
internal fun OudsRadioButtonItemWithUntintedIconSample() {
    val shippingMethods = listOf("Standard delivery", "Express delivery", "Pick up in store")
    var selectedMethod by rememberSaveable { mutableStateOf(shippingMethods.first()) }

    Column(modifier = Modifier.selectableGroup()) {
        shippingMethods.forEach { method ->
            OudsRadioButtonItem(
                selected = method == selectedMethod,
                label = method,
                icon = OudsControlItemIcon(painter = rememberRainbowHeartPainter(), tinted = false),
                onClick = { selectedMethod = method },
                divider = true
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonItemSample() = OudsPreview {
    OudsRadioButtonItemSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonItemWithAnnotatedErrorMessageSample() = OudsPreview {
    OudsRadioButtonItemWithAnnotatedErrorMessageSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsRadioButtonItemWithUntintedIconSample() = OudsPreview {
    OudsRadioButtonItemWithUntintedIconSample()
}
