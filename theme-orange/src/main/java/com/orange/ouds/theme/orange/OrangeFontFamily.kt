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

import androidx.annotation.FontRes
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/**
 * The font family to use for the Orange theme.
 */
// The primary constructor should theoretically contain nullable Latin and Arabic parameters but this leads to a platform declaration clash
// That's why we use a private empty primary constructor and set Latin and Arabic properties later on
class OrangeFontFamily private constructor() {

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
