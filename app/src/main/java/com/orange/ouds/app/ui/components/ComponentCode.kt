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
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.common.OudsError

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

fun FunctionCall.Builder.painterArgument(@DrawableRes resId: Int) {
    functionCallArgument(Argument.Painter, "painterResource") {
        typedArgument(Argument.Id, resId)
    }
}

fun FunctionCall.Builder.colorArgument(name: String, color: Color) {
    constructorCallArgument<Color>(name) {
        isMultiline = false
        rawArgument(null, "0x${color.toArgb().toHexString()}")
    }
}

fun FunctionCall.Builder.stringArgument(name: String, @StringRes resId: Int) = formattableArgument(name) { "\"${it.getString(resId)}\"" }

fun FunctionCall.Builder.constrainedMaxWidthArgument(value: Boolean) = typedArgument(Argument.ConstrainedMaxWidth, value)

fun FunctionCall.Builder.contentDescriptionArgument(@StringRes resId: Int) = stringArgument(Argument.ContentDescription, resId)
fun FunctionCall.Builder.contentDescriptionArgument(@StringRes resId: Int, vararg formatArgs: Any) =
    stringResourceArgument(Argument.ContentDescription, resId, *formatArgs)

fun FunctionCall.Builder.contentDescriptionArgument(@PluralsRes resId: Int, count: Int, vararg formatArgs: Any) =
    pluralStringResourceArgument(Argument.ContentDescription, resId, count, *formatArgs)


fun FunctionCall.Builder.enabledArgument(value: Boolean) = typedArgument(Argument.Enabled, value)

fun FunctionCall.Builder.errorArgument(message: String) {
    constructorCallArgument<OudsError>(Argument.Error) {
        typedArgument("message", message)
    }
}

fun FunctionCall.Builder.errorArgument(@StringRes messageResId: Int) {
    constructorCallArgument<OudsError>(Argument.Error) {
        stringResourceArgument("message", messageResId)
    }
}

fun FunctionCall.Builder.labelArgument(label: String?) = typedArgument(Argument.Label, label)
fun FunctionCall.Builder.labelArgument(@StringRes resId: Int) = stringResourceArgument(Argument.Label, resId)

fun FunctionCall.Builder.onClickArgument(init: Code.Builder.() -> Unit = {}) = lambdaArgument(Argument.OnClick, init)

fun FunctionCall.Builder.readOnlyArgument(value: Boolean) = typedArgument(Argument.ReadOnly, value)

private object Argument {

    const val Color = "color"
    const val ConstrainedMaxWidth = "constrainedMaxWidth"
    const val ContentDescription = "contentDescription"
    const val Content = "content"
    const val Enabled = "enabled"
    const val Error = "error"
    const val Id = "id"
    const val Label = "label"
    const val OnClick = "onClick"
    const val Painter = "painter"
    const val ReadOnly = "readOnly"
}
