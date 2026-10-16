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

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.database.ContentObserver
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.foundation.extensions.tryOrNull
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * @return true if the high contrast mode is enabled on the device, false otherwise
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

@Composable
internal fun areSystemAnimationsDisabled(): Boolean {
    val context = LocalContext.current
    val flow = remember(context) { context.animatorDurationScaleFlow() }
    val scale by flow.collectAsStateWithLifecycle(initialValue = 1f)

    return scale == 0f
}

private fun Context.animatorDurationScaleFlow(): Flow<Float> = callbackFlow {
    val resolver = contentResolver

    val observer = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            trySend(resolver.getAnimatorDurationScale())
        }
    }

    val uri = Settings.Global.getUriFor(Settings.Global.ANIMATOR_DURATION_SCALE)
    resolver.registerContentObserver(uri, false, observer)

    trySend(resolver.getAnimatorDurationScale())

    awaitClose {
        resolver.unregisterContentObserver(observer)
    }
}

private fun ContentResolver.getAnimatorDurationScale(): Float {
    return Settings.Global.getFloat(this, Settings.Global.ANIMATOR_DURATION_SCALE, 1f)
}
