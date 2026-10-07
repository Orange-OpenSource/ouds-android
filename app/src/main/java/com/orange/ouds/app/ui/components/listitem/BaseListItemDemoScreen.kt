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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.contentDescriptionArgument
import com.orange.ouds.app.ui.components.enabledArgument
import com.orange.ouds.app.ui.components.iconArgument
import com.orange.ouds.app.ui.components.labelArgument
import com.orange.ouds.app.ui.components.onClickArgument
import com.orange.ouds.app.ui.components.painterArgument
import com.orange.ouds.app.ui.utilities.FunctionCall
import com.orange.ouds.app.ui.utilities.FunctionCall.Builder
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.app.ui.utilities.ThemeDrawableResources
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChip
import com.orange.ouds.app.ui.utilities.composable.CustomizationFilterChips
import com.orange.ouds.app.ui.utilities.composable.CustomizationSwitchItem
import com.orange.ouds.app.ui.utilities.composable.CustomizationTextInput
import com.orange.ouds.app.ui.utilities.nestedName
import com.orange.ouds.app.ui.utilities.rememberImagePainter
import com.orange.ouds.app.ui.utilities.rememberUntintedIconPainter
import com.orange.ouds.core.component.OudsAlertMessage
import com.orange.ouds.core.component.OudsAlertMessageStatus
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.component.OudsListItemContent
import com.orange.ouds.core.component.OudsListItemDefaults
import com.orange.ouds.core.component.OudsListItemIcon
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImage
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemScope
import com.orange.ouds.core.component.OudsListItemState
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsListItemVerticalAlignment
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.core.component.OudsTagAppearance
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.toSentenceCase
import kotlinx.coroutines.launch

private const val TrailingBadgeExampleCount = 7
private val trailingBadgeExampleStatus = OudsBadgeStatus.Negative
private val trailingTagExampleSize = OudsTagSize.Small
private val trailingTagExampleStatus = OudsTagStatus.Positive()

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
        { GeneralClickableCustomization(state = state) },
        { GeneralIndicatorCustomization(state = state) },
        { GeneralVerticalAlignmentCustomization(state = state) },
        { GeneralEnabledCustomization(state = state) },
        { GeneralBottomContentCustomization(state = state) }
    )
    extraCustomizations.sortedBy { it.index }.forEach { (index, content) ->
        customizations.add(minOf(index, customizations.count()), content)
    }
    customizations.forEach { it() }
}

@Composable
private fun GeneralClickableCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_clickable_tech),
            checked = clickable,
            onCheckedChange = { clickable = it },
        )
    }
}

@Composable
private fun GeneralIndicatorCustomization(state: BaseListItemDemoState) {
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
private fun GeneralVerticalAlignmentCustomization(state: BaseListItemDemoState) {
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
private fun GeneralEnabledCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_common_enabled_tech),
            checked = enabled,
            onCheckedChange = { enabled = it },
        )
    }
}

@Composable
private fun GeneralBottomContentCustomization(state: BaseListItemDemoState) {
    with(state) {
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_bottomContent_tech),
            checked = bottomContent,
            onCheckedChange = { bottomContent = it },
        )
    }
}

@Composable
fun BaseListItemLeadingCustomizationContent(state: BaseListItemDemoState) {
    with(state) {
        Column {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = null,
                chipLabels = BaseListItemDemoState.Leading.entries.filter { it.availableForSizes.contains(state.size) }.map { it.name.toSentenceCase() },
                selectedChipIndex = BaseListItemDemoState.Leading.entries.indexOf(leading),
                onSelectionChange = { index ->
                    leading = BaseListItemDemoState.Leading.entries[index]
                }
            )
            AnimatedVisibility(
                visible = leadingIconSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LeadingIconConfiguration(state = state)
            }

            AnimatedVisibility(
                visible = leadingImageSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LeadingImageConfiguration(state = state)
            }

            AnimatedVisibility(
                visible = leadingContentSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LeadingTrailingContentAlertMessage()
            }
        }
    }
}

