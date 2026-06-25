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

package com.orange.ouds.core.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.extensions.value
import com.orange.ouds.foundation.RestrictedOudsApi
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
import com.orange.ouds.theme.tokens.components.OudsPinCodeInputTokens
import com.orange.ouds.theme.tokens.components.OudsProgressIndicatorMonoTokens
import com.orange.ouds.theme.tokens.components.OudsProgressIndicatorTokens
import com.orange.ouds.theme.tokens.components.OudsRadioButtonTokens
import com.orange.ouds.theme.tokens.components.OudsSwitchTokens
import com.orange.ouds.theme.tokens.components.OudsTagTokens
import com.orange.ouds.theme.tokens.components.OudsTextAreaTokens
import com.orange.ouds.theme.tokens.components.OudsTextInputTokens

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
    val controlItem: ControlItem,
    val divider: Divider,
    val icon: Icon,
    val inputTag: InputTag,
    val link: Link,
    val linkMonochrome: LinkMonochrome,
    val pinCodeInput: PinCodeInput,
    val progressIndicator: ProgressIndicator,
    val progressIndicatorMonochrome: ProgressIndicatorMonochrome,
    val radioButton: RadioButton,
    val switch: Switch,
    val tag: Tag,
    val textArea: TextArea,
    val textInput: TextInput
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
                val default: Dp,
                val rounded: Dp,
                val social: Dp
            )

            @ConsistentCopyVisibility
            data class Width internal constructor(
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
                val minimal: Minimal
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
            }

            @ConsistentCopyVisibility
            data class Border internal constructor(
                val default: Default
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
            }

            @ConsistentCopyVisibility
            data class Content internal constructor(
                val brand: Brand,
                val default: Default,
                val minimal: Minimal
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
            }
        }

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val icon: Dp,
            val iconOnly: Dp,
            val loader: Dp,
            val maxHeightIconOnly: Dp,
            val minHeight: Dp,
            val minWidth: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val insetIconOnly: Dp,
            val paddingBlock: Dp,
            val paddingInline: PaddingInline
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val chevron: Dp,
                val icon: Dp,
                val iconChevron: Dp
            )

            @ConsistentCopyVisibility
            data class PaddingInline internal constructor(
                val chevronEnd: Dp,
                val chevronStart: Dp,
                val endIconStart: Dp,
                val iconNone: Dp,
                val iconStart: Dp,
                val startIconEnd: Dp
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
    data class ControlItem internal constructor(
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
                val media: Dp,
                val mediaRoundedCorner: Dp,
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
            val badgeSafetyArea: androidx.compose.ui.graphics.Color,
            val background: Background,
            val content: Content
        ) {

            @ConsistentCopyVisibility
            data class Background internal constructor(
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
            val maxWidth: Dp,
            val minHeightCompact: Dp,
            val minHeightDefault: Dp,
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
            val rowGap: Dp
        ) {

            @ConsistentCopyVisibility
            data class PaddingBlock internal constructor(
                val bottomSlot: Dp,
                val densityCompact: Dp,
                val densityCompactBottomExpandContainer: Dp,
                val densityCompactTopAlignmentTopCounterweight: Dp,
                val densityCompactTopAlignmentTopTextContainer: Dp,
                val densityDefault: Dp,
                val densityDefaultBottomExpandContainer: Dp,
                val densityDefaultTopAlignmentTopCounterweight: Dp,
                val densityDefaultTopAlignmentTopTextContainer: Dp,
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

        @ConsistentCopyVisibility
        data class Size internal constructor(
            val iconDefault: Dp,
            val iconSmall: Dp,
            val minHeightDefault: Dp,
            val minHeightSmall: Dp,
            val minWidthDefault: Dp,
            val minWidthSmall: Dp
        )

        @ConsistentCopyVisibility
        data class Space internal constructor(
            val columnGap: ColumnGap,
            val paddingBlock: Dp,
            val paddingInline: Dp
        ) {

            @ConsistentCopyVisibility
            data class ColumnGap internal constructor(
                val chevronDefault: Dp,
                val chevronSmall: Dp,
                val iconDefault: Dp,
                val iconSmall: Dp
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
        controlItem = controlItem.getControlItem(),
        divider = divider.getDivider(),
        icon = icon.getIcon(),
        inputTag = inputTag.getInputTag(),
        link = link.getLink(),
        linkMonochrome = linkMonochrome.getLinkMonochrome(),
        pinCodeInput = pinCodeInput.getPinCodeInput(),
        progressIndicator = progressIndicator.getProgressIndicator(),
        progressIndicatorMonochrome = progressIndicatorMonochrome.getProgressIndicatorMonochrome(),
        radioButton = radioButton.getRadioButton(),
        switch = switch.getSwitch(),
        tag = tag.getTag(),
        textArea = textArea.getTextArea(),
        textInput = textInput.getTextInput()
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
                default = borderRadiusDefault.value,
                rounded = borderRadiusRounded.value,
                social = borderRadiusSocial.value
            ),
            width = OudsComponents.Button.Border.Width(
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
                )
            )
        ),
        size = OudsComponents.Button.Size(
            icon = sizeIcon.value,
            iconOnly = sizeIconOnly.value,
            loader = sizeLoader.value,
            maxHeightIconOnly = sizeMaxHeightIconOnly.value,
            minHeight = sizeMinHeight.value,
            minWidth = sizeMinWidth.value
        ),
        space = OudsComponents.Button.Space(
            columnGap = OudsComponents.Button.Space.ColumnGap(
                chevron = spaceColumnGapChevron.value,
                icon = spaceColumnGapIcon.value,
                iconChevron = spaceColumnGapIconChevron.value
            ),
            insetIconOnly = spaceInsetIconOnly.value,
            paddingBlock = spacePaddingBlock.value,
            paddingInline = OudsComponents.Button.Space.PaddingInline(
                chevronEnd = spacePaddingInlineChevronEnd.value,
                chevronStart = spacePaddingInlineChevronStart.value,
                endIconStart = spacePaddingInlineEndIconStart.value,
                iconNone = spacePaddingInlineIconNone.value,
                iconStart = spacePaddingInlineIconStart.value,
                startIconEnd = spacePaddingInlineStartIconEnd.value
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
private fun OudsControlItemTokens.getControlItem(): OudsComponents.ControlItem {
    return OudsComponents.ControlItem(
        border = OudsComponents.ControlItem.Border(
            radius = OudsComponents.ControlItem.Border.Radius(
                currentIndicator = borderRadiusCurrentIndicator.value,
                default = borderRadiusDefault.value,
                media = borderRadiusMedia.value,
                mediaRoundedCorner = borderRadiusMediaRoundedCorner.value,
                rounded = borderRadiusRounded.value
            ),
            width = OudsComponents.ControlItem.Border.Width(
                currentPage = borderWidthCurrentPage.value,
                default = borderWidthDefault.value
            )
        ),
        color = OudsComponents.ControlItem.Color(
            badgeSafetyArea = colorBadgeSafetyArea.value,
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
            minHeightCompact = sizeMinHeightCompact.value,
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
                bottomSlot = spacePaddingBlockBottomSlot.value,
                densityCompact = spacePaddingBlockDensityCompact.value,
                densityCompactTopAlignmentTopCounterweight = spacePaddingBlockDensityCompactTopAlignmentTopCounterweight.value,
                densityCompactTopAlignmentTopTextContainer = spacePaddingBlockDensityCompactTopAlignmentTopTextContainer.value,
                densityCompactBottomExpandContainer = spacePaddingBlockDensityCompactBottomExpandContainer.value,
                densityDefault = spacePaddingBlockDensityDefault.value,
                densityDefaultTopAlignmentTopCounterweight = spacePaddingBlockDensityDefaultTopAlignmentTopCounterweight.value,
                densityDefaultTopAlignmentTopTextContainer = spacePaddingBlockDensityDefaultTopAlignmentTopTextContainer.value,
                densityDefaultBottomExpandContainer = spacePaddingBlockDensityDefaultBottomExpandContainer.value,
            )
        )
    )
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
            minHeightDefault = sizeMinHeightDefault.value,
            minHeightSmall = sizeMinHeightSmall.dp,
            minWidthDefault = sizeMinWidthDefault.value,
            minWidthSmall = sizeMinWidthSmall.dp
        ),
        space = OudsComponents.Link.Space(
            columnGap = OudsComponents.Link.Space.ColumnGap(
                chevronDefault = spaceColumnGapChevronDefault.value,
                chevronSmall = spaceColumnGapChevronSmall.value,
                iconDefault = spaceColumnGapIconDefault.value,
                iconSmall = spaceColumnGapIconSmall.value
            ),
            paddingBlock = spacePaddingBlock.value,
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
                loaderSmall = spaceInsetLoaderSmall.value,
                iconDefault = spaceInsetIconDefault.value,
                bulletDefault = spaceInsetBulletDefault.dp,
                loaderDefault = spaceInsetLoaderDefault.value
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
