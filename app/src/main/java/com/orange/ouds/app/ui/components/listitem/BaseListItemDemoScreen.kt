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
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.rememberImagePainter
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsListItemDefaults
import com.orange.ouds.core.component.OudsListItemIcon
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImage
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsListItemVerticalAlignment
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.foundation.extensions.toSentenceCase
import kotlinx.coroutines.launch


@Composable
fun BaseListItemDemoBottomSheetTabs(state: BaseListItemDemoState) {
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
fun BaseListItemDemoBottomSheetContent(state: BaseListItemDemoState) {
    with(state) {
        HorizontalPager(state = pagerState, userScrollEnabled = false) { page ->
            Column {
                tabs[page].Content(state)
            }
        }
    }
}

@Composable
fun BaseListItemDemoState.CustomizationTab.Content(state: BaseListItemDemoState) {
    when (this) {
        BaseListItemDemoState.CustomizationTab.General -> when (state) {
            is CardItemDemoState -> CardItemGeneralCustomizationContent(state = state)
            is ListItemDemoState -> ListItemGeneralCustomizationContent(state = state)
        }
        BaseListItemDemoState.CustomizationTab.Leading -> BaseListItemLeadingCustomizationContent(state = state)
        BaseListItemDemoState.CustomizationTab.Texts -> BaseListItemTextsCustomizationContent(state = state)
        BaseListItemDemoState.CustomizationTab.Trailing -> BaseListItemTrailingCustomizationContent(state = state)
    }
}

data class BaseListItemGeneralCustomization(val index: Int, val content: @Composable () -> Unit)

fun baseListItemGeneralCustomization(index: Int, content: @Composable () -> Unit) = BaseListItemGeneralCustomization(index, content)

@Composable
fun BaseListItemGeneralCustomizations(state: BaseListItemDemoState, extraCustomizations: List<BaseListItemGeneralCustomization> = listOf()) {
    val customizations: MutableList<@Composable () -> Unit> = mutableListOf(
        { BaseListItemClickableCustomization(state = state) },
        { BaseListItemIndicatorCustomization(state = state) },
        { BaseListItemVerticalAlignmentCustomization(state = state) },
        { BaseListItemEnabledCustomization(state = state) },
    )
    extraCustomizations.forEach { (index, content) ->
        customizations.add(minOf(index, customizations.count()), content)
    }
    customizations.forEach { it() }
}

@Composable
private fun BaseListItemClickableCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_clickable_tech),
            checked = clickable,
            onCheckedChange = { clickable = it },
        )
    }
}

@Composable
private fun BaseListItemIndicatorCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_indicator_tech),
            chips = BaseListItemDemoState.Indicator.entries.map { CustomizationFilterChip(label = it.name.toSentenceCase(), enabled = indicatorEnabled) },
            selectedChipIndex = BaseListItemDemoState.Indicator.entries.indexOf(indicator),
            onSelectionChange = { index -> indicator = BaseListItemDemoState.Indicator.entries[index] }
        )
    }
}

@Composable
private fun BaseListItemVerticalAlignmentCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_verticalAlignment_tech),
            chipLabels = OudsListItemVerticalAlignment.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = OudsListItemVerticalAlignment.entries.indexOf(verticalAlignment),
            onSelectionChange = { index -> verticalAlignment = OudsListItemVerticalAlignment.entries[index] }
        )
    }
}

@Composable
private fun BaseListItemEnabledCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )
    }
}

