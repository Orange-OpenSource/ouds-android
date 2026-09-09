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

@file:Suppress("DEPRECATION")

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.value
import com.orange.ouds.foundation.RestrictedOudsApi
import com.orange.ouds.theme.tokens.components.OudsAccordionTokens
import com.orange.ouds.theme.tokens.components.OudsAlertTokens
import com.orange.ouds.theme.tokens.components.OudsBadgeTokens
import com.orange.ouds.theme.tokens.components.OudsBarTokens
import com.orange.ouds.theme.tokens.components.OudsBulletListTokens
import com.orange.ouds.theme.tokens.components.OudsButtonMonoTokens
import com.orange.ouds.theme.tokens.components.OudsButtonTokens
import com.orange.ouds.theme.tokens.components.OudsCheckboxTokens
import com.orange.ouds.theme.tokens.components.OudsChipTokens
import com.orange.ouds.theme.tokens.components.OudsComponentsTokens
import com.orange.ouds.theme.tokens.components.OudsDividerTokens
import com.orange.ouds.theme.tokens.components.OudsIconTokens
import com.orange.ouds.theme.tokens.components.OudsInputTagTokens
import com.orange.ouds.theme.tokens.components.OudsLinkMonoTokens
import com.orange.ouds.theme.tokens.components.OudsLinkTokens
import com.orange.ouds.theme.tokens.components.OudsListItemTokens
import com.orange.ouds.theme.tokens.components.OudsPinCodeInputTokens
import com.orange.ouds.theme.tokens.components.OudsProgressIndicatorMonoTokens
import com.orange.ouds.theme.tokens.components.OudsProgressIndicatorTokens
import com.orange.ouds.theme.tokens.components.OudsRadioButtonTokens
import com.orange.ouds.theme.tokens.components.OudsSwitchTokens
import com.orange.ouds.theme.tokens.components.OudsTagTokens
import com.orange.ouds.theme.tokens.components.OudsTextAreaTokens
import com.orange.ouds.theme.tokens.components.OudsTextInputTokens
import com.orange.ouds.theme.tokens.components.OudsTypographyTokens

