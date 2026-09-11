# OUDS Android — Semantic Tokens Reference

All OUDS semantic tokens are accessed via `OudsTheme` in composable functions.  
**Never hardcode values** — always use token references for consistency and theme support.

> **Golden Rule:** Always use `OudsTheme.*` tokens instead of hardcoded values.
> ```kotlin
> // ✅ CORRECT
> Text(
>     text = "Title",
>     color = OudsTheme.colorScheme.content.default,
>     style = OudsTheme.typography.heading.large
> )
> Box(
>     modifier = Modifier
>         .padding(OudsTheme.spaces.fixed.medium)
>         .border(
>             width = OudsTheme.borders.width.default,
>             color = OudsTheme.colorScheme.border.default
>         )
> )
> 
> // ❌ INCORRECT
> Text(text = "Title", color = Color.Black, fontSize = 16.sp)
> Box(modifier = Modifier.padding(16.dp))
> ```

## Table of Contents

- [Color Scheme](#color-scheme-oudsthemecolorscheme)
- [Typography](#typography-oudsthemetypography)
- [Spacing](#spacing-oudsthemespaces)
- [Sizes](#sizes-oudsthemesizes)
- [Borders](#borders-oudsthemeborders)
- [Elevations](#elevations-oudsthemeelevations)
- [Opacities](#opacities-oudsthemeopacities)
- [Effects](#effects-oudsthemeeffects)
- [Grids](#grids-oudsthemegrids)
- [Usage Examples](#usage-examples)

> **Advanced Usage:** For component-level tokens (`@RestrictedOudsApi`), see [`component-tokens.md`](component-tokens.md).

---

## Color Scheme (`OudsTheme.colorScheme`)

**Design guidelines:** [Color tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-color)

> Color tokens are **auto-adaptive**: they automatically adjust between Light and Dark modes.

### Actions

Interactive element colors for buttons, links, and actionable items.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.action.enabled` | Primary action color (enabled button, active link) |
| `OudsTheme.colorScheme.action.disabled` | Disabled action state |
| `OudsTheme.colorScheme.action.hover` | Hover state |
| `OudsTheme.colorScheme.action.pressed` | Pressed/tap state |
| `OudsTheme.colorScheme.action.focus` | Focus state |
| `OudsTheme.colorScheme.action.loading` | Loading state |
| `OudsTheme.colorScheme.action.highlighted` | Highlighted state |
| `OudsTheme.colorScheme.action.selected` | Selected state |
| `OudsTheme.colorScheme.action.visited` | Visited link state |
| `OudsTheme.colorScheme.action.readOnlyPrimary` | Read-only primary |
| `OudsTheme.colorScheme.action.readOnlySecondary` | Read-only secondary |
| `OudsTheme.colorScheme.action.negative.enabled` | Destructive action enabled |
| `OudsTheme.colorScheme.action.negative.hover` | Destructive action hover |
| `OudsTheme.colorScheme.action.negative.pressed` | Destructive action pressed |
| `OudsTheme.colorScheme.action.negative.loading` | Destructive action loading |
| `OudsTheme.colorScheme.action.negative.focus` | Destructive action focus |
| `OudsTheme.colorScheme.action.support.enabled` | Support/secondary action enabled |
| `OudsTheme.colorScheme.action.support.disabled` | Support/secondary action disabled |
| `OudsTheme.colorScheme.action.support.hover` | Support action hover |
| `OudsTheme.colorScheme.action.support.pressed` | Support action pressed |
| `OudsTheme.colorScheme.action.support.focus` | Support action focus |
| `OudsTheme.colorScheme.action.support.loading` | Support action loading |

```kotlin
// Action colors in use
Button(
    onClick = { },
    colors = ButtonDefaults.buttonColors(
        containerColor = OudsTheme.colorScheme.action.enabled,
        contentColor = OudsTheme.colorScheme.content.onAction.enabled,
        disabledContainerColor = OudsTheme.colorScheme.action.disabled
    )
) {
    Text("Action")
}
```

### Always

Colors that remain constant regardless of Light/Dark mode.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.always.black` | Absolute black (mode-independent) |
| `OudsTheme.colorScheme.always.white` | Absolute white (mode-independent) |
| `OudsTheme.colorScheme.always.onBlack` | Content on absolute black |
| `OudsTheme.colorScheme.always.onWhite` | Content on absolute white |

```kotlin
// Always colors for mode-independent elements
Surface(color = OudsTheme.colorScheme.always.black) {
    Text(
        text = "Always visible",
        color = OudsTheme.colorScheme.always.onBlack
    )
}
```

### Background

Page and screen background colors.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.background.primary` | Primary page background |
| `OudsTheme.colorScheme.background.secondary` | Secondary background (sections) |
| `OudsTheme.colorScheme.background.tertiary` | Tertiary background |
| `OudsTheme.colorScheme.background.inverseHigh` | High contrast inverse background |
| `OudsTheme.colorScheme.background.inverseLow` | Low contrast inverse background |

### Border

Border and divider colors.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.border.default` | Standard border |
| `OudsTheme.colorScheme.border.muted` | Subtle border |
| `OudsTheme.colorScheme.border.emphasized` | Strong border |
| `OudsTheme.colorScheme.border.minimal` | Minimal border |
| `OudsTheme.colorScheme.border.focus` | Focus ring (outer) |
| `OudsTheme.colorScheme.border.focusInset` | Focus ring (inner/inset) |
| `OudsTheme.colorScheme.border.brandPrimary` | Brand-colored border |
| `OudsTheme.colorScheme.border.brandSecondary` | Brand secondary border |
| `OudsTheme.colorScheme.border.brandTertiary` | Brand tertiary border |
| `OudsTheme.colorScheme.border.onBrand.primary` | Border on brand primary background |
| `OudsTheme.colorScheme.border.onBrand.secondary` | Border on brand secondary background |
| `OudsTheme.colorScheme.border.onBrand.tertiary` | Border on brand tertiary background |
| `OudsTheme.colorScheme.border.status.accent` | Accent status border |
| `OudsTheme.colorScheme.border.status.negative` | Error/negative border |
| `OudsTheme.colorScheme.border.status.positive` | Success/positive border |
| `OudsTheme.colorScheme.border.status.warning` | Warning border |
| `OudsTheme.colorScheme.border.status.info` | Info border |

```kotlin
// Border colors
Box(
    modifier = Modifier.border(
        width = OudsTheme.borders.width.default,
        color = OudsTheme.colorScheme.border.default,
        shape = RoundedCornerShape(OudsTheme.borders.radius.medium)
    )
)
```

### Content

Text, icon, and foreground element colors.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.content.default` | Primary text/icon color |
| `OudsTheme.colorScheme.content.muted` | Secondary text, hints, captions |
| `OudsTheme.colorScheme.content.disabled` | Disabled text/icon |
| `OudsTheme.colorScheme.content.inverse` | Text on inverse background |
| `OudsTheme.colorScheme.content.brandPrimary` | Brand primary colored text |
| `OudsTheme.colorScheme.content.brandSecondary` | Brand secondary colored text |
| `OudsTheme.colorScheme.content.brandTertiary` | Brand tertiary colored text |
| `OudsTheme.colorScheme.content.onAction.enabled` | Content on enabled action background |
| `OudsTheme.colorScheme.content.onAction.disabled` | Content on disabled action background |
| `OudsTheme.colorScheme.content.onAction.focus` | Content on focused action |
| `OudsTheme.colorScheme.content.onAction.highlighted` | Content on highlighted action |
| `OudsTheme.colorScheme.content.onAction.hover` | Content on hovered action |
| `OudsTheme.colorScheme.content.onAction.loading` | Content on loading action |
| `OudsTheme.colorScheme.content.onAction.pressed` | Content on pressed action |
| `OudsTheme.colorScheme.content.onAction.selected` | Content on selected action |
| `OudsTheme.colorScheme.content.onBrand.primary` | Text on brand primary background |
| `OudsTheme.colorScheme.content.onBrand.secondary` | Text on brand secondary background |
| `OudsTheme.colorScheme.content.onBrand.tertiary` | Text on brand tertiary background |
| `OudsTheme.colorScheme.content.onStatus.accent.emphasized` | Text on accent emphasized background |
| `OudsTheme.colorScheme.content.onStatus.accent.muted` | Text on accent muted background |
| `OudsTheme.colorScheme.content.onStatus.info.emphasized` | Text on info emphasized background |
| `OudsTheme.colorScheme.content.onStatus.info.muted` | Text on info muted background |
| `OudsTheme.colorScheme.content.onStatus.negative.emphasized` | Text on error emphasized background |
| `OudsTheme.colorScheme.content.onStatus.negative.muted` | Text on error muted background |
| `OudsTheme.colorScheme.content.onStatus.positive.emphasized` | Text on success emphasized background |
| `OudsTheme.colorScheme.content.onStatus.positive.muted` | Text on success muted background |
| `OudsTheme.colorScheme.content.onStatus.warning.emphasized` | Text on warning emphasized background |
| `OudsTheme.colorScheme.content.onStatus.warning.muted` | Text on warning muted background |
| `OudsTheme.colorScheme.content.status.accent` | Accent colored text |
| `OudsTheme.colorScheme.content.status.info` | Info colored text |
| `OudsTheme.colorScheme.content.status.negative` | Error colored text |
| `OudsTheme.colorScheme.content.status.positive` | Success colored text |
| `OudsTheme.colorScheme.content.status.warning` | Warning colored text |

```kotlin
// Content colors for text hierarchy
Column {
    Text(
        text = stringResource(R.string.title),
        color = OudsTheme.colorScheme.content.default,
        style = OudsTheme.typography.heading.large
    )
    Text(
        text = stringResource(R.string.subtitle),
        color = OudsTheme.colorScheme.content.muted,
        style = OudsTheme.typography.body.medium.default
    )
}
```

### Opacity

Transparency colors.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.opacity.transparent` | Fully transparent |
| `OudsTheme.colorScheme.opacity.lowest` | Very high transparency |
| `OudsTheme.colorScheme.opacity.lower` | High transparency |

### Overlay

Overlay, modal, and tooltip background colors.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.overlay.backdrop` | Modal backdrop/scrim |
| `OudsTheme.colorScheme.overlay.modalSheet` | Bottom sheet background |
| `OudsTheme.colorScheme.overlay.dropdown` | Dropdown background |
| `OudsTheme.colorScheme.overlay.tooltip` | Tooltip background |
| `OudsTheme.colorScheme.overlay.drag` | Drag overlay |

### Surface

Surface colors for cards, dialogs, and elevated components.

| Token | Usage |
|-------|-------|
| `OudsTheme.colorScheme.surface.primary` | Primary surface (cards, modals) |
| `OudsTheme.colorScheme.surface.secondary` | Secondary surface |
| `OudsTheme.colorScheme.surface.tertiary` | Tertiary surface |
| `OudsTheme.colorScheme.surface.inverseHigh` | High contrast inverse surface |
| `OudsTheme.colorScheme.surface.inverseLow` | Low contrast inverse surface |
| `OudsTheme.colorScheme.surface.brand.primary` | Brand primary surface |
| `OudsTheme.colorScheme.surface.brand.secondary` | Brand secondary surface |
| `OudsTheme.colorScheme.surface.brand.tertiary` | Brand tertiary surface |
| `OudsTheme.colorScheme.surface.status.accent.emphasized` | Accent emphasized surface |
| `OudsTheme.colorScheme.surface.status.accent.muted` | Accent muted surface |
| `OudsTheme.colorScheme.surface.status.info.emphasized` | Info emphasized surface |
| `OudsTheme.colorScheme.surface.status.info.muted` | Info muted surface |
| `OudsTheme.colorScheme.surface.status.negative.emphasized` | Error emphasized surface |
| `OudsTheme.colorScheme.surface.status.negative.muted` | Error muted surface |
| `OudsTheme.colorScheme.surface.status.positive.emphasized` | Success emphasized surface |
| `OudsTheme.colorScheme.surface.status.positive.muted` | Success muted surface |
| `OudsTheme.colorScheme.surface.status.warning.emphasized` | Warning emphasized surface |
| `OudsTheme.colorScheme.surface.status.warning.muted` | Warning muted surface |

---

## Typography (`OudsTheme.typography`)

**Design guidelines:** [Typography tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-typography)

> Text styles that automatically adapt to screen size (mobile/tablet).

| Token | Recommended Usage |
|-------|-------------------|
| `OudsTheme.typography.display.large` | Hero title, splash screens |
| `OudsTheme.typography.display.medium` | Large display title |
| `OudsTheme.typography.display.small` | Medium display title |
| `OudsTheme.typography.heading.extraLarge` | H0 — Main page title |
| `OudsTheme.typography.heading.large` | H1 — Primary section title |
| `OudsTheme.typography.heading.medium` | H2 — Subsection title |
| `OudsTheme.typography.heading.small` | H3 — Group title |
| `OudsTheme.typography.body.large.default` | Large body text (regular weight) |
| `OudsTheme.typography.body.large.moderate` | Large body text (medium weight) |
| `OudsTheme.typography.body.large.strong` | Large body text (bold weight) |
| `OudsTheme.typography.body.medium.default` | Standard body text (regular) |
| `OudsTheme.typography.body.medium.moderate` | Standard body text (medium) |
| `OudsTheme.typography.body.medium.strong` | Standard body text (bold) |
| `OudsTheme.typography.body.small.default` | Compact body text (regular) |
| `OudsTheme.typography.body.small.moderate` | Compact body text (medium) |
| `OudsTheme.typography.body.small.strong` | Compact body text (bold) |
| `OudsTheme.typography.label.extraLarge.default` | XL label (regular) |
| `OudsTheme.typography.label.extraLarge.moderate` | XL label (medium) |
| `OudsTheme.typography.label.extraLarge.strong` | XL label (bold, for buttons) |
| `OudsTheme.typography.label.large.default` | Large label (regular) |
| `OudsTheme.typography.label.large.moderate` | Large label (medium) |
| `OudsTheme.typography.label.large.strong` | Large label (bold) |
| `OudsTheme.typography.label.medium.default` | Medium label (regular) |
| `OudsTheme.typography.label.medium.moderate` | Medium label (medium) |
| `OudsTheme.typography.label.medium.strong` | Medium label (bold) |
| `OudsTheme.typography.label.small.default` | Small label (regular) |
| `OudsTheme.typography.label.small.moderate` | Small label (medium) |
| `OudsTheme.typography.label.small.strong` | Small label (bold) |

```kotlin
// Typography hierarchy
Column(verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)) {
    Text(
        text = stringResource(R.string.page_title),
        style = OudsTheme.typography.heading.large
    )
    Text(
        text = stringResource(R.string.section_title),
        style = OudsTheme.typography.heading.medium
    )
    Text(
        text = stringResource(R.string.body),
        style = OudsTheme.typography.body.medium.default
    )
    Text(
        text = stringResource(R.string.caption),
        style = OudsTheme.typography.label.small.default,
        color = OudsTheme.colorScheme.content.muted
    )
}
```

---

## Spacing (`OudsTheme.spaces`)

**Design guidelines:** [Space tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-space)

### Fixed Spaces

Fixed spacing values that remain constant regardless of screen size.

| Token | Approximate Size |
|-------|-----------------|
| `OudsTheme.spaces.fixed.none` | 0 dp |
| `OudsTheme.spaces.fixed.threeExtraSmall` | ~2 dp |
| `OudsTheme.spaces.fixed.twoExtraSmall` | ~4 dp |
| `OudsTheme.spaces.fixed.extraSmall` | ~8 dp |
| `OudsTheme.spaces.fixed.small` | ~12 dp |
| `OudsTheme.spaces.fixed.medium` | ~16 dp |
| `OudsTheme.spaces.fixed.large` | ~24 dp |
| `OudsTheme.spaces.fixed.extraLarge` | ~32 dp |
| `OudsTheme.spaces.fixed.twoExtraLarge` | ~40 dp |
| `OudsTheme.spaces.fixed.threeExtraLarge` | ~48 dp |
| `OudsTheme.spaces.fixed.fourExtraLarge` | ~64 dp |
| `OudsTheme.spaces.fixed.fiveExtraLarge` | ~80 dp |

### Scaled Spaces

Adaptive spacing values that adjust based on screen size (mobile vs tablet).

| Token | Usage |
|-------|-------|
| `OudsTheme.spaces.scaled.none` | 0 dp |
| `OudsTheme.spaces.scaled.threeExtraSmall` | Very compact spacing |
| `OudsTheme.spaces.scaled.twoExtraSmall` | Very compact spacing |
| `OudsTheme.spaces.scaled.extraSmall` | Compact spacing |
| `OudsTheme.spaces.scaled.small` | Small spacing |
| `OudsTheme.spaces.scaled.medium` | Medium spacing |
| `OudsTheme.spaces.scaled.large` | Large spacing |
| `OudsTheme.spaces.scaled.extraLarge` | Very large spacing |
| `OudsTheme.spaces.scaled.twoExtraLarge` | 2XL spacing |
| `OudsTheme.spaces.scaled.threeExtraLarge` | 3XL spacing |

```kotlin
// Fixed spacing for consistent layouts
Card(
    modifier = Modifier.padding(OudsTheme.spaces.fixed.medium)
) {
    Column(
        modifier = Modifier.padding(OudsTheme.spaces.fixed.large),
        verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
    ) {
        Text(stringResource(R.string.title))
        Text(stringResource(R.string.content))
    }
}

// Scaled spacing for responsive layouts
Column(
    modifier = Modifier.padding(OudsTheme.spaces.scaled.medium),
    verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.scaled.small)
) {
    // Content adapts spacing to screen size
}
```

---

## Sizes (`OudsTheme.sizes`)

**Design guidelines:** [Size tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-size)

### Icon Sizes — Decorative

General-purpose icon sizes for standalone icons without textual context.

| Token | Usage |
|-------|-------|
| `OudsTheme.sizes.icon.decorative.fourExtraSmall` | Tiny icon |
| `OudsTheme.sizes.icon.decorative.threeExtraSmall` | Very small icon |
| `OudsTheme.sizes.icon.decorative.twoExtraSmall` | Extra small icon |
| `OudsTheme.sizes.icon.decorative.extraSmall` | Small icon |
| `OudsTheme.sizes.icon.decorative.small` | Small-standard icon |
| `OudsTheme.sizes.icon.decorative.medium` | Standard icon (common use) |
| `OudsTheme.sizes.icon.decorative.large` | Large icon |
| `OudsTheme.sizes.icon.decorative.extraLarge` | Very large icon |
| `OudsTheme.sizes.icon.decorative.twoExtraLarge` | 2XL icon |

### Icon Sizes — Contextual

Icon sizes paired with text elements for proper alignment.

```kotlin
// Icon with label
OudsTheme.sizes.icon.withLabel.small.sizeExtraSmall
OudsTheme.sizes.icon.withLabel.small.sizeSmall
OudsTheme.sizes.icon.withLabel.small.sizeMedium
OudsTheme.sizes.icon.withLabel.small.sizeLarge
OudsTheme.sizes.icon.withLabel.medium.sizeExtraSmall
OudsTheme.sizes.icon.withLabel.medium.sizeSmall
OudsTheme.sizes.icon.withLabel.medium.sizeMedium
OudsTheme.sizes.icon.withLabel.medium.sizeLarge
OudsTheme.sizes.icon.withLabel.large.sizeExtraSmall
OudsTheme.sizes.icon.withLabel.large.sizeSmall
OudsTheme.sizes.icon.withLabel.large.sizeMedium
OudsTheme.sizes.icon.withLabel.large.sizeLarge
OudsTheme.sizes.icon.withLabel.large.sizeExtraLarge
OudsTheme.sizes.icon.withLabel.extraLarge.sizeExtraSmall
OudsTheme.sizes.icon.withLabel.extraLarge.sizeSmall
OudsTheme.sizes.icon.withLabel.extraLarge.sizeMedium
OudsTheme.sizes.icon.withLabel.extraLarge.sizeLarge

// Icon with body text
OudsTheme.sizes.icon.withBody.small.sizeSmall
OudsTheme.sizes.icon.withBody.small.sizeMedium
OudsTheme.sizes.icon.withBody.small.sizeLarge
OudsTheme.sizes.icon.withBody.medium.sizeSmall
OudsTheme.sizes.icon.withBody.medium.sizeMedium
OudsTheme.sizes.icon.withBody.medium.sizeLarge
OudsTheme.sizes.icon.withBody.large.sizeSmall
OudsTheme.sizes.icon.withBody.large.sizeMedium
OudsTheme.sizes.icon.withBody.large.sizeLarge

// Icon with heading
OudsTheme.sizes.icon.withHeading.small.sizeSmall
OudsTheme.sizes.icon.withHeading.small.sizeMedium
OudsTheme.sizes.icon.withHeading.small.sizeLarge
OudsTheme.sizes.icon.withHeading.medium.sizeSmall
OudsTheme.sizes.icon.withHeading.medium.sizeMedium
OudsTheme.sizes.icon.withHeading.medium.sizeLarge
OudsTheme.sizes.icon.withHeading.large.sizeSmall
OudsTheme.sizes.icon.withHeading.large.sizeMedium
OudsTheme.sizes.icon.withHeading.large.sizeLarge
OudsTheme.sizes.icon.withHeading.extraLarge.sizeSmall
OudsTheme.sizes.icon.withHeading.extraLarge.sizeMedium
OudsTheme.sizes.icon.withHeading.extraLarge.sizeLarge
```

### Max Width Constraints

Maximum width constraints for readable text blocks.

```kotlin
// Body text
OudsTheme.sizes.maxWidth.body.small
OudsTheme.sizes.maxWidth.body.medium
OudsTheme.sizes.maxWidth.body.large

// Display text
OudsTheme.sizes.maxWidth.display.small
OudsTheme.sizes.maxWidth.display.medium
OudsTheme.sizes.maxWidth.display.large

// Heading text
OudsTheme.sizes.maxWidth.heading.small
OudsTheme.sizes.maxWidth.heading.medium
OudsTheme.sizes.maxWidth.heading.large
OudsTheme.sizes.maxWidth.heading.extraLarge

// Label text
OudsTheme.sizes.maxWidth.label.small
OudsTheme.sizes.maxWidth.label.medium
OudsTheme.sizes.maxWidth.label.large
OudsTheme.sizes.maxWidth.label.extraLarge
```

### Minimum Interactive Area

Minimum touch target size for accessibility.

```kotlin
OudsTheme.sizes.minInteractiveArea        // General minimum touch target
```

```kotlin
// Using icon sizes
Icon(
    imageVector = Icons.Default.Settings,
    contentDescription = stringResource(R.string.settings),
    modifier = Modifier.size(OudsTheme.sizes.icon.decorative.medium)
)

// Using max width for readable text
Text(
    text = stringResource(R.string.long_content),
    style = OudsTheme.typography.body.medium.default,
    modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.body.medium)
)
```

---

## Borders (`OudsTheme.borders`)

**Design guidelines:** [Border tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-border)

### Width

Border stroke widths.

| Token | Usage |
|-------|-------|
| `OudsTheme.borders.width.none` | No border (0 dp) |
| `OudsTheme.borders.width.default` | Standard border width |
| `OudsTheme.borders.width.thin` | Thin border |
| `OudsTheme.borders.width.medium` | Medium border |
| `OudsTheme.borders.width.thick` | Thick border |
| `OudsTheme.borders.width.thicker` | Very thick border |
| `OudsTheme.borders.width.focus` | Focus ring (outer) |
| `OudsTheme.borders.width.focusInset` | Focus ring (inner/inset) |

### Radius

Corner rounding radii.

| Token | Usage |
|-------|-------|
| `OudsTheme.borders.radius.none` | Square corners (0 dp) |
| `OudsTheme.borders.radius.default` | Standard rounding |
| `OudsTheme.borders.radius.small` | Slight rounding |
| `OudsTheme.borders.radius.medium` | Medium rounding (cards, chips) |
| `OudsTheme.borders.radius.large` | Large rounding |
| `OudsTheme.borders.radius.pill` | Pill/capsule shape (buttons, badges) |

### Style

Border stroke styles.

| Token | Usage |
|-------|-------|
| `OudsTheme.borders.style.default` | Solid border |
| `OudsTheme.borders.style.drag` | Dashed border (drag & drop) |

```kotlin
// Using border tokens
Box(
    modifier = Modifier
        .size(100.dp)
        .border(
            width = OudsTheme.borders.width.default,
            color = OudsTheme.colorScheme.border.default,
            shape = RoundedCornerShape(OudsTheme.borders.radius.medium)
        )
)

// Card with border
Card(
    shape = RoundedCornerShape(OudsTheme.borders.radius.large),
    border = BorderStroke(
        width = OudsTheme.borders.width.thin,
        color = OudsTheme.colorScheme.border.muted
    )
) {
    // Content
}
```

---

## Elevations (`OudsTheme.elevations`)

**Design guidelines:** [Elevation tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-elevation)

Shadow and z-axis elevation values.

| Token | Usage |
|-------|-------|
| `OudsTheme.elevations.none` | No shadow (0 dp) |
| `OudsTheme.elevations.default` | Default elevation |
| `OudsTheme.elevations.raised` | Raised card elevation |
| `OudsTheme.elevations.sticky` | Sticky/floating bar elevation |
| `OudsTheme.elevations.drag` | Drag & drop elevation |
| `OudsTheme.elevations.emphasized` | Modal/dialog elevation |

```kotlin
// Using elevations
Card(
    elevation = CardDefaults.cardElevation(
        defaultElevation = OudsTheme.elevations.raised
    )
) {
    // Content
}

Surface(
    shadowElevation = OudsTheme.elevations.emphasized,
    shape = RoundedCornerShape(OudsTheme.borders.radius.medium)
) {
    // Modal content
}
```

---

## Opacities (`OudsTheme.opacities`)

**Design guidelines:** [Opacity tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-opacity)

Transparency alpha values (Float, 0.0 to 1.0).

| Token | Value Range | Usage |
|-------|------------|-------|
| `OudsTheme.opacities.invisible` | 0.0 | Fully transparent |
| `OudsTheme.opacities.weakest` | Very low | Very subtle overlay |
| `OudsTheme.opacities.weaker` | Low | Subtle overlay |
| `OudsTheme.opacities.weak` | Medium-low | Light overlay |
| `OudsTheme.opacities.medium` | Medium | Standard overlay |
| `OudsTheme.opacities.strong` | Medium-high | Strong overlay |
| `OudsTheme.opacities.disabled` | Variable | Disabled state opacity |
| `OudsTheme.opacities.opaque` | 1.0 | Fully opaque |

```kotlin
// Using opacity
Box(
    modifier = Modifier
        .fillMaxSize()
        .background(
            OudsTheme.colorScheme.always.black.copy(
                alpha = OudsTheme.opacities.medium
            )
        )
)
```

---

## Effects (`OudsTheme.effects`)

Visual effect values.

| Token | Usage |
|-------|-------|
| `OudsTheme.effects.blurDrag` | Blur radius for drag effects (Int) |

---

## Grids (`OudsTheme.grids`)

**Design guidelines:** [Grid tokens documentation](https://r.orange.fr/r/S-ouds-doc-token-grid)

Grid layout properties that adapt to screen size.

| Token | Usage |
|-------|-------|
| `OudsTheme.grids.minWidth` | Minimum grid column width |
| `OudsTheme.grids.maxWidth` | Maximum grid column width |
| `OudsTheme.grids.margin` | Grid outer margin |
| `OudsTheme.grids.columnGap` | Gap between grid columns |

```kotlin
// Using grid tokens
LazyVerticalGrid(
    columns = GridCells.Adaptive(minSize = OudsTheme.grids.minWidth),
    contentPadding = PaddingValues(OudsTheme.grids.margin),
    horizontalArrangement = Arrangement.spacedBy(OudsTheme.grids.columnGap)
) {
    // Grid items
}
```

---

## Usage Examples

### Complete Card Layout

```kotlin
@Composable
fun ProductCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(OudsTheme.spaces.fixed.medium),
        shape = RoundedCornerShape(OudsTheme.borders.radius.large),
        elevation = CardDefaults.cardElevation(
            defaultElevation = OudsTheme.elevations.raised
        ),
        border = BorderStroke(
            width = OudsTheme.borders.width.thin,
            color = OudsTheme.colorScheme.border.muted
        )
    ) {
        Column(
            modifier = Modifier.padding(OudsTheme.spaces.fixed.large),
            verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
        ) {
            Text(
                text = stringResource(R.string.product_name),
                style = OudsTheme.typography.heading.medium,
                color = OudsTheme.colorScheme.content.default
            )
            Text(
                text = stringResource(R.string.product_description),
                style = OudsTheme.typography.body.medium.default,
                color = OudsTheme.colorScheme.content.muted,
                modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.body.medium)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(OudsTheme.sizes.icon.decorative.small),
                    tint = OudsTheme.colorScheme.content.status.positive
                )
                Text(
                    text = "4.5",
                    style = OudsTheme.typography.label.medium.strong
                )
            }
        }
    }
}
```

### Styled Button

```kotlin
@Composable
fun CustomStyledButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.colorScheme.action.enabled,
            contentColor = OudsTheme.colorScheme.content.onAction.enabled
        ),
        shape = RoundedCornerShape(OudsTheme.borders.radius.pill),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = OudsTheme.elevations.raised
        ),
        contentPadding = PaddingValues(
            horizontal = OudsTheme.spaces.fixed.large,
            vertical = OudsTheme.spaces.fixed.medium
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(OudsTheme.sizes.icon.decorative.small)
            )
            Text(
                text = stringResource(R.string.add_to_cart),
                style = OudsTheme.typography.label.large.strong
            )
        }
    }
}
```

### Text Hierarchy

```kotlin
@Composable
fun ArticleContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(OudsTheme.spaces.fixed.large),
        verticalArrangement = Arrangement.spacedBy(OudsTheme.spaces.fixed.medium)
    ) {
        Text(
            text = stringResource(R.string.article_title),
            style = OudsTheme.typography.heading.large,
            color = OudsTheme.colorScheme.content.default
        )
        Text(
            text = stringResource(R.string.article_subtitle),
            style = OudsTheme.typography.heading.small,
            color = OudsTheme.colorScheme.content.muted
        )
        HorizontalDivider(
            thickness = OudsTheme.borders.width.thin,
            color = OudsTheme.colorScheme.border.muted
        )
        Text(
            text = stringResource(R.string.article_body),
            style = OudsTheme.typography.body.medium.default,
            color = OudsTheme.colorScheme.content.default,
            modifier = Modifier.widthIn(max = OudsTheme.sizes.maxWidth.body.medium)
        )
        Text(
            text = stringResource(R.string.article_caption),
            style = OudsTheme.typography.label.small.default,
            color = OudsTheme.colorScheme.content.muted
        )
    }
}
```

---

## Token Generation

All OUDS tokens are **generated by Tokenator**, a tool that converts Figma design tokens to Kotlin code. Generated files include comments like:

```kotlin
// Orange brand tokens version 2.5.0
// Generated by Tokenator
```

**Do not manually edit generated token files.** All token modifications must be made in Figma and regenerated through Tokenator.

---

## References

- **Documentation:** https://android.unified-design-system.orange.com/
- **Repository:** https://github.com/Orange-OpenSource/ouds-android
- **Design System:** https://unified-design-system.orange.com/
- **Color Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-color
- **Typography Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-typography
- **Space Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-space
- **Size Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-size
- **Border Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-border
- **Elevation Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-elevation
- **Opacity Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-opacity
- **Grid Guidelines:** https://r.orange.fr/r/S-ouds-doc-token-grid
