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

package com.orange.ouds.theme.orangecountry.components

import com.orange.ouds.theme.orange.tokens.components.OrangeBadgeTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeButtonMonoTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeButtonTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeCheckboxTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeChipTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeControlItemTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeDividerTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeInputTagTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeLinkMonoTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeLinkTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeRadioButtonTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeSwitchTokens
import com.orange.ouds.theme.orange.tokens.components.OrangeTagTokens
import com.orange.ouds.theme.tokens.OudsBorderKeyToken
import com.orange.ouds.theme.tokens.OudsColorKeyToken
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

class OrangeCountryComponentsTokens(
    override val badge: OudsBadgeTokens = OrangeBadgeTokens(),
    override val button: OudsButtonTokens = OrangeButtonTokens(
        colorBgDefaultEnabled = OudsColorKeyToken.Surface.Status.Warning.Muted,
        borderRadiusDefault = OudsBorderKeyToken.Radius.Small
    ),
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
    override val inputTag: OudsInputTagTokens = OrangeInputTagTokens()
) : OudsComponentsTokens