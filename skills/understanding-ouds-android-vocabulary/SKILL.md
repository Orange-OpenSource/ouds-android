---
name: understanding-ouds-android-vocabulary
description: Use this skill whenever the user asks about OUDS-specific terminology, concepts, or vocabulary. This includes questions about tokens (raw, semantic, component), Tokenator, OudsThemeContract, OudsTheme, OudsColoredBox, or core OUDS architectural concepts. ALWAYS trigger when the user asks to explain, define, clarify, understand the difference, or asks 'what is', 'how does X work', 'why do we use', 'what's the purpose of', or any conceptual question about OUDS architecture, even if they don't use these exact phrases. Also trigger when they ask about relationships between OUDS architecture elements (e.g., how tokens relate to each other, theme hierarchy, how Tokenator generates code, where tokens are defined). Trigger even when they seem confused about OUDS terminology without explicitly asking for definitions, or when they use incorrect terminology that suggests they need clarification.
license: MIT
---

# OUDS Android Vocabulary

| Term                  | Definition                                                                                                                                                                                                                                    |
|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **component**         | Jetpack Compose composable shipped by OUDS, always prefixed with `Ouds` (e.g. `OudsButton`, `OudsTag`, `OudsCheckboxItem`); token-driven, accessible, multi-brand                                                                             |
| **component token**   | Token scoped to a specific component, referencing semantic or raw tokens for per-component styling overrides (e.g. `OudsButtonTokens`, `OudsTagTokens`); exposed to consumers via `@OptIn(RestrictedOudsApi::class) OudsTheme.components`     |
| **OudsColoredBox**    | Special OUDS container composable that creates a semantically colored surface; child OUDS components automatically switch to their monochrome variant to maximise contrast                                                                    |
| **OudsTheme**         | The Jetpack Compose entry-point composable that wraps your UI with a given theme; it also exposes static accessors (`OudsTheme.colorScheme`, `OudsTheme.spaces`, `OudsTheme.borders`, etc.) for reading token values inside composables       |
| **OudsThemeContract** | Kotlin interface that every theme must implement; centralises all semantic token groups (`colorTokens`, `borderTokens`, `fontTokens`, `spaceTokens`, `componentsTokens`, etc.) and drawable resources                                         |
| **theme**             | Cohesive set of tokens and assets (fonts, drawables) controlling the look and feel of an app; available themes: `OrangeTheme`, `OrangeCompactTheme`, `SoshTheme`, `WireframeTheme`                                                            |
| **token**             | Named variable holding a design value (color, size, spacing, border…); most tokens are produced by Tokenator                                                                                                                                  |
| **Tokenator**         | Internal tool that converts Figma-exported JSON token specs into Kotlin source files and submits them via pull requests                                                                                                                       |
| **raw token**         | Token whose value is a primitive Kotlin/Compose type (`Color`, `Dp`, `Int`…); grouped in the `:global-raw-tokens` module (e.g. `OudsColorRawTokens`, `OudsBorderRawTokens`)                                                                   |
| **semantic token**    | Token that references a raw token and carries semantic meaning (e.g. `colorScheme.action.enabled`); used directly inside components via `OudsTheme.*`                                                                                         |

## Token access inside composables

Tokens are accessed via the `OudsTheme` static object inside any composable wrapped by `OudsTheme { }`:

| Accessor                | Content                                                                                         |
|-------------------------|-------------------------------------------------------------------------------------------------|
| `OudsTheme.borders`     | Border radius, style and width tokens                                                           |
| `OudsTheme.colorScheme` | Color semantic tokens (content, background, border, action, surface…)                           |
| `OudsTheme.components`  | Component-level tokens for advanced customization (requires `@OptIn(RestrictedOudsApi::class)`) |
| `OudsTheme.effects`     | Visual effect tokens                                                                            |
| `OudsTheme.elevations`  | Elevation / shadow tokens                                                                       |
| `OudsTheme.grids`       | Grid tokens                                                                                     |
| `OudsTheme.opacities`   | Opacity tokens                                                                                  |
| `OudsTheme.sizes`       | Size tokens                                                                                     |
| `OudsTheme.spaces`      | Spacing tokens (`fixed.*`, `scaled.*`)                                                          |
| `OudsTheme.typography`  | Typography / font tokens                                                                        |

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
