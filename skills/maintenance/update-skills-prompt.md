# Update OUDS Android Skills Prompt

You are tasked with updating the OUDS Android skills to keep them synchronized with code evolution. Analyze the OUDS Android codebase and identify all changes
since the last skill update, then update the skills accordingly.

## CONTEXT

The skills are reference guides for AI agents assisting with OUDS Android development. They are located in `skills/` and include:

1. **understanding-ouds-android-vocabulary/** — OUDS terminology and concepts
2. **using-ouds-android/** — Practical usage guide (setup, components, tokens, patterns)
    - Reference files in `references/`: components by category, tokens, index

## OBJECTIVE

Identify and integrate into the skills all of the following changes:

- ✅ **New components** (e.g., OudsTooltip, OudsSnackbar)
- ✅ **API modifications** (new parameters, options, appearances)
- ✅ **New tokens** (semantic, component, or new token categories)
- ✅ **New themes** (e.g., OudsInverseTheme)
- ✅ **Integration changes** (Gradle setup, OudsTheme configuration)
- ✅ **New patterns** (best practices, troubleshooting)
- ✅ **Deprecations** (APIs to remove)

## SOURCES OF TRUTH (in priority order)

1. **Kotlin source code** — `.kt` files in `:core/src/main/`, `:theme-contract/src/main/`, `:theme-*/src/main/`
2. **Component samples** — Sample functions in `:core/src/main/java/com/orange/ouds/core/component/samples/` referenced via `@sample` annotations in component
   KDoc
3. **KDoc documentation** — Comments in source code (usage signals, parameters, examples)
4. **AGENTS.md** — Architectural source of truth

## DETAILED INSTRUCTIONS

### Step 1: Discovery of Changes

1. **Components**: List all `Ouds*` composables in `:core/src/main/java/com/orange/ouds/core/component/`
    - Compare with `using-ouds-android/references/components-index.md`
    - Identify **new** components absent from the index
    - Identify **removed** components (present in index but absent from code)
    - Identify components whose signatures have changed (new/removed parameters, new enums)

2. **Component samples**: For each component, check `:core/src/main/java/com/orange/ouds/core/component/samples/`
    - Identify sample files (e.g., `OudsButtonSamples.kt`, `OudsTagSamples.kt`)
    - Read the component's KDoc to find `@sample` annotations
    - Extract the referenced sample functions to use as skill examples
    - Note: Sample files contain `@Composable` functions with naming pattern `Ouds[Component][Variant]Sample`

3. **Tokens**: Explore `:theme-contract/src/main/java/com/orange/ouds/theme/tokens/`
    - Check for new token interfaces (e.g., `OudsXxxTokens`)
    - Compare `OudsTheme.*` accessors with `using-ouds-android/references/tokens.md`
    - Identify **new** semantic tokens or component tokens
    - Identify **removed** tokens (present in skills but absent from code)

4. **Themes**: List `:theme-*` modules
    - Compare with the theme list in `using-ouds-android/SKILL.md` section 2
    - Identify **new** themes
    - Identify **removed** themes
    - Check for new/removed configuration options (e.g., `roundedCorner*` parameters)

5. **Setup & integration**: Check `:core/build.gradle.kts`, README.md
    - Identify Maven dependency changes, versions, setup

### Step 2: Categorization of Changes

For each identified change, determine:

- **Impact**: Major (new component, breaking change) vs. Minor (new optional parameter)
- **Destination**: Which skill and which file to update?
    - New component → `using-ouds-android/references/[category]-components.md` + `components-index.md`
    - New token → `using-ouds-android/references/tokens.md` or `component-tokens.md`
    - New concept → `understanding-ouds-android-vocabulary/SKILL.md`
    - New pattern → `using-ouds-android/SKILL.md` appropriate sections

#### Vocabulary terms exclusion policy

**DO NOT add to `understanding-ouds-android-vocabulary/SKILL.md`:**
- **Any OUDS composable component**, even if it has special behavior (e.g., `OudsColoredBox`, `OudsButton`, `OudsTag`) — components belong in `using-ouds-android/references/` only
- Component-specific parameter classes and wrapper types (e.g., `OudsListItemLeading`, `OudsTextInputLeadingIcon`, `OudsError`, `OudsButtonIcon`, `OudsControlItemIcon`)
- Enum classes that are component-specific configuration options (e.g., `OudsButtonAppearance`, `OudsTagStatus`, `OudsBulletListType`)
- Builder pattern helper classes
- Parameter flags and options (e.g., `tinted`, `enabled`, `boldLabel`)
- Any class that serves as a parameter type in component APIs

**DO add to vocabulary:**
- High-level framework entry points that are **not components** (e.g., `OudsTheme`, `OudsThemeContract`)
- Core architectural terms (e.g., `token`, `Tokenator`, `raw token`, `semantic token`, `component token`)
- Top-level abstractions (e.g., `theme`, `component`)

**Rule of thumb**: The vocabulary should explain "how OUDS Android architecture works at a high level", not "how to use specific components or their parameters". Components (Ouds* composables) are documented exclusively in `using-ouds-android/references/`, never in vocabulary.

### Step 3: Update (MINIMAL level of detail)

For each change, apply modifications with a MINIMAL level of detail:

**New components**:

- Add to `components-index.md` with link to appropriate category
- Create a section in the category file (e.g., `action-components.md`)
- **Extract examples from component samples**:
    - Locate `:core/src/main/java/com/orange/ouds/core/component/samples/Ouds[Component]Samples.kt`
    - Find samples referenced in the component's KDoc via `@sample` annotations
    - Copy sample functions **as-is** (keep exact code, parameters, and structure)
    - Remove only preview-specific annotations (`@PreviewLightDark`, `@Composable`, `internal fun`)
    - Keep hardcoded strings, comments, and all other code unchanged
- Include ONLY:
    - Name and 1-line description
    - Available layouts/appearances/sizes
    - 2-3 basic examples **copied directly from official samples**
    - Note about special component interactions if applicable (e.g., "Negative appearance forbidden in OudsColoredBox")

**API modifications**:

- Add the new parameter with a minimal example
- If breaking change, add migration note

**New tokens**:

- Add to the appropriate table in `tokens.md` or `component-tokens.md`
- Format: `| OudsTheme.xxx | Concise description |`

**New themes**:

- Add to the available themes table (section 2 of `using-ouds-android/SKILL.md`)
- Include minimal setup example

**New patterns**:

- Add to section 5 "Common patterns" of `using-ouds-android/SKILL.md`
- OR section 7 "Common mistakes" if it's troubleshooting

**Deprecations**:

- Remove from skills EXCEPT if migration needed → add migration note
- Check all references across all files

### Step 4: Consistency and Validation

1. **Cross-references**: Verify that all internal links remain valid
2. **Naming conventions**: All components use the `Ouds*` prefix
3. **Sample correspondence**: For each component in the skills, verify at least one example corresponds to an official sample from `:core/.../samples/`
4. **Accessibility**: Icon-only examples include `contentDescription`
5. **Structure**: New components follow the same format as existing ones
6. **Removed items**: Verify that removed components/tokens/classes have been deleted from all skill files
7. **Vocabulary scope**: Verify that `understanding-ouds-android-vocabulary/SKILL.md` contains ONLY high-level architectural concepts (5-10 core terms max), not component-specific classes, parameter types, or configuration options

### Step 5: Report

Generate a structured report of modifications:

```markdown
# OUDS Android Skills Update — [Date]

