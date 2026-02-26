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

package com.orange.ouds.app.ui.components.topappbar

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R

@Composable
fun rememberTopAppBarDemoState(
    size: TopAppBarDemoState.Size = TopAppBarDemoState.Size.Small,
    centerAligned: Boolean = false,
    navigationIcon: TopAppBarDemoState.NavigationIcon = TopAppBarDemoState.NavigationIcon.None,
    title: String = stringResource(id = R.string.app_components_topAppBar_title_label),
    actionCount: Int = 2,
    lastActionIconBadge: TopAppBarDemoState.ActionIconBadge = TopAppBarDemoState.ActionIconBadge.None,
    actionAvatar: TopAppBarDemoState.ActionAvatar = TopAppBarDemoState.ActionAvatar.Image,
    actionAvatarMonogram: Char = 'A'
) = rememberSaveable(
    size,
    centerAligned,
    navigationIcon,
    title,
    actionCount,
    lastActionIconBadge,
    actionAvatar,
    actionAvatarMonogram,
    saver = TopAppBarDemoState.Saver
) {
    TopAppBarDemoState(size, centerAligned, navigationIcon, title, actionCount, lastActionIconBadge, actionAvatar, actionAvatarMonogram)
}

class TopAppBarDemoState(
    size: Size,
    centerAligned: Boolean,
    navigationIcon: NavigationIcon,
    title: String,
    actionCount: Int,
    lastActionIconBadge: ActionIconBadge,
    actionAvatar: ActionAvatar,
    actionAvatarMonogram: Char
) {
    companion object {

        const val MinActionCount = 0
        const val MaxActionCount = 3
        const val ActionIconBadgeCount = 1

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        size,
                        centerAligned,
                        navigationIcon,
                        title,
                        actionCount,
                        lastActionIconBadge,
                        actionAvatar,
                        actionAvatarMonogram
                    )
                }
            },
            restore = { list ->
                TopAppBarDemoState(
                    list[0] as Size,
                    list[1] as Boolean,
                    list[2] as NavigationIcon,
                    list[3] as String,
                    list[4] as Int,
                    list[5] as ActionIconBadge,
                    list[6] as ActionAvatar,
                    list[7] as Char
                )
            }
        )
    }

    private var _size: Size by mutableStateOf(size)
    var size: Size
        get() = _size
        set(value) {
            _size = value
            if (value != Size.Small) {
                centerAligned = false
            }
        }

    var centerAligned: Boolean by mutableStateOf(centerAligned)

    var navigationIcon: NavigationIcon by mutableStateOf(navigationIcon)

    var title: String by mutableStateOf(title)

    var actionCount: Int by mutableStateOf(actionCount)

    val actions: List<Action>
        get() = List(actionCount) { index ->
            if (actionCount > 1 && index == actionCount - 1) Action.Avatar else Action.Icon
        }

    var lastActionIconBadge: ActionIconBadge by mutableStateOf(lastActionIconBadge)

    var actionAvatar: ActionAvatar by mutableStateOf(actionAvatar)

    var actionAvatarMonogram: Char by mutableStateOf(actionAvatarMonogram)

    val centerAlignedSwitchEnabled: Boolean
        get() = size == Size.Small

    val lastActionIconBadgeFilterChipsEnabled: Boolean
        get() = actions.contains(Action.Icon)

    val actionAvatarFilterChipsEnabled: Boolean
        get() = actions.contains(Action.Avatar)

    val actionAvatarMonogramTextInputEnabled: Boolean
        get() = actions.contains(Action.Avatar) && actionAvatar == ActionAvatar.Monogram

    enum class Size(@StringRes val labelRes: Int) {
        Small(R.string.app_components_common_smallSize_tech),
        Medium(R.string.app_components_common_mediumSize_tech),
        Large(R.string.app_components_common_largeSize_tech)
    }

    enum class NavigationIcon(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_tech),
        Back(R.string.app_components_topAppBar_backNavigationIcon_tech),
        Close(R.string.app_components_topAppBar_closeNavigationIcon_tech),
        Menu(R.string.app_components_topAppBar_menuNavigationIcon_tech),
        Custom(R.string.app_components_topAppBar_customNavigationIcon_tech)
    }

    enum class ActionIconBadge(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_tech),
        Standard(R.string.app_components_badge_standardType_tech),
        Count(R.string.app_components_badge_countType_tech)
    }

    enum class ActionAvatar(@StringRes val labelRes: Int) {
        Image(R.string.app_components_topAppBar_imageActionAvatar_tech),
        Monogram(R.string.app_components_topAppBar_monogramActionAvatar_tech)
    }

    enum class Action {
        Icon,
        Avatar
    }
}
