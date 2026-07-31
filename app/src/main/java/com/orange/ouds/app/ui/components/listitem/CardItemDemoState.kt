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

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.component.OudsListItemDefaults
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemVerticalAlignment

@Composable
fun rememberCardItemDemoState(
    decoration: CardItemDemoState.Decoration = CardItemDemoState.Decoration.Background,
    size: BaseListItemDemoState.Size,
    selectedTabIndex: Int = 0,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    clickable: Boolean = false,
    indicator: BaseListItemDemoState.Indicator = BaseListItemDemoState.Indicator.Next,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: BaseListItemDemoState.Leading = BaseListItemDemoState.Leading.None,
    leadingIconSize: OudsListItemIconSize = OudsListItemDefaults.IconSize,
    leadingStatusIcon: BaseListItemDemoState.StatusIcon = BaseListItemDemoState.StatusIcon.None,
    leadingImageSize: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
    leadingImageRatio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
    leadingImageRoundedCorners: Boolean = false,
    trailing: BaseListItemDemoState.Trailing = BaseListItemDemoState.Trailing.None,
    trailingIconSize: OudsListItemIconSize = OudsListItemDefaults.IconSize,
    trailingStatusIcon: BaseListItemDemoState.StatusIcon = BaseListItemDemoState.StatusIcon.None,
    trailingImageSize: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
    trailingImageRatio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
    trailingImageRoundedCorners: Boolean = false,
    trailingTextLabel: String = stringResource(id = R.string.app_components_common_label_label),
    trailingTextExtraLabel: String? = null,
    trailingTextStyle: OudsListItemTextStyle = OudsListItemTextStyle.Label,
    divider: Boolean = true,
    helperText: String? = null,
    boldLabel: Boolean = false,
    enabled: Boolean = true
): CardItemDemoState {
    val state = rememberSaveable(
        decoration,
        size,
        selectedTabIndex,
        label,
        clickable,
        indicator,
        verticalAlignment,
        overline,
        extraLabel,
        description,
        leading,
        leadingIconSize,
        leadingStatusIcon,
        leadingImageSize,
        leadingImageRatio,
        leadingImageRoundedCorners,
        trailing,
        trailingIconSize,
        trailingStatusIcon,
        trailingImageSize,
        trailingImageRatio,
        trailingImageRoundedCorners,
        trailingTextLabel,
        trailingTextExtraLabel,
        trailingTextStyle,
        divider,
        helperText,
        boldLabel,
        enabled,
        saver = CardItemDemoState.Saver
    ) {
        CardItemDemoState(
            decoration,
            size,
            selectedTabIndex,
            label,
            clickable,
            indicator,
            verticalAlignment,
            overline,
            extraLabel,
            description,
            leading,
            leadingIconSize,
            leadingStatusIcon,
            leadingImageSize,
            leadingImageRatio,
            leadingImageRoundedCorners,
            trailing,
            trailingIconSize,
            trailingStatusIcon,
            trailingImageSize,
            trailingImageRatio,
            trailingImageRoundedCorners,
            trailingTextLabel,
            trailingTextExtraLabel,
            trailingTextStyle,
            divider,
            helperText,
            boldLabel,
            enabled,
        )
    }

    val pagerState = rememberPagerState(initialPage = selectedTabIndex) {
        BaseListItemDemoState.CustomizationTab.entries.size
    }
    state.pagerState = pagerState
    return state
}

class CardItemDemoState(
    decoration: Decoration,
    size: Size,
    selectedTabIndex: Int,
    label: String,
    clickable: Boolean,
    indicator: Indicator,
    verticalAlignment: OudsListItemVerticalAlignment,
    overline: String?,
    extraLabel: String?,
    description: String?,
    leading: Leading,
    leadingIconSize: OudsListItemIconSize,
    leadingStatusIcon: StatusIcon,
    leadingImageSize: OudsListItemImageSize,
    leadingImageRatio: OudsListItemImageRatio,
    leadingImageRoundedCorners: Boolean,
    trailing: Trailing,
    trailingIconSize: OudsListItemIconSize,
    trailingStatusIcon: StatusIcon,
    trailingImageSize: OudsListItemImageSize,
    trailingImageRatio: OudsListItemImageRatio,
    trailingImageRoundedCorners: Boolean,
    trailingTextLabel: String,
    trailingTextExtraLabel: String?,
    trailingTextStyle: OudsListItemTextStyle,
    divider: Boolean,
    helperText: String?,
    boldLabel: Boolean,
    enabled: Boolean,
) : BaseListItemDemoState(
    size,
    selectedTabIndex,
    label,
    clickable,
    indicator,
    verticalAlignment,
    overline,
    extraLabel,
    description,
    leading,
    leadingIconSize,
    leadingStatusIcon,
    leadingImageSize,
    leadingImageRatio,
    leadingImageRoundedCorners,
    trailing,
    trailingIconSize,
    trailingStatusIcon,
    trailingImageSize,
    trailingImageRatio,
    trailingImageRoundedCorners,
    trailingTextLabel,
    trailingTextExtraLabel,
    trailingTextStyle,
    divider,
    helperText,
    boldLabel,
    enabled
) {
    companion object {

        @Suppress("UNCHECKED_CAST")
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        decoration,
                        with(BaseListItemDemoState.Saver) { save(state) },
                    )
                }
            },
            restore = { list: List<Any?> ->
                val itemDemoState = list[1]?.let { BaseListItemDemoState.Saver.restore(it) }
                itemDemoState?.run {
                    CardItemDemoState(
                        list[0] as Decoration,
                        size,
                        selectedTabIndex,
                        label,
                        clickable,
                        indicator,
                        verticalAlignment,
                        overline,
                        extraLabel,
                        description,
                        leading,
                        leadingIconSize,
                        leadingStatusIcon,
                        leadingImageSize,
                        leadingImageRatio,
                        leadingImageRoundedCorners,
                        trailing,
                        trailingIconSize,
                        trailingStatusIcon,
                        trailingImageSize,
                        trailingImageRatio,
                        trailingImageRoundedCorners,
                        trailingTextLabel,
                        trailingTextExtraLabel,
                        trailingTextStyle,
                        divider,
                        helperText,
                        boldLabel,
                        enabled
                    )
                }
            }
        )
    }

    var decoration: Decoration by mutableStateOf(decoration)

    val dividerEnabled: Boolean
        get() = decoration in listOf(Decoration.Background, Decoration.BackgroundOnInteraction)

    enum class Decoration {
        Background, BackgroundOnInteraction, Outlined, OutlinedOnInteraction;

        fun toOudsListItemDecoration(divider: Boolean = true) =
            when (this) {
                Background -> OudsListItemDecoration.Background(divider = divider)
                Outlined -> OudsListItemDecoration.Outlined
                BackgroundOnInteraction -> OudsListItemDecoration.BackgroundOnInteraction(divider = divider)
                OutlinedOnInteraction -> OudsListItemDecoration.OutlinedOnInteraction
            }
    }
}
