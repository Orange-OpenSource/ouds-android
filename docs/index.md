# OUDS Android

The OUDS Android library is compatible with **Android 5.0 (API level 21) and higher**. It serves as the interface between applications and custom themes,
providing essential components for app development.

## Getting started

### Gradle

OUDS Android is available through [Maven Central Repository](https://central.sonatype.com/search?q=com.orange.ouds.android). To use it, add the OUDS Android
library core and the theme you want to use in the `dependencies` section of your app build script:</br></br>

<details open>
<summary>Kotlin DSL</summary>

```kotlin
dependencies {
    // ...
    implementation("com.orange.ouds.android:ouds-core:0.4.0")
    implementation("com.orange.ouds.android:ouds-theme-orange:0.4.0")
    // ...
}
```

</details></br>

<details>
<summary>Groovy DSL</summary>

```shell
dependencies {
    // ...
    implementation 'com.orange.ouds.android:ouds-core:0.4.0'
    implementation 'com.orange.ouds.android:ouds-theme-orange:0.4.0'
    // ...
}
```

</details>

### Themes

The `OudsTheme` method is an extension of the `MaterialTheme` method which allows to use the Orange Unified Design System in Jetpack Compose applications.
Because OUDS Android supports multi-theme, you must provide the theme to use in your application as a parameter. OUDS Android components such as `OudsButton`
and `OudsCheckbox` as well as Material components use values provided by the theme when they are rendered on screen. For instance if you want to use the Orange
theme:

```kotlin
OudsTheme(theme = OrangeTheme()) {
    // Use OUDS Android or Material components here for an interface
    // matching the Orange Unified Design System with the Orange theme
}
```

### Design tokens

Design tokens are a set of platform-agnostic design values that provide a consistent way to manage and maintain the design system across design and development
for each platforms. You can find more information about design tokens in
the [Orange Unified Design System documentation](https://unified-design-system.orange.com/472794e18/p/903414-introduction).

Semantic tokens in OUDS Android can be accessed using the various properties of the `OudsTheme` singleton. As an example, the token for the small heading font
can be retrieved as follows:

```kotlin
OudsTheme.typography.heading.large
```

Please note that the token hierarchy in `OudsTheme` is as close as possible as token names in Figma. Thus `ouds/color/content/on/status/neutral/emphasized` in
Figma is equivalent to:

```kotlin
OudsTheme.colorScheme.content.onStatus.neutral.emphasized
```

Raw and component tokens are not directly available in the `OudsTheme` singleton and should only be used when customizing themes.

### Components

OUDS Android components are all prefixed with `Ouds`. For instance, `OudsButton` is the equivalent of the Material `Button` for Orange Unified Design System.

Using Material components such as `Text` when they are surrounded by the `OudsTheme` method will make them use the current theme colors, because Orange Unified
Design System maps several colors from the current theme to Material colors. However, please use the OUDS Android variant of a component instead of its Material
counterpart when it is available, otherwise you will not fully conform to the Orange Unified Design System.

Several sample codes for the OUDS Android components are also available in the `com.orange.ouds.core.component.samples` package.

## Going further

### Light and dark themes

OUDS Android handles light and dark themes with the `darkThemeEnabled` parameter of the `OudsTheme` method. However, it is also possible to force light or dark
theme, or invert the current theme anywhere in the design hierarchy by using the `OudsThemeTweak` method:

```kotlin
OudsThemeTweak(tweak = OudsTheme.Tweak.ForceDark) {
    // The content here will be displayed using the dark theme
    // whether the system uses light or dark theme
}
```

### Customizing themes

If you want to use a custom theme, you can create your own `OudsThemeContract` implementation or extend `OrangeTheme` to create a customized theme close to that
of Orange.

## Data privacy

The Orange Unified Design System library is an SDK that allows a developer to create Orange branded mobile application. As such:

- this SDK does not handle any personal data.
- this SDK does not require any device permission to work.