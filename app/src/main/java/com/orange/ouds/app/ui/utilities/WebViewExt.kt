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

import android.webkit.WebView
import androidx.annotation.RawRes
import com.orange.ouds.app.R
import com.orange.ouds.foundation.extensions.contentAsString

internal fun WebView.injectLightDarkModeCss(isDarkModeEnabled: Boolean) {
    injectCss(R.raw.base_style)
    val css = if (isDarkModeEnabled) R.raw.dark_style else R.raw.light_style
    injectCss(css)
}

private fun WebView.injectCss(@RawRes cssResource: Int) {
    val injectCssFunction = context.resources.openRawResource(R.raw.inject_css).contentAsString().orEmpty()
    val css = context.resources.openRawResource(cssResource).contentAsString().orEmpty()
    val javascript = String.format(injectCssFunction, css.trim { it <= ' ' })
    val code = "javascript:(function() { $javascript })()"
    loadUrl(code)
}