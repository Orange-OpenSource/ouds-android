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
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
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

const val ORANGE_THEME_NAME = "Orange"

open class OrangeTheme(
    fontFamily: OrangeFontFamily,
    private val roundedCornerButtons: Boolean = false,
    private val roundedCornerTextInputs: Boolean = false,
) : OudsThemeContract {

    @Deprecated("Please use constructor with fontFamily parameter instead.")
    constructor(
        roundedCornerButtons: Boolean = false,
        roundedCornerTextInputs: Boolean = false
    ) : this(OrangeFontFamily.Downloadable, roundedCornerButtons, roundedCornerTextInputs)

    companion object {

        private var downloadableFontFamily: FontFamily? = null

        fun preloadDownloadableFontFamily(context: Context, onComplete: (success: Boolean) -> Unit) {
            if (downloadableFontFamily != null) {
                onComplete(true)
            } else {
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

                val fontWeights = listOf(FontWeight.Normal, FontWeight.Medium, FontWeight.Bold)
                val typefaces = mutableMapOf<FontWeight, Typeface?>()
                val callbackExecutor = ContextCompat.getMainExecutor(context)

                fontWeights.forEach { fontWeight ->
                    val query = "${OrangeFontProvider.QUERY_WEIGHT_PARAMETER}=${fontWeight.weight}"
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
                                    onComplete(false)
                                } else {
                                    val fonts = typefaces.mapNotNull { (fontWeight, typeface) ->
                                        typeface?.let { Font(fontWeight, typeface) }
                                    }
                                    downloadableFontFamily = FontFamily(fonts)
                                    onComplete(true)
                                }
                            }
                        }
                    }

                    FontsContractCompat.requestFont(context, fontRequest, Typeface.NORMAL, null, callbackExecutor, callback)
                }
            }
        }
    }

    override val name: String
        get() = ORANGE_THEME_NAME

    private val orangeFontFamily: OrangeFontFamily = fontFamily

    override val fontFamily: FontFamily
        get() = when (orangeFontFamily) {
            is OrangeFontFamily.Bundled -> FontFamily(
                Font(orangeFontFamily.regularFontResId, FontWeight.Normal),
                Font(orangeFontFamily.mediumFontResId, FontWeight.Medium),
                Font(orangeFontFamily.boldFontResId, FontWeight.Bold)
            )
            is OrangeFontFamily.Downloadable -> downloadableFontFamily ?: super.fontFamily
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
