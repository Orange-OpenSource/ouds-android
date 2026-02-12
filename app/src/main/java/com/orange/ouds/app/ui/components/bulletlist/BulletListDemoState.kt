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
import androidx.compose.ui.res.stringResource
import com.orange.ouds.app.R
import com.orange.ouds.core.component.OudsBulletListDefaults
import com.orange.ouds.core.component.OudsBulletListFontSize
import com.orange.ouds.core.component.OudsBulletListFontWeight
import com.orange.ouds.core.component.OudsBulletListType
import com.orange.ouds.core.component.OudsBulletListUnorderedAsset
import kotlin.reflect.full.createInstance

@Composable
fun rememberBulletListDemoState(
    type: OudsBulletListType = OudsBulletListType.Unordered(),
    unorderedAssetClassName: String = OudsBulletListUnorderedAsset.Bullet::class.java.name,
    unorderedAssetBrandColor: Boolean = true,
    fontSize: OudsBulletListFontSize = OudsBulletListDefaults.TextStyle.fontSize,
    fontWeight: OudsBulletListFontWeight = OudsBulletListDefaults.TextStyle.fontWeight,
    levelCount: Int = BulletListDemoState.MinLevelCount,
    label: String = stringResource(R.string.app_components_common_label_value)
): BulletListDemoState {
    return rememberSaveable(
        type,
        unorderedAssetClassName,
        unorderedAssetBrandColor,
        fontSize,
        fontWeight,
        levelCount,
        label,
        saver = BulletListDemoState.Saver
    ) {
        BulletListDemoState(
            type = type,
            unorderedAssetClassName = unorderedAssetClassName,
            unorderedAssetBrandColor = unorderedAssetBrandColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
            levelCount = levelCount,
            label = label
        )
    }
}

class BulletListDemoState(
    type: OudsBulletListType,
    unorderedAssetClassName: String,
    unorderedAssetBrandColor: Boolean,
    fontSize: OudsBulletListFontSize,
    fontWeight: OudsBulletListFontWeight,
    levelCount: Int,
    label: String
) {

    companion object {
        const val MinLevelCount = 1
        const val MaxLevelCount = 3

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        type::class.java.name,
                        unorderedAssetClassName,
                        unorderedAssetBrandColor,
                        fontSize,
                        fontWeight,
                        levelCount,
                        label
                    )
                }
            },
            restore = { list: List<Any?> ->
                val typeName = list[0] as String
                BulletListDemoState(
                    Class.forName(typeName).kotlin.createInstance() as OudsBulletListType,
                    list[1] as String,
                    list[2] as Boolean,
                    list[3] as OudsBulletListFontSize,
                    list[4] as OudsBulletListFontWeight,
                    list[5] as Int,
                    list[6] as String
                )
            }
        )
    }

    var type: OudsBulletListType by mutableStateOf(type)

    var unorderedAssetClassName: String by mutableStateOf(unorderedAssetClassName)

    var unorderedAssetBrandColor: Boolean by mutableStateOf(unorderedAssetBrandColor)

    var fontSize: OudsBulletListFontSize by mutableStateOf(fontSize)

    var fontWeight: OudsBulletListFontWeight by mutableStateOf(fontWeight)

    var levelCount: Int by mutableIntStateOf(levelCount)

    var label: String by mutableStateOf(label)

    val unorderedAssetChipsEnabled: Boolean
        get() = type is OudsBulletListType.Unordered

    val unorderedAssetBrandColorSwitchEnabled: Boolean
        get() = type is OudsBulletListType.Unordered

}