@ConsistentCopyVisibility
@RestrictedOudsApi
data class OudsComponents internal constructor(
    val alert: Alert,
    val badge: Badge,
    val bar: Bar,
    val bulletList: BulletList,
    val button: Button,
    val buttonMonochrome: ButtonMonochrome,
    val checkbox: Checkbox,
    val chip: Chip,
    @Deprecated("Please use listItem instead.", ReplaceWith("OudsTheme.components.listItem"))
    val controlItem: ControlItem,
    val divider: Divider,
    val icon: Icon,
    val inputTag: InputTag,
    val link: Link,
    val linkMonochrome: LinkMonochrome,
    val listItem: ListItem,
    val pinCodeInput: PinCodeInput,
    val progressIndicator: ProgressIndicator,
    val progressIndicatorMonochrome: ProgressIndicatorMonochrome,
    val radioButton: RadioButton,
    val switch: Switch,
    val tag: Tag,
    val textArea: TextArea,
    val textInput: TextInput,
    val typography: Typography
) {

    @ConsistentCopyVisibility
    data class Alert internal constructor(
        val border: Border,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius,
            val width: Dp
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val default: Dp,
                val rounded: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val icon: Dp,
            val minHeight: Dp,
            val minHeightBottomActionPlacement: Dp,
            val minWidth: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: Dp,
            val columnGapAction: Dp,
            val paddingBlock: Dp,
            val paddingInline: Dp,
            val rowGap: Dp,
            val rowGapAction: Dp,
            val rowGapBullet: Dp
        )
    }

    @ConsistentCopyVisibility
    data class Badge internal constructor(
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val extraSmall: Dp,
            val small: Dp,
            val medium: Dp,
            val large: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val insetExtraSmall: Dp,
            val insetSmall: Dp,
            val insetMediumLarge: Dp,
            val paddingInline: PaddingInline
        ) {
            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val medium: Dp,
                val large: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class Bar internal constructor(
        val border: Border,
        val color: Color,
        val effect: Effect,
        val opacity: Opacity,
        val size: Size,
        @Deprecated(
            "Please use effect.backgroundBlur instead.",
            ReplaceWith("OudsTheme.components.bar.effect.backgroundBlur")
        )
        val blurRadius: Int
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val currentIndicatorCustom: CurrentIndicatorCustom
            ) {

                @ConsistentCopyVisibility
                data class CurrentIndicatorCustom internal constructor(
                    val bottom: Dp,
                    val top: Dp
                )
            }
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val border: Border,
            val content: Content,
            val currentIndicator: CurrentIndicator
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val opaque: androidx.compose.ui.graphics.Color,
                val translucent: androidx.compose.ui.graphics.Color
            )

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val badge: androidx.compose.ui.graphics.Color
            )

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val selected: Selected,
                val unselected: Unselected
            ) {

                @ConsistentCopyVisibility
                data class Selected internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Unselected internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class CurrentIndicator internal constructor(
                val android: Android,
                val custom: Custom
            ) {

                @ConsistentCopyVisibility
                data class Android internal constructor(
                    val selected: Selected,
                    val unselected: Unselected
                ) {

                    @ConsistentCopyVisibility
                    data class Selected internal constructor(
                        val disabled: androidx.compose.ui.graphics.Color,
                        val enabled: androidx.compose.ui.graphics.Color,
                        val focus: androidx.compose.ui.graphics.Color,
                        val hover: androidx.compose.ui.graphics.Color,
                        val pressed: androidx.compose.ui.graphics.Color
                    )

                    @ConsistentCopyVisibility
                    data class Unselected internal constructor(
                        val disabled: androidx.compose.ui.graphics.Color,
                        val focus: androidx.compose.ui.graphics.Color,
                        val hover: androidx.compose.ui.graphics.Color,
                        val pressed: androidx.compose.ui.graphics.Color
                    )
                }

                @ConsistentCopyVisibility
                data class Custom internal constructor(
                    val selected: Selected
                ) {

                    @ConsistentCopyVisibility
                    data class Selected internal constructor(
                        val enabled: androidx.compose.ui.graphics.Color,
                        val focus: androidx.compose.ui.graphics.Color,
                        val hover: androidx.compose.ui.graphics.Color,
                        val pressed: androidx.compose.ui.graphics.Color
                    )
                }
            }
        }

        @ConsistentCopyVisibility
        data class Effect internal constructor(
            val backgroundBlur: Int
        )

        @ConsistentCopyVisibility
        data class Opacity internal constructor(
            val currentIndicatorCustom: Float
        )

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val height: Height,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Height internal constructor(
                val currentIndicatorCustom: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val currentIndicatorCustom: CurrentIndicatorCustom
            ) {

                @ConsistentCopyVisibility
                data class CurrentIndicatorCustom internal constructor(
                    val bottom: Dp,
                    val top: Dp
                )
            }
        }
    }

    @ConsistentCopyVisibility
    data class BulletList internal constructor(
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val paddingBlock: PaddingBlock,
            val paddingInline: PaddingInline
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val bodyLarge: Dp,
                val bodyMedium: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val bodyLarge: Dp,
                val bodyMedium: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val level0: Dp,
                val level1: Dp,
                val level2: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class Button internal constructor(
        val border: Border,
        val color: Color,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val aiIconOnly: Dp,
                val default: Dp,
                val rounded: Dp,
                val social: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val ai: Dp,
                val aiInteraction: Dp,
                val default: Dp,
                val defaultInteraction: Dp,
                val defaultInteractionMonochrome: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val border: Border,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val brand: Brand,
                val default: Default,
                val minimal: Minimal,
                val ai: Ai
            ) {

                @ConsistentCopyVisibility
                data class Brand internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Minimal internal constructor(
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Ai internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val default: Default,
                val ai: Ai
            ) {

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Ai internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val brand: Brand,
                val default: Default,
                val minimal: Minimal,
                val ai: Ai
            ) {

                @ConsistentCopyVisibility
                data class Brand internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Minimal internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Ai internal constructor(
                    val enabled: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val disabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color
                )
            }
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            @Deprecated("Please use iconDefault instead.", ReplaceWith("OudsTheme.components.button.size.iconDefault"))
            val icon: Dp,
            val iconDefault: Dp,
            val iconSmall: Dp,
            @Deprecated("Please use iconOnlyDefault instead.", ReplaceWith("OudsTheme.components.button.size.iconOnlyDefault"))
            val iconOnly: Dp,
            val iconOnlyDefault: Dp,
            val iconOnlySmall: Dp,
            @Deprecated("Please use progressIndicatorDefault instead.", ReplaceWith("OudsTheme.components.button.size.progressIndicatorDefault"))
            val loader: Dp,
            @Deprecated("Please use maxSizeIconOnlyDefault instead.", ReplaceWith("OudsTheme.components.button.size.maxSizeIconOnlyDefault"))
            val maxHeightIconOnly: Dp,
            val maxSizeIconOnlyDefault: Dp,
            val maxSizeIconOnlySmall: Dp,
            @Deprecated("Please use minHeightDefault instead.", ReplaceWith("OudsTheme.components.button.size.minHeightDefault"))
            val minHeight: Dp,
            val minHeightDefault: Dp,
            val minHeightSmall: Dp,
            @Deprecated("Please use minWidthDefault instead.", ReplaceWith("OudsTheme.components.button.size.minWidthDefault"))
            val minWidth: Dp,
            val minWidthDefault: Dp,
            val minWidthSmall: Dp,
            val progressIndicatorDefault: Dp,
            val progressIndicatorSmall: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            @Deprecated("Please use inset.iconOnlyDefault instead.", ReplaceWith("OudsTheme.components.button.space.inset.iconOnlyDefault"))
            val insetIconOnly: Dp,
            val inset: Inset,
            @Deprecated("Please use paddingBlockDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingBlockDefault"))
            val paddingBlock: Dp,
            val paddingBlockDefault: Dp,
            val paddingBlockSmall: Dp,
            val paddingInline: PaddingInline
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                @Deprecated("Please use chevronDefault instead.", ReplaceWith("OudsTheme.components.button.space.columnGap.chevronDefault"))
                val chevron: Dp,
                val chevronDefault: Dp,
                val chevronSmall: Dp,
                @Deprecated("Please use iconDefault instead.", ReplaceWith("OudsTheme.components.button.space.columnGap.iconDefault"))
                val icon: Dp,
                val iconDefault: Dp,
                val iconSmall: Dp,
                @Deprecated("Please use iconChevronDefault instead.", ReplaceWith("OudsTheme.components.button.space.columnGap.iconChevronDefault"))
                val iconChevron: Dp,
                val iconChevronDefault: Dp,
                val iconChevronSmall: Dp
            )

            @ConsistentCopyVisibility
            data class Inset internal constructor(
                val iconOnlyDefault: Dp,
                val iconOnlySmall: Dp,
                val progressIndicatorOnlyDefault: Dp,
                val progressIndicatorOnlySmall: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                @Deprecated("Please use chevronEndDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.chevronEndDefault"))
                val chevronEnd: Dp,
                val chevronEndDefault: Dp,
                val chevronEndSmall: Dp,
                @Deprecated("Please use chevronStartDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.chevronStartDefault"))
                val chevronStart: Dp,
                val chevronStartDefault: Dp,
                val chevronStartSmall: Dp,
                @Deprecated("Please use endIconStartDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.endIconStartDefault"))
                val endIconStart: Dp,
                val endIconStartDefault: Dp,
                val endIconStartSmall: Dp,
                @Deprecated("Please use iconNoneDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.iconNoneDefault"))
                val iconNone: Dp,
                val iconNoneDefault: Dp,
                val iconNoneSmall: Dp,
                @Deprecated("Please use iconStartDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.iconStartDefault"))
                val iconStart: Dp,
                val iconStartDefault: Dp,
                val iconStartSmall: Dp,
                @Deprecated("Please use startIconEndDefault instead.", ReplaceWith("OudsTheme.components.button.space.paddingInline.startIconEndDefault"))
                val startIconEnd: Dp,
                val startIconEndDefault: Dp,
                val startIconEndSmall: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class ButtonMonochrome internal constructor(
        val color: Color
    ) {

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val border: Border,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val default: Default,
                val minimal: Minimal,
                val strong: Strong
            ) {

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Minimal internal constructor(
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Strong internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val default: Default
            ) {

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val default: Default,
                val minimal: Minimal,
                val strong: Strong
            ) {

                @ConsistentCopyVisibility
                data class Default internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Minimal internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Strong internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val loading: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }
        }
    }

    @ConsistentCopyVisibility
    data class Checkbox internal constructor(
        val border: Border,
        val size: Size
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Dp,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val selected: Dp,
                val selectedFocus: Dp,
                val selectedHover: Dp,
                val selectedPressed: Dp,
                val unselected: Dp,
                val unselectedFocus: Dp,
                val unselectedHover: Dp,
                val unselectedPressed: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            @Deprecated("")
            val indicator: Dp,
            val maxHeight: Dp,
            val minHeight: Dp,
            val minWidth: Dp
        )
    }

    @ConsistentCopyVisibility
    data class Chip internal constructor(
        val badge: Badge,
        val border: Border,
        val color: Color,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Badge internal constructor(
            val color: Color
        ) {

            @ConsistentCopyVisibility
            data class Color internal constructor(
                val background: androidx.compose.ui.graphics.Color,
                val content: androidx.compose.ui.graphics.Color
            )
        }

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Dp,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val selected: Dp,
                val unselected: Dp,
                val unselectedInteraction: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val border: Border,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val selected: Selected,
                val unselected: Unselected
            ) {

                @ConsistentCopyVisibility
                data class Selected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Unselected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val selected: Selected,
                val unselected: Unselected
            ) {

                @ConsistentCopyVisibility
                data class Selected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Unselected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val selected: Selected,
                val unselected: Unselected
            ) {

                @ConsistentCopyVisibility
                data class Selected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color,
                    val tickEnabled: androidx.compose.ui.graphics.Color
                )

                @ConsistentCopyVisibility
                data class Unselected internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val icon: Dp,
            val minHeight: Dp,
            val minHeightInteractiveArea: Dp,
            val minWidth: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val paddingBlock: Dp,
            val paddingBlockIconOnly: Dp,
            val paddingInline: PaddingInline
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val badgeChevron: Dp,
                val icon: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val badgeStart: Dp,
                val chevronEnd: Dp,
                val icon: Dp,
                val iconNone: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    @Deprecated("Please use OudsComponents.ListItem instead.")
    data class ControlItem internal constructor(
        @Deprecated("Please use components.listItem.border instead.", ReplaceWith("OudsTheme.components.listItem.border"))
        val border: Border,
        @Deprecated("Please use components.listItem.color instead.", ReplaceWith("OudsTheme.components.listItem.color"))
        val color: Color,
        @Deprecated("Please use components.listItem.font instead.", ReplaceWith("OudsTheme.components.listItem.font"))
        val font: Font,
        @Deprecated("Please use components.listItem.opacity instead.", ReplaceWith("OudsTheme.components.listItem.opacity"))
        val opacity: Opacity,
        @Deprecated("Please use components.listItem.size instead.", ReplaceWith("OudsTheme.components.listItem.size"))
        val size: Size,
        @Deprecated("Please use components.listItem.space instead.", ReplaceWith("OudsTheme.components.listItem.space"))
        val space: Space
    ) {

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Border instead.")
        data class Border internal constructor(
            @Deprecated("Please use components.listItem.border.radius instead.", ReplaceWith("OudsTheme.components.listItem.border.radius"))
            val radius: Radius,
            @Deprecated("Please use components.listItem.border.width instead.", ReplaceWith("OudsTheme.components.listItem.border.width"))
            val width: Width
        ) {

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Border.Radius instead.")
            data class Radius internal constructor(
                @Deprecated(
                    "Please use components.listItem.border.radius.currentIndicator instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.radius.currentIndicator")
                )
                val currentIndicator: Dp,
                @Deprecated(
                    "Please use components.listItem.border.radius.default instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.radius.default")
                )
                val default: Dp,
                @Deprecated(
                    "Please use components.listItem.border.radius.media instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.radius.media")
                )
                val media: Dp,
                @Deprecated(
                    "Please use components.listItem.border.radius.mediaRounded instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.radius.mediaRounded")
                )
                val mediaRoundedCorner: Dp,
                @Deprecated(
                    "Please use components.listItem.border.radius.rounded instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.radius.rounded")
                )
                val rounded: Dp
            )

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Border.Width instead.")
            data class Width internal constructor(
                @Deprecated(
                    "Please use components.listItem.border.width.currentPage instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.width.currentPage")
                )
                val currentPage: Dp,
                @Deprecated(
                    "Please use components.listItem.border.width.default instead.",
                    ReplaceWith("OudsTheme.components.listItem.border.width.default")
                )
                val default: Dp
            )
        }

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Color instead.")
        data class Color internal constructor(
            @Deprecated(
                "Please use components.listItem.color.background.badge.safetyArea instead.",
                ReplaceWith("OudsTheme.components.listItem.color.background.badge.safetyArea")
            )
            val badgeSafetyArea: androidx.compose.ui.graphics.Color,
            @Deprecated(
                "Please use components.listItem.color.background instead.",
                ReplaceWith("OudsTheme.components.listItem.color.background")
            )
            val background: Background,
            @Deprecated(
                "Please use components.listItem.color.content instead.",
                ReplaceWith("OudsTheme.components.listItem.color.content")
            )
            val content: Content
        ) {

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Color.Background instead.")
            data class Background internal constructor(

                @Deprecated(
                    "Please use components.listItem.color.background.current instead.",
                    ReplaceWith("OudsTheme.components.listItem.color.background.current")
                )
                val current: Current
            ) {

                @ConsistentCopyVisibility
                @Deprecated("Please use OudsComponents.ListItem.Color.Background.Current instead.")
                data class Current internal constructor(
                    @Deprecated(
                        "Please use components.listItem.color.background.current.disabled instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.background.current.disabled")
                    )
                    val disabled: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.background.current.enabled instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.background.current.enabled")
                    )
                    val enabled: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.background.current.focus instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.background.current.focus")
                    )
                    val focus: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.background.current.hover instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.background.current.hover")
                    )
                    val hover: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.background.current.pressed instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.background.current.pressed")
                    )
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Color.Content instead.")
            data class Content internal constructor(
                @Deprecated(
                    "Please use components.listItem.color.content.current instead.",
                    ReplaceWith("OudsTheme.components.listItem.color.content.current")
                )
                val current: Current
            ) {

                @ConsistentCopyVisibility
                @Deprecated("Please use OudsComponents.ListItem.Color.Content.Current instead.")
                data class Current internal constructor(
                    @Deprecated(
                        "Please use components.listItem.color.content.current.disabled instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.content.current.disabled")
                    )
                    val disabled: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.content.current.enabled instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.content.current.enabled")
                    )
                    val enabled: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.content.current.focus instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.content.current.focus")
                    )
                    val focus: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.content.current.hover instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.content.current.hover")
                    )
                    val hover: androidx.compose.ui.graphics.Color,
                    @Deprecated(
                        "Please use components.listItem.color.content.current.pressed instead.",
                        ReplaceWith("OudsTheme.components.listItem.color.content.current.pressed")
                    )
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }
        }

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Font instead.")
        data class Font internal constructor(
            @Deprecated(
                "Please use components.listItem.font.letterSpacing instead.",
                ReplaceWith("OudsTheme.components.listItem.font.letterSpacing")
            )
            val letterSpacing: LetterSpacing,
            @Deprecated(
                "Please use components.listItem.font.lineHeight instead.",
                ReplaceWith("OudsTheme.components.listItem.font.lineHeight")
            )
            val lineHeight: LineHeight,
            @Deprecated(
                "Please use components.listItem.font.size instead.",
                ReplaceWith("OudsTheme.components.listItem.font.size")
            )
            val size: Size
        ) {

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Font.LetterSpacing instead.")
            data class LetterSpacing internal constructor(
                @Deprecated(
                    "Please use components.listItem.font.letterSpacing.avatarInitialExtraLarge instead.",
                    ReplaceWith("OudsTheme.components.listItem.font.letterSpacing.avatarInitialExtraLarge")
                )
                val avatarInitialExtraLarge: Dp
            )

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Font.LineHeight instead.")
            data class LineHeight internal constructor(
                @Deprecated(
                    "Please use components.listItem.font.lineHeight.avatarInitialExtraLarge instead.",
                    ReplaceWith("OudsTheme.components.listItem.font.lineHeight.avatarInitialExtraLarge")
                )
                val avatarInitialExtraLarge: Dp
            )

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Font.Size instead.")
            data class Size internal constructor(
                @Deprecated(
                    "Please use components.listItem.font.size.avatarInitialExtraLarge instead.",
                    ReplaceWith("OudsTheme.components.listItem.font.size.avatarInitialExtraLarge")
                )
                val avatarInitialExtraLarge: Dp
            )
        }

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Opacity instead.")
        data class Opacity internal constructor(
            @Deprecated(
                "Please use components.listItem.opacity.currentDivider instead.",
                ReplaceWith("OudsTheme.components.listItem.opacity.currentDivider")
            )
            val currentDivider: Float,
            @Deprecated(
                "Please use components.listItem.opacity.currentIndicator instead.",
                ReplaceWith("OudsTheme.components.listItem.opacity.currentIndicator")
            )
            val currentIndicator: Float
        )

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Size instead.")
        data class Size internal constructor(
            @Deprecated("Please use components.listItem.size.asset instead.", ReplaceWith("OudsTheme.components.listItem.size.asset"))
            val asset: Asset,
            @Deprecated("Please use components.listItem.size.controlIndicator instead.", ReplaceWith("OudsTheme.components.listItem.size.controlIndicator"))
            val controlIndicator: Dp,
            @Deprecated("Please use components.listItem.size.currentIndicator instead.", ReplaceWith("OudsTheme.components.listItem.size.currentIndicator"))
            val currentIndicator: CurrentIndicator,
            @Deprecated("Please use components.listItem.size.flag instead.", ReplaceWith("OudsTheme.components.listItem.size.flag"))
            val flag: Flag,
            @Deprecated("Please use components.listItem.size.maxWidth instead.", ReplaceWith("OudsTheme.components.listItem.size.maxWidth"))
            val maxWidth: Dp,
            @Deprecated("Please use components.listItem.size.minHeightSmall instead.", ReplaceWith("OudsTheme.components.listItem.size.minHeightSmall"))
            val minHeightCompact: Dp,
            @Deprecated("Please use components.listItem.size.minHeightDefault instead.", ReplaceWith("OudsTheme.components.listItem.size.minHeightDefault"))
            val minHeightDefault: Dp,
            @Deprecated("Please use components.listItem.size.minWidth instead.", ReplaceWith("OudsTheme.components.listItem.size.minWidth"))
            val minWidth: Dp
        ) {

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Size.Asset instead.")
            data class Asset internal constructor(
                @Deprecated("Please use components.listItem.size.asset.large instead.", ReplaceWith("OudsTheme.components.listItem.size.asset.large"))
                val large: Dp,
                @Deprecated("Please use components.listItem.size.asset.medium instead.", ReplaceWith("OudsTheme.components.listItem.size.asset.medium"))
                val medium: Dp,
                @Deprecated("Please use components.listItem.size.asset.small instead.", ReplaceWith("OudsTheme.components.listItem.size.asset.small"))
                val small: Dp,
                @Deprecated("Please use components.listItem.size.asset.extraLarge instead.", ReplaceWith("OudsTheme.components.listItem.size.asset.extraLarge"))
                val extraLarge: Dp
            )

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Size.Flag instead.")
            data class Flag internal constructor(
                @Deprecated("Please use components.listItem.size.flag.height instead.", ReplaceWith("OudsTheme.components.listItem.size.flag.height"))
                val height: Dp
            )

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Size.CurrentIndicator instead.")
            data class CurrentIndicator internal constructor(
                @Deprecated(
                    "Please use components.listItem.size.currentIndicator.width instead.",
                    ReplaceWith("OudsTheme.components.listItem.size.currentIndicator.width")
                )
                val width: Dp
            )
        }

        @ConsistentCopyVisibility
        @Deprecated("Please use OudsComponents.ListItem.Space instead.")
        data class Space internal constructor(
            @Deprecated("Please use components.listItem.space.columnGap instead.", ReplaceWith("OudsTheme.components.listItem.space.columnGap"))
            val columnGap: Dp,
            @Deprecated("Please use components.listItem.space.paddingBlock instead.", ReplaceWith("OudsTheme.components.listItem.space.paddingBlock"))
            val paddingBlock: PaddingBlock,
            @Deprecated("Please use components.listItem.space.paddingInline instead.", ReplaceWith("OudsTheme.components.listItem.space.paddingInline"))
            val paddingInline: Dp,
            @Deprecated("Please use components.listItem.space.rowGap instead.", ReplaceWith("OudsTheme.components.listItem.space.rowGap"))
            val rowGap: Dp
        ) {

            @ConsistentCopyVisibility
            @Deprecated("Please use OudsComponents.ListItem.Space.PaddingBlock instead.")
            data class PaddingBlock internal constructor(
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.slotTextContainer instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.slotTextContainer")
                )
                val bottomSlot: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.small instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.small")
                )
                val densityCompact: Dp,
                @Deprecated("This token has been removed.")
                val densityCompactBottomExpandContainer: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.topAlignment.topCounterweightSmall instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.topAlignment.topCounterweightSmall")
                )
                val densityCompactTopAlignmentTopCounterweight: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.topAlignment.topTextContainerSmall instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.topAlignment.topTextContainerSmall")
                )
                val densityCompactTopAlignmentTopTextContainer: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.default instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.default")
                )
                val densityDefault: Dp,
                @Deprecated("This token has been removed.")
                val densityDefaultBottomExpandContainer: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.topAlignment.topCounterweightDefault instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.topAlignment.topCounterweightDefault")
                )
                val densityDefaultTopAlignmentTopCounterweight: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.topAlignment.topTextContainerDefault instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.topAlignment.topTextContainerDefault")
                )
                val densityDefaultTopAlignmentTopTextContainer: Dp,
                @Deprecated(
                    "Please use components.listItem.space.paddingBlock.topHelperText instead.",
                    ReplaceWith("OudsTheme.components.listItem.space.paddingBlock.topHelperText")
                )
                val topHelperText: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class Divider internal constructor(
        val border: Border
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val width: Dp
        )
    }

    @ConsistentCopyVisibility
    data class Icon internal constructor(
        val color: Color
    ) {

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val status: Status
            ) {

                @ConsistentCopyVisibility
                data class Status internal constructor(
                    val warning: Warning
                ) {

                    @ConsistentCopyVisibility
                    data class Warning internal constructor(
                        val externalShape: androidx.compose.ui.graphics.Color,
                        val internalShape: androidx.compose.ui.graphics.Color,
                        val inverse: Inverse
                    ) {

                        @ConsistentCopyVisibility
                        data class Inverse internal constructor(
                            val externalShape: androidx.compose.ui.graphics.Color,
                            val internalShape: androidx.compose.ui.graphics.Color
                        )
                    }
                }
            }
        }
    }

    @ConsistentCopyVisibility
    data class InputTag internal constructor(
        val border: Border,
        val color: Color
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val default: Dp,
                val defaultInteraction: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val border: Border,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val pressed: androidx.compose.ui.graphics.Color
            )

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val pressed: androidx.compose.ui.graphics.Color
            )

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val pressed: androidx.compose.ui.graphics.Color
            )
        }
    }

    @ConsistentCopyVisibility
    data class Link internal constructor(
        val color: Color,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val chevron: Chevron,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Chevron internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                @Deprecated("Please use color.content.focus instead.", ReplaceWith("OudsTheme.components.link.color.content.focus"))
                val focus: androidx.compose.ui.graphics.Color,
                @Deprecated("Please use color.content.hover instead.", ReplaceWith("OudsTheme.components.link.color.content.hover"))
                val hover: androidx.compose.ui.graphics.Color,
                @Deprecated("Please use color.content.pressed instead.", ReplaceWith("OudsTheme.components.link.color.content.pressed"))
                val pressed: androidx.compose.ui.graphics.Color
            )

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val pressed: androidx.compose.ui.graphics.Color
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val iconDefault: Dp,
            val iconSmall: Dp,
            val minHeightCompactDensity: Dp,
            val minHeightDefault: Dp,
            val minHeightSmall: Dp,
            val minWidth: Dp,
            @Deprecated("Please use minWidth instead.", ReplaceWith("OudsTheme.components.link.size.minWidth"))
            val minWidthDefault: Dp,
            @Deprecated("This token has been removed.")
            val minWidthSmall: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val paddingBlock: PaddingBlock,
            val paddingInline: Dp
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val chevronDefault: Dp,
                val chevronSmall: Dp,
                val iconDefault: Dp,
                val iconSmall: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val default: Dp,
                val small: Dp,
                val compactDensityDefault: Dp,
                val compactDensitySmall: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class LinkMonochrome internal constructor(
        val color: Color
    ) {

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val disabled: androidx.compose.ui.graphics.Color,
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val pressed: androidx.compose.ui.graphics.Color
            )
        }
    }

    @ConsistentCopyVisibility
    data class ListItem internal constructor(
        val border: Border,
        val color: Color,
        val font: Font,
        val opacity: Opacity,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val currentIndicator: Dp,
                val default: Dp,
                @Deprecated("") val itemOnly: Dp,
                val media: Dp,
                val mediaRounded: Dp,
                val rounded: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val currentPage: Dp,
                val default: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val background: Background,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
                val badge: Badge,
                val current: Current
            ) {

                @ConsistentCopyVisibility
                data class Badge internal constructor(
                    val safetyArea: androidx.compose.ui.graphics.Color,
                )

                @ConsistentCopyVisibility
                data class Current internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val current: Current
            ) {

                @ConsistentCopyVisibility
                data class Current internal constructor(
                    val disabled: androidx.compose.ui.graphics.Color,
                    val enabled: androidx.compose.ui.graphics.Color,
                    val focus: androidx.compose.ui.graphics.Color,
                    val hover: androidx.compose.ui.graphics.Color,
                    val pressed: androidx.compose.ui.graphics.Color
                )
            }
        }

        @ConsistentCopyVisibility
        data class Font internal constructor(
            val letterSpacing: LetterSpacing,
            val lineHeight: LineHeight,
            val size: Size
        ) {

            @ConsistentCopyVisibility
            data class LetterSpacing internal constructor(
                val avatarInitialExtraLarge: Dp
            )

            @ConsistentCopyVisibility
            data class LineHeight internal constructor(
                val avatarInitialExtraLarge: Dp
            )

            @ConsistentCopyVisibility
            data class Size internal constructor(
                val avatarInitialExtraLarge: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Opacity internal constructor(
            val currentDivider: Float,
            val currentIndicator: Float
        )

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val asset: Asset,
            val controlIndicator: Dp,
            val currentIndicator: CurrentIndicator,
            val flag: Flag,
            @Deprecated("") val icon: Dp,
            @Deprecated("") val loader: Dp,
            @Deprecated("") val maxHeightAssetsContainer: Dp,
            val maxSizeLeadingTrailingSlot: Dp,
            val maxWidth: Dp,
            val minHeightDefault: Dp,
            val minHeightSmall: Dp,
            val minWidth: Dp
        ) {

            @ConsistentCopyVisibility
            data class Asset internal constructor(
                val large: Dp,
                val medium: Dp,
                val small: Dp,
                val extraLarge: Dp
            )

            @ConsistentCopyVisibility
            data class Flag internal constructor(
                val height: Dp
            )

            @ConsistentCopyVisibility
            data class CurrentIndicator internal constructor(
                val width: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: Dp,
            val paddingBlock: PaddingBlock,
            val paddingInline: Dp,
            @Deprecated("") val paddingInlineErrorIcon: Dp,
            val rowGap: Dp
        ) {

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val bottomSlotListItemContainer: Dp,
                val default: Dp,
                val small: Dp,
                val slotTextContainer: Dp,
                val topAlignment: TopAlignment,
                val topHelperText: Dp
            ) {

                @ConsistentCopyVisibility
                data class TopAlignment internal constructor(
                    val topCounterweightDefault: Dp,
                    val topCounterweightSmall: Dp,
                    val topTextContainerDefault: Dp,
                    val topTextContainerSmall: Dp
                )
            }
        }
    }

    @ConsistentCopyVisibility
    data class PinCodeInput internal constructor(
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val maxWidth: Dp,
            val minWidth: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGapDigitInput: Dp
        )
    }

    @ConsistentCopyVisibility
    data class ProgressIndicator internal constructor(
        val border: Border,
        val color: Color,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val default: Dp,
                val rounded: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val track: androidx.compose.ui.graphics.Color
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val linearIndicatorHeight: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val paddingBlock: Dp
        )
    }

    @ConsistentCopyVisibility
    data class ProgressIndicatorMonochrome internal constructor(
        val color: Color
    ) {
        @ConsistentCopyVisibility
        data class Color internal constructor(
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val indicator: androidx.compose.ui.graphics.Color,
                val track: androidx.compose.ui.graphics.Color
            )
        }
    }

    @ConsistentCopyVisibility
    data class RadioButton internal constructor(
        val border: Border,
        val size: Size
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Dp,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val selected: Dp,
                val selectedFocus: Dp,
                val selectedHover: Dp,
                val selectedPressed: Dp,
                val unselected: Dp,
                val unselectedFocus: Dp,
                val unselectedHover: Dp,
                val unselectedPressed: Dp,
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val maxHeight: Dp,
            val minHeight: Dp,
            val minWidth: Dp
        )
    }

    @ConsistentCopyVisibility
    data class Switch internal constructor(
        val border: Border,
        val color: Color,
        val opacity: Opacity,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radiusCursor: Dp,
            val radiusTrack: Dp
        )

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val check: androidx.compose.ui.graphics.Color,
            val checkInteraction: androidx.compose.ui.graphics.Color,
            val cursor: androidx.compose.ui.graphics.Color,
            val track: Track
        ) {

            @ConsistentCopyVisibility
            data class Track internal constructor(
                val selected: androidx.compose.ui.graphics.Color,
                val selectedInteraction: androidx.compose.ui.graphics.Color,
                val unselected: androidx.compose.ui.graphics.Color,
                val unselectedInteraction: androidx.compose.ui.graphics.Color
            )
        }

        @ConsistentCopyVisibility
        data class Opacity internal constructor(
            val check: Float
        )

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val height: Height,
            val maxHeight: Dp,
            val minHeight: Dp,
            val minHeightInteractiveArea: Dp,
            val minWidth: Dp,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Height internal constructor(
                val cursorSelected: Dp,
                val cursorUnselected: Dp,
                val track: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val cursorSelected: Dp,
                val cursorUnselected: Dp,
                val cursorSelectedPressed: Dp,
                val cursorUnselectedPressed: Dp,
                val track: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val paddingInline: PaddingInline
        ) {

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val selected: Dp,
                val unselected: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class Tag internal constructor(
        val border: Border,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Dp
        )

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val assetDefault: Dp,
            val assetSmall: Dp,
            val minHeightDefault: Dp,
            val minHeightSmall: Dp,
            val minWidthDefault: Dp,
            val minWidthSmall: Dp,
            val minHeightInteractiveArea: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val paddingInline: PaddingInline,
            val paddingBlock: PaddingBlock,
            val inset: Inset,
            val columnGap: ColumnGap
        ) {

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val small: Dp,
                val default: Dp,
                val assetSmall: Dp,
                val assetDefault: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val small: Dp,
                val default: Dp
            )

            @ConsistentCopyVisibility
            data class Inset internal constructor(
                val iconSmall: Dp,
                val bulletSmall: Dp,
                val loaderSmall: Dp,
                val iconDefault: Dp,
                val bulletDefault: Dp,
                val loaderDefault: Dp
            )

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val small: Dp,
                val default: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class TextArea internal constructor(
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val maxHeightInput: Dp,
            val maxHeightAssetsContainer: Dp,
            val maxWidth: Dp,
            val minHeightInput: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val paddingBlock: Dp,
            val paddingBlockTopEmpty: Dp,
            val paddingBlockTrailingContainer: Dp,
            val paddingBlockEmptyTrailingContainer: Dp
        )
    }

    @ConsistentCopyVisibility
    data class TextInput internal constructor(
        val border: Border,
        val color: Color,
        val size: Size,
        val space: Space
    ) {

        @ConsistentCopyVisibility
        data class Border internal constructor(
            val radius: Radius,
            val width: Width
        ) {

            @ConsistentCopyVisibility
            data class Radius internal constructor(
                val default: Dp,
                val rounded: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
                val default: Dp,
                val focus: Dp
            )
        }

        @ConsistentCopyVisibility
        data class Color internal constructor(
            val border: Border
        ) {

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val enabled: androidx.compose.ui.graphics.Color,
                val focus: androidx.compose.ui.graphics.Color,
                val hover: androidx.compose.ui.graphics.Color,
                val loading: androidx.compose.ui.graphics.Color
            )
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val countrySelectorFlagHeight: Dp,
            val labelMaxHeight: Dp,
            val leadingIcon: Dp,
            val maxWidth: Dp,
            val minHeight: Dp,
            val minWidth: Dp,
            val verticalDividerHeight: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val paddingBlock: PaddingBlock,
            val paddingInline: PaddingInline,
            val rowGap: RowGap
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val default: Dp,
                val inlineText: Dp,
                val labelAsterisk: Dp,
                val labelSmallAsterisk: Dp,
                val trailingErrorAction: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val default: Dp,
                val topHelperText: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val countrySelectorEnd: Dp,
                val countrySelectorStart: Dp,
                val default: Dp,
                val trailingAction: Dp
            )

            @ConsistentCopyVisibility
            data class RowGap internal constructor(
                val labelInput: Dp
            )
        }
    }

    @ConsistentCopyVisibility
    data class Typography internal constructor(
        val headingLargeMarker: Boolean,
        val space: Space
    ) {
        @ConsistentCopyVisibility
        data class Space internal constructor(
            val paddingBlock: PaddingBlock,
        ) {
            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val bottomHeadingLargeMarker: Dp,
                val topHeadingLargeMarker: Dp
            )
        }
    }
}

