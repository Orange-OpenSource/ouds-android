# Developer guide

## Documentation

OUDS Android documentation is generated using [Dokka](https://github.com/Kotlin/dokka).

You can test the documentation locally by launching the `dokkaGenerate` Gradle task.

```
./gradlew dokkaGenerate
```

When generated, click on the HTML link provided by the task: [http://localhost:63342/OUDS%20Android/core/build/dokka/html/index.html](http://localhost:63342/OUDS%20Android/core/build/dokka/html/index.html).

## Tests

Each component offered by OUDS Android has two kinds of tests: Android instrumented tests and [Paparazzi](https://github.com/cashapp/paparazzi) tests.

The instrumented tests are in `core/src/androidTest` and allow to test interactions and behaviors.

The [Paparazzi](https://github.com/cashapp/paparazzi) tests are in `core/src/test`. They generate snapshots of the components in their various
state (pressed, hover, focused, disabled, ...) and compare them to reference snapshots in order to identify possible regressions.  
You can generate reference snapshots of the components by launching the `recordPaparazzi` or the `cleanRecordPaparazzi` Gradle task.

```
./gradlew recordPaparazzi
```

## API Versioning

OUDS Android follows [Semantic Versioning](https://semver.org/) and maintains binary compatibility within major versions. This section outlines the conventions and tools we use to track API changes.

### Semantic Versioning

OUDS Android uses semantic versioning (`MAJOR.MINOR.PATCH`):

- **PATCH** (`X.Y.Z+1`) - Bug fixes, internal improvements (no API changes)
- **MINOR** (`X.Y+1.0`) - New features, new APIs (backward compatible)
- **MAJOR** (`X+1.0.0`) - Breaking changes (see MIGRATION_GUIDE.md)

Breaking changes should:
- Be documented in `MIGRATION_GUIDE.md` with clear migration steps
- Include code examples showing before/after patterns
- Provide context on why the change was necessary
- Be grouped together in major version releases when possible

### Binary Compatibility

OUDS Android maintains both binary and source compatibility within major versions using the [Kotlin Binary Compatibility Validator](https://github.com/Kotlin/binary-compatibility-validator).

The validator tracks the public API surface of each published module by generating `.api` files that contain the binary signatures of all public declarations.

**Key tasks:**

- **`./gradlew :module:releaseApiDump`** - Generates or updates the API dump file for a specific module (e.g., `./gradlew :core:releaseApiDump`)
- **`./gradlew :module:releaseApiCheck`** - Checks that the current public API matches the committed API dump file

**When to use:**

- Run `releaseApiDump` after adding new public APIs or modifying existing ones
- Run `releaseApiCheck` in CI/CD to ensure no unintended API changes
- API dump files are located in each module's `api/` directory (e.g., `core/api/core.api`)

When adding new parameters to existing constructors or methods, use `@IntroducedAt` with `@OptIn(ExperimentalVersionOverloading::class)` to maintain binary compatibility while tracking when the parameter was introduced.

### @IntroducedAt Annotation

Use the `@IntroducedAt` annotation to mark when new parameters were introduced.

**Format:**
- Released versions: `@IntroducedAt("X.Y.Z")` (e.g., `@IntroducedAt("1.6.0")`)
- Unreleased features: `@IntroducedAt("X.Y.Z-Unreleased")` (automatically updated during release)

**Example:**
```kotlin
constructor(
    painter: Painter,
    contentDescription: String,
    @IntroducedAt("1.6.0") tinted: Boolean = true
) : this(painter as Any, contentDescription, tinted)
```

During the release process, the `prepareRelease` task automatically converts all `@IntroducedAt("X.Y.Z-Unreleased")` annotations to the actual release version and validates that all annotations use valid semantic version format.

### Release Process

The `@IntroducedAt` annotations integrate with our release automation:

1. During development, mark new APIs with `@IntroducedAt("X.Y.Z-Unreleased")`
2. The `prepareRelease` task validates and updates annotations to the actual version
3. Released versions become part of the public API contract

For detailed release procedures, see [release/RELEASE.md](../release/RELEASE.md).

