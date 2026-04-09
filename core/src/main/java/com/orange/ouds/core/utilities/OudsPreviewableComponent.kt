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
import com.orange.ouds.core.component.OudsAlertMessagePreviewParameter
import com.orange.ouds.core.component.OudsAlertMessagePreviewParameterProvider
import com.orange.ouds.core.component.OudsBadgePreviewParameter
import com.orange.ouds.core.component.OudsBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsBadgeWithIconPreviewParameter
import com.orange.ouds.core.component.OudsBadgeWithIconPreviewParameterProvider
import com.orange.ouds.core.component.OudsBulletListPreviewParameter
import com.orange.ouds.core.component.OudsBulletListPreviewParameterProvider
import com.orange.ouds.core.component.OudsButtonPreviewParameter
import com.orange.ouds.core.component.OudsButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsButtonWithIconBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxPreviewParameterProvider
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.OudsColoredBoxPreviewParameterProvider
import com.orange.ouds.core.component.OudsControlItemConstrainedMaxWidthPreviewParameterProvider
import com.orange.ouds.core.component.OudsDividerColor
import com.orange.ouds.core.component.OudsDividerOrientation
import com.orange.ouds.core.component.OudsDividerPreviewParameterProvider
import com.orange.ouds.core.component.OudsFilterChipHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsFilterChipPreviewParameter
import com.orange.ouds.core.component.OudsFilterChipPreviewParameterProvider
import com.orange.ouds.core.component.OudsFloatingActionButtonAppearance
import com.orange.ouds.core.component.OudsFloatingActionButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsInlineAlertPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinkPreviewParameter
import com.orange.ouds.core.component.OudsLinkPreviewParameterProvider
import com.orange.ouds.core.component.OudsNavigationBarItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsNavigationBarPreviewParameterProvider
import com.orange.ouds.core.component.OudsPasswordInputPreviewParameter
import com.orange.ouds.core.component.OudsPasswordInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsPinCodeInputPreviewParameter
import com.orange.ouds.core.component.OudsPinCodeInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsPinCodeInputWithRoundedCornersPreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameter
import com.orange.ouds.core.component.OudsRadioButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameter
import com.orange.ouds.core.component.OudsSuggestionChipPreviewParameterProvider
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameter
import com.orange.ouds.core.component.OudsSwitchItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsSwitchPreviewParameterProvider
import com.orange.ouds.core.component.OudsTagPreviewParameter
import com.orange.ouds.core.component.OudsTagPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextInputConstrainedMaxWidthPreviewParameterProvider
import com.orange.ouds.core.component.OudsTextInputPreviewParameter
import com.orange.ouds.core.component.OudsTextInputPreviewParameterProvider
import com.orange.ouds.core.component.OudsTopAppBarPreviewParameter
import com.orange.ouds.core.component.OudsTopAppBarPreviewParameterProvider
import com.orange.ouds.core.component.PreviewOudsAlertMessage
import com.orange.ouds.core.component.PreviewOudsBadge
import com.orange.ouds.core.component.PreviewOudsBadgeWithIcon
import com.orange.ouds.core.component.PreviewOudsBulletList
import com.orange.ouds.core.component.PreviewOudsBulletListRtl
import com.orange.ouds.core.component.PreviewOudsButton
import com.orange.ouds.core.component.PreviewOudsButtonOnTwoLines
import com.orange.ouds.core.component.PreviewOudsButtonWithIconBadge
import com.orange.ouds.core.component.PreviewOudsButtonWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsCenterAlignedTopAppBar
import com.orange.ouds.core.component.PreviewOudsCheckbox
import com.orange.ouds.core.component.PreviewOudsCheckboxItem
import com.orange.ouds.core.component.PreviewOudsCheckboxItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsCheckboxItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithLongDescription
import com.orange.ouds.core.component.PreviewOudsColoredBox
import com.orange.ouds.core.component.PreviewOudsDivider
import com.orange.ouds.core.component.PreviewOudsExtendedFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsFilterChip
import com.orange.ouds.core.component.PreviewOudsFilterChipHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsInlineAlert
import com.orange.ouds.core.component.PreviewOudsInputTag
import com.orange.ouds.core.component.PreviewOudsLargeFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsLargeTopAppBar
import com.orange.ouds.core.component.PreviewOudsLink
import com.orange.ouds.core.component.PreviewOudsLinkOnTwoLines
import com.orange.ouds.core.component.PreviewOudsMediumTopAppBar
import com.orange.ouds.core.component.PreviewOudsNavigationBar
import com.orange.ouds.core.component.PreviewOudsNavigationBarItem
import com.orange.ouds.core.component.PreviewOudsPasswordInput
import com.orange.ouds.core.component.PreviewOudsPinCodeInput
import com.orange.ouds.core.component.PreviewOudsPinCodeInputWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsRadioButton
import com.orange.ouds.core.component.PreviewOudsRadioButtonItem
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithDescriptionText
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsSmallFloatingActionButton
import com.orange.ouds.core.component.PreviewOudsSuggestionChip
import com.orange.ouds.core.component.PreviewOudsSuggestionChipHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsSwitch
import com.orange.ouds.core.component.PreviewOudsSwitchItem
import com.orange.ouds.core.component.PreviewOudsSwitchItemConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithEdgeToEdgeDisabled
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithLongDescription
import com.orange.ouds.core.component.PreviewOudsTag
import com.orange.ouds.core.component.PreviewOudsTextInput
import com.orange.ouds.core.component.PreviewOudsTextInputConstrainedMaxWidth
import com.orange.ouds.core.component.PreviewOudsTextInputWithLongLabels
import com.orange.ouds.core.component.PreviewOudsTextInputWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsTopAppBar
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

    fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !highContrastModeEnabled

    @Composable
    fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?)

    object AlertMessage : OudsPreviewableComponent {

        const val PreviewHeightDp = 1470

        override val parameters: List<Any> = OudsAlertMessagePreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsAlertMessage(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter as OudsAlertMessagePreviewParameter
            )
        }
    }

    object Badge {

        const val PreviewWidthDp = 420

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsBadgePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBadge(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBadgePreviewParameter
                )
            }
        }

        object WithIcon : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsBadgeWithIconPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBadgeWithIcon(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBadgeWithIconPreviewParameter
                )
            }
        }
    }

    object BulletList {

        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 730

            override val parameters: List<Any> = OudsBulletListPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletList(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBulletListPreviewParameter
                )
            }
        }

        object Rtl : OudsPreviewableComponent {

            const val PreviewHeightDp = 730

            override val parameters: List<Any> = OudsBulletListPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsBulletListRtl(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsBulletListPreviewParameter
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object Button {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsButtonPreviewParameter
                )
            }
        }

        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object WithIconBadge : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsButtonWithIconBadgePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithIconBadge(
                    theme = theme,
                    count = parameter as Int
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object CheckboxItem {

        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 880

            override val parameters: List<Any> = OudsCheckboxItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsCheckboxItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCheckboxItemPreviewParameter
                )
            }
        }

        object HighContrastModeEnabled : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsCheckboxItemHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsCheckboxItemHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsCheckboxItemHighContrastModePreviewParameter
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = highContrastModeEnabled
        }

        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithLongDescription(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 790

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object Checkbox : OudsPreviewableComponent {

        const val PreviewWidthDp = 410

        override val parameters: List<Any> = OudsCheckboxPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsCheckbox(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                highContrastModeEnabled = highContrastModeEnabled,
                parameter = parameter as OudsCheckboxPreviewParameter
            )
        }

        override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = true
    }

    object ColoredBox : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsColoredBoxPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsColoredBox(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                color = parameter as OudsColoredBoxColor
            )
        }
    }

    object Divider {

        object Horizontal : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsDividerPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsDivider(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    orientation = OudsDividerOrientation.Horizontal,
                    color = parameter as OudsDividerColor
                )
            }
        }

        object Vertical : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsDividerPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsDivider(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    orientation = OudsDividerOrientation.Vertical,
                    color = parameter as OudsDividerColor
                )
            }
        }
    }

    object FilterChip {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFilterChipPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFilterChip(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsFilterChipPreviewParameter
                )
            }
        }

        object HighContrastModeEnabled : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFilterChipHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsFilterChipHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    selected = parameter as Boolean
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = highContrastModeEnabled
        }
    }

    object FloatingActionButton {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        object Small : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSmallFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        object Large : OudsPreviewableComponent {

            const val PreviewWidthDp = 480

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLargeFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }

        object Extended : OudsPreviewableComponent {

            const val PreviewWidthDp = 700

            override val parameters: List<Any> = OudsFloatingActionButtonPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsExtendedFloatingActionButton(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    appearance = parameter as OudsFloatingActionButtonAppearance
                )
            }
        }
    }

    object InlineAlert : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsInlineAlertPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsInlineAlert(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                label = parameter as String
            )
        }
    }

    object InputTag : OudsPreviewableComponent {

        override val parameters: List<Any> = emptyList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsInputTag(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled
            )
        }
    }

    object Link {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsLinkPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLink(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsLinkPreviewParameter
                )
            }
        }

        object OnTwoLines : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinkOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object NavigationBar {

        object Default : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    itemCount = parameter as Int,
                    windowWidthSizeClass = WindowWidthSizeClass.COMPACT
                )
            }
        }

        object WithHorizontalItems : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsNavigationBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    itemCount = parameter as Int,
                    windowWidthSizeClass = WindowWidthSizeClass.MEDIUM
                )
            }
        }
    }

    object NavigationBarItem {

        const val PreviewWidthDp = 400

        object Default : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBarItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    selected = parameter as Boolean,
                    windowWidthSizeClass = WindowWidthSizeClass.COMPACT
                )
            }
        }

        object Horizontal : OudsPreviewableComponent {
            override val parameters: List<Any> = OudsNavigationBarItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsNavigationBarItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    selected = parameter as Boolean,
                    windowWidthSizeClass = WindowWidthSizeClass.MEDIUM
                )
            }
        }
    }

    object PasswordInput : OudsPreviewableComponent {

        const val PreviewHeightDp = 840

        override val parameters: List<Any> = OudsPasswordInputPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsPasswordInput(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter as OudsPasswordInputPreviewParameter
            )
        }
    }

    object PinCodeInput {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPinCodeInputPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPinCodeInput(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsPinCodeInputPreviewParameter
                )
            }
        }

        object WithRoundedCorners : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsPinCodeInputWithRoundedCornersPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsPinCodeInputWithRoundedCorners(
                    theme = theme,
                    outlined = parameter as Boolean
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object RadioButtonItem {

        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 880

            override val parameters: List<Any> = OudsRadioButtonItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsRadioButtonItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsRadioButtonItemPreviewParameter
                )
            }
        }

        object HighContrastModeEnabled : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsRadioButtonItemHighContrastModePreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsRadioButtonItemHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsRadioButtonItemHighContrastModePreviewParameter
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = highContrastModeEnabled
        }

        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithDescriptionText(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 880

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

    }

    object RadioButton : OudsPreviewableComponent {

        const val PreviewWidthDp = 410

        override val parameters: List<Any> = OudsRadioButtonPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsRadioButton(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                highContrastModeEnabled = highContrastModeEnabled,
                parameter = parameter as OudsRadioButtonPreviewParameter
            )
        }

        override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = true
    }

    object SuggestionChip {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsSuggestionChipPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSuggestionChip(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsSuggestionChipPreviewParameter
                )
            }
        }

        object HighContrastModeEnabled : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSuggestionChipHighContrastModeEnabled(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled
                )
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = highContrastModeEnabled
        }
    }

    object SwitchItem {

        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 880

            override val parameters: List<Any> = OudsSwitchItemPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                @Suppress("UNCHECKED_CAST")
                PreviewOudsSwitchItem(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsSwitchItemPreviewParameter
                )
            }
        }

        object WithLongDescription : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithLongDescription(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object WithEdgeToEdgeDisabled : OudsPreviewableComponent {

            const val PreviewHeightDp = 820

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithEdgeToEdgeDisabled(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsControlItemConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemConstrainedMaxWidth(theme, parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object Switch : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsSwitchPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsSwitch(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                checked = parameter as Boolean
            )
        }
    }

    object Tag : OudsPreviewableComponent {

        override val parameters: List<Any> = OudsTagPreviewParameterProvider().values.toList()

        @Composable
        override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
            PreviewOudsTag(
                theme = theme,
                darkThemeEnabled = darkThemeEnabled,
                parameter = parameter as OudsTagPreviewParameter
            )
        }
    }

    object TextInput {


        object Default : OudsPreviewableComponent {

            const val PreviewHeightDp = 1100

            override val parameters: List<Any> = OudsTextInputPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInput(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTextInputPreviewParameter
                )
            }
        }

        object WithRoundedCorners : OudsPreviewableComponent {

            const val PreviewHeightDp = 670

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object WithLongLabels : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputWithLongLabels(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }

        object ConstrainedMaxWidth : OudsPreviewableComponent {

            const val PreviewWidthDp = 600

            override val parameters: List<Any> = OudsTextInputConstrainedMaxWidthPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTextInputConstrainedMaxWidth(theme = theme, constrainedMaxWidth = parameter as Boolean)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object TopAppBar {

        object Default : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        object CenterAligned : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCenterAlignedTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        object Medium : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsMediumTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }

        object Large : OudsPreviewableComponent {

            override val parameters: List<Any> = OudsTopAppBarPreviewParameterProvider().values.toList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLargeTopAppBar(
                    theme = theme,
                    darkThemeEnabled = darkThemeEnabled,
                    parameter = parameter as OudsTopAppBarPreviewParameter
                )
            }
        }
    }
}
