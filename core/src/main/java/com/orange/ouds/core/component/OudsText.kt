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

package com.orange.ouds.core.component

/**
 * Represents the weight of an [OudsBodyText] or an [OudsLabelText].
 */
enum class OudsTextWeight {
    /**
     * The default weight provides the standard level of typographic emphasis for Body and Label styles. It is designed for optimal readability
     * and neutral hierarchy, making it the baseline for most interface content. Use it whenever no additional emphasis is required.
     */
    Default,

    /**
     * Moderate weight increases visual presence while remaining balanced and readable. It is used to reinforce hierarchy within Body
     * and Label typography without introducing strong emphasis. Suitable for highlighting important information or improving scanability.
     */
    Moderate,

    /**
     * Strong weight provides the highest level of emphasis for Body and Label styles. It is intended for critical information, key actions,
     * or elements that must stand out within dense content. Use it sparingly to preserve its impact and avoid visual overload.
     */
    Strong
}