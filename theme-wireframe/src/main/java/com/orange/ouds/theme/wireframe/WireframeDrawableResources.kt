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

package com.orange.ouds.theme.wireframe

import com.orange.ouds.theme.OudsDrawableResources

class WireframeDrawableResources : OudsDrawableResources() {
    override val checkboxIndeterminate
        get() = R.drawable.wireframe_checkbox_indeterminate
    override val checkboxSelected: Int
        get() = R.drawable.wireframe_checkbox_selected
    override val chevronLeft: Int
        get() = R.drawable.wireframe_chevron_left
    override val tick: Int
        get() = R.drawable.wireframe_tick
    override val radioButtonSelected: Int
        get() = R.drawable.wireframe_radio_button_selected
    override val switchSelected: Int
        get() = R.drawable.wireframe_switch_selected
}
