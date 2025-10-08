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
import com.orange.ouds.core.component.OudsBadgePreviewParameter
import com.orange.ouds.core.component.OudsBadgePreviewParameterProvider
import com.orange.ouds.core.component.OudsButtonPreviewParameter
import com.orange.ouds.core.component.OudsButtonPreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemHighContrastModePreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxItemPreviewParameterProvider
import com.orange.ouds.core.component.OudsCheckboxPreviewParameter
import com.orange.ouds.core.component.OudsCheckboxPreviewParameterProvider
import com.orange.ouds.core.component.OudsColoredBoxColor
import com.orange.ouds.core.component.OudsColoredBoxPreviewParameterProvider
import com.orange.ouds.core.component.OudsDividerColor
import com.orange.ouds.core.component.OudsDividerOrientation
import com.orange.ouds.core.component.OudsDividerPreviewParameterProvider
import com.orange.ouds.core.component.OudsFilterChipPreviewParameter
import com.orange.ouds.core.component.OudsFilterChipPreviewParameterProvider
import com.orange.ouds.core.component.OudsLinkPreviewParameter
import com.orange.ouds.core.component.OudsLinkPreviewParameterProvider
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
import com.orange.ouds.core.component.PreviewOudsBadge
import com.orange.ouds.core.component.PreviewOudsButton
import com.orange.ouds.core.component.PreviewOudsButtonWithRoundedCorners
import com.orange.ouds.core.component.PreviewOudsCheckbox
import com.orange.ouds.core.component.PreviewOudsCheckboxItem
import com.orange.ouds.core.component.PreviewOudsCheckboxItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsCheckboxItemWithLongHelperText
import com.orange.ouds.core.component.PreviewOudsColoredBox
import com.orange.ouds.core.component.PreviewOudsDivider
import com.orange.ouds.core.component.PreviewOudsFilterChip
import com.orange.ouds.core.component.PreviewOudsInputTag
import com.orange.ouds.core.component.PreviewOudsLink
import com.orange.ouds.core.component.PreviewOudsLinkOnTwoLines
import com.orange.ouds.core.component.PreviewOudsRadioButton
import com.orange.ouds.core.component.PreviewOudsRadioButtonItem
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemHighContrastModeEnabled
import com.orange.ouds.core.component.PreviewOudsRadioButtonItemWithLongHelperText
import com.orange.ouds.core.component.PreviewOudsSuggestionChip
import com.orange.ouds.core.component.PreviewOudsSwitch
import com.orange.ouds.core.component.PreviewOudsSwitchItem
import com.orange.ouds.core.component.PreviewOudsSwitchItemWithLongHelperText
import com.orange.ouds.core.component.PreviewOudsTag
import com.orange.ouds.theme.OudsThemeContract

interface OudsPreviewableComponent {

    val parameters: List<Any>

    fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !highContrastModeEnabled

    @Composable
    fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?)

    object Badge : OudsPreviewableComponent {

        const val PreviewWidthDp = 420

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

    object Button {

        object Parameterized : OudsPreviewableComponent {

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

        object NonParameterized : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsButtonWithRoundedCorners(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object CheckboxItem {

        object Parameterized : OudsPreviewableComponent {

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

        object ParameterizedHighContrastMode : OudsPreviewableComponent {

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

        object NonParameterized : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsCheckboxItemWithLongHelperText(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object Checkbox : OudsPreviewableComponent {

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

    object FilterChip : OudsPreviewableComponent {

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

    object HorizontalDivider : OudsPreviewableComponent {

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

        object Parameterized : OudsPreviewableComponent {

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

        object NonParameterized : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsLinkOnTwoLines(theme = theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object RadioButtonItem {

        object Parameterized : OudsPreviewableComponent {

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

        object ParameterizedHighContrastMode : OudsPreviewableComponent {

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

        object NonParameterized : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsRadioButtonItemWithLongHelperText(theme)
            }

            override fun isPreviewAvailable(darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean) = !darkThemeEnabled && !highContrastModeEnabled
        }
    }

    object RadioButton : OudsPreviewableComponent {

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

    object SuggestionChip : OudsPreviewableComponent {

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

    object SwitchItem {

        object Parameterized : OudsPreviewableComponent {

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

        object NonParameterized : OudsPreviewableComponent {

            override val parameters: List<Any> = emptyList()

            @Composable
            override fun Preview(theme: OudsThemeContract, darkThemeEnabled: Boolean, highContrastModeEnabled: Boolean, parameter: Any?) {
                PreviewOudsSwitchItemWithLongHelperText(theme)
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

    object VerticalDivider : OudsPreviewableComponent {

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
