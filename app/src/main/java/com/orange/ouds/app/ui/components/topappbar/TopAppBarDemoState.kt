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
import com.orange.ouds.app.R

@Composable
fun rememberTopAppBarDemoState(
    size: TopAppBarDemoState.Size = TopAppBarDemoState.Size.Small,
    centerAligned: Boolean = false,
    navigationIcon: TopAppBarDemoState.NavigationIcon = TopAppBarDemoState.NavigationIcon.None,
    title: String = "Title",
    actionIconBadge: TopAppBarDemoState.ActionIconBadge = TopAppBarDemoState.ActionIconBadge.None,
    actionAvatar: TopAppBarDemoState.ActionAvatar = TopAppBarDemoState.ActionAvatar.Image,
    actionAvatarMonogram: Char = 'A'
) = rememberSaveable(size, centerAligned, navigationIcon, title, actionAvatar, actionAvatarMonogram, saver = TopAppBarDemoState.Saver) {
    TopAppBarDemoState(size, centerAligned, navigationIcon, title, actionIconBadge, actionAvatar, actionAvatarMonogram)
}

class TopAppBarDemoState(
    size: Size,
    centerAligned: Boolean,
    navigationIcon: NavigationIcon,
    title: String,
    actionIconBadge: ActionIconBadge,
    actionAvatar: ActionAvatar,
    actionAvatarMonogram: Char
) {
    companion object {

        const val ActionIconBadgeCount = 1

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        size,
                        centerAligned,
                        navigationIcon,
                        title,
                        actionIconBadge,
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
                    list[4] as ActionIconBadge,
                    list[5] as ActionAvatar,
                    list[6] as Char
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

    var actionIconBadge: ActionIconBadge by mutableStateOf(actionIconBadge)

    var actionAvatar: ActionAvatar by mutableStateOf(actionAvatar)

    var actionAvatarMonogram: Char by mutableStateOf(actionAvatarMonogram)

    val centerAlignedSwitchEnabled: Boolean
        get() = size == Size.Small

    val actionAvatarMonogramTextFieldEnabled: Boolean
        get() = actionAvatar == ActionAvatar.Monogram

    enum class Size(@StringRes val labelRes: Int) {
        Small(R.string.app_components_topAppBar_smallSize_label),
        Medium(R.string.app_components_topAppBar_mediumSize_label),
        Large(R.string.app_components_topAppBar_largeSize_label)
    }

    enum class NavigationIcon(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_label),
        Back(R.string.app_components_topAppBar_backNavigationIcon_label),
        Close(R.string.app_components_topAppBar_closeNavigationIcon_label),
        Menu(R.string.app_components_topAppBar_menuNavigationIcon_label),
        Custom(R.string.app_components_topAppBar_customNavigationIcon_label)
    }

    enum class ActionIconBadge(@StringRes val labelRes: Int) {
        None(R.string.app_components_common_none_label),
        Standard(R.string.app_components_badge_standardType_label),
        Count(R.string.app_components_badge_countType_label)
    }

    enum class ActionAvatar(@StringRes val labelRes: Int) {
        Image(R.string.app_components_topAppBar_imageActionAvatar_label),
        Monogram(R.string.app_components_topAppBar_monogramActionAvatar_label)
    }
}
