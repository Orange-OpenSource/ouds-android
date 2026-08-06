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
 * A circular progress indicator displayed in an OUDS component.
 *
 * @param progress The loading progress, where 0.0 represents no progress and 1.0 represents full progress.
 *   Values outside of this range are coerced into the range.
 *   Set this value to `null` to display a circular indeterminate progress indicator.
 */
class OudsLoader(val progress: Float?)
