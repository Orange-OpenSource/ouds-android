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

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import com.orange.ouds.core.component.OudsListItemIconSize
import com.orange.ouds.core.component.OudsListItemImageRatio
import com.orange.ouds.core.component.OudsListItemImageSize
import com.orange.ouds.core.component.OudsListItemIndicator
import com.orange.ouds.core.component.OudsListItemTextStyle
import com.orange.ouds.core.component.OudsListItemVerticalAlignment


open class BaseListItemDemoState(
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
    leadingIconTint: IconTint,
    leadingStatusIcon: StatusIcon,
    leadingImageSize: OudsListItemImageSize,
    leadingImageRatio: OudsListItemImageRatio,
    leadingImageRoundedCorners: Boolean,
    trailing: Trailing,
    trailingIconSize: OudsListItemIconSize,
    trailingIconTint: IconTint,
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
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
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
                        leadingIconTint,
                        leadingStatusIcon,
                        leadingImageSize,
                        leadingImageRatio,
                        leadingImageRoundedCorners,
                        trailing,
                        trailingIconSize,
                        trailingIconTint,
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
            },
            restore = { list: List<Any?> ->
                BaseListItemDemoState(
                    list[0] as Size,
                    list[1] as Int,
                    list[2] as String,
                    list[3] as Boolean,
                    list[4] as Indicator,
                    list[5] as OudsListItemVerticalAlignment,
                    list[6] as String?,
                    list[7] as String?,
                    list[8] as String?,
                    list[9] as Leading,
                    list[10] as OudsListItemIconSize,
                    list[11] as IconTint,
                    list[12] as StatusIcon,
                    list[13] as OudsListItemImageSize,
                    list[14] as OudsListItemImageRatio,
                    list[15] as Boolean,
                    list[16] as Trailing,
                    list[17] as OudsListItemIconSize,
                    list[18] as IconTint,
                    list[19] as StatusIcon,
                    list[20] as OudsListItemImageSize,
                    list[21] as OudsListItemImageRatio,
                    list[22] as Boolean,
                    list[23] as String,
                    list[24] as String?,
                    list[25] as OudsListItemTextStyle,
                    list[26] as Boolean,
                    list[27] as String?,
                    list[28] as Boolean,
                    list[29] as Boolean,
                )
            }
        )
    }

    val size: Size by mutableStateOf(size)

    lateinit var pagerState: PagerState
    var selectedTabIndex: Int by mutableIntStateOf(selectedTabIndex)
    val tabs = CustomizationTab.entries

    var boldLabel: Boolean by mutableStateOf(boldLabel)

    var clickable: Boolean by mutableStateOf(clickable)

    var description: String? by mutableStateOf(description)

    var divider: Boolean by mutableStateOf(divider)

    var enabled: Boolean by mutableStateOf(enabled)

    var extraLabel: String? by mutableStateOf(extraLabel)

    var helperText: String? by mutableStateOf(helperText)

    var indicator: Indicator by mutableStateOf(indicator)
    val indicatorEnabled: Boolean
        get() = clickable

    var label: String by mutableStateOf(label)

    var leading: Leading by mutableStateOf(leading)

    var leadingIconSize: OudsListItemIconSize by mutableStateOf(leadingIconSize)
    var leadingIconTint: IconTint by mutableStateOf(leadingIconTint)
    var leadingStatusIcon: StatusIcon by mutableStateOf(leadingStatusIcon)
    val leadingIconOptionsEnabled: Boolean
        get() = leading == Leading.Icon

    var leadingImageSize: OudsListItemImageSize by mutableStateOf(leadingImageSize)
    var leadingImageRatio: OudsListItemImageRatio by mutableStateOf(leadingImageRatio)
    var leadingImageRoundedCorners: Boolean by mutableStateOf(leadingImageRoundedCorners)
    val leadingImageOptionsEnabled: Boolean
        get() = leading == Leading.Image

    var overline: String? by mutableStateOf(overline)

    var trailing: Trailing by mutableStateOf(trailing)

    var trailingIconSize: OudsListItemIconSize by mutableStateOf(trailingIconSize)
    var trailingIconTint: IconTint by mutableStateOf(trailingIconTint)
    var trailingStatusIcon: StatusIcon by mutableStateOf(trailingStatusIcon)
    val trailingIconOptionsEnabled: Boolean
        get() = trailing == Trailing.Icon

    var trailingImageSize: OudsListItemImageSize by mutableStateOf(trailingImageSize)
    var trailingImageRatio: OudsListItemImageRatio by mutableStateOf(trailingImageRatio)
    var trailingImageRoundedCorners: Boolean by mutableStateOf(trailingImageRoundedCorners)
    val trailingImageOptionsEnabled: Boolean
        get() = trailing == Trailing.Image

    var trailingTextLabel: String by mutableStateOf(trailingTextLabel)
    var trailingTextExtraLabel: String? by mutableStateOf(trailingTextExtraLabel)
    var trailingTextStyle: OudsListItemTextStyle by mutableStateOf(trailingTextStyle)
    val trailingTextOptionsEnabled: Boolean
        get() = trailing == Trailing.Text

    var verticalAlignment: OudsListItemVerticalAlignment by mutableStateOf(verticalAlignment)

    enum class CustomizationTab {
        General, Leading, Texts, Trailing
    }

    enum class Size {
        Default, Small
    }

    enum class Indicator {
        Next, Previous, External;

        fun toOudsListItemIndicator() = when (this) {
            External -> OudsListItemIndicator.External
            Previous -> OudsListItemIndicator.Previous
            else -> OudsListItemIndicator.Next
        }
    }

    enum class Leading {
        None, Icon, Image
    }

    enum class Trailing {
        None, Icon, Image, Text
    }

    enum class StatusIcon {
        None, Info, Negative, Positive, Warning
    }

    enum class IconTint {
        Tinted, Untinted
    }
}