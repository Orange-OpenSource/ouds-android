# Getting started

The OUDS Android library is compatible with **Android 5.0 (API level 21) and higher**.

## 1. Add library dependency

> **OUDS Android is not yet released** so you cannot use it. We will provide integration instructions here after the first release.

## 2. Use OudsTheme

`OudsTheme` is a Material theme extension for Jetpack Compose applications. Cause OUDS support multi-theme, you must specify the theme to use for your
application.

Use `OudsTheme` in your screens by providing the `OrangeTheme` for example as theme contract to use the Orange theme.

```kotlin
OudsTheme(themeContract = OrangeTheme()) {
    // Use OUDS components or modules here for an interface
    // matching the Orange Unified Design System
}
```

<br/>

> Use another `OudsThemeContract` implementation if you want to use a custom theme. For example the existing `WhiteLabelTheme`.

<br/>
