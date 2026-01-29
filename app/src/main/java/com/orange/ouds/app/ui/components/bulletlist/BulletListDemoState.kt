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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.LocalThemeDrawableResources
import com.orange.ouds.core.component.OudsBulletListTextStyle
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsBulletListUnorderedIcon
import kotlin.reflect.full.createInstance

val DefaultUnorderedFreeIconId
    @Composable
    get() = LocalThemeDrawableResources.current.home

@Composable
fun rememberBulletListDemoState(
    type: OudsBulletListType = OudsBulletListType.Unordered(),
    unorderedIcon: OudsBulletListUnorderedIcon = OudsBulletListUnorderedIcon.Bullet(),
    brandColorIcon: Boolean = false,
    textStyle: OudsBulletListTextStyle = OudsBulletListTextStyle.BodyLarge,
    bold: Boolean = true,
    levelCount: Int = BulletListDemoState.MinLevelCount,
    label: String = stringResource(R.string.app_components_common_label_label)
): BulletListDemoState {
    val defaultFreeIconPainter = painterResource(DefaultUnorderedFreeIconId)
    return rememberSaveable(
        type,
        unorderedIcon,
        brandColorIcon,
        textStyle,
        bold,
        levelCount,
        label,
        saver = BulletListDemoState.getSaver(defaultFreeIconPainter)
    ) {
        BulletListDemoState(
            type = type,
            unorderedIcon = unorderedIcon,
            brandColorIcon = brandColorIcon,
            textStyle = textStyle,
            bold = bold,
            levelCount = levelCount,
            label = label
        )
    }
}

class BulletListDemoState(
    type: OudsBulletListType,
    unorderedIcon: OudsBulletListUnorderedIcon,
    brandColorIcon: Boolean,
    textStyle: OudsBulletListTextStyle,
    bold: Boolean,
    levelCount: Int,
    label: String
) {

    companion object {
        const val MinLevelCount = 1
        const val MaxLevelCount = 3

        fun getSaver(defaultFreeIconPainter: Painter) = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        type::class.java.name,
                        unorderedIcon::class.java.name,
                        brandColorIcon,
                        textStyle,
                        bold,
                        levelCount,
                        label
                    )
                }
            },
            restore = { list: List<Any?> ->
                val typeName = list[0] as String
                val unorderedIconName = list[1] as String
                val unorderedIcon = if (unorderedIconName == OudsBulletListUnorderedIcon.Free::class.java.name) {
                    OudsBulletListUnorderedIcon.Free(painter = defaultFreeIconPainter)
                } else {
                    Class.forName(unorderedIconName).kotlin.createInstance() as OudsBulletListUnorderedIcon
                }

                BulletListDemoState(
                    Class.forName(typeName).kotlin.createInstance() as OudsBulletListType,
                    unorderedIcon,
                    list[2] as Boolean,
                    list[3] as OudsBulletListTextStyle,
                    list[4] as Boolean,
                    list[5] as Int,
                    list[6] as String
                )
            }
        )
    }

    var type: OudsBulletListType by mutableStateOf(type)

    var unorderedIcon: OudsBulletListUnorderedIcon by mutableStateOf(unorderedIcon)

    var brandColorIcon: Boolean by mutableStateOf(brandColorIcon)

    var textStyle: OudsBulletListTextStyle by mutableStateOf(textStyle)

    var bold: Boolean by mutableStateOf(bold)

    var levelCount: Int by mutableIntStateOf(levelCount)

    var label: String by mutableStateOf(label)

}