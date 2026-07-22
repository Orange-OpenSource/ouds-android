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

package com.orange.ouds.theme.orange

/**
 * Preloads the downloadable font families for the Orange themes on iOS.
 * This is a no-op implementation since iOS already embeds Helvetica Neue by default.
 *
 * @param context Unused on iOS.
 * @param downloadableFontFamilies The downloadable font families to preload (ignored on iOS).
 * @param onComplete A callback that is called immediately with success=true.
 */
actual fun preloadDownloadableFontFamilies(
    context: Any,
    downloadableFontFamilies: List<OrangeDownloadableFontFamily>,
    onComplete: (success: Boolean) -> Unit
) {
    // iOS embeds Helvetica Neue by default, no need to download fonts
    // Simply call the callback with success
    onComplete(true)
}