@Composable
fun BaseListItemTextsCustomizationContent(state: BaseListItemDemoState) {
    with(state) {
        CustomizationTextInput(
            applyTopPadding = true,
            label = stringResource(R.string.app_components_common_label_tech),
            value = label,
            onValueChange = { value -> label = value },
            enabled = labelOptionsEnabled
        )
        CustomizationSwitchItem(
            label = stringResource(R.string.app_components_listItem_boldLabel_tech),
            checked = boldLabel,
            onCheckedChange = { boldLabel = it },
            enabled = labelOptionsEnabled
        )
        if (state.size == BaseListItemDemoState.Size.Default) {
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_listItem_labelContent_tech),
                checked = labelContent,
                onCheckedChange = { labelContent = it },
            )
        }
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
        if (state.size == BaseListItemDemoState.Size.Default) {
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_listItem_belowTextContent_tech),
                checked = belowTextContent,
                onCheckedChange = { belowTextContent = it }
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
        Column {
            CustomizationFilterChips(
                applyTopPadding = true,
                label = null,
                chipLabels = BaseListItemDemoState.Trailing.entries.filter { it.availableForSizes.contains(state.size) }.map { it.name.toSentenceCase() },
                selectedChipIndex = BaseListItemDemoState.Trailing.entries.indexOf(trailing),
                onSelectionChange = { index ->
                    trailing = BaseListItemDemoState.Trailing.entries[index]
                }
            )

            AnimatedVisibility(
                visible = trailingComponentExampleAlertVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TrailingBadgeOrTagConfiguration(
                    componentName = when (trailing) {
                        BaseListItemDemoState.Trailing.Badge -> "OudsBadge"
                        BaseListItemDemoState.Trailing.Tag -> "OudsTag"
                        else -> null
                    }
                )
            }

            AnimatedVisibility(
                visible = trailingIconSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TrailingIconConfiguration(state = state)
            }

            AnimatedVisibility(
                visible = trailingImageSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TrailingImageConfiguration(state = state)
            }

            AnimatedVisibility(
                visible = trailingTextSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TrailingTextConfiguration(state = state)
            }

            AnimatedVisibility(
                visible = trailingContentSettingsVisible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LeadingTrailingContentAlertMessage()
            }
        }
    }
}

@Composable
private fun LeadingTrailingContentAlertMessage() {
    OudsAlertMessage(
        modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.small),
        status = OudsAlertMessageStatus.Info,
        label = stringResource(
            R.string.app_components_listItem_specificComponentExampleAlertLabel_label,
            stringResource(R.string.app_components_listItem_content_tech)
        ),
        description = stringResource(R.string.app_components_listItem_contentExempleAlertDescription_text)
    )
}

@Composable
private fun LeadingIconConfiguration(state: BaseListItemDemoState) {
    with(state) {
        Column {
            if (size == BaseListItemDemoState.Size.Default) {
                CustomizationFilterChips(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_listItem_iconSize_tech),
                    chips = OudsListItemIconSize.entries.map {
                        CustomizationFilterChip(
                            label = it.name.toSentenceCase(),
                        )
                    },
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
                    )
                },
                selectedChipIndex = BaseListItemDemoState.StatusIcon.entries.indexOf(leadingStatusIcon),
                onSelectionChange = { index -> leadingStatusIcon = BaseListItemDemoState.StatusIcon.entries[index] }
            )
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_iconTint_tech),
                chips = BaseListItemDemoState.IconTint.entries.map {
                    CustomizationFilterChip(
                        it.name.toSentenceCase(),
                    )
                },
                selectedChipIndex = BaseListItemDemoState.IconTint.entries.indexOf(leadingIconTint),
                onSelectionChange = { index -> leadingIconTint = BaseListItemDemoState.IconTint.entries[index] }
            )
        }
    }
}

