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

import android.content.Context
import android.view.accessibility.AccessibilityManager
import com.orange.ouds.foundation.extensions.orElse

/**
 * @return true is the high contrast mode is enabled on the device, false otherwise
 */
internal fun Context.isHighContrastModeEnabled(): Boolean {
    val accessibilityManager = this.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    // Workaround to use 'isHighTextContrastEnabled()' because it is hidden with a @UnsupportedAppUsage annotation
    return (accessibilityManager.javaClass.getMethod("isHighTextContrastEnabled").invoke(accessibilityManager) as? Boolean).orElse { false }
}