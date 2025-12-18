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

    val avatar: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_avatar
            is SoshTheme -> R.drawable.ic_sosh_avatar
            is WireframeTheme -> R.drawable.ic_wireframe_avatar
            else -> error(::avatar)
        }

    val call: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_call
            is SoshTheme -> R.drawable.ic_sosh_call
            is WireframeTheme -> R.drawable.ic_wireframe_call
            else -> error(::call)
        }

    val filters: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_filters
            is SoshTheme -> R.drawable.ic_sosh_filters
            is WireframeTheme -> R.drawable.ic_wireframe_filters
            else -> error(::filters)
        }

    val formChevronDown: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_form_chevron_down
            is SoshTheme -> R.drawable.ic_sosh_form_chevron_down
            is WireframeTheme -> R.drawable.ic_wireframe_form_chevron_down
            else -> error(::formChevronDown)
        }

    val home: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_home
            is SoshTheme -> R.drawable.ic_sosh_home
            is WireframeTheme -> R.drawable.ic_wireframe_home
            else -> error(::home)
        }

    val info: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_info
            is SoshTheme -> R.drawable.ic_sosh_info
            is WireframeTheme -> R.drawable.ic_wireframe_info
            else -> error(::info)
        }

    val menuGrid: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_menu_grid
            is SoshTheme -> R.drawable.ic_sosh_menu_grid
            is WireframeTheme -> R.drawable.ic_wireframe_menu_grid
            else -> error(::menuGrid)
        }

    val notificationAlert: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_notification_alert
            is SoshTheme -> R.drawable.ic_sosh_notification_alert
            is WireframeTheme -> R.drawable.ic_wireframe_notification_alert
            else -> error(::notificationAlert)
        }

    val palette: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_palette
            is SoshTheme -> R.drawable.ic_sosh_palette
            is WireframeTheme -> R.drawable.ic_wireframe_palette
            else -> error(::palette)
        }

    val settings: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_settings
            is SoshTheme -> R.drawable.ic_sosh_settings
            is WireframeTheme -> R.drawable.ic_wireframe_settings
            else -> error(::settings)
        }

    val shop: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_shop
            is SoshTheme -> R.drawable.ic_sosh_shop
            is WireframeTheme -> R.drawable.ic_wireframe_shop
            else -> error(::shop)
        }

    val smsMessage: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_sms_message
            is SoshTheme -> R.drawable.ic_sosh_sms_message
            is WireframeTheme -> R.drawable.ic_wireframe_sms_message
            else -> error(::smsMessage)
        }

    val tipsAndTricks: Int
        @DrawableRes
        get() = when (theme) {
            is OrangeTheme -> R.drawable.ic_orange_tips_and_tricks
            is SoshTheme -> R.drawable.ic_sosh_tips_and_tricks
            is WireframeTheme -> R.drawable.ic_wireframe_tips_and_tricks
            else -> error(ThemeDrawableResources::tipsAndTricks)
        }

    private fun error(property: KProperty1<ThemeDrawableResources, Int>): Nothing {
        error("${property.name} drawable not found for theme ${theme.name}.")
    }
}

fun interface ThemeDrawableResourceProvider {

    @DrawableRes
    fun getResource(themeDrawableResources: ThemeDrawableResources): Int
}
