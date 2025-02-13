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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
internal fun AboutFileScreen(fileMenuItem: AboutFileMenuItem) {
    val context = LocalContext.current
    val horizontalPadding = dimensionResource(id = R.dimen.screen_horizontal_margin).value
    val verticalPadding = dimensionResource(id = R.dimen.screen_vertical_margin).value
    val isSystemInDarkTheme = isSystemInDarkTheme()
    Screen {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WebView(context).apply {
                    @SuppressLint("SetJavaScriptEnabled")
                    settings.javaScriptEnabled = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            view?.loadUrl("javascript:(function(){ document.body.style.padding = '${verticalPadding}px ${horizontalPadding}px' })();")
                            view?.injectLightDarkModeCss(isSystemInDarkTheme)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                            request?.url?.let { url ->
                                context.launchUrl(url.toString())
                            }
                            return true
                        }
                    }

                    val fileContent = resources.openRawResource(fileMenuItem.file.resource)
                        .bufferedReader()
                        .use(BufferedReader::readText)

                    val html = when (fileMenuItem.file.format) {
                        AboutFileMenuItem.File.Format.Html -> fileContent
                        AboutFileMenuItem.File.Format.Markdown -> Markdown.toHtml(fileContent)
                    }

                    // Use loadDataWithBaseURL instead of loadData otherwise CSS won't work
                    loadDataWithBaseURL(FilePath, html, "text/html; charset=UTF-8", StandardCharsets.UTF_8.name(), null)

                    setBackgroundColor(Color.TRANSPARENT)
                }
            },
            update = {
                it.injectLightDarkModeCss(isSystemInDarkTheme)
            })
    }
}