@Composable
private fun LeadingImageConfiguration(state: BaseListItemDemoState) {
    with(state) {
        Column {
            if (size == BaseListItemDemoState.Size.Default) {
                CustomizationFilterChips(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_listItem_imageSize_tech),
                    chips = OudsListItemImageSize.entries.map {
                        CustomizationFilterChip(
                            label = it.name.toSentenceCase(),
                        )
                    },
                    selectedChipIndex = OudsListItemImageSize.entries.indexOf(leadingImageSize),
                    onSelectionChange = { index -> leadingImageSize = OudsListItemImageSize.entries[index] }
                )
            }
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_imageRatio_tech),
                chips = OudsListItemImageRatio.entries.map {
                    CustomizationFilterChip(
                        label = it.name.toSentenceCase(),
                    )
                },
                selectedChipIndex = OudsListItemImageRatio.entries.indexOf(leadingImageRatio),
                onSelectionChange = { index -> leadingImageRatio = OudsListItemImageRatio.entries[index] }
            )
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_listItem_roundedCornerImage_tech),
                checked = leadingImageRoundedCorners,
                onCheckedChange = { leadingImageRoundedCorners = it },
            )
        }
    }
}

@Composable
private fun TrailingBadgeOrTagConfiguration(componentName: String?) {
    OudsAlertMessage(
        modifier = Modifier.padding(horizontal = OudsTheme.grids.margin, vertical = OudsTheme.spaces.fixed.small),
        status = OudsAlertMessageStatus.Info,
        label = componentName?.let { stringResource(R.string.app_components_listItem_specificComponentExampleAlertLabel_label, it) }
            .orElse { stringResource(R.string.app_components_listItem_componentExampleAlertLabel_label) },
        description = stringResource(R.string.app_components_listItem_componentExampleAlertDescription_text)
    )
}

@Composable
private fun TrailingIconConfiguration(state: BaseListItemDemoState) {
    with(state) {
        Column {
            if (size == BaseListItemDemoState.Size.Default) {
                CustomizationFilterChips(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_listItem_iconSize_tech),
                    chips = OudsListItemIconSize.entries.map {
                        CustomizationFilterChip(label = it.name.toSentenceCase())
                    },
                    selectedChipIndex = OudsListItemIconSize.entries.indexOf(trailingIconSize),
                    onSelectionChange = { index -> trailingIconSize = OudsListItemIconSize.entries[index] }
                )
            }
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_statusIcon_tech),
                chips = BaseListItemDemoState.StatusIcon.entries.map {
                    CustomizationFilterChip(label = it.name.toSentenceCase())
                },
                selectedChipIndex = BaseListItemDemoState.StatusIcon.entries.indexOf(trailingStatusIcon),
                onSelectionChange = { index -> trailingStatusIcon = BaseListItemDemoState.StatusIcon.entries[index] }
            )
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_iconTint_tech),
                chips = BaseListItemDemoState.IconTint.entries.map {
                    CustomizationFilterChip(it.name.toSentenceCase())
                },
                selectedChipIndex = BaseListItemDemoState.IconTint.entries.indexOf(trailingIconTint),
                onSelectionChange = { index -> trailingIconTint = BaseListItemDemoState.IconTint.entries[index] }
            )
        }
    }
}

@Composable
private fun TrailingImageConfiguration(state: BaseListItemDemoState) {
    with(state) {
        Column {
            if (size == BaseListItemDemoState.Size.Default) {
                CustomizationFilterChips(
                    applyTopPadding = true,
                    label = stringResource(R.string.app_components_listItem_imageSize_tech),
                    chips = OudsListItemImageSize.entries.map {
                        CustomizationFilterChip(label = it.name.toSentenceCase())
                    },
                    selectedChipIndex = OudsListItemImageSize.entries.indexOf(trailingImageSize),
                    onSelectionChange = { index -> trailingImageSize = OudsListItemImageSize.entries[index] }
                )
            }
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_imageRatio_tech),
                chips = OudsListItemImageRatio.entries.map {
                    CustomizationFilterChip(label = it.name.toSentenceCase())
                },
                selectedChipIndex = OudsListItemImageRatio.entries.indexOf(trailingImageRatio),
                onSelectionChange = { index -> trailingImageRatio = OudsListItemImageRatio.entries[index] }
            )
            CustomizationSwitchItem(
                label = stringResource(R.string.app_components_listItem_roundedCornerImage_tech),
                checked = trailingImageRoundedCorners,
                onCheckedChange = { trailingImageRoundedCorners = it },
            )
        }
    }
}

