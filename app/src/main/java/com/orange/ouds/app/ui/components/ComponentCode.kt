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
import com.orange.ouds.core.component.OudsColoredBoxColor

fun Code.Builder.coloredBoxCall(onColoredBox: Boolean, content: Code.Builder.() -> Unit) {
    if (onColoredBox) {
        functionCall("OudsColoredBox") {
            trailingLambda = true
            typedArgument(Argument.Color, OudsColoredBoxColor.BrandPrimary)
            lambdaArgument(Argument.Content, content)
        }
    } else {
        content()
    }
}

fun FunctionCall.Builder.painterArgument(@DrawableRes id: Int) {
    functionCallArgument(Argument.Painter, "painterResource") {
        typedArgument(Argument.Id, id)
    }
}

fun FunctionCall.Builder.stringArgument(name: String, @StringRes id: Int) = formattableArgument(name) { "\"${it.getString(id)}\"" }

fun FunctionCall.Builder.contentDescriptionArgument(@StringRes id: Int) = stringArgument(Argument.ContentDescription, id)
fun FunctionCall.Builder.contentDescriptionArgument(@StringRes id: Int, vararg formatArgs: Any) =
    stringResourceArgument(Argument.ContentDescription, id, formatArgs)

fun FunctionCall.Builder.enabledArgument(value: Boolean) = typedArgument(Argument.Enabled, value)

fun FunctionCall.Builder.labelArgument(label: String?) = typedArgument(Argument.Label, label)
fun FunctionCall.Builder.labelArgument(@StringRes id: Int) = stringResourceArgument(Argument.Label, id)

fun FunctionCall.Builder.onClickArgument(init: Code.Builder.() -> Unit = {}) = lambdaArgument(Argument.OnClick, init)

private object Argument {

    const val Color = "color"
    const val ContentDescription = "contentDescription"
    const val Content = "content"
    const val Enabled = "enabled"
    const val Id = "id"
    const val Label = "label"
    const val OnClick = "onClick"
    const val Painter = "painter"
}
