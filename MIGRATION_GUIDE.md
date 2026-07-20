# Migration Guide

This guide helps you migrate between major versions of OUDS Android.

## Migrating to 2.0.0

### Breaking changes

#### Space inset token reorganization

The space inset token scale has been reorganized to align with the latest Orange Unified Design System specifications. A new larger `small` token has been introduced, and all smaller tokens have shifted down by one size level.

**Impact:** Applications using `OudsTheme.spaces.inset.*` properties will experience visual spacing changes unless code is updated.

##### Property name changes

| Old property (v1.x) | New property (v2.0) | Action required |
|---------------------|---------------------|-----------------|
| `small` | `extraSmall` | Replace `small` with `extraSmall` |
| `extraSmall` | `twoExtraSmall` | Replace `extraSmall` with `twoExtraSmall` |
| `twoExtraSmall` | `threeExtraSmall` | Replace `twoExtraSmall` with `threeExtraSmall` |
| `threeExtraSmall` | `fourExtraSmall` | Replace `threeExtraSmall` with `fourExtraSmall` |
| `fourExtraSmall` | `fiveExtraSmall` | Replace `fourExtraSmall` with `fiveExtraSmall` |
| N/A | `small` | New larger size available |

> **Note:** Actual spacing values depend on your theme (Orange, Sosh, etc.). The table above shows the property name mappings to maintain equivalent semantic sizes across the update.

##### Code examples

**Before (v1.x):**
```kotlin
Modifier.padding(OudsTheme.spaces.inset.small)
```

**After (v2.0):**
```kotlin
// To maintain the same semantic size:
Modifier.padding(OudsTheme.spaces.inset.extraSmall)

// Or use the new larger small size:
Modifier.padding(OudsTheme.spaces.inset.small)
```

##### Migration steps

1. **Search your codebase** for usage of `OudsTheme.spaces.inset.*` properties
2. **Review each usage** to determine the intended spacing value
3. **Update property names** according to the table above to maintain current spacing values
4. **Test visually** to ensure layouts appear as expected
5. **Consider using the new `small`** where a slightly larger spacing would improve your design

#### Link component: paddingBlock structure change

The `OudsComponents.Link.Space.paddingBlock` property has changed from a single `Dp` value to a structured object.

**Before (v1.x):**
```kotlin
val padding = OudsTheme.components.link.space.paddingBlock // Dp
```

**After (v2.0):**
```kotlin
// paddingBlock is now a data class with multiple properties:
val padding = OudsTheme.components.link.space.paddingBlock.default // Dp
// Available properties: default, small, compactDensityDefault, compactDensitySmall
```

##### Migration steps

1. **Find usages** of `OudsTheme.components.link.space.paddingBlock`
2. **Choose the appropriate property** based on your use case
3. **Update references** to access the specific property

---

## Version history

- **2.0.0** - Space inset token reorganization, Link paddingBlock structure change
- **1.x.x** - Previous stable releases
