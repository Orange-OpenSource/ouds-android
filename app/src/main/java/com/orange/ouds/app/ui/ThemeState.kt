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

package com.orange.ouds.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.orange.ouds.foundation.extensions.orElse
import com.orange.ouds.theme.OudsThemeContract
import com.orange.ouds.theme.OudsThemeSettings
import com.orange.ouds.theme.orange.ORANGE_THEME_NAME
import com.orange.ouds.theme.orange.OrangeFontFamily
import com.orange.ouds.theme.orange.OrangeHelveticaNeueArabic
import com.orange.ouds.theme.orange.OrangeHelveticaNeueLatin
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orangecompact.ORANGE_COMPACT_THEME_NAME
import com.orange.ouds.theme.orangecompact.OrangeCompactTheme
import com.orange.ouds.theme.sosh.SOSH_THEME_NAME
import com.orange.ouds.theme.sosh.SoshTheme
import com.orange.ouds.theme.whitelabel.WHITE_LABEL_THEME_NAME
import com.orange.ouds.theme.whitelabel.WhiteLabelFontFamily
import com.orange.ouds.theme.whitelabel.WhiteLabelTheme
import com.orange.ouds.theme.whitelabel.tokens.material.WhiteLabelMaterialColorTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorActionSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorBorderSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorContentSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorRepositorySemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSemanticTokens
import com.orange.ouds.theme.whitelabel.tokens.semantic.WhiteLabelColorSurfaceSemanticTokens
import com.orange.ouds.theme.wireframe.WIREFRAME_THEME_NAME
import com.orange.ouds.theme.wireframe.WireframeTheme

@Composable
fun rememberThemeState(
    settings: OudsThemeSettings = OudsThemeSettings(),
    themeNames: List<String> = listOf(
        ORANGE_THEME_NAME,
        ORANGE_COMPACT_THEME_NAME,
        SOSH_THEME_NAME,
        WIREFRAME_THEME_NAME,
        WHITE_LABEL_THEME_NAME
    ),
    currentThemeName: String = ORANGE_THEME_NAME,
    areDownloadableOrangeFontFamiliesPreloaded: Boolean = false
) = rememberSaveable(settings, themeNames, currentThemeName, areDownloadableOrangeFontFamiliesPreloaded, saver = ThemeState.Saver) {
    ThemeState(settings, themeNames, currentThemeName, areDownloadableOrangeFontFamiliesPreloaded)
}

/**
 * Theme state source of truth.
 */
