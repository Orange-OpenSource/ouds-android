# AGENTS.md - AI Agent Guidelines for OUDS Android

This document provides context and guidelines for AI agents assisting with the **OUDS Android** (Orange Unified Design System for Android) project.

## Project Overview

**OUDS Android** is an Android design system library that provides Orange-branded UI components and tokens for building consistent, accessible Android applications across all Orange brands and affiliates. This library is the Android implementation of the [Orange Unified Design System](https://unified-design-system.orange.com/).

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Minimum SDK**: API 23 (Android 6.0)
- **Build System**: Gradle (Kotlin DSL)
- **License**: MIT
- **Repository**: https://github.com/Orange-OpenSource/ouds-android
- **Documentation**: https://android.unified-design-system.orange.com/
- **Distribution**: Maven Central

## ⚠️ CRITICAL: Mandatory Skill Usage Protocol

**AI agents MUST systematically load the appropriate skill(s) BEFORE starting ANY task related to OUDS Android.**
This is **NOT optional**. Skills contain essential project-specific context, conventions, patterns, and up-to-date information that are critical for maintaining
code quality and consistency.

### When to Load Skills

#### 🔧 `using-ouds-android` - REQUIRED for ALL coding tasks

**Load this skill FIRST when:**

- Writing any Kotlin/Compose code using OUDS components
- Creating new components (`OudsButton`, `OudsTextInput`, etc.)
- Modifying existing component APIs or implementations
- Setting up OUDS library dependencies
- Configuring themes (`OudsTheme`, `OrangeTheme`, etc.)
- Accessing design tokens (`OudsTheme.colorScheme`, `OudsTheme.spaces`, etc.)
- Debugging or fixing OUDS-related issues
- Creating examples, samples, or demo screens
- Writing tests for OUDS components
- Migrating from Material 3 to OUDS
- **ANY code modification or creation task in this repository**

#### 📚 `understanding-ouds-android-vocabulary` - REQUIRED for conceptual questions

**Load this skill FIRST when:**

- User asks to explain, define, or clarify OUDS terminology
- Questions about tokens (raw, semantic, component)
- Questions about Tokenator, OudsThemeContract, OudsTheme
- "What is X?", "How does X work?", "Why do we use X?"
- Understanding relationships between architecture elements
- User seems confused about OUDS concepts
- User uses incorrect terminology that needs clarification
- **ANY conceptual or architectural question**

### Enforcement

**ZERO exceptions.** Even if you think you know the answer or pattern:

1. **Always load the appropriate skill(s) first**
2. **Read the skill content thoroughly**
3. **Follow the patterns and conventions specified in the skill**
4. **Only then proceed with implementation**
   Skills are kept up-to-date with the latest project conventions, and bypassing them may result in:

- ❌ Outdated patterns or deprecated APIs being used
- ❌ Inconsistent code style
- ❌ Missing critical context or requirements
- ❌ Breaking changes that could have been avoided

### Skill Loading Workflow

User Request → Load Appropriate Skill(s) → Review Skill Content → Execute Task

**Example (CORRECT):**
User: "Add an hiddenCharacters parameter to OudsPinCodeInput"
Agent:

1. ✅ Load using-ouds-android skill
2. ✅ Review component API patterns from skill
3. ✅ Check for similar existing parameters
4. ✅ Follow documented conventions for parameter naming and defaults
5. ✅ Implement according to skill guidelines

**Counter-example (FORBIDDEN):**
User: "Add an hiddenCharacters parameter to OudsPinCodeInput"
Agent:
❌ Directly starts coding without loading skill
❌ Risks missing project-specific conventions
❌ May use inconsistent naming or wrong defaults

**Multiple skills example:**
User: "What's the difference between semantic and component tokens, and how do I use them in OudsButton?"
Agent:

1. ✅ Load understanding-ouds-android-vocabulary skill (for terminology)
2. ✅ Load using-ouds-android skill (for code usage)
3. ✅ Review both skills to provide complete answer
4. ✅ Respond with accurate terminology AND practical code examples

## Project Structure

The project is organized into the following modules:

### Essential Modules

- **`:core`** - Core library containing reusable UI components (e.g., `OudsTag`, buttons, etc.)
- **`:foundation`** - Foundation layer containing code used in all modules (extension methods, annotations, etc.)
- **`:global-raw-tokens`** - Raw design tokens (colors, typography, spacing, etc.)
- **`:theme-contract`** - Theme abstraction layer and token interfaces (e.g., `OudsThemeContract`, `OudsAlertTokens`)

### Theme Modules

- **`:theme-orange`** - Orange brand theme implementation
- **`:theme-orange-compact`** - Compact variant of Orange theme
- **`:theme-sosh`** - Sosh brand theme implementation
- **`:theme-wireframe`** - Wireframe theme for development/prototyping

### Application & Testing

- **`:app`** - Demo application "Design Toolbox" showcasing components and design tokens
- **`:core-test`** - Shared testing utilities
- **`:dokka-plugin`** - Custom Dokka plugin for documentation generation

### Build Configuration

- **`:buildSrc`** - Build configuration and shared Gradle logic
- **Root project** - Overall project configuration

## Key Architecture Concepts

### 1. Theming System

OUDS Android uses a multi-brand theming system:

```kotlin
OudsTheme(
    theme = OrangeTheme(
        orangeFontFamily = OrangeFontFamily(
            latin = OrangeHelveticaNeueLatin.Bundled(...)
        )
    )
) {
    // UI components here
}
```

- Themes are interchangeable (Orange, Sosh, Wireframe, etc.)
- Each theme implements the `theme-contract` interfaces
- Tokens drive all visual properties (colors, typography, spacing, etc.)

### 2. Token-Based Design

- **Raw Tokens** (`global-raw-tokens`) - Base design values
- **Semantic Tokens** (`theme-contract`) - Semantic token definitions (e.g., `OudsColorActionSemanticTokens`)
- **Component Tokens** (`theme-contract`) - Component-specific token definitions (e.g., `OudsAlertTokens`)
- **Theme Implementations** - Concrete semantic and component token values for each brand, as well as brand-specific raw tokens

The files containing the tokens are generated by an external tool called Tokenator. This tool takes Figma tokens as an input and convert them to Kotlin code.

### 3. Component Structure

Components follow a consistent pattern:
- Composable functions in `:core`
- Token-driven styling via theme
- Preview functions for development
- Accessibility support built-in
- KDoc-based documentation for public methods that includes simple code samples

## Development Guidelines

### Code Style

1. **Compose Best Practices**: Use modern Jetpack Compose patterns
2. **Coding Conventions**: Follow [Style guidelines for Jetpack Compose APIs](https://developer.android.com/develop/ui/compose/api-guidelines) which basically includes:
   - The [API Guidelines for Jetpack Compose](https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md) (based on [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html))
   - The [API Guidelines for @Composable components in Jetpack Compose](https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-component-api-guidelines.md)
3. **Naming**: 
   - Prefix components with `Ouds` (e.g., `OudsTag`, `OudsButton`)
   - Token classes end with `Tokens` (e.g., `OudsAlertTokens`)

### File Organization

- Component files in `:core/src/main/java/com/orange/ouds/core/component/`
- Token definitions in `:theme-contract/src/main/java/com/orange/ouds/theme/tokens/`
- Demo app code in `:app/src/main/java/com/orange/ouds/app`

### Adding New Components

When adding new components:

1. Verify that component tokens generated by Tokenator are defined in `:theme-contract` and implemented in theme modules (`:theme-orange`, etc.)
2. Create one or more composables in `:core` module, trying to match the equivalent Material 3 APIs as much as possible
3. Ensure accessibility semantics are properly implemented
4. Add a sample code for each component composable
5. Include `@Preview` annotations for IDE preview
6. Add snapshot tests in `:core-test` module and generate snapshots for each theme
7. Add instrumented Compose UI tests for user interactions such as clicks
8. Add demo screen in `:app` module
9. Update `Component.kt` in `:buildSrc` as well as the component KDoc so that it references the design guidelines link and version

### State Management

Demo screens use state classes (e.g., `AlertMessageDemoState`, `InlineAlertDemoState`) to:
- Manage component configuration
- Provide interactive controls in demo app
- Showcase different component variants
Customization parameters in demo screens match the parameters of the component APIs

### Resource Handling

- Drawable resources defined via `OudsDrawableResources` interface
- Each theme provides concrete drawable implementations
- Use token system for colors, dimensions, and other values

## Testing

- Snapshot tests for visual regression
- Compose UI tests for component behavior
- Accessibility testing should be considered

## Documentation

- Public APIs must have KDoc comments
- Documentation generated with Dokka
- Published to https://android.unified-design-system.orange.com/
- README examples should be clear and concise

## Common Tasks

### Adding a New Theme

1. Create new module (e.g., `:theme-newbrand`)
2. Implement `OudsTheme` interface from `:theme-contract`
3. Use concrete token values generated by Tokenator
4. Add to `:app` for testing
5. Update documentation

### Modifying Existing Components

1. Check token definitions in `:theme-contract`
2. Update component implementation in `:core`
3. Update reference snapshots in all theme modules
4. Update demo screens if needed

## Build & Deploy

- **Build**: `./gradlew build`
- **Run demo app**: `./gradlew :app:installProdDebug`
- **Snapshot tests**: `./gradlew verifyPaparazziDebug`
- **Instrumented tests**: `./gradlew :core:connectedCheck`
- **Distribution**: Maven Central via `com.orange.ouds.android` group

## Important Considerations

### 1. Multi-Brand Support

Always consider that changes affect multiple brands. Test components with:
- Orange theme
- Orange Compact theme
- Sosh theme
- Wireframe theme
- Light and dark modes

### 2. Accessibility

- Provide content descriptions
- Ensure proper semantic roles
- Support TalkBack and other accessibility services
- Maintain sufficient color contrast

### 3. Backward Compatibility

- Minimum SDK 23 (Android 6.0)
- Be mindful of deprecated APIs
- Maintain stable public API surface

### 4. Performance

- Compose best practices (remember, derivedStateOf, etc.)
- Avoid unnecessary recompositions
- Optimize preview rendering

### 5. Miscellaneous

- Any hardcoded string must be avoided; use stringResource.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines.

## AI Skills

Agent skills are located in the `skills/` directory at the repository root. The `.agents/`, `.claude/`, and `.opencode/` directories contain `skills` symlinks pointing to the root `skills/` directory, ensuring compatibility across all major AI agent tools.

### Available skills

| Skill | When to load |
|---|---|
| `understanding-ouds-android-vocabulary` | User asks to explain, define, or understand OUDS-specific terminology and concepts (tokens, Tokenator, OudsThemeContract, tinted, OudsError, etc.) or relationships between OUDS architecture elements |
| `using-ouds-android` | User needs to write code, create components, set up OUDS, configure themes, access tokens, or use any Ouds* component in Kotlin/Compose |

### Maintaining Skills

The skills must be kept synchronized with code changes to ensure AI agents have accurate, up-to-date information.

**When to update skills:**

- Before each release

**How to update:**

1. Navigate to `skills/maintenance/update-skills-prompt.md`
2. Copy the prompt and execute it with your AI agent
3. Review the generated report and proposed changes
4. Commit the updated skills

The update prompt automatically detects changes in components, tokens, themes, integration setup, patterns, and deprecations by analyzing the codebase source
code, KDoc documentation, and demo app.

## Resources

- **Documentation**: https://android.unified-design-system.orange.com/
- **Repository**: https://github.com/Orange-OpenSource/ouds-android
- **Demo App**: http://oran.ge/designtoolbox
- **Issues**: https://github.com/Orange-OpenSource/ouds-android/issues
- **Orange Unified Design System**: https://unified-design-system.orange.com/

## Data Privacy & Permissions

- This SDK does not handle personal data
- No device permissions required
- Complies with Orange data privacy standards

---

**For AI Agents**: This document is specifically designed to provide context for AI assistants. When making changes, consider the multi-module, multi-brand nature of this design system.
