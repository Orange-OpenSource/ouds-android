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

package com.orange.ouds.core.component.content

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsBadge
import com.orange.ouds.core.component.OudsBadgeShape
import com.orange.ouds.core.component.OudsBadgeSize
import com.orange.ouds.core.component.OudsBadgeStatus
import com.orange.ouds.core.theme.outerBorder
import com.orange.ouds.foundation.extensions.orElse

open class OudsComponentIconBadge internal constructor(val contentDescription: String, val count: Int?) : OudsComponentContent<Nothing>(Nothing::class.java) {

    protected open val borderColor: Color
        @Composable
        get() = Color.Unspecified

    @Composable
    override fun Content(modifier: Modifier) {
        Box(
            modifier = modifier
                .outerBorder(1.dp, color = borderColor, shape = OudsBadgeShape, innerOffsetPx = -1f)
                .semantics {
                    contentDescription = this@OudsComponentIconBadge.contentDescription
                }
        ) {
            val status = OudsBadgeStatus.Negative // A badge always has a negative status on an icon
            count?.let {
                OudsBadge(count = count, status = status, size = OudsBadgeSize.Medium)
            }.orElse {
                OudsBadge(status = status, size = OudsBadgeSize.ExtraSmall)
            }
        }
    }
}
