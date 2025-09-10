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

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Theme settings.
 *
 * @property roundedButtonCorners Indicates if rounded corners should be applied to every buttons.
 *   Set to `false` for a default finish, or `true` for a finish with rounded corner.
 *   To be favored in more emotional, immersive contexts or those tied to specific visual identities.
 *   For standard or business-oriented journeys, keep the default corners.
 *   This evolution addresses the need for flexibility in adapting the design to certain brand contexts.
 *   Set to `null` if this setting has no effect on the associated theme.
 *
 * @constructor Creates an instance of [OudsThemeSettings].
 */
@Parcelize
data class OudsThemeSettings(val roundedButtonCorners: Boolean? = null) : Parcelable
