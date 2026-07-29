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

package com.orange.ouds.app.ui.components.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.rememberImagePainter
import com.orange.ouds.core.component.OudsListItemDefaults
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsListItemVerticalAlignment
import com.orange.ouds.foundation.extensions.toSentenceCase
import kotlinx.coroutines.launch


@Composable
fun ItemDemoBottomSheetTabs(state: ItemDemoState) {
    with(state) {
        val scope = rememberCoroutineScope()

        //TODO Replace by OudsTabRow when available
        PrimaryScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            tabs = {
                tabs.mapIndexed { index, customizationTab ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        text = { Text(text = customizationTab.name.toSentenceCase()) },
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                                state.selectedTabIndex = index
                            }
                        }
                    )
                }
            },
            containerColor = Color.Transparent
        )
    }
}

@Composable
fun ItemDemoBottomSheetContent(state: ItemDemoState) {
    with(state) {
        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            Column {
                tabs[page].Content(state)
            }
        }
    }
}

data class ItemGlobalCustomization(val index: Int, val content: @Composable () -> Unit)

fun itemGlobalCustomization(index: Int, content: @Composable () -> Unit) = ItemGlobalCustomization(index, content)

@Composable
fun ItemGlobalCustomizations(state: ItemDemoState, extraCustomizations: List<ItemGlobalCustomization> = listOf()) {
    val customizations: MutableList<@Composable () -> Unit> = mutableListOf(
        { ItemClickableCustomization(state = state) },
        { ItemIndicatorCustomization(state = state) },
        { ItemContentAlignmentCustomization(state = state) },
        { ItemEnabledCustomization(state = state) },
        { ItemHelperTextCustomization(state = state) }
    )
    extraCustomizations.forEach { (index, content) ->
        customizations.add(minOf(index, customizations.count()), content)
    }
    customizations.forEach { it() }
}

@Composable
private fun ItemClickableCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_clickable_tech),
            checked = clickable,
            onCheckedChange = { clickable = it },
        )
    }
}

@Composable
private fun ItemIndicatorCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_indicator_tech),
            chips = ItemDemoState.Indicator.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = indicatorEnabled) },
            selectedChipIndex = ItemDemoState.Indicator.entries.indexOf(indicator),
            onSelectionChange = { index -> indicator = ItemDemoState.Indicator.entries[index] }
        )
    }
}

@Composable
private fun ItemContentAlignmentCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_contentAlignment_tech),
            chipLabels = OudsListItemVerticalAlignment.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsListItemVerticalAlignment.entries.indexOf(verticalAlignment),
            onSelectionChange = { index -> verticalAlignment = OudsListItemVerticalAlignment.entries[index] }
        )
    }
}

@Composable
private fun ItemEnabledCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )
    }
}

@Composable
private fun ItemHelperTextCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_tech),
            value = helperText.orEmpty(),
            onValueChange = { value -> helperText = value }
        )
    }
}

@Composable
fun ItemLeadingCustomizationContent(state: ItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = null,
            chipLabels = ItemDemoState.Leading.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = ItemDemoState.Leading.entries.indexOf(leading),
            onSelectionChange = { index -> leading = ItemDemoState.Leading.entries[index] }
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_iconSize_tech),
                chips = OudsListItemIconSize.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = leadingIconOptionsEnabled) },
                selectedChipIndex = OudsListItemIconSize.entries.indexOf(leadingIconSize),
                onSelectionChange = { index -> leadingIconSize = OudsListItemIconSize.entries[index] }
            )
        }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_statusIcon_tech),
            chips = ItemDemoState.StatusIcon.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = leadingIconOptionsEnabled) },
            selectedChipIndex = ItemDemoState.StatusIcon.entries.indexOf(leadingStatusIcon),
            onSelectionChange = { index -> leadingStatusIcon = ItemDemoState.StatusIcon.entries[index] }
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_imageSize_tech),
                chips = OudsListItemImageSize.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = leadingImageOptionsEnabled) },
                selectedChipIndex = OudsListItemImageSize.entries.indexOf(leadingImageSize),
                onSelectionChange = { index -> leadingImageSize = OudsListItemImageSize.entries[index] }
            )
        }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_imageRatio_tech),
            chips = OudsListItemImageRatio.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = leadingImageOptionsEnabled) },
            selectedChipIndex = OudsListItemImageRatio.entries.indexOf(leadingImageRatio),
            onSelectionChange = { index -> leadingImageRatio = OudsListItemImageRatio.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_imageRoundedCorner_tech),
            checked = leadingImageRoundedCorners,
            onCheckedChange = { leadingImageRoundedCorners = it },
            enabled = leadingImageOptionsEnabled
        )
    }
}

