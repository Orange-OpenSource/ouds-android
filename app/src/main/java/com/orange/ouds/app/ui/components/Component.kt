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
import com.orange.ouds.app.ui.components.link.LinkDemoScreen
import com.orange.ouds.app.ui.utilities.LightDarkResourceId

val components = Component::class.sealedSubclasses.mapNotNull { it.objectInstance }

@Immutable
sealed class Component(
    @StringRes val nameRes: Int,
    val imageRes: LightDarkResourceId,
    @StringRes val descriptionRes: Int,
    val demoScreen: @Composable () -> Unit
) {

    companion object {
        fun fromId(componentId: Long) = components.firstOrNull { component -> component.id == componentId }
    }

    val id: Long = Component::class.sealedSubclasses.indexOf(this::class).toLong()

    data object Button : Component(
        R.string.app_components_button_label,
        LightDarkResourceId(R.drawable.il_components_button, R.drawable.il_components_button_dark),
        R.string.app_components_button_description_text,
        { ButtonDemoScreen() }
    )

    data object Link : Component(
        R.string.app_components_link_label,
        LightDarkResourceId(R.drawable.il_components_link, R.drawable.il_components_link),
        R.string.app_components_link_description_text,
        { LinkDemoScreen() }
    )
}