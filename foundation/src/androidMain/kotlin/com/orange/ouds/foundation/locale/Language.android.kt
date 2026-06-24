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

package com.orange.ouds.foundation.locale

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.core.app.LocaleManagerCompat
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.orange.ouds.foundation.extensions.orElse
import java.util.Locale

@Composable
actual fun getLanguage(): String {
    val applicationLocaleList = if (!LocalInspectionMode.current) {
        val context = LocalContext.current
        LocaleManagerCompat.getApplicationLocales(context)
    } else {
        LocaleListCompat.getEmptyLocaleList()
    }
    val locale = applicationLocaleList.get(0)
        .orElse { ConfigurationCompat.getLocales(LocalConfiguration.current).get(0) }
        .orElse { Locale.getDefault() }

    return locale.toString()
}
