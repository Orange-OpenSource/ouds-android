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

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.utilities.UiModePreviews

@Composable
fun AboutScreen(onMenuItemClick: (id: Int) -> Unit) {
    Screen {
        LazyColumn {
            items(AboutMenuItem.entries) { item ->
                ListItem(
                    modifier = Modifier.clickable { onMenuItemClick(item.id) },
                    headlineContent = { Text(text = stringResource(id = item.labelRes)) }
                )
            }
        }
    }
}

enum class AboutMenuItem(val id: Int, @StringRes val labelRes: Int, @RawRes val fileRes: Int) {
    LegalInformation(1, R.string.app_about_legalInformation_label, R.raw.about_legal_information),
    PrivacyPolicy(2, R.string.app_about_privacyPolicy_label, R.raw.about_privacy_policy);

    companion object {
        fun fromId(id: Int) = AboutMenuItem.entries.firstOrNull { it.id == id }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewAboutScreen() = OudsPreview {
    AboutScreen {}
}
