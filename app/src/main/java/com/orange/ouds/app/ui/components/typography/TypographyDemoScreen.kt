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

package com.orange.ouds.app.ui.components.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput

@Composable
fun TypographyDemoBottomSheetContent(state: TypographyDemoState) {
    with(state) {
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_typography_common_text_tech),
            value = text,
            onValueChange = { value -> text = value },
            helperText = stringResource(id = R.string.app_components_common_annotatedTextHelperText_tech)
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_annotatedText_tech),
            checked = annotatedText,
            onCheckedChange = { annotatedText = it },
        )
    }
}

fun FunctionCall.Builder.typographyArguments(state: TypographyDemoState) = with(state) {
    if (annotatedText) {
        functionCallArgument("text", "buildAnnotatedString") {
            trailingLambda = true
            lambdaArgument("builder") {
                comment("Build annotated string")
            }
        }
    } else {
        typedArgument("text", text)
    }
}