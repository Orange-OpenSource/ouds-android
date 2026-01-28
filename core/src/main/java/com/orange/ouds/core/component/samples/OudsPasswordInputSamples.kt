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

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.core.component.OudsPasswordInput
import com.orange.ouds.core.component.common.OudsError
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsPasswordInputSample() {
    var value = ""
    OudsPasswordInput(
        value = value,
        onValueChange = { value = it },
        leadingIcon = true,
        label = "Password",
        helperText = "Password must be at least 8 characters.",
    )
}

@Composable
internal fun OudsPasswordInputErrorSample() {
    var value = "abc"
    OudsPasswordInput(
        value = value,
        onValueChange = { value = it },
        label = "Password",
        error = OudsError("Password must be at least 8 characters.")
    )
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputSample() = OudsPreview {
    OudsPasswordInputSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsPasswordInputErrorSample() = OudsPreview {
    OudsPasswordInputErrorSample()
}