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
import com.orange.ouds.app.ui.components.button.ButtonDemoScreen
import com.orange.ouds.app.ui.components.checkbox.CheckboxDemoScreen
import com.orange.ouds.app.ui.components.checkbox.CheckboxItemDemoScreen
import com.orange.ouds.app.ui.components.link.LinkDemoScreen
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonDemoScreen
import com.orange.ouds.app.ui.components.radiobutton.RadioButtonItemDemoScreen
import com.orange.ouds.app.ui.utilities.LightDarkResourceId

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

    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

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
}

sealed class Variant(
    @StringRes val nameRes: Int,
    val demoScreen: @Composable () -> Unit
) {

    companion object {
        fun fromId(variantId: Long?) = components.flatMap { it.variants }.firstOrNull { it.id == variantId }
    }

    val id: Long = Variant::class.sealedSubclasses.indexOf(this::class).toLong()

    data object Checkbox : Variant(R.string.app_components_checkbox_checkbox_label, { CheckboxDemoScreen() })
    data object CheckboxItem : Variant(R.string.app_components_checkbox_checkboxItem_label, { CheckboxItemDemoScreen() })
    data object IndeterminateCheckbox : Variant(R.string.app_components_checkbox_indeterminateCheckbox_label, { CheckboxDemoScreen(indeterminate = true) })
    data object IndeterminateCheckboxItem :
        Variant(R.string.app_components_checkbox_indeterminateCheckboxItem_label, { CheckboxItemDemoScreen(indeterminate = true) })

    data object RadioButton : Variant(R.string.app_components_radioButton_radioButton_label, { RadioButtonDemoScreen() })
    data object RadioButtonItem : Variant(R.string.app_components_radioButton_radioButtonItem_label, { RadioButtonItemDemoScreen() })
}