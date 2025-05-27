# Getting started

The OUDS Android library is compatible with **Android 5.0 (API level 21) and higher**.

## 1. Add library dependencies

Orange Unified Design System for Android is available through [Maven Central Repository](https://mvnrepository.com/artifact/com.orange.ouds.android). To use it:

1. Open the `build.gradle` file of your application.

2. Make sure that the `repositories` section includes Maven Central. For example:

   ```kotlin
   allprojects {
     repositories {
       google()
       mavenCentral()
     }
   }
   ```

3. Add the OUDS library and the theme you want to use in the `dependencies` section.

    ```kotlin
    dependencies {
      // ...
      implementation("com.orange.ouds.android:ouds-core:0.2.0")
      implementation("com.orange.ouds.android:ouds-theme-orange:0.2.0")
      // ...
    }
    ```

## 2. Use OudsTheme

`OudsTheme` is a Material theme extension for Jetpack Compose applications. Because OUDS supports multi-theme, you must specify the theme to use for your
application.

Use `OudsTheme` in your screens by providing the theme contract implementation available in the dependency you added in the previous step. In our example `OrangeTheme`.

```kotlin
OudsTheme(themeContract = OrangeTheme()) {
    // Use OUDS components or modules here for an interface
    // matching the Orange Unified Design System
}
```

<br/>

> If you want to use a custom theme, you can create your own `OudsThemeContract` implementation or extend `OrangeTheme` to create a customized theme close to 
> that of Orange.

<br/>
