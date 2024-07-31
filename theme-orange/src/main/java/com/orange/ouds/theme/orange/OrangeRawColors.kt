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

import androidx.compose.ui.graphics.Color

internal interface OrangeRawColors {

    val orange100: Color
        get() = Color(0xffff7900)
    val orange200: Color
        get() = Color(0xfff16e00)
    val tmpOrangeFFA14D: Color
        get() = Color(0xffffa14d)
    val tmpOrangeFFB68E: Color
        get() = Color(0xffffb68e)

    val white100: Color
        get() = Color(0xffffffff)
    val black900: Color
        get() = Color(0xff000000)

    val tmpBrown362F2C: Color
        get() = Color(0xff362f2c)
    val tmpBrown9C4500: Color
        get() = Color(0xff9C4500)
    val tmpBrown52443C: Color
        get() = Color(0xff52443C)
    val tmpGrey333333: Color
        get() = Color(0xff333333)
    val tmpGrey666666: Color
        get() = Color(0xff666666)
    val tmpGrey999999: Color
        get() = Color(0xff999999)
    val tmpGreyCCCCCC: Color
        get() = Color(0xffcccccc)
    val tmpGreyEBEBEB: Color
        get() = Color(0xffebebeb)
    val tmpGreyEEEEEE: Color
        get() = Color(0xffeeeeee)

    val info100: Color
        get() = Color(0xff527EDB)
    val info200: Color
        get() = Color(0xff4170D8)
    val alert100: Color
        get() = Color(0xffFFCC00)
    val alert200: Color
        get() = Color(0xff8F7200)
    val positive100: Color
        get() = Color(0xff32C832)
    val positive200: Color
        get() = Color(0xff228722)
    val negative100: Color
        get() = Color(0xffD53F15)
    val negative200: Color
        get() = Color(0xffCD3C14)

    val tmpRedD53F15: Color
        get() = Color(0xffd53f15)
    val tmpRedFFDAD6: Color
        get() = Color(0xffffdad6)
    val tmpRed410002: Color
        get() = Color(0xff410002)
    val tmpRed93000A: Color
        get() = Color(0xff93000a)
}
