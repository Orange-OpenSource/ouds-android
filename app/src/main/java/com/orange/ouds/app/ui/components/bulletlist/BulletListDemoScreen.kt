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
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
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
import com.orange.ouds.core.component.OudsBulletListFontSize
import com.orange.ouds.core.component.OudsBulletListFontWeight
import com.orange.ouds.core.component.OudsBulletListTextStyle
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsBulletListUnorderedAsset
import com.orange.ouds.foundation.extensions.tryOrNull
import com.orange.ouds.theme.OudsVersion
import kotlin.reflect.full.createInstance

@Composable
fun BulletListDemoScreen() {
    val state = rememberBulletListDemoState()
    val themeDrawableResources = LocalThemeDrawableResources.current
    DemoScreen(
        description = stringResource(id = Component.BulletList.descriptionRes),
        bottomSheetContent = { BulletListDemoBottomSheetContent(state = state) },
        codeSnippet = { bulletListDemoCodeSnippet(state = state, themeDrawableResources = themeDrawableResources) },
        demoContent = { BulletListDemoContent(state = state) },
        version = OudsVersion.Component.BulletList
    )
}

@Composable
private fun BulletListDemoBottomSheetContent(state: BulletListDemoState) {
    with(state) {
        val types = getTypes()
        val unorderedAssetClasses = getUnorderedAssetClasses()
        CustomizationFilterChips(
            applyTopPadding = false,
            label = stringResource(R.string.app_components_common_type_label),
            chipLabels = types.map { it::class.simpleName.orEmpty().toSentenceCase() },
            selectedChipIndex = types.indexOfFirst { it::class.qualifiedName == type::class.qualifiedName },
            onSelectionChange = { type = types[it] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_unorderedAsset_label),
            chips = unorderedAssetClasses.map { CustomizationFilterChip(it.java.simpleName.toSentenceCase(), enabled = unorderedAssetChipsEnabled) },
            selectedChipIndex = unorderedAssetClasses.indexOfFirst { it.java.name == unorderedAssetClassName },
            onSelectionChange = { unorderedAssetClassName = unorderedAssetClasses[it].java.name }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bulletList_unorderedAssetBrandColor_label),
            checked = unorderedAssetBrandColor,
            onCheckedChange = { unorderedAssetBrandColor = it },
            enabled = unorderedAssetBrandColorSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_fontSize_label),
            chipLabels = OudsBulletListFontSize.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBulletListFontSize.entries.indexOf(fontSize),
            onSelectionChange = { id -> fontSize = OudsBulletListFontSize.entries[id] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_fontWeight_label),
            chipLabels = OudsBulletListFontWeight.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBulletListFontWeight.entries.indexOf(fontWeight),
            onSelectionChange = { id -> fontWeight = OudsBulletListFontWeight.entries[id] }
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
            label = stringResource(R.string.app_components_common_label_tech),
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
                    asset = unorderedAsset(unorderedAssetClassName),
                    brandColor = unorderedAssetBrandColor
                )
            } else {
                type
            },
            textStyle = OudsBulletListTextStyle(fontSize, fontWeight)
        ) {
            builder()
        }
    }
}

private fun Code.Builder.bulletListDemoCodeSnippet(state: BulletListDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        functionCall("OudsBulletList") {
            trailingLambda = true
            val typeParameterName = "type"
            if (type is OudsBulletListType.Unordered) {
                functionCallArgument(typeParameterName, type::class.java.nestedName) {
                    val assetParameterName = "asset"
                    if (unorderedAssetClassName == OudsBulletListUnorderedAsset.Icon::class.java.name) {
                        functionCallArgument(assetParameterName, unorderedAssetClassName.nestedName) {
                            painterArgument(themeDrawableResources.tipsAndTricks)
                        }
                    } else {
                        rawArgument(assetParameterName, unorderedAssetClassName.nestedName)
                    }
                    if (unorderedAssetBrandColor) typedArgument("brandColor", unorderedAssetBrandColor)
                }
            } else {
                rawArgument(typeParameterName, type::class.java.nestedName)
            }
            if (fontSize != OudsBulletListDefaults.TextStyle.fontSize || fontWeight != OudsBulletListDefaults.TextStyle.fontWeight) {
                constructorCallArgument<OudsBulletListTextStyle>("textStyle") {
                    typedArgument(OudsBulletListTextStyle::fontSize.name, fontSize)
                    typedArgument(OudsBulletListTextStyle::fontWeight.name, fontWeight)
                }
            }
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
private fun unorderedAsset(unorderedAssetClassName: String): OudsBulletListUnorderedAsset {
    return if (unorderedAssetClassName == OudsBulletListUnorderedAsset.Icon::class.java.name) {
        OudsBulletListUnorderedAsset.Icon(painter = painterResource(LocalThemeDrawableResources.current.tipsAndTricks))
    } else {
        val kClass = Class.forName(unorderedAssetClassName).kotlin
        (kClass.objectInstance ?: kClass.createInstance()) as OudsBulletListUnorderedAsset
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
private fun getUnorderedAssetClasses() = if (LocalInspectionMode.current) {
    // Fixes a bug where calling sealedSubclasses returns an empty list in Compose previews
    // See https://issuetracker.google.com/issues/240601093
    listOf(
        OudsBulletListUnorderedAsset.Bullet::class,
        OudsBulletListUnorderedAsset.Tick::class,
        OudsBulletListUnorderedAsset.Icon::class,
    )
} else {
    OudsBulletListUnorderedAsset::class.sealedSubclasses
}

@PreviewLightDark
@Composable
private fun PreviewBulletListDemoScreen() = AppPreview {
    BulletListDemoScreen()
}
