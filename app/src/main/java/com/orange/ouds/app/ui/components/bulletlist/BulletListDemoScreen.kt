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

package com.orange.ouds.app.ui.components.bulletlist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.Component
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.composable.DemoScreen
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.toSentenceCase
import com.orange.ouds.core.component.OudsBulletList
import com.orange.ouds.core.component.OudsBulletListBuilder
import com.orange.ouds.core.component.OudsBulletListDefaults
import com.orange.ouds.core.component.OudsBulletListTextStyle
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsBulletListUnorderedIcon
import com.orange.ouds.foundation.extensions.tryOrNull
import com.orange.ouds.theme.OudsVersion
import kotlin.reflect.full.createInstance

@Composable
fun BulletListDemoScreen() {
    val state = rememberBulletListDemoState()
    val unorderedFreeIconId = DefaultUnorderedFreeIconId
    DemoScreen(
        description = stringResource(id = Component.BulletList.descriptionRes),
        bottomSheetContent = { BulletListDemoBottomSheetContent(state = state) },
        codeSnippet = { bulletListDemoCodeSnippet(state = state, unorderedFreeIconId = unorderedFreeIconId) },
        demoContent = { BulletListDemoContent(state = state) },
        version = OudsVersion.Component.BulletList
    )
}

@Composable
private fun BulletListDemoBottomSheetContent(state: BulletListDemoState) {
    with(state) {
        val types = getTypes()
        val unorderedIcons = getUnorderedIcons()
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_type_label),
            chipLabels = types.map { it::class.simpleName.orEmpty().toSentenceCase() },
            selectedChipIndex = types.indexOfFirst { it::class.qualifiedName == type::class.qualifiedName },
            onSelectionChange = { type = types[it] }
        )
        if (type is OudsBulletListType.Unordered) {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_bulletList_unorderedIcon_label),
                chipLabels = unorderedIcons.map { it::class.simpleName.orEmpty().toSentenceCase() },
                selectedChipIndex = unorderedIcons.indexOfFirst { it::class.qualifiedName == unorderedIcon::class.qualifiedName },
                onSelectionChange = { unorderedIcon = unorderedIcons[it] }
            )
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_bulletList_brandColor_label),
                checked = brandColorIcon,
                onCheckedChange = { brandColorIcon = it },
            )
        }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_textStyle_label),
            chipLabels = OudsBulletListTextStyle.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBulletListTextStyle.entries.indexOf(textStyle),
            onSelectionChange = { id -> textStyle = OudsBulletListTextStyle.entries[id] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bulletList_bold_label),
            checked = bold,
            onCheckedChange = { bold = it },
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_levelCount_label),
            chipLabels = BulletListDemoState.LevelCount.entries.map { stringResource(it.labelRes) },
            selectedChipIndex = BulletListDemoState.LevelCount.entries.indexOf(levelCount),
            onSelectionChange = { id -> levelCount = BulletListDemoState.LevelCount.entries[id] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_label),
            value = label,
            onValueChange = { value -> label = value }
        )
    }
}

@Composable
private fun BulletListDemoContent(state: BulletListDemoState) {
    with(state) {
        val builder: OudsBulletListBuilder.() -> Unit = remember(levelCount, label) {
            {
                when (levelCount) {
                    BulletListDemoState.LevelCount.One -> {
                        item(label = label)
                        item(label = label)
                        item(label = label)
                    }
                    BulletListDemoState.LevelCount.Two -> {
                        item(label = label) {
                            item(label = label)
                            item(label = label)
                        }
                    }
                    BulletListDemoState.LevelCount.Three -> {
                        item(label = label) {
                            item(label = label) {
                                item(label = label)
                            }
                        }
                    }
                }
            }
        }
        OudsBulletList(
            modifier = Modifier.fillMaxWidth(),
            type = if (type is OudsBulletListType.Unordered) {
                OudsBulletListType.Unordered(
                    icon = unorderedIcon,
                    brandColor = brandColorIcon
                )
            } else {
                type
            },
            textStyle = textStyle,
            bold = bold
        ) {
            builder()
        }
    }
}

private fun Code.Builder.bulletListDemoCodeSnippet(state: BulletListDemoState, unorderedFreeIconId: Int) {
    with(state) {
        functionCall("OudsBulletList") {
            trailingLambda = true
            if (type is OudsBulletListType.Unordered) {
                functionCallArgument("type", type::class.java.nestedName) {
                    functionCallArgument("icon", unorderedIcon::class.java.nestedName) {
                        if (unorderedIcon is OudsBulletListUnorderedIcon.Free) {
                            painterArgument(unorderedFreeIconId)
                        }
                    }
                    if (brandColorIcon) typedArgument("brandColor", brandColorIcon)
                }
            } else {
                functionCallArgument("type", type::class.java.nestedName)
            }
            if (textStyle != OudsBulletListDefaults.TextStyle) typedArgument("textStyle", textStyle)
            if (!bold) typedArgument("bold", bold)
            lambdaArgument("builder") {
                when (levelCount) {
                    BulletListDemoState.LevelCount.One -> {
                        for (i in 1..3) {
                            itemFunctionCall(label)
                        }
                    }
                    BulletListDemoState.LevelCount.Two -> {
                        itemFunctionCall(label) {
                            itemFunctionCall(label)
                            itemFunctionCall(label)
                        }
                    }
                    BulletListDemoState.LevelCount.Three -> {
                        itemFunctionCall(label) {
                            itemFunctionCall(label) {
                                itemFunctionCall(label)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Code.Builder.itemFunctionCall(label: String, content: (Code.Builder.() -> Unit)? = null) = functionCall("item") {
    trailingLambda = true
    typedArgument("label", label)
    content?.let {
        lambdaArgument("builder") {
            content()
        }
    }
}

@Composable
private fun getTypes() = if (LocalInspectionMode.current) {
    // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
    // See https://issuetracker.google.com/issues/240601093
    listOf(
        OudsBulletListType.Unordered(),
        OudsBulletListType.Ordered(),
        OudsBulletListType.Bare()
    )
} else {
    OudsBulletListType::class.sealedSubclasses.mapNotNull { kClass ->
        tryOrNull {
            kClass.createInstance()
        }
    }
}

@Composable
private fun getUnorderedIcons() = if (LocalInspectionMode.current) {
    // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
    // See https://issuetracker.google.com/issues/240601093
    listOf(
        OudsBulletListUnorderedIcon.Bullet(),
        OudsBulletListUnorderedIcon.Tick(),
        OudsBulletListUnorderedIcon.Free(painter = painterResource(LocalThemeDrawableResources.current.home)),
    )
} else {
    OudsBulletListUnorderedIcon::class.sealedSubclasses.mapNotNull { kClass ->
        tryOrNull {
            if (kClass == OudsBulletListUnorderedIcon.Free::class) {
                OudsBulletListUnorderedIcon.Free(painter = painterResource(LocalThemeDrawableResources.current.home))
            } else {
                kClass.createInstance()
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBulletListDemoScreen() = AppPreview {
    BulletListDemoScreen()
}
