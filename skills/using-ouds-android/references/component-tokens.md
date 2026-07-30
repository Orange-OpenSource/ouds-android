# OUDS Android — Component Tokens Reference

**Restricted API** (`@RestrictedOudsApi`) — For advanced component customization.

Component tokens provide granular control over the appearance of OUDS components. These tokens are designed for building custom components or extending existing OUDS components with precise design specifications.

> **Important:** Most developers should use [semantic tokens](tokens.md) and standard OUDS components. Only use component tokens when you have specific customization needs that cannot be met with the standard API.

---

## When to Use Component Tokens

**Use component tokens when:**
- Building custom components that need to align with OUDS design language
- Extending existing OUDS components with additional functionality
- Implementing precise design specifications that require component-level token values
- Creating reusable component patterns within your design system

**Do NOT use component tokens when:**
- Standard OUDS components (e.g., `OudsButton`, `OudsChip`, `OudsTextInput`) meet your needs
- Building typical UI layouts — use [semantic tokens](tokens.md) instead
- You're unsure whether component tokens are needed — start with semantic tokens first

---

## Accessing Component Tokens

Component tokens are accessed via `OudsTheme.components` and require opt-in to the restricted API:

```kotlin
import com.orange.ouds.core.theme.OudsTheme
import com.orange.ouds.theme.tokens.utils.RestrictedOudsApi

@OptIn(RestrictedOudsApi::class)
@Composable
fun MyCustomComponent() {
    val buttonPadding = OudsTheme.components.button.space.paddingInline.medium
    val buttonRadius = OudsTheme.components.button.border.radius.default
    
    // Use component tokens for precise customization
}
```

---

## Available Component Token Categories

### Action Components

```kotlin
// Button tokens
OudsTheme.components.button.border.radius.default
OudsTheme.components.button.border.radius.rounded
OudsTheme.components.button.border.width
OudsTheme.components.button.color.background.*
OudsTheme.components.button.color.border.*
OudsTheme.components.button.color.content.*
OudsTheme.components.button.elevation.*
OudsTheme.components.button.opacity.*
OudsTheme.components.button.size.height.*
OudsTheme.components.button.size.icon.*
OudsTheme.components.button.size.minWidth.*
OudsTheme.components.button.space.columnGap.*
OudsTheme.components.button.space.paddingInline.*
OudsTheme.components.button.typography.*

// Monochrome button variant
OudsTheme.components.buttonMonochrome.color.background.*
OudsTheme.components.buttonMonochrome.color.border.*
OudsTheme.components.buttonMonochrome.color.content.*

// Link tokens
OudsTheme.components.link.color.content.*
OudsTheme.components.link.size.icon.*
OudsTheme.components.link.space.columnGap.*
OudsTheme.components.link.typography.*

// Monochrome link variant
OudsTheme.components.linkMonochrome.color.content.*
```

### Indicator Components

```kotlin
// Badge tokens
OudsTheme.components.badge.border.radius
OudsTheme.components.badge.color.background.*
OudsTheme.components.badge.color.content.*
OudsTheme.components.badge.size.height.*
OudsTheme.components.badge.size.icon.*
OudsTheme.components.badge.size.minWidth.*
OudsTheme.components.badge.size.width.*
OudsTheme.components.badge.space.paddingInline.*
OudsTheme.components.badge.typography.*

// Tag tokens
OudsTheme.components.tag.border.radius.default
OudsTheme.components.tag.border.radius.rounded
OudsTheme.components.tag.border.width
OudsTheme.components.tag.color.background.*
OudsTheme.components.tag.color.border.*
OudsTheme.components.tag.color.content.*
OudsTheme.components.tag.size.height.*
OudsTheme.components.tag.size.icon.*
OudsTheme.components.tag.space.columnGap.*
OudsTheme.components.tag.space.paddingInline.*
OudsTheme.components.tag.typography.*

// Progress indicator tokens
OudsTheme.components.progressIndicator.border.radius.default
OudsTheme.components.progressIndicator.border.radius.rounded
OudsTheme.components.progressIndicator.color.background.*
OudsTheme.components.progressIndicator.color.content.*
OudsTheme.components.progressIndicator.size.height.*
OudsTheme.components.progressIndicator.size.width.*
OudsTheme.components.progressIndicator.space.rowGap.*
```

### Alert Components

