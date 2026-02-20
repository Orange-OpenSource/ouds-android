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

package com.orange.ouds.theme.orangecompact.tokens.components

import com.orange.ouds.theme.tokens.components.OudsAlertTokens
import com.orange.ouds.theme.tokens.components.OudsBadgeTokens
import com.orange.ouds.theme.tokens.components.OudsBarTokens
import com.orange.ouds.theme.tokens.components.OudsBulletListTokens
import com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsCheckboxTokens
import com.orange.ouds.theme.tokens.components.OudsChipTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.components.OudsControlItemTokens
import com.orange.ouds.theme.tokens.components.OudsDividerTokens
import com.orange.ouds.theme.tokens.components.OudsIconTokens
import com.orange.ouds.theme.tokens.components.OudsInputTagTokens
import com.orange.ouds.theme.tokens.components.OudsLinkMonoTokens
import com.orange.ouds.theme.tokens.components.OudsLinkTokens
import com.orange.ouds.theme.tokens.components.OudsRadioButtonTokens
import com.orange.ouds.theme.tokens.components.OudsSwitchTokens
import com.orange.ouds.theme.tokens.components.OudsTagTokens
import com.orange.ouds.theme.tokens.components.OudsTextInputTokens

internal data class OrangeCompactComponentsTokens(
    override val alert: OudsAlertTokens = OrangeCompactAlertTokens(),
    override val badge: OudsBadgeTokens = OrangeCompactBadgeTokens(),
    override val bar: OudsBarTokens = OrangeCompactBarTokens(),
    override val bulletList: OudsBulletListTokens = OrangeCompactBulletListTokens(),
    override val button: OudsButtonTokens = OrangeCompactButtonTokens(),
    override val buttonMonochrome: OudsButtonMonoTokens = OrangeCompactButtonMonoTokens(),
    override val checkbox: OudsCheckboxTokens = OrangeCompactCheckboxTokens(),
    override val chip: OudsChipTokens = OrangeCompactChipTokens(),
    override val controlItem: OudsControlItemTokens = OrangeCompactControlItemTokens(),
    override val divider: OudsDividerTokens = OrangeCompactDividerTokens(),
    override val icon: OudsIconTokens = OrangeCompactIconTokens(),
    override val inputTag: OudsInputTagTokens = OrangeCompactInputTagTokens(),
    override val link: OudsLinkTokens = OrangeCompactLinkTokens(),
    override val linkMonochrome: OudsLinkMonoTokens = OrangeCompactLinkMonoTokens(),
    override val radioButton: OudsRadioButtonTokens = OrangeCompactRadioButtonTokens(),
    override val switch: OudsSwitchTokens = OrangeCompactSwitchTokens(),
    override val tag: OudsTagTokens = OrangeCompactTagTokens(),
    override val textInput: OudsTextInputTokens = OrangeCompactTextInputTokens()
) : OudsComponentsTokens