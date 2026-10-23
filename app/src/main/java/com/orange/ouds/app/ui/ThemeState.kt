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
import androidx.compose.ui.unit.dp
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
import com.orange.ouds.theme.tokens.components.OudsLightDarkColor
import com.orange.ouds.theme.whitelabel.WHITE_LABEL_THEME_NAME
import com.orange.ouds.theme.whitelabel.WhiteLabelBorders
import com.orange.ouds.theme.whitelabel.WhiteLabelColorScheme
import com.orange.ouds.theme.whitelabel.WhiteLabelMaterialColorScheme
import com.orange.ouds.theme.whitelabel.WhiteLabelTheme
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

private val WhiteLabelThemeExample = with(WhiteLabelThemeExampleColors) {
    WhiteLabelTheme(
        whiteLabelFontFamily = { FontFamily.Cursive },
        borders = WhiteLabelBorders(
            radius = WhiteLabelBorders.Radius(
                default = 3.0.dp
            )
        ),
        colorScheme = WhiteLabelColorScheme(
            action = WhiteLabelColorScheme.Action(
                enabled = OudsLightDarkColor(Green900, Green300),
                pressed = OudsLightDarkColor(Green950, Green200),
                focus = OudsLightDarkColor(Green950, Green200),
                highlighted = OudsLightDarkColor(Green800, Green400),
                hover = OudsLightDarkColor(Green800, Green400),
                loading = OudsLightDarkColor(Green700, Green500),
                readOnly = WhiteLabelColorScheme.Action.ReadOnly(
                    primary = OudsLightDarkColor(Green700, Green400),
                ),
                selected = OudsLightDarkColor(Green900, Green300),
                support = WhiteLabelColorScheme.Action.Support(
                    focus = OudsLightDarkColor(Green950, Green200),
                    hover = OudsLightDarkColor(Green800, Green400),
                    loading = OudsLightDarkColor(Green700, Green500)
                )
            ),
            border = WhiteLabelColorScheme.Border(
                brandPrimary = OudsLightDarkColor(Green900, Green300),
                brandSecondary = OudsLightDarkColor(Green900, Green800),
                focus = OudsLightDarkColor(Green950, Green200),
                status = WhiteLabelColorScheme.Border.Status(
                    info = OudsLightDarkColor(Teal700, Teal300),
                )
            ),
            content = WhiteLabelColorScheme.Content(
                brandPrimary = OudsLightDarkColor(Green900, Green300),
                brandSecondary = OudsLightDarkColor(Green900, Green800),
                onBrand = WhiteLabelColorScheme.Content.OnBrand(
                    primary = OudsLightDarkColor(White, Black),
                    secondary = OudsLightDarkColor(White, White)
                ),
                status = WhiteLabelColorScheme.Content.Status(
                    info = OudsLightDarkColor(Teal700, Teal300),
                    inverse = WhiteLabelColorScheme.Content.Status.Inverse(
                        info = OudsLightDarkColor(Teal300, Teal700)
                    )
                )
            ),
            repository = WhiteLabelColorScheme.Repository(
                opacity = WhiteLabelColorScheme.Repository.Opacity(
                    primary = WhiteLabelColorScheme.Repository.Opacity.Primary(
                        lowest = GreenOpacity80,
                        lower = GreenOpacity200,
                        low = GreenOpacity360,
                        medium = GreenOpacity400,
                        high = GreenOpacity480,
                        higher = GreenOpacity560,
                        highest = GreenOpacity640
                    )
                ),
                primary = WhiteLabelColorScheme.Repository.Primary(
                    lowest = Green100,
                    lower = Green200,
                    low = Green300,
                    medium = Green500,
                    high = Green700,
                    higher = Green800,
                    highest = Green900
                )
            ),
            surface = WhiteLabelColorScheme.Surface(
                brand = WhiteLabelColorScheme.Surface.Brand(
                    primary = OudsLightDarkColor(Green900, Green300),
                    secondary = OudsLightDarkColor(Green900, Green800)
                ),
                status = WhiteLabelColorScheme.Surface.Status(
                    info = WhiteLabelColorScheme.Surface.Status.Info(
                        emphasized = OudsLightDarkColor(Teal700, Teal300),
                        muted = OudsLightDarkColor(Teal100, Teal800)
                    )
                )
            )
        ),
        materialColorScheme = WhiteLabelMaterialColorScheme(
            primary = OudsLightDarkColor(Green900, Green300),
            primaryContainer = OudsLightDarkColor(Green100, Green800),
            onPrimary = OudsLightDarkColor(White, Black),
            onPrimaryContainer = OudsLightDarkColor(Green950, Green100),
            secondary = OudsLightDarkColor(Green800, Green400),
            secondaryContainer = OudsLightDarkColor(Green200, Green900),
            onSecondary = OudsLightDarkColor(White, Black),
            onSecondaryContainer = OudsLightDarkColor(Green950, Green200),
            tertiary = OudsLightDarkColor(Teal700, Teal300),
            tertiaryContainer = OudsLightDarkColor(Teal100, Teal800),
            onTertiary = OudsLightDarkColor(White, Black),
            onTertiaryContainer = OudsLightDarkColor(Teal900, Teal100),
            surfaceTint = OudsLightDarkColor(Green900, Green300)
        )
    )
}