```kotlin
// Alert message tokens
OudsTheme.components.alert.border.radius.default
OudsTheme.components.alert.border.radius.rounded
OudsTheme.components.alert.border.width
OudsTheme.components.alert.color.background.*
OudsTheme.components.alert.color.border.*
OudsTheme.components.alert.color.content.*
OudsTheme.components.alert.size.icon
OudsTheme.components.alert.size.minHeight
OudsTheme.components.alert.size.minHeightBottomActionPlacement
OudsTheme.components.alert.size.minWidth
OudsTheme.components.alert.space.columnGap
OudsTheme.components.alert.space.columnGapAction
OudsTheme.components.alert.space.paddingBlock
OudsTheme.components.alert.space.paddingInline
OudsTheme.components.alert.space.rowGap
OudsTheme.components.alert.space.rowGapAction
OudsTheme.components.alert.space.rowGapBullet
```

### Control Components

```kotlin
// Checkbox tokens
OudsTheme.components.checkbox.border.radius
OudsTheme.components.checkbox.border.width.*
OudsTheme.components.checkbox.color.background.*
OudsTheme.components.checkbox.color.border.*
OudsTheme.components.checkbox.color.content.*
OudsTheme.components.checkbox.elevation.*
OudsTheme.components.checkbox.size.*

// Radio button tokens
OudsTheme.components.radioButton.border.width.*
OudsTheme.components.radioButton.color.background.*
OudsTheme.components.radioButton.color.border.*
OudsTheme.components.radioButton.color.content.*
OudsTheme.components.radioButton.elevation.*
OudsTheme.components.radioButton.size.*

// Switch tokens
OudsTheme.components.switch.border.width.*
OudsTheme.components.switch.color.background.*
OudsTheme.components.switch.color.border.*
OudsTheme.components.switch.color.content.*
OudsTheme.components.switch.elevation.*
OudsTheme.components.switch.size.*

// Control item tokens (CheckboxItem, RadioButtonItem, SwitchItem)
OudsTheme.components.controlItem.color.background.*
OudsTheme.components.controlItem.color.content.*
OudsTheme.components.controlItem.size.icon
OudsTheme.components.controlItem.space.columnGap
OudsTheme.components.controlItem.space.paddingBlock
OudsTheme.components.controlItem.space.paddingInline
OudsTheme.components.controlItem.space.rowGap
OudsTheme.components.controlItem.typography.*

// Chip tokens
OudsTheme.components.chip.border.radius
OudsTheme.components.chip.border.width
OudsTheme.components.chip.color.background.*
OudsTheme.components.chip.color.border.*
OudsTheme.components.chip.color.content.*
OudsTheme.components.chip.size.height.*
OudsTheme.components.chip.size.icon.*
OudsTheme.components.chip.space.columnGap.*
OudsTheme.components.chip.space.paddingInline.*
OudsTheme.components.chip.typography.*
```

### Input Components

```kotlin
// Text input tokens
OudsTheme.components.textInput.border.radius.default
OudsTheme.components.textInput.border.radius.rounded
OudsTheme.components.textInput.border.width
OudsTheme.components.textInput.color.background.*
OudsTheme.components.textInput.color.border.*
OudsTheme.components.textInput.color.content.*
OudsTheme.components.textInput.opacity.*
OudsTheme.components.textInput.size.height
OudsTheme.components.textInput.size.icon
OudsTheme.components.textInput.space.columnGap
OudsTheme.components.textInput.space.paddingBlock
OudsTheme.components.textInput.space.paddingInline
OudsTheme.components.textInput.space.rowGap
OudsTheme.components.textInput.typography.*

// Text area tokens
OudsTheme.components.textArea.border.radius.default
OudsTheme.components.textArea.border.radius.rounded
OudsTheme.components.textArea.border.width
OudsTheme.components.textArea.color.background.*
OudsTheme.components.textArea.color.border.*
OudsTheme.components.textArea.color.content.*
OudsTheme.components.textArea.opacity.*
OudsTheme.components.textArea.size.minHeight
OudsTheme.components.textArea.space.paddingBlock
OudsTheme.components.textArea.space.paddingInline
OudsTheme.components.textArea.space.rowGap
OudsTheme.components.textArea.typography.*

// PIN code input tokens
OudsTheme.components.pinCodeInput.border.radius.default
OudsTheme.components.pinCodeInput.border.radius.rounded
OudsTheme.components.pinCodeInput.border.width
OudsTheme.components.pinCodeInput.color.background.*
OudsTheme.components.pinCodeInput.color.border.*
OudsTheme.components.pinCodeInput.color.content.*
OudsTheme.components.pinCodeInput.opacity.*
OudsTheme.components.pinCodeInput.size.digitWidth
OudsTheme.components.pinCodeInput.size.height
OudsTheme.components.pinCodeInput.space.columnGap
OudsTheme.components.pinCodeInput.space.rowGap
OudsTheme.components.pinCodeInput.typography.*

// Input tag tokens
OudsTheme.components.inputTag.color.background.*
OudsTheme.components.inputTag.color.border.*
OudsTheme.components.inputTag.color.content.*
```

