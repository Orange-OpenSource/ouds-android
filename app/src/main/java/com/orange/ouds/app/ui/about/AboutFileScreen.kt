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

package com.orange.ouds.app.ui.about

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import com.orange.ouds.app.R
import com.orange.ouds.app.ui.utilities.composable.Screen
import com.orange.ouds.app.ui.utilities.injectLightDarkModeCss
import com.orange.ouds.app.ui.utilities.launchUrl
import java.io.BufferedReader
import java.nio.charset.StandardCharsets

private const val FileResourceDir = "raw"
private const val FilePath = "file:///android_res/$FileResourceDir/"

@Composable
internal fun AboutFileScreen(@RawRes fileRes: Int, darkModeEnabled: Boolean) {
    val context = LocalContext.current
    val horizontalPadding = dimensionResource(id = R.dimen.screen_horizontal_margin).value
    val verticalPadding = dimensionResource(id = R.dimen.screen_vertical_margin).value
    Screen {
        AndroidView(
            factory = {
                WebView(context).apply {
                    @SuppressLint("SetJavaScriptEnabled")
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            view?.loadUrl("javascript:(function(){ document.body.style.padding = '${verticalPadding}px ${horizontalPadding}px' })();")
                            view?.injectLightDarkModeCss(darkModeEnabled)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            request?.url?.let { url ->
                                context.launchUrl(url.toString())
                            }
                            return true
                        }
                    }

                    val fileContent = resources.openRawResource(fileRes)
                        .bufferedReader()
                        .use(BufferedReader::readText)

                    // Use loadDataWithBaseURL instead of loadData otherwise CSS won't work
                    loadDataWithBaseURL(FilePath, fileContent, "text/html; charset=UTF-8", StandardCharsets.UTF_8.name(), null)

                    setBackgroundColor(Color.TRANSPARENT)
                }
            },
            update = {
                it.injectLightDarkModeCss(darkModeEnabled)
            })
    }
}
