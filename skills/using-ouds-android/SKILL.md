---
name: using-ouds-android
description: Use this skill whenever the user needs to write code using OUDS Android components, set up the library, configure themes, access design tokens, debug OUDS code, or fix OUDS-related issues in Kotlin/Compose. This includes adding dependencies, wrapping UI with OudsTheme, creating any Ouds* component (OudsButton, OudsTextInput, OudsNavigationBar, OudsTag, OudsCheckbox, etc.), accessing tokens via OudsTheme.colorScheme/spaces/typography/borders, configuring fonts, handling tinted/untinted icons, using OudsColoredBox, showing error messages with OudsError, switching themes dynamically, troubleshooting component behavior, styling with component tokens, or migrating from Material 3 to OUDS. ALWAYS trigger when the user asks to 'create', 'show me how', 'write a composable', 'set up', 'access', 'fix', 'debug', 'implement', 'migrate', 'add', 'use', 'build', or 'style' anything related to OUDS components or tokens, even if they don't explicitly mention 'OUDS framework' or 'design system'. Also trigger when they want examples, code samples, or ask how to do something UI-related in the context of an OUDS-based Android app.
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

| Theme class          | Brand          | Notes                                                  |
|----------------------|----------------|--------------------------------------------------------|
| `OrangeTheme`        | Orange         | Requires Helvetica Neue font (bundled or downloadable) |
| `OrangeCompactTheme` | Orange Compact | Compact size variant of Orange                         |
| `SoshTheme`          | Sosh           | —                                                      |
| `WireframeTheme`     | Wireframe      | For development and prototyping only                   |

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

| Accessor                | Content                                                                                            |
|-------------------------|----------------------------------------------------------------------------------------------------|
| `OudsTheme.colorScheme` | Color tokens (`.content.*`, `.background.*`, `.border.*`, `.action.*`, `.surface.*`, `.overlay.*`) |
| `OudsTheme.borders`     | Border radius (`.radius.*`), style (`.style.*`), width (`.width.*`)                                |
| `OudsTheme.spaces`      | Spacing tokens (`.fixed.*`, `.scaled.*`)                                                           |
| `OudsTheme.sizes`       | Size tokens                                                                                        |
| `OudsTheme.typography`  | Typography / font tokens                                                                           |
| `OudsTheme.elevations`  | Elevation / shadow tokens                                                                          |
| `OudsTheme.grids`       | Grid tokens                                                                                        |
| `OudsTheme.opacities`   | Opacity tokens                                                                                     |
| `OudsTheme.effects`     | Visual effect tokens                                                                               |
| `OudsTheme.components`  | Component-level tokens for advanced customization (requires `@OptIn(RestrictedOudsApi::class)`)    |

---

## 4. OudsColoredBox — colored surfaces

`OudsColoredBox` creates a semantically colored surface. All OUDS child components inside automatically switch to their **monochrome** variant for maximum
contrast:

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

By default, icons passed to OUDS components are **tinted** (color driven by tokens). Pass `tinted = false` to preserve the painter's own colors (
brand/multi-color icons):

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

## 7. Common mistakes and troubleshooting

### Missing OudsTheme wrapper

**Problem:** Components don't display correctly, colors are wrong, or app crashes with "CompositionLocal LocalOudsTheme not present"

**Solution:** Ensure your UI is wrapped with `OudsTheme { }` at the root:

```kotlin
@Composable
fun App() {
    OudsTheme(theme = OrangeTheme(...)) {
        // Your composables here
    }
}
```

### Hardcoded strings

**Problem:** Strings appear directly in code instead of using resources

**Solution:** Always use `stringResource(R.string.*)`:

```kotlin
// Wrong
OudsButton(label = "Submit", onClick = { })

// Correct
OudsButton(label = stringResource(R.string.submit), onClick = { })
```

### OudsButtonAppearance.Negative inside OudsColoredBox

**Problem:** App crashes or displays incorrectly when using `OudsButtonAppearance.Negative` inside `OudsColoredBox`

**Solution:** The `Negative` appearance is forbidden inside `OudsColoredBox`. Use `Default`, `Strong`, `Brand`, or `Minimal` instead. The button will
automatically adopt its monochrome variant inside `OudsColoredBox`.

### Disabled component with loader

**Problem:** A component is both disabled and showing a loader simultaneously

**Solution:** A disabled component must not have a loader. Choose one state:

```kotlin
// Wrong
OudsButton(
    label = stringResource(R.string.action),
    enabled = false,
    loader = OudsButtonLoader(progress = null),
    onClick = { }
)

// Correct - either disabled OR loading
OudsButton(
    label = stringResource(R.string.action),
    loader = OudsButtonLoader(progress = null),
    onClick = { }
)
```

### Icon not tinted correctly

**Problem:** Icon appears in wrong color or doesn't match theme

