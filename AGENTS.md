# AGENTS.md - AI Agent Guidelines for OUDS Android

This document provides context and guidelines for AI agents assisting with the **OUDS Android** (Orange Unified Design System for Android) project.

## Project Overview

**OUDS Android** is an Android design system library that provides Orange-branded UI components and tokens for building consistent, accessible Android applications across all Orange brands and affiliates.

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Minimum SDK**: API 23 (Android 6.0)
- **Build System**: Gradle (Kotlin DSL)
- **License**: MIT
- **Repository**: https://github.com/Orange-OpenSource/ouds-android
- **Documentation**: https://android.unified-design-system.orange.com/
- **Distribution**: Maven Central

## Project Structure

The project is organized into the following modules:

### Core Modules

- **`:core`** - Core library containing reusable UI components (e.g., `OudsTag`, buttons, etc.)
- **`:foundation`** - Foundation layer with basic design primitives
- **`:global-raw-tokens`** - Raw design tokens (colors, typography, spacing, etc.)
- **`:theme-contract`** - Theme abstraction layer and token interfaces (e.g., `OudsDrawableResources`, `OudsAlertTokens`)

### Theme Modules

- **`:theme-orange`** - Orange brand theme implementation
- **`:theme-orange-compact`** - Compact variant of Orange theme
- **`:theme-orange-business-tools`** - Orange Business Tools theme variant
- **`:theme-sosh`** - Sosh brand theme implementation
- **`:theme-wireframe`** - Wireframe theme for development/prototyping

### Application & Testing

- **`:app`** - Demo application "Design Toolbox" showcasing components (e.g., `AlertMessageDemoState`, `InlineAlertDemoState`, `ComponentCode`)
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
- **Component Tokens** (`theme-contract`) - Component-specific token definitions (e.g., `OudsAlertTokens`)
- **Theme Implementations** - Concrete values for each brand

### 3. Component Structure

Components follow a consistent pattern:
- Composable functions in `:core`
- Token-driven styling via theme
- Preview functions for development
- Accessibility support built-in

## Development Guidelines

### Code Style

1. **Kotlin Conventions**: Follow official Kotlin coding conventions
2. **Compose Best Practices**: Use modern Jetpack Compose patterns
3. **Naming**: 
   - Prefix components with `Ouds` (e.g., `OudsTag`, `OudsButton`)
   - Token classes end with `Tokens` (e.g., `OudsAlertTokens`)
   - Theme-related resources use `Ouds` prefix (e.g., `OudsDrawableResources`)

### File Organization

- Component files in `:core/src/main/java/com/orange/ouds/core/component/`
- Token definitions in `:theme-contract/src/main/java/com/orange/ouds/theme/tokens/`
- Demo/preview code in `:app/src/main/java/com/orange/ouds/app/ui/components/`

### Adding New Components

When adding new components:

1. Create the composable in `:core` module
2. Define component tokens in `:theme-contract`
3. Implement token values in theme modules (`:theme-orange`, etc.)
4. Add demo screens in `:app` module
5. Include `@Preview` annotations for IDE preview
6. Ensure accessibility semantics are properly implemented

### State Management

Demo screens use state classes (e.g., `AlertMessageDemoState`, `InlineAlertDemoState`) to:
- Manage component configuration
- Provide interactive controls in demo app
- Showcase different component variants

### Resource Handling

- Drawable resources defined via `OudsDrawableResources` interface
- Each theme provides concrete drawable implementations
- Use token system for colors, dimensions, and other values

## Testing

- Unit tests for logic and state management
- Compose UI tests for component behavior
- Visual regression testing (where applicable)
- Accessibility testing should be considered

## Documentation

- Public APIs must have KDoc comments
- Documentation generated with Dokka
- Published to https://android.unified-design-system.orange.com/
- README examples should be clear and concise

## Common Tasks

### Adding a New Theme Variant

1. Create new module (e.g., `:theme-newbrand`)
2. Implement `OudsTheme` interface from `:theme-contract`
3. Define all required token values
4. Add to `:app` for testing
5. Update documentation

### Modifying Existing Components

1. Check token definitions in `:theme-contract`
2. Update component implementation in `:core`
3. Verify changes across all theme modules
4. Update demo screens if needed
5. Test in light and dark modes

### Working with Tokens

1. Raw tokens → `:global-raw-tokens`
2. Component contracts → `:theme-contract/tokens/components/`
3. Theme implementations → specific theme modules

## Build & Deploy

- **Build**: `./gradlew build`
- **Run app**: `./gradlew :app:installDebug`
- **Tests**: `./gradlew test`
- **Distribution**: Maven Central via `com.orange.ouds.android` group

## Important Considerations

### 1. Multi-Brand Support

Always consider that changes affect multiple brands. Test components with:
- Orange theme
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

## Files to Watch

Based on recent edits, key areas of active development:

- **Alert Components**: `OudsAlertTokens`, demo states
- **Tag Component**: `OudsTag.kt`
- **Theme Resources**: `OudsDrawableResources.kt`
- **Demo Infrastructure**: `ComponentCode.kt`, demo state classes

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines.

## Resources

- **Documentation**: https://android.unified-design-system.orange.com/
- **Repository**: https://github.com/Orange-OpenSource/ouds-android
- **Demo App**: http://oran.ge/designtoolbox
- **Issues**: https://github.com/Orange-OpenSource/ouds-android/issues

## Data Privacy & Permissions

- This SDK does not handle personal data
- No device permissions required
- Complies with Orange data privacy standards

---

**Last Updated**: 2025
**For AI Agents**: This document is specifically designed to provide context for AI assistants. When making changes, consider the multi-module, multi-brand nature of this design system.
