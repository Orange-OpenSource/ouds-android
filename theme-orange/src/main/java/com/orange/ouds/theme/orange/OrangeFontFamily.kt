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
import androidx.annotation.FontRes
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
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.foundation.extensions.orElse
import java.util.Locale

/**
 * The font family to use for the Orange themes.
 */
// The primary constructor should theoretically contain nullable Latin and Arabic parameters but this leads to a platform declaration clash
// That's why we use a private empty primary constructor and set Latin and Arabic properties later on
class OrangeFontFamily private constructor() {

    companion object {

        private var downloadedLatinFontFamily: FontFamily? = null
        private var downloadedArabicFontFamily: FontFamily? = null

        /**
         * Preloads the downloadable font families for the Orange themes.
         * Call this method if either the [OrangeFontFamily.latin] or [OrangeFontFamily.arabic] property of the [OrangeFontFamily] used to create the Orange theme
         * instance is an implementation of [OrangeDownloadableFontFamily] ([OrangeHelveticaNeueLatin.Downloadable] or [OrangeHelveticaNeueArabic.Downloadable]).
         *
         * Please note that downloading font files is not the preferred way of configuring font families for the Orange themes. See documentation of [OrangeTheme] for more information.
         *
         * @param context The context.
         * @param downloadableFontFamilies The downloadable font families to preload.
         * @param onComplete A callback that is called when the font families are fully loaded.
         */
        fun preloadDownloadableFontFamilies(
            context: Context,
            downloadableFontFamilies: List<OrangeDownloadableFontFamily>,
            onComplete: (success: Boolean) -> Unit
        ) {
            val downloadableFontFamiliesToPreload = downloadableFontFamilies.filter { downloadableFontFamily ->
                when (downloadableFontFamily) {
                    is OrangeHelveticaNeueLatin.Downloadable -> downloadedLatinFontFamily == null
                    is OrangeHelveticaNeueArabic.Downloadable -> downloadedArabicFontFamily == null
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
                    val packageInfo = context.packageManager.getPackageInfo(context.packageName, flags)
                    val signatures = with(packageInfo) {
                        @Suppress("DEPRECATION")
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) signingInfo?.apkContentsSigners else signatures
                    }
                    listOf(signatures.orEmpty().map { it.toByteArray() })
                } catch (_: Exception) {
                    emptyList()
                }

                var success = true
                val callbackExecutor = ContextCompat.getMainExecutor(context)
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
                        val fontRequest = FontRequest(OrangeFontProvider.AUTHORITY, context.packageName, query, certificates)

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
                                            is OrangeHelveticaNeueLatin.Downloadable -> downloadedLatinFontFamily = downloadedFontFamily
                                            is OrangeHelveticaNeueArabic.Downloadable -> downloadedArabicFontFamily = downloadedFontFamily
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
                        FontsContractCompat.requestFont(context, fontRequest, style, null, callbackExecutor, callback)
                    }
                }
            }
        }
    }

    /** The Helvetica Neue Latin font family. */
    var latin: OrangeHelveticaNeueLatin? = null
        private set

    /** The Helvetica Neue Arabic font family. */
    var arabic: OrangeHelveticaNeueArabic? = null
        private set

    /**
     * Creates an instance of [OrangeFontFamily] with the Helvetica Neue Latin font family.
     *
     * @param latin The Helvetica Neue Latin font family.
     */
    constructor(latin: OrangeHelveticaNeueLatin) : this() {
        this.latin = latin
    }

    /**
     * Creates an instance of [OrangeFontFamily] with the Helvetica Neue Arabic font family.
     *
     * @param arabic The Helvetica Neue Arabic font family.
     */
    constructor(arabic: OrangeHelveticaNeueArabic) : this() {
        this.arabic = arabic
    }

    /**
     * Creates an instance of [OrangeFontFamily] with Helvetica Neue Latin and Arabic font families.
     *
     * @param latin The Helvetica Neue Latin font family.
     * @param arabic The Helvetica Neue Arabic font family.
     */
    constructor(latin: OrangeHelveticaNeueLatin, arabic: OrangeHelveticaNeueArabic) : this() {
        this.latin = latin
        this.arabic = arabic
    }

    /**
     * @suppress
     */
    @InternalOudsApi
    fun getFontFamily(locale: Locale): FontFamily {
        val localizedFontFamily = if (locale.language == "ar") arabic else latin
        return when (localizedFontFamily) {
            is OrangeBundledFontFamily -> localizedFontFamily.fontFamily
            is OrangeDownloadableFontFamily -> when (localizedFontFamily) {
                is OrangeHelveticaNeueLatin.Downloadable -> downloadedLatinFontFamily
                is OrangeHelveticaNeueArabic.Downloadable -> downloadedArabicFontFamily
            }
            else -> null
        }.orElse {
            FontFamily.Default
        }
    }
}

/**
 * A bundled font family for the Orange theme.
 */
sealed interface OrangeBundledFontFamily {

    /** The associated Compose font family. */
    val fontFamily: FontFamily
}

/**
 * A downloadable font family for the Orange theme.
 */
sealed interface OrangeDownloadableFontFamily

/**
 * The Helvetica Neue Latin font family for the Orange theme.
 */
sealed class OrangeHelveticaNeueLatin {

    /**
     * The bundled Helvetica Neue Latin font family for the Orange theme.
     *
     * @param regularFontResId The resource identifier of the Helvetica Neue Latin regular font.
     * @param mediumFontResId The resource identifier of the Helvetica Neue Latin medium font.
     * @param boldFontResId The resource identifier of the Helvetica Neue Latin bold font.
     */
    class Bundled(
        @FontRes regularFontResId: Int,
        @FontRes mediumFontResId: Int,
        @FontRes boldFontResId: Int
    ) : OrangeHelveticaNeueLatin(), OrangeBundledFontFamily {

        override val fontFamily: FontFamily = FontFamily(
            Font(regularFontResId, FontWeight.Normal),
            Font(mediumFontResId, FontWeight.Medium),
            Font(boldFontResId, FontWeight.Bold)
        )
    }

    /**
     * The downloadable Helvetica Neue Latin font family for the Orange theme.
     */
    object Downloadable : OrangeHelveticaNeueLatin(), OrangeDownloadableFontFamily
}

/**
 * The Helvetica Neue Arabic font family for the Orange theme.
 */
sealed class OrangeHelveticaNeueArabic {

    /**
     * The bundled Helvetica Neue Arabic font family for the Orange theme.
     *
     * @param lightFontResId The resource identifier of the Helvetica Neue Arabic light font.
     * @param regularFontResId The resource identifier of the Helvetica Neue Arabic regular font.
     * @param boldFontResId The resource identifier of the Helvetica Neue Arabic bold font.
     */
    class Bundled(
        @FontRes lightFontResId: Int,
        @FontRes regularFontResId: Int,
        @FontRes boldFontResId: Int
    ) : OrangeHelveticaNeueArabic(), OrangeBundledFontFamily {

        override val fontFamily: FontFamily = FontFamily(
            Font(lightFontResId, FontWeight.Light),
            Font(regularFontResId, FontWeight.Normal),
            Font(boldFontResId, FontWeight.Bold)
        )
    }

    /**
     * The downloadable Helvetica Neue Arabic font family for the Orange theme.
     */
    object Downloadable : OrangeHelveticaNeueArabic(), OrangeDownloadableFontFamily
}

/**
 * @suppress
 */
@InternalOudsApi
fun getPreviewOrangeFontFamily() = OrangeFontFamily(OrangeHelveticaNeueLatin.Downloadable)
