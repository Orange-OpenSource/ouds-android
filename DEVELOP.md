# Developer guide

## Documentation

OUDS Android documentation is generated using [Dokka](https://github.com/Kotlin/dokka).

You can test the documentation locally by launching the `dokkaGenerate` Gradle task.

```
./gradlew dokkaGenerate
```

When generated, open `ouds-android/docs/dokka/index.html` in your browser.

## Tests

Each component offered by OUDS Android has two kinds of tests: Android instrumented tests and [Paparazzi](https://github.com/cashapp/paparazzi) tests.

The instrumented tests are in `ouds-android/core/src/androidTest` and allow to test interactions and behaviors.

The [Paparazzi](https://github.com/cashapp/paparazzi) tests are in `ouds-android/core/src/test`. They generate snapshots of the components in their various
state (pressed, hover, focused, disabled, ...). These snapshots are compared on CI in order to identify possible regressions.  
You can generate components snapshots by launching the `recordPaparazzi` or the `cleanRecordPaparazzi` Gradle task.

```
./gradlew recordPaparazzi
```