@Composable
fun BaseListItemLeadingCustomizationContent(state: BaseListItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = null,
            chipLabels = BaseListItemDemoState.Leading.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = BaseListItemDemoState.Leading.entries.indexOf(leading),
            onSelectionChange = { index -> leading = BaseListItemDemoState.Leading.entries[index] }
        )
        if (size == BaseListItemDemoState.Size.Default) {
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
            chips = BaseListItemDemoState.StatusIcon.entries.map {
                CustomizationFilterChip(
                    label = it.name.toSentenceCase(),
                    enabled = leadingIconOptionsEnabled
                )
            },
            selectedChipIndex = BaseListItemDemoState.StatusIcon.entries.indexOf(leadingStatusIcon),
            onSelectionChange = { index -> leadingStatusIcon = BaseListItemDemoState.StatusIcon.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_iconTint_tech),
            chips = BaseListItemDemoState.IconTint.entries.map { CustomizationFilterChip(it.name.toSentenceCase(), enabled = leadingIconOptionsEnabled) },
            selectedChipIndex = BaseListItemDemoState.IconTint.entries.indexOf(leadingIconTint),
            onSelectionChange = { index -> leadingIconTint = BaseListItemDemoState.IconTint.entries[index] }
        )
        if (size == BaseListItemDemoState.Size.Default) {
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
            label = stringResource(R.string.app_components_listItem_roundedCornerImage_tech),
            checked = leadingImageRoundedCorners,
            onCheckedChange = { leadingImageRoundedCorners = it },
            enabled = leadingImageOptionsEnabled
        )
    }
}

@Composable
fun BaseListItemTextsCustomizationContent(state: BaseListItemDemoState) {
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
        if (size == BaseListItemDemoState.Size.Default) {
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
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_helperText_tech),
            value = helperText.orEmpty(),
            onValueChange = { value -> helperText = value }
        )
    }
}

