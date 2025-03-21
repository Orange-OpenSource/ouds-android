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

package com.orange.ouds.core.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.view.accessibility.AccessibilityManager
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.tryOrNull

/**
 * @return true is the high contrast mode is enabled on the device, false otherwise
 */
@SuppressLint("DiscouragedPrivateApi", "PrivateApi")
internal fun Context.isHighContrastModeEnabled(): Boolean {
    val accessibilityManager = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager

    return with(accessibilityManager) {
        // Workaround to access mIsHighTextContrastEnabled field because it is hidden with a @UnsupportedAppUsage annotation
        // On some OS versions this field is named mIsHighContrastTextEnabled
        // TODO Use high text contrast APIs once API level 36 is available
        tryOrNull { javaClass.getDeclaredField("mIsHighTextContrastEnabled") }
            .orElse { tryOrNull { javaClass.getDeclaredField("mIsHighContrastTextEnabled") } }
            ?.also { it.isAccessible = true }
            ?.getBoolean(this)
            .orElse { false }
    }
}