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
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.OrangeTheme.Companion.preloadDownloadableFontFamilies
import com.orange.ouds.theme.orange.tokens.components.OrangeComponentsTokens
import com.orange.ouds.theme.orange.tokens.material.OrangeMaterialColorTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeBorderSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeColorSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeEffectSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeElevationSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeFontSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeGridSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeOpacitySemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeSizeSemanticTokens
import com.orange.ouds.theme.orange.tokens.semantic.OrangeSpaceSemanticTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.material.OudsMaterialColorTokens
import com.orange.ouds.theme.tokens.semantic.OudsBorderSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsColorSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsEffectSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsElevationSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsFontSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsGridSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsOpacitySemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSizeSemanticTokens
import com.orange.ouds.theme.tokens.semantic.OudsSpaceSemanticTokens
import java.util.Locale

const val ORANGE_THEME_NAME = "Orange"

/**
 * The Orange theme.
 *
 * This theme uses the Helvetica Neue font family. Due to legal issues Helvetica Neue font files are not bundled with this library.
 *
 * The Helvetica Neue font files for the Orange theme can be retrieved by copying the `ttf` files in the `res/font` directory of the project
 * and use an implementation of [OrangeBundledFontFamily] ([OrangeHelveticaNeueLatin.Bundled] or [OrangeHelveticaNeueArabic.Bundled])
 * when creating the [OrangeFontFamily] instance passed as the [OrangeTheme.orangeFontFamily] parameter:
 *
 * ```
 * OrangeTheme(
 *     orangeFontFamily = OrangeFontFamily(
 *         latin = OrangeHelveticaNeueLatin.Bundled(
 *             R.font.helvetica_neue_latin_regular,
 *             R.font.helvetica_neue_latin_medium,
 *             R.font.helvetica_neue_latin_bold
 *         ),
 *         arabic = OrangeHelveticaNeueArabic.Bundled(
 *             R.font.helvetica_neue_arabic_light,
 *             R.font.helvetica_neue_arabic_regular,
 *             R.font.helvetica_neue_arabic_medium,
 *         )
 *     )
 * )
 * ```
 *
 * The Helvetica Neue font files can alternatively be downloaded through the Android Downloadable Fonts feature
 * by using an implementation of [OrangeDownloadableFontFamily] ([OrangeHelveticaNeueLatin.Downloadable] or [OrangeHelveticaNeueArabic.Downloadable]) instead:
 *
 * ```
 * OrangeTheme(
 *     orangeFontFamily = OrangeFontFamily(
 *         latin = OrangeHelveticaNeueLatin.Downloadable,
 *         arabic = OrangeHelveticaNeueArabic.Downloadable
 *     )
 * )
 * ```
 *
 * In order to enable the Android Downloadable Fonts feature for the Orange theme, please also add [OrangeFontProvider] as a provider in your app manifest:
 *
 * ```
 * <provider
 *     android:name="com.orange.ouds.theme.orange.OrangeFontProvider"
 *     android:authorities="com.orange.ouds.theme.orange.fontprovider"
 *     android:exported="false" />
 * ```
 *
 * As well as the following permission:
 *
 * ```
 * <uses-permission android:name="android.permission.INTERNET" />
 * ```
 *
 * Finally, call the [OrangeTheme.preloadDownloadableFontFamilies] method in the `onCreate` method of your application singleton or main activity,
 * and use the `onComplete` parameter to update your UI when preload is complete:
 *
 * ```
 * var areDownloadableOrangeFontFamiliesPreloaded by mutableStateOf(false)
 *     private set
 * ```
 * ```
 * OrangeTheme.preloadDownloadableFontFamilies(this, listOf(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable)) {
 *     areDownloadableOrangeFontFamiliesPreloaded = true
 * }
 * ```
 *
 * Please note that [FontFamily.Default] will be used if download fails.
 *
 * @param orangeFontFamily The Helvetica Neue font family to use for the Orange theme.
 *   If an [OrangeBundledFontFamily] is used, the resource identifiers should reference Helvetica Neue font files.
 *   If an [OrangeDownloadableFontFamily] is used, the [preloadDownloadableFontFamilies] method should be called to download the Helvetica Neue font files through the Android Downloadable Fonts feature.
 * @param roundedCornerButtons Whether or not buttons have rounded corners.
 * @param roundedCornerTextInputs Whether or not text inputs have rounded corners.
 */
open class OrangeTheme(
    private val orangeFontFamily: OrangeFontFamily,
    private val roundedCornerButtons: Boolean = false,
    private val roundedCornerTextInputs: Boolean = false
) : OudsThemeContract {

    /**
     * Creates a new Orange theme.
     *
     * @param roundedCornerButtons Whether or not buttons have rounded corners.
     * @param roundedCornerTextInputs Whether or not text inputs have rounded corners.
     *
     * @deprecated Use constructor with orangeFontFamily parameter instead.
     */
    @Deprecated(
        "Use constructor with orangeFontFamily parameter instead.",
        ReplaceWith("OrangeTheme(OrangeFontFamily(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable), roundedCornerButtons, roundedCornerTextInputs)")
    )
    constructor(
        roundedCornerButtons: Boolean = false,
        roundedCornerTextInputs: Boolean = false
    ) : this(OrangeFontFamily(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable), roundedCornerButtons, roundedCornerTextInputs)

    companion object {

        private var downloadedLatinFontFamily: FontFamily? = null
        private var downloadedArabicFontFamily: FontFamily? = null

        /**
         * Preloads the downloadable font families for the Orange theme.
         * Call this method if any of the [OrangeTheme.orangeFontFamily] properties of the Orange theme instance is an implementation of [OrangeDownloadableFontFamily] ([OrangeHelveticaNeueLatin.Downloadable] or [OrangeHelveticaNeueArabic.Downloadable]).
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

                // Font requests require the list of sets of hashes for the certificates the provider is signed with
                // As OrangeFontProvider is embedded in the app, it is signed with the app certificate
                // That is why we can retrieve the certificate using methods on package manager
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
                        }

                        val style = if (fontWeight.weight >= FontWeight.Bold.weight) Typeface.BOLD else Typeface.NORMAL
                        FontsContractCompat.requestFont(context, fontRequest, style, null, callbackExecutor, callback)
                    }
                }
            }
        }
    }

    override val name: String
        get() = ORANGE_THEME_NAME

    @Suppress("OVERRIDE_DEPRECATION")
    override val fontFamily: FontFamily
        get() = getFontFamily(Locale.getDefault())

    override fun getFontFamily(locale: Locale): FontFamily {
        val localizedFontFamily = if (locale.language == "ar") orangeFontFamily.arabic else orangeFontFamily.latin
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

    override val settings: OudsThemeSettings
        get() = OudsThemeSettings(roundedCornerButtons, roundedCornerTextInputs)

    override val colorTokens: OudsColorSemanticTokens
        get() = OrangeColorSemanticTokens()

    override val materialColorTokens: OudsMaterialColorTokens
        get() = OrangeMaterialColorTokens()

    override val borderTokens: OudsBorderSemanticTokens
        get() = OrangeBorderSemanticTokens()

    override val effectTokens: OudsEffectSemanticTokens
        get() = OrangeEffectSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = OrangeElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = OrangeFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = OrangeGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = OrangeOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = OrangeSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = OrangeSpaceSemanticTokens()

    override val componentsTokens: OudsComponentsTokens
        get() = OrangeComponentsTokens()

    override val drawableResources: OudsDrawableResources
        get() = OrangeDrawableResources()
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
