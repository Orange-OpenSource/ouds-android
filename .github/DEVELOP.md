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