**Solution:** By default, icons are tinted (color driven by tokens). For brand/multi-color icons, set `tinted = false`:

```kotlin
// Tinted icon (default) - color from tokens
OudsButtonIcon(imageVector = Icons.Filled.Star, contentDescription = "")

// Untinted icon - preserves painter's colors
OudsButtonIcon(painter = myBrandIcon, contentDescription = "", tinted = false)
```

### Missing content description

**Problem:** Accessibility warnings or icon-only components without descriptions

**Solution:** Always provide `contentDescription` for icon-only elements:

```kotlin
// Wrong
OudsButton(
    icon = OudsButtonIcon(imageVector = Icons.Filled.Star, contentDescription = ""),
    onClick = { }
)

// Correct
OudsButton(
    icon = OudsButtonIcon(
        imageVector = Icons.Filled.Star,
        contentDescription = stringResource(R.string.favorite_desc)
    ),
    onClick = { }
)
```

### Font not loaded for OrangeTheme

**Problem:** Text doesn't display in Helvetica Neue, falls back to system font

**Solution:** For `OrangeTheme`, you must provide the Helvetica Neue font either as bundled (`.ttf` files in `res/font/`) or downloadable:

```kotlin
// Bundled font (recommended)
OrangeTheme(
    orangeFontFamily = OrangeFontFamily(
        latin = OrangeHelveticaNeueLatin.Bundled(
            R.font.helvetica_neue_latin_roman,
            R.font.helvetica_neue_latin_medium,
            R.font.helvetica_neue_latin_bold
        )
    )
)

// Downloadable font (requires INTERNET permission)
OrangeTheme(
    orangeFontFamily = OrangeFontFamily(
        latin = OrangeHelveticaNeueLatin.Downloadable
    )
)
```

### Token access outside OudsTheme

**Problem:** `OudsTheme.colorScheme` or other token accessors return unexpected values or crash

**Solution:** Token accessors only work inside composables wrapped by `OudsTheme { }`. Move token access inside the theme wrapper:

```kotlin
// Wrong - outside OudsTheme
val color = OudsTheme.colorScheme.background.primary
OudsTheme(theme = OrangeTheme(...)) {
    Box(modifier = Modifier.background(color))
}

// Correct - inside OudsTheme
OudsTheme(theme = OrangeTheme(...)) {
    val color = OudsTheme.colorScheme.background.primary
    Box(modifier = Modifier.background(color))
}
```

### Error message not displaying

**Problem:** Error passed to input component but not showing

**Solution:** Ensure you're using `OudsError` wrapper with either `message` or `annotatedMessage`:

```kotlin
// Correct - plain error
error = OudsError(message = stringResource(R.string.error_required))

// Correct - rich annotated error
error = OudsError(
    annotatedMessage = buildOudsAnnotatedErrorMessage {
        append(stringResource(R.string.error_prefix))
        withStrong { append(stringResource(R.string.error_highlight)) }
    }
)
```

---

## 8. Reference documentation

### Components reference

Component documentation is organized by category. When the user asks about specific components, consult the relevant reference file:

- **Action components** (buttons, FAB): [`references/action-components.md`](references/action-components.md)
    - Button, FloatingActionButton, NavigationButton, SmallButton

- **Alert components** (alerts, messages): [`references/alert-components.md`](references/alert-components.md)
    - AlertMessage, InlineAlert

- **Content components** (lists): [`references/content-components.md`](references/content-components.md)
    - BulletList

- **Control components** (checkboxes, switches, chips): [`references/control-components.md`](references/control-components.md)
    - Checkbox, CheckboxItem, RadioButton, RadioButtonItem, Switch, SwitchItem, FilterChip, SuggestionChip

- **Indicator components** (badges, progress, tags): [`references/indicator-components.md`](references/indicator-components.md)
    - Badge, CircularProgressIndicator, LinearProgressIndicator, Tag

- **Input components** (text fields): [`references/input-components.md`](references/input-components.md)
    - TextInput, TextArea, PasswordInput, PinCodeInput

- **Layout components** (containers, dividers): [`references/layout-components.md`](references/layout-components.md)
    - BottomSheetScaffold, ColoredBox, Divider, ModalBottomSheet

- **Navigation components** (links, bars): [`references/navigation-components.md`](references/navigation-components.md)
    - Link, NavigationBar, TopAppBar

**Complete component index:** See [`references/components-index.md`](references/components-index.md) for a full cross-reference of all components.

### Tokens reference

- **Semantic tokens:** See [`references/tokens.md`](references/tokens.md) for color scheme, typography, spacing, sizes, borders, elevations, opacities, effects,
  and grids
- **Component tokens:** See [`references/component-tokens.md`](references/component-tokens.md) for advanced component-level tokens (`@RestrictedOudsApi`)
