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

package com.orange.ouds.app.ui.components

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.components.badge.BadgeDemoScreen
import com.orange.ouds.app.ui.components.button.ButtonDemoScreen
import com.orange.ouds.app.ui.components.checkbox.CheckboxDemoScreen
import com.orange.ouds.app.ui.components.checkbox.CheckboxItemDemoScreen
import com.orange.ouds.app.ui.components.chip.FilterChipDemoScreen
import com.orange.ouds.app.ui.components.chip.SuggestionChipDemoScreen
import com.orange.ouds.app.ui.components.coloredbackground.ColoredBackgroundDemoScreen
import com.orange.ouds.app.ui.components.divider.DividerDemoScreen
import com.orange.ouds.app.ui.components.link.LinkDemoScreen
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonDemoScreen
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonItemDemoScreen
import com.orange.ouds.app.ui.components.switch.SwitchDemoScreen
import com.orange.ouds.app.ui.components.switch.SwitchItemDemoScreen
import com.orange.ouds.app.ui.utilities.LightDarkResourceId
import com.orange.ouds.app.ui.utilities.previewCompatibleClass

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class Component(
    @StringRes val nameRes: Int,
    val imageRes: LightDarkResourceId,
    @StringRes val descriptionRes: Int,
    val variants: List<Variant> = emptyList(),
    val demoScreen: (@Composable () -> Unit)? = null
) {

    companion object {
        fun fromId(componentId: Long) = components.firstOrNull { component -> component.id == componentId }
    }

    val id: Long = Component::class.previewCompatibleClass.sealedSubclasses.indexOf(this::class).toLong()

    data object Badge : Component(
        R.string.app_components_badge_label,
        LightDarkResourceId(R.drawable.il_components_badge, R.drawable.il_components_badge_dark),
        R.string.app_components_badge_description_text,
        demoScreen = { BadgeDemoScreen() }
    )

    data object Button : Component(
        R.string.app_components_button_label,
        LightDarkResourceId(R.drawable.il_components_button, R.drawable.il_components_button_dark),
        R.string.app_components_button_description_text,
        demoScreen = { ButtonDemoScreen() }
    )

    data object Checkbox : Component(
        R.string.app_components_checkbox_label,
        LightDarkResourceId(R.drawable.il_components_checkbox, R.drawable.il_components_checkbox_dark),
        R.string.app_components_checkbox_description_text,
        listOf(Variant.Checkbox, Variant.CheckboxItem, Variant.IndeterminateCheckbox, Variant.IndeterminateCheckboxItem)
    )

    data object Chip : Component(
        R.string.app_components_chip_label,
        LightDarkResourceId(R.drawable.il_components_chip, R.drawable.il_components_chip_dark),
        R.string.app_components_chip_description_text,
        listOf(Variant.FilterChip, Variant.SuggestionChip)
    )

    data object ColoredBackground : Component(
        R.string.app_components_coloredBackground_label,
        LightDarkResourceId(R.drawable.il_components_colored_background, R.drawable.il_components_colored_background),
        R.string.app_components_coloredBackground_description_text,
        demoScreen = { ColoredBackgroundDemoScreen() }
    )

    data object Divider : Component(
        R.string.app_components_divider_label,
        LightDarkResourceId(R.drawable.il_components_divider, R.drawable.il_components_divider_dark),
        R.string.app_components_divider_description_text,
        listOf(Variant.HorizontalDivider, Variant.VerticalDivider)
    )

    data object Link : Component(
        R.string.app_components_link_label,
        LightDarkResourceId(R.drawable.il_components_link, R.drawable.il_components_link_dark),
        R.string.app_components_link_description_text,
        demoScreen = { LinkDemoScreen() }
    )

    data object RadioButton : Component(
        R.string.app_components_radioButton_label,
        LightDarkResourceId(R.drawable.il_components_radiobutton, R.drawable.il_components_radiobutton_dark),
        R.string.app_components_radioButton_description_text,
        listOf(Variant.RadioButton, Variant.RadioButtonItem)
    )

    data object Switch : Component(
        R.string.app_components_switch_label,
        LightDarkResourceId(R.drawable.il_components_switch, R.drawable.il_components_switch_dark),
        R.string.app_components_switch_description_text,
        listOf(Variant.Switch, Variant.SwitchItem)
    )
}

sealed class Variant(
    @StringRes val nameRes: Int,
    val demoScreen: @Composable () -> Unit
) {

    companion object {
        fun fromId(variantId: Long?) = components.flatMap { it.variants }.firstOrNull { it.id == variantId }
    }

    val id: Long = Variant::class.previewCompatibleClass.sealedSubclasses.indexOf(this::class).toLong()

    // Checkbox
    data object Checkbox : Variant(R.string.app_components_checkbox_checkbox_label, { CheckboxDemoScreen() })
    data object CheckboxItem : Variant(R.string.app_components_checkbox_checkboxItem_label, { CheckboxItemDemoScreen() })
    data object IndeterminateCheckbox : Variant(R.string.app_components_checkbox_indeterminateCheckbox_label, { CheckboxDemoScreen(indeterminate = true) })
    data object IndeterminateCheckboxItem :
        Variant(R.string.app_components_checkbox_indeterminateCheckboxItem_label, { CheckboxItemDemoScreen(indeterminate = true) })

    // Chip
    data object FilterChip : Variant(R.string.app_components_chip_filterChip_label, { FilterChipDemoScreen() })
    data object SuggestionChip : Variant(R.string.app_components_chip_suggestionChip_label, { SuggestionChipDemoScreen() })

    // Divider
    data object HorizontalDivider : Variant(R.string.app_components_divider_horizontalDivider_label, { DividerDemoScreen() })
    data object VerticalDivider : Variant(R.string.app_components_divider_verticalDivider_label, { DividerDemoScreen(vertical = true) })

    // Radio button
    data object RadioButton : Variant(R.string.app_components_radioButton_radioButton_label, { RadioButtonDemoScreen() })
    data object RadioButtonItem : Variant(R.string.app_components_radioButton_radioButtonItem_label, { RadioButtonItemDemoScreen() })

    // Switch
    data object Switch : Variant(R.string.app_components_switch_switch_label, { SwitchDemoScreen() })
    data object SwitchItem : Variant(R.string.app_components_switch_switchItem_label, { SwitchItemDemoScreen() })
}