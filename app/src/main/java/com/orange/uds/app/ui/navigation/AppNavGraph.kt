/*
 * Software Name: Orange Unified Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.uds.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import com.orange.uds.app.ui.addBottomBarGraph

/**
 * Navigation graph of the application.
 */
fun NavGraphBuilder.appNavGraph() {
    addBottomBarGraph()
}