@Composable
internal fun OudsComponentsTokens.getComponents(): OudsComponents {
    return OudsComponents(
        alert = alert.getAlert(),
        badge = badge.getBadge(),
        bar = bar.getBar(),
        bulletList = bulletList.getBulletList(),
        button = button.getButton(),
        buttonMonochrome = buttonMonochrome.getButtonMonochrome(),
        checkbox = checkbox.getCheckbox(),
        chip = chip.getChip(),
        controlItem = getControlItem(listItem, accordion),
        divider = divider.getDivider(),
        icon = icon.getIcon(),
        inputTag = inputTag.getInputTag(),
        link = link.getLink(),
        linkMonochrome = linkMonochrome.getLinkMonochrome(),
        listItem = listItem.getListItem(),
        pinCodeInput = pinCodeInput.getPinCodeInput(),
        progressIndicator = progressIndicator.getProgressIndicator(),
        progressIndicatorMonochrome = progressIndicatorMonochrome.getProgressIndicatorMonochrome(),
        radioButton = radioButton.getRadioButton(),
        switch = switch.getSwitch(),
        tag = tag.getTag(),
        textArea = textArea.getTextArea(),
        textInput = textInput.getTextInput(),
        typography = typography.getTypography()
    )
}

@Composable
private fun OudsAlertTokens.getAlert(): OudsComponents.Alert {
    return OudsComponents.Alert(
        border = OudsComponents.Alert.Border(
            radius = OudsComponents.Alert.Border.Radius(
                default = borderRadiusDefault.value,
                rounded = borderRadiusRounded.value
            ),
            width = borderWidth.value
        ),
        size = OudsComponents.Alert.Size(
            icon = sizeIcon.value,
            minHeight = sizeMinHeight.value,
            minHeightBottomActionPlacement = sizeMinHeightBottomActionPlacement.dp,
            minWidth = sizeMinWidth.dp
        ),
        space = OudsComponents.Alert.Space(
            paddingBlock = spacePaddingBlock.value,
            paddingInline = spacePaddingInline.value,
            columnGap = spaceColumnGap.value,
            columnGapAction = spaceColumnGapAction.value,
            rowGap = spaceRowGap.value,
            rowGapAction = spaceRowGapAction.value,
            rowGapBullet = spaceRowGapBullet.value
        )
    )
}

