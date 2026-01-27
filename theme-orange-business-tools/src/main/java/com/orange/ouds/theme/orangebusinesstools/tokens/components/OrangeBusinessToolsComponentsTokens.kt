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

package com.orange.ouds.theme.orangebusinesstools.tokens.components

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

internal data class OrangeBusinessToolsComponentsTokens(
    override val badge: OudsBadgeTokens = OrangeBusinessToolsBadgeTokens(),
    override val bar: OudsBarTokens = OrangeBusinessToolsBarTokens(),
    override val bulletList: OudsBulletListTokens = OrangeBusinessToolsBulletListTokens(),
    override val button: OudsButtonTokens = OrangeBusinessToolsButtonTokens(),
    override val buttonMonochrome: OudsButtonMonoTokens = OrangeBusinessToolsButtonMonoTokens(),
    override val checkbox: OudsCheckboxTokens = OrangeBusinessToolsCheckboxTokens(),
    override val chip: OudsChipTokens = OrangeBusinessToolsChipTokens(),
    override val controlItem: OudsControlItemTokens = OrangeBusinessToolsControlItemTokens(),
    override val divider: OudsDividerTokens = OrangeBusinessToolsDividerTokens(),
    override val icon: OudsIconTokens = OrangeBusinessToolsIconTokens(),
    override val inputTag: OudsInputTagTokens = OrangeBusinessToolsInputTagTokens(),
    override val link: OudsLinkTokens = OrangeBusinessToolsLinkTokens(),
    override val linkMonochrome: OudsLinkMonoTokens = OrangeBusinessToolsLinkMonoTokens(),
    override val radioButton: OudsRadioButtonTokens = OrangeBusinessToolsRadioButtonTokens(),
    override val switch: OudsSwitchTokens = OrangeBusinessToolsSwitchTokens(),
    override val tag: OudsTagTokens = OrangeBusinessToolsTagTokens(),
    override val textInput: OudsTextInputTokens = OrangeBusinessToolsTextInputTokens()
) : OudsComponentsTokens