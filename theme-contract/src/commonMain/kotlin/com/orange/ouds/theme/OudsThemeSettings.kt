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

package com.orange.ouds.theme

import androidx.compose.runtime.saveable.listSaver
import com.orange.ouds.theme.OudsThemeSettings.Companion.Saver
import kotlin.jvm.JvmOverloads

/**
 * Theme settings.
 *
 * @property roundedCornerButtons Indicates if rounded corners should be applied to every buttons.
 *   Set to `false` for a default finish, or `true` for a finish with rounded corner.
 *   To be favored in more emotional, immersive contexts or those tied to specific visual identities.
 *   For standard or business-oriented journeys, keep the default corners.
 *   This evolution addresses the need for flexibility in adapting the design to certain brand contexts.
 *   Set to `null` if this setting has no effect on the associated theme (theme has always the same appearance managed by tokens).
 * @property roundedCornerTextInputs Indicates if rounded corners should be applied to every text inputs.
 *   Set to `false` for a square finish, or `true` for a finish with rounded corner.
 *   To be favored in more emotional, immersive contexts or those tied to specific visual identities.
 *   For standard or business-oriented journeys, keep the default corners.
 *   This evolution addresses the need for flexibility in adapting the design to certain brand contexts.
 *   Set to `null` if this setting has no effect on the associated theme (theme has always the same appearance managed by tokens).
 * @property roundedCornerAlertMessages Indicates if rounded corners should be applied to every alert messages.
 *   Set to `false` for a square finish, or `true` for a finish with rounded corner.
 *   To be favored in more emotional, immersive contexts or those tied to specific visual identities.
 *   For standard or business-oriented journeys, keep the default corners.
 *   This evolution addresses the need for flexibility in adapting the design to certain brand contexts.
 *   Set to `null` if this setting has no effect on the associated theme (theme has always the same appearance managed by tokens).
 * @property roundedCornerProgressIndicators Indicates if rounded corners should be applied to every progress indicators.
 *   Set to `false` for a square finish, or `true` for a finish with rounded corner.
 *   To be favored in more emotional, immersive contexts or those tied to specific visual identities.
 *   For standard or business-oriented journeys, keep the default corners.
 *   This evolution addresses the need for flexibility in adapting the design to certain brand contexts.
 *   Set to `null` if this setting has no effect on the associated theme (theme has always the same appearance managed by tokens).
 *
 * @constructor Creates an instance of [OudsThemeSettings].
 */
data class OudsThemeSettings @JvmOverloads constructor(
    val roundedCornerButtons: Boolean? = null,
    val roundedCornerTextInputs: Boolean? = null,
    val roundedCornerAlertMessages: Boolean? = null,
    val roundedCornerProgressIndicators: Boolean? = null
) {
    companion object {

        /**
         * [Saver] implementation for [OudsThemeSettings] to support saving/restoring state in Compose.
         */
        val Saver = listSaver(
            save = { settings ->
                with(settings) {
                    listOf(
                        roundedCornerButtons,
                        roundedCornerTextInputs,
                        roundedCornerAlertMessages,
                        roundedCornerProgressIndicators
                    )
                }
            },
            restore = { list ->
                OudsThemeSettings(
                    roundedCornerButtons = list[0] as Boolean,
                    roundedCornerTextInputs = list[1] as Boolean,
                    roundedCornerAlertMessages = list[2] as Boolean,
                    roundedCornerProgressIndicators = list[3] as Boolean
                )
            }
        )
    }
}
