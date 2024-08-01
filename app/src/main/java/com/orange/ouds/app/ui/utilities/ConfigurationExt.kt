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

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES

var Configuration.isDarkModeEnabled: Boolean
    get() {
        return uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }
    set(value) {
        val uiModeNight = if (value) UI_MODE_NIGHT_YES else UI_MODE_NIGHT_NO
        uiMode = (uiMode and UI_MODE_NIGHT_MASK.inv()) or uiModeNight
    }