@Composable
private fun OudsBadgeTokens.getBadge(): OudsComponents.Badge {
    return OudsComponents.Badge(
        size = OudsComponents.Badge.Size(
            large = sizeLarge.dp,
            medium = sizeMedium.dp,
            small = sizeSmall.dp,
            extraSmall = sizeXsmall.dp
        ),
        space = OudsComponents.Badge.Space(
            insetExtraSmall = spaceInsetXsmall.dp,
            insetSmall = spaceInsetSmall.dp,
            insetMediumLarge = spaceInsetMediumLarge.dp,
            paddingInline = OudsComponents.Badge.Space.PaddingInline(
                large = spacePaddingInlineLarge.value,
                medium = spacePaddingInlineMedium.value
            )
        )
    )
}

@Composable
private fun OudsBarTokens.getBar(): OudsComponents.Bar {
    return OudsComponents.Bar(
        border = OudsComponents.Bar.Border(
            radius = OudsComponents.Bar.Border.Radius(
                currentIndicatorCustom = OudsComponents.Bar.Border.Radius.CurrentIndicatorCustom(
                    bottom = borderRadiusCurrentIndicatorCustomBottom.value,
                    top = borderRadiusCurrentIndicatorCustomTop.value
                )
            )
        ),
        color = OudsComponents.Bar.Color(
            background = OudsComponents.Bar.Color.Background(
                opaque = colorBgOpaque.value,
                translucent = colorBgTranslucent.value
            ),
            border = OudsComponents.Bar.Color.Border(
                badge = colorBorderBadge.value
            ),
            content = OudsComponents.Bar.Color.Content(
                selected = OudsComponents.Bar.Color.Content.Selected(
                    enabled = colorContentSelectedEnabled.value,
                    focus = colorContentSelectedFocus.value,
                    hover = colorContentSelectedHover.value,
                    pressed = colorContentSelectedPressed.value
                ),
                unselected = OudsComponents.Bar.Color.Content.Unselected(
                    enabled = colorContentUnselectedEnabled.value,
                    focus = colorContentUnselectedFocus.value,
                    hover = colorContentUnselectedHover.value,
                    pressed = colorContentUnselectedPressed.value
                )
            ),
            currentIndicator = OudsComponents.Bar.Color.CurrentIndicator(
                android = OudsComponents.Bar.Color.CurrentIndicator.Android(
                    selected = OudsComponents.Bar.Color.CurrentIndicator.Android.Selected(
                        disabled = colorCurrentIndicatorAndroidSelectedDisabled.value,
                        enabled = colorCurrentIndicatorAndroidSelectedEnabled.value,
                        focus = colorCurrentIndicatorAndroidSelectedFocus.value,
                        hover = colorCurrentIndicatorAndroidSelectedHover.value,
                        pressed = colorCurrentIndicatorAndroidSelectedPressed.value
                    ),
                    unselected = OudsComponents.Bar.Color.CurrentIndicator.Android.Unselected(
                        disabled = colorCurrentIndicatorAndroidUnselectedDisabled.value,
                        focus = colorCurrentIndicatorAndroidUnselectedFocus.value,
                        hover = colorCurrentIndicatorAndroidUnselectedHover.value,
                        pressed = colorCurrentIndicatorAndroidUnselectedPressed.value
                    )
                ),
                custom = OudsComponents.Bar.Color.CurrentIndicator.Custom(
                    selected = OudsComponents.Bar.Color.CurrentIndicator.Custom.Selected(
                        enabled = colorCurrentIndicatorCustomSelectedEnabled.value,
                        focus = colorCurrentIndicatorCustomSelectedFocus.value,
                        hover = colorCurrentIndicatorCustomSelectedHover.value,
                        pressed = colorCurrentIndicatorCustomSelectedPressed.value
                    )
                )
            )
        ),
        effect = OudsComponents.Bar.Effect(
            backgroundBlur = effectBgBlur
        ),
        opacity = OudsComponents.Bar.Opacity(
            currentIndicatorCustom = opacityCurrentIndicatorCustom.value
        ),
        size = OudsComponents.Bar.Size(
            height = OudsComponents.Bar.Size.Height(
                currentIndicatorCustom = sizeHeightCurrentIndicatorCustom.dp
            ),
            width = OudsComponents.Bar.Size.Width(
                currentIndicatorCustom = OudsComponents.Bar.Size.Width.CurrentIndicatorCustom(
                    bottom = sizeWidthCurrentIndicatorCustomBottom.dp,
                    top = sizeWidthCurrentIndicatorCustomTop.dp
                )
            )
        ),
        blurRadius = effectBgBlur
    )
}

