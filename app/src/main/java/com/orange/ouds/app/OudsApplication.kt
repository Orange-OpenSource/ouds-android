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

package com.orange.ouds.app

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.orange.ouds.theme.orange.OrangeTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OudsApplication : Application() {

    companion object {
        
        var isDownloadableOrangeFontFamilyPreloaded by mutableStateOf(false)
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initializeCrashlytics()
        OrangeTheme.preloadDownloadableFontFamily(this) {
            isDownloadableOrangeFontFamilyPreloaded = true
        }
    }

    private fun initializeCrashlytics() {
        Firebase.crashlytics.isCrashlyticsCollectionEnabled = !BuildConfig.DEBUG
    }
}
