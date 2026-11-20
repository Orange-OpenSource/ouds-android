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

package com.orange.ouds.core.component.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.orange.ouds.core.component.OudsBodyText
import com.orange.ouds.core.component.OudsButton
import com.orange.ouds.core.component.OudsButtonAppearance
import com.orange.ouds.core.component.OudsCard
import com.orange.ouds.core.component.OudsCardStyle
import com.orange.ouds.core.component.OudsElevatedCard
import com.orange.ouds.core.component.OudsHeadingText
import com.orange.ouds.core.component.OudsInlineAlert
import com.orange.ouds.core.component.OudsInlineAlertStatus
import com.orange.ouds.core.component.OudsLabelText
import com.orange.ouds.core.component.OudsLabelTextSize
import com.orange.ouds.core.component.OudsOutlinedCard
import com.orange.ouds.core.component.OudsTag
import com.orange.ouds.core.component.OudsTagSize
import com.orange.ouds.core.component.OudsTagStatus
import com.orange.ouds.core.component.OudsTextWeight
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.core.utilities.OudsPreview

@Composable
internal fun OudsCardSample() {
    val cardModifier = Modifier.width(350.dp)
    Column(verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)) {
        OudsCard(
            modifier = cardModifier,
            style = OudsCardStyle.Filled()
        ) {
            Column(
                modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
                verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsHeadingText(text = "Opening Hours")
                OudsBodyText(text = "Monday to Friday: 9:00 AM - 6:00 PM")
                OudsBodyText(text = "Saturday: 10:00 AM - 4:00 PM")
                OudsBodyText(text = "Sunday: Closed")
            }
        }
        OudsCard(
            modifier = cardModifier,
            style = OudsCardStyle.Elevated()
        ) {
            Column(
                modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
                verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.extraSmall)
            ) {
                OudsHeadingText(text = "Practical Tips")
                OudsBodyText(text = "• Arrive 15 minutes early")
                OudsBodyText(text = "• Bring a valid ID")
                OudsBodyText(text = "• Check your email for updates")
            }
        }
        OudsCard(
            modifier = cardModifier,
            style = OudsCardStyle.Outlined
        ) {
            Column(
                modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
                verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsHeadingText(text = "Contact Support")
                OudsBodyText(text = "Phone: +33 1 23 45 67 89")
                OudsBodyText(text = "Email: support@example.com")
            }
        }
    }
}

@Composable
internal fun OudsFilledCardSample() {
    OudsCard(
        style = OudsCardStyle.Filled(divider = true)
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            OudsHeadingText(text = "Premium Headphones")
            OudsBodyText(text = "High-quality wireless headphones with noise cancellation and 30-hour battery life.")
            Row(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsTag(label = "Free shipping", status = OudsTagStatus.Positive(), size = OudsTagSize.Small)
                OudsTag(label = "24h delivery", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
            OudsLabelText(
                text = "In stock: 15 units",
                size = OudsLabelTextSize.Medium,
                weight = OudsTextWeight.Strong
            )
        }
    }
}

@Composable
internal fun OudsElevatedCardSample() {
    OudsElevatedCard(
        background = true
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            OudsHeadingText(text = "Community Workshop")
            OudsBodyText(text = "Join us for an interactive learning session about design systems and best practices.")
            Row(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsTag(label = "Free event", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
                OudsTag(label = "Beginner-friendly", status = OudsTagStatus.Positive(), size = OudsTagSize.Small)
            }
            OudsLabelText(
                text = "Saturday, March 15 at 2:00 PM",
                size = OudsLabelTextSize.Small,
                color = OudsTheme.colorScheme.content.muted
            )
        }
    }
}

@Composable
internal fun OudsOutlinedCardSample() {
    OudsOutlinedCard {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Contact icon",
                    tint = OudsTheme.colorScheme.content.default
                )
                OudsHeadingText(text = "Customer Service")
            }
            OudsBodyText(text = "Our team is available to help you with any questions or concerns you may have.")
            Row(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsTag(label = "24/7 available", status = OudsTagStatus.Positive(), size = OudsTagSize.Small)
                OudsTag(label = "Multilingual", status = OudsTagStatus.Info(), size = OudsTagSize.Small)
            }
            OudsLabelText(
                text = "Average response time: 5 minutes",
                size = OudsLabelTextSize.Small,
                color = OudsTheme.colorScheme.content.muted
            )
        }
    }
}

@Composable
internal fun OudsElevatedActionCardSample() {
    OudsElevatedCard(
        onClick = { /* Complete profile */ },
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            OudsHeadingText(text = "Complete Your Profile")
            OudsBodyText(text = "Add your personal information to personalize your experience and unlock additional features.")
            OudsLabelText(
                text = "Profile: 60% complete",
                size = OudsLabelTextSize.Medium,
                weight = OudsTextWeight.Strong
            )
        }
    }
}

@Composable
internal fun OudsFilledActionCardSample() {
    OudsCard(
        onClick = { /* Add to cart */ },
        style = OudsCardStyle.Filled()
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.threeExtraSmall)
            ) {
                OudsHeadingText(text = "Wireless Speaker")
                OudsBodyText(text = "Portable Bluetooth speaker with premium sound quality")
            }
            Row(
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small),
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsTag(label = "Best seller", status = OudsTagStatus.Warning(), size = OudsTagSize.Small)
                OudsTag(label = "In stock", status = OudsTagStatus.Positive(), size = OudsTagSize.Small)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = OudsTheme.spaces.fixed.small)
            ) {
                OudsLabelText(
                    modifier = Modifier.weight(1f),
                    text = "€129.99",
                    size = OudsLabelTextSize.Large,
                    weight = OudsTextWeight.Strong
                )
                OudsButton(
                    label = "Add to Cart",
                    appearance = OudsButtonAppearance.Strong,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
internal fun OudsOutlinedActionCardSample() {
    OudsCard(
        onClick = { /* View offer */ },
        style = OudsCardStyle.Outlined
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.medium),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
        ) {
            OudsHeadingText(text = "Special Offer")
            OudsBodyText(text = "Get 20% off on your first purchase. Limited time offer for new customers only.")
            Row(
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                OudsLabelText(
                    text = "Code: WELCOME20",
                    size = OudsLabelTextSize.Medium,
                    weight = OudsTextWeight.Strong
                )
            }
            OudsInlineAlert(
                label = "Offer ends in 3 days",
                status = OudsInlineAlertStatus.Warning
            )
            OudsButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Shop Now",
                appearance = OudsButtonAppearance.Strong,
                onClick = {}
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewOudsCardSample() = OudsPreview {
    OudsCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsFilledCardSample() = OudsPreview {
    OudsFilledCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsElevatedCardSample() = OudsPreview {
    OudsElevatedCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsOutlinedCardSample() = OudsPreview {
    OudsOutlinedCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsElevatedActionCardSample() = OudsPreview {
    OudsElevatedActionCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsFilledActionCardSample() = OudsPreview {
    OudsFilledActionCardSample()
}

@PreviewLightDark
@Composable
private fun PreviewOudsOutlinedActionCardSample() = OudsPreview {
    OudsOutlinedActionCardSample()
}
