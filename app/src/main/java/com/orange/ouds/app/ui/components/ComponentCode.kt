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

package com.orange.ouds.app.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.core.component.OudsColoredBox

fun Code.Builder.coloredBoxCall(onColoredBox: Boolean, content: Code.Builder.() -> Unit) {
    if (onColoredBox) {
        functionCall(OudsColoredBox::class.simpleName.orEmpty()) {
            trailingLambda = true
            typedArgument("color", OudsColoredBox.Color.BrandPrimary)
            lambdaArgument("content", content)
        }
    } else {
        content()
    }
}

fun FunctionCall.Builder.painterArgument(@DrawableRes id: Int) {
    functionCallArgument("painter", "painterResource") {
        typedArgument("id", id)
    }
}

fun FunctionCall.Builder.contentDescriptionArgument(@StringRes id: Int) {
    formattableArgument("contentDescription") { "\"${it.getString(id)}\"" }
}

fun FunctionCall.Builder.onClickArgument(init: Code.Builder.() -> Unit = {}) = lambdaArgument("onClick", init)

fun FunctionCall.Builder.labelArgument(label: String?) = typedArgument("label", label)

fun FunctionCall.Builder.enabledArgument(boolean: Boolean) = typedArgument("enabled", boolean)