@Composable
private fun TrailingTextConfiguration(state: BaseListItemDemoState) {
    with(state) {
        Column {
            CustomizationTextInput(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_trailingTextLabel_tech),
                value = trailingTextLabel,
                onValueChange = { value -> trailingTextLabel = value },
            )
            CustomizationFilterChips(
                applyTopPadding = true,
                label = stringResource(R.string.app_components_listItem_trailingTextStyle_tech),
                chips = OudsListItemTextStyle.entries.map {
                    CustomizationFilterChip(
                        label = it.name.toSentenceCase(),
                        enabled = it == OudsListItemTextStyle.Label || trailingTextExtraLabel.isNullOrBlank()
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
                    }
                )
            }
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
        BaseListItemDemoState.Leading.Content -> OudsListItemLeading.Content { CustomContent(state = this.state, modifier = Modifier.fillMaxSize()) }
    }
}

@Composable
fun baseSmallListItemDemoLeading(state: BaseListItemDemoState): OudsSmallListItemLeading? = with(state) {
    when (leading) {
        BaseListItemDemoState.Leading.None, BaseListItemDemoState.Leading.Content -> null
        BaseListItemDemoState.Leading.Icon -> {
            when (leadingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsSmallListItemLeading.Icon(
                    painter = iconPainter(leadingIconTint),
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
        BaseListItemDemoState.Trailing.Badge -> OudsListItemTrailing.Badge(
            count = TrailingBadgeExampleCount,
            status = trailingBadgeExampleStatus,
            enabled = enabled
        )
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
        BaseListItemDemoState.Trailing.Tag -> OudsListItemTrailing.Tag(
            label = stringResource(R.string.app_components_common_label_tech),
            appearance = OudsTagAppearance.Muted,
            size = OudsTagSize.Small,
            status = OudsTagStatus.Positive(),
            enabled = enabled
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
        BaseListItemDemoState.Trailing.Content -> OudsListItemTrailing.Content { CustomContent(state = this.state, modifier = Modifier.fillMaxSize()) }
    }
}

@Composable
fun baseSmallListItemDemoTrailing(state: BaseListItemDemoState): OudsSmallListItemTrailing? = with(state) {
    when (trailing) {
        BaseListItemDemoState.Trailing.None, BaseListItemDemoState.Trailing.Content -> null
        BaseListItemDemoState.Trailing.Badge ->
            OudsSmallListItemTrailing.Badge(
                count = TrailingBadgeExampleCount,
                status = trailingBadgeExampleStatus,
                enabled = enabled
            )
        BaseListItemDemoState.Trailing.Icon -> {
            when (trailingStatusIcon) {
                BaseListItemDemoState.StatusIcon.None -> OudsSmallListItemTrailing.Icon(
                    painter = iconPainter(trailingIconTint),
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
        BaseListItemDemoState.Trailing.Tag ->
            OudsSmallListItemTrailing.Tag(
                label = stringResource(R.string.app_components_common_label_label),
                size = trailingTagExampleSize,
                status = trailingTagExampleStatus,
                enabled = enabled
            )
        BaseListItemDemoState.Trailing.Text ->
            OudsSmallListItemTrailing.Text(
                label = trailingTextLabel,
                style = trailingTextStyle
            )
    }
}

fun getContentOrNull(contentActivated: Boolean): @Composable (OudsListItemScope.() -> Unit)? = if (contentActivated) {
    { CustomContent(state = this.state, modifier = Modifier.fillMaxWidth()) }
} else {
    null
}

val baseLabelContent: @Composable OudsListItemScope.() -> Unit = { CustomContent(state = this.state, modifier = Modifier.fillMaxWidth()) }

@Composable
fun CustomContent(state: OudsListItemState, modifier: Modifier = Modifier) {
    val enabled = state == OudsListItemState.Enabled
    Box(
        modifier = modifier
            .background(if (enabled) OudsTheme.colorScheme.surface.status.info.muted else OudsTheme.colorScheme.action.disabled)
            .padding(all = OudsTheme.spaces.fixed.twoExtraSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_components_listItem_content_tech),
            style = OudsTheme.typography.label.small.strong,
            color = if (enabled) OudsTheme.colorScheme.content.onStatus.info.muted else OudsTheme.colorScheme.content.onAction.disabled,
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

        if (labelContent) {
            lambdaArgument("labelContent") {
                comment("Custom label content")
            }
        } else {
            labelArgument(label)
        }

        if (verticalAlignment != OudsListItemDefaults.VerticalAlignment) {
            typedArgument("verticalAlignment", verticalAlignment)
        }

        if (!overline.isNullOrBlank()) typedArgument("overline", overline)
        if (!extraLabel.isNullOrBlank()) typedArgument("extraLabel", extraLabel)
        if (!description.isNullOrBlank()) typedArgument("description", description)

        val leadingParameterName = "leading"
        val trailingParameterName = "trailing"
        when (leading) {
            BaseListItemDemoState.Leading.Icon -> when (size) {
                BaseListItemDemoState.Size.Default -> addIconCodeSnippet<OudsListItemLeading.Icon>(
                    argumentName = leadingParameterName,
                    statusIcon = leadingStatusIcon,
                    iconSize = leadingIconSize,
                    iconTint = leadingIconTint,
                    themeDrawableResources = themeDrawableResources
                )
                BaseListItemDemoState.Size.Small -> addIconCodeSnippet<OudsSmallListItemLeading.Icon>(
                    argumentName = leadingParameterName,
                    statusIcon = leadingStatusIcon,
                    iconSize = leadingIconSize,
                    iconTint = leadingIconTint,
                    themeDrawableResources = themeDrawableResources
                )
            }
            BaseListItemDemoState.Leading.Image -> when (size) {
                BaseListItemDemoState.Size.Default -> addImageCodeSnippet<OudsListItemLeading.Image>(
                    argumentName = leadingParameterName,
                    imageSize = leadingImageSize,
                    imageRatio = leadingImageRatio,
                    roundedCorners = leadingImageRoundedCorners
                )
                BaseListItemDemoState.Size.Small -> addImageCodeSnippet<OudsSmallListItemLeading.Image>(
                    argumentName = leadingParameterName,
                    imageSize = leadingImageSize,
                    imageRatio = leadingImageRatio,
                    roundedCorners = leadingImageRoundedCorners
                )
            }
            BaseListItemDemoState.Leading.None -> {}
            BaseListItemDemoState.Leading.Content -> addContentCodeSnippet<OudsListItemLeading.Content>(argumentName = leadingParameterName)
        }

        when (trailing) {
            BaseListItemDemoState.Trailing.Badge -> {
                val init: Builder.() -> Unit = {
                    typedArgument("count", TrailingBadgeExampleCount)
                    typedArgument("status", trailingBadgeExampleStatus)
                    if (!enabled) enabledArgument(enabled)
                }
                when (size) {
                    BaseListItemDemoState.Size.Default -> constructorCallArgument<OudsListItemTrailing.Badge>(trailingParameterName, init)
                    BaseListItemDemoState.Size.Small -> constructorCallArgument<OudsSmallListItemTrailing.Badge>(trailingParameterName, init)
                }
            }
            BaseListItemDemoState.Trailing.Icon -> when (size) {
                BaseListItemDemoState.Size.Default -> addIconCodeSnippet<OudsListItemTrailing.Icon>(
                    argumentName = trailingParameterName,
                    statusIcon = trailingStatusIcon,
                    iconSize = trailingIconSize,
                    iconTint = trailingIconTint,
                    themeDrawableResources = themeDrawableResources
                )
                BaseListItemDemoState.Size.Small -> addIconCodeSnippet<OudsSmallListItemTrailing.Icon>(
                    argumentName = trailingParameterName,
                    statusIcon = trailingStatusIcon,
                    iconSize = trailingIconSize,
                    iconTint = trailingIconTint,
                    themeDrawableResources = themeDrawableResources
                )
            }
            BaseListItemDemoState.Trailing.Image -> when (size) {
                BaseListItemDemoState.Size.Default -> addImageCodeSnippet<OudsListItemTrailing.Image>(
                    argumentName = trailingParameterName,
                    imageSize = trailingImageSize,
                    imageRatio = trailingImageRatio,
                    roundedCorners = trailingImageRoundedCorners
                )
                BaseListItemDemoState.Size.Small -> addImageCodeSnippet<OudsSmallListItemTrailing.Image>(
                    argumentName = trailingParameterName,
                    imageSize = trailingImageSize,
                    imageRatio = trailingImageRatio,
                    roundedCorners = trailingImageRoundedCorners
                )
            }
            BaseListItemDemoState.Trailing.Tag -> {
                val init: Builder.() -> Unit = {
                    labelArgument(R.string.app_components_common_label_label)
                    typedArgument("size", trailingTagExampleSize)
                    typedArgument("status", trailingTagExampleStatus)
                    if (!enabled) enabledArgument(enabled)
                }
                when (size) {
                    BaseListItemDemoState.Size.Default -> constructorCallArgument<OudsListItemTrailing.Tag>(trailingParameterName, init)
                    BaseListItemDemoState.Size.Small -> constructorCallArgument<OudsSmallListItemTrailing.Tag>(trailingParameterName, init)
                }
            }
            BaseListItemDemoState.Trailing.Text -> {
                val init: Builder.() -> Unit = {
                    labelArgument(trailingTextLabel)
                    if (trailingTextStyle == OudsListItemTextStyle.Label && !trailingTextExtraLabel.isNullOrBlank()) {
                        typedArgument("extraLabel", trailingTextExtraLabel)
                    } else if (trailingTextStyle != OudsListItemTextStyle.Label) {
                        typedArgument("style", trailingTextStyle)
                    }
                }
                when (size) {
                    BaseListItemDemoState.Size.Default -> constructorCallArgument<OudsListItemTrailing.Text>(trailingParameterName, init)
                    BaseListItemDemoState.Size.Small -> constructorCallArgument<OudsSmallListItemTrailing.Text>(trailingParameterName, init)
                }
            }
            BaseListItemDemoState.Trailing.None -> {}
            BaseListItemDemoState.Trailing.Content -> addContentCodeSnippet<OudsListItemTrailing.Content>(argumentName = trailingParameterName)
        }

        if (!helperText.isNullOrBlank()) typedArgument("helperText", helperText)
        if (boldLabel) typedArgument("boldLabel", boldLabel)
        if (!enabled) enabledArgument(enabled)
    }
}

private inline fun <reified ContentType : OudsListItemContent> FunctionCall.Builder.addContentCodeSnippet(
    argumentName: String
) {
    constructorCallArgument<ContentType>(argumentName) {
        trailingLambda = true
        lambdaArgument("content") {
            comment("Custom content")
        }
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
            iconArgument<IconType>(
                argumentName,
                themeDrawableResources.tipsAndTricks,
                R.string.app_components_listItem_icon_a11y,
                iconTint == BaseListItemDemoState.IconTint.Tinted
            ) {
                if (iconSize != OudsListItemDefaults.IconSize) {
                    typedArgument(sizeParameterName, iconSize)
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