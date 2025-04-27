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

package com.orange.ouds.app.ui.utilities.composable

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.theme.orange.OrangeTheme

@Composable
fun CodeSnippet(modifier: Modifier = Modifier, init: Code.Builder.() -> Unit) {
    val code = Code.Builder().apply(init).build()
    val context = LocalContext.current
    CodeSnippet(code = code.format(context), modifier = modifier)
}

@Composable
fun CodeSnippet(code: String, modifier: Modifier = Modifier) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

    Row(
        modifier = modifier
            .background(color = OudsTheme.colorScheme.background.secondary)
            .border(width = 1.dp, color = OudsTheme.colorScheme.border.default),
        horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.shorter),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = OudsTheme.spaces.fixed.medium)
                .padding(start = OudsTheme.spaces.fixed.medium),
            text = code,
            style = TextStyle(fontFamily = FontFamily.Monospace),
            color = OudsTheme.colorScheme.content.default
        )
        val context = LocalContext.current
        val clipboardManager = LocalClipboardManager.current
        IconButton(
            onClick = { copyCodeToClipboard(context, code, clipboardManager) },
            colors = IconButtonDefaults.iconButtonColors(contentColor = OudsTheme.colorScheme.content.default)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_copy),
                contentDescription = stringResource(id = R.string.app_common_copyCode_a11y)
            )
        }
    }
    }
}

private fun copyCodeToClipboard(context: Context, code: String, clipboardManager: ClipboardManager) {
    clipboardManager.setText(AnnotatedString(code))
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
        Toast.makeText(context, context.getString(R.string.app_common_codeCopied_text), Toast.LENGTH_SHORT).show()
    }
}

@PreviewLightDark
@Composable
internal fun PreviewCodeSnippet() = OudsPreview {
    CodeSnippet {
        comment("Apply Orange theme")
        functionCall("OudsTheme") {
            trailingLambda = true
            constructorCallArgument<OrangeTheme>("themeContract")
            functionCallArgument("darkThemeEnabled", "isSystemInDarkTheme")
            lambdaArgument("content") {
                comment("Content")
            }
        }
    }
}
