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

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.coloredbackground.ColoredBackgroundDemoStateDefaults
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.Illustration
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.component.OudsBulletList
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsCheckbox
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.component.OudsFilterChip
import com.orange.ouds.core.component.OudsFloatingActionButton
import com.orange.ouds.core.component.OudsFloatingActionButtonIcon
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.component.OudsLinkChevron
import com.orange.ouds.core.component.OudsNavigationBar
import com.orange.ouds.core.component.OudsNavigationBarItem
import com.orange.ouds.core.component.OudsNavigationBarItemIcon
import com.orange.ouds.core.component.OudsPasswordInput
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.component.OudsSwitch
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.component.OudsTextInput
import com.orange.ouds.core.component.OudsTopAppBar
import com.orange.ouds.core.component.OudsTopAppBarAction
import com.orange.ouds.core.component.OudsTopAppBarNavigationIcon
import com.orange.ouds.core.theme.isOudsInDarkTheme
import com.orange.ouds.foundation.ExperimentalOudsApi

@Composable
fun AlertIllustration() = ComponentIllustration {
    OudsAlertMessage(
        modifier = Modifier.padding(horizontal = 12.dp),
        label = stringResource(id = R.string.app_components_common_label_label),
        onClose = {},
    )
}

@Composable
fun BadgeIllustration() = ComponentIllustration {
    OudsBadge(
        count = 1,
        status = OudsBadgeStatus.Negative,
        size = OudsBadgeSize.Large
    )
}

@Composable
fun BulletListIllustration() = ComponentIllustration {
    val label = stringResource(id = R.string.app_components_common_label_label)
    OudsBulletList(modifier = Modifier.padding(end = 16.dp), type = OudsBulletListType.Unordered()) {
        repeat(2) {
            item(label = label)
        }
    }
}

@Composable
fun ButtonIllustration() = ComponentIllustration {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        val label = stringResource(id = R.string.app_components_common_label_tech)
        OudsButton(
            label = label,
            onClick = {},
            appearance = if (isOudsInDarkTheme()) OudsButtonAppearance.Default else OudsButtonAppearance.Strong
        )
        OudsButton(
            label = label,
            onClick = {},
            appearance = if (isOudsInDarkTheme()) OudsButtonAppearance.Strong else OudsButtonAppearance.Default
        )
    }
}

@Composable
fun CheckboxIllustration() = ComponentIllustration {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsCheckbox(
            checked = true,
            onCheckedChange = {}
        )
        OudsCheckbox(
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Composable
fun ChipIllustration() = ComponentIllustration {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OudsFilterChip(
            selected = true,
            onClick = {},
            label = stringResource(id = R.string.app_components_common_label_tech)
        )
        OudsFilterChip(
            selected = false,
            onClick = {},
            label = stringResource(id = R.string.app_components_common_label_tech)
        )
    }
}

@Composable
fun ColoredBackgroundIllustration() = ComponentIllustration {
    OudsColoredBox(
        modifier = Modifier
            .width(200.dp)
            .height(80.dp),
        color = ColoredBackgroundDemoStateDefaults.Color
    ) {}
}

@Composable
fun DividerIllustration() = ComponentIllustration {
    OudsHorizontalDivider(modifier = Modifier.padding(horizontal = 40.dp))
}

@Composable
fun FloatingActionButtonIllustration() = ComponentIllustration {
    OudsFloatingActionButton(
        icon = OudsFloatingActionButtonIcon(
            painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks),
            contentDescription = ""
        ),
        onClick = {}
    )
}

@Composable
fun LinkIllustration() = ComponentIllustration {
    OudsLink(
        label = stringResource(id = R.string.app_components_common_label_tech),
        chevron = OudsLinkChevron.Next,
        onClick = {}
    )
}

@Composable
fun NavigationBarIllustration() = ComponentIllustration {
    val items = List(3) { index ->
        OudsNavigationBarItem(
            selected = index == 0,
            onClick = {},
            icon = OudsNavigationBarItemIcon(painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks)),
            label = stringResource(R.string.app_components_common_label_tech)
        )
    }
    OudsNavigationBar(
        items = items,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}

@OptIn(ExperimentalOudsApi::class)
@Composable
fun PasswordInputIllustration() = ComponentIllustration {
    OudsPasswordInput(
        modifier = Modifier.padding(horizontal = 12.dp),
        value = "",
        onValueChange = {},
        label = stringResource(id = R.string.app_components_passwordInput_password_label),
        helperText = stringResource(id = R.string.app_components_passwordInputHelperText_label)
    )
}

@Composable
fun RadioButtonIllustration() = ComponentIllustration {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OudsRadioButton(
            selected = true,
            onClick = {}
        )
        OudsRadioButton(
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun SwitchIllustration() = ComponentIllustration {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OudsSwitch(
            checked = true,
            onCheckedChange = {}
        )
        OudsSwitch(
            checked = false,
            onCheckedChange = {}
        )
    }
}

@Composable
fun TagIllustration() = ComponentIllustration {
    OudsTag(label = stringResource(id = R.string.app_components_common_label_tech), status = OudsTagStatus.Positive())
}

@Composable
fun TextInputIllustration() = ComponentIllustration {
    OudsTextInput(
        modifier = Modifier.padding(horizontal = 12.dp),
        textFieldState = rememberTextFieldState(),
        label = stringResource(id = R.string.app_components_common_label_tech),
        helperText = stringResource(id = R.string.app_components_textInputHelperText_label)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarIllustration() = ComponentIllustration {
    OudsTopAppBar(
        modifier = Modifier.padding(horizontal = 12.dp),
        title = stringResource(id = R.string.app_components_common_label_tech),
        navigationIcon = OudsTopAppBarNavigationIcon.Back {},
        actions = listOf(
            OudsTopAppBarAction.Icon(
                painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks),
                contentDescription = "",
                onClick = {}
            )
        )
    )
}

@Composable
private fun ComponentIllustration(content: @Composable () -> Unit) {
    // Do not take user font scale into account
    val configuration = Configuration(LocalConfiguration.current).apply { fontScale = 1f }
    val density = Density(LocalDensity.current.density, 1f)
    CompositionLocalProvider(
        LocalDensity provides density,
        LocalConfiguration provides configuration
    ) {
        Illustration(content = content)
    }
}
