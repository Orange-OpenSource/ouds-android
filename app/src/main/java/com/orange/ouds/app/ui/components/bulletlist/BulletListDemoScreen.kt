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
import com.orange.ouds.app.ui.components.annotatedStringArgument
import com.orange.ouds.app.ui.components.bulletlist.BulletListDemoState.Companion.MaxLevelCount
import com.orange.ouds.app.ui.components.bulletlist.BulletListDemoState.Companion.MinLevelCount
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
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
import com.orange.ouds.core.component.OudsBulletList
import com.orange.ouds.core.component.OudsBulletListBuilder
import com.orange.ouds.core.component.OudsBulletListDefaults
import com.orange.ouds.core.component.OudsBulletListFontSize
import com.orange.ouds.core.component.OudsBulletListFontWeight
import com.orange.ouds.core.component.OudsBulletListTextStyle
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsBulletListUnorderedAsset
import com.orange.ouds.core.component.common.text.OudsAnnotatedBulletListLabel
import com.orange.ouds.core.component.common.text.OudsLinkAnnotation
import com.orange.ouds.core.component.common.text.buildOudsAnnotatedBulletListLabel
import com.orange.ouds.core.component.common.text.withLink
import com.orange.ouds.core.component.common.text.withStrong
import com.orange.ouds.foundation.extensions.toSentenceCase
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
            label = stringResource(R.string.app_components_common_type_tech),
            chipLabels = types.map { it::class.simpleName.orEmpty().toSentenceCase() },
            selectedChipIndex = types.indexOfFirst { it::class.qualifiedName == type::class.qualifiedName },
            onSelectionChange = { type = types[it] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_unorderedAsset_tech),
            chips = unorderedAssetClasses.map { CustomizationFilterChip(it.java.simpleName.toSentenceCase(), enabled = unorderedAssetChipsEnabled) },
            selectedChipIndex = unorderedAssetClasses.indexOfFirst { it.java.name == unorderedAssetClassName },
            onSelectionChange = { unorderedAssetClassName = unorderedAssetClasses[it].java.name }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_bulletList_unorderedAssetBrandColor_tech),
            checked = unorderedAssetBrandColor,
            onCheckedChange = { unorderedAssetBrandColor = it },
            enabled = unorderedAssetBrandColorSwitchEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_fontSize_tech),
            chipLabels = OudsBulletListFontSize.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBulletListFontSize.entries.indexOf(fontSize),
            onSelectionChange = { id -> fontSize = OudsBulletListFontSize.entries[id] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_fontWeight_tech),
            chipLabels = OudsBulletListFontWeight.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsBulletListFontWeight.entries.indexOf(fontWeight),
            onSelectionChange = { id -> fontWeight = OudsBulletListFontWeight.entries[id] }
        )
        val levelCountOptions = remember { (MinLevelCount..MaxLevelCount).toList() }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_bulletList_levelCount_tech),
            chipLabels = levelCountOptions.map { it.toString() },
            selectedChipIndex = levelCountOptions.indexOf(levelCount),
            onSelectionChange = { index -> levelCount = levelCountOptions[index] }
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelTextInputEnabled,
            helperText = stringResource(id = R.string.app_components_common_annotatedTextHelperText_tech)
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_common_annotatedText_tech),
            checked = annotatedText,
            onCheckedChange = { annotatedText = it },
        )
    }
}

@Composable
private fun BulletListDemoContent(state: BulletListDemoState) {
    with(state) {
        val builder: OudsBulletListBuilder.() -> Unit = remember(levelCount, label, annotatedText) {
            {
                when (levelCount) {
                    1 -> repeat(3) { index ->
                        bulletListDemoItem(index, this@with)
                    }
                    2 -> {
                        bulletListDemoItem(0, this@with) {
                            bulletListDemoItem(1, this@with)
                            bulletListDemoItem(2, this@with)
                        }
                    }
                    else -> {
                        bulletListDemoItem(0, this@with) {
                            bulletListDemoItem(1, this@with) {
                                bulletListDemoItem(2, this@with)
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
            textStyle = OudsBulletListTextStyle(fontSize, fontWeight),
            builder = builder
        )
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
                        iconArgument<OudsBulletListUnorderedAsset.Icon>(assetParameterName, themeDrawableResources.tipsAndTricks)
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
                            itemFunctionCall(state)
                        }
                    }
                    2 -> {
                        itemFunctionCall(state) {
                            itemFunctionCall(state)
                            itemFunctionCall(state)
                        }
                    }
                    else -> {
                        itemFunctionCall(state) {
                            itemFunctionCall(state) {
                                itemFunctionCall(state)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun Code.Builder.itemFunctionCall(state: BulletListDemoState, content: (Code.Builder.() -> Unit)? = null) = functionCall("item") {
    trailingLambda = true
    isMultiline = false
    with(state) {
        if (annotatedText) {
            annotatedStringArgument<OudsAnnotatedBulletListLabel>("label")
        } else {
            labelArgument(label)
        }
    }
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

private fun OudsBulletListBuilder.bulletListDemoItem(index: Int, state: BulletListDemoState, builder: (OudsBulletListBuilder.() -> Unit)? = null) {
    with(state) {
        if (annotatedText) {
            val annotatedLabel = buildOudsAnnotatedBulletListLabel {
                when (index) {
                    0 -> {
                        append("Your payment was ")
                        withStrong { append("declined") }
                        append(".")
                    }
                    1 -> {
                        append("Check your ")
                        withStrong { append("available balance") }
                        append(" before retrying.")
                    }
                    2 -> {
                        append("Update your ")
                        withLink(OudsLinkAnnotation.Url("https://unified-design-system.orange.com")) { append("declined") }
                        append(" if needed.")
                    }
                    else -> {}
                }
            }
            item(label = annotatedLabel, builder = builder)
        } else {
            item(label = label, builder = builder)
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewBulletListDemoScreen() = AppPreview {
    BulletListDemoScreen()
}
