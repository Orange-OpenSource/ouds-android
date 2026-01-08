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

package com.orange.ouds.core.component.figma

import androidx.compose.runtime.Composable
import com.figma.code.connect.Figma
import com.figma.code.connect.FigmaConnect
import com.figma.code.connect.FigmaProperty
import com.figma.code.connect.FigmaType
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus

/**
 * @suppress
 */
@FigmaConnect(url = "<FIGMA_COMPONENTS_BADGE_COUNT>")
class OudsBadgeCountConnection {

    @FigmaProperty(FigmaType.Enum, "Status")
    val status: OudsBadgeStatus = Figma.mapping(
        "Accent" to OudsBadgeStatus.Accent,
        "Positive" to OudsBadgeStatus.Positive,
        "Warning" to OudsBadgeStatus.Warning,
        "Negative" to OudsBadgeStatus.Negative,
        "Info" to OudsBadgeStatus.Info,
        "Neutral" to OudsBadgeStatus.Neutral
    )

    @FigmaProperty(FigmaType.Enum, "Size")
    val size: OudsBadgeSize = Figma.mapping(
        "Medium" to OudsBadgeSize.Medium,
        "Large" to OudsBadgeSize.Large
    )

    @FigmaProperty(FigmaType.Enum, "State")
    val enabled: Boolean = Figma.mapping(
        "Enabled" to true,
        "Disabled" to false
    )

    @FigmaProperty(FigmaType.Text, "✏️ Number")
    val count: Int = 0

    @Composable
    fun OudsBadgeCountExample() {
        OudsBadge(count = count, status = status, size = size, enabled = enabled)
    }
}