@Composable
private fun OudsBulletListTokens.getBulletList(): OudsComponents.BulletList {
    return OudsComponents.BulletList(
        space = OudsComponents.BulletList.Space(
            columnGap = OudsComponents.BulletList.Space.ColumnGap(
                bodyLarge = spaceColumnGapBodyLarge.value,
                bodyMedium = spaceColumnGapBodyMedium.value
            ),
            paddingBlock = OudsComponents.BulletList.Space.PaddingBlock(
                bodyLarge = spacePaddingBlockBodyLarge.value,
                bodyMedium = spacePaddingBlockBodyMedium.value
            ),
            paddingInline = OudsComponents.BulletList.Space.PaddingInline(
                level0 = spacePaddingInlineLevel0.dp,
                level1 = spacePaddingInlineLevel1.dp,
                level2 = spacePaddingInlineLevel2.dp
            )
        )
    )
}

@Composable
private fun OudsButtonTokens.getButton(): OudsComponents.Button {
    return OudsComponents.Button(
        border = OudsComponents.Button.Border(
            radius = OudsComponents.Button.Border.Radius(
                aiIconOnly = borderRadiusAiIconOnly.value,
                default = borderRadiusDefault.value,
                rounded = borderRadiusRounded.value,
                social = borderRadiusSocial.value
            ),
            width = OudsComponents.Button.Border.Width(
                ai = borderWidthAi.value,
                aiInteraction = borderWidthAiInteraction.value,
                default = borderWidthDefault.value,
                defaultInteraction = borderWidthDefaultInteraction.value,
                defaultInteractionMonochrome = borderWidthDefaultInteractionMono.value
            )
        ),
        color = OudsComponents.Button.Color(
            background = OudsComponents.Button.Color.Background(
                brand = OudsComponents.Button.Color.Background.Brand(
                    enabled = colorBgBrandEnabled.value,
                    hover = colorBgBrandHover.value,
                    pressed = colorBgBrandPressed.value,
                    loading = colorBgBrandLoading.value,
                    focus = colorBgBrandFocus.value
                ),
                default = OudsComponents.Button.Color.Background.Default(
                    enabled = colorBgDefaultEnabled.value,
                    hover = colorBgDefaultHover.value,
                    pressed = colorBgDefaultPressed.value,
                    loading = colorBgDefaultLoading.value,
                    disabled = colorBgDefaultDisabled.value,
                    focus = colorBgDefaultFocus.value
                ),
                minimal = OudsComponents.Button.Color.Background.Minimal(
                    hover = colorBgMinimalHover.value,
                    pressed = colorBgMinimalPressed.value,
                    focus = colorBgMinimalFocus.value
                ),
                ai = OudsComponents.Button.Color.Background.Ai(
                    enabled = colorBgAiEnabled.value,
                    hover = colorBgAiHover.value,
                    pressed = colorBgAiPressed.value,
                    loading = colorBgAiLoading.value,
                    disabled = colorBgAiDisabled.value,
                    focus = colorBgAiFocus.value
                )
            ),
            border = OudsComponents.Button.Color.Border(
                default = OudsComponents.Button.Color.Border.Default(
                    enabled = colorBorderDefaultEnabled.value,
                    hover = colorBorderDefaultHover.value,
                    pressed = colorBorderDefaultPressed.value,
                    loading = colorBorderDefaultLoading.value,
                    disabled = colorBorderDefaultDisabled.value,
                    focus = colorBorderDefaultFocus.value
                ),
                ai = OudsComponents.Button.Color.Border.Ai(
                    enabled = colorBorderAiEnabled.value,
                    hover = colorBorderAiHover.value,
                    pressed = colorBorderAiPressed.value,
                    loading = colorBorderAiLoading.value,
                    disabled = colorBorderAiDisabled.value,
                    focus = colorBorderAiFocus.value
                )
            ),
            content = OudsComponents.Button.Color.Content(
                brand = OudsComponents.Button.Color.Content.Brand(
                    enabled = colorContentBrandEnabled.value,
                    hover = colorContentBrandHover.value,
                    pressed = colorContentBrandPressed.value,
                    loading = colorContentBrandLoading.value,
                    focus = colorContentBrandFocus.value
                ),
                default = OudsComponents.Button.Color.Content.Default(
                    enabled = colorContentDefaultEnabled.value,
                    hover = colorContentDefaultHover.value,
                    pressed = colorContentDefaultPressed.value,
                    loading = colorContentDefaultLoading.value,
                    disabled = colorContentDefaultDisabled.value,
                    focus = colorContentDefaultFocus.value
                ),
                minimal = OudsComponents.Button.Color.Content.Minimal(
                    enabled = colorContentMinimalEnabled.value,
                    hover = colorContentMinimalHover.value,
                    pressed = colorContentMinimalPressed.value,
                    loading = colorContentMinimalLoading.value,
                    disabled = colorContentMinimalDisabled.value,
                    focus = colorContentMinimalFocus.value
                ),
                ai = OudsComponents.Button.Color.Content.Ai(
                    enabled = colorContentAiEnabled.value,
                    hover = colorContentAiHover.value,
                    pressed = colorContentAiPressed.value,
                    loading = colorContentAiLoading.value,
                    disabled = colorContentAiDisabled.value,
                    focus = colorContentAiFocus.value
                )
            )
        ),
        size = OudsComponents.Button.Size(
            icon = sizeIconDefault.value,
            iconDefault = sizeIconDefault.value,
            iconSmall = sizeIconSmall.value,
            iconOnly = sizeIconOnlyDefault.value,
            iconOnlyDefault = sizeIconOnlyDefault.value,
            iconOnlySmall = sizeIconOnlySmall.value,
            loader = sizeProgressIndicatorDefault.value,
            maxHeightIconOnly = sizeMaxSizeIconOnlyDefault.value,
            maxSizeIconOnlyDefault = sizeMaxSizeIconOnlyDefault.value,
            maxSizeIconOnlySmall = sizeMaxSizeIconOnlySmall.value,
            minHeight = sizeMinHeightDefault.value,
            minHeightDefault = sizeMinHeightDefault.value,
            minHeightSmall = sizeMinHeightSmall.value,
            minWidth = sizeMinWidthDefault.value,
            minWidthDefault = sizeMinWidthDefault.value,
            minWidthSmall = sizeMinWidthSmall.value,
            progressIndicatorDefault = sizeProgressIndicatorDefault.value,
            progressIndicatorSmall = sizeProgressIndicatorSmall.value
        ),
        space = OudsComponents.Button.Space(
            columnGap = OudsComponents.Button.Space.ColumnGap(
                chevron = spaceColumnGapChevronDefault.value,
                chevronDefault = spaceColumnGapChevronDefault.value,
                chevronSmall = spaceColumnGapChevronSmall.value,
                icon = spaceColumnGapIconDefault.value,
                iconDefault = spaceColumnGapIconDefault.value,
                iconSmall = spaceColumnGapIconSmall.value,
                iconChevron = spaceColumnGapIconChevronDefault.value,
                iconChevronDefault = spaceColumnGapIconChevronDefault.value,
                iconChevronSmall = spaceColumnGapIconChevronSmall.value
            ),
            insetIconOnly = spaceInsetIconOnlyDefault.value,
            inset = OudsComponents.Button.Space.Inset(
                iconOnlyDefault = spaceInsetIconOnlyDefault.value,
                iconOnlySmall = spaceInsetIconOnlySmall.value,
                progressIndicatorOnlyDefault = spaceInsetProgressIndicatorOnlyDefault.value,
                progressIndicatorOnlySmall = spaceInsetProgressIndicatorOnlySmall.value
            ),
            paddingBlock = spacePaddingBlockDefault.value,
            paddingBlockDefault = spacePaddingBlockDefault.value,
            paddingBlockSmall = spacePaddingBlockSmall.value,
            paddingInline = OudsComponents.Button.Space.PaddingInline(
                chevronEnd = spacePaddingInlineChevronEndDefault.value,
                chevronEndDefault = spacePaddingInlineChevronEndDefault.value,
                chevronEndSmall = spacePaddingInlineChevronEndSmall.value,
                chevronStart = spacePaddingInlineChevronStartDefault.value,
                chevronStartDefault = spacePaddingInlineChevronStartDefault.value,
                chevronStartSmall = spacePaddingInlineChevronStartSmall.value,
                endIconStart = spacePaddingInlineEndIconStartDefault.value,
                endIconStartDefault = spacePaddingInlineEndIconStartDefault.value,
                endIconStartSmall = spacePaddingInlineEndIconStartSmall.value,
                iconNone = spacePaddingInlineIconNoneDefault.value,
                iconNoneDefault = spacePaddingInlineIconNoneDefault.value,
                iconNoneSmall = spacePaddingInlineIconNoneSmall.value,
                iconStart = spacePaddingInlineIconStartDefault.value,
                iconStartDefault = spacePaddingInlineIconStartDefault.value,
                iconStartSmall = spacePaddingInlineIconStartSmall.value,
                startIconEnd = spacePaddingInlineStartIconEndDefault.value,
                startIconEndDefault = spacePaddingInlineStartIconEndDefault.value,
                startIconEndSmall = spacePaddingInlineStartIconEndSmall.value
            )
        )
    )
}

