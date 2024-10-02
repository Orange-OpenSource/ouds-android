# Getting started

## 1. Add library dependency

> **OUDS Android is not yet released** so you cannot use it. We will provide integration instructions here after the first release.
{style=warning}

## 2. Check the prerequisites

### 1. Compile your app with Android 11
OUDS Android library depends on Material Design library from Google. For this reason, you will have to install Android Studio 4.0 or higher to build with Android 11, and update your app’s `compileSdkVersion` to `34`.

### 2. Ensure you are using `AppCompatActivity`
Using `AppCompatActivity` will ensure that all the components work correctly. If you are unable to extend from `AppCompatActivity`, update your activities to use `AppCompatDelegate`. This will enable the `AppCompat` versions of components to be inflated among other important things.

### 3. Ensure the theme specified for your application is a `NoActionBar` theme

In the `Manifest.xml` file, your application theme should be a `NoActionBar` theme.
```xml
<application android:theme="@style/Theme.MaterialComponents.NoActionBar">
    <!-- ... -->
</application>
```

## 3. Use `OudsTheme`

`OudsTheme` is a Material theme extension for Jetpack Compose applications. Cause OUDS support multi-theme, you must specify the theme to use for your application.

Use `OudsTheme` in your screens by providing the `OrangeTheme` for example as theme contract to use the Orange theme.

```kotlin
OudsTheme(themeContract = OrangeTheme()) {
    // Use OUDS components or modules here for an interface
    // matching the Orange Unified Design System
}
```
> Use another `OudsThemeContract` implementation if you want to use a custom theme. For example the existing `WhiteLabelTheme`.