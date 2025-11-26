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
    navigationIcon: Boolean = false,
    title: String = "Title",
    avatar: TopAppBarDemoState.Avatar = TopAppBarDemoState.Avatar.Image,
    avatarMonogram: String = "A"
) = rememberSaveable(size, centerAligned, navigationIcon, title, avatar, avatarMonogram, saver = TopAppBarDemoState.Saver) {
    TopAppBarDemoState(size, centerAligned, navigationIcon, title, avatar, avatarMonogram)
}

class TopAppBarDemoState(
    size: Size,
    centerAligned: Boolean,
    navigationIcon: Boolean,
    title: String,
    avatar: Avatar,
    avatarMonogram: String
) {
    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        size,
                        centerAligned,
                        navigationIcon,
                        title,
                        avatar,
                        avatarMonogram
                    )
                }
            },
            restore = { list ->
                TopAppBarDemoState(
                    list[0] as Size,
                    list[1] as Boolean,
                    list[2] as Boolean,
                    list[3] as String,
                    list[4] as Avatar,
                    list[5] as String
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

    var navigationIcon: Boolean by mutableStateOf(navigationIcon)

    var title: String by mutableStateOf(title)

    var avatar: Avatar by mutableStateOf(avatar)

    var avatarMonogram: String by mutableStateOf(avatarMonogram)

    val centerAlignedSwitchEnabled: Boolean
        get() = size == Size.Small

    val avatarMonogramTextFieldEnabled: Boolean
        get() = avatar == Avatar.Monogram

    enum class Size(@StringRes val labelRes: Int) {
        Small(R.string.app_components_topAppBar_smallSize_label),
        Medium(R.string.app_components_topAppBar_mediumSize_label),
        Large(R.string.app_components_topAppBar_largeSize_label)
    }

    enum class Avatar(@StringRes val labelRes: Int) {
        Image(R.string.app_components_topAppBar_imageAvatar_label),
        Monogram(R.string.app_components_topAppBar_monogramAvatar_label)
    }
}