### Layout Components

```kotlin
// Divider tokens
OudsTheme.components.divider.color.background
OudsTheme.components.divider.size.height

// Top app bar tokens
OudsTheme.components.bar.topAppBar.color.background
OudsTheme.components.bar.topAppBar.color.content
OudsTheme.components.bar.topAppBar.elevation.*
OudsTheme.components.bar.topAppBar.size.actionIcon
OudsTheme.components.bar.topAppBar.size.height.*
OudsTheme.components.bar.topAppBar.size.navigationIcon
OudsTheme.components.bar.topAppBar.space.columnGap
OudsTheme.components.bar.topAppBar.space.paddingBlock
OudsTheme.components.bar.topAppBar.space.paddingInline
OudsTheme.components.bar.topAppBar.typography.*

// Navigation bar tokens
OudsTheme.components.bar.navigationBar.color.background
OudsTheme.components.bar.navigationBar.color.content.*
OudsTheme.components.bar.navigationBar.elevation
OudsTheme.components.bar.navigationBar.size.height
OudsTheme.components.bar.navigationBar.size.icon
OudsTheme.components.bar.navigationBar.space.rowGap
OudsTheme.components.bar.navigationBar.typography.*
```

### Content Components

```kotlin
// Bullet list tokens
OudsTheme.components.bulletList.color.content.*
OudsTheme.components.bulletList.size.bullet.*
OudsTheme.components.bulletList.size.icon
OudsTheme.components.bulletList.space.columnGap
OudsTheme.components.bulletList.space.indent
OudsTheme.components.bulletList.space.rowGap

// Icon tokens
OudsTheme.components.icon.color.content
```

---

## Component Token Examples

### Example 1: Custom Button with Component Tokens

```kotlin
@OptIn(RestrictedOudsApi::class)
@Composable
fun CustomBrandButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(OudsTheme.components.button.border.radius.rounded),
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.components.button.color.background.brand.enabled,
            contentColor = OudsTheme.components.button.color.content.brand.enabled
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = OudsTheme.components.button.elevation.default
        ),
        contentPadding = PaddingValues(
            horizontal = OudsTheme.components.button.space.paddingInline.medium,
            vertical = 0.dp
        )
    ) {
        Text(
            text = text,
            style = OudsTheme.components.button.typography.medium.strong
        )
    }
}
```

### Example 2: Custom Chip with Component Tokens

```kotlin
@OptIn(RestrictedOudsApi::class)
@Composable
fun CustomChip(
    text: String,
    icon: ImageVector? = null,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (selected) {
        OudsTheme.components.chip.color.background.outlined.selected
    } else {
        OudsTheme.components.chip.color.background.outlined.enabled
    }
    
    val contentColor = if (selected) {
        OudsTheme.components.chip.color.content.outlined.selected
    } else {
        OudsTheme.components.chip.color.content.outlined.enabled
    }
    
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(OudsTheme.components.chip.border.radius),
        color = backgroundColor,
        border = BorderStroke(
            width = OudsTheme.components.chip.border.width,
            color = OudsTheme.components.chip.color.border.outlined.enabled
        )
    ) {
        Row(
            modifier = Modifier
                .height(OudsTheme.components.chip.size.height.medium)
                .padding(horizontal = OudsTheme.components.chip.space.paddingInline.medium),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.components.chip.space.columnGap.icon),
            verticalAlignment = Alignment.CenterVertically
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(OudsTheme.components.chip.size.icon.outlined.medium),
                    tint = contentColor
                )
            }
            Text(
                text = text,
                style = OudsTheme.components.chip.typography.medium.default,
                color = contentColor
            )
        }
    }
}
```

### Example 3: Custom Text Input with Component Tokens

```kotlin
@OptIn(RestrictedOudsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    val borderColor = if (isError) {
        OudsTheme.components.textInput.color.border.error
    } else {
        OudsTheme.components.textInput.color.border.default
    }
    
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(
                text = label,
                style = OudsTheme.components.textInput.typography.label
            )
        },
        textStyle = OudsTheme.components.textInput.typography.content,
        shape = RoundedCornerShape(OudsTheme.components.textInput.border.radius.default),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = OudsTheme.components.textInput.color.background.default,
            unfocusedContainerColor = OudsTheme.components.textInput.color.background.default,
            focusedIndicatorColor = OudsTheme.components.textInput.color.border.focus,
            unfocusedIndicatorColor = borderColor,
            errorIndicatorColor = OudsTheme.components.textInput.color.border.error,
            focusedTextColor = OudsTheme.components.textInput.color.content.default,
            unfocusedTextColor = OudsTheme.components.textInput.color.content.default
        ),
        isError = isError
    )
}
```

