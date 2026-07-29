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
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.rememberImagePainter
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
internal fun ItemDemoBottomSheetTabs(state: ItemDemoState) {
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
internal fun ItemDemoBottomSheetContent(state: ItemDemoState) {
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
        { ItemNavigationItemCustomization(state = state) },
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
private fun ItemNavigationItemCustomization(state: ItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_navigationItem_tech),
            checked = navigationItem,
            onCheckedChange = { navigationItem = it },
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
internal fun ItemLeadingCustomizationContent(state: ItemDemoState) {
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
internal fun ItemTextContainerCustomizationContent(state: ItemDemoState) {
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
internal fun ItemTrailingCustomizationContent(state: ItemDemoState) {
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
internal fun itemDemoLeading(state: ItemDemoState): OudsListItemLeading? = with(state) {
    when (leading) {
        ItemDemoState.Leading.None -> null
        ItemDemoState.Leading.Icon -> {
            when (leadingStatusIcon) {
                ItemDemoState.StatusIcon.None -> OudsListItemLeading.Icon(
                    painter = iconPainter,
                    contentDescription = "",
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
            contentDescription = "",
            size = leadingImageSize,
            ratio = leadingImageRatio,
            roundedCorner = leadingImageRoundedCorners,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
internal fun itemDemoTrailing(state: ItemDemoState): OudsListItemTrailing? = with(state) {
    when (trailing) {
        ItemDemoState.Trailing.None -> null
        ItemDemoState.Trailing.Icon -> {
            when (trailingStatusIcon) {
                ItemDemoState.StatusIcon.None -> OudsListItemTrailing.Icon(
                    painter = iconPainter,
                    contentDescription = "",
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
            contentDescription = "",
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
