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

package com.orange.ouds.core.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.state.ToggleableState
import com.orange.ouds.core.component.OudsAlertMessagePreviewParameter
import com.orange.ouds.core.component.OudsAlertMessagePreviewParameterProvider
import com.orange.ouds.core.component.OudsBadgePreviewParameter
import com.orange.ouds.core.component.OudsBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsBadgeWithIconPreviewParameterProvider
import com.orange.ouds.core.component.OudsBulletListPreviewParameter
import com.orange.ouds.core.component.OudsBulletListPreviewParameterProvider
import com.orange.ouds.core.component.OudsButtonPreviewParameter
import com.orange.ouds.core.component.OudsButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsButtonWithIconBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsCardItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsCardItemWithRoundedCornersParameterProvider
import com.orange.ouds.core.component.OudsCheckboxHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxPreviewParameterProvider
import com.orange.ouds.core.component.OudsCircularProgressIndicatorPreviewParameter
import com.orange.ouds.core.component.OudsCircularProgressIndicatorPreviewParameterProvider
import com.orange.ouds.core.component.OudsCircularProgressIndicatorSizedPreviewParameterProvider
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.OudsColoredBoxPreviewParameterProvider
import com.orange.ouds.core.component.OudsControlItemConstrainedMaxWidthPreviewParameterProvider
import com.orange.ouds.core.component.OudsDividerColor
import com.orange.ouds.core.component.OudsDividerOrientation
import com.orange.ouds.core.component.OudsDividerPreviewParameterProvider
import com.orange.ouds.core.component.OudsFilterChipPreviewParameter
import com.orange.ouds.core.component.OudsFilterChipPreviewParameterProvider
import com.orange.ouds.core.component.OudsFloatingActionButtonAppearance
import com.orange.ouds.core.component.OudsFloatingActionButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsInlineAlertPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinearProgressIndicatorPreviewParameter
import com.orange.ouds.core.component.OudsLinearProgressIndicatorPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinkCompactDensityPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinkPreviewParameter
import com.orange.ouds.core.component.OudsLinkPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinkSize
import com.orange.ouds.core.component.OudsListItemDecoration
import com.orange.ouds.core.component.OudsListItemLeading
import com.orange.ouds.core.component.OudsListItemPreviewParameter
import com.orange.ouds.core.component.OudsListItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsListItemTrailing
import com.orange.ouds.core.component.OudsNavigationBarItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsNavigationBarPreviewParameterProvider
import com.orange.ouds.core.component.OudsNavigationButtonPreviewParameter
import com.orange.ouds.core.component.OudsNavigationButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsPasswordInputPreviewParameter
import com.orange.ouds.core.component.OudsPasswordInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsPasswordInputWithRichTextPreviewParameterProvider
import com.orange.ouds.core.component.OudsPinCodeInputPreviewParameter
import com.orange.ouds.core.component.OudsPinCodeInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsPinCodeInputWithRichTextPreviewParameterProvider
import com.orange.ouds.core.component.OudsPinCodeInputWithRoundedCornersPreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsSkeletonPreviewParameter
import com.orange.ouds.core.component.OudsSkeletonPreviewParameterProvider
import com.orange.ouds.core.component.OudsSmallCardItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsSmallListItemLeading
import com.orange.ouds.core.component.OudsSmallListItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsSmallListItemTrailing
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameter
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameterProvider
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameter
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsSwitchPreviewParameterProvider
import com.orange.ouds.core.component.OudsTagPreviewParameter
import com.orange.ouds.core.component.OudsTagPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextAreaAutoResizePreviewParameterProvider
import com.orange.ouds.core.component.OudsTextAreaConstrainedMaxWidthPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextAreaPreviewParameter
import com.orange.ouds.core.component.OudsTextAreaPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextAreaWithRichTextPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextInputConstrainedMaxWidthPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextInputPreviewParameter
import com.orange.ouds.core.component.OudsTextInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextInputWithRichTextPreviewParameterProvider
import com.orange.ouds.core.component.OudsTopAppBarPreviewParameter
import com.orange.ouds.core.component.OudsTopAppBarPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsAlertMessage
import com.orange.ouds.core.component.PreviewOudsAlertMessageSkeleton
import com.orange.ouds.core.component.PreviewOudsAlertMessageWithRichText
import com.orange.ouds.core.component.PreviewOudsAlertMessageWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsBadge
import com.orange.ouds.core.component.PreviewOudsBadgeWithIcon
import com.orange.ouds.core.component.PreviewOudsBadgeWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsBottomSheetScaffold
import com.orange.ouds.core.component.PreviewOudsBulletList
import com.orange.ouds.core.component.PreviewOudsBulletListRtl
import com.orange.ouds.core.component.PreviewOudsBulletListSkeleton
import com.orange.ouds.core.component.PreviewOudsBulletListWithRichText
import com.orange.ouds.core.component.PreviewOudsButton
import com.orange.ouds.core.component.PreviewOudsButtonMaxWidthReached
import com.orange.ouds.core.component.PreviewOudsButtonOnTwoLines
import com.orange.ouds.core.component.PreviewOudsButtonWithIconBadge
import com.orange.ouds.core.component.PreviewOudsButtonWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsButtonWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsCenterAlignedTopAppBar
import com.orange.ouds.core.component.PreviewOudsCheckbox
import com.orange.ouds.core.component.PreviewOudsCheckboxHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItem
import com.orange.ouds.core.component.PreviewOudsCheckboxItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsCheckboxItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithLongDescription
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithRichText
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsCircularProgressIndicator
import com.orange.ouds.core.component.PreviewOudsCircularProgressIndicatorSized
import com.orange.ouds.core.component.PreviewOudsCircularProgressIndicatorWithHelperText
import com.orange.ouds.core.component.PreviewOudsColoredBox
import com.orange.ouds.core.component.PreviewOudsDivider
import com.orange.ouds.core.component.PreviewOudsExtendedFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsFilterChip
import com.orange.ouds.core.component.PreviewOudsFilterChipOnTwoLines
import com.orange.ouds.core.component.PreviewOudsFilterChipWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsFloatingActionButtonWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsInlineAlert
import com.orange.ouds.core.component.PreviewOudsInlineAlertWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsInputTag
import com.orange.ouds.core.component.PreviewOudsLargeFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsLargeTopAppBar
import com.orange.ouds.core.component.PreviewOudsLinearProgressIndicator
import com.orange.ouds.core.component.PreviewOudsLinearProgressIndicatorWithHelperText
import com.orange.ouds.core.component.PreviewOudsLink
import com.orange.ouds.core.component.PreviewOudsLinkCompactDensity
import com.orange.ouds.core.component.PreviewOudsLinkOnTwoLines
import com.orange.ouds.core.component.PreviewOudsLinkWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsMediumTopAppBar
import com.orange.ouds.core.component.PreviewOudsModalBottomSheet
import com.orange.ouds.core.component.PreviewOudsNavigationBar
import com.orange.ouds.core.component.PreviewOudsNavigationBarItem
import com.orange.ouds.core.component.PreviewOudsNavigationButton
import com.orange.ouds.core.component.PreviewOudsNavigationButtonMaxWidthReached
import com.orange.ouds.core.component.PreviewOudsNavigationButtonOnTwoLines
import com.orange.ouds.core.component.PreviewOudsNavigationButtonWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsNavigationCardItem
import com.orange.ouds.core.component.PreviewOudsNavigationCardItemWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsNavigationListItem
import com.orange.ouds.core.component.PreviewOudsNavigationListItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsNavigationListItemWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsNavigationSmallCardItem
import com.orange.ouds.core.component.PreviewOudsNavigationSmallCardItemWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsNavigationSmallListItem
import com.orange.ouds.core.component.PreviewOudsPasswordInput
import com.orange.ouds.core.component.PreviewOudsPasswordInputWithRichText
import com.orange.ouds.core.component.PreviewOudsPinCodeInput
import com.orange.ouds.core.component.PreviewOudsPinCodeInputWithRichText
import com.orange.ouds.core.component.PreviewOudsPinCodeInputWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsRadioButton
import com.orange.ouds.core.component.PreviewOudsRadioButtonHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItem
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithDescriptionText
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithRichText
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsSkeleton
import com.orange.ouds.core.component.PreviewOudsSmallButton
import com.orange.ouds.core.component.PreviewOudsSmallButtonOnTwoLines
import com.orange.ouds.core.component.PreviewOudsSmallButtonWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsSmallButtonWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsSmallFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsStaticCardItem
import com.orange.ouds.core.component.PreviewOudsStaticCardItemWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsStaticListItem
import com.orange.ouds.core.component.PreviewOudsStaticSmallCardItem
import com.orange.ouds.core.component.PreviewOudsStaticSmallCardItemWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsStaticSmallListItem
import com.orange.ouds.core.component.PreviewOudsSuggestionChip
import com.orange.ouds.core.component.PreviewOudsSuggestionChipOnTwoLines
import com.orange.ouds.core.component.PreviewOudsSuggestionChipWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsSwitch
import com.orange.ouds.core.component.PreviewOudsSwitchItem
import com.orange.ouds.core.component.PreviewOudsSwitchItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithLongDescription
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithRichText
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsTag
import com.orange.ouds.core.component.PreviewOudsTagWithUntintedIcon
import com.orange.ouds.core.component.PreviewOudsTextArea
import com.orange.ouds.core.component.PreviewOudsTextAreaAutoResize
import com.orange.ouds.core.component.PreviewOudsTextAreaConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsTextAreaWithRichText
import com.orange.ouds.core.component.PreviewOudsTextAreaWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsTextInput
import com.orange.ouds.core.component.PreviewOudsTextInputConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsTextInputWithLongLabels
import com.orange.ouds.core.component.PreviewOudsTextInputWithRichText
import com.orange.ouds.core.component.PreviewOudsTextInputWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsTextInputWithUntintedLeadingIcon
import com.orange.ouds.core.component.PreviewOudsTopAppBar
import com.orange.ouds.core.component.PreviewOudsTopAppBarBackgrounds
import com.orange.ouds.core.component.PreviewOudsTopAppBarWithUntintedIcon
import com.orange.ouds.core.theme.WindowWidthSizeClass
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.theme.OudsThemeContract

