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
import com.orange.ouds.app.ui.components.bulletlist.BulletListDemoState.Companion.MaxLevelCount
import com.orange.ouds.app.ui.components.bulletlist.BulletListDemoState.Companion.MinLevelCount
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.Code
import com.orange.ouds.app.ui.utilities.composable.AppPreview
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
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
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_unorderedIcon_label),
            chips = unorderedIcons.map { CustomizationFilterChip(it::class.simpleName.orEmpty().toSentenceCase(), enabled = unorderedIconChipsEnabled) },
            selectedChipIndex = unorderedIcons.indexOfFirst { it::class.java.name == unorderedIconClassName },
            onSelectionChange = { unorderedIconClassName = unorderedIcons[it]::class.java.name }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bulletList_unorderedIconBrandColor_label),
            checked = brandColorIcon,
            onCheckedChange = { brandColorIcon = it },
            enabled = unorderedIconBrandColorSwitchEnabled
        )
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
        val levelCountOptions = remember { (MinLevelCount..MaxLevelCount).toList() }

        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_levelCount_label),
            chipLabels = levelCountOptions.map { it.toString() },
            selectedChipIndex = levelCountOptions.indexOf(levelCount),
            onSelectionChange = { index -> levelCount = levelCountOptions[index] }
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
                    1 -> {
                        item(label = label)
                        item(label = label)
                        item(label = label)
                    }
                    2 -> {
                        item(label = label) {
                            item(label = label)
                            item(label = label)
                        }
                    }
                    else -> {
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
                    icon = unorderedIcon(unorderedIconClassName),
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
                    functionCallArgument("icon", unorderedIconClassName.nestedName) {
                        if (unorderedIconClassName == OudsBulletListUnorderedIcon.Free::class.java.name) {
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
                    1 -> {
                        repeat(3) {
                            itemFunctionCall(label)
                        }
                    }
                    2 -> {
                        itemFunctionCall(label) {
                            itemFunctionCall(label)
                            itemFunctionCall(label)
                        }
                    }
                    else -> {
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
    isMultiline = false
    typedArgument("label", label)
    content?.let {
        lambdaArgument("builder") {
            content()
        }
    }
}

@Composable
private fun unorderedIcon(unorderedIconClassName: String): OudsBulletListUnorderedIcon {
    return if (unorderedIconClassName == OudsBulletListUnorderedIcon.Free::class.java.name) {
        OudsBulletListUnorderedIcon.Free(painter = painterResource(DefaultUnorderedFreeIconId))
    } else {
        Class.forName(unorderedIconClassName).kotlin.createInstance() as OudsBulletListUnorderedIcon
    }
}

@Composable
private fun getTypes() = if (LocalInspectionMode.current) {
    // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
    // See https://issuetracker.google.com/issues/240601093
    listOf(
        OudsBulletListType.Unordered(),
        OudsBulletListType.Ordered,
        OudsBulletListType.Bare
    )
} else {
    OudsBulletListType::class.sealedSubclasses.mapNotNull { kClass ->
        tryOrNull {
            kClass.objectInstance ?: kClass.createInstance()
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
        OudsBulletListUnorderedIcon.Free(painter = painterResource(DefaultUnorderedFreeIconId)),
    )
} else {
    OudsBulletListUnorderedIcon::class.sealedSubclasses.mapNotNull { kClass ->
        tryOrNull {
            if (kClass == OudsBulletListUnorderedIcon.Free::class) {
                OudsBulletListUnorderedIcon.Free(painter = painterResource(DefaultUnorderedFreeIconId))
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