## Changes Detected

### New Components

- `OudsTooltip` → added to `indicator-components.md`
- `OudsSnackbar` → added to `dialog-components.md`

### API Modifications

- `OudsButton`: new parameter `badge: OudsBadge?` → updated in `action-components.md`

### New Tokens

- `OudsTheme.tooltips` → added to `tokens.md`

### New Themes

- None

### Integration Changes

- New Maven version: 1.2.0 → updated in "Gradle setup" section

### New Patterns

- Pattern "Badge on button" → added to "Common patterns"

### Deprecations

- `OudsOldComponent` → removed from `components-index.md`

### Removed Components

- `OudsOldButton` → removed from `action-components.md` and `components-index.md`

### Removed Tokens

- `OudsTheme.oldProperty` → removed from `tokens.md`

## Modified Files

- `skills/using-ouds-android/SKILL.md`
- `skills/using-ouds-android/references/action-components.md`
- `skills/using-ouds-android/references/components-index.md`
- `skills/using-ouds-android/references/tokens.md`
- `skills/understanding-ouds-android-vocabulary/SKILL.md`

## Validation

✅ All internal links verified
✅ Examples extracted from official samples in `:core/.../samples/`
✅ Format consistent with existing skills
✅ Cross-references updated
✅ Removed items deleted from all skill files
```

## IMPORTANT RULES

- ✅ **USE official samples** — Always extract examples from `:core/.../samples/` files, keeping code as-is
- ✅ **BIDIRECTIONAL detection** — Detect both additions (new items) and removals (items present in skills but absent from code)
- ❌ **NO superlatives** ("amazing", "powerful") — stay factual
- ❌ **NO duplication** — if info exists elsewhere, create a link
- ✅ **MINIMAL level** — only the essentials, no verbosity
- ✅ **Consistency** — follow exactly the format of existing skills
- ✅ **Precision** — copy exact names of classes, parameters, tokens from source code

Begin the analysis and generate the update report.
