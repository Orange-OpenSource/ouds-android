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

package com.orange.ouds.app.ui.about

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Locale
import com.orange.ouds.app.R

//Dialog which can change language from
@Composable
fun LanguageChangeDialog(onDismiss: () -> Unit, onLanguageSelected: (Locale) -> Unit) {
    val availableLanguages = listOf(
        Pair(stringResource(id = R.string.app_about_choose_language_english), Locale("en", "US")),
        Pair(stringResource(id = R.string.app_about_choose_language_arabic), Locale("ar", "EG"))
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.app_about_choose_language_label)) },
        text = {
            Column {
                availableLanguages.forEach { (languageName, locale) ->
                    Text(
                        text = languageName,
                        modifier = Modifier
                            .clickable {
                                onLanguageSelected(locale)
                                onDismiss()
                            }
                            .padding(vertical = 8.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(id = R.string.app_about_choose_language_cancel))
            }
        }
    )
}
//Function Used To Change Language
fun updateLocale(context: Context, locale: Locale) {
    val resources = context.resources
    val configuration = resources.configuration
        Locale.setDefault(locale)
        configuration.setLocale(locale)
    context.createConfigurationContext(configuration)
    (context as? AppCompatActivity)?.recreate()
}