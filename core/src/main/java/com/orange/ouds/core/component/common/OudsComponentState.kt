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

package com.orange.ouds.core.component.common

internal interface OudsComponentState {

    companion object {

        const val Enabled = "Enabled"
        const val Disabled = "Disabled"
        const val Focused = "Focused"
        const val Hovered = "Hovered"
        const val Loading = "Loading"
        const val ReadOnly = "ReadOnly"
        const val Skeleton = "Skeleton"
    }

    val areInteractionsEnabled: Boolean
        get() {
            val name = (this as? Enum<*>)?.name
            return name != Disabled && name != Loading && name != ReadOnly && name != Skeleton
        }
}
