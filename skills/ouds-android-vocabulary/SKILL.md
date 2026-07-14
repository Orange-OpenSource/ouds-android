---
name: ouds-android-vocabulary
description: Use when the user asks about OUDS-specific terms such as tokenator, token, raw token, semantic token, component token, theme, OudsThemeContract, OudsTheme, component, OudsColoredBox, Tokenator
license: MIT
---

# OUDS Android Vocabulary

| Term | Definition |
|---|---|
| **Tokenator** | Internal tool that converts Figma-exported JSON token specs into Kotlin source files and submits them via pull requests; generates files in `:global-raw-tokens` and `:theme-contract` |
| **token** | Named variable holding a design value (color, size, spacing, border…); most tokens are produced by Tokenator |
| **raw token** | Token whose value is a primitive Kotlin/Compose type (`Color`, `Dp`, `Int`…); grouped in the `:global-raw-tokens` module (e.g. `OudsColorRawTokens`, `OudsBorderRawTokens`) |
| **semantic token** | Token that references a raw token and carries semantic meaning (e.g. `actionColorTokens.enabled`); used directly inside components via `OudsTheme.*` |
 | **component token** | Token scoped to a specific component, referencing semantic tokens for per-component styling overrides (e.g. `OudsButtonTokens`, `OudsTagTokens`); exposed to consumers via `@OptIn(RestrictedOudsApi::class) OudsTheme.components` |
| **OudsThemeContract** | Kotlin interface that every theme must implement; centralises all semantic token groups (`colorTokens`, `borderTokens`, `fontTokens`, `spaceTokens`, `componentsTokens`, etc.) and drawable resources |
| **theme** | Cohesive set of tokens and assets (fonts, drawables) controlling the look and feel of an app; available themes: `OrangeTheme`, `OrangeCompactTheme`, `SoshTheme`, `WireframeTheme` |
| **OudsTheme** | The Jetpack Compose entry-point composable that wraps your UI with a given theme; it also exposes static accessors (`OudsTheme.colorScheme`, `OudsTheme.spaces`, `OudsTheme.borders`, etc.) for reading token values inside composables |
| **component** | Jetpack Compose composable shipped by OUDS, always prefixed with `Ouds` (e.g. `OudsButton`, `OudsTag`, `OudsCheckboxItem`); token-driven, accessible, multi-brand |
| **OudsColoredBox** | Special OUDS container composable that creates a semantically colored surface; child OUDS components automatically switch to their monochrome variant to maximise contrast |
| **OudsButtonIcon** | Wrapper class used to pass an icon to `OudsButton` or `OudsSmallButton`; accepts `ImageVector`, `Painter`, or `ImageBitmap`, plus a `tinted` flag |
| **OudsControlItemIcon** | Wrapper class used to pass an optional icon to item-type controls (`OudsCheckboxItem`, `OudsRadioButtonItem`, `OudsSwitchItem`); accepts `ImageVector`, `Painter`, or `ImageBitmap`, plus a `tinted` flag |
| **OudsError** | Data class wrapping an error message (plain `String` or `AnnotatedString`) to display in input components (`OudsTextInput`, `OudsTextArea`, `OudsPasswordInput`, `OudsPinCodeInput`, `OudsCheckboxItem`, etc.) |
| **tinted** | Boolean flag on icon wrapper classes (`OudsButtonIcon`, `OudsControlItemIcon`, `OudsLinkIcon`, etc.) — when `true` (default) the icon color is driven by tokens; when `false` the painter's own colors are preserved (useful for brand/multi-color icons) |

## Token access inside composables

Tokens are accessed via the `OudsTheme` static object inside any composable wrapped by `OudsTheme { }`:

| Accessor | Content |
|---|---|
| `OudsTheme.colorScheme` | Color semantic tokens (content, background, border, action, surface…) |
| `OudsTheme.borders` | Border radius, style and width tokens |
| `OudsTheme.spaces` | Spacing tokens (`fixed.*`, `scaled.*`) |
| `OudsTheme.sizes` | Size tokens |
| `OudsTheme.typography` | Typography / font tokens |
| `OudsTheme.elevations` | Elevation / shadow tokens |
| `OudsTheme.grids` | Grid tokens |
| `OudsTheme.opacities` | Opacity tokens |
| `OudsTheme.effects` | Visual effect tokens |
| `OudsTheme.components` | Component-level tokens for advanced customization (requires `@OptIn(RestrictedOudsApi::class)`) |

## Token hierarchy

```
Figma design tokens
        │
    Tokenator (generates Kotlin)
        │
        ├── :global-raw-tokens   ← raw values (OudsColorRawTokens, OudsDimensionRawTokens…)
        │
        └── :theme-contract      ← semantic interfaces + component token interfaces
                │
                └── :theme-orange / :theme-sosh / :theme-wireframe / :theme-orange-compact
                        └── concrete token values per brand
                                │
                                └── :core   ← Ouds* composables read tokens via OudsTheme.*
```

## When to load which skill

| Task | Skill to load |
|---|---|
| Write or review Kotlin/Compose code using OUDS components or tokens | `ouds-android-framework-usage` |
| Ask about OUDS-specific terminology | `ouds-android-vocabulary` (this skill) |