class ThemeState(
    settings: OudsThemeSettings,
    private val themeNames: List<String>,
    private var currentThemeName: String,
    areDownloadableOrangeFontFamiliesPreloaded: Boolean
) {

    companion object {

        val Saver = listSaver(
            save = { state ->
                with(state) {
                    listOf(
                        settings,
                        themeNames,
                        currentThemeName,
                        areDownloadableOrangeFontFamilyPreloaded
                    )
                }
            },
            restore = { list ->
                @Suppress("UNCHECKED_CAST")
                ThemeState(
                    list[0] as OudsThemeSettings,
                    list[1] as List<String>,
                    list[2] as String,
                    list[3] as Boolean
                )
            }
        )
    }

    private var _settings by mutableStateOf(settings)
    var settings: OudsThemeSettings
        get() = _settings
        set(value) {
            _settings = value
            themes = getThemes(value, themeNames)
            setCurrentTheme(currentThemeName)
        }

    val areDownloadableOrangeFontFamilyPreloaded by mutableStateOf(areDownloadableOrangeFontFamiliesPreloaded)

    var themes by mutableStateOf(getThemes(settings, themeNames))
        private set

    var currentTheme by mutableStateOf(getCurrentTheme(currentThemeName))
        private set

    fun setCurrentTheme(name: String) {
        currentThemeName = name
        currentTheme = getCurrentTheme(name)
    }

    private fun getCurrentTheme(name: String): OudsThemeContract {
        return themes.firstOrNull { it.name == name }
            .orElse { themes.firstOrNull { it.name == ORANGE_THEME_NAME } }
            .orElse { themes.first() }
    }

    private fun getThemes(settings: OudsThemeSettings, names: List<String>): List<OudsThemeContract> {
        return with(settings) {
            names.mapNotNull { name ->
                when (name) {
                    ORANGE_THEME_NAME -> OrangeTheme(
                        orangeFontFamily = OrangeFontFamily(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable),
                        roundedCornerAlertMessages = roundedCornerAlertMessages.orElse { false },
                        roundedCornerButtons = roundedCornerButtons.orElse { false },
                        roundedCornerCardItems = roundedCornerCardItems.orElse { false },
                        roundedCornerProgressIndicators = roundedCornerProgressIndicators.orElse { false },
                        roundedCornerTextInputs = roundedCornerTextInputs.orElse { false }
                    )
                    ORANGE_COMPACT_THEME_NAME -> OrangeCompactTheme(
                        orangeFontFamily = OrangeFontFamily(OrangeHelveticaNeueLatin.Downloadable, OrangeHelveticaNeueArabic.Downloadable),
                        roundedCornerAlertMessages = roundedCornerAlertMessages.orElse { false },
                        roundedCornerButtons = roundedCornerButtons.orElse { false },
                        roundedCornerCardItems = roundedCornerCardItems.orElse { false },
                        roundedCornerProgressIndicators = roundedCornerProgressIndicators.orElse { false },
                        roundedCornerTextInputs = roundedCornerTextInputs.orElse { false }
                    )
                    SOSH_THEME_NAME -> SoshTheme()
                    WIREFRAME_THEME_NAME -> WireframeTheme()
                    WHITE_LABEL_THEME_NAME -> WhiteLabelThemeExample
                    else -> null
                }
            }
        }
    }
}

private object WhiteLabelThemeExampleColors {

    val Green900 = Color(0xFF1B5E20)
    val Green800 = Color(0xFF2E7D32)
    val Green700 = Color(0xFF388E3C)
    val Green500 = Color(0xFF4CAF50)
    val Green400 = Color(0xFF66BB6A)
    val Green300 = Color(0xFF81C784)
    val Green200 = Color(0xFFA5D6A7)
    val Green100 = Color(0xFFC8E6C9)
    val Green950 = Color(0xFF0D3D11)

    val GreenOpacity80 = Color(0x141B5E20)
    val GreenOpacity200 = Color(0x331B5E20)
    val GreenOpacity360 = Color(0x5C1B5E20)
    val GreenOpacity400 = Color(0x661B5E20)
    val GreenOpacity480 = Color(0x7A1B5E20)
    val GreenOpacity560 = Color(0x8F1B5E20)
    val GreenOpacity640 = Color(0xA31B5E20)

    val Teal800 = Color(0xFF00695C)
    val Teal700 = Color(0xFF00897B)
    val Teal300 = Color(0xFF4DB6AC)
    val Teal100 = Color(0xFFB2DFDB)
    val Teal900 = Color(0xFF004D40)

    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF121212)
}