/**
 * This interface and its nested objects allow to use preview methods and parameters in the core-test module while keeping them internal in the core module.
 * This avoids polluting Android Studio code completion with methods and classes related to component previews.
 *
 * @suppress
 */
@InternalOudsApi
interface OudsPreviewableComponent {

    val parameters: List<Any>

    fun isPreviewAvailable(darkThemeEnabled: Boolean) = true

    @Composable
    fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?)

    @InternalOudsApi
    object AlertMessage {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1700

            override val parameters: List<Any> = OudsAlertMessagePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsAlertMessage(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsAlertMessagePreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsAlertMessageWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsAlertMessageWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object Skeleton : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsAlertMessageSkeleton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }
    }

    @InternalOudsApi
    object Badge {

        const val PreviewWidthDp = 420

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsBadgePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBadge(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBadgePreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsBadgeWithIconPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBadgeWithIcon(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    enabled = parameter as Boolean
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBadgeWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object BottomSheetScaffold : OudsPreviewableComponent {

        override val parameters: List<Any> = emptyList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsBottomSheetScaffold(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled
            )
        }
    }

    @InternalOudsApi
    object BulletList {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 730

            override val parameters: List<Any> = OudsBulletListPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletList(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBulletListPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object Rtl : OudsPreviewableComponent {

            const val PreviewHeightDp = 730

            override val parameters: List<Any> = OudsBulletListPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletListRtl(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBulletListPreviewParameter
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletListWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }

        @InternalOudsApi
        object Skeleton : OudsPreviewableComponent {

            const val PreviewHeightDp = 730

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletListSkeleton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }
    }

    @InternalOudsApi
    object Button {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsButtonPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithIconBadge : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsButtonWithIconBadgePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithIconBadge(
                    theme = theme,
                    count = parameter as Int
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object MaxWidthReached : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonMaxWidthReached(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object CardItem {

        @InternalOudsApi
        object Navigation : OudsPreviewableComponent {

            const val PreviewHeightDp = 1050

            override val parameters: List<Any> = OudsCardItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsNavigationCardItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object NavigationWithRoundedCorners : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsCardItemWithRoundedCornersParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationCardItemWithRoundedCorners(
                    theme = theme,
                    decoration = parameter as OudsListItemDecoration
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = darkThemeEnabled
        }

        @InternalOudsApi
        object Static : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsCardItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsStaticCardItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object StaticWithRoundedCorners : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsCardItemWithRoundedCornersParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsStaticCardItemWithRoundedCorners(
                    theme = theme,
                    decoration = parameter as OudsListItemDecoration
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object CheckboxItem {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1030

            override val parameters: List<Any> = OudsCheckboxItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsCheckboxItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCheckboxItemPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object HighContrastModeEnabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = OudsCheckboxItemHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsCheckboxItemHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCheckboxItemHighContrastModePreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithLongDescription(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 970

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object Checkbox {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = OudsCheckboxPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckbox(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCheckboxPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object HighContrastModeEnabled : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = OudsCheckboxHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    toggleableState = parameter as ToggleableState
                )
            }
        }
    }

    @InternalOudsApi
    object CircularProgressIndicator {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewWidthDp = 410

            override val parameters: List<Any> = OudsCircularProgressIndicatorPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCircularProgressIndicator(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCircularProgressIndicatorPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object Sized : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsCircularProgressIndicatorSizedPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCircularProgressIndicatorSized(
                    theme = theme,
                    size = parameter as Float
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithHelperText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCircularProgressIndicatorWithHelperText(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object ColoredBox : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsColoredBoxPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsColoredBox(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                color = parameter as OudsColoredBoxColor
            )
        }
    }

    @InternalOudsApi
    object Divider {

        @InternalOudsApi
        object Horizontal : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsDividerPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsDivider(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    orientation = OudsDividerOrientation.Horizontal,
                    color = parameter as OudsDividerColor
                )
            }
        }

        @InternalOudsApi
        object Vertical : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsDividerPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsDivider(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    orientation = OudsDividerOrientation.Vertical,
                    color = parameter as OudsDividerColor
                )
            }
        }
    }

    @InternalOudsApi
    object FilterChip {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFilterChipPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFilterChip(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsFilterChipPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFilterChipWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFilterChipOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object FloatingActionButton {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        @InternalOudsApi
        object Small : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        @InternalOudsApi
        object Large : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLargeFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        @InternalOudsApi
        object Extended : OudsPreviewableComponent {

            const val PreviewWidthDp = 700

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsExtendedFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFloatingActionButtonWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object InlineAlert {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsInlineAlertPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsInlineAlert(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    label = parameter as String
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsInlineAlertWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object InputTag : OudsPreviewableComponent {

        override val parameters: List<Any> = emptyList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsInputTag(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled
            )
        }
    }

    @InternalOudsApi
    object LinearProgressIndicator {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsLinearProgressIndicatorPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinearProgressIndicator(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsLinearProgressIndicatorPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithHelperText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinearProgressIndicatorWithHelperText(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object Link {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsLinkPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLink(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsLinkPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object CompactDensity : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsLinkCompactDensityPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinkCompactDensity(
                    theme = theme,
                    size = parameter as OudsLinkSize
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinkOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinkWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object ListItem {

        @InternalOudsApi
        object Navigation : OudsPreviewableComponent {

            const val PreviewHeightDp = 1100

            override val parameters: List<Any> = OudsListItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsNavigationListItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object Static : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsListItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsStaticListItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsListItemLeading, OudsListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            const val PreviewHeightDp = 720

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationListItemWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 720

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationListItemWithEdgeToEdgeDisabled(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object ModalBottomSheet : OudsPreviewableComponent {

        override val parameters: List<Any> = emptyList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsModalBottomSheet(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled
            )
        }
    }

    @InternalOudsApi
    object NavigationBar {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    itemCount = parameter as Int,
                    windowWidthSizeClass = WindowWidthSizeClass.COMPACT
                )
            }
        }

        @InternalOudsApi
        object WithHorizontalItems : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsNavigationBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    itemCount = parameter as Int,
                    windowWidthSizeClass = WindowWidthSizeClass.MEDIUM
                )
            }
        }
    }

    @InternalOudsApi
    object NavigationBarItem {

        const val PreviewWidthDp = 400

        @InternalOudsApi
        object Default : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBarItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    selected = parameter as Boolean,
                    windowWidthSizeClass = WindowWidthSizeClass.COMPACT
                )
            }
        }

        @InternalOudsApi
        object Horizontal : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBarItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    selected = parameter as Boolean,
                    windowWidthSizeClass = WindowWidthSizeClass.MEDIUM
                )
            }
        }
    }

    @InternalOudsApi
    object NavigationButton {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsNavigationButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsNavigationButtonPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationButtonWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationButtonOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object MaxWidthReached : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationButtonMaxWidthReached(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object PasswordInput {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 980

            override val parameters: List<Any> = OudsPasswordInputPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPasswordInput(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsPasswordInputPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPasswordInputWithRichTextPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPasswordInputWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    error = parameter as Boolean
                )
            }
        }
    }

    @InternalOudsApi
    object PinCodeInput {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPinCodeInputPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPinCodeInput(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsPinCodeInputPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPinCodeInputWithRoundedCornersPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPinCodeInputWithRoundedCorners(
                    theme = theme,
                    outlined = parameter as Boolean
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPinCodeInputWithRichTextPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPinCodeInputWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    error = parameter as Boolean
                )
            }
        }
    }

    @InternalOudsApi
    object RadioButtonItem {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1030

            override val parameters: List<Any> = OudsRadioButtonItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsRadioButtonItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsRadioButtonItemPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object HighContrastModeEnabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = OudsRadioButtonItemHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsRadioButtonItemHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsRadioButtonItemHighContrastModePreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithDescriptionText(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 970

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object RadioButton {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = OudsRadioButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsRadioButtonPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object HighContrastModeEnabled : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }
    }

    @InternalOudsApi
    object Skeleton : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsSkeletonPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsSkeleton(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter as OudsSkeletonPreviewParameter
            )
        }
    }

    @InternalOudsApi
    object SmallButton {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsButtonPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallButtonWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallButtonOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallButtonWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object SmallCardItem {

        @InternalOudsApi
        object Navigation : OudsPreviewableComponent {

            const val PreviewHeightDp = 840

            override val parameters: List<Any> = OudsSmallCardItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsNavigationSmallCardItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object NavigationWithRoundedCorners : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsCardItemWithRoundedCornersParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationSmallCardItemWithRoundedCorners(
                    theme = theme,
                    decoration = parameter as OudsListItemDecoration
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = darkThemeEnabled
        }

        @InternalOudsApi
        object Static : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsSmallCardItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsStaticSmallCardItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object StaticWithRoundedCorners : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsCardItemWithRoundedCornersParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsStaticSmallCardItemWithRoundedCorners(
                    theme = theme,
                    decoration = parameter as OudsListItemDecoration
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object SmallListItem {

        @InternalOudsApi
        object Navigation : OudsPreviewableComponent {

            const val PreviewHeightDp = 840

            override val parameters: List<Any> = OudsSmallListItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsNavigationSmallListItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
                )
            }
        }

        @InternalOudsApi
        object Static : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsSmallListItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsStaticSmallListItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsListItemPreviewParameter<OudsSmallListItemLeading, OudsSmallListItemTrailing>
                )
            }
        }
    }

    @InternalOudsApi
    object SuggestionChip {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsSuggestionChipPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSuggestionChip(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsSuggestionChipPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSuggestionChipWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSuggestionChipOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object SwitchItem {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1030

            override val parameters: List<Any> = OudsSwitchItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsSwitchItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsSwitchItemPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithLongDescription(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 970

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object Switch : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsSwitchPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
            PreviewOudsSwitch(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                checked = parameter as Boolean
            )
        }
    }

    @InternalOudsApi
    object Tag {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTagPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTag(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTagPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTagWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object TextArea {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1590

            override val parameters: List<Any> = OudsTextAreaPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextArea(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTextAreaPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            const val PreviewHeightDp = 1110

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextAreaWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 800

            override val parameters: List<Any> = OudsTextAreaConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextAreaConstrainedMaxWidth(theme = theme, constrainedMaxWidth = parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object AutoResize : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTextAreaAutoResizePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextAreaAutoResize(theme = theme, autoResize = parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTextAreaWithRichTextPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextAreaWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    error = parameter as Boolean
                )
            }
        }
    }

    @InternalOudsApi
    object TextInput {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1260

            override val parameters: List<Any> = OudsTextInputPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInput(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTextInputPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithRoundedCorners : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithLongLabels : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithLongLabels(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsTextInputConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputConstrainedMaxWidth(theme = theme, constrainedMaxWidth = parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object WithRichText : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTextInputWithRichTextPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithRichText(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    error = parameter as Boolean
                )
            }
        }

        @InternalOudsApi
        object WithUntintedLeadingIcon : OudsPreviewableComponent {

            const val PreviewHeightDp = 780

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithUntintedLeadingIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }

    @InternalOudsApi
    object TopAppBar {

        @InternalOudsApi
        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object CenterAligned : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCenterAlignedTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object Medium : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsMediumTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object Large : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLargeTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        @InternalOudsApi
        object WithUntintedIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTopAppBarWithUntintedIcon(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }

        @InternalOudsApi
        object Backgrounds : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTopAppBarBackgrounds(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean) = !darkThemeEnabled
        }
    }
}
