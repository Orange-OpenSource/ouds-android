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

package com.orange.ouds.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import com.orange.ouds.app.R
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.sosh.SoshTheme
import com.orange.ouds.theme.wireframe.WireframeTheme
import kotlin.reflect.KProperty1

val LocalThemeDrawableResources = staticCompositionLocalOf<ThemeDrawableResources> { error("CompositionLocal LocalThemeDrawableResources not present") }

class ThemeDrawableResources(val theme: OudsThemeContract) {

    val call: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_call
            is SoshTheme -> R.drawable.ic_sosh_call
            is WireframeTheme -> R.drawable.ic_wireframe_call
            else -> error(ThemeDrawableResources::call)
        }

    val filters: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_filters
            is SoshTheme -> R.drawable.ic_sosh_filters
            is WireframeTheme -> R.drawable.ic_wireframe_filters
            else -> error(ThemeDrawableResources::filters)
        }

    val formChevronDown: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_form_chevron_down
            is SoshTheme -> R.drawable.ic_sosh_form_chevron_down
            is WireframeTheme -> R.drawable.ic_wireframe_form_chevron_down
            else -> error(ThemeDrawableResources::formChevronDown)
        }

    val heartEmpty: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_heart_empty
            is SoshTheme -> R.drawable.ic_sosh_heart_empty
            is WireframeTheme -> R.drawable.ic_wireframe_heart_empty
            else -> error(ThemeDrawableResources::heartEmpty)
        }

    val info: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_info
            is SoshTheme -> R.drawable.ic_sosh_info
            is WireframeTheme -> R.drawable.ic_wireframe_info
            else -> error(ThemeDrawableResources::info)
        }

    val menuGrid: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_menu_grid
            is SoshTheme -> R.drawable.ic_sosh_menu_grid
            is WireframeTheme -> R.drawable.ic_wireframe_menu_grid
            else -> error(ThemeDrawableResources::menuGrid)
        }

    val palette: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_palette
            is SoshTheme -> R.drawable.ic_sosh_palette
            is WireframeTheme -> R.drawable.ic_wireframe_palette
            else -> error(ThemeDrawableResources::palette)
        }

    val smsMessage: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_sms_message
            is SoshTheme -> R.drawable.ic_sosh_sms_message
            is WireframeTheme -> R.drawable.ic_wireframe_sms_message
            else -> error(ThemeDrawableResources::smsMessage)
        }

    private fun error(property: KProperty1<ThemeDrawableResources, Int>): Nothing {
        error("${property.name} drawable not found for theme ${theme.name}.")
    }
}
