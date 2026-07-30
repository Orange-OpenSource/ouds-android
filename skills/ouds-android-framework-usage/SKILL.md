---
name: ouds-android-framework-usage
description: Use this skill whenever the user needs to write code using OUDS Android components, set up the library, configure themes, or access design tokens in Kotlin/Compose. This includes adding dependencies, wrapping UI with OudsTheme, creating any Ouds* component (OudsButton, OudsTextInput, OudsNavigationBar, etc.), accessing tokens via OudsTheme.colorScheme/spaces/typography, configuring fonts, handling tinted/untinted icons, using OudsColoredBox, showing error messages, or switching themes dynamically. ALWAYS trigger when the user asks to 'create', 'show me how', 'write a composable', 'set up', or 'access' anything related to OUDS components or tokens, even if they don't explicitly mention 'OUDS framework'.
license: MIT
---

# OUDS Android Framework Usage

## 1. Gradle setup

Add the OUDS dependencies to your module's `build.gradle.kts`. Choose the theme modules you need:

```kotlin
dependencies {
    // Core components — always required
    implementation("com.orange.ouds.android:core:<version>")

    // Choose one or more theme modules:
    implementation("com.orange.ouds.android:theme-orange:<version>")         // Orange brand
    implementation("com.orange.ouds.android:theme-orange-compact:<version>") // Orange Compact variant
    implementation("com.orange.ouds.android:theme-sosh:<version>")           // Sosh brand
    implementation("com.orange.ouds.android:theme-wireframe:<version>")      // Wireframe (dev/prototyping)
}
```

Latest version available on Maven Central under group `com.orange.ouds.android`.

---

## 2. OudsTheme setup

Wrap your root composable with `OudsTheme`, passing a theme object:

```kotlin
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.orange.OrangeTheme
import com.orange.ouds.theme.orange.OrangeFontFamily
import com.orange.ouds.theme.orange.OrangeHelveticaNeueLatin

@Composable
fun App() {
    OudsTheme(
        theme = OrangeTheme(
            orangeFontFamily = OrangeFontFamily(
                latin = OrangeHelveticaNeueLatin.Bundled(
                    R.font.helvetica_neue_latin_roman,
                    R.font.helvetica_neue_latin_medium,
                    R.font.helvetica_neue_latin_bold
                )
            )
        )
    ) {
        // Your app UI here
    }
}
```

### Available themes

| Theme class | Brand | Notes |
|---|---|---|
| `OrangeTheme` | Orange | Requires Helvetica Neue font (bundled or downloadable) |
| `OrangeCompactTheme` | Orange Compact | Compact size variant of Orange |
| `SoshTheme` | Sosh | — |
| `WireframeTheme` | Wireframe | For development and prototyping only |

### OrangeTheme — font options

**Bundled font** (copy `.ttf` files to `res/font/`):
```kotlin
OrangeTheme(
    orangeFontFamily = OrangeFontFamily(
        latin = OrangeHelveticaNeueLatin.Bundled(
            R.font.helvetica_neue_latin_roman,
            R.font.helvetica_neue_latin_medium,
            R.font.helvetica_neue_latin_bold
        )
    )
)
```

**Downloadable font** (via Android Downloadable Fonts — requires `INTERNET` permission and a `<provider>` in the manifest):
```kotlin
OrangeTheme(
    orangeFontFamily = OrangeFontFamily(
        latin = OrangeHelveticaNeueLatin.Downloadable
    )
)
// Call once at startup:
OrangeFontFamily.preloadDownloadableFontFamilies(context, listOf(OrangeHelveticaNeueLatin.Downloadable)) {
    // Update UI state when ready
}
```

### OrangeTheme — optional rounded corner settings

```kotlin
OrangeTheme(
    orangeFontFamily = ...,
    roundedCornerButtons = true,
    roundedCornerTextInputs = true,
    roundedCornerAlertMessages = true,
    roundedCornerProgressIndicators = true
)
```

---

## 3. Accessing tokens inside composables

Tokens are accessed via the `OudsTheme` static object inside any composable wrapped by `OudsTheme { }`:

```kotlin
import com.orange.ouds.core.theme.OudsTheme

@Composable
fun MyView() {
    Box(
        modifier = Modifier
            .background(OudsTheme.colorScheme.background.primary)
            .padding(OudsTheme.spaces.fixed.medium)
    ) {
        Text(
            text = stringResource(R.string.my_text),
            color = OudsTheme.colorScheme.content.default,
            style = OudsTheme.typography.body.medium.default
        )
    }
}
```

