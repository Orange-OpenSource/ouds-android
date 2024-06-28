/*
 * Software Name: Orange Unified Design System
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

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun AboutScreen(onMenuItemClick: (id: Int) -> Unit) {
    LazyColumn {
        items(AboutMenuItem.entries.toList()) { item ->
            ListItem(
                modifier = Modifier.clickable { onMenuItemClick(item.id)  },
                headlineContent = { Text(text = stringResource(id = item.labelRes)) }
            )
        }
    }
}

enum class AboutMenuItem(val id: Int, @StringRes val labelRes: Int, @RawRes val fileRes: Int ) {
    LegalInformation(1, R.string.app_about_menu_legalInformation, R.raw.about_legal_information),
    PrivacyPolicy(2, R.string.app_about_menu_privacyPolicy, R.raw.about_privacy_policy);

    companion object {
        fun fromId(id: Int) = AboutMenuItem.entries.firstOrNull { it.id == id }
    }
}