@Composable
fun ItemTextContainerCustomizationContent(state: ItemDemoState) {
    with(state) {
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_boldLabel_tech),
            checked = boldLabel,
            onCheckedChange = { boldLabel = it },
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_description_tech),
            value = description.orEmpty(),
            onValueChange = { value -> description = value }
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_overline_tech),
                value = overline.orEmpty(),
                onValueChange = { value -> overline = value }
            )
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_common_extraLabel_tech),
                value = extraLabel.orEmpty(),
                onValueChange = { value -> extraLabel = value }
            )
        }
    }
}

@Composable
fun ItemTrailingCustomizationContent(state: ItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = null,
            chipLabels = ItemDemoState.Trailing.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = ItemDemoState.Trailing.entries.indexOf(trailing),
            onSelectionChange = { index -> trailing = ItemDemoState.Trailing.entries[index] }
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_iconSize_tech),
                chips = OudsListItemIconSize.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = trailingIconOptionsEnabled) },
                selectedChipIndex = OudsListItemIconSize.entries.indexOf(trailingIconSize),
                onSelectionChange = { index -> trailingIconSize = OudsListItemIconSize.entries[index] }
            )
        }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_statusIcon_tech),
            chips = ItemDemoState.StatusIcon.entries.map {
                CustomizationFilterChip(
                    label = it.name.toSentenceCase(),
                    enabled = trailingIconOptionsEnabled
                )
            },
            selectedChipIndex = ItemDemoState.StatusIcon.entries.indexOf(trailingStatusIcon),
            onSelectionChange = { index -> trailingStatusIcon = ItemDemoState.StatusIcon.entries[index] }
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_imageSize_tech),
                chips = OudsListItemImageSize.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = trailingImageOptionsEnabled) },
                selectedChipIndex = OudsListItemImageSize.entries.indexOf(trailingImageSize),
                onSelectionChange = { index -> trailingImageSize = OudsListItemImageSize.entries[index] }
            )
        }
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_imageRatio_tech),
            chips = OudsListItemImageRatio.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = trailingImageOptionsEnabled) },
            selectedChipIndex = OudsListItemImageRatio.entries.indexOf(trailingImageRatio),
            onSelectionChange = { index -> trailingImageRatio = OudsListItemImageRatio.entries[index] }
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_imageRoundedCorner_tech),
            checked = trailingImageRoundedCorners,
            onCheckedChange = { trailingImageRoundedCorners = it },
            enabled = trailingImageOptionsEnabled
        )
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_trailingTextLabel_tech),
            value = trailingTextLabel,
            onValueChange = { value -> trailingTextLabel = value },
            enabled = trailingTextOptionsEnabled
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_trailingTextStyle_tech),
            chips = OudsListItemTextStyle.entries.map {
                CustomizationFilterChip(
                    it.name.toSentenceCase(),
                    trailingTextOptionsEnabled && ((!trailingTextExtraLabel.isNullOrBlank() && it == OudsListItemTextStyle.Label) || trailingTextExtraLabel.isNullOrBlank())
                )
            },
            selectedChipIndex = OudsListItemTextStyle.entries.indexOf(trailingTextStyle),
            onSelectionChange = { index -> trailingTextStyle = OudsListItemTextStyle.entries[index] },
        )
        if (size == ItemDemoState.Size.Default) {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_trailingTextExtraLabel_tech),
                value = trailingTextExtraLabel.orEmpty(),
                onValueChange = { value ->
                    trailingTextExtraLabel = value
                    if (value.isNotBlank()) {
                        trailingTextStyle = OudsListItemTextStyle.Label
                    }
                },
                enabled = trailingTextOptionsEnabled
            )
        }
    }
}

