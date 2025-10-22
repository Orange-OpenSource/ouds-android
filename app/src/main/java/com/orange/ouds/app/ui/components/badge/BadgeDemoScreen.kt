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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsBadgeIcon
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.component.OudsBadgeWithIconStatus
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsVersion

@Composable
fun BadgeDemoScreen() {
    val state = rememberBadgeDemoState()
    val badgeWithIconStatus = state.badgeWithIconStatus
    DemoScreen(
        description = stringResource(id = Component.Badge.descriptionRes),
        bottomSheetContent = { BadgeDemoBottomSheetContent(state = state) },
        codeSnippet = { badgeDemoCodeSnippet(state = state, badgeWithIconStatus = badgeWithIconStatus) },
        demoContent = { BadgeDemoContent(state = state) },
        version = OudsVersion.Component.Badge
    )
}

@Composable
private fun BadgeDemoBottomSheetContent(state: BadgeDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_badge_type_label),
            chipLabels = BadgeDemoState.Type.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = BadgeDemoState.Type.entries.indexOf(type),
            onSelectionChange = { id -> type = BadgeDemoState.Type.entries[id] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_size_label),
            chips = OudsBadgeSize.entries.map { CustomizationFilterChip(it.name.toSentenceCase(), enabled = it in enabledSizes) },
            selectedChipIndex = OudsBadgeSize.entries.indexOf(size),
            onSelectionChange = { id -> size = OudsBadgeSize.entries[id] }
        )
        val statuses = OudsBadgeStatus.entries
        CustomizationDropdownMenu(
            applyTopPadding = true,
            label = stringResource(id = R.string.app_components_common_status_label),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status.name,
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(status.color())
                        )
                    }
                )
            },
            selectedItemIndex = statuses.indexOf(status),
            onSelectionChange = { status = statuses[it] }
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
        val content: @Composable (BadgeDemoState.Type, OudsBadgeSize, Boolean) -> Unit = { type, size, visible ->
            val alpha = if (visible) 1f else 0f
            val modifier = Modifier.alpha(alpha)
            when (type) {
                BadgeDemoState.Type.Standard -> {
                    val contentDescription = stringResource(id = R.string.app_components_badge_unreadNotifications_a11y)
                    OudsBadge(
                        modifier = modifier.semantics { this.contentDescription = contentDescription },
                        status = status,
                        size = size
                    )
                }
                BadgeDemoState.Type.Count -> {
                    val contentDescription = pluralStringResource(id = R.plurals.app_components_badge_unreadMessageCount_a11y, count = count, count)
                    OudsBadge(
                        modifier = modifier.clearAndSetSemantics { this.contentDescription = contentDescription },
                        count = count,
                        status = status,
                        size = size
                    )
                }
                BadgeDemoState.Type.Icon -> {
                    val contentDescription = stringResource(id = R.string.app_components_common_icon_a11y)
                    OudsBadge(
                        modifier = modifier.semantics { this.contentDescription = contentDescription },
                        status = badgeWithIconStatus,
                        size = size
                    )
                }
            }
        }

        // Reserve space to avoid changing the height of the demo box when switching between sizes and types
        // A large count badge has the biggest height because font size can be increased by the user
        // Draw this invisible composable first otherwise the actual badge cannot be directly selected when TalkBack is enabled
        content(BadgeDemoState.Type.Count, OudsBadgeSize.Large, false)
        content(type, size, true)
    }
}

private fun Code.Builder.badgeDemoCodeSnippet(state: BadgeDemoState, badgeWithIconStatus: OudsBadgeWithIconStatus) {
    with(state) {
        functionCall("OudsBadge") {
            if (type == BadgeDemoState.Type.Count) {
                typedArgument("count", count)
            }

            val statusParameterName = "status"
            if (type == BadgeDemoState.Type.Icon) {
                functionCallArgument(statusParameterName, badgeWithIconStatus::class.java.nestedName) {
                    when (badgeWithIconStatus) {
                        is OudsBadgeWithIconStatus.Neutral, is OudsBadgeWithIconStatus.Accent ->
                            constructorCallArgument<OudsBadgeIcon.Custom>("icon") {
                                painterArgument(R.drawable.ic_heart)
                                contentDescriptionArgument(R.string.app_components_common_icon_a11y)
                            }
                        is OudsBadgeWithIconStatus.Positive, is OudsBadgeWithIconStatus.Warning, is OudsBadgeWithIconStatus.Info, is OudsBadgeWithIconStatus.Negative -> {}
                    }
                }
            } else {
                typedArgument(statusParameterName, status)
            }

            typedArgument("size", size)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBadgeDemoScreen() = OudsPreview {
    BadgeDemoScreen()
}
