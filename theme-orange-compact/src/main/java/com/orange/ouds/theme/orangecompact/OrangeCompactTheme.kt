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

package com.orange.ouds.theme.orangecompact

import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.theme.OudsDrawableResources
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.OrangeBundledFontFamily
import com.orange.ouds.theme.orange.OrangeDownloadableFontFamily
import com.orange.ouds.theme.orange.OrangeDrawableResources
import com.orange.ouds.theme.orange.OrangeFontFamily
import com.orange.ouds.theme.orange.OrangeFontProvider
import com.orange.ouds.theme.orange.OrangeHelveticaNeueArabic
import com.orange.ouds.theme.orange.OrangeHelveticaNeueLatin
import com.orange.ouds.theme.orangecompact.tokens.components.OrangeCompactComponentsTokens
import com.orange.ouds.theme.orangecompact.tokens.material.OrangeCompactMaterialColorTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactBorderSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactColorSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactEffectSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactElevationSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactFontSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactGridSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactOpacitySemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactSizeSemanticTokens
import com.orange.ouds.theme.orangecompact.tokens.semantic.OrangeCompactSpaceSemanticTokens
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

const val ORANGE_COMPACT_THEME_NAME = "Orange Compact"

/**
 * The Orange Compact theme.
 *
 * This theme uses the Helvetica Neue font family. **Due to legal issues Helvetica Neue font files are not bundled with this library.**
 *
 * The Helvetica Neue font files for the Orange Compact theme are available at
 * [https://brand.orange.com/en/brand-basics/typography](https://brand.orange.com/en/brand-basics/typography) and can be used by copying the `ttf` files
 * in the `res/font` directory of the project and by using an implementation of [OrangeBundledFontFamily] ([OrangeHelveticaNeueLatin.Bundled]
 * or [OrangeHelveticaNeueArabic.Bundled]) when creating the [OrangeFontFamily] instance passed as the [OrangeCompactTheme.orangeFontFamily] parameter:
 *
 * ```
 * OrangeCompactTheme(
 *     orangeFontFamily = OrangeFontFamily(
 *         latin = OrangeHelveticaNeueLatin.Bundled(
 *             R.font.helvetica_neue_latin_roman,
 *             R.font.helvetica_neue_latin_medium,
 *             R.font.helvetica_neue_latin_bold
 *         ),
 *         arabic = OrangeHelveticaNeueArabic.Bundled(
 *             R.font.helvetica_neue_arabic_light,
 *             R.font.helvetica_neue_arabic_roman,
 *             R.font.helvetica_neue_arabic_bold,
 *         )
 *     )
 * )
 * ```
 *
 * Although the preferred way of using the Helvetica Neue font is configuring bundled font files, there are some cases where this is not possible,
 * for instance in open source projects where the font files cannot be bundled due to legal issues. In these cases, the font files can
 * alternatively be downloaded through the Android Downloadable Fonts feature by using an implementation of
 * [OrangeDownloadableFontFamily] ([OrangeHelveticaNeueLatin.Downloadable] or [OrangeHelveticaNeueArabic.Downloadable]) instead:
 *
 * ```
 * OrangeCompactTheme(
 *     orangeFontFamily = OrangeFontFamily(
 *         latin = OrangeHelveticaNeueLatin.Downloadable,
 *         arabic = OrangeHelveticaNeueArabic.Downloadable
 *     )
 * )
 * ```
 *
 * In order to enable the Android Downloadable Fonts feature for the Orange Compact theme, please also add [OrangeFontProvider] as a provider in your app manifest:
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
 * Finally, call the [OrangeFontFamily.preloadDownloadableFontFamilies] method in the `onCreate` method of your application singleton or main activity,
 * and use the `onComplete` parameter to update your UI when preload is complete:
 *
 * ```
 * var areDownloadableOrangeFontFamiliesPreloaded by mutableStateOf(false)
 *     private set
 * ```
 * ```
 * OrangeFontFamily.preloadDownloadableFontFamilies(this, listOf(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable)) {
 *     areDownloadableOrangeFontFamiliesPreloaded = true
 * }
 * ```
 *
 * Please note that the Android Downloadable Font feature works asynchronously, whether the font is already downloaded or not,
 * and that [FontFamily.Default] will be used if download fails.
 *
 * @param orangeFontFamily The Helvetica Neue font family to use for the Orange Compact theme.
 *   If an [OrangeBundledFontFamily] is used, the resource identifiers should reference Helvetica Neue font files.
 *   If an [OrangeDownloadableFontFamily] is used, the [OrangeFontFamily.preloadDownloadableFontFamilies] method should be called to download the Helvetica Neue font files through the Android Downloadable Fonts feature.
 * @param roundedCornerButtons Whether or not buttons have rounded corners.
 * @param roundedCornerTextInputs Whether or not text inputs have rounded corners.
 * @param roundedCornerAlertMessages Whether or not alert messages have rounded corners.
 */
open class OrangeCompactTheme(
    private val orangeFontFamily: OrangeFontFamily,
    private val roundedCornerButtons: Boolean = false,
    private val roundedCornerTextInputs: Boolean = true,
    private val roundedCornerAlertMessages: Boolean = false
) : OudsThemeContract {

    override val name: String
        get() = ORANGE_COMPACT_THEME_NAME

    @Suppress("OVERRIDE_DEPRECATION")
    override val fontFamily: FontFamily
        get() = getFontFamily(Locale.getDefault())

    override fun getFontFamily(locale: Locale): FontFamily {
        return orangeFontFamily.getFontFamily(locale)
    }

    override val settings: OudsThemeSettings
        get() = OudsThemeSettings(roundedCornerButtons, roundedCornerTextInputs, roundedCornerAlertMessages)

    override val colorTokens: OudsColorSemanticTokens
        get() = OrangeCompactColorSemanticTokens()

    override val materialColorTokens: OudsMaterialColorTokens
        get() = OrangeCompactMaterialColorTokens()

    override val borderTokens: OudsBorderSemanticTokens
        get() = OrangeCompactBorderSemanticTokens()

    override val effectTokens: OudsEffectSemanticTokens
        get() = OrangeCompactEffectSemanticTokens()

    override val elevationTokens: OudsElevationSemanticTokens
        get() = OrangeCompactElevationSemanticTokens()

    override val fontTokens: OudsFontSemanticTokens
        get() = OrangeCompactFontSemanticTokens()

    override val gridTokens: OudsGridSemanticTokens
        get() = OrangeCompactGridSemanticTokens()

    override val opacityTokens: OudsOpacitySemanticTokens
        get() = OrangeCompactOpacitySemanticTokens()

    override val sizeTokens: OudsSizeSemanticTokens
        get() = OrangeCompactSizeSemanticTokens()

    override val spaceTokens: OudsSpaceSemanticTokens
        get() = OrangeCompactSpaceSemanticTokens()

    override val componentsTokens: OudsComponentsTokens
        get() = OrangeCompactComponentsTokens()

    override val drawableResources: OudsDrawableResources
        get() = OrangeDrawableResources()
}
