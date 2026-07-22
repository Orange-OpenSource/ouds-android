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

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.orange.ouds.foundation.InternalOudsApi
import com.orange.ouds.foundation.extensions.orElse
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource

/**
 * Preloads the downloadable font families for the Orange themes.
 * Call this method if either the [OrangeFontFamily.latin] or [OrangeFontFamily.arabic] property of the [OrangeFontFamily] used to create the Orange theme
 * instance is an implementation of [OrangeDownloadableFontFamily] ([OrangeHelveticaNeueLatin.Downloadable] or [OrangeHelveticaNeueArabic.Downloadable]).
 *
 * Please note that downloading font files is not the preferred way of configuring font families for the Orange themes. See documentation of [OrangeTheme] for more information.
 *
 * **Android**: Downloads fonts using Android Downloadable Fonts feature.
 * **iOS**: No-op since iOS embeds Helvetica Neue by default.
 *
 * @param context The context (Android only, can be Any on other platforms).
 * @param downloadableFontFamilies The downloadable font families to preload.
 * @param onComplete A callback that is called when the font families are fully loaded.
 */
expect fun preloadDownloadableFontFamilies(
    context: Any,
    downloadableFontFamilies: List<OrangeDownloadableFontFamily>,
    onComplete: (success: Boolean) -> Unit
)

/**
 * The font family to use for the Orange themes.
 */
// The primary constructor should theoretically contain nullable Latin and Arabic parameters but this leads to a platform declaration clash
// That's why we use a private empty primary constructor and set Latin and Arabic properties later on
class OrangeFontFamily private constructor() {

    companion object {

        internal var downloadedLatinFontFamily: FontFamily? = null
        internal var downloadedArabicFontFamily: FontFamily? = null
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
    @Composable
    @InternalOudsApi
    fun getFontFamily(language: String): FontFamily {
        val localizedFontFamily = if (language == "ar") arabic else latin
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
    @get:Composable
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
        val regularFontResId: FontResource,
        val mediumFontResId: FontResource,
        val boldFontResId: FontResource
    ) : OrangeHelveticaNeueLatin(), OrangeBundledFontFamily {

        @get:Composable
        override val fontFamily: FontFamily
            get() = FontFamily(
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
        val lightFontResId: FontResource,
        val regularFontResId: FontResource,
        val boldFontResId: FontResource
    ) : OrangeHelveticaNeueArabic(), OrangeBundledFontFamily {

        @get:Composable
        override val fontFamily: FontFamily
            get() = FontFamily(
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
