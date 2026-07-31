---
name: understanding-ouds-android-vocabulary
description: Use this skill whenever the user asks about OUDS-specific terminology, concepts, or vocabulary. This includes questions about tokens (raw, semantic, component), Tokenator, OudsThemeContract, OudsTheme, OudsColoredBox, tinted parameters, OudsError, or any OUDS-specific classes and patterns. ALWAYS trigger when the user asks to explain, define, clarify, understand the difference, or asks 'what is', 'how does X work', 'why do we use', 'what's the purpose of', or any conceptual question about OUDS concepts, even if they don't use these exact phrases. Also trigger when they ask about relationships between OUDS architecture elements (e.g., how tokens relate to each other, theme hierarchy, how Tokenator generates code, where tokens are defined). Trigger even when they seem confused about OUDS terminology without explicitly asking for definitions, or when they use incorrect terminology that suggests they need clarification.
license: MIT
---

# OUDS Android Vocabulary

| Term                    | Definition                                                                                                                                                                                                                                                |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Tokenator**           | Internal tool that converts Figma-exported JSON token specs into Kotlin source files and submits them via pull requests.                                                                                                                                  |
| **token**               | Named variable holding a design value (color, size, spacing, border…); most tokens are produced by Tokenator                                                                                                                                              |
| **raw token**           | Token whose value is a primitive Kotlin/Compose type (`Color`, `Dp`, `Int`…); grouped in the `:global-raw-tokens` module (e.g. `OudsColorRawTokens`, `OudsBorderRawTokens`)                                                                               |
| **semantic token**      | Token that references a raw token and carries semantic meaning (e.g. `colorScheme.action.enabled`); used directly inside components via `OudsTheme.*`                                                                                                     |
| **component token**     | Token scoped to a specific component, referencing semantic tokens for per-component styling overrides (e.g. `OudsButtonTokens`, `OudsTagTokens`); exposed to consumers via `@OptIn(RestrictedOudsApi::class) OudsTheme.components`                        |
| **OudsThemeContract**   | Kotlin interface that every theme must implement; centralises all semantic token groups (`colorTokens`, `borderTokens`, `fontTokens`, `spaceTokens`, `componentsTokens`, etc.) and drawable resources                                                     |
| **theme**               | Cohesive set of tokens and assets (fonts, drawables) controlling the look and feel of an app; available themes: `OrangeTheme`, `OrangeCompactTheme`, `SoshTheme`, `WireframeTheme`                                                                        |
| **OudsTheme**           | The Jetpack Compose entry-point composable that wraps your UI with a given theme; it also exposes static accessors (`OudsTheme.colorScheme`, `OudsTheme.spaces`, `OudsTheme.borders`, etc.) for reading token values inside composables                   |
| **component**           | Jetpack Compose composable shipped by OUDS, always prefixed with `Ouds` (e.g. `OudsButton`, `OudsTag`, `OudsCheckboxItem`); token-driven, accessible, multi-brand                                                                                         |
| **OudsColoredBox**      | Special OUDS container composable that creates a semantically colored surface; child OUDS components automatically switch to their monochrome variant to maximise contrast                                                                                |
| **OudsButtonIcon**      | Wrapper class used to pass an icon to `OudsButton` or `OudsSmallButton`; accepts `ImageVector`, `Painter`, or `ImageBitmap`, plus a `tinted` flag                                                                                                         |
| **OudsControlItemIcon** | Wrapper class used to pass an optional icon to item-type controls (`OudsCheckboxItem`, `OudsRadioButtonItem`, `OudsSwitchItem`); accepts `ImageVector`, `Painter`, or `ImageBitmap`, plus a `tinted` flag                                                 |
| **OudsError**           | Data class wrapping an error message (plain `String` or `AnnotatedString`) to display in input components (`OudsTextInput`, `OudsTextArea`, `OudsPasswordInput`, `OudsPinCodeInput`, `OudsCheckboxItem`, etc.)                                            |
| **tinted**              | Boolean flag on icon wrapper classes (`OudsButtonIcon`, `OudsControlItemIcon`, `OudsLinkIcon`, etc.) — when `true` (default) the icon color is driven by tokens; when `false` the painter's own colors are preserved (useful for brand/multi-color icons) |

## Token access inside composables

Tokens are accessed via the `OudsTheme` static object inside any composable wrapped by `OudsTheme { }`:

| Accessor                | Content                                                                                         |
|-------------------------|-------------------------------------------------------------------------------------------------|
| `OudsTheme.colorScheme` | Color semantic tokens (content, background, border, action, surface…)                           |
| `OudsTheme.borders`     | Border radius, style and width tokens                                                           |
| `OudsTheme.spaces`      | Spacing tokens (`fixed.*`, `scaled.*`)                                                          |
| `OudsTheme.sizes`       | Size tokens                                                                                     |
| `OudsTheme.typography`  | Typography / font tokens                                                                        |
| `OudsTheme.elevations`  | Elevation / shadow tokens                                                                       |
| `OudsTheme.grids`       | Grid tokens                                                                                     |
| `OudsTheme.opacities`   | Opacity tokens                                                                                  |
| `OudsTheme.effects`     | Visual effect tokens                                                                            |
| `OudsTheme.components`  | Component-level tokens for advanced customization (requires `@OptIn(RestrictedOudsApi::class)`) |

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

| Task                                                                | Skill to load                                        |
|---------------------------------------------------------------------|------------------------------------------------------|
| Write or review Kotlin/Compose code using OUDS components or tokens | `using-ouds-android`                                 |
| Ask about OUDS-specific terminology                                 | `understanding-ouds-android-vocabulary` (this skill) |