private val iconPainter
    @Composable
    get() = painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)

private val imagePainter
    @Composable
    get() = rememberImagePainter()

@Composable
fun itemDemoLeading(state: ItemDemoState): OudsListItemLeading? = with(state) {
    when (leading) {
        ItemDemoState.Leading.None -> null
        ItemDemoState.Leading.Icon -> {
            when (leadingStatusIcon) {
                ItemDemoState.StatusIcon.None -> OudsListItemLeading.Icon(
                    painter = iconPainter,
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y),
                    size = leadingIconSize
                )
                ItemDemoState.StatusIcon.Info -> OudsListItemLeading.Icon.Info(size = leadingIconSize)
                ItemDemoState.StatusIcon.Negative -> OudsListItemLeading.Icon.Negative(size = leadingIconSize)
                ItemDemoState.StatusIcon.Positive -> OudsListItemLeading.Icon.Positive(size = leadingIconSize)
                ItemDemoState.StatusIcon.Warning -> OudsListItemLeading.Icon.Warning(size = leadingIconSize)
            }
        }
        ItemDemoState.Leading.Image -> OudsListItemLeading.Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.app_components_listItem_image_a11y),
            size = leadingImageSize,
            ratio = leadingImageRatio,
            roundedCorner = leadingImageRoundedCorners,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun itemDemoTrailing(state: ItemDemoState): OudsListItemTrailing? = with(state) {
    when (trailing) {
        ItemDemoState.Trailing.None -> null
        ItemDemoState.Trailing.Icon -> {
            when (trailingStatusIcon) {
                ItemDemoState.StatusIcon.None -> OudsListItemTrailing.Icon(
                    painter = iconPainter,
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y),
                    size = trailingIconSize
                )
                ItemDemoState.StatusIcon.Info -> OudsListItemTrailing.Icon.Info(size = trailingIconSize)
                ItemDemoState.StatusIcon.Negative -> OudsListItemTrailing.Icon.Negative(size = trailingIconSize)
                ItemDemoState.StatusIcon.Positive -> OudsListItemTrailing.Icon.Positive(size = trailingIconSize)
                ItemDemoState.StatusIcon.Warning -> OudsListItemTrailing.Icon.Warning(size = trailingIconSize)
            }
        }
        ItemDemoState.Trailing.Image -> OudsListItemTrailing.Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.app_components_listItem_image_a11y),
            size = trailingImageSize,
            ratio = trailingImageRatio,
            roundedCorner = trailingImageRoundedCorners,
            contentScale = ContentScale.Crop
        )
        ItemDemoState.Trailing.Text -> {
            if (trailingTextStyle == OudsListItemTextStyle.Label && !trailingTextExtraLabel.isNullOrBlank()) {
                OudsListItemTrailing.Text(
                    label = trailingTextLabel,
                    extraLabel = trailingTextExtraLabel.orEmpty()
                )
            } else {
                OudsListItemTrailing.Text(
                    label = trailingTextLabel,
                    style = trailingTextStyle
                )
            }
        }
    }
}