@Composable
private fun OudsButtonMonoTokens.getButtonMonochrome(): OudsComponents.ButtonMonochrome {
    return OudsComponents.ButtonMonochrome(
        color = OudsComponents.ButtonMonochrome.Color(
            background = OudsComponents.ButtonMonochrome.Color.Background(
                default = OudsComponents.ButtonMonochrome.Color.Background.Default(
                    disabled = colorBgDefaultDisabled.value,
                    enabled = colorBgDefaultEnabled.value,
                    focus = colorBgDefaultFocus.value,
                    hover = colorBgDefaultHover.value,
                    loading = colorBgDefaultLoading.value,
                    pressed = colorBgDefaultPressed.value
                ),
                minimal = OudsComponents.ButtonMonochrome.Color.Background.Minimal(
                    focus = colorBgMinimalFocus.value,
                    hover = colorBgMinimalHover.value,
                    pressed = colorBgMinimalPressed.value
                ),
                strong = OudsComponents.ButtonMonochrome.Color.Background.Strong(
                    disabled = colorBgStrongDisabled.value,
                    enabled = colorBgStrongEnabled.value,
                    focus = colorBgStrongFocus.value,
                    hover = colorBgStrongHover.value,
                    loading = colorBgStrongLoading.value,
                    pressed = colorBgStrongPressed.value
                )
            ),
            border = OudsComponents.ButtonMonochrome.Color.Border(
                default = OudsComponents.ButtonMonochrome.Color.Border.Default(
                    disabled = colorBorderDefaultDisabled.value,
                    enabled = colorBorderDefaultEnabled.value,
                    focus = colorBorderDefaultFocus.value,
                    hover = colorBorderDefaultHover.value,
                    loading = colorBorderDefaultLoading.value,
                    pressed = colorBorderDefaultPressed.value
                )
            ),
            content = OudsComponents.ButtonMonochrome.Color.Content(
                default = OudsComponents.ButtonMonochrome.Color.Content.Default(
                    disabled = colorContentDefaultDisabled.value,
                    enabled = colorContentDefaultEnabled.value,
                    focus = colorContentDefaultFocus.value,
                    hover = colorContentDefaultHover.value,
                    loading = colorContentDefaultLoading.value,
                    pressed = colorContentDefaultPressed.value
                ),
                minimal = OudsComponents.ButtonMonochrome.Color.Content.Minimal(
                    disabled = colorContentMinimalDisabled.value,
                    enabled = colorContentMinimalEnabled.value,
                    focus = colorContentMinimalFocus.value,
                    hover = colorContentMinimalHover.value,
                    loading = colorContentMinimalLoading.value,
                    pressed = colorContentMinimalPressed.value
                ),
                strong = OudsComponents.ButtonMonochrome.Color.Content.Strong(
                    disabled = colorContentStrongDisabled.value,
                    enabled = colorContentStrongEnabled.value,
                    focus = colorContentStrongFocus.value,
                    hover = colorContentStrongHover.value,
                    loading = colorContentStrongLoading.value,
                    pressed = colorContentStrongPressed.value
                )
            )
        )
    )
}

@Composable
private fun OudsCheckboxTokens.getCheckbox(): OudsComponents.Checkbox {
    return OudsComponents.Checkbox(
        border = OudsComponents.Checkbox.Border(
            radius = borderRadius.value,
            width = OudsComponents.Checkbox.Border.Width(
                selected = borderWidthSelected.value,
                selectedFocus = borderWidthSelectedFocus.value,
                selectedHover = borderWidthSelectedHover.value,
                selectedPressed = borderWidthSelectedPressed.value,
                unselected = borderWidthUnselected.value,
                unselectedFocus = borderWidthUnselectedFocus.value,
                unselectedHover = borderWidthUnselectedHover.value,
                unselectedPressed = borderWidthUnselectedPressed.value
            )
        ),
        size = OudsComponents.Checkbox.Size(
            indicator = sizeIndicator.value,
            maxHeight = sizeMaxHeight.value,
            minHeight = sizeMinHeight.value,
            minWidth = sizeMinWidth.value
        )
    )
}

@Composable
private fun OudsChipTokens.getChip(): OudsComponents.Chip {
    return OudsComponents.Chip(
        badge = OudsComponents.Chip.Badge(
            color = OudsComponents.Chip.Badge.Color(
                background = badgeColorBg.value,
                content = badgeColorContent.value
            )
        ),
        border = OudsComponents.Chip.Border(
            radius = borderRadius.value,
            width = OudsComponents.Chip.Border.Width(
                selected = borderWidthSelected.value,
                unselected = borderWidthUnselected.value,
                unselectedInteraction = borderWidthUnselectedInteraction.value
            )
        ),
        color = OudsComponents.Chip.Color(
            background = OudsComponents.Chip.Color.Background(
                selected = OudsComponents.Chip.Color.Background.Selected(
                    disabled = colorBgSelectedDisabled.value,
                    enabled = colorBgSelectedEnabled.value,
                    focus = colorBgSelectedFocus.value,
                    hover = colorBgSelectedHover.value,
                    pressed = colorBgSelectedPressed.value
                ),
                unselected = OudsComponents.Chip.Color.Background.Unselected(
                    disabled = colorBgUnselectedDisabled.value,
                    enabled = colorBgUnselectedEnabled.value,
                    focus = colorBgUnselectedFocus.value,
                    hover = colorBgUnselectedHover.value,
                    pressed = colorBgUnselectedPressed.value
                )
            ),
            border = OudsComponents.Chip.Color.Border(
                selected = OudsComponents.Chip.Color.Border.Selected(
                    disabled = colorBorderSelectedDisabled.value,
                    enabled = colorBorderSelectedEnabled.value,
                    focus = colorBorderSelectedFocus.value,
                    hover = colorBorderSelectedHover.value,
                    pressed = colorBorderSelectedPressed.value
                ),
                unselected = OudsComponents.Chip.Color.Border.Unselected(
                    disabled = colorBorderUnselectedDisabled.value,
                    enabled = colorBorderUnselectedEnabled.value,
                    focus = colorBorderUnselectedFocus.value,
                    hover = colorBorderUnselectedHover.value,
                    pressed = colorBorderUnselectedPressed.value
                )
            ),
            content = OudsComponents.Chip.Color.Content(
                selected = OudsComponents.Chip.Color.Content.Selected(
                    disabled = colorContentSelectedDisabled.value,
                    enabled = colorContentSelectedEnabled.value,
                    focus = colorContentSelectedFocus.value,
                    hover = colorContentSelectedHover.value,
                    pressed = colorContentSelectedPressed.value,
                    tickEnabled = colorContentSelectedTickEnabled.value
                ),
                unselected = OudsComponents.Chip.Color.Content.Unselected(
                    disabled = colorContentUnselectedDisabled.value,
                    enabled = colorContentUnselectedEnabled.value,
                    focus = colorContentUnselectedFocus.value,
                    hover = colorContentUnselectedHover.value,
                    pressed = colorContentUnselectedPressed.value
                )
            )
        ),
        size = OudsComponents.Chip.Size(
            icon = sizeIcon.value,
            minHeight = sizeMinHeight.dp,
            minHeightInteractiveArea = sizeMinHeightInteractiveArea.value,
            minWidth = sizeMinWidth.dp
        ),
        space = OudsComponents.Chip.Space(
            columnGap = OudsComponents.Chip.Space.ColumnGap(
                badgeChevron = spaceColumnGapBadgeChevron.value,
                icon = spaceColumnGapIcon.value
            ),
            paddingBlock = spacePaddingBlock.value,
            paddingBlockIconOnly = spacePaddingBlockIconOnly.value,
            paddingInline = OudsComponents.Chip.Space.PaddingInline(
                badgeStart = spacePaddingInlineBadgeStart.value,
                chevronEnd = spacePaddingInlineChevronEnd.value,
                icon = spacePaddingInlineIcon.value,
                iconNone = spacePaddingInlineIconNone.value
            )
        )
    )
}

