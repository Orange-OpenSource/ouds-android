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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.coloredbackground.ColoredBackgroundDemoStateDefaults
import com.orange.ouds.app.ui.utilities.composable.Illustration
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsCheckbox
import com.orange.ouds.core.component.OudsColoredBox
import com.orange.ouds.core.component.OudsFilterChip
import com.orange.ouds.core.component.OudsHorizontalDivider
import com.orange.ouds.core.component.OudsLink
import com.orange.ouds.core.component.OudsRadioButton
import com.orange.ouds.core.component.OudsSwitch
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.theme.isOudsInDarkTheme

@Composable
fun BadgeIllustration() = ComponentIllustration {
    OudsBadge(
        count = 1,
        status = OudsBadge.Status.Negative,
        size = OudsBadge.Size.Large
    )
}

@Composable
fun ButtonIllustration() = ComponentIllustration {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        val label = stringResource(id = R.string.app_components_common_label_label)
        OudsButton(
            label = label,
            onClick = {},
            hierarchy = if (isOudsInDarkTheme()) OudsButton.Hierarchy.Default else OudsButton.Hierarchy.Strong
        )
        OudsButton(
            label = label,
            onClick = {},
            hierarchy = if (isOudsInDarkTheme()) OudsButton.Hierarchy.Strong else OudsButton.Hierarchy.Default
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
            label = stringResource(id = R.string.app_components_common_label_label)
        )
        OudsFilterChip(
            selected = false,
            onClick = {},
            label = stringResource(id = R.string.app_components_common_label_label)
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
fun LinkIllustration() = ComponentIllustration {
    OudsLink(
        label = stringResource(id = R.string.app_components_common_label_label),
        arrow = OudsLink.Arrow.Next,
        onClick = {}
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
    val label = stringResource(id = R.string.app_components_common_label_label)
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OudsTag(label = label)
        OudsTag(label = label, hierarchy = OudsTag.Hierarchy.Muted)
    }
}

@Composable
private fun ComponentIllustration(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        value = LocalDensity provides Density(
            LocalDensity.current.density,
            1f // Do not take user font scale into account
        )
    ) {
        Illustration(content = content)
    }
}
