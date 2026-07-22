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

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Build
import androidx.compose.ui.text.font.AndroidFont
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation.Settings
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat

/**
 * Preloads the downloadable font families for the Orange themes on Android.
 * Downloads fonts using Android Downloadable Fonts feature.
 *
 * @param context The Android context.
 * @param downloadableFontFamilies The downloadable font families to preload.
 * @param onComplete A callback that is called when the font families are fully loaded.
 */
actual fun preloadDownloadableFontFamilies(
    context: Any,
    downloadableFontFamilies: List<OrangeDownloadableFontFamily>,
    onComplete: (success: Boolean) -> Unit
) {
    val androidContext = context as Context
    
    val downloadableFontFamiliesToPreload = downloadableFontFamilies.filter { downloadableFontFamily ->
        when (downloadableFontFamily) {
            is OrangeHelveticaNeueLatin.Downloadable -> OrangeFontFamily.downloadedLatinFontFamily == null
            is OrangeHelveticaNeueArabic.Downloadable -> OrangeFontFamily.downloadedArabicFontFamily == null
        }
    }

    if (downloadableFontFamiliesToPreload.isEmpty()) {
        onComplete(true)
    } else {
        var preloadedDownloadableFontFamilyCount = 0

        // Font requests require the list of sets of hashes for the certificates the provider is signed with.
        // As OrangeFontProvider is embedded in the app, it is signed with the app certificate.
        // That is why we can retrieve the certificate using methods on package manager.
        val certificates = try {
            @Suppress("DEPRECATION")
            val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) PackageManager.GET_SIGNING_CERTIFICATES else PackageManager.GET_SIGNATURES
            val packageInfo = androidContext.packageManager.getPackageInfo(androidContext.packageName, flags)
            val signatures = with(packageInfo) {
                @Suppress("DEPRECATION")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) signingInfo?.apkContentsSigners else signatures
            }
            listOf(signatures.orEmpty().map { it.toByteArray() })
        } catch (_: Exception) {
            emptyList()
        }

        var success = true
        val callbackExecutor = ContextCompat.getMainExecutor(androidContext)
        downloadableFontFamiliesToPreload.forEach { downloadableFontFamily ->
            val fontWeights = when (downloadableFontFamily) {
                is OrangeHelveticaNeueLatin.Downloadable -> listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.Bold)
                is OrangeHelveticaNeueArabic.Downloadable -> listOf(FontWeight.Light, FontWeight.Normal, FontWeight.Bold)
            }
            val script = when (downloadableFontFamily) {
                is OrangeHelveticaNeueLatin.Downloadable -> OrangeFontProvider.QUERY_SCRIPT_PARAMETER_VALUE_LATIN
                is OrangeHelveticaNeueArabic.Downloadable -> OrangeFontProvider.QUERY_SCRIPT_PARAMETER_VALUE_ARABIC
            }
            val typefaces = mutableMapOf<FontWeight, Typeface?>()

            fontWeights.forEach { fontWeight ->
                val query =
                    "${OrangeFontProvider.QUERY_WEIGHT_PARAMETER_KEY}=${fontWeight.weight}&${OrangeFontProvider.QUERY_SCRIPT_PARAMETER_KEY}=$script"
                val fontRequest = FontRequest(OrangeFontProvider.AUTHORITY, androidContext.packageName, query, certificates)

                val callback = object : FontsContractCompat.FontRequestCallback() {

                    override fun onTypefaceRetrieved(typeface: Typeface?) {
                        onTypefaceRequestComplete(fontWeight, typeface)
                    }

                    override fun onTypefaceRequestFailed(reason: Int) {
                        onTypefaceRequestComplete(fontWeight, null)
                    }

                    private fun onTypefaceRequestComplete(fontWeight: FontWeight, typeface: Typeface?) {
                        typefaces[fontWeight] = typeface
                        if (typefaces.size == fontWeights.size) {
                            if (typefaces.values.any { it == null }) {
                                success = false
                            } else {
                                val fonts = typefaces.mapNotNull { (fontWeight, typeface) ->
                                    typeface?.let { Font(fontWeight, typeface) }
                                }
                                val downloadedFontFamily = FontFamily(fonts)
                                when (downloadableFontFamily) {
                                    is OrangeHelveticaNeueLatin.Downloadable -> OrangeFontFamily.downloadedLatinFontFamily = downloadedFontFamily
                                    is OrangeHelveticaNeueArabic.Downloadable -> OrangeFontFamily.downloadedArabicFontFamily = downloadedFontFamily
                                }
                            }
                            preloadedDownloadableFontFamilyCount++
                            if (downloadableFontFamiliesToPreload.size == preloadedDownloadableFontFamilyCount) {
                                onComplete(success)
                            }
                        }
                    }

                    private fun Font(weight: FontWeight, typeface: Typeface, style: FontStyle = FontStyle.Normal): Font {
                        val typefaceLoader = object : AndroidFont.TypefaceLoader {
                            override fun loadBlocking(context: Context, font: AndroidFont): Typeface? = typeface
                            override suspend fun awaitLoad(context: Context, font: AndroidFont): Typeface? = typeface
                        }

                        return object : AndroidFont(FontLoadingStrategy.Blocking, typefaceLoader, Settings()) {
                            override val weight: FontWeight = weight
                            override val style: FontStyle = style
                        }
                    }

                }

                val style = if (fontWeight.weight >= FontWeight.Bold.weight) Typeface.BOLD else Typeface.NORMAL
                FontsContractCompat.requestFont(androidContext, fontRequest, style, null, callbackExecutor, callback)
            }
        }
    }
}