@Composable
private fun getControlItem(listItemTokens: OudsListItemTokens, accordionTokens: OudsAccordionTokens): OudsComponents.ControlItem {
    return with(listItemTokens) {
        OudsComponents.ControlItem(
            border = OudsComponents.ControlItem.Border(
                radius = OudsComponents.ControlItem.Border.Radius(
                    currentIndicator = borderRadiusCurrentIndicator.value,
                    default = borderRadiusDefault.value,
                    media = borderRadiusMedia.value,
                    mediaRoundedCorner = borderRadiusMediaRounded.value,
                    rounded = borderRadiusRounded.value
                ),
                width = OudsComponents.ControlItem.Border.Width(
                    currentPage = borderWidthCurrentPage.value,
                    default = borderWidthDefault.value
                )
            ),
            color = OudsComponents.ControlItem.Color(
                badgeSafetyArea = colorBgBadgeSafetyArea.value,
                background = OudsComponents.ControlItem.Color.Background(
                    current = OudsComponents.ControlItem.Color.Background.Current(
                        disabled = colorBgCurrentDisabled.value,
                        enabled = colorBgCurrentEnabled.value,
                        focus = colorBgCurrentFocus.value,
                        hover = colorBgCurrentHover.value,
                        pressed = colorBgCurrentPressed.value
                    )
                ),
                content = OudsComponents.ControlItem.Color.Content(
                    current = OudsComponents.ControlItem.Color.Content.Current(
                        disabled = colorContentCurrentDisabled.value,
                        enabled = colorContentCurrentEnabled.value,
                        focus = colorContentCurrentFocus.value,
                        hover = colorContentCurrentHover.value,
                        pressed = colorContentCurrentPressed.value
                    )
                )
            ),
            font = OudsComponents.ControlItem.Font(
                letterSpacing = OudsComponents.ControlItem.Font.LetterSpacing(
                    avatarInitialExtraLarge = fontLetterSpacingAvatarInitialXlarge.dp
                ),
                lineHeight = OudsComponents.ControlItem.Font.LineHeight(
                    avatarInitialExtraLarge = fontLineHeightAvatarInitialXlarge.dp
                ),
                size = OudsComponents.ControlItem.Font.Size(
                    avatarInitialExtraLarge = fontSizeAvatarInitialXlarge.dp
                )
            ),
            opacity = OudsComponents.ControlItem.Opacity(
                currentDivider = opacityCurrentDivider.value,
                currentIndicator = opacityCurrentIndicator.value
            ),
            size = OudsComponents.ControlItem.Size(
                asset = OudsComponents.ControlItem.Size.Asset(
                    large = sizeAssetLarge.dp,
                    medium = sizeAssetMedium.value,
                    small = sizeAssetSmall.value,
                    extraLarge = sizeAssetXlarge.dp
                ),
                controlIndicator = sizeControlIndicator.value,
                currentIndicator = OudsComponents.ControlItem.Size.CurrentIndicator(
                    width = sizeCurrentIndicatorWidth.dp
                ),
                minHeightCompact = sizeMinHeightSmall.value,
                minHeightDefault = sizeMinHeightDefault.dp,
                minWidth = sizeMinWidth.dp,
                maxWidth = sizeMaxWidth.dp,
                flag = OudsComponents.ControlItem.Size.Flag(
                    height = sizeFlagHeight.value
                )
            ),
            space = OudsComponents.ControlItem.Space(
                paddingInline = spacePaddingInline.value,
                columnGap = spaceColumnGap.value,
                rowGap = spaceRowGap.value,
                paddingBlock = OudsComponents.ControlItem.Space.PaddingBlock(
                    topHelperText = spacePaddingBlockTopHelperText.value,
                    bottomSlot = spacePaddingBlockSlotTextContainer.value,
                    densityCompact = spacePaddingBlockSmall.value,
                    densityCompactTopAlignmentTopCounterweight = spacePaddingBlockTopAlignmentTopCounterweightSmall.value,
                    densityCompactTopAlignmentTopTextContainer = spacePaddingBlockTopAlignmentTopTextContainerSmall.value,
                    densityCompactBottomExpandContainer = accordionTokens.spacePaddingBlockBottomExpandContainerSmall.value,
                    densityDefault = spacePaddingBlockDefault.value,
                    densityDefaultTopAlignmentTopCounterweight = spacePaddingBlockTopAlignmentTopCounterweightDefault.value,
                    densityDefaultTopAlignmentTopTextContainer = spacePaddingBlockTopAlignmentTopTextContainerDefault.value,
                    densityDefaultBottomExpandContainer = accordionTokens.spacePaddingBlockBottomExpandContainerDefault.value,
                )
            )
        )
    }
}

@Composable
private fun OudsDividerTokens.getDivider(): OudsComponents.Divider {
    return OudsComponents.Divider(
        border = OudsComponents.Divider.Border(
            width = borderWidth.value
        )
    )
}

@Composable
private fun OudsIconTokens.getIcon(): OudsComponents.Icon {
    return OudsComponents.Icon(
        color = OudsComponents.Icon.Color(
            content = OudsComponents.Icon.Color.Content(
                status = OudsComponents.Icon.Color.Content.Status(
                    warning = OudsComponents.Icon.Color.Content.Status.Warning(
                        externalShape = colorContentStatusWarningExternalShape.value,
                        internalShape = colorContentStatusWarningInternalShape.value,
                        inverse = OudsComponents.Icon.Color.Content.Status.Warning.Inverse(
                            externalShape = colorContentStatusWarningInverseExternalShape.value,
                            internalShape = colorContentStatusWarningInverseInternalShape.value
                        )
                    )
                )
            )
        )
    )
}

@Composable
private fun OudsInputTagTokens.getInputTag(): OudsComponents.InputTag {
    return OudsComponents.InputTag(
        border = OudsComponents.InputTag.Border(
            width = OudsComponents.InputTag.Border.Width(
                default = borderWidthDefault.value,
                defaultInteraction = borderWidthDefaultInteraction.value
            )
        ),
        color = OudsComponents.InputTag.Color(
            background = OudsComponents.InputTag.Color.Background(
                enabled = colorBgEnabled.value,
                focus = colorBgFocus.value,
                hover = colorBgHover.value,
                pressed = colorBgPressed.value
            ),
            border = OudsComponents.InputTag.Color.Border(
                enabled = colorBorderEnabled.value,
                focus = colorBorderFocus.value,
                hover = colorBorderHover.value,
                pressed = colorBorderPressed.value
            ),
            content = OudsComponents.InputTag.Color.Content(
                enabled = colorContentEnabled.value,
                focus = colorContentFocus.value,
                hover = colorContentHover.value,
                pressed = colorContentPressed.value
            )
        )
    )
}

@Composable
private fun OudsLinkTokens.getLink(): OudsComponents.Link {
    return OudsComponents.Link(
        color = OudsComponents.Link.Color(
            chevron = OudsComponents.Link.Color.Chevron(
                enabled = colorChevronEnabled.value,
                focus = colorChevronFocus.value,
                hover = colorChevronHover.value,
                pressed = colorChevronPressed.value
            ),
            content = OudsComponents.Link.Color.Content(
                enabled = colorContentEnabled.value,
                focus = colorContentFocus.value,
                hover = colorContentHover.value,
                pressed = colorContentPressed.value
            )
        ),
        size = OudsComponents.Link.Size(
            iconDefault = sizeIconDefault.value,
            iconSmall = sizeIconSmall.value,
            minHeightCompactDensity = sizeMinHeightCompactDensity.dp,
            minHeightDefault = sizeMinHeightDefault.value,
            minHeightSmall = sizeMinHeightSmall.value,
            minWidth = sizeMinWidth.dp,
            minWidthDefault = sizeMinWidth.dp,
            minWidthSmall = sizeMinWidthSmall.value
        ),
        space = OudsComponents.Link.Space(
            columnGap = OudsComponents.Link.Space.ColumnGap(
                chevronDefault = spaceColumnGapChevronDefault.value,
                chevronSmall = spaceColumnGapChevronSmall.value,
                iconDefault = spaceColumnGapIconDefault.value,
                iconSmall = spaceColumnGapIconSmall.value
            ),
            paddingBlock = OudsComponents.Link.Space.PaddingBlock(
                default = spacePaddingBlockDefault.value,
                small = spacePaddingBlockSmall.value,
                compactDensityDefault = spacePaddingBlockCompactDensityDefault.value,
                compactDensitySmall = spacePaddingBlockCompactDensitySmall.value
            ),
            paddingInline = spacePaddingInline.value
        )
    )
}

@Composable
private fun OudsLinkMonoTokens.getLinkMonochrome(): OudsComponents.LinkMonochrome {
    return OudsComponents.LinkMonochrome(
        color = OudsComponents.LinkMonochrome.Color(
            content = OudsComponents.LinkMonochrome.Color.Content(
                disabled = colorContentDisabled.value,
                enabled = colorContentEnabled.value,
                focus = colorContentFocus.value,
                hover = colorContentHover.value,
                pressed = colorContentPressed.value
            )
        )
    )
}

@Composable
private fun OudsListItemTokens.getListItem(): OudsComponents.ListItem {
    return OudsComponents.ListItem(
        border = OudsComponents.ListItem.Border(
            radius = OudsComponents.ListItem.Border.Radius(
                currentIndicator = borderRadiusCurrentIndicator.value,
                default = borderRadiusDefault.value,
                itemOnly = borderRadiusItemOnly.value,
                media = borderRadiusMedia.value,
                mediaRounded = borderRadiusMediaRounded.value,
                rounded = borderRadiusRounded.value
            ),
            width = OudsComponents.ListItem.Border.Width(
                currentPage = borderWidthCurrentPage.value,
                default = borderWidthDefault.value
            )
        ),
        color = OudsComponents.ListItem.Color(
            background = OudsComponents.ListItem.Color.Background(
                badge = OudsComponents.ListItem.Color.Background.Badge(
                    safetyArea = colorBgBadgeSafetyArea.value
                ),
                current = OudsComponents.ListItem.Color.Background.Current(
                    disabled = colorBgCurrentDisabled.value,
                    enabled = colorBgCurrentEnabled.value,
                    focus = colorBgCurrentFocus.value,
                    hover = colorBgCurrentHover.value,
                    pressed = colorBgCurrentPressed.value
                )
            ),
            content = OudsComponents.ListItem.Color.Content(
                current = OudsComponents.ListItem.Color.Content.Current(
                    disabled = colorContentCurrentDisabled.value,
                    enabled = colorContentCurrentEnabled.value,
                    focus = colorContentCurrentFocus.value,
                    hover = colorContentCurrentHover.value,
                    pressed = colorContentCurrentPressed.value
                )
            )
        ),
        font = OudsComponents.ListItem.Font(
            letterSpacing = OudsComponents.ListItem.Font.LetterSpacing(
                avatarInitialExtraLarge = fontLetterSpacingAvatarInitialXlarge.dp
            ),
            lineHeight = OudsComponents.ListItem.Font.LineHeight(
                avatarInitialExtraLarge = fontLineHeightAvatarInitialXlarge.dp
            ),
            size = OudsComponents.ListItem.Font.Size(
                avatarInitialExtraLarge = fontSizeAvatarInitialXlarge.dp
            )
        ),
        opacity = OudsComponents.ListItem.Opacity(
            currentDivider = opacityCurrentDivider.value,
            currentIndicator = opacityCurrentIndicator.value
        ),
        size = OudsComponents.ListItem.Size(
            asset = OudsComponents.ListItem.Size.Asset(
                large = sizeAssetLarge.dp,
                medium = sizeAssetMedium.value,
                small = sizeAssetSmall.value,
                extraLarge = sizeAssetXlarge.dp
            ),
            controlIndicator = sizeControlIndicator.value,
            currentIndicator = OudsComponents.ListItem.Size.CurrentIndicator(
                width = sizeCurrentIndicatorWidth.dp
            ),
            icon = sizeIcon.value,
            loader = sizeLoader.value,
            maxHeightAssetsContainer = sizeMaxHeightAssetsContainer.dp,
            maxSizeLeadingTrailingSlot = sizeMaxSizeLeadingTrailingSlot.dp,
            maxWidth = sizeMaxWidth.dp,
            minHeightDefault = sizeMinHeightDefault.dp,
            minHeightSmall = sizeMinHeightSmall.value,
            minWidth = sizeMinWidth.dp,
            flag = OudsComponents.ListItem.Size.Flag(
                height = sizeFlagHeight.value
            )
        ),
        space = OudsComponents.ListItem.Space(
            columnGap = spaceColumnGap.value,
            paddingBlock = OudsComponents.ListItem.Space.PaddingBlock(
                bottomSlotListItemContainer = spacePaddingBlockBottomSlotListItemContainer.value,
                default = spacePaddingBlockDefault.value,
                small = spacePaddingBlockSmall.value,
                slotTextContainer = spacePaddingBlockSlotTextContainer.value,
                topAlignment = OudsComponents.ListItem.Space.PaddingBlock.TopAlignment(
                    topCounterweightDefault = spacePaddingBlockTopAlignmentTopCounterweightDefault.value,
                    topCounterweightSmall = spacePaddingBlockTopAlignmentTopCounterweightSmall.value,
                    topTextContainerDefault = spacePaddingBlockTopAlignmentTopTextContainerDefault.value,
                    topTextContainerSmall = spacePaddingBlockTopAlignmentTopTextContainerSmall.value
                ),
                topHelperText = spacePaddingBlockTopHelperText.value
            ),
            paddingInline = spacePaddingInline.value,
            paddingInlineErrorIcon = spacePaddingInlineErrorIcon.value,
            rowGap = spaceRowGap.value
        )
    )
}

