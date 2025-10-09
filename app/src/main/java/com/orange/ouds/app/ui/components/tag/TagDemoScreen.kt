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

package com.orange.ouds.app.ui.components.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenu
import com.orange.ouds.app.ui.utilities.composable.CustomizationDropdownMenuItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextField
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagAppearance
import com.orange.ouds.core.component.OudsTagIcon
import com.orange.ouds.core.component.OudsTagLoader
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.utilities.OudsPreview
import com.orange.ouds.foundation.extensions.tryOrNull
import com.orange.ouds.theme.OudsVersion
import kotlin.reflect.full.createInstance

@Composable
fun TagDemoScreen() {
    val state = rememberTagDemoState()
    DemoScreen(
        bottomSheetContent = { TagDemoBottomSheetContent(state = state) },
        codeSnippet = { tagDemoCodeSnippet(state = state) },
        demoContent = { TagDemoContent(state = state) },
        version = OudsVersion.Component.Tag
    )
}

@Composable
private fun TagDemoBottomSheetContent(state: TagDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_appearance_label),
            chipLabels = OudsTagAppearance.entries.map { it.name },
            selectedChipIndex = OudsTagAppearance.entries.indexOf(appearance),
            onSelectionChange = { id -> appearance = OudsTagAppearance.entries[id] }
        )
        val statuses = if (LocalInspectionMode.current) {
            // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
            // See https://issuetracker.google.com/issues/240601093
            listOf(
                OudsTagStatus.Accent(),
                OudsTagStatus.Neutral(),
                OudsTagStatus.Positive(),
                OudsTagStatus.Info(),
                OudsTagStatus.Warning(),
                OudsTagStatus.Negative()
            )
        } else {
            OudsTagStatus::class.sealedSubclasses.mapNotNull { kClass ->
                tryOrNull {
                    kClass.createInstance()
                }
            }
        }
        CustomizationDropdownMenu(
            applyTopPadding = true,
            label = stringResource(id = R.string.app_components_common_status_label),
            items = statuses.map { status ->
                CustomizationDropdownMenuItem(
                    label = status::class.simpleName.orEmpty(),
                    leadingIcon = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    when (appearance) {
                                        OudsTagAppearance.Emphasized -> status.color()
                                        OudsTagAppearance.Muted -> status.mutedColor()
                                    }
                                )
                        )
                    },
                    enabled = enabled && !hasLoader
                )
            },
            selectedItemIndex = statuses.indexOfFirst { it::class.qualifiedName == status::class.qualifiedName },
            onSelectionChange = { status = statuses[it] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_layout_label),
            chipLabels = TagDemoState.Layout.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = TagDemoState.Layout.entries.indexOf(layout),
            onSelectionChange = { id -> layout = TagDemoState.Layout.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_loader_label),
            checked = hasLoader,
            onCheckedChange = { hasLoader = it },
            enabled = loaderSwitchEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_tag_roundedCorners_label),
            checked = roundedCorners,
            onCheckedChange = { roundedCorners = it },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_size_label),
            chipLabels = OudsTagSize.entries.map { it.name },
            selectedChipIndex = OudsTagSize.entries.indexOf(size),
            onSelectionChange = { id -> size = OudsTagSize.entries[id] }
        )
        CustomizationTextField(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun TagDemoContent(state: TagDemoState) {
    with(state) {
        val content: @Composable (OudsTagSize, Boolean) -> Unit = { size, visible ->
            val loader = if (hasLoader) OudsTagLoader(null) else null
            val customIcon = OudsTagIcon.Custom(painter = painterResource(R.drawable.ic_heart))
            val alpha = if (visible) 1f else 0f
            OudsTag(
                modifier = Modifier.alpha(alpha),
                label = label,
                appearance = appearance,
                status = when (status) {
                    is OudsTagStatus.Neutral -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Neutral()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Neutral(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Neutral(icon = customIcon)
                    }
                    is OudsTagStatus.Accent -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Accent()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Accent(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Accent(icon = customIcon)
                    }
                    is OudsTagStatus.Positive -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Positive()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Positive(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Positive(icon = OudsTagIcon.Default)
                    }
                    is OudsTagStatus.Warning -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Warning()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Warning(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Warning(icon = OudsTagIcon.Default)
                    }
                    is OudsTagStatus.Negative -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Negative()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Negative(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Negative(icon = OudsTagIcon.Default)

                    }
                    is OudsTagStatus.Info -> when (layout) {
                        TagDemoState.Layout.TextOnly -> OudsTagStatus.Info()
                        TagDemoState.Layout.TextAndBullet -> OudsTagStatus.Info(icon = OudsTagIcon.Bullet)
                        TagDemoState.Layout.TextAndIcon -> OudsTagStatus.Info(icon = OudsTagIcon.Default)
                    }
                },
                size = size,
                roundedCorners = roundedCorners,
                loader = loader,
            )
        }

        // Reserve space to avoid changing the height of the demo box when switching between sizes
        // Draw this invisible composable first otherwise the actual tag cannot be directly selected when TalkBack is enabled
        content(OudsTagSize.Default, false)
        content(size, true)
    }
}

private fun Code.Builder.tagDemoCodeSnippet(state: TagDemoState) {
    with(state) {
        functionCall("OudsTag") {
            labelArgument(label)
            typedArgument("appearance", appearance)
            typedArgument("roundedCorners", roundedCorners)
            typedArgument("size", size)
            functionCallArgument("status", status::class.java.nestedName) {
                when (layout) {
                    TagDemoState.Layout.TextOnly -> {}
                    TagDemoState.Layout.TextAndBullet -> typedArgument("icon", OudsTagIcon.Bullet)
                    TagDemoState.Layout.TextAndIcon -> {
                        when (status) {
                            is OudsTagStatus.Neutral, is OudsTagStatus.Accent ->
                                constructorCallArgument<OudsTagIcon.Custom>("icon") {
                                    painterArgument(R.drawable.ic_heart)
                                }
                            is OudsTagStatus.Positive, is OudsTagStatus.Warning, is OudsTagStatus.Info, is OudsTagStatus.Negative ->
                                typedArgument("icon", OudsTagIcon.Default)
                        }
                    }
                }
            }
            if (hasLoader) {
                constructorCallArgument<OudsTagLoader>("loader") {
                    typedArgument("progress", null)
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewTagDemoScreen() = OudsPreview {
    TagDemoScreen()
}
