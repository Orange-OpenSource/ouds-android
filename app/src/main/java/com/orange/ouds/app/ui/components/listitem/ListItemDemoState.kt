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
import com.orange.ouds.core.component.OudsListItemDefaults
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemVerticalAlignment

@Composable
fun rememberListItemDemoState(
    background: Boolean = false,
    size: ItemDemoState.Size,
    selectedTabIndex: Int = 0,
    label: String = stringResource(id = R.string.app_components_common_label_label),
    navigationItem: Boolean = false,
    indicator: ItemDemoState.Indicator = ItemDemoState.Indicator.Next,
    verticalAlignment: OudsListItemVerticalAlignment = OudsListItemDefaults.VerticalAlignment,
    overline: String? = null,
    extraLabel: String? = null,
    description: String? = null,
    leading: ItemDemoState.Leading = ItemDemoState.Leading.None,
    leadingIconSize: OudsListItemIconSize = OudsListItemDefaults.IconSize,
    leadingStatusIcon: ItemDemoState.StatusIcon = ItemDemoState.StatusIcon.None,
    leadingImageSize: OudsListItemImageSize = OudsListItemDefaults.ImageSize,
    leadingImageRatio: OudsListItemImageRatio = OudsListItemDefaults.ImageRatio,
    leadingImageRoundedCorners: Boolean = false,
    trailing: ItemDemoState.Trailing = ItemDemoState.Trailing.None,
    trailingIconSize: OudsListItemIconSize = OudsListItemDefaults.IconSize,
    trailingStatusIcon: ItemDemoState.StatusIcon = ItemDemoState.StatusIcon.None,
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
): ListItemDemoState {
    val state = rememberSaveable(
        background,
        size,
        selectedTabIndex,
        label,
        navigationItem,
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
        saver = ListItemDemoState.Saver
    ) {
        ListItemDemoState(
            background,
            size,
            selectedTabIndex,
            label,
            navigationItem,
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

    val pagerState = rememberPagerState(initialPage = selectedTabIndex) {
        ItemDemoState.CustomizationTab.entries.size
    }
    state.pagerState = pagerState
    return state
}

class ListItemDemoState(
    background: Boolean,
    size: Size,
    selectedTabIndex: Int,
    label: String,
    navigationItem: Boolean,
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
) : ItemDemoState(
    size,
    selectedTabIndex,
    label,
    navigationItem,
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
        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        background,
                        with(ItemDemoState.Saver) { save(state) }
                    )
                }
            },
            restore = { list: List<Any?> ->
                val itemDemoState = list[1]?.let { ItemDemoState.Saver.restore(it) }
                itemDemoState?.run {
                    ListItemDemoState(
                        list[0] as Boolean,
                        size,
                        selectedTabIndex,
                        label,
                        navigationItem,
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

    var background: Boolean by mutableStateOf(background)

}
