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

package com.orange.ouds.app.domain.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "datastore")

class DataStoreServiceImpl @Inject constructor(private val context: Context) : DataStoreService {

    override suspend fun putString(key: String, value: String?) = putValue(key, value)

    override suspend fun getString(key: String): String? = getValue(key)

    override suspend fun putBoolean(key: String, value: Boolean?) = putValue(key, value)

    override suspend fun getBoolean(key: String): Boolean? = getValue(key)

    private suspend inline fun <reified T> putValue(key: String, value: T) {
        context.dataStore.edit { preferences ->
            preferences[preferencesKey(key)] = value
        }
    }

    private suspend inline fun <reified T> getValue(key: String): T? {
        return context.dataStore.data.first()[preferencesKey(key)]
    }

    private inline fun <reified T> preferencesKey(key: String): Preferences.Key<T> {
        @Suppress("UNCHECKED_CAST")
        return when (T::class) {
            String::class -> stringPreferencesKey(key)
            Boolean::class -> booleanPreferencesKey(key)
            else -> error("Type of preferences key not supported.")
        } as Preferences.Key<T>
    }
}