### Token namespaces

| Accessor | Content |
|---|---|
| `OudsTheme.colorScheme` | Color tokens (`.content.*`, `.background.*`, `.border.*`, `.action.*`, `.surface.*`, `.overlay.*`) |
| `OudsTheme.borders` | Border radius (`.radius.*`), style (`.style.*`), width (`.width.*`) |
| `OudsTheme.spaces` | Spacing tokens (`.fixed.*`, `.scaled.*`) |
| `OudsTheme.sizes` | Size tokens |
| `OudsTheme.typography` | Typography / font tokens |
| `OudsTheme.elevations` | Elevation / shadow tokens |
| `OudsTheme.grids` | Grid tokens |
| `OudsTheme.opacities` | Opacity tokens |
| `OudsTheme.effects` | Visual effect tokens |
| `OudsTheme.components` | Component-level tokens for advanced customization (requires `@OptIn(RestrictedOudsApi::class)`) |

---

## 4. OudsColoredBox — colored surfaces

`OudsColoredBox` creates a semantically colored surface. All OUDS child components inside automatically switch to their **monochrome** variant for maximum contrast:

```kotlin
OudsColoredBox(color = OudsColoredBoxColor.StatusInfoEmphasized) {
    // OudsButton inside uses monochrome colors automatically
    OudsButton(label = stringResource(R.string.action), onClick = { })
    Text(
        text = stringResource(R.string.description),
        color = OudsTheme.colorScheme.content.default  // automatically inverted
    )
}
```

---

## 5. Common patterns

### Tinted vs. untinted icons

By default, icons passed to OUDS components are **tinted** (color driven by tokens). Pass `tinted = false` to preserve the painter's own colors (brand/multi-color icons):

```kotlin
// Tinted icon (default)
OudsButtonIcon(imageVector = Icons.Filled.FavoriteBorder, contentDescription = "Favorite")

// Untinted icon — painter colors preserved
OudsButtonIcon(painter = myMultiColorPainter, contentDescription = "Brand", tinted = false)
```

The same `tinted` flag is available on `OudsControlItemIcon`, `OudsLinkIcon`, `OudsTagAsset.Icon`, `OudsAlertIcon`, `OudsBadgeIcon`, etc.

### Error and helper text

Input components accept both plain strings and rich `AnnotatedString` for error and helper text:

```kotlin
// Plain error
error = OudsError(message = "This field cannot be empty.")

// Rich annotated error
error = OudsError(
    annotatedMessage = buildOudsAnnotatedErrorMessage {
        append("This field ")
        withStrong { append("cannot") }
        append(" be empty.")
    }
)

// Plain helper text
helperText = "Minimum 8 characters."

// Rich annotated helper text
helperText = buildOudsAnnotatedHelperText {
    append("Password must be ")
    withStrong { append("at least 8 characters") }
    append(" long.")
}
```

### Hardcoded strings — forbidden

Never use hardcoded strings in OUDS components or any composable:

```kotlin
// Wrong
OudsButton(label = "Submit", onClick = { })

// Correct
OudsButton(label = stringResource(R.string.submit), onClick = { })
```

---

## 6. Checklist before writing components

- `OudsTheme { }` wraps the UI at the root
- All user-visible strings use `stringResource(R.string.*)`
- Icons that should preserve their original colors have `tinted = false`
- Content descriptions are provided for all icon-only elements
- Components inside `OudsColoredBox` do **not** need manual color adjustment
- `OudsButtonAppearance.Negative` must **not** be used inside `OudsColoredBox`
- A disabled component must **not** have a loader simultaneously

---

## 7. Components reference

See [`references/components.md`](references/components.md) for the full list of components with signatures and usage examples.

**Index:** [Button](#button) · [SmallButton](#smallbutton) · [Tag](#tag) · [Badge](#badge) · [AlertMessage](#alertmessage) · [InlineAlert](#inlinealert) · [BulletList](#bulletlist) · [CheckboxItem](#checkboxitem) · [RadioButtonItem](#radiobuttonitem) · [SwitchItem](#switchitem) · [TextInput](#textinput) · [TextArea](#textarea) · [PasswordInput](#passwordinput) · [PinCodeInput](#pincodeinput) · [FilterChip / SuggestionChip](#filterchip--suggestionchip) · [Link](#link) · [Divider](#divider) · [NavigationBar](#navigationbar) · [TopAppBar](#topappbar) · [ColoredBox](#coloredbox)
