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


package com.orange.ouds.app.ui

import androidx.lifecycle.ViewModel
import com.orange.ouds.app.domain.datastore.DataStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataStoreService: DataStoreService) : ViewModel() {

    companion object {
        private const val USER_THEME_NAME_KEY = "userThemeName"
    }

    fun storeUserThemeName(themeName: String) = runBlocking {
        dataStoreService.putString(USER_THEME_NAME_KEY, themeName)
    }

    fun getUserThemeName(): String? = runBlocking { dataStoreService.getString(USER_THEME_NAME_KEY) }
}