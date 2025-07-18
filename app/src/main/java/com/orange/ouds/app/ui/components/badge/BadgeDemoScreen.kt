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

package com.orange.ouds.app.ui.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.formattedName
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsVersion

@Composable
fun BadgeDemoScreen() {
    val state = rememberBadgeDemoState()
    DemoScreen(
        description = stringResource(id = Component.Badge.descriptionRes),
        bottomSheetContent = { BadgeDemoBottomSheetContent(state = state) },
        codeSnippet = { badgeDemoCodeSnippet(state = state) },
        demoContent = { BadgeDemoContent(state = state) },
        version = OudsVersion.Component.Badge
    )
}

@Composable
private fun BadgeDemoBottomSheetContent(state: BadgeDemoState) {
    with(state) {
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_badge_type_label),
            chipLabels = BadgeDemoState.Type.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = BadgeDemoState.Type.entries.indexOf(type),
            onSelectionChange = { id -> type = BadgeDemoState.Type.entries[id] }
        )
        CustomizationFilterChips(
            modifier = Modifier.padding(top = OudsTheme.spaces.fixed.medium),
            label = stringResource(R.string.app_components_badge_size_label),
            chipLabels = OudsBadge.Size.entries.map { it.formattedName },
            selectedChipIndex = OudsBadge.Size.entries.indexOf(size),
            onSelectionChange = { id -> size = OudsBadge.Size.entries[id] }
        )
        val statuses = OudsBadge.Status.entries
        CustomizationDropdownMenu(
            label = stringResource(id = R.string.app_components_badge_status_label),
            itemLabels = statuses.map { it.formattedName },
            selectedItemIndex = statuses.indexOf(status),
            onSelectionChange = { status = statuses[it] },
            itemLeadingIcons = statuses.map { status ->
                {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(status.backgroundColor)
                    )
                }
            }
        )
        CustomizationTextField(
            label = stringResource(R.string.app_components_badge_count_label),
            value = TextFieldValue(count.toString(), TextRange(count.toString().length)),
            onValueChange = { value ->
                val filteredCount = value.text
                    .filter { it.isDigit() }
                    .replace("^0*".toRegex(), "")
                    .ifEmpty { "0" }
                count = filteredCount.toIntOrNull().orElse { if (filteredCount.isEmpty()) 0 else Int.MAX_VALUE }
            },
            enabled = countTextFieldEnabled,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
private fun BadgeDemoContent(state: BadgeDemoState) {
    with(state) {
        val content: @Composable (BadgeDemoState.Type, OudsBadge.Size, Boolean) -> Unit = { type, size, visible ->
            val alpha = if (visible) 1f else 0f
            val modifier = Modifier.alpha(alpha)
            when (type) {
                BadgeDemoState.Type.Standard -> {
                    OudsBadge(
                        modifier = modifier,
                        status = status,
                        size = size
                    )
                }
                BadgeDemoState.Type.Count -> {
                    OudsBadge(
                        modifier = modifier,
                        count = count,
                        status = status,
                        size = size
                    )
                }
                BadgeDemoState.Type.Icon -> {
                    OudsBadge(
                        modifier = modifier,
                        icon = OudsBadge.Icon(
                            painter = painterResource(R.drawable.ic_heart),
                            contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
                        ),
                        status = status,
                        size = size
                    )
                }
            }
        }

        content(type, size, true)
        // Reserve space to avoid changing the height of the demo box when switching between sizes and types
        // A large count badge has the biggest height because font size can be increased by the user
        content(BadgeDemoState.Type.Count, OudsBadge.Size.Large, false)
    }
}

private fun Code.Builder.badgeDemoCodeSnippet(state: BadgeDemoState) {
    with(state) {
        functionCall(OudsBadge::class.simpleName.orEmpty()) {
            when (type) {
                BadgeDemoState.Type.Standard -> {}
                BadgeDemoState.Type.Count -> typedArgument("count", count)
                BadgeDemoState.Type.Icon -> constructorCallArgument<OudsBadge.Icon>("icon") {
                    painterArgument(R.drawable.ic_heart)
                    contentDescriptionArgument(R.string.app_components_common_icon_a11y)
                }
            }
            typedArgument("status", status)
            typedArgument("size", size)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBadgeDemoScreen() = OudsPreview {
    BadgeDemoScreen()
}