private val WhiteLabelThemeExample: WhiteLabelTheme
    get() = WhiteLabelTheme(
        whiteLabelFontFamily = WhiteLabelFontFamily {
            FontFamily.Cursive
        },
        borderTokens = WhiteLabelBorderSemanticTokens(
            radiusDefault = 3.0f
        ),
        colorTokens = WhiteLabelColorSemanticTokens(
            actionColorTokens = WhiteLabelColorActionSemanticTokens(
                actionEnabledLight = WhiteLabelThemeExampleColors.Green900,
                actionEnabledDark = WhiteLabelThemeExampleColors.Green300,
                actionPressedLight = WhiteLabelThemeExampleColors.Green950,
                actionPressedDark = WhiteLabelThemeExampleColors.Green200,
                actionFocusLight = WhiteLabelThemeExampleColors.Green950,
                actionFocusDark = WhiteLabelThemeExampleColors.Green200,
                actionHighlightedLight = WhiteLabelThemeExampleColors.Green800,
                actionHighlightedDark = WhiteLabelThemeExampleColors.Green400,
                actionHoverLight = WhiteLabelThemeExampleColors.Green800,
                actionHoverDark = WhiteLabelThemeExampleColors.Green400,
                actionLoadingLight = WhiteLabelThemeExampleColors.Green700,
                actionLoadingDark = WhiteLabelThemeExampleColors.Green500,
                actionReadOnlyPrimaryLight = WhiteLabelThemeExampleColors.Green700,
                actionReadOnlyPrimaryDark = WhiteLabelThemeExampleColors.Green400,
                actionSelectedLight = WhiteLabelThemeExampleColors.Green900,
                actionSelectedDark = WhiteLabelThemeExampleColors.Green300,
                actionSupportHoverLight = WhiteLabelThemeExampleColors.Green800,
                actionSupportHoverDark = WhiteLabelThemeExampleColors.Green400,
                actionSupportFocusLight = WhiteLabelThemeExampleColors.Green950,
                actionSupportFocusDark = WhiteLabelThemeExampleColors.Green200,
                actionSupportLoadingLight = WhiteLabelThemeExampleColors.Green700,
                actionSupportLoadingDark = WhiteLabelThemeExampleColors.Green500
            ),
            borderColorTokens = WhiteLabelColorBorderSemanticTokens(
                borderBrandPrimaryLight = WhiteLabelThemeExampleColors.Green900,
                borderBrandPrimaryDark = WhiteLabelThemeExampleColors.Green300,
                borderBrandSecondaryLight = WhiteLabelThemeExampleColors.Green900,
                borderBrandSecondaryDark = WhiteLabelThemeExampleColors.Green800,
                borderFocusLight = WhiteLabelThemeExampleColors.Green950,
                borderFocusDark = WhiteLabelThemeExampleColors.Green200,
                borderStatusInfoLight = WhiteLabelThemeExampleColors.Teal700,
                borderStatusInfoDark = WhiteLabelThemeExampleColors.Teal300
            ),
            contentColorTokens = WhiteLabelColorContentSemanticTokens(
                contentBrandPrimaryLight = WhiteLabelThemeExampleColors.Green900,
                contentBrandPrimaryDark = WhiteLabelThemeExampleColors.Green300,
                contentBrandSecondaryLight = WhiteLabelThemeExampleColors.Green900,
                contentBrandSecondaryDark = WhiteLabelThemeExampleColors.Green800,
                contentOnBrandPrimaryLight = WhiteLabelThemeExampleColors.White,
                contentOnBrandPrimaryDark = WhiteLabelThemeExampleColors.Black,
                contentOnBrandSecondaryLight = WhiteLabelThemeExampleColors.White,
                contentOnBrandSecondaryDark = WhiteLabelThemeExampleColors.White,
                contentStatusInfoLight = WhiteLabelThemeExampleColors.Teal700,
                contentStatusInfoDark = WhiteLabelThemeExampleColors.Teal300,
                contentStatusInverseInfoLight = WhiteLabelThemeExampleColors.Teal300,
                contentStatusInverseInfoDark = WhiteLabelThemeExampleColors.Teal700
            ),
            repositoryColorTokens = WhiteLabelColorRepositorySemanticTokens(
                repositoryOpacityPrimaryLowest = WhiteLabelThemeExampleColors.GreenOpacity80,
                repositoryOpacityPrimaryLower = WhiteLabelThemeExampleColors.GreenOpacity200,
                repositoryOpacityPrimaryLow = WhiteLabelThemeExampleColors.GreenOpacity360,
                repositoryOpacityPrimaryMedium = WhiteLabelThemeExampleColors.GreenOpacity400,
                repositoryOpacityPrimaryHigh = WhiteLabelThemeExampleColors.GreenOpacity480,
                repositoryOpacityPrimaryHigher = WhiteLabelThemeExampleColors.GreenOpacity560,
                repositoryOpacityPrimaryHighest = WhiteLabelThemeExampleColors.GreenOpacity640,
                repositoryPrimaryLowest = WhiteLabelThemeExampleColors.Green100,
                repositoryPrimaryLower = WhiteLabelThemeExampleColors.Green200,
                repositoryPrimaryLow = WhiteLabelThemeExampleColors.Green300,
                repositoryPrimaryMedium = WhiteLabelThemeExampleColors.Green500,
                repositoryPrimaryHigh = WhiteLabelThemeExampleColors.Green700,
                repositoryPrimaryHigher = WhiteLabelThemeExampleColors.Green800,
                repositoryPrimaryHighest = WhiteLabelThemeExampleColors.Green900
            ),
            surfaceColorTokens = WhiteLabelColorSurfaceSemanticTokens(
                surfaceBrandPrimaryLight = WhiteLabelThemeExampleColors.Green900,
                surfaceBrandPrimaryDark = WhiteLabelThemeExampleColors.Green300,
                surfaceBrandSecondaryLight = WhiteLabelThemeExampleColors.Green900,
                surfaceBrandSecondaryDark = WhiteLabelThemeExampleColors.Green800,
                surfaceStatusInfoEmphasizedLight = WhiteLabelThemeExampleColors.Teal700,
                surfaceStatusInfoEmphasizedDark = WhiteLabelThemeExampleColors.Teal300,
                surfaceStatusInfoMutedLight = WhiteLabelThemeExampleColors.Teal100,
                surfaceStatusInfoMutedDark = WhiteLabelThemeExampleColors.Teal800
            )
        ),
        materialColorTokens = WhiteLabelMaterialColorTokens(
            primaryLight = WhiteLabelThemeExampleColors.Green900,
            primaryDark = WhiteLabelThemeExampleColors.Green300,
            primaryContainerLight = WhiteLabelThemeExampleColors.Green100,
            primaryContainerDark = WhiteLabelThemeExampleColors.Green800,
            onPrimaryLight = WhiteLabelThemeExampleColors.White,
            onPrimaryDark = WhiteLabelThemeExampleColors.Black,
            onPrimaryContainerLight = WhiteLabelThemeExampleColors.Green950,
            onPrimaryContainerDark = WhiteLabelThemeExampleColors.Green100,
            secondaryLight = WhiteLabelThemeExampleColors.Green800,
            secondaryDark = WhiteLabelThemeExampleColors.Green400,
            secondaryContainerLight = WhiteLabelThemeExampleColors.Green200,
            secondaryContainerDark = WhiteLabelThemeExampleColors.Green900,
            onSecondaryLight = WhiteLabelThemeExampleColors.White,
            onSecondaryDark = WhiteLabelThemeExampleColors.Black,
            onSecondaryContainerLight = WhiteLabelThemeExampleColors.Green950,
            onSecondaryContainerDark = WhiteLabelThemeExampleColors.Green200,
            tertiaryLight = WhiteLabelThemeExampleColors.Teal700,
            tertiaryDark = WhiteLabelThemeExampleColors.Teal300,
            tertiaryContainerLight = WhiteLabelThemeExampleColors.Teal100,
            tertiaryContainerDark = WhiteLabelThemeExampleColors.Teal800,
            onTertiaryLight = WhiteLabelThemeExampleColors.White,
            onTertiaryDark = WhiteLabelThemeExampleColors.Black,
            onTertiaryContainerLight = WhiteLabelThemeExampleColors.Teal900,
            onTertiaryContainerDark = WhiteLabelThemeExampleColors.Teal100,
            surfaceTintLight = WhiteLabelThemeExampleColors.Green900,
            surfaceTintDark = WhiteLabelThemeExampleColors.Green300
        )
    )
