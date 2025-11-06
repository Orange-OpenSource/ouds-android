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

package com.orange.ouds.theme.wireframe.tokens.components

import com.orange.ouds.theme.tokens.components.OudsBadgeTokens
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

data class WireframeComponentsTokens(
    override val badge: OudsBadgeTokens = WireframeBadgeTokens(),
    override val button: OudsButtonTokens = WireframeButtonTokens(),
    override val buttonMonochrome: OudsButtonMonoTokens = WireframeButtonMonoTokens(),
    override val checkbox: OudsCheckboxTokens = WireframeCheckboxTokens(),
    override val chip: OudsChipTokens = WireframeChipTokens(),
    override val controlItem: OudsControlItemTokens = WireframeControlItemTokens(),
    override val divider: OudsDividerTokens = WireframeDividerTokens(),
    override val icon: OudsIconTokens = WireframeIconTokens(),
    override val inputTag: OudsInputTagTokens = WireframeInputTagTokens(),
    override val link: OudsLinkTokens = WireframeLinkTokens(),
    override val linkMonochrome: OudsLinkMonoTokens = WireframeLinkMonoTokens(),
    override val radioButton: OudsRadioButtonTokens = WireframeRadioButtonTokens(),
    override val switch: OudsSwitchTokens = WireframeSwitchTokens(),
    override val tag: OudsTagTokens = WireframeTagTokens(),
    override val textInput: OudsTextInputTokens = WireframeTextInputTokens()
) : OudsComponentsTokens
