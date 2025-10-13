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

package com.orange.ouds.theme.sosh.tokens.components

import com.orange.ouds.theme.tokens.components.OudsBadgeTokens
import com.orange.ouds.theme.tokens.components.OudsBulletListTokens
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

data class SoshComponentsTokens(
    override val badge: OudsBadgeTokens = SoshBadgeTokens(),
    override val bulletList: OudsBulletListTokens = SoshBulletListTokens(),
    override val button: OudsButtonTokens = SoshButtonTokens(),
    override val buttonMonochrome: OudsButtonMonoTokens = SoshButtonMonoTokens(),
    override val checkbox: OudsCheckboxTokens = SoshCheckboxTokens(),
    override val chip: OudsChipTokens = SoshChipTokens(),
    override val controlItem: OudsControlItemTokens = SoshControlItemTokens(),
    override val divider: OudsDividerTokens = SoshDividerTokens(),
    override val link: OudsLinkTokens = SoshLinkTokens(),
    override val linkMonochrome: OudsLinkMonoTokens = SoshLinkMonoTokens(),
    override val radioButton: OudsRadioButtonTokens = SoshRadioButtonTokens(),
    override val switch: OudsSwitchTokens = SoshSwitchTokens(),
    override val tag: OudsTagTokens = SoshTagTokens(),
    override val inputTag: OudsInputTagTokens = SoshInputTagTokens()
) : OudsComponentsTokens