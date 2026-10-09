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
    belowTextContent: Boolean,
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
    bottomContent: Boolean,
    divider: Boolean,
    helperText: String?,
    boldLabel: Boolean,
    labelContent: Boolean,
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
                        belowTextContent,
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
                        bottomContent,
                        divider,
                        helperText,
                        boldLabel,
                        labelContent,
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
                    list[9] as Boolean,
                    list[10] as Leading,
                    list[11] as OudsListItemIconSize,
                    list[12] as IconTint,
                    list[13] as StatusIcon,
                    list[14] as OudsListItemImageSize,
                    list[15] as OudsListItemImageRatio,
                    list[16] as Boolean,
                    list[17] as Trailing,
                    list[18] as OudsListItemIconSize,
                    list[19] as IconTint,
                    list[20] as StatusIcon,
                    list[21] as OudsListItemImageSize,
                    list[22] as OudsListItemImageRatio,
                    list[23] as Boolean,
                    list[24] as String,
                    list[25] as String?,
                    list[26] as OudsListItemTextStyle,
                    list[27] as Boolean,
                    list[28] as Boolean,
                    list[29] as String?,
                    list[30] as Boolean,
                    list[31] as Boolean,
                    list[32] as Boolean
                )
            }
        )
    }

    val size: Size by mutableStateOf(size)

    lateinit var pagerState: PagerState
    var selectedTabIndex: Int by mutableIntStateOf(selectedTabIndex)
    val tabs = CustomizationTab.entries

    //// General configuration

    var clickable: Boolean by mutableStateOf(clickable)

    var indicator: Indicator by mutableStateOf(indicator)
    val indicatorEnabled: Boolean
        get() = clickable

    var verticalAlignment: OudsListItemVerticalAlignment by mutableStateOf(verticalAlignment)

    var divider: Boolean by mutableStateOf(divider)

    var enabled: Boolean by mutableStateOf(enabled)

    var bottomContent: Boolean by mutableStateOf(bottomContent)

    //// Leading configuration

    var leading: Leading by mutableStateOf(leading)

    var leadingIconSize: OudsListItemIconSize by mutableStateOf(leadingIconSize)
    var leadingIconTint: IconTint by mutableStateOf(leadingIconTint)
    var leadingStatusIcon: StatusIcon by mutableStateOf(leadingStatusIcon)
    val leadingIconSettingsVisible: Boolean
        get() = leading == Leading.Icon

    var leadingImageSize: OudsListItemImageSize by mutableStateOf(leadingImageSize)
    var leadingImageRatio: OudsListItemImageRatio by mutableStateOf(leadingImageRatio)
    var leadingImageRoundedCorners: Boolean by mutableStateOf(leadingImageRoundedCorners)
    val leadingImageSettingsVisible: Boolean
        get() = leading == Leading.Image

    val leadingContentSettingsVisible: Boolean
        get() = leading == Leading.Content

    //// Texts configuration

    var label: String by mutableStateOf(label)
    var boldLabel: Boolean by mutableStateOf(boldLabel)
    var labelContent: Boolean by mutableStateOf(labelContent)
    val labelOptionsEnabled: Boolean
        get() = !labelContent

    var description: String? by mutableStateOf(description)

    var overline: String? by mutableStateOf(overline)

    var extraLabel: String? by mutableStateOf(extraLabel)

    var belowTextContent: Boolean by mutableStateOf(belowTextContent)

    var helperText: String? by mutableStateOf(helperText)

    //// Trailing configuration

    var trailing: Trailing by mutableStateOf(trailing)

    var trailingIconSize: OudsListItemIconSize by mutableStateOf(trailingIconSize)
    var trailingIconTint: IconTint by mutableStateOf(trailingIconTint)
    var trailingStatusIcon: StatusIcon by mutableStateOf(trailingStatusIcon)
    val trailingIconSettingsVisible: Boolean
        get() = trailing == Trailing.Icon

    var trailingImageSize: OudsListItemImageSize by mutableStateOf(trailingImageSize)
    var trailingImageRatio: OudsListItemImageRatio by mutableStateOf(trailingImageRatio)
    var trailingImageRoundedCorners: Boolean by mutableStateOf(trailingImageRoundedCorners)
    val trailingImageSettingsVisible: Boolean
        get() = trailing == Trailing.Image

    var trailingTextLabel: String by mutableStateOf(trailingTextLabel)
    var trailingTextExtraLabel: String? by mutableStateOf(trailingTextExtraLabel)
    var trailingTextStyle: OudsListItemTextStyle by mutableStateOf(trailingTextStyle)
    val trailingTextSettingsVisible: Boolean
        get() = trailing == Trailing.Text

    val trailingComponentExampleAlertVisible: Boolean
        get() = trailing in listOf(Trailing.Badge, Trailing.Tag)

    val trailingContentSettingsVisible: Boolean
        get() = trailing == Trailing.Content


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

    enum class Leading(val availableForSizes: List<Size> = listOf(Size.Default, Size.Small)) {
        None, Icon, Image, Content(listOf(Size.Default))
    }

    enum class Trailing(val availableForSizes: List<Size> = listOf(Size.Default, Size.Small)) {
        None, Badge, Icon, Image, Tag, Text, Content(listOf(Size.Default))
    }

    enum class StatusIcon {
        None, Info, Negative, Positive, Warning
    }

    enum class IconTint {
        Tinted, Untinted
    }
}