@Composable
private fun OudsPinCodeInputTokens.getPinCodeInput(): OudsComponents.PinCodeInput {
    return OudsComponents.PinCodeInput(
        size = OudsComponents.PinCodeInput.Size(
            maxWidth = sizeMaxWidth.dp,
            minWidth = sizeMinWidth.dp
        ),
        space = OudsComponents.PinCodeInput.Space(
            columnGapDigitInput = spaceColumnGapDigitInput.value
        )
    )
}

@Composable
private fun OudsProgressIndicatorTokens.getProgressIndicator(): OudsComponents.ProgressIndicator {
    return OudsComponents.ProgressIndicator(
        border = OudsComponents.ProgressIndicator.Border(
            radius = OudsComponents.ProgressIndicator.Border.Radius(
                default = borderRadiusDefault.value,
                rounded = borderRadiusRounded.value
            )
        ),
        color = OudsComponents.ProgressIndicator.Color(
            content = OudsComponents.ProgressIndicator.Color.Content(
                track = colorContentTrack.value
            )
        ),
        size = OudsComponents.ProgressIndicator.Size(
            linearIndicatorHeight = sizeLinearIndicatorHeight.dp
        ),
        space = OudsComponents.ProgressIndicator.Space(
            paddingBlock = spacePaddingBlock.value
        )
    )
}

@Composable
private fun OudsProgressIndicatorMonoTokens.getProgressIndicatorMonochrome(): OudsComponents.ProgressIndicatorMonochrome {
    return OudsComponents.ProgressIndicatorMonochrome(
        color = OudsComponents.ProgressIndicatorMonochrome.Color(
            content = OudsComponents.ProgressIndicatorMonochrome.Color.Content(
                indicator = colorContentIndicator.value,
                track = colorContentTrack.value
            )
        )
    )
}

@Composable
private fun OudsRadioButtonTokens.getRadioButton(): OudsComponents.RadioButton {
    return OudsComponents.RadioButton(
        border = OudsComponents.RadioButton.Border(
            radius = borderRadius.value,
            width = OudsComponents.RadioButton.Border.Width(
                selected = borderWidthSelected.value,
                selectedFocus = borderWidthSelectedFocus.value,
                selectedHover = borderWidthSelectedHover.value,
                selectedPressed = borderWidthSelectedPressed.value,
                unselected = borderWidthUnselected.value,
                unselectedFocus = borderWidthUnselectedFocus.value,
                unselectedHover = borderWidthUnselectedHover.value,
                unselectedPressed = borderWidthUnselectedPressed.value
            )
        ),
        size = OudsComponents.RadioButton.Size(
            maxHeight = sizeMaxHeight.value,
            minHeight = sizeMinHeight.value,
            minWidth = sizeMinWidth.value
        )
    )
}

@Composable
private fun OudsSwitchTokens.getSwitch(): OudsComponents.Switch {
    return OudsComponents.Switch(
        border = OudsComponents.Switch.Border(
            radiusCursor = borderRadiusCursor.value,
            radiusTrack = borderRadiusTrack.value
        ),
        color = OudsComponents.Switch.Color(
            check = colorCheck.value,
            checkInteraction = colorCheckInteraction.value,
            cursor = colorCursor.value,
            track = OudsComponents.Switch.Color.Track(
                selected = colorTrackSelected.value,
                selectedInteraction = colorTrackSelectedInteraction.value,
                unselected = colorTrackUnselected.value,
                unselectedInteraction = colorTrackUnselectedInteraction.value
            )
        ),
        opacity = OudsComponents.Switch.Opacity(
            check = opacityCheck.value
        ),
        size = OudsComponents.Switch.Size(
            height = OudsComponents.Switch.Size.Height(
                cursorSelected = sizeHeightCursorSelected.dp,
                cursorUnselected = sizeHeightCursorUnselected.dp,
                track = sizeHeightTrack.dp
            ),
            maxHeight = sizeMaxHeight.dp,
            minHeight = sizeMinHeight.dp,
            minHeightInteractiveArea = sizeMinHeightInteractiveArea.value,
            minWidth = sizeMinWidth.dp,
            width = OudsComponents.Switch.Size.Width(
                cursorSelected = sizeWidthCursorSelected.dp,
                cursorSelectedPressed = sizeWidthCursorSelectedPressed.dp,
                cursorUnselected = sizeWidthCursorUnselected.dp,
                cursorUnselectedPressed = sizeWidthCursorUnselectedPressed.dp,
                track = sizeWidthTrack.dp
            )
        ),
        space = OudsComponents.Switch.Space(
            paddingInline = OudsComponents.Switch.Space.PaddingInline(
                selected = spacePaddingInlineSelected.value,
                unselected = spacePaddingInlineUnselected.value
            )
        )
    )
}

@Composable
private fun OudsTagTokens.getTag(): OudsComponents.Tag {
    return OudsComponents.Tag(
        border = OudsComponents.Tag.Border(
            radius = borderRadius.value
        ),
        size = OudsComponents.Tag.Size(
            assetSmall = sizeAssetSmall.value,
            assetDefault = sizeAssetDefault.value,
            minHeightSmall = sizeMinHeightSmall.dp,
            minHeightDefault = sizeMinHeightDefault.dp,
            minWidthSmall = sizeMinWidthSmall.dp,
            minWidthDefault = sizeMinWidthDefault.dp,
            minHeightInteractiveArea = sizeMinHeightInteractiveArea.value
        ),
        space = OudsComponents.Tag.Space(
            paddingInline = OudsComponents.Tag.Space.PaddingInline(
                small = spacePaddingInlineSmall.value,
                default = spacePaddingInlineDefault.value,
                assetSmall = spacePaddingInlineAssetSmall.value,
                assetDefault = spacePaddingInlineAssetDefault.value
            ),
            paddingBlock = OudsComponents.Tag.Space.PaddingBlock(
                small = spacePaddingBlockSmall.value,
                default = spacePaddingBlockDefault.value
            ),
            inset = OudsComponents.Tag.Space.Inset(
                iconSmall = spaceInsetIconSmall.value,
                bulletSmall = spaceInsetBulletSmall.value,
                loaderSmall = spaceInsetProgressIndicatorSmall.value,
                iconDefault = spaceInsetIconDefault.value,
                bulletDefault = spaceInsetBulletDefault.dp,
                loaderDefault = spaceInsetProgressIndicatorDefault.value
            ),
            columnGap = OudsComponents.Tag.Space.ColumnGap(
                small = spaceColumnGapSmall.value,
                default = spaceColumnGapDefault.value
            )
        )
    )
}

@Composable
private fun OudsTextAreaTokens.getTextArea(): OudsComponents.TextArea {
    return OudsComponents.TextArea(
        size = OudsComponents.TextArea.Size(
            maxHeightAssetsContainer = sizeMaxHeightAssetsContainer.dp,
            maxHeightInput = sizeMaxHeightInput.dp,
            maxWidth = sizeMaxWidth.dp,
            minHeightInput = sizeMinHeightInput.dp
        ),
        space = OudsComponents.TextArea.Space(
            paddingBlock = spacePaddingBlock.value,
            paddingBlockTopEmpty = spacePaddingBlockTopEmpty.value,
            paddingBlockEmptyTrailingContainer = spacePaddingBlockEmptyTrailingContainer.value,
            paddingBlockTrailingContainer = spacePaddingBlockTrailingContainer.value,
        )
    )
}

@Composable
private fun OudsTextInputTokens.getTextInput(): OudsComponents.TextInput {
    return OudsComponents.TextInput(
        border = OudsComponents.TextInput.Border(
            radius = OudsComponents.TextInput.Border.Radius(
                default = borderRadiusDefault.value,
                rounded = borderRadiusRounded.value
            ),
            width = OudsComponents.TextInput.Border.Width(
                default = borderWidthDefault.value,
                focus = borderWidthFocus.value
            )
        ),
        color = OudsComponents.TextInput.Color(
            border = OudsComponents.TextInput.Color.Border(
                enabled = colorBorderEnabled.value,
                focus = colorBorderFocus.value,
                hover = colorBorderHover.value,
                loading = colorBorderLoading.value
            )
        ),
        size = OudsComponents.TextInput.Size(
            countrySelectorFlagHeight = sizeCountrySelectorFlagHeight.value,
            labelMaxHeight = sizeLabelMaxHeight.dp,
            leadingIcon = sizeLeadingIcon.value,
            maxWidth = sizeMaxWidth.dp,
            minHeight = sizeMinHeight.dp,
            minWidth = sizeMinWidth.dp,
            verticalDividerHeight = sizeVerticalDividerHeight.dp
        ),
        space = OudsComponents.TextInput.Space(
            columnGap = OudsComponents.TextInput.Space.ColumnGap(
                default = spaceColumnGapDefault.value,
                inlineText = spaceColumnGapInlineText.value,
                labelAsterisk = spaceColumnGapLabelAsterisk.value,
                labelSmallAsterisk = spaceColumnGapLabelSmallAsterisk.dp,
                trailingErrorAction = spaceColumnGapTrailingErrorAction.value
            ),
            paddingBlock = OudsComponents.TextInput.Space.PaddingBlock(
                default = spacePaddingBlockDefault.value,
                topHelperText = spacePaddingBlockTopHelperText.value
            ),
            paddingInline = OudsComponents.TextInput.Space.PaddingInline(
                countrySelectorEnd = spacePaddingInlineCountrySelectorEnd.value,
                countrySelectorStart = spacePaddingInlineCountrySelectorStart.value,
                default = spacePaddingInlineDefault.value,
                trailingAction = spacePaddingInlineTrailingAction.value
            ),
            rowGap = OudsComponents.TextInput.Space.RowGap(
                labelInput = spaceRowGapLabelInput.value
            )
        )
    )
}

@Composable
private fun OudsTypographyTokens.getTypography(): OudsComponents.Typography {
    return OudsComponents.Typography(
        headingLargeMarker = headingLargeMarker,
        space = OudsComponents.Typography.Space(
            paddingBlock = OudsComponents.Typography.Space.PaddingBlock(
                bottomHeadingLargeMarker = spacePaddingBlockBottomHeadingLargeMarker.value,
                topHeadingLargeMarker = spacePaddingBlockTopHeadingLargeMarker.value
            )
        )
    )
}