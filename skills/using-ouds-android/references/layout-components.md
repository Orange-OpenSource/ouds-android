# OUDS Android — Layout Components

All components are in the `com.orange.ouds.core.component` package.  
All user-visible strings must use `stringResource(R.string.*)` — never hardcode.

> **Naming Convention:** All OUDS components follow the `Ouds*` prefix naming pattern (e.g., `OudsButton`, `OudsTag`, `OudsTextInput`).

## Table of Contents

- [OudsColoredBox](#coloredbox) — Colored surface container
- [OudsDivider](#divider) — Horizontal and vertical dividers

---

## ColoredBox

Creates a colored surface where child OUDS components automatically switch to their monochrome variant.

**Colors:** 24 values organized by category:
- **Background** (5): `BackgroundInverseHigh`, `BackgroundInverseLow`, `BackgroundPrimary`, `BackgroundSecondary`, `BackgroundTertiary`
- **Brand** (3): `BrandPrimary`, `BrandSecondary`, `BrandTertiary`
- **Overlay** (3): `OverlayDropdown`, `OverlayModal`, `OverlayTooltip`
- **Status** (8): `StatusAccentEmphasized`, `StatusAccentMuted`, `StatusInfoEmphasized`, `StatusInfoMuted`, `StatusNegativeEmphasized`, `StatusNegativeMuted`, `StatusPositiveEmphasized`, `StatusPositiveMuted`, `StatusWarningEmphasized`, `StatusWarningMuted`
- **Surface** (5): `SurfaceInverseHigh`, `SurfaceInverseLow`, `SurfacePrimary`, `SurfaceSecondary`, `SurfaceTertiary`

> **Note:** Not all colors are supported by all themes. Check `color.isSupported` before using a color in production code.

```kotlin
// Basic usage
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    // Child OUDS components adopt monochrome colors automatically
    OudsButton(label = stringResource(R.string.action), onClick = { })
    Text(
        text = stringResource(R.string.description),
        color = OudsTheme.colorScheme.content.default
    )
}

// Check if color is supported by current theme
val color = OudsColoredBoxColor.BrandPrimary
if (color.isSupported) {
    OudsColoredBox(color = color) {
        // Content
    }
}

// Different color categories
OudsColoredBox(color = OudsColoredBoxColor.BrandPrimary) { /* Brand colors */ }
OudsColoredBox(color = OudsColoredBoxColor.StatusPositiveEmphasized) { /* Status colors */ }
OudsColoredBox(color = OudsColoredBoxColor.SurfacePrimary) { /* Surface colors */ }
```

---

## Divider

```kotlin
// Horizontal
OudsHorizontalDivider(modifier = Modifier.fillMaxWidth())

// Vertical
OudsVerticalDivider(modifier = Modifier.height(50.dp))
```