fun FunctionCall.Builder.itemArguments(state: ItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        if (clickable) {
            onClickArgument {
                comment("Do something")
            }
            if (indicator != ItemDemoState.Indicator.Next) {
                typedArgument("indicator", indicator.toOudsListItemIndicator())
            }
        }

        labelArgument(label)

        if (verticalAlignment != OudsListItemDefaults.VerticalAlignment) {
            typedArgument("verticalAlignment", verticalAlignment)
        }

        if (!overline.isNullOrBlank()) typedArgument("overline", overline)
        if (!extraLabel.isNullOrBlank()) typedArgument("extraLabel", extraLabel)
        if (!description.isNullOrBlank()) typedArgument("description", description)

        val leadingParameterName = "leading"
        val trailingParameterName = "trailing"
        when (leading) {
            ItemDemoState.Leading.Icon -> addIconCodeSnippet<
                    OudsListItemLeading.Icon,
                    OudsListItemLeading.Icon.Info,
                    OudsListItemLeading.Icon.Negative,
                    OudsListItemLeading.Icon.Positive,
                    OudsListItemLeading.Icon.Warning
                    >(
                argumentName = leadingParameterName,
                statusIcon = leadingStatusIcon,
                iconSize = leadingIconSize,
                themeDrawableResources = themeDrawableResources
            )
            ItemDemoState.Leading.Image -> addImageCodeSnippet<OudsListItemLeading.Image>(
                argumentName = leadingParameterName,
                imageSize = leadingImageSize,
                imageRatio = leadingImageRatio,
                roundedCorners = leadingImageRoundedCorners
            )
            ItemDemoState.Leading.None -> {}
        }

        when (trailing) {
            ItemDemoState.Trailing.Icon -> addIconCodeSnippet<
                    OudsListItemTrailing.Icon,
                    OudsListItemTrailing.Icon.Info,
                    OudsListItemTrailing.Icon.Negative,
                    OudsListItemTrailing.Icon.Positive,
                    OudsListItemTrailing.Icon.Warning
                    >(
                argumentName = trailingParameterName,
                statusIcon = trailingStatusIcon,
                iconSize = trailingIconSize,
                themeDrawableResources = themeDrawableResources
            )
            ItemDemoState.Trailing.Image -> addImageCodeSnippet<OudsListItemTrailing.Image>(
                argumentName = trailingParameterName,
                imageSize = trailingImageSize,
                imageRatio = trailingImageRatio,
                roundedCorners = trailingImageRoundedCorners
            )
            ItemDemoState.Trailing.Text -> {
                constructorCallArgument<OudsListItemTrailing.Text>(trailingParameterName) {
                    labelArgument(trailingTextLabel)
                    if (trailingTextStyle == OudsListItemTextStyle.Label && !trailingTextExtraLabel.isNullOrBlank()) {
                        typedArgument("extraLabel", trailingTextExtraLabel)
                    } else if (trailingTextStyle != OudsListItemTextStyle.Label) {
                        typedArgument("style", trailingTextStyle)
                    }
                }
            }
            ItemDemoState.Trailing.None -> {}
        }

        if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
        if (boldLabel) typedArgument("boldLabel", boldLabel)
        if (!enabled) enabledArgument(enabled)
    }
}

inline fun <reified IconType, reified IconInfo, reified IconNegative, reified IconPositive, reified IconWarning> FunctionCall.Builder.addIconCodeSnippet(
    argumentName: String,
    statusIcon: ItemDemoState.StatusIcon,
    iconSize: OudsListItemIconSize,
    themeDrawableResources: ThemeDrawableResources
) {
    val sizeParameterName = "size"
    when (statusIcon) {
        ItemDemoState.StatusIcon.Info -> {
            constructorCallArgument<IconInfo>(argumentName) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
        ItemDemoState.StatusIcon.Negative -> {
            constructorCallArgument<IconNegative>(argumentName) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
        ItemDemoState.StatusIcon.Positive -> {
            constructorCallArgument<IconPositive>(argumentName) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
        ItemDemoState.StatusIcon.Warning -> {
            constructorCallArgument<IconWarning>(argumentName) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
        ItemDemoState.StatusIcon.None -> {
            constructorCallArgument<IconType>(argumentName) {
                painterArgument(themeDrawableResources.tipsAndTricks)
                contentDescriptionArgument(R.string.app_components_listItem_icon_a11y)
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
    }
}

inline fun <reified ImageType> FunctionCall.Builder.addImageCodeSnippet(
    argumentName: String,
    imageSize: OudsListItemImageSize,
    imageRatio: OudsListItemImageRatio,
    roundedCorners: Boolean
) {
    constructorCallArgument<ImageType>(argumentName) {
        painterArgument(R.drawable.ic_untinted_widescreen)
        contentDescriptionArgument(R.string.app_components_listItem_image_a11y)
        if (imageSize != OudsListItemDefaults.ImageSize) {
            typedArgument("size", imageSize)
        }
        if (imageRatio != OudsListItemDefaults.ImageRatio) {
            typedArgument("ratio", imageRatio)
        }
        if (roundedCorners) {
            typedArgument("roundedCorner", true)
        }
        rawArgument("contentScale", "ContentScale.Crop")
    }
}