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

package com.orange.ouds.foundation.extensions

/**
 * Calls the specified function [block] and returns its result if `this` is `null`.
 *
 * @param R The type of the receiver.
 * @param block The function to execute if `this` is `null`.
 * @return `this` if it is not `null`, or the [block] result if `this` is `null`.
 */
inline fun <R> R?.orElse(block: () -> R) = this ?: run(block)