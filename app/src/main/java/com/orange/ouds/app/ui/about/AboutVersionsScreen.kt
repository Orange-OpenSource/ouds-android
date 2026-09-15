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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.consumeTopBarsTopWindowInsets
import com.orange.ouds.app.ui.utilities.topBarsTopPadding
import com.orange.ouds.core.component.OudsListItem
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.ExperimentalOudsApi
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.OudsIconsPackVersion
import com.orange.ouds.theme.OudsVersion
import kotlin.reflect.full.declaredMemberProperties

@Composable
fun AboutVersionsScreen() {
    Screen {
        LazyColumn(
            modifier = Modifier.consumeTopBarsTopWindowInsets(),
            contentPadding = PaddingValues(
                top = topBarsTopPadding,
                bottom = OudsTheme.spaces.fixed.medium
            )
        ) {
            item {
                VersionsSectionTitle(titleRes = R.string.app_about_versions_tokens_label)
            }
            items(getVersions<OudsVersion.Tokens>()) { version ->
                Version(version = version)
            }
            item {
                VersionsSectionTitle(
                    modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                    titleRes = R.string.app_about_versions_components_label
                )
            }
            items(getVersions<OudsVersion.Component>()) { version ->
                Version(version = version)
            }
            item {
                VersionsSectionTitle(
                    modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                    titleRes = R.string.app_about_versions_icons_label
                )
            }
            item {
                @OptIn(InternalOudsApi::class)
                Version(version = Version(name = stringResource(R.string.app_about_versions_iconsPack_label), value = OudsIconsPackVersion))
            }
        }
    }
}

@Composable
private fun VersionsSectionTitle(@StringRes titleRes: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(horizontal = OudsTheme.grids.margin)
            .padding(top = OudsTheme.spaces.fixed.medium, bottom = OudsTheme.spaces.fixed.small),
        text = stringResource(id = titleRes),
        style = OudsTheme.typography.heading.medium
    )
}

@OptIn(ExperimentalOudsApi::class)
@Composable
private fun Version(version: Version) {
    OudsListItem(
        label = version.name,
        trailing = OudsListItemTrailing.Text(label = version.value),
        divider = false
    )
}

private data class Version(val name: String, val value: String)

private inline fun <reified T : Any> getVersions(): List<Version> {
    return T::class.declaredMemberProperties.map { property ->
        val name = property.name
            .split("(?=\\p{Upper})".toRegex())
            .filter { it.isNotEmpty() }
            .mapIndexed { index, word -> if (index == 0) word else word.lowercase() }
            .joinToString(" ")
        val value = property.getter.call() as String
        Version(name, value)
    }
}

@PreviewLightDark
@Composable
fun PreviewVersionsScreen() = AppPreview {
    AboutVersionsScreen()
}
