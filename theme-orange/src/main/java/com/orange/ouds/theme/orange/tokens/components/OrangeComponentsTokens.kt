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

package com.orange.ouds.theme.orange.tokens.components

import com.orange.ouds.theme.tokens.components.OudsBadgeTokens
import com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsCheckboxTokens
import com.orange.ouds.theme.tokens.components.OudsChipTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.components.OudsControlItemTokens
import com.orange.ouds.theme.tokens.components.OudsDividerTokens
import com.orange.ouds.theme.tokens.components.OudsInputTagTokens
import com.orange.ouds.theme.tokens.components.OudsLinkMonoTokens
import com.orange.ouds.theme.tokens.components.OudsLinkTokens
import com.orange.ouds.theme.tokens.components.OudsRadioButtonTokens
import com.orange.ouds.theme.tokens.components.OudsSwitchTokens
import com.orange.ouds.theme.tokens.components.OudsTagTokens
import com.orange.ouds.theme.tokens.components.OudsTextInputTokens

data class OrangeComponentsTokens(
    override val badge: OudsBadgeTokens = OrangeBadgeTokens(),
    override val button: OudsButtonTokens = OrangeButtonTokens(),
    override val buttonMonochrome: OudsButtonMonoTokens = OrangeButtonMonoTokens(),
    override val checkbox: OudsCheckboxTokens = OrangeCheckboxTokens(),
    override val chip: OudsChipTokens = OrangeChipTokens(),
    override val controlItem: OudsControlItemTokens = OrangeControlItemTokens(),
    override val divider: OudsDividerTokens = OrangeDividerTokens(),
    override val link: OudsLinkTokens = OrangeLinkTokens(),
    override val linkMonochrome: OudsLinkMonoTokens = OrangeLinkMonoTokens(),
    override val radioButton: OudsRadioButtonTokens = OrangeRadioButtonTokens(),
    override val switch: OudsSwitchTokens = OrangeSwitchTokens(),
    override val tag: OudsTagTokens = OrangeTagTokens(),
    override val inputTag: OudsInputTagTokens = OrangeInputTagTokens(),
    override val textInput: OudsTextInputTokens = OrangeTextInputTokens()
) : OudsComponentsTokens