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

/**
 * The Helvetica Neue font family to use for the Orange theme.
 */
sealed class OrangeHelveticaNeueFontFamily {

    /**
     * A bundled Helvetica Neue font family.
     *
     * @param regularFontResId The resource identifier of the Helvetica Neue regular font.
     * @param mediumFontResId The resource identifier of the Helvetica Neue medium font.
     * @param boldFontResId The resource identifier of the Helvetica Neue bold font.
     */
    data class Bundled(
        @FontRes val regularFontResId: Int,
        @FontRes val mediumFontResId: Int,
        @FontRes val boldFontResId: Int
    ) : OrangeHelveticaNeueFontFamily()

    /**
     * A downloadable Helvetica Neue font family.
     */
    object Downloadable : OrangeHelveticaNeueFontFamily()
}