@Composable
fun BaseListItemTrailingCustomizationContent(state: BaseListItemDemoState) {
    with(state) {
        CustomizationFilterChips(
            applyTopPadding = true,
            label = null,
            chipLabels = BaseListItemDemoState.Trailing.entries.map { it.name.toSentenceCase() },
            selectedChipIndex = BaseListItemDemoState.Trailing.entries.indexOf(trailing),
            onSelectionChange = { index -> trailing = BaseListItemDemoState.Trailing.entries[index] }
        )
        if (size == BaseListItemDemoState.Size.Default) {
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
            chips = BaseListItemDemoState.StatusIcon.entries.map {
                CustomizationFilterChip(
                    label = it.name.toSentenceCase(),
                    enabled = trailingIconOptionsEnabled
                )
            },
            selectedChipIndex = BaseListItemDemoState.StatusIcon.entries.indexOf(trailingStatusIcon),
            onSelectionChange = { index -> trailingStatusIcon = BaseListItemDemoState.StatusIcon.entries[index] }
        )
        CustomizationFilterChips(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_listItem_iconTint_tech),
            chips = BaseListItemDemoState.IconTint.entries.map { CustomizationFilterChip(it.name.toSentenceCase(), enabled = trailingIconOptionsEnabled) },
            selectedChipIndex = BaseListItemDemoState.IconTint.entries.indexOf(trailingIconTint),
            onSelectionChange = { index -> trailingIconTint = BaseListItemDemoState.IconTint.entries[index] }
        )
        if (size == BaseListItemDemoState.Size.Default) {
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
            label = stringResource(R.string.app_components_listItem_roundedCornerImage_tech),
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
                    trailingTextOptionsEnabled && (it == OudsListItemTextStyle.Label || trailingTextExtraLabel.isNullOrBlank())
                )
            },
            selectedChipIndex = OudsListItemTextStyle.entries.indexOf(trailingTextStyle),
            onSelectionChange = { index -> trailingTextStyle = OudsListItemTextStyle.entries[index] },
        )
        if (size == BaseListItemDemoState.Size.Default) {
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

@Composable
private fun iconPainter(iconTint: BaseListItemDemoState.IconTint) = when (iconTint) {
    BaseListItemDemoState.IconTint.Tinted -> painterResource(id = LocalThemeDrawableResources.current.tipsAndTricks)
    BaseListItemDemoState.IconTint.Untinted -> rememberUntintedIconPainter()
}

private val imagePainter
    @Composable
    get() = rememberImagePainter()

@Composable
fun baseListItemDemoLeading(state: BaseListItemDemoState): OudsListItemLeading? = with(state) {
    when (leading) {
        BaseListItemDemoState.Leading.None -> null
        BaseListItemDemoState.Leading.Icon -> {
            when (leadingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsListItemLeading.Icon(
                    painter = iconPainter(leadingIconTint),
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y),
                    size = leadingIconSize,
                    tinted = leadingIconTint == BaseListItemDemoState.IconTint.Tinted
                )
                BaseListItemDemoState.StatusIcon.Info -> OudsListItemLeading.Icon.Info(size = leadingIconSize)
                BaseListItemDemoState.StatusIcon.Negative -> OudsListItemLeading.Icon.Negative(size = leadingIconSize)
                BaseListItemDemoState.StatusIcon.Positive -> OudsListItemLeading.Icon.Positive(size = leadingIconSize)
                BaseListItemDemoState.StatusIcon.Warning -> OudsListItemLeading.Icon.Warning(size = leadingIconSize)
            }
        }
        BaseListItemDemoState.Leading.Image -> OudsListItemLeading.Image(
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
fun baseSmallListItemDemoLeading(state: BaseListItemDemoState): OudsSmallListItemLeading? = with(state) {
    when (leading) {
        BaseListItemDemoState.Leading.None -> null
        BaseListItemDemoState.Leading.Icon -> {
            when (leadingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsSmallListItemLeading.Icon(
                    painter = iconPainter,
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y)
                )
                BaseListItemDemoState.StatusIcon.Info -> OudsSmallListItemLeading.Icon.Info
                BaseListItemDemoState.StatusIcon.Negative -> OudsSmallListItemLeading.Icon.Negative
                BaseListItemDemoState.StatusIcon.Positive -> OudsSmallListItemLeading.Icon.Positive
                BaseListItemDemoState.StatusIcon.Warning -> OudsSmallListItemLeading.Icon.Warning
            }
        }
        BaseListItemDemoState.Leading.Image -> OudsSmallListItemLeading.Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.app_components_listItem_image_a11y),
            ratio = leadingImageRatio,
            roundedCorner = leadingImageRoundedCorners,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun baseListItemDemoTrailing(state: BaseListItemDemoState): OudsListItemTrailing? = with(state) {
    when (trailing) {
        BaseListItemDemoState.Trailing.None -> null
        BaseListItemDemoState.Trailing.Icon -> {
            when (trailingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsListItemTrailing.Icon(
                    painter = iconPainter(trailingIconTint),
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y),
                    size = trailingIconSize,
                    tinted = trailingIconTint == BaseListItemDemoState.IconTint.Tinted
                )
                BaseListItemDemoState.StatusIcon.Info -> OudsListItemTrailing.Icon.Info(size = trailingIconSize)
                BaseListItemDemoState.StatusIcon.Negative -> OudsListItemTrailing.Icon.Negative(size = trailingIconSize)
                BaseListItemDemoState.StatusIcon.Positive -> OudsListItemTrailing.Icon.Positive(size = trailingIconSize)
                BaseListItemDemoState.StatusIcon.Warning -> OudsListItemTrailing.Icon.Warning(size = trailingIconSize)
            }
        }
        BaseListItemDemoState.Trailing.Image -> OudsListItemTrailing.Image(
            painter = imagePainter,
            contentDescription = stringResource(R.string.app_components_listItem_image_a11y),
            size = trailingImageSize,
            ratio = trailingImageRatio,
            roundedCorner = trailingImageRoundedCorners,
            contentScale = ContentScale.Crop
        )
        BaseListItemDemoState.Trailing.Text -> {
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

@Composable
fun baseSmallListItemDemoTrailing(state: BaseListItemDemoState): OudsSmallListItemTrailing? = with(state) {
    when (trailing) {
        BaseListItemDemoState.Trailing.None -> null
        BaseListItemDemoState.Trailing.Icon -> {
            when (trailingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsSmallListItemTrailing.Icon(
                    painter = iconPainter,
                    contentDescription = stringResource(R.string.app_components_listItem_icon_a11y)
                )
                BaseListItemDemoState.StatusIcon.Info -> OudsSmallListItemTrailing.Icon.Info
                BaseListItemDemoState.StatusIcon.Negative -> OudsSmallListItemTrailing.Icon.Negative
                BaseListItemDemoState.StatusIcon.Positive -> OudsSmallListItemTrailing.Icon.Positive
                BaseListItemDemoState.StatusIcon.Warning -> OudsSmallListItemTrailing.Icon.Warning
            }
        }
        BaseListItemDemoState.Trailing.Image ->
            OudsSmallListItemTrailing.Image(
                painter = imagePainter,
                contentDescription = stringResource(R.string.app_components_listItem_image_a11y),
                ratio = trailingImageRatio,
                roundedCorner = trailingImageRoundedCorners,
                contentScale = ContentScale.Crop
            )
        BaseListItemDemoState.Trailing.Text ->
            OudsSmallListItemTrailing.Text(
                label = trailingTextLabel,
                style = trailingTextStyle
            )
    }
}

fun FunctionCall.Builder.baseListItemArguments(state: BaseListItemDemoState, themeDrawableResources: ThemeDrawableResources) {
    with(state) {
        if (clickable) {
            onClickArgument {
                comment("Do something")
            }
            if (indicator != BaseListItemDemoState.Indicator.Next) {
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
            BaseListItemDemoState.Leading.Icon -> addIconCodeSnippet<OudsListItemLeading.Icon>(
                argumentName = leadingParameterName,
                statusIcon = leadingStatusIcon,
                iconSize = leadingIconSize,
                iconTint = leadingIconTint,
                themeDrawableResources = themeDrawableResources
            )
            BaseListItemDemoState.Leading.Image -> addImageCodeSnippet<OudsListItemLeading.Image>(
                argumentName = leadingParameterName,
                imageSize = leadingImageSize,
                imageRatio = leadingImageRatio,
                roundedCorners = leadingImageRoundedCorners
            )
            BaseListItemDemoState.Leading.None -> {}
        }

        when (trailing) {
            BaseListItemDemoState.Trailing.Icon -> addIconCodeSnippet<OudsListItemTrailing.Icon>(
                argumentName = trailingParameterName,
                statusIcon = trailingStatusIcon,
                iconSize = trailingIconSize,
                iconTint = trailingIconTint,
                themeDrawableResources = themeDrawableResources
            )
            BaseListItemDemoState.Trailing.Image -> addImageCodeSnippet<OudsListItemTrailing.Image>(
                argumentName = trailingParameterName,
                imageSize = trailingImageSize,
                imageRatio = trailingImageRatio,
                roundedCorners = trailingImageRoundedCorners
            )
            BaseListItemDemoState.Trailing.Text -> {
                constructorCallArgument<OudsListItemTrailing.Text>(trailingParameterName) {
                    labelArgument(trailingTextLabel)
                    if (trailingTextStyle == OudsListItemTextStyle.Label && !trailingTextExtraLabel.isNullOrBlank()) {
                        typedArgument("extraLabel", trailingTextExtraLabel)
                    } else if (trailingTextStyle != OudsListItemTextStyle.Label) {
                        typedArgument("style", trailingTextStyle)
                    }
                }
            }
            BaseListItemDemoState.Trailing.None -> {}
        }

        if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
        if (boldLabel) typedArgument("boldLabel", boldLabel)
        if (!enabled) enabledArgument(enabled)
    }
}

private inline fun <reified IconType : OudsListItemIcon> FunctionCall.Builder.addIconCodeSnippet(
    argumentName: String,
    statusIcon: BaseListItemDemoState.StatusIcon,
    iconSize: OudsListItemIconSize,
    iconTint: BaseListItemDemoState.IconTint,
    themeDrawableResources: ThemeDrawableResources
) {
    val sizeParameterName = "size"
    when (statusIcon) {
        BaseListItemDemoState.StatusIcon.Info,
        BaseListItemDemoState.StatusIcon.Negative,
        BaseListItemDemoState.StatusIcon.Positive,
        BaseListItemDemoState.StatusIcon.Warning -> {
            val functionName = "${IconType::class.java.nestedName}.${statusIcon.name}"
            functionCallArgument(argumentName, functionName) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
            }
        }
        BaseListItemDemoState.StatusIcon.None -> {
            constructorCallArgument<IconType>(argumentName) {
                painterArgument(themeDrawableResources.tipsAndTricks)
                contentDescriptionArgument(R.string.app_components_listItem_icon_a11y)
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
                }
                if (iconTint != BaseListItemDemoState.IconTint.Tinted) {
                    typedArgument("tinted", false)
                }
            }
        }
    }
}

private inline fun <reified ImageType : OudsListItemImage> FunctionCall.Builder.addImageCodeSnippet(
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