### Example 4: Custom Alert Banner with Component Tokens

```kotlin
@OptIn(RestrictedOudsApi::class)
@Composable
fun CustomAlertBanner(
    title: String,
    message: String,
    icon: ImageVector,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(OudsTheme.components.alert.border.radius.default),
        color = OudsTheme.components.alert.color.background.info.emphasized,
        border = BorderStroke(
            width = OudsTheme.components.alert.border.width,
            color = OudsTheme.components.alert.color.border.info.emphasized
        )
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = OudsTheme.components.alert.space.paddingInline,
                    vertical = OudsTheme.components.alert.space.paddingBlock
                ),
            horizontalArrangement = Arrangement.spacedBy(OudsTheme.components.alert.space.columnGap)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(OudsTheme.components.alert.size.icon),
                tint = OudsTheme.components.alert.color.content.info.emphasized
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(OudsTheme.components.alert.space.rowGap)
            ) {
                Text(
                    text = title,
                    color = OudsTheme.components.alert.color.content.info.emphasized
                )
                Text(
                    text = message,
                    color = OudsTheme.components.alert.color.content.info.emphasized
                )
            }
            IconButton(onClick = onDismiss) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = OudsTheme.components.alert.color.content.info.emphasized
                )
            }
        }
    }
}
```

---

## Best Practices

### 1. Prefer Semantic Tokens When Possible

Before using component tokens, check if semantic tokens can achieve your goal:

```kotlin
// ❌ Avoid if unnecessary
@OptIn(RestrictedOudsApi::class)
val spacing = OudsTheme.components.button.space.paddingInline.medium

// ✅ Prefer semantic tokens
val spacing = OudsTheme.spaces.fixed.medium
```

### 2. Use Component Tokens for Component-Specific Values

Component tokens are ideal when you need values specific to a component's design:

```kotlin
@OptIn(RestrictedOudsApi::class)
// ✅ Good use case: component-specific height
val chipHeight = OudsTheme.components.chip.size.height.medium

// ✅ Good use case: component-specific color states
val chipBackground = OudsTheme.components.chip.color.background.outlined.selected
```

### 3. Document Your Custom Components

When creating custom components with component tokens, document your decisions:

```kotlin
/**
 * Custom branded chip component.
 * 
 * Uses component tokens for:
 * - Precise height matching OUDS chip specs
 * - Brand-specific color states
 * - Consistent padding and spacing
 * 
 * @param text Chip label text
 * @param selected Whether the chip is selected
 * @param onClick Callback when chip is clicked
 */
@OptIn(RestrictedOudsApi::class)
@Composable
fun BrandChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    // Implementation
}
```

### 4. Keep Component Token Usage Localized

Limit component token access to component files:

```kotlin
// ✅ Good: Component token usage in a dedicated component file
// file: CustomChip.kt
@OptIn(RestrictedOudsApi::class)
@Composable
fun CustomChip(...) {
    val chipHeight = OudsTheme.components.chip.size.height.medium
    // ...
}

// ❌ Avoid: Spreading component tokens across screen/page files
// file: HomeScreen.kt
@OptIn(RestrictedOudsApi::class)
@Composable
fun HomeScreen() {
    val chipHeight = OudsTheme.components.chip.size.height.medium  // Prefer CustomChip component
    // ...
}
```

---

## Migration from Semantic to Component Tokens

If you find yourself needing more control than semantic tokens provide, migrate gradually:

```kotlin
// Step 1: Start with semantic tokens
@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.colorScheme.action.enabled
        )
    ) {
        Text(text, style = OudsTheme.typography.label.large.strong)
    }
}

// Step 2: Identify specific needs (e.g., precise padding)
@OptIn(RestrictedOudsApi::class)
@Composable
fun MyButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OudsTheme.colorScheme.action.enabled
        ),
        contentPadding = PaddingValues(
            horizontal = OudsTheme.components.button.space.paddingInline.medium  // Component token
        )
    ) {
        Text(text, style = OudsTheme.typography.label.large.strong)  // Semantic token
    }
}
```

---

## References

- **Semantic Tokens:** See [`tokens.md`](tokens.md) for standard design tokens
- **Components:** See [`components.md`](components.md) for standard OUDS components
- **Documentation:** https://android.unified-design-system.orange.com/
- **Repository:** https://github.com/Orange-OpenSource/ouds-android
- **Design System:** https://unified-design-system.orange.com/
