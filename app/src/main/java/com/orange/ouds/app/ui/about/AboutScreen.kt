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

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.BuildConfig
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.listItemHorizontalPadding
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

private val canOpenAppSettings = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

private val oudsAboutMenuItems = listOf(
    AboutFileMenuItem(1, R.string.app_about_legalInformation_label, AboutFileMenuItem.File(R.raw.about_legal_information, AboutFileMenuItem.File.Format.Html)),
    AboutFileMenuItem(2, R.string.app_about_privacyPolicy_label, AboutFileMenuItem.File(R.raw.about_privacy_policy, AboutFileMenuItem.File.Format.Html)),
    AboutFileMenuItem(3, R.string.app_about_changelog_label, AboutFileMenuItem.File(R.raw.changelog, AboutFileMenuItem.File.Format.Markdown)),
    AboutRouteMenuItem(4, R.string.app_about_materialComponents_label, AboutDestinations.MaterialComponentsRoute),
    AboutAppSettingsItem(5, if (canOpenAppSettings) R.string.app_about_openAppSettings_label else R.string.app_about_changeLanguage_label)
)

sealed class AboutMenuItem(val id: Int, @StringRes val labelRes: Int) {

    companion object {
        fun fromId(id: Int) = oudsAboutMenuItems.firstOrNull { it.id == id }
    }
}

class AboutFileMenuItem(id: Int, @StringRes labelRes: Int, val file: File) : AboutMenuItem(id, labelRes) {

    class File(@RawRes val resource: Int, val format: Format) {

        enum class Format {
            Html, Markdown
        }
    }
}

class AboutRouteMenuItem(id: Int, @StringRes labelRes: Int, val route: String) : AboutMenuItem(id, labelRes)

class AboutAppSettingsItem(id: Int, @StringRes labelRes: Int) : AboutMenuItem(id, labelRes)

@Composable
fun AboutScreen(onMenuItemClick: (id: Int) -> Unit) {
    val context = LocalContext.current
    Screen {
        LazyColumn {
            item {
                val version = stringResource(R.string.app_about_version_label, BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE.toLong())
                val pullRequestNumber: String? = BuildConfig.PULL_REQUEST_NUMBER
                ListItem(
                    modifier = Modifier.listItemHorizontalPadding(),
                    headlineContent = {
                        Column(verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.short)) {
                            Text(text = stringResource(id = R.string.app_about_name_label), style = OudsTheme.typography.heading.extraLarge)
                            Text(text = version, style = OudsTheme.typography.body.default.large)
                            if (pullRequestNumber != null) {
                                val pullRequest = buildAnnotatedString {
                                    append(stringResource(R.string.app_about_pullRequest_label))
                                    withLink(LinkAnnotation.Url("https://github.com/Orange-OpenSource/ouds-android/pull/$pullRequestNumber")) {
                                        withStyle(SpanStyle(OudsTheme.colorScheme.content.brandPrimary)) {
                                            append(stringResource(R.string.app_about_pullRequestNumber_label, pullRequestNumber))
                                        }
                                    }
                                }
                                Text(text = pullRequest, style = OudsTheme.typography.body.default.medium)
                            }
                            Text(
                                text = stringResource(R.string.app_about_tokensVersion_label, BuildConfig.TOKENS_VERSION),
                                style = OudsTheme.typography.body.default.medium
                            )
                        }
                    }
                )
            }
            items(oudsAboutMenuItems) { item ->
                ListItem(
                    modifier = Modifier
                        .clickable {
                            if (item is AboutAppSettingsItem) {
                               context.openAppSettings()
                            } else {
                               onMenuItemClick(item.id)
                            }
                        }
                        .listItemHorizontalPadding(),
                    headlineContent = { Text(text = stringResource(id = item.labelRes), style = OudsTheme.typography.body.strong.large) }
                )
            }
        }
    }
}

private fun Context.openAppSettings() {
    val intent = if (canOpenAppSettings) {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", this.packageName, null)
        )
    } else {
        Intent(Settings.ACTION_LOCALE_SETTINGS)
    }
    startActivity(intent)
}

@PreviewLightDark
@Composable
private fun PreviewAboutScreen() = OudsPreview {
    AboutScreen {}
}
